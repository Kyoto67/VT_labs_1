package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.Module;
import com.example.vt_labs_1.utility.User;

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
     */
    @Override
    public boolean exec() {
        Module.addMessage("Завершаю программу.");
        return true;
    }
}
