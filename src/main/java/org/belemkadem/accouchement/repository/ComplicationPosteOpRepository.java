package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.ComplicationPosteOp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComplicationPosteOp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplicationPosteOpRepository extends JpaRepository<ComplicationPosteOp, Long> {

}
