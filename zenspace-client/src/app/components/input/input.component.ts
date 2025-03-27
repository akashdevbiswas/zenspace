import { NgClass } from '@angular/common';
import { Component, input, OnInit, output } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-input',
  imports: [ReactiveFormsModule, FontAwesomeModule,NgClass],
  templateUrl: './input.component.html',
})
export class InputComponent implements OnInit {
  label = input<string>('');
  type = input<InputType>('text');
  forInput = `input-${this.label()}`;
  placeholder = input<string>('');
  fcName = input.required<FormControl<string | null>>();
  icon = input<HTMLSpanElement>();
  options = input<string[]>();
  showVisibilityOptions = input<boolean>(false);
  classNames = input<string>('');
  maxLength = input<number>(100);
  minLength = input<number>(5);
  required = input<boolean>(false);
  isError = input<boolean>(false);

  inputType: InputType = this.type();
  iconVisibility = true;

  // Eye icons
  eye = faEye;
  eyeSlash = faEyeSlash;

  ngOnInit(): void {
    if (this.type() === 'options' && this.options === null) {
      throw new Error('Options are required');
    }
    if (this.type() === 'password') {
      this.inputType = 'password';
      this.iconVisibility = false;
    } else {
      this.iconVisibility = true;
      this.inputType = this.type();
    }
  }

  onVisibilityChange() {
    if (this.type() !== 'password') {
      return;
    }
    if (this.inputType === 'password') {
      this.inputType = 'text';
    } else {
      this.inputType = 'password';
    }
  }
}

type InputType =
  | 'text'
  | 'password'
  | 'email'
  | 'number'
  | 'date'
  | 'file'
  | 'options';
