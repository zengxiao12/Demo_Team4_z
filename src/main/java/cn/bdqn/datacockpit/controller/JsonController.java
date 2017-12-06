/**
 * Project Name:adminlte
 * File Name:JsonController.java
 * Package Name:cn.bdqn.lesson.controller
 * Date:2017年8月21日下午3:45:28
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 */

package cn.bdqn.datacockpit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.datatable.DatatableResult;
import cn.bdqn.datacockpit.datatable.IsSearchCondition;
import cn.bdqn.datacockpit.datatable.SearchCondition;
import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.service.CompanyinfoService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.UserinfoService;

/**
 * Description: <br/>
 * Date: 2017年8月21日 下午3:45:28 <br/>
 * 
 * @author junwen.bao@airintelli.com
 * @version
 * @see
 */
@Controller
public class JsonController {
    @Autowired
    private UserinfoService us;

    @Autowired
    private InfoService ifo;

    @Autowired
    private CompanyinfoService cfs;

    @RequestMapping(value = "dt_list")
    public DatatableResult<Userinfo> datatable(@IsSearchCondition SearchCondition searchCondition) {
        DatatableResult<Userinfo> list = new DatatableResult<Userinfo>();
        Userinfo rec = new Userinfo();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "dt_list2")
    public DatatableResult<Userinfo> datatable2(@RequestBody SearchCondition searchCondition) {
        DatatableResult<Userinfo> list = new DatatableResult<Userinfo>();
        List<Userinfo> list2 = us.selectAllUserinfo();
        list.setData(list2);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "dt_lists3")
    public DatatableResult<Info> datatable23(@RequestBody SearchCondition searchCondition) {
        DatatableResult<Info> list = new DatatableResult<Info>();
        List<Info> list2 = ifo.selectAllInfo();
        list.setData(list2);
        return list;
    }

    /***
     * 
     * @param searchCondition:使用datatable获取companyInfo信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "dt_lists4")
    public DatatableResult<Companyinfo> datatable4(@RequestBody SearchCondition searchCondition) {
        DatatableResult<Companyinfo> list = new DatatableResult<Companyinfo>();
        List<Companyinfo> list2 = cfs.selectAllCompanies();
        list.setData(list2);
        return list;
    }
}
