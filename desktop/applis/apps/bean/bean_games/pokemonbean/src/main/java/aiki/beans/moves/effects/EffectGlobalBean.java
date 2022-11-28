package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Comparing;

public class EffectGlobalBean extends EffectBean {
    private EffectGlobalCore effectGlobalCore;
    private StringList preventStatus;
    private StringList immuneTypes;
    private DictionaryComparator<TypesDuo, Rate> efficiencyMoves;
    private StringList disableImmuAgainstTypes;
    private StringList cancelProtectingAbilities;
    private StringList unusableMoves;
    private NatStringTreeMap< Rate> multDamagePrepaRound;
    private StringList movesUsedByTargetedFighters;
    private Rate multEffectLovingAlly;
    private DictionaryComparator<String, Rate> multPowerMoves;
    private DictionaryComparator<StatisticType, Rate> multStatIfContainsType;
    private StringList cancelEffects;
    private NatStringTreeMap< Rate> multDamageTypesMoves;
    private StringList cancelChgtStat;
    private String invokedMoveTerrain;
    private StringList invokingMoves;
    private StringList changedTypesTerrain;
    private StringList invokingMovesChangingTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectGlobal effect_ = (EffectGlobal) getEffect();
        effectGlobalCore = effect_.getEffectGlobalCore();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        multEffectLovingAlly = effect_.getMultEffectLovingAlly();
        preventStatus = listTrStrings(effect_.getPreventStatus(), DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        immuneTypes = list(effect_.getImmuneTypes(), translatedTypes_);
//        TreeMap<TypesDuo, Rate> efficiencyMoves_;
//        efficiencyMoves_ = new TreeMap<new>(new NaturalComparator<TypesDuo>() {
//            @Override
//            public int compare(TypesDuo _o1, TypesDuo _o2) {
//                int cmp_ = _o1.getDamageType().compareTo(_o2.getDamageType());
//                if (cmp_ != 0) {
//                    return cmp_;
//                }
//                return _o1.getPokemonType().compareTo(_o2.getPokemonType());
//            }
//        });
        DictionaryComparator<TypesDuo, Rate> efficiencyMoves_;
        efficiencyMoves_ = DictionaryComparatorUtil.buildTypesDuoRate(data_, getLanguage(), true,false);
        for (TypesDuo t: effect_.getEfficiencyMoves().getKeys()) {
            TypesDuo t_ = new TypesDuo();
            t_.setDamageType(translatedTypes_.getVal(t.getDamageType()));
            t_.setPokemonType(translatedTypes_.getVal(t.getPokemonType()));
            efficiencyMoves_.put(t_, effect_.getEfficiencyMoves().getVal(t));
        }
        efficiencyMoves = efficiencyMoves_;
        disableImmuAgainstTypes = list(effect_.getDisableImmuAgainstTypes(), translatedTypes_);
        cancelProtectingAbilities = listTrStrings(effect_.getCancelProtectingAbilities(), DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        unusableMoves = unusableMoves(effect_);
        cancelEffects = cancelEffects(effect_);
        multPowerMoves = multPowerMoves(effect_);
        multDamageTypesMoves = map(effect_.getMultDamageTypesMoves(), translatedTypes_);
        StringList cancelChgtStat_;
        cancelChgtStat_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStat()) {
            cancelChgtStat_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStat_.sort();
        cancelChgtStat = cancelChgtStat_;
        invokedMoveTerrain = invokedMoveTerrain(effect_);
        StringList invokingMoves_ = invokingMoves(data_);
        invokingMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        invokingMoves = invokingMoves_;
        StringList changedTypesTerrain_;
        changedTypesTerrain_ = new StringList();
        for (String s: effect_.getChangedTypesTerrain()) {
            changedTypesTerrain_.add(translatedTypes_.getVal(s));
        }
        changedTypesTerrain = changedTypesTerrain_;
        StringList invokingMovesChangingTypes_ = invokingMovesChangingTypes(data_);
        invokingMovesChangingTypes_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        invokingMovesChangingTypes = invokingMovesChangingTypes_;
        DictionaryComparator<StatisticType, Rate> multStatIfContainsType_;
//        multStatIfContainsType_ = new TreeMap<new>(new NaturalComparator<Pair<String,String>>() {
//            @Override
//            public int compare(Pair<String, String> _o1,
//                    Pair<String, String> _o2) {
//                int cmp_ = _o1.getFirst().compareTo(_o2.getFirst());
//                if (cmp_ != 0) {
//                    return cmp_;
//                }
//                return _o1.getSecond().compareTo(_o2.getSecond());
//            }
//        });
        multStatIfContainsType_ = DictionaryComparatorUtil.buildStatisTypeRate(data_,getLanguage());
        for (StatisticType s: effect_.getMultStatIfContainsType().getKeys()) {
//            StatisticType key_ = new StatisticType();
//            key_.setFirst(translatedStatistics_.getVal(s.getStatistic()));
//            key_.setSecond(translatedTypes_.getVal(s.getType()));
            multStatIfContainsType_.put(s, effect_.getMultStatIfContainsType().getVal(s));
        }
        multStatIfContainsType = multStatIfContainsType_;
        multDamagePrepaRound = map(effect_.getMultDamagePrepaRound(), translatedTypes_);
        movesUsedByTargetedFighters = movesUsedByTargetedFighters(effect_);
    }

    private StringList movesUsedByTargetedFighters(Effect _eff) {
        return listTrStrings(((EffectGlobal)_eff).getMovesUsedByTargetedFighters(), DictionaryComparatorUtil.cmpMoves(getDataBase(), getLanguage()));
    }

    private String invokedMoveTerrain(Effect _eff) {
        return ((EffectGlobal)_eff).getInvokedMoveTerrain();
    }

    private DictionaryComparator<String, Rate> multPowerMoves(Effect _eff) {
        EffectGlobal effect_ = (EffectGlobal) _eff;
        DictionaryComparator<String, Rate> multPowerMoves_;
        multPowerMoves_ = DictionaryComparatorUtil.buildMovesRate(getDataBase(),getLanguage());
        for (String m: effect_.getMultPowerMoves().getKeys()) {
            multPowerMoves_.put(m, effect_.getMultPowerMoves().getVal(m));
        }
        return multPowerMoves_;
    }

    private StringList cancelEffects(Effect _eff) {
        return listTrStrings(((EffectGlobal)_eff).getCancelEffects(), DictionaryComparatorUtil.cmpMoves(getDataBase(), getLanguage()));
    }

    private StringList unusableMoves(Effect _eff) {
        return listTrStrings(((EffectGlobal)_eff).getUnusableMoves(), DictionaryComparatorUtil.cmpMoves(getDataBase(), getLanguage()));
    }

    public static StringList invokingMovesChangingTypes(DataBase _data) {
        StringList invokingMovesChangingTypes_;
        invokingMovesChangingTypes_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData inv_ = _data.getMove(m);
            for (Effect e: inv_.getEffects()) {
                if (!(e instanceof EffectSwitchTypes)) {
                    continue;
                }
                EffectSwitchTypes eff_ = (EffectSwitchTypes) e;
                if (!eff_.getChgtTypeByEnv().isEmpty()) {
                    invokingMovesChangingTypes_.add(m);
                }
            }
        }
        return invokingMovesChangingTypes_;
    }

    public static StringList invokingMoves(DataBase _data) {
        StringList invokingMoves_;
        invokingMoves_ = new StringList();
        for (String m: _data.getMovesInvoking()) {
            MoveData inv_ = _data.getMove(m);
            for (Effect e: inv_.getEffects()) {
                if (!(e instanceof EffectInvoke)) {
                    continue;
                }
                EffectInvoke eff_ = (EffectInvoke) e;
                if (!eff_.getMoveFctEnv().isEmpty()) {
                    invokingMoves_.add(m);
                }
            }
        }
        return invokingMoves_;
    }

    private NatStringTreeMap<Rate> map(StringMap<Rate> _input, StringMap<String> _translated) {
        NatStringTreeMap< Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new NatStringTreeMap< Rate>();
        for (String m: _input.getKeys()) {
            multDamageTypesMoves_.put(_translated.getVal(m), _input.getVal(m));
        }
        return multDamageTypesMoves_;
    }

    private StringList list(StringList _input, StringMap<String> _translated) {
        StringList res_ = new StringList();
        for (String t: _input) {
            res_.add(_translated.getVal(t));
        }
        res_.sort();
        return res_;
    }

    private static StringList listTrStrings(StringList _input, Comparing<String> _comp) {
        StringList res_ = new StringList();
        for (String s: _input) {
            res_.add(s);
        }
        res_.sortElts(_comp);
        return res_;
    }

    public String clickPreventedStatus(int _eff, int _index) {
        EffectGlobal effect_ = (EffectGlobal) getEffect(_eff);
        return tryRedirectSt(listTrStrings(effect_.getPreventStatus(), DictionaryComparatorUtil.cmpStatus(getDataBase(), getLanguage())).get(_index));
    }
    public String getTrPreventedStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = preventStatus.get(_index);
        return translatedStatus_.getVal(st_);
    }
    public String clickCancelledAbility(int _eff, int _index) {
        EffectGlobal effect_ = (EffectGlobal) getEffect(_eff);
        return tryRedirectAb(listTrStrings(effect_.getCancelProtectingAbilities(), DictionaryComparatorUtil.cmpAbilities(getDataBase(),getLanguage())).get(_index));
    }
    public String getTrCancelledAbility(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String st_ = cancelProtectingAbilities.get(_index);
        return translatedAbilities_.getVal(st_);
    }
    public String clickUnusableMove(int _eff, int _index) {
        return tryRedirectMv(unusableMoves(getEffect(_eff)).get(_index));
    }
    public String getTrUnusableMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = unusableMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickCancelledEffect(int _eff, int _index) {
        return tryRedirectMv(cancelEffects(getEffect(_eff)).get(_index));
    }
    public String getTrCancelledEffect(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = cancelEffects.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickMultMovePower(int _eff, int _index) {
        return tryRedirectMv(multPowerMoves(getEffect(_eff)).getKey(_index));
    }
    public String getTrMultMovePower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = multPowerMoves.getKey(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickInvokedMove(int _eff) {
        return tryRedirectMv(invokedMoveTerrain(getEffect(_eff)));
    }
    public String getTrInvokedMoveTerrain() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(invokedMoveTerrain);
    }
    public String clickInvokingMove(int _index) {
        String st_ = invokingMoves.get(_index);
        return tryRedirectMv(st_);
    }
    public String getTrInvokingMove(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickInvokingMoveTypes(int _index) {
        String st_ = invokingMovesChangingTypes.get(_index);
        return tryRedirectMv(st_);
    }
    public String getTrInvokingMoveTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMovesChangingTypes.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickMovesTarget(int _eff, int _index) {
        return tryRedirectMv(movesUsedByTargetedFighters(getEffect(_eff)).get(_index));
    }
    public String getTrMovesTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = movesUsedByTargetedFighters.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String getTrMultStatIfDamgeTypeFirst(int _index) {
        Statistic statis_ = multStatIfContainsType.getKey(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(statis_);
    }
    public String getTrMultStatIfDamgeTypeSecond(int _index) {
        String type_ = multStatIfContainsType.getKey(_index).getType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(type_);
    }

    public EffectGlobalCore getEffectGlobalCore() {
        return effectGlobalCore;
    }

    public Rate getMultEffectLovingAlly() {
        return multEffectLovingAlly;
    }

    public StringList getPreventStatus() {
        return preventStatus;
    }

    public StringList getImmuneTypes() {
        return immuneTypes;
    }

    public DictionaryComparator<TypesDuo,Rate> getEfficiencyMoves() {
        return efficiencyMoves;
    }

    public StringList getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public StringList getCancelProtectingAbilities() {
        return cancelProtectingAbilities;
    }

    public StringList getUnusableMoves() {
        return unusableMoves;
    }

    public StringList getCancelEffects() {
        return cancelEffects;
    }

    public DictionaryComparator<String,Rate> getMultPowerMoves() {
        return multPowerMoves;
    }

    public NatStringTreeMap<Rate> getMultDamageTypesMoves() {
        return multDamageTypesMoves;
    }

    public StringList getCancelChgtStat() {
        return cancelChgtStat;
    }

    public String getInvokedMoveTerrain() {
        return invokedMoveTerrain;
    }

    public StringList getInvokingMoves() {
        return invokingMoves;
    }

    public StringList getChangedTypesTerrain() {
        return changedTypesTerrain;
    }

    public StringList getInvokingMovesChangingTypes() {
        return invokingMovesChangingTypes;
    }

    public DictionaryComparator<StatisticType,Rate> getMultStatIfContainsType() {
        return multStatIfContainsType;
    }

    public NatStringTreeMap<Rate> getMultDamagePrepaRound() {
        return multDamagePrepaRound;
    }

    public StringList getMovesUsedByTargetedFighters() {
        return movesUsedByTargetedFighters;
    }
}