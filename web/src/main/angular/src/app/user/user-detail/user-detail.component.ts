import {Component, OnInit, AfterViewInit} from "@angular/core";
import {UserService} from "../user.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {User} from "../user";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit,AfterViewInit {

  user:User;
  constructor(private userService:UserService, private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {

  }


  ngAfterViewInit(): void {
    this.route.params.switchMap((params:Params)=> this.userService.getUser(params['id']))
      .subscribe(result=>this.user=result.data);
  }

  edit() {
    this.router.navigate(['user','edit',this.user.id]);
  }

}
