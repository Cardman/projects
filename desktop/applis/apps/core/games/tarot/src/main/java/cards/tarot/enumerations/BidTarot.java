package cards.tarot.enumerations;
import code.util.IdList;

public enum BidTarot {
    FOLD(false,PlayingDog.OUT,0,0,AllowedBiddingTarot.ALWAYS,""),
    TAKE(true,PlayingDog.WITH,1,1,AllowedBiddingTarot.CAN_BE_FORBIDDEN,"0"),
    GUARD(true,PlayingDog.WITH,2,2,AllowedBiddingTarot.ALWAYS,"1"),
    GUARD_WITHOUT(true,PlayingDog.WITHOUT,3,4,AllowedBiddingTarot.CAN_BE_FORBIDDEN,"2"),
    GUARD_AGAINST(true,PlayingDog.AGAINST,4,6,AllowedBiddingTarot.CAN_BE_FORBIDDEN,"3"),
    SLAM(true,PlayingDog.AGAINST,5,10,AllowedBiddingTarot.CAN_BE_FORBIDDEN_BUT_NOT_AFTER,true,"4");
    private final boolean jouerDonne;
    private final PlayingDog jeuChien;
    private final int force;
    private final int coefficient;
    private final AllowedBiddingTarot possibiliteAnnonce;
    private final boolean faireTousPlis;
    private final String st;
    BidTarot(boolean _jouerDonne,
            PlayingDog _jeuChien,
            int _force,int _coefficient,
            AllowedBiddingTarot _possibiliteAnnonce, String _s){
        jouerDonne = _jouerDonne;
        jeuChien = _jeuChien;
        force = _force;
        coefficient = _coefficient;
        possibiliteAnnonce = _possibiliteAnnonce;
        faireTousPlis = false;
        st = _s;
    }
    BidTarot(boolean _jouerDonne,
            PlayingDog _jeuChien,
            int _force,int _coefficient,
            AllowedBiddingTarot _possibiliteAnnonce,
            boolean _faireTousPlis, String _s){
        jouerDonne = _jouerDonne;
        jeuChien = _jeuChien;
        force = _force;
        coefficient = _coefficient;
        possibiliteAnnonce = _possibiliteAnnonce;
        faireTousPlis = _faireTousPlis;
        st = _s;
    }

    public String getSt() {
        return st;
    }

    public boolean isJouerDonne() {
        return jouerDonne;
    }
    public PlayingDog getJeuChien() {
        return jeuChien;
    }
    public int getForce() {
        return force;
    }
    public int getCoefficient() {
        return coefficient;
    }
    public AllowedBiddingTarot getPossibiliteAnnonce() {
        return possibiliteAnnonce;
    }
    public boolean isFaireTousPlis() {
        return faireTousPlis;
    }
    private boolean estPlusFortQue(BidTarot _c){
        return getForce()>_c.getForce();
    }
    public boolean estDemandable(BidTarot _c){
        if(!jouerDonne) {
            return true;
        }
        return estPlusFortQue(_c);
    }

    public static IdList<BidTarot> getValidBids() {
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        zero(bids_);
        nonZero(bids_);
        return bids_;
    }
    public static IdList<BidTarot> getAlwaysUsableBids() {
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(FOLD);
        bids_.add(GUARD);
        return bids_;
    }
    public static IdList<BidTarot> getZeroBids() {
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        zero(bids_);
        return bids_;
    }

    private static void zero(IdList<BidTarot> _bids) {
        _bids.add(FOLD);
    }

    public static IdList<BidTarot> getNonZeroBids() {
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        nonZero(bids_);
        return bids_;
    }

    private static void nonZero(IdList<BidTarot> _bids) {
        _bids.add(TAKE);
        _bids.add(GUARD);
        _bids.add(GUARD_WITHOUT);
        _bids.add(GUARD_AGAINST);
        _bids.add(SLAM);
    }

    public boolean strongerThan(BidTarot _o2) {
        return estPlusFortQue(_o2);
    }
}
