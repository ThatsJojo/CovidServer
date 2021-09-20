package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TCPRequest {

    private String method;
    private String protocol;
    private String route;
    private boolean keepAlive;
    private long timeOut;
    private final HashMap<String, String[]> content = new HashMap();

    /**
     * Request gerada a partir de um inputStream de socket TCP.
     * @param inputStream inputStream do Socket.
     * @return A Request do usuário encapsulada na classe atual.
     * @throws IOException Caso não consiga ler a request por completo.
     */
    static TCPRequest read(InputStream inputStream) throws IOException {
        TCPRequest req = new TCPRequest();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        String line = buffer.readLine();
        String[] dataReq = line.split(" ");
        req.method = dataReq[0];//Captura o método.
        req.route = dataReq[1];//Captura a rota.
        req.protocol = dataReq[2];//Captura o protocolo.
        line = buffer.readLine();
        //Lê a Request até o fim para capturar o conteúdo/cabeçalho da Request.
        while (line != null && !line.isEmpty()) {
            String[] segments = line.split(":");
            if (segments.length > 1) {
                req.content.put(segments[0], segments[1].trim().split(","));
            }
            line = buffer.readLine();
        }
        System.out.println(req.method + " " + req.route + " " + req.protocol + " " + (req.content == null ? "" : req.content.toString()));
        return req;
    }

    /**
     * Retorna o método da Request.
     * @return 
     */
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Retorna o protocolo da Request.
     * @return 
     */
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public HashMap<String, String[]> getContent() {
        return content;
    }

    public String[] getContentByKey(String key) {
        return content.get(key);
    }

}
