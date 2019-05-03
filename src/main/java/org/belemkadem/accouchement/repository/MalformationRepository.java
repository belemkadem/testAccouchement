package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.Malformation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Malformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MalformationRepository extends JpaRepository<Malformation, Long> {

}
