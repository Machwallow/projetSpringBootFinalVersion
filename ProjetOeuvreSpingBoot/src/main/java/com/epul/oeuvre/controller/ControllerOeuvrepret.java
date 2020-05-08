package com.epul.oeuvre.controller;
import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityOeuvrepret;
import com.epul.oeuvre.domains.EntityProprietaire;
import com.epul.oeuvre.meserreurs.InsufficientRightsException;
import com.epul.oeuvre.meserreurs.WrongAdherentException;
import com.epul.oeuvre.persistence.service.ServiceAdherent;
import com.epul.oeuvre.persistence.service.ServiceOeuvrepret;
import com.epul.oeuvre.persistence.service.ServiceProprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("/oeuvrepret")
@RestController
@CrossOrigin
public class ControllerOeuvrepret {

    @Autowired
    private ServiceOeuvrepret serviceOeuvrepret = new ServiceOeuvrepret();
    @Autowired
    private ServiceAdherent serviceAdherent = new ServiceAdherent();
    @Autowired
    private ServiceProprietaire serviceProprietaire = new ServiceProprietaire();

    @RequestMapping(method = RequestMethod.GET, value = "/getOeuvres")
    public ModelAndView getOeuvrespret(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        String destinationPage="";
        List<EntityOeuvrepret> mesOeuvres = null;
        try {
            mesOeuvres = serviceOeuvrepret.getOeuvres();
            request.setAttribute("mesOeuvres", mesOeuvres);
            destinationPage = "vues/listerOeuvrepret";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST, value="/emprunterOeuvre")
    public ModelAndView emprunterOeuvre(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value = "id") int id) throws Exception{

        String destinationPage="";
        try {

            HttpSession session = request.getSession();

            int idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());

            serviceOeuvrepret.changeEtatOeuvrepret(id,serviceAdherent.getAdherent(idAdh), "emprunter");

            response.sendRedirect("/oeuvrepret/getOeuvres");

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST, value="/rendreOeuvre")
    public ModelAndView rendreOeuvre(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value = "id") int id) throws Exception{

        String destinationPage="";
        try {

            HttpSession session = request.getSession();

            int idAdh = Integer.parseInt(session.getAttribute("idAdh").toString());

            serviceOeuvrepret.changeEtatOeuvrepret(id,serviceAdherent.getAdherent(idAdh), "rendre");

            response.sendRedirect("/oeuvrepret/getOeuvres");

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    //Modif : affichage
    @RequestMapping(method = RequestMethod.GET, value = "/updateViewOeuvrepret")
    public ModelAndView updateViewOeuvrepret(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "id") int id) throws Exception{
        String destinationPage ="";
        try {
            HttpSession session = request.getSession();

            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                EntityOeuvrepret oeuvre = serviceOeuvrepret.getOeuvreById(id);
                List<EntityAdherent> allAdh = serviceAdherent.getAllAdherents();
                List<EntityProprietaire> allProp = serviceProprietaire.getAllProprietaire();
                request.setAttribute("oeuvre", oeuvre);
                request.setAttribute("allAdh", allAdh);
                request.setAttribute("allProp", allProp);
                destinationPage = "vues/modifierOeuvrepret";
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
    @RequestMapping(method = RequestMethod.POST, value = "/updateOeuvrepret")
    public ModelAndView updateOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                Integer idP = Integer.parseInt(request.getParameter("idProprietaire"));
                Integer idA = null;
                if(!request.getParameter("idAdherent").equals("null"))
                    idA = Integer.parseInt(request.getParameter("idAdherent"));
                Integer idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
                String titre = request.getParameter("titre");

                EntityProprietaire tempP = serviceProprietaire.getProprietaire(idP);
                EntityAdherent tempA = serviceAdherent.getAdherent(idA);

                serviceOeuvrepret.updateOeuvre(idOeuvre, titre, tempP, tempA);

                response.sendRedirect("/oeuvrepret/getOeuvres");

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
    @RequestMapping(method = RequestMethod.GET, value = "/addViewOeuvrepret")
    public ModelAndView addViewOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {
            HttpSession session = request.getSession();

            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                List<EntityAdherent> allAdh = serviceAdherent.getAllAdherents();
                List<EntityProprietaire> allProp = serviceProprietaire.getAllProprietaire();
                request.setAttribute("allAdh", allAdh);
                request.setAttribute("allProp", allProp);
                destinationPage = "vues/addOeuvrepret";
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
    @RequestMapping(method = RequestMethod.POST, value = "/addOeuvrepret")
    public ModelAndView addOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                Integer idP = Integer.parseInt(request.getParameter("idProprietaire"));
                Integer idA = null;
                if(!request.getParameter("idAdherent").equals("null"))
                    idA = Integer.parseInt(request.getParameter("idAdherent"));
                String titre = request.getParameter("titre");

                EntityProprietaire tempP = serviceProprietaire.getProprietaire(idP);
                EntityAdherent tempA = serviceAdherent.getAdherent(idA);

                serviceOeuvrepret.addOeuvre(titre, tempP, tempA);

                response.sendRedirect("/oeuvrepret/getOeuvres");

            } else {
                throw new InsufficientRightsException();
            }

        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/deleteOeuvre")
    public ModelAndView deleteOeuvrepret(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(value = "id") int id) throws Exception {

        String destinationPage = "";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                serviceOeuvrepret.deleteOeuvreById(id);
                response.sendRedirect("/oeuvrepret/getOeuvres");
            }else {
                throw new InsufficientRightsException();
            }


        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}

