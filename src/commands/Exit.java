package commands;


import utility.Module;
import utility.User;

import java.io.IOException;

public class Exit extends AbstractCommand {

    private User user;

    /**
     * конструктор
     *
     * @param name
     * @param description
     */
    public Exit(String name, String description) {
        super(name, description);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * Метод выводит сообщение о завершении программы. Сама программа завершается автоматически после выполнения цикла в методе client.client.Main с введённой командой exit.
     *
     * @return Возвращает True после выполнения
     * @throws IOException
     */
    @Override
    public boolean exec() throws IOException {
        Module.addMessage("Завершаю программу.");
        return true;
    }
}
