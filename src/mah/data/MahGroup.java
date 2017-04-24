package mah.data;

import mah.algorithm.MahBaseFunc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gary on 2017/3/9 0009.
 */
public class MahGroup {
    public static final int GROUP_NONE = 0;
    public static final int GROUP_DOUBLE = 1;
    public static final int GROUP_LINK = 2;
    public static final int GROUP_SAME = 3;
    public static final int GROUP_FOUR = 4;

    private int groupType = GROUP_NONE;
    private List<MahModel> models;
    private boolean hadJoker = false;

    public MahGroup(List<MahModel> models) {
        initGroup(models);
    }

    public MahGroup(int... mahs) {
        List<MahModel> models = new ArrayList<>();
        for (int mah : mahs) {
            models.add(new MahModel(mah));
        }
        initGroup(models);
    }

    private void initGroup(List<MahModel> group) {
        Collections.sort(group);
        this.models = new ArrayList<>(group);
        if (!MahConfig.HAS_JOKER) {
            switch (group.size()) {
                case 2:
                    if (group.get(0).equals(group.get(1)))
                        groupType = GROUP_DOUBLE;
                    break;
                case 3:
                    if (group.get(0).equals(group.get(1)) && group.get(1).equals(group.get(2)))
                        groupType = GROUP_SAME;
                    if (group.get(2).getValue() - group.get(0).getValue() == 2 &&
                            group.get(2).getValue() < MahEnum.MahValue.DONG &&
                            group.get(0).getTypeValue() > 0 && group.get(2).getTypeValue() < 9)
                        groupType = GROUP_LINK;
                    break;
                case 4:
                    if (group.get(0).equals(group.get(1)) && group.get(1).equals(group.get(2)) &&
                            group.get(2).equals(group.get(3)))
                        groupType = GROUP_FOUR;
                default:
                    break;
            }
        } else {

            List<MahModel> jokers = new ArrayList<>();
            List<MahModel> normals = new ArrayList<>();
            // hava joker
            if (MahBaseFunc.separateOutJokers(group, jokers, normals)) {
                hadJoker = true;
                if (group.size() == 2)
                    groupType = GROUP_DOUBLE;
                else if (group.size() == 3) {
                    if (jokers.size() == 3)
                        groupType = GROUP_SAME;
                    else if (jokers.size() == 2)
                        groupType = GROUP_SAME;
                    else if (jokers.size() == 1)
                        if (normals.get(0).equals(normals.get(1)))
                            groupType = GROUP_SAME;
                        else if (normals.get(1).getValue() < MahEnum.MahValue.DONG) {
                            if (normals.get(1).getValue() - normals.get(0).getValue() == 1 ||
                                    normals.get(1).getValue() - normals.get(0).getValue() == 2)
                                groupType = GROUP_LINK;
                        }

                } else {
                    if (group.get(0).equals(group.get(1)) && group.get(1).equals(group.get(2)) &&
                            group.get(2).equals(group.get(3)))
                        groupType = GROUP_FOUR;
                }
            }
        }
    }

    public int getGroupType() {
        return groupType;
    }

    public List<MahModel> getModels() {
        return models;
    }

    @Override
    public String toString() {
        String mahString = "";
        if (null != models) {
            for (MahModel model : models) {
                mahString += model.getMahName();
                mahString += " ";
            }
        }
        String typeString = "错误";
        switch (this.groupType) {
            case GROUP_DOUBLE:
                typeString = "将对";
                break;
            case GROUP_LINK:
                typeString = "顺子";
                break;
            case GROUP_SAME:
                typeString = "刻子";
                break;
            case GROUP_FOUR:
                typeString = "杠子";
                break;
        }
        return "组牌:" + mahString +
                "  牌型:" +
                typeString +
                " 是否有财神:" +
                hadJoker;
    }
}
