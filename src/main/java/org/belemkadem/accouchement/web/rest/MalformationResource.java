package org.belemkadem.accouchement.web.rest;
import org.belemkadem.accouchement.domain.Malformation;
import org.belemkadem.accouchement.repository.MalformationRepository;
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
 * REST controller for managing Malformation.
 */
@RestController
@RequestMapping("/api")
public class MalformationResource {

    private final Logger log = LoggerFactory.getLogger(MalformationResource.class);

    private static final String ENTITY_NAME = "malformation";

    private final MalformationRepository malformationRepository;

    public MalformationResource(MalformationRepository malformationRepository) {
        this.malformationRepository = malformationRepository;
    }

    /**
     * POST  /malformations : Create a new malformation.
     *
     * @param malformation the malformation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new malformation, or with status 400 (Bad Request) if the malformation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/malformations")
    public ResponseEntity<Malformation> createMalformation(@RequestBody Malformation malformation) throws URISyntaxException {
        log.debug("REST request to save Malformation : {}", malformation);
        if (malformation.getId() != null) {
            throw new BadRequestAlertException("A new malformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Malformation result = malformationRepository.save(malformation);
        return ResponseEntity.created(new URI("/api/malformations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /malformations : Updates an existing malformation.
     *
     * @param malformation the malformation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated malformation,
     * or with status 400 (Bad Request) if the malformation is not valid,
     * or with status 500 (Internal Server Error) if the malformation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/malformations")
    public ResponseEntity<Malformation> updateMalformation(@RequestBody Malformation malformation) throws URISyntaxException {
        log.debug("REST request to update Malformation : {}", malformation);
        if (malformation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Malformation result = malformationRepository.save(malformation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, malformation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /malformations : get all the malformations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of malformations in body
     */
    @GetMapping("/malformations")
    public List<Malformation> getAllMalformations() {
        log.debug("REST request to get all Malformations");
        return malformationRepository.findAll();
    }

    /**
     * GET  /malformations/:id : get the "id" malformation.
     *
     * @param id the id of the malformation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the malformation, or with status 404 (Not Found)
     */
    @GetMapping("/malformations/{id}")
    public ResponseEntity<Malformation> getMalformation(@PathVariable Long id) {
        log.debug("REST request to get Malformation : {}", id);
        Optional<Malformation> malformation = malformationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(malformation);
    }

    /**
     * DELETE  /malformations/:id : delete the "id" malformation.
     *
     * @param id the id of the malformation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/malformations/{id}")
    public ResponseEntity<Void> deleteMalformation(@PathVariable Long id) {
        log.debug("REST request to delete Malformation : {}", id);
        malformationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
