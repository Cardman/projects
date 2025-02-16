package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.beans.abilities.AbilityBean;
import aiki.beans.abilities.TranslatedKeyPair;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;
import code.maths.Rate;
import code.util.*;

public class EffectGlobalBean extends EffectBean {
    private EffectGlobalCore effectGlobalCore;
    private CustList<TranslatedKey> preventStatus;
    private CustList<TranslatedKey> immuneTypes;
    private DictionaryComparator<TranslatedKeyPair, Rate> efficiencyMoves;
    private CustList<TranslatedKey> disableImmuAgainstTypes;
    private CustList<TranslatedKey> cancelProtectingAbilities;
    private CustList<TranslatedKey> unusableMoves;
    private DictionaryComparator<TranslatedKey, Rate> multDamagePrepaRound;
    private CustList<TranslatedKey> movesUsedByTargetedFighters;
    private Rate multEffectLovingAlly;
    private DictionaryComparator<TranslatedKey, Rate> multPowerMoves;
    private DictionaryComparator<TranslatedKeyPair, Rate> multStatIfContainsType;
    private CustList<TranslatedKey> cancelEffects;
    private DictionaryComparator<TranslatedKey, Rate> multDamageTypesMoves;
    private CustList<TranslatedKey> cancelChgtStat;
    private TranslatedKey invokedMoveTerrain;
    private CustList<TranslatedKey> invokingMoves;
    private CustList<TranslatedKey> changedTypesTerrain;
    private CustList<TranslatedKey> invokingMovesChangingTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectGlobal effect_ = (EffectGlobal) getEffect();
        effectGlobalCore = effect_.getEffectGlobalCore();
        DataBase data_ = getDataBase();
        multEffectLovingAlly = effect_.getMultEffectLovingAlly();
        preventStatus = listTrStringsSt(effect_.getPreventStatus(), getFacade());
        immuneTypes = listTrStringsTy(effect_.getImmuneTypes(), getFacade());
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
        DictionaryComparator<TranslatedKeyPair, Rate> efficiencyMoves_;
        efficiencyMoves_ = DictionaryComparatorUtil.buildTypesDuoRate();
        for (TypesDuo t: effect_.getEfficiencyMoves().getKeys()) {
            efficiencyMoves_.put(new TranslatedKeyPair(buildTy(getFacade(),t.getDamageType()),buildTy(getFacade(),t.getPokemonType())), effect_.getEfficiencyMoves().getVal(t));
        }
        efficiencyMoves = efficiencyMoves_;
        disableImmuAgainstTypes = listTrStringsTy(effect_.getDisableImmuAgainstTypes(), getFacade());
        cancelProtectingAbilities = listTrStringsAb(effect_.getCancelProtectingAbilities(), getFacade());
        unusableMoves = listTrStringsMv(effect_.getUnusableMoves(), getFacade());
        cancelEffects = listTrStringsMv(effect_.getCancelEffects(), getFacade());
        multPowerMoves = multPowerMoves(effect_);
        multDamageTypesMoves = map(effect_.getMultDamageTypesMoves());
        CustList<TranslatedKey> cancelChgtStat_;
        cancelChgtStat_ = new CustList<TranslatedKey>();
        for (Statistic s: effect_.getCancelChgtStat()) {
            cancelChgtStat_.add(buildSi(getFacade(),s));
        }
        cancelChgtStat_.sortElts(new ComparingTranslatedKey());
        cancelChgtStat = cancelChgtStat_;
        invokedMoveTerrain = buildMv(getFacade(), effect_.getInvokedMoveTerrain());
        invokingMoves = listTrStringsMv(invokingMoves(data_),getFacade());
        CustList<TranslatedKey> changedTypesTerrain_;
        changedTypesTerrain_ = new CustList<TranslatedKey>();
        for (String s: effect_.getChangedTypesTerrain()) {
            changedTypesTerrain_.add(buildTy(getFacade(),s));
        }
        changedTypesTerrain = changedTypesTerrain_;
        invokingMovesChangingTypes = listTrStringsMv(invokingMovesChangingTypes(data_),getFacade());
        DictionaryComparator<TranslatedKeyPair, Rate> multStatIfContainsType_;
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
        multStatIfContainsType_ = DictionaryComparatorUtil.buildStatisTypeRate();
        for (StatisticType s: effect_.getMultStatIfContainsType().getKeys()) {
//            StatisticType key_ = new StatisticType();
//            key_.setFirst(translatedStatistics_.getVal(s.getStatistic()));
//            key_.setSecond(translatedTypes_.getVal(s.getType()));
            multStatIfContainsType_.put(AbilityBean.buildPair(getFacade(),s), effect_.getMultStatIfContainsType().getVal(s));
        }
        multStatIfContainsType = multStatIfContainsType_;
        multDamagePrepaRound = map(effect_.getMultDamagePrepaRound());
        movesUsedByTargetedFighters = listTrStringsMv(effect_.getMovesUsedByTargetedFighters(), getFacade());
    }

    private DictionaryComparator<TranslatedKey, Rate> multPowerMoves(EffectGlobal _eff) {
        DictionaryComparator<TranslatedKey, Rate> multPowerMoves_;
        multPowerMoves_ = DictionaryComparatorUtil.buildMovesRate();
        for (String m: _eff.getMultPowerMoves().getKeys()) {
            multPowerMoves_.put(buildMv(getFacade(),m), _eff.getMultPowerMoves().getVal(m));
        }
        return multPowerMoves_;
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

    public String clickPreventedStatus(int _eff, int _index) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).preventStatus.get(_index));
    }
    public String getTrPreventedStatus(int _index) {
        return preventStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String st_ = preventStatus.get(_index);
//        return translatedStatus_.getVal(st_);
    }
    public String clickCancelledAbility(int _eff, int _index) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).cancelProtectingAbilities.get(_index));
    }
    public String getTrCancelledAbility(int _index) {
        return cancelProtectingAbilities.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        String st_ = cancelProtectingAbilities.get(_index);
//        return translatedAbilities_.getVal(st_);
    }
    public String clickUnusableMove(int _eff, int _index) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).unusableMoves.get(_index));
    }
    public String getTrUnusableMoves(int _index) {
        return unusableMoves.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = unusableMoves.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickCancelledEffect(int _eff, int _index) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).cancelEffects.get(_index));
    }
    public String getTrCancelledEffect(int _index) {
        return cancelEffects.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = cancelEffects.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickMultMovePower(int _eff, int _index) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).multPowerMoves.getKey(_index));
    }
    public String getTrMultMovePower(int _index) {
        return multPowerMoves.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = multPowerMoves.getKey(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickInvokedMove(int _eff) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).invokedMoveTerrain);
    }
    public String getTrInvokedMoveTerrain() {
        return invokedMoveTerrain.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(invokedMoveTerrain);
    }
    public String clickInvokingMove(int _index) {
        return tryRedirect(invokingMoves.get(_index));
    }
    public String getTrInvokingMove(int _index) {
        return invokingMoves.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = invokingMoves.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickInvokingMoveTypes(int _index) {
        return tryRedirect(invokingMovesChangingTypes.get(_index));
    }
    public String getTrInvokingMoveTypes(int _index) {
        return invokingMovesChangingTypes.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = invokingMovesChangingTypes.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickMovesTarget(int _eff, int _index) {
        return tryRedirect(((EffectGlobalBean)getForms().getCurrentBean().get(_eff)).movesUsedByTargetedFighters.get(_index));
    }
    public String getTrMovesTarget(int _index) {
        return movesUsedByTargetedFighters.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = movesUsedByTargetedFighters.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String getTrMultStatIfDamgeTypeFirst(int _index) {
        return multStatIfContainsType.getKey(_index).getFirst().getTranslation();
//        Statistic statis_ = multStatIfContainsType.getKey(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(statis_);
    }
    public String getTrMultStatIfDamgeTypeSecond(int _index) {
        return multStatIfContainsType.getKey(_index).getSecond().getTranslation();
//        String type_ = multStatIfContainsType.getKey(_index).getType();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(type_);
    }

    public EffectGlobalCore getEffectGlobalCore() {
        return effectGlobalCore;
    }

    public Rate getMultEffectLovingAlly() {
        return multEffectLovingAlly;
    }

    public CustList<TranslatedKey> getPreventStatus() {
        return preventStatus;
    }

    public CustList<TranslatedKey> getImmuneTypes() {
        return immuneTypes;
    }

    public DictionaryComparator<TranslatedKeyPair,Rate> getEfficiencyMoves() {
        return efficiencyMoves;
    }

    public CustList<TranslatedKey> getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public CustList<TranslatedKey> getCancelProtectingAbilities() {
        return cancelProtectingAbilities;
    }

    public CustList<TranslatedKey> getUnusableMoves() {
        return unusableMoves;
    }

    public CustList<TranslatedKey> getCancelEffects() {
        return cancelEffects;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultPowerMoves() {
        return multPowerMoves;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultDamageTypesMoves() {
        return multDamageTypesMoves;
    }

    public CustList<TranslatedKey> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public TranslatedKey getInvokedMoveTerrain() {
        return invokedMoveTerrain;
    }

    public CustList<TranslatedKey> getInvokingMoves() {
        return invokingMoves;
    }

    public CustList<TranslatedKey> getChangedTypesTerrain() {
        return changedTypesTerrain;
    }

    public CustList<TranslatedKey> getInvokingMovesChangingTypes() {
        return invokingMovesChangingTypes;
    }

    public DictionaryComparator<TranslatedKeyPair,Rate> getMultStatIfContainsType() {
        return multStatIfContainsType;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultDamagePrepaRound() {
        return multDamagePrepaRound;
    }

    public CustList<TranslatedKey> getMovesUsedByTargetedFighters() {
        return movesUsedByTargetedFighters;
    }
}