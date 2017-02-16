import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Headers, RequestOptions, Http, Response} from "@angular/http";
import {Result} from "./result";
import {User} from "../user/user";

@Injectable()
export class ApiService {

  constructor(private _http:Http) { }

  /**
   * get方法
   * @param apiPath
   */
  get<T>(apiPath:string):Observable<Result<T>> {
    return this._http.get(apiPath).map(this.extractData).catch(this.handleError);
  }

  /**
   * post对象
   * @param apiPath
   * @param body
   * @returns {Observable<R>}
   */
  postBody<T>(apiPath:string,body:any):Observable<Result<T>>  {
    let postBody=JSON.stringify(body);
    let headers=new Headers({'Content-type':'application/json'});
    let options=new RequestOptions({headers:headers});
    return this._http.post(apiPath,postBody,options)
      .map(this.extractData).catch(this.handleError);
  }



  private extractData<T>(res:Response):Result<T> {
    let body = res.json();
    console.log(JSON.stringify(body));
    return body as Result<T> || null;
  }

  private handleError(error:any) {
    console.error(JSON.stringify(error));
    return Observable.throw(error.message);
  }

}
