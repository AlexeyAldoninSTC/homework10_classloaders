package app.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SourceFileFiller  implements Runnable {

    /**
     * Creates and fills Class header with method access modifier and signature
     * Method body is filled by code entered via console
     * @throws IOException - in case of file access failure
     */
    @Override
    public void run() {
        final String s = "package app.samples;\n\npublic class SomeClass {\n public void doWork() {\n  ";
        StringBuilder builder = new StringBuilder(s);
        String path = "src/main/java/app/samples/SomeClass.java";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите текст реализации метода");
            String temp = " ";
            while (!temp.equals("")) {
                temp = scanner.nextLine();
                builder.append(temp).append("\n");
            }
        }
        builder.append("}\n}");
        try {
            Files.write(Paths.get(path), builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
