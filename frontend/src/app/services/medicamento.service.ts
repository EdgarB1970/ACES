import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Medicamento } from '../models/medicamento.model';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {

  private apiUrl = 'api/medicamentos';

  constructor(private http: HttpClient) { }


  create(medicamento: Medicamento): Observable <Medicamento> {
    return this.http.post<Medicamento>(this.apiUrl, medicamento);
  }

  update(id: number, medicamento: Medicamento): Observable<Medicamento>{
    return this.http.put<Medicamento>(`${this.apiUrl}/${id}`, medicamento);
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  findById(id: number): Observable<Medicamento>{
    return this.http.get<Medicamento>(`${this.apiUrl}/${id}`);
  }

  findAll(): Observable<Medicamento[]>{
    return this.http.get<Medicamento[]>(this.apiUrl);
  }

}
