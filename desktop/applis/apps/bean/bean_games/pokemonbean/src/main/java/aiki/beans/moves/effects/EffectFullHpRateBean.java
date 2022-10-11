package aiki.beans.moves.effects;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectFullHpRate;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class EffectFullHpRateBean extends EffectBean {
    private Rate leftUserHp;
    private String restoredHp;
    private Rate closestFoeDamageRateHp;
    private NatStringTreeMap<String> mapVarsRestored;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        EffectFullHpRate effect_ = (EffectFullHpRate) getEffect();
        leftUserHp = effect_.getLeftUserHp();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        restoredHp = data_.getFormula(effect_.getRestoredHp(),getLanguage());
//        restoredHp = StringList.replace(restoredHp, loc_);
//        restoredHp = restoredHp.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        restoredHp = restoredHp.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getRestoredHp(),getLanguage());
        NatStringTreeMap<String> mapVarsAccuracy_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsAccuracy_.put(k, mapVars_.getVal(k));
        }
        mapVarsRestored = mapVarsAccuracy_;
        closestFoeDamageRateHp = effect_.getClosestFoeDamageRateHp();
    }

    public Rate getLeftUserHp() {
        return leftUserHp;
    }

    public String getRestoredHp() {
        return restoredHp;
    }

    public NatStringTreeMap<String> getMapVarsRestored() {
        return mapVarsRestored;
    }

    public Rate getClosestFoeDamageRateHp() {
        return closestFoeDamageRateHp;
    }
}