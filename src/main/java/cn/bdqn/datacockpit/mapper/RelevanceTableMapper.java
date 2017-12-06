/**
 * Project Name:DataCockpit
 * File Name:RelevanceTableMapper.java
 * Package Name:cn.bdqn.datacockpit.mapper
 * Date:2017年8月29日上午9:08:59
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */
/**
 * 
 */

package cn.bdqn.datacockpit.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2017年8月29日 上午9:08:59 <br/>
 * 
 * @author jiaoHJ
 * @version
 * @see
 */

public interface RelevanceTableMapper {
    List<Map<String, Object>> selectAll() throws Exception;
}
