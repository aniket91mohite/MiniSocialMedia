package MainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

import static MainWindow.SetupSocket.*;

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


    @FXML
    public void saveData() throws Exception{

        setupSocket();

        Dout.write(0);

        Dout.writeUTF(first_name.getText());
        Dout.writeUTF(last_name.getText());
        Dout.writeUTF(email_id.getText());
        Dout.writeUTF(contact_no.getText());
        Dout.writeUTF(username.getText());
        Dout.writeUTF(password.getText());
        Dout.writeUTF(cpassword.getText());
        Dout.writeUTF(birthday.getText());
        Dout.writeUTF(gender.getText());
        Dout.writeUTF(about.getText());

        dateOfSignUp = LocalDate.now();
        Dout.writeUTF(dateOfSignUp.toString());
        userid = Math.abs(username.hashCode());
        System.out.println(userid);
        Dout.writeInt(userid);


        if(Din.readBoolean()) {
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

        closeSocket();
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
