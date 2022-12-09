package com.ezapi.api.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.ezapi.api.domain.TblClmClaimsMaster} entity.
 */
public class TblClmClaimsMasterDTO implements Serializable {

    private Long id;

    private ZonedDateTime cmDateReceived;

    private String cmClaimStatus;

    private String cmClaimNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCmDateReceived() {
        return cmDateReceived;
    }

    public void setCmDateReceived(ZonedDateTime cmDateReceived) {
        this.cmDateReceived = cmDateReceived;
    }

    public String getCmClaimStatus() {
        return cmClaimStatus;
    }

    public void setCmClaimStatus(String cmClaimStatus) {
        this.cmClaimStatus = cmClaimStatus;
    }

    public String getCmClaimNumber() {
        return cmClaimNumber;
    }

    public void setCmClaimNumber(String cmClaimNumber) {
        this.cmClaimNumber = cmClaimNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TblClmClaimsMasterDTO)) {
            return false;
        }

        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = (TblClmClaimsMasterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tblClmClaimsMasterDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TblClmClaimsMasterDTO{" +
            "id=" + getId() +
            ", cmDateReceived='" + getCmDateReceived() + "'" +
            ", cmClaimStatus='" + getCmClaimStatus() + "'" +
            ", cmClaimNumber='" + getCmClaimNumber() + "'" +
            "}";
    }
}
