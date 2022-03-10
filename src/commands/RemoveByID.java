package commands;

import util.Asker;
import util.CollectionManager;

public class RemoveByID extends AbstractCommand{

    private final CollectionManager manager;

    /**
     * конструктор
     * @param name
     * @param description
     * @param manager сущность, управляющая коллекцией. Команда выполняется консолью, вызывая методы у менеджера
     */
    public RemoveByID(String name, String description, CollectionManager manager) {
        super(name, description);
        this.manager = manager;
    }

    /**
     * Метод конвертирует переданный id в числовой формат, при неверно введённом аргументе запрашивает повторный ввод.
     * Если элемент с введённым ID отсутствует в коллекции, запрашивает повторный ввод.
     * Вызывает удаление элемента из коллекции по номеру id
     * @param argument номер id элемента, который нужно удалить
     * @return Возвращает True при выполнении
     * @see CollectionManager#checkMatchingID(long)
     * @see CollectionManager#removeElementByID(long)
     * @see Asker#askIDForExec()
     */
    @Override
    public boolean exec(String argument) {
        boolean successParse = false;
        String argForParse = argument;
        long id=0;
        while (!successParse) {
            try {
                id = Long.valueOf(argForParse);
                while ((!(id>0)) || (manager.checkMatchingID(id))){
                    throw new NumberFormatException();
                }
                successParse = true;
            } catch(NumberFormatException e){
                System.out.println("Введён неверный id, повторите ввод.");
                argForParse= Asker.askIDForExec();
            }
        }
        manager.removeElementByID(id);
        return true;
    }
}
