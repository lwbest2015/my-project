package com.qf.mapper;


import base.IBaseDao;
import com.qf.entity.TProduct;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct> {

    List<TProduct> selectAll();
}
