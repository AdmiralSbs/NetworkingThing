import java.io.*;
import java.net.Socket;

class TTTSocket extends Socket {

    private BufferedReader br = new BufferedReader(new InputStreamReader(getInputStream()));
    private PrintWriter bw = new PrintWriter(getOutputStream());
    private TTTSocket serverSocket = null;
    //private TTTServer serverClass = null;
    private TTTSocket connectedUser = null;

    public TTTSocket(String host, int port) throws IOException {
        super(host, port);
    }

    public BufferedReader getReader() throws IOException {
        return br;
    }

    public PrintWriter getWriter() throws IOException {
        return bw;
    }

    public void setServerSocket(TTTSocket sS) {
        serverSocket = sS;
    }

    //public void setServerClass(TTTServer sS) {
    //    serverClass = sS;
    //}

    public TTTSocket getServerSocket() {
        return serverSocket;
    }

    public TTTSocket getConnectedUser() {
        return connectedUser;
    }

}
