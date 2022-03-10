package tests;

import commands.ExecuteScript;
import util.CommandManager;

import java.io.IOException;

public class testExecuteScript extends AbstractTest{
    public static void run() throws IOException {
        ExecuteScript command = new ExecuteScript("name", "descripton", new CommandManager());
        command.exec("scripttest");
    }
}
