package cards.belote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Numbers;


public final class RulesBelote {

    public static final int DIVISIONS = 10;

    private MixCardsChoice mixedCards=MixCardsChoice.EACH_LAUNCHING;
    private EnumMap<DeclaresBelote,Boolean> allowedDeclares = new EnumMap<DeclaresBelote,Boolean>();
    private boolean underTrumpFoe;
    private BeloteTrumpPartner trumpPartner=BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP;
    private EnumMap<BidBelote,Boolean> allowedBids = new EnumMap<BidBelote,Boolean>();
    private DealingBelote dealing = DealingBelote.CLASSIC_2_VS_2;
    private boolean classicCountPoints=true;
    private int nbDeals;

    public RulesBelote() {
        for(DeclaresBelote a:DeclaresBelote.annoncesValides()){
            allowedDeclares.put(a, false);
        }
        for(BidBelote e:BidBelote.values()){
            if(e.getToujoursPossibleAnnoncer()){
                allowedBids.put(e, true);
            }else{
                allowedBids.put(e, false);
            }
        }
    }

    public RulesBelote(RulesBelote _reglesBelote) {
        mixedCards = _reglesBelote.mixedCards;
        allowedDeclares = new EnumMap<DeclaresBelote,Boolean>(_reglesBelote.allowedDeclares);
        underTrumpFoe = _reglesBelote.underTrumpFoe;
        trumpPartner = _reglesBelote.trumpPartner;
        allowedBids = new EnumMap<BidBelote,Boolean>(_reglesBelote.allowedBids);
        dealing = _reglesBelote.dealing;
        classicCountPoints = _reglesBelote.classicCountPoints;
        nbDeals = _reglesBelote.nbDeals;
    }
    public boolean isValidRules() {
        if (mixedCards == null) {
            return false;
        }
        if (allowedDeclares == null) {
            return false;
        }
        if (trumpPartner == null) {
            return false;
        }
        if (allowedBids == null) {
            return false;
        }
        if (dealing == null) {
            return false;
        }
        for(DeclaresBelote a:DeclaresBelote.annoncesValides()){
            if (!allowedDeclares.contains(a)) {
                return false;
            }
        }
        for(BidBelote b:BidBelote.values()){
            if (!allowedBids.contains(b)) {
                return false;
            }
        }
        return true;
    }

    public boolean dealAll() {
        return dealing.getRemainingCards() == 0;
    }

    public static Numbers<Integer> getPoints() {
        Numbers<Integer> list_ = new Numbers<Integer>();
        int sum_ = HandBelote.pointsTotauxDixDeDer();
        int min_ = sum_ / 2;
        if (min_ % DIVISIONS != 0) {
            min_ = (min_ / DIVISIONS) * DIVISIONS;
        }
        while (min_ < sum_) {
            list_.add(min_);
            min_ += DIVISIONS;
        }
        list_.add(sum_);
        list_.removeDuplicates();
        return list_;
    }

    public MixCardsChoice getCartesBattues() {
        return mixedCards;
    }
    public void setCartesBattues(MixCardsChoice _distribution) {
        mixedCards = _distribution;
    }
    public EnumList<DeclaresBelote> getListeAnnoncesAutorisees() {
        EnumList<DeclaresBelote> l_;
        l_ = new EnumList<DeclaresBelote>();
        for (EntryCust<DeclaresBelote,Boolean> e: allowedDeclares.entryList()) {
            if (e.getValue()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }
    public EnumMap<DeclaresBelote,Boolean> getAnnoncesAutorisees() {
        return allowedDeclares;
    }
    public void setAnnoncesAutorisees(EnumMap<DeclaresBelote,Boolean> _annoncesAutorisees) {
        allowedDeclares = _annoncesAutorisees;
    }
    public boolean getSousCoupeAdv() {
        return underTrumpFoe;
    }
    public void setSousCoupeAdv(boolean _sousCoupeAdv) {
        underTrumpFoe = _sousCoupeAdv;
    }
    public BeloteTrumpPartner getGestionCoupePartenaire() {
        return trumpPartner;
    }
    public void setGestionCoupePartenaire(
            BeloteTrumpPartner _gestionCoupePartenaire) {
        trumpPartner = _gestionCoupePartenaire;
    }
    public EnumList<BidBelote> getListeEncheresAutorisees() {
        EnumList<BidBelote> l_;
        l_ = new EnumList<BidBelote>();
        for (EntryCust<BidBelote,Boolean> e: allowedBids.entryList()) {
            if (e.getValue()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public EnumMap<BidBelote,Boolean> getEncheresAutorisees() {
        return allowedBids;
    }

    public void setEncheresAutorisees(EnumMap<BidBelote,Boolean> _encheresAutorisees) {
        allowedBids = _encheresAutorisees;
    }


    public DealingBelote getRepartition() {
        return dealing;
    }

    public void setRepartition(DealingBelote _repartition) {
        dealing = _repartition;
    }

    public boolean getComptePointsClassique() {
        return classicCountPoints;
    }
    public void setComptePointsClassique(boolean _comptePointsClassique) {
        classicCountPoints = _comptePointsClassique;
    }

    public int getNombreParties() {
        return nbDeals;
    }
    public void setNombreParties(int _nombreParties) {
        nbDeals = _nombreParties;
    }

    public MixCardsChoice getMixedCards() {
        return mixedCards;
    }

    public void setMixedCards(MixCardsChoice _mixedCards) {
        mixedCards = _mixedCards;
    }

    public EnumMap<DeclaresBelote, Boolean> getAllowedDeclares() {
        return allowedDeclares;
    }

    public void setAllowedDeclares(EnumMap<DeclaresBelote, Boolean> _allowedDeclares) {
        allowedDeclares = _allowedDeclares;
    }

    public boolean isUnderTrumpFoe() {
        return underTrumpFoe;
    }

    public void setUnderTrumpFoe(boolean _underTrumpFoe) {
        underTrumpFoe = _underTrumpFoe;
    }

    public BeloteTrumpPartner getTrumpPartner() {
        return trumpPartner;
    }

    public void setTrumpPartner(BeloteTrumpPartner _trumpPartner) {
        trumpPartner = _trumpPartner;
    }

    public EnumMap<BidBelote, Boolean> getAllowedBids() {
        return allowedBids;
    }

    public void setAllowedBids(EnumMap<BidBelote, Boolean> _allowedBids) {
        allowedBids = _allowedBids;
    }

    public DealingBelote getDealing() {
        return dealing;
    }

    public void setDealing(DealingBelote _dealing) {
        dealing = _dealing;
    }

    public boolean isClassicCountPoints() {
        return classicCountPoints;
    }

    public void setClassicCountPoints(boolean _classicCountPoints) {
        classicCountPoints = _classicCountPoints;
    }

    public int getNbDeals() {
        return nbDeals;
    }

    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
    }
}
