package application;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class applicationController {
	@FXML
	private Label lbRe;

	private double number1 = 0.0; // ���� 1�� �ʱⰪ
	private String operator = "";
	Model model = new Model();
	// Event Listener on Button.onAction
	@FXML
	public void numbers(ActionEvent event) {
		//  ���ڸ� ��������
		String num = ((Button)event.getSource()).getText();
		lbRe.setText( lbRe.getText() + num );
	}

	@FXML
	public void oprater(ActionEvent event) {
		//  ��� ���۷����͸� �������� + - / * = C
		String value = ((Button)event.getSource()).getText();

		if(!value.equals("=")){ // operator "=" ���� �ϱ� ����
			if(value.equals("C")){ // "C"�� �������� ȭ�� ����
				lbRe.setText("");
				return;
			}

			number1 = Double.parseDouble(lbRe.getText());//ù��° ���� ����
			operator = value; // operator ����
			lbRe.setText(""); // ȭ�� ���� ��Ű�� ����
		}
		else { //"=" ���۷����� ���
			double number2 = Double.parseDouble(lbRe.getText());

			double result = model.calculate(number1, number2, operator);
			lbRe.setText(String.valueOf(result));
			operator = "";
		}
	}

}