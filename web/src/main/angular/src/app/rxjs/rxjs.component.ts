import {Component, OnInit, AfterViewInit} from '@angular/core';
import {Observable} from "rxjs";
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-rxjs',
  templateUrl: './rxjs.component.html',
  styleUrls: ['./rxjs.component.css']
})
export class RxjsComponent implements OnInit,AfterViewInit {
  ngAfterViewInit(): void {

    // const input=document.querySelector('input');
    //
    // Observable.fromEvent(input,'input')
    //   .map(e => e.target.value )

  }

  constructor() { }

  ngOnInit() {
  }

}
