package model;

import java.io.IOException;
import java.io.OutputStream;

public class TCPResponse {

    private String protocol;
    private int statusCode;
    private String message;
    private String content;
    private OutputStream outputStream;

    /**
     * Resposta a ser enviada pela API.
     * @param protocol Protocolo de resposta.
     * @param statusCode Status da resposta.
     * @param message Mensagem contida na resposta.
     * @param content Conteúdo da resposta.
     */
    public TCPResponse(String protocol, int statusCode, String message, String content) {
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.message = message;
        this.content = content;
    }

    /**
     * Envia a resposta.
     * @throws IOException Caso não consiga enviar a resposta por completo.
     */
    public void send() throws IOException {
        outputStream.write(("" + protocol + " " + statusCode + " " + message + "\r\n" + content).getBytes());
        outputStream.flush();
        outputStream.close();
        outputStream = null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
