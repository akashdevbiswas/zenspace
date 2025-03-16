import { NgClass } from '@angular/common';
import { Component, input ,output} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-button',
  imports: [NgClass],
  templateUrl: './button.component.html',
})
export class ButtonComponent {
  public constructor(private router: Router) {}

  buttonType = input.required<StyleType>();
  name = input.required<string>();
  clicked = output<void>();
  type = input<ButtonType>('button');

  onClick() {
    this.clicked.emit();
  }
  styles={
    'primary':'',
    'secondary':''
  }
}

type StyleType = 'primary' | 'secondary';
type ButtonType = 'submit' | 'reset' | 'button';

