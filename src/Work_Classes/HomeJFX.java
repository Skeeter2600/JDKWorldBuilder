package Work_Classes;

//import JFXDisplays.NPCJFX;
import Pages.City;
import Pages.NPC;
import Pages.Special;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class HomeJFX {

    private final HashSet<Object> processor;
    private final boolean admin;
    private final Scanner input;
    private final Stage primaryStage;
    private GridPane grid;
    private final FileProcessor fileProcessor;

    /**
     * This will create a new instance of a Command Processor
     *
     * @param processor the set of objects in the world
     * @param admin     if admin privileges are enabled
     */
    public HomeJFX(HashSet<Object> processor, boolean admin, Stage primaryStage, FileProcessor fileProcessor) {
        this.processor = processor;
        this.admin = admin;
        this.fileProcessor = fileProcessor;
        input = new Scanner(System.in);
        this.primaryStage = primaryStage;
        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    /**
     * This initiates and runs the system while taking commands
     */
    public void commandProgram() {

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);

        Text sceneTitle = new Text("Welcome to the World!");
        sceneTitle.setFont(Font.font("DroidSansMono.ttf", FontWeight.NORMAL, 30));
        sceneTitle.setFill(Color.WHITE.darker());
        grid.add(sceneTitle, 0, 0, 3, 1);

        Button NPCButton = new Button("NPCs");
        HBox GeneralBtn = new HBox(40);
        GeneralBtn.getChildren().add(NPCButton);

        Button CityButton = new Button("Cities");
        GeneralBtn.getChildren().add(CityButton);

        Button SpecialButton = new Button("Specials");
        GeneralBtn.getChildren().add(SpecialButton);
        GeneralBtn.setAlignment(Pos.CENTER);
        grid.add(GeneralBtn, 2, 3);

        Button exit = new Button("Exit Program");
        HBox exitBtn = new HBox(80);
        exitBtn.getChildren().add(exit);

        Button close = new Button("Close World");
        exitBtn.getChildren().add(close);
        exitBtn.setAlignment(Pos.CENTER);
        grid.add(exitBtn, 2, 6);

        NPCButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NPCs(admin);
            }
        });

        CityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	cities(admin); }
        });

        SpecialButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                specials(admin);
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                AltMain.restart(primaryStage);
            }
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
            grid.add(AdminBtn, 2, 4);

            AddNPCButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    addNPC();
                }
            });

            AddCityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) { addCity(); }
            });

            AddSpecialButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    addSpecial();
                }
            });
        }

        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        boolean quit = false;
        System.out.print("Welcome! ");
    }


    /**
     * This will add an NPC to the system
     *
     * @return true if completed, false if a problem occurred
     */
    public void addNPC() {

        // wipe grid
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Get the name of the NPC with text box
        Label NPCname = new Label("Name:");
        NPCname.setFont(Font.font("DroidSansMono.ttf"));
        NPCname.setTextFill(Color.WHITE.darker());
        grid.add(NPCname, 0, 0);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, 0);
        System.out.println("Name Text generated");

        // Gets the NPC's occupation
        Label NPCOccupation = new Label("Occupation:");
        NPCOccupation.setFont(Font.font("DroidSansMono.ttf"));
        NPCOccupation.setTextFill(Color.WHITE.darker());
        grid.add(NPCOccupation, 0, 1);
        System.out.print("Occupation generated");

        TextField occupationTextField = new TextField();
        occupationTextField.setPrefWidth(200);
        grid.add(occupationTextField, 1, 1);
        System.out.println("Occupation Text generated");

        // Gets a description of the NPC
        Label NPCDescription = new Label("Description:");
        NPCDescription.setFont(Font.font("DroidSansMono.ttf"));
        NPCDescription.setTextFill(Color.WHITE.darker());
        grid.add(NPCDescription, 0, 2);
        System.out.print("Description Generated");

        TextArea descriptionTextField = new TextArea();
        descriptionTextField.setPrefWidth(200);
        grid.add(descriptionTextField, 1, 2);
        System.out.println("Occupation Text generated");

        // Asks if hidden description exists
        Label HiddenDescription = new Label("Hidden Description?:");
        HiddenDescription.setFont(Font.font("DroidSansMono.ttf"));
        HiddenDescription.setTextFill(Color.WHITE.darker());
        grid.add(HiddenDescription, 0, 3);
        System.out.print("Description Generated");

        CheckBox DescriptionExists = new CheckBox();
        grid.add(DescriptionExists, 1, 3);
        System.out.println("Occupation Text generated");
        boolean hidden = false;

        // Gets a hidden description of the NPC
        Label NPCHiddenDescription = new Label("Hidden Description:");
        NPCHiddenDescription.setFont(Font.font("DroidSansMono.ttf"));
        NPCHiddenDescription.setTextFill(Color.WHITE.darker());
        grid.add(NPCHiddenDescription, 0, 4);
        System.out.print("Description Generated");

        TextArea hiddenDescriptionTextField = new TextArea();
        grid.add(hiddenDescriptionTextField, 1, 4);
        System.out.println("Occupation Text generated");

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 5);

        Button save = new Button("Save");
        HBox newWorldBtn = new HBox(20);
        newWorldBtn.setAlignment(Pos.BOTTOM_LEFT);
        newWorldBtn.getChildren().add(save);
        grid.add(newWorldBtn, 3, 5);

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        final String[] outcome = {"false"};

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                commandProgram();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // checks for the separation string
                if (nameTextField.getText().contains(" _-_ ") || descriptionTextField.getText().contains(" _-_ ") || occupationTextField.getText().contains(" _-_ ") ||
                        (hiddenDescriptionTextField.getText().contains(" _-_ "))) {
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Please avoid using the string ' _-_ '!");
                }

                // checks for if an NPC by that name already exists
                boolean notMultiple = false;
                boolean multipleCheck = true;
                NPC comparator = new NPC("Paul Blart", "comparer", "I check for class", true, "12345", "");

                while (!notMultiple) {
                    notMultiple = true;
                    for (Object o : processor) {
                        if (o.getClass() == comparator.getClass()) {
                            NPC check = (NPC) o;
                            if (check.getName().equals(NPCname.getText())) {
                                actionTarget.setFill(Color.FIREBRICK);
                                actionTarget.setText("An NPC by this name already exists!");
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

                        NPC tempNPC = new NPC("Paul Blart", "checker", "fat", true,"12345","");
                        // add check for if another part has the same revealCode here
                        for (Object part : processor) {
                            if (part.getClass() == tempNPC.getClass()) {
                                if (tempNPC.getRevealCode().equals(revealCode)) {
                                    revealCoded = false;
                                }
                            }
                        }
                    }
                    NPC newNPC;
                    if (DescriptionExists.isSelected()) {
                        newNPC = new NPC(nameTextField.getText(), occupationTextField.getText(), descriptionTextField.getText(),
                                hiddenDescriptionTextField.getText(), false, revealCode, "");
                    } else {
                        newNPC = new NPC(nameTextField.getText(), occupationTextField.getText(), descriptionTextField.getText(),
                                false, revealCode, "");
                        processor.add(newNPC);
                    }
                    processor.add(newNPC);
                    fileProcessor.addComponent(newNPC);
                    fileProcessor.writeFile();
                }
                NPCs(admin);
            }
        });
    }


    /**
     * This will add a city to the system using JFX
     */
    public void addCity(){
        // wipe grid
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Get the name of the NPC with text box
        Label cityName = new Label("Name:");
        cityName.setFont(Font.font("DroidSansMono.ttf"));
        cityName.setTextFill(Color.WHITE.darker());
        grid.add(cityName, 0, 0);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, 0);
        System.out.println("Name Text generated");

        // Gets the City's population
        Label CityPopulation = new Label("Population:");
        CityPopulation.setFont(Font.font("DroidSansMono.ttf"));
        CityPopulation.setTextFill(Color.WHITE.darker());
        grid.add(CityPopulation, 0, 1);
        System.out.print("Population generated");

        TextField populationTextField = new TextField();
        populationTextField.setPrefWidth(50);
        grid.add(populationTextField, 1, 1);
        System.out.println("Population Text generated");

        // Gets the City's aesthetic
        Label cityAesthetic = new Label("Aesthetic:");
        cityAesthetic.setFont(Font.font("DroidSansMono.ttf"));
        cityAesthetic.setTextFill(Color.WHITE.darker());
        grid.add(cityAesthetic, 0, 2);
        System.out.print("Aesthetic generated");

        TextArea aestheticTextField = new TextArea();
        aestheticTextField.setPrefWidth(200);
        grid.add(aestheticTextField, 1, 2);
        System.out.println("Aesthetic Text generated");

        // Gets the City's song
        Label citySong = new Label("Song:");
        citySong.setFont(Font.font("DroidSansMono.ttf"));
        citySong.setTextFill(Color.WHITE.darker());
        grid.add(citySong, 0, 3);
        System.out.print("Description Generated");

        TextField songTextField = new TextField();
        songTextField.setPrefWidth(200);
        grid.add(songTextField, 1, 3);
        System.out.println("Song Text generated");


        // Gets the City's trades
        Label cityTrade = new Label("Trades:");
        cityTrade.setFont(Font.font("DroidSansMono.ttf"));
        cityTrade.setTextFill(Color.WHITE.darker());
        grid.add(cityTrade, 0, 4);
        System.out.print("Description Generated");

        TextArea tradeTextField = new TextArea();
        tradeTextField.setPrefWidth(200);
        grid.add(tradeTextField, 1, 4, 1, 5);
        System.out.println("Trade Text generated");

        // Gets the City's Residents
        Label cityResidents = new Label("Residents:");
        cityResidents.setFont(Font.font("DroidSansMono.ttf"));
        cityResidents.setTextFill(Color.WHITE.darker());
        grid.add(cityResidents, 3, 0);
        System.out.print("Residents Generated");

        ArrayList<NPC> NPCList = fileProcessor.getNPCList();
        ArrayList<String> NPCNames = new ArrayList<String>();
        NPCNames.add("Choose Residents");
        for (NPC n : NPCList) {
        	NPCNames.add(n.getName());
        }
        ComboBox<String> residentsComboBox = new ComboBox<String>(FXCollections.observableArrayList(NPCNames));
        residentsComboBox.getSelectionModel().selectFirst();
        grid.add(residentsComboBox, 4, 0);

        ListView<String> residentsList = new ListView<String>();
        ObservableList<String> residentsObservableList = FXCollections.observableArrayList ();
        residentsList.setItems(residentsObservableList);
        residentsList.setPrefWidth(200);
        residentsList.setPrefHeight(100);
        grid.add(residentsList, 4, 1, 4, 2);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		String selected = residentsComboBox.getValue();
        		residentsList.getItems().add(selected);
        	}
        };
        residentsComboBox.setOnAction(event);

        residentsList.setOnMouseClicked(new EventHandler<MouseEvent>() {

        	@Override
        	public void handle(MouseEvent arg0) {
            	int selected = residentsList.getSelectionModel().getSelectedIndex();
            	residentsList.getItems().remove(selected);
        	}
        });

        // Gets the City's Specials
        Label citySpecials = new Label("Specials:");
        citySpecials.setFont(Font.font("DroidSansMono.ttf"));
        citySpecials.setTextFill(Color.WHITE.darker());
        grid.add(citySpecials, 3, 3);
        System.out.println("Specials Generated");

        ArrayList<Special> specialList = fileProcessor.getSpecialList();
        ArrayList<String> specialNames = new ArrayList<String>();
        specialNames.add("Choose Residents");
        for (Special s : specialList) {
        	specialNames.add(s.getName());
        }

        ComboBox<String> specialsComboBox = new ComboBox<String>(FXCollections.observableArrayList(specialNames));
        specialsComboBox.getSelectionModel().selectFirst();
        grid.add(specialsComboBox, 4, 3);

        ListView<String> specialsList = new ListView<String>();
        ObservableList<String> specialsObservableList = FXCollections.observableArrayList ();
        specialsList.setItems(specialsObservableList);
        specialsList.setPrefWidth(200);
        specialsList.setPrefHeight(100);
        grid.add(specialsList, 4, 4, 4, 5);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		String selected = (String) specialsComboBox.getValue();
        		specialsList.getItems().add(selected);
        	}
        };
        specialsComboBox.setOnAction(event2);

        specialsList.setOnMouseClicked(new EventHandler<MouseEvent>() {

        	@Override
        	public void handle(MouseEvent arg0) {
            	int selected = specialsList.getSelectionModel().getSelectedIndex();
            	specialsList.getItems().remove(selected);
        	}
        });

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 9);

        Button save = new Button("Save");
        HBox newWorldBtn = new HBox(20);
        newWorldBtn.setAlignment(Pos.BOTTOM_LEFT);
        newWorldBtn.getChildren().add(save);
        grid.add(newWorldBtn, 8, 9);

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        final String[] outcome = {"false"};

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                commandProgram();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // checks for the separation string
                if (nameTextField.getText().contains(" _-_ ") || populationTextField.getText().contains(" _-_ ") || aestheticTextField.getText().contains(" _-_ ") ||
                        songTextField.getText().contains(" _-_ ") || tradeTextField.getText().contains(" _-_ ") || specialsList.getItems().contains(" _-_ ") ||
                        residentsList.getItems().contains(" _-_ ")) {
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Please avoid using the string ' _-_ '!");
                }

                // checks for if an NPC by that name already exists
                boolean notMultiple = false;
                boolean multipleCheck = true;
                NPC comparator = new NPC("Paul Blart", "comparer", "I check for class", true, "12345", "");

                while (!notMultiple) {
                    notMultiple = true;
                    for (Object o : processor) {
                        if (o.getClass() == comparator.getClass()) {
                            NPC check = (NPC) o;
                            if (check.getName().equals(nameTextField.getText())) {
                                actionTarget.setFill(Color.FIREBRICK);
                                actionTarget.setText("An NPC by this name already exists!");
                                multipleCheck = false;
                            }
                        }
                    }
                    if (!multipleCheck) {
                        notMultiple = false;
                    }
                }
                List<String> residents = new ArrayList<>(residentsList.getItems());

                List<String> specials = new ArrayList<>(specialsList.getItems());

                if (multipleCheck) {
                    boolean revealCoded = false;
                    String revealCode = "";
                    while(!revealCoded) {
                        revealCoded = true;
                        // generates reveal code
                        int one = new Random().nextInt(9);
                        int two = new Random().nextInt(9);
                        int three = new Random().nextInt(9);
                        int four = new Random().nextInt(9);
                        int five = new Random().nextInt(9);
                        revealCode = one + String.valueOf(two) + three + four + five;
                        City tempCity = new City("testville", "1.3 mil.", null, null, "never gonna give you up",
                                "ballsy", null, false, "12345", "");
                        // add check for if another part has the same reveal code here
                        for (Object part : processor) {
                            if (part.getClass() == tempCity.getClass()) {
                                if (tempCity.getRevealCode().equals(revealCode)) {
                                    revealCoded = false;
                                }
                            }
                        }
                    }
                    // adds the City to the system
                    City newCity = new City(nameTextField.getText(), populationTextField.getText(), tradeTextField.getText(),
                            residents, songTextField.getText(), aestheticTextField.getText(), specials, false, revealCode, "");
                    processor.add(newCity);
                    fileProcessor.addComponent(newCity);
                    fileProcessor.writeFile();
                    }
                cities(admin);
                }
            }
        );
    }

    /**
     * This will add a special to the system
     * experimental
     *
     */
    public void addSpecial() {

        // wipe grid
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Get the name of the Special with text box
        Label specialName = new Label("Name:");
        specialName.setFont(Font.font("DroidSansMono.ttf"));
        specialName.setTextFill(Color.WHITE.darker());
        grid.add(specialName, 0, 0);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, 0);
        System.out.println("Name Text generated");

        // Gets a description of the Special
        Label specialDescription = new Label("Description:");
        specialDescription.setFont(Font.font("DroidSansMono.ttf"));
        specialDescription.setTextFill(Color.WHITE.darker());
        grid.add(specialDescription, 0, 2);
        System.out.print("Description Generated");

        TextArea descriptionTextField = new TextArea();
        descriptionTextField.setPrefWidth(200);
        grid.add(descriptionTextField, 1, 2);
        System.out.println("Occupation Text generated");

        // Asks if hidden description exists
        Label hiddenDescription = new Label("Hidden Description?:");
        hiddenDescription.setFont(Font.font("DroidSansMono.ttf"));
        hiddenDescription.setTextFill(Color.WHITE.darker());
        grid.add(hiddenDescription, 0, 3);
        System.out.print("Description Generated");

        CheckBox DescriptionExists = new CheckBox();
        grid.add(DescriptionExists, 1, 3);
        System.out.println("Occupation Text generated");
        boolean hidden = false;

        // Gets a hidden description of the Special
        Label specialHiddenDescription = new Label("Hidden Description:");
        specialHiddenDescription.setFont(Font.font("DroidSansMono.ttf"));
        specialHiddenDescription.setTextFill(Color.WHITE.darker());
        grid.add(specialHiddenDescription, 0, 4);
        System.out.print("Description Generated");

        TextArea hiddenDescriptionTextField = new TextArea();
        grid.add(hiddenDescriptionTextField, 1, 4);
        System.out.println("Occupation Text generated");

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 5);

        Button save = new Button("Save");
        HBox newWorldBtn = new HBox(20);
        newWorldBtn.setAlignment(Pos.BOTTOM_LEFT);
        newWorldBtn.getChildren().add(save);
        grid.add(newWorldBtn, 3, 5);

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        final String[] outcome = {"false"};

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                commandProgram();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // checks for the separation string
                if (nameTextField.getText().contains(" _-_ ") || descriptionTextField.getText().contains(" _-_ ") ||
                        (hiddenDescriptionTextField.getText().contains(" _-_ "))) {
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Please avoid using the string ' _-_ '!");
                }

                // checks for if an Special by that name already exists
                boolean notMultiple = false;
                boolean multipleCheck = true;
                Special comparator = new Special("Paul Blart", "I check for class", true, "12345", "");

                while (!notMultiple) {
                    notMultiple = true;
                    for (Object o : processor) {
                        if (o.getClass() == comparator.getClass()) {
                            Special check = (Special) o;
                            if (check.getName().equals(specialName.getText())) {
                                actionTarget.setFill(Color.FIREBRICK);
                                actionTarget.setText("A special by this name already exists!");
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
                    while (!revealCoded) {
                        revealCoded = true;
                        // adds the specials to the system
                        int one = new Random().nextInt(9);
                        int two = new Random().nextInt(9);
                        int three = new Random().nextInt(9);
                        int four = new Random().nextInt(9);
                        int five = new Random().nextInt(9);
                        revealCode = one + String.valueOf(two) + three + four + five;

                        // add check for if another part has the same revealCode here
                        Special tempSpecial = new Special("Capitol Building", "tester", true, "12345", "");
                        for (Object part : processor) {
                            if (part.getClass() == tempSpecial.getClass()) {
                                if (tempSpecial.getRevealCode().equals(revealCode)) {
                                    revealCoded = false;
                                }
                            }
                        }
                    }
                    Special newSpecial;
                    if (DescriptionExists.isSelected()) {
                        newSpecial = new Special(nameTextField.getText(), descriptionTextField.getText(),
                                hiddenDescriptionTextField.getText(), false, revealCode, "");
                    } else {
                        newSpecial = new Special(nameTextField.getText(), descriptionTextField.getText(),
                                false, revealCode, "");
                    }
                    processor.add(newSpecial);
                    fileProcessor.addComponent(newSpecial);
                    fileProcessor.writeFile();
                }
                specials(admin);
            }
        });
    }

    /**
     * This will show available NPCs in the system administrator permitting
     *
     */
    public void NPCs(boolean admin) {
    	System.out.println("ran NPCs");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Field labels for list of NPCs
        Label NPCName = new Label("Name");
        NPCName.setFont(Font.font("DroidSansMono.ttf"));
        NPCName.setTextFill(Color.WHITE.darker());
        grid.add(NPCName, 0, 0);
        System.out.println("Name box generated");

        Label NPCOccupation = new Label("Occupation");
        NPCOccupation.setFont(Font.font("DroidSansMono.ttf"));
        NPCOccupation.setTextFill(Color.WHITE.darker());
        grid.add(NPCOccupation, 20, 0);
        System.out.println("Occupation box generated");

        if (admin) {
            Label revealed = new Label("Revealed?");
            revealed.setFont(Font.font("DroidSansMono.ttf"));
            revealed.setTextFill(Color.WHITE.darker());
            grid.add(revealed, 24, 0);
            System.out.println("Revealed  box generated");

            //Edit buttons for admin will be below this space
            Label editSpace = new Label("");
            editSpace.setFont(Font.font("DroidSansMono.ttf"));
            editSpace.setTextFill(Color.WHITE.darker());
            grid.add(editSpace, 25, 0);
            System.out.println("Edit space generated");
        }


        ObservableList<String> items =FXCollections.observableArrayList ();

        ArrayList<NPC> NPCList = fileProcessor.getNPCList();
        ListView<String> list = new ListView<String>();
        for (NPC n : NPCList) {
        	String NPCLoop = n.getName() + "							" + n.getOccupation() + "			" + n.getMet();
        	items.add(NPCLoop);
        }

        list.setItems(items);
        list.setPrefWidth(500);
        list.setPrefHeight(400);
        grid.add(list, 0, 1, 25, 1);
        System.out.println("List generated");

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 2);

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                commandProgram();
            }
        });
    }


    /**
     * This will show available cities in the system administrator permitting
     *
     */
    private void cities(boolean admin) {
        System.out.println("ran cities");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Field labels for list of cities
        Label cityName = new Label("Name");
        cityName.setFont(Font.font("DroidSansMono.ttf"));
        cityName.setTextFill(Color.WHITE.darker());
        grid.add(cityName, 0, 0);
        System.out.println("Name box generated");

        Label cityPop = new Label("Population");
        cityPop.setFont(Font.font("DroidSansMono.ttf"));
        cityPop.setTextFill(Color.WHITE.darker());
        grid.add(cityPop, 20, 0);
        System.out.println("Population box generated");

        if (admin) {
            Label revealed = new Label("Revealed?");
            revealed.setFont(Font.font("DroidSansMono.ttf"));
            revealed.setTextFill(Color.WHITE.darker());
            grid.add(revealed, 24, 0);
            System.out.println("Revealed  box generated");

            Label editSpace = new Label("");
            editSpace.setFont(Font.font("DroidSansMono.ttf"));
            editSpace.setTextFill(Color.WHITE.darker());
            grid.add(editSpace, 25, 0);
            System.out.println("Edit space generated");
        }

        ObservableList<String> items =FXCollections.observableArrayList ();

        ArrayList<City> cityList = fileProcessor.getCityList();

        ListView<String> list = new ListView<String>();
        for (City c : cityList) {
            int spacingSize1 = 72 - c.getName().length();
            int spacingSize2 = 20 - c.getPopulation().length();
            StringBuilder spacing1 = new StringBuilder();
            StringBuilder spacing2 = new StringBuilder();
            while (spacingSize1 > 0) {
                spacing1.append(" ");
                spacingSize1 -= 1;
            }
            while (spacingSize2 > 0) {
                spacing2.append(" ");
                spacingSize2 -= 1;
            }
            String specialLoop = c.getName() + spacing1 + c.getPopulation() + spacing2 + c.getRevealed();
            items.add(specialLoop);
        }

        list.setItems(items);
        list.setPrefWidth(500);
        list.setPrefHeight(400);
        grid.add(list, 0, 1, 25, 1);
        System.out.println("List generated");

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 2);

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                commandProgram();
            }
        });
    }


    /**
     * This will show available specials in the system administrator permitting
     *
     */
    public void specials(boolean admin) {
    	System.out.println("ran specials");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Field labels for list of specials
        Label specialName = new Label("Name");
        specialName.setFont(Font.font("DroidSansMono.ttf"));
        specialName.setTextFill(Color.WHITE.darker());
        grid.add(specialName, 0, 0);
        System.out.println("Name box generated");

        if (admin) {
            Label revealed = new Label("Revealed?");
            revealed.setFont(Font.font("DroidSansMono.ttf"));
            revealed.setTextFill(Color.WHITE.darker());
            grid.add(revealed, 24, 0);
            System.out.println("Revealed  box generated");

        	Label editSpace = new Label("");
        	editSpace.setFont(Font.font("DroidSansMono.ttf"));
        	editSpace.setTextFill(Color.WHITE.darker());
        	grid.add(editSpace, 25, 0);
        	System.out.println("Edit space generated");
    	}
        
       ObservableList<String> items =FXCollections.observableArrayList ();
        
        ArrayList<Special> specialList = fileProcessor.getSpecialList();
        ListView<String> list = new ListView<String>();
        for (Special s : specialList) {
            int spacingSize = 81 - s.getName().length();
            StringBuilder spacing = new StringBuilder();
            while (spacingSize > 0) {
                spacing.append(" ");
                spacingSize -= 1;
            }
        	String specialLoop = s.getName() + spacing + s.getMet();
        	items.add(specialLoop);
        }
        
        list.setItems(items);
        list.setPrefWidth(500);
        list.setPrefHeight(400);
        grid.add(list, 0, 1, 25, 1);
        System.out.println("List generated");

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 2);

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);
        
        Scene scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                commandProgram();
            }
        });
    }
}
