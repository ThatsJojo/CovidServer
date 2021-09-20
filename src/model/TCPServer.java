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

    /**
     * Enquanto conectado, gerencia as ações a serem realizadas dada cada request recebida
     * bem como as respostas para elas.
     */
    @Override
    public void start() {
        try {
            connected = true;
            server = new ServerSocket(port, 0, InetAddress.getByName(ip));
            while (connected) {
                Socket socket = server.accept();
                TCPRequest req = TCPRequest.read(socket.getInputStream());
                String route = req.getRoute();
                String data = "";
                int statusCode;
                String message;
                if (route.equals("/")) {//Assume-se um GET em todos os usuários.
                    data = myController.getUserData();
                } else {
                    String[] splitRoute = route.split("/");
                    if (splitRoute.length == 3) {
                        if (splitRoute[1].equals("user")) {//Assume-se um GET em um usuário específico.
                            data = myController.getUserData(splitRoute[2]);
                        } else if (splitRoute[1].equals("sendAllert")) {//Assume-se um POST de Alerta em um usuário específico.
                            String[] st = req.getContentByKey("message");
                            if (st != null && st.length != 0) {
                                data = myController.sendAllert(splitRoute[2], st[0]);
                            }
                        }
                    }
                }
                if (data.equals("")) {//Resposta caso não encontre o usuário para GET ou POST de Alerta.
                    statusCode = 404;
                    message = "Not Found";
                } else {//Resposta caso encontre.
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
