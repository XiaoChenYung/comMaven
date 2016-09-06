package com.yxc.repository;

import com.yxc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tm on 16/9/6.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
