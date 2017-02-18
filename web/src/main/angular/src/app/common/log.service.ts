import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";

@Injectable()
export class LogService {

  constructor() { }

  debug(msg:string){
      if(!environment.production) {
        console.log(msg);
      }
  }
  error(msg:string) {
    if(!environment.production) {
      console.error(msg);
    }
  }

}
