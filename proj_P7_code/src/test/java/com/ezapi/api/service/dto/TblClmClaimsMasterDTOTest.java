package com.ezapi.api.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezapi.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblClmClaimsMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblClmClaimsMasterDTO.class);
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO1 = new TblClmClaimsMasterDTO();
        tblClmClaimsMasterDTO1.setId(1L);
        TblClmClaimsMasterDTO tblClmClaimsMasterDTO2 = new TblClmClaimsMasterDTO();
        assertThat(tblClmClaimsMasterDTO1).isNotEqualTo(tblClmClaimsMasterDTO2);
        tblClmClaimsMasterDTO2.setId(tblClmClaimsMasterDTO1.getId());
        assertThat(tblClmClaimsMasterDTO1).isEqualTo(tblClmClaimsMasterDTO2);
        tblClmClaimsMasterDTO2.setId(2L);
        assertThat(tblClmClaimsMasterDTO1).isNotEqualTo(tblClmClaimsMasterDTO2);
        tblClmClaimsMasterDTO1.setId(null);
        assertThat(tblClmClaimsMasterDTO1).isNotEqualTo(tblClmClaimsMasterDTO2);
    }
}
