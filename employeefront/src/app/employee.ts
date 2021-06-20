import { DatePipe } from "@angular/common";

export interface Employee {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    imageUrl: string;
    jobTitle: string;
    birthDate: DatePipe;
    passport: string;
    cardNumber: string;
}