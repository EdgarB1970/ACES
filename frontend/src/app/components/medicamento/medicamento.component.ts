import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Medicamento } from '../../models/medicamento.model';
import { MedicamentoService } from '../../services/medicamento.service';

@Component({
    selector: 'app-medicamento',
    templateUrl: './medicamento.component.html',
    styleUrls: ['./medicamento.component.css'],
})
export class MedicamentoComponent implements OnInit {
    medicamentos: any[] = [];
    medicamentoForm: FormGroup;
    editMode: boolean = false;


    displayedColumns: string[] = ['codigo','descripcion', 'fabricante', 'registroSanitario','tipo','acciones'];


    constructor(private medicamentoService: MedicamentoService, private fb: FormBuilder) {
        this.medicamentoForm = this.fb.group({
            codigo: [''],
            registroSanitario: ['', Validators.required],
            descripcion: [''],
            fabricante: ['', Validators.required],
            tipo: ['', Validators.required]
        });
    }

    ngOnInit(): void {
        this.loadMedicamentos();
    }

    loadMedicamentos(): void {
        this.medicamentoService.findAll().subscribe(
            data => {
                this.medicamentos = data;
                console.log(this.medicamentos);
            },
            error => {
                console.error('Error al cargar medicamentos', error);
            }
        );
    }

    createMedicamento(): void {
        if (this.medicamentoForm.valid) {
            const newMedicamento = this.medicamentoForm.value;
            this.medicamentoService.create(newMedicamento).subscribe(() => {
                this.loadMedicamentos();
                this.resetForm();
            });
        }
    }

    editMedicamento(medicamento: Medicamento): void {
        this.editMode = true;
        this.medicamentoForm.patchValue(medicamento);
    }

    updateMedicamento(): void {
        if (this.medicamentoForm.valid) {
            const updatedMedicamento = { ...this.medicamentoForm.value, id: this.medicamentoForm.value.id };
            this.medicamentoService.update(updatedMedicamento.id, updatedMedicamento).subscribe(() => {
                this.loadMedicamentos();
                this.resetForm();
                this.editMode = false;
            });
        }
    }

    deleteMedicamento(id: number): void {
        this.medicamentoService.delete(id).subscribe(() => {
            this.loadMedicamentos();
        });
    }

    resetForm(): void {
        this.medicamentoForm.reset();
        this.editMode = false;
    }
}
