package MainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SignUpController {

    @FXML
    private GridPane signUpWindow;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField email_id;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField cpassword;
    @FXML
    private TextField birthday;
    @FXML
    private TextField contact_no;
    @FXML
    private RadioButton gender;
    @FXML
    private TextField about;

    private int userid;
    private LocalDate dateOfSignUp;

    private SetupSocket server;

    public void initialize() throws Exception{
        server = SetupSocket.getINSTANCE();
    }


    @FXML
    public void saveData() throws Exception{


        server.getDout().write(0);

        server.getDout().writeUTF(first_name.getText());
        server.getDout().writeUTF(last_name.getText());
        server.getDout().writeUTF(email_id.getText());
        server.getDout().writeUTF(contact_no.getText());
        server.getDout().writeUTF(username.getText());
        server.getDout().writeUTF(password.getText());
        server.getDout().writeUTF(cpassword.getText());
        server.getDout().writeUTF(birthday.getText());
        server.getDout().writeUTF(gender.getText());
        server.getDout().writeUTF(about.getText());

        dateOfSignUp = LocalDate.now();
        server.getDout().writeUTF(dateOfSignUp.toString());
        userid = Math.abs(username.hashCode());
        System.out.println(userid);
        server.getDout().writeInt(userid);

        if(server.getDin().readBoolean()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success!");
            alert.setContentText("Your account has been successfully created! You can Sign In!");
            alert.showAndWait();
            rollBackScene();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Failure!");
            alert.setContentText("Please Retry!");
            alert.showAndWait();
        }
    }

    @FXML
    public void rollBackScene() throws Exception{
        Stage stage = (Stage) signUpWindow.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(root, 640,480);
        stage.setTitle("MSM");
        stage.setScene(scene);
        stage.show();
    }


}
