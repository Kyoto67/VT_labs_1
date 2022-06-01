package com.example.vt_labs_1.commands;

import com.example.vt_labs_1.utility.User;

public class Connect extends AbstractCommand{

    private final User user;
    /**
     * конструктор
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public Connect(String name, String description, User user) {
        super(name, description);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean exec() {
        return true;
    }
}
