package com.epul.oeuvre.controller;

import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityOeuvrevente;
import com.epul.oeuvre.domains.EntityReservation;
import com.epul.oeuvre.persistence.service.ServiceAdherent;
import com.epul.oeuvre.persistence.service.ServiceOeuvrevente;
import com.epul.oeuvre.persistence.service.ServiceProprietaire;
import com.epul.oeuvre.persistence.service.ServiceReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/reservation")
@RestController
@CrossOrigin
public class ControllerReservation {

    @Autowired
    ServiceReservation serviceReservation = new ServiceReservation();

    @Autowired
    ServiceAdherent serviceAdherent = new ServiceAdherent();

    @Autowired
    ServiceOeuvrevente serviceOeuvrevente = new ServiceOeuvrevente();

    @Autowired
    ServiceProprietaire serviceProprietaire = new ServiceProprietaire();

    @RequestMapping(method = RequestMethod.GET, value = "/getReservations")
    public ModelAndView getReservations(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            HttpSession session = request.getSession();

            Integer idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());
            EntityAdherent temp = serviceAdherent.getAdherent(idAdh);

            List<EntityReservation> mesReservations = serviceReservation.getReservationsByAdherent(temp);
            List<EntityReservation> mesOeuvresReservees = serviceReservation.getReservationsByProprietaire(serviceProprietaire.getProprietaire(idAdh));

            request.setAttribute("mesReservations", mesReservations);
            request.setAttribute("mesOeuvresReservees", mesOeuvresReservees);

            destinationPage = "vues/listerReservations";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);

    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteReservation")
    public ModelAndView deleteReservation(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "idOeuvre") int idOeuvre,
                                          @RequestParam(value = "idAdh") int idAdh) throws Exception {

        String destinationPage = "";
        try {


            EntityAdherent tempA = serviceAdherent.getAdherent(idAdh);
            EntityOeuvrevente tempO = serviceOeuvrevente.getOeuvreById(idOeuvre);

            serviceReservation.deleteReservation(tempA,tempO);
            serviceOeuvrevente.updateEtatOeuvre(idOeuvre,"L");

            response.sendRedirect("/reservation/getReservations");


        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/validateReservation")
    public ModelAndView validateReservation(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "idOeuvre") int idOeuvre,
                                            @RequestParam(value = "idAdh") int idAdh) throws Exception {

        String destinationPage = "";
        try {

            EntityAdherent tempA = serviceAdherent.getAdherent(idAdh);
            EntityOeuvrevente tempO = serviceOeuvrevente.getOeuvreById(idOeuvre);

            serviceReservation.updateStatut(tempA,tempO, "confirmee");

            response.sendRedirect("/reservation/getReservations");


        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

}
