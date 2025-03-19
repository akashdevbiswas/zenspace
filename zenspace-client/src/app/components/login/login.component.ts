import { Component } from '@angular/core';
import { InputComponent } from '../input/input.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ButtonComponent } from '../button/button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import AuthService from '../../service/auth.service';
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
  constructor(private authService: AuthService) {}

  email = new FormControl('');
  password = new FormControl('');

  onSubmit(eve: Event) {
    eve.preventDefault();

    if (this.email.value && this.password.value) {
      this.authService.login({
        username: this.email.value,
        password: this.password.value,
      }).subscribe(
        (response) => {
        if(response.status === 201){
          
        }
      });
    }
  }

  faSomeIcon = faUser;
}
