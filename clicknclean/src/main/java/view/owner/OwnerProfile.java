package view.owner;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import view.Window;

public class OwnerProfile extends Scene {
	public OwnerProfile(ScrollPane container, Window window) {
		  super(container, 800, 600);
		  
		  Label nameOwner = new Label("Nom :");
		  Label surnameOwner = new Label("Prenom :");
		  Label mailOwner = new Label("Mail :");
		  Label phoneOwner = new Label("Téléphone :");
		  Label birthDateOwner = new Label("Date de naissance :");
		  Label motivationOwner = new Label("Type de prestation recherchée :");
		  
		  
		
		  
		  VBox vbox = new VBox();
		  vbox.getChildren().add(nameOwner);
		  vbox.getChildren().add(surnameOwner);
		  vbox.getChildren().add(mailOwner);
		  vbox.getChildren().add(phoneOwner);
		  vbox.getChildren().add(birthDateOwner);
		  vbox.getChildren().add(motivationOwner);
		  
		  container.setContent(vbox);

			container.setPannable(true);
			container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

			this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
	}

}
