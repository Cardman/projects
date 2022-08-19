package cards.belote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.RulesCommon;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Ints;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;


public final class RulesBelote {

    public static final int DIVISIONS = 10;

    private RulesCommon common = new RulesCommon();
    private EnumMap<DeclaresBelote,BoolVal> allowedDeclares = new EnumMap<DeclaresBelote,BoolVal>();
    private boolean underTrumpFoe;
    private BeloteTrumpPartner trumpPartner=BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP;
    private EnumMap<BidBelote,BoolVal> allowedBids = new EnumMap<BidBelote,BoolVal>();
    private DealingBelote dealing = DealingBelote.CLASSIC_2_VS_2;
    private boolean classicCountPoints=true;

    public RulesBelote() {
        for(DeclaresBelote a:DeclaresBelote.annoncesValides()){
            allowedDeclares.put(a, BoolVal.FALSE);
        }
        for(BidBelote e:BidBelote.values()){
            allowedBids.put(e, ComparatorBoolean.of(e.getToujoursPossibleAnnoncer()));
        }
    }

    public RulesBelote(RulesBelote _reglesBelote) {
        common = new RulesCommon(_reglesBelote.common);
        allowedDeclares = new EnumMap<DeclaresBelote,BoolVal>(_reglesBelote.allowedDeclares);
        underTrumpFoe = _reglesBelote.underTrumpFoe;
        trumpPartner = _reglesBelote.trumpPartner;
        allowedBids = new EnumMap<BidBelote,BoolVal>(_reglesBelote.allowedBids);
        dealing = _reglesBelote.dealing;
        classicCountPoints = _reglesBelote.classicCountPoints;
    }
    public boolean isValidRules() {
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

    public EnumList<DeclaresBelote> getListeAnnoncesAutorisees() {
        EnumList<DeclaresBelote> l_;
        l_ = new EnumList<DeclaresBelote>();
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
    public EnumList<BidBelote> getListeEncheresAutorisees() {
        EnumList<BidBelote> l_;
        l_ = new EnumList<BidBelote>();
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

    public EnumMap<DeclaresBelote, BoolVal> getAllowedDeclares() {
        return allowedDeclares;
    }

    public void setAllowedDeclares(EnumMap<DeclaresBelote, BoolVal> _allowedDeclares) {
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

    public EnumMap<BidBelote, BoolVal> getAllowedBids() {
        return allowedBids;
    }

    public void setAllowedBids(EnumMap<BidBelote, BoolVal> _allowedBids) {
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
