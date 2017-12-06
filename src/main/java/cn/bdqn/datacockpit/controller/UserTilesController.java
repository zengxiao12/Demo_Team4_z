package cn.bdqn.datacockpit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.XsTableService;
import cn.bdqn.datacockpit.utils.ChineseToPinYin;
import cn.bdqn.datacockpit.utils.ImportExecl;
import cn.bdqn.datacockpit.utils.JdbcUtil;

/**
 * Created by ehsy_it on 2016/8/10.
 */
@Controller
public class UserTilesController {
    @Autowired
    private XsTableService xs;

    @Autowired
    private InfoService infoService;

    @RequestMapping("/user_pass")
    public String pass(Model model) {
        model.addAttribute("checks", "geren2");
        return "user_pass.pages";
    }

    @RequestMapping("/user_update")
    public String update(Model model) {
        model.addAttribute("checks", "geren1");
        return "user_update.pages";
    }

    /**
     * 
     * Description: 转发到用户首页<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    @RequestMapping("/user_index")
    public String index(Model model) {
    	
    	return "user_index.pages";
    }

    /**
     * 
     * Description: 取通知信息和系统信息并重定向到user_index<br/>
     *
     * @author dengJ
     * @param req
     * @return
     */
    @RequestMapping("/user_second")
    public String userSecond(HttpServletRequest req) {
        List<Info> infoList = infoService.selectAllInfo();
        if (infoList != null) {
            for (Info info : infoList) {
                Date date = info.getPublishDate();
                System.out.println(date);
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("tongzhi", infoList);
        return "redirect:/user_index.shtml";
    }

    @RequestMapping("/user_shuju1")
    public String shuju1(Model model) {
        model.addAttribute("checks", "shuju1");
        return "user_shuju1.pages";
    }


    @RequestMapping("/user_shuju2")
    public String shuju2(Model model) {
        model.addAttribute("checks", "shuju2");
        return "user_shuju2.pages";
    }

    @RequestMapping("/user_shuju3")
    public String shuju3(Model model, HttpServletRequest req) {
        model.addAttribute("checks", "shuju3");
        String names = req.getParameter("id");
        ChineseToPinYin ctp = new ChineseToPinYin();
        String name = ctp.getPingYin(names);
        model.addAttribute("name2", names);
        model.addAttribute("name1", name);
        JdbcUtil jdbc1 = new JdbcUtil();
        ApplicationContext context = jdbc1.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        List<Map<String, Object>> lists = jdbc1.selectObj(jt, name);
        if (lists != null) {
            try {
                int shows = (Integer)lists.get(0).get("shows");
                model.addAttribute("shows", shows);
                String time = "'";
                Date date = null;
                for (int i = 0; i < lists.size(); i++) {
                    date = (Date) lists.get(i).get("times");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    time = time + sdf.format(date) + "','";
                    if (i == lists.size() - 1) {
                        date = (Date) lists.get(i).get("times");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        time = time + sdf2.format(date);
                    }
                }
                time = "[" + time + "']";
                model.addAttribute("lists", time);
                String fNums = "";
                for (int i = 0; i < lists.size(); i++) {
                    if (i == lists.size() - 1) {
                        fNums = fNums + lists.get(i).get("daofangrenshu");
                    } else {
                        fNums = fNums + lists.get(i).get("daofangrenshu") + ",";
                    }
                }
                fNums = "[" + fNums + "]";

                String rNums = "";
                for (int i = 0; i < lists.size(); i++) {

                    if (i == lists.size() - 1) {
                        rNums = rNums + lists.get(i).get("renchourenshu");
                    } else {
                        rNums = rNums + lists.get(i).get("renchourenshu") + ",";
                    }
                }
                rNums = "[" + rNums + "]";
                model.addAttribute("rNums", rNums);
                model.addAttribute("fNums", fNums);
                Set<String> sets = new HashSet<String>();

                for (int i = 0; i < lists.size(); i++) {
                    sets = lists.get(i).keySet();
                }
                List<String> lists3 = new ArrayList<String>();
                for (String string : sets) {
                    lists3.add(string);
                }
                model.addAttribute("lists3", lists3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "user_shuju3.pages";
    }

    @RequestMapping("/user_guanxitu")
    public String userGuanxitu(Model model) {
        model.addAttribute("checks", "shuju4");
        return "user_guanxitu.pages";
    }

    @RequestMapping("/user_uploads")
    public String upload(Model model, HttpServletRequest req) throws Exception {
        String urls = req.getParameter("urls");
        String tb1 = urls.substring(12);
        String[] tb2 = tb1.split("\\.");
        String tbNames = tb2[0];
        ChineseToPinYin ctp = new ChineseToPinYin();
        String tableName = ctp.getPingYin(tbNames);
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(urls);
        List<String> head = list.get(0);
        List<String> heads = new ArrayList<String>();
        for (int i = 0; i < head.size(); i++) {
            if (head.get(i).equals("日期")) {
                heads.add("times");
            } else {
                heads.add(ctp.getPingYin(head.get(i)));
            }
        }

        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int j = 1; j < list.size(); j++) {
            List<String> content = list.get(j);
            Map<String, Object> maps = new HashMap<String, Object>();
            for (int k = 0; k < content.size(); k++) {
                if (heads.get(k).equals("times")) {
                    String date1 = content.get(k);
                    StringBuilder sb = new StringBuilder(date1);
                    sb.insert(4, "-");
                    sb.insert(7, "-");
                    String dates = sb.toString();
                    maps.put(heads.get(k), dates);
                } else if (content.get(k).matches("[0-9]+")) {
                    Integer num = Integer.parseInt(content.get(k));
                    maps.put(heads.get(k), num);

                } else {
                    maps.put(heads.get(k), content.get(k));
                }
            }
            contents.add(maps);
        }
        JdbcUtil jdbcs = new JdbcUtil();
        ApplicationContext context = jdbcs.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcs.saveObj(jt, tableName, contents);
        return null;
    }

    @ResponseBody
    @RequestMapping("/user_uploadss")
    public Map<String, String> uploads(Model model, HttpServletRequest req) throws Exception {
        String urls = req.getParameter("urls");
        String tb1 = urls.substring(12);
        String[] tb2 = tb1.split("\\.");
        String tbNames = tb2[0];
        ChineseToPinYin ctp = new ChineseToPinYin();
        String tableName = ctp.getPingYin(tbNames);
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(urls);
        List<String> head = list.get(0);
        List<String> heads = new ArrayList<String>();
        for (int i = 0; i < head.size(); i++) {
            if (head.get(i).equals("日期")) {
                heads.add("times");
            } else {
                heads.add(ctp.getPingYin(head.get(i)));
            }
        }

        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int j = 1; j < list.size(); j++) {
            List<String> content = list.get(j);
            Map<String, Object> maps = new HashMap<String, Object>();
            for (int k = 0; k < content.size(); k++) {
                if (heads.get(k).equals("times")) {
                    String date1 = content.get(k);
                    StringBuilder sb = new StringBuilder(date1);
                    sb.insert(4, "-");
                    sb.insert(7, "-");
                    String dates = sb.toString();
                    maps.put(heads.get(k), dates);
                } else if (content.get(k).matches("[0-9]+")) {
                    Integer num = Integer.parseInt(content.get(k));
                    maps.put(heads.get(k), num);

                } else {
                    maps.put(heads.get(k), content.get(k));
                }
            }
            contents.add(maps);
        }
        JdbcUtil jdbcs = new JdbcUtil();
        ApplicationContext context = jdbcs.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcs.saveObj(jt, tableName, contents);
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("flag", "1");
        return maps;
    }

}
