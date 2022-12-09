package com.ezapi.api.web.rest;

import com.ezapi.api.repository.TblClmClaimsMasterRepository;
import com.ezapi.api.service.TblClmClaimsMasterService;
import com.ezapi.api.service.dto.TblClmClaimsMasterDTO;
import com.ezapi.api.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ezapi.api.domain.TblClmClaimsMaster}.
 */
@RestController
@RequestMapping("/api")
public class TblClmClaimsMasterResource {

    private final Logger log = LoggerFactory.getLogger(TblClmClaimsMasterResource.class);

    private static final String ENTITY_NAME = "p7TblClmClaimsMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TblClmClaimsMasterService tblClmClaimsMasterService;

    private final TblClmClaimsMasterRepository tblClmClaimsMasterRepository;

    public TblClmClaimsMasterResource(
        TblClmClaimsMasterService tblClmClaimsMasterService,
        TblClmClaimsMasterRepository tblClmClaimsMasterRepository
    ) {
        this.tblClmClaimsMasterService = tblClmClaimsMasterService;
        this.tblClmClaimsMasterRepository = tblClmClaimsMasterRepository;
    }

    /**
     * {@code POST  /tbl-clm-claims-masters} : Create a new tblClmClaimsMaster.
     *
     * @param tblClmClaimsMasterDTO the tblClmClaimsMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tblClmClaimsMasterDTO, or with status {@code 400 (Bad Request)} if the tblClmClaimsMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tbl-clm-claims-masters")
    public ResponseEntity<TblClmClaimsMasterDTO> createTblClmClaimsMaster(@RequestBody TblClmClaimsMasterDTO tblClmClaimsMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save TblClmClaimsMaster : {}", tblClmClaimsMasterDTO);
        if (tblClmClaimsMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new tblClmClaimsMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblClmClaimsMasterDTO result = tblClmClaimsMasterService.save(tblClmClaimsMasterDTO);
        return ResponseEntity
            .created(new URI("/api/tbl-clm-claims-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tbl-clm-claims-masters/:id} : Updates an existing tblClmClaimsMaster.
     *
     * @param id the id of the tblClmClaimsMasterDTO to save.
     * @param tblClmClaimsMasterDTO the tblClmClaimsMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblClmClaimsMasterDTO,
     * or with status {@code 400 (Bad Request)} if the tblClmClaimsMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tblClmClaimsMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tbl-clm-claims-masters/{id}")
    public ResponseEntity<TblClmClaimsMasterDTO> updateTblClmClaimsMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TblClmClaimsMasterDTO tblClmClaimsMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TblClmClaimsMaster : {}, {}", id, tblClmClaimsMasterDTO);
        if (tblClmClaimsMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblClmClaimsMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblClmClaimsMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TblClmClaimsMasterDTO result = tblClmClaimsMasterService.save(tblClmClaimsMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblClmClaimsMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tbl-clm-claims-masters/:id} : Partial updates given fields of an existing tblClmClaimsMaster, field will ignore if it is null
     *
     * @param id the id of the tblClmClaimsMasterDTO to save.
     * @param tblClmClaimsMasterDTO the tblClmClaimsMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tblClmClaimsMasterDTO,
     * or with status {@code 400 (Bad Request)} if the tblClmClaimsMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tblClmClaimsMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tblClmClaimsMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tbl-clm-claims-masters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TblClmClaimsMasterDTO> partialUpdateTblClmClaimsMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TblClmClaimsMasterDTO tblClmClaimsMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TblClmClaimsMaster partially : {}, {}", id, tblClmClaimsMasterDTO);
        if (tblClmClaimsMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tblClmClaimsMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tblClmClaimsMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TblClmClaimsMasterDTO> result = tblClmClaimsMasterService.partialUpdate(tblClmClaimsMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tblClmClaimsMasterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tbl-clm-claims-masters} : get all the tblClmClaimsMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tblClmClaimsMasters in body.
     */
    @GetMapping("/tbl-clm-claims-masters")
    public List<TblClmClaimsMasterDTO> getAllTblClmClaimsMasters() {
        log.debug("REST request to get all TblClmClaimsMasters");
        return tblClmClaimsMasterService.findAll();
    }

    /**
     * {@code GET  /tbl-clm-claims-masters/:id} : get the "id" tblClmClaimsMaster.
     *
     * @param id the id of the tblClmClaimsMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tblClmClaimsMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tbl-clm-claims-masters/{id}")
    public ResponseEntity<TblClmClaimsMasterDTO> getTblClmClaimsMaster(@PathVariable Long id) {
        log.debug("REST request to get TblClmClaimsMaster : {}", id);
        Optional<TblClmClaimsMasterDTO> tblClmClaimsMasterDTO = tblClmClaimsMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tblClmClaimsMasterDTO);
    }

    /**
     * {@code DELETE  /tbl-clm-claims-masters/:id} : delete the "id" tblClmClaimsMaster.
     *
     * @param id the id of the tblClmClaimsMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tbl-clm-claims-masters/{id}")
    public ResponseEntity<Void> deleteTblClmClaimsMaster(@PathVariable Long id) {
        log.debug("REST request to delete TblClmClaimsMaster : {}", id);
        tblClmClaimsMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
