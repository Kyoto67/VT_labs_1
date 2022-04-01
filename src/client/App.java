package client;

import java.io.IOException;

//@author Kyoto67
//@variant 3130

public class App {
    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 1337);
    }
}