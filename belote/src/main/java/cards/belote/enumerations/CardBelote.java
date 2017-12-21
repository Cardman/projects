package cards.belote.enumerations;
import code.format.Format;
import code.util.EnumList;
import code.util.StringList;
import code.util.consts.Constants;
import cards.belote.BidBeloteSuit;
import cards.consts.CardChar;
import cards.consts.Order;
import cards.consts.Suit;
/**
    */
public enum CardBelote {
    WHITE,
    HEART_JACK(CardChar.JACK,Suit.HEART,8,4,5,20,2,2,14),
    HEART_9(9,Suit.HEART,7,3,3,14,0,0,9),
    HEART_1(1,Suit.HEART,6,8,8,11,11,19,6),
    HEART_10(10,Suit.HEART,5,7,4,10,10,10,4),
    HEART_KING(CardChar.KING,Suit.HEART,4,6,7,4,4,4,3),
    HEART_QUEEN(CardChar.QUEEN,Suit.HEART,3,5,6,3,3,3,2),
    HEART_8(8,Suit.HEART,2,2,2,0,0,0,0),
    HEART_7(7,Suit.HEART,1,1,1,0,0,0,0),
    SPADE_JACK(CardChar.JACK,Suit.SPADE,8,4,5,20,2,2,14),
    SPADE_9(9,Suit.SPADE,7,3,3,14,0,0,9),
    SPADE_1(1,Suit.SPADE,6,8,8,11,11,19,6),
    SPADE_10(10,Suit.SPADE,5,7,4,10,10,10,4),
    SPADE_KING(CardChar.KING,Suit.SPADE,4,6,7,4,4,4,3),
    SPADE_QUEEN(CardChar.QUEEN,Suit.SPADE,3,5,6,3,3,3,2),
    SPADE_8(8,Suit.SPADE,2,2,2,0,0,0,0),
    SPADE_7(7,Suit.SPADE,1,1,1,0,0,0,0),
    DIAMOND_JACK(CardChar.JACK,Suit.DIAMOND,8,4,5,20,2,2,14),
    DIAMOND_9(9,Suit.DIAMOND,7,3,3,14,0,0,9),
    DIAMOND_1(1,Suit.DIAMOND,6,8,8,11,11,19,6),
    DIAMOND_10(10,Suit.DIAMOND,5,7,4,10,10,10,4),
    DIAMOND_KING(CardChar.KING,Suit.DIAMOND,4,6,7,4,4,4,3),
    DIAMOND_QUEEN(CardChar.QUEEN,Suit.DIAMOND,3,5,6,3,3,3,2),
    DIAMOND_8(8,Suit.DIAMOND,2,2,2,0,0,0,0),
    DIAMOND_7(7,Suit.DIAMOND,1,1,1,0,0,0,0),
    CLUB_JACK(CardChar.JACK,Suit.CLUB,8,4,5,20,2,2,14),
    CLUB_9(9,Suit.CLUB,7,3,3,14,0,0,9),
    CLUB_1(1,Suit.CLUB,6,8,8,11,11,19,6),
    CLUB_10(10,Suit.CLUB,5,7,4,10,10,10,4),
    CLUB_KING(CardChar.KING,Suit.CLUB,4,6,7,4,4,4,3),
    CLUB_QUEEN(CardChar.QUEEN,Suit.CLUB,3,5,6,3,3,3,2),
    CLUB_8(8,Suit.CLUB,2,2,2,0,0,0,0),
    CLUB_7(7,Suit.CLUB,1,1,1,0,0,0,0);

    private final Suit couleur;
    /**Numero de valeur de la carte (Numeros pour les atouts du tarot, et pour les cartes chiffrees, position pour les figures avec Roi, Dame, Cavalier, Valet)*/
    private final byte valeur;
    private final CardChar nomFigure;
    private final byte forceAtout;
    private final byte forceCouleur;
    private final byte forceAnnonce;
    private final byte pointsAtout;
    private final byte pointsCouleur;
    private final byte pointsSansAt;
    private final byte pointsToutAt;
    private final boolean jouable;

    CardBelote() {
        jouable = false;
        couleur = Suit.UNDEFINED;
        nomFigure = CardChar.UNDEFINED;
        valeur = 0;
        forceAtout = 0;
        forceCouleur = 0;
        forceAnnonce = 0;
        pointsAtout = 0;
        pointsCouleur = 0;
        pointsSansAt = 0;
        pointsToutAt = 0;
    }
    CardBelote(CardChar _figure, Suit _pcouleur,
            int _pordreAtout, int _pordreCouleur,
            int _pordreAnnonce,
            int _ppointsAtout, int _ppointsCouleur,
            int _ppointsSansAt, int _ppointsToutAt) {
        jouable = true;
        nomFigure = _figure;
        couleur=_pcouleur;
        forceAtout = (byte) _pordreAtout;
        forceCouleur = (byte) _pordreCouleur;
        forceAnnonce = (byte) _pordreAnnonce;
        pointsAtout = (byte) _ppointsAtout;
        pointsCouleur = (byte) _ppointsCouleur;
        pointsSansAt = (byte) _ppointsSansAt;
        pointsToutAt = (byte) _ppointsToutAt;
        valeur = 0;
    }
    CardBelote(int _pvaleur, Suit _pcouleur,
            int _pordreAtout, int _pordreCouleur,
            int _pordreAnnonce,
            int _ppointsAtout, int _ppointsCouleur,
            int _ppointsSansAt, int _ppointsToutAt) {
        jouable = true;
        valeur=(byte) _pvaleur;
        couleur=_pcouleur;
        forceAtout = (byte) _pordreAtout;
        forceCouleur = (byte) _pordreCouleur;
        forceAnnonce = (byte) _pordreAnnonce;
        pointsAtout = (byte) _ppointsAtout;
        pointsCouleur = (byte) _ppointsCouleur;
        pointsSansAt = (byte) _ppointsSansAt;
        pointsToutAt = (byte) _ppointsToutAt;
        nomFigure = CardChar.UNDEFINED;
    }
    public static boolean eq(CardBelote _one, CardBelote _two) {
        return _one == _two;
    }
    public static boolean equalsCards(EnumList<CardBelote> _one, EnumList<CardBelote> _two) {
        if (_one.size() != _two.size()) {
            return false;
        }
        for (CardBelote e: _one) {
            if (_two.indexesOfObj(e).size() != _one.indexesOfObj(e).size()) {
                return false;
            }
        }
        return true;
    }
    public byte strength(Suit _dem,BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return strength(_contrat.getCouleur(),_dem);
        }
        if(_contrat.ordreCouleur()) {
            if(couleur == _dem) {
                return forceCouleur;
            }
            //return 0;
        }
        if(_contrat.ordreAtout()) {
            if(couleur == _dem) {
                return forceAtout;
            }
            //return 0;
        }
        return 0;
    }
    public byte strength(Suit _atout,Suit _dem) {
        if(_dem==_atout) {
            if(couleur()==_atout) {
                return forceAtout;
            }
        } else {
            if(couleur()==_atout) {
                byte maxForceDemandee_ = 0;
                for(CardBelote c: CardBelote.values()) {
                    if(c.couleur() != _dem) {
                        continue;
                    }
                    if(c.forceCouleur <= maxForceDemandee_) {
                        continue;
                    }
                    maxForceDemandee_ = c.forceCouleur;
                }
                return (byte) (forceAtout + maxForceDemandee_);
            }
            if(couleur()==_dem) {
                return forceCouleur;
            }
        }
        //Si on_ n'a ni_ de_ l'atout_ ni_ la_ couleur demandee_
        return (byte)0;
    }
    public byte forceAnnonce() {
        return forceAnnonce;
    }

    private byte points(Suit _atout) {
        if(_atout==couleur()) {
            return pointsAtout;
        }
        return pointsCouleur;
    }
    public byte points(BidBeloteSuit _contrat) {
        if(_contrat.ordreAtout()) {
            return pointsToutAt;
        }
        if(_contrat.ordreCouleur()) {
            return pointsSansAt;
        }
        return points(_contrat.getCouleur());
    }
    public byte forceValeurDansUnTri(boolean _decroissant,Order _ordre) {
        if(_decroissant) {
            if(_ordre == Order.TRUMP) {
                return (byte)(9-strength(couleur(),couleur()));
            }
            return (byte)(9-strength(Suit.UNDEFINED,couleur()));
        }
        if(_ordre == Order.TRUMP) {
            return strength(couleur(),couleur());
        }
        return strength(Suit.UNDEFINED,couleur());
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
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_CARD, name());
    }

    public String getImageFileName(String _ext) {
        return StringList.toUpperCase(name())+_ext;
    }

    public boolean vientAvant(CardBelote _c,boolean _decroissant,Order _ordre,EnumList<Suit> _couleurs) {
        byte forceCouleur1_=forceCouleurDansUnTri(_couleurs);
        byte forceCouleur2_=_c.forceCouleurDansUnTri(_couleurs);
        byte forceValeur_=forceValeurDansUnTri(_decroissant,_ordre);
        byte forceValeur2_=_c.forceValeurDansUnTri(_decroissant,_ordre);
        if(forceCouleur1_<forceCouleur2_) {
            return true;
        }
        if(forceCouleur1_==forceCouleur2_) {
            return forceValeur_<forceValeur2_;
        }
        return false;
    }

    public boolean isCharacter() {
        return nomFigure != CardChar.UNDEFINED;
    }

    public boolean isPlayable() {
        return jouable;
    }
}
