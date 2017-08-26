package cards.president.enumerations;
import code.format.Format;
import code.util.EnumList;
import code.util.consts.Constants;
import cards.consts.CardChar;
import cards.consts.Suit;

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

    /**Numero de couleur de la carte (0: Excuse Tarot,1: Atout Tarot,2: Coeur,3: Pique,4: Carreau,5: Tr&egrave;fle)*/
    private final Suit suit;
    /**Numero de valeur de la carte (Numeros pour les atouts du tarot, et pour les cartes chiffrees, position pour les figures avec Roi, Dame, Cavalier, Valet)*/
    private final byte valeur;
    private final CardChar nomFigure;
    private final byte force;
    private final boolean jouable;

    CardPresident() {
        suit = Suit.UNDEFINED;
        valeur = 0;
        nomFigure = CardChar.UNDEFINED;
        jouable = false;
        force = 0;
    }

    CardPresident(int _value, Suit _suit, int _strength) {
        suit = _suit;
        valeur = (byte) _value;
        nomFigure = CardChar.UNDEFINED;
        jouable = true;
        force = (byte) _strength;
    }

    CardPresident(CardChar _char, Suit _suit, int _strength) {
        suit = _suit;
        valeur = 0;
        nomFigure = _char;
        jouable = true;
        force = (byte) _strength;
    }
    public static boolean eq(CardPresident _one, CardPresident _two) {
        return _one == _two;
    }
    public static boolean equalsCards(EnumList<CardPresident> _one, EnumList<CardPresident> _two) {
        if (_one.size() != _two.size()) {
            return false;
        }
        for (CardPresident e: _one) {
            if (_two.indexesOfObj(e).size() != _one.indexesOfObj(e).size()) {
                return false;
            }
        }
        return true;
    }

    public static byte getMaxStrength(boolean _reverse) {
        byte max_ = 0;
        for (CardPresident c: values()) {
            if (!c.jouable) {
                continue;
            }
            byte s_ = c.strength(_reverse);
            if (s_ > max_) {
                max_ = s_;
            }
        }
        return max_;
    }

    public static byte getAvgStrength(boolean _reverse) {
        long avg_ = 0;
        int nb_ = 0;
        for (CardPresident c: values()) {
            if (!c.jouable) {
                continue;
            }
            byte s_ = c.strength(_reverse);
            avg_ += s_;
            nb_++;
        }
        return (byte) (avg_/nb_);
    }

    public boolean vientAvant(CardPresident _c,boolean _decroissant,EnumList<Suit> _couleurs) {
        byte forceCouleur1_=forceCouleurDansUnTri(_couleurs);
        byte forceCouleur2_=_c.forceCouleurDansUnTri(_couleurs);
        byte forceValeur_=forceValeurDansUnTri(_decroissant);
        byte forceValeur2_=_c.forceValeurDansUnTri(_decroissant);
        if(forceValeur_<forceValeur2_) {
            return true;
        }
        if(forceValeur_==forceValeur2_) {
            return forceCouleur1_<forceCouleur2_;
        }
        return false;
    }

    private byte forceCouleurDansUnTri(EnumList<Suit> _couleurs) {
        return (byte) (_couleurs.indexOfObj(suit)+1);
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
    public boolean isPlayable() {
        return jouable;
    }
    public CardChar getNomFigure() {
        return nomFigure;
    }
    public String getSymbol(String _loc) {
        if (nomFigure != CardChar.UNDEFINED) {
            return nomFigure.getSymbol(_loc);
        }
        return String.valueOf(valeur);
    }
    public Suit couleur() {
        return suit;
    }
    public byte valeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.PRESIDENT_CARD,name());
    }

    public String getImageFileName(String _ext) {
        return name().toUpperCase()+_ext;
    }
}
