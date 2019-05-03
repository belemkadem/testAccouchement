package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.ComplicationPosteOp;
import org.belemkadem.accouchement.repository.ComplicationPosteOpRepository;
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
 * REST controller for managing ComplicationPosteOp.
 */
@RestController
@RequestMapping("/api")
public class ComplicationPosteOpResource {

    private final Logger log = LoggerFactory.getLogger(ComplicationPosteOpResource.class);

    private static final String ENTITY_NAME = "complicationPosteOp";

    private final ComplicationPosteOpRepository complicationPosteOpRepository;

    public ComplicationPosteOpResource(ComplicationPosteOpRepository complicationPosteOpRepository) {
        this.complicationPosteOpRepository = complicationPosteOpRepository;
    }

    /**
     * POST  /complication-poste-ops : Create a new complicationPosteOp.
     *
     * @param complicationPosteOp the complicationPosteOp to create
     * @return the ResponseEntity with status 201 (Created) and with body the new complicationPosteOp, or with status 400 (Bad Request) if the complicationPosteOp has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/complication-poste-ops")
    public ResponseEntity<ComplicationPosteOp> createComplicationPosteOp(@RequestBody ComplicationPosteOp complicationPosteOp) throws URISyntaxException {
        log.debug("REST request to save ComplicationPosteOp : {}", complicationPosteOp);
        if (complicationPosteOp.getId() != null) {
            throw new BadRequestAlertException("A new complicationPosteOp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComplicationPosteOp result = complicationPosteOpRepository.save(complicationPosteOp);
        return ResponseEntity.created(new URI("/api/complication-poste-ops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /complication-poste-ops : Updates an existing complicationPosteOp.
     *
     * @param complicationPosteOp the complicationPosteOp to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated complicationPosteOp,
     * or with status 400 (Bad Request) if the complicationPosteOp is not valid,
     * or with status 500 (Internal Server Error) if the complicationPosteOp couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/complication-poste-ops")
    public ResponseEntity<ComplicationPosteOp> updateComplicationPosteOp(@RequestBody ComplicationPosteOp complicationPosteOp) throws URISyntaxException {
        log.debug("REST request to update ComplicationPosteOp : {}", complicationPosteOp);
        if (complicationPosteOp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComplicationPosteOp result = complicationPosteOpRepository.save(complicationPosteOp);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, complicationPosteOp.getId().toString()))
            .body(result);
    }

    /**
     * GET  /complication-poste-ops : get all the complicationPosteOps.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of complicationPosteOps in body
     */
    @GetMapping("/complication-poste-ops")
    public List<ComplicationPosteOp> getAllComplicationPosteOps() {
        log.debug("REST request to get all ComplicationPosteOps");
        return complicationPosteOpRepository.findAll();
    }

    /**
     * GET  /complication-poste-ops/:id : get the "id" complicationPosteOp.
     *
     * @param id the id of the complicationPosteOp to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the complicationPosteOp, or with status 404 (Not Found)
     */
    @GetMapping("/complication-poste-ops/{id}")
    public ResponseEntity<ComplicationPosteOp> getComplicationPosteOp(@PathVariable Long id) {
        log.debug("REST request to get ComplicationPosteOp : {}", id);
        Optional<ComplicationPosteOp> complicationPosteOp = complicationPosteOpRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(complicationPosteOp);
    }

    /**
     * DELETE  /complication-poste-ops/:id : delete the "id" complicationPosteOp.
     *
     * @param id the id of the complicationPosteOp to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/complication-poste-ops/{id}")
    public ResponseEntity<Void> deleteComplicationPosteOp(@PathVariable Long id) {
        log.debug("REST request to delete ComplicationPosteOp : {}", id);
        complicationPosteOpRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
