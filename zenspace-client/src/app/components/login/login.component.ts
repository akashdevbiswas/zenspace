import { Component } from '@angular/core';
import { InputComponent } from '../input/input.component';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { ButtonComponent } from '../button/button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
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
  userForm = {
    email: new FormControl(''),
    password: new FormControl(''),
  };

  onSubmit(eve: Event) {
    eve.preventDefault();
    console.log(this.userForm.email.value);
  }

  faSomeIcon = faUser;
}
