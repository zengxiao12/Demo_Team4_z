/**
 * Project Name:DataCockpit
 * File Name:TablecolumninfoServiceImpl.java
 * Package Name:cn.bdqn.datacockpit.service.impl
 * Date:2017年8月25日上午11:48:50
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bdqn.datacockpit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Tablecolumninfo;
import cn.bdqn.datacockpit.mapper.TablecolumninfoMapper;
import cn.bdqn.datacockpit.service.TablecolumninfoService;

/**
 * Description:	   <br/>
 * Date:     2017年8月25日 上午11:48:50 <br/>
 * @author   caoS
 * @version  
 * @see 	 
 */
@Service
public class TablecolumninfoServiceImpl implements TablecolumninfoService {
    
    @Autowired
    TablecolumninfoMapper tablecolumninfo;
    
    @Override
    public List<Tablecolumninfo> selectAllTablecolumninfo() {
        return tablecolumninfo.selectAllTablecolumninfo();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int flag=tablecolumninfo.deleteByPrimaryKey(id);
        return flag;
    }

    @Override
    public int insert(Tablecolumninfo record) {
        int flag=tablecolumninfo.insert(record);
        return flag;
    }

    @Override
    public int insertSelective(Tablecolumninfo record) {
        int flag=tablecolumninfo.insertSelective(record);
        return flag;
    }

    @Override
    public Tablecolumninfo selectByPrimaryKey(Integer id) {
        return tablecolumninfo.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Tablecolumninfo record) {
        int flag=tablecolumninfo.updateByPrimaryKeySelective(record);
        return flag;
    }

    @Override
    public int updateByPrimaryKey(Tablecolumninfo record) {
        int flag=tablecolumninfo.updateByPrimaryKey(record);
        return flag;
    }

   

}

