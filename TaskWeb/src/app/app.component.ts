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
  ngOnInit(): void {
  }
  title = 'TaskWeb';
  salvar(){
    let task = new TaskViewModel();
    task.description = "teste";
    task.status = 3;
    this.http.post("http://localhost:8080/save",task).subscribe(result=>{
      alert(result)
    },error => console.log(error));
  }
  remover(){
    this.http.delete("http://localhost:8080/delete/"+5).subscribe(()=>{
      alert("Sucesso!");
    },error => console.log(error));
  }
  concluido(){
    let task = new TaskViewModel();
    task.status = 2
    this.http.put("http://localhost:8080/concluded/"+4,task).subscribe(()=>{
      alert("Suceso!");
    },error => console.log(error))
    }
}
