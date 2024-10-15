package com.gerenciador_tarefas.controller;

import com.gerenciador_tarefas.entity.Usuario;
import com.gerenciador_tarefas.repository.IUSuarioRepository;
import com.gerenciador_tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IUSuarioRepository iUsuarioRepository;

    @PostMapping
    public ResponseEntity<String> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return new ResponseEntity<>("Novo usuário "+ usuarioSalvo.getUsername(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuario);
        return new ResponseEntity<>("Novo atualizado "+ usuarioAtualizado.getUsername(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtemUsuarios() {
        return new ResponseEntity<>(usuarioService.obtemUsuarios(), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUsuario(@RequestBody Usuario usuario) {
        usuarioService.excluirUsuario(usuario);
    }
}