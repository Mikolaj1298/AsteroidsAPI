package asteroids;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainPanel {
    private TreeView<String> tree;
    private String tempName;
    public String startDate;
    public String endDate;
    public LocalDate startDate_dat;
    public LocalDate endDate_dat;
    List<Asteroid> asteroidList;
    Asteroid actualAsteroid;

    public MainPanel() {

    }

    public Scene makeScene() {
    	ResourceBundle rb = ResourceBundle.getBundle("asteroids/cfg/resource_bundle");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);
        
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(false);

        Label nameLabel = new Label(rb.getString("entryDate"));
        GridPane.setConstraints(nameLabel, 1, 0);

        DatePicker firstDatePicker = new DatePicker();
        GridPane.setConstraints(firstDatePicker, 1, 1);
        firstDatePicker.setOnAction(event -> {
        	this.startDate_dat = firstDatePicker.getValue();
        	this.startDate = firstDatePicker.getValue().toString();
        });

        Label descriptionLabel = new Label(rb.getString("endDate"));
        GridPane.setConstraints(descriptionLabel, 1, 2);

        DatePicker secondDatePicker = new DatePicker();
        GridPane.setConstraints(secondDatePicker, 1, 3);
        secondDatePicker.setOnAction(event -> {
        	this.endDate_dat = secondDatePicker.getValue();
        	this.endDate = secondDatePicker.getValue().toString();
        	
        });

        HBox acceptContainer = new HBox(10);
        Button fileButton = new Button(rb.getString("showAsteroids"));
        fileButton.setOnAction(event -> {
        	try {
        		if(firstDatePicker.getValue() != null && secondDatePicker.getValue() != null) {        			
        			System.out.println(getDatesBetweenUsingJava8(this.startDate_dat, this.endDate_dat));
        			if(getDatesBetweenUsingJava8(this.startDate_dat, this.endDate_dat).size() > 7) {
        				this.endDate_dat = this.startDate_dat.plusDays(7);
        				this.endDate = endDate_dat.toString();
        				//System.out.println("endDate: "+ this.endDate);
        				fillTheTree(root, getDatesBetweenUsingJava8(this.startDate_dat, this.endDate_dat));
        			}
        		}
				//createAsteroidList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
              

        acceptContainer.getChildren().addAll(fileButton);
        GridPane.setConstraints(acceptContainer, 1, 8);

        
        tree = new TreeView<>(root);
        tree.setShowRoot(false);

        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                tempName = newValue.getValue();
                //System.out.println(asteroidList.size());
                               
                for(int i=0; i<asteroidList.size(); i++) {
                	if (asteroidList.get(i).name == tempName) {
                		actualAsteroid = asteroidList.get(i);
                	}
                }                   
            }
        });

        Button detailsButton = new Button(rb.getString("showDetails"));
        GridPane.setConstraints(detailsButton, 0, 9);
        detailsButton.setOnAction(event -> {
            DetailsBox.display("Szczegó³y", actualAsteroid);
        });

        tree.setPrefHeight(270);

        GridPane.setConstraints(tree, 0, 0, 1, 9);
        grid.getChildren().addAll(firstDatePicker, nameLabel, descriptionLabel, secondDatePicker, acceptContainer,
                 detailsButton, tree);


        Scene scene = new Scene(grid, 500, 340);
        return scene;
    }

    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
    
    
    public static List<LocalDate> getDatesBetweenUsingJava8(
    		LocalDate startDate, LocalDate endDate) { 
    			long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate); 
    		    return IntStream.iterate(0, i -> i + 1)
    		      .limit(numOfDaysBetween)
    		      .mapToObj(i -> startDate.plusDays(i))
    		      .collect(Collectors.toList()); 
    		}
    
    public void fillTheTree(TreeItem<String> root, List<LocalDate> list) throws Exception {
    	this.asteroidList = new LinkedList<Asteroid>();
    	for(int i=0; i<list.size(); i++) {
    		AsteroidsApi api = new AsteroidsApi(startDate, endDate);
    		int dailyAsteroidsNumber = api.getDailyAsteroidsNumber(list.get(i));

    		TreeItem<String> asteroids = new TreeItem<String>("Asteroidy");
    		asteroids = makeBranch(list.get(i).toString(), root);
    		for(int j=0; j<dailyAsteroidsNumber; j++) {
    			try {    				
    				Asteroid asteroid = new Asteroid(api, list.get(i).toString(), j);
    				makeBranch(asteroid.name, asteroids);
    				this.asteroidList.add(asteroid);
    			} catch (Exception e) {
    				
    			}
    			
    		}
    		       
    	}
    	
    }
}
