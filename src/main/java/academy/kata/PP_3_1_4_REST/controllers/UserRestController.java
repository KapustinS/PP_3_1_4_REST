package academy.kata.PP_3_1_4_REST.controllers;

import academy.kata.PP_3_1_4_REST.dto.UserDTO;
import academy.kata.PP_3_1_4_REST.service.UserService;
import academy.kata.PP_3_1_4_REST.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;
    private final DtoConverter dtoConverter;

    @Autowired
    public UserRestController(UserService userService, DtoConverter dtoConverter) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserInfo(@AuthenticationPrincipal UserDetails userDetails){

        UserDTO userDTO = dtoConverter.convertUserToDTO(userService.
                showByEmail(userDetails.getUsername())
                .orElse(null)
        );

        return ResponseEntity.ok(userDTO);
    }

}
