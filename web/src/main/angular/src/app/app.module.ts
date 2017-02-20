import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {UserListComponent} from './user/user-list/user-list.component';
import {UserDetailComponent} from './user/user-detail/user-detail.component';
import {UserService} from "./user/user.service";
import {ApiService} from "./common/api.service";
import {RxjsComponent} from './rxjs/rxjs.component';
import {UserEditComponent} from './user/user-edit/user-edit.component';
import {NumberBetweenValidatorDirective} from './common/validator/number-between-validator.directive';
import {MaterialModule} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {FileUploadModule} from "ng2-file-upload";
import {LogService} from "./common/log.service";


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserDetailComponent,
    RxjsComponent,
    UserEditComponent,
    NumberBetweenValidatorDirective,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MaterialModule,
    FlexLayoutModule,
    FileUploadModule,
  ],
  providers: [LogService, ApiService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
