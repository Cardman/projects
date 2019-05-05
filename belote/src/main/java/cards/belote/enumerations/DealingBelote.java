package cards.belote.enumerations;
import cards.belote.HandBelote;
import code.util.EnumList;
import code.util.Numbers;

public enum DealingBelote {
    CLASSIC_2_VS_2(4,0,Numbers.wrapIntArray(3,2),Numbers.wrapIntArray(3)),
    COINCHE_2_VS_2(4,0,Numbers.wrapIntArray(3,3,2),Numbers.wrapIntArray());
    private final int nombreJoueurs;
    private final int cartesAPart;
    private final int[] distributionDebut;
    private final int[] distributionFin;
    DealingBelote(int _nombreJoueurs, int _cartesAPart,
            int[] _distributionDebut, int[] _distributionFin){
        nombreJoueurs = _nombreJoueurs;
        cartesAPart = _cartesAPart;
        distributionDebut = _distributionDebut;
        distributionFin = _distributionFin;
    }
    public int getNombreJoueurs() {
        return nombreJoueurs;
    }
    public int getCartesAPart() {
        return cartesAPart;
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

    public Numbers<Integer> getDistributionDebut() {
        Numbers<Integer> distributionDebut_ = new Numbers<Integer>();
        for(int i: distributionDebut){
            distributionDebut_.add(i);
        }
        return distributionDebut_;
    }
    public Numbers<Integer> getDistributionFin() {
        Numbers<Integer> distributionFin_ = new Numbers<Integer>();
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
            if(!r.repartitionValide()) {
                continue;
            }
            repartitions_.add(r);
        }
        return repartitions_;
    }
    public boolean repartitionValide(){
        for(int i: distributionDebut) {
            if(i <= 0) {
                return false;
            }
        }
        for(int i: distributionFin) {
            if(i <= 0) {
                return false;
            }
        }
        if(distributionDebut.length == 0) {
            return false;
        }
        if(nombreJoueurs < 2) {
            return false;
        }
        if(cartesAPart < 0) {
            return false;
        }
        return getNombreCartesParJoueur()*nombreJoueurs+cartesAPart==HandBelote.pileBase().total();
    }
}
