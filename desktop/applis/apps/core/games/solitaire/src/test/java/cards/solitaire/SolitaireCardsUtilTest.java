package cards.solitaire;

import org.junit.Test;

public final class SolitaireCardsUtilTest extends EquallableSolitaireUtil {
    @Test
    public void i0(){
        assertEq(CardSolitaire.WHITE,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.WHITE)));
    }
    @Test
    public void i1(){
        assertEq(CardSolitaire.HEART_2,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_2)));
    }
    @Test
    public void i2(){
        assertEq(CardSolitaire.HEART_1,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_1)));
    }
    @Test
    public void i3(){
        assertEq(CardSolitaire.HEART_KING,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_KING)));
    }
    @Test
    public void i4(){
        assertEq(CardSolitaire.HEART_QUEEN,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_QUEEN)));
    }
    @Test
    public void i5(){
        assertEq(CardSolitaire.HEART_JACK,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_JACK)));
    }
    @Test
    public void i6(){
        assertEq(CardSolitaire.HEART_10,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_10)));
    }
    @Test
    public void i7(){
        assertEq(CardSolitaire.HEART_9,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_9)));
    }
    @Test
    public void i8(){
        assertEq(CardSolitaire.HEART_8,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_8)));
    }
    @Test
    public void i9(){
        assertEq(CardSolitaire.HEART_7,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_7)));
    }
    @Test
    public void i10(){
        assertEq(CardSolitaire.HEART_6,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_6)));
    }
    @Test
    public void i11(){
        assertEq(CardSolitaire.HEART_5,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_5)));
    }
    @Test
    public void i12(){
        assertEq(CardSolitaire.HEART_4,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_4)));
    }
    @Test
    public void i13(){
        assertEq(CardSolitaire.HEART_3,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.HEART_3)));
    }
    @Test
    public void i14(){
        assertEq(CardSolitaire.SPADE_2,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_2)));
    }
    @Test
    public void i15(){
        assertEq(CardSolitaire.SPADE_1,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_1)));
    }
    @Test
    public void i16(){
        assertEq(CardSolitaire.SPADE_KING,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_KING)));
    }
    @Test
    public void i17(){
        assertEq(CardSolitaire.SPADE_QUEEN,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_QUEEN)));
    }
    @Test
    public void i18(){
        assertEq(CardSolitaire.SPADE_JACK,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_JACK)));
    }
    @Test
    public void i19(){
        assertEq(CardSolitaire.SPADE_10,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_10)));
    }
    @Test
    public void i20(){
        assertEq(CardSolitaire.SPADE_9,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_9)));
    }
    @Test
    public void i21(){
        assertEq(CardSolitaire.SPADE_8,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_8)));
    }
    @Test
    public void i22(){
        assertEq(CardSolitaire.SPADE_7,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_7)));
    }
    @Test
    public void i23(){
        assertEq(CardSolitaire.SPADE_6,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_6)));
    }
    @Test
    public void i24(){
        assertEq(CardSolitaire.SPADE_5,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_5)));
    }
    @Test
    public void i25(){
        assertEq(CardSolitaire.SPADE_4,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_4)));
    }
    @Test
    public void i26(){
        assertEq(CardSolitaire.SPADE_3,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.SPADE_3)));
    }
    @Test
    public void i27(){
        assertEq(CardSolitaire.DIAMOND_2,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_2)));
    }
    @Test
    public void i28(){
        assertEq(CardSolitaire.DIAMOND_1,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_1)));
    }
    @Test
    public void i29(){
        assertEq(CardSolitaire.DIAMOND_KING,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_KING)));
    }
    @Test
    public void i30(){
        assertEq(CardSolitaire.DIAMOND_QUEEN,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_QUEEN)));
    }
    @Test
    public void i31(){
        assertEq(CardSolitaire.DIAMOND_JACK,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_JACK)));
    }
    @Test
    public void i32(){
        assertEq(CardSolitaire.DIAMOND_10,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_10)));
    }
    @Test
    public void i33(){
        assertEq(CardSolitaire.DIAMOND_9,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_9)));
    }
    @Test
    public void i34(){
        assertEq(CardSolitaire.DIAMOND_8,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_8)));
    }
    @Test
    public void i35(){
        assertEq(CardSolitaire.DIAMOND_7,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_7)));
    }
    @Test
    public void i36(){
        assertEq(CardSolitaire.DIAMOND_6,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_6)));
    }
    @Test
    public void i37(){
        assertEq(CardSolitaire.DIAMOND_5,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_5)));
    }
    @Test
    public void i38(){
        assertEq(CardSolitaire.DIAMOND_4,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_4)));
    }
    @Test
    public void i39(){
        assertEq(CardSolitaire.DIAMOND_3,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.DIAMOND_3)));
    }
    @Test
    public void i40(){
        assertEq(CardSolitaire.CLUB_2,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_2)));
    }
    @Test
    public void i41(){
        assertEq(CardSolitaire.CLUB_1,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_1)));
    }
    @Test
    public void i42(){
        assertEq(CardSolitaire.CLUB_KING,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_KING)));
    }
    @Test
    public void i43(){
        assertEq(CardSolitaire.CLUB_QUEEN,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_QUEEN)));
    }
    @Test
    public void i44(){
        assertEq(CardSolitaire.CLUB_JACK,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_JACK)));
    }
    @Test
    public void i45(){
        assertEq(CardSolitaire.CLUB_10,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_10)));
    }
    @Test
    public void i46(){
        assertEq(CardSolitaire.CLUB_9,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_9)));
    }
    @Test
    public void i47(){
        assertEq(CardSolitaire.CLUB_8,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_8)));
    }
    @Test
    public void i48(){
        assertEq(CardSolitaire.CLUB_7,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_7)));
    }
    @Test
    public void i49(){
        assertEq(CardSolitaire.CLUB_6,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_6)));
    }
    @Test
    public void i50(){
        assertEq(CardSolitaire.CLUB_5,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_5)));
    }
    @Test
    public void i51(){
        assertEq(CardSolitaire.CLUB_4,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_4)));
    }
    @Test
    public void i52(){
        assertEq(CardSolitaire.CLUB_3,SolitaireCardsRetrieverUtil.toCardSolitaire(SolitaireCardsExporterUtil.fromCardSolitaire(CardSolitaire.CLUB_3)));
    }
    @Test
    public void t1() {
        assertEq(SolitaireType.CLASSIC,SolitaireType.getSolitaireTypeByName(Integer.toString(SolitaireType.CLASSIC.getKind())));
        assertEq(4,SolitaireType.CLASSIC.getSupp());
    }
    @Test
    public void t2() {
        assertEq(SolitaireType.FREECELL,SolitaireType.getSolitaireTypeByName(Integer.toString(SolitaireType.FREECELL.getKind())));
        assertEq(8,SolitaireType.FREECELL.getSupp());
    }
    @Test
    public void t3() {
        assertEq(SolitaireType.SPIDER,SolitaireType.getSolitaireTypeByName(Integer.toString(SolitaireType.SPIDER.getKind())));
        assertEq(1,SolitaireType.SPIDER.getSupp());
    }
    @Test
    public void t6() {
        assertEq(null,SolitaireType.getSolitaireTypeByName(""));
    }
}
