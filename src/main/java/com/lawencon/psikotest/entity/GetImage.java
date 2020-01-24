package com.lawencon.psikotest.entity;

public class GetImage {
	
	private String fileName;
	private byte[] data;
	private String type;
	
	public GetImage() {
		super();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
