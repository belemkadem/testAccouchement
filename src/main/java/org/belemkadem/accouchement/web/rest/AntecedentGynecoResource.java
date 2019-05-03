package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.AntecedentGyneco;
import org.belemkadem.accouchement.repository.AntecedentGynecoRepository;
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
 * REST controller for managing AntecedentGyneco.
 */
@RestController
@RequestMapping("/api")
public class AntecedentGynecoResource {

    private final Logger log = LoggerFactory.getLogger(AntecedentGynecoResource.class);

    private static final String ENTITY_NAME = "antecedentGyneco";

    private final AntecedentGynecoRepository antecedentGynecoRepository;

    public AntecedentGynecoResource(AntecedentGynecoRepository antecedentGynecoRepository) {
        this.antecedentGynecoRepository = antecedentGynecoRepository;
    }

    /**
     * POST  /antecedent-gynecos : Create a new antecedentGyneco.
     *
     * @param antecedentGyneco the antecedentGyneco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new antecedentGyneco, or with status 400 (Bad Request) if the antecedentGyneco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/antecedent-gynecos")
    public ResponseEntity<AntecedentGyneco> createAntecedentGyneco(@RequestBody AntecedentGyneco antecedentGyneco) throws URISyntaxException {
        log.debug("REST request to save AntecedentGyneco : {}", antecedentGyneco);
        if (antecedentGyneco.getId() != null) {
            throw new BadRequestAlertException("A new antecedentGyneco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AntecedentGyneco result = antecedentGynecoRepository.save(antecedentGyneco);
        return ResponseEntity.created(new URI("/api/antecedent-gynecos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /antecedent-gynecos : Updates an existing antecedentGyneco.
     *
     * @param antecedentGyneco the antecedentGyneco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated antecedentGyneco,
     * or with status 400 (Bad Request) if the antecedentGyneco is not valid,
     * or with status 500 (Internal Server Error) if the antecedentGyneco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/antecedent-gynecos")
    public ResponseEntity<AntecedentGyneco> updateAntecedentGyneco(@RequestBody AntecedentGyneco antecedentGyneco) throws URISyntaxException {
        log.debug("REST request to update AntecedentGyneco : {}", antecedentGyneco);
        if (antecedentGyneco.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AntecedentGyneco result = antecedentGynecoRepository.save(antecedentGyneco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, antecedentGyneco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /antecedent-gynecos : get all the antecedentGynecos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of antecedentGynecos in body
     */
    @GetMapping("/antecedent-gynecos")
    public List<AntecedentGyneco> getAllAntecedentGynecos() {
        log.debug("REST request to get all AntecedentGynecos");
        return antecedentGynecoRepository.findAll();
    }

    /**
     * GET  /antecedent-gynecos/:id : get the "id" antecedentGyneco.
     *
     * @param id the id of the antecedentGyneco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the antecedentGyneco, or with status 404 (Not Found)
     */
    @GetMapping("/antecedent-gynecos/{id}")
    public ResponseEntity<AntecedentGyneco> getAntecedentGyneco(@PathVariable Long id) {
        log.debug("REST request to get AntecedentGyneco : {}", id);
        Optional<AntecedentGyneco> antecedentGyneco = antecedentGynecoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(antecedentGyneco);
    }

    /**
     * DELETE  /antecedent-gynecos/:id : delete the "id" antecedentGyneco.
     *
     * @param id the id of the antecedentGyneco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/antecedent-gynecos/{id}")
    public ResponseEntity<Void> deleteAntecedentGyneco(@PathVariable Long id) {
        log.debug("REST request to delete AntecedentGyneco : {}", id);
        antecedentGynecoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
