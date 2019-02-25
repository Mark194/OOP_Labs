package main.data;

import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import main.message.Message;
import main.message.MessageAlert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveFileText implements SaveFile {

    Message message = new MessageAlert();
    private List<Double> numbers;

    public void saveFile() {
        String folder = "";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите директорию");
        directoryChooser.setInitialDirectory(new File("C:\\Users\\zheny\\Desktop\\"));
        File dir = directoryChooser.showDialog(null);
        if (dir != null) {
            folder = dir.getAbsolutePath();
        }
        writeDouble(folder, "sorting_nums.txt");
    }

    public void writeDouble(String directory, String name){
        try (FileWriter fos = new FileWriter(directory+"\\"+name)) {
            fos.write(convertNumbersToLine());
            message.showInformation("Запись в файл выполнена");
        } catch (FileNotFoundException e) {
            message.showError("Cann't open file!");
        } catch(IOException e){
            message.showError("Ошибка ввода/вывода");
        }
    }

    public void setNumbers(ObservableList<Double> numbers) {
        this.numbers = new ArrayList <>(numbers);
    }


    public String convertNumbersToLine(){
        StringBuilder line = new StringBuilder();
        for (double number : numbers){
            line.append(new StringBuilder(Double.toString(number)).append(" "));
        }
        return line.toString();
    }
}
