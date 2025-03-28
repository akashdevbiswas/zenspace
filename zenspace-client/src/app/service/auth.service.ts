import { HttpClient } from '@angular/common/http';
import { Injectable, signal, WritableSignal } from '@angular/core';
import { BaseResponse } from './http-constants.service';

@Injectable({
  providedIn: 'root',
})
export default class AuthService {
  private token = signal<null | string>(null);
  constructor(private http: HttpClient) {
    this.refreshAccessToken().subscribe((res) => {
      if (res.body && res.body.accessToken) {
        this.token.set(res.body.accessToken);
      }
    });
  }

  register(user: NewUser) {
    const formData = new FormData();
    let key: keyof NewUser;
    for (key in user) {
      const value = user[key];
      if (key === 'avatar' && typeof value === 'object') {
        formData.append('profileImage', value);
      } else if (key === 'avatar' && typeof value === 'number') {
        formData.append('avatar', value.toString());
      } else if (key !== 'avatar' && typeof value === 'string') {
        formData.append(key, value);
      }
    }
    return this.http.post<Object>('/api/v1/auth/register', formData, {
      observe: 'response',
    });
  }

  login(userCredentials: { usernameOrEmail: string; password: string }) {
    return this.http.post<
      BaseResponse<{ accessToken: string; refreshToken: string }>
    >('/api/v1/auth', userCredentials, {
      observe: 'response',
    });
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

  getAuthorization(): string | null {
    return this.token();
  }

  getAuthorizationSignal():WritableSignal<null | string> {
    return this.token;
  }

  fetchUserAvatars() {
    return this.http.get<string[]>('/api/v1/auth/avatars');
  }
}

export interface NewUser {
  avatar: number | File;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  gender: string;
  dateOfBirth: string;
}
