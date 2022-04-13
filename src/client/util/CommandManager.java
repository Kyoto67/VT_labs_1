package client.util;

import client.Client;
import common.commands.*;
import common.data.Movie;
import server.util.ScriptManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс сущности, которая будет парсить все поступаемые команды и вызывать их выполнение.
 */
public class CommandManager implements Serializable {

    /**
     * Поля, содержащие объекты команд.
     */
    private final Add add;
    private final AddIfMin addIfMin;
    private final Clear clear;
    private final ExecuteScript executeScript;
    private final Info info;
    private final PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount;
    private final RemoveAllByOscarsCount removeAllByOscarsCount;
    private final RemoveAnyByDirector removeAnyByDirector;
    private final RemoveByID removeByID;
    private final RemoveGreater removeGreater;
    private final RemoveLower removeLower;
    private final Save save;
    private final Show show;
    private final UpdateByID updateByID;
    private final Exit exit;
    private final Help help;
    private final Add_for_script add_for_script;
    private final AddIfMin_for_script addIfMin_for_script;
    private final UpdateByID_for_script updateByID_for_script;
    private final RemoveGreater_for_script removeGreater_for_script;
    private final RemoveLower_for_script removeLower_for_script;
    private final Client client;
    private final ScriptManager scriptManager;
    private final String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script",
            "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
            "print_field_descending_oscars_count"};
    /**
     * Конструктор менеджера. Автоматически инициализирует объекты всех команд при создании и менеджера коллекций.
     * @throws IOException
     */
    public CommandManager(Client client) throws IOException {
        this.client=client;
        scriptManager = new ScriptManager();
        add = new Add("add", "добавить новый элемент в коллекцию");
        addIfMin = new AddIfMin("add_if_min", "добавить новый элемент в коллекцию, если его " +
                "значение меньше, чем у наименьшего элемента этой коллекции. Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ");
        clear = new Clear("clear","очистить коллекцию");
        info = new Info("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата " +
                "инициализации, количество элементов и т.д.)");
        printFieldDescendingOscarsCount = new PrintFieldDescendingOscarsCount("print_field_descending_oscars_count"
                , "вывести значения поля oscarsCount всех элементов в порядке убывания");
        removeAllByOscarsCount= new RemoveAllByOscarsCount("remove_all_by_oscars_count oscarsCount",
                "удалить из коллекции все элементы, значение поля oscarsCount которого эквивалентно заданному");
        removeAnyByDirector = new RemoveAnyByDirector("remove_any_by_director director", "удалить из " +
                "коллекции один элемент, значение поля director которого эквивалентно заданному (вводится имя режиссёра)");
        removeByID = new RemoveByID("remove_by_id id", "удалить элемент из коллекции по его id");
        removeGreater = new RemoveGreater("remove_greater", "удалить из коллекции все элементы, " +
                "превышающие заданный.  Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ");
        removeLower = new RemoveLower("remove_lower", "удалить из коллекции все элементы, меньшие, " +
                "чем заданный. Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ");
        save = new Save("save", "сохранить коллекцию в файл");
        show = new Show("show", "вывести в стандартный поток вывода все элементы коллекции в строковом " +
                "представлении");
        updateByID = new UpdateByID("update id", "обновить значение элемента коллекции, id которого " +
                "равен заданному");
        exit = new Exit("exit", "завершить программу (без сохранения в файл)");
        add_for_script = new Add_for_script("add", "добавить новый элемент в коллекцию", scriptManager);
        addIfMin_for_script = new AddIfMin_for_script("add_if_min id", "добавить новый элемент в коллекцию, если его " +
                "значение меньше, чем у наименьшего элемента этой коллекции (введите значение id в качестве аргумента)", scriptManager);
        updateByID_for_script = new UpdateByID_for_script("update id", "обновить значение элемента коллекции, id которого " +
                "равен заданному", scriptManager);
        removeGreater_for_script = new RemoveGreater_for_script("remove_greater", "удалить из коллекции все элементы, " +
                "превышающие заданный.  Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", scriptManager);
        removeLower_for_script = new RemoveLower_for_script("remove_lower", "удалить из коллекции все элементы, меньшие, " +
                "чем заданный. Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", scriptManager);
        executeScript = new ExecuteScript("execute_script filename", "считать и исполнить скрипт из " +
                "указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в " +
                "интерактивном режиме.", scriptManager);
        help = new Help("help", "вывести справку по доступным командам", add, addIfMin, clear,
                executeScript,
                info, printFieldDescendingOscarsCount, removeAllByOscarsCount, removeAnyByDirector, removeByID, removeGreater, removeLower, save, show, updateByID, exit);
        scriptManager.setClear(clear);
        scriptManager.setExecuteScript(executeScript);
        scriptManager.setInfo(info);
        scriptManager.setPrintFieldDescendingOscarsCount(printFieldDescendingOscarsCount);
        scriptManager.setRemoveAllByOscarsCount(removeAllByOscarsCount);
        scriptManager.setRemoveAnyByDirector(removeAnyByDirector);
        scriptManager.setRemoveByID(removeByID);
        scriptManager.setSave(save);
        scriptManager.setShow(show);
        scriptManager.setUpdateByID_for_script(updateByID_for_script);
        scriptManager.setAdd_for_script(add_for_script);
        scriptManager.setAddIfMin_for_script(addIfMin_for_script);
        scriptManager.setRemoveGreater_for_script(removeGreater_for_script);
        scriptManager.setRemoveLower_for_script(removeLower_for_script);

    }

    /**
     * @param line принимает на вход строку, парсит её на команду и её аргументы, по полученному имени запускает выполнение нужной команды, передаёт на вход команде аргумент или пустую строку, выводит ошибку, если не удалось сопоставить строке команду
     * @see CommandManager#commandParser(String)
     * @see CommandManager#chooseCommand(String)
     * @throws IOException
     */
    public void managerWork(String line) throws IOException, ClassNotFoundException {
        String[] data = commandParser(line);
        switch(chooseCommand(data[0])){
            case(0):{
                System.out.println("Запускаю команду "+getHelp().getName()+" ...");
                client.uploadObject(getHelp());
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(1):{
                System.out.println("Запускаю команду "+getInfo().getName()+" ...");
                client.uploadObject(getInfo());
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(2):{
                System.out.println("Запускаю команду "+getShow().getName()+" ...");
                client.uploadObject(getShow());
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(3):{
                Movie newMovie = Asker.askMovie();
                System.out.println("Запускаю команду "+getAdd().getName()+" ...");
                client.uploadObject(getAdd());
                client.uploadObject(newMovie);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(4):{
                boolean successParse = false;
                String argForParse = data[1];
                long id=0;
                while (!successParse) {
                    try {
                        id = Long.valueOf(argForParse);
                        while (!(id>0)){
                            throw new NumberFormatException();
                        }
                        successParse = true;
                    } catch(NumberFormatException e){
                        System.out.println("Введён неверный формат id, повторите ввод.");
                        argForParse=Asker.askIDForExec();
                    }
                }
                Movie newMovie = Asker.askMovie();
                newMovie.setId(id);
                System.out.println("Запускаю команду "+getUpdateByID().getName()+" ...");
                client.uploadObject(getUpdateByID());
                client.uploadObject(newMovie);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(5): {
                boolean successParse = false;
                String argForParse = data[1];
                long id=0;
                while (!successParse) {
                    try {
                        id = Long.valueOf(argForParse);
                        while (!(id>0)){
                            throw new NumberFormatException();
                        }
                        successParse = true;
                    } catch(NumberFormatException e){
                        System.out.println("Введён неверный формат id, повторите ввод.");
                        argForParse= Asker.askIDForExec();
                    }
                }
                System.out.println("Запускаю команду "+getRemoveByID().getName()+" ...");
                client.uploadObject(getRemoveByID());
                client.uploadText(String.valueOf(id));
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(6):{
                System.out.println("Запускаю команду "+getClear().getName()+" ...");
                client.uploadObject(getClear());
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(7):{
                System.out.println("Запускаю команду "+getSave().getName()+" ...");
                client.uploadObject(getSave());
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(8):{
                System.out.println("Запускаю команду "+getExecuteScript().getName()+" ...");
                client.uploadObject(getExecuteScript());
                client.uploadText(data[1]);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(9):{
                System.out.println("Запускаю команду "+getExit().getName()+" ...");
                client.uploadObject(getSave());
                exit.exec("");
                break;
            }
            case(10):{
                Movie newMovie = Asker.askMovie();
                System.out.println("Запускаю команду "+getAddIfMin().getName()+" ...");
                client.uploadObject(getAddIfMin());
                client.uploadObject(newMovie);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(11):{
                Movie movieForCompare = Asker.askMovie();
                System.out.println("Запускаю команду "+getRemoveGreater().getName()+" ...");
                client.uploadObject(getRemoveGreater());
                client.uploadObject(movieForCompare);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(12):{
                Movie movieForCompare = Asker.askMovie();
                System.out.println("Запускаю команду "+getRemoveLower().getName()+" ...");
                client.uploadObject(getRemoveLower());
                client.uploadObject(movieForCompare);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(13):{
                boolean successParse = false;
                String argForParse = data[1];
                long OscarsCount=0;
                while (!successParse) {
                    try {
                        OscarsCount = Long.valueOf(argForParse);
                        while (!(OscarsCount>0)){
                            throw new NumberFormatException();
                        }
                        successParse = true;
                    } catch(NumberFormatException e) {
                        System.out.println("Введен неверный формат данных, повторите ввод.");
                        argForParse= Asker.askIDForExec();
                    }
                }
                System.out.println("Запускаю команду "+getRemoveAllByOscarsCount().getName()+" ...");
                client.uploadObject(getRemoveAllByOscarsCount());
                client.uploadText(String.valueOf(OscarsCount));
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(14):{
                System.out.println("Запускаю команду "+getRemoveAnyByDirector().getName()+" ...");
                client.uploadObject(getRemoveAnyByDirector());
                client.uploadText(data[1]);
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(15):{
                System.out.println("Запускаю команду "+getPrintFieldDescendingOscarsCount().getName()+" ...");
                client.uploadObject(getPrintFieldDescendingOscarsCount());
                if(client.downloadResult()){
                    System.out.println("Команда выполнена.");
                } else {
                    System.out.println("Произошла ошибка при выполнении команды.");
                }
                break;
            }
            case(-1):{
                System.out.println("Команда не распознана.");
                break;
            }
        }
    }


    public Add getAdd() {
        return add;
    }

    public Exit getExit() {
        return exit;
    }

    public Help getHelp() {
        return help;
    }

    public AddIfMin getAddIfMin() {
        return addIfMin;
    }

    public Clear getClear() {
        return clear;
    }

    public ExecuteScript getExecuteScript() {
        return executeScript;
    }

    public Info getInfo() {
        return info;
    }

    public PrintFieldDescendingOscarsCount getPrintFieldDescendingOscarsCount() {
        return printFieldDescendingOscarsCount;
    }

    public RemoveAllByOscarsCount getRemoveAllByOscarsCount() {
        return removeAllByOscarsCount;
    }

    public RemoveAnyByDirector getRemoveAnyByDirector() {
        return removeAnyByDirector;
    }

    public RemoveByID getRemoveByID() {
        return removeByID;
    }

    public RemoveGreater getRemoveGreater() {
        return removeGreater;
    }

    public RemoveLower getRemoveLower() {
        return removeLower;
    }

    public Save getSave() {
        return save;
    }

    public Show getShow() {
        return show;
    }

    public UpdateByID getUpdateByID() {
        return updateByID;
    }


    /**
     * @param line получает на вход строку, разбивает её на пробелы, первое слово - команда, второе (если есть) - аргумент.
     * @return возвращает массив строк, где 0й элемент - команда, 1й (если есть) - аргумент.
     */
    public String[] commandParser(String line) {
        try {
            Scanner scanner = new Scanner(line);
            if (!(line.indexOf(" ") == -1)) {
                scanner.useDelimiter("\\s");
                String command = scanner.next();
                String data = "";
                if (scanner.hasNext()) {
                    data = scanner.next();
                }
                return new String[]{command, data};
            } else {
                String commandwodata = scanner.next();
                return new String[]{commandwodata};
            }
        } catch (NoSuchElementException e){
            return new String[]{"  "};
        }
        }
        /**
         * @param command принимает на вход команду, сопоставляет ей элемент из списка команд.
         * @return возвращает порядковый номер элемента, который удалось сопоставить или -1, если не получилось.
         */
    private int chooseCommand(String command){
        for (int i=0; i<commands.length; i++){
            if (command.equals(commands[i])){
                return i;
            }
        }
        return -1;
    }

    private static Object byteBufferToObject(ByteBuffer byteBuffer)
            throws Exception {
        System.out.println(byteBuffer);
        //byte[] bytes = new byte[byteBuffer.limit()];
        byte[] bytes = byteBuffer.array();
        for (byte b : bytes){
            System.out.print(b);
        }
        System.out.print("\n");
        Object object = deSerializer(bytes);
        return object;
    }

    private static Object deSerializer(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(bytes));
        return objectInputStream.readObject();
    }
}
