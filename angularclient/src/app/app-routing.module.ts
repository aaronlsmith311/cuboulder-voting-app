import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateQuestionsComponent } from './create-questions/create-questions.component';
import { ViewQuestionsComponent } from './view-questions/view-questions.component';
import { ResultsComponent } from './results/results.component';




const routes: Routes = [
{ path: 'login', component: LoginComponent },
{ path: 'dashboard', component: DashboardComponent },
{ path: 'view-questions', component: ViewQuestionsComponent },
{ path: 'create-questions', component: CreateQuestionsComponent },
{ path: 'results', component: ResultsComponent },
{ path: '', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
