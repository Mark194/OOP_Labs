package main.data;

import javafx.collections.ObservableList;

public interface SaveFile {
    void saveFile();
    void setNumbers(ObservableList<Double> numbers);
}
