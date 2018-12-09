package cards.tarot;
import cards.consts.MixCardsChoice;
import cards.tarot.comparators.AllowedHandfulComparator;
import cards.tarot.enumerations.AllowedBiddingTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.EndDealTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.ModeTarot;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.NumberMap;


public final class RulesTarot {

    private MixCardsChoice mixedCards=MixCardsChoice.EACH_LAUNCHING;
    private EnumList<Miseres> miseres=new EnumList<Miseres>();
    private EnumMap<BidTarot,Boolean> allowedBids=new EnumMap<BidTarot,Boolean>();
    private ModeTarot mode=ModeTarot.NORMAL;
    private DealingTarot dealing=DealingTarot.DEAL_2_VS_3_CALL_KING;
    private EnumMap<Handfuls,Integer> allowedHandfuls = new EnumMap<Handfuls,Integer>();
    private EndDealTarot endDealTarot=EndDealTarot.ATTACK_LOOSE;
    private int nbDeals;
    private boolean discardAfterCall = true;

    public RulesTarot() {
        this(DealingTarot.DEAL_2_VS_3_CALL_KING);
    }

    RulesTarot(DealingTarot _rep) {
        for(BidTarot c:BidTarot.values()){
            if(c.getPossibiliteAnnonce()==AllowedBiddingTarot.CAN_BE_FORBIDDEN_BUT_NOT_AFTER){
                allowedBids.put(c, false);
            }else{
                allowedBids.put(c, true);
            }
        }
        int nbCartesParJoueurs_ = _rep.getNombreCartesParJoueur();
        for(Handfuls p: Handfuls.getPoigneesValidesParDefaut()) {
            NumberMap<Integer,Integer> conf_ = Handfuls.getConfigurationParDefautAnnoncePoignee(p);
            allowedHandfuls.put(p,conf_.getVal(nbCartesParJoueurs_));
        }
    }
    public RulesTarot(byte _nbPlayers) {
        for(BidTarot c:BidTarot.values()){
            if(c.getPossibiliteAnnonce()==AllowedBiddingTarot.CAN_BE_FORBIDDEN_BUT_NOT_AFTER){
                allowedBids.put(c, false);
            }else{
                allowedBids.put(c, true);
            }
        }
        for (DealingTarot r: DealingTarot.getRepartitionsValides()) {
            if (r.getNombreJoueurs() == _nbPlayers) {
                dealing = r;
                int nbCartesParJoueurs_ = r.getNombreCartesParJoueur();
                for(Handfuls p: Handfuls.getPoigneesValidesParDefaut()) {
                    NumberMap<Integer,Integer> conf_ = Handfuls.getConfigurationParDefautAnnoncePoignee(p);
                    allowedHandfuls.put(p,conf_.getVal(nbCartesParJoueurs_));
                }
                break;
            }
        }
    }
    public RulesTarot(RulesTarot _reglesTarot){
        mixedCards = _reglesTarot.mixedCards;
        miseres = new EnumList<Miseres>(_reglesTarot.miseres);
        allowedBids = new EnumMap<BidTarot,Boolean>(_reglesTarot.allowedBids);
        mode = _reglesTarot.mode;
        dealing = _reglesTarot.dealing;
        allowedHandfuls = new EnumMap<Handfuls,Integer>(_reglesTarot.allowedHandfuls);
        endDealTarot = _reglesTarot.endDealTarot;
        nbDeals = _reglesTarot.nbDeals;
    }
    public boolean isValidRules() {
        if (mixedCards == null) {
            return false;
        }
        if (miseres == null) {
            return false;
        }
        if (allowedBids == null) {
            return false;
        }
        if (mode == null) {
            return false;
        }
        if (dealing == null) {
            return false;
        }
        if (allowedHandfuls == null) {
            return false;
        }
        if (endDealTarot == null) {
            return false;
        }
        for(Handfuls p: Handfuls.getPoigneesValidesParDefaut()) {
            if (!allowedHandfuls.contains(p)) {
                return false;
            }
        }
        for(BidTarot b:BidTarot.values()){
            if (!allowedBids.contains(b)) {
                return false;
            }
        }
        int nbCardsPerPlayer_ = dealing.getNombreCartesParJoueur();
        int nbTrumps_ = HandTarot.trumpsPlusExcuse().total();
        for (Handfuls p: allowedHandfuls.getKeys()) {
            if (allowedHandfuls.getVal(p) < 0) {
                return false;
            }
            if (allowedHandfuls.getVal(p) > nbCardsPerPlayer_) {
                return false;
            }
            if (allowedHandfuls.getVal(p) > nbTrumps_) {
                return false;
            }
        }
        return true;
    }
    public MixCardsChoice getCartesBattues() {
        return mixedCards;
    }
    public void setCartesBattues(MixCardsChoice _cartesBattues) {
        mixedCards = _cartesBattues;
    }
    public EnumList<Handfuls> getCurrentAllowedHandfuls() {
        EnumList<Handfuls> handfuls_ = new EnumList<Handfuls>();
        for (Handfuls h: allowedHandfuls.getKeys()) {
            if (!poigneeAutorisee(h)) {
                continue;
            }
            handfuls_.add(h);
        }
        return handfuls_;
    }
    public boolean poigneeAutorisee(Handfuls _poignee) {
        return allowedHandfuls.getVal(_poignee) > 0;
    }
    public EnumList<Handfuls> getPoigneesOrdonnees() {
        EnumList<Handfuls> poignees_ = allowedHandfuls.getKeys();
        poignees_.sortElts(new AllowedHandfulComparator(allowedHandfuls));
        return poignees_;
    }
    public EnumList<Miseres> getMiseres() {
        return miseres;
    }
    public void setMiseres(EnumList<Miseres> _miseres) {
        miseres = _miseres;
    }
    public EnumList<BidTarot> getContratsAutorises() {
        EnumList<BidTarot> l_;
        l_ = new EnumList<BidTarot>();
        for (EntryCust<BidTarot, Boolean> e: allowedBids.entryList()) {
            if (e.getValue()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }
    public EnumMap<BidTarot,Boolean> getContrats() {
        return allowedBids;
    }
    public void setContrats(EnumMap<BidTarot,Boolean> _contrats) {
        allowedBids = _contrats;
    }
    public ModeTarot getMode() {
        return mode;
    }
    public void setMode(ModeTarot _mode) {
        mode = _mode;
    }
    public DealingTarot getRepartition() {
        return dealing;
    }
    public void setRepartition(DealingTarot _repartition) {
        dealing = _repartition;
    }
    public EnumMap<Handfuls,Integer> getPoigneesAutorisees() {
        return allowedHandfuls;
    }
    public void setPoigneesAutorisees(EnumMap<Handfuls,Integer> _poigneesAutorisees) {
        allowedHandfuls = _poigneesAutorisees;
    }
    public EndDealTarot getFinPartieTarot() {
        return endDealTarot;
    }
    public void setFinPartieTarot(EndDealTarot _finPartieTarot) {
        endDealTarot = _finPartieTarot;
    }
    public int getNombreParties() {
        return nbDeals;
    }
    public void setNombreParties(int _nombreParties) {
        nbDeals = _nombreParties;
    }

    public boolean getDiscardAfterCall() {
        if (dealing.getAppel() == CallingCard.DEFINED) {
            return true;
        }
        if (dealing.getAppel() == CallingCard.WITHOUT) {
            return true;
        }
        return discardAfterCall;
    }

    public void setDiscardAfterCall(boolean _discardAfterCall) {
        discardAfterCall = _discardAfterCall;
    }

    public MixCardsChoice getMixedCards() {
        return mixedCards;
    }

    public void setMixedCards(MixCardsChoice _mixedCards) {
        mixedCards = _mixedCards;
    }

    public EnumMap<BidTarot, Boolean> getAllowedBids() {
        return allowedBids;
    }

    public void setAllowedBids(EnumMap<BidTarot, Boolean> _allowedBids) {
        allowedBids = _allowedBids;
    }

    public DealingTarot getDealing() {
        return dealing;
    }

    public void setDealing(DealingTarot _dealing) {
        dealing = _dealing;
    }

    public EnumMap<Handfuls, Integer> getAllowedHandfuls() {
        return allowedHandfuls;
    }

    public void setAllowedHandfuls(EnumMap<Handfuls, Integer> _allowedHandfuls) {
        allowedHandfuls = _allowedHandfuls;
    }

    public EndDealTarot getEndDealTarot() {
        return endDealTarot;
    }

    public void setEndDealTarot(EndDealTarot _endDealTarot) {
        endDealTarot = _endDealTarot;
    }

    public int getNbDeals() {
        return nbDeals;
    }

    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
    }
}
