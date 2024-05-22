package br.com.fiap.coletadelixo.dtos;

import br.com.fiap.coletadelixo.models.User;

public record UserExhibitionDTO(
        Long id,
        String name,
        String email
) {
    public UserExhibitionDTO(User user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}