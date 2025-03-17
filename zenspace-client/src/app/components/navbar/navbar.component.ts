import { Component, OnInit } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive
} from '@angular/router';
import { ButtonComponent } from '../button/button.component';
@Component({
  selector: 'app-navbar',
  imports: [ButtonComponent, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent  {
  constructor(protected router: Router) {
    console.log(this.router.url);
  }

  onClickHandler() {
    this.router.navigateByUrl('/auth');
  }

  isAuthenticated() {
    return false;
  }
  
}
