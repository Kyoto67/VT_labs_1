package server;

import common.commands.*;
import common.data.Movie;
import server.util.CollectionManager;
import sun.awt.X11.XSystemTrayPeer;

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
    private SocketChannel socketChannel;
    private ByteBuffer buffer;
    private Selector selector;
    private static AbstractCommand command;
    private static Object argument;
    private static CollectionManager collectionManager;

    public Server(int port) throws IOException {
        this.port = port;
        server = ServerSocketChannel.open();
        selector = Selector.open();
        server.bind(new InetSocketAddress(port));
        System.out.println("Сервер поднят.");
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        buffer = ByteBuffer.allocate(4096);
    }

    public void Run() throws Exception {
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
                        socketChannel.register(key.selector(), SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        int data = socketChannel.read(buffer);
                        socketChannel.configureBlocking(false);
                        if(!(data==-1)){
                            command= (AbstractCommand) byteBufferToObject(buffer);
                            data = socketChannel.read(buffer);
                            if(!(data==1)){
                                argument = byteBufferToObject(buffer);
                            }
                        }
                        buffer.clear();
                        if(!(command==null)){
                            if(Module.Run()){
                                buffer= ByteBuffer.wrap(new byte[]{1});
                                socketChannel.write(buffer);
                            } else {
                                buffer= ByteBuffer.wrap(new byte[]{0});
                                socketChannel.write(buffer);
                            }
                        }
                        System.out.println(new String(buffer.array()).trim() + data);
                        buffer.clear();
                        server.register(key.selector(), SelectionKey.OP_ACCEPT);
                    }
                }
            }
        }
    }

    public void Close() throws IOException {
        selector.close();
    }

    public ByteBuffer getBuffer(){
        return buffer;
    }

    private static Object byteBufferToObject(ByteBuffer byteBuffer)
            throws Exception {
        byte[] bytes = byteBuffer.array();
        return deSerializer(bytes);
    }

    private static Object deSerializer(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(bytes));
        return objectInputStream.readObject();
    }

    private static class Module {

        private static final String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script",
                "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
                "print_field_descending_oscars_count"};

        public static boolean Run() throws IOException {
            String actualCommand = "hello";
            while (!(actualCommand.equals("save"))) {
                actualCommand = command.getName();
                Scanner scanner = new Scanner(actualCommand);
                scanner.useDelimiter("\\s");
                actualCommand = scanner.next();
                switch (chooseCommand(actualCommand)) {
                    case (0): {
                        Help help = (Help) command;
                        command=null;
                        argument=null;
                        return (help.exec(""));
                    }
                    case (1): {
                        Info info = (Info) command;
                        info.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (info.exec(""));
                    }
                    case (2): {
                        Show show = (Show) command;
                        show.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (show.exec(""));
                    }
                    case (3): {
                        Movie newMovie = (Movie) argument;
                        Add add = (Add) command;
                        add.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (add.exec(newMovie));
                    }
                    case (4): {
                        Movie newMovie = (Movie) argument;
                        UpdateByID updateByID = (UpdateByID) command;
                        updateByID.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (updateByID.exec(newMovie));
                    }
                    case (5): {
                        String id = (String) argument;
                        RemoveByID removeByID = (RemoveByID) command;
                        removeByID.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (removeByID.exec(id));
                    }
                    case (6): {
                        Clear clear = (Clear) command;
                        clear.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (clear.exec(""));
                    }
                    case (7): {
                        Save save = (Save) command;
                        save.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (save.exec(""));
                    }
                    case (8): {
                        String filename = (String) argument;
                        ExecuteScript executeScript = (ExecuteScript) command;
                        executeScript.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (executeScript.exec(filename));
                    }
                    case (9): {
                        Exit exit = (Exit) command;
                        command=null;
                        argument=null;
                        return (exit.exec(""));
                    }
                    case (10): {
                        Movie newMovie = (Movie) argument;
                        AddIfMin addIfMin = (AddIfMin) command;
                        addIfMin.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (addIfMin.exec(newMovie));
                    }
                    case (11): {
                        Movie movieForCompare = (Movie) argument;
                        RemoveGreater removeGreater = (RemoveGreater) command;
                        removeGreater.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (removeGreater.exec(movieForCompare));
                    }
                    case (12): {
                        Movie movieForCompare = (Movie) argument;
                        RemoveLower removeLower = (RemoveLower) command;
                        removeLower.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (removeLower.exec(movieForCompare));
                    }
                    case (13): {
                        String oscarsCount = (String) argument;
                        RemoveAllByOscarsCount removeAllByOscarsCount = (RemoveAllByOscarsCount) command;
                        removeAllByOscarsCount.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (removeAllByOscarsCount.exec(oscarsCount));
                    }
                    case (14): {
                        String directorName = (String) argument;
                        RemoveAnyByDirector removeAnyByDirector = (RemoveAnyByDirector) command;
                        removeAnyByDirector.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
                        return (removeAnyByDirector.exec(directorName));
                    }
                    case (15): {
                        PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount = (PrintFieldDescendingOscarsCount) command;
                        printFieldDescendingOscarsCount.setCollectionManager(collectionManager);
                        command=null;
                        argument=null;
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

        private static int chooseCommand(String command) {
            for (int i = 0; i < commands.length; i++) {
                if (command.equals(commands[i])) {
                    return i;
                }
            }
            return -1;
        }
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        Server.collectionManager = collectionManager;
    }
}
