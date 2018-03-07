import java.io.*;
import java.net.Socket;

class TTTSocket extends Socket {

    private BufferedReader br = new BufferedReader(new InputStreamReader(getInputStream()));
    private PrintWriter bw = new PrintWriter(getOutputStream());
    private TTTSocket server = null;
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

    public TTTSocket getServer() {
        return server;
    }

    public TTTSocket getConnectedUser() {
        return connectedUser;
    }

}
