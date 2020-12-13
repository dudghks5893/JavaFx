package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class MainController implements Initializable {
	@FXML
	private TextField tfId;
	@FXML
	private TextField tfTitle;
	@FXML
	private TextField tfAuthor;
	@FXML
	private TextField tfYear;
	@FXML
	private TextField tfPages;
	@FXML
	private TableView<Books> tvBooks;
	@FXML
	private TableColumn<Books, Integer> colId;
	@FXML
	private TableColumn<Books, String> colTitle;
	@FXML
	private TableColumn<Books, String> colAuthor;
	@FXML
	private TableColumn<Books, Integer> colYear;
	@FXML
	private TableColumn<Books, Integer> colPages;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;

	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "scott";
	private final String PASSWORD = "1234";

	// 버튼을 눌렀을때 ( 3개 인서트, 업데이트, 딜레이트 )
	@FXML
	public void handelButton(ActionEvent event) {
		//System.out.println("버튼을 눌렀음!");
		//showBooks();
		if(event.getSource() == btnInsert) { //btnInsert 버튼을 눌렀을때!!!
			insertRow();
		}
		else if(event.getSource() == btnUpdate) {
			updateRow();
		}
		else if(event.getSource() == btnDelete) {
			deleteRow();
		}

		showBooks();
	}

	//DB 연결해서 모든 데이터를 가져오는 메소드를 만든다.
	//0. 오라클 JDBC 드라이버 빌드패스에 추가
	//1. DB연결 메소드 => 커넥션을 리턴
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

	// 2. books테이블의 모든 내용을 받아온다. 이때 테이블뷰에 입력하기 위해서 observablelist를 사용
	public ObservableList<Books> getBooksList(){
		//fx에서 테이블뷰에 표시하기 위한 리스트로 ObservableList 사용
		ObservableList<Books> bookList = FXCollections.observableArrayList();
		//sql 작성
		String sql = "SELECT * FROM books ORDER BY id";
		Connection conn = getConnection(); //위에서 만든 DB연결 메소드
		Statement stmt;   //DB에 보낼 쿼리 객체 선언
		ResultSet rs;     //DB에서 받아오는 결과객체 선언

		try {
			stmt = conn.createStatement(); //쿼리 객체 생성
			rs = stmt.executeQuery(sql); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
			//결과를 한 행씩 읽어서 bookList에서 입력
			Books book;
			while(rs.next()) {
				book = new Books(rs.getInt("id"), rs.getString("title"), rs.getString("author")
						, rs.getInt("year"), rs.getInt("pages"));
				bookList.add(book); //북리스트에 하나의 book객체를 입력한다.
			}
		} catch (Exception e) {
			System.out.println("DB에서 sql문을 실행불가: "+e);
		}

		return bookList;
	}

	public void showBooks() {
		ObservableList<Books> list = getBooksList();
		// 테이블뷰에 리스트를 넣고
		tvBooks.setItems(list);
		// 각각의 열에 데이터를 불러오는 코드를 작성
		colId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
		colTitle.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
		colYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
		colPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// 프로그램 실행시 아래 내용이 실행됨
		showBooks();
		// 마우스로 테이블셀을 클릭시 그 행의 내용이 다르면 이벤트를 발생한다.
		tvBooks.getSelectionModel().selectedItemProperty().addListener(
				//테이블안의 내용중 행이 하나의 book객체이고 다른 행을 선택(마우스클릭시)
				//그전 행과 다를경우 이벤트 발생하고 showBookDetails를 실행한다.
				(obs,oldValue,newValue) -> showBookDetails(newValue)
				);
	}

	private void showBookDetails(Books book) {
		if(book != null) {
			//널값이 아닐때
			tfId.setText(Integer.toString(book.getId()));
			tfTitle.setText(book.getTitle());
			tfAuthor.setText(book.getAuthor());
			tfYear.setText(Integer.toString(book.getYear()));
			tfPages.setText(Integer.toString(book.getPages()));

		} else {
			//널값일때 => 모든 tf를 내용을 지운다.
			tfId.setText("");
			tfTitle.setText("");
			tfAuthor.setText("");
			tfYear.setText("");
			tfPages.setText("");
		}

	}

	// DB에 한줄 입력
	private void insertRow() {
		String sql = "INSERT INTO books VALUES(?,?,?,?,?)";

		Connection conn = getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(tfId.getText()));    //id 숫자
			pstmt.setString(2, tfTitle.getText()); 					//title
			pstmt.setString(3, tfAuthor.getText()); 				//author
			pstmt.setInt(4, Integer.parseInt(tfYear.getText()) );   //year
			pstmt.setInt(5, Integer.parseInt(tfPages.getText()) );   //pages
			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 인서트 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("인서트 에러발생!");
		}
		tfId.setText("");
		tfTitle.setText("");
		tfAuthor.setText("");
		tfYear.setText("");
		tfPages.setText("");
	}

	// DB의 행을 수정한다.
	private void updateRow() {
		String sql = "UPDATE books SET title=?, author=?, year=?, pages=? WHERE id=?";

		Connection conn = getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tfTitle.getText()); 					//title
			pstmt.setString(2, tfAuthor.getText()); 				//author
			pstmt.setInt(3, Integer.parseInt(tfYear.getText()) );   //year
			pstmt.setInt(4, Integer.parseInt(tfPages.getText()) );   //pages
			pstmt.setInt(5, Integer.parseInt(tfId.getText()) );     //아이디
			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 업데이트 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("업데이트중 에러발생!");
		}
	}

	private void deleteRow() {
		String sql = "DELETE FROM books WHERE id=?";
		Connection conn = getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(tfId.getText()) );     //아이디
			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 삭제 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("삭제중 에러발생!");
		}
	}


}
































