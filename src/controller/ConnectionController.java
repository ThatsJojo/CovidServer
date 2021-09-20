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

    /**
     * Informações de Conexão
     * @param UDPReceiverIP IP para conexão UDP de recebimento dos pacotes referentes aos dados do dispositivo.
     * @param UDPReceiverPort Porta para conexão UDP de recebimento dos pacotes referentes aos dados do dispositivo.
     * @param UDPSenderIP IP para conexão UDP de envio dos pacotes referentes às Alertas.
     * @param UDPSenderPort Porta para conexão UDP de envio dos pacotes referentes às Alertas.
     * @param TCPServerIP
     * @param TCPServerPort 
     */
    public ConnectionController(String UDPReceiverIP, int UDPReceiverPort, String UDPSenderIP, int UDPSenderPort, String TCPServerIP, int TCPServerPort) {
        this.udpReceiver = new UDPReceiver(this, UDPReceiverIP, UDPReceiverPort);
        this.udpSender = new UDPSender(this, UDPSenderIP, UDPSenderPort);
        this.tcpServer = new TCPServer(this, TCPServerIP, TCPServerPort);

    }

    /**
     * Atualiza as informações de um usuário.
     * @param recievedMessage Dados recebidos.
     * @param user Chave do usuário.
     */
    public void updateUser(String[] recievedMessage, String user) {
            User u = userController.updateUser(recievedMessage, user);
    }

    @Deprecated
    private boolean isValidMessage(String[] recievedMessage) {
        int i = 0;
        if (recievedMessage != null && recievedMessage.length == 12) {
            for (; i < 12 && recievedMessage[i] != null && recievedMessage[i].length() > 3; i++) {
            }
        }
        return i == 12;
    }

    /**
     * Envia uma Alerta para o usuário.
     * @param userKey Chave do usuário.
     * @param message Mensagem da Alerta.
     * @return "200 Ok" caso tenha encontrado o usuário e "404 Não encontrado" caso contrário.
     */
    public String sendAllert(String userKey, String message) {
        return userController.containsUser(userKey) && udpSender.sendMessage(userKey, message) ? "200 OK" : "404 Não encontrado";
    }

    /**
     * Recupera as informações de um usuário dada uma chave.
     * @param userKey chave do usuário.
     * @return informações do usuário formatadas para envio como conteúdo de uma Request.
     */
    public String getUserData(String userKey) {
        User u = userController.getUser(userKey);
        if(u == null)
            return "";
        String reqString = u.getData();
        return reqString;
    }

    /**
     * Utilizada para listar os usuários.
     * @return String Retorna as informações básicas para reconhecimento de todos os usuários já conectados.
     */
    public String getUserData() {
        userController.getUSERS().forEach((String t, User u) -> {
            auxString += t + ":" + u.getName().replace(' ', '&') + "," + u.getAge() + "," + u.getSex() + "\n";
        });
        return auxString;
    }

    /**
     * Inicia a APIRest e o recebimento de dados dos dispositivos.
     */
    public void startConnections() {
        udpReceiver.start();
        udpSender.start();
        tcpServer.start();
    }

    /**
     * Finaliza as conexões da API e de recebimento de dados dos dispositivos.
     */
    public void stopConnections() {
        udpReceiver.stopConnection();
        udpSender.stopConnection();
        tcpServer.stopConnection();
    }

}
