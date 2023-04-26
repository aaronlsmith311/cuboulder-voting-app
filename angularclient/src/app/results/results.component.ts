import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  questions:any;

  constructor(private service:ApiService) {};


  ngOnInit() {
      this.service.getQuestions()
        .subscribe(response => {
          this.questions = response;
        });

  }

}
