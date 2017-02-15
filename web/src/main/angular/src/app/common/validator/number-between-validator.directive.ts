import {Directive, Input, SimpleChanges} from '@angular/core';
import {AbstractControl, ValidatorFn} from "@angular/forms";

@Directive({
  selector: '[numberBetween]'
})
export class NumberBetweenValidatorDirective {

  @Input('numberBetween') numberRange:number;
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
