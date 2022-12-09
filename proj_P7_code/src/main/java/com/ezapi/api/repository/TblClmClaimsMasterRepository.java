package com.ezapi.api.repository;

import com.ezapi.api.domain.TblClmClaimsMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TblClmClaimsMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblClmClaimsMasterRepository extends JpaRepository<TblClmClaimsMaster, Long> {}
