package com.darkwater.dao;

import com.darkwater.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Mr.Darkwater on 2017/7/26.
 */
@Mapper
public interface CommentDao {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = "entity_id,entity_type,uid,entity_uid,liked,status,content,date";
    String SELECT_FIELDS = "id,"+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{entity_id},#{entity_type},#{uid},#{entity_uid},#{liked},#{status},#{content},#{date})"})
    int createComment(Comment comment);

    @Select({"select * from",TABLE_NAME,"where id = #{id}"})
    Comment selectCommentById(int id);

    @Select({"select * from",TABLE_NAME,"where entity_id = #{entity_id} and entity_type = #{entity_type} order by date desc limit #(limit),#(offset)"})
    List<Comment> selectCommentBYEntity(@Param("entity_id")int entityId,@Param("entity_type")String entityType,@Param("limit")int limit,@Param("offset")int offset);

    @Update({"update",TABLE_NAME,"set status = #{status} where id = #{id}"})
    int deleteCommentById(int id);
}
