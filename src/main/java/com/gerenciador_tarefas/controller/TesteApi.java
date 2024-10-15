package com.gerenciador_tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApi {

	@GetMapping("/teste-api")
	private String testeApiController() {
		return "Sucesso";
	}
}
