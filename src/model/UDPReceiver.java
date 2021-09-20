package model;

import controller.ConnectionController;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPReceiver extends Thread {

    private final ConnectionController myController;
    private final String ip;
    private final int port;
    private boolean connected;

    public UDPReceiver(ConnectionController myController, String ip, int port) {
        this.myController = myController;
        this.ip = ip;
        this.port = port;
        connected = false;
    }

    @Override
    public void run() {
        String[] recievedMessage;
        connected = true;
        DatagramSocket ds;
        try {
            ds = new DatagramSocket(port);
            System.out.println("Ouvindo a porta: " + port);
            byte[] msg = new byte[1000];
            DatagramPacket pkg = new DatagramPacket(msg, msg.length);
            while (connected) {
                ds.receive(pkg);
                recievedMessage = new String(pkg.getData()).trim().split("\n");
                myController.updateUser(recievedMessage, recievedMessage[0]);
            }
        } catch (SocketException ex) {
            Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopConnection() {
        this.connected = false;
    }
}
