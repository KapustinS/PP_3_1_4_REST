package academy.kata.PP_3_1_4_REST.controllers;

import academy.kata.PP_3_1_4_REST.dto.UserDTO;
import academy.kata.PP_3_1_4_REST.model.Role;
import academy.kata.PP_3_1_4_REST.service.RoleService;
import academy.kata.PP_3_1_4_REST.service.UserService;
import academy.kata.PP_3_1_4_REST.util.DtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final DtoConverter dtoConverter;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, DtoConverter dtoConverter) {
        this.userService = userService;
        this.roleService = roleService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>>  getUsersList(){

        List<UserDTO> userDTOList = userService.listUsers()
                .stream().map(dtoConverter::convertUserToDTO)
                .toList();

        if(userDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){

        UserDTO userDTO = dtoConverter.convertUserToDTO(userService.showById(id));

        if(userDTO == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create (@RequestBody UserDTO userDTO){

        userService.add(dtoConverter.convertDtoToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update (@RequestBody UserDTO userDTO,
                                              @PathVariable("id") int id){

        userService.update(dtoConverter.convertDtoToUser(userDTO), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {

        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }


}
