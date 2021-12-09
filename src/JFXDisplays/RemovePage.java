package JFXDisplays;

import Components.City;
import Components.WorldElement;
import Work_Classes.FileProcessor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RemovePage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final FileProcessor fileProcessor;
    private final WorldElement removal;

    public RemovePage(Stage primaryStage, Page previous, WorldElement removal, FileProcessor fileProcessor){
        this.primaryStage = primaryStage;
        this.previous = previous;
        this.removal = removal;
        this.fileProcessor = fileProcessor;

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
        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Text sceneTitle1 = new Text("Are you sure you would like to delete " + removal.getName() + "?");
        sceneTitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        sceneTitle1.setFill(Color.WHITE.darker());
        grid.add(sceneTitle1, 0, 0, 2, 1);

        Button yesButton = new Button("Yes");
        HBox yesBtn = new HBox(20);
        yesBtn.setAlignment(Pos.BOTTOM_RIGHT);
        yesBtn.getChildren().add(yesButton);
        grid.add(yesBtn, 1, 1);

        Button noButton = new Button("No");
        HBox noBtn = new HBox(20);
        noBtn.setAlignment(Pos.BOTTOM_RIGHT);
        noBtn.getChildren().add(noButton);
        grid.add(noBtn, 0, 1);

        yesButton.setOnAction(actionEvent -> {
            fileProcessor.removeWorldElement(removal.getName());
            previous.getPrevious().loadLast();
        });

        noButton.setOnAction(ae -> loadLast());
    }

    @Override
    public void reloadPage() {}

    @Override
    public void loadLast() {
        previous.loadLast();
    }

    @Override
    public Page getPrevious() {
        return previous;
    }
}
