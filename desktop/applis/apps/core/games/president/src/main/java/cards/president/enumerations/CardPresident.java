package cards.president.enumerations;
import cards.consts.CardChar;
import cards.consts.CouleurValeur;
import cards.consts.Suit;
import code.util.IdList;

public enum CardPresident {
    WHITE,
    HEART_2(2,Suit.HEART, 13),
    HEART_1(1,Suit.HEART, 12),
    HEART_KING(CardChar.KING,Suit.HEART,11),
    HEART_QUEEN(CardChar.QUEEN,Suit.HEART,10),
    HEART_JACK(CardChar.JACK,Suit.HEART,9),
    HEART_10(10,Suit.HEART, 8),
    HEART_9(9,Suit.HEART, 7),
    HEART_8(8,Suit.HEART, 6),
    HEART_7(7,Suit.HEART, 5),
    HEART_6(6,Suit.HEART, 4),
    HEART_5(5,Suit.HEART, 3),
    HEART_4(4,Suit.HEART, 2),
    HEART_3(3,Suit.HEART, 1),
    SPADE_2(2,Suit.SPADE, 13),
    SPADE_1(1,Suit.SPADE, 12),
    SPADE_KING(CardChar.KING,Suit.SPADE, 11),
    SPADE_QUEEN(CardChar.QUEEN,Suit.SPADE, 10),
    SPADE_JACK(CardChar.JACK,Suit.SPADE, 9),
    SPADE_10(10,Suit.SPADE, 8),
    SPADE_9(9,Suit.SPADE, 7),
    SPADE_8(8,Suit.SPADE, 6),
    SPADE_7(7,Suit.SPADE, 5),
    SPADE_6(6,Suit.SPADE, 4),
    SPADE_5(5,Suit.SPADE, 3),
    SPADE_4(4,Suit.SPADE, 2),
    SPADE_3(3,Suit.SPADE, 1),
    DIAMOND_2(2,Suit.DIAMOND, 13),
    DIAMOND_1(1,Suit.DIAMOND, 12),
    DIAMOND_KING(CardChar.KING,Suit.DIAMOND, 11),
    DIAMOND_QUEEN(CardChar.QUEEN,Suit.DIAMOND, 10),
    DIAMOND_JACK(CardChar.JACK,Suit.DIAMOND, 9),
    DIAMOND_10(10,Suit.DIAMOND, 8),
    DIAMOND_9(9,Suit.DIAMOND, 7),
    DIAMOND_8(8,Suit.DIAMOND, 6),
    DIAMOND_7(7,Suit.DIAMOND, 5),
    DIAMOND_6(6,Suit.DIAMOND, 4),
    DIAMOND_5(5,Suit.DIAMOND, 3),
    DIAMOND_4(4,Suit.DIAMOND, 2),
    DIAMOND_3(3,Suit.DIAMOND, 1),
    CLUB_2(2,Suit.CLUB, 13),
    CLUB_1(1,Suit.CLUB, 12),
    CLUB_KING(CardChar.KING,Suit.CLUB, 11),
    CLUB_QUEEN(CardChar.QUEEN,Suit.CLUB, 10),
    CLUB_JACK(CardChar.JACK,Suit.CLUB, 9),
    CLUB_10(10,Suit.CLUB, 8),
    CLUB_9(9,Suit.CLUB, 7),
    CLUB_8(8,Suit.CLUB, 6),
    CLUB_7(7,Suit.CLUB, 5),
    CLUB_6(6,Suit.CLUB, 4),
    CLUB_5(5,Suit.CLUB, 3),
    CLUB_4(4,Suit.CLUB, 2),
    CLUB_3(3,Suit.CLUB, 1);

    private final byte force;
    private final CouleurValeur id;

    CardPresident() {
        force = 0;
        id = new CouleurValeur(Suit.UNDEFINED,(byte)0,CardChar.UNDEFINED,false);
    }

    CardPresident(int _value, Suit _suit, int _strength) {
        force = (byte) _strength;
        id = new CouleurValeur(_suit,(byte)_value,CardChar.UNDEFINED,true);
    }

    CardPresident(CardChar _char, Suit _suit, int _strength) {
        force = (byte) _strength;
        id = new CouleurValeur(_suit,(byte)0,_char,true);
    }

    public static byte getMaxStrength(boolean _reverse) {
        byte max_ = 0;
        for (CardPresident c: values()) {
            if (!c.id.isJouable()) {
                continue;
            }
            byte s_ = c.strength(_reverse);
            if (s_ > max_) {
                max_ = s_;
            }
        }
        return max_;
    }

    public boolean vientAvant(CardPresident _c,boolean _decroissant,IdList<Suit> _couleurs) {
        return CouleurValeur.vientAvant(getId().forceCouleurDansUnTri(_couleurs),forceValeurDansUnTri(_decroissant),_c.getId().forceCouleurDansUnTri(_couleurs),_c.forceValeurDansUnTri(_decroissant));
    }

    public byte forceValeurDansUnTri(boolean _decroissant) {
        if(_decroissant) {
            return (byte) (14 - strength(false));
        }
        return strength(false);
    }

    /**Appele lors de la fin d'un pli pour determiner le ramasseur
    mais aussi pour dire qui est en train de prendre la main du pli qui est en cours d'etre joue
    si ce pli n'est pas reduit a l'Excuse
    @param _couleurDemande couleur demandee du pli*/
    public byte strength(boolean _reverse) {
        if(!_reverse) {
            return force;
        }
        return (byte) (14 - force);
    }

    public CouleurValeur getId() {
        return id;
    }

}
