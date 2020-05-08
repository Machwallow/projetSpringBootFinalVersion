package com.epul.oeuvre.domains;

import java.io.Serializable;
import java.util.Objects;

public class EntityReservationPK implements Serializable {
    private EntityOeuvrevente oeuvreventeByIdOeuvrevente;
    private EntityAdherent adherentByIdAdherent;

    public EntityReservationPK() {
    }

    public EntityReservationPK(EntityOeuvrevente oeuvreventeByIdOeuvrevente, EntityAdherent adherentByIdAdherent) {
        this.oeuvreventeByIdOeuvrevente = oeuvreventeByIdOeuvrevente;
        this.adherentByIdAdherent = adherentByIdAdherent;
    }

    public EntityOeuvrevente getOeuvreventeByIdOeuvrevente() {
        return oeuvreventeByIdOeuvrevente;
    }

    public void setOeuvreventeByIdOeuvrevente(EntityOeuvrevente oeuvreventeByIdOeuvrevente) {
        this.oeuvreventeByIdOeuvrevente = oeuvreventeByIdOeuvrevente;
    }

    public EntityAdherent getAdherentByIdAdherent() {
        return adherentByIdAdherent;
    }

    public void setAdherentByIdAdherent(EntityAdherent adherentByIdAdherent) {
        this.adherentByIdAdherent = adherentByIdAdherent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityReservationPK that = (EntityReservationPK) o;
        return Objects.equals(oeuvreventeByIdOeuvrevente, that.oeuvreventeByIdOeuvrevente) &&
                Objects.equals(adherentByIdAdherent, that.adherentByIdAdherent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oeuvreventeByIdOeuvrevente, adherentByIdAdherent);
    }
}
