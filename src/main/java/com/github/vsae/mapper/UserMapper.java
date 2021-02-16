package com.github.vsae.mapper;


import com.github.vsae.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{userName} ")
    public User getUserByUsername(String userName);

    @Insert("INSERT INTO user( " +
            "id, username, password, email) " +
            "VALUES(#{userName}, #{password}, #{email}) "
    )
    int insert(User user);

}
