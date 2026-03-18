# 💬 JavaChat — Multi-Client Chat Application

A real-time, multi-client chat application built with **Java Sockets**, **Multithreading**, and **Java Swing** GUI. Supports multiple users chatting simultaneously with a clean graphical interface.

---

## 📸 Screenshots

> _Add your screenshots here after running the app._

| Login Screen | Chat Window | Server Console |
|---|---|---|
| ![login](#) | ![chat](#) | ![server](#) |

---

## 🚀 Features

- **Real-time messaging** — instant message delivery between connected clients
- **Multi-client support** — multiple users can chat simultaneously via multithreading
- **Java Swing GUI** — clean, responsive graphical interface for the chat client
- **Username system** — users set a display name on login
- **Broadcast messages** — server relays messages to all connected clients
- **Server console** — live log of connected clients and activity
- **Graceful disconnect** — notifies users when someone leaves the chat

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 8+ |
| Networking | Java Sockets (`java.net`) |
| Concurrency | Multithreading (`java.lang.Thread`) |
| GUI | Java Swing (`javax.swing`) |
| I/O | `BufferedReader`, `PrintWriter` |

---

## 📁 Project Structure

```
JavaChat/
├── src/
│   ├── server/
│   │   ├── Server.java          # Main server — accepts client connections
│   │   └── ClientHandler.java   # Thread per client — handles messaging
│   ├── client/
│   │   ├── Client.java          # Client networking logic
│   │   └── ChatWindow.java      # Swing GUI — chat interface
│   └── Main.java                # Entry point (optional launcher)
├── README.md
└── .gitignore
```

---

## ⚙️ How It Works

```
┌─────────────┐        Socket         ┌──────────────────────┐
│  Client 1   │ ◄────────────────────► │                      │
│  (Swing UI) │                        │   Server             │
└─────────────┘                        │   (Multi-threaded)   │
                                       │                      │
┌─────────────┐        Socket         │  ClientHandler × N   │
│  Client 2   │ ◄────────────────────► │  (one thread/client) │
│  (Swing UI) │                        │                      │
└─────────────┘                        └──────────────────────┘
```

1. **Server** starts and listens on a port (default: `12345`)
2. Each **Client** connects via a socket and enters a username
3. Server spawns a new **`ClientHandler` thread** for each connection
4. Messages from one client are **broadcast to all** connected clients
5. The **Swing GUI** renders the chat in real time

---

## 🔧 Getting Started

### Prerequisites

- Java JDK 8 or higher
- Any IDE (IntelliJ IDEA, Eclipse, VS Code) or terminal

### Clone the Repository

```bash
git clone https://github.com/your-username/JavaChat.git
cd JavaChat
```

### Compile

```bash
javac -d out src/server/*.java src/client/*.java
```

### Run the Server

```bash
java -cp out server.Server
```

> Server starts listening on port `12345` by default.

### Run the Client(s)

Open **multiple terminals** (or run from multiple machines):

```bash
java -cp out client.Client
```

> Each client instance opens the Swing chat window. Enter your username and start chatting!

---

## 🖥️ Usage

1. Start the **server** first
2. Launch one or more **client** windows
3. Enter a **username** when prompted
4. Type messages and hit **Send** or press **Enter**
5. All connected users receive messages in real time
6. Close the window to **disconnect**

---

## 🧵 Multithreading Design

| Thread | Purpose |
|---|---|
| `Server` main thread | Listens for new client connections in a loop |
| `ClientHandler` thread | One per client — reads incoming messages and broadcasts |
| Swing EDT | Handles all GUI updates (via `SwingUtilities.invokeLater`) |
| Client listener thread | Reads incoming server messages without blocking the UI |

---

## 🔒 Configuration

Edit these constants in `Server.java` and `Client.java` to change defaults:

```java
// Server.java
private static final int PORT = 12345;

// Client.java
private static final String HOST = "localhost";
private static final int PORT = 12345;
```

---

## 📌 Known Limitations

- No persistent chat history (messages are lost on server restart)
- No private/direct messaging (all messages are broadcast)
- No encryption (plain-text socket communication)
- No authentication beyond a username

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Your Name**
- GitHub: [@your-username](https://github.com/your-username)

---

> ⭐ If you found this project useful, consider giving it a star!
