package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.PathologieActuelle;
import org.belemkadem.accouchement.repository.PathologieActuelleRepository;
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
 * REST controller for managing PathologieActuelle.
 */
@RestController
@RequestMapping("/api")
public class PathologieActuelleResource {

    private final Logger log = LoggerFactory.getLogger(PathologieActuelleResource.class);

    private static final String ENTITY_NAME = "pathologieActuelle";

    private final PathologieActuelleRepository pathologieActuelleRepository;

    public PathologieActuelleResource(PathologieActuelleRepository pathologieActuelleRepository) {
        this.pathologieActuelleRepository = pathologieActuelleRepository;
    }

    /**
     * POST  /pathologie-actuelles : Create a new pathologieActuelle.
     *
     * @param pathologieActuelle the pathologieActuelle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pathologieActuelle, or with status 400 (Bad Request) if the pathologieActuelle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pathologie-actuelles")
    public ResponseEntity<PathologieActuelle> createPathologieActuelle(@RequestBody PathologieActuelle pathologieActuelle) throws URISyntaxException {
        log.debug("REST request to save PathologieActuelle : {}", pathologieActuelle);
        if (pathologieActuelle.getId() != null) {
            throw new BadRequestAlertException("A new pathologieActuelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PathologieActuelle result = pathologieActuelleRepository.save(pathologieActuelle);
        return ResponseEntity.created(new URI("/api/pathologie-actuelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pathologie-actuelles : Updates an existing pathologieActuelle.
     *
     * @param pathologieActuelle the pathologieActuelle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pathologieActuelle,
     * or with status 400 (Bad Request) if the pathologieActuelle is not valid,
     * or with status 500 (Internal Server Error) if the pathologieActuelle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pathologie-actuelles")
    public ResponseEntity<PathologieActuelle> updatePathologieActuelle(@RequestBody PathologieActuelle pathologieActuelle) throws URISyntaxException {
        log.debug("REST request to update PathologieActuelle : {}", pathologieActuelle);
        if (pathologieActuelle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PathologieActuelle result = pathologieActuelleRepository.save(pathologieActuelle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pathologieActuelle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pathologie-actuelles : get all the pathologieActuelles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pathologieActuelles in body
     */
    @GetMapping("/pathologie-actuelles")
    public List<PathologieActuelle> getAllPathologieActuelles() {
        log.debug("REST request to get all PathologieActuelles");
        return pathologieActuelleRepository.findAll();
    }

    /**
     * GET  /pathologie-actuelles/:id : get the "id" pathologieActuelle.
     *
     * @param id the id of the pathologieActuelle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pathologieActuelle, or with status 404 (Not Found)
     */
    @GetMapping("/pathologie-actuelles/{id}")
    public ResponseEntity<PathologieActuelle> getPathologieActuelle(@PathVariable Long id) {
        log.debug("REST request to get PathologieActuelle : {}", id);
        Optional<PathologieActuelle> pathologieActuelle = pathologieActuelleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pathologieActuelle);
    }

    /**
     * DELETE  /pathologie-actuelles/:id : delete the "id" pathologieActuelle.
     *
     * @param id the id of the pathologieActuelle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pathologie-actuelles/{id}")
    public ResponseEntity<Void> deletePathologieActuelle(@PathVariable Long id) {
        log.debug("REST request to delete PathologieActuelle : {}", id);
        pathologieActuelleRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
