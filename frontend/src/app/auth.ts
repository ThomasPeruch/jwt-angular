import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class AuthService {
    constructor(private http: HttpClient){}

    login(username: string, password: string) {
        return this.http.post<{token: string}>(
            'http://localhost:8080/auth/login',{username, password}
        );
    }

    authenticate(token: string): Observable<HttpResponse<any>> {
    return this.http.get<any>
      ('http://localhost:8080/auth',
        {
          headers: {
            'Authorization': 'Bearer '+ token,
            'Content-Type': 'application/json'
          }, 
          observe: 'response'
        }
      );
    }
}
