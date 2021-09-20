package model;

import controller.ConnectionController;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPSender extends Thread {

    private final ConnectionController myController;
    private final String ip;
    private final int port;
    private boolean connected = false;

    /**
     * Resposabiliza-se por enviar as alertas para os usuários.
     * @param myController Controllador de usuários.
     * @param ip Endereço para conexão UDP de envio de alertas.
     * @param port Porta para conexão UDP de envio de alertas. 
     */
    public UDPSender(ConnectionController myController, String ip, int port) {
        this.myController = myController;
        this.ip = ip;
        this.port = port;
    }

    /**
     * Envia uma mensagem de alerta para um dado usuário.
     * @param userKey chave relacionada ao usuário.
     * @param message Mensagem a ser enviada.
     * @return true caso consiga enviar. False caso não consiga.
     */
    public boolean sendMessage(String userKey, String message) {
        new Thread() {
            @Override
            public void run() {
                byte[] msg = (userKey + "\n" + message).getBytes();
                for (int i = 0; i < 2; i++) {
                    try {
                        DatagramPacket pkg = new DatagramPacket(msg, msg.length, InetAddress.getByName(ip), port);
                        DatagramSocket UDPSocket = new DatagramSocket();
                        UDPSocket.send(pkg);
                        UDPSocket.close();
                        Thread.sleep(333);
                    } catch (SocketException ex) {
                        Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        return true;
    }

    public void stopConnection() {
        this.connected = false;
    }
}
