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
import model.Admin;
import model.Cleaner;
import model.CleanerExperience;
import view.Window;
import controller.admin.AdminMainController;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminMain extends Scene {

    public AdminMain(ScrollPane container, Window window, Admin admin, ArrayList<Cleaner> cleanersToConfirm) throws Exception {
        super(container, 800, 600);
        System.out.println("AdminMain constructor");

        window.setTitle("Accueil d'Admin");

        Label welcomeMessageLabel = new Label("Bienvenue " + admin.getSurname());
        Label confirmCleanerLabel = new Label("Cleaners en attente de confirmation :");

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

            Menu display = new Menu("Nom :" + currentCleaner.getSurname() + " " + currentCleaner.getName()
                    + "\nDate de naissance : " + currentCleaner.getBirthDate()
                    + "\nExpérience : " + CleanerExperience.asString(currentCleaner.getExperience())
                    + "\nMotivation :  " + currentCleaner.getMotivation());
            cleanerBlockContainer.getItems().addAll(display, acceptContainer, blockContainer);
            menuButton.getItems().add(cleanerBlockContainer);
        }

        Label displayDispute = new Label("Litiges à gérer");

        ListView<String> listViewSuspend = new ListView<>();

        listViewSuspend.getItems().add("Cleaner 1");
        listViewSuspend.getItems().add("Cleaner 2");
        listViewSuspend.getItems().add("Cleaner 3");

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
        vbox.getChildren().add(displayDispute);
        vbox.getChildren().add(listViewSuspend);

        listViewSuspend.setMaxHeight(100);
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
}
