package com.example.registroenbd2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.registroenbd2.model.Contacto;
import com.example.registroenbd2.dao.ContactoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContactoServlet", value = "/contacto")
public class ContactoServlet extends HttpServlet {

    private final ContactoDao contactoDao = new ContactoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //llamar a contactoDao para que traiga todos los contactos ya registrados para ponerlos en la lista
        List<Contacto> lista = contactoDao.getAll();
        request.setAttribute("listaContactos", lista);
        //darle los contactos al index.jsp para que los ponga en la lista
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //Rellena la lista con los contactos guardados en la BD
        Contacto nuevoContacto = new Contacto();
        nuevoContacto.setNombre(request.getParameter("nombre"));
        nuevoContacto.setApellidoPaterno(request.getParameter("apellidoPaterno"));
        nuevoContacto.setApellidoMaterno(request.getParameter("apellidoMaterno"));
        nuevoContacto.setTelefono(request.getParameter("telefono"));
        nuevoContacto.setTelefonoAlternativo(request.getParameter("telefonoAlternativo"));
        nuevoContacto.setCorreo(request.getParameter("correo"));
        nuevoContacto.setRedSocial(request.getParameter("redSocial"));

        contactoDao.create(nuevoContacto);
        
        response.sendRedirect("contacto");
    }
}