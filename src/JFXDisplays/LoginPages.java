package JFXDisplays;

import Basic_Classes.Password;
import Components.WorldElement;
import Work_Classes.FileProcessor;
import Work_Classes.HomeJFX;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Base64;
import java.util.HashSet;

public class LoginPages implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Page previous;
    private final File file;

    public LoginPages(Stage stage, Page previous, File file){
        this.primaryStage = stage;
        this.previous = previous;
        this.file = file;

        this.grid = new GridPane();
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
    }

    @Override
    public void loadPage() {

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Text sceneTitle1 = new Text("Would you like to enter as an admin?");
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
            grid.getChildren().clear();

            Text sceneTitle11 = new Text("Please enter the password:");
            sceneTitle11.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            sceneTitle11.setFill(Color.WHITE.darker());
            grid.add(sceneTitle11, 0, 0, 2, 1);

            Label newPassword = new Label("Password:");
            newPassword.setTextFill(Color.WHITE.darker());
            grid.add(newPassword, 0, 3);

            PasswordField passwordTextField = new PasswordField();
            grid.add(passwordTextField, 1, 3);

            Button cancelButton = new Button("Never Mind");
            HBox cancelBtn = new HBox(20);
            cancelBtn.setAlignment(Pos.BOTTOM_RIGHT);
            cancelBtn.getChildren().add(cancelButton);
            grid.add(cancelBtn, 0, 6);

            Button enterButton = new Button("Enter");
            HBox hbBtn1 = new HBox(20);
            hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn1.getChildren().add(enterButton);
            grid.add(hbBtn1, 1, 6);

            final Text actionTarget1 = new Text();
            grid.add(actionTarget1, 1, 7);

            BufferedReader reader = null;
            try { reader = new BufferedReader(new FileReader(file)); }

            catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }

            String passwordInput = null;
            try {
                assert reader != null;
                passwordInput = reader.readLine();
            } catch (IOException ioException) { ioException.printStackTrace(); }

            byte[] decodedBytes = Base64.getDecoder().decode(passwordInput);
            String decodedString = new String(decodedBytes);

            cancelButton.setOnAction(actionEvent1 -> {
                Password password = new Password(decodedString);
                FileProcessor fileProcessor = new FileProcessor(file, false, password);
                HashSet<WorldElement> objects = fileProcessor.readFile(file);
                HomeJFX commander = new HomeJFX(objects, false, primaryStage, fileProcessor);
                commander.commandProgram();
                fileProcessor.writeFile();
                System.out.println("Thanks! come again");
            });

            enterButton.setOnAction(actionEvent12 -> {
                String login = passwordTextField.getText();
                if (decodedString.equals(login)) {
                    primaryStage.close();
                    primaryStage.setTitle(file.getName());
                    Password password = new Password(decodedString);
                    FileProcessor fileProcessor = new FileProcessor(file, true, password);
                    HashSet<WorldElement> objects = fileProcessor.readFile(file);
                    HomePage commander = new HomePage(objects, true, primaryStage, fileProcessor, this);
                    commander.loadPage();
                }else{
                    actionTarget1.setFill(Color.FIREBRICK);
                    actionTarget1.setText("Password is incorrect. Try again");
                }
            });
        });

        noButton.setOnAction(actionEvent -> {
            primaryStage.close();
            primaryStage.setTitle(file.getName());
            BufferedReader reader = null;
            try { reader = new BufferedReader(new FileReader(file)); }
            catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }
            String passwordInput = null;
            try {
                assert reader != null;
                passwordInput = reader.readLine(); }
            catch (IOException ioException) { ioException.printStackTrace(); }
            byte[] decodedBytes = Base64.getDecoder().decode(passwordInput);
            String decodedString = new String(decodedBytes);

            Password password = new Password(decodedString);
            FileProcessor fileProcessor = new FileProcessor(file, false, password);
            HashSet<WorldElement> objects = fileProcessor.readFile(file);
            HomePage commander = new HomePage(objects, false, primaryStage, fileProcessor, this);
            commander.loadPage();
        });
    }

    @Override
    public void reloadPage() {
        previous.loadPage();
    }
}
