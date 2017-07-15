package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorStatisticType;
import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;

public class EffectGlobalBean extends EffectBean {

    @Accessible
    private boolean weather;

    @Accessible
    private boolean canceledIfUsed;

    @Accessible
    private boolean reverseOrderOfSortBySpeed;

    @Accessible
    private boolean puttingKo;

    @Accessible
    private Rate multAccuracy;

    @Accessible
    private boolean unusableItem;

    @Accessible
    private StringList preventStatus;

    @Accessible
    private StringList immuneTypes;

    @Accessible
    private Rate damageEndRound;

    @Accessible
    private Rate healingEndRound;

    @Accessible
    private Rate healingEndRoundGround;

    @Accessible
    private TreeMap<TypesDuo, Rate> efficiencyMoves;

    @Accessible
    private StringList disableImmuAgainstTypes;

    @Accessible
    private StringList cancelProtectingAbilities;

    @Accessible
    private StringList unusableMoves;

    @Accessible
    private NatTreeMap<String, Rate> multDamagePrepaRound;

    @Accessible
    private StringList movesUsedByTargetedFighters;

    @Accessible
    private Rate multEffectLovingAlly;

    @Accessible
    private TreeMap<String, Rate> multPowerMoves;

    @Accessible
    private TreeMap<StatisticType, Rate> multStatIfContainsType;

    @Accessible
    private StringList cancelEffects;

    @Accessible
    private NatTreeMap<String, Rate> multDamageTypesMoves;

    @Accessible
    private StringList cancelChgtStat;

    @Accessible
    private String invokedMoveTerrain;

    @Accessible
    private StringList invokingMoves;

    @Accessible
    private StringList changedTypesTerrain;

    @Accessible
    private StringList invokingMovesChangingTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectGlobal effect_ = (EffectGlobal) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        weather = effect_.getWeather();
        canceledIfUsed = effect_.getCanceledIfUsed();
        reverseOrderOfSortBySpeed = effect_.getReverseOrderOfSortBySpeed();
        puttingKo = effect_.getPuttingKo();
        multAccuracy = effect_.getMultAccuracy();
        unusableItem = effect_.getUnusableItem();
        damageEndRound = effect_.getDamageEndRound();
        healingEndRound = effect_.getHealingEndRound();
        healingEndRoundGround = effect_.getHealingEndRoundGround();
        multEffectLovingAlly = effect_.getMultEffectLovingAlly();
        StringList preventStatus_;
        preventStatus_ = new StringList();
        for (String s: effect_.getPreventStatus()) {
            preventStatus_.add(s);
        }
        preventStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        preventStatus = preventStatus_;
        StringList immuneTypes_;
        immuneTypes_ = new StringList();
        for (String t: effect_.getImmuneTypes()) {
            immuneTypes_.add(translatedTypes_.getVal(t));
        }
        immuneTypes_.sort();
        immuneTypes = immuneTypes_;
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
        TreeMap<TypesDuo, Rate> efficiencyMoves_;
        efficiencyMoves_ = new TreeMap<TypesDuo, Rate>(new ComparatorTypesDuo(data_, getLanguage(), true));
        for (TypesDuo t: effect_.getEfficiencyMoves().getKeys()) {
            TypesDuo t_ = new TypesDuo();
            t_.setDamageType(translatedTypes_.getVal(t.getDamageType()));
            t_.setPokemonType(translatedTypes_.getVal(t.getPokemonType()));
            efficiencyMoves_.put(t_, effect_.getEfficiencyMoves().getVal(t));
        }
        efficiencyMoves = efficiencyMoves_;
        StringList disableImmuAgainstTypes_;
        disableImmuAgainstTypes_ = new StringList();
        for (String t: effect_.getDisableImmuAgainstTypes()) {
            disableImmuAgainstTypes_.add(translatedTypes_.getVal(t));
        }
        disableImmuAgainstTypes_.sort();
        disableImmuAgainstTypes = disableImmuAgainstTypes_;
        StringList cancelProtectingAbilities_;
        cancelProtectingAbilities_ = new StringList();
        for (String t: effect_.getCancelProtectingAbilities()) {
            cancelProtectingAbilities_.add(t);
        }
        cancelProtectingAbilities_.sortElts(new ComparatorTrStrings(translatedAbilities_));
        cancelProtectingAbilities = cancelProtectingAbilities_;
        StringList unusableMoves_;
        unusableMoves_ = new StringList();
        for (String m: effect_.getUnusableMoves()) {
            unusableMoves_.add(m);
        }
        unusableMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        unusableMoves = unusableMoves_;
        StringList cancelEffects_;
        cancelEffects_ = new StringList();
        for (String m: effect_.getCancelEffects()) {
            cancelEffects_.add(m);
        }
        cancelEffects_.sortElts(new ComparatorTrStrings(translatedMoves_));
        cancelEffects = cancelEffects_;
        TreeMap<String, Rate> multPowerMoves_;
        multPowerMoves_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedMoves_));
        for (String m: effect_.getMultPowerMoves().getKeys()) {
            multPowerMoves_.put(m, effect_.getMultPowerMoves().getVal(m));
        }
        multPowerMoves = multPowerMoves_;
        NatTreeMap<String, Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new NatTreeMap<String, Rate>();
        for (String m: effect_.getMultDamageTypesMoves().getKeys()) {
            multDamageTypesMoves_.put(translatedTypes_.getVal(m), effect_.getMultDamageTypesMoves().getVal(m));
        }
        multDamageTypesMoves = multDamageTypesMoves_;
        StringList cancelChgtStat_;
        cancelChgtStat_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStat()) {
            cancelChgtStat_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStat_.sort();
        cancelChgtStat = cancelChgtStat_;
        invokedMoveTerrain = effect_.getInvokedMoveTerrain();
        StringList invokingMoves_;
        invokingMoves_ = new StringList();
        for (String m: data_.getMovesInvoking()) {
            MoveData inv_ = data_.getMove(m);
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
        invokingMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        invokingMoves = invokingMoves_;
        StringList changedTypesTerrain_;
        changedTypesTerrain_ = new StringList();
        for (String s: effect_.getChangedTypesTerrain()) {
            changedTypesTerrain_.add(translatedTypes_.getVal(s));
        }
        changedTypesTerrain = changedTypesTerrain_;
        StringList invokingMovesChangingTypes_;
        invokingMovesChangingTypes_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData inv_ = data_.getMove(m);
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
        invokingMovesChangingTypes_.sortElts(new ComparatorTrStrings(translatedMoves_));
        invokingMovesChangingTypes = invokingMovesChangingTypes_;
        TreeMap<StatisticType, Rate> multStatIfContainsType_;
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
        multStatIfContainsType_ = new TreeMap<StatisticType, Rate>(new ComparatorStatisticType(data_, getLanguage()));
        for (StatisticType s: effect_.getMultStatIfContainsType().getKeys()) {
//            StatisticType key_ = new StatisticType();
//            key_.setFirst(translatedStatistics_.getVal(s.getStatistic()));
//            key_.setSecond(translatedTypes_.getVal(s.getType()));
            multStatIfContainsType_.put(s, effect_.getMultStatIfContainsType().getVal(s));
        }
        multStatIfContainsType = multStatIfContainsType_;
        NatTreeMap<String, Rate> multDamagePrepaRound_;
        multDamagePrepaRound_ = new NatTreeMap<String, Rate>();
        for (String m: effect_.getMultDamagePrepaRound().getKeys()) {
            multDamagePrepaRound_.put(translatedTypes_.getVal(m), effect_.getMultDamagePrepaRound().getVal(m));
        }
        multDamagePrepaRound = multDamagePrepaRound_;
        StringList movesUsedByTargetedFighters_;
        movesUsedByTargetedFighters_ = new StringList();
        for (String m: effect_.getMovesUsedByTargetedFighters()) {
            movesUsedByTargetedFighters_.add(m);
        }
        movesUsedByTargetedFighters_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesUsedByTargetedFighters = movesUsedByTargetedFighters_;
    }

    @Accessible
    private Listable<TypesDuo> getTypesDuos() {
        return efficiencyMoves.getKeys();
    }

    @Accessible
    private String clickPreventedStatus(Long _index) {
        String st_ = preventStatus.get(_index.intValue());
        getForms().put(STATUS, st_);
        return STATUS;
    }

    @Accessible
    private String getTrPreventedStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = preventStatus.get(_index.intValue());
        return translatedStatus_.getVal(st_);
    }

    @Accessible
    private String clickCancelledAbility(Long _index) {
        String st_ = cancelProtectingAbilities.get(_index.intValue());
        getForms().put(ABILITY, st_);
        return ABILITY;
    }

    @Accessible
    private String getTrCancelledAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String st_ = cancelProtectingAbilities.get(_index.intValue());
        return translatedAbilities_.getVal(st_);
    }

    @Accessible
    private String clickUnusableMove(Long _index) {
        String st_ = unusableMoves.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrUnusableMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = unusableMoves.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickCancelledEffect(Long _index) {
        String st_ = cancelEffects.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrCancelledEffect(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = cancelEffects.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickMultMovePower(Long _index) {
        String st_ = multPowerMoves.getKey(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrMultMovePower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = multPowerMoves.getKey(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickInvokedMove() {
        getForms().put(MOVE, invokedMoveTerrain);
        return MOVE;
    }

    @Accessible
    private String getTrInvokedMoveTerrain() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(invokedMoveTerrain);
    }

    @Accessible
    private String clickInvokingMove(Long _index) {
        String st_ = invokingMoves.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrInvokingMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMoves.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickInvokingMoveTypes(Long _index) {
        String st_ = invokingMovesChangingTypes.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrInvokingMoveTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMovesChangingTypes.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickMovesTarget(Long _index) {
        String st_ = movesUsedByTargetedFighters.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrMovesTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = movesUsedByTargetedFighters.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String getTrMultStatIfDamgeTypeFirst(Long _index) {
        Statistic statis_ = multStatIfContainsType.getKey(_index.intValue()).getStatistic();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(statis_);
    }

    @Accessible
    private String getTrMultStatIfDamgeTypeSecond(Long _index) {
        String type_ = multStatIfContainsType.getKey(_index.intValue()).getType();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(type_);
    }

    @Accessible
    private Listable<StatisticType> getStatisType() {
        return multStatIfContainsType.getKeys();
    }
}
