package cards.solitaire;

import cards.consts.CardChar;
import cards.consts.CouleurValeur;
import cards.consts.Suit;

public enum CardSolitaire {
    WHITE,
    HEART_KING(CardChar.KING,Suit.HEART, CouleurValeur.RED,13),
    HEART_QUEEN(CardChar.QUEEN,Suit.HEART, CouleurValeur.RED,12),
    HEART_JACK(CardChar.JACK,Suit.HEART, CouleurValeur.RED,11),
    HEART_10(10,Suit.HEART, CouleurValeur.RED),
    HEART_9(9,Suit.HEART, CouleurValeur.RED),
    HEART_8(8,Suit.HEART, CouleurValeur.RED),
    HEART_7(7,Suit.HEART, CouleurValeur.RED),
    HEART_6(6,Suit.HEART, CouleurValeur.RED),
    HEART_5(5,Suit.HEART, CouleurValeur.RED),
    HEART_4(4,Suit.HEART, CouleurValeur.RED),
    HEART_3(3,Suit.HEART, CouleurValeur.RED),
    HEART_2(2, Suit.HEART, CouleurValeur.RED),
    HEART_1(1,Suit.HEART, CouleurValeur.RED),
    SPADE_KING(CardChar.KING,Suit.SPADE, CouleurValeur.BLACK, 13),
    SPADE_QUEEN(CardChar.QUEEN,Suit.SPADE, CouleurValeur.BLACK, 12),
    SPADE_JACK(CardChar.JACK,Suit.SPADE, CouleurValeur.BLACK, 11),
    SPADE_10(10,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_9(9,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_8(8,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_7(7,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_6(6,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_5(5,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_4(4,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_3(3,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_2(2,Suit.SPADE, CouleurValeur.BLACK),
    SPADE_1(1,Suit.SPADE, CouleurValeur.BLACK),
    DIAMOND_KING(CardChar.KING,Suit.DIAMOND, CouleurValeur.RED, 13),
    DIAMOND_QUEEN(CardChar.QUEEN,Suit.DIAMOND, CouleurValeur.RED, 12),
    DIAMOND_JACK(CardChar.JACK,Suit.DIAMOND, CouleurValeur.RED, 11),
    DIAMOND_10(10,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_9(9,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_8(8,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_7(7,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_6(6,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_5(5,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_4(4,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_3(3,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_2(2,Suit.DIAMOND, CouleurValeur.RED),
    DIAMOND_1(1,Suit.DIAMOND, CouleurValeur.RED),
    CLUB_KING(CardChar.KING,Suit.CLUB, CouleurValeur.BLACK, 13),
    CLUB_QUEEN(CardChar.QUEEN,Suit.CLUB, CouleurValeur.BLACK, 12),
    CLUB_JACK(CardChar.JACK,Suit.CLUB, CouleurValeur.BLACK, 11),
    CLUB_10(10,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_9(9,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_8(8,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_7(7,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_6(6,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_5(5,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_4(4,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_3(3,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_2(2,Suit.CLUB, CouleurValeur.BLACK),
    CLUB_1(1,Suit.CLUB, CouleurValeur.BLACK);

    private final int force;
    private final CouleurValeur id;
    private final int group;

    CardSolitaire() {
        force = 0;
        id = new CouleurValeur(Suit.UNDEFINED,0,CardChar.UNDEFINED,false);
        group = -1;
    }

    CardSolitaire(int _value, Suit _suit, int _g) {
        force = _value;
        id = new CouleurValeur(_suit,_value,CardChar.UNDEFINED,true);
        group = _g;
    }

    CardSolitaire(CardChar _char, Suit _suit, int _g, int _strength) {
        force = _strength;
        id = new CouleurValeur(_suit,0,_char,true);
        group = _g;
    }

    public int getForce() {
        return force;
    }

    public CouleurValeur getId() {
        return id;
    }

    public int getGroup() {
        return group;
    }
}
