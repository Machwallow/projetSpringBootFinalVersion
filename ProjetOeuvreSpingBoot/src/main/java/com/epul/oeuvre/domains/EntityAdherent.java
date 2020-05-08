package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

//list of named queries
/*
@NamedQuery(name="EntityAdherent.findEntityAdherentByPrenomAdherentAndNomAdherent",
    query="Select a from EntityAdherent a where a.prenomAdherent = :prenom and a.nomAdherent = :nom")
*/


@Entity
@Table(name = "adherent", schema = "baseoeuvre", catalog = "")
public class EntityAdherent {
    private Integer idAdherent;
    private String nomAdherent;
    private String prenomAdherent;
    private String villeAdherent;
    private Collection<EntityReservation> reservationsByIdAdherent;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_adherent", nullable = false)
    public Integer getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    public void setIdAdherent(Integer idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Basic
    @Column(name = "nom_adherent", nullable = false, length = 100)
    public String getNomAdherent() {
        return nomAdherent;
    }

    public void setNomAdherent(String nomAdherent) {
        this.nomAdherent = nomAdherent;
    }

    @Basic
    @Column(name = "prenom_adherent", nullable = true, length = 100)
    public String getPrenomAdherent() {
        return prenomAdherent;
    }

    public void setPrenomAdherent(String prenomAdherent) {
        this.prenomAdherent = prenomAdherent;
    }

    @Basic
    @Column(name = "ville_adherent", nullable = true, length = 100)
    public String getVilleAdherent() {
        return villeAdherent;
    }

    public void setVilleAdherent(String villeAdherent) {
        this.villeAdherent = villeAdherent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityAdherent that = (EntityAdherent) o;
        return Objects.equals(idAdherent, that.idAdherent) &&
                Objects.equals(nomAdherent, that.nomAdherent) &&
                Objects.equals(prenomAdherent, that.prenomAdherent) &&
                Objects.equals(villeAdherent, that.villeAdherent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAdherent, nomAdherent, prenomAdherent, villeAdherent);
    }

    @OneToMany(mappedBy = "oeuvreventeByIdOeuvrevente")
    public Collection<EntityReservation> getReservationsByIdAdherent() {
        return reservationsByIdAdherent;
    }

    public void setReservationsByIdAdherent(Collection<EntityReservation> reservationsByIdAdherent) {
        this.reservationsByIdAdherent = reservationsByIdAdherent;
    }

}
