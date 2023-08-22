package com.tech.blog.entities;

import java.sql.Timestamp;

public class Post {
	private int id;
	private String title;
	private String content;
	private String code;
	private String picture;
	private Timestamp date;
	private int category_id;

	public Post() {
		super();
	}

	public Post(String title, String content, String code, String picture, Timestamp date, int category_id) {
		super();
		this.title = title;
		this.content = content;
		this.code = code;
		this.picture = picture;
		this.date = date;
		this.category_id = category_id;
	}

	public Post(int id, String title, String content, String code, String picture, Timestamp date, int category_id) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.code = code;
		this.picture = picture;
		this.date = date;
		this.category_id = category_id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

}
