package Work_Classes;


import Basic_Classes.Password;
import Work_Classes.HomeJFX;
import Work_Classes.FileProcessor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashSet;


public class AltMain{

    /**
     * This a recreation of the start function that launches
     * when the program begins. It exists to restart the program
     * @param primaryStage the initial stage
     */
    public static void restart(Stage primaryStage){

        final boolean[] admin = {false};
        final String[] filename = {null};
        final File[] mainFile = {null};

        GridPane grid = new GridPane();
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

        signIn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                filename[0] = userTextField.getText();
                System.out.println("name of file: " + userTextField.getText());
                File directory = new File(filename[0]);
                if (!directory.exists()) {
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("File does not exist");
                }
                else if(userTextField.getText().equals("")){
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Please give a name for the file");
                } else {
                    mainFile[0] = new File(filename[0] + "/" + filename[0] + ".bck");
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

                    Text sceneTitle = new Text("Would you like to enter as an admin?");
                    sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    sceneTitle.setFill(Color.WHITE.darker());
                    newSceneGrid.add(sceneTitle, 0, 0, 2, 1);

                    Button yesButton = new Button("Yes");
                    HBox yesBtn = new HBox(20);
                    yesBtn.setAlignment(Pos.BOTTOM_RIGHT);
                    yesBtn.getChildren().add(yesButton);
                    newSceneGrid.add(yesBtn, 1, 1);

                    Button noButton = new Button("No");
                    HBox noBtn = new HBox(20);
                    noBtn.setAlignment(Pos.BOTTOM_RIGHT);
                    noBtn.getChildren().add(noButton);
                    newSceneGrid.add(noBtn, 0, 1);
                    admin[0] = false;

                    yesButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
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

                            Text sceneTitle = new Text("Please enter the password:");
                            sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            sceneTitle.setFill(Color.WHITE.darker());
                            newSceneGrid.add(sceneTitle, 0, 0, 2, 1);

                            Label newPassword = new Label("Password:");
                            newPassword.setTextFill(Color.WHITE.darker());
                            newSceneGrid.add(newPassword, 0, 3);

                            PasswordField passwordTextField = new PasswordField();
                            newSceneGrid.add(passwordTextField, 1, 3);

                            Button cancelButton = new Button("Never Mind");
                            HBox cancelBtn = new HBox(20);
                            cancelBtn.setAlignment(Pos.BOTTOM_RIGHT);
                            cancelBtn.getChildren().add(cancelButton);
                            newSceneGrid.add(cancelBtn, 0, 6);

                            Button enterButton = new Button("Enter");
                            HBox hbBtn = new HBox(20);
                            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
                            hbBtn.getChildren().add(enterButton);
                            newSceneGrid.add(hbBtn, 1, 6);

                            final Text actionTarget = new Text();
                            newSceneGrid.add(actionTarget, 1, 7);

                            BufferedReader reader = null;
                            try { reader = new BufferedReader(new FileReader(mainFile[0])); }
                            catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }
                            String passwordInput = null;
                            try {
                                assert reader != null;
                                passwordInput = reader.readLine(); }
                            catch (IOException ioException) { ioException.printStackTrace(); }
                            byte[] decodedBytes = Base64.getDecoder().decode(passwordInput);
                            String decodedString = new String(decodedBytes);

                            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Password password = new Password(decodedString);
                                    FileProcessor fileProcessor = new FileProcessor(mainFile[0], false, password);
                                    HashSet<Object> objects = fileProcessor.readFile(mainFile[0]);
                                    HomeJFX commander = new HomeJFX(objects, false, primaryStage, fileProcessor);
                                    commander.commandProgram();
                                    fileProcessor.writeFile();
                                    System.out.println("Thanks! come again");
                                }
                            });

                            enterButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    String login = passwordTextField.getText();
                                    if (decodedString.equals(login)) {
                                        primaryStage.close();
                                        primaryStage.setTitle(filename[0]);
                                        Password password = new Password(decodedString);
                                        FileProcessor fileProcessor = new FileProcessor(mainFile[0], true, password);
                                        HashSet<Object> objects = fileProcessor.readFile(mainFile[0]);
                                        HomeJFX commander = new HomeJFX(objects, true, primaryStage, fileProcessor);
                                        commander.commandProgram();
                                        fileProcessor.writeFile();
                                    }else{
                                        actionTarget.setFill(Color.FIREBRICK);
                                        actionTarget.setText("Password is incorrect. Try again");
                                    }
                                }
                            });
                        }
                    });

                    noButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.close();
                            primaryStage.setTitle(filename[0]);
                            BufferedReader reader = null;
                            try { reader = new BufferedReader(new FileReader(mainFile[0])); }
                            catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }
                            String passwordInput = null;
                            try {
                                assert reader != null;
                                passwordInput = reader.readLine(); }
                            catch (IOException ioException) { ioException.printStackTrace(); }
                            byte[] decodedBytes = Base64.getDecoder().decode(passwordInput);
                            String decodedString = new String(decodedBytes);

                            Password password = new Password(decodedString);
                            FileProcessor fileProcessor = new FileProcessor(mainFile[0], false, password);
                            HashSet<Object> objects = fileProcessor.readFile(mainFile[0]);
                            HomeJFX commander = new HomeJFX(objects, false, primaryStage, fileProcessor);
                            commander.commandProgram();
                            fileProcessor.writeFile();
                            System.out.println("Thanks! come again");
                        }
                    });
                }
            }
        });

        newWorld.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                filename[0] = userTextField.getText();
                System.out.println("name of new file: " + userTextField.getText());
                Path filePath = Paths.get(filename[0]);
                mainFile[0] = new File(filename[0] + ".bck");
                if (Files.exists(filePath)&&mainFile[0].exists()) {
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("File already exists");
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

                    Text sceneTitle = new Text("Please enter the admin password for the new world");
                    sceneTitle.setFill(Color.WHITE.darker());
                    sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    newSceneGrid.add(sceneTitle, 0, 0, 2, 1);

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
                    HBox hbBtn = new HBox(20);
                    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
                    hbBtn.getChildren().add(passwordButton);
                    newSceneGrid.add(hbBtn, 1, 6);

                    final Text actionTarget = new Text();
                    newSceneGrid.add(actionTarget, 1, 7);

                    passwordButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent actionEvent) {
                            String newPassword = passwordTextField.getText();
                            String newPassCheck = passwordCheck.getText();
                            if(newPassword.equals(newPassCheck)) {
                                if(newPassword.length() < 6){
                                    actionTarget.setFill(Color.FIREBRICK);
                                    actionTarget.setText("Make sure that your password is 6 or more character");
                                }
                                else {
                                    try {
                                        primaryStage.close();
                                        primaryStage.setTitle(filename[0]);
                                        File directory = new File(String.valueOf(filePath.getFileName()));
                                        boolean directoryMake = directory.mkdirs();            // this is here to make the directory, don't delete
                                        File file = new File(directory.toString() + "/" + mainFile[0]);
                                        Password password = new Password(newPassword);
                                        Writer writer = new FileWriter(file);
                                        String encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
                                        writer.write(encodedString);
                                        writer.flush();
                                        writer.close();
                                        FileProcessor fileProcessor = new FileProcessor(file, true, password);
                                        HashSet<Object> objects = fileProcessor.readFile(file);
                                        HomeJFX commander = new HomeJFX(objects, true, primaryStage, fileProcessor);
                                        commander.commandProgram();
                                        fileProcessor.writeFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else{
                                actionTarget.setFill(Color.FIREBRICK);
                                actionTarget.setText("Make sure that your passwords match");
                            }
                            admin[0] = true;
                        }
                    });
                }
            }
        });
    }
}
