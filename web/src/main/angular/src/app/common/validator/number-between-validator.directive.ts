import {Directive, Input, SimpleChanges, OnChanges} from '@angular/core';
import {AbstractControl, ValidatorFn, NG_VALIDATORS, Validator} from "@angular/forms";
/**
 * 校验数字是否在指定范围内
 * 使用方法：
 * input标签添加numberBetween="1,100"
 * 上限或下限可留空，如numberBetween="1,"表示限制必须大于等于1
 * 条件不符合，错误类型为 numberBetween
 */
@Directive({
  selector: '[numberBetween]',
  providers: [{provide: NG_VALIDATORS, useExisting: NumberBetweenValidatorDirective, multi: true}]
})
export class NumberBetweenValidatorDirective  implements Validator,OnChanges{

  @Input('numberBetween') numberBetween:number;
  min:number;
  max:number;
  validate(c: AbstractControl): {[p: string]: any} {
    return between(c,this.min,this.max);
  }

  ngOnChanges(changes: SimpleChanges): void {
    let numbers=changes['numberBetween'].currentValue.split(',');
    if(numbers[0]!=''&&!isNaN(numbers[0])) {
      this.min=parseInt(numbers[0]);
    }else{
      this.min=null;
    }
    if(numbers[1]!=''&&!isNaN(numbers[1])) {
      this.max=parseInt(numbers[1]);
    }else{
      this.max=null;
    }
  }
  constructor() { }
}

/**
 * 响应式表单使用
 * @param min
 * @param max
 * @returns {(currentControl:AbstractControl)=>{[p: string]: any}}
 */
export function numberBetween(min:number,max:number):ValidatorFn {
  return (currentControl: AbstractControl): {[key: string]: any} => {
    return between(currentControl,min,max);
  };
}
/**
 * 数字是否在指定范围
 * @param currentControl
 * @param min
 * @param max
 */
function between(currentControl: AbstractControl,min:number,max:number) {
  if(currentControl.value==''||isNaN(currentControl.value)) {
    return {numberBetween:{'errMsg':'数字输入不合法'}}
  }
  let integerValue=parseInt(currentControl.value);
  if((!currentControl.untouched||currentControl.dirty)) {
    if((min!=null&&integerValue<min)||(max!=null&&integerValue>max)) {
      return {numberBetween:{'errMsg':'数字输入不合法'}}
    }
  }
}
