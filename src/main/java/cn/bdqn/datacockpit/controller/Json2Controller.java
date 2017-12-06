package cn.bdqn.datacockpit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.datatable.DatatableResult;
import cn.bdqn.datacockpit.datatable.IsSearchCondition;
import cn.bdqn.datacockpit.datatable.SearchCondition;
import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Tableinfo;
import cn.bdqn.datacockpit.service.TableinfoService;
import cn.bdqn.datacockpit.service.XsTableService;
import cn.bdqn.datacockpit.utils.JdbcUtil;

/**
 * Project Name:DataCockpit
 * File Name:Json2Controller.java
 * Package Name:
 * Date:2017年8月26日下午6:18:40
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

/**
 * Description: <br/>
 * Date: 2017年8月26日 下午6:18:40 <br/>
 * 
 * @author yuanX
 * @version
 * @see
 */
@Controller
public class Json2Controller {
    @Autowired
    private XsTableService xs;

    @Autowired
    private TableinfoService ts;

    @ResponseBody
    @RequestMapping(value = "shuju_1")
    public DatatableResult<Map<String, String>> datatable2(@RequestBody SearchCondition searchCondition) {
        DatatableResult<Map<String, String>> list = new DatatableResult<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime", "1995-3-5 16:20:21");
        map.put("stopTime", "1995-7-5 16:20:21");
        map.put("zt", "已完成");
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        lists.add(map);
        list.setData(lists);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "shuju_2")
    public DatatableResult<Tableinfo> datatable(@IsSearchCondition SearchCondition searchCondition,
            HttpServletRequest req) {
        DatatableResult<Tableinfo> list = new DatatableResult<Tableinfo>();
        HttpSession session = req.getSession();
        Companyinfo cy = (Companyinfo) session.getAttribute("infos");
        Integer id = cy.getId();

        List<Tableinfo> lists = ts.selectAll(id);
        list.setData(lists);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "shujus_2")
    public DatatableResult<Tableinfo> datatable6(@RequestBody SearchCondition searchCondition, HttpServletRequest req) {
        DatatableResult<Tableinfo> list = new DatatableResult<Tableinfo>();
        HttpSession session = req.getSession();
        HttpSession session1 = req.getSession();
        String ids = (String) session1.getAttribute("No1");

        Integer id = Integer.parseInt(ids);
        List<Tableinfo> lists = ts.selectAll(id);
        System.out.println(lists);
        list.setData(lists);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "shuju_3")
    public DatatableResult<Map<String, Object>> datatable3(@IsSearchCondition SearchCondition searchCondition,
            HttpServletRequest req) {
        DatatableResult<Map<String, Object>> list = new DatatableResult<Map<String, Object>>();
        String name = req.getParameter("id");
        JdbcUtil jdbc1 = new JdbcUtil();
        ApplicationContext context = jdbc1.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        List<Map<String, Object>> lists = jdbc1.selectObj(jt, name);

        list.setData(lists);
        return list;
    }
}
