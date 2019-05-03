package org.belemkadem.accouchement.repository;

import org.belemkadem.accouchement.domain.Accouchement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Accouchement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccouchementRepository extends JpaRepository<Accouchement, Long> {

    @Query(value = "select distinct accouchement from Accouchement accouchement left join fetch accouchement.antecedentChirurgicals left join fetch accouchement.antecedentMedicals left join fetch accouchement.antecedentGynecos left join fetch accouchement.antecedentObstetricos left join fetch accouchement.indicationDeCS left join fetch accouchement.complicationPosteOps left join fetch accouchement.pathologieActuelles left join fetch accouchement.complicationPerOps left join fetch accouchement.malformations left join fetch accouchement.complicationNNES",
        countQuery = "select count(distinct accouchement) from Accouchement accouchement")
    Page<Accouchement> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct accouchement from Accouchement accouchement left join fetch accouchement.antecedentChirurgicals left join fetch accouchement.antecedentMedicals left join fetch accouchement.antecedentGynecos left join fetch accouchement.antecedentObstetricos left join fetch accouchement.indicationDeCS left join fetch accouchement.complicationPosteOps left join fetch accouchement.pathologieActuelles left join fetch accouchement.complicationPerOps left join fetch accouchement.malformations left join fetch accouchement.complicationNNES")
    List<Accouchement> findAllWithEagerRelationships();

    @Query("select accouchement from Accouchement accouchement left join fetch accouchement.antecedentChirurgicals left join fetch accouchement.antecedentMedicals left join fetch accouchement.antecedentGynecos left join fetch accouchement.antecedentObstetricos left join fetch accouchement.indicationDeCS left join fetch accouchement.complicationPosteOps left join fetch accouchement.pathologieActuelles left join fetch accouchement.complicationPerOps left join fetch accouchement.malformations left join fetch accouchement.complicationNNES where accouchement.id =:id")
    Optional<Accouchement> findOneWithEagerRelationships(@Param("id") Long id);

}
