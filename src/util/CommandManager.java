package util;

import commands.*;
import commands_for_script.*;
import exceptions.IncompleteData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс сущности, которая будет парсить все поступаемые команды и вызывать их выполнение.
 */
public class CommandManager {

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
    private final CollectionManager collectionManager;
    private ArrayList<String> ScriptsStack = new ArrayList<>();
    private ArrayList<Scanner> scripts = new ArrayList<>();
    private int scriptcounter=-1;
    String[] commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script",
            "exit", "add_if_min", "remove_greater", "remove_lower", "remove_all_by_oscars_count", "remove_any_by_director",
            "print_field_descending_oscars_count"};

    /**
     * Конструктор менеджера. Автоматически инициализирует объекты всех команд при создании и менеджера коллекций.
     * @see CollectionManager
     * @throws IOException
     */
    public CommandManager() throws IOException {
        collectionManager = new CollectionManager();
        add = new Add("add", "добавить новый элемент в коллекцию", getCollectionManager());
        addIfMin = new AddIfMin("add_if_min", "добавить новый элемент в коллекцию, если его " +
                "значение меньше, чем у наименьшего элемента этой коллекции. Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", getCollectionManager());
        clear = new Clear("clear","очистить коллекцию",getCollectionManager());
        executeScript = new ExecuteScript("execute_script filename", "считать и исполнить скрипт из " +
                "указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в " +
                "интерактивном режиме.", this);
        info = new Info("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата " +
                "инициализации, количество элементов и т.д.)", getCollectionManager());
        printFieldDescendingOscarsCount = new PrintFieldDescendingOscarsCount("print_field_descending_oscars_count"
                , "вывести значения поля oscarsCount всех элементов в порядке убывания", getCollectionManager());
        removeAllByOscarsCount= new RemoveAllByOscarsCount("remove_all_by_oscars_count oscarsCount",
                "удалить из коллекции все элементы, значение поля oscarsCount которого эквивалентно заданному"
                , getCollectionManager());
        removeAnyByDirector = new RemoveAnyByDirector("remove_any_by_director director", "удалить из " +
                "коллекции один элемент, значение поля director которого эквивалентно заданному (вводится имя режиссёра)", getCollectionManager());
        removeByID = new RemoveByID("remove_by_id id", "удалить элемент из коллекции по его id",
                getCollectionManager());
        removeGreater = new RemoveGreater("remove_greater", "удалить из коллекции все элементы, " +
                "превышающие заданный.  Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", getCollectionManager());
        removeLower = new RemoveLower("remove_lower", "удалить из коллекции все элементы, меньшие, " +
                "чем заданный. Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", getCollectionManager());
        save = new Save("save", "сохранить коллекцию в файл", getCollectionManager());
        show = new Show("show", "вывести в стандартный поток вывода все элементы коллекции в строковом " +
                "представлении", getCollectionManager());
        updateByID = new UpdateByID("update id", "обновить значение элемента коллекции, id которого " +
                "равен заданному", getCollectionManager());
        exit = new Exit("exit", "завершить программу (без сохранения в файл)");
        help = new Help("help", "вывести справку по доступным командам", this);
        add_for_script = new Add_for_script("add", "добавить новый элемент в коллекцию", getCollectionManager(), this);
        addIfMin_for_script = new AddIfMin_for_script("add_if_min id", "добавить новый элемент в коллекцию, если его " +
                "значение меньше, чем у наименьшего элемента этой коллекции (введите значение id в качестве аргумента)", getCollectionManager(), this);
        updateByID_for_script = new UpdateByID_for_script("update id", "обновить значение элемента коллекции, id которого " +
                "равен заданному", getCollectionManager(), this);
        removeGreater_for_script = new RemoveGreater_for_script("remove_greater", "удалить из коллекции все элементы, " +
                "превышающие заданный.  Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", getCollectionManager(), this);
        removeLower_for_script = new RemoveLower_for_script("remove_lower", "удалить из коллекции все элементы, меньшие, " +
                "чем заданный. Объекты сравниваются по хэшкоду. Значение " +
                "хэшкода объекта формируется из суммы: длины имени фильма, координат с отбрасыванием дробной части, " +
                "координат локации режиссёра с отбрасыванием дробной части, длины имени режиссёра. ", getCollectionManager(), this);
    }

    /**
     * @param line принимает на вход строку, парсит её на команду и её аргументы, по полученному имени запускает выполнение нужной команды, передаёт на вход команде аргумент или пустую строку, выводит ошибку, если не удалось сопоставить строке команду
     * @see CommandManager#commandParser(String)
     * @see CommandManager#chooseCommand(String)
     * @throws IOException
     */
    public void managerWork(String line) throws IOException {
        String[] data = commandParser(line);
        switch(chooseCommand(data[0])){
            case(0):{
                help.exec("");
                break;
            }
            case(1):{
                info.exec("");
                break;
            }
            case(2):{
                show.exec("");
                break;
            }
            case(3):{
                add.exec("");
                break;
            }
            case(4):{
                updateByID.exec(data[1]);
                break;
            }
            case(5): {
                removeByID.exec(data[1]);
                break;
            }
            case(6):{
                clear.exec("");
                break;
            }
            case(7):{
                save.exec("");
                break;
            }
            case(8):{
                executeScript.exec(data[1]);
                break;
            }
            case(9):{
                exit.exec("");
                break;
            }
            case(10):{
                addIfMin.exec("");
                break;
            }
            case(11):{
                removeGreater.exec("");
                break;
            }
            case(12):{
                removeLower.exec("");
                break;
            }
            case(13):{
                removeAllByOscarsCount.exec(data[1]);
                break;
            }
            case(14):{
                removeAnyByDirector.exec(data[1]);
                break;
            }
            case(15):{
                printFieldDescendingOscarsCount.exec("");
                break;
            }
            case(-1):{
                System.out.println("Команда не распознана.");
                break;
            }
        }
    }

    public String getNextLineFromScript() throws IncompleteData {
        Scanner scanner = scripts.get(scriptcounter);
        for (String c : commands) {
            if (scanner.hasNext(c)) {
                throw new IncompleteData("Данные объекта неполные.");
            }
            }
        return scanner.next();
    }

    public void rollScriptForNextCommand(){
        boolean rollComplete = false;
        Scanner scanner = scripts.get(scriptcounter);
        while (scanner.hasNext()){
            for (String c : commands){
                if (scanner.hasNext(c)){
                    rollComplete=true;
                    break;
                }
            }
            if (!rollComplete){
                scanner.next();
            } else {
                break;
            }
        }
    }

    public void scriptscounterIncrement(){
        scriptcounter+=1;
    }

    public void scriptscounterDecrement(){
        scripts.remove(scriptcounter);
        scriptcounter-=1;
        if (scriptcounter==-1){
            ScriptsStack.removeAll(ScriptsStack);
        }
    }

    /**
     * Метод для запуска скрипта. Парсит его и по очереди запускает команды, тащит в себе дополнительные аргументы-строки, если нужны.
     * @param script
     * @throws IOException
     */
    public void managerWorkForScript(Scanner script) throws IOException {
        scripts.add(script);
        while (scripts.get(scriptcounter).hasNext()) {
            String[] data = commandParser(scripts.get(scriptcounter).next());
            switch (chooseCommand(data[0])) {
                case (0): {
                    help.exec("");
                    break;
                }
                case (1): {
                    info.exec("");
                    break;
                }
                case (2): {
                    show.exec("");
                    break;
                }
                case (3): {
                    add_for_script.exec("");
                    break;
                }
                case (4): {
                    updateByID_for_script.exec(data[1]);
                    break;
                }
                case (5): {
                    removeByID.exec(data[1]);
                    break;
                }
                case (6): {
                    clear.exec("");
                    break;
                }
                case (7): {
                    save.exec("");
                    break;
                }
                case (8): {
                    executeScript.exec(data[1]);
                    break;
                }
                case (9): {
                    exit.exec("");
                    break;
                }
                case (10): {
                    addIfMin_for_script.exec("");
                    break;
                }
                case (11): {
                    removeGreater_for_script.exec("");
                    break;
                }
                case (12): {
                    removeLower_for_script.exec("");
                    break;
                }
                case (13): {
                    removeAllByOscarsCount.exec(data[1]);
                    break;
                }
                case (14): {
                    removeAnyByDirector.exec(data[1]);
                    break;
                }
                case (15): {
                    printFieldDescendingOscarsCount.exec("");
                    break;
                }
                case (-1): {
                    System.out.println("Команда не распознана.");
                    break;
                }
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

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public ArrayList<String> getScriptsStack() {
        return ScriptsStack;
    }

    public void setScriptsStack(ArrayList<String> scriptsStack) {
        ScriptsStack = scriptsStack;
    }

     public void addScriptsStack(String newScriptFile){
        ScriptsStack.add(newScriptFile);
     }

     public boolean checkLoopTry(String newScriptFile){
        for (String s : ScriptsStack){
            if (s.equals(newScriptFile)){
                return true;
            }
        }
        return false;
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
}
