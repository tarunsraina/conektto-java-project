package com.ezapi.api.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TblClmClaimsMasterMapperTest {

    private TblClmClaimsMasterMapper tblClmClaimsMasterMapper;

    @BeforeEach
    public void setUp() {
        tblClmClaimsMasterMapper = new TblClmClaimsMasterMapperImpl();
    }
}
