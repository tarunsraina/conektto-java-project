package com.ezapi.api.service.impl;

import com.ezapi.api.domain.TblClmClaimsMaster;
import com.ezapi.api.repository.TblClmClaimsMasterRepository;
import com.ezapi.api.service.TblClmClaimsMasterService;
import com.ezapi.api.service.dto.TblClmClaimsMasterDTO;
import com.ezapi.api.service.mapper.TblClmClaimsMasterMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TblClmClaimsMaster}.
 */
@Service
@Transactional
public class TblClmClaimsMasterServiceImpl implements TblClmClaimsMasterService {

    private final Logger log = LoggerFactory.getLogger(TblClmClaimsMasterServiceImpl.class);

    private final TblClmClaimsMasterRepository tblClmClaimsMasterRepository;

    private final TblClmClaimsMasterMapper tblClmClaimsMasterMapper;

    public TblClmClaimsMasterServiceImpl(
        TblClmClaimsMasterRepository tblClmClaimsMasterRepository,
        TblClmClaimsMasterMapper tblClmClaimsMasterMapper
    ) {
        this.tblClmClaimsMasterRepository = tblClmClaimsMasterRepository;
        this.tblClmClaimsMasterMapper = tblClmClaimsMasterMapper;
    }

    @Override
    public TblClmClaimsMasterDTO save(TblClmClaimsMasterDTO tblClmClaimsMasterDTO) {
        log.debug("Request to save TblClmClaimsMaster : {}", tblClmClaimsMasterDTO);
        TblClmClaimsMaster tblClmClaimsMaster = tblClmClaimsMasterMapper.toEntity(tblClmClaimsMasterDTO);
        tblClmClaimsMaster = tblClmClaimsMasterRepository.save(tblClmClaimsMaster);
        return tblClmClaimsMasterMapper.toDto(tblClmClaimsMaster);
    }

    @Override
    public Optional<TblClmClaimsMasterDTO> partialUpdate(TblClmClaimsMasterDTO tblClmClaimsMasterDTO) {
        log.debug("Request to partially update TblClmClaimsMaster : {}", tblClmClaimsMasterDTO);

        return tblClmClaimsMasterRepository
            .findById(tblClmClaimsMasterDTO.getId())
            .map(
                existingTblClmClaimsMaster -> {
                    tblClmClaimsMasterMapper.partialUpdate(existingTblClmClaimsMaster, tblClmClaimsMasterDTO);

                    return existingTblClmClaimsMaster;
                }
            )
            .map(tblClmClaimsMasterRepository::save)
            .map(tblClmClaimsMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TblClmClaimsMasterDTO> findAll() {
        log.debug("Request to get all TblClmClaimsMasters");
        return tblClmClaimsMasterRepository
            .findAll()
            .stream()
            .map(tblClmClaimsMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TblClmClaimsMasterDTO> findOne(Long id) {
        log.debug("Request to get TblClmClaimsMaster : {}", id);
        return tblClmClaimsMasterRepository.findById(id).map(tblClmClaimsMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TblClmClaimsMaster : {}", id);
        tblClmClaimsMasterRepository.deleteById(id);
    }
}
