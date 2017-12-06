/**
 * Project Name:DataCockpit
 * File Name:AnalysistasksService.java
 * Package Name:cn.bdqn.datacockpit.service.impl
 * Date:2017年8月25日上午11:39:55
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bdqn.datacockpit.service;

import java.util.List;

import cn.bdqn.datacockpit.entity.Analysistasks;

/**
 * Description:	   <br/>
 * Date:     2017年8月25日 上午11:39:55 <br/>
 * @author   caoS
 * @version  
 * @see 	 
 */
public interface AnalysistasksService {
    List<Analysistasks> selectAllTasks();
    
    int deleteByPrimaryKey(Integer id);

    int insert(Analysistasks record);

    int insertSelective(Analysistasks record);

    Analysistasks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Analysistasks record);

    int updateByPrimaryKey(Analysistasks record);
}

