package com.yxc.repository;

import com.yxc.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tm on 16/9/7.
 */
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
}
