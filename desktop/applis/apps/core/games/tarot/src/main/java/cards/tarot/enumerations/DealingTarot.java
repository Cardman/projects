package cards.tarot.enumerations;
import code.util.EnumList;
import code.util.*;
import code.util.Ints;
import code.util.core.NumberUtil;

public enum DealingTarot {
    DEAL_1_VS_2(3,CallingCard.WITHOUT,6,0),
    DEAL_1_VS_3(4,CallingCard.WITHOUT,6,0),
    DEAL_2_VS_2_WITHOUT_CALL(4,CallingCard.DEFINED,6,1),
    DEAL_2_VS_2_CALL_KING(4,CallingCard.KING,6,1),
    DEAL_2_VS_2_CALL_CHAR(4,CallingCard.CHARACTER_CARD,6,1),
    DEAL_1_VS_4(5,CallingCard.WITHOUT,8,0),
    DEAL_2_VS_3_CALL_KING(5,CallingCard.KING,3,1),
    DEAL_2_VS_3_CALL_CHAR(5,CallingCard.CHARACTER_CARD,3,1),
    DEAL_2_VS_4_WITHOUT_CALL(6,CallingCard.DEFINED,6,1),
    DEAL_2_VS_4_CALL_KING(6,CallingCard.KING,6,1),
    DEAL_2_VS_4_CALL_CHAR(6,CallingCard.CHARACTER_CARD,6,1);
    private final int nombreJoueurs;
    private final CallingCard appel;
    private final int nombreCartesChien;
    private final int nbAppeles;
    DealingTarot(int _nombreJoueurs,CallingCard _appel,
            int _nombreCartesChien,
            int _nbAppeles){
        nombreJoueurs = _nombreJoueurs;
        appel = _appel;
        nombreCartesChien = _nombreCartesChien;
        nbAppeles = _nbAppeles;
    }
    public int getNombreJoueurs() {
        return nombreJoueurs;
    }
    public CallingCard getAppel() {
        return appel;
    }
    public int getNbAppeles() {
        return nbAppeles;
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
    public Ints getDistribution(){
        Ints distribution_ = new Ints();
        for(int i: valDistribution()){
            distribution_.add(i);
        }
        return distribution_;
    }
    public int getNombreCartesChien() {
        return nombreCartesChien;
    }
    public static EnumList<DealingTarot> getRepartitionsValides(){
        EnumList<DealingTarot> repartitions_ = new EnumList<DealingTarot>();
        for(DealingTarot r: DealingTarot.values()) {
            repartitions_.add(r);
        }
        return repartitions_;
    }

    public IntMap<Integer> getDistributionAuChien(){
        IntMap<Integer> indices_ = new IntMap<Integer>();
        int nbToursTot_ = valDistribution().length * nombreJoueurs;
        for(int i=1;i<=nombreCartesChien;i++) {
            indices_.put((nbToursTot_*i)/(nombreCartesChien+1)-1,1);
        }
        return indices_;
    }
    public int getNombreCartesParJoueur() {
        int nombreCartesParJoueur_ = 0;
        for(int i: valDistribution()) {
            nombreCartesParJoueur_ += i;
        }
        return nombreCartesParJoueur_;
    }
    private int[] valDistribution(){
        if (nombreJoueurs == 3) {
            return NumberUtil.wrapIntArray(3,3,3,3,3,3,3,3);
        }
        if (nombreJoueurs == 4) {
            return NumberUtil.wrapIntArray(3,3,3,3,3,3);
        }
        if (nombreJoueurs == 6) {
            return NumberUtil.wrapIntArray(2,2,2,2,2,2);
        }
        if (this == DEAL_1_VS_4) {
            return NumberUtil.wrapIntArray(2,2,2,2,2,2,2);
        }
        return NumberUtil.wrapIntArray(3,3,3,3,3);
    }
    public Bytes getAppelesDetermines(byte _preneur) {
        if(appel != CallingCard.DEFINED) {
            return new Bytes();
        }
        Bytes appeles_ = new Bytes();
        int delta_ = nombreJoueurs/(nbAppeles+1);
        for(int i=0;i<nbAppeles;i++) {
            appeles_.add((byte) ((_preneur+(i+1)*delta_)%nombreJoueurs));
        }
        return appeles_;
    }

    public boolean callCard() {
        if (appel == CallingCard.KING) {
            return true;
        }
        return appel == CallingCard.CHARACTER_CARD;
    }
}
