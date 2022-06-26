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
