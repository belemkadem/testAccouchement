package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.AntecedentChirurgical;
import org.belemkadem.accouchement.repository.AntecedentChirurgicalRepository;
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
 * REST controller for managing AntecedentChirurgical.
 */
@RestController
@RequestMapping("/api")
public class AntecedentChirurgicalResource {

    private final Logger log = LoggerFactory.getLogger(AntecedentChirurgicalResource.class);

    private static final String ENTITY_NAME = "antecedentChirurgical";

    private final AntecedentChirurgicalRepository antecedentChirurgicalRepository;

    public AntecedentChirurgicalResource(AntecedentChirurgicalRepository antecedentChirurgicalRepository) {
        this.antecedentChirurgicalRepository = antecedentChirurgicalRepository;
    }

    /**
     * POST  /antecedent-chirurgicals : Create a new antecedentChirurgical.
     *
     * @param antecedentChirurgical the antecedentChirurgical to create
     * @return the ResponseEntity with status 201 (Created) and with body the new antecedentChirurgical, or with status 400 (Bad Request) if the antecedentChirurgical has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/antecedent-chirurgicals")
    public ResponseEntity<AntecedentChirurgical> createAntecedentChirurgical(@RequestBody AntecedentChirurgical antecedentChirurgical) throws URISyntaxException {
        log.debug("REST request to save AntecedentChirurgical : {}", antecedentChirurgical);
        if (antecedentChirurgical.getId() != null) {
            throw new BadRequestAlertException("A new antecedentChirurgical cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AntecedentChirurgical result = antecedentChirurgicalRepository.save(antecedentChirurgical);
        return ResponseEntity.created(new URI("/api/antecedent-chirurgicals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /antecedent-chirurgicals : Updates an existing antecedentChirurgical.
     *
     * @param antecedentChirurgical the antecedentChirurgical to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated antecedentChirurgical,
     * or with status 400 (Bad Request) if the antecedentChirurgical is not valid,
     * or with status 500 (Internal Server Error) if the antecedentChirurgical couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/antecedent-chirurgicals")
    public ResponseEntity<AntecedentChirurgical> updateAntecedentChirurgical(@RequestBody AntecedentChirurgical antecedentChirurgical) throws URISyntaxException {
        log.debug("REST request to update AntecedentChirurgical : {}", antecedentChirurgical);
        if (antecedentChirurgical.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AntecedentChirurgical result = antecedentChirurgicalRepository.save(antecedentChirurgical);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, antecedentChirurgical.getId().toString()))
            .body(result);
    }

    /**
     * GET  /antecedent-chirurgicals : get all the antecedentChirurgicals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of antecedentChirurgicals in body
     */
    @GetMapping("/antecedent-chirurgicals")
    public List<AntecedentChirurgical> getAllAntecedentChirurgicals() {
        log.debug("REST request to get all AntecedentChirurgicals");
        return antecedentChirurgicalRepository.findAll();
    }

    /**
     * GET  /antecedent-chirurgicals/:id : get the "id" antecedentChirurgical.
     *
     * @param id the id of the antecedentChirurgical to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the antecedentChirurgical, or with status 404 (Not Found)
     */
    @GetMapping("/antecedent-chirurgicals/{id}")
    public ResponseEntity<AntecedentChirurgical> getAntecedentChirurgical(@PathVariable Long id) {
        log.debug("REST request to get AntecedentChirurgical : {}", id);
        Optional<AntecedentChirurgical> antecedentChirurgical = antecedentChirurgicalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(antecedentChirurgical);
    }

    /**
     * DELETE  /antecedent-chirurgicals/:id : delete the "id" antecedentChirurgical.
     *
     * @param id the id of the antecedentChirurgical to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/antecedent-chirurgicals/{id}")
    public ResponseEntity<Void> deleteAntecedentChirurgical(@PathVariable Long id) {
        log.debug("REST request to delete AntecedentChirurgical : {}", id);
        antecedentChirurgicalRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
