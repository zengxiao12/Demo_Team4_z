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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Datarelation;
import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.entity.Tableinfo;
import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.service.CompanyinfoService;
import cn.bdqn.datacockpit.service.DatarelationService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.RelevanceTableService;
import cn.bdqn.datacockpit.service.TableinfoService;
import cn.bdqn.datacockpit.service.UserinfoService;
import cn.bdqn.datacockpit.utils.ChineseToPinYin;
import cn.bdqn.datacockpit.utils.JdbcUtil;

/**
 * Created by ehsy_it on 2016/8/10.
 */
@Controller
public class AdminTilesController {
    @Autowired
    private TableinfoService ts;

    @Autowired
    private UserinfoService us;

    @Autowired
    private InfoService is;

    @Autowired
    private CompanyinfoService companyinfo;

    @Autowired
    private DatarelationService dataService;

    @Autowired
    private RelevanceTableService releTable;

    @RequestMapping("/admin_index")
    public String index(Model model) {
        return "admin_index.page";
    }

    /**
     * 
     * Description: 管理员通知页面<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    @RequestMapping("/admin_tongzhi1")
    public String tongzhi1(Model model) {
        model.addAttribute("menus", "1");
        return "admin_tongzhi1.page";
    }

    /**
     * 
     * Description: 添加通知页面<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    @RequestMapping("/admin_tongzhi2")
    public String tongzhi2(Model model) {
        return "admin_tongzhi2.page";
    }

    @RequestMapping("/admin_tongzhi3")
    public String tongzhi3(Model model, HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        Info info = is.selectByPrimaryKey(id);
        model.addAttribute("info", info);
        return "admin_tongzhi3.page";
    }

    @RequestMapping("/tongzhi_update")
    public String tongzhi_update(Info info) {
        // 获取实体类信息
        is.updateByPrimaryKeySelective(info);
        return "admin_tongzhi1.page";
    }

    /**
     * 
     * Description: 添加通知<br/>
     *
     * @author dengJ
     * @param info
     * @return
     */
    @RequestMapping("/tongzhi_insert")
    public String tongzhi_insert(Info info) {
        // 获取实体类信息，将新增数据存入数据库
        is.insertSelective(info);
        // 获取本地时间与数据库时间格式一致
        java.util.Date date = new java.util.Date();
        java.sql.Date data1 = new java.sql.Date(date.getTime());
        info.setPublishDate(data1);
        // 获取最新一条记录ID
        Integer infoMax = is.selectMaxId();
        info.setId(infoMax);
        // 将时间存入最后一条记录中
        is.updateByPrimaryKey(info);
        return "admin_tongzhi1.page";
    }

    @RequestMapping("/tongzhi_delete")
    public String tongzhi_delete(HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        is.deleteByPrimaryKey(id);
        return "admin_tongzhi1.page";
    }

    @RequestMapping("/admin_delete")
    public String admin_delete(HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        us.deleteByPrimaryKey(id);
        return "admin_shuju4.page";
    }

    @RequestMapping("/adminus_delete")
    public String adminus_delete(HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        companyinfo.deleteByPrimaryKey(id);
        return "admin_userDsh.page";
    }

    @RequestMapping("/aduser_update")
    public String aduser_update(Model model, HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        model.addAttribute("comp", comp);
        return "aduser_update.page";
    }

    /***
     * 
     * @param req:通过id更新approval状态
     * @return
     */
    @RequestMapping("/adminuss_updatee")
    public String adminuss_updatee(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setApproval(1);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userDsh.page";
    }

    /***
     * 
     * @param req:通过id更新approval状态
     * @return
     */
    @RequestMapping("/adminuss_updatee0")
    public String adminuss_updatee0(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setApproval(0);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userDsh.page";
    }

    /***
     * 
     * @param req:通过id更新state状态:1
     * @return
     */
    @RequestMapping("/aduser_update1")
    public String aduser_update1(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setState(1);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userMan.page";
    }

    /***
     * 
     * @param req:通过id更新state状态:0
     * @return
     */
    @RequestMapping("/aduser_update0")
    public String aduser_update0(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setState(0);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userMan.page";
    }

    @RequestMapping("/aduser_update2")
    public String aduser_insert(Companyinfo comps) {
        // 获取实体类信息
        int flag = companyinfo.updateByPrimaryKey(comps);

        System.out.println(flag);
        return "admin_userMan.page";
    }

    @RequestMapping("/admin_cominfo")
    public String cominfo(Model model) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("menus", "3");
        model.addAttribute("lists", lists);
        return "admin_cominfo.page";
    }

    @RequestMapping("/admin_shuju1")
    public String shuju1(Model model, HttpServletRequest req) throws Exception {
        model.addAttribute("menus", "3");
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        session.setAttribute("No1", id);
        List<Map<String, Object>> lists = releTable.selectAllTables();
        model.addAttribute("lists", lists);
        return "admin_shuju1.page";
    }

    @RequestMapping("/admin_shuju2")
    public String shuju2(Model model, HttpServletRequest req) {
        return "admin_shuju2.page";
    }

    @RequestMapping("/admin_shuju3")
    public String shuju3(Model model) {
        return "admin_shuju3.page";
    }

    @RequestMapping("/admin_shuju4")
    public String shuju4(Model model) {
        model.addAttribute("menus", "2");
        return "admin_shuju4.page";
    }

    @RequestMapping("insertAdminReg")
    public String insertAdminReg(Userinfo record) {
        int flag = us.insertSelective(record);
        // 转发
        return "admin_shuju4.page";
    }

    @RequestMapping("/selectAllCompanyinfo")
    public String selectAllCompanyinfo(Model model, HttpServletRequest req) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("lists", lists);
        List<Map<String, Object>> lists1 = new ArrayList<Map<String, Object>>();
        List<Info> infoList= is.selectAllInfo();
        Date time = new Date();
        Date ti1 = new Date(time.getTime() - 7 * 24 * 60 * 60 * 1000);
       if (infoList != null) {
            for (Info info : infoList) {
                Date date = info.getPublishDate();
                Map<String, Object> map = new HashMap<String, Object>();
                if(ti1.before(date)){
                	map.put("date",1);
                }else{
                	map.put("date",0);
                }
                map.put("info",info);
                lists1.add(map);
                System.out.println(date);
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("flag", lists1);
        // 转发
        return "admin_index.page";
    }

    @RequestMapping("/admin_userDsh")
    public String dshCompanyinfo(Model model) {

        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("menus", "5");
        model.addAttribute("lists", lists);
        // 转发
        return "admin_userDsh.page";
    }

    @RequestMapping("/admin_userMan")
    public String userMan(Model model) {

        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("menus", "4");
        model.addAttribute("lists", lists);

        // 转发
        return "admin_userMan.page";
    }

    // 新建数据表
    @ResponseBody
    @RequestMapping("/admin_create")
    public Map<String, String> creats(@RequestParam("values") String id, HttpServletRequest req) {
        String[] attr = id.split(",");
        ChineseToPinYin ctp = new ChineseToPinYin();
        Map<String, Object> map = new HashMap<String, Object>();
        String tbName = null;
        for (int i = 0; i < attr.length; i++) {
            if (i == 0) {
                map.put("shows", attr[0]);
            } else if (i == 1) {
                tbName = ctp.getPingYin(attr[1]);
            } else if (2 * i - 1 <= attr.length) {

                map.put(ctp.getPingYin(attr[2 * i - 2]), attr[2 * i - 1]);

            }
        }

        JdbcUtil creats = new JdbcUtil();
        ApplicationContext context = creats.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        creats.createTable(jt, tbName, map);

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sdf.format(dt);
        Tableinfo record = new Tableinfo();
        record.setName(attr[1]);
        record.setUpdatetime(date);
        record.setShowtype(attr[0]);
        HttpSession session = req.getSession();
        String ids = (String) session.getAttribute("No1");
        Integer cid = Integer.parseInt(ids);
        record.setCid(cid);
        ts.insert(record);

        Map<String, String> maps = new HashMap<String, String>();
        maps.put("flag", "1");
        return maps;
    }

    @RequestMapping("/admin_selects")
    public String selects(Model model) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        System.out.println(lists);
        model.addAttribute("menus", "4");
        model.addAttribute("lists", lists);
        // 转发
        return "admin_userMan.page";
    }

    /**
     * 公告详情
     * 
     * @param req
     * @return
     */
    @RequestMapping("/admin_gongGao")
    public String gongGao1(Integer id, Model model) {
        Info infos = is.selectByPrimaryKey(id);
        model.addAttribute("ggg", infos);
        return "admin_gongGao.page";
    }

    @RequestMapping("/insert_guanlian")
    public String insertGL(Datarelation record, HttpServletRequest req) {
        String id = req.getParameter("id");
        int flag = dataService.insert(record);
        if (flag >= 1) {
            return "redirect:/admin_shuju1.shtml?id=1";
        }

        return "admin_shuju1.page";
    }

    @RequestMapping("/admin_adds")
    public String adds(Model model) {

        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        System.out.println(lists);
        model.addAttribute("menus", "4");
        model.addAttribute("lists", lists);

        // 转发
        return null;
    }

    @RequestMapping("/admin_shujus")
    public String shuju3(Model model, HttpServletRequest req) {
        model.addAttribute("menus", "3");
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
            	Integer shows = (Integer) lists.get(0).get("shows");
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

        return "admin_shujus.page";
    }

    @RequestMapping("/admin_uppassword")
    public String admin_uppassword(Model model) {
        model.addAttribute("checks", "geren2");
        return "admin_pass.page";
    }
}
