package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HistoryReader {
    public String readHistory(String fileName) {
        StringBuilder msg = new StringBuilder();
        try (FileReader historyLog = new FileReader(fileName)) {
            int c;
            while ((c = historyLog.read()) != -1) {
                msg.append((char) c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("История сообщений отуствует");
        } catch (IOException e) {
            throw new RuntimeException("Невозможно открыть файл");
        }
        return String.valueOf(msg);
    }
}
