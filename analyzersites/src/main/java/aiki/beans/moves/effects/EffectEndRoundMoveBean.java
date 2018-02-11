package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.util.NatTreeMap;
import code.util.StringList;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.fight.moves.effects.EffectEndRound;

public class EffectEndRoundMoveBean extends EffectBean {

    @Accessible
    private int endRoundRank;

    @Accessible
    private StringList reasonsEndRound;

    @Accessible
    private NatTreeMap<String,String> mapVarsFailEndRound;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        EffectEndRound effect_ = (EffectEndRound) getEffect();
        endRoundRank = effect_.getEndRoundRank();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(EAMP, E_AMP);
//        loc_.put(EGT, E_GT);
//        loc_.put(ELT, E_LT);
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_ = CommonBean.getFormattedReasons(data_, getFailEndRoundReasons(), getLanguage());
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
        reasonsEndRound = reasons_;
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(effect_.getFailEndRound(),getLanguage());
        NatTreeMap<String,String> mapVarsFail_ = new NatTreeMap<String,String>();
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
}
