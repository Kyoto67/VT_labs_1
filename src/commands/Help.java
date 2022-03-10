package commands;

import util.CommandManager;

import java.io.IOException;

public class Help extends AbstractCommand{

    private final CommandManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, содержащая в себе объекты всех команд и выполняющая их.
     */
    public Help(String name, String description, CommandManager manager) {
        super(name, description);
        this.manager=manager;
    }

    /**
     * Метод вызывает у Менеджера команд геттеры имён и описаний всех команд и выводит их в консоль.
     * @param argument не требует аргументов, вызывается с пустой строкой.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CommandManager
     */
    @Override
    public boolean exec(String argument) throws IOException {
        System.out.println(getName()+": "+getDescription());
        System.out.println(manager.getInfo().getName()+": "+manager.getInfo().getDescription());
        System.out.println(manager.getShow().getName()+": "+manager.getShow().getDescription());
        System.out.println(manager.getAdd().getName()+": "+manager.getAdd().getDescription());
        System.out.println(manager.getUpdateByID().getName()+": "+manager.getUpdateByID().getDescription());
        System.out.println(manager.getRemoveByID().getName()+": "+manager.getRemoveByID().getDescription());
        System.out.println(manager.getClear().getName()+": "+manager.getClear().getDescription());
        System.out.println(manager.getSave().getName()+": "+manager.getSave().getDescription());
        System.out.println(manager.getExecuteScript().getName()+": "+manager.getExecuteScript().getDescription());
        System.out.println(manager.getExit().getName()+": "+manager.getExit().getDescription());
        System.out.println(manager.getAddIfMin().getName()+": "+manager.getAddIfMin().getDescription());
        System.out.println(manager.getRemoveGreater().getName()+": "+manager.getRemoveGreater().getDescription());
        System.out.println(manager.getRemoveLower().getName()+": "+manager.getRemoveLower().getDescription());
        System.out.println(manager.getRemoveAllByOscarsCount().getName()+": "+manager.getRemoveAllByOscarsCount().getDescription());
        System.out.println(manager.getRemoveAnyByDirector().getName()+": "+manager.getRemoveAnyByDirector().getDescription());
        System.out.println(manager.getPrintFieldDescendingOscarsCount().getName()+": "+manager.getPrintFieldDescendingOscarsCount().getDescription());
        return true;
    }
}
