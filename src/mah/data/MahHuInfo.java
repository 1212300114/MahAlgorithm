package mah.data;

import java.util.ArrayList;
import java.util.List;

/**
 * mah hu group model class
 * Created by Gary on 2017/3/14 0014.
 */
public class MahHuInfo {

    private List<MahGroup> groups;
    private int fanType;//藩形  以后可用
    private int fanMulti; // 藩的倍数


    public MahHuInfo() {
        groups = new ArrayList<>();
    }

    public MahHuInfo(List<MahGroup> groups) {
        this.groups = new ArrayList<>(groups);
        this.groups.addAll(groups);
    }

    /**
     * add group into groups
     *
     * @param group the mah group
     */
    public void addGroup(MahGroup group) {
        if (null == group) throw new IllegalArgumentException("group can't be null");
        this.groups.add(group);
    }

    /**
     * return an copy of groups
     *
     * @return the copy of groups
     */
    public List<MahGroup> getAllGroups() {
        return new ArrayList<>(groups);
    }

    /**
     * 计算藩形
     *
     * @return fan type
     */
    public int getFanType() {
        return fanType;
    }
}
