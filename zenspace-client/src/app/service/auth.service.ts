import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export default class AuthService {
  private token: string | null = null;
  constructor(private http: HttpClient) {
    this.refreshAccessToken().subscribe((res) => {
      if (res.body && res.body.accessToken) {
        this.token = res.body.accessToken;
      }
    });
  }

  register(user: NewUser) {
    const formData = new FormData();
    let key: keyof NewUser;
    for (key in user) {
      const value = user[key];
      if (value !== null) {
        formData.append(key, value);
      }
    }
    return this.http.post<Object>('/api/v1/auth/register', formData, {
      observe: 'response',
    });
  }

  login(userCredentials: { username: string; password: string }) {
    return this.http.post<{ accessToken: string; refreshToken: string }>(
      '/api/v1/auth',
      userCredentials,
      {
        observe: 'response',
      }
    );
  }

  refreshAccessToken() {
    return this.http.post<{ accessToken: string; refreshToken: string }>(
      '/api/v1/auth/refresh',
      null,
      {
        observe: 'response',
      }
    );
  }

  logOut() {
    return this.http.post<Object>('/api/v1/auth/logout', null, {
      observe: 'response',
    });
  }

  getAuthorization() :string | null { 
      return this.token
  }


  
  fetchUserAvatars(){
    return this.http.get<string[]>('/api/v1/auth/avatars')
  }
}

export interface NewUser {
  avatar: null | File;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  gender: string;
  dateOfBirth: string;
}
