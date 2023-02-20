import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private readonly BASE_URL: string = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public request(nome: string, signature:string): Observable<string> {
    const body = JSON.stringify(nome);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Signature': signature // Aggiungi la firma MD5 della richiesta
    });
    return this.http.post<string>(`${this.BASE_URL}/interview`, body, { headers: headers });
  }
}