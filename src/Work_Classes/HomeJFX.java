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
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
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
//        while(!quit){
//            System.out.print("What would you like to do today (type help for list of commands)?: ");
//            String response = input.nextLine();
//
//            switch (response){
//                case ("help"):
//                    System.out.println("To add a new component, type 'add'");
//                    System.out.println("To quit, type 'quit'");
//                    break;
//                case ("quit"):
//                    System.out.print("Are you sure you would like to quit?: ");
//                    response = input.nextLine();
//                    if(response.equals("yes") || response.equals("y")) quit = true;
//                    break;
//                case ("add"):
//                    if(admin) {
//                        System.out.print("Sure! ");
//                            System.out.print("What would you like to add today (NPC, City, Special)?: ");
//                            response = input.nextLine();
//                            switch (response.toLowerCase()) {
//                                case ("npc"):
//                                    if (addNPC()) {
//                                        System.out.println("NPC has been successfully created");
//                                    } else {
//                                        System.out.println("Something has gone wrong with the creation. Please try again");
//                                    }
//                                    break;
//                                case ("city"):
//                                    if (addCity()) {
//                                        System.out.println("City has been successfully created");
//                                    } else {
//                                        System.out.println("Something has gone wrong with the creation. Please try again");
//                                    }
//                                    break;
//                                case ("special"):
//                                    if (addSpecial()) {
//                                        System.out.println("Special has been successfully created");
//                                    } else {
//                                        System.out.println("Something has gone wrong with the creation. Please try again");
//                                    }
//                                    break;
//                                default:
//                                    System.out.println("That's not a valid type, please try again");
//                            }
//                    }else{
//                        System.out.println("Sorry, only admins have the ability to add components to the world. :(");
//                    }break;
//            }
//        }
        // addCity();
        // addSpecial();
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
        NPCname.setFont(Font.font("Tahoma"));
        NPCname.setTextFill(Color.WHITE.darker());
        grid.add(NPCname, 0, 0);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, 0);
        System.out.println("Name Text generated");

        // Gets the NPC's occupation
        Label NPCOccupation = new Label("Occupation:");
        NPCOccupation.setFont(Font.font("Tahoma"));
        NPCOccupation.setTextFill(Color.WHITE.darker());
        grid.add(NPCOccupation, 0, 1);
        System.out.print("Occupation generated");

        TextField occupationTextField = new TextField();
        occupationTextField.setPrefWidth(200);
        grid.add(occupationTextField, 1, 1);
        System.out.println("Occupation Text generated");

        // Gets a description of the NPC
        Label NPCDescription = new Label("Description:");
        NPCDescription.setFont(Font.font("Tahoma"));
        NPCDescription.setTextFill(Color.WHITE.darker());
        grid.add(NPCDescription, 0, 2);
        System.out.print("Description Generated");

        TextArea descriptionTextField = new TextArea();
        descriptionTextField.setPrefWidth(200);
        grid.add(descriptionTextField, 1, 2);
        System.out.println("Occupation Text generated");

        // Asks if hidden description exists
        Label HiddenDescription = new Label("Hidden Description?:");
        HiddenDescription.setFont(Font.font("Tahoma"));
        HiddenDescription.setTextFill(Color.WHITE.darker());
        grid.add(HiddenDescription, 0, 3);
        System.out.print("Description Generated");

        CheckBox DescriptionExists = new CheckBox();
        grid.add(DescriptionExists, 1, 3);
        System.out.println("Occupation Text generated");
        boolean hidden = false;

        // Gets a hidden description of the NPC
        Label NPCHiddenDescription = new Label("Hidden Description:");
        NPCHiddenDescription.setFont(Font.font("Tahoma"));
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
                    //
                    if (DescriptionExists.isSelected()) {
                        processor.add(new NPC(nameTextField.getText(), occupationTextField.getText(), descriptionTextField.getText(),
                        		hiddenDescriptionTextField.getText(), false, revealCode, ""));
                    } else {
                        processor.add(new NPC(nameTextField.getText(), occupationTextField.getText(), descriptionTextField.getText(), 
                        		false, revealCode, ""));
                    }
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
        cityName.setFont(Font.font("Tahoma"));
        cityName.setTextFill(Color.WHITE.darker());
        grid.add(cityName, 0, 0);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, 0);
        System.out.println("Name Text generated");

        // Gets the City's population
        Label CityPopulation = new Label("Population:");
        CityPopulation.setFont(Font.font("Tahoma"));
        CityPopulation.setTextFill(Color.WHITE.darker());
        grid.add(CityPopulation, 0, 1);
        System.out.print("Population generated");

        TextField populationTextField = new TextField();
        populationTextField.setPrefWidth(50);
        grid.add(populationTextField, 1, 1);
        System.out.println("Population Text generated");

        // Gets the City's song
        Label citySong = new Label("Song:");
        citySong.setFont(Font.font("Tahoma"));
        citySong.setTextFill(Color.WHITE.darker());
        grid.add(citySong, 0, 2);
        System.out.print("Description Generated");

        TextField songTextField = new TextField();
        songTextField.setPrefWidth(200);
        grid.add(songTextField, 1, 2);
        System.out.println("Song Text generated");

        // Gets the City's population
        Label cityAesthetic = new Label("Aesthetic:");
        cityAesthetic.setFont(Font.font("Tahoma"));
        cityAesthetic.setTextFill(Color.WHITE.darker());
        grid.add(cityAesthetic, 0, 3);
        System.out.print("Aesthetic generated");

        TextArea aestheticTextField = new TextArea();
        aestheticTextField.setPrefWidth(200);
        grid.add(aestheticTextField, 1, 3);
        System.out.println("Aesthetic Text generated");

        // Gets the City's trades
        Label cityTrade = new Label("Trades:");
        cityTrade.setFont(Font.font("Tahoma"));
        cityTrade.setTextFill(Color.WHITE.darker());
        grid.add(cityTrade, 0, 4);
        System.out.print("Description Generated");

        TextField tradeTextField = new TextField();
        tradeTextField.setPrefWidth(200);
        grid.add(tradeTextField, 1, 4);
        System.out.println("Trade Text generated");

        // Gets the City's Residents
        Label cityResidents = new Label("Residents:");
        cityResidents.setFont(Font.font("Tahoma"));
        cityResidents.setTextFill(Color.WHITE.darker());
        grid.add(cityResidents, 0, 2);
        System.out.print("Residents Generated");

        TextField residentsTextField = new TextField();
        residentsTextField.setPrefWidth(200);
        grid.add(residentsTextField, 1, 2);
        System.out.println("Residents Text generated");

        // Gets the City's Specials
        Label citySpecials = new Label("Specials:");
        citySpecials.setFont(Font.font("Tahoma"));
        citySpecials.setTextFill(Color.WHITE.darker());
        grid.add(citySpecials, 0, 2);
        System.out.print("Specials Generated");

        TextField specialsTextField = new TextField();
        specialsTextField.setPrefWidth(200);
        grid.add(specialsTextField, 1, 2);
        System.out.println("Specials Text generated");

        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(cancel);
        grid.add(hbBtn, 0, 7);

        Button save = new Button("Save");
        HBox newWorldBtn = new HBox(20);
        newWorldBtn.setAlignment(Pos.BOTTOM_LEFT);
        newWorldBtn.getChildren().add(save);
        grid.add(newWorldBtn, 3, 7);

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
                        (songTextField.getText().contains(" _-_ ") || tradeTextField.getText().contains(" _-_ "))) {
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
                List<String> residents = new ArrayList<>();
                residents.add(residentsTextField.getText());
                List<String> specials = new ArrayList<>();
                residents.add(specialsTextField.getText());
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
                    // adds the NPCs to the system
                    //List<String> trades = tradeTextField.getText().split(",");
                    //processor.add(new City(nameTextField.getText(),populationTextField.getText(),));
                    processor.add(new City(nameTextField.getText(), populationTextField.getText(), tradeTextField.getText(),residents,
                    songTextField.getText(), aestheticTextField.getText(),specials, false, revealCode, ""));
                    fileProcessor.writeFile();
                    }
                }
            }
        );
    }

    /**
     * This will add a special to the system
     * experimental
     * 
     * @return true if completed, false if a problem occurred
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
        specialName.setFont(Font.font("Tahoma"));
        specialName.setTextFill(Color.WHITE.darker());
        grid.add(specialName, 0, 0);
        System.out.print("Name box generated");

        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);
        grid.add(nameTextField, 1, 0);
        System.out.println("Name Text generated");

        // Gets a description of the Special
        Label specialDescription = new Label("Description:");
        specialDescription.setFont(Font.font("Tahoma"));
        specialDescription.setTextFill(Color.WHITE.darker());
        grid.add(specialDescription, 0, 2);
        System.out.print("Description Generated");

        TextArea descriptionTextField = new TextArea();
        descriptionTextField.setPrefWidth(200);
        grid.add(descriptionTextField, 1, 2);
        System.out.println("Occupation Text generated");

        // Asks if hidden description exists
        Label hiddenDescription = new Label("Hidden Description?:");
        hiddenDescription.setFont(Font.font("Tahoma"));
        hiddenDescription.setTextFill(Color.WHITE.darker());
        grid.add(hiddenDescription, 0, 3);
        System.out.print("Description Generated");

        CheckBox DescriptionExists = new CheckBox();
        grid.add(DescriptionExists, 1, 3);
        System.out.println("Occupation Text generated");
        boolean hidden = false;

        // Gets a hidden description of the Special
        Label specialHiddenDescription = new Label("Hidden Description:");
        specialHiddenDescription.setFont(Font.font("Tahoma"));
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
                    while(!revealCoded) {
                        revealCoded = true;
                        // adds the specials to the system
                        int one = new Random().nextInt(9);
                        int two = new Random().nextInt(9);
                        int three = new Random().nextInt(9);
                        int four = new Random().nextInt(9);
                        int five = new Random().nextInt(9);
                        revealCode = one + String.valueOf(two) + three + four + five;

                        // add check for if another part has the same revealCode here
                        Special tempSpecial = new Special("Capitol Building", "tester", true, "12345","");
                        for (Object part : processor){
                            if (part.getClass() == tempSpecial.getClass()) {
                                if (tempSpecial.getRevealCode().equals(revealCode)) {
                                    revealCoded = false;
                                }
                            }
                        }
                    }
                    if (DescriptionExists.isSelected()) {
                        processor.add(new Special(nameTextField.getText(), descriptionTextField.getText(),
                        		hiddenDescriptionTextField.getText(), false, revealCode, ""));
                    } else {
                        processor.add(new Special(nameTextField.getText(), descriptionTextField.getText(),
                                false, revealCode, ""));
                    }
                    fileProcessor.writeFile();
                }
                specials(admin);
            }
        });
    }

//    /**
//     * OLD CONSOLE METHOD
//     * This will add a Special to the system
//     * @return true if completed, false if a problem occurred
//     */
//    public boolean addSpecial(){
//
//        System.out.print("Awesome! What would you like its name to be?: ");
//        // checks for if an NPC by that name already exists
//        boolean notMultiple = false;
//        String SpecialName = "blank";
//        boolean multipleCheck = true;
//        Special comparator = new Special("God Staff", "I check for class",true,"12345","");
//
//        // checks each element for its existence
//        while (!notMultiple) {
//            SpecialName = input.nextLine();
//            notMultiple = true;
//            for (Object o : processor) {
//                if (o.getClass() == comparator.getClass()) {
//                    Special check = (Special) o;
//                    if (check.getName().equals(((Special) o).getName())) {
//                        System.out.println("An Special by this name already exists. Please provide a new name");
//                        multipleCheck = false;
//                    }
//                }
//            }if(!multipleCheck){ notMultiple = false; }
//        }
//
//        // gets the description
//        System.out.print("Interesting.... Can I get a description of it?: ");
//        String SpecialDescription = input.nextLine();
//
//        // checks if a hidden description will exist
//        System.out.print("Nice! Does it have a hidden description?: ");
//        String response = input.nextLine();
//        boolean hidden = false;
//        String SpecialHiddenDescription = null;
//        // if yes
//        if(response.equals("yes")||response.equals("y")){
//            hidden = true;
//            System.out.print("What is it?: ");
//            SpecialHiddenDescription =  input.nextLine();
//        }
//
//        // shows the aspects of the Special
//        System.out.println("So this is what you want the Special to be like?");
//        System.out.println("Name: " + SpecialName + "\nDescription: " + SpecialDescription);
//        if(hidden) System.out.println("Hidden Description (this won't actually show): " + SpecialHiddenDescription);
//
//        // Makes sure everything is good
//        System.out.print("Is this good?: ");
//        String good = input.nextLine();
//        // if not, change a part
//        while (!(good.equals("yes")||good.equals("y"))){
//            // loop for changes
//            System.out.print("What needs to change (Name, Description, Hidden Description [if exists], or cancel)?: ");
//            String change = input.nextLine();
//            boolean found = false;
//            while (!found) {
//                switch (change.toLowerCase()) {
//                    case ("name"):
//                        System.out.print("Awesome! What would you like its name to be?: ");
//                        SpecialName = input.nextLine();
//                        found = true;
//                        break;
//                    case ("description"):
//                        System.out.print("Can I get a description of it?: ");
//                        SpecialDescription = input.nextLine();
//                        found = true;
//                        break;
//                    case ("hidden description"):
//                        System.out.print("What is it?: ");
//                        SpecialHiddenDescription = input.nextLine();
//                        found = true;
//                        break;
//                    case ("cancel"):
//                        found = true;
//                        break;
//                }
//            }
//
//            System.out.println("So this is what you want the NPC to be like?");
//            System.out.println("Name: " + SpecialName + "\nDescription: " + SpecialDescription);
//            if(hidden) System.out.println("Hidden Description (this won't actually show):" + SpecialHiddenDescription);
//            System.out.print("Is this good?: ");
//            good = input.nextLine();
//        }
//
//        int one = new Random().nextInt(9);
//        int two = new Random().nextInt(9);
//        int three = new Random().nextInt(9);
//        int four = new Random().nextInt(9);
//        int five = new Random().nextInt(9);
//        String revealCode = one + String.valueOf(two) + three + four + five;
//
//        // checks for the separation string
//        if(SpecialName.contains(" _-_ ")||SpecialDescription.contains(" _-_ ")||
//                (SpecialHiddenDescription != null && SpecialHiddenDescription.contains(" _-_ "))){ return false; }
//
//        // adds the NPCs to the system
//        if(SpecialHiddenDescription!=null){
//            processor.add(new Special(SpecialName, SpecialDescription, SpecialHiddenDescription, false, revealCode,"" ));
//        } else {
//            processor.add(new Special(SpecialName, SpecialDescription, false, revealCode,""));
//        }
//        return true;
//    }
    
    
    /**
     * This will show available NPCs in the system administrator permitting
     *
     * @return true if completed, false if a problem occurred
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
        NPCName.setFont(Font.font("Tahoma"));
        NPCName.setTextFill(Color.WHITE.darker());
        grid.add(NPCName, 0, 0);
        System.out.println("Name box generated");
        
        Label NPCOccupation = new Label("Occupation");
        NPCOccupation.setFont(Font.font("Tahoma"));
        NPCOccupation.setTextFill(Color.WHITE.darker());
        grid.add(NPCOccupation, 20, 0);
        System.out.println("Occupation box generated");
        
        if (admin == true) {
            Label revealed = new Label("Revealed?");
            revealed.setFont(Font.font("Tahoma"));
            revealed.setTextFill(Color.WHITE.darker());
            grid.add(revealed, 24, 0);
            System.out.println("Revealed  box generated");

            //Edit buttons for admin will be below this space
            Label editSpace = new Label("");
            editSpace.setFont(Font.font("Tahoma"));
            editSpace.setTextFill(Color.WHITE.darker());
            grid.add(editSpace, 25, 0);
            System.out.println("Edit space generated");
        }
        
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
            "Joe Swanson", "Darwin", "The Warden", "Mysterious Wizard");
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
     * @return true if completed, false if a problem occurred
     */
    public void cities(boolean admin) {
    	System.out.println("ran cities");
    	
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //Field labels for list of cities
        Label cityName = new Label("Name");
        cityName.setFont(Font.font("Tahoma"));
        cityName.setTextFill(Color.WHITE.darker());
        grid.add(cityName, 0, 0);
        System.out.println("Name box generated");
        
        Label cityPop = new Label("Population");
        cityPop.setFont(Font.font("Tahoma"));
        cityPop.setTextFill(Color.WHITE.darker());
        grid.add(cityPop, 20, 0);
        System.out.println("Population box generated");
        
        if (admin == true) {
            Label revealed = new Label("Revealed?");
            revealed.setFont(Font.font("Tahoma"));
            revealed.setTextFill(Color.WHITE.darker());
            grid.add(revealed, 24, 0);
            System.out.println("Revealed  box generated");
                
        	Label editSpace = new Label("");
        	editSpace.setFont(Font.font("Tahoma"));
        	editSpace.setTextFill(Color.WHITE.darker());
        	grid.add(editSpace, 25, 0);
        	System.out.println("Edit space generated");
    	}
        
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
            "Onnet", "Twoson", "Threed", "Fourside");
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
     * @return true if completed, false if a problem occurred
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
        specialName.setFont(Font.font("Tahoma"));
        specialName.setTextFill(Color.WHITE.darker());
        grid.add(specialName, 0, 0);
        System.out.println("Name box generated");
        
        if (admin == true) {
            Label revealed = new Label("Revealed?");
            revealed.setFont(Font.font("Tahoma"));
            revealed.setTextFill(Color.WHITE.darker());
            grid.add(revealed, 24, 0);
            System.out.println("Revealed  box generated");
                
        	Label editSpace = new Label("");
        	editSpace.setFont(Font.font("Tahoma"));
        	editSpace.setTextFill(Color.WHITE.darker());
        	grid.add(editSpace, 25, 0);
        	System.out.println("Edit space generated");
    	}
        
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
            "Single", "Double", "Suite", "Family App");
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
