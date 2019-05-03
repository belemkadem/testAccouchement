package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.AntecedentMedical;
import org.belemkadem.accouchement.repository.AntecedentMedicalRepository;
import org.belemkadem.accouchement.web.rest.errors.BadRequestAlertException;
import org.belemkadem.accouchement.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AntecedentMedical.
 */
@RestController
@RequestMapping("/api")
public class AntecedentMedicalResource {

    private final Logger log = LoggerFactory.getLogger(AntecedentMedicalResource.class);

    private static final String ENTITY_NAME = "antecedentMedical";

    private final AntecedentMedicalRepository antecedentMedicalRepository;

    public AntecedentMedicalResource(AntecedentMedicalRepository antecedentMedicalRepository) {
        this.antecedentMedicalRepository = antecedentMedicalRepository;
    }

    /**
     * POST  /antecedent-medicals : Create a new antecedentMedical.
     *
     * @param antecedentMedical the antecedentMedical to create
     * @return the ResponseEntity with status 201 (Created) and with body the new antecedentMedical, or with status 400 (Bad Request) if the antecedentMedical has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/antecedent-medicals")
    public ResponseEntity<AntecedentMedical> createAntecedentMedical(@RequestBody AntecedentMedical antecedentMedical) throws URISyntaxException {
        log.debug("REST request to save AntecedentMedical : {}", antecedentMedical);
        if (antecedentMedical.getId() != null) {
            throw new BadRequestAlertException("A new antecedentMedical cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AntecedentMedical result = antecedentMedicalRepository.save(antecedentMedical);
        return ResponseEntity.created(new URI("/api/antecedent-medicals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /antecedent-medicals : Updates an existing antecedentMedical.
     *
     * @param antecedentMedical the antecedentMedical to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated antecedentMedical,
     * or with status 400 (Bad Request) if the antecedentMedical is not valid,
     * or with status 500 (Internal Server Error) if the antecedentMedical couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/antecedent-medicals")
    public ResponseEntity<AntecedentMedical> updateAntecedentMedical(@RequestBody AntecedentMedical antecedentMedical) throws URISyntaxException {
        log.debug("REST request to update AntecedentMedical : {}", antecedentMedical);
        if (antecedentMedical.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AntecedentMedical result = antecedentMedicalRepository.save(antecedentMedical);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, antecedentMedical.getId().toString()))
            .body(result);
    }

    /**
     * GET  /antecedent-medicals : get all the antecedentMedicals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of antecedentMedicals in body
     */
    @GetMapping("/antecedent-medicals")
    public List<AntecedentMedical> getAllAntecedentMedicals() {
        log.debug("REST request to get all AntecedentMedicals");
        return antecedentMedicalRepository.findAll();
    }

    /**
     * GET  /antecedent-medicals/:id : get the "id" antecedentMedical.
     *
     * @param id the id of the antecedentMedical to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the antecedentMedical, or with status 404 (Not Found)
     */
    @GetMapping("/antecedent-medicals/{id}")
    public ResponseEntity<AntecedentMedical> getAntecedentMedical(@PathVariable Long id) {
        log.debug("REST request to get AntecedentMedical : {}", id);
        Optional<AntecedentMedical> antecedentMedical = antecedentMedicalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(antecedentMedical);
    }

    /**
     * DELETE  /antecedent-medicals/:id : delete the "id" antecedentMedical.
     *
     * @param id the id of the antecedentMedical to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/antecedent-medicals/{id}")
    public ResponseEntity<Void> deleteAntecedentMedical(@PathVariable Long id) {
        log.debug("REST request to delete AntecedentMedical : {}", id);
        antecedentMedicalRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
