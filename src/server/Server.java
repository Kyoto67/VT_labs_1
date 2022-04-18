package server;

import common.commands.*;
import common.data.Movie;
import server.util.CollectionManager;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server {

    private final int port;
    private ServerSocketChannel server;
    private SocketChannel client;
    private ByteBuffer buffer;
    private Selector selector;
    private AbstractCommand command;
    private Object argument;
    private CollectionManager collectionManager;
    private int client_counter;
    public static String outputMessage;

    public Server(int port) throws IOException {
        this.port = port;
        server = ServerSocketChannel.open();
        selector = Selector.open();
        server.bind(new InetSocketAddress(port));
        System.out.println("Сервер поднят.");
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        buffer = ByteBuffer.allocate(10000);
        client_counter = 0;
        outputMessage="";
    }


    public void Run() throws Exception {
        SocketChannel socketChannel = null;
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        socketChannel = server.accept();
                        socketChannel.configureBlocking(false);
                        System.out.println("Новый клиент подключился к серверу.");
                        client_counter += 1;
                        socketChannel.register(key.selector(), SelectionKey.OP_READ);
                    }
                    try {
                        if (key.isReadable() && client_counter > 0) {
                            client = (SocketChannel) key.channel();
                            downloadCommand();
                        }
                    } catch (IOException e) {
                        //pass
                    }
                    if (!(command == null)) {
                        if (execution()) {
                            buffer = ByteBuffer.wrap(new byte[]{1});
                            socketChannel.write(buffer);
                            uploadObject(outputMessage);
                            outputMessage="";
                        } else {
                            buffer = ByteBuffer.wrap(new byte[]{0});
                            socketChannel.write(buffer);
                            uploadObject(outputMessage);
                        }
                    }
                    buffer = ByteBuffer.allocate(10000);
                    server.register(key.selector(), SelectionKey.OP_ACCEPT);
                }
            }
        }
    }

    public void Close() throws IOException {
        selector.close();
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void uploadObject(Object object) throws IOException {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bytesOut);
        outputStream.writeObject(object);
        outputStream.flush();
        outputStream.close();
        byte[] bytes = bytesOut.toByteArray();
        bytesOut.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        client.write(buffer);
    }

    private void downloadArgument() throws Exception {
        int data = client.read(buffer);
        if (!(data == -1) && (!(data == 0))) {
            argument = byteBufferToObject(buffer);
        }
        client.configureBlocking(false);
    }
    private void downloadCommand() throws Exception {
        int data = client.read(buffer);
        client.configureBlocking(false);
        if (!(data == -1) && (!(data == 0))) {
            command = (AbstractCommand) byteBufferToObject(buffer);
            buffer = ByteBuffer.allocate(10000);
        }
    }

    private Object byteBufferToObject(ByteBuffer byteBuffer)
            throws Exception {
        byte[] bytes = byteBuffer.array();
        return deSerializer(bytes);
    }

    private Object deSerializer(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(bytes));
        Object object = null;
        boolean check = false;
        while (!(check)) {
            try {
                object = objectInputStream.readObject();
                check = true;
            } catch (EOFException ex) {
                check = false;
            }
        }
        return object;
    }

    private final String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script",
            "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
            "print_field_descending_oscars_count"};

    public boolean execution() throws Exception {
        String actualCommand = "hello";
        while (!(actualCommand.equals("save"))) {
            actualCommand = command.getName();
            Scanner scanner = new Scanner(actualCommand);
            scanner.useDelimiter("\\s");
            actualCommand = scanner.next();
            switch (chooseCommand(actualCommand)) {
                case (0): {
                    Help help = (Help) command;
                    command = null;
                    argument = null;
                    return (help.exec(""));
                }
                case (1): {
                    Info info = (Info) command;
                    info.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (info.exec(""));
                }
                case (2): {
                    Show show = (Show) command;
                    show.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (show.exec(""));
                }
                case (3): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    Movie newMovie = (Movie) argument;
                    Add add = (Add) command;
                    add.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (add.exec(newMovie));
                }
                case (4): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    Movie newMovie = (Movie) argument;
                    UpdateByID updateByID = (UpdateByID) command;
                    updateByID.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (updateByID.exec(newMovie));
                }
                case (5): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    String id = (String) argument;
                    RemoveByID removeByID = (RemoveByID) command;
                    removeByID.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (removeByID.exec(id));
                }
                case (6): {
                    Clear clear = (Clear) command;
                    clear.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (clear.exec(""));
                }
                case (7): {
                    Save save = (Save) command;
                    save.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    client_counter -= 1;
                    return (save.exec(""));
                }
                case (8): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    String filename = (String) argument;
                    ExecuteScript executeScript = (ExecuteScript) command;
                    executeScript.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (executeScript.exec(filename));
                }
                case (9): {
                    Exit exit = (Exit) command;
                    command = null;
                    argument = null;
                    return (exit.exec(""));
                }
                case (10): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    Movie newMovie = (Movie) argument;
                    AddIfMin addIfMin = (AddIfMin) command;
                    addIfMin.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (addIfMin.exec(newMovie));
                }
                case (11): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    Movie movieForCompare = (Movie) argument;
                    RemoveGreater removeGreater = (RemoveGreater) command;
                    removeGreater.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (removeGreater.exec(movieForCompare));
                }
                case (12): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    Movie movieForCompare = (Movie) argument;
                    RemoveLower removeLower = (RemoveLower) command;
                    removeLower.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (removeLower.exec(movieForCompare));
                }
                case (13): {
                    String oscarsCount = (String) argument;
                    RemoveAllByOscarsCount removeAllByOscarsCount = (RemoveAllByOscarsCount) command;
                    removeAllByOscarsCount.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (removeAllByOscarsCount.exec(oscarsCount));
                }
                case (14): {
                    while (argument == null) {
                        downloadArgument();
                    }
                    String directorName = (String) argument;
                    RemoveAnyByDirector removeAnyByDirector = (RemoveAnyByDirector) command;
                    removeAnyByDirector.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (removeAnyByDirector.exec(directorName));
                }
                case (15): {
                    PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount = (PrintFieldDescendingOscarsCount) command;
                    printFieldDescendingOscarsCount.setCollectionManager(collectionManager);
                    command = null;
                    argument = null;
                    return (printFieldDescendingOscarsCount.exec(""));
                }
                case (-1): {
                    System.out.println("Неизвестная ошибка.");
                    return (false);
                }
            }
        }
        return false;
    }

    private int chooseCommand(String command) {
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
