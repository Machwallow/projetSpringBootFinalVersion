package com.epul.oeuvre.controller;

import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityOeuvrepret;
import com.epul.oeuvre.domains.EntityOeuvrevente;
import com.epul.oeuvre.domains.EntityProprietaire;
import com.epul.oeuvre.meserreurs.InsufficientRightsException;
import com.epul.oeuvre.meserreurs.WrongAdherentException;
import com.epul.oeuvre.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/profile")
@RestController
@CrossOrigin
public class ControllerUtilisateur {

    @Autowired
    ServiceOeuvrevente serviceOeuvrevente = new ServiceOeuvrevente();

    @Autowired
    ServiceOeuvrepret serviceOeuvrepret = new ServiceOeuvrepret();

    @Autowired
    ServiceProprietaire serviceProprietaire = new ServiceProprietaire();

    @Autowired
    ServiceAdherent serviceAdherent = new ServiceAdherent();

    @Autowired
    ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();

    @RequestMapping(method = RequestMethod.GET, value = "/getOeuvres")
    public ModelAndView getOeuvres(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String destinationPage="";
        List<EntityOeuvrepret> mesOeuvrepret = null;
        List<EntityOeuvrevente> mesOeuvrevente = null;
        List<EntityOeuvrepret> mesOeuvreempruntees = null;
        HttpSession session = request.getSession();

        try {

            Integer idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());
            EntityProprietaire tempP = serviceProprietaire.getProprietaire(idAdh);
            EntityAdherent tempA = serviceAdherent.getAdherent(idAdh);

            mesOeuvrepret = serviceOeuvrepret.getOeuvresByProprietaire(tempP);
            mesOeuvrevente = serviceOeuvrevente.getOeuvresByProprietaire(tempP);
            mesOeuvreempruntees = serviceOeuvrepret.getOeuvresByAdherent(tempA);
            request.setAttribute("mesOeuvrevente", mesOeuvrevente);
            request.setAttribute("mesOeuvrepret", mesOeuvrepret);
            request.setAttribute("mesOeuvreempruntees", mesOeuvreempruntees);

            destinationPage = "vues/listerOeuvres";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gererProfile")
    public ModelAndView getAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {
            HttpSession session = request.getSession();

            Integer idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());
            EntityAdherent temp = serviceAdherent.getAdherent(idAdh);

            request.setAttribute("adherent", temp);
            destinationPage = "vues/profile";
        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST, value="/addSolde")
    public ModelAndView addSolde(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String destinationPage="";
        try {

            HttpSession session = request.getSession();

            double solde = Double.parseDouble(session.getAttribute("solde").toString());
            double addValue = Double.parseDouble(request.getParameter("addSolde"));
            double newSolde = solde + addValue;

            int idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());
            EntityAdherent adh = serviceAdherent.getAdherent(idAdh);

            session.setAttribute("solde", newSolde);
            serviceUtilisateur.updateSolde(adh.getNomAdherent(), newSolde);

            response.sendRedirect("/profile/gererProfile");

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

}
