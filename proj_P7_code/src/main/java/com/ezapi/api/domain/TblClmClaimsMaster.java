package com.ezapi.api.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TblClmClaimsMaster.
 */
@Entity
@Table(name = "tbl_clm_claims_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TblClmClaimsMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cm_date_received")
    private ZonedDateTime cmDateReceived;

    @Column(name = "cm_claim_status")
    private String cmClaimStatus;

    @Column(name = "cm_claim_number")
    private String cmClaimNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TblClmClaimsMaster id(Long id) {
        this.id = id;
        return this;
    }

    public ZonedDateTime getCmDateReceived() {
        return this.cmDateReceived;
    }

    public TblClmClaimsMaster cmDateReceived(ZonedDateTime cmDateReceived) {
        this.cmDateReceived = cmDateReceived;
        return this;
    }

    public void setCmDateReceived(ZonedDateTime cmDateReceived) {
        this.cmDateReceived = cmDateReceived;
    }

    public String getCmClaimStatus() {
        return this.cmClaimStatus;
    }

    public TblClmClaimsMaster cmClaimStatus(String cmClaimStatus) {
        this.cmClaimStatus = cmClaimStatus;
        return this;
    }

    public void setCmClaimStatus(String cmClaimStatus) {
        this.cmClaimStatus = cmClaimStatus;
    }

    public String getCmClaimNumber() {
        return this.cmClaimNumber;
    }

    public TblClmClaimsMaster cmClaimNumber(String cmClaimNumber) {
        this.cmClaimNumber = cmClaimNumber;
        return this;
    }

    public void setCmClaimNumber(String cmClaimNumber) {
        this.cmClaimNumber = cmClaimNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblClmClaimsMaster)) {
            return false;
        }
        return id != null && id.equals(((TblClmClaimsMaster) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblClmClaimsMaster{" +
            "id=" + getId() +
            ", cmDateReceived='" + getCmDateReceived() + "'" +
            ", cmClaimStatus='" + getCmClaimStatus() + "'" +
            ", cmClaimNumber='" + getCmClaimNumber() + "'" +
            "}";
    }
}
