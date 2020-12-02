package view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SignUPController implements Initializable {

    @FXML
    private JFXTextField fullname;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXCheckBox check;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXButton login;

    @FXML
    void goLogin(ActionEvent event) {

    }

    @FXML
    void signUp(ActionEvent event) {
    	//DB로 입력 내용을 저장한다
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 초기 상태값을 설정
		fullname.setStyle("-fx-text-inner-color:#afbccd;");
		email.setStyle("-fx-text-inner-color:#afbccd;");
		password.setStyle("-fx-text-inner-color:#afbccd;");
	}

}