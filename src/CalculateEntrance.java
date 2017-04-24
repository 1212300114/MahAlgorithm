import mah.algorithm.MahHuFunc;
import mah.data.MahEnum;
import mah.data.MahHuInfo;
import mah.data.MahModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gary on 2017/3/9 0009.
 */
public class CalculateEntrance {

    static List<MahModel> removedGroup = new ArrayList<>();
    public static int joker = MahEnum.MahValue.ER_WAN;

    public static void main(String[] args) {


        ArrayList<MahModel> mahModels = new ArrayList<>();
        int[] mahs2 = {17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23};
        for (int mah : mahs2) {
            MahModel model = new MahModel(mah);
            mahModels.add(model);
        }

        List<MahHuInfo> info = new ArrayList<>();
        MahHuFunc.separateTo3n2(mahModels, info);
        System.out.print(info.size());
    }

}
