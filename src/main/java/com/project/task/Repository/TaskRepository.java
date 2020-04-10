package com.project.task.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.hibernate.Session;
import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.hibernate.*;

import com.project.task.Model.TaskModel;

//Repositório aonde são feitas minhas consultas
public class TaskRepository{

	//repositorio enviado via injeção de depêndencia
	private ITaskRepository taskRespository;
	public TaskRepository(ITaskRepository taskRepository) {
		this.taskRespository = taskRepository;
		
	}
	//Salva na base
	public String save(TaskModel t) {
		try {
			this.taskRespository.save(t);
			return "Inserido com sucesso";
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	//Retorna Dados com a clausula where definida no objeto
	public List<TaskModel> list() {		
		return this.taskRespository.findAll();
	}
	//faz a exclusão lógica do registro 
	public void delete(long id) {
		TaskModel objeto =  this.taskRespository.findById(id).get();
	    objeto.setStatus(3);
	    this.taskRespository.save(objeto);
	}
    //Defini o status como sendo o status 2 conculuído 
	public void concluded(long id, TaskModel t) {
		TaskModel objeto =  this.taskRespository.findById(id).get();
	    objeto.setStatus(t.status);
	    this.taskRespository.save(objeto);		
	}

}
