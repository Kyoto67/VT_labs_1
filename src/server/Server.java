package server;

import commands.AbstractCommand;
import commands.Save;
import exceptions.DatabaseHandlingException;
import utility.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private InputStream stream;

    public Server() throws DatabaseHandlingException {
        this.port = 2022;
        boolean connect = false;
        while (!connect) {
            try {
                server = new ServerSocket(port);
                connect = true;
                System.out.println("Сервер поднят и доступен по порту " + port + " .");
            } catch (Exception e) {
                port = (int) (Math.random() * 20000 + 10000);
            }
        }
        stream = System.in;
        DataBaseHandler dataBaseHandler = new DataBaseHandler("localhost", 1337,"s336759", "wes537");
        DataBaseUserManager dataBaseUserManager = new DataBaseUserManager(dataBaseHandler);
        DataBaseCollectionManager dataBaseCollectionManager = new DataBaseCollectionManager(dataBaseHandler, dataBaseUserManager);
        Module.setCollectionManager(new CollectionManager(dataBaseCollectionManager));
    }

    public void run() {
        try {
            connect();
            AbstractCommand command = null;
            while (command == null) {
                try {
                    command = (AbstractCommand) getObject();
                } catch (Exception e) {
                     
                }
            }
            boolean result = Module.running(command);
            if (result) {
                Module.addMessage("Выполнение успешно.");
            } else {
                Module.addMessage("Выполнить команду не удалось.");
            }
            sendObject(Module.messageFlush());
        } catch (Exception e) {
             
        }
        try {
            if (stream.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                if (reader.readLine().equals("save")) {
                    Save save = new Save("save", "desc");
                    save.setCollectionManager(Module.getCollectionManager());
                    save.exec();
                    System.out.println("Коллекция сохранена.");
                }
            }
        } catch (IOException e) {
             
        }
    }

    private void connect() throws IOException {
        socket = server.accept();
    }

    private Object getObject() throws Exception {
        inputStream = new ObjectInputStream(socket.getInputStream());
        return inputStream.readObject();
    }

    public void close() throws IOException {
        server.close();
    }

    private void sendObject(Object o) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(o);
        outputStream.flush();
    }
}
