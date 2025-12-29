package com.letsgorandy.usuario.business;

import com.letsgorandy.usuario.business.converter.UsuarioConverter;
import com.letsgorandy.usuario.business.dto.UsuarioDTO;
import com.letsgorandy.usuario.infrastructure.entity.Usuario;
import com.letsgorandy.usuario.infrastructure.exceptions.ConflictException;
import com.letsgorandy.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.letsgorandy.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExistente(String email){
        try {
            boolean  existente = usuarioRepository.existsByEmail(email);
            if(existente){
                throw new ClassCastException("Email já cadastrado" + email);
            }
        }  catch (ConflictException e){
            throw new ConflictException("Email já cadastrado" + e.getMessage());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }


    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletarUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

}
