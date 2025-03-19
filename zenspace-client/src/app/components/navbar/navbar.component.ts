import { Component, OnInit } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive
} from '@angular/router';
import { ButtonComponent } from '../button/button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import { NgOptimizedImage } from '@angular/common';
import UserService from '../../service/user.service';
@Component({
  selector: 'app-navbar',
  imports: [ButtonComponent, RouterLink, RouterLinkActive, FontAwesomeModule, NgOptimizedImage],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent  {
  notificationBell = faBell;
  constructor(protected router: Router, private userService: UserService) {
    console.log(this.router.url);
  }

  onClickHandler() {
    this.router.navigateByUrl('/auth');
  }

  isAuthenticated() {
    return false;
  }
  
}
