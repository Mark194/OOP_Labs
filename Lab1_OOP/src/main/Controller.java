package main;

import main.data.OpenFile;
import main.data.OpenTextFile;
import main.data.SaveFile;
import main.data.SaveFileText;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.message.Message;
import main.message.MessageAlert;
import main.sorting.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.NANOSECONDS;


public class Controller {
    Message message = new MessageAlert();
    private List<Double> numbers, result;
    int selectedMenuItem = 0;
    String[] lines = {"Сортировка простыми вставками", "Сортировка простым выбором", "Сортировка простым обменом",
            "Сортировка методом Шелла", "Метод квадратичной выборки", "Алгоритм \"быстрой сортировки\"",
            "Сортировка методом слияния"};
    @FXML
    Label resultTime;
    @FXML
    ListView initialListView, sortingListView;
    @FXML
    ChoiceBox sortSelection;
    @FXML
    Button sorting;
    @FXML
    Menu menu;

    @FXML
    private void initialize(){
        sortSelection.setItems(FXCollections.observableList(Arrays.asList(lines)));
        sortSelection.getSelectionModel().select(0);
        setControlStatus(true);
        menu.setOnAction(event -> selectedMenuItem = Arrays.asList(lines).indexOf(((MenuItem) event.getTarget()).getText()));
    }

    @FXML
    void newFile(){
        if (numbers != null)
            numbers.clear();
        initialListView.getItems().clear();
        sortingListView.getItems().clear();
        setControlStatus(true);
    }

    @FXML
    void openFile(){
        OpenFile openFile = new OpenTextFile();
        openFile.openFile();
        numbers =  openFile.getNumbers();
        initialListView.setItems(FXCollections.observableArrayList(numbers));
        if (numbers.size() > 0)
            setControlStatus(false);
    }

    @FXML
    void saveFile(){
        if (sortingListView.getItems().size() > 0) {
            if (message.showConfirmation("Сохранить изменения?").getResult() == ButtonType.YES){
                SaveFile saveFile = new SaveFileText();
                saveFile.setNumbers(sortingListView.getItems());
                saveFile.saveFile();
            }
        } else {
            message.showError("Данные для сохранения отсутствуют!");
        }
    }

    @FXML
    void close(){
        if (sortingListView.getItems().size() > 0) {
            if (message.showConfirmation("Сохранить изменения?").getResult() == ButtonType.YES){
                SaveFile saveFile = new SaveFileText();
                saveFile.setNumbers(sortingListView.getItems());
                saveFile.saveFile();
            }
        }
        System.exit(0);
    }

    @FXML
    private void runSorting(){
        if (!sortSelection.isDisable()) {
            sortSelection.getSelectionModel().select(selectedMenuItem);
            sorting();
        }
    }

    @FXML
    private void sorting(){
        result = new ArrayList <>(numbers);
        long start = System.nanoTime();
        selectSorting();
        showTime(start);
    }

    private void selectSorting(){
        Sorting sort = new Sorting();
        switch (sortSelection.getSelectionModel().getSelectedIndex()){
            case 0: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.sortBySimpleInsert(result))); break;
            case 1: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.simpleSorting(result))); break;
            case 2: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.sortDirectExchange(result))); break;
            case 3: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.sortingShell(result))); break;
            case 4: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.methodQuadraticSampling(result))); break;
            case 5: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.algorithmQuickSort(result,0, result.size()-1))); break;
            case 6: sortingListView.setItems(FXCollections
                    .observableArrayList(sort.mergesortInner(result,
                            createArrayList(result.size()), 0, result.size()))); break;
            default: message.showError("Неизвестный код!");
        }
    }

    public void showTime(long start){
        long finish = System.nanoTime();
        resultTime.setVisible(true);
        resultTime.setText(new StringBuilder(String.valueOf("Время выполнения: "))
                .append(NANOSECONDS.toMicros(finish-start))
                .append("мкс").toString());
    }

    public ArrayList<Double> createArrayList(int size){
        ArrayList<Double> arrayList = new ArrayList <>();
        for (int i = 0; i < size; i++) {
            arrayList.add(0.0);
        }
        return arrayList;
    }

    public void setControlStatus(boolean status){
        sortSelection.setDisable(status);
        sorting.setDisable(status);
        menu.setDisable(status);
    }
}
