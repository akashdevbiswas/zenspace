import { Component , input} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-button',
  imports: [],
  templateUrl: './button.component.html'
})
export class ButtonComponent {

  public constructor(private router: Router) {}

  onClickHandler = input.required<() => void>()

}
