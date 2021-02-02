package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import aiki.game.fight.ActivityOfMove;
import code.util.StringList;
import org.junit.Test;

public class ListActivityOfMovesTest extends EquallablePkUtil {
    @Test
    public void getVal1() {
        ListActivityOfMoves l_ = new ListActivityOfMoves();
        assertEq(0,l_.getVal(new StringList()).getNbTurn());
    }
    @Test
    public void getVal2() {
        ListActivityOfMoves l_ = new ListActivityOfMoves();
        l_.add(new ListActivityOfMove(new StringList(),new ActivityOfMove()));
        assertEq(0,l_.getVal(new StringList()).getNbTurn());
    }
    @Test
    public void getVal3() {
        ListActivityOfMoves l_ = new ListActivityOfMoves();
        l_.add(new ListActivityOfMove(new StringList(""),new ActivityOfMove()));
        assertEq(0,l_.getVal(new StringList()).getNbTurn());
    }
}
