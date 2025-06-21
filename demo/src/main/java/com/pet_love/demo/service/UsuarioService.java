package com.pet_love.demo.service;

import com.pet_love.demo.model.Pessoa;
import com.pet_love.demo.model.Usuario;
import com.pet_love.demo.model.dto.UsuarioCreateDTO;
import com.pet_love.demo.model.dto.UsuarioResponseDTO;
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

    /**
     * Método para converter um objeto da classe Usuario para UsuarioResponseDTO
     * @param usuario
     * @return
     */
    public static UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getPerfil(),
                usuario.getPessoa().getId()
        );
    }

    /**
     * Método para converter um objeto da classe UsuarioCreateDTO para Usuario
     * @param usuarioCreateDTO
     * @return
     */
    public Usuario convertFromCreateDTO(UsuarioCreateDTO usuarioCreateDTO) {
        Pessoa pessoa = pessoaRepository.findById(usuarioCreateDTO.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o id: " + usuarioCreateDTO.getPessoaId()));

        Usuario usuario = new Usuario();

        if (usuarioCreateDTO.getId() != null) {
            usuario = usuarioRepository.findById(usuarioCreateDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + usuarioCreateDTO.getId()));

            usuario.setLogin(usuarioCreateDTO.getLogin());
            usuario.setPerfil(usuarioCreateDTO.getPerfil());
            usuario.setPessoa(pessoa);

            if (usuarioCreateDTO.getSenha() != null && !usuarioCreateDTO.getSenha().trim().isEmpty()) {
                // O PasswordEncoder codifica a senha, criando um hash único e irreversível
                usuario.setSenha(passwordEncoder.encode(usuarioCreateDTO.getSenha()));
            }
        } else {
            usuario.setLogin(usuarioCreateDTO.getLogin());
            usuario.setSenha(passwordEncoder.encode(usuarioCreateDTO.getSenha()));
            usuario.setPerfil(usuarioCreateDTO.getPerfil());
            usuario.setPessoa(pessoa);
        }

        return usuario;
    }

    public List<UsuarioResponseDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioService::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> getUsuarioById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioService::convertToResponseDTO);
    }

    public UsuarioResponseDTO saveUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario savedUsuario = usuarioRepository.save(convertFromCreateDTO(usuarioCreateDTO));
        return convertToResponseDTO(savedUsuario);
    }

    public UsuarioResponseDTO updateUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario savedUsuario = usuarioRepository.save(convertFromCreateDTO(usuarioCreateDTO));
        return convertToResponseDTO(savedUsuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioResponseDTO> autenticarUsuario(String login, String senha) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByLogin(login);

        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            if (passwordEncoder.matches(senha, usuario.getSenha())) {
                return Optional.of(convertToResponseDTO(usuario));
            }
        }

        return Optional.empty();
    }
}
