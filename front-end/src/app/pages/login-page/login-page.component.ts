import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  login() {
    const loginData = { email: this.email, password: this.password };
    this.authService.login(loginData).subscribe(
      response => {
        console.log('Login bem-sucedido:', response);
        // Armazene o token de autenticação conforme necessário
        localStorage.setItem('authToken', response.token);
        this.router.navigate(['/home']);
      },
      error => {
        console.error('Erro no login:', error);
      }
    );
  }
}