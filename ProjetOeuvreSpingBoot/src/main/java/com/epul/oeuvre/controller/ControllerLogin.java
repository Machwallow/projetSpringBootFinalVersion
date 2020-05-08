package com.epul.oeuvre.controller;


import com.epul.oeuvre.domains.EntityAdherent;
import com.epul.oeuvre.domains.EntityUtilisateur;
import com.epul.oeuvre.meserreurs.MonException;
import com.epul.oeuvre.persistence.service.ServiceAdherent;
import com.epul.oeuvre.persistence.service.ServiceUtilisateur;
import com.epul.oeuvre.utilitaires.MonMotPassHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/authentification")
@RestController
@CrossOrigin
public class ControllerLogin {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();

    @Autowired
    private ServiceAdherent serviceAdherent = new ServiceAdherent();

    @RequestMapping("/login")
    public ModelAndView pageLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("vues/formLogin");
    }

    @RequestMapping("/accueil")
    public ModelAndView pageIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("index");
    }

    ///
    //
    //// Contrôle Login
    ///
    ////
    @RequestMapping(method = RequestMethod.POST, value="/getLogin")
    public ModelAndView controleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;

        HttpSession session;

        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        String message ="";
        try {
            EntityUtilisateur unUtilisateur = serviceUtilisateur.getUtilisateurByNom(login);
            if (unUtilisateur != null) {
                try {

                    // on récupère le mot de passe
                    String mdp = unUtilisateur.getMotPasse();
                    // on génère le mot de passe avec les données de connexion
                    byte[] salt = MonMotPassHash.transformeEnBytes(unUtilisateur.getSalt());
                    char[] pwd_char = MonMotPassHash.converttoCharArray(pwd);
                    byte[] monpwdCo = MonMotPassHash.generatePasswordHash(pwd_char,salt);
                    // on récupère le mot de passe enregistré
                    byte[] mdp_byte = MonMotPassHash.transformeEnBytes(mdp);

                    if (MonMotPassHash.verifyPassword(monpwdCo,mdp_byte))
                    {
                        session = request.getSession();
                        session.setAttribute("id", unUtilisateur.getNumUtil());

                        session.setAttribute("nom", unUtilisateur.getNomUtil());

                        if(unUtilisateur.getRole().equals("admin"))
                            session.setAttribute("isAdmin", 1);
                        else
                            session.setAttribute("isAdmin", 0);

                        session.setAttribute("solde", unUtilisateur.getSolde());

                        EntityAdherent adh = serviceAdherent.getAdherent(unUtilisateur.getPrenomUtil(), unUtilisateur.getNomUtil());
                        if(adh != null) session.setAttribute("idAdh", adh.getIdAdherent());
                        destinationPage = "/index";
                        //set num adherent


                    } else {
                        message = "mot de passe erroné";
                        request.setAttribute("message", message);
                        destinationPage = "/vues/formLogin";
                    }
                } catch (Exception e) {
                    request.setAttribute("MesErreurs", e.getMessage());
                    destinationPage = "/vues/Erreur";
                }
            } else {
                message = "login erroné";
                request.setAttribute("message", message);
                destinationPage = "/vues/formLogin";
            }
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping("/disconnect")
    public ModelAndView disconnect(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        session.removeAttribute("id");
        session.invalidate();

        return new ModelAndView("/index");

    }

}
