package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.Accouchement;
import org.belemkadem.accouchement.repository.AccouchementRepository;
import org.belemkadem.accouchement.web.rest.errors.BadRequestAlertException;
import org.belemkadem.accouchement.web.rest.util.HeaderUtil;
import org.belemkadem.accouchement.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Accouchement.
 */
@RestController
@RequestMapping("/api")
public class AccouchementResource {

    private final Logger log = LoggerFactory.getLogger(AccouchementResource.class);

    private static final String ENTITY_NAME = "accouchement";

    private final AccouchementRepository accouchementRepository;

    public AccouchementResource(AccouchementRepository accouchementRepository) {
        this.accouchementRepository = accouchementRepository;
    }

    /**
     * POST  /accouchements : Create a new accouchement.
     *
     * @param accouchement the accouchement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accouchement, or with status 400 (Bad Request) if the accouchement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/accouchements")
    public ResponseEntity<Accouchement> createAccouchement(@Valid @RequestBody Accouchement accouchement) throws URISyntaxException {
        log.debug("REST request to save Accouchement : {}", accouchement);
        if (accouchement.getId() != null) {
            throw new BadRequestAlertException("A new accouchement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Accouchement result = accouchementRepository.save(accouchement);
        return ResponseEntity.created(new URI("/api/accouchements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /accouchements : Updates an existing accouchement.
     *
     * @param accouchement the accouchement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accouchement,
     * or with status 400 (Bad Request) if the accouchement is not valid,
     * or with status 500 (Internal Server Error) if the accouchement couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/accouchements")
    public ResponseEntity<Accouchement> updateAccouchement(@Valid @RequestBody Accouchement accouchement) throws URISyntaxException {
        log.debug("REST request to update Accouchement : {}", accouchement);
        if (accouchement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Accouchement result = accouchementRepository.save(accouchement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accouchement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /accouchements : get all the accouchements.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of accouchements in body
     */
    @GetMapping("/accouchements")
    public ResponseEntity<List<Accouchement>> getAllAccouchements(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Accouchements");
        Page<Accouchement> page;
        if (eagerload) {
            page = accouchementRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = accouchementRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/accouchements?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /accouchements/:id : get the "id" accouchement.
     *
     * @param id the id of the accouchement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accouchement, or with status 404 (Not Found)
     */
    @GetMapping("/accouchements/{id}")
    public ResponseEntity<Accouchement> getAccouchement(@PathVariable Long id) {
        log.debug("REST request to get Accouchement : {}", id);
        Optional<Accouchement> accouchement = accouchementRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(accouchement);
    }

    /**
     * DELETE  /accouchements/:id : delete the "id" accouchement.
     *
     * @param id the id of the accouchement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/accouchements/{id}")
    public ResponseEntity<Void> deleteAccouchement(@PathVariable Long id) {
        log.debug("REST request to delete Accouchement : {}", id);
        accouchementRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
