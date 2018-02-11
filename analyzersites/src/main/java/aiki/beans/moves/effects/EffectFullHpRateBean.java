package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.NatTreeMap;
import code.util.StringList;
import aiki.DataBase;
import aiki.fight.moves.effects.EffectFullHpRate;

public class EffectFullHpRateBean extends EffectBean {

    @Accessible
    private Rate leftUserHp;

    @Accessible
    private String restoredHp;

    @Accessible
    private Rate closestFoeDamageRateHp;

    @Accessible
    private NatTreeMap<String,String> mapVarsRestored;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        EffectFullHpRate effect_ = (EffectFullHpRate) getEffect();
        leftUserHp = effect_.getLeftUserHp();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        restoredHp = data_.getFormula(effect_.getRestoredHp(),getLanguage());
//        restoredHp = StringList.replace(restoredHp, loc_);
//        restoredHp = restoredHp.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        restoredHp = restoredHp.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(effect_.getRestoredHp(),getLanguage());
        NatTreeMap<String,String> mapVarsAccuracy_ = new NatTreeMap<String,String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsAccuracy_.put(k, mapVars_.getVal(k));
        }
        mapVarsRestored = mapVarsAccuracy_;
        closestFoeDamageRateHp = effect_.getClosestFoeDamageRateHp();
    }
}
