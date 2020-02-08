package pers.zcy.myblogauthorize.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import pers.zcy.myblogboot.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
