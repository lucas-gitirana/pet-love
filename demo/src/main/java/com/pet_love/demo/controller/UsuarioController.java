package com.pet_love.demo.controller;

import com.pet_love.demo.model.dto.UsuarioCreateDTO;
import com.pet_love.demo.model.dto.UsuarioResponseDTO;
import com.pet_love.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponseDTO> addUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioResponseDTO savedUsuario = usuarioService.saveUsuario(usuarioCreateDTO);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseDTO> editUsuario(@PathVariable Long id, @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id));

        usuarioCreateDTO.setId(id);
        UsuarioResponseDTO updatedUsuario = usuarioService.updateUsuario(usuarioCreateDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if(usuarioService.getUsuarioById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id);
        }

        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
