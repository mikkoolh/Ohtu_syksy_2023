package com.automaatio.controller.mainpage.menu;

import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private Pane mainPane;

    private String username = cache.getUser().getUsername();

    private String name = cache.getUser().getFirstName();

    @FXML
    Text usernameTXT, nameTXT;

    public ProfileController() {
        mainPane = cache.getMainPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameTXT.setText(username);
        nameTXT.setText("Tervetuloa " + name + "!");
    }

    public void openProfile() {
       System.out.println("open profile");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user-profile.fxml"));
            Parent newView = loader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        // Siirry login-sivulle
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }


}
