package common.commands;

import java.io.IOException;

public class Exit extends AbstractCommand {

    private static final long serialVersionUID = 7L;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Exit(String name, String description) {
        super(name, description);
    }

    /**
     * Метод выводит сообщение о завершении программы. Сама программа завершается автоматически после выполнения цикла в методе client.Main с введённой командой exit.
     * @param argument не требует аргументов, вызывается с пустой строкой.
     * @return Возвращает True после выполнения
     * @throws IOException
     */
    @Override
    public boolean exec(String argument) throws IOException {
        System.out.println("Завершаю программу.");
        return true;
    }
}
