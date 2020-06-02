package aiki.beans.moves;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.MoveLine;
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
    private long index;

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
    public String clickMove(Long _index) {
        getForms().put(MOVE, sortedMoves.get(_index.intValue()));
        return MOVE;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
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

    public void setSortedMoves(StringList _sortedMoves) {
        sortedMoves = _sortedMoves;
    }
}