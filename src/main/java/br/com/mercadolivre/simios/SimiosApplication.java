package br.com.mercadolivre.simios;

import br.com.mercadolivre.simios.component.SimiosPerformaticComponent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

//@SpringBootApplication
public class SimiosApplication {

    public static void main(String[] args) throws IOException {
//	SpringApplication.run(SimiosApplication.class, args);

        File file = new File("/home/davidson/dna-10.json");
        ObjectMapper objectMapper = new ObjectMapper();
        DnaInput dnaInput = objectMapper.readValue(file, DnaInput.class);
        SimiosPerformaticComponent simiosComponent = new SimiosPerformaticComponent();

        LocalDateTime now = LocalDateTime.now();
        boolean simios = simiosComponent.isSimios(dnaInput.getDna());
        System.out.println("Simios: " + simios + " - tempo execução: " + Duration.between(now, LocalDateTime.now()));
    }


    public static class DnaInput {
        private String[] dna;

        public String[] getDna() {
	    return dna;
        }

        public void setDna(String[] dna) {
	    this.dna = dna;
        }
    }

}

