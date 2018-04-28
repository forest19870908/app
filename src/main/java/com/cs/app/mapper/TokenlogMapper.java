package com.cs.app.mapper;

import com.cs.app.model.Tokenlog;

import java.util.List;

public interface TokenlogMapper {
    int deleteByPrimaryKey(String uuidF);

    int insert(Tokenlog record);

    int insertSelective(Tokenlog record);

    Tokenlog selectByPrimaryKey(String uuidF);

    int updateByPrimaryKeySelective(Tokenlog record);

    int updateByPrimaryKey(Tokenlog record);

    List<Tokenlog> selectList();
}