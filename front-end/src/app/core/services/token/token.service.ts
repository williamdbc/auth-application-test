import { Injectable } from '@angular/core';
import { Router } from 'express';
import { interval } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private readonly TOKEN_KEY = 'authToken';

  constructor(private authService: AuthService) {
    this.startTokenValidation();
  }

  private startTokenValidation() {
    interval(1000 * 60)
      .subscribe(() => this.checkTokenValidity());
  }

  private checkTokenValidity() {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      this.authService.validateToken(token).subscribe({
        next: () => {
        },
        error: () => {
          this.handleInvalidToken();
        }
      });
    } else {
      this.handleInvalidToken();
    }
  }

  private handleInvalidToken() {
    this.authService.logout();
  }













}
