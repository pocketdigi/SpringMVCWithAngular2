import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Headers, RequestOptions, Http, Response, Request, RequestMethod} from "@angular/http";
import {Result} from "./result";
import {User} from "../user/user";
import {LogService} from "./log.service";

@Injectable()
export class ApiService {

  constructor(private _http:Http,private log:LogService) { }

  /**
   * get方法
   * @param url
   */
  get<T>(url:string):Observable<Result<T>> {
    let options=new RequestOptions({method:RequestMethod.Get});
    return this.sendRequest(url,options);
  }

  /**
   * post
   * @param url
   * @param body 如果不是RESTFul新增，body可为空
   * @returns {Observable<R>}
   */
  post<T>(url:string,body:any):Observable<Result<T>>  {
    let options:RequestOptions;
    if(body!=null) {
      let postBody=JSON.stringify(body);
      let headers=new Headers({'Content-type':'application/json'});
      options=new RequestOptions({headers:headers,body:postBody,method:RequestMethod.Post});
    }else{
      let headers=new Headers({'Content-type':'application/x-www-form-urlencoded'});
      options=new RequestOptions({headers:headers,method:RequestMethod.Post});
    }
    return this.sendRequest(url,options);
  }

  delete(url:string):Observable<Result<Boolean>> {
    let options=new RequestOptions({method:RequestMethod.Delete});
    return this.sendRequest(url,options);
  }

  put(url:string,body:any) {
    let postBody=JSON.stringify(body);
    let headers=new Headers({'Content-type':'application/json'});
    let options=new RequestOptions({headers:headers,body:postBody,method:RequestMethod.Put});
    return this.sendRequest(url,options);
  }

  private sendRequest<T>(url:string,options:RequestOptions):Observable<Result<T>>{
    return this._http.request(url,options).map(this.extractData.bind(this)).catch(this.handleError.bind(this));
  }

  private extractData<T>(res:Response):Result<T> {
    let body = res.json();
    this.log.debug(JSON.stringify(body));
    return body as Result<T> || null;
  }

  private handleError(error:any) {
    this.log.error(JSON.stringify(error));
    return Observable.throw(error.message);
  }

}
