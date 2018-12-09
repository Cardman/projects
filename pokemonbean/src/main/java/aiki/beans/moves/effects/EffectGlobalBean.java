package aiki.beans.moves.effects;
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
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public class EffectGlobalBean extends EffectBean {
    private boolean weather;
    private boolean canceledIfUsed;
    private boolean reverseOrderOfSortBySpeed;
    private boolean puttingKo;
    private Rate multAccuracy;
    private boolean unusableItem;
    private StringList preventStatus;
    private StringList immuneTypes;
    private Rate damageEndRound;
    private Rate healingEndRound;
    private Rate healingEndRoundGround;
    private TreeMap<TypesDuo, Rate> efficiencyMoves;
    private StringList disableImmuAgainstTypes;
    private StringList cancelProtectingAbilities;
    private StringList unusableMoves;
    private NatStringTreeMap< Rate> multDamagePrepaRound;
    private StringList movesUsedByTargetedFighters;
    private Rate multEffectLovingAlly;
    private TreeMap<String, Rate> multPowerMoves;
    private TreeMap<StatisticType, Rate> multStatIfContainsType;
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
        NatStringTreeMap< Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new NatStringTreeMap< Rate>();
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
        NatStringTreeMap< Rate> multDamagePrepaRound_;
        multDamagePrepaRound_ = new NatStringTreeMap< Rate>();
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
    public Listable<TypesDuo> getTypesDuos() {
        return efficiencyMoves.getKeys();
    }
    public String clickPreventedStatus(Long _index) {
        String st_ = preventStatus.get(_index.intValue());
        getForms().put(STATUS, st_);
        return STATUS;
    }
    public String getTrPreventedStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = preventStatus.get(_index.intValue());
        return translatedStatus_.getVal(st_);
    }
    public String clickCancelledAbility(Long _index) {
        String st_ = cancelProtectingAbilities.get(_index.intValue());
        getForms().put(ABILITY, st_);
        return ABILITY;
    }
    public String getTrCancelledAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String st_ = cancelProtectingAbilities.get(_index.intValue());
        return translatedAbilities_.getVal(st_);
    }
    public String clickUnusableMove(Long _index) {
        String st_ = unusableMoves.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrUnusableMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = unusableMoves.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
    public String clickCancelledEffect(Long _index) {
        String st_ = cancelEffects.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrCancelledEffect(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = cancelEffects.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
    public String clickMultMovePower(Long _index) {
        String st_ = multPowerMoves.getKey(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrMultMovePower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = multPowerMoves.getKey(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
    public String clickInvokedMove() {
        getForms().put(MOVE, invokedMoveTerrain);
        return MOVE;
    }
    public String getTrInvokedMoveTerrain() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(invokedMoveTerrain);
    }
    public String clickInvokingMove(Long _index) {
        String st_ = invokingMoves.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrInvokingMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMoves.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
    public String clickInvokingMoveTypes(Long _index) {
        String st_ = invokingMovesChangingTypes.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrInvokingMoveTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMovesChangingTypes.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
    public String clickMovesTarget(Long _index) {
        String st_ = movesUsedByTargetedFighters.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrMovesTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = movesUsedByTargetedFighters.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
    public String getTrMultStatIfDamgeTypeFirst(Long _index) {
        Statistic statis_ = multStatIfContainsType.getKey(_index.intValue()).getStatistic();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(statis_);
    }
    public String getTrMultStatIfDamgeTypeSecond(Long _index) {
        String type_ = multStatIfContainsType.getKey(_index.intValue()).getType();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(type_);
    }
    public Listable<StatisticType> getStatisType() {
        return multStatIfContainsType.getKeys();
    }

    public boolean getWeather() {
        return weather;
    }

    public boolean getCanceledIfUsed() {
        return canceledIfUsed;
    }

    public boolean getReverseOrderOfSortBySpeed() {
        return reverseOrderOfSortBySpeed;
    }

    public boolean getUnusableItem() {
        return unusableItem;
    }

    public boolean getPuttingKo() {
        return puttingKo;
    }

    public Rate getMultAccuracy() {
        return multAccuracy;
    }

    public Rate getDamageEndRound() {
        return damageEndRound;
    }

    public Rate getHealingEndRoundGround() {
        return healingEndRoundGround;
    }

    public Rate getHealingEndRound() {
        return healingEndRound;
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

    public TreeMap<TypesDuo,Rate> getEfficiencyMoves() {
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

    public TreeMap<String,Rate> getMultPowerMoves() {
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

    public TreeMap<StatisticType,Rate> getMultStatIfContainsType() {
        return multStatIfContainsType;
    }

    public NatStringTreeMap<Rate> getMultDamagePrepaRound() {
        return multDamagePrepaRound;
    }

    public StringList getMovesUsedByTargetedFighters() {
        return movesUsedByTargetedFighters;
    }
}