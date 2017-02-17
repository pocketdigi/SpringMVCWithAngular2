import {Component, OnInit, AfterViewInit, ViewChild, ElementRef} from "@angular/core";
import {ActivatedRoute, Router, Params} from "@angular/router";
import {UserService} from "../user.service";
import {FormBuilder, NgForm} from "@angular/forms";
import {User} from "../user";
import {Result} from "../../common/result";
import {isUndefined} from "util";
import {FileUploader, FileItem, ParsedResponseHeaders} from "ng2-file-upload";

const URL = "/upload";
@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit,AfterViewInit {

  //找到表单
  @ViewChild('userForm') userForm: NgForm;
  @ViewChild('uploadInput') uploadInput:ElementRef;
  public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'file', method: "POST",allowedMimeType:["image/png","image/jpeg"]});

  constructor(private route: ActivatedRoute, private userService: UserService, private fb: FormBuilder, private router: Router) {
  }

  user: User;
  ngOnInit() {
    this.user = new User();
    this.uploader.onSuccessItem = this.onSuccessItem.bind(this);
    this.uploader.onErrorItem = this.onErrorItem.bind(this);
    this.uploader.onWhenAddingFileFailed=this.onWhenAddingFileFailed.bind(this);
  }

  ngAfterViewInit(): void {
    this.userForm.valueChanges.subscribe(data => this.onValueChanged(data));

    this.route.params.filter((params: Params) => !isUndefined(params['id'])).switchMap((params: Params) => this.userService.getUser(params['id']))
      .subscribe((result: Result<User>) => this.bindData(result.data));
  }

  //添加用户
  addUser() {
    this.userService.addUser(this.user).subscribe(result => this.router.navigate(['/user', 'list']));
  }

  //保存用户
  editUser() {
    this.userService.editUser(this.user).subscribe(result => this.router.navigate(['/user', 'list']));
  }

  bindData(user: User) {
    this.user = user;
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
    'userAge': '',
  };
  //错误对应的提示
  validationMessages = {
    'userName': {
      'required': '用户名必填.',
      'minlength': '用户名太短',
    },
    'userAge': {
      'required': '请输入年龄',
      'numberBetween': '年龄范围不正确',
    },
    'passwordGroup.password2': {
      'required': '请重复输入密码',
      'minlength': '密码太短',
      'passwordNEQ': '两次输入密码不同',
      'password1InValid': ''
    },
    'sex': {
      'required': '性别必填'
    }

  };


  public hasBaseDropZoneOver: boolean = false;
  public hasAnotherDropZoneOver: boolean = false;

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  public fileOverAnother(e: any): void {
    this.hasAnotherDropZoneOver = e;
  }

  upload() {

    this.uploader.queue.forEach((fileItem: FileItem) => fileItem.alias = "file");
    this.uploader.uploadAll();
  }

  onSuccessItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
    let result=JSON.parse(response) as Result<string>;
    this.user.avatar=result.data;
  }

  onErrorItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
    let result=JSON.parse(response) as Result<string>;
    alert(result.msg);
  }
  onWhenAddingFileFailed(item: Object, filter: any, options: any): any {
    alert(item['name']+"无法上传，请重新选择");
    this.uploadInput.nativeElement.value=""
  }

}
