package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


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
    void handleBtn(ActionEvent event) {
    	//���̵� myid �̰� �н����尡 mypass ��
    	if(id.getText().equals("dudghks") && ps.getText().equals("1234")){
    		cant.setText("�α��� ����!");
    	}
    	else {
    		cant.setText("�α��� ����!");
    	}

    }

	}