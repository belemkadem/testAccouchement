package org.belemkadem.accouchement.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PathologieActuelle.
 */
@Entity
@Table(name = "pathologie_actuelle")
public class PathologieActuelle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @ManyToMany(mappedBy = "pathologieActuelles")
    @JsonIgnore
    private Set<Accouchement> accouchements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public PathologieActuelle designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Set<Accouchement> getAccouchements() {
        return accouchements;
    }

    public PathologieActuelle accouchements(Set<Accouchement> accouchements) {
        this.accouchements = accouchements;
        return this;
    }

    public PathologieActuelle addAccouchement(Accouchement accouchement) {
        this.accouchements.add(accouchement);
        accouchement.getPathologieActuelles().add(this);
        return this;
    }

    public PathologieActuelle removeAccouchement(Accouchement accouchement) {
        this.accouchements.remove(accouchement);
        accouchement.getPathologieActuelles().remove(this);
        return this;
    }

    public void setAccouchements(Set<Accouchement> accouchements) {
        this.accouchements = accouchements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PathologieActuelle pathologieActuelle = (PathologieActuelle) o;
        if (pathologieActuelle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pathologieActuelle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PathologieActuelle{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            "}";
    }
}
