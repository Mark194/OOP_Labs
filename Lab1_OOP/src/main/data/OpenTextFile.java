package main.data;

import javafx.stage.FileChooser;
import main.message.Message;
import main.message.MessageAlert;

import java.io.*;
import java.util.*;

public class OpenTextFile implements OpenFile {
    private Message message = new MessageAlert();
    private List<Double> numbers = new ArrayList<>();
    @Override
    public void openFile() {
        String folder;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите текстовый файл");
        fileChooser.setInitialDirectory(new File("C:\\Users\\zheny\\Desktop\\"));
        File directory = fileChooser.showOpenDialog(null);
        if (directory != null) {
            folder = directory.getAbsolutePath();
            readNumber(folder);
        }
    }

    private void readNumber(final String folder) {
        try  {
            File file = new File(folder);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    numbers.add(scanner.nextDouble());
                } else {
                    scanner.next();
                }
            }

            if (!numbers.isEmpty())
                message.showInformation("Числа успешно считаны.");
            else {
                message.showError("Числа не найдены в файле!");
            }
        } catch (FileNotFoundException e) {
            message.showError("Файл не найден!");
       }
    }

    public List<Double> getNumbers() {
        return numbers;
    }
}
