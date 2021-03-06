package application;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class applicationController {
	@FXML
	private Label lbRe;

	private double number1 = 0.0; // 숫자 1의 초기값
	private String operator = "";
	Model model = new Model();
	// Event Listener on Button.onAction
	@FXML
	public void numbers(ActionEvent event) {
		//  숫자를 눌렀을때
		String num = ((Button)event.getSource()).getText();
		lbRe.setText( lbRe.getText() + num );
	}

	@FXML
	public void oprater(ActionEvent event) {
		//  계산 오퍼레이터를 눌렀을때 + - / * = C
		String value = ((Button)event.getSource()).getText();

		if(!value.equals("=")){ // operator "=" 제외 하기 위한
			if(value.equals("C")){ // "C"를 눌렀을때 화면 리셋
				lbRe.setText("");
				return;
			}

			number1 = Double.parseDouble(lbRe.getText());//첫번째 숫자 저장
			operator = value; // operator 저장
			lbRe.setText(""); // 화면 리셋 시키기 위한
		}
		else { //"=" 오퍼레이터 계산
			double number2 = Double.parseDouble(lbRe.getText());

			double result = model.calculate(number1, number2, operator);
			lbRe.setText(String.valueOf(result));
			operator = "";
		}
	}

}
