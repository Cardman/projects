package cards.belote.enumerations;
import cards.belote.BidBeloteSuit;
import cards.consts.CardChar;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.EnumList;
import code.util.StringList;
/**
    */
public enum CardBelote {
    WHITE,
    HEART_JACK(CardChar.JACK,Suit.HEART,OrderPoints.TRUMP_JACK,OrderPoints.SUIT_JACK,5),
    HEART_9(9,Suit.HEART,OrderPoints.TRUMP_9,OrderPoints.SUIT_9,3),
    HEART_1(1,Suit.HEART,OrderPoints.TRUMP_1,OrderPoints.SUIT_1,8),
    HEART_10(10,Suit.HEART,OrderPoints.TRUMP_10,OrderPoints.SUIT_10,4),
    HEART_KING(CardChar.KING,Suit.HEART,OrderPoints.TRUMP_KING,OrderPoints.SUIT_KING,7),
    HEART_QUEEN(CardChar.QUEEN,Suit.HEART,OrderPoints.TRUMP_QUEEN,OrderPoints.SUIT_QUEEN,6),
    HEART_8(8,Suit.HEART,OrderPoints.TRUMP_8,OrderPoints.SUIT_8,2),
    HEART_7(7,Suit.HEART,OrderPoints.TRUMP_7,OrderPoints.SUIT_7,1),
    SPADE_JACK(CardChar.JACK,Suit.SPADE,OrderPoints.TRUMP_JACK,OrderPoints.SUIT_JACK,5),
    SPADE_9(9,Suit.SPADE,OrderPoints.TRUMP_9,OrderPoints.SUIT_9,3),
    SPADE_1(1,Suit.SPADE,OrderPoints.TRUMP_1,OrderPoints.SUIT_1,8),
    SPADE_10(10,Suit.SPADE,OrderPoints.TRUMP_10,OrderPoints.SUIT_10,4),
    SPADE_KING(CardChar.KING,Suit.SPADE,OrderPoints.TRUMP_KING,OrderPoints.SUIT_KING,7),
    SPADE_QUEEN(CardChar.QUEEN,Suit.SPADE,OrderPoints.TRUMP_QUEEN,OrderPoints.SUIT_QUEEN,6),
    SPADE_8(8,Suit.SPADE,OrderPoints.TRUMP_8,OrderPoints.SUIT_8,2),
    SPADE_7(7,Suit.SPADE,OrderPoints.TRUMP_7,OrderPoints.SUIT_7,1),
    DIAMOND_JACK(CardChar.JACK,Suit.DIAMOND,OrderPoints.TRUMP_JACK,OrderPoints.SUIT_JACK,5),
    DIAMOND_9(9,Suit.DIAMOND,OrderPoints.TRUMP_9,OrderPoints.SUIT_9,3),
    DIAMOND_1(1,Suit.DIAMOND,OrderPoints.TRUMP_1,OrderPoints.SUIT_1,8),
    DIAMOND_10(10,Suit.DIAMOND,OrderPoints.TRUMP_10,OrderPoints.SUIT_10,4),
    DIAMOND_KING(CardChar.KING,Suit.DIAMOND,OrderPoints.TRUMP_KING,OrderPoints.SUIT_KING,7),
    DIAMOND_QUEEN(CardChar.QUEEN,Suit.DIAMOND,OrderPoints.TRUMP_QUEEN,OrderPoints.SUIT_QUEEN,6),
    DIAMOND_8(8,Suit.DIAMOND,OrderPoints.TRUMP_8,OrderPoints.SUIT_8,2),
    DIAMOND_7(7,Suit.DIAMOND,OrderPoints.TRUMP_7,OrderPoints.SUIT_7,1),
    CLUB_JACK(CardChar.JACK,Suit.CLUB,OrderPoints.TRUMP_JACK,OrderPoints.SUIT_JACK,5),
    CLUB_9(9,Suit.CLUB,OrderPoints.TRUMP_9,OrderPoints.SUIT_9,3),
    CLUB_1(1,Suit.CLUB,OrderPoints.TRUMP_1,OrderPoints.SUIT_1,8),
    CLUB_10(10,Suit.CLUB,OrderPoints.TRUMP_10,OrderPoints.SUIT_10,4),
    CLUB_KING(CardChar.KING,Suit.CLUB,OrderPoints.TRUMP_KING,OrderPoints.SUIT_KING,7),
    CLUB_QUEEN(CardChar.QUEEN,Suit.CLUB,OrderPoints.TRUMP_QUEEN,OrderPoints.SUIT_QUEEN,6),
    CLUB_8(8,Suit.CLUB,OrderPoints.TRUMP_8,OrderPoints.SUIT_8,2),
    CLUB_7(7,Suit.CLUB,OrderPoints.TRUMP_7,OrderPoints.SUIT_7,1);

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
               OrderPoints _pordreAtout, OrderPoints _pordreCouleur,
               int _pordreAnnonce) {
        jouable = true;
        nomFigure = _figure;
        couleur=_pcouleur;
        forceAtout = (byte) _pordreAtout.getOrder();
        forceCouleur = (byte) _pordreCouleur.getOrder();
        forceAnnonce = (byte) _pordreAnnonce;
        pointsAtout = (byte) _pordreAtout.getPointsDomSuit();
        pointsCouleur = (byte) _pordreCouleur.getPointsDomSuit();
        pointsSansAt = (byte) _pordreCouleur.getPointsNoDomSuit();
        pointsToutAt = (byte) _pordreAtout.getPointsNoDomSuit();
        valeur = 0;
    }
    CardBelote(int _pvaleur, Suit _pcouleur,
               OrderPoints _pordreAtout, OrderPoints _pordreCouleur,
               int _pordreAnnonce) {
        jouable = true;
        valeur=(byte) _pvaleur;
        couleur=_pcouleur;
        forceAtout = (byte) _pordreAtout.getOrder();
        forceCouleur = (byte) _pordreCouleur.getOrder();
        forceAnnonce = (byte) _pordreAnnonce;
        pointsAtout = (byte) _pordreAtout.getPointsDomSuit();
        pointsCouleur = (byte) _pordreCouleur.getPointsDomSuit();
        pointsSansAt = (byte)  _pordreCouleur.getPointsNoDomSuit();
        pointsToutAt = (byte) _pordreAtout.getPointsNoDomSuit();
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
        }
        if(_contrat.ordreAtout()) {
            if(couleur == _dem) {
                return forceAtout;
            }
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

    public Suit couleur() {
        return couleur;
    }
    public byte valeur() {
        return valeur;
    }
    private byte forceCouleurDansUnTri(EnumList<Suit> _couleurs) {
        return (byte) (_couleurs.indexOfObj(couleur)+1);
    }

    public String getImageFileName(String _ext) {
        return StringList.concat(name(),_ext);
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
