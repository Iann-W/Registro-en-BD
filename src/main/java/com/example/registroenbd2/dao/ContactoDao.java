package com.example.registroenbd2.dao;

import com.example.registroenbd2.model.Contacto;
import com.example.registroenbd2.utils.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactoDao {

    public List<Contacto> getAll() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONTACTO ORDER BY Id_Contacto DESC";

        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contacto c = new Contacto();
                c.setIdContacto(rs.getInt("Id_Contacto"));
                c.setNombre(rs.getString("Nombre"));
                c.setApellidoPaterno(rs.getString("Apellido_Paterno"));
                c.setApellidoMaterno(rs.getString("Apellido_Materno"));
                c.setTelefono(rs.getString("Telefono"));
                c.setTelefonoAlternativo(rs.getString("Telefono_Alternativo"));
                c.setCorreo(rs.getString("Correo"));
                c.setRedSocial(rs.getString("Red_Social"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void create(Contacto c) {
        //Insertar el contacto que vamos a registrar
        String sql = "INSERT INTO CONTACTO (Nombre, Apellido_Paterno, Apellido_Materno, Telefono, Telefono_Alternativo, Correo, Red_Social) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            //lugar en donde ira cada valor
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidoPaterno());
            ps.setString(3, c.getApellidoMaterno());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getTelefonoAlternativo());
            ps.setString(6, c.getCorreo());
            ps.setString(7, c.getRedSocial());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}