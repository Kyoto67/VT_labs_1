package commands;


import utility.Module;

import java.io.IOException;

public class Exit extends AbstractCommand {

    private Save save;

    /**
     * конструктор
     * @param name
     * @param description
     */
    public Exit(String name, String description) {
        super(name, description);
    }

    public void setSave(Save save) {
        this.save = save;
    }

    public Save getSave() {
        return save;
    }

    /**
     * Метод выводит сообщение о завершении программы. Сама программа завершается автоматически после выполнения цикла в методе client.client.Main с введённой командой exit.
     * @return Возвращает True после выполнения
     * @throws IOException
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage("Завершаю программу.");
        return true;
    }
}
