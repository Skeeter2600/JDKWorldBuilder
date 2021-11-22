package main;

import JFXDisplays.WelcomePage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    // This is the main file
    // Created by Beck Anderson

    /**
     *
     */
    @Override
    public void start(Stage primaryStage){

        WelcomePage welcomePage = new WelcomePage(primaryStage);
        welcomePage.loadPage();

    }

    /**
     * This is the main.main function for the system
     *
     * @param args nada
     */
    public static void main(String[] args){
          launch(args);
    }
}
