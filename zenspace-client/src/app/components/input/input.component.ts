import { Component, input, OnInit, output } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-input',
  imports: [ReactiveFormsModule,FontAwesomeModule],
  templateUrl: './input.component.html',
})
export class InputComponent implements OnInit {
  ngOnInit(): void {
    if (this.type() === 'options' && this.options === null) {
      throw new Error('Options are required');
    }

    if(this.type() === 'password'){
      this.inputType = 'password';
      this.iconVisibility = false;
    }else{
      this.iconVisibility = true;
    }
  }

  label = input<string>('');
  type = input.required<InputType>();
  forInput = `input-${this.label()}`;
  placeholder = input<string>('');
  inputValue = input.required<FormControl<string | null>>();
  icon = input<HTMLSpanElement>();
  options = input<string[]>();
  showVisibilityOptions = input<boolean>(false);
  inputType: InputType = 'text';
  clasNames= input<string>(''); 

  iconVisibility = true
  eye = faEye;
  eyeSlash = faEyeSlash;

  onVisibilityChange() {
    if (this.inputType === 'password') {
      this.inputType = 'text';
    }else{
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
