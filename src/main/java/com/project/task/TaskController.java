package com.project.task;

import com.project.task.Model.*;
import com.project.task.Repository.ITaskRepository;
import com.project.task.Repository.TaskRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * @autho Gustavo Rosauro
 * o código foi feito em inglês pois se trata de um projeto internacional
 * soemente os comentários foram feitos em português para ajudar ao avaliador
 * Foi feito um método no JUnit para realizar o teste unitário validando se tem registros 
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TaskController {
	private ITaskRepository repository; 
	//Injeção de dependência
	TaskController(ITaskRepository repository){
		this.repository = repository;
	}
	//Retorna Listas de tarefas
	
@GetMapping("/list")
@Transactional
	public @ResponseBody List<TaskModel> listaTarefas() {
		return new TaskRepository(this.repository).list();
	}
//Recebe json no formato do objeto TaskModel e inseri na base
@PostMapping("/save")
@Transactional
	public @ResponseBody void Insert(@RequestBody TaskModel t) throws Exception {
	 new TaskRepository(this.repository).save(t);
}
// Altera o status para 2 que signicia concluido comentários
@PutMapping("/concluded/{id}")
@Transactional
	public @ResponseBody void change(@PathVariable long id, @RequestBody TaskModel t){
	new TaskRepository(this.repository).concluded(id, t);
}
//Altera o status para 3 como removido foi optado por fazer a exclusão lógica
@DeleteMapping("/delete/{id}")
@Transactional
	public @ResponseBody void delete(@PathVariable long id) {
	new TaskRepository(this.repository).delete(id);
}
//Edita conteudo da tarefa
@PutMapping("edit/{id}")
@Transactional
	public @ResponseBody void edit(@PathVariable long id,@RequestBody TaskModel t) {
	new TaskRepository(this.repository).edit(t, id);
}
}
