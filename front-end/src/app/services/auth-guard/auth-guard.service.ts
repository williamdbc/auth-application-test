import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private authService: AuthService, private router: Router) {}

 
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (typeof window !== 'undefined' && window.localStorage) {
      const isLoggedIn = !!localStorage.getItem('authToken'); // Verifica se o token existe
      if (!isLoggedIn) {
        this.router.navigate(['/login']); // Redireciona para a página de login se não estiver logado
      }
      return isLoggedIn;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
