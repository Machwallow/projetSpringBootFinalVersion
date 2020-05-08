package com.epul.oeuvre.controller;
import com.epul.oeuvre.domains.*;
import com.epul.oeuvre.meserreurs.InsufficientRightsException;
import com.epul.oeuvre.meserreurs.MonException;
import com.epul.oeuvre.meserreurs.WrongAdherentException;
import com.epul.oeuvre.persistence.repositories.RepositoryEntityAdherent;
import com.epul.oeuvre.persistence.service.ServiceAdherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Sort;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("/mediatheque")
@RestController
@CrossOrigin
public class ControllerAdherent {

    @Autowired
   private ServiceAdherent serviceAdherent;

/*************************************************/
/**************Tous les adhérents  ******************/
    /*************************************************/
    @RequestMapping(method = RequestMethod.GET, value = "/getAdherents")
    public ModelAndView getAllAdherents(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        String destinationPage="";
        List<EntityAdherent> mesClients = null;
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                mesClients = serviceAdherent.getAllAdherents();
                request.setAttribute("mesAdherents", mesClients);
                destinationPage = "vues/listerAdherent";

            }else {
                throw new InsufficientRightsException();
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    //MODIF -< AFFICHAGE
    @RequestMapping(method = RequestMethod.GET, value = "/gererAdherent")
    public ModelAndView getAdherent(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "id") int id) throws Exception{
        String destinationPage ="";
        try {
            HttpSession session = request.getSession();

            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                EntityAdherent adh = serviceAdherent.getAdherent(id);
                request.setAttribute("adh", adh);
                destinationPage = "vues/modifierAdherent";
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
    @RequestMapping(method = RequestMethod.POST, value = "/updateAdherent")
    public ModelAndView updateAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String destinationPage ="";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                serviceAdherent.updateAdherent(Integer.parseInt(request.getParameter("idAdh")),
                        request.getParameter("txtnom"),
                        request.getParameter("txtprenom"),
                        request.getParameter("txtville"));
                response.sendRedirect("/mediatheque/getAdherents");

            } else {
                throw new InsufficientRightsException();
            }

        } catch (WrongAdherentException wa){
            request.setAttribute("MesErreurs", wa.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    //AJOUT -> AFFICHAGE
    @RequestMapping(value = "/ajout")
    public ModelAndView ajouterAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            HttpSession session = request.getSession();

            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                destinationPage = "vues/ajouterAdherent";
            }else {
                throw new InsufficientRightsException();
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    //AJOUT -> POST
    @RequestMapping(method = RequestMethod.POST,value = "/insererAdherent")
    public ModelAndView insererAdherent(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {

                serviceAdherent.createAdherent(request.getParameter("txtnom"),
                        request.getParameter("txtprenom"),
                        request.getParameter("txtville"));

                response.sendRedirect("/mediatheque/getAdherents");
            }else {
                throw new InsufficientRightsException();
            }


        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    //DELETE -> POST
    @RequestMapping(method = RequestMethod.POST,value = "/deleteAdherent")
    public ModelAndView deleteAdherent(HttpServletRequest request,
                                        HttpServletResponse response,
                                       @RequestParam(value = "id") int id) throws Exception {

        String destinationPage = "";
        try {

            HttpSession session = request.getSession();
            if(Integer.parseInt(session.getAttribute("isAdmin").toString()) == 1) {
                serviceAdherent.deleteAdherent(id);
                response.sendRedirect("/mediatheque/getAdherents");
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

