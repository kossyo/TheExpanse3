package app.io;

import java.io.OutputStreamWriter;

public class ConsoleOutputWriter {

    private OutputStreamWriter writer;

    public ConsoleOutputWriter() {
    }

    public void writeLine(String output){
        System.out.print(output);
    }
}
