package aiki.beans.moves.effects;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectDamage;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectDamageBean extends EffectBean {

    @Accessible
    private byte chRate;

    @Accessible
    private boolean constDamage;

    @Accessible
    private NatTreeMap<String, Rate> damageLaw;

    @Accessible
    private NatTreeMap<String, Rate> multDamageAgainst;

    @Accessible
    private NatCmpTreeMap<Rate, Rate> chLaw;

    @Accessible
    private NatTreeMap<Long, Rate> hitsLaw;

    @Accessible
    private long nbHits;

    @Accessible
    private String power;

    @Accessible
    private boolean randMax;

    @Accessible
    private boolean summingUserTeamOkFighter;

    @Accessible
    private EnumList<Statistic> ignVarStatTargetPos;

    @Accessible
    private EnumList<Statistic> ignVarStatUserNeg;

    @Accessible
    private boolean userAttack;

    @Accessible
    private String statisAtt;

    @Accessible
    private boolean targetDefense;

    @Accessible
    private String statisDef;

    @Accessible
    private TreeMap<Statistic, Byte> boostStatisOnceKoFoe;

    @Accessible
    private NatTreeMap<String,String> mapVarsDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectDamage effect_ = (EffectDamage) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        constDamage = effect_.getConstDamage();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        power = data_.getFormula(effect_.getPower(),getLanguage());
//        power = StringList.replace(power, loc_);
//        power = power.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        power = power.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(effect_.getPower(),getLanguage());
        NatTreeMap<String,String> mapVarsAccuracy_ = new NatTreeMap<String, String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsAccuracy_.put(k, mapVars_.getVal(k));
        }
        NatTreeMap<String, Rate> multDamageAgainst_;
        multDamageAgainst_ = new NatTreeMap<String, Rate>();
        for (String c: effect_.getMultDamageAgainst().getKeys()) {
            multDamageAgainst_.put(translatedCategories_.getVal(c), effect_.getMultDamageAgainst().getVal(c));
        }
        multDamageAgainst = multDamageAgainst_;
        NatTreeMap<String, Rate> damageLaw_;
        damageLaw_ = new NatTreeMap<String, Rate>();
        for (String e: effect_.getDamageLaw().events()) {
            String formula_ = data_.getFormula(e, getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            NatTreeMap<String,String> mapVarsLoc_ = data_.getDescriptions(e,getLanguage());
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
        NatTreeMap<Long, Rate> hitsLaw_;
        hitsLaw_ = new NatTreeMap<Long, Rate>();
        for (Rate e: effect_.getHitsLaw().events()) {
            hitsLaw_.put(e.ll(), effect_.getHitsLaw().normalizedRate(e));
        }
        hitsLaw = hitsLaw_;
        if (hitsLaw.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            nbHits = hitsLaw.firstKey();
            hitsLaw = new NatTreeMap<Long, Rate>();
        }
        chRate = effect_.getChRate();
        NatCmpTreeMap<Rate, Rate> chLaw_;
        chLaw_ = new NatCmpTreeMap<Rate, Rate>();
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

    @Accessible
    private boolean hasLawForDamage() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        return !effect_.getDamageLaw().events().isEmpty();
    }

    @Accessible
    private boolean hasDeterminatedLawForDamage() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        return effect_.getDamageLaw().events().size() == DataBase.ONE_POSSIBLE_CHOICE;
    }

    @Accessible
    private boolean counterDamageCat() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        if (!effect_.getDamageLaw().events().isEmpty()) {
            return false;
        }
        return !effect_.getMultDamageAgainst().isEmpty();
    }

    @Accessible
    private boolean constPower() {
        EffectDamage effect_ = (EffectDamage) getEffect();
        if (!effect_.getDamageLaw().events().isEmpty()) {
            return false;
        }
        if (!effect_.getMultDamageAgainst().isEmpty()) {
            return false;
        }
        return !power.isEmpty();
    }

    @Accessible
    private boolean hasConstPower() {
        return Rate.isValid(power);
    }

    @Accessible
    private String getTranslatedStatisTarget(Long _index) {
        Statistic st_ = ignVarStatTargetPos.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }

    @Accessible
    private String getTranslatedStatisUser(Long _index) {
        Statistic st_ = ignVarStatUserNeg.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }

    @Accessible
    private String getTranslatedStatisKo(Long _index) {
        Statistic st_ = boostStatisOnceKoFoe.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
}
