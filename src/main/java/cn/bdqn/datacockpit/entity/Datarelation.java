package cn.bdqn.datacockpit.entity;

public class Datarelation {
    private Integer id;

    private String name;

    private Integer cid;

    private Integer tid1;

    private Integer tid2;

    private Integer col1;

    private Integer col2;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getTid1() {
        return tid1;
    }

    public void setTid1(Integer tid1) {
        this.tid1 = tid1;
    }

    public Integer getTid2() {
        return tid2;
    }

    public void setTid2(Integer tid2) {
        this.tid2 = tid2;
    }

    public Integer getCol1() {
        return col1;
    }

    public void setCol1(Integer col1) {
        this.col1 = col1;
    }

    public Integer getCol2() {
        return col2;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}