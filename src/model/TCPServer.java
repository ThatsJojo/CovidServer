package model;

import controller.ConnectionController;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer extends Thread {

    private final String ip;
    private final int port;
    private ServerSocket server;
    private boolean connected;
    private final ConnectionController myController;

    public TCPServer(ConnectionController myController, String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.myController = myController;
    }

    @Override
    public void start() {
        try {
            connected = true;
            server = new ServerSocket(port, 0, InetAddress.getByName(ip));
            while (connected) {    
                Socket socket = server.accept();
                System.out.println("CONECTADO COM " + socket.getInetAddress());
                TCPRequest req = TCPRequest.read(socket.getInputStream());
                String route = req.getRoute();
                String data = "";
                int statusCode;
                String message;
                if (route.equals("/")) {
                    data = myController.getUserData();
                } else {
                    String[] splitRoute = route.split("/");
                    if (splitRoute.length == 3) {
                        if (splitRoute[1].equals("user")) {
                            data = myController.getUserData(splitRoute[2]);
                        } else if (splitRoute[1].equals("sendAllert")) {
                            data = myController.sendAllert(splitRoute[2], req.getContentByKey("message")[0]);
                        }
                    }
                }
                if (data.equals("")) {
                    statusCode = 404;
                    message = "Not Found";
                } else {
                    statusCode = 200;
                    message = "OK";
                }
                TCPResponse res = new TCPResponse(req.getProtocol(), statusCode, message, data);
                res.setOutputStream(socket.getOutputStream());
                res.send();
                socket.close();
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopConnection() {
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }
}
