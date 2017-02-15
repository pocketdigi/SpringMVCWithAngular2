import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from "@angular/router";
import {UserService} from "../user.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {numberBetween} from "../../common/validator/number-between-validator.directive";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  userForm:FormGroup;
  constructor(private route:ActivatedRoute,private userService:UserService,private fb:FormBuilder) { }

  ngOnInit() {
    this.userForm=this.fb.group({
      'userName':['',Validators.required,Validators.minLength(4)],
      'userAge':['',Validators.required,Validators,numberBetween(1,100)],

    });
  }

}
