package cards.belote;
import cards.belote.enumerations.BidBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.EqList;
import code.util.annot.RwXml;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

@RwXml
public final class BidBeloteSuit implements Equallable<BidBeloteSuit>, Displayable {

    private static final String SPACE = " ";

    private BidBelote bid = BidBelote.FOLD;

    private Suit suit = Suit.UNDEFINED;

    private int points;

    public static boolean equalsSet(EqList<BidBeloteSuit> _list1,EqList<BidBeloteSuit> _list2) {
        for (BidBeloteSuit a: _list2) {
            boolean contains_ = false;
            for (BidBeloteSuit b: _list1) {
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (BidBeloteSuit a: _list1) {
            boolean contains_ = false;
            for (BidBeloteSuit b: _list2) {
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    public BidBelote getEnchere() {
        return bid;
    }

    public void setEnchere(BidBelote _enchere) {
        bid = _enchere;
    }

    public Suit getCouleur() {
        return suit;
    }

    public void setCouleur(Suit _couleur) {
        suit = _couleur;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int _points) {
        points = _points;
    }

    public boolean strongerThan(BidBeloteSuit _contrat) {
        return bid.getForce() > _contrat.bid.getForce();
    }

    public boolean estDemandable(BidBeloteSuit _contrat) {
        return bid.estDemandable(_contrat.bid);
    }

    public boolean getCouleurDominante() {
        return bid.getCouleurDominante();
    }

    public boolean ordreCouleur() {
        return bid.ordreCouleur();
    }

    public boolean ordreAtout() {
        return bid.ordreAtout();
    }

    public Order getOrdre() {
        return bid.getOrdre();
    }

    public boolean jouerDonne() {
        return bid.jouerDonne();
    }

    @Override
    public boolean eq(BidBeloteSuit _obj) {
        if (bid != _obj.bid) {
            return false;
        }
        if (suit != _obj.suit) {
            return false;
        }
        if (points != _obj.points) {
            return false;
        }
        return true;
    }
    public String toString(String _loc) {
        StringBuilder pts_ = new StringBuilder();
        if (points > 0) {
            pts_.append(SPACE);
            pts_.append(points);
        }
        if (getCouleurDominante()) {
            pts_.insert(0, suit.toString(_loc));
            return pts_.toString();
        }
        pts_.insert(0, bid.toString(_loc));
        return pts_.toString();
    }

    @Override
    public String display() {
        StringBuilder pts_ = new StringBuilder();
        if (points > 0) {
            pts_.append(SPACE);
            pts_.append(points);
        }
        if (getCouleurDominante()) {
            pts_.insert(0, suit.display());
            return pts_.toString();
        }
        pts_.insert(0, bid.display());
        return pts_.toString();
    }

    public BidBelote getBid() {
        return bid;
    }

    public void setBid(BidBelote _bid) {
        bid = _bid;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit _suit) {
        suit = _suit;
    }
}
