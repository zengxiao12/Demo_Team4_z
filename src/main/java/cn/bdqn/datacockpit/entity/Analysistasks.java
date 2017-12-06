package cn.bdqn.datacockpit.entity;

import java.util.Date;

public class Analysistasks {
    private Integer id;

    private Date starttime;
    
    private Date endtime;

    private Integer cid;

    private Integer did;

    private Integer taskstatus;

    private String rule;

    private String feedback;

    private Integer arithmeticid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getArithmeticid() {
        return arithmeticid;
    }

    public void setArithmeticid(Integer arithmeticid) {
        this.arithmeticid = arithmeticid;
    }
}