package aiki.util;

import aiki.comments.Comment;
import aiki.db.EquallablePkUtil;
import code.maths.montecarlo.MonteCarloNumber;
import org.junit.Test;

public class LawNumberTest extends EquallablePkUtil {
    @Test
    public void test() {
        assertEq("",new Comment().join());
        assertEq(0,new LawNumber(new MonteCarloNumber(), (short) 0).getNumber());
    }
}
