package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.AntecedentMedical;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AntecedentMedical entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AntecedentMedicalRepository extends JpaRepository<AntecedentMedical, Long> {

}
