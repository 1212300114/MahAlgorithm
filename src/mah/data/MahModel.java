package mah.data;

/**
 * Created by Gary on 2017/3/9 0009.
 */
public class MahModel implements Comparable<MahModel> {
    private int value;
    private int type;
    private int typeValue;
    private String mahName;
    private boolean isJoker;

    public MahModel(int value) {
        this.setValue(value);
    }

    public void setValue(int value) {
        this.value = value;
        this.type = value >> 4;
        this.typeValue = this.value - (this.type << 4);
        initName();
    }

    private void initName() {
        if (this.type <= MahEnum.MahType.TONG) {
            this.mahName = this.typeValue + MahEnum.MahType.TYPE_NAME[this.type];
        } else {
            if (type == MahEnum.MahType.FENG) {
                switch (this.typeValue) {
                    case 1:
                        this.mahName = "东风";
                        break;
                    case 2:
                        this.mahName = "南风";
                        break;
                    case 3:
                        this.mahName = "西风";
                        break;
                    case 4:
                        this.mahName = "北风";
                        break;
                    default:
                        this.mahName = "error";
                }
            } else if (type == MahEnum.MahType.JIAN) {
                switch (this.typeValue) {
                    case 1:
                        this.mahName = "红中";
                        break;
                    case 2:
                        this.mahName = "发财";
                        break;
                    case 3:
                        this.mahName = "白班";
                        break;
                    default:
                        this.mahName = "error";
                }
            }
        }
    }

    public int getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public String getMahName() {
        return mahName;
    }

    public boolean isJoker() {
        isJoker = this.value == MahConfig.JOKER_VALUE;
        return isJoker;
    }

    @Override
    public int compareTo(MahModel o) {
        if (o.getValue() > this.getValue())
            return -1;
        if (o.getValue() < this.getValue())
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MahModel) {
            if (((MahModel) obj).getValue() == this.getValue())
                return true;
        }
        return super.equals(obj);
    }
}
