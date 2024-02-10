import java.io.*;
import java.net.*;

public class AdviceGuyClient {

    public void startClient() {
        try {
            Socket socket = new Socket("localhost", 4242);
            System.out.println("Connected to AdviceGuyServer.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            for (int i = 0; i < 3; i++) {
                String receivedAdvice = reader.readLine();
                System.out.println("Received advice from server: " + receivedAdvice);

                Thread.sleep(1000);

                writer.println("Thanks for the advice!");
            }

            writer.close();
            reader.close();
            socket.close();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AdviceGuyClient client = new AdviceGuyClient();
        client.startClient();
    }
}
