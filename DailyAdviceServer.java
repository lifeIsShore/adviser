import java.io.*;
import java.net.*;

public class DailyAdviceServer {

    private String[] adviceList = { 
        "Take smaller bites", 
        "Go for the tight jeans. No they do NOT make you look fat.", 
        "One word: inappropriate", 
        "Just for today, be honest. Tell your boss what you really think", 
        "You might want to rethink that haircut.",
        
	"Don't be afraid to ask for help when you need it.",
        "Take breaks to avoid burnout.",
        "Learn from your mistakes, but don't dwell on them.",
        "Embrace change; it's a part of life.",
        "Always remember to stay hydrated."
    };

    public void startServer() {
        try {
            ServerSocket serverSock = new ServerSocket(4242);
            System.out.println("DailyAdviceServer is running. Waiting for clients...");

            while (true) {
                Socket clientSock = serverSock.accept();
                System.out.println("Client connected: " + clientSock);

                handleClient(clientSock);

                // Close the connection after handling the client
                clientSock.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket clientSock) throws IOException {
        PrintWriter writer = new PrintWriter(clientSock.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

        for (int i = 0; i < 3; i++) {
            String advice = getAdvice();
            writer.println(advice);
            System.out.println("Sent advice to client: " + advice);

            String clientResponse = reader.readLine();
            System.out.println("Client response: " + clientResponse);
        }

        writer.close();
        reader.close();
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.startServer();
    }
}
