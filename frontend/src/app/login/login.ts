import { Component } from '@angular/core';
import { AuthService } from '../auth';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [ FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username ='';
  password ='';
  message ='';

  constructor(private authService: AuthService) {}

  login() {
    this.authService.login(this.username, this.password)
      .subscribe({ 
        next: (res) => { 
          localStorage.setItem('token', res.token);
          this.message = 'Login realizado com sucesso!';
        },
        error:() => {
          this.message = "Credenciais inválidas"
        }
      });
  }
}