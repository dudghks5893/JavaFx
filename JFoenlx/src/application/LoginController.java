package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class LoginController {


    @FXML
    private JFXButton log;

    @FXML
    private JFXPasswordField ps;

    @FXML
    private JFXTextField id;

    @FXML
    private Label cant;

    @FXML
    void handleBtn(ActionEvent event) throws IOException {
    	//아이디가 myid 이고 패스워드가 mypass 다
    	if(id.getText().equals("dudghks") && ps.getText().equals("1234")){
    		cant.setText("로그인 성공!");
    		Stage stage = (Stage)this.log.getScene().getWindow();
    		stage.close();
    		newPage();
    	}
    	else {
    		cant.setText("로그인 실패!");
    	}

    }

    public void newPage() throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("NewPage.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
    }

	}
