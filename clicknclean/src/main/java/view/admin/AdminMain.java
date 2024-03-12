package view.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Admin;
import model.Cleaner;
import model.CleanerExperience;
import model.Dispute;
import model.Mission;
import view.Window;
import controller.admin.AdminMainController;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminMain extends Scene {

    public AdminMain(ScrollPane container, Window window, Admin admin, ArrayList<Cleaner> cleanersToConfirm, ArrayList<Pair<Dispute, Mission>> disputeList) throws Exception {
        super(container, 800, 600);
        System.out.println("AdminMain constructor");

        window.setTitle("Accueil d'Admin");

        Label welcomeMessageLabel = new Label("Bienvenue " + admin.getSurname());
        Label confirmCleanerLabel = new Label("Cleaners en attente de confirmation :");

/*----------------------------------------Display cleaners -----------------------------------------------*/

        MenuButton menuButton = new MenuButton("Sélectionnez un Cleaner");

        for (Cleaner currentCleaner : cleanersToConfirm) {
            Menu cleanerBlockContainer = new Menu(currentCleaner.getSurname() + " " + currentCleaner.getName());
            MenuItem acceptContainer = new MenuItem();
            Button accept = new Button("Confirmer");
            accept.setOnAction(e -> {
                try {
                    handleCleaner(window, admin, currentCleaner.getCleanerId(), 1);
                } catch (Exception e1) {
                    System.out.println("Failed to execute handleCleaner in AdminMain. Error : " + e1);
                }
            });
            acceptContainer.setGraphic(accept);

            MenuItem blockContainer = new MenuItem();
            Button block = new Button("Ne pas confirmer");
            block.setOnAction(e -> {
                try {
                    handleCleaner(window, admin, currentCleaner.getCleanerId(), 99);
                } catch (Exception e1) {
                    System.out.println("Failed to execute handleCleaner in AdminMain. Error : " + e1);
                }
            });
            blockContainer.setGraphic(block);

            MenuItem displayContainer = new MenuItem();

            Label display = new Label("Nom :" + currentCleaner.getSurname() + " " + currentCleaner.getName()
            + "\nDate de naissance : " + currentCleaner.getBirthDate()
            + "\nExpérience : " + CleanerExperience.asString(currentCleaner.getExperience())
            + "\nMotivation :  " + currentCleaner.getMotivation());
            displayContainer.setGraphic(display);

            cleanerBlockContainer.getItems().addAll(displayContainer, acceptContainer, blockContainer);
            menuButton.getItems().add(cleanerBlockContainer);
        }

/*----------------------------------------Display disputes -----------------------------------------------*/
        Label displayDisputeLabel = new Label("Litiges à gérer");

        MenuButton menuButton2 = new MenuButton("Sélectionnez un litige");

        for (Pair<Dispute, Mission> currentLitigation: disputeList) {
            Menu cleanerBlockContainer = new Menu(
                "Client : " + currentLitigation.getKey().getOwnerDisplay() 
                + "\nCleaner : " + currentLitigation.getValue());
            MenuItem acceptContainer = new MenuItem();
            Button accept = new Button("Donner raison au Cleaner");
            accept.setOnAction(e -> {
                try {
                    handleDispute(window, admin, currentLitigation.getKey().getCleanerId(), 1);
                } catch (Exception e1) {
                    System.out.println("Failed to execute handleCleaner in AdminMain. Error : " + e1);
                }
            });
            acceptContainer.setGraphic(accept);

            MenuItem blockContainer = new MenuItem();
            Button block = new Button("Donner raison au client");
            block.setOnAction(e -> {
                try {
                    handleDispute(window, admin, currentLitigation.getKey().getCleanerId(), 99);
                } catch (Exception e1) {
                    System.out.println("Failed to execute handleCleaner in AdminMain. Error : " + e1);
                }
            });
            blockContainer.setGraphic(block);

            MenuItem displayContainer = new MenuItem();

            Label display = new Label("Plainte :" + currentLitigation.getKey().getContent()
            + "\nMission :"
                + "\n\t Date : " + currentLitigation.getValue().getMissionDate()
                + "\n\t Prix : " + currentLitigation.getValue().getCost()
                + "\n\t Status :" + currentLitigation.getValue().getState());
            displayContainer.setGraphic(display);
            
            cleanerBlockContainer.getItems().addAll(displayContainer, acceptContainer, blockContainer);
            menuButton.getItems().add(cleanerBlockContainer);
        }


/*----------------------------------------------------------------------------------------------------------------- */

        MenuBar bar = new MenuBar();
        Menu disconnect = new Menu("Déconnexion");
        Menu activities = new Menu("Notifications");

        bar.getMenus().addAll(disconnect, activities);

        EventHandler<ActionEvent> eventDisconnect = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // window.setScene(SceneId.CONNECTION);
                // disconnectController? see for right
            }
        };
        disconnect.setOnAction(eventDisconnect);

        EventHandler<ActionEvent> eventViewProfil = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // go view Ownerprofil
            }
        };

        VBox vbox = new VBox();
        vbox.getChildren().add(bar);
        vbox.getChildren().add(confirmCleanerLabel);
        vbox.getChildren().add(menuButton);
        vbox.getChildren().add(displayDisputeLabel);
        vbox.getChildren().add(menuButton2);

        //vbox.getChildren().add(listViewSuspend);

        //listViewSuspend.setMaxHeight(100);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(100, 300, 20, 300));
        vbox.setAlignment(Pos.TOP_CENTER);

        container.setContent(vbox);

        container.setPannable(true);
        container.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        container.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        this.getStylesheets()
                .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
    }

    public void handleCleaner(Window win, Admin ad, int cleanerId, int isConfirmed) throws Exception {
        new AdminMainController(win, ad, 1, cleanerId, isConfirmed);
    }

    public void handleDispute(Window win, Admin ad, int userId, int winner) throws Exception {
        new AdminMainController(win, ad, 2, userId, winner );
    }
}
