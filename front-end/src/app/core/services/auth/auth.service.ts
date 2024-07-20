import { Injectable } from "@angular/core";
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { Login } from "../../models/login.model";
import { Observable } from "rxjs";
import { Auth } from "../../models/auth.model";
import { Register } from "../../models/register.model";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private readonly API = `${environment.API}/auth`;

  constructor(private http: HttpClient, private router: Router) {}

  login(data: Login): Observable<Auth> {
    return this.http.post<Auth>(`${this.API}/login`, data);
  }

  register(data: Register): Observable<void> {
    return this.http.post<void>(`${this.API}/register`, data);
  }

  validateToken(token: string): Observable<void> {
    return this.http.post<void>(`${this.API}/validate-token`, {token});
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

}
