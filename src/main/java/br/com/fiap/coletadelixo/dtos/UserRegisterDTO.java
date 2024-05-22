package br.com.fiap.coletadelixo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        Long id,

        @NotBlank(message = "O nome do usuário é obrigatório!")
        String name,

        @NotBlank(message = "O e-mail do usuário é obrigatório!")
        @Email(message = "O e-mail do usuário não é válido!")
        String email,
        @NotBlank(message = "A senha é obrigatório")
        @Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres!")
        String password
) {
}
