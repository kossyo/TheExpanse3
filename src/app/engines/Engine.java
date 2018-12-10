package app.engines;

import app.core.Manager;
import app.entities.Colony;
import app.exceptions.ColonyFullException;
import app.exceptions.FamilyDoesNotExistException;
import app.exceptions.FamilyFullException;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {

    private static final String INPUT_TERMINATING_COMMAND = "end";

    private ConsoleInputReader reader;
    private ConsoleOutputWriter writer;
    private Manager manager;

    public Engine(ConsoleInputReader reader, ConsoleOutputWriter writer, Manager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    public void run() {

        String[] famAndColLimits = reader.readLine().split("\\s+");
        int maxFamilyCount = Integer.parseInt(famAndColLimits[0]);
        int maxFamilyCapacity = Integer.parseInt(famAndColLimits[1]);

        Colony colony = new Colony(maxFamilyCount, maxFamilyCapacity);

        String inputLine;

        while (!INPUT_TERMINATING_COMMAND.equals(inputLine = reader.readLine())) {

            List<String> cmdArgs = //Arrays.asList(inputLine.split("\\s+"));
                    Arrays.stream(inputLine.split("\\s+")).collect(Collectors.toList());

            switch (cmdArgs.get(0)) {
                case "insert":

                    try {
                        this.manager.insert(colony, cmdArgs, maxFamilyCapacity);
                    } catch (ColonyFullException | FamilyFullException e) {
                        this.writer.writeLine(e.getMessage());
                    }
                    break;

                case "remove":
                    //try {
                        this.manager.remove(colony, cmdArgs);
                    //}catch (NullPointerException e){}
                    break;

                case "grow":

                    this.manager.grow(colony, Integer.parseInt(cmdArgs.get(1)));
                    break;

                case "potential":

                    this.writer.writeLine(this.manager.potential(colony));
                    break;

                case "capacity":

                    this.writer.writeLine(this.manager.capacity(colony));
                    break;

                case "family":

                    try {
                        this.writer.writeLine(this.manager.family(colony, cmdArgs.get(1)));
                    } catch (FamilyDoesNotExistException fdne) {
                        this.writer.writeLine(fdne.getMessage());
                    }
                    break;

                    default: break;
            }
        }
    }
}