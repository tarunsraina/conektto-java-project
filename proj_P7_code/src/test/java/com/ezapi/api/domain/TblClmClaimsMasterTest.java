package com.ezapi.api.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezapi.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TblClmClaimsMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblClmClaimsMaster.class);
        TblClmClaimsMaster tblClmClaimsMaster1 = new TblClmClaimsMaster();
        tblClmClaimsMaster1.setId(1L);
        TblClmClaimsMaster tblClmClaimsMaster2 = new TblClmClaimsMaster();
        tblClmClaimsMaster2.setId(tblClmClaimsMaster1.getId());
        assertThat(tblClmClaimsMaster1).isEqualTo(tblClmClaimsMaster2);
        tblClmClaimsMaster2.setId(2L);
        assertThat(tblClmClaimsMaster1).isNotEqualTo(tblClmClaimsMaster2);
        tblClmClaimsMaster1.setId(null);
        assertThat(tblClmClaimsMaster1).isNotEqualTo(tblClmClaimsMaster2);
    }
}
