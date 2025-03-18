import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCamera } from '@fortawesome/free-solid-svg-icons';
import { InputComponent } from '../input/input.component';
import { ButtonComponent } from '../button/button.component';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { NgClass, NgOptimizedImage } from '@angular/common';
@Component({
  selector: 'app-register',
  imports: [
    InputComponent,
    FontAwesomeModule,
    ButtonComponent,
    NgClass,
    ReactiveFormsModule,
  ],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  faAvatar = faCamera;
  faDelete = faXmark;

  avatar: null | File = null;

  firstName = new FormControl('');
  lastName = new FormControl<string>('');
  username = new FormControl<string>('');
  email = new FormControl<string>('');
  dateOfBirth = new FormControl<string | null>(null);
  gender = new FormControl<'MALE' | 'FEMALE' | 'OTHERS' | null>(null);
  password = new FormControl('');

  confirmPassword = new FormControl('');

  isImageSelected = true;

  avatarPreview: null | ArrayBuffer | string = null;

  onSubmit(eve: Event) {
    eve.preventDefault();

    if (
      this.firstName.value &&
      this.lastName.value &&
      this.email.value &&
      this.username.value &&
      this.password.value &&
      this.gender.value &&
      this.dateOfBirth.value
    ) {
      const newUser: NewUser = {
        avatar: this.avatar,
        firstName: this.firstName.value,
        lastName: this.lastName.value,
        email: this.email.value,
        username: this.username.value,
        password: this.password.value,
        gender: this.gender.value,
        dateOfBirth: this.dateOfBirth.value,
      };

      console.log(newUser);
    }
  }

  deleteAvatar() {
    if (!this.avatar) {
      return;
    }
    this.avatar = null;
  }

  onFileSelected(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      this.avatar = file;
      const reader = new FileReader();
      reader.onload = (e) => {
        if (e.target) this.avatarPreview = e.target.result; // Make the image visible
      };
      reader.readAsDataURL(this.avatar);
    }
  }
}

export interface NewUser {
  avatar: null | File;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  gender: string;
  dateOfBirth: string;
}
