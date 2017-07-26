package com.darkwater.dao;

import com.darkwater.model.Ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr.Darkwater on 2017/7/22.
 */
@Repository
@Mapper
//CREATE TABLE `weblog`.`ticket` (
//        `id` INT NOT NULL AUTO_INCREMENT,
//        `ticket` VARCHAR(45) NOT NULL,
//        `empried` DATETIME NOT NULL,
//        `userid` INT NOT NULL,
//        `status` INT ZEROFILL NOT NULL,
//        PRIMARY KEY (`id`));
public interface TicketDao {
    String TABLE_NAME = " ticket ";
    String INSERT_FIELD = " ticket,empried,userid,status ";
    String SELECT_FIELD = " id," + INSERT_FIELD;

    @Insert({"insert into ", TABLE_NAME, "( ", INSERT_FIELD, " ) values(#{ticket},#{empried},#{userid},#{status})"})
    public void createTicket(Ticket ticket);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME,"where id = #{id}"})
    public Ticket selectTicketById(int id);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME,"where ticket = #{ticket} and status = 0"})
    public Ticket selectTicketByTicket(String ticket);

}