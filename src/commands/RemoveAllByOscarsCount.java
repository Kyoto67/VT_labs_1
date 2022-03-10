package commands;

import util.Asker;
import util.CollectionManager;

import java.io.IOException;

public class RemoveAllByOscarsCount extends AbstractCommand{

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveAllByOscarsCount(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager=manager;
    }

    /**
     * Метод пытается считать из аргумента число oscarsCount, если данные введены неверно, запрашивает повторный ввод.
     * После вызывает у менеджера коллекций удаление всех элементов, чей oscarsCount равен переданному команде в аргументе.
     * @param argument число oscarsCount, объекты обладающие которым должны быть удалены из коллекции.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see Asker#askIDForExec() 
     * @see CollectionManager#removeByOscarsCount(long) 
     */
    @Override
    public boolean exec(String argument) throws IOException {
        boolean successParse = false;
        String argForParse = argument;
        long OscarsCount=0;
        while (!successParse) {
            try {
                OscarsCount = Long.valueOf(argForParse);
                while ((!(OscarsCount>0)) || (!(manager.checkMatchingOscarsCount(OscarsCount)))){
                    throw new NumberFormatException();
                }
                successParse = true;
            } catch(NumberFormatException e){
                System.out.println("Введено неверное количество, повторите ввод.");
                argForParse= Asker.askIDForExec();
            }
        }
        manager.removeByOscarsCount(OscarsCount);
        return true;
    }
}
