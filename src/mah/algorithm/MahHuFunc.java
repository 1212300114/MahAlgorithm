package mah.algorithm;

import mah.data.MahGroup;
import mah.data.MahHuInfo;
import mah.data.MahModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gary on 2017/4/13 0013.
 */
public class MahHuFunc {
    /**
     * to separate mahs to hu type 3333..2
     *
     * @param models   model list
     * @param huInfoss to store hu group results
     */
    public static boolean separateTo3n2(ArrayList<MahModel> models, List<MahHuInfo> huInfoss) {
        List<MahGroup> doubles = MahBaseFunc.getDoublesInList(models);
        for (MahGroup group : doubles) {
            ArrayList<MahModel> temp = (ArrayList<MahModel>) models.clone();

            MahBaseFunc.removeGroupFromList(temp, group);
            MahHuInfo infos = new MahHuInfo();
            if (separateTo3n(temp, infos)) {
                huInfoss.add(infos);
            }
        }
        return huInfoss.size() > 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * recursion function to separate an list of mah to empty
     *
     * @param models the models to remove group
     */
    public static boolean separateTo3n(List<MahModel> models, MahHuInfo infos) {
        if (models.size() % 3 != 0)
            return false;
        if (models.size() == 0)
            return true;
        List<MahGroup> relateGroups = MahBaseFunc.getRelateGroupsInListForMah(models.get(0), models);
        for (MahGroup group : relateGroups) {
            MahBaseFunc.removeGroupFromList(models, group);
            infos.addGroup(group);
            separateTo3n(models, infos);
        }
        return infos.getAllGroups().size() > 0;
    }
}
