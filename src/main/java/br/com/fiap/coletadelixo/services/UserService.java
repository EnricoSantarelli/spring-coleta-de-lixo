package br.com.fiap.coletadelixo.services;

import br.com.fiap.coletadelixo.dtos.UserExhibitionDTO;
import br.com.fiap.coletadelixo.dtos.UserRegisterDTO;
import br.com.fiap.coletadelixo.models.User;
import br.com.fiap.coletadelixo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserExhibitionDTO createUser(UserRegisterDTO userRegisterDTO) {
        String passwordEncrypted = new BCryptPasswordEncoder().encode(userRegisterDTO.password());

        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(passwordEncrypted);

        User userSaved = userRepository.save(user);
        return new UserExhibitionDTO(userSaved);
    }

    public UserExhibitionDTO getUserByEmail(String email) {
        UserDetails user = userRepository.findByEmail(email);


        if (user.isEnabled()) {
            return new UserExhibitionDTO((User) user);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    
}
