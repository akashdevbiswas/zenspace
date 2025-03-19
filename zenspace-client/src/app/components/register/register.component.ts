import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCamera, faChevronLeft , faChevronRight } from '@fortawesome/free-solid-svg-icons';
import { InputComponent } from '../input/input.component';
import { ButtonComponent } from '../button/button.component';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { AsyncPipe, NgClass } from '@angular/common';
import AuthService, { NewUser } from '../../service/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-register',
  imports: [InputComponent, FontAwesomeModule, ButtonComponent, NgClass,AsyncPipe],
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {
  faAvatar = faCamera;
  faDelete = faXmark;
  faChevronLeft = faChevronLeft;
  faChevronRight = faChevronRight

  constructor(private authService: AuthService, private router: Router) {}
  

  avatar: null | File = null;
  firstName = new FormControl('');
  lastName = new FormControl<string>('');
  username = new FormControl<string>('');
  email = new FormControl<string>('');
  dateOfBirth = new FormControl<string | null>(null);
  gender = new FormControl<string | null>('');
  password = new FormControl('');

  avatars$!: Observable<string[]>

  confirmPassword = new FormControl('');

  isImageSelected = true;

  avatarPreview: null | ArrayBuffer | string = null;

  ngOnInit(): void {
    this.avatars$ = this.authService.fetchUserAvatars()
  }

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

      this.authService.register(newUser).subscribe((response) => {
        if (response.status === 201) {
          this.router.navigateByUrl('/auth/login');
        }
      });
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

  currentAvatarIndex = 0;
  translateX = 0;


  onSlideRight() {
    this.translateX-= 83;
    this.currentAvatarIndex++;
  }

  onSlideLeft(){
    this.translateX+= 83
    this.currentAvatarIndex--;
  }

  
}
