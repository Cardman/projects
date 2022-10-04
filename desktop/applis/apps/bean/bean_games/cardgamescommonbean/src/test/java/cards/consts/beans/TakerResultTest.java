package cards.consts.beans;

import cards.consts.EndGameState;
import org.junit.Test;

public class TakerResultTest extends EquallableCardConstBeanUtil {
    @Test
    public void win1() {
        TakerResult tr_ = new TakerResult();
        tr_.setWinEqualityLoose(EndGameState.WIN);
        assertTrue(tr_.win());
    }
    @Test
    public void win2() {
        TakerResult tr_ = new TakerResult();
        tr_.setWinEqualityLoose(EndGameState.LOOSE);
        assertFalse(tr_.win());
    }
    @Test
    public void loose1() {
        TakerResult tr_ = new TakerResult();
        tr_.setWinEqualityLoose(EndGameState.WIN);
        assertFalse(tr_.loose());
    }
    @Test
    public void loose2() {
        TakerResult tr_ = new TakerResult();
        tr_.setWinEqualityLoose(EndGameState.LOOSE);
        assertTrue(tr_.loose());
    }
    @Test
    public void eq1() {
        TakerResult tr_ = new TakerResult();
        tr_.setWinEqualityLoose(EndGameState.WIN);
        assertFalse(tr_.equality());
    }
    @Test
    public void eq2() {
        TakerResult tr_ = new TakerResult();
        tr_.setWinEqualityLoose(EndGameState.EQUALLITY);
        assertTrue(tr_.equality());
    }
    @Test
    public void suc1() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) 1);
        assertTrue(tr_.successfulBid());
    }
    @Test
    public void suc2() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) -1);
        assertFalse(tr_.successfulBid());
    }
    @Test
    public void fail1() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) 1);
        assertFalse(tr_.failedBid());
    }
    @Test
    public void fail2() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) -1);
        assertTrue(tr_.failedBid());
    }
    @Test
    public void mid1() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) 1);
        assertFalse(tr_.midBid());
    }
    @Test
    public void mid2() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) 0);
        assertTrue(tr_.midBid());
    }
    @Test
    public void pts() {
        TakerResult tr_ = new TakerResult();
        tr_.setDifferenceScoreTaker((short) 1);
        assertEq(1, tr_.absoluteDiff());
        assertEq(1, tr_.getDifferenceScoreTaker());
    }
}
