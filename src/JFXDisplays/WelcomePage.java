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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashSet;

public class WelcomePage implements Page{

    private final Stage primaryStage;
    private final GridPane grid;
    private final Scene scene;

    public WelcomePage(Stage stage){
        this.primaryStage = stage;

        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        this.scene = new Scene(grid, 1152, 648);
        primaryStage.setScene(scene);

    }

    @Override
    public void loadPage() {

        Text sceneTitle = new Text("Welcome to World Builder!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        sceneTitle.setFill(Color.WHITE.darker());
        grid.add(sceneTitle, 0, 0, 3, 1);

        HBox worldName = new HBox(60);
        Label userName = new Label("World Name:");
        userName.setFont(Font.font("Tahoma"));
        userName.setTextFill(Color.WHITE.darker());
        worldName.getChildren().add(userName);

        TextField userTextField = new TextField();
        userTextField.setPrefWidth(240);
        worldName.getChildren().add(userTextField);
        grid.add(worldName, 0, 3);

        Button signIn = new Button("Open World");
        HBox hbBtn = new HBox(60);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(signIn);

        Button newWorld = new Button("Create World");
        hbBtn.getChildren().add(newWorld);

        Button addWorld = new Button("Add World");
        hbBtn.getChildren().add(addWorld);
        grid.add(hbBtn,0,5);

        final Text actionTarget = new Text();
        actionTarget.setTextAlignment(TextAlignment.CENTER);
        grid.add(actionTarget, 0, 6);

        primaryStage.setTitle("World Builder");
        primaryStage.setScene(scene);
        primaryStage.show();

        signIn.setOnAction(e -> {
            String filename = userTextField.getText();
            File mainFile = null;

            System.out.println("name of file: " + userTextField.getText());
            File directory = new File(filename);

            if (!directory.exists()) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("File does not exist");
            }
            else if (userTextField.getText().equals("")) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Please give a name for the file");
            }
            else {
                mainFile = new File(filename + "/" + filename + ".bck");

                LoginPages loginPages = new LoginPages(primaryStage, this, mainFile);
                loginPages.loadPage();
            }
        });

        newWorld.setOnAction(actionEvent -> {
            String filename = userTextField.getText();
            File mainFile;
            System.out.println("name of new file: " + userTextField.getText());
            Path filePath = Paths.get(filename);
            mainFile = new File(filename + ".bck");
            File fileLocation = new File(userTextField.getText()+ "/" + userTextField.getText() + ".bck");
            if(fileLocation.exists()){
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("World already exists, try opening it");
            }
            else if(userTextField.getText().equals("")){
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Please give a name for the file");
            }
            else {

                GridPane newSceneGrid = new GridPane();
                newSceneGrid.setAlignment(Pos.CENTER);
                newSceneGrid.setHgap(10);
                newSceneGrid.setVgap(10);
                newSceneGrid.setPadding(new Insets(25, 25, 25, 25));
                Scene newFileScene = new Scene(newSceneGrid, 1152, 648);
                primaryStage.setScene(newFileScene);

                BackgroundFill background_fill = new BackgroundFill(Color.DIMGREY.darker(),
                        CornerRadii.EMPTY, Insets.EMPTY);
                Background background = new Background(background_fill);
                newSceneGrid.setBackground(background);

                Text sceneTitle12 = new Text("Please enter the admin password for the new world");
                sceneTitle12.setFill(Color.WHITE.darker());
                sceneTitle12.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                newSceneGrid.add(sceneTitle12, 0, 0, 2, 1);

                Label newPassword = new Label("Password:");
                newPassword.setTextFill(Color.WHITE.darker());
                newSceneGrid.add(newPassword, 0, 3);

                PasswordField passwordTextField = new PasswordField();
                newSceneGrid.add(passwordTextField, 1, 3);

                Label newPasswordCheck = new Label("Confirm Password:");
                newPasswordCheck.setTextFill(Color.WHITE.darker());
                newSceneGrid.add(newPasswordCheck, 0, 4);

                PasswordField passwordCheck = new PasswordField();
                newSceneGrid.add(passwordCheck, 1, 4);

                Button passwordButton = new Button("Enter");
                HBox hbBtn12 = new HBox(20);
                hbBtn12.setAlignment(Pos.BOTTOM_RIGHT);
                hbBtn12.getChildren().add(passwordButton);
                newSceneGrid.add(hbBtn12, 1, 6);

                final Text actionTarget12 = new Text();
                newSceneGrid.add(actionTarget12, 1, 7);

                passwordButton.setOnAction(actionEvent13 -> {
                    String newPassword1 = passwordTextField.getText();
                    String newPassCheck = passwordCheck.getText();
                    if(newPassword1.equals(newPassCheck)) {
                        if(newPassword1.length() < 6){
                            actionTarget12.setFill(Color.FIREBRICK);
                            actionTarget12.setText("Make sure that your password is 6 or more character");
                        }
                        else {
                            try {
                                primaryStage.close();
                                primaryStage.setTitle(filename);
                                File directory = new File(String.valueOf(filePath.getFileName()));
                                boolean directoryMake = directory.mkdirs();            // this is here to make the directory, don't delete
                                File file = new File(directory.toString() + "/" + mainFile);
                                Password password = new Password(newPassword1);
                                Writer writer = new FileWriter(file);
                                String encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
                                writer.write(encodedString);
                                writer.flush();
                                writer.close();
                                FileProcessor fileProcessor = new FileProcessor(file, true, password);
                                HashSet<WorldElement> objects = fileProcessor.readFile(file);
                                HomeJFX commander = new HomeJFX(objects, true, primaryStage, fileProcessor);
                                commander.commandProgram();
                                fileProcessor.writeFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        actionTarget12.setFill(Color.FIREBRICK);
                        actionTarget12.setText("Make sure that your passwords match");
                    }
                });
            }
        });
    }

    @Override
    public void reloadPage() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
