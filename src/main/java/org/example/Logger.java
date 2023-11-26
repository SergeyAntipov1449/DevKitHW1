package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public void writeLog(String msg) throws IOException {
        try (FileWriter logWriter = new FileWriter("History.txt", true)){
            logWriter.write(msg + "\n");

        } catch (IOException e) {
            throw new IOException("Невозможно записать данные в файл");
        }
    }
}
