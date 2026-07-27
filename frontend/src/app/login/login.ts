import { NgZone, Component, signal } from '@angular/core';
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
  message = signal('');

  constructor(private authService: AuthService) {}

  login() {
    console.log(this.username),
    console.log(this.password),
    this.authService.login(this.username, this.password)
      .subscribe({ 
        next: (res) => { 
          localStorage.setItem('token', res.token);
          console.log(res.token)
          this.message.set('Login realizado com sucesso!');
        },
        error:() => {
          this.message.set("Credenciais inválidas")
        }
      });
  }
}