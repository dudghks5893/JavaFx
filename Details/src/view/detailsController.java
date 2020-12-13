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
		writing.getScene().getWindow().hide(); //현재 페이지를 안보이게 닫기

    	Stage review = new Stage();
		try {
		Parent root = FXMLLoader.load(getClass().getResource("../view/review.fxml"));
		Scene scene = new Scene(root);
		review.setScene(scene);
		review.show();
		} catch (IOException e) {
			System.out.println("리뷰쓰러가기 오류 발생"+e.getMessage());
		}
	}
	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("DB연결 문제발생!");
			return null; //에러발생시 리턴값은 널
		}
	}
	public ObservableList<Review> getReviewList(){
		//fx에서 테이블뷰에 표시하기 위한 리스트로 ObservableList 사용
		ObservableList<Review> ReviewList = FXCollections.observableArrayList();
		//sql 작성
		String sql = "SELECT * FROM review";
		Connection conn = getConnection(); //위에서 만든 DB연결 메소드
		Statement stmt;   //DB에 보낼 쿼리 객체 선언
		ResultSet rs;     //DB에서 받아오는
		 try {
			stmt = conn.createStatement(); //쿼리 객체 생성
			rs = stmt.executeQuery(sql); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
			Review review;
			while(rs.next()){
				review = new Review(rs.getString("text"),rs.getString("maskid"));
				ReviewList.add(review);
			}
		} catch (SQLException e) {
			System.out.println("DB에서 sql문을 실행불가:"+e.getMessage());
		}
		 return ReviewList;
	}
	public void showReview() {
		ObservableList<Review> list = getReviewList();
		// 테이블뷰에 리스트를 넣고
		tvReview.setItems(list);
		// 각각의 열에 데이터를 불러오는 코드를 작성
		coltext.setCellValueFactory(new PropertyValueFactory<Review, String>("text"));
		colscore.setCellValueFactory(new PropertyValueFactory<Review, String>("maskid"));
	}
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// 프로그램 실행시 아래 내용이 실행됨
		showReview();
//		// 마우스로 테이블셀을 클릭시 그 행의 내용이 다르면 이벤트를 발생한다.
//		tvReview.getSelectionModel().selectedItemProperty().addListener(
//				//테이블안의 내용중 행이 하나의 book객체이고 다른 행을 선택(마우스클릭시)
//				//그전 행과 다를경우 이벤트 발생하고 showBookDetails를 실행한다.
//				(obs,oldValue,newValue) -> showReviewDetails(newValue)
//				);
	}
//	private void showReviewDetails(Review review) {
//		if(review != null) {
//			//널값이 아닐때
//			tfreview.setText(review.getTitle());
//			tfAuthor.setText(review.getAuthor());
//
//		} else {
//			//널값일때 => 모든 tf를 내용을 지운다.
//			tfId.setText("");
//			tfTitle.setText("");
//			tfAuthor.setText("");
//			tfYear.setText("");
//			tfPages.setText("");
//		}
//
//	}
}
