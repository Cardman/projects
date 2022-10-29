package aiki.beans.moves;

import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import org.junit.Test;

public final class MoveBeanTest extends InitDbMove {
    @Test
    public void name1() {
        assertEq(M_DAM_TR,callMoveBeanDisplayNameGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void name2() {
        assertEq(M_STA_TR,callMoveBeanDisplayNameGet(dispMove(feedDbMoveSta(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true),1)));
    }
}
