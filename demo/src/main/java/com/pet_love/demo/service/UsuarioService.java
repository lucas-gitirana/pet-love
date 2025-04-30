package com.pet_love.demo.service;

import com.pet_love.demo.model.Pessoa;
import com.pet_love.demo.model.Usuario;
import com.pet_love.demo.model.dto.UsuarioDTO;
import com.pet_love.demo.repository.PessoaRepository;
import com.pet_love.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfil(),
                usuario.getPessoa().getId()
        );
    }

    public Usuario convertFromDTO(UsuarioDTO usuarioDTO) {
        Pessoa pessoa = pessoaRepository.findById(usuarioDTO.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o id: " + usuarioDTO.getPessoaId()));

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setLogin(usuarioDTO.getLogin());

        // O PasswordEncoder codifica a senha, criando um hash único e irreversível
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        usuario.setPerfil(usuarioDTO.getPerfil());
        usuario.setPessoa(pessoa);
        return usuario;
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioService::convertToDTO);
    }

    public UsuarioDTO saveUsuario (UsuarioDTO usuarioDTO) {
        Usuario savedUsuario = usuarioRepository.save(convertFromDTO(usuarioDTO));
        return convertToDTO(savedUsuario);
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO) {
        Usuario savedUsuario = usuarioRepository.save(convertFromDTO(usuarioDTO));
        return convertToDTO(savedUsuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
