# Projet Spring Boot - Aupoil Lucas

##### ! Ne pas oublier d'ajouter la base de données, fichier sql présent dans "resources" !

## Principes de l'utilisation

Gestion d'oeuvres qui peuvent être empruntées et d'oeuvres disponibles à l'achat

## Utilisateurs possibles
  
  - Roles : Admin & visiteur
  - Comptes à utiliser pour une démo : Merlot secret (Admin) | Aupoil secret (visiteur | Grand secret (visiteur)

(pour ajouter un compte, utiliser directement la bd, un visiteur doit forcément être un adhérent)

## Fonctionnalités implémentées

(un adhérent devient propriétaire lorsqu'il possède/achète une oeuvre)

  - Admin : 
      - Ajout d'oeuvres + modification + suppression (vente + prêt)
      - Ajout d'adhérent + modification + suppression
  - Visiteur
      - Emprunt pour les oeuvres en prêt
      - Reservation d'oeuvres en vente pour pouvoir les acheter
      - Le proprietaire de l'oeuvre peut accepter ou refuser une réservation, si elle est acceptée, l'utilisateur qui a fait la réservation pourra l'acheter et en deviendra le proprietaire
      - Achat d'oeuvres si la réservation est acceptée par le proprietaire
      - Gestion de votre compte (ajout d'argent sur votre compte pour acheter des oeuvres)
      
## Exemple d'utilisation pour un test global de l'application

>  - Admin :
      - test simple de toutes les fonctionnalités, ajout, suppression, ...



>  - Visiteur : Aupoil
>     - Emprunter une oeuvre disponible, consulter son profil et ses oeuvres, vérifier les réservations
>  - Visiteur : Grand
>      - Emprunter une oeuvre disponible, placer une réservation sur une oeuvre appartenant à Lucas Aupoil
>      - Ajouter des fonds si nécessaire
>      - Consultations des oeuvres 
>  - Visiteur : Aupoil
>      - Accepter la réservation de Grand
>  - Visiteur : Grand
>      - Acheter l'oeuvre réservée, vérifier qu'on est bien maintenant propriétaire de cette oeuvre (Grand est maintenant propriétaire
>  et l'admin peut le gérer en tant que tel lorsqu'il modifie les oeuvres ou qu'il en ajoute
      

  

      
      
      
      
      
      
      
      
 
