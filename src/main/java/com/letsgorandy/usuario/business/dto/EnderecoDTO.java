package com.letsgorandy.usuario.business.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String rua;
    private Long numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
}
