package cards.tarot;

import cards.tarot.enumerations.*;
import org.junit.Test;

public final class TarotCardsUtilTest extends EquallableTarotUtil {

    @Test
    public void converBidTarot0(){
        assertEq(BidTarot.FOLD,TarotCardsRetrieverUtil.toBidTarot(TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD)));
    }
    @Test
    public void converBidTarot1(){
        assertEq(BidTarot.TAKE,TarotCardsRetrieverUtil.toBidTarot(TarotCardsExporterUtil.fromBidTarot(BidTarot.TAKE)));
    }
    @Test
    public void converBidTarot2(){
        assertEq(BidTarot.GUARD,TarotCardsRetrieverUtil.toBidTarot(TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD)));
    }
    @Test
    public void converBidTarot3(){
        assertEq(BidTarot.GUARD_WITHOUT,TarotCardsRetrieverUtil.toBidTarot(TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT)));
    }
    @Test
    public void converBidTarot4(){
        assertEq(BidTarot.GUARD_AGAINST,TarotCardsRetrieverUtil.toBidTarot(TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_AGAINST)));
    }
    @Test
    public void converBidTarot5(){
        assertEq(BidTarot.SLAM,TarotCardsRetrieverUtil.toBidTarot(TarotCardsExporterUtil.fromBidTarot(BidTarot.SLAM)));
    }
    @Test
    public void converModeTarot0(){
        assertEq(ModeTarot.NORMAL,TarotCardsRetrieverUtil.toModeTarot(TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL)));
    }
    @Test
    public void converModeTarot1(){
        assertEq(ModeTarot.NORMAL_WITH_MISERE,TarotCardsRetrieverUtil.toModeTarot(TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_MISERE)));
    }
    @Test
    public void converModeTarot2(){
        assertEq(ModeTarot.NORMAL_WITH_ONE_FOR_ONE,TarotCardsRetrieverUtil.toModeTarot(TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_ONE_FOR_ONE)));
    }
    @Test
    public void converModeTarot3(){
        assertEq(ModeTarot.MISERE,TarotCardsRetrieverUtil.toModeTarot(TarotCardsExporterUtil.fromModeTarot(ModeTarot.MISERE)));
    }
    @Test
    public void converModeTarot4(){
        assertEq(ModeTarot.ONE_FOR_ONE,TarotCardsRetrieverUtil.toModeTarot(TarotCardsExporterUtil.fromModeTarot(ModeTarot.ONE_FOR_ONE)));
    }
    @Test
    public void converModeTarot5(){
        assertEq(ModeTarot.NORMAL,TarotCardsRetrieverUtil.toModeTarot(""));
    }
    @Test
    public void converDealingTarot0(){
        assertEq(DealingTarot.DEAL_1_VS_2,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_2)));
    }
    @Test
    public void converDealingTarot1(){
        assertEq(DealingTarot.DEAL_1_VS_3,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_3)));
    }
    @Test
    public void converDealingTarot2(){
        assertEq(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL)));
    }
    @Test
    public void converDealingTarot3(){
        assertEq(DealingTarot.DEAL_2_VS_2_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_KING)));
    }
    @Test
    public void converDealingTarot4(){
        assertEq(DealingTarot.DEAL_2_VS_2_CALL_CHAR,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR)));
    }
    @Test
    public void converDealingTarot5(){
        assertEq(DealingTarot.DEAL_1_VS_4,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_4)));
    }
    @Test
    public void converDealingTarot6(){
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_KING)));
    }
    @Test
    public void converDealingTarot7(){
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_CHAR,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_CHAR)));
    }
    @Test
    public void converDealingTarot8(){
        assertEq(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL)));
    }
    @Test
    public void converDealingTarot9(){
        assertEq(DealingTarot.DEAL_2_VS_4_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_KING)));
    }
    @Test
    public void converDealingTarot10(){
        assertEq(DealingTarot.DEAL_2_VS_4_CALL_CHAR,TarotCardsRetrieverUtil.toDealingTarot(TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR)));
    }
    @Test
    public void converDealingTarot11(){
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot(""));
    }
    @Test
    public void converHandfuls0(){
        assertEq(Handfuls.NO,TarotCardsRetrieverUtil.toHandfuls(TarotCardsExporterUtil.fromHandfuls(Handfuls.NO)));
    }
    @Test
    public void converHandfuls1(){
        assertEq(Handfuls.ONE,TarotCardsRetrieverUtil.toHandfuls(TarotCardsExporterUtil.fromHandfuls(Handfuls.ONE)));
    }
    @Test
    public void converHandfuls2(){
        assertEq(Handfuls.TWO,TarotCardsRetrieverUtil.toHandfuls(TarotCardsExporterUtil.fromHandfuls(Handfuls.TWO)));
    }
    @Test
    public void converHandfuls3(){
        assertEq(Handfuls.THREE,TarotCardsRetrieverUtil.toHandfuls(TarotCardsExporterUtil.fromHandfuls(Handfuls.THREE)));
    }
    @Test
    public void converHandfuls4(){
        assertEq(Handfuls.FOUR,TarotCardsRetrieverUtil.toHandfuls(TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR)));
    }
    @Test
    public void converMiseres0(){
        assertEq(Miseres.TRUMP,TarotCardsRetrieverUtil.toMiseres(TarotCardsExporterUtil.fromMiseres(Miseres.TRUMP)));
    }
    @Test
    public void converMiseres1(){
        assertEq(Miseres.POINT,TarotCardsRetrieverUtil.toMiseres(TarotCardsExporterUtil.fromMiseres(Miseres.POINT)));
    }
    @Test
    public void converMiseres2(){
        assertEq(Miseres.CHARACTER,TarotCardsRetrieverUtil.toMiseres(TarotCardsExporterUtil.fromMiseres(Miseres.CHARACTER)));
    }
    @Test
    public void converMiseres3(){
        assertEq(Miseres.SUIT,TarotCardsRetrieverUtil.toMiseres(TarotCardsExporterUtil.fromMiseres(Miseres.SUIT)));
    }
    @Test
    public void converMiseres4(){
        assertEq(Miseres.LOW_CARDS,TarotCardsRetrieverUtil.toMiseres(TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS)));
    }
    @Test
    public void converMiseres5(){
        assertEq(Miseres.TRUMP,TarotCardsRetrieverUtil.toMiseres(""));
    }
    @Test
    public void converEndDealTarot0(){
        assertEq(EndDealTarot.ATTACK_LOOSE,TarotCardsRetrieverUtil.toEndDealTarot(TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_LOOSE)));
    }
    @Test
    public void converEndDealTarot1(){
        assertEq(EndDealTarot.ATTACK_WIN,TarotCardsRetrieverUtil.toEndDealTarot(TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_WIN)));
    }
    @Test
    public void converEndDealTarot2(){
        assertEq(EndDealTarot.ZERO,TarotCardsRetrieverUtil.toEndDealTarot(TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ZERO)));
    }
    @Test
    public void converEndDealTarot3(){
        assertEq(EndDealTarot.ZERO,TarotCardsRetrieverUtil.toEndDealTarot(""));
    }
    @Test
    public void i0(){
        assertEq(CardTarot.WHITE,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.WHITE)));
    }
    @Test
    public void i1(){
        assertEq(CardTarot.EXCUSE,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.EXCUSE)));
    }
    @Test
    public void i2(){
        assertEq(CardTarot.TRUMP_21,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_21)));
    }
    @Test
    public void i3(){
        assertEq(CardTarot.TRUMP_20,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_20)));
    }
    @Test
    public void i4(){
        assertEq(CardTarot.TRUMP_19,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_19)));
    }
    @Test
    public void i5(){
        assertEq(CardTarot.TRUMP_18,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_18)));
    }
    @Test
    public void i6(){
        assertEq(CardTarot.TRUMP_17,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_17)));
    }
    @Test
    public void i7(){
        assertEq(CardTarot.TRUMP_16,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_16)));
    }
    @Test
    public void i8(){
        assertEq(CardTarot.TRUMP_15,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_15)));
    }
    @Test
    public void i9(){
        assertEq(CardTarot.TRUMP_14,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_14)));
    }
    @Test
    public void i10(){
        assertEq(CardTarot.TRUMP_13,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_13)));
    }
    @Test
    public void i11(){
        assertEq(CardTarot.TRUMP_12,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_12)));
    }
    @Test
    public void i12(){
        assertEq(CardTarot.TRUMP_11,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_11)));
    }
    @Test
    public void i13(){
        assertEq(CardTarot.TRUMP_10,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_10)));
    }
    @Test
    public void i14(){
        assertEq(CardTarot.TRUMP_9,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_9)));
    }
    @Test
    public void i15(){
        assertEq(CardTarot.TRUMP_8,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_8)));
    }
    @Test
    public void i16(){
        assertEq(CardTarot.TRUMP_7,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_7)));
    }
    @Test
    public void i17(){
        assertEq(CardTarot.TRUMP_6,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_6)));
    }
    @Test
    public void i18(){
        assertEq(CardTarot.TRUMP_5,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_5)));
    }
    @Test
    public void i19(){
        assertEq(CardTarot.TRUMP_4,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_4)));
    }
    @Test
    public void i20(){
        assertEq(CardTarot.TRUMP_3,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_3)));
    }
    @Test
    public void i21(){
        assertEq(CardTarot.TRUMP_2,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_2)));
    }
    @Test
    public void i22(){
        assertEq(CardTarot.TRUMP_1,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.TRUMP_1)));
    }
    @Test
    public void i23(){
        assertEq(CardTarot.HEART_KING,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_KING)));
    }
    @Test
    public void i24(){
        assertEq(CardTarot.HEART_QUEEN,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_QUEEN)));
    }
    @Test
    public void i25(){
        assertEq(CardTarot.HEART_KNIGHT,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_KNIGHT)));
    }
    @Test
    public void i26(){
        assertEq(CardTarot.HEART_JACK,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_JACK)));
    }
    @Test
    public void i27(){
        assertEq(CardTarot.HEART_10,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_10)));
    }
    @Test
    public void i28(){
        assertEq(CardTarot.HEART_9,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_9)));
    }
    @Test
    public void i29(){
        assertEq(CardTarot.HEART_8,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_8)));
    }
    @Test
    public void i30(){
        assertEq(CardTarot.HEART_7,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_7)));
    }
    @Test
    public void i31(){
        assertEq(CardTarot.HEART_6,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_6)));
    }
    @Test
    public void i32(){
        assertEq(CardTarot.HEART_5,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_5)));
    }
    @Test
    public void i33(){
        assertEq(CardTarot.HEART_4,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_4)));
    }
    @Test
    public void i34(){
        assertEq(CardTarot.HEART_3,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_3)));
    }
    @Test
    public void i35(){
        assertEq(CardTarot.HEART_2,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_2)));
    }
    @Test
    public void i36(){
        assertEq(CardTarot.HEART_1,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.HEART_1)));
    }
    @Test
    public void i37(){
        assertEq(CardTarot.SPADE_KING,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_KING)));
    }
    @Test
    public void i38(){
        assertEq(CardTarot.SPADE_QUEEN,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_QUEEN)));
    }
    @Test
    public void i39(){
        assertEq(CardTarot.SPADE_KNIGHT,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_KNIGHT)));
    }
    @Test
    public void i40(){
        assertEq(CardTarot.SPADE_JACK,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_JACK)));
    }
    @Test
    public void i41(){
        assertEq(CardTarot.SPADE_10,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_10)));
    }
    @Test
    public void i42(){
        assertEq(CardTarot.SPADE_9,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_9)));
    }
    @Test
    public void i43(){
        assertEq(CardTarot.SPADE_8,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_8)));
    }
    @Test
    public void i44(){
        assertEq(CardTarot.SPADE_7,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_7)));
    }
    @Test
    public void i45(){
        assertEq(CardTarot.SPADE_6,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_6)));
    }
    @Test
    public void i46(){
        assertEq(CardTarot.SPADE_5,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_5)));
    }
    @Test
    public void i47(){
        assertEq(CardTarot.SPADE_4,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_4)));
    }
    @Test
    public void i48(){
        assertEq(CardTarot.SPADE_3,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_3)));
    }
    @Test
    public void i49(){
        assertEq(CardTarot.SPADE_2,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_2)));
    }
    @Test
    public void i50(){
        assertEq(CardTarot.SPADE_1,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.SPADE_1)));
    }
    @Test
    public void i51(){
        assertEq(CardTarot.DIAMOND_KING,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_KING)));
    }
    @Test
    public void i52(){
        assertEq(CardTarot.DIAMOND_QUEEN,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_QUEEN)));
    }
    @Test
    public void i53(){
        assertEq(CardTarot.DIAMOND_KNIGHT,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_KNIGHT)));
    }
    @Test
    public void i54(){
        assertEq(CardTarot.DIAMOND_JACK,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_JACK)));
    }
    @Test
    public void i55(){
        assertEq(CardTarot.DIAMOND_10,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_10)));
    }
    @Test
    public void i56(){
        assertEq(CardTarot.DIAMOND_9,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_9)));
    }
    @Test
    public void i57(){
        assertEq(CardTarot.DIAMOND_8,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_8)));
    }
    @Test
    public void i58(){
        assertEq(CardTarot.DIAMOND_7,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_7)));
    }
    @Test
    public void i59(){
        assertEq(CardTarot.DIAMOND_6,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_6)));
    }
    @Test
    public void i60(){
        assertEq(CardTarot.DIAMOND_5,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_5)));
    }
    @Test
    public void i61(){
        assertEq(CardTarot.DIAMOND_4,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_4)));
    }
    @Test
    public void i62(){
        assertEq(CardTarot.DIAMOND_3,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_3)));
    }
    @Test
    public void i63(){
        assertEq(CardTarot.DIAMOND_2,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_2)));
    }
    @Test
    public void i64(){
        assertEq(CardTarot.DIAMOND_1,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.DIAMOND_1)));
    }
    @Test
    public void i65(){
        assertEq(CardTarot.CLUB_KING,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_KING)));
    }
    @Test
    public void i66(){
        assertEq(CardTarot.CLUB_QUEEN,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_QUEEN)));
    }
    @Test
    public void i67(){
        assertEq(CardTarot.CLUB_KNIGHT,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_KNIGHT)));
    }
    @Test
    public void i68(){
        assertEq(CardTarot.CLUB_JACK,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_JACK)));
    }
    @Test
    public void i69(){
        assertEq(CardTarot.CLUB_10,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_10)));
    }
    @Test
    public void i70(){
        assertEq(CardTarot.CLUB_9,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_9)));
    }
    @Test
    public void i71(){
        assertEq(CardTarot.CLUB_8,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_8)));
    }
    @Test
    public void i72(){
        assertEq(CardTarot.CLUB_7,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_7)));
    }
    @Test
    public void i73(){
        assertEq(CardTarot.CLUB_6,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_6)));
    }
    @Test
    public void i74(){
        assertEq(CardTarot.CLUB_5,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_5)));
    }
    @Test
    public void i75(){
        assertEq(CardTarot.CLUB_4,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_4)));
    }
    @Test
    public void i76(){
        assertEq(CardTarot.CLUB_3,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_3)));
    }
    @Test
    public void i77(){
        assertEq(CardTarot.CLUB_2,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_2)));
    }
    @Test
    public void i78(){
        assertEq(CardTarot.CLUB_1,TarotCardsRetrieverUtil.toCardTarot(TarotCardsExporterUtil.fromCardTarot(CardTarot.CLUB_1)));
    }

}
