package commands;

import java.io.IOException;

/**
 * интерфейс с объявленными методами, которые будут у всех команд
 */
public interface Command {
    String getName();
    String getDescription();
    boolean exec(String argument) throws IOException;
}
