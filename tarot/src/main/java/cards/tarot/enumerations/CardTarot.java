package cards.tarot.enumerations;
import code.format.Format;
import code.util.EnumList;
import code.util.StringList;
import code.util.consts.Constants;
import cards.consts.CardChar;
import cards.consts.Suit;

/**
    */
public enum CardTarot {
    WHITE,
    EXCUSE(CardChar.EXCUSE, 9),
    /**Oudler card*/
    TRUMP_21(21, 9),
    TRUMP_20(20, 1),
    TRUMP_19(19, 1),
    TRUMP_18(18, 1),
    TRUMP_17(17, 1),
    TRUMP_16(16, 1),
    TRUMP_15(15, 1),
    TRUMP_14(14, 1),
    TRUMP_13(13, 1),
    TRUMP_12(12, 1),
    TRUMP_11(11, 1),
    TRUMP_10(10, 1),
    TRUMP_9(9, 1),
    TRUMP_8(8, 1),
    TRUMP_7(7, 1),
    TRUMP_6(6, 1),
    TRUMP_5(5, 1),
    TRUMP_4(4, 1),
    TRUMP_3(3, 1),
    TRUMP_2(2, 1),
    TRUMP_1(1, 9),
    HEART_KING(CardChar.KING,Suit.HEART,14, 9),
    HEART_QUEEN(CardChar.QUEEN,Suit.HEART,13, 7),
    HEART_KNIGHT(CardChar.KNIGHT,Suit.HEART,12, 5),
    HEART_JACK(CardChar.JACK,Suit.HEART,11, 3),
    HEART_10(10,Suit.HEART, 1),
    HEART_9(9,Suit.HEART, 1),
    HEART_8(8,Suit.HEART, 1),
    HEART_7(7,Suit.HEART, 1),
    HEART_6(6,Suit.HEART, 1),
    HEART_5(5,Suit.HEART, 1),
    HEART_4(4,Suit.HEART, 1),
    HEART_3(3,Suit.HEART, 1),
    HEART_2(2,Suit.HEART, 1),
    HEART_1(1,Suit.HEART, 1),
    SPADE_KING(CardChar.KING,Suit.SPADE,14, 9),
    SPADE_QUEEN(CardChar.QUEEN,Suit.SPADE,13, 7),
    SPADE_KNIGHT(CardChar.KNIGHT,Suit.SPADE,12, 5),
    SPADE_JACK(CardChar.JACK,Suit.SPADE,11, 3),
    SPADE_10(10,Suit.SPADE, 1),
    SPADE_9(9,Suit.SPADE, 1),
    SPADE_8(8,Suit.SPADE, 1),
    SPADE_7(7,Suit.SPADE, 1),
    SPADE_6(6,Suit.SPADE, 1),
    SPADE_5(5,Suit.SPADE, 1),
    SPADE_4(4,Suit.SPADE, 1),
    SPADE_3(3,Suit.SPADE, 1),
    SPADE_2(2,Suit.SPADE, 1),
    SPADE_1(1,Suit.SPADE, 1),
    DIAMOND_KING(CardChar.KING,Suit.DIAMOND,14, 9),
    DIAMOND_QUEEN(CardChar.QUEEN,Suit.DIAMOND,13, 7),
    DIAMOND_KNIGHT(CardChar.KNIGHT,Suit.DIAMOND,12, 5),
    DIAMOND_JACK(CardChar.JACK,Suit.DIAMOND,11, 3),
    DIAMOND_10(10,Suit.DIAMOND, 1),
    DIAMOND_9(9,Suit.DIAMOND, 1),
    DIAMOND_8(8,Suit.DIAMOND, 1),
    DIAMOND_7(7,Suit.DIAMOND, 1),
    DIAMOND_6(6,Suit.DIAMOND, 1),
    DIAMOND_5(5,Suit.DIAMOND, 1),
    DIAMOND_4(4,Suit.DIAMOND, 1),
    DIAMOND_3(3,Suit.DIAMOND, 1),
    DIAMOND_2(2,Suit.DIAMOND, 1),
    DIAMOND_1(1,Suit.DIAMOND, 1),
    CLUB_KING(CardChar.KING,Suit.CLUB,14, 9),
    CLUB_QUEEN(CardChar.QUEEN,Suit.CLUB,13, 7),
    CLUB_KNIGHT(CardChar.KNIGHT,Suit.CLUB,12, 5),
    CLUB_JACK(CardChar.JACK,Suit.CLUB,11, 3),
    CLUB_10(10,Suit.CLUB, 1),
    CLUB_9(9,Suit.CLUB, 1),
    CLUB_8(8,Suit.CLUB, 1),
    CLUB_7(7,Suit.CLUB, 1),
    CLUB_6(6,Suit.CLUB, 1),
    CLUB_5(5,Suit.CLUB, 1),
    CLUB_4(4,Suit.CLUB, 1),
    CLUB_3(3,Suit.CLUB, 1),
    CLUB_2(2,Suit.CLUB, 1),
    CLUB_1(1,Suit.CLUB, 1);

    /**Numero de couleur de la carte (0: Excuse Tarot,1: Atout Tarot,2: Coeur,3: Pique,4: Carreau,5: Tr&egrave;fle)*/
    private final Suit couleur;
    /**Numero de valeur de la carte (Numeros pour les atouts du tarot, et pour les cartes chiffrees, position pour les figures avec Roi, Dame, Cavalier, Valet)*/
    private final byte valeur;
    private final CardChar nomFigure;
    private final byte force;
    private final boolean jouable;
    private final byte points;

    CardTarot() {
        jouable = false;
        couleur = Suit.UNDEFINED;
        nomFigure = CardChar.UNDEFINED;
        valeur = 0;
        force = 0;
        points = 0;
    }
    CardTarot(CardChar _figure, int _points) {
        jouable = true;
        nomFigure = _figure;
        valeur = 0;
        force = 0;
        couleur = Suit.UNDEFINED;
        points = (byte) _points;
    }
    CardTarot(int _pvaleur, int _points) {
        jouable = true;
        couleur = Suit.TRUMP;
        nomFigure = CardChar.UNDEFINED;
        valeur = (byte) _pvaleur;
        force = (byte) _pvaleur;
        points = (byte) _points;
    }
    CardTarot(CardChar _figure, Suit _pcouleur, int _pordre, int _points) {
        jouable = true;
        nomFigure = _figure;
        valeur = 0;
        couleur=_pcouleur;
        force = (byte) _pordre;
        points = (byte) _points;
    }
    CardTarot(int _pvaleur, Suit _pcouleur, int _points) {
        jouable = true;
        valeur=(byte) _pvaleur;
        nomFigure = CardChar.UNDEFINED;
        couleur=_pcouleur;
        force = (byte) _pvaleur;
        points = (byte) _points;
    }
    public static boolean eq(CardTarot _one, CardTarot _two) {
        return _one == _two;
    }
    public static boolean equalsCards(EnumList<CardTarot> _one, EnumList<CardTarot> _two) {
        if (_one.size() != _two.size()) {
            return false;
        }
        for (CardTarot e: _one) {
            if (_two.indexesOfObj(e).size() != _one.indexesOfObj(e).size()) {
                return false;
            }
        }
        return true;
    }
    public static CardTarot excuse() {
        return EXCUSE;
    }
    public static CardTarot petit() {
        return TRUMP_1;
    }
    public static CardTarot vingtEtUn() {
        return TRUMP_21;
    }

    public byte forceTri() {
        return force;
    }
    /**Appele lors de la fin d'un pli pour determiner le ramasseur
    mais aussi pour dire qui est en train de prendre la main du pli qui est en cours d'etre joue
    si ce pli n'est pas reduit a l'Excuse
    @param _couleurDemande couleur demandee du pli*/
    public byte strength(Suit _couleurDemande) {
        if(eq(this, excuse())) {
            return 0;
        }
        if(couleur == Suit.TRUMP) {
            //L'atout_ est_ plus_ fort_ que_ n'importe_ quelle_ couleur
            byte maxForceDemandee_ = 0;
            for(CardTarot c: CardTarot.values()) {
                if(c.couleur != _couleurDemande) {
                    continue;
                }
                if(c.force <= maxForceDemandee_) {
                    continue;
                }
                maxForceDemandee_ = c.force;
            }
            return (byte)(force+maxForceDemandee_);
        }
        /*Maintenant on_ traite_ le_ cas_ d'une_ Excuse et_ des_ cartes_ de_ couleur*/
        if(_couleurDemande==couleur) {
            //Une carte_ de_ la_ meme_ couleur que_ celle_ de_ la_ carte_ entamee_ et_ autre_ que_ l'Excuse
            return force;
        }
        //L'Excuse n'est_ pas_ entamee_ dans_ un_ pli_
        //Les cartes_ qui_ ne_ sont_ pas_ de_ l'atout_ ne_ permettent_ pas_ de_ faire_ un_ pli_ ou_ l'atout_ est_ demande_
        //Les cartes_ de_ couleur qui_ n'ont_ pas_ la_ meme_ couleur que_ celle_ entamee_
        //ne_ permettent_ pas_ de_ faire_ un_ pli_
        return 0;
    }
    /**Retourne le nombre de points d'une carte (multiplie par 2)
    au tarot, on evite d utiliser les flottants car la valeur de chaque carte au tarot est un nombre non entier de points*/
    public byte points() {
        return points;
    }
    public boolean isCharacter() {
        return nomFigure != CardChar.UNDEFINED && nomFigure != CardChar.EXCUSE;
    }
    public boolean isPlayable() {
        return jouable;
    }
    public boolean estUnBout() {
        return eq(this, excuse())||eq(this,petit())||eq(this,vingtEtUn());
    }
    public byte forceValeurDansUnTri(boolean _decroissant) {
        if(_decroissant) {
            if(couleur==Suit.TRUMP) {
                return (byte)(23-forceTri());
            }
            return (byte)(15-forceTri());
        }
        return forceTri();
    }
    public CardChar getNomFigure() {
        return nomFigure;
    }
    public String getSymbol(String _loc) {
        if (nomFigure != CardChar.UNDEFINED) {
            return nomFigure.getSymbol(_loc);
        }
        return String.valueOf(force);
    }
    public Suit couleur() {
        return couleur;
    }
    public byte valeur() {
        return valeur;
    }

    private byte forceCouleurDansUnTri(EnumList<Suit> _couleurs) {
        return (byte) (_couleurs.indexOfObj(couleur)+1);
    }
    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_CARD,name());
    }

    public String getImageFileName(String _ext) {
        return StringList.toUpperCase(name())+_ext;
    }
    public boolean vientAvant(CardTarot _c,boolean _decroissant,EnumList<Suit> _couleurs) {
        byte forceCouleur_=forceCouleurDansUnTri(_couleurs);
        byte forceCouleur2_=_c.forceCouleurDansUnTri(_couleurs);
        byte forceValeur_=forceValeurDansUnTri(_decroissant);
        byte forceValeur2_=_c.forceValeurDansUnTri(_decroissant);
        if(forceCouleur_<forceCouleur2_) {
            return true;
        }
        if(forceCouleur_==forceCouleur2_) {
            return forceValeur_<forceValeur2_;
        }
        return false;
    }
}
