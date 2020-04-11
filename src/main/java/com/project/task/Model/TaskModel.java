package com.project.task.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Task")
@Where(clause = "status != 3 ")
//Objeto que será criado em nossa base de dados fazendo a conexão no application.properties
public class TaskModel {
@Id
@Column(name="Id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String description;
private int status;

public long getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
}
