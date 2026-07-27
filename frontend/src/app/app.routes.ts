import { Routes } from '@angular/router';
import { Login } from './login/login'
import { Authenticated } from './authenticated/authenticated';

export const routes: Routes = [
    { path : '', component: Login }, 
    { path : 'authenticated', component: Authenticated }, 
];
