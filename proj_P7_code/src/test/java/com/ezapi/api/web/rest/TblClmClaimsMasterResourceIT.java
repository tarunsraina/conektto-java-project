package com.ezapi.api.web.rest;

import static com.ezapi.api.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ezapi.api.IntegrationTest;
import com.ezapi.api.domain.TblClmClaimsMaster;
import com.ezapi.api.repository.TblClmClaimsMasterRepository;
import com.ezapi.api.service.dto.TblClmClaimsMasterDTO;
import com.ezapi.api.service.mapper.TblClmClaimsMasterMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TblClmClaimsMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TblClmClaimsMasterResourceIT {

    private static final ZonedDateTime DEFAULT_CM_DATE_RECEIVED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CM_DATE_RECEIVED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CM_CLAIM_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CM_CLAIM_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CM_CLAIM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CM_CLAIM_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tbl-clm-claims-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TblClmClaimsMasterRepository tblClmClaimsMasterRepository;

    @Autowired
    private TblClmClaimsMasterMapper tblClmClaimsMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTblClmClaimsMasterMockMvc;

    private TblClmClaimsMaster tblClmClaimsMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblClmClaimsMaster createEntity(EntityManager em) {
        TblClmClaimsMaster tblClmClaimsMaster = new TblClmClaimsMaster()
            .cmDateReceived(DEFAULT_CM_DATE_RECEIVED)
            .cmClaimStatus(DEFAULT_CM_CLAIM_STATUS)
            .cmClaimNumber(DEFAULT_CM_CLAIM_NUMBER);
        return tblClmClaimsMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblClmClaimsMaster createUpdatedEntity(EntityManager em) {
        TblClmClaimsMaster tblClmClaimsMaster = new TblClmClaimsMaster()
            .cmDateReceived(UPDATED_CM_DATE_RECEIVED)
            .cmClaimStatus(UPDATED_CM_CLAIM_STATUS)
            .cmClaimNumber(UPDATED_CM_CLAIM_NUMBER);
        return tblClmClaimsMaster;
    }

    @BeforeEach
    public void initTest() {
        tblClmClaimsMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeCreate = tblClmClaimsMasterRepository.findAll().size();
        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);
        restTblClmClaimsMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeCreate + 1);
        TblClmClaimsMaster testTblClmClaimsMaster = tblClmClaimsMasterList.get(tblClmClaimsMasterList.size() - 1);
        assertThat(testTblClmClaimsMaster.getCmDateReceived()).isEqualTo(DEFAULT_CM_DATE_RECEIVED);
        assertThat(testTblClmClaimsMaster.getCmClaimStatus()).isEqualTo(DEFAULT_CM_CLAIM_STATUS);
        assertThat(testTblClmClaimsMaster.getCmClaimNumber()).isEqualTo(DEFAULT_CM_CLAIM_NUMBER);
    }

    @Test
    @Transactional
    void createTblClmClaimsMasterWithExistingId() throws Exception {
        // Create the TblClmClaimsMaster with an existing ID
        tblClmClaimsMaster.setId(1L);
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        int databaseSizeBeforeCreate = tblClmClaimsMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblClmClaimsMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTblClmClaimsMasters() throws Exception {
        // Initialize the database
        tblClmClaimsMasterRepository.saveAndFlush(tblClmClaimsMaster);

        // Get all the tblClmClaimsMasterList
        restTblClmClaimsMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblClmClaimsMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].cmDateReceived").value(hasItem(sameInstant(DEFAULT_CM_DATE_RECEIVED))))
            .andExpect(jsonPath("$.[*].cmClaimStatus").value(hasItem(DEFAULT_CM_CLAIM_STATUS)))
            .andExpect(jsonPath("$.[*].cmClaimNumber").value(hasItem(DEFAULT_CM_CLAIM_NUMBER)));
    }

    @Test
    @Transactional
    void getTblClmClaimsMaster() throws Exception {
        // Initialize the database
        tblClmClaimsMasterRepository.saveAndFlush(tblClmClaimsMaster);

        // Get the tblClmClaimsMaster
        restTblClmClaimsMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, tblClmClaimsMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tblClmClaimsMaster.getId().intValue()))
            .andExpect(jsonPath("$.cmDateReceived").value(sameInstant(DEFAULT_CM_DATE_RECEIVED)))
            .andExpect(jsonPath("$.cmClaimStatus").value(DEFAULT_CM_CLAIM_STATUS))
            .andExpect(jsonPath("$.cmClaimNumber").value(DEFAULT_CM_CLAIM_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingTblClmClaimsMaster() throws Exception {
        // Get the tblClmClaimsMaster
        restTblClmClaimsMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTblClmClaimsMaster() throws Exception {
        // Initialize the database
        tblClmClaimsMasterRepository.saveAndFlush(tblClmClaimsMaster);

        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();

        // Update the tblClmClaimsMaster
        TblClmClaimsMaster updatedTblClmClaimsMaster = tblClmClaimsMasterRepository.findById(tblClmClaimsMaster.getId()).get();
        // Disconnect from session so that the updates on updatedTblClmClaimsMaster are not directly saved in db
        em.detach(updatedTblClmClaimsMaster);
        updatedTblClmClaimsMaster
            .cmDateReceived(UPDATED_CM_DATE_RECEIVED)
            .cmClaimStatus(UPDATED_CM_CLAIM_STATUS)
            .cmClaimNumber(UPDATED_CM_CLAIM_NUMBER);
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(updatedTblClmClaimsMaster);

        restTblClmClaimsMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblClmClaimsMasterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
        TblClmClaimsMaster testTblClmClaimsMaster = tblClmClaimsMasterList.get(tblClmClaimsMasterList.size() - 1);
        assertThat(testTblClmClaimsMaster.getCmDateReceived()).isEqualTo(UPDATED_CM_DATE_RECEIVED);
        assertThat(testTblClmClaimsMaster.getCmClaimStatus()).isEqualTo(UPDATED_CM_CLAIM_STATUS);
        assertThat(testTblClmClaimsMaster.getCmClaimNumber()).isEqualTo(UPDATED_CM_CLAIM_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();
        tblClmClaimsMaster.setId(count.incrementAndGet());

        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblClmClaimsMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tblClmClaimsMasterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();
        tblClmClaimsMaster.setId(count.incrementAndGet());

        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblClmClaimsMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();
        tblClmClaimsMaster.setId(count.incrementAndGet());

        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblClmClaimsMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTblClmClaimsMasterWithPatch() throws Exception {
        // Initialize the database
        tblClmClaimsMasterRepository.saveAndFlush(tblClmClaimsMaster);

        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();

        // Update the tblClmClaimsMaster using partial update
        TblClmClaimsMaster partialUpdatedTblClmClaimsMaster = new TblClmClaimsMaster();
        partialUpdatedTblClmClaimsMaster.setId(tblClmClaimsMaster.getId());

        partialUpdatedTblClmClaimsMaster.cmClaimStatus(UPDATED_CM_CLAIM_STATUS);

        restTblClmClaimsMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblClmClaimsMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblClmClaimsMaster))
            )
            .andExpect(status().isOk());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
        TblClmClaimsMaster testTblClmClaimsMaster = tblClmClaimsMasterList.get(tblClmClaimsMasterList.size() - 1);
        assertThat(testTblClmClaimsMaster.getCmDateReceived()).isEqualTo(DEFAULT_CM_DATE_RECEIVED);
        assertThat(testTblClmClaimsMaster.getCmClaimStatus()).isEqualTo(UPDATED_CM_CLAIM_STATUS);
        assertThat(testTblClmClaimsMaster.getCmClaimNumber()).isEqualTo(DEFAULT_CM_CLAIM_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateTblClmClaimsMasterWithPatch() throws Exception {
        // Initialize the database
        tblClmClaimsMasterRepository.saveAndFlush(tblClmClaimsMaster);

        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();

        // Update the tblClmClaimsMaster using partial update
        TblClmClaimsMaster partialUpdatedTblClmClaimsMaster = new TblClmClaimsMaster();
        partialUpdatedTblClmClaimsMaster.setId(tblClmClaimsMaster.getId());

        partialUpdatedTblClmClaimsMaster
            .cmDateReceived(UPDATED_CM_DATE_RECEIVED)
            .cmClaimStatus(UPDATED_CM_CLAIM_STATUS)
            .cmClaimNumber(UPDATED_CM_CLAIM_NUMBER);

        restTblClmClaimsMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTblClmClaimsMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTblClmClaimsMaster))
            )
            .andExpect(status().isOk());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
        TblClmClaimsMaster testTblClmClaimsMaster = tblClmClaimsMasterList.get(tblClmClaimsMasterList.size() - 1);
        assertThat(testTblClmClaimsMaster.getCmDateReceived()).isEqualTo(UPDATED_CM_DATE_RECEIVED);
        assertThat(testTblClmClaimsMaster.getCmClaimStatus()).isEqualTo(UPDATED_CM_CLAIM_STATUS);
        assertThat(testTblClmClaimsMaster.getCmClaimNumber()).isEqualTo(UPDATED_CM_CLAIM_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();
        tblClmClaimsMaster.setId(count.incrementAndGet());

        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblClmClaimsMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tblClmClaimsMasterDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();
        tblClmClaimsMaster.setId(count.incrementAndGet());

        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblClmClaimsMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTblClmClaimsMaster() throws Exception {
        int databaseSizeBeforeUpdate = tblClmClaimsMasterRepository.findAll().size();
        tblClmClaimsMaster.setId(count.incrementAndGet());

        // Create the TblClmClaimsMaster
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO = tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTblClmClaimsMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tblClmClaimsMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TblClmClaimsMaster in the database
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTblClmClaimsMaster() throws Exception {
        // Initialize the database
        tblClmClaimsMasterRepository.saveAndFlush(tblClmClaimsMaster);

        int databaseSizeBeforeDelete = tblClmClaimsMasterRepository.findAll().size();

        // Delete the tblClmClaimsMaster
        restTblClmClaimsMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, tblClmClaimsMaster.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TblClmClaimsMaster> tblClmClaimsMasterList = tblClmClaimsMasterRepository.findAll();
        assertThat(tblClmClaimsMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
