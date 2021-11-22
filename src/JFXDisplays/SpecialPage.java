package JFXDisplays;

import Components.Special;
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

public class SpecialPage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final ResultsPage previous;
    private final Special special;
    private final boolean admin;

    public SpecialPage(Stage primaryStage, ResultsPage previous, Special special, boolean admin){
        this.primaryStage = primaryStage;
        this.previous = previous;
        this.special = special;
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

        scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);
    }

    @Override
    public void loadPage() {

        // header of the page
        Text actionText = new Text();
        actionText.setFill(Color.WHITE.darker());
        grid.add(actionText, 0, 4);
        StackPane root = new StackPane();
        TextArea sceneTitle = new TextArea(special.getName());
        if (!admin) sceneTitle.setEditable(false);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        root.getChildren().add(sceneTitle);
        grid.add(root, 0, 0);

        BorderPane windowDisplay = new BorderPane();  // the full window setup

        GridPane info = new GridPane();
        info.setHgap(20);
        info.setAlignment(Pos.CENTER);

        Text descriptionHeader = new Text("Description");
        descriptionHeader.setFill(Color.WHITE.darker());
        TextArea description = new TextArea(special.getDescription());
        if (!admin) description.setEditable(false);

        info.add(descriptionHeader, 0, 0);
        info.add(description, 0, 1);

        Text hiddenDescriptionHeader = new Text("Hidden Description:");
        hiddenDescriptionHeader.setFill(Color.WHITE.darker());
        TextArea hiddenDescription = new TextArea(special.getHiddenDescription());

        if (!admin) hiddenDescription.setEditable(false);
        CheckBox revealed = new CheckBox();
        TextArea revealCode = new TextArea(String.valueOf(special.getRevealCode()));
        revealCode.setEditable(false);

        if(admin) {
            Text revealedHeader = new Text("Revealed?");
            revealedHeader.setFill(Color.WHITE.darker());
            revealed.setSelected(special.getRevealed());

            info.add(revealedHeader, 1, 0);
            info.add(revealed, 1, 1);

            Text revealCodeHeader = new Text("Reveal Code: ");
            revealCodeHeader.setFill(Color.WHITE.darker());
            info.add(revealCodeHeader, 2, 0);
            info.add(revealCode, 2, 1);

            if (special.getHiddenDescription() != null){
                info.add(hiddenDescriptionHeader, 3, 0);
                info.add(hiddenDescription, 3, 1);
            }
        }

        Text notesHeader = new Text("Notes:");
        notesHeader.setFill(Color.WHITE.darker());
        TextArea notes = new TextArea(special.getNotes());
        info.add(notesHeader, 3, 0);
        info.add(notes, 3, 1);

        windowDisplay.setTop(info);
        BorderPane.setMargin(info, new Insets(10));

        HBox buttonBox = new HBox(100);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        Button back = new Button("Back");
        buttonBox.getChildren().add(back);

        Button save = new Button("Save");

        if (admin) buttonBox.getChildren().add(save);

        windowDisplay.setCenter(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(10));

        windowDisplay.setPadding(new Insets(10, 10, 10, 10));

        grid.add(windowDisplay, 0, 1);
        primaryStage.setScene(scene);
        primaryStage.show();

        back.setOnAction(actionEvent -> {
            if (sceneTitle.getText().contains(" _-_ ") || description.getText().contains(" _-_ ") ||
                    (notes.getText().contains(" _-_ "))) {
                actionText.setFill(Color.FIREBRICK);
                actionText.setText("Please avoid using the string ' _-_ '!");
            }
            else {
                special.setNotes(notes.getText());
                previous.reloadPage();
            }
        });

        save.setOnAction(actionEvent -> {
            if (sceneTitle.getText().contains(" _-_ ") || description.getText().contains(" _-_ ") ||
                    (notes.getText().contains(" _-_ "))) {
                actionText.setFill(Color.FIREBRICK);
                actionText.setText("Please avoid using the string ' _-_ '!");
            }
            else {
                special.setName(sceneTitle.getText());
                special.setDescription(description.getText());
                special.setNotes(notes.getText());
                actionText.setFill(Color.WHITE.darker());
                actionText.setText(sceneTitle.getText() + " has been updated!");
            }
        });
    }

    @Override
    public void reloadPage() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
