package br.com.fiap.coletadelixo.controllers;

import br.com.fiap.coletadelixo.config.security.TokenService;
import br.com.fiap.coletadelixo.dtos.TokenDTO;
import br.com.fiap.coletadelixo.dtos.UserExhibitionDTO;
import br.com.fiap.coletadelixo.dtos.UserLoginDTO;
import br.com.fiap.coletadelixo.dtos.UserRegisterDTO;
import br.com.fiap.coletadelixo.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coletadelixo.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            UserLoginDTO userLoginDTO
    ){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.email(),
                        userLoginDTO.password());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){

        UserExhibitionDTO userExhibitionDTO = null;
        userExhibitionDTO = userService.createUser(userRegisterDTO);

        return ResponseEntity.ok(userExhibitionDTO);

    }
}
