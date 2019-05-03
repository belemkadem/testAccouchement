package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.AntecedentObstetrico;
import org.belemkadem.accouchement.repository.AntecedentObstetricoRepository;
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
 * REST controller for managing AntecedentObstetrico.
 */
@RestController
@RequestMapping("/api")
public class AntecedentObstetricoResource {

    private final Logger log = LoggerFactory.getLogger(AntecedentObstetricoResource.class);

    private static final String ENTITY_NAME = "antecedentObstetrico";

    private final AntecedentObstetricoRepository antecedentObstetricoRepository;

    public AntecedentObstetricoResource(AntecedentObstetricoRepository antecedentObstetricoRepository) {
        this.antecedentObstetricoRepository = antecedentObstetricoRepository;
    }

    /**
     * POST  /antecedent-obstetricos : Create a new antecedentObstetrico.
     *
     * @param antecedentObstetrico the antecedentObstetrico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new antecedentObstetrico, or with status 400 (Bad Request) if the antecedentObstetrico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/antecedent-obstetricos")
    public ResponseEntity<AntecedentObstetrico> createAntecedentObstetrico(@RequestBody AntecedentObstetrico antecedentObstetrico) throws URISyntaxException {
        log.debug("REST request to save AntecedentObstetrico : {}", antecedentObstetrico);
        if (antecedentObstetrico.getId() != null) {
            throw new BadRequestAlertException("A new antecedentObstetrico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AntecedentObstetrico result = antecedentObstetricoRepository.save(antecedentObstetrico);
        return ResponseEntity.created(new URI("/api/antecedent-obstetricos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /antecedent-obstetricos : Updates an existing antecedentObstetrico.
     *
     * @param antecedentObstetrico the antecedentObstetrico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated antecedentObstetrico,
     * or with status 400 (Bad Request) if the antecedentObstetrico is not valid,
     * or with status 500 (Internal Server Error) if the antecedentObstetrico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/antecedent-obstetricos")
    public ResponseEntity<AntecedentObstetrico> updateAntecedentObstetrico(@RequestBody AntecedentObstetrico antecedentObstetrico) throws URISyntaxException {
        log.debug("REST request to update AntecedentObstetrico : {}", antecedentObstetrico);
        if (antecedentObstetrico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AntecedentObstetrico result = antecedentObstetricoRepository.save(antecedentObstetrico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, antecedentObstetrico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /antecedent-obstetricos : get all the antecedentObstetricos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of antecedentObstetricos in body
     */
    @GetMapping("/antecedent-obstetricos")
    public List<AntecedentObstetrico> getAllAntecedentObstetricos() {
        log.debug("REST request to get all AntecedentObstetricos");
        return antecedentObstetricoRepository.findAll();
    }

    /**
     * GET  /antecedent-obstetricos/:id : get the "id" antecedentObstetrico.
     *
     * @param id the id of the antecedentObstetrico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the antecedentObstetrico, or with status 404 (Not Found)
     */
    @GetMapping("/antecedent-obstetricos/{id}")
    public ResponseEntity<AntecedentObstetrico> getAntecedentObstetrico(@PathVariable Long id) {
        log.debug("REST request to get AntecedentObstetrico : {}", id);
        Optional<AntecedentObstetrico> antecedentObstetrico = antecedentObstetricoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(antecedentObstetrico);
    }

    /**
     * DELETE  /antecedent-obstetricos/:id : delete the "id" antecedentObstetrico.
     *
     * @param id the id of the antecedentObstetrico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/antecedent-obstetricos/{id}")
    public ResponseEntity<Void> deleteAntecedentObstetrico(@PathVariable Long id) {
        log.debug("REST request to delete AntecedentObstetrico : {}", id);
        antecedentObstetricoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
