-- ==============================================
-- DATOS DE USUARIOS
-- ==============================================
INSERT INTO usuarios (nombre, documento) VALUES ('Carlos Pérez', '123456789');
INSERT INTO usuarios (nombre, documento) VALUES ('María López', '987654321');
INSERT INTO usuarios (nombre, documento) VALUES ('Andrés Gómez', '1122334455');

-- ==============================================
-- DATOS DE LOCKERS
-- ==============================================
INSERT INTO lockers (ubicacion, disponible) VALUES ('Bloque A - Piso 1', TRUE);
INSERT INTO lockers (ubicacion, disponible) VALUES ('Bloque A - Piso 2', TRUE);
INSERT INTO lockers (ubicacion, disponible) VALUES ('Bloque B - Entrada Principal', FALSE);
INSERT INTO lockers (ubicacion, disponible) VALUES ('Bloque C - Biblioteca', TRUE);
INSERT INTO lockers (ubicacion, disponible) VALUES ('Bloque D - Laboratorio', TRUE);

-- ==============================================
-- DATOS DE ALQUILERES (solo si hay lockers ocupados)
-- ==============================================
-- Supongamos que el locker con id 3 está alquilado por el usuario con id 1
INSERT INTO alquileres (locker_id, usuario_id, activo, fecha_inicio, fecha_fin)
VALUES (3, 1, TRUE, CURRENT_TIMESTAMP(), NULL);
