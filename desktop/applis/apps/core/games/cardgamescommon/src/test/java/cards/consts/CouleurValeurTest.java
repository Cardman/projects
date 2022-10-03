package cards.consts;

import org.junit.Test;

public final class CouleurValeurTest extends EquallableCardsUtil {

    @Test
    public void card0(){
        assertEq(0,CouleurValeur.exc());
    }

    @Test
    public void card1(){
        assertEq(1,CouleurValeur.trump(21));
    }

    @Test
    public void card2(){
        assertEq(2,CouleurValeur.trump(20));
    }

    @Test
    public void card3(){
        assertEq(3,CouleurValeur.trump(19));
    }

    @Test
    public void card4(){
        assertEq(4,CouleurValeur.trump(18));
    }

    @Test
    public void card5(){
        assertEq(5,CouleurValeur.trump(17));
    }

    @Test
    public void card6(){
        assertEq(6,CouleurValeur.trump(16));
    }

    @Test
    public void card7(){
        assertEq(7,CouleurValeur.trump(15));
    }

    @Test
    public void card8(){
        assertEq(8,CouleurValeur.trump(14));
    }

    @Test
    public void card9(){
        assertEq(9,CouleurValeur.trump(13));
    }

    @Test
    public void card10(){
        assertEq(10,CouleurValeur.trump(12));
    }

    @Test
    public void card11(){
        assertEq(11,CouleurValeur.trump(11));
    }

    @Test
    public void card12(){
        assertEq(12,CouleurValeur.trump(10));
    }

    @Test
    public void card13(){
        assertEq(13,CouleurValeur.trump(9));
    }

    @Test
    public void card14(){
        assertEq(14,CouleurValeur.trump(8));
    }

    @Test
    public void card15(){
        assertEq(15,CouleurValeur.trump(7));
    }

    @Test
    public void card16(){
        assertEq(16,CouleurValeur.trump(6));
    }

    @Test
    public void card17(){
        assertEq(17,CouleurValeur.trump(5));
    }

    @Test
    public void card18(){
        assertEq(18,CouleurValeur.trump(4));
    }

    @Test
    public void card19(){
        assertEq(19,CouleurValeur.trump(3));
    }

    @Test
    public void card20(){
        assertEq(20,CouleurValeur.trump(2));
    }

    @Test
    public void card21(){
        assertEq(21,CouleurValeur.trump(1));
    }

    @Test
    public void card22(){
        assertEq(22,CouleurValeur.suit(Suit.HEART,CardChar.KING));
    }

    @Test
    public void card23(){
        assertEq(23,CouleurValeur.suit(Suit.HEART,CardChar.QUEEN));
    }

    @Test
    public void card24(){
        assertEq(24,CouleurValeur.suit(Suit.HEART,CardChar.KNIGHT));
    }

    @Test
    public void card25(){
        assertEq(25,CouleurValeur.suit(Suit.HEART,CardChar.JACK));
    }

    @Test
    public void card26(){
        assertEq(26,CouleurValeur.suit(Suit.HEART,10));
    }

    @Test
    public void card27(){
        assertEq(27,CouleurValeur.suit(Suit.HEART,9));
    }

    @Test
    public void card28(){
        assertEq(28,CouleurValeur.suit(Suit.HEART,8));
    }

    @Test
    public void card29(){
        assertEq(29,CouleurValeur.suit(Suit.HEART,7));
    }

    @Test
    public void card30(){
        assertEq(30,CouleurValeur.suit(Suit.HEART,6));
    }

    @Test
    public void card31(){
        assertEq(31,CouleurValeur.suit(Suit.HEART,5));
    }

    @Test
    public void card32(){
        assertEq(32,CouleurValeur.suit(Suit.HEART,4));
    }

    @Test
    public void card33(){
        assertEq(33,CouleurValeur.suit(Suit.HEART,3));
    }

    @Test
    public void card34(){
        assertEq(34,CouleurValeur.suit(Suit.HEART,2));
    }

    @Test
    public void card35(){
        assertEq(35,CouleurValeur.suit(Suit.HEART,1));
    }

    @Test
    public void card36(){
        assertEq(36,CouleurValeur.suit(Suit.SPADE,CardChar.KING));
    }

    @Test
    public void card37(){
        assertEq(37,CouleurValeur.suit(Suit.SPADE,CardChar.QUEEN));
    }

    @Test
    public void card38(){
        assertEq(38,CouleurValeur.suit(Suit.SPADE,CardChar.KNIGHT));
    }

    @Test
    public void card39(){
        assertEq(39,CouleurValeur.suit(Suit.SPADE,CardChar.JACK));
    }

    @Test
    public void card40(){
        assertEq(40,CouleurValeur.suit(Suit.SPADE,10));
    }

    @Test
    public void card41(){
        assertEq(41,CouleurValeur.suit(Suit.SPADE,9));
    }

    @Test
    public void card42(){
        assertEq(42,CouleurValeur.suit(Suit.SPADE,8));
    }

    @Test
    public void card43(){
        assertEq(43,CouleurValeur.suit(Suit.SPADE,7));
    }

    @Test
    public void card44(){
        assertEq(44,CouleurValeur.suit(Suit.SPADE,6));
    }

    @Test
    public void card45(){
        assertEq(45,CouleurValeur.suit(Suit.SPADE,5));
    }

    @Test
    public void card46(){
        assertEq(46,CouleurValeur.suit(Suit.SPADE,4));
    }

    @Test
    public void card47(){
        assertEq(47,CouleurValeur.suit(Suit.SPADE,3));
    }

    @Test
    public void card48(){
        assertEq(48,CouleurValeur.suit(Suit.SPADE,2));
    }

    @Test
    public void card49(){
        assertEq(49,CouleurValeur.suit(Suit.SPADE,1));
    }

    @Test
    public void card50(){
        assertEq(50,CouleurValeur.suit(Suit.DIAMOND,CardChar.KING));
    }

    @Test
    public void card51(){
        assertEq(51,CouleurValeur.suit(Suit.DIAMOND,CardChar.QUEEN));
    }

    @Test
    public void card52(){
        assertEq(52,CouleurValeur.suit(Suit.DIAMOND,CardChar.KNIGHT));
    }

    @Test
    public void card53(){
        assertEq(53,CouleurValeur.suit(Suit.DIAMOND,CardChar.JACK));
    }

    @Test
    public void card54(){
        assertEq(54,CouleurValeur.suit(Suit.DIAMOND,10));
    }

    @Test
    public void card55(){
        assertEq(55,CouleurValeur.suit(Suit.DIAMOND,9));
    }

    @Test
    public void card56(){
        assertEq(56,CouleurValeur.suit(Suit.DIAMOND,8));
    }

    @Test
    public void card57(){
        assertEq(57,CouleurValeur.suit(Suit.DIAMOND,7));
    }

    @Test
    public void card58(){
        assertEq(58,CouleurValeur.suit(Suit.DIAMOND,6));
    }

    @Test
    public void card59(){
        assertEq(59,CouleurValeur.suit(Suit.DIAMOND,5));
    }

    @Test
    public void card60(){
        assertEq(60,CouleurValeur.suit(Suit.DIAMOND,4));
    }

    @Test
    public void card61(){
        assertEq(61,CouleurValeur.suit(Suit.DIAMOND,3));
    }

    @Test
    public void card62(){
        assertEq(62,CouleurValeur.suit(Suit.DIAMOND,2));
    }

    @Test
    public void card63(){
        assertEq(63,CouleurValeur.suit(Suit.DIAMOND,1));
    }

    @Test
    public void card64(){
        assertEq(64,CouleurValeur.suit(Suit.CLUB,CardChar.KING));
    }

    @Test
    public void card65(){
        assertEq(65,CouleurValeur.suit(Suit.CLUB,CardChar.QUEEN));
    }

    @Test
    public void card66(){
        assertEq(66,CouleurValeur.suit(Suit.CLUB,CardChar.KNIGHT));
    }

    @Test
    public void card67(){
        assertEq(67,CouleurValeur.suit(Suit.CLUB,CardChar.JACK));
    }

    @Test
    public void card68(){
        assertEq(68,CouleurValeur.suit(Suit.CLUB,10));
    }

    @Test
    public void card69(){
        assertEq(69,CouleurValeur.suit(Suit.CLUB,9));
    }

    @Test
    public void card70(){
        assertEq(70,CouleurValeur.suit(Suit.CLUB,8));
    }

    @Test
    public void card71(){
        assertEq(71,CouleurValeur.suit(Suit.CLUB,7));
    }

    @Test
    public void card72(){
        assertEq(72,CouleurValeur.suit(Suit.CLUB,6));
    }

    @Test
    public void card73(){
        assertEq(73,CouleurValeur.suit(Suit.CLUB,5));
    }

    @Test
    public void card74(){
        assertEq(74,CouleurValeur.suit(Suit.CLUB,4));
    }

    @Test
    public void card75(){
        assertEq(75,CouleurValeur.suit(Suit.CLUB,3));
    }

    @Test
    public void card76(){
        assertEq(76,CouleurValeur.suit(Suit.CLUB,2));
    }

    @Test
    public void card77(){
        assertEq(77,CouleurValeur.suit(Suit.CLUB,1));
    }

    @Test
    public void no() {
        CouleurValeur couleurValeur_ = new CouleurValeur(Suit.UNDEFINED, (byte) 0, CardChar.UNDEFINED, true);
        assertEq(0, couleurValeur_.getNo());
        assertEq(0, couleurValeur_.getValeur());
        assertSame(CardChar.UNDEFINED, couleurValeur_.getNomFigure());
        assertSame(Suit.UNDEFINED, couleurValeur_.getCouleur());
        assertTrue(couleurValeur_.isJouable());
    }

    @Test
    public void noPl() {
        CouleurValeur couleurValeur_ = new CouleurValeur(Suit.UNDEFINED, (byte) 0, CardChar.UNDEFINED, false);
        assertEq("WHITE", couleurValeur_.getSt());
    }
    @Test
    public void st0(){
        assertEq("EXCUSE",CouleurValeur.excSt());
    }
    @Test
    public void st1(){
        assertEq("TRUMP_21",CouleurValeur.trumpSt(21));
    }
    @Test
    public void st2(){
        assertEq("TRUMP_20",CouleurValeur.trumpSt(20));
    }
    @Test
    public void st3(){
        assertEq("TRUMP_19",CouleurValeur.trumpSt(19));
    }
    @Test
    public void st4(){
        assertEq("TRUMP_18",CouleurValeur.trumpSt(18));
    }
    @Test
    public void st5(){
        assertEq("TRUMP_17",CouleurValeur.trumpSt(17));
    }
    @Test
    public void st6(){
        assertEq("TRUMP_16",CouleurValeur.trumpSt(16));
    }
    @Test
    public void st7(){
        assertEq("TRUMP_15",CouleurValeur.trumpSt(15));
    }
    @Test
    public void st8(){
        assertEq("TRUMP_14",CouleurValeur.trumpSt(14));
    }
    @Test
    public void st9(){
        assertEq("TRUMP_13",CouleurValeur.trumpSt(13));
    }
    @Test
    public void st10(){
        assertEq("TRUMP_12",CouleurValeur.trumpSt(12));
    }
    @Test
    public void st11(){
        assertEq("TRUMP_11",CouleurValeur.trumpSt(11));
    }
    @Test
    public void st12(){
        assertEq("TRUMP_10",CouleurValeur.trumpSt(10));
    }
    @Test
    public void st13(){
        assertEq("TRUMP_9",CouleurValeur.trumpSt(9));
    }
    @Test
    public void st14(){
        assertEq("TRUMP_8",CouleurValeur.trumpSt(8));
    }
    @Test
    public void st15(){
        assertEq("TRUMP_7",CouleurValeur.trumpSt(7));
    }
    @Test
    public void st16(){
        assertEq("TRUMP_6",CouleurValeur.trumpSt(6));
    }
    @Test
    public void st17(){
        assertEq("TRUMP_5",CouleurValeur.trumpSt(5));
    }
    @Test
    public void st18(){
        assertEq("TRUMP_4",CouleurValeur.trumpSt(4));
    }
    @Test
    public void st19(){
        assertEq("TRUMP_3",CouleurValeur.trumpSt(3));
    }
    @Test
    public void st20(){
        assertEq("TRUMP_2",CouleurValeur.trumpSt(2));
    }
    @Test
    public void st21(){
        assertEq("TRUMP_1",CouleurValeur.trumpSt(1));
    }
    @Test
    public void st22(){
        assertEq("HEART_KING",CouleurValeur.suitSt(Suit.HEART,CardChar.KING));
    }
    @Test
    public void st23(){
        assertEq("HEART_QUEEN",CouleurValeur.suitSt(Suit.HEART,CardChar.QUEEN));
    }
    @Test
    public void st24(){
        assertEq("HEART_KNIGHT",CouleurValeur.suitSt(Suit.HEART,CardChar.KNIGHT));
    }
    @Test
    public void st25(){
        assertEq("HEART_JACK",CouleurValeur.suitSt(Suit.HEART,CardChar.JACK));
    }
    @Test
    public void st26(){
        assertEq("HEART_10",CouleurValeur.suitSt(Suit.HEART,10));
    }
    @Test
    public void st27(){
        assertEq("HEART_9",CouleurValeur.suitSt(Suit.HEART,9));
    }
    @Test
    public void st28(){
        assertEq("HEART_8",CouleurValeur.suitSt(Suit.HEART,8));
    }
    @Test
    public void st29(){
        assertEq("HEART_7",CouleurValeur.suitSt(Suit.HEART,7));
    }
    @Test
    public void st30(){
        assertEq("HEART_6",CouleurValeur.suitSt(Suit.HEART,6));
    }
    @Test
    public void st31(){
        assertEq("HEART_5",CouleurValeur.suitSt(Suit.HEART,5));
    }
    @Test
    public void st32(){
        assertEq("HEART_4",CouleurValeur.suitSt(Suit.HEART,4));
    }
    @Test
    public void st33(){
        assertEq("HEART_3",CouleurValeur.suitSt(Suit.HEART,3));
    }
    @Test
    public void st34(){
        assertEq("HEART_2",CouleurValeur.suitSt(Suit.HEART,2));
    }
    @Test
    public void st35(){
        assertEq("HEART_1",CouleurValeur.suitSt(Suit.HEART,1));
    }
    @Test
    public void st36(){
        assertEq("SPADE_KING",CouleurValeur.suitSt(Suit.SPADE,CardChar.KING));
    }
    @Test
    public void st37(){
        assertEq("SPADE_QUEEN",CouleurValeur.suitSt(Suit.SPADE,CardChar.QUEEN));
    }
    @Test
    public void st38(){
        assertEq("SPADE_KNIGHT",CouleurValeur.suitSt(Suit.SPADE,CardChar.KNIGHT));
    }
    @Test
    public void st39(){
        assertEq("SPADE_JACK",CouleurValeur.suitSt(Suit.SPADE,CardChar.JACK));
    }
    @Test
    public void st40(){
        assertEq("SPADE_10",CouleurValeur.suitSt(Suit.SPADE,10));
    }
    @Test
    public void st41(){
        assertEq("SPADE_9",CouleurValeur.suitSt(Suit.SPADE,9));
    }
    @Test
    public void st42(){
        assertEq("SPADE_8",CouleurValeur.suitSt(Suit.SPADE,8));
    }
    @Test
    public void st43(){
        assertEq("SPADE_7",CouleurValeur.suitSt(Suit.SPADE,7));
    }
    @Test
    public void st44(){
        assertEq("SPADE_6",CouleurValeur.suitSt(Suit.SPADE,6));
    }
    @Test
    public void st45(){
        assertEq("SPADE_5",CouleurValeur.suitSt(Suit.SPADE,5));
    }
    @Test
    public void st46(){
        assertEq("SPADE_4",CouleurValeur.suitSt(Suit.SPADE,4));
    }
    @Test
    public void st47(){
        assertEq("SPADE_3",CouleurValeur.suitSt(Suit.SPADE,3));
    }
    @Test
    public void st48(){
        assertEq("SPADE_2",CouleurValeur.suitSt(Suit.SPADE,2));
    }
    @Test
    public void st49(){
        assertEq("SPADE_1",CouleurValeur.suitSt(Suit.SPADE,1));
    }
    @Test
    public void st50(){
        assertEq("DIAMOND_KING",CouleurValeur.suitSt(Suit.DIAMOND,CardChar.KING));
    }
    @Test
    public void st51(){
        assertEq("DIAMOND_QUEEN",CouleurValeur.suitSt(Suit.DIAMOND,CardChar.QUEEN));
    }
    @Test
    public void st52(){
        assertEq("DIAMOND_KNIGHT",CouleurValeur.suitSt(Suit.DIAMOND,CardChar.KNIGHT));
    }
    @Test
    public void st53(){
        assertEq("DIAMOND_JACK",CouleurValeur.suitSt(Suit.DIAMOND,CardChar.JACK));
    }
    @Test
    public void st54(){
        assertEq("DIAMOND_10",CouleurValeur.suitSt(Suit.DIAMOND,10));
    }
    @Test
    public void st55(){
        assertEq("DIAMOND_9",CouleurValeur.suitSt(Suit.DIAMOND,9));
    }
    @Test
    public void st56(){
        assertEq("DIAMOND_8",CouleurValeur.suitSt(Suit.DIAMOND,8));
    }
    @Test
    public void st57(){
        assertEq("DIAMOND_7",CouleurValeur.suitSt(Suit.DIAMOND,7));
    }
    @Test
    public void st58(){
        assertEq("DIAMOND_6",CouleurValeur.suitSt(Suit.DIAMOND,6));
    }
    @Test
    public void st59(){
        assertEq("DIAMOND_5",CouleurValeur.suitSt(Suit.DIAMOND,5));
    }
    @Test
    public void st60(){
        assertEq("DIAMOND_4",CouleurValeur.suitSt(Suit.DIAMOND,4));
    }
    @Test
    public void st61(){
        assertEq("DIAMOND_3",CouleurValeur.suitSt(Suit.DIAMOND,3));
    }
    @Test
    public void st62(){
        assertEq("DIAMOND_2",CouleurValeur.suitSt(Suit.DIAMOND,2));
    }
    @Test
    public void st63(){
        assertEq("DIAMOND_1",CouleurValeur.suitSt(Suit.DIAMOND,1));
    }
    @Test
    public void st64(){
        assertEq("CLUB_KING",CouleurValeur.suitSt(Suit.CLUB,CardChar.KING));
    }
    @Test
    public void st65(){
        assertEq("CLUB_QUEEN",CouleurValeur.suitSt(Suit.CLUB,CardChar.QUEEN));
    }
    @Test
    public void st66(){
        assertEq("CLUB_KNIGHT",CouleurValeur.suitSt(Suit.CLUB,CardChar.KNIGHT));
    }
    @Test
    public void st67(){
        assertEq("CLUB_JACK",CouleurValeur.suitSt(Suit.CLUB,CardChar.JACK));
    }
    @Test
    public void st68(){
        assertEq("CLUB_10",CouleurValeur.suitSt(Suit.CLUB,10));
    }
    @Test
    public void st69(){
        assertEq("CLUB_9",CouleurValeur.suitSt(Suit.CLUB,9));
    }
    @Test
    public void st70(){
        assertEq("CLUB_8",CouleurValeur.suitSt(Suit.CLUB,8));
    }
    @Test
    public void st71(){
        assertEq("CLUB_7",CouleurValeur.suitSt(Suit.CLUB,7));
    }
    @Test
    public void st72(){
        assertEq("CLUB_6",CouleurValeur.suitSt(Suit.CLUB,6));
    }
    @Test
    public void st73(){
        assertEq("CLUB_5",CouleurValeur.suitSt(Suit.CLUB,5));
    }
    @Test
    public void st74(){
        assertEq("CLUB_4",CouleurValeur.suitSt(Suit.CLUB,4));
    }
    @Test
    public void st75(){
        assertEq("CLUB_3",CouleurValeur.suitSt(Suit.CLUB,3));
    }
    @Test
    public void st76(){
        assertEq("CLUB_2",CouleurValeur.suitSt(Suit.CLUB,2));
    }
    @Test
    public void st77(){
        assertEq("CLUB_1",CouleurValeur.suitSt(Suit.CLUB,1));
    }

    @Test
    public void forceCouleurDansUnTri() {
        CouleurValeur couleurValeur_ = new CouleurValeur(Suit.TRUMP, (byte) 21, CardChar.UNDEFINED, true);
        assertEq(2, couleurValeur_.forceCouleurDansUnTri(Suit.toutesCouleurs()));
    }

    @Test
    public void vientAvant1() {
        assertTrue(CouleurValeur.vientAvant(1,0,2,0));
    }

    @Test
    public void vientAvant2() {
        assertTrue(!CouleurValeur.vientAvant(2,0,1,0));
    }

    @Test
    public void vientAvant3() {
        assertTrue(CouleurValeur.vientAvant(1,1,1,2));
    }

    @Test
    public void vientAvant4() {
        assertTrue(!CouleurValeur.vientAvant(1,2,1,1));
    }
}
