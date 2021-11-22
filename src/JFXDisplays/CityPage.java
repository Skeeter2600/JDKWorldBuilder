package JFXDisplays;

import Components.City;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CityPage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;
    private final Page previous;
    private final City city;
    private final boolean admin;

    public CityPage(Stage primaryStage, Page previous, City city, boolean admin){
        this.primaryStage = primaryStage;
        this.previous = previous;
        this.city = city;
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

        primaryStage.setScene(scene);
        primaryStage.show();

        //back.setOnAction(actionEvent -> previous.reloadPage());
    }

    @Override
    public void reloadPage() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
