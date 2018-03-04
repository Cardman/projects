package cards.belote.enumerations;
import cards.consts.Order;
import code.format.Format;
import code.util.EnumList;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum BidBelote implements Displayable {
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
    public int getPriorite(){
        return priorite;
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

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.BELOTE_BID,name());
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
    public static EnumList<BidBelote> getValidBids() {
        EnumList<BidBelote> bids_ = new EnumList<BidBelote>();
        for (BidBelote e: BidBelote.values()) {
            EnumList<BidBelote> bidsOfSameStrength_ = e.getBidsOfSameStrength();
            if (bidsOfSameStrength_.size() > 1) {
                boolean bidsOfSamePriority_ = false;
                for (BidBelote e2_: bidsOfSameStrength_) {
                    if (e2_ == e) {
                        continue;
                    }
                    if (e.getPriorite() == e2_.getPriorite()) {
                        bidsOfSamePriority_ = true;
                        break;
                    }
                }
                if (bidsOfSamePriority_) {
                    continue;
                }
                boolean existBidNotAlwaysUsable_ = false;
                for (BidBelote e2_: bidsOfSameStrength_) {
                    if (e2_.getToujoursPossibleAnnoncer()) {
                        continue;
                    }
                    existBidNotAlwaysUsable_ = true;
                }
                if (existBidNotAlwaysUsable_) {
                    continue;
                }
            }
            if (e.getOrdre() == Order.NOTHING) {
                if (!e.getCouleurDominante()) {
                    if (!e.jouerDonne()) {
                        bids_.add(e);
                    }
                    continue;
                }
                bids_.add(e);
                continue;
            }
            if (e.getOrdre() == Order.TRUMP || e.getOrdre() == Order.SUIT) {
                if (e.getCouleurDominante()) {
                    continue;
                }
                bids_.add(e);
            }
        }
        return bids_;
    }
    public EnumList<BidBelote> getBidsOfSameStrength() {
        EnumList<BidBelote> bids_ = new EnumList<BidBelote>();
        for (BidBelote e: BidBelote.values()) {
            if (e.getForce() == getForce()) {
                bids_.add(e);
            }
        }
        return bids_;
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
    public static boolean allOrderedBids() {
        for (BidBelote e: BidBelote.values()) {
            if (e.jouerDonne()) {
                for (BidBelote e2_: BidBelote.values()) {
                    if (e2_.jouerDonne()) {
                        continue;
                    }
                    if (e.getForce() > e2_.getForce()) {
                        continue;
                    }
                    return false;
                }
            } else {
                for (BidBelote e2_: BidBelote.values()) {
                    if (!e2_.jouerDonne()) {
                        continue;
                    }
                    if (e.getForce() < e2_.getForce()) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
}
