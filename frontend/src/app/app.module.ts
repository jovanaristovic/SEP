import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {RegistrationComponent} from './Components/registration/registration.component';
import {LogInComponent} from './Components/log-in/log-in.component';
import {CanActivateService} from './Services/security/can-activate.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptorService} from './Services/security/token-interceptor';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {ToastrModule} from 'ng6-toastr-notifications';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule, Routes} from '@angular/router';
import {HttpModule} from '@angular/http';
import {HomeComponent} from './Components/home/home.component';
import {Notauthorized} from './Components/guard/notauthorized.guard';
import {NavbarComponent} from './Components/navbar/navbar.component';
import { NewJournalComponent } from './Components/new-journal/new-journal.component';
import { AllJournalsComponent } from './Components/all-journals/all-journals.component';
import { JournalProfileComponent } from './Components/journal-profile/journal-profile.component';
import { NewWorkComponent } from './Components/new-work/new-work.component';
import { AddSubscriptionPeriodComponent } from './Components/add-subscription-period/add-subscription-period.component';
import { AllPurchasesComponent } from './Components/all-purchases/all-purchases.component';


const Routes = [
  {
    path: 'registrate',
    component: RegistrationComponent,
    canActivate: [Notauthorized]
  }
];

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LogInComponent,
    HomeComponent,
    NavbarComponent,
    NewJournalComponent,
    AllJournalsComponent,
    JournalProfileComponent,
    NewWorkComponent,
    AddSubscriptionPeriodComponent,
    AllPurchasesComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule.forRoot(Routes),
    HttpClientModule,
    HttpModule


  ],
  providers: [CanActivateService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
