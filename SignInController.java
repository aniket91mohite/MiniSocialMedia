package MainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import static MainWindow.SetupSocket.*;

public class SignInController {
    @FXML
    private GridPane signInWindow;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;

    @FXML
    public void onSignIn() throws Exception{

        setupSocket();

        Dout.write(1);

        Dout.writeUTF(userName.getText());
        Dout.writeUTF(passWord.getText());

        boolean permission = Din.readBoolean();

        if(permission == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Authentication Successful!!!");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Access Denied!!!");
            alert.showAndWait();
        }

        closeSocket();
    }

    @FXML
    public void onSignUp() throws Exception {
        Stage signUpStage = (Stage) signInWindow.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        signUpStage.setTitle("Create Account For Mini Social Media");
        Scene scene = new Scene(root, 640,480);
        signUpStage.setScene(scene);
        signUpStage.show();
    }
}
