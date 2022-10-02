package cards.belote;

import cards.belote.enumerations.BeloteCardsRetrieverUtil;
import cards.belote.enumerations.DeclaresBelote;
import org.junit.Test;

public final class BeloteCardsRetrieverUtilTest extends EquallableBeloteUtil {
    @Test
    public void toDeclaresBelote1() {
        assertEq(DeclaresBelote.FOUR_1, BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_1"));
    }
    @Test
    public void toDeclaresBelote2() {
        assertEq(DeclaresBelote.FOUR_KING,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_KING"));
    }
    @Test
    public void toDeclaresBelote3() {
        assertEq(DeclaresBelote.FOUR_QUEEN,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_QUEEN"));
    }
    @Test
    public void toDeclaresBelote4() {
        assertEq(DeclaresBelote.FOUR_JACK,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_JACK"));
    }
    @Test
    public void toDeclaresBelote5() {
        assertEq(DeclaresBelote.FOUR_10,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_10"));
    }
    @Test
    public void toDeclaresBelote6() {
        assertEq(DeclaresBelote.FOUR_9,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_9"));
    }
    @Test
    public void toDeclaresBelote7() {
        assertEq(DeclaresBelote.FOUR_8,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_8"));
    }
    @Test
    public void toDeclaresBelote8() {
        assertEq(DeclaresBelote.FOUR_7,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_7"));
    }
    @Test
    public void toDeclaresBelote9() {
        assertEq(DeclaresBelote.HUNDRED,BeloteCardsRetrieverUtil.toDeclaresBelote("HUNDRED"));
    }
    @Test
    public void toDeclaresBelote10() {
        assertEq(DeclaresBelote.FIFTY,BeloteCardsRetrieverUtil.toDeclaresBelote("FIFTY"));
    }
    @Test
    public void toDeclaresBelote11() {
        assertEq(DeclaresBelote.THIRTY,BeloteCardsRetrieverUtil.toDeclaresBelote("THIRTY"));
    }
    @Test
    public void toDeclaresBelote12() {
        assertEq(DeclaresBelote.UNDEFINED,BeloteCardsRetrieverUtil.toDeclaresBelote("UNDEFINED"));
    }
}
