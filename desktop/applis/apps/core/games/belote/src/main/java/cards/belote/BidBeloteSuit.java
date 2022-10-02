package cards.belote;
import cards.belote.enumerations.BeloteResoucesAccess;
import cards.belote.enumerations.BidBelote;
import cards.consts.CoreResourcesAccess;
import cards.consts.Order;
import cards.consts.Suit;
import code.format.Format;


public final class BidBeloteSuit {
    private static final String SPACE = " ";
    private BidBelote bid = BidBelote.FOLD;

    private Suit suit = Suit.UNDEFINED;

    private int points;
    public static String toString(BidBeloteSuit _b, String _coreFile, String _file) {
        StringBuilder pts_ = new StringBuilder();
        if (_b.getPoints() > 0) {
            pts_.append(SPACE);
            pts_.append(_b.getPoints());
        }
        if (_b.getCouleurDominante()) {
            pts_.insert(0, Format.getConstanteLangue(CoreResourcesAccess.key(_b.getSuit()), _coreFile));
            return pts_.toString();
        }
        pts_.insert(0, Format.getConstanteLangue(BeloteResoucesAccess.key(_b.getBid()), _file));
        return pts_.toString();
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
