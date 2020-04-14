import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HomeComponent} from './Components/home/home.component';
import {RegistrationComponent} from './Components/registration/registration.component';
import {LogInComponent} from './Components/log-in/log-in.component';
import {NewJournalComponent} from './Components/new-journal/new-journal.component';
import {AllJournalsComponent} from './Components/all-journals/all-journals.component';
import {JournalProfileComponent} from './Components/journal-profile/journal-profile.component';
import {NewWorkComponent} from './Components/new-work/new-work.component';
import {AddSubscriptionPeriodComponent} from './Components/add-subscription-period/add-subscription-period.component';
import {AllPurchasesComponent} from './Components/all-purchases/all-purchases.component';

const appRoutes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'register', component : RegistrationComponent},
    {path: 'login', component: LogInComponent},
  {path: 'journal/new', component: NewJournalComponent},
  {path: 'journal/all', component: AllJournalsComponent},
  {path: 'journal/:id', component: JournalProfileComponent},
  {path: 'work/new/:id', component: NewWorkComponent},
  {path: 'subscription/period/:id', component: AddSubscriptionPeriodComponent},
  {path: 'purchase/all', component: AllPurchasesComponent}

];

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(appRoutes, { useHash: true }), FormsModule],
    exports: [RouterModule]
})
export class AppRoutingModule { }
