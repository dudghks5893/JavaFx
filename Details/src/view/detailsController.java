package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.stage.Stage;
import model.Review;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class detailsController implements Initializable{
    @FXML
    private TableView<Review> tvReview;
    @FXML
    private TableColumn<Review, String> coltext;
    @FXML
    private TableColumn<Review, String> colscore;
	@FXML
	private Button writing;
	@FXML
	private Button goback;

	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "scott";
	private final String PASSWORD = "1234";

	@FXML
	public void GoReview(ActionEvent event) {
		writing.getScene().getWindow().hide(); //���� �������� �Ⱥ��̰� �ݱ�

    	Stage review = new Stage();
		try {
		Parent root = FXMLLoader.load(getClass().getResource("../view/review.fxml"));
		Scene scene = new Scene(root);
		review.setScene(scene);
		review.show();
		} catch (IOException e) {
			System.out.println("���侲������ ���� �߻�"+e.getMessage());
		}
	}
	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("DB���� �����߻�!");
			return null; //�����߻��� ���ϰ��� ��
		}
	}
	public ObservableList<Review> getReviewList(){
		//fx���� ���̺�信 ǥ���ϱ� ���� ����Ʈ�� ObservableList ���
		ObservableList<Review> ReviewList = FXCollections.observableArrayList();
		//sql �ۼ�
		String sql = "SELECT * FROM review";
		Connection conn = getConnection(); //������ ���� DB���� �޼ҵ�
		Statement stmt;   //DB�� ���� ���� ��ü ����
		ResultSet rs;     //DB���� �޾ƿ���
		 try {
			stmt = conn.createStatement(); //���� ��ü ����
			rs = stmt.executeQuery(sql); // ���ӵ� DB���� ������ �����ϰ� ����� ����
			Review review;
			while(rs.next()){
				review = new Review(rs.getString("text"),rs.getString("maskid"));
				ReviewList.add(review);
			}
		} catch (SQLException e) {
			System.out.println("DB���� sql���� ����Ұ�:"+e.getMessage());
		}
		 return ReviewList;
	}
	public void showReview() {
		ObservableList<Review> list = getReviewList();
		// ���̺�信 ����Ʈ�� �ְ�
		tvReview.setItems(list);
		// ������ ���� �����͸� �ҷ����� �ڵ带 �ۼ�
		coltext.setCellValueFactory(new PropertyValueFactory<Review, String>("text"));
		colscore.setCellValueFactory(new PropertyValueFactory<Review, String>("maskid"));
	}
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// ���α׷� ����� �Ʒ� ������ �����
		showReview();
//		// ���콺�� ���̺��� Ŭ���� �� ���� ������ �ٸ��� �̺�Ʈ�� �߻��Ѵ�.
//		tvReview.getSelectionModel().selectedItemProperty().addListener(
//				//���̺���� ������ ���� �ϳ��� book��ü�̰� �ٸ� ���� ����(���콺Ŭ����)
//				//���� ��� �ٸ���� �̺�Ʈ �߻��ϰ� showBookDetails�� �����Ѵ�.
//				(obs,oldValue,newValue) -> showReviewDetails(newValue)
//				);
	}
//	private void showReviewDetails(Review review) {
//		if(review != null) {
//			//�ΰ��� �ƴҶ�
//			tfreview.setText(review.getTitle());
//			tfAuthor.setText(review.getAuthor());
//
//		} else {
//			//�ΰ��϶� => ��� tf�� ������ �����.
//			tfId.setText("");
//			tfTitle.setText("");
//			tfAuthor.setText("");
//			tfYear.setText("");
//			tfPages.setText("");
//		}
//
//	}
}
