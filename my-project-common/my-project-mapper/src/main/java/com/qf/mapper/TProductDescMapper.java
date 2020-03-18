package com.qf.mapper;

import com.qf.base.IBaseDao;
import com.qf.entity.TProductDesc;

import java.util.Map;

public interface TProductDescMapper extends IBaseDao<TProductDesc> {

    TProductDesc selectByPid(Long pid);

    void updateDescByPid(Map map);
}
