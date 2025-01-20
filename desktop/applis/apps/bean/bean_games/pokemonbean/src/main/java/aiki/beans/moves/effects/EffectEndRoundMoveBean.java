package aiki.beans.moves.effects;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectEndRound;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class EffectEndRoundMoveBean extends EffectBean {
    private long endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        EffectEndRound effect_ = (EffectEndRound) getEffect();
        endRoundRank = effect_.getEndRoundRank();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(EAMP, E_AMP);
//        loc_.put(EGT, E_GT);
//        loc_.put(ELT, E_LT);
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//        reasons_ = new StringList();
//        for (String f: getFailEndRoundReasons()) {
//            String formula_ = data_.getFormula(f, getLanguage());
////            formula_ = StringList.replace(formula_, loc_);
////            formula_ = formula_.replace(EAMP, E_AMP);
////            formula_ = formula_.replace(EGT, E_GT);
////            formula_ = formula_.replace(ELT, E_LT);
////            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
////            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            reasons_.add(formula_);
//        }
        reasonsEndRound = CommonBean.getFormattedReasons(data_, getFailEndRoundReasons(), getLanguage());
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getFailEndRound(),getLanguage());
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailEndRound = mapVarsFail_;
    }

    private StringList getFailEndRoundReasons() {
        EffectEndRound effect_ = (EffectEndRound) getEffect();
        return getReasons(effect_.getFailEndRound());
    }

    public long getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
    }
}