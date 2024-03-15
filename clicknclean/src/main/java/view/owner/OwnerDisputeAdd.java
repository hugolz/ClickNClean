package view.owner;

import java.io.File;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import model.Owner;
import view.Window;

public class OwnerDisputeAdd extends Scene{
	public OwnerDisputeAdd(ScrollPane container, Window window, Owner owner) throws SQLException, Exception {
		super(container, 800, 600);
		System.out.println("OwnerDisputeAdd constructor");
		
		Label title = new Label("Création de Litige :");
		
		
		
		 VBox vbox = new VBox();
		 vbox.getChildren().add(title);
		
		 
		 
		 
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
