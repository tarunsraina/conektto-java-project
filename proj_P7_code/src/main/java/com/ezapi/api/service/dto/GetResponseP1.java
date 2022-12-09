
package com.ezapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * An address similar to http://microformats.org/wiki/h-card
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "paid-amount",
    "received-date",
    "ids",
    "claim-status",
    "related-entity-ids",
    "claim-number"
})
public class GetResponseP1 {

    @JsonProperty("paid-amount")
    private Double paidAmount;
    @JsonProperty("received-date")
    private String receivedDate;
    @JsonProperty("ids")
    private List<Id> ids = new ArrayList<Id>();
    @JsonProperty("claim-status")
    private String claimStatus;
    @JsonProperty("related-entity-ids")
    private List<RelatedEntityId> relatedEntityIds = new ArrayList<RelatedEntityId>();
    @JsonProperty("claim-number")
    private String claimNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("paid-amount")
    public Double getPaidAmount() {
        return paidAmount;
    }

    @JsonProperty("paid-amount")
    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public GetResponseP1 withPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    @JsonProperty("received-date")
    public String getReceivedDate() {
        return receivedDate;
    }

    @JsonProperty("received-date")
    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public GetResponseP1 withReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    @JsonProperty("ids")
    public List<Id> getIds() {
        return ids;
    }

    @JsonProperty("ids")
    public void setIds(List<Id> ids) {
        this.ids = ids;
    }

    public GetResponseP1 withIds(List<Id> ids) {
        this.ids = ids;
        return this;
    }

    @JsonProperty("claim-status")
    public String getClaimStatus() {
        return claimStatus;
    }

    @JsonProperty("claim-status")
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public GetResponseP1 withClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
        return this;
    }

    @JsonProperty("related-entity-ids")
    public List<RelatedEntityId> getRelatedEntityIds() {
        return relatedEntityIds;
    }

    @JsonProperty("related-entity-ids")
    public void setRelatedEntityIds(List<RelatedEntityId> relatedEntityIds) {
        this.relatedEntityIds = relatedEntityIds;
    }

    public GetResponseP1 withRelatedEntityIds(List<RelatedEntityId> relatedEntityIds) {
        this.relatedEntityIds = relatedEntityIds;
        return this;
    }

    @JsonProperty("claim-number")
    public String getClaimNumber() {
        return claimNumber;
    }

    @JsonProperty("claim-number")
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public GetResponseP1 withClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public GetResponseP1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(paidAmount).append(receivedDate).append(ids).append(claimStatus).append(relatedEntityIds).append(claimNumber).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetResponseP1) == false) {
            return false;
        }
        GetResponseP1 rhs = ((GetResponseP1) other);
        return new EqualsBuilder().append(paidAmount, rhs.paidAmount).append(receivedDate, rhs.receivedDate).append(ids, rhs.ids).append(claimStatus, rhs.claimStatus).append(relatedEntityIds, rhs.relatedEntityIds).append(claimNumber, rhs.claimNumber).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
