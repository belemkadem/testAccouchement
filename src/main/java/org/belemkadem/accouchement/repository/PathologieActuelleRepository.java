package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.PathologieActuelle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PathologieActuelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PathologieActuelleRepository extends JpaRepository<PathologieActuelle, Long> {

}
