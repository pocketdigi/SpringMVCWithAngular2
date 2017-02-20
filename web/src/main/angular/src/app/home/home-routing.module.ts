import {Routes, RouterModule} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {NgModule} from "@angular/core";

const routes: Routes=[
  {
    path:'',
    component:HomeComponent
  }
]

@NgModule({
  imports:[RouterModule.forChild(routes)],
  exports:[RouterModule],
  providers:[]

})
export class HomeRoutingModule{}
