# Prueba técnica resuelta por Alexander Alberto Cantoral Sedamano

**Backend** en la carpeta principal y en la carpeta insalud se encuentra un zip con el **Frontend**, como se aprecia en la figura siguiente:

---

[![image.png](https://i.postimg.cc/9QyjgSLC/image.png)](https://postimg.cc/FYFq7PtB)
[![image.png](https://i.postimg.cc/Fz0VjrsM/image.png)](https://postimg.cc/645ZKNZc)

---
Eso fue para que todo fuera en un solo repositorio.
Para el **Frontend** solo se debería extraer en una carpeta y una vez hecho ello correr el comando *npm install* para tener todo para correr el frontend, luego de ello usar *npm run dev*.

Para el backend pues es lo básico. Se clona el repositorio y se debe crear la basedatos con el nombre de **gestion** y cambiar el application.properties. Lo seleccionado en la imagen es lo que se debe cambiar.
---

[![image.png](https://i.postimg.cc/15nQ1b7L/image.png)](https://postimg.cc/PPjcmVj4)

---
Cambiar el puerto de *spring.datasource.url:..* si es que esté está en uso.

### **IMPORTANTE-NOTA 1:** Necesitarás esto para no hacer a mano los inserts de data

[Script para la base de datos](https://jumpshare.com/share/dDRxzjNGiw6EGwspaA22)

Por si el link se cayó:
<br>
---
<br>
-- Admin
INSERT INTO pacientes (nombre, email, contrasena, estado, rol)
VALUES ('Admin', 'admin@demo.com', 
        '$2a$10$b.xZdHayAdCx837iNohHuugtgRKs1b0cmp.Wc4zXenXTj1wwB9iF6', 
        true, 'ADMIN');

-- Paciente
INSERT INTO pacientes (nombre, email, contrasena, estado, rol)
VALUES ('Paciente1', 'paciente1@demo.com', 
        '$2a$10$b.xZdHayAdCx837iNohHuugtgRKs1b0cmp.Wc4zXenXTj1wwB9iF6', 
        true, 'PACIENTE');


INSERT INTO especialidades (nombre, estado)
VALUES ('Cardiología', true);

INSERT INTO especialidades (nombre, estado)
VALUES ('Neurología', true);
INSERT INTO medicos (nombre, estado)
VALUES ('Dr. Pérez', true);

INSERT INTO medicos (nombre, estado)
VALUES ('Dra. Gómez', true);

-- Dr. Pérez → Cardiología
INSERT INTO medico_especialidad (medico_id, especialidad_id)
VALUES (1, 1);

-- Dr. Pérez → Neurología
INSERT INTO medico_especialidad (medico_id, especialidad_id)
VALUES (1, 2);

-- Dra. Gómez → Neurología
INSERT INTO medico_especialidad (medico_id, especialidad_id)
VALUES (2, 2);


-- Asignaciones de especialidades a médicos
INSERT INTO especialidades (nombre, estado)
VALUES 
('Dermatología', true),
('Pediatría', true);
INSERT INTO medicos (nombre, estado)
VALUES 
('Dr. Ramírez', true),
('Dra. Torres', true);
INSERT INTO medico_especialidad (medico_id, especialidad_id)
VALUES 
(3, 1),  -- Cardiología
(3, 3);  -- Dermatología
INSERT INTO medico_especialidad (medico_id, especialidad_id)
VALUES 
(4, 4),  -- Pediatría
(4, 2);  -- Neurología

-- Atencion inicial
INSERT INTO atenciones (fecha, motivo, activo, paciente_id, medico_id)
VALUES (
    '2025-09-30 10:00:00', 
    'Consulta inicial', 
    true, 
    (SELECT id FROM pacientes WHERE nombre='Paciente1'),
    (SELECT id FROM medicos WHERE nombre='Dr. Pérez')
); 

---


### **IMPORTANTE-NOTA 2:**  La poderosa contraseña de ambos usuarios es *1234* en el login no poner lo que se mando a la base de datos. Si esto no funciona, leer la nota 3

Luego de ello hay dos caminos, revisar el backend endpoint por endpoint o ver el frontend y como funcionan dichos endpoints.
Para lo primero es dirigirse a esta carpeta con estos archivos e ir viendo que importa cada resource y sus comentarios. No hay spoilers, vaya y vealo, comentarlo todo me costó una cantidad razonable de tiempo.

---

[![image.png](https://i.postimg.cc/gJZTTP2D/image.png)](https://postimg.cc/47XPYqt7)

---
Para el segundo solo es jugar con en el frontend, sus métodos cruds, los dos tipos de usuario y como están desarrollados los endpoints estipulados por el pdf. Tienen validaciones convencionales y generales para la noción de métodologia de negocio que tengo de este rubro (Y para lo que me dio tiempo).


### **IMPORTANTE-NOTA 3:** Ignorar si funciona el login. En caso contrario, pues solo ejecutar el método de la siguiente imagen
---

[![image.png](https://i.postimg.cc/fLB7f0B4/image.png)](https://postimg.cc/Ppv8tJgK)

---
Y usar este script con la contraseña encriptada que generará dicho código. Donde se reemplazaría *contraseña encriptada* por lo generado.
<br>
-- Admin 2
INSERT INTO pacientes (nombre, email, contrasena, estado, rol)
VALUES ('Admin', 'admin2@demo.com', 
        '*contraseña encriptada*', 
        true, 'ADMIN');

-- Paciente 2
INSERT INTO pacientes (nombre, email, contrasena, estado, rol)
VALUES ('Paciente2', 'paciente2@demo.com', 
        '*contraseña encriptada*', 
        true, 'PACIENTE');

Gracias por su tiempo en leer esto y los comentarios del código.
