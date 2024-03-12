package view.owner;

import java.io.File;
import java.sql.SQLException;

import controller.owner.CreatePropertyController;
import controller.owner.OwnerProfileUpdateController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Owner;
import view.Connection;
import view.Window;

public class OwnerPropertyAdd extends Scene {
    public OwnerPropertyAdd(ScrollPane container, Window window, Owner owner)throws SQLException, Exception {
        super(container, 800, 600);
        System.out.println("OwnerPropertyAdd constructor");
        
        Label title = new Label("Ajouter une propriété :");
        Label address = new Label("Adresse du bien: ");
        Label houseNumberLabel = new Label("Numéro :");
		Label labelLabel = new Label("Nom de rue :");
		Label postCodeLabel = new Label("Code postal :");
		Label cityLabel = new Label("Ville :");
		Label surfaceProperty = new Label("Surface du bien :");	
		Label accesCode = new Label("Code d'accès :");
		Label keyBoxCode = new Label("Code de la boîte à clés :");
		Label specialInstruction = new Label("Instruction supplémentaires :");
		Button addProperty = new Button("Ajouter");
		Button returnview = new Button("Retour");
		
        
        
		TextField houseNumberInputField = new TextField();
		TextField labelInputField = new TextField();
		TextField postCodeInputField = new TextField();
		TextField cityInputField = new TextField();
		TextField surfacePropertyInputField= new TextField();
		
		TextField accesCodeInputField = new TextField();
		TextField keyBoxCodeInputField = new TextField();
		TextField specialInstructionInputField = new TextField();
		
		
		 EventHandler<ActionEvent> eventaddProperty = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				 try {
					new CreatePropertyController(houseNumberInputField.getText(),
												labelInputField.getText(),
												postCodeInputField.getText(),
												cityInputField.getText(),
												Integer.valueOf(surfacePropertyInputField.getText(), 10),
												accesCodeInputField.getText(),
												keyBoxCodeInputField.getText(),
												specialInstructionInputField.getText(),
												window,
												owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				}

			};
			addProperty.setOnAction(eventaddProperty);
			
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
        vbox.getChildren().add(address);
        vbox.getChildren().add(houseNumberLabel);
		vbox.getChildren().add(houseNumberInputField);
		vbox.getChildren().add(labelLabel);
		vbox.getChildren().add(labelInputField);
		vbox.getChildren().add(postCodeLabel);
		vbox.getChildren().add(postCodeInputField);
		vbox.getChildren().add(cityLabel);
		vbox.getChildren().add(cityInputField);
		vbox.getChildren().add(surfaceProperty);
		vbox.getChildren().add(surfacePropertyInputField);
		vbox.getChildren().add(accesCode);
		vbox.getChildren().add(accesCodeInputField);
		vbox.getChildren().add(keyBoxCode);
		vbox.getChildren().add(keyBoxCodeInputField);
		vbox.getChildren().add(specialInstruction);
		vbox.getChildren().add(specialInstructionInputField);
		vbox.getChildren().add(addProperty);
		vbox.getChildren().add(returnview);
        

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
