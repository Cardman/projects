package aiki.beans.effects;

import aiki.beans.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.maths.*;
import code.util.*;

public class EffectWhileSendingBean extends CommonBean {
    private EffectWhileSendingWithStatistic effect;
    private final EffectStatisticCommon effectStatisticCommon = new EffectStatisticCommon();
    private boolean disableWeather;
    private TranslatedKey enabledWeather;
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
        enabledWeather = buildMv(getFacade(),effectSend_.getEnabledWeather());
        multWeight = effectSend_.getMultWeight();
        EffectStatistic effect_ = effectSend_.getEffect();
        statistic = effectSend_.isWithEffect();
        effectStatisticCommon.init(getFacade(),getLanguage(),effect_, statistic);
        if (statistic) {
            reasons = getFormattedReasons(getDataBase(), effect_.getFail(), getLanguage());
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
        return enabledWeather.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(enabledWeather);
    }
    public String clickWeather() {
        return tryRedirect(enabledWeather);
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

    public TranslatedKey getEnabledWeather() {
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