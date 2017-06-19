package com.hdwang.dao.datajpa.firstDs;

import com.hdwang.entity.dbFirst.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

/**
 * Created by hdwang on 2017-06-17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * spring data jpa 会自动注入实现（根据方法命名规范）
     * @return
     */
    User findByNumber(String number);


    @Modifying
    @Query("delete from User u where u.id = :id")
    void deleteUser(@Param("id")int id);
}
