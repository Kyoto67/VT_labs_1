package tests;

import data.*;
import util.GeneratingRandomInfo;

import java.io.IOException;

public class testMovie extends AbstractTest {

    public static void run() throws IOException {
        Movie m = GeneratingRandomInfo.generateOneObject();
        System.out.println(m);
    }
}
