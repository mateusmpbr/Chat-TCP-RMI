# Chat-TCP-RMI

## Overview

Chat-TCP-RMI is a simple multi-user chat project implemented in Java that demonstrates distributed systems concepts using Java RMI (Remote Method Invocation) and TCP/IP networking. The project was implemented as part of the Distributed Systems course during the author's undergraduate studies at UFOP.

## Features

- Basic multi-user chat using RMI for remote method calls.
- Demonstrates server registration and client lookup via RMI.
- Simple, educational codebase suitable for experimenting with Java networking and RMI.

## Project Structure

- `src/br/ufop/main/` - Java source files.
  - `ChatServer.java` - Server implementation.
  - `ChatServerInt.java` - Server remote interface.
  - `ChatClient.java` - Client application.
  - `ChatClientInt.java` - (If present) client-side interface.
  - `ChatUI.java` - (If present) user interface code.
  - `StartServer.java` - Lightweight server starter / bootstrapper.
- `bin/` - Compiled classes (if present).

You can inspect the main sources in [src/br/ufop/main/](src/br/ufop/main/).

## Requirements

- Java JDK 8 or later installed and available on the `PATH`.

## Build and Run (simple instructions)

1. From the project root, compile the sources into the `bin` directory:

```bash
mkdir -p bin
javac -d bin -sourcepath src src/br/ufop/main/*.java
```

2. Start the RMI registry (run from project root or from `bin`):

```bash
# Option A: run rmiregistry from the bin directory in background
(cd bin && rmiregistry) &

# Option B: run rmiregistry in a separate terminal
# cd bin
# rmiregistry
```

3. Start the server (this usually registers the remote object with the RMI registry):

```bash
java -cp bin br.ufop.main.StartServer
```

4. Start one or more clients (in separate terminals):

```bash
java -cp bin br.ufop.main.ChatClient
```

Notes:

- If `StartServer` fails to find/locate the registry, ensure `rmiregistry` is running and that your `CLASSPATH`/`-cp` includes `bin`.
- If the project also contains TCP-specific code paths, examine `ChatServer.java` and `ChatClient.java` to choose the desired transport.

## Usage

- After starting the server and one or more clients, use the client UI/console to send messages. The server dispatches messages to connected clients using RMI callbacks (see `ChatServerInt.java` and client-side methods).

## Troubleshooting

- Common issues:
  - `java.rmi.ConnectException` or registry not found: start `rmiregistry` and ensure it's listening on the default port (1099) or adjust the code to match a custom port.
  - `ClassNotFoundException` for stub classes: compile all sources with `-d bin` and run `rmiregistry` from the `bin` directory or ensure `bin` is on the classpath.

## Extending / Notes for Developers

- This project is intended as an educational example. You can extend it by adding authentication, message history, persistence, or migrating to modern networking frameworks (e.g., WebSocket-based frontends).

## License & Author

This is a student project created for educational purposes. For questions or collaboration, contact the original author.
