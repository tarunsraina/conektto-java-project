package com.ezapi.api.service.mapper;

import com.ezapi.api.domain.*;
import com.ezapi.api.service.dto.TblClmClaimsMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TblClmClaimsMaster} and its DTO {@link TblClmClaimsMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TblClmClaimsMasterMapper extends EntityMapper<TblClmClaimsMasterDTO, TblClmClaimsMaster> {}
