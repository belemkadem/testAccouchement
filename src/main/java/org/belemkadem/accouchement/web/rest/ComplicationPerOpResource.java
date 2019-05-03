package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.ComplicationPerOp;
import org.belemkadem.accouchement.repository.ComplicationPerOpRepository;
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
 * REST controller for managing ComplicationPerOp.
 */
@RestController
@RequestMapping("/api")
public class ComplicationPerOpResource {

    private final Logger log = LoggerFactory.getLogger(ComplicationPerOpResource.class);

    private static final String ENTITY_NAME = "complicationPerOp";

    private final ComplicationPerOpRepository complicationPerOpRepository;

    public ComplicationPerOpResource(ComplicationPerOpRepository complicationPerOpRepository) {
        this.complicationPerOpRepository = complicationPerOpRepository;
    }

    /**
     * POST  /complication-per-ops : Create a new complicationPerOp.
     *
     * @param complicationPerOp the complicationPerOp to create
     * @return the ResponseEntity with status 201 (Created) and with body the new complicationPerOp, or with status 400 (Bad Request) if the complicationPerOp has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/complication-per-ops")
    public ResponseEntity<ComplicationPerOp> createComplicationPerOp(@RequestBody ComplicationPerOp complicationPerOp) throws URISyntaxException {
        log.debug("REST request to save ComplicationPerOp : {}", complicationPerOp);
        if (complicationPerOp.getId() != null) {
            throw new BadRequestAlertException("A new complicationPerOp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComplicationPerOp result = complicationPerOpRepository.save(complicationPerOp);
        return ResponseEntity.created(new URI("/api/complication-per-ops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /complication-per-ops : Updates an existing complicationPerOp.
     *
     * @param complicationPerOp the complicationPerOp to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated complicationPerOp,
     * or with status 400 (Bad Request) if the complicationPerOp is not valid,
     * or with status 500 (Internal Server Error) if the complicationPerOp couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/complication-per-ops")
    public ResponseEntity<ComplicationPerOp> updateComplicationPerOp(@RequestBody ComplicationPerOp complicationPerOp) throws URISyntaxException {
        log.debug("REST request to update ComplicationPerOp : {}", complicationPerOp);
        if (complicationPerOp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComplicationPerOp result = complicationPerOpRepository.save(complicationPerOp);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, complicationPerOp.getId().toString()))
            .body(result);
    }

    /**
     * GET  /complication-per-ops : get all the complicationPerOps.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of complicationPerOps in body
     */
    @GetMapping("/complication-per-ops")
    public List<ComplicationPerOp> getAllComplicationPerOps() {
        log.debug("REST request to get all ComplicationPerOps");
        return complicationPerOpRepository.findAll();
    }

    /**
     * GET  /complication-per-ops/:id : get the "id" complicationPerOp.
     *
     * @param id the id of the complicationPerOp to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the complicationPerOp, or with status 404 (Not Found)
     */
    @GetMapping("/complication-per-ops/{id}")
    public ResponseEntity<ComplicationPerOp> getComplicationPerOp(@PathVariable Long id) {
        log.debug("REST request to get ComplicationPerOp : {}", id);
        Optional<ComplicationPerOp> complicationPerOp = complicationPerOpRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(complicationPerOp);
    }

    /**
     * DELETE  /complication-per-ops/:id : delete the "id" complicationPerOp.
     *
     * @param id the id of the complicationPerOp to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/complication-per-ops/{id}")
    public ResponseEntity<Void> deleteComplicationPerOp(@PathVariable Long id) {
        log.debug("REST request to delete ComplicationPerOp : {}", id);
        complicationPerOpRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
