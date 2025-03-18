import { Component } from '@angular/core';
import { InputComponent } from '../input/input.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
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
  userCredentials = {
    email: new FormControl(''),
    password: new FormControl(''),
  }

  onSubmit() {
    console.log(this.userCredentials);
  }

  faSomeIcon = faUser;
}
