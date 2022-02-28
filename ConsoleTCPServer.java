//Majority of this for now is copied from here: https://www.baeldung.com/a-guide-to-java-sockets
//It seemed to be a good starting point to expand on later.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsoleTCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws java.io.IOException{
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        if(greeting.contains("GET")){
            String html = "<h1>Hello World</h1>";
            out.println(html);
        }
        System.out.println(greeting);
    }

    public void stop() throws java.io.IOException{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        ConsoleTCPServer server=new ConsoleTCPServer();
        try{
            server.start(6666);
        }catch (Exception e){
            System.out.println("crash");
            //crash
        }

    }
}