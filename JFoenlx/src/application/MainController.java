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

	// ��ư�� �������� ( 3�� �μ�Ʈ, ������Ʈ, ������Ʈ )
	@FXML
	public void handelButton(ActionEvent event) {
		//System.out.println("��ư�� ������!");
		//showBooks();
		if(event.getSource() == btnInsert) { //btnInsert ��ư�� ��������!!!
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

	//DB �����ؼ� ��� �����͸� �������� �޼ҵ带 �����.
	//0. ����Ŭ JDBC ����̹� �����н��� �߰�
	//1. DB���� �޼ҵ� => Ŀ�ؼ��� ����
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

	// 2. books���̺��� ��� ������ �޾ƿ´�. �̶� ���̺�信 �Է��ϱ� ���ؼ� observablelist�� ���
	public ObservableList<Books> getBooksList(){
		//fx���� ���̺�信 ǥ���ϱ� ���� ����Ʈ�� ObservableList ���
		ObservableList<Books> bookList = FXCollections.observableArrayList();
		//sql �ۼ�
		String sql = "SELECT * FROM books ORDER BY id";
		Connection conn = getConnection(); //������ ���� DB���� �޼ҵ�
		Statement stmt;   //DB�� ���� ���� ��ü ����
		ResultSet rs;     //DB���� �޾ƿ��� �����ü ����

		try {
			stmt = conn.createStatement(); //���� ��ü ����
			rs = stmt.executeQuery(sql); // ���ӵ� DB���� ������ �����ϰ� ����� ����
			//����� �� �྿ �о bookList���� �Է�
			Books book;
			while(rs.next()) {
				book = new Books(rs.getInt("id"), rs.getString("title"), rs.getString("author")
						, rs.getInt("year"), rs.getInt("pages"));
				bookList.add(book); //�ϸ���Ʈ�� �ϳ��� book��ü�� �Է��Ѵ�.
			}
		} catch (Exception e) {
			System.out.println("DB���� sql���� ����Ұ�: "+e);
		}

		return bookList;
	}

	public void showBooks() {
		ObservableList<Books> list = getBooksList();
		// ���̺�信 ����Ʈ�� �ְ�
		tvBooks.setItems(list);
		// ������ ���� �����͸� �ҷ����� �ڵ带 �ۼ�
		colId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
		colTitle.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
		colYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
		colPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// ���α׷� ����� �Ʒ� ������ �����
		showBooks();
		// ���콺�� ���̺��� Ŭ���� �� ���� ������ �ٸ��� �̺�Ʈ�� �߻��Ѵ�.
		tvBooks.getSelectionModel().selectedItemProperty().addListener(
				//���̺���� ������ ���� �ϳ��� book��ü�̰� �ٸ� ���� ����(���콺Ŭ����)
				//���� ��� �ٸ���� �̺�Ʈ �߻��ϰ� showBookDetails�� �����Ѵ�.
				(obs,oldValue,newValue) -> showBookDetails(newValue)
				);
	}

	private void showBookDetails(Books book) {
		if(book != null) {
			//�ΰ��� �ƴҶ�
			tfId.setText(Integer.toString(book.getId()));
			tfTitle.setText(book.getTitle());
			tfAuthor.setText(book.getAuthor());
			tfYear.setText(Integer.toString(book.getYear()));
			tfPages.setText(Integer.toString(book.getPages()));

		} else {
			//�ΰ��϶� => ��� tf�� ������ �����.
			tfId.setText("");
			tfTitle.setText("");
			tfAuthor.setText("");
			tfYear.setText("");
			tfPages.setText("");
		}

	}

	// DB�� ���� �Է�
	private void insertRow() {
		String sql = "INSERT INTO books VALUES(?,?,?,?,?)";

		Connection conn = getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(tfId.getText()));    //id ����
			pstmt.setString(2, tfTitle.getText()); 					//title
			pstmt.setString(3, tfAuthor.getText()); 				//author
			pstmt.setInt(4, Integer.parseInt(tfYear.getText()) );   //year
			pstmt.setInt(5, Integer.parseInt(tfPages.getText()) );   //pages
			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => �μ�Ʈ ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("�μ�Ʈ �����߻�!");
		}
		tfId.setText("");
		tfTitle.setText("");
		tfAuthor.setText("");
		tfYear.setText("");
		tfPages.setText("");
	}

	// DB�� ���� �����Ѵ�.
	private void updateRow() {
		String sql = "UPDATE books SET title=?, author=?, year=?, pages=? WHERE id=?";

		Connection conn = getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tfTitle.getText()); 					//title
			pstmt.setString(2, tfAuthor.getText()); 				//author
			pstmt.setInt(3, Integer.parseInt(tfYear.getText()) );   //year
			pstmt.setInt(4, Integer.parseInt(tfPages.getText()) );   //pages
			pstmt.setInt(5, Integer.parseInt(tfId.getText()) );     //���̵�
			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => ������Ʈ ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("������Ʈ�� �����߻�!");
		}
	}

	private void deleteRow() {
		String sql = "DELETE FROM books WHERE id=?";
		Connection conn = getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(tfId.getText()) );     //���̵�
			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => ���� ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("������ �����߻�!");
		}
	}


}
































