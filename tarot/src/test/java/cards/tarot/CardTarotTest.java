package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import junitparams.Parameters;

import org.junit.Ignore;
import org.junit.Test;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;

@SuppressWarnings("static-method")
@Ignore
public class CardTarotTest {
    @Test
    public void isPlayable1(){
        assertTrue(!CardTarot.WHITE.isPlayable());
        assertTrue(!CardTarot.WHITE.isCharacter());
        assertEq(0, CardTarot.WHITE.valeur());
        assertEq(Suit.UNDEFINED, CardTarot.WHITE.couleur());
        assertTrue(!CardTarot.EXCUSE.isCharacter());
        assertEq(Suit.UNDEFINED,CardTarot.EXCUSE.couleur());
        assertEq(0,CardTarot.EXCUSE.valeur());
    }
    @Test
    public void isPlayable2Test(){
        assertTrue(CardTarot.TRUMP_21.isPlayable());
    }
    @Test
    public void isPlayable3Test(){
        assertTrue(CardTarot.TRUMP_20.isPlayable());
    }
    @Test
    public void isPlayable4Test(){
        assertTrue(CardTarot.TRUMP_19.isPlayable());
    }
    @Test
    public void isPlayable5Test(){
        assertTrue(CardTarot.TRUMP_18.isPlayable());
    }
    @Test
    public void isPlayable6Test(){
        assertTrue(CardTarot.TRUMP_17.isPlayable());
    }
    @Test
    public void isPlayable7Test(){
        assertTrue(CardTarot.TRUMP_16.isPlayable());
    }
    @Test
    public void isPlayable8Test(){
        assertTrue(CardTarot.TRUMP_15.isPlayable());
    }
    @Test
    public void isPlayable9Test(){
        assertTrue(CardTarot.TRUMP_14.isPlayable());
    }
    @Test
    public void isPlayable10Test(){
        assertTrue(CardTarot.TRUMP_13.isPlayable());
    }
    @Test
    public void isPlayable11Test(){
        assertTrue(CardTarot.TRUMP_12.isPlayable());
    }
    @Test
    public void isPlayable12Test(){
        assertTrue(CardTarot.TRUMP_11.isPlayable());
    }
    @Test
    public void isPlayable13Test(){
        assertTrue(CardTarot.TRUMP_10.isPlayable());
    }
    @Test
    public void isPlayable14Test(){
        assertTrue(CardTarot.TRUMP_9.isPlayable());
    }
    @Test
    public void isPlayable15Test(){
        assertTrue(CardTarot.TRUMP_8.isPlayable());
    }
    @Test
    public void isPlayable16Test(){
        assertTrue(CardTarot.TRUMP_7.isPlayable());
    }
    @Test
    public void isPlayable17Test(){
        assertTrue(CardTarot.TRUMP_6.isPlayable());
    }
    @Test
    public void isPlayable18Test(){
        assertTrue(CardTarot.TRUMP_5.isPlayable());
    }
    @Test
    public void isPlayable19Test(){
        assertTrue(CardTarot.TRUMP_4.isPlayable());
    }
    @Test
    public void isPlayable20Test(){
        assertTrue(CardTarot.TRUMP_3.isPlayable());
    }
    @Test
    public void isPlayable21Test(){
        assertTrue(CardTarot.TRUMP_2.isPlayable());
    }
    @Test
    public void isPlayable22Test(){
        assertTrue(CardTarot.TRUMP_1.isPlayable());
    }
    @Test
    public void isPlayable23Test(){
        assertTrue(CardTarot.HEART_1.isPlayable());
    }
    @Test
    public void isPlayable24Test(){
        assertTrue(CardTarot.HEART_10.isPlayable());
    }
    @Test
    public void isPlayable25Test(){
        assertTrue(CardTarot.HEART_9.isPlayable());
    }
    @Test
    public void isPlayable26Test(){
        assertTrue(CardTarot.HEART_8.isPlayable());
    }
    @Test
    public void isPlayable27Test(){
        assertTrue(CardTarot.HEART_7.isPlayable());
    }
    @Test
    public void isPlayable28Test(){
        assertTrue(CardTarot.HEART_6.isPlayable());
    }
    @Test
    public void isPlayable29Test(){
        assertTrue(CardTarot.HEART_5.isPlayable());
    }
    @Test
    public void isPlayable30Test(){
        assertTrue(CardTarot.HEART_4.isPlayable());
    }
    @Test
    public void isPlayable31Test(){
        assertTrue(CardTarot.HEART_3.isPlayable());
    }
    @Test
    public void isPlayable32Test(){
        assertTrue(CardTarot.HEART_2.isPlayable());
    }
    @Test
    public void isPlayable33Test(){
        assertTrue(CardTarot.SPADE_1.isPlayable());
    }
    @Test
    public void isPlayable34Test(){
        assertTrue(CardTarot.SPADE_10.isPlayable());
    }
    @Test
    public void isPlayable35Test(){
        assertTrue(CardTarot.SPADE_9.isPlayable());
    }
    @Test
    public void isPlayable36Test(){
        assertTrue(CardTarot.SPADE_8.isPlayable());
    }
    @Test
    public void isPlayable37Test(){
        assertTrue(CardTarot.SPADE_7.isPlayable());
    }
    @Test
    public void isPlayable38Test(){
        assertTrue(CardTarot.SPADE_6.isPlayable());
    }
    @Test
    public void isPlayable39Test(){
        assertTrue(CardTarot.SPADE_5.isPlayable());
    }
    @Test
    public void isPlayable40Test(){
        assertTrue(CardTarot.SPADE_4.isPlayable());
    }
    @Test
    public void isPlayable41Test(){
        assertTrue(CardTarot.SPADE_3.isPlayable());
    }
    @Test
    public void isPlayable42Test(){
        assertTrue(CardTarot.SPADE_2.isPlayable());
    }
    @Test
    public void isPlayable43Test(){
        assertTrue(CardTarot.DIAMOND_1.isPlayable());
    }
    @Test
    public void isPlayable44Test(){
        assertTrue(CardTarot.DIAMOND_10.isPlayable());
    }
    @Test
    public void isPlayable45Test(){
        assertTrue(CardTarot.DIAMOND_9.isPlayable());
    }
    @Test
    public void isPlayable46Test(){
        assertTrue(CardTarot.DIAMOND_8.isPlayable());
    }
    @Test
    public void isPlayable47Test(){
        assertTrue(CardTarot.DIAMOND_7.isPlayable());
    }
    @Test
    public void isPlayable48Test(){
        assertTrue(CardTarot.DIAMOND_6.isPlayable());
    }
    @Test
    public void isPlayable49Test(){
        assertTrue(CardTarot.DIAMOND_5.isPlayable());
    }
    @Test
    public void isPlayable50Test(){
        assertTrue(CardTarot.DIAMOND_4.isPlayable());
    }
    @Test
    public void isPlayable51Test(){
        assertTrue(CardTarot.DIAMOND_3.isPlayable());
    }
    @Test
    public void isPlayable52Test(){
        assertTrue(CardTarot.DIAMOND_2.isPlayable());
    }
    @Test
    public void isPlayable53Test(){
        assertTrue(CardTarot.CLUB_1.isPlayable());
    }
    @Test
    public void isPlayable54Test(){
        assertTrue(CardTarot.CLUB_10.isPlayable());
    }
    @Test
    public void isPlayable55Test(){
        assertTrue(CardTarot.CLUB_9.isPlayable());
    }
    @Test
    public void isPlayable56Test(){
        assertTrue(CardTarot.CLUB_8.isPlayable());
    }
    @Test
    public void isPlayable57Test(){
        assertTrue(CardTarot.CLUB_7.isPlayable());
    }
    @Test
    public void isPlayable58Test(){
        assertTrue(CardTarot.CLUB_6.isPlayable());
    }
    @Test
    public void isPlayable59Test(){
        assertTrue(CardTarot.CLUB_5.isPlayable());
    }
    @Test
    public void isPlayable60Test(){
        assertTrue(CardTarot.CLUB_4.isPlayable());
    }
    @Test
    public void isPlayable61Test(){
        assertTrue(CardTarot.CLUB_3.isPlayable());
    }
    @Test
    public void isPlayable62Test(){
        assertTrue(CardTarot.CLUB_2.isPlayable());
    }
    @Test
    public void isPlayable63Test(){
        assertTrue(CardTarot.HEART_JACK.isPlayable());
    }
    @Test
    public void isPlayable64Test(){
        assertTrue(CardTarot.HEART_KNIGHT.isPlayable());
    }
    @Test
    public void isPlayable65Test(){
        assertTrue(CardTarot.HEART_KING.isPlayable());
    }
    @Test
    public void isPlayable66Test(){
        assertTrue(CardTarot.HEART_QUEEN.isPlayable());
    }
    @Test
    public void isPlayable67Test(){
        assertTrue(CardTarot.SPADE_JACK.isPlayable());
    }
    @Test
    public void isPlayable68Test(){
        assertTrue(CardTarot.SPADE_KNIGHT.isPlayable());
    }
    @Test
    public void isPlayable69Test(){
        assertTrue(CardTarot.SPADE_KING.isPlayable());
    }
    @Test
    public void isPlayable70Test(){
        assertTrue(CardTarot.SPADE_QUEEN.isPlayable());
    }
    @Test
    public void isPlayable71Test(){
        assertTrue(CardTarot.DIAMOND_JACK.isPlayable());
    }
    @Test
    public void isPlayable72Test(){
        assertTrue(CardTarot.DIAMOND_KNIGHT.isPlayable());
    }
    @Test
    public void isPlayable73Test(){
        assertTrue(CardTarot.DIAMOND_KING.isPlayable());
    }
    @Test
    public void isPlayable74Test(){
        assertTrue(CardTarot.DIAMOND_QUEEN.isPlayable());
    }
    @Test
    public void isPlayable75Test(){
        assertTrue(CardTarot.CLUB_JACK.isPlayable());
    }
    @Test
    public void isPlayable76Test(){
        assertTrue(CardTarot.CLUB_KNIGHT.isPlayable());
    }
    @Test
    public void isPlayable77Test(){
        assertTrue(CardTarot.CLUB_KING.isPlayable());
    }
    @Test
    public void isPlayable78Test(){
        assertTrue(CardTarot.CLUB_QUEEN.isPlayable());
    }
    @Test
    public void isCharacter_false1Test(){
        assertTrue(!CardTarot.HEART_1.isCharacter());
        assertTrue(CardTarot.HEART_1.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_1.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_1.couleur());
    }
    @Test
    public void isCharacter_false2Test(){
        assertTrue(!CardTarot.HEART_10.isCharacter());
        assertTrue(CardTarot.HEART_10.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_10.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_10.couleur());
    }
    @Test
    public void isCharacter_false3Test(){
        assertTrue(!CardTarot.HEART_9.isCharacter());
        assertTrue(CardTarot.HEART_9.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_9.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_9.couleur());
    }
    @Test
    public void isCharacter_false4Test(){
        assertTrue(!CardTarot.HEART_8.isCharacter());
        assertTrue(CardTarot.HEART_8.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_8.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_8.couleur());
    }
    @Test
    public void isCharacter_false5Test(){
        assertTrue(!CardTarot.HEART_7.isCharacter());
        assertTrue(CardTarot.HEART_7.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_7.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_7.couleur());
    }
    @Test
    public void isCharacter_false6Test(){
        assertTrue(!CardTarot.HEART_6.isCharacter());
        assertTrue(CardTarot.HEART_6.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_6.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_6.couleur());
    }
    @Test
    public void isCharacter_false7Test(){
        assertTrue(!CardTarot.HEART_5.isCharacter());
        assertTrue(CardTarot.HEART_5.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_5.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_5.couleur());
    }
    @Test
    public void isCharacter_false8Test(){
        assertTrue(!CardTarot.HEART_4.isCharacter());
        assertTrue(CardTarot.HEART_4.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_4.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_4.couleur());
    }
    @Test
    public void isCharacter_false9Test(){
        assertTrue(!CardTarot.HEART_3.isCharacter());
        assertTrue(CardTarot.HEART_3.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_3.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_3.couleur());
    }
    @Test
    public void isCharacter_false10Test(){
        assertTrue(!CardTarot.HEART_2.isCharacter());
        assertTrue(CardTarot.HEART_2.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_2.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_2.couleur());
    }
    @Test
    public void isCharacter_false11Test(){
        assertTrue(!CardTarot.SPADE_1.isCharacter());
        assertTrue(CardTarot.SPADE_1.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_1.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_1.couleur());
    }
    @Test
    public void isCharacter_false12Test(){
        assertTrue(!CardTarot.SPADE_10.isCharacter());
        assertTrue(CardTarot.SPADE_10.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_10.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_10.couleur());
    }
    @Test
    public void isCharacter_false13Test(){
        assertTrue(!CardTarot.SPADE_9.isCharacter());
        assertTrue(CardTarot.SPADE_9.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_9.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_9.couleur());
    }
    @Test
    public void isCharacter_false14Test(){
        assertTrue(!CardTarot.SPADE_8.isCharacter());
        assertTrue(CardTarot.SPADE_8.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_8.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_8.couleur());
    }
    @Test
    public void isCharacter_false15Test(){
        assertTrue(!CardTarot.SPADE_7.isCharacter());
        assertTrue(CardTarot.SPADE_7.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_7.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_7.couleur());
    }
    @Test
    public void isCharacter_false16Test(){
        assertTrue(!CardTarot.SPADE_6.isCharacter());
        assertTrue(CardTarot.SPADE_6.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_6.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_6.couleur());
    }
    @Test
    public void isCharacter_false17Test(){
        assertTrue(!CardTarot.SPADE_5.isCharacter());
        assertTrue(CardTarot.SPADE_5.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_5.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_5.couleur());
    }
    @Test
    public void isCharacter_false18Test(){
        assertTrue(!CardTarot.SPADE_4.isCharacter());
        assertTrue(CardTarot.SPADE_4.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_4.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_4.couleur());
    }
    @Test
    public void isCharacter_false19Test(){
        assertTrue(!CardTarot.SPADE_3.isCharacter());
        assertTrue(CardTarot.SPADE_3.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_3.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_3.couleur());
    }
    @Test
    public void isCharacter_false20Test(){
        assertTrue(!CardTarot.SPADE_2.isCharacter());
        assertTrue(CardTarot.SPADE_2.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_2.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_2.couleur());
    }
    @Test
    public void isCharacter_false21Test(){
        assertTrue(!CardTarot.DIAMOND_1.isCharacter());
        assertTrue(CardTarot.DIAMOND_1.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_1.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_1.couleur());
    }
    @Test
    public void isCharacter_false22Test(){
        assertTrue(!CardTarot.DIAMOND_10.isCharacter());
        assertTrue(CardTarot.DIAMOND_10.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_10.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_10.couleur());
    }
    @Test
    public void isCharacter_false23Test(){
        assertTrue(!CardTarot.DIAMOND_9.isCharacter());
        assertTrue(CardTarot.DIAMOND_9.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_9.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_9.couleur());
    }
    @Test
    public void isCharacter_false24Test(){
        assertTrue(!CardTarot.DIAMOND_8.isCharacter());
        assertTrue(CardTarot.DIAMOND_8.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_8.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_8.couleur());
    }
    @Test
    public void isCharacter_false25Test(){
        assertTrue(!CardTarot.DIAMOND_7.isCharacter());
        assertTrue(CardTarot.DIAMOND_7.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_7.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_7.couleur());
    }
    @Test
    public void isCharacter_false26Test(){
        assertTrue(!CardTarot.DIAMOND_6.isCharacter());
        assertTrue(CardTarot.DIAMOND_6.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_6.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_6.couleur());
    }
    @Test
    public void isCharacter_false27Test(){
        assertTrue(!CardTarot.DIAMOND_5.isCharacter());
        assertTrue(CardTarot.DIAMOND_5.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_5.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_5.couleur());
    }
    @Test
    public void isCharacter_false28Test(){
        assertTrue(!CardTarot.DIAMOND_4.isCharacter());
        assertTrue(CardTarot.DIAMOND_4.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_4.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_4.couleur());
    }
    @Test
    public void isCharacter_false29Test(){
        assertTrue(!CardTarot.DIAMOND_3.isCharacter());
        assertTrue(CardTarot.DIAMOND_3.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_3.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_3.couleur());
    }
    @Test
    public void isCharacter_false30Test(){
        assertTrue(!CardTarot.DIAMOND_2.isCharacter());
        assertTrue(CardTarot.DIAMOND_2.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_2.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_2.couleur());
    }
    @Test
    public void isCharacter_false31Test(){
        assertTrue(!CardTarot.CLUB_1.isCharacter());
        assertTrue(CardTarot.CLUB_1.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_1.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_1.couleur());
    }
    @Test
    public void isCharacter_false32Test(){
        assertTrue(!CardTarot.CLUB_10.isCharacter());
        assertTrue(CardTarot.CLUB_10.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_10.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_10.couleur());
    }
    @Test
    public void isCharacter_false33Test(){
        assertTrue(!CardTarot.CLUB_9.isCharacter());
        assertTrue(CardTarot.CLUB_9.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_9.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_9.couleur());
    }
    @Test
    public void isCharacter_false34Test(){
        assertTrue(!CardTarot.CLUB_8.isCharacter());
        assertTrue(CardTarot.CLUB_8.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_8.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_8.couleur());
    }
    @Test
    public void isCharacter_false35Test(){
        assertTrue(!CardTarot.CLUB_7.isCharacter());
        assertTrue(CardTarot.CLUB_7.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_7.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_7.couleur());
    }
    @Test
    public void isCharacter_false36Test(){
        assertTrue(!CardTarot.CLUB_6.isCharacter());
        assertTrue(CardTarot.CLUB_6.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_6.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_6.couleur());
    }
    @Test
    public void isCharacter_false37Test(){
        assertTrue(!CardTarot.CLUB_5.isCharacter());
        assertTrue(CardTarot.CLUB_5.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_5.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_5.couleur());
    }
    @Test
    public void isCharacter_false38Test(){
        assertTrue(!CardTarot.CLUB_4.isCharacter());
        assertTrue(CardTarot.CLUB_4.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_4.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_4.couleur());
    }
    @Test
    public void isCharacter_false39Test(){
        assertTrue(!CardTarot.CLUB_3.isCharacter());
        assertTrue(CardTarot.CLUB_3.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_3.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_3.couleur());
    }
    @Test
    public void isCharacter_false40Test(){
        assertTrue(!CardTarot.CLUB_2.isCharacter());
        assertTrue(CardTarot.CLUB_2.valeur()>0);
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_2.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_2.couleur());
    }
    @Test
    public void isCharacter_true1Test(){
        assertTrue(CardTarot.HEART_JACK.isCharacter());
        assertEq(0, CardTarot.HEART_JACK.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_JACK.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_JACK.couleur());
    }
    @Test
    public void isCharacter_true2Test(){
        assertTrue(CardTarot.HEART_KNIGHT.isCharacter());
        assertEq(0, CardTarot.HEART_KNIGHT.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_KNIGHT.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_KNIGHT.couleur());
    }
    @Test
    public void isCharacter_true3Test(){
        assertTrue(CardTarot.HEART_KING.isCharacter());
        assertEq(0, CardTarot.HEART_KING.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_KING.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_KING.couleur());
    }
    @Test
    public void isCharacter_true4Test(){
        assertTrue(CardTarot.HEART_QUEEN.isCharacter());
        assertEq(0, CardTarot.HEART_QUEEN.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.HEART_QUEEN.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.HEART_QUEEN.couleur());
    }
    @Test
    public void isCharacter_true5Test(){
        assertTrue(CardTarot.SPADE_JACK.isCharacter());
        assertEq(0, CardTarot.SPADE_JACK.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_JACK.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_JACK.couleur());
    }
    @Test
    public void isCharacter_true6Test(){
        assertTrue(CardTarot.SPADE_KNIGHT.isCharacter());
        assertEq(0, CardTarot.SPADE_KNIGHT.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_KNIGHT.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_KNIGHT.couleur());
    }
    @Test
    public void isCharacter_true7Test(){
        assertTrue(CardTarot.SPADE_KING.isCharacter());
        assertEq(0, CardTarot.SPADE_KING.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_KING.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_KING.couleur());
    }
    @Test
    public void isCharacter_true8Test(){
        assertTrue(CardTarot.SPADE_QUEEN.isCharacter());
        assertEq(0, CardTarot.SPADE_QUEEN.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.SPADE_QUEEN.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.SPADE_QUEEN.couleur());
    }
    @Test
    public void isCharacter_true9Test(){
        assertTrue(CardTarot.DIAMOND_JACK.isCharacter());
        assertEq(0, CardTarot.DIAMOND_JACK.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_JACK.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_JACK.couleur());
    }
    @Test
    public void isCharacter_true10Test(){
        assertTrue(CardTarot.DIAMOND_KNIGHT.isCharacter());
        assertEq(0, CardTarot.DIAMOND_KNIGHT.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_KNIGHT.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_KNIGHT.couleur());
    }
    @Test
    public void isCharacter_true11Test(){
        assertTrue(CardTarot.DIAMOND_KING.isCharacter());
        assertEq(0, CardTarot.DIAMOND_KING.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_KING.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_KING.couleur());
    }
    @Test
    public void isCharacter_true12Test(){
        assertTrue(CardTarot.DIAMOND_QUEEN.isCharacter());
        assertEq(0, CardTarot.DIAMOND_QUEEN.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.DIAMOND_QUEEN.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.DIAMOND_QUEEN.couleur());
    }
    @Test
    public void isCharacter_true13Test(){
        assertTrue(CardTarot.CLUB_JACK.isCharacter());
        assertEq(0, CardTarot.CLUB_JACK.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_JACK.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_JACK.couleur());
    }
    @Test
    public void isCharacter_true14Test(){
        assertTrue(CardTarot.CLUB_KNIGHT.isCharacter());
        assertEq(0, CardTarot.CLUB_KNIGHT.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_KNIGHT.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_KNIGHT.couleur());
    }
    @Test
    public void isCharacter_true15Test(){
        assertTrue(CardTarot.CLUB_KING.isCharacter());
        assertEq(0, CardTarot.CLUB_KING.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_KING.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_KING.couleur());
    }
    @Test
    public void isCharacter_true16Test(){
        assertTrue(CardTarot.CLUB_QUEEN.isCharacter());
        assertEq(0, CardTarot.CLUB_QUEEN.valeur());
        assertNotEquals(Suit.TRUMP, CardTarot.CLUB_QUEEN.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.CLUB_QUEEN.couleur());
    }
    @Test
    public void isCharacter_trump1Test(){
        assertTrue(!CardTarot.TRUMP_21.isCharacter());
        assertTrue(CardTarot.TRUMP_21.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_21.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_21.couleur());
    }
    @Test
    public void isCharacter_trump2Test(){
        assertTrue(!CardTarot.TRUMP_20.isCharacter());
        assertTrue(CardTarot.TRUMP_20.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_20.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_20.couleur());
    }
    @Test
    public void isCharacter_trump3Test(){
        assertTrue(!CardTarot.TRUMP_19.isCharacter());
        assertTrue(CardTarot.TRUMP_19.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_19.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_19.couleur());
    }
    @Test
    public void isCharacter_trump4Test(){
        assertTrue(!CardTarot.TRUMP_18.isCharacter());
        assertTrue(CardTarot.TRUMP_18.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_18.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_18.couleur());
    }
    @Test
    public void isCharacter_trump5Test(){
        assertTrue(!CardTarot.TRUMP_17.isCharacter());
        assertTrue(CardTarot.TRUMP_17.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_17.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_17.couleur());
    }
    @Test
    public void isCharacter_trump6Test(){
        assertTrue(!CardTarot.TRUMP_16.isCharacter());
        assertTrue(CardTarot.TRUMP_16.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_16.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_16.couleur());
    }
    @Test
    public void isCharacter_trump7Test(){
        assertTrue(!CardTarot.TRUMP_15.isCharacter());
        assertTrue(CardTarot.TRUMP_15.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_15.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_15.couleur());
    }
    @Test
    public void isCharacter_trump8Test(){
        assertTrue(!CardTarot.TRUMP_14.isCharacter());
        assertTrue(CardTarot.TRUMP_14.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_14.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_14.couleur());
    }
    @Test
    public void isCharacter_trump9Test(){
        assertTrue(!CardTarot.TRUMP_13.isCharacter());
        assertTrue(CardTarot.TRUMP_13.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_13.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_13.couleur());
    }
    @Test
    public void isCharacter_trump10Test(){
        assertTrue(!CardTarot.TRUMP_12.isCharacter());
        assertTrue(CardTarot.TRUMP_12.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_12.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_12.couleur());
    }
    @Test
    public void isCharacter_trump11Test(){
        assertTrue(!CardTarot.TRUMP_11.isCharacter());
        assertTrue(CardTarot.TRUMP_11.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_11.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_11.couleur());
    }
    @Test
    public void isCharacter_trump12Test(){
        assertTrue(!CardTarot.TRUMP_10.isCharacter());
        assertTrue(CardTarot.TRUMP_10.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_10.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_10.couleur());
    }
    @Test
    public void isCharacter_trump13Test(){
        assertTrue(!CardTarot.TRUMP_9.isCharacter());
        assertTrue(CardTarot.TRUMP_9.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_9.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_9.couleur());
    }
    @Test
    public void isCharacter_trump14Test(){
        assertTrue(!CardTarot.TRUMP_8.isCharacter());
        assertTrue(CardTarot.TRUMP_8.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_8.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_8.couleur());
    }
    @Test
    public void isCharacter_trump15Test(){
        assertTrue(!CardTarot.TRUMP_7.isCharacter());
        assertTrue(CardTarot.TRUMP_7.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_7.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_7.couleur());
    }
    @Test
    public void isCharacter_trump16Test(){
        assertTrue(!CardTarot.TRUMP_6.isCharacter());
        assertTrue(CardTarot.TRUMP_6.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_6.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_6.couleur());
    }
    @Test
    public void isCharacter_trump17Test(){
        assertTrue(!CardTarot.TRUMP_5.isCharacter());
        assertTrue(CardTarot.TRUMP_5.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_5.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_5.couleur());
    }
    @Test
    public void isCharacter_trump18Test(){
        assertTrue(!CardTarot.TRUMP_4.isCharacter());
        assertTrue(CardTarot.TRUMP_4.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_4.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_4.couleur());
    }
    @Test
    public void isCharacter_trump19Test(){
        assertTrue(!CardTarot.TRUMP_3.isCharacter());
        assertTrue(CardTarot.TRUMP_3.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_3.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_3.couleur());
    }
    @Test
    public void isCharacter_trump20Test(){
        assertTrue(!CardTarot.TRUMP_2.isCharacter());
        assertTrue(CardTarot.TRUMP_2.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_2.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_2.couleur());
    }
    @Test
    public void isCharacter_trump21Test(){
        assertTrue(!CardTarot.TRUMP_1.isCharacter());
        assertTrue(CardTarot.TRUMP_1.valeur()>0);
        assertEq(Suit.TRUMP, CardTarot.TRUMP_1.couleur());
        assertNotEquals(Suit.UNDEFINED, CardTarot.TRUMP_1.couleur());
    }


    Object[] cardsSuits() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot tCard_: CardTarot.values()) {
                if (tCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                for (CardTarot dCard_: CardTarot.values()) {
                    if (dCard_.couleur() != d) {
                        continue;
                    }
                    args_.add(new Object[]{d,dCard_,tCard_});
                }
            }
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuits")
    public void strength1Test(Suit _couleurDemandee,CardTarot _demCards, CardTarot _trumpCard) {
        byte force_ = _trumpCard.strength(_couleurDemandee);
        byte forceTwo_ = _demCards.strength(_couleurDemandee);
        assertTrue(force_>forceTwo_);
    }

    Object[] cardsSuitsTrump() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot tCard_: CardTarot.values()) {
                if (tCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                for (CardTarot dCard_: CardTarot.values()) {
                    if (dCard_.couleur() != Suit.TRUMP) {
                        continue;
                    }
                    if (tCard_ == dCard_) {
                        continue;
                    }
                    args_.add(new Object[]{d,dCard_,tCard_});
                }
            }
        }
        for (CardTarot tCard_: CardTarot.values()) {
            if (tCard_.couleur() != Suit.TRUMP) {
                continue;
            }
            for (CardTarot dCard_: CardTarot.values()) {
                if (dCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                if (tCard_ == dCard_) {
                    continue;
                }
                args_.add(new Object[]{Suit.TRUMP,dCard_,tCard_});
            }
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuitsTrump")
    public void strength2Test(Suit _couleurDemandee, CardTarot _trumpCard, CardTarot _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurDemandee);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurDemandee);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsTrumpOther() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot tCard_: CardTarot.values()) {
                if (tCard_.couleur() != d) {
                    continue;
                }
                for (CardTarot dCard_: CardTarot.values()) {
                    if (dCard_.couleur() != d) {
                        continue;
                    }
                    if (tCard_ == dCard_) {
                        continue;
                    }
                    args_.add(new Object[]{d,dCard_,tCard_});
                }
            }
        }
        for (CardTarot tCard_: CardTarot.values()) {
            if (tCard_.couleur() != Suit.TRUMP) {
                continue;
            }
            for (CardTarot dCard_: CardTarot.values()) {
                if (dCard_.couleur() != Suit.TRUMP) {
                    continue;
                }
                if (tCard_ == dCard_) {
                    continue;
                }
                args_.add(new Object[]{Suit.TRUMP,dCard_,tCard_});
            }
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuitsTrumpOther")
    public void strength3Test(Suit _couleurDemandee, CardTarot _trumpCard, CardTarot _trumpCardTwo) {
        byte force_ = _trumpCard.strength(_couleurDemandee);
        byte forceTwo_ = _trumpCardTwo.strength(_couleurDemandee);
        assertNotEquals(force_, forceTwo_);
        assertTrue(force_ > 0);
    }

    Object[] cardsSuitsZeros() {
        CustList<Object[]> args_ = new CustList<Object[]>();
        for (Suit d: Suit.couleursOrdinaires()) {
            for (CardTarot dCard_: CardTarot.values()) {
                if (!dCard_.isPlayable()) {
                    continue;
                }
                if (dCard_.couleur() == Suit.TRUMP) {
                    continue;
                }
                if (dCard_.couleur() == d) {
                    continue;
                }
                args_.add(new Object[]{d,dCard_});
            }
        }
        for (CardTarot dCard_: CardTarot.values()) {
            if (!dCard_.isPlayable()) {
                continue;
            }
            if (dCard_.couleur() == Suit.TRUMP) {
                continue;
            }
            args_.add(new Object[]{Suit.TRUMP,dCard_});
        }
        return args_.toArray();
    }

    @Test
    @Parameters(method="cardsSuitsZeros")
    public void strength4Test(Suit _couleurDemandee, CardTarot _card) {
        byte force_ = _card.strength(_couleurDemandee);
        assertEq(0, force_);
    }}