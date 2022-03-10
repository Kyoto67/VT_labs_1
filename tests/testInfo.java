package tests;

import commands.Info;

import java.io.IOException;

public class testInfo extends AbstractTest {

    public static void run() throws IOException {
        createTestCollection();
        OutputCollection();Info command = new Info("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", getManager());
        command.exec("0, olesmlvqs, 253.13560490810033, 278, 4972, ACTION, PG_13, oggnbcdvnd, 13.861575158301209, WHITE, BLACK, JAPAN, 137.3920943188411, 102.37304490650152, 487.62666584323557, qyefwlylgq");
    }
}
