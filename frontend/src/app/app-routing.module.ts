import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HomeComponent} from './Components/home/home.component';
import {RegistrationComponent} from './Components/registration/registration.component';
import {LogInComponent} from './Components/log-in/log-in.component';
import {NewJournalComponent} from './Components/new-journal/new-journal.component';
import {AllJournalsComponent} from './Components/all-journals/all-journals.component';

const appRoutes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'register', component : RegistrationComponent},
    {path: 'login', component: LogInComponent},
  {path: 'journal/new', component: NewJournalComponent},
  {path: 'journal/all', component: AllJournalsComponent}

];

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(appRoutes, { useHash: true }), FormsModule],
    exports: [RouterModule]
})
export class AppRoutingModule { }
