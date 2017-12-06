package cn.bdqn.datacockpit.utils;

import java.util.List;

public class test {
    public static void main(String[] args) {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read("D:/360Downloads/shujubiao.xlsx");
        String lists = list.toString();
        ChineseToPinYin ctp = new ChineseToPinYin();
        String pingyin = ctp.getPingYin(lists);
        System.out.println(pingyin);

    }
}
