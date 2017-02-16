import {Component, OnInit, AfterViewInit, ViewChild} from "@angular/core";
import {ActivatedRoute, Router, Params} from "@angular/router";
import {UserService} from "../user.service";
import {FormBuilder, NgForm} from "@angular/forms";
import {User} from "../user";
import {Result} from "../../common/result";
import {isUndefined} from "util";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit,AfterViewInit {
  //找到表单
  @ViewChild('userForm') userForm: NgForm;
  constructor(private route:ActivatedRoute,private userService:UserService,private fb:FormBuilder,private router:Router) { }
  user:User;
  ngOnInit() {
    this.user=new User();
  }
  ngAfterViewInit(): void {
    this.userForm.valueChanges.subscribe(data => this.onValueChanged(data));

    this.route.params.filter((params:Params)=>!isUndefined(params['id'])).switchMap((params:Params)=> this.userService.getUser(params['id']))
      .subscribe((result:Result<User>)=>this.bindData(result.data));
  }

  //添加用户
  addUser() {
    this.userService.addUser(this.user).subscribe(result=>this.router.navigate(['/user','list']));
  }
  //保存用户
  editUser() {
    this.userService.editUser(this.user).subscribe(result=>this.router.navigate(['/user','list']));
  }

  bindData(user:User) {
    this.user=user;
  }

  onValueChanged(data) {
    for (const field in this.formErrors) {
      this.formErrors[field] = '';
      //取到表单字段
      const control = this.userForm.form.get(field);
      //表单字段已修改或无效
      if (control && control.dirty && !control.valid) {
        //取出对应字段可能的错误信息
        const messages = this.validationMessages[field];
        //从errors里取出错误类型，再拼上该错误对应的信息
        for (const key in control.errors) {
          this.formErrors[field] += messages[key] + '';
        }
      }

    }
  }
  //存储错误信息
  formErrors = {
    'userName': '',
    'userAge':'',
  };
  //错误对应的提示
  validationMessages = {
    'userName': {
      'required': '用户名必填.',
      'minlength': '用户名太短',
    },
    'userAge':{
      'required': '请输入年龄',
      'numberBetween': '年龄范围不正确',
    },
    'passwordGroup.password2':{
      'required': '请重复输入密码',
      'minlength': '密码太短',
      'passwordNEQ':'两次输入密码不同',
      'password1InValid':''
    },
    'sex':{
      'required':'性别必填'
    }

  };

}
