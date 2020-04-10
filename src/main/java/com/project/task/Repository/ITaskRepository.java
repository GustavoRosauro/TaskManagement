package com.project.task.Repository;
import com.project.task.Model.*;

import org.springframework.data.jpa.repository.JpaRepository;


//Criando Jpa Repository
public interface ITaskRepository extends JpaRepository<TaskModel, Long>{
	
}
