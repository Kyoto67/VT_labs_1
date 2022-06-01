package com.example.vt_labs_1.commands;


import java.io.Serializable;

/**
 * Абстрактный класс, от которого наследуются все команды
 */
public abstract class AbstractCommand implements Command, Serializable {
    private final String name;
    private final String description;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     */
    public AbstractCommand(String name, String description){
        this.name=name;
        this.description=description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}
