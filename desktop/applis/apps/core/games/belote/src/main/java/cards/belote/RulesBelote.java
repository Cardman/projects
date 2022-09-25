package cards.belote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.RulesCommon;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;


public final class RulesBelote {

    public static final int DIVISIONS = 10;

    private RulesCommon common = new RulesCommon();
    private IdMap<DeclaresBelote,BoolVal> allowedDeclares = new IdMap<DeclaresBelote,BoolVal>();
    private boolean underTrumpFoe;
    private BeloteTrumpPartner trumpPartner=BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP;
    private IdMap<BidBelote,BoolVal> allowedBids = new IdMap<BidBelote,BoolVal>();
    private DealingBelote dealing = DealingBelote.CLASSIC_2_VS_2;
    private boolean classicCountPoints=true;

    public RulesBelote() {
        for(DeclaresBelote a:DeclaresBelote.annoncesValides()){
            allowedDeclares.put(a, BoolVal.FALSE);
        }
        for(BidBelote e:BidBelote.all()){
            allowedBids.put(e, ComparatorBoolean.of(e.getToujoursPossibleAnnoncer()));
        }
    }

    public RulesBelote(RulesBelote _reglesBelote) {
        common = new RulesCommon(_reglesBelote.common);
        allowedDeclares = new IdMap<DeclaresBelote,BoolVal>(_reglesBelote.allowedDeclares);
        underTrumpFoe = _reglesBelote.underTrumpFoe;
        trumpPartner = _reglesBelote.trumpPartner;
        allowedBids = new IdMap<BidBelote,BoolVal>(_reglesBelote.allowedBids);
        dealing = _reglesBelote.dealing;
        classicCountPoints = _reglesBelote.classicCountPoints;
    }

    public void allowBids(CustList<BidBelote> _bids) {
        IdMap<BidBelote,BoolVal> contrats_ = new IdMap<BidBelote,BoolVal>();
        for (BidBelote b: getAllowedBids().getKeys()) {
            contrats_.put(b, ComparatorBoolean.of(b.getToujoursPossibleAnnoncer()));
        }
        contrats_.put(BidBelote.NO_TRUMP,BoolVal.FALSE);
        contrats_.put(BidBelote.ALL_TRUMP,BoolVal.FALSE);
//        if(!BidBelote.NO_TRUMP.getToujoursPossibleAnnoncer()) {
//            contrats_.put(BidBelote.NO_TRUMP,BoolVal.FALSE);
//        }
//        if(!BidBelote.ALL_TRUMP.getToujoursPossibleAnnoncer()) {
//            contrats_.put(BidBelote.ALL_TRUMP,BoolVal.FALSE);
//        }
        for (BidBelote b: _bids) {
            contrats_.put(b,BoolVal.TRUE);
        }
        setAllowedBids(contrats_);
    }

    public boolean isValidRules() {
        for(DeclaresBelote a:DeclaresBelote.annoncesValides()){
            if (!allowedDeclares.contains(a)) {
                return false;
            }
        }
        for(BidBelote b:BidBelote.all()){
            if (!allowedBids.contains(b)) {
                return false;
            }
        }
        return true;
    }

    public boolean dealAll() {
        return dealing.getRemainingCards() == 0;
    }

    public static Ints getPoints() {
        Ints list_ = new Ints();
        int sum_ = HandBelote.pointsTotauxDixDeDer();
        int min_ = sum_ / 2;
        min_ = (min_ / DIVISIONS) * DIVISIONS;
        while (min_ < sum_) {
            list_.add(min_);
            min_ += DIVISIONS;
        }
        list_.add(sum_);
        return list_;
    }

    public IdList<DeclaresBelote> getListeAnnoncesAutorisees() {
        IdList<DeclaresBelote> l_;
        l_ = new IdList<DeclaresBelote>();
        for (EntryCust<DeclaresBelote,BoolVal> e: allowedDeclares.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public boolean getSousCoupeAdv() {
        return isUnderTrumpFoe();
    }
    public void setSousCoupeAdv(boolean _sousCoupeAdv) {
        setUnderTrumpFoe(_sousCoupeAdv);
    }
    public BeloteTrumpPartner getGestionCoupePartenaire() {
        return getTrumpPartner();
    }
    public void setGestionCoupePartenaire(
            BeloteTrumpPartner _gestionCoupePartenaire) {
        setTrumpPartner(_gestionCoupePartenaire);
    }
    public IdList<BidBelote> getListeEncheresAutorisees() {
        IdList<BidBelote> l_;
        l_ = new IdList<BidBelote>();
        for (EntryCust<BidBelote,BoolVal> e: allowedBids.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }


    public boolean getComptePointsClassique() {
        return isClassicCountPoints();
    }
    public void setComptePointsClassique(boolean _comptePointsClassique) {
        setClassicCountPoints(_comptePointsClassique);
    }

    public RulesCommon getCommon() {
        return common;
    }

    public IdMap<DeclaresBelote, BoolVal> getAllowedDeclares() {
        return allowedDeclares;
    }

    public void setAllowedDeclares(IdMap<DeclaresBelote, BoolVal> _allowedDeclares) {
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

    public IdMap<BidBelote, BoolVal> getAllowedBids() {
        return allowedBids;
    }

    public void setAllowedBids(IdMap<BidBelote, BoolVal> _allowedBids) {
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

}
