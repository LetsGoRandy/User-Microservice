package com.letsgorandy.usuario.business.converter;

import com.letsgorandy.usuario.business.dto.EnderecoDTO;
import com.letsgorandy.usuario.business.dto.TelefoneDTO;
import com.letsgorandy.usuario.business.dto.UsuarioDTO;
import com.letsgorandy.usuario.infrastructure.entity.Endereco;
import com.letsgorandy.usuario.infrastructure.entity.Telefone;
import com.letsgorandy.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UsuarioConverter {

    //Convertendo de Entity para DTO

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecosDTO){
        return enderecosDTO.stream().map(this::paraEndereco).toList();
    }

    public  Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .bairro(enderecoDTO.getBairro())
                .estado(enderecoDTO.getEstado())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone>  paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    //Convertendo de DTO para Entity

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos){
        return enderecos.stream().map(this::paraListaEnderecoDTO).toList();
    }

    public EnderecoDTO paraListaEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .bairro(endereco.getBairro())
                .estado(endereco.getEstado())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones){
        return telefones.stream().map(this::paraListaEnderecoDTO).toList();
    }

    public TelefoneDTO paraListaEnderecoDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }


}

