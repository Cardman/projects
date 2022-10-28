package aiki.beans.moves;

import aiki.beans.CommonBean;
import aiki.beans.facade.dto.MoveLine;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatArrayStruct;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public class MoveLineBean extends CommonBean {
    private MoveLine moveLine;
    private String displayName;
    private short pp;
    private StringList types;
    private String category;
    private short priority;
    private String accuracy;
    private String power;
    private StringList sortedMoves;
    private int index;

    @Override
    public void beforeDisplaying() {
        displayName = moveLine.getDisplayName();
        pp = moveLine.getPp();
        types = moveLine.getTypes();
        category = moveLine.getCategory();
        priority = moveLine.getPriority();
        accuracy = moveLine.getAccuracy();
        power = moveLine.getPower();
    }
    public String clickMove(int _index) {
        getForms().put(CST_MOVE, sortedMoves.get(_index));
        return AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public String getDisplayName() {
        return displayName;
    }

    public short getPp() {
        return pp;
    }

    public StringList getTypes() {
        return types;
    }

    public String getCategory() {
        return category;
    }

    public MoveLine getMoveLine() {
        return moveLine;
    }

    public void setMoveLine(MoveLine _moveLine) {
        moveLine = _moveLine;
    }

    public short getPriority() {
        return priority;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public String getPower() {
        return power;
    }

    public void setSortedMoves(Struct _sortedMoves) {
        sortedMoves = arr(_sortedMoves);
    }

    private static StringList arr(Struct _val) {
        NatArrayStruct arr_ = BeanNatCommonLgNames.getArray(_val);
        StringList elts_ = new StringList();
        int len_ = arr_.getLength();
        for (int i = 0; i < len_; i++) {
            elts_.add(NumParsers.getString(arr_.get(i)).getInstance());
        }
        return elts_;
    }
}