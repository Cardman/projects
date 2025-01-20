package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectDamage;
import code.maths.Rate;
import code.util.*;

public class EffectDamageBean extends EffectBean {
    private long chRate;
    private boolean constDamage;
    private NatStringTreeMap< Rate> damageLaw;
    private NatStringTreeMap< Rate> multDamageAgainst;
    private DictionaryComparator<Rate, Rate> chLaw;
    private LongTreeMap< Rate> hitsLaw;
    private long nbHits;
    private String power;
    private boolean randMax;
    private boolean summingUserTeamOkFighter;
    private IdList<Statistic> ignVarStatTargetPos;
    private IdList<Statistic> ignVarStatUserNeg;
    private boolean userAttack;
    private String statisAtt;
    private boolean targetDefense;
    private String statisDef;
    private DictionaryComparator<Statistic, Long> boostStatisOnceKoFoe;
    private NatStringTreeMap<String> mapVarsDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectDamage effect_ = (EffectDamage) getEffect();
        DataBase data_ = getDataBase();
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
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
        NatStringTreeMap< Rate> multDamageAgainst_;
        multDamageAgainst_ = new NatStringTreeMap< Rate>();
        for (String c: effect_.getMultDamageAgainst().getKeys()) {
            multDamageAgainst_.put(translatedCategories_.getVal(c), effect_.getMultDamageAgainst().getVal(c));
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
        IdList<Statistic> ignVarStatTargetPos_;
        ignVarStatTargetPos_ = new IdList<Statistic>();
        for (Statistic s: effect_.getIgnVarStatTargetPos()) {
            ignVarStatTargetPos_.add(s);
        }
        ignVarStatTargetPos_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
        ignVarStatTargetPos = ignVarStatTargetPos_;
        IdList<Statistic> ignVarStatUserNeg_;
        ignVarStatUserNeg_ = new IdList<Statistic>();
        for (Statistic s: effect_.getIgnVarStatUserNeg()) {
            ignVarStatUserNeg_.add(s);
        }
        ignVarStatUserNeg_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
        ignVarStatUserNeg = ignVarStatUserNeg_;
        DictionaryComparator<Statistic, Long> boostStatisOnceKoFoe_;
        boostStatisOnceKoFoe_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: effect_.getBoostStatisOnceKoFoe().getKeys()) {
            boostStatisOnceKoFoe_.put(s, effect_.getBoostStatisOnceKoFoe().getVal(s));
        }
        boostStatisOnceKoFoe = boostStatisOnceKoFoe_;
        summingUserTeamOkFighter = effect_.getSummingUserTeamOkFighter();
        randMax = effect_.getRandMax();
        mapVarsDamage = mapVarsAccuracy_;
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
        Statistic st_ = ignVarStatTargetPos.get(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
    public String getTranslatedStatisUser(int _index) {
        Statistic st_ = ignVarStatUserNeg.get(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
    public String getTranslatedStatisKo(int _index) {
        Statistic st_ = boostStatisOnceKoFoe.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
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

    public NatStringTreeMap<Rate> getDamageLaw() {
        return damageLaw;
    }

    public NatStringTreeMap<String> getMapVarsDamage() {
        return mapVarsDamage;
    }

    public NatStringTreeMap<Rate> getMultDamageAgainst() {
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

    public IdList<Statistic> getIgnVarStatTargetPos() {
        return ignVarStatTargetPos;
    }

    public IdList<Statistic> getIgnVarStatUserNeg() {
        return ignVarStatUserNeg;
    }

    public boolean getRandMax() {
        return randMax;
    }

    public DictionaryComparator<Statistic,Long> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }

    public boolean getSummingUserTeamOkFighter() {
        return summingUserTeamOkFighter;
    }
}