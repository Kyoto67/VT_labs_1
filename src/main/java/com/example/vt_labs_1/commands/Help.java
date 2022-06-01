package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;

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
    private final Show show;
    private final UpdateByID updateByID;
    private final Exit exit;

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
     * @param show
     * @param updateByID
     * @param exit
     */
    public Help(String name, String description, Add add, AddIfMin addIfMin, Clear clear,
                ExecuteScript executeScript,
                Info info, PrintFieldDescendingOscarsCount printFieldDescendingOscarsCount, RemoveAllByOscarsCount removeAllByOscarsCount, RemoveAnyByDirector removeAnyByDirector, RemoveByID removeByID, RemoveGreater removeGreater, RemoveLower removeLower, Show show, UpdateByID updateByID, Exit exit) {
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
        this.show = show;
        this.updateByID = updateByID;
        this.exit = exit;
    }

    /**
     * Метод вызывает у Менеджера команд геттеры имён и описаний всех команд и выводит их в консоль.
     * @return Возвращает True при выполнении
     */
    @Override
    public boolean exec()  {
        Module.addMessage(getName()+": "+getDescription()+"\n"+info.getName()+": "+info.getDescription()+"\n"+
                show.getName()+": "+show.getDescription()+"\n"+add.getName()+": "+add.getDescription()+"\n"+updateByID.getName()
                +": "+updateByID.getDescription()+"\n"+removeByID.getName()+": "+removeByID.getDescription()+"\n"+clear.getName()+
                ": "+clear.getDescription()+"\n"+executeScript.getName()+": "+executeScript.getDescription()+
                "\n"+exit.getName()+": "+exit.getDescription()+"\n"+addIfMin.getName()+": "+addIfMin.getDescription()+"\n"+
                removeGreater.getName()+": "+removeGreater.getDescription()+"\n"+removeLower.getName()+": "+removeLower.getDescription()
                +"\n"+removeAllByOscarsCount.getName()+": "+removeAllByOscarsCount.getDescription()+"\n"+removeAnyByDirector.getName()+
                ": "+removeAnyByDirector.getDescription()+"\n"+printFieldDescendingOscarsCount.getName()+": "+printFieldDescendingOscarsCount.getDescription());
        return true;
    }
}
