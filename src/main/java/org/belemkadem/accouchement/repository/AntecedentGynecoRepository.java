package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.AntecedentGyneco;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AntecedentGyneco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AntecedentGynecoRepository extends JpaRepository<AntecedentGyneco, Long> {

}
