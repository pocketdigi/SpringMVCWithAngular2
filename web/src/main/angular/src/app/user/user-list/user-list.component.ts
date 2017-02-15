import {Component, OnInit, AfterViewInit} from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers:[UserService]
})
export class UserListComponent implements OnInit,AfterViewInit {
  userList:User[];
  ngAfterViewInit(): void {
    this.userService.getUserList().subscribe(result=>(this.loadData(result.data)));
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
}
