/**
 * Project Name:DataCockpit
 * File Name:ExcelTest.java
 * Package Name:cn.bdqn.datacockpit.utils
 * Date:2017年8月27日下午4:07:37
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bdqn.datacockpit.utils;

import java.util.List;

/**
 * Description:	   <br/>
 * Date:     2017年8月27日 下午4:07:37 <br/>
 * @author   caoS
 * @version  
 * @see 	 
 */
public class ExcelTest {

    public static void main(String[] args) throws Exception {

        ExcelUtils el=new ExcelUtils();
        List<List<String>> mp=el.readXls("E:\\test1.xls");
        System.err.println(mp.get(0));
    }

}

