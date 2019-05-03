package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.IndicationCS;
import org.belemkadem.accouchement.repository.IndicationCSRepository;
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
 * REST controller for managing IndicationCS.
 */
@RestController
@RequestMapping("/api")
public class IndicationCSResource {

    private final Logger log = LoggerFactory.getLogger(IndicationCSResource.class);

    private static final String ENTITY_NAME = "indicationCS";

    private final IndicationCSRepository indicationCSRepository;

    public IndicationCSResource(IndicationCSRepository indicationCSRepository) {
        this.indicationCSRepository = indicationCSRepository;
    }

    /**
     * POST  /indication-cs : Create a new indicationCS.
     *
     * @param indicationCS the indicationCS to create
     * @return the ResponseEntity with status 201 (Created) and with body the new indicationCS, or with status 400 (Bad Request) if the indicationCS has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/indication-cs")
    public ResponseEntity<IndicationCS> createIndicationCS(@RequestBody IndicationCS indicationCS) throws URISyntaxException {
        log.debug("REST request to save IndicationCS : {}", indicationCS);
        if (indicationCS.getId() != null) {
            throw new BadRequestAlertException("A new indicationCS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndicationCS result = indicationCSRepository.save(indicationCS);
        return ResponseEntity.created(new URI("/api/indication-cs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /indication-cs : Updates an existing indicationCS.
     *
     * @param indicationCS the indicationCS to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated indicationCS,
     * or with status 400 (Bad Request) if the indicationCS is not valid,
     * or with status 500 (Internal Server Error) if the indicationCS couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/indication-cs")
    public ResponseEntity<IndicationCS> updateIndicationCS(@RequestBody IndicationCS indicationCS) throws URISyntaxException {
        log.debug("REST request to update IndicationCS : {}", indicationCS);
        if (indicationCS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IndicationCS result = indicationCSRepository.save(indicationCS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, indicationCS.getId().toString()))
            .body(result);
    }

    /**
     * GET  /indication-cs : get all the indicationCS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of indicationCS in body
     */
    @GetMapping("/indication-cs")
    public List<IndicationCS> getAllIndicationCS() {
        log.debug("REST request to get all IndicationCS");
        return indicationCSRepository.findAll();
    }

    /**
     * GET  /indication-cs/:id : get the "id" indicationCS.
     *
     * @param id the id of the indicationCS to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the indicationCS, or with status 404 (Not Found)
     */
    @GetMapping("/indication-cs/{id}")
    public ResponseEntity<IndicationCS> getIndicationCS(@PathVariable Long id) {
        log.debug("REST request to get IndicationCS : {}", id);
        Optional<IndicationCS> indicationCS = indicationCSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(indicationCS);
    }

    /**
     * DELETE  /indication-cs/:id : delete the "id" indicationCS.
     *
     * @param id the id of the indicationCS to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/indication-cs/{id}")
    public ResponseEntity<Void> deleteIndicationCS(@PathVariable Long id) {
        log.debug("REST request to delete IndicationCS : {}", id);
        indicationCSRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
