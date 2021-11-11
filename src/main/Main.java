package main;

import Basic_Classes.Password;
import JFXDisplays.LoginPage;
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

    /**
     *
     */
    @Override
    public void start(Stage primaryStage){

        LoginPage loginPage = new LoginPage(primaryStage);
        loginPage.loadPage();

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
