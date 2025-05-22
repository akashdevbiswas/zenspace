import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { InputComponent } from '../../components/input.component';
import { ButtonComponent } from '../../components/button.component';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { ErrorService } from '../../services/error.service';

@Component({
  selector: 'sign-up-page',
  templateUrl: 'sign-up.page.html',
  imports: [InputComponent, ButtonComponent],
})
export class SignUpPage {
  constructor(
    private authService: AuthService,
    private router: Router,
    protected errorService: ErrorService
  ) {}

  username: FormControl<string | null> = new FormControl('');
  password: FormControl<string | null> = new FormControl('');
  firstName: FormControl<string | null> = new FormControl('');
  lastName: FormControl<string | null> = new FormControl('');

  onSubmit(eve: SubmitEvent) {
    eve.preventDefault();

    if (
      this.username.value &&
      this.password.value &&
      this.firstName.value &&
      this.lastName.value
    ) {
      this.authService
        .register({
          username: this.username.value,
          password: this.password.value,
          firstName: this.firstName.value,
          lastName: this.lastName.value,
        })
        .subscribe({
          next: (res) => {
            const { status } = res;
            if (status === 201) {
              this.router.navigateByUrl('/auth/login');
            } else {
              console.error('Some error occurred while saving the user');
              this.errorService.setError(
                'Some error occurred while saving the user'
              );
            }
          },
          error: (err) => {
            console.error(err);
            this.errorService.setError(
              'Some error occurred while saving the user'
            );
          },
          complete: () => {
            console.log('User saved successfully');
          },
        });
    }
  }
}
