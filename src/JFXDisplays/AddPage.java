package JFXDisplays;

import Basic_Classes.Password;
import Components.City;
import Components.NPC;
import Components.Special;
import Components.WorldElement;
import Work_Classes.FileProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class AddPage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final FileProcessor fileProcessor;
    private final String addType;
    private final HashMap<String,WorldElement> processor;

    public AddPage(Stage stage, FileProcessor fileProcessor, Page previous, HashMap<String, WorldElement> processor, String addType){
        this.primaryStage = stage;
        this.previous = previous;
        this.fileProcessor = fileProcessor;
        this.addType = addType;
        this.processor = processor;

        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
    }

    @Override
    public void loadPage() {

        int level = 0;

        // Get the name of the NPC with text box
        Label NPCname = new Label("Name:");
        NPCname.setTextFill(Color.WHITE.darker());
        grid.add(NPCname, 0, level);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, level);
        System.out.println("Name Text generated");
        level++;

        TextField occupationTextField = null;
        if (addType.equals("NPC")) {
            // Gets the NPC's occupation
            Label NPCOccupation = new Label("Occupation:");
            NPCOccupation.setTextFill(Color.WHITE.darker());
            grid.add(NPCOccupation, 0, level);
            System.out.print("Occupation generated");

            occupationTextField = new TextField();
            occupationTextField.setPrefWidth(200);
            grid.add(occupationTextField, 1, level);
            System.out.println("Occupation Text generated");
            level++;
        }

        TextField populationTextField = null;
        TextField songTextField = null;
        TextArea tradeTextField = null;
        ListView<String> residentsList = null;
        ListView<String> specialsList = null;
        if (addType.equals("City")){
            // Gets the City's population
            Label CityPopulation = new Label("Population:");
            CityPopulation.setTextFill(Color.WHITE.darker());
            grid.add(CityPopulation, 0, level);
            System.out.print("Population generated");

            populationTextField = new TextField();
            populationTextField.setPrefWidth(50);
            grid.add(populationTextField, 1, level);
            System.out.println("Population Text generated");
            level++;

            // Gets the City's song
            Label citySong = new Label("Song:");
            citySong.setTextFill(Color.WHITE.darker());
            grid.add(citySong, 0, level);
            System.out.print("Description Generated");

            songTextField = new TextField();
            songTextField.setPrefWidth(200);
            grid.add(songTextField, 1, level);
            System.out.println("Song Text generated");
            level++;

            // Gets the City's trades
            Label cityTrade = new Label("Trades:");
            cityTrade.setTextFill(Color.WHITE.darker());
            grid.add(cityTrade, 0, level);
            System.out.print("Trades Generated");

            tradeTextField = new TextArea();
            tradeTextField.setPrefWidth(200);
            tradeTextField.setPrefHeight(40);
            tradeTextField.setWrapText(true);
            grid.add(tradeTextField, 1, level);
            System.out.println("Trade Text generated");
            level++;

            // Gets the City's Residents
            Label cityResidents = new Label("Residents:");
            cityResidents.setTextFill(Color.WHITE.darker());
            grid.add(cityResidents, 3, 0);
            System.out.print("Residents Generated");

            HashSet<WorldElement> NPCList = fileProcessor.getSelectedList("NPC");
            ArrayList<String> NPCNames = new ArrayList<>();
            NPCNames.add("Choose Residents");
            for (WorldElement n : NPCList) {
                NPCNames.add(n.getName());
            }
            ComboBox<String> residentsComboBox = new ComboBox<>(FXCollections.observableArrayList(NPCNames));
            residentsComboBox.getSelectionModel().selectFirst();
            grid.add(residentsComboBox, 4, 0);

            residentsList = new ListView<>();
            ObservableList<String> residentsObservableList = FXCollections.observableArrayList ();
            residentsList.setItems(residentsObservableList);
            residentsList.setPrefWidth(200);
            residentsList.setPrefHeight(100);
            grid.add(residentsList, 4, 1, 4, 2);
            level++;

            ListView<String> finalResidentsList1 = residentsList;
            EventHandler<ActionEvent> event = e -> {
                String selected = residentsComboBox.getValue();
                if (!(finalResidentsList1.getItems().contains(selected) || selected.equals("Choose Residents"))) finalResidentsList1.getItems().add(selected);
            };
            residentsComboBox.setOnAction(event);

            ListView<String> finalResidentsList = residentsList;
            residentsList.setOnMouseClicked(arg0 -> {
                int selected = finalResidentsList.getSelectionModel().getSelectedIndex();
                finalResidentsList.getItems().remove(selected);
            });

            // Gets the City's Specials
            Label citySpecials = new Label("Specials:");
            citySpecials.setTextFill(Color.WHITE.darker());
            grid.add(citySpecials, 3, 3);
            System.out.println("Specials Generated");

            HashSet<WorldElement> specialList = fileProcessor.getSelectedList("Special");
            ArrayList<String> specialNames = new ArrayList<>();
            specialNames.add("Choose Special");
            for (WorldElement s : specialList) {
                specialNames.add(s.getName());
            }

            ComboBox<String> specialsComboBox = new ComboBox<>(FXCollections.observableArrayList(specialNames));
            specialsComboBox.getSelectionModel().selectFirst();
            grid.add(specialsComboBox, 4, 3);

            specialsList = new ListView<>();
            ObservableList<String> specialsObservableList = FXCollections.observableArrayList ();
            specialsList.setItems(specialsObservableList);
            specialsList.setPrefWidth(200);
            specialsList.setPrefHeight(100);
            grid.add(specialsList, 4, 4, 4, 5);
            level++;

            ListView<String> finalSpecialsList = specialsList;
            EventHandler<ActionEvent> event2 = e -> {
                String selected = specialsComboBox.getValue();
                if (!(finalResidentsList.getItems().contains(selected) || selected.equals("Choose Special"))) finalSpecialsList.getItems().add(selected);
            };
            specialsComboBox.setOnAction(event2);

            ListView<String> finalSpecialsList1 = specialsList;
            specialsList.setOnMouseClicked(arg0 -> {
                int selected = finalSpecialsList1.getSelectionModel().getSelectedIndex();
                if (selected >= 0) finalSpecialsList1.getItems().remove(selected);
            });
        }

        // Gets a description of the Element
        Label NPCDescription = new Label("Description:");
        NPCDescription.setTextFill(Color.WHITE.darker());
        grid.add(NPCDescription, 0, level);
        System.out.print("Description Generated");

        TextArea descriptionTextField = new TextArea();
        descriptionTextField.setPrefWidth(200);
        descriptionTextField.setWrapText(true);
        grid.add(descriptionTextField, 1, level);
        System.out.println("Description Text generated");
        level++;

        // Asks if hidden description exists
        Label hiddenDescriptionQuestion = new Label("Hidden Description?:");
        hiddenDescriptionQuestion.setTextFill(Color.WHITE.darker());
        System.out.print("Description Generated");

        CheckBox descriptionExists = new CheckBox();
        if (!addType.equals("City")) {
            grid.add(hiddenDescriptionQuestion, 0, level);
            grid.add(descriptionExists, 1, level);
            System.out.println("Occupation Text generated");
        }
        level++;

        // Gets a hidden description of the element
        Label hiddenDescription = new Label("Hidden Description:");
        hiddenDescription.setTextFill(Color.WHITE.darker());
        System.out.print("Description Generated");

        TextArea hiddenDescriptionTextField = new TextArea();
        hiddenDescriptionTextField.setWrapText(true);
        System.out.println("Hidden Description Text generated");
        int tempLevel = level + 1;
        level += 2;

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, level);

        Button save = new Button("Save");
        HBox newWorldBtn = new HBox(20);
        newWorldBtn.setAlignment(Pos.BOTTOM_LEFT);
        newWorldBtn.getChildren().add(save);
        grid.add(newWorldBtn, 3, level);
        level++;

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, level);

        primaryStage.setScene(scene);
        primaryStage.show();

        cancel.setOnAction(e -> loadLast());

        descriptionExists.setOnAction(actionEvent -> {
            if (descriptionExists.isSelected()) {
                grid.add(hiddenDescription, 0, tempLevel);
                grid.add(hiddenDescriptionTextField, 1, tempLevel);
            }else{
                grid.getChildren().remove(hiddenDescription);
                grid.getChildren().remove(hiddenDescriptionTextField);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        TextField finalOccupationTextField = occupationTextField;
        ListView<String> finalResidentsList2 = residentsList;
        ListView<String> finalSpecialsList2 = specialsList;
        TextField finalPopulationTextField = populationTextField;
        TextArea finalTradeTextField = tradeTextField;
        TextField finalSongTextField = songTextField;
        save.setOnAction(e -> {
            // checks for the separation string
            if (nameTextField.getText().contains(" _-_ ") || descriptionTextField.getText().contains(" _-_ ") ||
                    (hiddenDescriptionTextField.getText().contains(" _-_ "))) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Please avoid using the string ' _-_ '!");
            }

            // checks for if an NPC by that name already exists
            boolean notMultiple = false;
            boolean multipleCheck = true;

            while (!notMultiple) {
                notMultiple = true;
                for (String key : processor.keySet()) {
                    WorldElement part = processor.get(key);
                    if (part.getClass() != Password.class) {
                        if (part.getName().equals(NPCname.getText())) {
                            actionTarget.setFill(Color.FIREBRICK);
                            actionTarget.setText("An element by this name already exists!");
                            multipleCheck = false;
                        }
                    }
                }
                if (!multipleCheck) {
                    notMultiple = false;
                }
            }
            if (multipleCheck) {
                boolean revealCoded = false;
                String revealCode = "";
                while(!revealCoded) {
                    revealCoded = true;
                    // adds the NPCs to the system
                    int one = new Random().nextInt(9);
                    int two = new Random().nextInt(9);
                    int three = new Random().nextInt(9);
                    int four = new Random().nextInt(9);
                    int five = new Random().nextInt(9);
                    revealCode = one + String.valueOf(two) + three + four + five;

                    // add check for if another part has the same revealCode here
                    for (String key : processor.keySet()) {
                        WorldElement part = processor.get(key);
                        if (part.getClass() != Password.class) {
                            if (part.getRevealCode().equals(revealCode)) {
                                revealCoded = false;
                            }
                        }
                    }
                }
                WorldElement newWorldElement;
                if(addType.equals("NPC")) {
                    if (descriptionExists.isSelected()) {
                        newWorldElement = new NPC(nameTextField.getText(), finalOccupationTextField.getText(), descriptionTextField.getText(),
                                hiddenDescriptionTextField.getText(), false, revealCode, "");
                    } else {
                        newWorldElement = new NPC(nameTextField.getText(), finalOccupationTextField.getText(), descriptionTextField.getText(),
                                false, revealCode, "");
                    }
                }
                else if (addType.equals("City")){
                    assert false;
                    List<String> residents = new ArrayList<>(finalResidentsList2.getItems());
                    assert false;
                    List<String> specials = new ArrayList<>(finalSpecialsList2.getItems());
                    newWorldElement = new City(nameTextField.getText(), finalPopulationTextField.getText(), finalTradeTextField.getText(),
                            residents, finalSongTextField.getText(), descriptionTextField.getText(), specials, false, revealCode, "");

                }
                else {
                    if (descriptionExists.isSelected()) {
                        newWorldElement = new Special(nameTextField.getText(), descriptionTextField.getText(),
                                hiddenDescriptionTextField.getText(), false, revealCode, "");
                    } else {
                        newWorldElement = new Special(nameTextField.getText(), descriptionTextField.getText(),
                                false, revealCode, "");
                    }
                }
                processor.put(newWorldElement.getName(), newWorldElement);
                fileProcessor.addComponent(newWorldElement);
                fileProcessor.writeFile();
            }
            HashSet<WorldElement> resultList = fileProcessor.getSelectedList(addType);
            ResultsPage resultsPage = new ResultsPage(resultList, true, primaryStage, fileProcessor, previous, addType);
            resultsPage.loadPage();
        });
    }

    @Override
    public void reloadPage() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void loadLast() {
        previous.reloadPage();
    }

    @Override
    public Page getPrevious() {
        return previous;
    }
}
