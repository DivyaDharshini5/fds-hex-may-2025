package com.ecom.model;

public class Category {
public Category() {
	}
private int id;
@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + "]";
}
public Category(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
private String name;
}
