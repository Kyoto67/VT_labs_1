package tests;

import commands.AddIfMin;
import commands.RemoveLower;
import java.io.IOException;

public class testAddIfMin extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();
        RemoveLower command1 = new RemoveLower("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный", getManager());
        command1.exec("4");
        AddIfMin command = new AddIfMin("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", getManager());
        command.exec("0, olesmlvqs, 253.13560490810033, 278, 4972, ACTION, PG_13, oggnbcdvnd, 13.861575158301209, WHITE, BLACK, JAPAN, 137.3920943188411, 102.37304490650152, 487.62666584323557, qyefwlylgq");
        OutputCollection();
    }
}
