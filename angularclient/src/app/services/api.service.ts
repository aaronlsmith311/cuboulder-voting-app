import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private questionsUrl = 'http://localhost:8080/questions';

  constructor(private httpClient: HttpClient) { }

  getQuestions(){
    return this.httpClient.get(this.questionsUrl);
  }
  
  createQuestions(value: string) {
    const headers = { 'content-type': 'application/json'} 
    const request = {appUserId: 1, questionDescription: value}; 
    const body=JSON.stringify(request);
    return this.httpClient.post(this.questionsUrl, body, {'headers':headers})
  }

  questionVote(id: number, vote: boolean) {
    const headers = { 'content-type': 'application/json'} 
    const request = {appUserId: 1, questionId: id, questionVote: vote}; 
    const body=JSON.stringify(request);
    return this.httpClient.put(this.questionsUrl + "/vote/" + id, body, {'headers':headers})
  }
}
