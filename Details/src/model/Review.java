package model;

public class Review {
	private String text;
	private String maskid;
	public Review(String text, String maskid) {
		super();
		this.text = text;
		this.maskid = maskid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMaskid() {
		return maskid;
	}
	public void setMaskid(String maskid) {
		this.maskid = maskid;
	}

}
