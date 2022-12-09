package com.ezapi.api.service;

import com.ezapi.api.service.dto.TblClmClaimsMasterDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ezapi.api.domain.TblClmClaimsMaster}.
 */
public interface TblClmClaimsMasterService {
    /**
     * Save a tblClmClaimsMaster.
     *
     * @param tblClmClaimsMasterDTO the entity to save.
     * @return the persisted entity.
     */
    TblClmClaimsMasterDTO save(TblClmClaimsMasterDTO tblClmClaimsMasterDTO);

    /**
     * Partially updates a tblClmClaimsMaster.
     *
     * @param tblClmClaimsMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TblClmClaimsMasterDTO> partialUpdate(TblClmClaimsMasterDTO tblClmClaimsMasterDTO);

    /**
     * Get all the tblClmClaimsMasters.
     *
     * @return the list of entities.
     */
    List<TblClmClaimsMasterDTO> findAll();

    /**
     * Get the "id" tblClmClaimsMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TblClmClaimsMasterDTO> findOne(Long id);

    /**
     * Delete the "id" tblClmClaimsMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
