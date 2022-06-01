package com.example.vt_labs_1.commands;

/**
 * интерфейс с объявленными методами, которые будут у всех команд
 */
public interface Command {
    String getName();
    String getDescription();
    boolean exec() ;
}
