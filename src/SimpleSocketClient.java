import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Simple socket client that sends messages via a socket on an output stream
 * and receives a response back on an input stream.
 * The socket host and port are specified by the user from the command line.
 */
public class SimpleSocketClient {
	
    /**
     * Main function.
     * Get server and port from command line argument and connect to the server via the associated socket.
     * @param args - server and port for communication
     */
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: SimpleSocketClient <server> <port>");
            System.exit(0);
        }
        
        String server = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.println("Connect to server at: " + server + " using port: " + port);

        try
        {
            // Connect to the server
            Socket socket = new Socket(server, port);

            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send messages to server
            out.println("GetCreditStatus");
            // Blank message indicates the connection can be closed 
            out.println();

            // Read and print out response from the server
            // Blank line indicates the end of the response
            String line = in.readLine();
            while(line != null) {
                System.out.println(line);
                line = in.readLine();
            }

            // Close streams and socket
            in.close();
            out.close();
            socket.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}