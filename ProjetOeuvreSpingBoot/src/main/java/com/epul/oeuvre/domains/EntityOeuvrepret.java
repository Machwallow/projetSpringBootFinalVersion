package com.epul.oeuvre.domains;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "oeuvrepret", schema = "baseoeuvre", catalog = "")
public class EntityOeuvrepret {
    private Integer idOeuvrepret;
    private String titreOeuvrepret;
    //private Integer idProprietaire;
    private EntityProprietaire proprietaireByIdProprietaire;
    //private Integer idAdherent;
    private EntityAdherent adherentByIdAdherent;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_oeuvrepret", nullable = false)
    public Integer getIdOeuvrepret() {
        return idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    public void setIdOeuvrepret(Integer idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    @Basic
    @Column(name = "titre_oeuvrepret", nullable = false, length = 200)
    public String getTitreOeuvrepret() {
        return titreOeuvrepret;
    }

    public void setTitreOeuvrepret(String titreOeuvrepret) {
        this.titreOeuvrepret = titreOeuvrepret;
    }

    /*@Basic
    @Column(name = "id_proprietaire", insertable =true, updatable=true, nullable = true)
    public Integer getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(Integer idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    @Basic
    @Column(name = "id_adherent", insertable =true, updatable=true, nullable = true)
    public Integer getIdAdherent() { return idAdherent; }

    public void setIdAdherent(Integer idAdherent) { this.idAdherent = idAdherent; }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityOeuvrepret that = (EntityOeuvrepret) o;
        return Objects.equals(idOeuvrepret, that.idOeuvrepret) &&
                Objects.equals(titreOeuvrepret, that.titreOeuvrepret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOeuvrepret, titreOeuvrepret);
    }

    @ManyToOne
    @JoinColumn(name = "id_proprietaire", referencedColumnName = "id_proprietaire")
    public EntityProprietaire getProprietaireByIdProprietaire() {
        return proprietaireByIdProprietaire;
    }

    public void setProprietaireByIdProprietaire(EntityProprietaire proprietaireByIdProprietaire) {
        this.proprietaireByIdProprietaire = proprietaireByIdProprietaire;
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent")
    public EntityAdherent getAdherentByIdAdherent() {
        return adherentByIdAdherent;
    }

    public void setAdherentByIdAdherent(EntityAdherent adherentByIdAdherent) {
        this.adherentByIdAdherent = adherentByIdAdherent;
    }
}
