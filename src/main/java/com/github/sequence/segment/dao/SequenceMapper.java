package com.github.sequence.segment.dao;

import com.github.sequence.segment.model.Sequence;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SequenceMapper {

    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT biz_tag, max_id, step, update_time FROM sequence")
    List<Sequence> getAllSequence();

    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step")
    })
    @Select("SELECT biz_tag, max_id, step FROM sequence WHERE biz_tag = #{tag}")
    Sequence getSequence(@Param("tag") String tag);

    @Update("UPDATE sequence SET max_id = max_id + step WHERE biz_tag = #{tag}")
    void updateMaxId(@Param("tag") String tag);

    @Update("UPDATE sequence SET max_id = max_id + #{sequence.step} WHERE biz_tag = #{sequence.key}")
    void updateMaxIdByCustomStep(@Param("sequence") Sequence sequence);

    @Select("SELECT biz_tag FROM sequence")
    List<String> getAllTags();
}
