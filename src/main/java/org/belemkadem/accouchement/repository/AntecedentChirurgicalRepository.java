package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.AntecedentChirurgical;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AntecedentChirurgical entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AntecedentChirurgicalRepository extends JpaRepository<AntecedentChirurgical, Long> {

}
