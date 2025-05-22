import { Component, input, OnInit } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faEye,
  faEyeSlash,
  IconDefinition,
} from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'input-component',
  template: `
    <div class="input">
      <label class="input-label" [for]="'id-' + title()">{{ title() }}</label>
      @if (type() === 'password') {
      <div class="input-field">
        <input
        class="input-tag"
          [id]="'id-' + title()"
          [type]="isPassword ? 'password' : 'text'"
          [formControl]="formControlName()"
        />
        <span class="icon"
          ><fa-icon
            class="w-full"
            (click)="isPassword = !isPassword"
            [icon]="isPassword ? faEyeSlash : faEye"
          ></fa-icon
        ></span>
      </div>
      } @else if (type() === 'options' ) {
      <div class="input-field">
        <select class="input-tag" name="" id="" [formControl]="formControlName()">
          <option disabled selected>Select {{ title() }}</option>
          @for(item of options(); track item) {
          <option value="">{{ item }}</option>
          }

          <span class="icon"><fa-icon [icon]="inputIcon"></fa-icon></span>
        </select>
      </div>
      } @else {
      <div class="input-field">
        <input
        class="input-tag"
          [id]="'id-' + title()"
          [type]="type()"
          [formControl]="formControlName()"
        />
        @if (icon()) {
        <span class="icon"><fa-icon [icon]="inputIcon"></fa-icon></span>
        }
      </div>
      }
    </div>
  `,
  standalone: true,
  imports: [ReactiveFormsModule, FontAwesomeModule],
})
export class InputComponent implements OnInit {
  // Icons
  faEye = faEye;
  faEyeSlash = faEyeSlash;

  // Input fields

  type = input<InputType>('text');
  title = input<string>('');
  formControlName = input<FormControl<string | null>>(new FormControl(''));
  icon = input<IconDefinition | null>(null);
  options = input<string[]>([]);

  isPassword: boolean = false;
  inputIcon!: IconDefinition;

  ngOnInit(): void {
    this.isPassword = this.type() === 'password';
    if (this.icon()) {
      this.inputIcon = this.icon()!;
    }
  }
}

type InputType =
  | 'text'
  | 'password'
  | 'email'
  | 'number'
  | 'date'
  | 'time'
  | 'options';
