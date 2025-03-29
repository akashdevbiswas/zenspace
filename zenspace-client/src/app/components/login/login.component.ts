import { Component } from '@angular/core';
import { InputComponent } from '../input/input.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ButtonComponent } from '../button/button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import AuthService from '../../service/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [
    InputComponent,
    ReactiveFormsModule,
    ButtonComponent,
    FontAwesomeModule,
  ],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  usernameOrEmail = new FormControl('');
  password = new FormControl('');

  onSubmit(eve: Event) {
    eve.preventDefault();

    if (this.usernameOrEmail.value && this.password.value) {
      this.authService
        .login({
          usernameOrEmail: this.usernameOrEmail.value,
          password: this.password.value,
        })
        .subscribe((response) => {
          const { status } = response;
          if (status === 201 && response.body) {
            const {accessToken} = response.body;
            if(accessToken){
              this.authService.setAuthorization(accessToken);
              this.router.navigateByUrl('/home');
            }else{
              // Some error occurred at server side, handle that error.
              console.error('Some error occurred at server side.')
              return;
            }
          }else if(status === 401){
            // Handle other cases.
          }
        });
    }
  }

  faSomeIcon = faUser;
}
