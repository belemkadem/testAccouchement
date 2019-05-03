package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.ComplicationPerOp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComplicationPerOp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplicationPerOpRepository extends JpaRepository<ComplicationPerOp, Long> {

}
