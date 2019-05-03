package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.IndicationCS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IndicationCS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicationCSRepository extends JpaRepository<IndicationCS, Long> {

}
