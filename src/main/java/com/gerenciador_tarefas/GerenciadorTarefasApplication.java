package com.gerenciador_tarefas;

import com.gerenciador_tarefas.entity.Role;
import com.gerenciador_tarefas.entity.Usuario;
import com.gerenciador_tarefas.permissoes.PermissaoEnum;
import com.gerenciador_tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername("admin");
		usuario.setPassword("123456");

		List<Role> roles = new ArrayList<>();

		Role roleAdmin = new Role();
		roleAdmin.setNome(PermissaoEnum.ADMINSTRADOR);

		Role roleUser = new Role();
		roleUser.setNome(PermissaoEnum.USUARIO);

		roles.add(roleAdmin);
		roles.add(roleUser);

		usuario.setRoles(roles);

		usuarioService.salvarUsuario(usuario);
	}
}