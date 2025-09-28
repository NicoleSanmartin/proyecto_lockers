package com.lockers.repository;

import com.lockers.model.Alquiler;
import com.lockers.model.Estudiante;
import com.lockers.model.Locker;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LockerRepository {

    private static final String DB_URL = "jdbc:sqlite:lockers.db";

    public LockerRepository() {
        initDatabase();
    }

    private void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement st = conn.createStatement()) {
            st.executeUpdate("PRAGMA foreign_keys = ON;");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS estudiantes (documento TEXT PRIMARY KEY, nombre TEXT NOT NULL);");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS lockers (id INTEGER PRIMARY KEY, disponible INTEGER NOT NULL);");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS alquileres (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "documento TEXT NOT NULL, " +
                    "locker_id INTEGER NOT NULL, " +
                    "fechaInicio TEXT NOT NULL, " +
                    "estado TEXT NOT NULL, " +
                    "FOREIGN KEY(documento) REFERENCES estudiantes(documento), " +
                    "FOREIGN KEY(locker_id) REFERENCES lockers(id)" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // === Estudiantes ===
    public void addEstudiante(Estudiante e) {
        String sql = "INSERT OR IGNORE INTO estudiantes(documento, nombre) VALUES(?, ?);";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getDocumento());
            ps.setString(2, e.getNombre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Estudiante findEstudianteByDocumento(String documento) {
        String sql = "SELECT documento, nombre FROM estudiantes WHERE documento = ?;";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(rs.getString("nombre"), rs.getString("documento"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // === Lockers ===
    public void addLocker(Locker l) {
        String sql = "INSERT OR IGNORE INTO lockers(id, disponible) VALUES(?, ?);";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, l.getId());
            ps.setInt(2, l.isDisponible() ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Locker> getLockers() {
        List<Locker> list = new ArrayList<>();
        String sql = "SELECT id, disponible FROM lockers ORDER BY id;";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Locker l = new Locker(rs.getInt("id"));
                l.setDisponible(rs.getInt("disponible") == 1);
                list.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // === Alquileres ===
    public void addAlquiler(Alquiler a) {
        String sql = "INSERT INTO alquileres(documento, locker_id, fechaInicio, estado) VALUES(?, ?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getEstudiante().getDocumento());
            ps.setInt(2, a.getLocker().getId());
            ps.setString(3, a.getFechaInicio().toString());
            ps.setString(4, "ACTIVO");
            ps.executeUpdate();

            // marcar locker como no disponible
            try (PreparedStatement ps2 = conn.prepareStatement("UPDATE lockers SET disponible = ? WHERE id = ?;")) {
                ps2.setInt(1, 0);
                ps2.setInt(2, a.getLocker().getId());
                ps2.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean liberarLocker(int lockerId) {
        String sqlUpdateLocker = "UPDATE lockers SET disponible = ? WHERE id = ?;";
        String sqlUpdateAlquiler = "UPDATE alquileres SET estado = 'INACTIVO' WHERE locker_id = ? AND estado = 'ACTIVO';";
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateLocker)) {
                ps.setInt(1, 1);
                ps.setInt(2, lockerId);
                ps.executeUpdate();
            }
            try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateAlquiler)) {
                ps2.setInt(1, lockerId);
                ps2.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Alquiler> getAlquileres() {
        List<Alquiler> list = new ArrayList<>();
        String sql = "SELECT a.documento, a.locker_id, a.fechaInicio, a.estado, e.nombre FROM alquileres a JOIN estudiantes e ON a.documento = e.documento ORDER BY a.id;";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante est = new Estudiante(rs.getString("nombre"), rs.getString("documento"));
                Locker l = new Locker(rs.getInt("locker_id"));
                l.setDisponible(false);
                Alquiler a = new Alquiler(est, l, LocalDateTime.parse(rs.getString("fechaInicio")));
                list.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
