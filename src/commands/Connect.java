package commands;

import java.io.IOException;

public class Connect extends AbstractCommand{
    /**
     * конструктор
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public Connect(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean exec() throws IOException {
        System.out.println("Новый клиент подключился к серверу.");
        return true;
    }
}
