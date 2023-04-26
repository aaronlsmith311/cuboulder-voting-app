import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-create-questions',
  templateUrl: './create-questions.component.html',
  styleUrls: ['./create-questions.component.css']
})
export class CreateQuestionsComponent implements OnInit {

  constructor(private router: Router, private service:ApiService) { }

question: string = "";

  ngOnInit() {
  }

  createQuestion() : void {
    if(this.question.length > 0 && this.question.length <= 200){
      this.service.createQuestions(this.question)
      .subscribe((response: any) => {
        alert("Question successfully created!");
        this.router.navigate(["dashboard"]);
      },
      (error) => {
        console.log(error);
        if (error.status === 400) {
          alert("Questions must be unique");
        } else {
          alert("An unexcepted error occured");
        }});
    } else {
      alert("Question must be between 1 and 200 characters");
    }
  }

}
