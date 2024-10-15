package com.gerenciador_tarefas.service;

import com.gerenciador_tarefas.entity.Usuario;
import com.gerenciador_tarefas.repository.IUSuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUSuarioRepository iuSuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iuSuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iuSuarioRepository.save(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        this.iuSuarioRepository.deleteById(usuario.getId());
    }
    public List<Usuario> obtemUsuarios() {
        return this.iuSuarioRepository.findAll();
    }
}
