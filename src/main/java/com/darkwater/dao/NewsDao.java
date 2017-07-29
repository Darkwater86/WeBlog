package com.darkwater.dao;

import com.darkwater.model.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Darkwater on 2017/7/24.
 */
@Repository
@Mapper
public interface NewsDao {
    String TABLE_NAME = "news";
    String INSERT_FIELD = "content,title,uid,liked,type,status,date,img";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELD,") values (#{content},#{title},#{uid},#{liked},#{type},#{status},#{date},#{img})"})
    int createBlog(News news);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME," order by id desc limit #{limit},#{offset}"})
    List<News> selectNews(@Param("limit")int limit, @Param("offset")int offset);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME," where uid = #{uid} order by id desc limit #{limit},#{offset}"})
    List<News> selectNewsByUserId(@Param("limit")int limit,@Param("offset")int offset,@Param("uid")int uid);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME," where id = #{id}"})
    News selectNewsById(@Param("id")int id);

}