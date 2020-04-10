import { Component, OnInit } from '@angular/core';
import { TaskViewModel } from './Model/TaskModel';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(private http:HttpClient){}
  list:any = [];
  task:TaskViewModel;
  button:string = "Salvar";
  selectedTask:TaskViewModel;
  async ngOnInit(): Promise<void> {
     this.task = new TaskViewModel();
    await this.LoadTasks();
  } 
  //Carrega tarefas do java 
  async LoadTasks(){
      await this.http.get("http://localhost:8080/list").subscribe(result=>{
          this.list = result;
      },error => console.log(error))
  }
  //Chama o metodo responsável por inserir ou Alterar no java 
  async save(){
    if(this.task.id == null){
    await this.http.post("http://localhost:8080/save",this.task).subscribe(()=>{
      this.task = new TaskViewModel();
      this.LoadTasks();
    },error => console.log(error));
  }else{
    await this.http.put(`http://localhost:8080/edit/${this.task.id}`,this.task).subscribe(()=>{
      this.LoadTasks();
      this.clean();
    },error=> console.log(error))
  }
}
  //Qaundo soltar a linha remove a linha selecionada da base mudando seu status para 3
  drop() {
    this.remove(this.selectedTask);
  }
  allowDrop(ev) {
    ev.preventDefault();
  }

  remove(task:TaskViewModel){
    this.http.delete("http://localhost:8080/delete/"+task.id).subscribe(()=>{
      this.LoadTasks();
      this.clean();
    },error => console.log(error));
  }
  //Chama o método que conclui ou volta a tarefa 
  concluded(task:TaskViewModel){
    let mensagem = task.status == 1?"Deseja concluir essa tarefa":"Deseja Reabrir essa tarefa"
    if(confirm(mensagem)){
    task.status = task.status == 1?2:1;
    this.http.put("http://localhost:8080/concluded/"+task.id,task).subscribe(()=>{
      this.LoadTasks();
    },error => console.log(error))
  }
  this.LoadTasks();
    }
    //Quando segurar preenche o objeto com os dados do que foi segurado
    drag(task:TaskViewModel) {
      this.selectedTask = task;
    }
  clean(){
    this.button = "Salvar";
    this.task = new TaskViewModel();
  }
  edit(task:TaskViewModel){
    this.task = task;
    this.button = "Editar";
  }
}
