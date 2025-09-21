package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.ComparingTranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectDamage;
import code.maths.Rate;
import code.scripts.pages.aiki.*;
import code.util.*;

public class EffectDamageBean extends EffectBean {
    private long chRate;
    private boolean constDamage;
    private NatStringTreeMap< Rate> damageLaw;
    private DictionaryComparator<TranslatedKey,Rate> multDamageAgainst;
    private DictionaryComparator<Rate, Rate> chLaw;
    private LongTreeMap< Rate> hitsLaw;
    private long nbHits;
    private String power;
    private boolean randMax;
    private boolean summingUserTeamOkFighter;
    private CustList<TranslatedKey> ignVarStatTargetPos;
    private CustList<TranslatedKey> ignVarStatUserNeg;
    private boolean userAttack;
    private String statisAtt;
    private boolean targetDefense;
    private String statisDef;
    private DictionaryComparator<TranslatedKey, Long> boostStatisOnceKoFoe;
    private NatStringTreeMap<String> mapVarsDamage;
    private Rate closestFoeDamageRateHp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectDamage effect_ = (EffectDamage) getEffect();
        DataBase data_ = getDataBase();
        constDamage = effect_.getConstDamage();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        power = data_.getFormula(effect_.getPower(),getLanguage());
//        power = StringList.replace(power, loc_);
//        power = power.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        power = power.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getPower(),getLanguage());
        NatStringTreeMap<String> mapVarsAccuracy_ = new NatStringTreeMap< String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsAccuracy_.put(k, mapVars_.getVal(k));
        }
        DictionaryComparator<TranslatedKey,Rate> multDamageAgainst_;
        multDamageAgainst_ = new DictionaryComparator<TranslatedKey,Rate>(new ComparingTranslatedKey());
        for (String c: effect_.getMultDamageAgainst().getKeys()) {
            multDamageAgainst_.put(buildCa(getFacade(), c), effect_.getMultDamageAgainst().getVal(c));
        }
        multDamageAgainst = multDamageAgainst_;
        NatStringTreeMap< Rate> damageLaw_;
        damageLaw_ = new NatStringTreeMap< Rate>();
        for (String e: effect_.getDamageLaw().eventsDiff()) {
            String formula_ = data_.getFormula(e, getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            NatStringTreeMap<String> mapVarsLoc_ = data_.getDescriptions(e,getLanguage());
            desc_ = new StringList(mapVarsLoc_.getKeys());
            desc_.sort();
            for (String k: desc_) {
                mapVarsAccuracy_.put(k, mapVarsLoc_.getVal(k));
            }
            damageLaw_.put(formula_, effect_.getDamageLaw().normalizedRate(e));
        }
        damageLaw = damageLaw_;
        if (hasDeterminatedLawForDamage()) {
            power = data_.getFormula(effect_.getDamageLaw().eventsDiff().first(),getLanguage());
//            power = StringList.replace(power, loc_);
//            power = power.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            power = power.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            damageLaw.clear();
        }
        LongTreeMap< Rate> hitsLaw_;
        hitsLaw_ = new LongTreeMap< Rate>();
        for (Rate e: effect_.getHitsLaw().eventsDiff()) {
            hitsLaw_.put(e.ll(), effect_.getHitsLaw().normalizedRate(e));
        }
        hitsLaw = hitsLaw_;
        if (hitsLaw.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            nbHits = hitsLaw.firstKey();
            hitsLaw = new LongTreeMap< Rate>();
        }
        chRate = effect_.getChRate();
        DictionaryComparator<Rate, Rate> chLaw_;
        chLaw_ = DictionaryComparatorUtil.buildRateRate();
        for (Rate e: effect_.getChLaw().eventsDiff()) {
            chLaw_.put(e, effect_.getChLaw().normalizedRate(e));
        }
        chLaw = chLaw_;
        userAttack = effect_.isUserAttack();
        targetDefense = effect_.isTargetDefense();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        statisAtt = translatedStatistics_.getVal(effect_.getStatisAtt());
        statisDef = translatedStatistics_.getVal(effect_.getStatisDef());
        CustList<TranslatedKey> ignVarStatTargetPos_;
        ignVarStatTargetPos_ = new CustList<TranslatedKey>();
        for (Statistic s: effect_.getIgnVarStatTargetPos()) {
            ignVarStatTargetPos_.add(buildSi(getFacade(),s));
        }
        ignVarStatTargetPos_.sortElts(new ComparingTranslatedKey());
        ignVarStatTargetPos = ignVarStatTargetPos_;
        CustList<TranslatedKey> ignVarStatUserNeg_;
        ignVarStatUserNeg_ = new CustList<TranslatedKey>();
        for (Statistic s: effect_.getIgnVarStatUserNeg()) {
            ignVarStatUserNeg_.add(buildSi(getFacade(),s));
        }
        ignVarStatUserNeg_.sortElts(new ComparingTranslatedKey());
        ignVarStatUserNeg = ignVarStatUserNeg_;
        DictionaryComparator<TranslatedKey, Long> boostStatisOnceKoFoe_;
        boostStatisOnceKoFoe_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: effect_.getBoostStatisOnceKoFoe().getKeys()) {
            boostStatisOnceKoFoe_.put(buildSi(getFacade(),s), effect_.getBoostStatisOnceKoFoe().getVal(s));
        }
        boostStatisOnceKoFoe = boostStatisOnceKoFoe_;
        summingUserTeamOkFighter = effect_.getSummingUserTeamOkFighter();
        randMax = effect_.getRandMax();
        mapVarsDamage = mapVarsAccuracy_;
        closestFoeDamageRateHp = effect_.getClosestFoeDamageRateHp();
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_EFFECT);
    }

    @Override
    public void buildSubEff() {
        displayEmpty(getHitsLaw(), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_HIT_LAW_CONST,Long.toString(getNbHits()));
        new BeanDisplayMap<Long,Rate>(new BeanDisplayLong(),new BeanDisplayRate()).displayGrid(this, getHitsLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_HIT_LAW,MessagesDataEffdamage.M_P_45_EVENT_NB_HITS,MessagesDataEffdamage.M_P_45_RATE_EVENT);
        displayBoolTrue(toInt(getConstDamage()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_CONST_DAMAGE,getPower());
        int condDet_ = toInt(hasLawForDamage())*toInt(!getConstDamage());
//        if (hasLawForDamage()) {
        if (condDet_ == TRUE_VALUE) {
            displayBoolTrue(toInt(hasDeterminatedLawForDamage()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_DAMAG_LAW_CONST,getPower());
            if (!hasDeterminatedLawForDamage()) {
                new BeanDisplayMap<String,Rate>(new BeanDisplayString(),new BeanDisplayRate()).displayGrid(this, getDamageLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_DAMAG_LAW,MessagesDataEffdamage.M_P_45_EVENT,MessagesDataEffdamage.M_P_45_RATE_EVENT);
            }
            mapVarsInit(getMapVarsDamage());
        }
        if (!getConstDamage()) {
//            if (hasLawForDamage()) {
//                displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(hasDeterminatedLawForDamage()),MessagesDataEffdamage.M_P_45_DAMAG_LAW_CONST,getPower());
//                if (!hasDeterminatedLawForDamage()) {
//                    new BeanDisplayMap<String,Rate>(new BeanDisplayString(),new BeanDisplayRate()).displayGrid(this, getDamageLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_DAMAG_LAW,MessagesDataEffdamage.M_P_45_EVENT,MessagesDataEffdamage.M_P_45_RATE_EVENT);
//                }
//                mapVarsInit(getMapVarsDamage());
//            }
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(), new BeanDisplayRate()).displayGrid(this, getMultDamageAgainst(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_DAMAGE_MULT_COUNTER,MessagesDataEffdamage.M_P_45_CATEGORY,MessagesDataEffdamage.M_P_45_RATE);
//            if (constPower()) {
//                displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(hasConstPower()),MessagesDataEffdamage.M_P_45_CONST_POWER,MessagesDataEffdamage.M_P_45_VAR_POWER,getPower());
//                mapVarsInit(getMapVarsDamage());
//                formatMessage(MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_RATE,Long.toString(getChRate()));
//                new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).displayGrid(this, getChLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_LAW,MessagesDataEffdamage.M_P_45_EVENT_RATE,MessagesDataEffdamage.M_P_45_RATE);
//                displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(getUserAttack()),MessagesDataEffdamage.M_P_45_ATTACK_USER,MessagesDataEffdamage.M_P_45_ATTACK_TARGET,getStatisAtt());
//                displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(getTargetDefense()),MessagesDataEffdamage.M_P_45_DEFENSE_TARGET,MessagesDataEffdamage.M_P_45_DEFENSE_USER,getStatisDef());
//                new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getIgnVarStatTargetPos(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_POS_STAT);
//                new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getIgnVarStatUserNeg(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_NEG_STAT);
//                displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(getRandMax()),MessagesDataEffdamage.M_P_45_RAND_MAX);
//            }
        }
        int cond_ = toInt(constPower())*toInt(!getConstDamage());
        if (cond_ == TRUE_VALUE) {
            displayBoolFull(toInt(hasConstPower()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_CONST_POWER,MessagesDataEffdamage.M_P_45_VAR_POWER,getPower());
            mapVarsInit(getMapVarsDamage());
            formatMessage(MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_RATE,Long.toString(getChRate()));
            new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).displayGrid(this, getChLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_LAW,MessagesDataEffdamage.M_P_45_EVENT_RATE,MessagesDataEffdamage.M_P_45_RATE);
            displayBoolFull(toInt(getUserAttack()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_ATTACK_USER,MessagesDataEffdamage.M_P_45_ATTACK_TARGET,getStatisAtt());
            displayBoolFull(toInt(getTargetDefense()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_DEFENSE_TARGET,MessagesDataEffdamage.M_P_45_DEFENSE_USER,getStatisDef());
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getIgnVarStatTargetPos(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_POS_STAT);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getIgnVarStatUserNeg(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_NEG_STAT);
            displayBoolTrue(toInt(getRandMax()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_RAND_MAX);
        }
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this, getBoostStatisOnceKoFoe(), MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_BOOST_STATIS_ONCE_KO_FOE, MessagesDataEffdamage.M_P_45_STATISTIC, MessagesDataEffdamage.M_P_45_BOOST);
        displayBoolTrue(toInt(getSummingUserTeamOkFighter()), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_SUMMING_TEAM);
        displayIntDef(getClosestFoeDamageRateHp(), MessagesPkBean.EFF_DAMAGE, MessagesDataEffdamage.M_P_45_CLOSEST_FOE_DAMAGE_RATE_HP);
    }
    public boolean hasLawForDamage() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        return !effect_.getDamageLaw().isEmpty();
    }
    public boolean hasDeterminatedLawForDamage() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        return effect_.getDamageLaw().eventsDiff().size() == DataBase.ONE_POSSIBLE_CHOICE;
    }
    public boolean counterDamageCat() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        if (!effect_.getDamageLaw().isEmpty()) {
            return false;
        }
        return !effect_.getMultDamageAgainst().isEmpty();
    }
    public boolean constPower() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        if (!effect_.getDamageLaw().isEmpty()) {
            return false;
        }
        if (!effect_.getMultDamageAgainst().isEmpty()) {
            return false;
        }
        return !power.isEmpty();
    }
    public boolean hasConstPower() {
        return Rate.isValid(power);
    }
    public String getTranslatedStatisTarget(int _index) {
        return ignVarStatTargetPos.get(_index).getTranslation();
//        Statistic st_ = ignVarStatTargetPos.get(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(st_);
    }
    public String getTranslatedStatisUser(int _index) {
        return ignVarStatUserNeg.get(_index).getTranslation();
//        Statistic st_ = ignVarStatUserNeg.get(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(st_);
    }
    public String getTranslatedStatisKo(int _index) {
        return boostStatisOnceKoFoe.getKey(_index).getTranslation();
//        Statistic st_ = boostStatisOnceKoFoe.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(st_);
    }

    public LongTreeMap<Rate> getHitsLaw() {
        return hitsLaw;
    }

    public long getNbHits() {
        return nbHits;
    }

    public boolean getConstDamage() {
        return constDamage;
    }

    public String getPower() {
        return power;
    }

    public Rate getClosestFoeDamageRateHp() {
        return closestFoeDamageRateHp;
    }

    public NatStringTreeMap<Rate> getDamageLaw() {
        return damageLaw;
    }

    public NatStringTreeMap<String> getMapVarsDamage() {
        return mapVarsDamage;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultDamageAgainst() {
        return multDamageAgainst;
    }

    public long getChRate() {
        return chRate;
    }

    public DictionaryComparator<Rate,Rate> getChLaw() {
        return chLaw;
    }

    public boolean getUserAttack() {
        return userAttack;
    }

    public String getStatisAtt() {
        return statisAtt;
    }

    public boolean getTargetDefense() {
        return targetDefense;
    }

    public String getStatisDef() {
        return statisDef;
    }

    public CustList<TranslatedKey> getIgnVarStatTargetPos() {
        return ignVarStatTargetPos;
    }

    public CustList<TranslatedKey> getIgnVarStatUserNeg() {
        return ignVarStatUserNeg;
    }

    public boolean getRandMax() {
        return randMax;
    }

    public DictionaryComparator<TranslatedKey,Long> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }

    public boolean getSummingUserTeamOkFighter() {
        return summingUserTeamOkFighter;
    }
}