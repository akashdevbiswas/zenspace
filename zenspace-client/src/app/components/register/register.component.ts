import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
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
  ],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  faAvatar = faCamera;
  faDelete = faXmark;

  avatar: null | File = null;
  firstName = new FormControl('');
  lastName = new FormControl('');
  username = new FormControl('');
  email = new FormControl('');
  password = new FormControl('');
  confirmPassword = new FormControl('');
  dateOfBirth = new FormControl('');
  gender = new FormControl('');

  isImageSelected = true;

  avatarPreview: null | ArrayBuffer | string = null;

  onSubmit(eve: Event) {
    eve.preventDefault();
    console.log();
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
        if(e.target)
          this.avatarPreview = e.target.result; // Make the image visible
      };
      reader.readAsDataURL(this.avatar);
    }
  }
}
