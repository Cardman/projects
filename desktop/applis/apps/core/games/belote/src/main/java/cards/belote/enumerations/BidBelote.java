package cards.belote.enumerations;
import cards.consts.Order;
import code.util.IdList;

public enum BidBelote {
    FOLD(0,true),
    SUIT(1, true,true),
    OTHER_SUIT(1, true,true),
    NO_TRUMP(3, Order.SUIT,false),
    ALL_TRUMP(4, Order.TRUMP,false);
    private final int force;
    private final boolean couleurDominante;
    private final boolean toujoursPossibleAnnoncer;
    private final Order ordre;
    BidBelote(int _force,
            boolean _toujoursPossibleAnnoncer){
        force = _force;
        ordre = Order.NOTHING;
        couleurDominante = false;
        toujoursPossibleAnnoncer = _toujoursPossibleAnnoncer;
    }
    BidBelote(int _force,
              boolean _couleurDominante,
              boolean _toujoursPossibleAnnoncer){
        force = _force;
        ordre = Order.NOTHING;
        couleurDominante = _couleurDominante;
        toujoursPossibleAnnoncer = _toujoursPossibleAnnoncer;
    }
    BidBelote(int _force,
              Order _ordre,
              boolean _toujoursPossibleAnnoncer){
        force = _force;
        ordre = _ordre;
        couleurDominante = false;
        toujoursPossibleAnnoncer = _toujoursPossibleAnnoncer;
    }
    public boolean getToujoursPossibleAnnoncer(){
        return toujoursPossibleAnnoncer;
    }
    public boolean getCouleurDominante(){
        return couleurDominante;
    }

    //portee projet
    public Order getOrdre() {
        return ordre;
    }

    public boolean ordreCouleur(){
        if(couleurDominante){
            return false;
        }
        return ordre == Order.SUIT;
    }
    public boolean ordreAtout(){
        if(couleurDominante){
            return false;
        }
        return ordre == Order.TRUMP;
    }

    public boolean jouerDonne(){
        return force>0;
    }
    //portee projet
    public int getForce() {
        return force;
    }
    public boolean estDemandable(BidBelote _contrat) {
        if(getForce()==0) {
            return true;
        }
        return estPlusFortQue(_contrat);
    }
    private boolean estPlusFortQue(BidBelote _contrat) {
        return getForce()>_contrat.getForce();
    }

    public static IdList<BidBelote> getAlwaysUsableBids() {
        IdList<BidBelote> bids_ = new IdList<BidBelote>();
        bids_.add(FOLD);
        bids_.add(SUIT);
        bids_.add(OTHER_SUIT);
        return bids_;
    }
    public static IdList<BidBelote> all() {
        IdList<BidBelote> bids_ = new IdList<BidBelote>();
        fold(bids_);
        take(bids_);
        return bids_;
    }
    public static IdList<BidBelote> getZeroBids() {
        IdList<BidBelote> bids_ = new IdList<BidBelote>();
        fold(bids_);
        return bids_;
    }

    private static void fold(IdList<BidBelote> _bids) {
        _bids.add(FOLD);
    }

    public static IdList<BidBelote> getNonZeroBids() {
        IdList<BidBelote> bids_ = new IdList<BidBelote>();
        take(bids_);
        return bids_;
    }

    private static void take(IdList<BidBelote> _bids) {
        _bids.add(SUIT);
        _bids.add(OTHER_SUIT);
        _bids.add(NO_TRUMP);
        _bids.add(ALL_TRUMP);
    }
}
