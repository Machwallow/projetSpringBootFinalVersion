package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "baseoeuvre", catalog = "")
public class EntityUtilisateur {
    private Integer numUtil;
    private String nomUtil;
    private String motPasse;
    private String role;
    private String salt;
    private Double solde;
    private String prenomUtil;


    @Id
    @Column(name = "NumUtil", nullable = false)
    public Integer getNumUtil() {
        return numUtil;
    }

    public void setNumUtil(int numUtil) {
        this.numUtil = numUtil;
    }

    public void setNumUtil(Integer numUtil) {
        this.numUtil = numUtil;
    }


    @Column(name = "NomUtil", nullable = false, length = 100)
    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    @Basic
    @Column(name = "MotPasse", nullable = false, length = 100)
    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    @Basic
    @Column(name = "salt", nullable = false, length = 100)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 100)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "solde", nullable = false, precision = 2)
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityUtilisateur that = (EntityUtilisateur) o;
        return Objects.equals(numUtil, that.numUtil) &&
                Objects.equals(nomUtil, that.nomUtil) &&
                Objects.equals(motPasse, that.motPasse) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numUtil, nomUtil, motPasse, role);
    }

    @Basic
    @Column(name = "PrenomUtil", nullable = false, length = 100)
    public String getPrenomUtil() {
        return prenomUtil;
    }

    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }
}
