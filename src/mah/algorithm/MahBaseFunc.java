package mah.algorithm;

import mah.data.MahConfig;
import mah.data.MahGroup;
import mah.data.MahModel;

import java.util.*;

/**
 * the mah calculate Class hold data methods and logic methods
 * Created by Gary on 2017/3/13 0013.
 */
public class MahBaseFunc {
    /****************************************************************************************************************/
    // mah data methods

    /**
     * the to separate mahs to singles in a map like (1 : 2, 2 : 3) the key is the mah the value is the number of mah
     *
     * @param models the whole hand mah models
     * @return the mah singles info map
     */
    public static Map<Integer, Integer> separateMahsToNums(List<MahModel> models) {
        Map<Integer, Integer> handMahMap = new HashMap<>();// to store mah with count handMahMap

        for (MahModel mahModel : models) {
            if (handMahMap.containsKey(mahModel.getValue())) {
                handMahMap.put(mahModel.getValue(), handMahMap.get(mahModel.getValue()) + 1);
            } else {
                handMahMap.put(mahModel.getValue(), 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : handMahMap.entrySet()) {
            System.out.println("key " + entry.getKey() + " value " + entry.getValue());
        }
        return handMahMap;
    }

    /**
     * change model list like 111222333 to 123
     *
     * @param models the somes list
     * @return the ones list
     */
    public static List<MahModel> somesToOnes(List<MahModel> models) {
        Map<Integer, Integer> handMahMap = separateMahsToNums(models);
        List<MahModel> ones = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : handMahMap.entrySet()) {
            ones.add(new MahModel(entry.getKey()));
        }
        return ones;
    }


    /**
     * change mah model list to index list
     *
     * @param models model list
     * @return index list
     */
    public static List<Integer> mahModels2mahValues(List<MahModel> models) {
        List<Integer> mahs = new ArrayList<>();
        for (MahModel model : models) {
            mahs.add(model.getValue());
        }
        return mahs;
    }

    /**
     * change mah index list to model list
     *
     * @param mahs the index list
     * @return the model list
     */
    public static List<MahModel> mahValues2mahModels(List<Integer> mahs) {
        List<MahModel> models = new ArrayList<>();
        for (Integer mah : mahs) {
            models.add(new MahModel(mah));
        }
        return models;
    }

    /**
     * to get all doubles in the hand mahs
     *
     * @param handModels the whole hand mahs
     * @return the list of double groups
     */
    public static List<MahGroup> getDoublesInList(List<MahModel> handModels) {
        List<MahGroup> handDoubleGroups = new ArrayList<>();
        Map<Integer, Integer> handMahs = MahBaseFunc.separateMahsToNums(handModels);
        for (Map.Entry<Integer, Integer> entry : handMahs.entrySet()) {
            if (entry.getValue() >= 2) {
                MahGroup group = new MahGroup(entry.getKey(), entry.getKey());
                handDoubleGroups.add(group);
            }
        }
        if (MahConfig.HAS_JOKER) {
            // 计算出财神组的对子
            List<MahModel> jokers = new ArrayList<>();
            List<MahModel> normals = new ArrayList<>();
            if (separateOutJokers(handModels, jokers, normals)) {
                normals = somesToOnes(normals);
                for (MahModel model : normals) {
                    MahGroup group = new MahGroup(model.getValue(), jokers.get(0).getValue());
                    if (group.getGroupType() == MahGroup.GROUP_DOUBLE) handDoubleGroups.add(group);
                }
            }
        }
        return handDoubleGroups;
    }

    /**
     * find all relate groups for mah in a list
     *
     * @param model  target model
     * @param models model list
     * @return the relate groups in list
     */
    public static List<MahGroup> getRelateGroupsInListForMah(MahModel model, List<MahModel> models) {
        List<MahGroup> allRelate = getAllRelateGroupsForMah(model);
        List<MahGroup> relates = new ArrayList<>();
        List<MahModel> normals = new ArrayList<>();
        List<MahModel> jokers = new ArrayList<>();
        separateOutJokers(models, jokers, normals);

        for (MahGroup group : allRelate) {
            if (isSubMahs(models, group.getModels())) {
                relates.add(group);
            }
            if (MahConfig.HAS_JOKER) {
                List<MahModel> inters = interMahs(models, group.getModels());
                if (inters.size() + jokers.size() > group.getModels().size())
                    relates.add(group);
            }
        }
        return relates;
    }

    /**
     * to get all relate groups for mah
     *
     * @param model target mah
     * @return groups for mah
     */

    public static List<MahGroup> getAllRelateGroupsForMah(MahModel model) {
        List<MahGroup> groups = new ArrayList<>();
        for (int i = -2; i < 1; i++) {
            MahGroup group = new MahGroup(model.getValue() + i, model.getValue() + i + 1, model.getValue() + i + 2);
            if (group.getGroupType() != MahGroup.GROUP_NONE) groups.add(group);
        }
        groups.add(new MahGroup(model.getValue(), model.getValue(), model.getValue()));
        return groups;
    }

    /**
     * to separate out jokers and normal mahs
     *
     * @param handModels   the whole hand mahs
     * @param jokerHolder  the list to hold jokers
     * @param normalHolder the list to hold normal mahs
     * @return whether hand models have joker or not
     */
    public static boolean separateOutJokers(List<MahModel> handModels, List<MahModel> jokerHolder, List<MahModel> normalHolder) {
        if (null == jokerHolder) jokerHolder = new ArrayList<>();
        else jokerHolder.clear();
        if (null == normalHolder) normalHolder = new ArrayList<>();
        else normalHolder.clear();
        for (MahModel model : handModels) {
            if (model.isJoker()) {
                jokerHolder.add(model);
            } else normalHolder.add(model);
        }
        return jokerHolder.size() > 0;
    }


    /**
     * to calculate whether handModels have all sub mahs
     *
     * @param models  the whole mah model list
     * @param subMahs the sub mah model list
     * @return have or not
     */

    public static boolean isSubMahs(List<MahModel> models, List<MahModel> subMahs) {
        if (null == models || models.size() == 0 || null == subMahs || subMahs.size() == 0) return false;
        int j = 0;
        int count = 0;
        for (MahModel model : subMahs) {
            for (int i = j; i < models.size(); i++) {
                if (models.get(i).equals(model)) {
                    count++;
                    j = i + 1;
                    break;
                }
            }
        }
        return count == subMahs.size();
    }

    /**
     * find the inter mahs between two list
     *
     * @param firstList  the first list
     * @param secondList the second lis
     * @return the inter mahs
     */

    public static List<MahModel> interMahs(List<MahModel> firstList, List<MahModel> secondList) {
        List<MahModel> inters = new ArrayList<>();
        int j = 0;
        for (MahModel model : firstList) {
            for (int i = j; i < secondList.size(); i++) {
                if (model.equals(secondList.get(i))) {
                    inters.add(model);
                    j = i + 1;
                    break;
                }
            }
        }
        return inters;
    }

    /**
     * to remove a group of mah data from hand models
     *
     * @param handModels the whole models of mah to be removed;
     * @param group      the target group to remove;
     * @return whether the whole model contains all the sub models
     * @see #removeListFromList(List, List)
     */
    public static boolean removeGroupFromList(List<MahModel> handModels, MahGroup group) {
        return removeListFromList(handModels, group.getModels());

    }

    /**
     * to remove an array of mah data from hand Models
     * --tips only the hand models contains the subs then the remove action will be done
     *
     * @param handModels the whole models of mah to be removed;
     * @param subModels  the target mahs to remove;
     * @return whether the whole model contains all the sub models
     */
    public static boolean removeListFromList(List<MahModel> handModels, List<MahModel> subModels) {
        if (isSubMahs(handModels, subModels)) {
            for (MahModel model : subModels) {
                for (int i = 0; i < handModels.size(); i++) {
                    if (handModels.get(i).equals(model)) {
                        handModels.remove(i);
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * is all th models in list in the same type
     *
     * @param models the model list
     * @return whether is same type
     */
    public static boolean isTypeSameForList(List<MahModel> models) {
        if (null == models || models.size() == 0) {
            throw new IllegalArgumentException("error models");
        }
        int type = models.get(0).getType();
        for (MahModel model : models) {
            if (model.getType() != type)
                return false;
        }
        return true;
    }

}
