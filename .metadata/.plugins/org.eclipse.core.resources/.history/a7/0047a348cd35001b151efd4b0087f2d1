package view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane anPane;

    @FXML
    private JFXTextField tfFname;

    @FXML
    private JFXPasswordField tfPass;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnLogin;

    Message mgs = new Message();

    @FXML
    void createLogin(ActionEvent event) {
//    	System.out.println("로그인 페이지로");
    	if(tfFname.getText().equals("")){
    		mgs.setMessage("이름 미입력!");
    	}
    }

    @FXML
    void createregister(ActionEvent event) throws IOException {
//    	System.out.println("가입 페이지로");
    	btnRegister.getScene().getWindow().hide(); //현재 페이지를 안보이게 닫기

    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/SignUP.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
    }
}
