package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.ComplicationNNE;
import org.belemkadem.accouchement.repository.ComplicationNNERepository;
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
 * REST controller for managing ComplicationNNE.
 */
@RestController
@RequestMapping("/api")
public class ComplicationNNEResource {

    private final Logger log = LoggerFactory.getLogger(ComplicationNNEResource.class);

    private static final String ENTITY_NAME = "complicationNNE";

    private final ComplicationNNERepository complicationNNERepository;

    public ComplicationNNEResource(ComplicationNNERepository complicationNNERepository) {
        this.complicationNNERepository = complicationNNERepository;
    }

    /**
     * POST  /complication-nnes : Create a new complicationNNE.
     *
     * @param complicationNNE the complicationNNE to create
     * @return the ResponseEntity with status 201 (Created) and with body the new complicationNNE, or with status 400 (Bad Request) if the complicationNNE has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/complication-nnes")
    public ResponseEntity<ComplicationNNE> createComplicationNNE(@RequestBody ComplicationNNE complicationNNE) throws URISyntaxException {
        log.debug("REST request to save ComplicationNNE : {}", complicationNNE);
        if (complicationNNE.getId() != null) {
            throw new BadRequestAlertException("A new complicationNNE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComplicationNNE result = complicationNNERepository.save(complicationNNE);
        return ResponseEntity.created(new URI("/api/complication-nnes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /complication-nnes : Updates an existing complicationNNE.
     *
     * @param complicationNNE the complicationNNE to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated complicationNNE,
     * or with status 400 (Bad Request) if the complicationNNE is not valid,
     * or with status 500 (Internal Server Error) if the complicationNNE couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/complication-nnes")
    public ResponseEntity<ComplicationNNE> updateComplicationNNE(@RequestBody ComplicationNNE complicationNNE) throws URISyntaxException {
        log.debug("REST request to update ComplicationNNE : {}", complicationNNE);
        if (complicationNNE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComplicationNNE result = complicationNNERepository.save(complicationNNE);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, complicationNNE.getId().toString()))
            .body(result);
    }

    /**
     * GET  /complication-nnes : get all the complicationNNES.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of complicationNNES in body
     */
    @GetMapping("/complication-nnes")
    public List<ComplicationNNE> getAllComplicationNNES() {
        log.debug("REST request to get all ComplicationNNES");
        return complicationNNERepository.findAll();
    }

    /**
     * GET  /complication-nnes/:id : get the "id" complicationNNE.
     *
     * @param id the id of the complicationNNE to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the complicationNNE, or with status 404 (Not Found)
     */
    @GetMapping("/complication-nnes/{id}")
    public ResponseEntity<ComplicationNNE> getComplicationNNE(@PathVariable Long id) {
        log.debug("REST request to get ComplicationNNE : {}", id);
        Optional<ComplicationNNE> complicationNNE = complicationNNERepository.findById(id);
        return ResponseUtil.wrapOrNotFound(complicationNNE);
    }

    /**
     * DELETE  /complication-nnes/:id : delete the "id" complicationNNE.
     *
     * @param id the id of the complicationNNE to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/complication-nnes/{id}")
    public ResponseEntity<Void> deleteComplicationNNE(@PathVariable Long id) {
        log.debug("REST request to delete ComplicationNNE : {}", id);
        complicationNNERepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
