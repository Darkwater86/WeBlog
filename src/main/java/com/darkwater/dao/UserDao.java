package com.darkwater.dao;

import com.darkwater.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Repository
@Mapper
public interface UserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELD = " name,password,salt,head_url ";
    String SELECT_FIELD = " id, "+INSERT_FIELD;


    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELD,")"," values(#{name},#{password},#{salt},#{headUrl})"})
    int insertUser(User user);

    @Select({"select ",SELECT_FIELD ," from ",TABLE_NAME,"where id = #{id}" })
    User selectById(int id);

    @Select({"select ",SELECT_FIELD ," from ",TABLE_NAME,"where name = #{name}" })
    User selectByName(String name);

}
