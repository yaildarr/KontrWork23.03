package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ProgramDataBase database = new ProgramDataBase();
            database.importFromFile("/Users/ildar/Documents/Java itis/KontrWork23.03/src/main/java/org/example/schedule.txt");
            List<Program> allPrograms = database.getAllPrograms();
//            System.out.println(allPrograms);
            System.out.println(database.getProgramsByChannel("Первый"));
        } catch (IOException e) {
            System.out.println("ошибка");
        }
    }
}