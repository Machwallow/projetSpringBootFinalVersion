package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "proprietaire", schema = "baseoeuvre", catalog = "")
public class EntityProprietaire {
    private Integer idProprietaire;
    private String nomProprietaire;
    private String prenomProprietaire;
    private Collection<EntityOeuvrepret> oeuvrepretsByIdProprietaire;
    private Collection<EntityOeuvrevente> oeuvreventesByIdProprietaire;

    @Id
    @Column(name = "id_proprietaire", nullable = false)
    public Integer getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public void setIdProprietaire(Integer idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    @Basic
    @Column(name = "nom_proprietaire", nullable = false, length = 100)
    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    @Basic
    @Column(name = "prenom_proprietaire", nullable = true, length = 100)
    public String getPrenomProprietaire() {
        return prenomProprietaire;
    }

    public void setPrenomProprietaire(String prenomProprietaire) {
        this.prenomProprietaire = prenomProprietaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityProprietaire that = (EntityProprietaire) o;
        return Objects.equals(idProprietaire, that.idProprietaire) &&
                Objects.equals(nomProprietaire, that.nomProprietaire) &&
                Objects.equals(prenomProprietaire, that.prenomProprietaire);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProprietaire, nomProprietaire, prenomProprietaire);
    }

    @OneToMany(mappedBy = "proprietaireByIdProprietaire")
    public Collection<EntityOeuvrepret> getOeuvrepretsByIdProprietaire() {
        return oeuvrepretsByIdProprietaire;
    }

    public void setOeuvrepretsByIdProprietaire(Collection<EntityOeuvrepret> oeuvrepretsByIdProprietaire) {
        this.oeuvrepretsByIdProprietaire = oeuvrepretsByIdProprietaire;
    }

    @OneToMany(mappedBy = "proprietaireByIdProprietaire")
    public Collection<EntityOeuvrevente> getOeuvreventesByIdProprietaire() {
        return oeuvreventesByIdProprietaire;
    }

    public void setOeuvreventesByIdProprietaire(Collection<EntityOeuvrevente> oeuvreventesByIdProprietaire) {
        this.oeuvreventesByIdProprietaire = oeuvreventesByIdProprietaire;
    }
}
