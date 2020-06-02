package cards.belote;

import cards.belote.enumerations.DeclaresBelote;
import org.junit.Test;

import static cards.belote.EquallableBeloteUtil.assertEq;

public final class DeclaresBeloteTest {
    @Test
    public void nombreCartes1Test() {
        assertEq(3,DeclaresBelote.THIRTY.nombreCartes());
    }
    @Test
    public void nombreCartes2Test() {
        assertEq(4,DeclaresBelote.FIFTY.nombreCartes());
    }
    @Test
    public void nombreCartes3Test() {
        assertEq(5,DeclaresBelote.HUNDRED.nombreCartes());
    }
    @Test
    public void nombreCartes4Test() {
        assertEq(4,DeclaresBelote.FOUR_1.nombreCartes());
    }
    @Test
    public void nombreCartes5Test() {
        assertEq(4,DeclaresBelote.FOUR_KING.nombreCartes());
    }
    @Test
    public void nombreCartes6Test() {
        assertEq(4,DeclaresBelote.FOUR_QUEEN.nombreCartes());
    }
    @Test
    public void nombreCartes7Test() {
        assertEq(4,DeclaresBelote.FOUR_JACK.nombreCartes());
    }
    @Test
    public void nombreCartes8Test() {
        assertEq(4,DeclaresBelote.FOUR_10.nombreCartes());
    }
    @Test
    public void nombreCartes9Test() {
        assertEq(4,DeclaresBelote.FOUR_9.nombreCartes());
    }
    @Test
    public void nombreCartes10Test() {
        assertEq(4,DeclaresBelote.FOUR_8.nombreCartes());
    }
    @Test
    public void nombreCartes11Test() {
        assertEq(4,DeclaresBelote.FOUR_7.nombreCartes());
    }
}
