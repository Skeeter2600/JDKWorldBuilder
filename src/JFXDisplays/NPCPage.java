package JFXDisplays;

import Components.NPC;
import Work_Classes.FileProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class NPCPage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final NPC npc;
    private final FileProcessor fileProcessor;
    private final boolean admin;

    public NPCPage(Stage primaryStage, Page previous, NPC npc, FileProcessor fileProcessor, boolean admin){
        this.primaryStage = primaryStage;
        this.previous = previous;
        this.npc = npc;
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
        grid.add(actionText, 0, 6);

        StackPane root = new StackPane();
        TextArea sceneTitle = new TextArea(npc.getName());
        sceneTitle.setMaxSize(400, 100);
        if (!admin) sceneTitle.setEditable(false);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        root.getChildren().add(sceneTitle);
        grid.add(root, 0, 0);

        BorderPane windowDisplay = new BorderPane();  // the full window setup

        GridPane info = new GridPane();
        info.setVgap(20);
        info.setHgap(10);
        info.setAlignment(Pos.CENTER);

        Text occupationHeader = new Text("Occupation:");
        occupationHeader.setFill(Color.WHITE.darker());
        TextArea occupation = new TextArea(npc.getOccupation());
        occupation.setMaxSize(300, 20);
        if (!admin) occupation.setEditable(false);

        info.add(occupationHeader, 0, 1);
        info.add(occupation, 1, 1);

        Text descriptionHeader = new Text("Description:");
        descriptionHeader.setFill(Color.WHITE.darker());
        TextArea description = new TextArea(npc.getDescription());
        if (!admin) description.setEditable(false);

        info.add(descriptionHeader, 0, 2);
        info.add(description, 1, 2);

        Text hiddenDescriptionHeader = new Text("Hidden Description:");
        hiddenDescriptionHeader.setFill(Color.WHITE.darker());
        TextArea hiddenDescription;
        if (npc.getHiddenDescription() != null) hiddenDescription = new TextArea(npc.getHiddenDescription());
        else hiddenDescription = new TextArea();

        if (!admin) hiddenDescription.setEditable(false);
        CheckBox revealed = new CheckBox();
        Text revealCode = new Text(String.valueOf(npc.getRevealCode()));
        revealCode.setFill(Color.WHITE.darker());

        if(admin) {
            Text revealedHeader = new Text("Revealed?");
            revealedHeader.setFill(Color.WHITE.darker());
            revealed.setSelected(npc.getRevealed());

            info.add(revealedHeader, 0, 3);
            info.add(revealed, 1, 3);

            Text revealCodeHeader = new Text("Reveal Code:");
            revealCodeHeader.setFill(Color.WHITE.darker());
            info.add(revealCodeHeader, 0, 4);
            info.add(revealCode, 1, 4);

            if (npc.getHiddenDescription() != null){
                info.add(hiddenDescriptionHeader, 0, 5);
                info.add(hiddenDescription, 1, 5);
            }
        }

        Text notesHeader = new Text("Notes:");
        notesHeader.setFill(Color.WHITE.darker());
        TextArea notes = new TextArea(npc.getNotes());
        if(admin) {
            info.add(notesHeader, 0, 6);
            info.add(notes, 1, 6);
        }
        else{
            info.add(notesHeader, 0, 3);
            info.add(notes, 1, 3);
        }

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
        primaryStage.setScene(scene);
        primaryStage.show();

        TextArea finalHiddenDescription = hiddenDescription;
        back.setOnAction(actionEvent -> {
            if (sceneTitle.getText().contains(" _-_ ") || description.getText().contains(" _-_ ") ||
                    (notes.getText().contains(" _-_ ")) || occupation.getText().contains(" _-_ ") ||
                     Objects.requireNonNull(finalHiddenDescription).getText().contains(" _-_ ")) {
                actionText.setFill(Color.FIREBRICK);
                actionText.setText("Please avoid using the string ' _-_ '!");
            }
            else {
                npc.setNotes(notes.getText());
                loadLast();
            }
        });

        TextArea finalHiddenDescription1 = hiddenDescription;
        save.setOnAction(actionEvent -> {
            if (sceneTitle.getText().contains(" _-_ ") || description.getText().contains(" _-_ ") ||
                    (notes.getText().contains(" _-_ ")) || occupation.getText().contains(" _-_ ") ||
                    Objects.requireNonNull(finalHiddenDescription1).getText().contains(" _-_ ")) {
                actionText.setFill(Color.FIREBRICK);
                actionText.setText("Please avoid using the string ' _-_ '!");
            }
            else {
                npc.setName(sceneTitle.getText());
                npc.setDescription(description.getText());
                npc.setNotes(notes.getText());
                actionText.setFill(Color.WHITE.darker());
                actionText.setText(sceneTitle.getText() + " has been updated!");
            }
        });

        delete.setOnAction( ae -> {
            RemovePage deletePage = new RemovePage(primaryStage, this, npc, fileProcessor);
            deletePage.loadPage();
        });

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
}
