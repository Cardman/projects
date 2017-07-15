package aiki.util;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Cmp;
import code.util.pagination.Sorting;

public final class SortingHealingItem implements Sorting,Cmp<SortingHealingItem> {

    private int index;

    private String itemClass;

    private String name;

    private String keyName;

    private int nbHealedStatus;

    private StringList status = new StringList();

    private boolean relativeRateHp;

    private Rate hp;

    private Rate hpRate;

    private boolean relativeRatePp;

    private Rate pp;

    private boolean healOneMove;

    private int nbStatistics;

    private StringList statistics = new StringList();

    private boolean ko;

    private int price;

    private LgInt number = LgInt.zero();

    @Override
    public boolean eq(SortingHealingItem _obj) {
        return cmp(_obj) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SortingHealingItem _o2) {
        return Numbers.compare(getIndex(), _o2.getIndex());
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String _itemClass) {
        itemClass = _itemClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String _keyName) {
        keyName = _keyName;
    }

    public int getNbHealedStatus() {
        return nbHealedStatus;
    }

    public void setNbHealedStatus(int _nbHealedStatus) {
        nbHealedStatus = _nbHealedStatus;
    }

    public boolean isRelativeRateHp() {
        return relativeRateHp;
    }

    public void setRelativeRateHp(boolean _relativeRateHp) {
        relativeRateHp = _relativeRateHp;
    }

    public Rate getHp() {
        return hp;
    }

    public void setHp(Rate _hp) {
        hp = _hp;
    }

    public Rate getHpRate() {
        return hpRate;
    }

    public void setHpRate(Rate _hpRate) {
        hpRate = _hpRate;
    }

    public boolean isRelativeRatePp() {
        return relativeRatePp;
    }

    public void setRelativeRatePp(boolean _relativeRatePp) {
        relativeRatePp = _relativeRatePp;
    }

    public Rate getPp() {
        return pp;
    }

    public void setPp(Rate _pp) {
        pp = _pp;
    }

    public boolean isHealOneMove() {
        return healOneMove;
    }

    public void setHealOneMove(boolean _healOneMove) {
        healOneMove = _healOneMove;
    }

    public int getNbStatistics() {
        return nbStatistics;
    }

    public void setNbStatistics(int _nbStatistics) {
        nbStatistics = _nbStatistics;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean _ko) {
        ko = _ko;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }

    public LgInt getNumber() {
        return number;
    }

    public void setNumber(LgInt _number) {
        number = _number;
    }

    public StringList getStatistics() {
        return statistics;
    }

    public StringList getStatus() {
        return status;
    }
}
