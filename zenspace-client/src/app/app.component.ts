import { Component, HostListener } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import AuthService from './service/auth.service';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
})
export class AppComponent  {
  scrollPosition = 0;

  constructor(private authService: AuthService) {}

  @HostListener('window:scroll', ['$event'])
  onScroll(_: Event): void {
    const scrollTop = window.scrollY || document.documentElement.scrollTop;
    this.scrollPosition = scrollTop;
  }
}
