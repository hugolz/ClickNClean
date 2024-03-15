package view.owner;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.owner.OwnerAskAddProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import model.Owner;
import model.Property;
import tools.Db;
import view.Window;

public class OwnerProperty extends Scene{
	public OwnerProperty(ScrollPane container, Window window, Owner owner)throws SQLException, Exception {
		 super(container, 800, 600);
		 System.out.println("OwnerProperty constructor");
		 
		 Label title = new Label("Vos Propriétés :");
		 Button addProperty = new Button("Ajouter une Propriété");
		 Button returnview = new Button("Retour");
		 
		
		 ListView<String> listView = new ListView<String>();
		 Db db = new Db();
		 ArrayList<Property> prop = db.DAOReadOwnerProperties(owner.getOwnerID());
		 
		 for (int i=0; i< prop.size(); i++) {
	        listView.getItems().add("Propriété "+ i
	        		+ "\nAdresse : " + prop.get(i).getPropertyAddress().asString()
	                + "\nSurface : " + prop.get(i).getPropertySurface() + "m²"
	                + "\nCode d'accès : " + prop.get(i).getAccesCode()
	                + "\nCode boite à clé :  " + prop.get(i).getKeyBoxCode()
	                + "\nInstructions Spéciales :  " + prop.get(i).getSpecialInstruction());
		 }
		 
		 EventHandler<ActionEvent> eventNewProperty = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					try {
						new OwnerAskAddProperty(window, owner);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			};
			addProperty.setOnAction(eventNewProperty);
			
			
			EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					try {
						window.setScene(new OwnerMain(new ScrollPane(), window, owner));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			};
			returnview.setOnAction(eventReturn);
		 
		 
		 VBox vbox = new VBox();
		 vbox.getChildren().add(title);
		 vbox.getChildren().add(addProperty);
		 vbox.getChildren().add(returnview);
		 vbox.getChildren().add(listView);
		 
		 
		 vbox.setSpacing(10);
			vbox.setPadding(new Insets(100, 300, 20, 300));
			vbox.setAlignment(Pos.TOP_CENTER);

		
			container.setContent(vbox);
			
			container.setPannable(true);
			container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

	        this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		 
	}

}
