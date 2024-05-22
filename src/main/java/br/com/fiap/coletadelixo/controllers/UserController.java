package br.com.fiap.coletadelixo.controllers;

import br.com.fiap.coletadelixo.dtos.UserExhibitionDTO;
import br.com.fiap.coletadelixo.enums.Status;
import br.com.fiap.coletadelixo.models.Scheduling;
import br.com.fiap.coletadelixo.models.User;
import br.com.fiap.coletadelixo.services.AddressService;
import br.com.fiap.coletadelixo.services.SchedulingService;
import br.com.fiap.coletadelixo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    SchedulingService schedulingService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @PostMapping("/create-scheduling")
    public ResponseEntity<Scheduling> createScheduling(@RequestBody Scheduling scheduling, Principal principal) {
        UserExhibitionDTO userLogged = userService.getUserByEmail(principal.getName());
        scheduling.setStatus(Status.PENDING);
        User user = new User();
        BeanUtils.copyProperties(userLogged, user);
        scheduling.setUser(user);
        Scheduling savedScheduling = schedulingService.createScheduling(scheduling);
        return ResponseEntity.ok(savedScheduling);
    }

    @PutMapping("/update-scheduling")
    public ResponseEntity<Scheduling> updateScheduling(@RequestBody Scheduling schedulingDetails, Principal principal) {
        UserExhibitionDTO userLogged = userService.getUserByEmail(principal.getName());
        User user = new User();
        BeanUtils.copyProperties(userLogged, user);
        schedulingDetails.setUser(user);
        Scheduling updatedScheduling = schedulingService.updateScheduling(schedulingDetails);
        return ResponseEntity.ok(updatedScheduling);
    }

    @DeleteMapping("/delete-scheduling/{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable Long id, Principal principal) {
        UserExhibitionDTO userLogged = userService.getUserByEmail(principal.getName());
        schedulingService.deleteScheduling(id, userLogged.id());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-scheduling/{id}")
    public ResponseEntity<Scheduling> getScheduling(@PathVariable Long id, Principal principal) {
        UserExhibitionDTO userLogged = userService.getUserByEmail(principal.getName());
        Scheduling scheduling = schedulingService.getSchedulingById(id, userLogged.id());
        return ResponseEntity.ok(scheduling);
    }

    @PutMapping("/confirm-scheduling/{id}")
    public ResponseEntity<Scheduling> confirmScheduling(@PathVariable Long id) {
        Scheduling scheduling = schedulingService.confirmScheduling(id);
        return ResponseEntity.ok(scheduling);
    }
}
