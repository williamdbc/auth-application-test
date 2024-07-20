import { PasswordModule } from 'primeng/password';
import { Component } from '@angular/core';
import { TokenService } from '../../core/services/token/token.service';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth/auth.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { Login } from '../../core/models/login.model';
import { Auth } from '../../core/models/auth.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule, InputTextModule, ButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginData: Login = {
    email: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private router: Router,
    private tokenService: TokenService
  ) { }

  onLogin(){
    this.authService.login(this.loginData).subscribe(
      response => this.handleLoginSuccess(response),
      error => this.handleLoginError(error)
    );
  }

  private handleLoginSuccess(response: Auth) {
    localStorage.setItem('authToken', response.token);
    this.router.navigate(['/home']);
  }
  
  private handleLoginError(error: any) {
    console.error('Erro no login:', error);
  }

}
