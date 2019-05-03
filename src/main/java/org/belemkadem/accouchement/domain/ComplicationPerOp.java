package org.belemkadem.accouchement.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ComplicationPerOp.
 */
@Entity
@Table(name = "complication_per_op")
public class ComplicationPerOp implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @ManyToMany(mappedBy = "complicationPerOps")
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

    public ComplicationPerOp designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Set<Accouchement> getAccouchements() {
        return accouchements;
    }

    public ComplicationPerOp accouchements(Set<Accouchement> accouchements) {
        this.accouchements = accouchements;
        return this;
    }

    public ComplicationPerOp addAccouchement(Accouchement accouchement) {
        this.accouchements.add(accouchement);
        accouchement.getComplicationPerOps().add(this);
        return this;
    }

    public ComplicationPerOp removeAccouchement(Accouchement accouchement) {
        this.accouchements.remove(accouchement);
        accouchement.getComplicationPerOps().remove(this);
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
        ComplicationPerOp complicationPerOp = (ComplicationPerOp) o;
        if (complicationPerOp.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), complicationPerOp.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComplicationPerOp{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            "}";
    }
}
