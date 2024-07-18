import { Routes } from '@angular/router';
import { FirstPageComponent } from './pages/first-page/first-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthGuardService } from './services/auth-guard/auth-guard.service';

export const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: 'home', component: HomePageComponent, canActivate: [AuthGuardService]},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
