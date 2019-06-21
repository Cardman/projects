package cards.belote.enumerations;
import cards.consts.Order;
import code.util.EnumList;

public enum BidBelote {
    FOLD(0,true),
    SUIT(1,2,true,true),
    OTHER_SUIT(1,1,true,true),
    NO_TRUMP(3,1,Order.SUIT,false),
    ALL_TRUMP(4,1,Order.TRUMP,false);
    private final int force;
    private final int priorite;
    private final boolean couleurDominante;
    private final boolean toujoursPossibleAnnoncer;
    private final Order ordre;
    BidBelote(int _force,
            boolean _toujoursPossibleAnnoncer){
        force = _force;
        priorite = 0;
        ordre = Order.NOTHING;
        couleurDominante = false;
        toujoursPossibleAnnoncer = _toujoursPossibleAnnoncer;
    }
    BidBelote(int _force,int _priorite,
            boolean _couleurDominante,
            boolean _toujoursPossibleAnnoncer){
        force = _force;
        priorite = _priorite;
        ordre = Order.NOTHING;
        couleurDominante = _couleurDominante;
        toujoursPossibleAnnoncer = _toujoursPossibleAnnoncer;
    }
    BidBelote(int _force,int _priorite,
            Order _ordre,
            boolean _toujoursPossibleAnnoncer){
        force = _force;
        priorite = _priorite;
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
    //portee projet
    public boolean estPrioritaire(BidBelote _enchere, boolean _premierTour){
        if(force != _enchere.force){
            return false;
        }
        if(_premierTour){
            if(priorite > _enchere.priorite){
                return true;
            }
        }else{
            if(priorite < _enchere.priorite){
                return true;
            }
        }
        return false;
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

    public static EnumList<BidBelote> getAlwaysUsableBids() {
        EnumList<BidBelote> bids_ = new EnumList<BidBelote>();
        for (BidBelote e: BidBelote.values()) {
            if (!e.getToujoursPossibleAnnoncer()) {
                continue;
            }
            bids_.add(e);
        }
        return bids_;
    }
    public static EnumList<BidBelote> getZeroBids() {
        EnumList<BidBelote> bids_ = new EnumList<BidBelote>();
        for (BidBelote e: BidBelote.values()) {
            if (e.jouerDonne()) {
                continue;
            }
            bids_.add(e);
        }
        return bids_;
    }
    public static EnumList<BidBelote> getNonZeroBids() {
        EnumList<BidBelote> bids_ = new EnumList<BidBelote>();
        for (BidBelote e: BidBelote.values()) {
            if (!e.jouerDonne()) {
                continue;
            }
            bids_.add(e);
        }
        return bids_;
    }
}
