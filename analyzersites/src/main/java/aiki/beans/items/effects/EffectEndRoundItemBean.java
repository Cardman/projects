package aiki.beans.items.effects;
import code.bean.Accessible;
import code.util.StringList;
import code.util.TreeMap;
import aiki.beans.CommonBean;

public class EffectEndRoundItemBean extends CommonBean {

    @Accessible
    private StringList reasons;

    @Accessible
    private TreeMap<String,String> mapVarsFail;

    @Accessible
    private int endRoundRank;

    @Accessible
    private StringList reasonsEndRound;

    @Accessible
    private TreeMap<String,String> mapVarsFailEndRound;

    @Override
    public void beforeDisplaying() {
//        DataBase data_ = (DataBase) getDataBase();
//        EffectEndRound effect_ = (EffectEndRound) getEffect();
//        endRoundRank = effect_.getEndRoundRank();
//        reasonsEndRound = EffectBean.getFormattedReasons(data_, getFailEndRoundReasons(), getLanguage());
//        mapVarsFailEndRound = EffectBean.getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
    }

//    private StringList getFailEndRoundReasons() {
//        EffectEndRound effect_ = (EffectEndRound) getEffect();
//        return EffectBean.getReasons(effect_.getFailEndRound());
//    }
}
