// src/app/horario-testdrive/horario-testdrive-detail.model.ts

export interface HorarioTestDriveDetail {
    id: string;
    fecha: string;
    disponible: { [key: string]: boolean };
  }
  