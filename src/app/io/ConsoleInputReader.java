package app.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader {

    private BufferedReader br;

    public ConsoleInputReader() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() {

        try {
            return this.br.readLine();
        } catch (IOException ignored) {
            ;
        }
        return null;
    }
}
