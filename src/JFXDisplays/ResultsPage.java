package JFXDisplays;

import Components.City;
import Components.NPC;
import Components.Special;
import Components.WorldElement;
import Work_Classes.FileProcessor;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    private TableView<WorldElement> table;

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

        BorderPane windowDisplay = new BorderPane();  // the full window setup

        HBox title = new HBox(40);
        title.setAlignment(Pos.CENTER);
        Text sceneTitle = new Text("Results for " + resultType);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        sceneTitle.setFill(Color.WHITE.darker());
        title.getChildren().add(sceneTitle);
        grid.add(title, 0, 0);

        HBox actionBox = new HBox();
        Text actionText = new Text();
        actionText.setTextAlignment(TextAlignment.CENTER);
        actionBox.getChildren().add(actionText);
        actionText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        actionText.setFill(Color.WHITE.darker());
        actionBox.setAlignment(Pos.CENTER);

        table = new TableView<>();

        // the column for the name of the element
        TableColumn<WorldElement, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> {
            WorldElement name = cellData.getValue();
            return new ReadOnlyStringWrapper(name.getName());
        });
        nameCol.setStyle("-fx-alignment: CENTER;");
        nameCol.setMinWidth(150);
        nameCol.setMaxWidth(150);
        table.getColumns().add(nameCol);

        // the column for the special part of the element
        if (!resultType.equals("Special")) {
            TableColumn<WorldElement, String> col2;
            if (resultType.equals("NPC")) col2 = new TableColumn<>("Occupation");
            else col2 = new TableColumn<>("Population");
            col2.setCellValueFactory(cellData -> {
                WorldElement element = cellData.getValue();
                if (resultType.equals("NPC")) return new ReadOnlyStringWrapper(((NPC) element).getOccupation());
                return new ReadOnlyStringWrapper(((City) element).getPopulation());
            });
            col2.setStyle("-fx-alignment: CENTER;");
            col2.setMinWidth(150);
            col2.setMaxWidth(150);
            table.getColumns().add(col2);
        }


        if (admin){
            // the column for the name of the element
            TableColumn<WorldElement, String> revealedCol = new TableColumn<>("Revealed?");
            revealedCol.setCellValueFactory(cellData -> {
                WorldElement element = cellData.getValue();
                if (element.getRevealed()) return new ReadOnlyStringWrapper("Yes");
                return new ReadOnlyStringWrapper("No");
            });
            revealedCol.setStyle("-fx-alignment: CENTER;");
            revealedCol.setMinWidth(100);
            revealedCol.setMaxWidth(100);

            // the column for the name of the element
            TableColumn<WorldElement, String> revealCodeCol = new TableColumn<>("Revealed Code");
            revealCodeCol.setCellValueFactory(cellData -> {
                WorldElement element = cellData.getValue();
                return new ReadOnlyStringWrapper(element.getRevealCode());
            });
            revealCodeCol.setStyle("-fx-alignment: CENTER;");
            revealCodeCol.setMinWidth(100);
            revealCodeCol.setMaxWidth(100);

            table.getColumns().add(revealedCol);
            table.getColumns().add(revealCodeCol);
        }
        ObservableList<WorldElement> tableList = FXCollections.observableArrayList();

        tableList.addAll(processor);
        table.setItems(tableList);

        // result if row is double-clicked
        table.setRowFactory( tr -> {
            TableRow<WorldElement> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    actionText.setText("");
                    WorldElement rowContent = row.getItem();
                    if (resultType.equals("City")){
                        CityPage details = new CityPage(primaryStage, this, (City) rowContent, fileProcessor, admin);
                        details.loadPage();
                    }
                    else if (resultType.equals("NPC")){
                        NPCPage details = new NPCPage(primaryStage, this, (NPC) rowContent, fileProcessor, admin);
                        details.loadPage();
                    }
                    else {
                        SpecialPage details = new SpecialPage(primaryStage, this, (Special) rowContent, fileProcessor, admin);
                        details.loadPage();
                    }
                }
            });
            return row ;
        });

        windowDisplay.setCenter(table);

        Button back = new Button("Back");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(back);
        grid.add(hbBtn, 0, 2);

        grid.add(windowDisplay, 0, 1);

        grid.add(actionBox, 0, 5);

        primaryStage.setScene(scene);
        primaryStage.show();

        back.setOnAction(actionEvent -> {
            for (WorldElement part : processor){
                fileProcessor.updateComponent(part);
            }
            loadLast();
        });

    }

    @Override
    public void reloadPage() {
        primaryStage.setScene(scene);
        primaryStage.show();
        table.refresh();
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
