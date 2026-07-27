import { Component, signal } from '@angular/core';
import { AuthService } from '../auth';
import { CommonModule } from '@angular/common';
import { HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-authenticated',
  imports: [CommonModule],
  templateUrl: './authenticated.html',
  styleUrl: './authenticated.css',
})

export class Authenticated {

  isAuthenticated = signal(false);

  constructor(private authService: AuthService) {}

  ngOnInit(){
    let token = localStorage.getItem("token")
    console.log(token)
    if(token){
      this.authService.authenticate(token).subscribe({
        next: (response) => {
          console.log(response.status)
          this.isAuthenticated.set(response.status === 200) 
        },
        error: () => {
          this.isAuthenticated.set(false)
        }
      });
    }
  }
}