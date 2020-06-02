package cards.belote.enumerations;
import code.util.EnumList;
import code.util.*;
import code.util.Ints;

public enum DealingBelote {
    CLASSIC_2_VS_2(4, Numbers.wrapIntArray(3,2),Numbers.wrapIntArray(3)),
    COINCHE_2_VS_2(4, Numbers.wrapIntArray(3,3,2),Numbers.wrapIntArray());
    private final int nombreJoueurs;
    private final int[] distributionDebut;
    private final int[] distributionFin;
    DealingBelote(int _nombreJoueurs,
                  int[] _distributionDebut, int[] _distributionFin){
        nombreJoueurs = _nombreJoueurs;
        distributionDebut = _distributionDebut;
        distributionFin = _distributionFin;
    }
    public int getNombreJoueurs() {
        return nombreJoueurs;
    }
    public byte getNextPlayer(int _player) {
        int next_ = _player;
        next_++;
        return (byte) (next_%nombreJoueurs);
    }

    public Bytes getSortedPlayers(int _player) {
        Bytes players_ = new Bytes();
        int next_ = _player;
        next_ = (byte) (next_%nombreJoueurs);
        while (players_.size() < nombreJoueurs) {
            players_.add((byte) next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }

    public Bytes getSortedPlayersAfter(int _player) {
        Bytes players_ = new Bytes();
        int next_ = _player;
        next_++;
        next_ = (byte) (next_%nombreJoueurs);
        while (players_.size() < nombreJoueurs) {
            players_.add((byte) next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }

    public Ints getDistributionDebut() {
        Ints distributionDebut_ = new Ints();
        for(int i: distributionDebut){
            distributionDebut_.add(i);
        }
        return distributionDebut_;
    }
    public Ints getDistributionFin() {
        Ints distributionFin_ = new Ints();
        for(int i: distributionFin){
            distributionFin_.add(i);
        }
        return distributionFin_;
    }

    public int getFirstCards() {
        int remainingCards_ = 0;
        for(int i: distributionDebut) {
            remainingCards_ += i;
        }
        return remainingCards_;
    }
    public int getRemainingCards() {
        int remainingCards_ = 0;
        for(int i: distributionFin) {
            remainingCards_ += i * nombreJoueurs;
        }
        return remainingCards_;
    }
    public int getNombreCartesParJoueur(){
        int nombreCartesParJoueur_ = 0;
        for(int i: distributionDebut) {
            nombreCartesParJoueur_ += i;
        }
        for(int i: distributionFin) {
            nombreCartesParJoueur_ += i;
        }
        return nombreCartesParJoueur_;
    }
    public static EnumList<DealingBelote> getRepartitionsValides() {
        EnumList<DealingBelote> repartitions_ = new EnumList<DealingBelote>();
        for(DealingBelote r: DealingBelote.values()) {
            repartitions_.add(r);
        }
        return repartitions_;
    }
}
