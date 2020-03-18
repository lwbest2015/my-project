package com.qf.mapper;

import com.qf.base.IBaseDao;
import com.qf.entity.TProductType;

import java.util.List;

public interface TProductTypeMapper extends IBaseDao<TProductType> {

    List<TProductType> selectAll();
}
