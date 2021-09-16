package covidserver;

import controller.ConnectionController;
import java.util.Scanner;
import model.UDPReceiver;

public class CovidServer {

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Insira o IP para receber os pacotes dos dispositivos: ");
        String UDPReceiverIP = "127.0.0.1";//sc1.nextLine();
        System.out.print("Agora a porta: ");
        int UDPReceiverPort = 8085;//sc2.nextInt();
        System.out.print("Insira o IP para enviar alertas aos dispositivos: ");
        String UDPSenderIP = "127.0.0.1";//sc1.nextLine();
        System.out.print("Agora a porta: ");
        int UDPSenderPort = 8080;//sc2.nextInt();
        System.out.print("Insira o IP para conexão com o médico: ");
        String TCPReceiverIP = "127.0.0.1";//sc1.nextLine();
        System.out.print("Agora a porta: ");
        int TCPReceiverPort = 8083;//sc2.nextInt();
        ConnectionController cc = new ConnectionController(UDPReceiverIP, UDPReceiverPort, UDPSenderIP, UDPSenderPort, TCPReceiverIP, TCPReceiverPort);
        cc.startConnections();
    }

}
