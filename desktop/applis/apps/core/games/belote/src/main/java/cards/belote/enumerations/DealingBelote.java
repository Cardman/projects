package cards.belote.enumerations;
import cards.consts.SortedPlayers;
import code.util.IdList;
import code.util.Ints;
import code.util.core.NumberUtil;

public enum DealingBelote {
    CLASSIC_2_VS_2(4,"", 0),
    COINCHE_2_VS_2(4,"_", 0),
    CLASSIC_1_VS_2(3, "0", 8),
    COINCHE_1_VS_2(3, "1", 8),
    CLASSIC_1_VS_2_5(3, "2", 5),
    COINCHE_1_VS_2_5(3, "3", 5),
    CLASSIC_1_VS_2_2(3, "4", 2),
    COINCHE_1_VS_2_2(3, "5", 2),
    CLASSIC_1_VS_2_24(3, "6", 0),
    COINCHE_1_VS_2_24(3, "7", 0);
    private final SortedPlayers id;
    private final String st;
    private final int discarded;

    DealingBelote(int _nombreJoueurs, String _s, int _d){
        id = new SortedPlayers(_nombreJoueurs);
        st = _s;
        discarded = _d;
    }

    public String getSt() {
        return st;
    }

    public SortedPlayers getId() {
        return id;
    }

    public Ints getDistributionDebut() {
        Ints distributionDebut_ = new Ints();
        for(int i: valDistributionDebut()){
            distributionDebut_.add(i);
        }
        return distributionDebut_;
    }
    public Ints getDistributionFin() {
        Ints distributionFin_ = new Ints();
        for(int i: valDistributionFin()){
            distributionFin_.add(i);
        }
        return distributionFin_;
    }

    public int getFirstCards() {
        int remainingCards_ = 0;
        for(int i: valDistributionDebut()) {
            remainingCards_ += i;
        }
        return remainingCards_;
    }
    public int remainingCards() {
        if (discarded > 0) {
            return discarded;
        }
        if (splitHand()) {
            return 8 + getRemainingCards();
        }
        return getRemainingCards();
    }
    public boolean splitHand() {
        return this == CLASSIC_1_VS_2_24 || this == COINCHE_1_VS_2_24;
    }
    public boolean withBidPointsForAllPlayers() {
        return this == COINCHE_2_VS_2 || this == COINCHE_1_VS_2 || this == COINCHE_1_VS_2_5 || this == COINCHE_1_VS_2_2 || this == COINCHE_1_VS_2_24;
    }
    public int getRemainingCards() {
        int remainingCards_ = 0;
        for(int i: valDistributionFin()) {
            remainingCards_ += i * getId().getNombreJoueurs();
        }
        return remainingCards_;
    }

    public int getDiscarded() {
        return discarded;
    }

    public int getNombreCartesParJoueur(){
        int nombreCartesParJoueur_ = 0;
        for(int i: valDistributionDebut()) {
            nombreCartesParJoueur_ += i;
        }
        for(int i: valDistributionFin()) {
            nombreCartesParJoueur_ += i;
        }
        return nombreCartesParJoueur_;
    }
    private int[] valDistributionDebut() {
        if (this == CLASSIC_2_VS_2 || this == CLASSIC_1_VS_2_24) {
            return NumberUtil.wrapIntArray(3,2);
        }
        if (discarded == 2) {
            return NumberUtil.wrapIntArray(3,2,3,2);
        }
        if (discarded == 5) {
            return NumberUtil.wrapIntArray(3,3,3);
        }
        return NumberUtil.wrapIntArray(3,3,2);
    }
    private int[] valDistributionFin() {
        if (this == CLASSIC_2_VS_2 || this == CLASSIC_1_VS_2_24) {
            return NumberUtil.wrapIntArray(3);
        }
        return NumberUtil.wrapIntArray();
    }
    public static IdList<DealingBelote> getRepartitionsValides() {
        IdList<DealingBelote> repartitions_ = new IdList<DealingBelote>();
        repartitions_.add(CLASSIC_2_VS_2);
        repartitions_.add(COINCHE_2_VS_2);
        return repartitions_;
    }
}
