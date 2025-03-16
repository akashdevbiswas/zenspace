import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCamera } from '@fortawesome/free-solid-svg-icons';
import { InputComponent } from '../input/input.component';
import { ButtonComponent } from "../button/button.component";
@Component({
  selector: 'app-register',
  imports: [InputComponent, FontAwesomeModule, ButtonComponent],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  faAvatar = faCamera;

  avatar = new FormControl<string | null>(null);
  firstName = new FormControl('');
  lastName = new FormControl('');
  username = new FormControl('');
  email = new FormControl('');
  password = new FormControl('');
  confirmPassword = new FormControl('');
  dateOfBirth = new FormControl('');
  gender = new FormControl('');

  onSubmit(eve: Event) {
    eve.preventDefault();
    console.log();
  }
}
