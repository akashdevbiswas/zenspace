import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import AuthService from "./auth.service";
import { BaseResponse } from "./http-constants.service";


@Injectable({
    providedIn: 'root'
})
export default class UserService {
    constructor(private http: HttpClient, private auth: AuthService) {}


    getUser() {
        return this.http.get<BaseResponse<User>>('/api/v1/user',{
            headers:{
                'Authorization': 'Bearer '+this.auth.getAuthorization()
            }
        });
    }
}


export interface User{
    id: string;
    username: string;
    firstName: string;
    lastName: string;
    email: string;
    dateOfBirth: string;
    gender: string;
    avatar: string;
}