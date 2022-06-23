package com.example.vt_labs_1.server;

import com.example.vt_labs_1.commands.AbstractCommand;
import com.example.vt_labs_1.exceptions.DatabaseHandlingException;
import com.example.vt_labs_1.utility.*;
import com.example.vt_labs_1.utility.Module;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Server {

    private int port;
    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private InputStream stream;
    private AbstractCommand c = null;

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
        DataBaseHandler dataBaseHandler = new DataBaseHandler("localhost", 1337, "s336759", "wes537");
        DataBaseUserManager dataBaseUserManager = new DataBaseUserManager(dataBaseHandler);
        Module.setDataBaseUserManager(dataBaseUserManager);
        DataBaseCollectionManager dataBaseCollectionManager = new DataBaseCollectionManager(dataBaseHandler, dataBaseUserManager);
        Module.setCollectionManager(new CollectionManager(dataBaseCollectionManager));
    }

    class A implements Runnable {

        @Override
        public void run() {
            ForkJoinPool pool = ForkJoinPool.commonPool();
            AbstractCommand command = null;
            pool.invoke(new ForkJoinTask<Object>() {
                @Override
                public Object getRawResult() {
                    return null;
                }

                @Override
                protected void setRawResult(Object value) {

                }

                @Override
                protected boolean exec() {
                    while (c == null) {
                        try {
                            c = (AbstractCommand) getObject();
                        } catch (Exception e) {
                            //pass
                        }
                    }
                    return true;
                }

                private Object getObject() throws Exception {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    return inputStream.readObject();
                }
            });


            command = c;
            c = null;
            boolean result = Module.running(command);
            if (result) {
                Module.addMessage("Выполнение завершено.");
            } else {
                Module.addMessage("Выполнить команду не удалось.");
            }


            pool.invoke(new ForkJoinTask<Object>() {
                @Override
                public Object getRawResult() {
                    return null;
                }

                @Override
                protected void setRawResult(Object value) {

                }

                @Override
                protected boolean exec() {
                    try {
                        sendObject(Module.messageFlush());
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }

                private void sendObject(Object o) throws IOException {
                    String s = (String) o;
                    byte[] sbytes = s.getBytes();
                    String sEncoded = new String(sbytes, StandardCharsets.UTF_8);
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(sEncoded);
                    outputStream.flush();
                    outputStream.close();
                }
            });
        }
    }

    public void run() {
        connect();
        new Thread(new A()).start();
    }

    private void connect() {
        try {
            socket = server.accept();
        } catch (IOException ignored) {
            //pass
        }
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
        outputStream.close();
    }
}
