package JFXDisplays;

import Components.WorldElement;
import Work_Classes.FileProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HomePage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final FileProcessor fileProcessor;
    private final boolean admin;
    private final HashMap<String,WorldElement> processor;

    public HomePage(HashMap<String,WorldElement> processor, boolean admin, Stage stage, FileProcessor fileProcessor, Page previous){

        this.primaryStage = stage;
        this.previous = previous;
        this.fileProcessor = fileProcessor;
        this.admin = admin;
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

        String name =  fileProcessor.getName();
        name = name.replaceAll(".bck", "");

        HBox title = new HBox(40);
        title.setAlignment(Pos.CENTER);
        Text sceneTitle = new Text("Welcome to " + name + "!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        sceneTitle.setFill(Color.WHITE.darker());
        title.getChildren().add(sceneTitle);
        grid.add(title, 0, 0);

        Button NPCButton = new Button("NPCs");
        HBox generalBtn = new HBox(40);
        generalBtn.getChildren().add(NPCButton);

        Button CityButton = new Button("Cities");
        generalBtn.getChildren().add(CityButton);

        Button SpecialButton = new Button("Specials");
        generalBtn.getChildren().add(SpecialButton);
        generalBtn.setAlignment(Pos.CENTER);
        grid.add(generalBtn, 0, 3);

        Button exit = new Button("Exit Program");
        HBox exitBtn = new HBox(80);
        exitBtn.getChildren().add(exit);

        Button close = new Button("Close World");
        exitBtn.getChildren().add(close);
        exitBtn.setAlignment(Pos.CENTER);
        grid.add(exitBtn, 0, 6);

        NPCButton.setOnAction(actionEvent -> {
            HashSet<WorldElement> NPCList = fileProcessor.getSelectedList("NPC");
            ResultsPage resultsPage = new ResultsPage(NPCList, admin, primaryStage, fileProcessor, this, "NPC");
            resultsPage.loadPage();
        });

        CityButton.setOnAction(actionEvent -> {
            HashSet<WorldElement> cityList = fileProcessor.getSelectedList("City");
            ResultsPage resultsPage = new ResultsPage(cityList, admin, primaryStage, fileProcessor, this, "City");
            resultsPage.loadPage();
        });

        SpecialButton.setOnAction(actionEvent -> {
            HashSet<WorldElement> specialList = fileProcessor.getSelectedList("Special");
            ResultsPage resultsPage = new ResultsPage(specialList, admin, primaryStage, fileProcessor, this, "Special");
            resultsPage.loadPage();
        });

        exit.setOnAction(actionEvent -> {
            fileProcessor.writeFile();
            System.out.println("Thanks! come again");
            primaryStage.close();
        });

        close.setOnAction(actionEvent -> {
            fileProcessor.writeFile();
            System.out.println("Thanks! come again");
            previous.loadLast();
        });

        if (admin) {
            Button AddNPCButton = new Button("Add NPC");
            HBox AdminBtn = new HBox(40);
            AdminBtn.getChildren().add(AddNPCButton);

            Button AddCityButton = new Button("Add City");
            AdminBtn.getChildren().add(AddCityButton);

            Button AddSpecialButton = new Button("Add Special");
            AdminBtn.getChildren().add(AddSpecialButton);
            AdminBtn.setAlignment(Pos.CENTER);
            grid.add(AdminBtn, 0, 4);

            AddNPCButton.setOnAction(actionEvent -> {
                AddPage addNPC = new AddPage(primaryStage, fileProcessor, this, processor, "NPC");
                addNPC.loadPage();
            });

            AddCityButton.setOnAction(actionEvent -> {
                AddPage addCity = new AddPage(primaryStage, fileProcessor, this, processor, "City");
                addCity.loadPage();
            });

            AddSpecialButton.setOnAction(actionEvent -> {
                AddPage addSpecial = new AddPage(primaryStage, fileProcessor, this, processor, "Special");
                addSpecial.loadPage();
            });
        }

        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.print("Welcome!");

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
