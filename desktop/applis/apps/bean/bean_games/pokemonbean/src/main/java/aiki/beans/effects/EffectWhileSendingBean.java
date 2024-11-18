package aiki.beans.effects;

import aiki.beans.CommonBean;
import aiki.beans.EffectStatisticCommon;
import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.instances.Instances;
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
    private boolean statistic;
    private Rate multWeight;
    private StringList reasons;
    private NatStringTreeMap<String> mapVarsFail;

    @Override
    public void beforeDisplaying() {
        EffectWhileSendingWithStatistic effectSend_ = patch(effect);
        disableWeather = effectSend_.getDisableWeather();
        copyingAbility = effectSend_.getCopyingAbility();
        enabledWeather = effectSend_.getEnabledWeather();
        multWeight = effectSend_.getMultWeight();
        EffectStatistic effect_ = effectSend_.getEffect();
        statistic = effectSend_.isWithEffect();
        effectStatisticCommon.init(getDataBase(),getLanguage(),effect_, statistic);
        if (statistic) {
            reasons = getFormattedReasons(getDataBase(), getReasons(effect_.getFail()), getLanguage());
            mapVarsFail = getMapVarsFail(getDataBase(), effect_.getFail(), getLanguage());
        } else {
            reasons = new StringList();
            mapVarsFail = new NatStringTreeMap<String>();
        }
    }
    public static EffectWhileSendingWithStatistic patch(EffectWhileSendingWithStatistic _eff) {
        if (_eff == null) {
            return Instances.newEffectWhileSendingSimple();
        }
        return _eff;
    }
    public String getTrWeather() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(enabledWeather);
    }
    public String clickWeather() {
        return tryRedirectMv(enabledWeather);
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