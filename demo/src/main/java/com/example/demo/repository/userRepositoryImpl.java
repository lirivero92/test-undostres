package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.exceptions.EtBadRequestExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class userRepositoryImpl implements userRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String SQL_FIND_USER="SELECT * FROM tbl_users WHERE email= ? AND ((LENGTH(password) = 32 AND password= ?) OR (password= ?)) ORDER BY trust_score DESC  LIMIT 1";
    @Override
    public User findByUserAndPassword(String user, String paswMD5,String paswSHA256) throws EtBadRequestExcepcion {
        try {
            User userresponse = jdbcTemplate.queryForObject(SQL_FIND_USER,new Object[]{user,paswMD5,paswSHA256}, UserRowMapper);
            return userresponse;
        } catch (DataAccessException e) {
            System.out.println("Error -> "+ e.toString());
        }
        return null;
    }
    private RowMapper<User> UserRowMapper=((rs, rowNum)->{
        return new User(rs.getString("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getString("password"));

    });
}
