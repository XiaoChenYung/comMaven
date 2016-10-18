package com.yxc.model;
import java.util.List;
/**
 * Created by tm on 2016/10/12.
 */
public class PageModel {
    /**
     * 总记录数
     * */
    private int total;

    /**
     * 当前页的记录集
     * */
    private List datas;
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List getDatas() {
        return datas;
    }
    public void setDatas(List datas) {
        this.datas = datas;
    }
}
