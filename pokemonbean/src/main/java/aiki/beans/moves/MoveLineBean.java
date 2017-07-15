package aiki.beans.moves;
import code.bean.Accessible;
import code.util.StringList;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.MoveLine;

public class MoveLineBean extends CommonBean {

    @Accessible
    private MoveLine moveLine;

    @Accessible
    private String displayName;

    @Accessible
    private String name;

    @Accessible
    private short pp;

    @Accessible
    private StringList types;

    @Accessible
    private String category;

    @Accessible
    private short priority;

    @Accessible
    private String accuracy;

    @Accessible
    private String power;

    @Accessible
    private StringList sortedMoves;

    @Accessible
    private long index;

    @Override
    public void beforeDisplaying() {
        //super.beforeDisplaying();
        displayName = moveLine.getDisplayName();
        name = moveLine.getName();
        pp = moveLine.getPp();
        types = moveLine.getTypes();
        category = moveLine.getCategory();
        priority = moveLine.getPriority();
        accuracy = moveLine.getAccuracy();
        power = moveLine.getPower();
    }

    @Accessible
    private String clickMove(Long _index) {
        getForms().put(MOVE, sortedMoves.get(_index.intValue()));
        return MOVE;
    }
}
