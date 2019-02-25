package br.com.mercadolivre.simios.infrastructure.controller;

import br.com.mercadolivre.simios.domain.http.DNARequest;
import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.infrastructure.services.DNAService;
import br.com.mercadolivre.simios.infrastructure.services.SimiosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by davidson on 24/02/19.
 */
@Controller
public class DNAEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(DNAEndpoint.class);

    private static final ResponseEntity<String> OK = ResponseEntity.ok("It's a simio");
    private static final ResponseEntity<String> NOK = ResponseEntity.status(HttpStatus.FORBIDDEN).body("This is not a simio");

    @Autowired
    @Qualifier("simiosPerformingService")
    private SimiosService simiosService;

    private DNAService dnaService;

    @RequestMapping(method = RequestMethod.POST, path = "simian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isSimio(DNARequest dnaRequest) {
        try {
            return simiosService.isSimio(dnaRequest.getDnaSequence()) ? OK : NOK;
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
