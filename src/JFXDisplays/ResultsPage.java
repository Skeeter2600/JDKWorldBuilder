package JFXDisplays;

import Components.WorldElement;
import Work_Classes.FileProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;

public class ResultsPage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final FileProcessor fileProcessor;
    private final boolean admin;
    private final HashSet<WorldElement> processor;
    private final String resultType;

    public ResultsPage(HashSet<WorldElement> processor, boolean admin, Stage stage, FileProcessor fileProcessor, Page previous, String resultType){

        this.primaryStage = stage;
        this.previous = previous;
        this.fileProcessor = fileProcessor;
        this.admin = admin;
        this.processor = processor;
        this.resultType = resultType;

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

    }

    @Override
    public void reloadPage() {

    }
}
