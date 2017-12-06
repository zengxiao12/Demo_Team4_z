/**
 * Project Name:DataCockpit
 * File Name:AnalysistasksServiceImpl.java
 * Package Name:cn.bdqn.datacockpit.service.impl
 * Date:2017年8月25日上午11:47:50
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bdqn.datacockpit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Analysistasks;
import cn.bdqn.datacockpit.mapper.AnalysistasksMapper;
import cn.bdqn.datacockpit.service.AnalysistasksService;

/**
 * Description:	   <br/>
 * Date:     2017年8月25日 上午11:47:50 <br/>
 * @author   caoS
 * @version  
 * @see 	 
 */
@Service
public class AnalysistasksServiceImpl implements AnalysistasksService {
    
    @Autowired
    AnalysistasksMapper analysis;
    
    @Override
    public List<Analysistasks> selectAllTasks() {
        return analysis.selectAllTasks();
    }
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int flag=analysis.deleteByPrimaryKey(id);
        return flag;
    }

    @Override
    public int insert(Analysistasks record) {
        Date dt = new Date();     
        record.setStarttime(dt);
        int flag=analysis.insert(record);
        return flag;
    }

    @Override
    public int insertSelective(Analysistasks record) {
        int flag=analysis.insertSelective(record);
        return flag;
    }

    @Override
    public Analysistasks selectByPrimaryKey(Integer id) {
        return analysis.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Analysistasks record) {
        Date dt = new Date();     
        record.setStarttime(dt);
        int flag=analysis.updateByPrimaryKeySelective(record);
        return flag;
    }

    @Override
    public int updateByPrimaryKey(Analysistasks record) {
        Date dt = new Date();     
        record.setStarttime(dt);
        int flag=analysis.updateByPrimaryKey(record);
        return flag;
    }

    

}

