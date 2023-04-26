import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css']
})
export class ViewQuestionsComponent implements OnInit {
  questions:any;

  constructor(private service:ApiService) {};


  ngOnInit() {
      this.service.getQuestions()
        .subscribe(response => {
          this.questions = response;
          this.questions.sort(function(x: { questionId: number; }, y: { questionId: number; }){
            return y.questionId - x.questionId;
        })
        });

  }

  vote(id: number, vote: boolean) : void {
    console.log("Vote: " + vote);
      this.service.questionVote(id, vote)
      .subscribe((response: any) => {
        alert("Vote saved");
      },
      (error) => {
        console.log(error);
        if (error.status === 400) {
          alert("You can only vote on a question once");
        } else {
          alert("An unexcepted error occured");
        }
  
      });
  }
}
