package mah.data;

/**
 * Created by Gary on 2017/3/9 0009.
 */
public interface MahEnum {

    interface MahType {
        int WAN = 1;
        int TIAO = 2;
        int TONG = 3;
        int FENG = 4;
        int JIAN = 5;
        String[] TYPE_NAME = {"", "万", "条", "筒", "风", "箭"};
    }

    int BASE_INDEX = 1;

    interface MahValue {
        int YI_WAN = (1 << (3 + MahType.WAN)) + 1;
        int ER_WAN = (1 << (3 + MahType.WAN)) + 2;
        int SAN_WAN = (1 << (3 + MahType.WAN)) + 3;
        int SI_WAN = (1 << (3 + MahType.WAN)) + 4;
        int WU_WAN = (1 << (3 + MahType.WAN)) + 5;
        int LIU_WAN = (1 << (3 + MahType.WAN)) + 6;
        int QI_WAN = (1 << (3 + MahType.WAN)) + 7;
        int BA_WAN = (1 << (3 + MahType.WAN)) + 8;
        int JIU_WAN = (1 << (3 + MahType.WAN)) + 9;

        int YI_TIAO = (1 << (3 + MahType.TIAO)) + 1;
        int ER_TIAO = (1 << (3 + MahType.TIAO)) + 2;
        int SAN_TIAO = (1 << (3 + MahType.TIAO)) + 3;
        int SI_TIAO = (1 << (3 + MahType.TIAO)) + 4;
        int WU_TIAO = (1 << (3 + MahType.TIAO)) + 5;
        int LIU_TIAO = (1 << (3 + MahType.TIAO)) + 6;
        int QI_TIAO = (1 << (3 + MahType.TIAO)) + 7;
        int BA_TIAO = (1 << (3 + MahType.TIAO)) + 8;
        int JIU_TIAO = (1 << (3 + MahType.TIAO)) + 9;

        int YI_TONG = (1 << (3 + MahType.TONG)) + 1;
        int ER_TONG = (1 << (3 + MahType.TONG)) + 2;
        int SAN_TONG = (1 << (3 + MahType.TONG)) + 3;
        int SI_TONG = (1 << (3 + MahType.TONG)) + 4;
        int WU_TONG = (1 << (3 + MahType.TONG)) + 5;
        int LIU_TONG = (1 << (3 + MahType.TONG)) + 6;
        int QI_TONG = (1 << (3 + MahType.TONG)) + 7;
        int BA_TONG = (1 << (3 + MahType.TONG)) + 8;
        int JIU_TONG = (1 << (3 + MahType.TONG)) + 9;

        int DONG = (1 << (3 + MahType.FENG)) + 1;
        int NAN = (1 << (3 + MahType.FENG)) + 2;
        int XI = (1 << (3 + MahType.FENG)) + 3;
        int BEI = (1 << (3 + MahType.FENG)) + 4;

        int ZHONG = (1 << (3 + MahType.JIAN)) + 1;
        int FA = (1 << (3 + MahType.JIAN)) + 2;
        int BAI = (1 << (3 + MahType.JIAN)) + 3;

        int JOKER = 100;


    }
}