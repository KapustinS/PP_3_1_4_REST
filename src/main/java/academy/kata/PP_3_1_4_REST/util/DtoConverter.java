package academy.kata.PP_3_1_4_REST.util;

import academy.kata.PP_3_1_4_REST.dto.UserDTO;
import academy.kata.PP_3_1_4_REST.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    @Autowired
    private final ModelMapper modelMapper;

    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertUserToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertDtoToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
