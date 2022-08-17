package cards.belote.enumerations;
import cards.consts.SortedPlayers;
import code.util.EnumList;
import code.util.Ints;
import code.util.core.NumberUtil;

public enum DealingBelote {
    CLASSIC_2_VS_2(4),
    COINCHE_2_VS_2(4);
    private final SortedPlayers id;

    DealingBelote(int _nombreJoueurs){
        id = new SortedPlayers(_nombreJoueurs);
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
    public int getRemainingCards() {
        int remainingCards_ = 0;
        for(int i: valDistributionFin()) {
            remainingCards_ += i * getId().getNombreJoueurs();
        }
        return remainingCards_;
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
        if (this == CLASSIC_2_VS_2) {
            return NumberUtil.wrapIntArray(3,2);
        }
        return NumberUtil.wrapIntArray(3,3,2);
    }
    private int[] valDistributionFin() {
        if (this == CLASSIC_2_VS_2) {
            return NumberUtil.wrapIntArray(3);
        }
        return NumberUtil.wrapIntArray();
    }
    public static EnumList<DealingBelote> getRepartitionsValides() {
        EnumList<DealingBelote> repartitions_ = new EnumList<DealingBelote>();
        for(DealingBelote r: DealingBelote.values()) {
            repartitions_.add(r);
        }
        return repartitions_;
    }
}
