package aiki.beans.effects;

import aiki.beans.CommonBean;
import aiki.beans.EffectStatisticCommon;
import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.moves.effects.EffectStatistic;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;

public class EffectWhileSendingBean extends CommonBean {
    private EffectWhileSendingWithStatistic effect;
    private final EffectStatisticCommon effectStatisticCommon = new EffectStatisticCommon();
    private boolean disableWeather;
    private String enabledWeather;
    private boolean copyingAbility;
    private boolean plate;
    private boolean statistic;
    private Rate multWeight;
    private StringList reasons;
    private NatStringTreeMap<String> mapVarsFail;

    @Override
    public void beforeDisplaying() {
        if (effect == null) {
            disableWeather = false;
            enabledWeather="";
            copyingAbility=false;
            multWeight = Rate.zero();
            reasons = new StringList();
            mapVarsFail = new NatStringTreeMap<String>();
            return;
        }
        disableWeather = effect.getDisableWeather();
        copyingAbility = effect.getCopyingAbility();
        enabledWeather = effect.getEnabledWeather();
        multWeight = effect.getMultWeight();
        EffectWhileSendingWithStatistic effectSend_ = effect;
        EffectStatistic effect_ = effectSend_.getEffect();
        statistic = effect_ != null;
        effectStatisticCommon.init(getDataBase(),getLanguage(),effect_);
        if (effect_ != null) {
            reasons = getFormattedReasons(getDataBase(), getReasons(effect_.getFail()), getLanguage());
            mapVarsFail = getMapVarsFail(getDataBase(), effect_.getFail(), getLanguage());
        } else {
            reasons = new StringList();
            mapVarsFail = new NatStringTreeMap<String>();
        }
    }
    public String getTrWeather() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(enabledWeather);
    }
    public String clickWeather() {
        getForms().put(CST_MOVE, enabledWeather);
        return CST_MOVE;
    }

    public EffectStatisticCommon getEffectStatisticCommon() {
        return effectStatisticCommon;
    }

    public void setEffect(EffectWhileSendingWithStatistic _effect) {
        effect = _effect;
    }

    public boolean getDisableWeather() {
        return disableWeather;
    }

    public String getEnabledWeather() {
        return enabledWeather;
    }

    public boolean getCopyingAbility() {
        return copyingAbility;
    }

    public boolean getPlate() {
        return plate;
    }

    public Rate getMultWeight() {
        return multWeight;
    }

    public boolean getStatistic() {
        return statistic;
    }

    public StringList getReasons() {
        return reasons;
    }

    public NatStringTreeMap<String> getMapVarsFail() {
        return mapVarsFail;
    }

}