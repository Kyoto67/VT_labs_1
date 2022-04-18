package common.commands;

import server.Server;

import java.io.IOException;

public class Help extends AbstractCommand{

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
    private static final long serialVersionUID = 8L;

    /**
     * конструктор
     * @param name
     * @param description
     * @param add
     * @param addIfMin
     * @param clear
     * @param executeScript
     * @param info
     * @param printFieldDescendingOscarsCount
     * @param removeAllByOscarsCount
     * @param removeAnyByDirector
     * @param removeByID
     * @param removeGreater
     * @param removeLower
     * @param save
     * @param show
     * @param updateByID
     * @param exit
     */
    public Help(String name, String description, Add add, AddIfMin addIfMin, Clear clear,
                ExecuteScript executeScript,
                Info info, PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount, RemoveAllByOscarsCount removeAllByOscarsCount, RemoveAnyByDirector removeAnyByDirector, RemoveByID removeByID, RemoveGreater removeGreater, RemoveLower removeLower, Save save, Show show, UpdateByID updateByID, Exit exit) {
        super(name, description);
        this.add = add;
        this.addIfMin = addIfMin;
        this.clear = clear;
        this.executeScript = executeScript;
        this.info = info;
        this.printFieldDescendingOscarsCount = printFieldDescendingOscarsCount;
        this.removeAllByOscarsCount = removeAllByOscarsCount;
        this.removeAnyByDirector = removeAnyByDirector;
        this.removeByID = removeByID;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.save = save;
        this.show = show;
        this.updateByID = updateByID;
        this.exit = exit;
    }

    /**
     * Метод вызывает у Менеджера команд геттеры имён и описаний всех команд и выводит их в консоль.
     * @param argument не требует аргументов, вызывается с пустой строкой.
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec(String argument) throws IOException {
        Server.outputMessage+=getName()+": "+getDescription()+"\n"+info.getName()+": "+info.getDescription()+"\n"+
                show.getName()+": "+show.getDescription()+"\n"+add.getName()+": "+add.getDescription()+"\n"+updateByID.getName()
                +": "+updateByID.getDescription()+"\n"+removeByID.getName()+": "+removeByID.getDescription()+"\n"+clear.getName()+
                ": "+clear.getDescription()+"\n"+save.getName()+": "+save.getDescription()+
                " (доступна только на сервере после завершения клиентского приложения)"+"\n"+executeScript.getName()+": "+executeScript.getDescription()+
                "\n"+exit.getName()+": "+exit.getDescription()+"\n"+addIfMin.getName()+": "+addIfMin.getDescription()+"\n"+
                removeGreater.getName()+": "+removeGreater.getDescription()+"\n"+removeLower.getName()+": "+removeLower.getDescription()
                +"\n"+removeAllByOscarsCount.getName()+": "+removeAllByOscarsCount.getDescription()+"\n"+removeAnyByDirector.getName()+
                ": "+removeAnyByDirector.getDescription()+"\n"+printFieldDescendingOscarsCount.getName()+": "+printFieldDescendingOscarsCount.getDescription();
        return true;
    }
}
