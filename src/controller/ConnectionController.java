package controller;

import model.TCPServer;
import model.UDPReceiver;
import model.UDPSender;
import model.User;

public class ConnectionController {

    UDPReceiver udpReceiver;
    UDPSender udpSender;
    TCPServer tcpServer;
    String UDPReceiverIP;
    int UDPReceiverPort;
    String UDPSenderIP;
    int UDPSenderPort;
    String TCPServerIP;
    int TCPServerPort;
    private static String auxString = "";

    public ConnectionController(String UDPReceiverIP, int UDPReceiverPort, String UDPSenderIP, int UDPSenderPort, String TCPServerIP, int TCPServerPort) {
//        this.UDPReceiverIP = UDPReceiverIP;
//        this.UDPReceiverPort = UDPReceiverPort;
//        this.UDPSenderIP = UDPSenderIP;
//        this.UDPSenderPort = UDPSenderPort;
//        this.TCPServerIP = TCPServerIP;
//        this.TCPServerPort = TCPServerPort;
        this.udpReceiver = new UDPReceiver(this, UDPReceiverIP, UDPReceiverPort);
        this.udpSender = new UDPSender(this, UDPSenderIP, UDPSenderPort);
        this.tcpServer = new TCPServer(this, TCPServerIP, TCPServerPort);

    }

    public void updateUser(String[] recievedMessage, String user) {
        if (isValidMessage(recievedMessage)) {
            User u = userController.updateUser(recievedMessage, user);
        }
    }

    private boolean isValidMessage(String[] recievedMessage) {
        int i = 0;
        if (recievedMessage != null && recievedMessage.length == 12) {
            for (; i < 12 && recievedMessage[i] != null && recievedMessage[i].length() > 3; i++) {
            }
        }
        return i == 12;
    }

    public String sendAllert(String userKey, String message) {
        return userController.containsUser(userKey) && udpSender.sendMessage(userKey, message) ? "200 OK" : "404 Não encontrado";
    }

    public String getUserData(String userKey) {
        User u = userController.getUser(userKey);
        if(u == null)
            return "";
        String reqString = u.getData();
        return reqString;
    }

    public String getUserData() {
        userController.getUSERS().forEach((String t, User u) -> {
            auxString += t + ":" + u.getName() + "," + u.getKey() + "," + u.getAge() + "," + u.getSex() + "\n";
        });
        return auxString;
    }

    public void startConnections() {
        udpReceiver.start();
        udpSender.start();
        tcpServer.start();
    }

    public void stopConnections() {
        udpReceiver.stopConnection();
        udpSender.stopConnection();
        tcpServer.stopConnection();
    }

}
