package com.epul.oeuvre.controller;
import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityOeuvrepret;
import com.epul.oeuvre.domains.EntityOeuvrevente;
import com.epul.oeuvre.domains.EntityProprietaire;
import com.epul.oeuvre.meserreurs.InsufficientFundsException;
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


@RequestMapping("/oeuvrevente")
@RestController
@CrossOrigin
public class ControllerOeuvrevente{

    @Autowired
    private ServiceOeuvrevente serviceOeuvrevente = new ServiceOeuvrevente();

    @Autowired
    private ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();

    @Autowired
    private ServiceReservation serviceReservation = new ServiceReservation();

    @Autowired
    private ServiceProprietaire serviceProprietaire = new ServiceProprietaire();

    @Autowired
    private ServiceAdherent serviceAdherent = new ServiceAdherent();

    @RequestMapping(method = RequestMethod.GET, value = "/getOeuvres")
    public ModelAndView getOeuvresvente(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String destinationPage="";
        List<EntityOeuvrevente> mesOeuvresLibres = null;
        List<EntityOeuvrevente> mesOeuvresReservees = null;
        List<EntityOeuvrevente> mesOeuvresVendues = null;
            try {

                mesOeuvresLibres = serviceOeuvrevente.getOeuvresLibres();
                mesOeuvresReservees = serviceOeuvrevente.getOeuvresReservees();
                mesOeuvresVendues = serviceOeuvrevente.getOeuvresVendues();

                request.setAttribute("mesOeuvresLibres", mesOeuvresLibres);
                request.setAttribute("mesOeuvresReservees", mesOeuvresReservees);
                request.setAttribute("mesOeuvresVendues", mesOeuvresVendues);

                destinationPage = "vues/listerOeuvrevente";
            } catch (Exception e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "vues/Erreur";
            }
            return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteOeuvre")
    public ModelAndView deleteOeuvrevente(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "id") int id) throws Exception {

        String destinationPage = "";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {


                serviceOeuvrevente.deleteOeuvreById(id);
                response.sendRedirect("/oeuvrevente/getOeuvres");
            }else {
                throw new InsufficientRightsException();
            }


        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/reserverOeuvre")
    public ModelAndView reserverOeuvrevente(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "id") int id) throws Exception {

        String destinationPage = "";
        try {

            HttpSession session = request.getSession();

            Integer idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());
            EntityAdherent adh = serviceAdherent.getAdherent(idAdh);

            EntityOeuvrevente oeuvrevente = serviceOeuvrevente.getOeuvreById(id);


            EntityProprietaire prop = serviceProprietaire.getProprietaire(oeuvrevente.getProprietaireByIdProprietaire().getIdProprietaire());

            serviceReservation.addReservation(oeuvrevente, adh, prop);

            serviceOeuvrevente.updateEtatOeuvre(id, "R");

            response.sendRedirect("/oeuvrevente/getOeuvres");

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/buyOeuvre")
    public ModelAndView buyOeuvrevente(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "id") int id) throws Exception {

        String destinationPage = "";
        try {

            EntityOeuvrevente tempO = serviceOeuvrevente.getOeuvreById(id);

            HttpSession session = request.getSession();
            double solde = Double.parseDouble(session.getAttribute("solde").toString());

            if(solde >= tempO.getPrixOeuvrevente()) {

                Integer idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());
                EntityAdherent tempA = serviceAdherent.getAdherent(idAdh);
                EntityProprietaire tempP = serviceProprietaire.getProprietaire(tempA.getIdAdherent());

                if(tempP == null){
                    serviceProprietaire.addProprietaire(tempA.getIdAdherent(), tempA.getNomAdherent(), tempA.getPrenomAdherent());
                    tempP = serviceProprietaire.getProprietaire(tempA.getIdAdherent());
                }

                double newSolde = solde - tempO.getPrixOeuvrevente();
                session.setAttribute("solde", newSolde);
                serviceUtilisateur.updateSolde(session.getAttribute("nom").toString(), newSolde);
                serviceOeuvrevente.updateEtatOeuvre(id, "V");
                serviceOeuvrevente.updateProprietaireOeuvre(id, tempP);
                serviceReservation.deleteReservation(tempA, tempO);
                response.sendRedirect("/oeuvrevente/getOeuvres");
            }else {
                throw new InsufficientFundsException();
            }


        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    //Modif : affichage
    @RequestMapping(method = RequestMethod.GET, value = "/updateViewOeuvrevente")
    public ModelAndView updateViewOeuvrevente(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(value = "id") int id) throws Exception{
        String destinationPage ="";
        try {
            HttpSession session = request.getSession();

            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                EntityOeuvrevente oeuvre = serviceOeuvrevente.getOeuvreById(id);
                List<EntityProprietaire> allProp = serviceProprietaire.getAllProprietaire();
                request.setAttribute("oeuvre", oeuvre);
                request.setAttribute("allProp", allProp);
                destinationPage = "vues/modifierOeuvrevente";
            }else {
                throw new InsufficientRightsException();
            }

        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    //MODIF -> POST
    @RequestMapping(method = RequestMethod.POST, value = "/updateOeuvrevente")
    public ModelAndView updateOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                Integer idP = Integer.parseInt(request.getParameter("idProprietaire"));
                Integer idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
                Double prix = Double.parseDouble(request.getParameter("prix"));
                String titre = request.getParameter("titre");

                EntityProprietaire tempP = serviceProprietaire.getProprietaire(idP);

                serviceOeuvrevente.updateOeuvrevente(idOeuvre, titre, prix, tempP);

                response.sendRedirect("/oeuvrevente/getOeuvres");

            } else {
                throw new InsufficientRightsException();
            }

        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    //Ajout -> affichage
    @RequestMapping(method = RequestMethod.GET, value = "/addViewOeuvrevente")
    public ModelAndView addViewOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {
            HttpSession session = request.getSession();

            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                List<EntityProprietaire> allProp = serviceProprietaire.getAllProprietaire();
                request.setAttribute("allProp", allProp);
                destinationPage = "vues/addOeuvrevente";
            }else {
                throw new InsufficientRightsException();
            }

        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    //Ajout -> POST
    @RequestMapping(method = RequestMethod.POST, value = "/addOeuvrevente")
    public ModelAndView addOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                Integer idP = Integer.parseInt(request.getParameter("idProprietaire"));
                EntityProprietaire tempP = serviceProprietaire.getProprietaire(idP);
                String titre = request.getParameter("titre");
                Double prix = Double.parseDouble(request.getParameter("prix"));

                serviceOeuvrevente.addOeuvrevente(titre, prix, tempP);

                response.sendRedirect("/oeuvrevente/getOeuvres");

            } else {
                throw new InsufficientRightsException();
            }

        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

}

