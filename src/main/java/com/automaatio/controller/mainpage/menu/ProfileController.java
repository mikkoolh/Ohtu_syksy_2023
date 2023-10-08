package com.automaatio.controller.mainpage.menu;

import com.automaatio.model.ElectricityPriceConnector;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ProfileController implements Initializable, Menu{
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private final Pane mainPane;

    private final String username = cache.getUser().getUsername();

    private final String name = cache.getUser().getFirstName();

    @FXML
    Text usernameTXT, nameTXT, electricityPrice;

    @FXML
    Button picBtn;

    @FXML
    ImageView profileView;

    private ElectricityPriceConnector elConnect;

    private int selectedPic = cache.getUser().getSelectedPicture();

    public ProfileController() {
        mainPane = cache.getMainPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
        loadProfilePic();
    }

    private void loadProfilePic() {
        String imagePath;

        switch (selectedPic) {
            case 1:
                imagePath = "/images/male.jpg";
                break;
            case 2:
                imagePath = "/images/female.jpg";
                break;
            case 3:
                imagePath = "/images/anime.jpg";
                break;
            case 4:
                imagePath = "/images/hacker.jpg";
                break;
            case 5:
                imagePath = "/images/detective.jpg";
                break;
            case 6:
                imagePath = "/images/profilepic.jpg";
                break;
            default:
                imagePath = "/images/profilepic.jpg";
                break;
        }

        Image image = new Image(getClass().getResourceAsStream(imagePath));
        profileView.setImage(image);
    }

    @Override
    public void show() {
        usernameTXT.setText(username);
        nameTXT.setText("Tervetuloa " + name + "!");
        loadProfilePic();

        try{
            elConnect = new ElectricityPriceConnector();
            electricityPrice.setText("Sähkön hinta nyt: " + elConnect.getElPrice());
        } catch (Exception e) {
            System.out.println("Ongelma sähkönhinnan lataamisessa: " + e);
        }

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

    @FXML
    protected void onBtnClick(ActionEvent event) throws IOException {
        System.out.println("Pic selection window");

        Stage stage = new Stage();

        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        ImageView male = createImageView("images/male.jpg", "Male");
        ImageView female = createImageView("images/female.jpg", "Female");
        ImageView anime = createImageView("images/anime.jpg", "Anime");
        ImageView hacker = createImageView("images/hacker.jpg", "Hacker");
        ImageView detective = createImageView("images/detective.jpg", "Detective");
        ImageView defaultpic = createImageView("images/profilepic.jpg", "Default");

        Button save = new Button("Save");
        Button exit = new Button("Exit");
        save.setPrefWidth(120);
        save.setPrefHeight(40);
        exit.setPrefWidth(120);
        exit.setPrefHeight(40);

        root.setStyle("-fx-background-color: #575656;");
        anime.getStyleClass().addAll("image-border");
        hacker.getStyleClass().addAll("image-border");
        detective.getStyleClass().addAll("image-border");
        defaultpic.getStyleClass().addAll("image-border");

        save.setOnAction(saveClick -> {
            if (selectedPic != -1) {
                User user = cache.getUser();
                UserDAO userDAO = new UserDAO();
                userDAO.updatePicture(username, selectedPic);
                show();
                stage.close();
            } else {
                System.out.println("No image selected");
            }
        });
        exit.setOnAction(exitClick -> {
            System.out.println("Closing without changes");
            stage.close();
        });

        root.add(male, 0, 0);
        root.add(female, 1, 0);
        root.add(anime, 2, 0);
        root.add(hacker, 0, 1);
        root.add(detective, 1, 1);
        root.add(defaultpic, 2, 1);
        root.add(save, 0, 2);
        root.add(exit, 2, 2);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Profile Picture");
        stage.show();
    }

    private ImageView createImageView(String imageUrl, String caption) {
        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        imageView.getStyleClass().addAll("image-border");

        imageView.setOnMouseClicked(event -> {
            selectedPic = getSelectedPicFromCaption(caption);
        });

        return imageView;
    }

    private int getSelectedPicFromCaption(String caption) {
        switch (caption) {
            case "Male":
                return 1;
            case "Female":
                return 2;
            case "Anime":
                return 3;
            case "Hacker":
                return 4;
            case "Detective":
                return 5;
            case "Default":
                return 6;
            default:
                return -1;
        }
    }
}
