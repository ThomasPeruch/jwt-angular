import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class AuthService {
    constructor(private http: HttpClient){}

    login(username: string, password: string) {
        return this.http.post<{token: string}>(
            'http://localhost:8080/auth/login',{username, password}
        );
    }
}
