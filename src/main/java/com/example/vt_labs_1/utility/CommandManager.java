package com.example.vt_labs_1.utility;import com.example.vt_labs_1.client.*;import com.example.vt_labs_1.data.*;import com.example.vt_labs_1.commands.*;import com.example.vt_labs_1.exceptions.ArgumentException;import com.example.vt_labs_1.exceptions.IncorrectData;import org.controlsfx.control.action.ActionGroup;import java.io.Serializable;import java.util.NoSuchElementException;import java.util.Scanner;/** * Класс сущности, которая будет парсить все поступаемые команды и вызывать их выполнение. */public class CommandManager implements Serializable {    /**     * Поля, содержащие объекты команд.     */    private final Add add;    private final AddIfMin addIfMin;    private final Clear clear;    private final ExecuteScript executeScript;    private final Info info;    private final PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount;    private final RemoveAllByOscarsCount removeAllByOscarsCount;    private final RemoveAnyByDirector removeAnyByDirector;    private final RemoveByID removeByID;    private final RemoveGreater removeGreater;    private final RemoveLower removeLower;    private final Show show;    private final UpdateByID updateByID;    private final Exit exit;    private final Help help;    private final Client client;    private final ScriptManager scriptManager;    private final String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "execute_script",            "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",            "print_field_descending_oscars_count", "getTable"};    /**     * Конструктор менеджера. Автоматически инициализирует объекты всех команд при создании и менеджера коллекций.     */    public CommandManager(Client client) {        this.client = client;        add = new Add("add", "добавить новый элемент в коллекцию");        addIfMin = new AddIfMin("add_if_min", "добавить новый элемент в коллекцию, если его " +                "значение меньше, чем у наименьшего элемента этой коллекции. Объекты сравниваются по хэшкоду. Значение " +                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ");        clear = new Clear("clear", "очистить коллекцию");        info = new Info("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата " +                "инициализации, количество элементов и т.д.)");        printFieldDescendingOscarsCount = new PrintFieldDescendingOscarsCount("print_field_descending_oscars_count"                , "вывести значения поля oscarsCount всех элементов в порядке убывания");        removeAllByOscarsCount = new RemoveAllByOscarsCount("remove_all_by_oscars_count oscarsCount",                "удалить из коллекции все элементы, значение поля oscarsCount которого эквивалентно заданному");        removeAnyByDirector = new RemoveAnyByDirector("remove_any_by_director director", "удалить из " +                "коллекции один элемент, значение поля director которого эквивалентно заданному (вводится имя режиссёра)");        removeByID = new RemoveByID("remove_by_id id", "удалить элемент из коллекции по его id");        removeGreater = new RemoveGreater("remove_greater", "удалить из коллекции все элементы, " +                "превышающие заданный.  Объекты сравниваются по хэшкоду. Значение " +                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ");        removeLower = new RemoveLower("remove_lower", "удалить из коллекции все элементы, меньшие, " +                "чем заданный. Объекты сравниваются по хэшкоду. Значение " +                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ");        show = new Show("show", "вывести в стандартный поток вывода все элементы коллекции в строковом " +                "представлении");        updateByID = new UpdateByID("update id", "обновить значение элемента коллекции, id которого " +                "равен заданному");        exit = new Exit("exit", "завершить программу (без сохранения в файл)");        executeScript = new ExecuteScript("execute_script filename", "считать и исполнить скрипт из " +                "указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в " +                "интерактивном режиме.");        help = new Help("help", "вывести справку по доступным командам", add, addIfMin, clear,                executeScript,                info, printFieldDescendingOscarsCount, removeAllByOscarsCount, removeAnyByDirector, removeByID, removeGreater, removeLower, show, updateByID, exit);        scriptManager = new ScriptManager(getAdd(), getAddIfMin(), getClear(), getExecuteScript(), getInfo(), getPrintFieldDescendingOscarsCount(), getRemoveAllByOscarsCount(), getRemoveAnyByDirector(), getRemoveByID(), getRemoveGreater(), getRemoveLower(), getShow(), getUpdateByID(), getExit(), getHelp());    }    /**     * @param line принимает на вход строку, парсит её на команду и её аргументы, по полученному имени запускает выполнение нужной команды, передаёт на вход команде аргумент или пустую строку, выводит ошибку, если не удалось сопоставить строке команду     * @see CommandManager#commandParser(String)     * @see CommandManager#chooseCommand(String)     */    public String managerWork(String line) throws ArgumentException {        String ans = "";        String[] data = commandParser(line);        switch (chooseCommand(data[0])) {            case (0): {                ans += ("Запускаю команду " + getHelp().getName() + " ...");                ans += (client.run(getHelp()));                break;            }            case (1): {                ans += ("Запускаю команду " + getInfo().getName() + " ...");                ans += (client.run(getInfo()));                break;            }            case (2): {                ans += ("Запускаю команду " + getShow().getName() + " ...");                ans += (client.run(getShow()));                break;            }            case (3): {                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");                Movie movie = Data.movie;                    ans += ("Запускаю команду " + getAdd().getName() + " ...");                    add.setArgument(movie);                    Data.movie = null;                    add.setUser(client.getUser());                    ans += (client.run(getAdd()));                break;            }            case (4): {                String argForParse = data[1];                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");                try {                    long id = Long.parseLong(argForParse);                    if (!(id > 0)) {                        throw new NumberFormatException();                    }                    Movie movie = Data.movie;                    Data.movie = null;                    movie.setId(id);                    ans += ("Запускаю команду " + getUpdateByID().getName() + ": " + id + " ...");                    updateByID.setArgument(movie);                    updateByID.setUser(client.getUser());                    ans += (client.run(getUpdateByID()));                } catch (NumberFormatException | IncorrectData exception) {                    throw new ArgumentException("Введён неверный формат id, повторите ввод.");                }                break;            }            case (5): {                String argForParse = data[1];                try {                    long id = Long.parseLong(argForParse);                    if (!(id > 0)) {                        throw new NumberFormatException();                    }                    ans += ("Запускаю команду " + getRemoveByID().getName() + ": " + id + " ...");                    removeByID.setArgument(id);                    removeByID.setUser(client.getUser());                    ans += (client.run(getRemoveByID()));                } catch (NumberFormatException e) {                    throw new ArgumentException("Введён неверный формат id, повторите ввод.");                }                break;            }            case (6): {                ans += ("Запускаю команду " + getClear().getName() + " ...");                clear.setUser(client.getUser());                ans += (client.run(getClear()));                break;            }            case (7): {                ans += ("Запускаю команду " + getExecuteScript().getName() + ": " + data[1] + " ...");                executeScript.setArgument(data[1]);                executeScript.setScriptManager(scriptManager);                executeScript.setUser(client.getUser());                ans += (client.run(getExecuteScript()));                break;            }            case (8): {                exit.setUser(client.getUser());                ans += (client.run(exit));                break;            }            case (9): {                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");                Movie movie = Data.movie;                Data.movie = null;                ans += ("Запускаю команду " + getAddIfMin().getName() + " ...");                addIfMin.setArgument(movie);                addIfMin.setUser(client.getUser());                ans += (client.run(getAddIfMin()));                break;            }            case (10): {                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");                Movie movieForCompare = Data.movie;                Data.movie = null;                ans += ("Запускаю команду " + getRemoveGreater().getName() + ": " + movieForCompare.hashCode() + " ...");                removeGreater.setArgument(movieForCompare);                removeGreater.setUser(client.getUser());                ans += (client.run(getRemoveGreater()));                break;            }            case (11): {                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");                Movie movieForCompare = Data.movie;                Data.movie = null;                ans += ("Запускаю команду " + getRemoveLower().getName() + ": " + movieForCompare.hashCode() + " ...");                removeLower.setArgument(movieForCompare);                removeLower.setUser(client.getUser());                ans += (client.run(getRemoveLower()));                break;            }            case (12): {                String argForParse = data[1];                try {                    long OscarsCount = Long.parseLong(argForParse);                    if (!(OscarsCount > 0)) {                        throw new NumberFormatException();                    }                    ans += ("Запускаю команду " + getRemoveAllByOscarsCount().getName() + ": " + OscarsCount + " ...");                    removeAllByOscarsCount.setArgument(OscarsCount);                    removeAllByOscarsCount.setUser(client.getUser());                    ans += (client.run(getRemoveAllByOscarsCount()));                } catch (NumberFormatException e) {                    ans += ("Введен неверный формат данных, повторите ввод.");                }                break;            }            case (13): {                ans += ("Запускаю команду " + getRemoveAnyByDirector().getName() + ": " + data[1] + " ...");                removeAnyByDirector.setArgument(data[1]);                removeAnyByDirector.setUser(client.getUser());                ans += (client.run(getRemoveAnyByDirector()));                break;            }            case (14): {                ans += ("Запускаю команду " + getPrintFieldDescendingOscarsCount().getName() + " ...");                ans += (client.run(getPrintFieldDescendingOscarsCount()));                break;            }            case (15): {                return client.run(new getTable("getTable", "desc"));            }            case (-1): {                ans += ("Команда не распознана.");                break;            }        }        return ans;    }    public Add getAdd() {        return add;    }    public Exit getExit() {        return exit;    }    public Help getHelp() {        return help;    }    public AddIfMin getAddIfMin() {        return addIfMin;    }    public Clear getClear() {        return clear;    }    public ExecuteScript getExecuteScript() {        return executeScript;    }    public Info getInfo() {        return info;    }    public PrintFieldDescendingOscarsCount getPrintFieldDescendingOscarsCount() {        return printFieldDescendingOscarsCount;    }    public RemoveAllByOscarsCount getRemoveAllByOscarsCount() {        return removeAllByOscarsCount;    }    public RemoveAnyByDirector getRemoveAnyByDirector() {        return removeAnyByDirector;    }    public RemoveByID getRemoveByID() {        return removeByID;    }    public RemoveGreater getRemoveGreater() {        return removeGreater;    }    public RemoveLower getRemoveLower() {        return removeLower;    }    public Show getShow() {        return show;    }    public UpdateByID getUpdateByID() {        return updateByID;    }    /**     * @param line получает на вход строку, разбивает её на пробелы, первое слово - команда, второе (если есть) - аргумент.     * @return возвращает массив строк, где 0й элемент - команда, 1й (если есть) - аргумент.     */    public String[] commandParser(String line) {        try {            Scanner scanner = new Scanner(line);            if (!(line.indexOf(" ") == -1)) {                scanner.useDelimiter("\\s");                String command = scanner.next();                String data = "";                if (scanner.hasNext()) {                    data = scanner.next();                }                return new String[]{command, data};            } else {                String commandwodata = scanner.next();                return new String[]{commandwodata, " "};            }        } catch (NoSuchElementException e) {            return new String[]{"  ", " "};        }    }    /**     * @param command принимает на вход команду, сопоставляет ей элемент из списка команд.     * @return возвращает порядковый номер элемента, который удалось сопоставить или -1, если не получилось.     */    private int chooseCommand(String command) {        for (int i = 0; i < commands.length; i++) {            if (command.equals(commands[i])) {                return i;            }        }        return -1;    }}