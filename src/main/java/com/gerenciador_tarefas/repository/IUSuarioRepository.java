package com.gerenciador_tarefas.repository;

import com.gerenciador_tarefas.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface IUSuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByUsername(String username);
}
