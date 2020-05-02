package com.softplan.ex2;

import com.softplan.ex2.application.IApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    IApplicationController applicationController;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(String... args) {
        String json = lerEntrada();

        System.out.println(applicationController.getPrecoDetalhadoComposicao(json));
    }

    private static String lerEntrada() {
        try {
            return String.join(" ",
                    Files.readAllLines(
                            Paths.get("./../dados-entrada-servicos-composicoes.json"),
                            StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            return "";
        }
    }
}
