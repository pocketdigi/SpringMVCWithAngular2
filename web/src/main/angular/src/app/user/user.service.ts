import { Injectable } from '@angular/core';
import {ApiService} from "../common/api.service";
import {Observable} from "rxjs";
import {User} from "./user";
import {Result} from "../common/result";

@Injectable()
export class UserService {

  constructor(private apiService:ApiService) { }

  /**
   * 获取用户列表
   * @returns {Observable<any[]>}
   */
  getUserList():Observable<Result<User[]>> {
    return this.apiService.get('/user/list');
  }

  getUser(id:string):Observable<Result<User>> {
    return this.apiService.get('/user/'+id);
  }

  addUser(user:User):Observable<Result<User>> {
    return this.apiService.post('/user',user);
  }

  deleteUser(id:string):Observable<Result<boolean>> {
    return this.apiService.delete('/user/'+id);
  }

  editUser(user:User):Observable<Result<User>> {
    return this.apiService.put('/user/'+user.id,user);
  }
}
