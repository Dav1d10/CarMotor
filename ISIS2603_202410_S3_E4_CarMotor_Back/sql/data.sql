delete from IMAGEN_ENTITY;
delete from HORARIO_TEST_DRIVE_ENTITY;
delete from VEHICULO_ENTITY;
delete from ENTIDAD_BANCARIA_ENTITY;
delete from ASESOR_ENTITY;
delete from SEDE_ENTITY;
delete from AUTO_INNOV_ENTITY;

insert into AUTO_INNOV_ENTITY(id) values (1) ;



insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (1, 'Gina', '658 4th Terrace', '775-786-5268', '2:39 AM', 'https://assets.cai-media-management.com/resize/690x240/dealership/157c4e41-9d94-43c0-9154-6c582c94f6f0.jpg', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (2, 'Declaration', '87 Delladonna Terrace', '153-847-7504', '12:54 AM', 'https://www.shutterstock.com/image-photo/riga-latvia-10-april-2020-600nw-1702334596.jpg', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (3, 'American Ash', '31 Dennis Way', '822-600-0070', '12:57 PM', 'https://media.gettyimages.com/id/458680771/es/foto/toyota-sucursal-en-halifax-nova-scotia.jpg?s=612x612&w=gi&k=20&c=yQg1SuqjdWmT75f9nKSnyT954aoE7doqoU-1EFxo17c=', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (4, 'Barnett', '4160 Dixon Pass', '822-434-2792', '8:56 AM', 'https://www.shutterstock.com/image-photo/audis-office-dealer-center-audi-600nw-2370668043.jpg', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (5, 'Northport', '59346 Northview Plaza', '894-760-4915', '10:55 PM', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9qMxiqDOevhfqivwGJitVqtgJgAshMN0O0Q&usqp=CAU', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (6, 'Spaight', '8072 Merrick Avenue', '497-961-8141', '3:28 PM', 'https://dms.software/wp-content/uploads/elementor/thumbs/Banner-19-pj7wtqvqstst0yf1vll3fx7hck005bat41mpffkbu8.jpg', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (7, 'Haas', '8 Aberg Plaza', '928-643-9658', '4:01 PM', 'https://vw-autoberlin.co/wp-content/uploads/sites/14/2023/06/Auto-BerlinEST-1200X700.jpg', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (8, 'Talmadge', '9937 Eastwood Alley', '522-365-5311', '7:09 PM', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjHGUXsx9knDAt71ImMxCb3lUrKSorY6tvcplTv2BsKmNS5QpxD40IpZkwmTfnqV8eHSI&usqp=CAU', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (9, 'Mendota', '97184 Duke Way', '707-394-6262', '5:42 PM', 'https://www.shutterstock.com/image-photo/las-vegas-nv-usa-232021-260nw-1914328012.jpg', 1);
insert into SEDE_ENTITY (id, nombre, direccion, telefono, horario_atencion, imagen, auto_innov_entity_id) values (10, 'Pawling', '9353 Bluejay Parkway', '861-572-1496', '12:15 AM', 'https://i.pinimg.com/736x/c9/c4/39/c9c439050ce156a88a211fb14e2486ef.jpg', 1);


insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (1, 'Tatiana Strothers', '789-296-8329', 'tstrothers0@bravesites.com', 'https://www.shutterstock.com/image-photo/photo-shiny-confident-lady-wear-600nw-2345901081.jpg', 1);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (2, 'Kimberley Shovelton', '678-372-8902', 'kshovelton1@omniture.com', 'https://img.freepik.com/foto-gratis/mujer-negocios-portapapeles-hablando-telefono_23-2148073162.jpg', 2);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (3, 'West Rodda', '110-375-2626', 'wrodda2@unblog.fr', 'https://www.shutterstock.com/image-photo/successful-confident-smart-caucasian-young-600nw-2139568149.jpg', 3);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (4, 'Bernie Watling', '738-512-4094', 'bwatling3@dedecms.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHRpc3M6oC9X0v3tVAaopGJZ-v9Tjx9zgoaPG9MSZ5D8oEujauMbR3xg5u3mn4zgzS99g&usqp=CAU', 4);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (5, 'Aggi Quilter', '330-218-1879', 'aquilter4@uiuc.edu', 'https://i.ibb.co/yP1St7K/venta-virtual-2.png', 5);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (6, 'Morgun Sheach', '147-597-7874', 'msheach5@woothemes.com', 'https://www.monlau.com/formacio-professional/wp-content/uploads/sites/3/2020/01/AdobeStock_866037062.jpg', 6);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (7, 'Emilio Pelling', '633-248-8525', 'epelling6@bloomberg.com', 'https://www.shutterstock.com/image-photo/closeup-shot-successful-confident-smiling-600nw-2103436118.jpg', 7);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (8, 'Rosemarie Budge', '506-227-6332', 'rbudge7@com.com', 'https://static.vecteezy.com/system/resources/previews/002/371/422/large_2x/professional-woman-with-a-clipboard-photo.jpg', 8);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (9, 'Angelika Wilby', '286-840-7624', 'awilby8@google.co.jp', 'https://img.freepik.com/foto-gratis/mujer-negocios-alegre-celebracion-portapapeles_171337-8515.jpg', 9);
insert into ASESOR_ENTITY (id, nombre, telefono, correo, fotografia, sede_id) values (10, 'Marsh Robens', '587-526-0537', 'mrobens9@java.com', 'https://espaciorrhh.com/imagenes/Funciones-de-un-asesor-comercial-1170x550.jpg', 10);

insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (1, 'Wadsworth', 816, 'Fivebridge', 573, '685 134 6155');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (2, 'Omero', 371, 'Ooba', 1024, '763 852 6115');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (3, 'Trefor', 890, 'Fiveclub', 1003, '221 778 4928');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (4, 'Pammie', 846, 'Zooxo', 429, '729 725 3464');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (5, 'Ignaz', 743, 'Jaxspan', 2384, '560 852 0468');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (6, 'Dorree', 778, 'Topicshots', 2358, '510 699 0907');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (7, 'Rosabel', 39, 'Dabshots', 2128, '497 445 6772');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (8, 'Jess', 319, 'Yodel', 672, '184 678 6300');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (9, 'Daniela', 952, 'Skaboo', 2331, '561 360 0525');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (10, 'Lanie', 806, 'Eimbee', 383, '753 735 7460');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (11, 'Ricard', 830, 'Yombu', 1978, '182 265 5369');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (12, 'Cherlyn', 742, 'Buzzster', 234, '274 763 1459');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (13, 'Alfred', 234, 'Brainbox', 1697, '660 365 9083');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (14, 'Conni', 580, 'Eazzy', 156, '670 641 0812');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (15, 'Diana', 26, 'Twitterbeat', 1000, '395 371 7789');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (16, 'Janela', 224, 'Divape', 2022, '975 651 0126');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (17, 'Maxy', 864, 'Skyndu', 1122, '688 932 2387');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (18, 'Cly', 100, 'Kwimbee', 1084, '249 119 0401');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (19, 'Orsa', 202, 'Jabberstorm', 1794, '150 264 1271');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (20, 'Ellene', 850, 'Eimbee', 1440, '159 866 6724');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (21, 'Garv', 996, 'Jayo', 1844, '625 292 1101');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (22, 'Marty', 243, 'Brainlounge', 245, '682 781 5029');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (23, 'Gerrard', 418, 'Innotype', 624, '624 961 5317');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (24, 'Rozalin', 918, 'Skidoo', 2132, '799 301 1609');
insert into ENTIDAD_BANCARIA_ENTITY (ID, ASESOR_FINANCIERO, ID_ENTIDAD_BANCARIA, NOMBRE, POLIZA, TELEFONO_CONTACTO) values (25, 'Willetta', 528, 'Zoombox', 728, '499 539 1183');

INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (1, '2024-05-30', 1);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (2, '2024-05-31', 2);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (3, '2024-06-01', 3);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (4, '2024-06-02', 4);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (5, '2024-06-03', 5);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (6, '2024-06-04', 6);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (7, '2024-06-05', 7);
INSERT INTO HORARIO_TEST_DRIVE_ENTITY (id, fecha, sede_id) VALUES (8, '2024-06-06', 8);


insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (1, 'Dodge', 'B91011H', 9, 'Avenger', 'Sedan', 5, 6000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWBAjyI8HoRtRRSFcM2GRxiGMXnY6tkyM_Gg&s', 1, 1, 1);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (2, 'Ford', 'X1234T', 8, 'F-Series', 'Deportivo', 8, 52000, 'https://www.ford.com.co/content/dam/Ford/website-assets/latam/co/nameplate/raptor/2023/models/f150-raptor/fco-showroom-f150-raptor.jpg', 1, 1, 1);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (3, 'Oldsmobile', 'X1234T', 4, '88', 'SUV', 4, 4000, 'https://noticias.coches.com/wp-content/uploads/2020/08/Oldsmobile-442-1987-1.jpg', 1, 1, 1);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (4, 'Dodge', 'A5678F', 7, 'Caravan', 'SUV', 7, 49000, 'https://upload.wikimedia.org/wikipedia/commons/a/a9/Dodge_Charger_Hirschaid-20220709-RM-113717.jpg', 2, 2, 2);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (5, 'Audi', 'B91011H', 0, '100', 'Sedan', 4, 10000, 'https://www.las2orillas.co/wp-content/uploads/2023/10/audi-s3.jpg', 2, 2, 2);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (6, 'Porsche', 'B91011H', 6, 'Carrera GT', 'Deportivo', 5, 80000, 'https://www.neumarket.com/blog/wp-content/uploads/2013/10/Neumarket.com-Porsche-Spyder-918.png', 2, 2, 2);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (7, 'Audi', 'A5678F', 1, 'Allroad', 'Camioneta', 4, 40000, 'https://www.elcarrocolombiano.com/wp-content/uploads/2020/06/AUDI-1.jpg', 2, 2, 2);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (8, 'Subaru', 'Q1415A', 6, 'Tribeca', 'Sedan', 8, 9000, 'https://www.elcarrocolombiano.com/wp-content/uploads/2020/07/20200907-SUBARU-WRX-PORTADA-01.jpg', 3, 3, 3);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (9, 'Cadillac', 'X1234T', 1, 'Escalade EXT', 'Familiar', 8, 20000, 'https://st1.uvnimg.com/7d/40/797d59a343a8bdf26f5c06f7661d/2020-cadillac-ct5-1-6.jpg', 3, 3, 3);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (10, 'Mazda', 'X1234T', 9, 'MPV', 'Sedan', 6, 5000, 'https://www.mazda.com.co/link/e87723b08aa2476c89b7463e679e0e2c.aspx', 3, 3, 3);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (11, 'GMC', 'X1234T', 7, 'Sierra 3500', 'Sedan', 8, 60000, 'https://www.elcarrocolombiano.com/wp-content/uploads/2022/05/20220504-GMC-YUKON-DENALI-ULTIMATE-PORTADA.jpg', 3, 3, 3);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (12, 'Mercedes-Benz', 'M1213B', 8, 'C-Class', 'Camioneta', 5, 45000, 'https://www.elcarrocolombiano.com/wp-content/uploads/2017/03/20170307-MERCEDES-BENZ-CLASE-E-CABRIOLET-2017-01.jpg', 4, 4, 4);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (13, 'Chevrolet', 'M1213B', 2, 'Corvette', 'Camioneta', 7, 8000, 'https://www.chevrolet.com.co/content/dam/chevrolet/south-america/colombia/espanol/index/plan-siempre-chevrolet/intro/plano-chevrolet-intro.jpg?imwidth=960', 4, 4, 4);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (14, 'Jeep', 'M1213B', 5, 'Grand Cherokee', 'SUV', 4, 25000, 'https://www.carroya.com/noticias/sites/default/files/carroya_-_jeep_wrangler_unlimited_sahara_2.jpg', 4, 4, 4);
insert into VEHICULO_ENTITY (id, marca, serie, ultimo_placa, modelo, tipo, capacidad, precio, fotografia, asesor_id, sede_id, horario_test_drive_id) values (15, 'Toyota', 'B91011H', 4, 'RAV4', 'SUV', 4, 15000, 'https://acroadtrip.blob.core.windows.net/catalogo-imagenes/m/RT_V_21dc54196ab14e4cbe61f841d856e894.webp', 4, 4, 4);


insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (1, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 1);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (2, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 1);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (3, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 1);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (4, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 2);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (5, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 2);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (6, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 2);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (7, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 3);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (8, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 3);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (9, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 3);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (10, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 4);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (11, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 4);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (12, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 4);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (13, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 5);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (14, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 5);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (15, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 5);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (16, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 6);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (17, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 6);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (18, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 6);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (19, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 7);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (20, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 7);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (21, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 7);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (22, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 8);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (23, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 8);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (24, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 8);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (25, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 9);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (26, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 9);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (27, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 9);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (28, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 10);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (29, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 10);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (30, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 10);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (31, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 11);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (32, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 11);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (33, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 11);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (34, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 12);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (35, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 12);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (36, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 12);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (37, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 13);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (38, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 13);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (39, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 13);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (40, 'http://dummyimage.com/218x100.png/dddddd/000000', 'INTERIOR', 14);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (41, 'http://dummyimage.com/200x100.png/ff4444/ffffff', 'MOTOR', 14);
insert into IMAGEN_ENTITY (id, URL, tipo_imagen, vehiculo_id) values (42, 'http://dummyimage.com/200x100.png/5fa2dd/ffffff', 'EXTERIOR', 14);





