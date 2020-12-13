package view;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DBConnect;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
public class reviewController {
	@FXML
	private AnchorPane AP;
	@FXML
	private VBox VB2;
	@FXML
	private HBox VB2_HB1;
	@FXML
	private Label VB2_HB1_La;
	@FXML
	private ImageView VB2_HB1_img1;
	@FXML
	private ImageView VB2_HB1_img2;
	@FXML
	private ImageView VB2_HB1_img3;
	@FXML
	private ImageView VB2_HB1_img4;
	@FXML
	private ImageView VB2_HB1_img5;
	@FXML
    private Label VB2_HB1_Score;
	@FXML
	private HBox VB2_HB2;
	@FXML
	private Label VB2_HB2_La;
	@FXML
	private ImageView VB2_HB2_img1;
	@FXML
	private ImageView VB2_HB2_img2;
	@FXML
	private ImageView VB2_HB2_img3;
	@FXML
	private ImageView VB2_HB2_img4;
	@FXML
	private ImageView VB2_HB2_img5;
	@FXML
    private Label VB2_HB2_Score;
	@FXML
	private HBox VB2_HB3;
	@FXML
	private Label VB2_HB3_La;
	@FXML
	private ImageView VB2_HB3_img1;
	@FXML
	private ImageView VB2_HB3_img2;
	@FXML
	private ImageView VB2_HB3_img3;
	@FXML
	private ImageView VB2_HB3_img4;
	@FXML
	private ImageView VB2_HB3_img5;
	@FXML
    private Label VB2_HB3_Score;
	@FXML
	private HBox VB2_HB4;
	@FXML
	private Label VB2_HB4_La;
	@FXML
	private ImageView VB2_HB4_img1;
	@FXML
	private ImageView VB2_HB4_img2;
	@FXML
	private ImageView VB2_HB4_img3;
	@FXML
	private ImageView VB2_HB4_img4;
	@FXML
	private ImageView VB2_HB4_img5;
	@FXML
    private Label VB2_HB4_Score;
	@FXML
	private VBox VB1;
	@FXML
	private Label VB1_La;
	@FXML
	private VBox VB3;
	@FXML
	private HBox VB3_HB1;
	@FXML
	private Button VB3_HB1_but1;
	@FXML
	private Button VB3_HB1_but2;
	@FXML
	private Text VB3_text;
    @FXML
    private TextArea TA;

    private PreparedStatement ps;

    DBConnect conOBJ = new DBConnect();
    Connection con;

//���콺Ŭ���׼� 1���� ����
    @FXML
    void imgclick1(MouseEvent event){
		//��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		//����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB1_img1);
		img.add(VB2_HB1_img2);
		img.add(VB2_HB1_img3);
		img.add(VB2_HB1_img4);
		img.add(VB2_HB1_img5);
		insertimage(event, img, VB2_HB1_Score);
    }
//���콺Ŭ���׼� 1���� ����
    @FXML
    void imgclick2(MouseEvent event){
		//��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		//����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB2_img1);
		img.add(VB2_HB2_img2);
		img.add(VB2_HB2_img3);
		img.add(VB2_HB2_img4);
		img.add(VB2_HB2_img5);
		insertimage(event, img, VB2_HB2_Score);
    }
//���콺Ŭ���׼� 1���� ����
    @FXML
    void imgclick3(MouseEvent event){
		//��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		//����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB3_img1);
		img.add(VB2_HB3_img2);
		img.add(VB2_HB3_img3);
		img.add(VB2_HB3_img4);
		img.add(VB2_HB3_img5);
		insertimage(event, img, VB2_HB3_Score);
    }
//���콺Ŭ���׼� 1���� ����
    @FXML
    void imgclick4(MouseEvent event){
		//��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		//����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB4_img1);
		img.add(VB2_HB4_img2);
		img.add(VB2_HB4_img3);
		img.add(VB2_HB4_img4);
		img.add(VB2_HB4_img5);
		insertimage(event, img, VB2_HB4_Score);
    }
    @FXML
    void GoBack(ActionEvent event) {
    	VB3_HB1_but1.getScene().getWindow().hide(); //���� �������� �Ⱥ��̰� �ݱ�

    	Stage details = new Stage();
		try {
		Parent root = FXMLLoader.load(getClass().getResource("../view/details.fxml"));
		Scene scene = new Scene(root);
		details.setScene(scene);
		details.show();
		} catch (IOException e) {
			System.out.println("�ڷΰ��� ���� �߻�"+e.getMessage());
		}

    }
    @FXML
    void Completion(ActionEvent event) throws SQLException {
    	 con = conOBJ.getConnection();

         String insert = "INSERT INTO review(text,maskid)"
               +"Values (?,?)";
         ps = con.prepareStatement(insert);

         ps.setString(1, TA.getText());
         ps.setString(2, VB2_HB1_Score.getText());

         ps.executeUpdate();

         VB3_HB1_but2.getScene().getWindow().hide(); //���� �������� �Ⱥ��̰� �ݱ�

      	Stage details = new Stage();
  		try {
  		Parent root = FXMLLoader.load(getClass().getResource("../view/details.fxml"));
  		Scene scene = new Scene(root);
  		details.setScene(scene);
  		details.show();
  		} catch (IOException e) {
  			System.out.println("�Ϸ��ư ���� �߻�"+e.getMessage());
  		}
    }

//���� reviewControllerŬ���� �ȼ���
//�ּҰ� ���ڿ��� �Է�
    String imgAddress = "file:/E:/JavaFxGit/Details/src/img/Mask.png";
	String imgAddress2 = "file:/E:/JavaFxGit/Details/src/img/Mask2.png";
//�̹��� ��ü�� ����µ� imgAddress,imgAddress2������ ���� �̰� image,image2 ������ ����
		Image image = new Image(imgAddress);
		Image image2 = new Image(imgAddress2);
//�̹��� �ֱ� �޼ҵ� ����
public void insertimage(MouseEvent event,
    						ArrayList<ImageView> img, Label score) {
    	// �̹������ Ŭ���� �ش� ���̵� �ҷ���
    	String value = ((ImageView)event.getSource()).getId();
    	//Ŭ���� �̹������ ���̵��� 11��° �ε����� ������ tempstr�� ����
    	char tempstr = value.charAt(11);
    	//�������� ���������� �ٲ� (�̰� �ٲٴ� �޼ҵ� Character.getNumericValue)
    	int Selectimg = Character.getNumericValue(tempstr);
    	//���� ��ȯ
    	score.setText(String.valueOf(Selectimg));
    	//�̹����� Ŭ���� ����Ʈ�� ��� ���̵� ���������� ���� (�ʱ�ȭ)
    for(int i=0; i < 5; i++){
        img.get(i).setImage(image2);
    	}
    	//�̹����� Ŭ���� �ش� ���̵� �Է��� �Ǳ� ������ Selectimg�� ��� ���ڰ� �ٲ� (����������� ����)
    for(int i=0; i < Selectimg; i++){
    	   img.get(i).setImage(image);
    		}
    }

}

//=================�޸��� ����========================

//�ʱⰪ ���� (�޼ҵ� �ȿ� ������ ��� true���̿��� ������ ������)
//boolean check = true;
//
////���콺Ŭ���׼� 1���� ����
//@FXML
//void imgclick2(MouseEvent event){
//		//�̹��� �ּҸ� ���ڿ��� ����
////	String imgAddress = "D:\\gitProject\\Details\\src\\img\\Mask2.png";
//		//���ϰ�ü�� ����µ� imgAddress�� �ּҷ� ����
////	File file = new File(imgAddress);
//		//���ϰ�ü �ȿ��ִ� �ּ� ������ ���ڿ��� ��ȯ
////		imgAddress = file.toURI().toURL().toExternalForm();
//		//�ּҰ� ���
////		System.out.println(tmpAddress);
//
//		// ���콺 Ŭ���׼ǽ� Ŭ���� ���̵� �ҷ���
//	String value = ((ImageView)event.getSource()).getId();
		// Ŭ���� �̹������ ���̵� ��µ�
//	System.out.println(value);
//		//������ ������ �����̹����ּҰ��� ���ڿ��� ����
//	String imgAddress = "file:/D:/gitProject/Details/src/img/Mask.png";
//	String imgAddress2 = "file:/D:/gitProject/Details/src/img/Mask2.png";
//		//�̹��� ��ü�� ����µ� imgAddress������ ���� �̰� image ������ ����
//	Image image = new Image(imgAddress);
//	Image image2 = new Image(imgAddress2);
//
//		//��̸���Ʈ ����
//	ArrayList<ImageView> img = new ArrayList<ImageView>();
//		//����Ʈ�� �̹������ ���̵� ���
//	img.add(VB2_HB2_img1);
//	img.add(VB2_HB2_img2);
//	img.add(VB2_HB2_img3);
//	img.add(VB2_HB2_img4);
//	img.add(VB2_HB2_img5);
//
//
//���������� ���ڿ��� 11��° �ִ� ���ڸ� ��������.charAt(11��° �ε����� ��������) (VB2_HB2_img5)
//	char tempstr = value.charAt(11);
//		//�������� ���������� �ٲ� (�̰� �ٲٴ� �޼ҵ� Character.getNumericValue)
//	int Selectimg = Character.getNumericValue(tempstr);
//	if(check == true){
		//�̹����� Ŭ���� ����Ʈ�� ��� ���̵� ���������� ���� (�ʱ�ȭ)
//	for(int i=0; i < 5; i++){
//	    img.get(i).setImage(image2);
//		}
//		// �̹����� Ŭ���� �ش� ���̵� �Է��� �Ǳ� ������ Selectimg�� ��� ���ڰ� �ٲ� (����̹����� ����)
//	for(int i=0; i < Selectimg; i++){
//		   img.get(i).setImage(image);
//		}
//	}
//
//}

// ========================�ݺ��� ���� ��=============================

	//�ʱⰪ ���� (�޼ҵ� �ȿ� ������ ��� true���̿��� ������ ������)
//boolean check = true;
	//���콺 Ŭ�� �׼��� �̹������ ���̵� �ϳ� �ϳ��� �ٸ��� �������� ���
//@FXML
//void imgclick1(MouseEvent event) {
//
//	if(check == true){
			//�ּҸ� ���ڿ��� ����
//		String imgAddress = "D:\\gitProject\\Details\\src\\img\\Mask.png";
			//������ �ʱ�ȭ
//		String tmpAddress = null;
			//���ϰ�ü�� ����µ� imgAddress�� �ּҷ� ���� �̰� file�̶�� ������ ����
//		File file = new File(imgAddress);
//
//		try {
				//���ϰ�ü�ȿ� �ִ�  �ּ������� �ٽ� ���ڿ��� ��ȯ
//			tmpAddress = file.toURI().toURL().toExternalForm();
				//�̹�������ü �����ϴµ� tmpAddress�ּҷ� ���� �̰� image��� ������ ����
//			Image image = new Image(tmpAddress);
				// �ش� �̹������ ���̵� �̹����ּҸ� ���
//			VB2_HB1_img1.setImage(image);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}
//	else{
//		String imgAddress = "D:\\gitProject\\Details\\src\\img\\Mask2.png";
//		String tmpAddress = null;
//		File file = new File(imgAddress);
//
//		try {
//			tmpAddress = file.toURI().toURL().toExternalForm();
//			Image image = new Image(tmpAddress);
//			VB2_HB1_img1.setImage(image);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//	}
	//�޽��� ���������
//	check = !check;
// }
