import { Component, computed, effect, OnInit, Signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { ButtonComponent } from '../button/button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import { AsyncPipe, NgIf, NgOptimizedImage } from '@angular/common';
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
  ],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent {
  notificationBell = faBell;

  user: User | null = null;

  constructor(
    protected router: Router,
    private userService: UserService,
    protected authService: AuthService
  ) {
    effect(() => {
      const authorization = this.authService.getAuthorizationSignal();
      if (authorization()) {
        this.userService.getUser().subscribe((res) => {
          if (res.status === 200 && res.body) {
            this.user = res.body;
          } else {
            this.router.navigateByUrl('/auth');
          }
        });
      }
    });
  }

  onClickHandler() {
    this.router.navigateByUrl('/auth');
  }
}
