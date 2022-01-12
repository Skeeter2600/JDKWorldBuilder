package JFXDisplays;

import Components.City;
import Components.NPC;
import Components.Special;
import Components.WorldElement;
import Work_Classes.FileProcessor;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CityPage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final City city;
    private final boolean admin;
    private final FileProcessor fileProcessor;

    public CityPage(Stage primaryStage, Page previous, City city, FileProcessor fileProcessor, boolean admin){
        this.primaryStage = primaryStage;
        this.previous = previous;
        this.city = city;
        this.fileProcessor = fileProcessor;
        this.admin = admin;

        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        ScrollPane sp = new ScrollPane();
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setContent(grid);

        scene = new Scene(sp, 1152, 648);

        primaryStage.setScene(scene);

    }

    @Override
    public void loadPage() {

        // header of the page
        Text actionText = new Text();
        actionText.setFill(Color.WHITE.darker());
        grid.add(actionText, 0, 4);
        StackPane root = new StackPane();
        TextArea sceneTitle = new TextArea(city.getName());
        if (!admin) sceneTitle.setEditable(false);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        root.getChildren().add(sceneTitle);
        grid.add(root, 0, 0);

        BorderPane windowDisplay = new BorderPane();  // the full window setup

        GridPane info = new GridPane();
        info.setVgap(20);
        info.setAlignment(Pos.CENTER);

        Text populationHeader = new Text("Population:");
        populationHeader.setFill(Color.WHITE.darker());
        TextArea population = new TextArea(city.getPopulation());
        population.setMaxSize(300, 20);
        if (!admin) population.setEditable(false);

        info.add(populationHeader, 0, 1);
        info.add(population, 1, 1);

        Text tradeHeader = new Text("Trades:");
        tradeHeader.setFill(Color.WHITE.darker());
        TextArea trades = new TextArea(city.getTrade());
        trades.setMaxSize(500, 60);
        if (!admin) trades.setEditable(false);

        info.add(tradeHeader, 0, 2);
        info.add(trades, 1, 2);

        Text descriptionHeader = new Text("Description:");
        descriptionHeader.setFill(Color.WHITE.darker());
        TextArea description = new TextArea(city.getDescription());
        if (!admin) description.setEditable(false);

        info.add(descriptionHeader, 0, 3);
        info.add(description, 1, 3);

        Hyperlink songHyper = new Hyperlink("Song");
        songHyper.setMinWidth(25);
        TextArea songLink = new TextArea(city.getSong());
        songLink.setMaxSize(500, 20);
        songLink.setEditable(true);

        songHyper.setOnAction(actionEvent -> {
            openBrowser(songLink.getText());
            songHyper.setVisited(false);
        });

        info.add(songHyper, 0, 4);
        if (admin) info.add(songLink, 1, 4);

        CheckBox revealed = new CheckBox();
        Text revealCode = new Text(String.valueOf(city.getRevealCode()));
        revealCode.setFill(Color.WHITE.darker());

        if(admin) {
            Text revealedHeader = new Text("Revealed?");
            revealedHeader.setFill(Color.WHITE.darker());
            revealed.setSelected(city.getRevealed());

            info.add(revealedHeader, 0, 8);
            info.add(revealed, 1, 8);

            Text revealCodeHeader = new Text("Reveal Code:");
            revealCodeHeader.setFill(Color.WHITE.darker());
            info.add(revealCodeHeader, 0, 9);
            info.add(revealCode, 1, 9);
        }

        // add the NPCs of the City to a table

        TableView<NPC> NPCTable = new TableView<>();
        NPCTable.setMaxHeight(120);
        NPCTable.setMaxWidth(200);

        // the column for the name of the NPC

        TableColumn<NPC, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> {
            NPC name = cellData.getValue();
            return new ReadOnlyStringWrapper(name.getName());
        });
        nameCol.setStyle("-fx-alignment: CENTER;");
        nameCol.setMinWidth(100);
        nameCol.setMaxWidth(100);
        NPCTable.getColumns().add(nameCol);

        // the column for the Occupation of the NPC

        TableColumn<NPC, String> occupationCol = new TableColumn<>("Occupation");
        occupationCol.setCellValueFactory(cellData -> {
            NPC name = cellData.getValue();
            return new ReadOnlyStringWrapper(name.getOccupation());
        });
        occupationCol.setStyle("-fx-alignment: CENTER;");
        occupationCol.setMinWidth(100);
        occupationCol.setMaxWidth(100);
        NPCTable.getColumns().add(occupationCol);

        // add the NPCs to the table

        ObservableList<NPC> NPCList = FXCollections.observableArrayList();
        List<String> NPCStringList = city.getResidents();
        for (String name : NPCStringList){
            NPCList.add((NPC) fileProcessor.getWorldElement(name));
        }
        NPCTable.setItems(NPCList);

        // add NPC table to page

        HBox NPCListBox = new HBox();
        NPCListBox.setSpacing(30);

        Text NPCTableHeader = new Text("NPCs:");
        NPCTableHeader.setFill(Color.WHITE.darker());
        NPCListBox.getChildren().add(NPCTable);

        // add the editable list if admin

        if (admin){

            HashSet<WorldElement> allNPCList = fileProcessor.getSelectedList("NPC");
            ArrayList<String> NPCNames = new ArrayList<>();
            NPCNames.add("Choose Residents");
            for (WorldElement n : allNPCList) {
                NPCNames.add(n.getName());
            }
            ComboBox<String> residentsComboBox = new ComboBox<>(FXCollections.observableArrayList(NPCNames));
            residentsComboBox.getSelectionModel().selectFirst();

            // adding and removing from list here
            residentsComboBox.setOnAction(mouseEvent -> {
                String selected = residentsComboBox.getValue();
                boolean addCheck = true;
                for (NPC NPCChecker : NPCList){
                    if (NPCChecker.getName().equals(selected)) {
                        addCheck = false;
                        break;
                    }
                }
                if (!(!addCheck || selected.equals("Choose Residents"))) {
                    NPCList.add((NPC) fileProcessor.getWorldElement(selected));
                    NPCTable.setItems(NPCList);
                    NPCTable.refresh();
                }
            });

            NPCTable.setOnMouseClicked(arg0 -> {
                int selected = NPCTable.getSelectionModel().getSelectedIndex();
                NPCTable.getItems().remove(selected);
            });

            NPCListBox.getChildren().add(residentsComboBox);

        }

        // load the selected element if not admin

        else{
            NPCTable.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2){
                    int selected = NPCTable.getSelectionModel().getSelectedIndex();
                    NPCPage details = new NPCPage(primaryStage, this, NPCTable.getItems().get(selected), fileProcessor, false);
                    details.loadPage();
                }
            });
        }

        info.add(NPCTableHeader, 0, 5);
        info.add(NPCListBox, 1, 5);

        // add the Specials of the City to a table

        TableView<Special> specialTable = new TableView<>();
        specialTable.setMaxHeight(120);
        specialTable.setMaxWidth(200);

        // add the specials to the table

        ObservableList<Special> specialList = FXCollections.observableArrayList();
        List<String> specialStringList = city.getSpecials();
        for (String name : specialStringList){ specialList.add((Special) fileProcessor.getWorldElement(name)); }
        specialTable.setItems(specialList);

        // the column for the name of the element

        TableColumn<Special, String> specialNameCol = new TableColumn<>("Name");
        specialNameCol.setCellValueFactory(cellData -> {
            Special name = cellData.getValue();
            return new ReadOnlyStringWrapper(name.getName());
        });
        specialNameCol.setStyle("-fx-alignment: CENTER;");
        specialNameCol.setMinWidth(200);
        specialNameCol.setMaxWidth(200);
        specialTable.getColumns().add(specialNameCol);

        // add the Special table to the page

        HBox specialsListBox = new HBox();
        specialsListBox.setSpacing(30);

        Text specialTableHeader = new Text("Specials:");
        specialTableHeader.setFill(Color.WHITE.darker());
        specialsListBox.getChildren().add(specialTable);

        // add the editable list if admin

        if (admin){

            HashSet<WorldElement> allSpecialList = fileProcessor.getSelectedList("Special");
            ArrayList<String> specialNames = new ArrayList<>();
            specialNames.add("Choose Specials");
            for (WorldElement n : allSpecialList) {
                specialNames.add(n.getName());
            }
            ComboBox<String> specialsComboBox = new ComboBox<>(FXCollections.observableArrayList(specialNames));
            specialsComboBox.getSelectionModel().selectFirst();

            // adding and removing from list here
            specialsComboBox.setOnAction(mouseEvent -> {
                String selected = specialsComboBox.getValue();
                boolean addCheck = true;
                for (Special specialChecker : specialList){
                    if (specialChecker.getName().equals(selected)) {
                        addCheck = false;
                        break;
                    }
                }
                if (!(!addCheck || selected.equals("Choose Specials"))) {
                    specialList.add((Special) fileProcessor.getWorldElement(selected));
                    specialTable.setItems(specialList);
                    specialTable.refresh();
                }
            });

            specialTable.setOnMouseClicked(arg0 -> {
                int selected = specialTable.getSelectionModel().getSelectedIndex();
                specialTable.getItems().remove(selected);
            });

            specialsListBox.getChildren().add(specialsComboBox);

        }

        else{
            specialTable.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2){
                    int selected = specialTable.getSelectionModel().getSelectedIndex();
                    SpecialPage details = new SpecialPage(primaryStage, this, specialTable.getItems().get(selected), fileProcessor, false);
                    details.loadPage();
                }
            });
        }

        // add specials to the table

        info.add(specialTableHeader, 0, 6);
        info.add(specialsListBox, 1, 6);

        Text notesHeader = new Text("Notes:");
        notesHeader.setFill(Color.WHITE.darker());
        TextArea notes = new TextArea(city.getNotes());
        info.add(notesHeader, 0, 7);
        info.add(notes, 1, 7);

        windowDisplay.setTop(info);
        BorderPane.setMargin(info, new Insets(10));

        HBox buttonBox = new HBox(100);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        Button back = new Button("Back");
        buttonBox.getChildren().add(back);

        Button save = new Button("Save");
        Button delete = new Button("Delete");

        if (admin) {
            buttonBox.getChildren().add(save);
            buttonBox.getChildren().add(delete);
        }

        windowDisplay.setCenter(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(10));

        windowDisplay.setPadding(new Insets(10, 10, 10, 10));

        grid.add(windowDisplay, 0, 1);

        back.setOnAction(actionEvent -> {
            if (sceneTitle.getText().contains(" _-_ ") || description.getText().contains(" _-_ ") ||
                    (notes.getText().contains(" _-_ "))) {
                actionText.setFill(Color.FIREBRICK);
                actionText.setText("Please avoid using the string ' _-_ '!");
            }
            else {
                city.setNotes(notes.getText());
                loadLast();
            }
        });

        save.setOnAction(actionEvent -> {
            if (sceneTitle.getText().contains(" _-_ ") || description.getText().contains(" _-_ ") ||
                    (notes.getText().contains(" _-_ "))) {
                actionText.setFill(Color.FIREBRICK);
                actionText.setText("Please avoid using the string ' _-_ '!");
            }
            else {
                city.setName(sceneTitle.getText());
                city.setDescription(description.getText());
                city.setSong(songLink.getText());
                city.setNotes(notes.getText());

                List<String> residentList = new LinkedList<>();
                for (NPC resident : NPCList){
                    residentList.add(resident.getName());
                }

                city.setResidents(residentList);

                List<String> specialSaveList = new LinkedList<>();
                for (Special special : specialList){
                    specialSaveList.add(special.getName());
                }

                city.setSpecials(specialSaveList);

                actionText.setFill(Color.WHITE.darker());
                actionText.setText(sceneTitle.getText() + " has been updated!");
            }
        });

        delete.setOnAction( ae -> {
            RemovePage deletePage = new RemovePage(primaryStage, this, city, fileProcessor);
            deletePage.loadPage();
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void reloadPage() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void loadLast(){
        previous.reloadPage();
    }

    @Override
    public Page getPrevious() {
        return previous;
    }

    private void openBrowser(String urlLink) {
        Desktop desktop = Desktop.getDesktop();
        try {
            //specify the protocol along with the URL
            URI oURL = new URI(urlLink);
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}
