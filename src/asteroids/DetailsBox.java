package asteroids;

import java.text.ChoiceFormat;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DetailsBox {
    public static void display(String title, Asteroid asteroid) {
    	ResourceBundle rb = ResourceBundle.getBundle("asteroids/cfg/resource_bundle");
    	
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        VBox listContainer = new VBox(20);
        listContainer.setPadding(new Insets(20, 20, 20, 20));

        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.BASELINE_LEFT);
        Label nameLabel = new Label(rb.getString("asteroidsName"));
        Label name = new Label(asteroid.name);
        nameBox.getChildren().addAll(nameLabel, name);

        HBox closeApproachDateFullBox = new HBox(10);
        closeApproachDateFullBox.setAlignment(Pos.BASELINE_LEFT);
        Label closeApproachDateFullLabel = new Label(rb.getString("approach"));
        Label closeApproachDateFull = new Label(asteroid.closeApproachDateFull);
        closeApproachDateFullBox.getChildren().addAll(closeApproachDateFullLabel, closeApproachDateFull);

        HBox diameterBox = new HBox(10);
        diameterBox.setAlignment(Pos.BASELINE_LEFT);
        Label diameterLabel = new Label(rb.getString("diameter"));
        Label diameter = new Label(asteroid.diameter);
        Label diameterMetrics = new Label();
        ChoiceFormat cf1 = new ChoiceFormat("1#meters_1| 2#meters_2| 3#meters_5");
        if(asteroid.diameter == "1") {
        	diameterMetrics.setText(rb.getString(cf1.format(1).toString()));
        }
        else if(Integer.parseInt(asteroid.diameter) % 10 == 2 || Integer.parseInt(asteroid.diameter) % 10 == 3 ||
        		Integer.parseInt(asteroid.diameter) % 10 == 4) {
        	diameterMetrics.setText(rb.getString(cf1.format(2).toString()));
        } else diameterMetrics.setText(rb.getString(cf1.format(3).toString()));
        
        
        diameterBox.getChildren().addAll(diameterLabel, diameter, diameterMetrics);
        
        HBox missDistanceBox = new HBox(10);
        missDistanceBox.setAlignment(Pos.BASELINE_LEFT);
        Label missDistanceLabel = new Label(rb.getString("distance"));
        Label missDistance = new Label(asteroid.missDistance);
        Label missDistanceMetrics = new Label();
        ChoiceFormat cf2 = new ChoiceFormat("1#kilometers_1| 2#kilometers_2| 3#kilometers_5");
        if(asteroid.missDistance == "1") {
        	missDistanceMetrics.setText(rb.getString(cf2.format(1).toString()));
        }
        else if(Integer.parseInt(asteroid.missDistance) % 10 == 2 || Integer.parseInt(asteroid.missDistance) % 10 == 3 ||
        		Integer.parseInt(asteroid.missDistance) % 10 == 4) {
        	missDistanceMetrics.setText(rb.getString(cf2.format(2).toString()));
        } else missDistanceMetrics.setText(rb.getString(cf2.format(3).toString()));
        
        missDistanceBox.getChildren().addAll(missDistanceLabel, missDistance, missDistanceMetrics);


        Button buttonClose = new Button(rb.getString("back"));
        buttonClose.setOnAction(e -> window.close());

        listContainer.getChildren().addAll(nameBox, missDistanceBox, diameterBox,closeApproachDateFullBox, buttonClose);

        Scene scene = new Scene(listContainer, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}
