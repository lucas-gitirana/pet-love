package com.pet_love.demo.controller;

import com.pet_love.demo.model.Usuario;
import com.pet_love.demo.model.dto.UsuarioDTO;
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
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.saveUsuario(usuarioDTO);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> editUsuario (@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id));

        usuarioDTO.setId(id);
        UsuarioDTO updatedUsuario = usuarioService.updateUsuario(usuarioDTO);
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
