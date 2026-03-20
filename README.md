# simple-java-makefile-app

👉 Makefile is NOT only for C/C++
👉 It is a general-purpose build automation tool

You can use it for:

Java ✅

Python ✅

Docker ✅

Kubernetes ✅

Shell scripts ✅

Anything you can run in terminal ✅

🧠 Why people think it's only for C/C++

Because traditionally:

make was heavily used with C/C++ compilers (gcc, g++)

It handles compilation dependencies very well

So it became famous in that ecosystem.

🔥 How it works (important concept)

Makefile doesn’t “know Java” or “know C”.

👉 It just runs commands like this:

javac Main.java
java Main

Same as if you typed manually.

✅ Example (Your case)

In your Makefile:

build:
	javac -d build src/Main.java

run:
	java -cp build Main

👉 Make is simply automating:

Compile → javac

Run → java

—---------------------------------------------------------------------------------------------------------------------------- 
📦 Project: simple-java-make-app
📁 Folder Structure
simple-java-make-app/
 ├── src/
 │    └── Main.java
 ├── Makefile
 └── README.md
☕ Java Source Code
📄 File: src/Main.java
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
⚙️ Makefile
📄 File: Makefile
# Variables
SRC_DIR=src
BUILD_DIR=build
MAIN_CLASS=Main
PORT=8087

# Default target
all: build

# Compile Java code
build:
	mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) $(SRC_DIR)/Main.java

# Run application
run: build
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

# Clean build files
clean:
	rm -rf $(BUILD_DIR)

# Show access URL (optional helper)
url:
	@echo "http://$$(curl -s ifconfig.me):$(PORT)"
📄 README.md (Optional but good practice)
# Simple Java Makefile App

## 🚀 Run the app

```bash
make run
🌐 Access

The app will print:

You can access the application at: http://<PUBLIC_IP>:8087

Or manually:

make url

---

# 🚀 How to Use

### 1. Build & Run
```bash
make run
2. Clean
make clean
3. Get URL separately
make url
🔥 Output in Terminal
🚀 Application started!
👉 You can access the application at: http://<your-public-ip>:8087
