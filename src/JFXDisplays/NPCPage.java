package JFXDisplays;
//
//import Pages.NPC;
//import Work_Classes.HomeJFX;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.util.HashSet;
//import java.util.Random;
//
/**
* This code is commented out bc it isn't used yet
*it's being saved for if we decide to use it
*/
//
public class NPCPage implements Page{
    @Override
    public void loadPage() {

    }

    @Override
    public void reloadPage() {

    }
//
//    private final HashSet<Object> processor;
//    private final Stage primaryStage;
//    private final boolean admin;
//
//
//    public NPCJFX(HashSet<Object> processor, Stage primaryStage, boolean admin){
//        this.processor = processor;
//        this.primaryStage = primaryStage;
//        this.admin = admin;
//    }
//
//    /**
//     * This will add an NPC to the system
//     *
//     * @return true if completed, false if a problem occurred
//     */
//    public void addNPC() {
//
//        // wipe grid
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        // Get the name of the NPC with text box
//        Label NPCname = new Label("Name:");
//        NPCname.setFont(Font.font("Tahoma"));
//        NPCname.setTextFill(Color.WHITE.darker());
//        grid.add(NPCname, 0, 0);
//        System.out.print("Name box generated");
//
//        TextField nameTextField = new TextField();
//        nameTextField.setPrefWidth(200);
//        grid.add(nameTextField, 1, 0);
//        System.out.println("Name Text generated");
//
//        // Gets the NPC's occupation
//        Label NPCOccupation = new Label("Occupation:");
//        NPCOccupation.setFont(Font.font("Tahoma"));
//        NPCOccupation.setTextFill(Color.WHITE.darker());
//        grid.add(NPCOccupation, 0, 1);
//        System.out.print("Occupation generated");
//
//        TextField OccupationTextField = new TextField();
//        OccupationTextField.setPrefWidth(200);
//        grid.add(OccupationTextField, 1, 1);
//        System.out.println("Occupation Text generated");
//
//        // Gets a description of the NPC
//        Label NPCDescription = new Label("Description:");
//        NPCDescription.setFont(Font.font("Tahoma"));
//        NPCDescription.setTextFill(Color.WHITE.darker());
//        grid.add(NPCDescription, 0, 2);
//        System.out.print("Description Generated");
//
//        TextArea DescriptionTextField = new TextArea();
//        DescriptionTextField.setPrefWidth(200);
//        grid.add(DescriptionTextField, 1, 2);
//        System.out.println("Occupation Text generated");
//
//        // Asks if hidden description exists
//        Label HiddenDescription = new Label("Hidden Description?:");
//        HiddenDescription.setFont(Font.font("Tahoma"));
//        HiddenDescription.setTextFill(Color.WHITE.darker());
//        grid.add(HiddenDescription, 0, 3);
//        System.out.print("Description Generated");
//
//        CheckBox DescriptionExists = new CheckBox();
//        grid.add(DescriptionExists, 1, 3);
//        System.out.println("Occupation Text generated");
//        boolean hidden = false;
//
//        // Gets a description of the NPC
//        Label NPCHiddenDescription = new Label("Hidden Description:");
//        NPCHiddenDescription.setFont(Font.font("Tahoma"));
//        NPCHiddenDescription.setTextFill(Color.WHITE.darker());
//        grid.add(NPCHiddenDescription, 0, 4);
//        System.out.print("Description Generated");
//
//        TextArea HiddenDescriptionTextField = new TextArea();
//        grid.add(HiddenDescriptionTextField, 1, 4);
//        System.out.println("Occupation Text generated");
//
//        Button cancel = new Button("Cancel");
//        HBox hbBtn = new HBox(20);
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().add(cancel);
//        grid.add(hbBtn, 0, 5);
//
//        Button save = new Button("Save");
//        HBox newWorldBtn = new HBox(20);
//        newWorldBtn.setAlignment(Pos.BOTTOM_LEFT);
//        newWorldBtn.getChildren().add(save);
//        grid.add(newWorldBtn, 3, 5);
//
//        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
//                CornerRadii.EMPTY, Insets.EMPTY);
//        Background background = new Background(background_fill);
//        grid.setBackground(background);
//
//        Scene scene = new Scene(grid, 1152, 648);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("World Viewer");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        final Text actionTarget = new Text();
//        grid.add(actionTarget, 1, 6);
//
//        final String[] outcome = {"false"};
//
//        cancel.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                HomeJFX commander = new HomeJFX(processor, false, primaryStage);
//                commander.commandProgram();
//            }
//        });
//        save.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                // checks for the separation string
//                if (NPCname.getText().contains(" _-_ ") || NPCDescription.getText().contains(" _-_ ") || NPCOccupation.getText().contains(" _-_ ") ||
//                        (NPCHiddenDescription.getText().contains(" _-_ "))) {
//                    actionTarget.setFill(Color.FIREBRICK);
//                    actionTarget.setText("Please avoid using the string ' _-_ '!");
//                }
//
//                // checks for if an NPC by that name already exists
//                boolean notMultiple = false;
//                boolean multipleCheck = true;
//                NPC comparator = new NPC("Paul Blart", "comparer", "I check for class", true, "12345", "");
//
//                while (!notMultiple) {
//                    notMultiple = true;
//                    for (Object o : processor) {
//                        if (o.getClass() == comparator.getClass()) {
//                            NPC check = (NPC) o;
//                            if (check.getName().equals(NPCname.getText())) {
//                                actionTarget.setFill(Color.FIREBRICK);
//                                actionTarget.setText("An NPC by this name already exists!");
//                                multipleCheck = false;
//                            }
//                        }
//                    }
//                    if (!multipleCheck) {
//                        notMultiple = false;
//                    }
//                }
//                if (multipleCheck) {
//                    // adds the NPCs to the system
//                    int one = new Random().nextInt(9);
//                    int two = new Random().nextInt(9);
//                    int three = new Random().nextInt(9);
//                    int four = new Random().nextInt(9);
//                    int five = new Random().nextInt(9);
//                    String revealCode = one + String.valueOf(two) + three + four + five;
//                    if (DescriptionExists.isSelected()) {
//                        processor.add(new NPC(NPCname.getText(), NPCOccupation.getText(), NPCDescription.getText(),
//                                NPCHiddenDescription.getText(), false, revealCode, ""));
//                    } else {
//                        processor.add(new NPC(NPCname.getText(), NPCOccupation.getText(), NPCDescription.getText(),
//                                false, revealCode, ""));
//                    }
//                }
//                //displayNPCs();
//            }
//        });
//    }
}
