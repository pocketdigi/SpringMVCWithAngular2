import {Component, OnInit, AfterViewInit} from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers:[UserService]
})
export class UserListComponent implements OnInit,AfterViewInit {
  userList:User[];
  ngAfterViewInit(): void {
    this.userService.getUserList().subscribe(result=>
      (result.code==0?this.loadData(result.data):this.showError(result.msg)));
  }

  constructor(private userService:UserService) { }

  ngOnInit() {
    this.userList=[];
  }

  loadData(data:User[]) {
    data.forEach(user=>this.userList.push(user));
  }

  _trackByFn(index:number,user:User) {
    return user.id;
  }

  delete(id:string) {
    this.userService.deleteUser(id).subscribe(result=>(location.reload()));
  }
  showError(msg:string) {
    alert(msg);
  }
}
