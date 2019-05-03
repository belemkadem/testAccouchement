package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.ComplicationNNE;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComplicationNNE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplicationNNERepository extends JpaRepository<ComplicationNNE, Long> {

}
