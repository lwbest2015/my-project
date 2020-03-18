package com.qf.base;

public interface IBaseService<T> {

    int deleteByPrimaryKey(Long cid);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long cid);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);


}
