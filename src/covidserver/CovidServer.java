package covidserver;

import controller.ConnectionController;
import java.util.Scanner;

public class CovidServer {

    public static void main(String[] args) {
        //Cadastra endereçoes comuns de utilização caso o usuário não deseje especificálos.
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String UDPReceiverIP = "127.0.0.1";
        int UDPReceiverPort = 8085;
        String UDPSenderIP = "127.0.0.1";
        int UDPSenderPort = 8080;
        String TCPReceiverIP = "127.0.0.1";
        int TCPReceiverPort = 8083;
        //Pede que o usuário informe os endereços de conexão.
        System.out.println("Deseja configurar os endereços de comunicação? (sim/não)");
        boolean flag = sc1.nextLine().equals("sim");
        if (flag) {
            System.out.print("Insira o IP para receber os pacotes dos dispositivos: ");
            UDPReceiverIP = sc1.nextLine();
            System.out.print("Agora a porta: ");
            UDPReceiverPort = sc2.nextInt();
            System.out.print("Insira o IP para enviar alertas aos dispositivos: ");
            UDPSenderIP = sc1.nextLine();
            System.out.print("Agora a porta: ");
            UDPSenderPort = sc2.nextInt();
            System.out.print("Insira o IP para conexão com o médico: ");
            TCPReceiverIP = sc1.nextLine();
            System.out.print("Agora a porta: ");
            TCPReceiverPort = sc2.nextInt();
        }
        ConnectionController cc = new ConnectionController(UDPReceiverIP, UDPReceiverPort, UDPSenderIP, UDPSenderPort, TCPReceiverIP, TCPReceiverPort);
        cc.startConnections();
    }

}
