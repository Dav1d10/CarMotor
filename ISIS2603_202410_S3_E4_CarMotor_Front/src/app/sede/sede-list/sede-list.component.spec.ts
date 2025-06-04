/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { faker } from '@faker-js/faker';
import { SedeListComponent } from './sede-list.component';
import { HttpClientModule } from '@angular/common/http';
import { SedeService } from '../sede.service';
import { Sede } from '../sede';
import { SedeDetail } from '../sede-detail';


//import { Editorial } from '../../editorial/editorial';



describe('SedeListComponent', () => {
 let component: SedeListComponent;
 let fixture: ComponentFixture<SedeListComponent>;
 let debug: DebugElement;

 beforeEach(async(() => {
   TestBed.configureTestingModule({
     imports: [HttpClientModule],
     declarations: [ SedeListComponent ],
     providers: [ SedeService ]
   })
   .compileComponents();
 }));

 beforeEach(() => {
   fixture = TestBed.createComponent(SedeListComponent);
   component = fixture.componentInstance;

    /*
   const editorial = new Editorial(
     faker.number.int(),
     faker.lorem.sentence()
   );
   */
  let testSedes: Array<SedeDetail> = [];

   for(let i = 0; i < 10; i++) {
     testSedes[i] = new SedeDetail(
       faker.number.int(),
       faker.lorem.sentence(),
       faker.lorem.sentence(),
       faker.lorem.sentence(),
       faker.lorem.sentence(),
       faker.image.url(),
       //editorial,
     );
   }
   component.sedes = testSedes;
   fixture.detectChanges();
   debug = fixture.debugElement;
 });

 it('should create', () => {
   expect(component).toBeTruthy();
 });

 it('should have 10 <div.col.mb-2> elements', () => {
   expect(debug.queryAll(By.css('div.col.mb-2'))).toHaveSize(10)
 });

 it('should have 10 <card.p-2> elements', () => {
   expect(debug.queryAll(By.css('div.card.p-2'))).toHaveSize(10)
 });

 it('should have 10 <img> elements', () => {
   expect(debug.queryAll(By.css('img'))).toHaveSize(10)
 });

 it('should have 10 <div.card-body> elements', () => {
   expect(debug.queryAll(By.css('div.card-body'))).toHaveSize(10)
 });

 it('should have the corresponding src to the sede image and alt to the sede name', () => {
   debug.queryAll(By.css('img')).forEach((img, i)=>{
     expect(img.attributes['src']).toEqual(
       component.sedes[i].imagen)

     expect(img.attributes['alt']).toEqual(
       component.sedes[i].nombre)
   })
 });

 it('should have h5 tag with the sede.nombre', () => {
   debug.queryAll(By.css('h5.card-title')).forEach((h5, i)=>{
     expect(h5.nativeElement.textContent).toContain(component.sedes[i].nombre)
   });
 });

 /*
 it('should have p tag with the book.editorial.name', () => {
   debug.queryAll(By.css('p.card-text')).forEach((p, i)=>{
     expect(p.nativeElement.textContent).toContain(component.books[i].editorial.name)
   });
 });
 */
 it('should have 9 <div.col.mb-2> elements and the deleted sede should not exist', () => {
   const sede = component.sedes.pop()!;
   fixture.detectChanges();
   expect(debug.queryAll(By.css('div.col.mb-2'))).toHaveSize(9)

   debug.queryAll(By.css('div.col.mb-2')).forEach((selector, i)=>{
     expect(selector.nativeElement.textContent).not.toContain(sede.nombre);
   });
 });

});
