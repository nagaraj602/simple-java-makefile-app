import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {

        int port = 8087;

        // Start HTTP Server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();

        // Fetch Public IP
        String publicIp = "localhost";
        try {
            publicIp = new BufferedReader(
                    new InputStreamReader(new URL("http://checkip.amazonaws.com").openStream())
            ).readLine();
        } catch (Exception e) {
            System.out.println("⚠️ Could not fetch public IP, using localhost");
        }

        System.out.println("\n🚀 Application started!");
        System.out.println("👉 You can access the application at: http://" + publicIp + ":" + port + "\n");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try {
                String response = """
                        <html>
                        <body style="margin:0; background-color:#0a2540; display:flex; justify-content:center; align-items:center; height:100vh;">
                            <h1 style="color:white; font-family:Arial;">
                                Hello from Makefile Java App (Port 8087)!
                            </h1>
                        </body>
                        </html>
                        """;

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
