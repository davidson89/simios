package br.com.mercadolivre.simios.infrastructure.controller;

import br.com.mercadolivre.simios.domain.http.DNARequest;
import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.infrastructure.services.DNAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created by davidson on 24/02/19.
 */
@RestController
public class DNAEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(DNAEndpoint.class);

    private static final ResponseEntity<String> OK = ResponseEntity.ok("It's a simio");
    private static final ResponseEntity<String> NOK = ResponseEntity.status(FORBIDDEN).body("This is not a simio");

    private final DNAService dnaService;

    @Autowired
    public DNAEndpoint(DNAService dnaService) {
        this.dnaService = dnaService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "simian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isSimio(@RequestBody DNARequest dnaRequest) {
        try {
            return dnaService.isSimio(dnaRequest.getDnaSequence()) ? OK : NOK;
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DNAStats> stats() {
        try {
            return ResponseEntity.ok(dnaService.getStats());
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
