package cards.president;

import cards.president.enumerations.*;
import org.junit.Test;

public final class PresidentCardsUtilTest extends EquallablePresidentUtil {

    @Test
    public void converEqualtyPlaying0(){
        assertEq(EqualtyPlaying.FORBIDDEN,PresidentCardsRetrieverUtil.toEqualtyPlaying(PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.FORBIDDEN)));
    }
    @Test
    public void converEqualtyPlaying1(){
        assertEq(EqualtyPlaying.SKIP_ALWAYS_NEXT,PresidentCardsRetrieverUtil.toEqualtyPlaying(PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_ALWAYS_NEXT)));
    }
    @Test
    public void converEqualtyPlaying2(){
        assertEq(EqualtyPlaying.SKIP_DIFF_NEXT_STOP,PresidentCardsRetrieverUtil.toEqualtyPlaying(PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_DIFF_NEXT_STOP)));
    }
    @Test
    public void converEqualtyPlaying3(){
        assertEq(EqualtyPlaying.NO_SKIP,PresidentCardsRetrieverUtil.toEqualtyPlaying(PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.NO_SKIP)));
    }
    @Test
    public void converEqualtyPlaying4(){
        assertEq(EqualtyPlaying.SKIP_ALWAYS_NEXT,PresidentCardsRetrieverUtil.toEqualtyPlaying(""));
    }
    @Test
    public void i0(){
        assertEq(CardPresident.WHITE,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.WHITE)));
    }
    @Test
    public void i1(){
        assertEq(CardPresident.HEART_2,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_2)));
    }
    @Test
    public void i2(){
        assertEq(CardPresident.HEART_1,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_1)));
    }
    @Test
    public void i3(){
        assertEq(CardPresident.HEART_KING,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_KING)));
    }
    @Test
    public void i4(){
        assertEq(CardPresident.HEART_QUEEN,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_QUEEN)));
    }
    @Test
    public void i5(){
        assertEq(CardPresident.HEART_JACK,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_JACK)));
    }
    @Test
    public void i6(){
        assertEq(CardPresident.HEART_10,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_10)));
    }
    @Test
    public void i7(){
        assertEq(CardPresident.HEART_9,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_9)));
    }
    @Test
    public void i8(){
        assertEq(CardPresident.HEART_8,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_8)));
    }
    @Test
    public void i9(){
        assertEq(CardPresident.HEART_7,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_7)));
    }
    @Test
    public void i10(){
        assertEq(CardPresident.HEART_6,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_6)));
    }
    @Test
    public void i11(){
        assertEq(CardPresident.HEART_5,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_5)));
    }
    @Test
    public void i12(){
        assertEq(CardPresident.HEART_4,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_4)));
    }
    @Test
    public void i13(){
        assertEq(CardPresident.HEART_3,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.HEART_3)));
    }
    @Test
    public void i14(){
        assertEq(CardPresident.SPADE_2,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_2)));
    }
    @Test
    public void i15(){
        assertEq(CardPresident.SPADE_1,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_1)));
    }
    @Test
    public void i16(){
        assertEq(CardPresident.SPADE_KING,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_KING)));
    }
    @Test
    public void i17(){
        assertEq(CardPresident.SPADE_QUEEN,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_QUEEN)));
    }
    @Test
    public void i18(){
        assertEq(CardPresident.SPADE_JACK,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_JACK)));
    }
    @Test
    public void i19(){
        assertEq(CardPresident.SPADE_10,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_10)));
    }
    @Test
    public void i20(){
        assertEq(CardPresident.SPADE_9,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_9)));
    }
    @Test
    public void i21(){
        assertEq(CardPresident.SPADE_8,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_8)));
    }
    @Test
    public void i22(){
        assertEq(CardPresident.SPADE_7,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_7)));
    }
    @Test
    public void i23(){
        assertEq(CardPresident.SPADE_6,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_6)));
    }
    @Test
    public void i24(){
        assertEq(CardPresident.SPADE_5,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_5)));
    }
    @Test
    public void i25(){
        assertEq(CardPresident.SPADE_4,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_4)));
    }
    @Test
    public void i26(){
        assertEq(CardPresident.SPADE_3,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.SPADE_3)));
    }
    @Test
    public void i27(){
        assertEq(CardPresident.DIAMOND_2,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_2)));
    }
    @Test
    public void i28(){
        assertEq(CardPresident.DIAMOND_1,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_1)));
    }
    @Test
    public void i29(){
        assertEq(CardPresident.DIAMOND_KING,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_KING)));
    }
    @Test
    public void i30(){
        assertEq(CardPresident.DIAMOND_QUEEN,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_QUEEN)));
    }
    @Test
    public void i31(){
        assertEq(CardPresident.DIAMOND_JACK,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_JACK)));
    }
    @Test
    public void i32(){
        assertEq(CardPresident.DIAMOND_10,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_10)));
    }
    @Test
    public void i33(){
        assertEq(CardPresident.DIAMOND_9,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_9)));
    }
    @Test
    public void i34(){
        assertEq(CardPresident.DIAMOND_8,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_8)));
    }
    @Test
    public void i35(){
        assertEq(CardPresident.DIAMOND_7,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_7)));
    }
    @Test
    public void i36(){
        assertEq(CardPresident.DIAMOND_6,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_6)));
    }
    @Test
    public void i37(){
        assertEq(CardPresident.DIAMOND_5,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_5)));
    }
    @Test
    public void i38(){
        assertEq(CardPresident.DIAMOND_4,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_4)));
    }
    @Test
    public void i39(){
        assertEq(CardPresident.DIAMOND_3,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.DIAMOND_3)));
    }
    @Test
    public void i40(){
        assertEq(CardPresident.CLUB_2,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_2)));
    }
    @Test
    public void i41(){
        assertEq(CardPresident.CLUB_1,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_1)));
    }
    @Test
    public void i42(){
        assertEq(CardPresident.CLUB_KING,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_KING)));
    }
    @Test
    public void i43(){
        assertEq(CardPresident.CLUB_QUEEN,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_QUEEN)));
    }
    @Test
    public void i44(){
        assertEq(CardPresident.CLUB_JACK,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_JACK)));
    }
    @Test
    public void i45(){
        assertEq(CardPresident.CLUB_10,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_10)));
    }
    @Test
    public void i46(){
        assertEq(CardPresident.CLUB_9,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_9)));
    }
    @Test
    public void i47(){
        assertEq(CardPresident.CLUB_8,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_8)));
    }
    @Test
    public void i48(){
        assertEq(CardPresident.CLUB_7,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_7)));
    }
    @Test
    public void i49(){
        assertEq(CardPresident.CLUB_6,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_6)));
    }
    @Test
    public void i50(){
        assertEq(CardPresident.CLUB_5,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_5)));
    }
    @Test
    public void i51(){
        assertEq(CardPresident.CLUB_4,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_4)));
    }
    @Test
    public void i52(){
        assertEq(CardPresident.CLUB_3,PresidentCardsRetrieverUtil.toCardPresident(PresidentCardsExporterUtil.fromCardPresident(CardPresident.CLUB_3)));
    }

}
