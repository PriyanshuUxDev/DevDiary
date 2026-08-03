package com.Spring.DevDiary.Repository;

import com.Spring.DevDiary.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameIgnoringCase(String userName);
}
