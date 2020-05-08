package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "oeuvrevente", schema = "baseoeuvre", catalog = "")
public class EntityOeuvrevente {
    private Integer idOeuvrevente;
    private String titreOeuvrevente;
    private String etatOeuvrevente;
    private Double prixOeuvrevente;
    //private Integer idProprietaire;
    private EntityProprietaire proprietaireByIdProprietaire;
    private Collection<EntityReservation> reservationsByIdOeuvrevente;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_oeuvrevente", nullable = false)
    public Integer getIdOeuvrevente() {
        return idOeuvrevente;
    }

    public void setIdOeuvrevente(int idOeuvrevente) {
        this.idOeuvrevente = idOeuvrevente;
    }

    public void setIdOeuvrevente(Integer idOeuvrevente) {
        this.idOeuvrevente = idOeuvrevente;
    }

    @Basic
    @Column(name = "titre_oeuvrevente", nullable = false, length = 200)
    public String getTitreOeuvrevente() {
        return titreOeuvrevente;
    }

    public void setTitreOeuvrevente(String titreOeuvrevente) {
        this.titreOeuvrevente = titreOeuvrevente;
    }

    @Basic
    @Column(name = "etat_oeuvrevente", nullable = false, length = 1)
    public String getEtatOeuvrevente() {
        return etatOeuvrevente;
    }

    public void setEtatOeuvrevente(String etatOeuvrevente) {
        this.etatOeuvrevente = etatOeuvrevente;
    }

    @Basic
    @Column(name = "prix_oeuvrevente", nullable = false, precision = 0)
    public Double getPrixOeuvrevente() {
        return prixOeuvrevente;
    }

    public void setPrixOeuvrevente(double prixOeuvrevente) {
        this.prixOeuvrevente = prixOeuvrevente;
    }

    public void setPrixOeuvrevente(Double prixOeuvrevente) {
        this.prixOeuvrevente = prixOeuvrevente;
    }

    /*@Basic
    @Column(name = "id_proprietaire", insertable =false, updatable=false, nullable = true)
    public Integer getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(Integer idProprietaire) {
        this.idProprietaire = idProprietaire;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityOeuvrevente that = (EntityOeuvrevente) o;
        return Objects.equals(idOeuvrevente, that.idOeuvrevente) &&
                Objects.equals(titreOeuvrevente, that.titreOeuvrevente) &&
                Objects.equals(etatOeuvrevente, that.etatOeuvrevente) &&
                Objects.equals(prixOeuvrevente, that.prixOeuvrevente);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOeuvrevente, titreOeuvrevente, etatOeuvrevente, prixOeuvrevente);
    }

    @ManyToOne
    @JoinColumn(name = "id_proprietaire", referencedColumnName = "id_proprietaire")
    public EntityProprietaire getProprietaireByIdProprietaire() {
        return proprietaireByIdProprietaire;
    }

    public void setProprietaireByIdProprietaire(EntityProprietaire proprietaireByIdProprietaire) {
        this.proprietaireByIdProprietaire = proprietaireByIdProprietaire;
    }

    @OneToMany(mappedBy = "oeuvreventeByIdOeuvrevente")
    public Collection<EntityReservation> getReservationsByIdOeuvrevente() {
        return reservationsByIdOeuvrevente;
    }

    public void setReservationsByIdOeuvrevente(Collection<EntityReservation> reservationsByIdOeuvrevente) {
        this.reservationsByIdOeuvrevente = reservationsByIdOeuvrevente;
    }


}
