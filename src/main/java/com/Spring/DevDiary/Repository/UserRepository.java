package com.Spring.DevDiary.Repository;

import com.Spring.DevDiary.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE UPPER(u.userName) = UPPER(:userName)")
    User findByUserNameIgnoringCase(@Param("userName") String userName);
}
