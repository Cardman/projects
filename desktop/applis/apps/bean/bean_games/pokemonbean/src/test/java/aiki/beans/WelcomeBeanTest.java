package aiki.beans;

import aiki.beans.moves.AikiBeansMovesStd;
import org.junit.Test;

public final class WelcomeBeanTest extends InitDbWelcome {
    @Test
    public void allMoves1() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(beanWelcome(feedDb()))));
    }
    @Test
    public void allMoves2() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(displaying(beanWelcome(feedDb())))));
    }
}
