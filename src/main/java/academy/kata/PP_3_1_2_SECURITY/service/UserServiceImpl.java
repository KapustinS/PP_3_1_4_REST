package academy.kata.PP_3_1_2_SECURITY.service;

import academy.kata.PP_3_1_2_SECURITY.dao.UserDao;
import academy.kata.PP_3_1_2_SECURITY.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    public User showById(int id) {
        return userDao.showById(id);
    }

    @Transactional
    public void update(User user, int id) {
        user.setId(id);
        userDao.update(user);
    }

    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }
}
