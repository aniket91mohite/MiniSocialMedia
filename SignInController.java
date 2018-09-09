import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignInController {
    @FXML
    private GridPane signInWindow;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;

    private SetupSocket server;

    public void initialize() throws Exception{
        server = SetupSocket.getINSTANCE();
    }

    @FXML
    public void onSignIn() throws Exception{

        server.getDout().write(1);

        server.getDout().writeUTF(userName.getText());
        server.getDout().writeUTF(passWord.getText());

        boolean permission = server.getDin().readBoolean();

        if(permission == true){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Authentication Successful!!!");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Access Denied!!!");
            alert.showAndWait();
        }
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
