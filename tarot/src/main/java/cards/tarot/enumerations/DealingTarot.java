package cards.tarot.enumerations;
import cards.tarot.HandTarot;
import code.util.EnumList;
import code.util.NumberMap;
import code.util.Numbers;

public enum DealingTarot {
    DEAL_1_VS_2(3,CallingCard.WITHOUT,Numbers.wrapIntArray(3,3,3,3,3,3,3,3),6,0),
    DEAL_1_VS_3(4,CallingCard.WITHOUT,Numbers.wrapIntArray(3,3,3,3,3,3),6,0),
    DEAL_2_VS_2_WITHOUT_CALL(4,CallingCard.DEFINED,Numbers.wrapIntArray(3,3,3,3,3,3),6,1),
    DEAL_2_VS_2_CALL_KING(4,CallingCard.KING,Numbers.wrapIntArray(3,3,3,3,3,3),6,1),
    DEAL_2_VS_2_CALL_CHAR(4,CallingCard.CHARACTER_CARD,Numbers.wrapIntArray(3,3,3,3,3,3),6,1),
    DEAL_1_VS_4(5,CallingCard.WITHOUT,Numbers.wrapIntArray(2,2,2,2,2,2,2),8,0),
    DEAL_2_VS_3_CALL_KING(5,CallingCard.KING,Numbers.wrapIntArray(3,3,3,3,3),3,1),
    DEAL_2_VS_3_CALL_CHAR(5,CallingCard.CHARACTER_CARD,Numbers.wrapIntArray(3,3,3,3,3),3,1),
    DEAL_2_VS_4_WITHOUT_CALL(6,CallingCard.DEFINED,Numbers.wrapIntArray(2,2,2,2,2,2),6,1),
    DEAL_2_VS_4_CALL_KING(6,CallingCard.KING,Numbers.wrapIntArray(2,2,2,2,2,2),6,1),
    DEAL_2_VS_4_CALL_CHAR(6,CallingCard.CHARACTER_CARD,Numbers.wrapIntArray(2,2,2,2,2,2),6,1);
    private final int nombreJoueurs;
    private final CallingCard appel;
    private final int nombreCartesChien;
    private final int[] distribution;
    private final int nbAppeles;
    DealingTarot(int _nombreJoueurs,CallingCard _appel,
            int[] _distribution,int _nombreCartesChien,
            int _nbAppeles){
        nombreJoueurs = _nombreJoueurs;
        appel = _appel;
        distribution = _distribution;
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
    public Numbers<Byte> getSortedPlayers(int _player) {
        Numbers<Byte> players_ = new Numbers<Byte>();
        int next_ = _player;
        next_ = (byte) (next_%nombreJoueurs);
        while (players_.size() < nombreJoueurs) {
            players_.add((byte) next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }
    public Numbers<Byte> getSortedPlayersAfter(int _player) {
        Numbers<Byte> players_ = new Numbers<Byte>();
        int next_ = _player;
        next_++;
        next_ = (byte) (next_%nombreJoueurs);
        while (players_.size() < nombreJoueurs) {
            players_.add((byte) next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }
    public Numbers<Integer> getDistribution(){
        Numbers<Integer> distribution_ = new Numbers<Integer>();
        for(int i: distribution){
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

    public NumberMap<Integer,Integer> getDistributionAuChien(){
        NumberMap<Integer,Integer> indices_ = new NumberMap<Integer,Integer>();
        int nbToursTot_ = distribution.length * nombreJoueurs;
        for(int i=1;i<=nombreCartesChien;i++) {
            indices_.put((nbToursTot_*i)/(nombreCartesChien+1)-1,1);
        }
        return indices_;
    }
    public int getNombreCartesParJoueur() {
        int nombreCartesParJoueur_ = 0;
        for(int i: distribution) {
            nombreCartesParJoueur_ += i;
        }
        return nombreCartesParJoueur_;
    }
    public Numbers<Byte> getAppelesDetermines(byte _preneur) {
        if(appel != CallingCard.DEFINED) {
            return new Numbers<Byte>();
        }
        Numbers<Byte> appeles_ = new Numbers<Byte>();
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
        if (appel == CallingCard.CHARACTER_CARD) {
            return true;
        }
        return false;
    }
}
