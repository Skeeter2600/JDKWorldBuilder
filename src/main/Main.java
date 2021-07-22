package main;

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

public class Main extends Application {

    // This is the main file
    // Created by Beck Anderson

    @Override
    public void start(Stage primaryStage){

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
                                    HomeJFX commander = new HomeJFX(objects, false, primaryStage);
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
                                        Password password = new Password(decodedString);
                                        FileProcessor fileProcessor = new FileProcessor(mainFile[0], true, password);
                                        HashSet<Object> objects = fileProcessor.readFile(mainFile[0]);
                                        HomeJFX commander = new HomeJFX(objects, true, primaryStage);
                                        commander.commandProgram();
                                        fileProcessor.writeFile();
                                        System.out.println("Thanks! come again");
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
                            HomeJFX commander = new HomeJFX(objects, false, primaryStage);
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
                                try {
                                    File file = new File(String.valueOf(filePath.getFileName()));
                                    boolean directory = file.mkdirs();
                                    File create = new File(file+ "/" + mainFile[0]);
                                    boolean fileMade = create.createNewFile();
                                    Password password = new Password(newPassword);
                                    Writer writer;
                                    writer = new FileWriter(create);
                                    String encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
                                    writer.write(encodedString);
                                    writer.flush();
                                    writer.close();
                                    FileProcessor fileProcessor = new FileProcessor(mainFile[0], true, password);
                                    HashSet<Object> objects = fileProcessor.readFile(mainFile[0]);
                                    HomeJFX commander = new HomeJFX(objects, true, primaryStage);
                                    commander.commandProgram();
                                    fileProcessor.writeFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
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



//        while (!fileOpener) {
//            while (!fileReceiver) {
//                if (!mainFile[0].exists()) {
//                    System.out.print("File does not exist, create a new world named " + filename[0] + ".bck (yes or no)?: ");
//                    filename[0] = filename[0] + ".bck";
//                    String response = input.nextLine();
//                    if (response.equals("yes") || response.equals("y")) {
//                        mainFile[0] = new File(filename[0]);
//                        mainFile[0].createNewFile();
//                        String StringPassword;
//                        do {
//                            System.out.print("Please create an admin password: ");
//                            StringPassword = input.nextLine();
//                            System.out.print("Password will be " + StringPassword + ". Okay?: ");
//                            response = input.nextLine();
//                        } while (!response.equals("yes") && !response.equals("y"));
//                        password = new Password(StringPassword);
//                        Writer writer = new FileWriter(mainFile[0]);
//                        String encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
//                        writer.write(encodedString);
//                        writer.flush();
//                        writer.close();
//                        admin = true;
//                    }
//                }
//                if (mainFile[0].exists()) {
//                    System.out.print("Open " + filename[0] + " (yes or no)?: ");
//                    String response = input.nextLine();
//                    if (response.equals("yes") || response.equals("y")) {
//                        fileOpener = true;
//                        fileReceiver = true;
//                        if(!admin) {
//                            System.out.print("Open as an admin?: ");
//                            response = input.nextLine();
//                        }if (response.equals("yes") || response.equals("y") || admin) {
//                            String login;
//                            BufferedReader reader = new BufferedReader(new FileReader(mainFile[0]));
//                            String passwordInput = reader.readLine();
//                            byte[] decodedBytes = Base64.getDecoder().decode(passwordInput);
//                            String decodedString = new String(decodedBytes);
//                            while (true) {
//                                if (admin) break;
//                                System.out.print("Password: ");
//                                login = input.nextLine();
//                                assert false;
//                                if (login.equals("cancel")) break;
//                                assert true;
//                                if (decodedString.equals(login)) {
//                                    password = new Password(decodedString);
//                                    admin = true;
//                                } else {
//                                    System.out.println("Wrong password, try again.");
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            if(filename[0].equals("quit"))break;
//        }
//        assert false;
//        if(!filename[0].equals("quit")) {
//            FileOutputStream oFile = new FileOutputStream(mainFile[0], false);
//            FileProcessor fileProcessor = new FileProcessor(mainFile[0], admin, password);
//            HashSet<Object> objects = fileProcessor.readFile(mainFile[0]);
//            HomeJFX commander = new HomeJFX(objects, admin);
//            commander.commandProgram();
//            fileProcessor.writeFile();
//            System.out.println("Thanks! come again");
//        }

    }

    /**
     * This is the main.main function for the system
     *
     * @param args nada
     */
    public static void main(String[] args){
          launch(args);

//        boolean fileReceiver = false;
//        boolean fileOpener = false;
//        boolean admin = false;
//        Scanner input = new Scanner(System.in);
//        String filename = null;
//        File mainFile = null;
//        Password password = null;
//        while (!fileOpener) {
//            while (!fileReceiver) {
//                System.out.print("Please provide the name of the world file or type quit to quit: ");
//                filename = input.nextLine();
//                if(filename.equals("quit"))break;
//                mainFile = new File(filename + ".bck");
//                if (!mainFile.exists()) {
//                    System.out.print("File does not exist, create a new world named " + filename + ".bck (yes or no)?: ");
//                    filename = filename + ".bck";
//                    String response = input.nextLine();
//                    if (response.equals("yes") || response.equals("y")) {
//                        mainFile = new File(filename);
//                        mainFile.createNewFile();
//                        String StringPassword;
//                        do {
//                            System.out.print("Please create an admin password: ");
//                            StringPassword = input.nextLine();
//                            System.out.print("Password will be " + StringPassword + ". Okay?: ");
//                            response = input.nextLine();
//                        } while (!response.equals("yes") && !response.equals("y"));
//                        password = new Password(StringPassword);
//                        Writer writer = new FileWriter(mainFile);
//                        String encodedString = Base64.getEncoder().encodeToString(password.getPassword().getBytes());
//                        writer.write(encodedString);
//                        writer.flush();
//                        writer.close();
//                        admin = true;
//                    }
//                }
//                if (mainFile.exists()) {
//                    System.out.print("Open " + filename + " (yes or no)?: ");
//                    String response = input.nextLine();
//                    if (response.equals("yes") || response.equals("y")) {
//                        fileOpener = true;
//                        fileReceiver = true;
//                        if(!admin) {
//                            System.out.print("Open as an admin?: ");
//                            response = input.nextLine();
//                        }if (response.equals("yes") || response.equals("y") || admin) {
//                            String login;
//                            BufferedReader reader = new BufferedReader(new FileReader(mainFile));
//                            String passwordInput = reader.readLine();
//                            byte[] decodedBytes = Base64.getDecoder().decode(passwordInput);
//                            String decodedString = new String(decodedBytes);
//                            while (true) {
//                                if (admin) break;
//                                System.out.print("Password: ");
//                                login = input.nextLine();
//                                assert false;
//                                if (login.equals("cancel")) break;
//                                assert true;
//                                if (decodedString.equals(login)) {
//                                    password = new Password(decodedString);
//                                    admin = true;
//                                } else {
//                                    System.out.println("Wrong password, try again.");
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            if(filename.equals("quit"))break;
//        }
//        assert false;
//        if(!filename.equals("quit")) {
//            FileOutputStream oFile = new FileOutputStream(mainFile, false);
//            FileProcessor fileProcessor = new FileProcessor(mainFile, admin, password);
//            HashSet<Object> objects = fileProcessor.readFile(mainFile);
//            HomeJFX commander = new HomeJFX(objects, admin);
//            commander.commandProgram();
//            fileProcessor.writeFile();
//            System.out.println("Thanks! come again");
//        }
//
    }
}
