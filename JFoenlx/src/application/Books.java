package application;

public class Books {
	//필드변수 (테이블 열과 동일)
	private int id;
	private String title;
	private String author;
	private int year;
	private int pages;
	//생성자, get/set 메소드 자동생성
	public Books(int id, String title, String author, int year, int pages) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
}
