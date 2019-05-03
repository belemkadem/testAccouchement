package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.AntecedentObstetrico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AntecedentObstetrico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AntecedentObstetricoRepository extends JpaRepository<AntecedentObstetrico, Long> {

}
