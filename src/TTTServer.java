import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;

import static java.lang.Thread.sleep;

class TTTServer {

    private static final int DEFAULT_PORT = 1728;
    private static final String HANDSHAKE = "Admiral";
    private static final char MESSAGE = '0';
    private static final char CLOSE = '1';

    private static final Vector<TTTSocket> users = new Vector<>();

    public static void main(String[] args) {

        int port;   // The port on which the server listens.

        BufferedReader incoming;  // Stream for receiving data from client.
        PrintWriter outgoing;     // Stream for sending data to client.
        String messageOut;        // A message to be sent to the client.
        String messageIn;         // A message received from the client.

        Scanner userInput;        // A wrapper for System.in, for reading
        // lines of input from the user.

        /* First, get the port number from the command line,
            or use the default port if none is specified. */

        if (args.length == 0)
            port = DEFAULT_PORT;
        else {
            try {
                port = Integer.parseInt(args[0]);
                if (port < 0 || port > 65535)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Illegal port number, " + args[0]);
                return;
            }
        }

        /* Wait for a connection request.  When it arrives, close
           down the listener.  Create streams for communication
           and exchange the handshake. */

        Runnable clientFinder = () -> {
            ServerSocket listener = null;
            try {
                listener = new ServerSocket(port);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    TTTSocket clientSocket = (TTTSocket) listener.accept();
                    clientSocket.getWriter().println(HANDSHAKE);  // Send handshake to client.
                    clientSocket.getWriter().flush();
                    String message = clientSocket.getReader().readLine();  // Receive handshake from client.
                    if (!HANDSHAKE.equals(message)) {
                        throw new Exception("Connected program is not CLChat!");
                    } else {
                        System.out.println("Client connected.");
                    }
                } catch (Exception e) {
                    System.err.println("Accept failed: " + e.getMessage());
                }
            }
        };

        Runnable stillThere = () -> {
            while (true) {
                /*
                Runnable timer = () -> {
                    try {
                        sleep(10000);
                    } catch (Exception e) {
                        System.err.println("Timer issue");
                    }
                };
                Thread timerThread = new Thread(timer, "timer");
                while (timerThread.isAlive()) {

                }*/
                for (Iterator<TTTSocket> iterator = users.iterator(); iterator.hasNext();) {
                    TTTSocket ts = iterator.next();
                    try {
                        if (ts.getWriter().checkError()) {
                            //ts.warnConnectedUser()
                            iterator.remove();
                        }
                    } catch (Exception ignore) {
                    }
                }
                try {
                    sleep(10000);
                } catch (Exception e) {
                    System.err.println("Timer issue");
                }


            }
        };

    }  // end main()


} //end class TTTServer