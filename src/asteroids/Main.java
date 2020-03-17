package asteroids;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import org.json.*;


public class Main extends Application{
	Stage window;
	MainPanel mainPanel = new MainPanel();
	static String napis;
	
	public static void main(String[] args) {
     //Locale.setDefault(new Locale("en", "EN"));
     launch(args);
    }
	
		
	public void start(Stage stage) {
        window = stage;
        window.setTitle("Asteroidy");

        window.setScene(mainPanel.makeScene());
        window.show();
	    }	
		  

}