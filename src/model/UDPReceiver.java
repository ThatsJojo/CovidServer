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

    /**
     * Classe que realiza a conexão e reconhecimento dos pacotes UDP recebidos.
     * @param myController
     * @param ip Endereço IP para receber os pacotes dos dispositivos.
     * @param port Porta para receber os pacotes dos dispositivos.
     */
    public UDPReceiver(ConnectionController myController, String ip, int port) {
        this.myController = myController;
        this.ip = ip;
        this.port = port;
        connected = false;
    }

    /**
     * Enquanto conectado, recebe os pacotes TCP no endereço indicado e redireciona
     * uma ação para o usuário correto.
     */
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

    /**
     * Finaliza a conexão.
     */
    public void stopConnection() {
        this.connected = false;
    }
}
