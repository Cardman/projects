package cards.tarot.enumerations;
import code.util.EnumList;

public enum BidTarot {
    FOLD(false,PlayingDog.OUT,0,0,AllowedBiddingTarot.ALWAYS),
    TAKE(true,PlayingDog.WITH,1,1,AllowedBiddingTarot.CAN_BE_FORBIDDEN),
    GUARD(true,PlayingDog.WITH,2,2,AllowedBiddingTarot.ALWAYS),
    GUARD_WITHOUT(true,PlayingDog.WITHOUT,3,4,AllowedBiddingTarot.CAN_BE_FORBIDDEN),
    GUARD_AGAINST(true,PlayingDog.AGAINST,4,6,AllowedBiddingTarot.CAN_BE_FORBIDDEN),
    SLAM(true,PlayingDog.AGAINST,5,10,AllowedBiddingTarot.CAN_BE_FORBIDDEN_BUT_NOT_AFTER,true);
    private final boolean jouerDonne;
    private final PlayingDog jeuChien;
    private final int force;
    private final int coefficient;
    private final AllowedBiddingTarot possibiliteAnnonce;
    private final boolean faireTousPlis;
    BidTarot(boolean _jouerDonne,
            PlayingDog _jeuChien,
            int _force,int _coefficient,
            AllowedBiddingTarot _possibiliteAnnonce){
        jouerDonne = _jouerDonne;
        jeuChien = _jeuChien;
        force = _force;
        coefficient = _coefficient;
        possibiliteAnnonce = _possibiliteAnnonce;
        faireTousPlis = false;
    }
    BidTarot(boolean _jouerDonne,
            PlayingDog _jeuChien,
            int _force,int _coefficient,
            AllowedBiddingTarot _possibiliteAnnonce,
            boolean _faireTousPlis){
        jouerDonne = _jouerDonne;
        jeuChien = _jeuChien;
        force = _force;
        coefficient = _coefficient;
        possibiliteAnnonce = _possibiliteAnnonce;
        faireTousPlis = _faireTousPlis;
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

    public static EnumList<BidTarot> getValidBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot e: BidTarot.values()) {
            bids_.add(e);
        }
        return bids_;
    }
    public static EnumList<BidTarot> getAlwaysUsableBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot e: BidTarot.values()) {
            if (e.getPossibiliteAnnonce() != AllowedBiddingTarot.ALWAYS) {
                continue;
            }
            bids_.add(e);
        }
        return bids_;
    }
    public static EnumList<BidTarot> getZeroBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot e: BidTarot.values()) {
            if (e.isJouerDonne()) {
                continue;
            }
            bids_.add(e);
        }
        return bids_;
    }
    public static EnumList<BidTarot> getNonZeroBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot e: BidTarot.values()) {
            if (!e.isJouerDonne()) {
                continue;
            }
            bids_.add(e);
        }
        return bids_;
    }

    public boolean strongerThan(BidTarot _o2) {
        return estPlusFortQue(_o2);
    }
}
