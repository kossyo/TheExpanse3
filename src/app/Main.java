package app;

import app.core.Manager;
import app.engines.Engine;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;

public class Main {

    public static void main(String[] args) {

        ConsoleInputReader reader = new ConsoleInputReader();
        ConsoleOutputWriter writer = new ConsoleOutputWriter();
        Manager manager = new Manager();
        Engine engine = new Engine(reader, writer, manager);
        engine.run();
    }
}
