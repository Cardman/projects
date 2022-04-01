package aiki.beans.moves.effects;

import aiki.beans.facade.comparators.ComparatorTrStringStatistic;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectDamage;
import code.maths.ComparatorRate;
import code.maths.Rate;
import code.util.*;

public class EffectDamageBean extends EffectBean {
    private byte chRate;
    private boolean constDamage;
    private NatStringTreeMap< Rate> damageLaw;
    private NatStringTreeMap< Rate> multDamageAgainst;
    private TreeMap<Rate, Rate> chLaw;
    private LongTreeMap< Rate> hitsLaw;
    private long nbHits;
    private String power;
    private boolean randMax;
    private boolean summingUserTeamOkFighter;
    private EnumList<Statistic> ignVarStatTargetPos;
    private EnumList<Statistic> ignVarStatUserNeg;
    private boolean userAttack;
    private String statisAtt;
    private boolean targetDefense;
    private String statisDef;
    private TreeMap<Statistic, Byte> boostStatisOnceKoFoe;
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
        for (String e: effect_.getDamageLaw().events()) {
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
            power = data_.getFormula(effect_.getDamageLaw().events().first(),getLanguage());
//            power = StringList.replace(power, loc_);
//            power = power.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            power = power.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            damageLaw.clear();
        }
        LongTreeMap< Rate> hitsLaw_;
        hitsLaw_ = new LongTreeMap< Rate>();
        for (Rate e: effect_.getHitsLaw().events()) {
            hitsLaw_.put(e.ll(), effect_.getHitsLaw().normalizedRate(e));
        }
        hitsLaw = hitsLaw_;
        if (hitsLaw.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            nbHits = hitsLaw.firstKey();
            hitsLaw = new LongTreeMap< Rate>();
        }
        chRate = effect_.getChRate();
        TreeMap<Rate, Rate> chLaw_;
        chLaw_ = new TreeMap<Rate, Rate>(new ComparatorRate());
        for (Rate e: effect_.getChLaw().events()) {
            chLaw_.put(e, effect_.getChLaw().normalizedRate(e));
        }
        chLaw = chLaw_;
        userAttack = effect_.isUserAttack();
        targetDefense = effect_.isTargetDefense();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        statisAtt = translatedStatistics_.getVal(effect_.getStatisAtt());
        statisDef = translatedStatistics_.getVal(effect_.getStatisDef());
        EnumList<Statistic> ignVarStatTargetPos_;
        ignVarStatTargetPos_ = new EnumList<Statistic>();
        for (Statistic s: effect_.getIgnVarStatTargetPos()) {
            ignVarStatTargetPos_.add(s);
        }
        ignVarStatTargetPos_.sortElts(new ComparatorTrStringStatistic(translatedStatistics_));
        ignVarStatTargetPos = ignVarStatTargetPos_;
        EnumList<Statistic> ignVarStatUserNeg_;
        ignVarStatUserNeg_ = new EnumList<Statistic>();
        for (Statistic s: effect_.getIgnVarStatUserNeg()) {
            ignVarStatUserNeg_.add(s);
        }
        ignVarStatUserNeg_.sortElts(new ComparatorTrStringStatistic(translatedStatistics_));
        ignVarStatUserNeg = ignVarStatUserNeg_;
        TreeMap<Statistic, Byte> boostStatisOnceKoFoe_;
        boostStatisOnceKoFoe_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
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
        return !effect_.getDamageLaw().events().isEmpty();
    }
    public boolean hasDeterminatedLawForDamage() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        return effect_.getDamageLaw().events().size() == DataBase.ONE_POSSIBLE_CHOICE;
    }
    public boolean counterDamageCat() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        if (!effect_.getDamageLaw().events().isEmpty()) {
            return false;
        }
        return !effect_.getMultDamageAgainst().isEmpty();
    }
    public boolean constPower() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        if (!effect_.getDamageLaw().events().isEmpty()) {
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
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
    public String getTranslatedStatisUser(int _index) {
        Statistic st_ = ignVarStatUserNeg.get(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
    public String getTranslatedStatisKo(int _index) {
        Statistic st_ = boostStatisOnceKoFoe.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
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

    public byte getChRate() {
        return chRate;
    }

    public TreeMap<Rate,Rate> getChLaw() {
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

    public EnumList<Statistic> getIgnVarStatTargetPos() {
        return ignVarStatTargetPos;
    }

    public EnumList<Statistic> getIgnVarStatUserNeg() {
        return ignVarStatUserNeg;
    }

    public boolean getRandMax() {
        return randMax;
    }

    public TreeMap<Statistic,Byte> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }

    public boolean getSummingUserTeamOkFighter() {
        return summingUserTeamOkFighter;
    }
}