package br.com.mercadolivre.simios;

import org.springframework.boot.SpringApplication;

import java.io.IOException;

//@SpringBootApplication
public class SimiosApplication {

    public static void main(String[] args) throws IOException {
	SpringApplication.run(SimiosApplication.class, args);
//
//        File file = new File("/home/davidson/dna-10.json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        DnaInput dnaInput = objectMapper.readValue(file, DnaInput.class);
//        SimiosPerformingService simiosComponent = new SimiosPerformingService();
//
//        LocalDateTime now = LocalDateTime.now();
//        boolean simios = simiosComponent.isSimio(dnaInput.getDna());
//        System.out.println("Simios: " + simios + " - tempo execução: " + Duration.between(now, LocalDateTime.now()));
    }
}

