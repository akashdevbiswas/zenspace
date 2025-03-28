import { Component, computed, OnInit, Signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { ButtonComponent } from '../button/button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import { AsyncPipe, NgOptimizedImage } from '@angular/common';
import UserService, { User } from '../../service/user.service';
import AuthService from '../../service/auth.service';
import { Observable } from 'rxjs';
import { BaseResponse } from '../../service/http-constants.service';
@Component({
  selector: 'app-navbar',
  imports: [
    ButtonComponent,
    RouterLink,
    RouterLinkActive,
    FontAwesomeModule,
    NgOptimizedImage,
    AsyncPipe
  ],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {
  notificationBell = faBell;

  isAuthenticated: Signal<string | null>;
  user$!: Observable<BaseResponse<User>>;

  constructor(
    protected router: Router,
    private userService: UserService,
    private authService: AuthService
  ) {
    this.isAuthenticated = computed(() => {
      const authenticationSignal = this.authService.getAuthorizationSignal();
      return authenticationSignal();
    });
  }
  ngOnInit(): void {
    this.user$ = this.userService.getUser();
  }

  onClickHandler() {
    this.router.navigateByUrl('/auth');
  }
}
