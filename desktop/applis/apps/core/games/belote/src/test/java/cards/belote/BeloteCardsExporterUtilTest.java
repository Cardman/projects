package cards.belote;

import cards.belote.enumerations.BeloteCardsExporterUtil;
import cards.belote.enumerations.DeclaresBelote;
import org.junit.Test;

public final class BeloteCardsExporterUtilTest extends EquallableBeloteUtil {
    @Test
    public void fromDeclaresBelote1() {
        assertEq("FOUR_1", BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_1));
    }
    @Test
    public void fromDeclaresBelote2() {
        assertEq("FOUR_KING",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_KING));
    }
    @Test
    public void fromDeclaresBelote3() {
        assertEq("FOUR_QUEEN",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_QUEEN));
    }
    @Test
    public void fromDeclaresBelote4() {
        assertEq("FOUR_JACK",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_JACK));
    }
    @Test
    public void fromDeclaresBelote5() {
        assertEq("FOUR_10",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_10));
    }
    @Test
    public void fromDeclaresBelote6() {
        assertEq("FOUR_9",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_9));
    }
    @Test
    public void fromDeclaresBelote7() {
        assertEq("FOUR_8",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_8));
    }
    @Test
    public void fromDeclaresBelote8() {
        assertEq("FOUR_7",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_7));
    }
    @Test
    public void fromDeclaresBelote9() {
        assertEq("HUNDRED",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED));
    }
    @Test
    public void fromDeclaresBelote10() {
        assertEq("FIFTY",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FIFTY));
    }
    @Test
    public void fromDeclaresBelote11() {
        assertEq("THIRTY",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.THIRTY));
    }
    @Test
    public void fromDeclaresBelote12() {
        assertEq("UNDEFINED",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.UNDEFINED));
    }
}
