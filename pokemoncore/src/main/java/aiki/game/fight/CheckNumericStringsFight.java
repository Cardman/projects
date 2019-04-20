package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.status.Status;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Player;
import aiki.map.characters.GymLeader;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.SortableCustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.NumericableString;

public final class CheckNumericStringsFight {

    private CheckNumericStringsFight() {
    }

    public static void validateNumericBooleanStrings(DataBase _data,
            boolean _validateLevel) {
        // NumericString.setCheckSyntax(true);
        checkBoosts(_data);
        checkRateGrowLevel(_data, _validateLevel);
        // String varRegExp_ = StringList.BOUNDS+DataBase.VAR_PREFIX;
        checkExpGrowth(_data);
        Difficulty diff_ = new Difficulty();
        Player user_ = new Player(DataBase.EMPTY_STRING, null, diff_, false,
                _data);
        WildPk pk_ = new WildPk();
        pk_.setName(_data.getPokedex().getKeys().first());
        pk_.setAbility(_data.getAbilities().getKeys().first());
        PokemonPlayer pkUser_ = new PokemonPlayer(pk_, _data);
        user_.getTeam().add(pkUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(_data.getPokedex().getKeys().first());
        foePokemon_.setAbility(_data.getAbilities().getKeys().first());
        foePokemon_
                .setMoves(new StringList(_data.getMoves().getKeys().first()));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, user_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        TeamPosition userFighter_ = Fight.toUserFighter((byte) 0);
        TeamPosition foeFighter_ = Fight.toFoeFighter((byte) 0);
        StringMap<String> variablesDiff_;
        StringMap<String> variablesFighter_;
        StringMap<String> variablesSame_;
        variablesDiff_ = FightValues.calculateValues(fight_, userFighter_,
                foeFighter_, _data);
        variablesFighter_ = FightValues.calculateValuesFighter(fight_,
                userFighter_, _data);
        variablesSame_ = FightValues.calculateValues(fight_, userFighter_,
                userFighter_, _data);
        StringMap<String> boolVarsNotSending_;
        StringMap<String> boolVarsDiffNotSending_;
        StringMap<String> boolVarsSending_;
        StringMap<String> varsSending_ = FightValues.calculateSendingVariables(
                fight_, userFighter_, _data);
        boolVarsNotSending_ = calculateBooleanValuesForValidation(fight_,
                userFighter_, userFighter_, false, _data);
        boolVarsDiffNotSending_ = calculateBooleanValuesForValidation(fight_,
                userFighter_, foeFighter_, false, _data);
        boolVarsSending_ = calculateBooleanValuesForValidation(fight_,
                userFighter_, userFighter_, true, _data);
        boolVarsSending_.putAllMap(varsSending_);
        checkMovesStr(_data, diff_, fight_, userFighter_, foeFighter_,
                variablesDiff_, variablesSame_, boolVarsNotSending_,
                boolVarsDiffNotSending_, boolVarsSending_, varsSending_);
        checkAbilitiesStr(_data, diff_, fight_, userFighter_, foeFighter_,
                variablesDiff_, variablesFighter_, variablesSame_,
                boolVarsNotSending_, boolVarsDiffNotSending_);
        checkCombosStr(_data, variablesDiff_, variablesSame_,
                boolVarsNotSending_, boolVarsDiffNotSending_);
        checkStatusStr(_data, variablesDiff_, variablesFighter_,
                boolVarsDiffNotSending_);
        checkItemsStr(_data, diff_, fight_, userFighter_, foeFighter_,
                variablesDiff_, variablesFighter_, variablesSame_,
                boolVarsNotSending_, boolVarsDiffNotSending_);
        FightFacade.endFight(fight_);
        FightFacade.initFight(fight_, user_, diff_, pk_, _data);
        checkNumericStringsBalls(_data, fight_);
        checkDamageStr(_data);
        // FightRound.calculateCatchingRate
        // NumericString.setCheckSyntax(false);
    }

    private static void checkDamageStr(DataBase _data) {
        String numericExp_;
        numericExp_ = _data.getDamageFormula();
        NumericableString<Rate> num_;
        StringMap<String> varLocs_ = new StringMap<String>();
        varLocs_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.ATTACK),
                DataBase.defRateProduct().toNumberString());
        varLocs_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.DEFENSE),
                DataBase.defRateProduct().toNumberString());
        varLocs_.put(
                StringList.concat(DataBase.VAR_PREFIX, Fight.LANCEUR_NIVEAU),
                Integer.toString(_data.getMinLevel()));
        varLocs_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.POWER),
                DataBase.getDefaultPower().toNumberString());
        num_ = _data.createNumericableString(numericExp_, varLocs_);
        num_.evaluateExp(true);
        if (!num_.isValid()) {
            _data.setError(true);

        }
    }

    private static void checkCombosStr(DataBase _data,
            StringMap<String> _varsDiff, StringMap<String> _varsSame,
            StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending) {
        for (EffectCombo e : _data.getCombos().getEffects().values()) {
            for (EffectEndRound effEndRound_ : e.getEffectEndRound()) {
                if (effEndRound_.getFailEndRound().isEmpty()) {
                    continue;
                }
                checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                        effEndRound_.getFailEndRound());
                checkBoolStrings(_data, _varsSame, _boolVarsNotSending,
                        effEndRound_.getFailEndRound());
            }
        }
    }

    private static void checkStatusStr(DataBase _data,
            StringMap<String> _varsDiff, StringMap<String> _varsFighter,
            StringMap<String> _boolVarsDiffNotSending) {
        for (Status s : _data.getStatus().values()) {
            for (EffectEndRound e : s.getEffectEndRound()) {
                if (e.getFailEndRound().isEmpty()) {
                    continue;
                }
                checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                        e.getFailEndRound());
            }
            String fail_ = s.getFail();
            if (!fail_.isEmpty()) {
                NumericableString<Boolean> chBool_;
                StringMap<String> map_ = new StringMap<String>();
                map_.putAllMap(_varsFighter);
                chBool_ = _data.createBooleanString(fail_, map_);
                checkIfVarPresent(_data, chBool_.beforeEvaluated());
                checkTranslations(_data, chBool_.beforeEvaluated());
                chBool_.evaluateExp(true);
                if (!chBool_.isValid()) {
                    _data.setError(true);
                    return;

                }
            }
        }
    }

    private static void checkItemsStr(DataBase _data, Difficulty _diff,
            Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter,
            StringMap<String> _varsDiff, StringMap<String> _varsFighter,
            StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending) {
        for (Item o : _data.getItems().values()) {
            if (o instanceof ItemForBattle) {
                ItemForBattle o_ = (ItemForBattle) o;
                if (!o_.getMultPower().isEmpty()) {
                    StringMap<String> loc_ = new StringMap<String>(_varsDiff);
                    loc_.putAllMap(_varsFighter);
                    Fighter userFighterLoc_ = _fight.getFighter(_userFighter);
                    String cat_ = _data.getMove(_data.getDefaultMove())
                            .getCategory();
                    StringList types_ = _data.getMove(_data.getDefaultMove())
                            .getTypes();
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_CATEGORIE), cat_);
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.LANCEUR_NOM), userFighterLoc_.getName());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_TYPES), types_.join(_data
                            .getSepartorSetChar()));
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_NOM), _data.getDefaultMove());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.PUISSANCE_BASE), DataBase.getDefaultPower()
                            .toNumberString());
                    checkNumString(_data, loc_, o_.getMultPower());
                }
                if (!o_.getMultDamage().isEmpty()) {
                    StringMap<String> loc_ = new StringMap<String>(_varsDiff);
                    Fighter userFighterLoc_ = _fight.getFighter(_userFighter);
                    String cat_ = _data.getMove(_data.getDefaultMove())
                            .getCategory();
                    StringList types_ = _data.getMove(_data.getDefaultMove())
                            .getTypes();
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_CATEGORIE), cat_);
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.LANCEUR_NOM), userFighterLoc_.getName());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_TYPES), types_.join(_data
                            .getSepartorSetChar()));
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_NOM), _data.getDefaultMove());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.PUISSANCE_BASE), DataBase.getDefaultPower()
                            .toNumberString());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.COEFF_EFF), DataBase.defRateProduct()
                            .toNumberString());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.NB_UTILISATION_CONSECUTIF), userFighterLoc_
                            .getNbRepeatingSuccessfulMoves().toNumberString());
                    checkNumString(_data, loc_, o_.getMultDamage());
                }
                for (Statistic s : o_.getMultStat().getKeys()) {
                    String str_ = o_.getMultStat().getVal(s);
                    if (str_.isEmpty()) {
                        continue;
                    }
                    StringMap<String> loc_;
                    boolean special_ = false;
                    if (s == Statistic.SPEED) {
                        special_ = true;
                    } else if (s == Statistic.ACCURACY) {
                        special_ = true;
                    } else if (s == Statistic.EVASINESS) {
                        special_ = true;
                    }
                    if (special_) {
                        loc_ = new StringMap<String>(_varsFighter);
                    } else {
                        Fighter userFighterLoc_ = _fight
                                .getFighter(_userFighter);
                        String cat_ = _data.getMove(_data.getDefaultMove())
                                .getCategory();
                        StringList types_ = _data.getMove(
                                _data.getDefaultMove()).getTypes();
                        loc_ = new StringMap<String>(_varsDiff);
                        loc_.putAllMap(_varsFighter);
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.ATTAQUE_CATEGORIE), cat_);
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.LANCEUR_NOM), userFighterLoc_.getName());
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.ATTAQUE_TYPES), types_.join(_data
                                .getSepartorSetChar()));
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.ATTAQUE_NOM), _data.getDefaultMove());
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.PUISSANCE_BASE), DataBase
                                .getDefaultPower().toNumberString());
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.COEFF_EFF), DataBase.defRateProduct()
                                .toNumberString());
                        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.NB_UTILISATION_CONSECUTIF),
                                userFighterLoc_.getNbRepeatingSuccessfulMoves()
                                        .toNumberString());
                    }
                    checkNumString(_data, loc_, str_);
                }
                for (String v : o_.getFailStatus().values()) {
                    if (v.isEmpty()) {
                        continue;
                    }
                    checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                            v);
                    checkBoolStrings(_data, _varsSame, _boolVarsNotSending, v);
                }
                for (EffectEndRound e : o_.getEffectEndRound()) {
                    if (e.getFailEndRound().isEmpty()) {
                        continue;
                    }
                    checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                            e.getFailEndRound());
                    checkBoolStrings(_data, _varsSame, _boolVarsNotSending,
                            e.getFailEndRound());
                }
                for (EffectWhileSending e : o_.getEffectSending()) {
                    if (e instanceof EffectWhileSendingWithStatistic) {
                        EffectWhileSendingWithStatistic eff_ = (EffectWhileSendingWithStatistic) e;
                        StringList fails_ = new StringList();
                        EffectStatistic e_ = eff_.getEffect();
                        fails_.addAllElts(e_.getLocalFailStatis().values());
                        fails_.addAllElts(e_.getLocalFailSwapBoostStatis()
                                .values());
                        EqList<TeamPosition> listFighters_ = FightOrder
                                .targetsEffect(_fight, _userFighter, e_, _diff,
                                        _data);
                        boolean containsTarget_ = listFighters_
                                .containsObj(_foeFighter);
                        boolean containsThrower_ = listFighters_
                                .containsObj(_userFighter);
                        if (containsTarget_) {
                            for (String f : fails_) {
                                if (f.isEmpty()) {
                                    continue;
                                }
                                checkBoolStrings(_data, _varsDiff,
                                        _boolVarsDiffNotSending, f);
                            }
                        }
                        if (containsThrower_) {
                            for (String f : fails_) {
                                if (f.isEmpty()) {
                                    continue;
                                }
                                checkBoolStrings(_data, _varsSame,
                                        _boolVarsNotSending, f);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void checkAbilitiesStr(DataBase _data, Difficulty _diff,
            Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter,
            StringMap<String> _varsDiff, StringMap<String> _varsFighter,
            StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending) {
        for (String ab_ : _data.getAbilities().getKeys()) {
            AbilityData a = _data.getAbility(ab_);
            if (!a.getMultPower().isEmpty()) {
                StringMap<String> loc_ = new StringMap<String>(_varsDiff);
                loc_.putAllMap(_varsFighter);
                Fighter userFighterLoc_ = _fight.getFighter(_userFighter);
                String cat_ = _data.getMove(_data.getDefaultMove())
                        .getCategory();
                StringList types_ = _data.getMove(_data.getDefaultMove())
                        .getTypes();
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.ATTAQUE_CATEGORIE), cat_);
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.LANCEUR_NOM), userFighterLoc_.getName());
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.ATTAQUE_TYPES), types_.join(_data
                        .getSepartorSetChar()));
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.ATTAQUE_NOM), _data.getDefaultMove());
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.PUISSANCE_BASE), DataBase.getDefaultPower()
                        .toNumberString());
                checkNumString(_data, loc_, a.getMultPower());
            }
            if (!a.getMultDamage().isEmpty()) {
                StringMap<String> loc_ = new StringMap<String>(_varsDiff);
                Fighter userFighterLoc_ = _fight.getFighter(_userFighter);
                String cat_ = _data.getMove(_data.getDefaultMove())
                        .getCategory();
                StringList types_ = _data.getMove(_data.getDefaultMove())
                        .getTypes();
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.ATTAQUE_CATEGORIE), cat_);
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.LANCEUR_NOM), userFighterLoc_.getName());
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.ATTAQUE_TYPES), types_.join(_data
                        .getSepartorSetChar()));
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.ATTAQUE_NOM), _data.getDefaultMove());
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.PUISSANCE_BASE), DataBase.getDefaultPower()
                        .toNumberString());
                loc_.put(
                        StringList.concat(DataBase.VAR_PREFIX, Fight.COEFF_EFF),
                        DataBase.defRateProduct().toNumberString());
                loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.NB_UTILISATION_CONSECUTIF), userFighterLoc_
                        .getNbRepeatingSuccessfulMoves().toNumberString());
                checkNumString(_data, loc_, a.getMultDamage());
            }
            for (Statistic s : a.getMultStat().getKeys()) {
                String str_ = a.getMultStat().getVal(s);
                if (str_.isEmpty()) {
                    continue;
                }
                StringMap<String> loc_;
                boolean special_ = false;
                if (s == Statistic.SPEED) {
                    special_ = true;
                } else if (s == Statistic.ACCURACY) {
                    special_ = true;
                } else if (s == Statistic.EVASINESS) {
                    special_ = true;
                }
                if (special_) {
                    loc_ = new StringMap<String>(_varsFighter);
                } else {
                    Fighter userFighterLoc_ = _fight.getFighter(_userFighter);
                    String cat_ = _data.getMove(_data.getDefaultMove())
                            .getCategory();
                    StringList types_ = _data.getMove(_data.getDefaultMove())
                            .getTypes();
                    loc_ = new StringMap<String>(_varsDiff);
                    loc_.putAllMap(_varsFighter);
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_CATEGORIE), cat_);
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.LANCEUR_NOM), userFighterLoc_.getName());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_TYPES), types_.join(_data
                            .getSepartorSetChar()));
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.ATTAQUE_NOM), _data.getDefaultMove());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.PUISSANCE_BASE), DataBase.getDefaultPower()
                            .toNumberString());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.COEFF_EFF), DataBase.defRateProduct()
                            .toNumberString());
                    loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                            Fight.NB_UTILISATION_CONSECUTIF), userFighterLoc_
                            .getNbRepeatingSuccessfulMoves().toNumberString());
                }
                checkNumString(_data, loc_, str_);
            }
            for (String v : a.getFailStatus().values()) {
                if (v.isEmpty()) {
                    continue;
                }
                checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending, v);
            }
            for (EffectEndRound e : a.getEffectEndRound()) {
                if (e.getFailEndRound().isEmpty()) {
                    continue;
                }
                checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                        e.getFailEndRound());
                checkBoolStrings(_data, _varsSame, _boolVarsNotSending,
                        e.getFailEndRound());
            }
            for (EffectWhileSending e : a.getEffectSending()) {
                if (e instanceof EffectWhileSendingWithStatistic) {
                    EffectWhileSendingWithStatistic eff_ = (EffectWhileSendingWithStatistic) e;
                    StringList fails_ = new StringList();
                    EffectStatistic e_ = eff_.getEffect();
                    fails_.addAllElts(e_.getLocalFailStatis().values());
                    fails_.addAllElts(e_.getLocalFailSwapBoostStatis().values());
                    EqList<TeamPosition> listFighters_ = FightOrder
                            .targetsEffect(_fight, _userFighter, e_, _diff,
                                    _data);
                    boolean containsTarget_ = listFighters_
                            .containsObj(_foeFighter);
                    boolean containsThrower_ = listFighters_
                            .containsObj(_userFighter);
                    if (containsTarget_) {
                        for (String f : fails_) {
                            if (f.isEmpty()) {
                                continue;
                            }
                            checkBoolStrings(_data, _varsDiff,
                                    _boolVarsDiffNotSending, f);
                        }
                    }
                    if (containsThrower_) {
                        for (String f : fails_) {
                            if (f.isEmpty()) {
                                continue;
                            }
                            checkBoolStrings(_data, _varsSame,
                                    _boolVarsNotSending, f);
                        }
                    }
                }
            }
        }
    }

    private static void checkMovesStr(DataBase _data, Difficulty _diff,
            Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter,
            StringMap<String> _varsDiff, StringMap<String> _varsSame,
            StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending,
            StringMap<String> _boolVarsSending, StringMap<String> _varsSending) {
        NumericableString<Rate> chNum_;
        for (String m : _data.getMoves().getKeys()) {
            MoveData m_ = _data.getMove(m);
            for (TeamPosition t : FightOrder.targetsEffect(_fight,
                    _userFighter, m_.getEffet(m_.indexOfPrimaryEffect()),
                    _diff, _data)) {
                if (TeamPosition.eq(t, _userFighter)) {
                    chNum_ = _data.createNumericableString(m_.getAccuracy(),
                            _varsSame);
                } else {
                    chNum_ = _data.createNumericableString(m_.getAccuracy(),
                            _varsDiff);
                }
                checkIfVarPresent(_data, chNum_.beforeEvaluated());
                checkTranslations(_data, chNum_.beforeEvaluated());
                chNum_.evaluateExp(true);
                if (!chNum_.isValid()) {
                    _data.setError(true);
                    return;

                }
            }
            for (Effect e : m_.getEffects()) {
                String fail_ = e.getFail();
                if (fail_.isEmpty()) {
                    continue;
                }
                for (TeamPosition t : FightOrder.targetsEffect(_fight,
                        _userFighter, e, _diff, _data)) {
                    NumericableString<Boolean> chBool_;
                    if (TeamPosition.eq(t, _userFighter)) {
                        StringMap<String> map_ = new StringMap<String>();
                        map_.putAllMap(_varsSame);
                        map_.putAllMap(_boolVarsNotSending);
                        chBool_ = _data.createBooleanString(fail_, map_);
                    } else {
                        StringMap<String> map_ = new StringMap<String>();
                        map_.putAllMap(_varsDiff);
                        map_.putAllMap(_boolVarsDiffNotSending);
                        chBool_ = _data.createBooleanString(fail_, map_);
                    }
                    checkIfVarPresent(_data, chBool_.beforeEvaluated());
                    checkTranslations(_data, chBool_.beforeEvaluated());
                    chBool_.evaluateExp(true);
                    if (!chBool_.isValid()) {
                        _data.setError(true);
                        return;

                    }
                }
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage eff_ = (EffectDamage) e;
                if (eff_.getConstDamage()) {
                    if (!Rate.isValid(eff_.getPower())) {
                        _data.setError(true);
                        return;

                    }
                } else if (!eff_.getPower().isEmpty()) {
                    chNum_ = _data.createNumericableString(eff_.getPower(),
                            _varsDiff);
                    checkIfVarPresent(_data, chNum_.beforeEvaluated());
                    checkTranslations(_data, chNum_.beforeEvaluated());
                    chNum_.evaluateExp(true);
                    if (!chNum_.isValid()) {
                        _data.setError(true);
                        return;

                    }
                }
                for (String e2_ : eff_.getDamageLaw().events()) {
                    // accept empty strings
                    if (e2_.isEmpty()) {
                        continue;
                    }
                    checkNumString(_data, _varsDiff, e2_);
                }
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectTeamWhileSendFoe)) {
                    continue;
                }
                EffectTeamWhileSendFoe e_ = (EffectTeamWhileSendFoe) e;
                String fail_ = e_.getFailSending();
                if (fail_.isEmpty()) {
                    continue;
                }
                checkBoolStrings(_data, _varsSame, _boolVarsSending, fail_);
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectTeamWhileSendFoe)) {
                    continue;
                }
                EffectTeamWhileSendFoe e_ = (EffectTeamWhileSendFoe) e;
                String string_ = e_.getDamageRateAgainstFoe();
                if (string_.isEmpty()) {
                    continue;
                }
                StringMap<String> map_ = new StringMap<String>();
                map_.putAllMap(_varsSame);
                map_.putAllMap(_varsSending);
                checkNumString(_data, map_, string_);
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectFullHpRate)) {
                    continue;
                }
                EffectFullHpRate e_ = (EffectFullHpRate) e;
                String string_ = e_.getRestoredHp();
                if (string_.isEmpty()) {
                    continue;
                }
                checkNumString(_data, _varsSame, string_);
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectCommonStatistics)) {
                    continue;
                }
                EffectCommonStatistics eff_ = (EffectCommonStatistics) e;
                for (String e2_ : eff_.getCommonValue().values()) {
                    checkNumString(_data, _varsDiff, e2_);
                }
            }
            for (Effect e : m_.getEffects()) {
                StringList fails_ = new StringList();
                if (e instanceof EffectStatistic) {
                    EffectStatistic e_ = (EffectStatistic) e;
                    fails_.addAllElts(e_.getLocalFailStatis().values());
                    fails_.addAllElts(e_.getLocalFailSwapBoostStatis().values());
                }
                if (e instanceof EffectStatus) {
                    EffectStatus e_ = (EffectStatus) e;
                    fails_.addAllElts(e_.getLocalFailStatus().values());
                }
                if (fails_.isEmpty()) {
                    continue;
                }
                EqList<TeamPosition> listFighters_ = FightOrder.targetsEffect(
                        _fight, _userFighter, e, _diff, _data);
                boolean containsTarget_ = listFighters_
                        .containsObj(_foeFighter);
                boolean containsThrower_ = listFighters_
                        .containsObj(_userFighter);
                if (containsTarget_) {
                    for (String f : fails_) {
                        if (f.isEmpty()) {
                            continue;
                        }
                        checkBoolStrings(_data, _varsDiff,
                                _boolVarsDiffNotSending, f);
                    }
                }
                if (containsThrower_) {
                    for (String f : fails_) {
                        if (f.isEmpty()) {
                            continue;
                        }
                        checkBoolStrings(_data, _varsSame, _boolVarsNotSending,
                                f);
                    }
                }
            }
            for (Effect e : m_.getEffects()) {
                StringList fails_ = new StringList();
                if (e instanceof EffectEndRound) {
                    EffectEndRound e_ = (EffectEndRound) e;
                    fails_.add(e_.getFailEndRound());
                }
                if (fails_.isEmpty()) {
                    continue;
                }
                for (String f : fails_) {
                    if (f.isEmpty()) {
                        continue;
                    }
                    checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                            f);
                }
            }
            for (Effect e : m_.getEffects()) {
                StringList fails_ = new StringList();
                if (e instanceof EffectCounterAttack) {
                    EffectCounterAttack e_ = (EffectCounterAttack) e;
                    fails_.add(e_.getCounterFail());
                    fails_.add(e_.getProtectFail());
                }
                if (fails_.isEmpty()) {
                    continue;
                }
                for (String f : fails_) {
                    if (f.isEmpty()) {
                        continue;
                    }
                    checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                            f);
                }
            }
        }
    }

    private static void checkRateGrowLevel(DataBase _data,
            boolean _validateLevel) {
        if (_validateLevel) {
            long minLevel_ = _data.getMinLevel();
            long maxLevel_ = _data.getMaxLevel();
            for (long levelWin_ = minLevel_; levelWin_ < maxLevel_; levelWin_++) {
                for (long levelLoose_ = minLevel_; levelLoose_ < maxLevel_; levelLoose_++) {
                    for (DifficultyWinPointsFight d : DifficultyWinPointsFight
                            .values()) {
                        StringMap<String> vars_ = new StringMap<String>();
                        vars_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.LEVEL_WINNER), Long.toString(levelWin_));
                        vars_.put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.LEVEL_LOOSER), Long.toString(levelLoose_));
                        NumericableString<Rate> chNum_ = _data
                                .createNumericableString(_data.getRates()
                                        .getVal(d), vars_);
                        chNum_.evaluateExp(false);
                        if (!chNum_.isValid()) {
                            _data.setError(true);
                            return;
                        }
                        Rate rate_ = chNum_.getResult();
                        SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
                        rates_.add(rate_);
                        rates_.add(Rate.zero());
                        rates_.sort();
                        if (rates_.last().isZero()) {
                            _data.setError(true);
                            return;

                        }
                    }
                }
            }
        } else {
            for (DifficultyWinPointsFight d : DifficultyWinPointsFight.values()) {
                StringMap<String> vars_ = new StringMap<String>();
                vars_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.LEVEL_WINNER), LgInt.one().toNumberString());
                vars_.put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.LEVEL_LOOSER), LgInt.one().toNumberString());
                NumericableString<Rate> chNum_ = _data.createNumericableString(
                        _data.getRates().getVal(d), vars_);
                chNum_.evaluateExp(true);
                if (!chNum_.isValid()) {
                    _data.setError(true);
                    return;

                }
            }
        }
    }

    private static void checkBoolStrings(DataBase _data,
            StringMap<String> _variablesSame,
            StringMap<String> _boolVarsSending, String _fail) {
        NumericableString<Boolean> chBool_;
        StringMap<String> map_ = new StringMap<String>();
        map_.putAllMap(_variablesSame);
        map_.putAllMap(_boolVarsSending);
        chBool_ = _data.createBooleanString(_fail, map_);
        checkIfVarPresent(_data, chBool_.beforeEvaluated());
        checkTranslations(_data, chBool_.beforeEvaluated());
        chBool_.evaluateExp(true);
        if (!chBool_.isValid()) {
            _data.setError(true);

        }
    }

    private static void checkBoosts(DataBase _data) {
        long minBoost_ = _data.getMinBoost();
        long defBoost_ = _data.getDefaultBoost();
        long maxBoost_ = _data.getMaxBoost();
        EqList<Rate> ratesBoost_ = new EqList<Rate>();
        for (long b = minBoost_; b <= maxBoost_; b++) {
            String rateBoost_ = _data.getRateBoost();
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.BOOST),
                    Long.toString(b));
            NumericableString<Rate> chNum_ = _data.createNumericableString(
                    rateBoost_, variables_);
            chNum_.evaluateExp(false);
            if (!chNum_.isValid()) {
                _data.setError(true);
                return;
            }
            Rate res_ = chNum_.getResult();
            if (defBoost_ == b) {
                if (!Rate.eq(res_, Rate.one())) {
                    _data.setError(true);
                    return;

                }
            }
            SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
            rates_.add(res_);
            rates_.add(Rate.zero());
            rates_.sort();
            if (rates_.last().isZero()) {
                _data.setError(true);
                return;

            }
            ratesBoost_.add(res_);
        }
        int nbRates_ = ratesBoost_.size();
        for (int i = CustList.SECOND_INDEX; i < nbRates_; i++) {
            if (Rate.greaterEq(ratesBoost_.get(i - 1), ratesBoost_.get(i))) {
                _data.setError(true);
                return;

            }
        }
        ratesBoost_ = new EqList<Rate>();
        for (long b = minBoost_; b <= maxBoost_; b++) {
            String rateBoost_ = _data.getRateBoostCriticalHit();
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.BOOST),
                    Long.toString(b));
            NumericableString<Rate> chNum_ = _data.createNumericableString(
                    rateBoost_, variables_);
            chNum_.evaluateExp(false);
            if (!chNum_.isValid()) {
                _data.setError(true);
                return;
            }
            Rate res_ = chNum_.getResult();
            SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
            rates_.add(res_);
            rates_.add(Rate.zero());
            rates_.sort();
            if (rates_.last().isZero()) {
                _data.setError(true);
                return;

            }
            ratesBoost_.add(res_);
        }
        nbRates_ = ratesBoost_.size();
        for (int i = CustList.SECOND_INDEX; i < nbRates_; i++) {
            if (Rate.greaterEq(ratesBoost_.get(i - 1), ratesBoost_.get(i))) {
                _data.setError(true);
                return;

            }
        }
    }

    private static void checkNumString(DataBase _data,
            StringMap<String> _variablesDiff, String _numString) {
        NumericableString<Rate> num_ = _data.createNumericableString(
                _numString, _variablesDiff);
        checkIfVarPresent(_data, num_.beforeEvaluated());
        checkTranslations(_data, num_.beforeEvaluated());
        num_.evaluateExp(true);
        if (!num_.isValid()) {
            _data.setError(true);

        }
    }

    private static void checkExpGrowth(DataBase _data) {
        long minLevel_ = _data.getMinLevel();
        long maxLevel_ = _data.getMaxLevel();
        String varName_ = StringList
                .concat(DataBase.VAR_PREFIX, Fighter.NIVEAU);
        for (ExpType e : _data.getExpGrowth().getKeys()) {
            String formula_ = _data.getExpGrowth().getVal(e);
            StringMap<String> vars_ = new StringMap<String>();

            vars_.put(varName_, String.valueOf(minLevel_));
            NumericableString<Rate> chNum_ = _data.createNumericableString(
                    formula_, vars_);
            chNum_.evaluateExp(false);
            if (!chNum_.isValid()) {
                _data.setError(true);
                return;
            }
            Rate min_ = chNum_.getResult();
            if (min_.isZeroOrLt()) {
                _data.setError(true);
                return;

            }
            for (long l = minLevel_ + 1; l <= maxLevel_; l++) {
                vars_.put(varName_, String.valueOf(l));
                chNum_ = _data.createNumericableString(formula_, vars_);
                chNum_.evaluateExp(false);
                if (!chNum_.isValid()) {
                    _data.setError(true);
                    return;
                }
                Rate next_ = chNum_.getResult();
                if (!Rate.strGreater(next_, min_)) {
                    _data.setError(true);
                    return;

                }
                min_ = next_;
            }
        }
    }

    private static void checkNumericStringsBalls(DataBase _data, Fight _fight) {
        StringMap<String> vars_;
        vars_ = new StringMap<String>();
        vars_.put(Fight.BASE_CAPT_PK, LgInt.one().toNumberString());
        vars_.put(Fight.RATE_BALL_STATUS, LgInt.one().toNumberString());
        vars_.put(Fight.FOE_PK_MAX_HP, LgInt.one().toNumberString());
        vars_.put(Fight.FOE_PK_REMOTE_HP, LgInt.one().toNumberString());
        String numericExp_ = _data.getCatchingFormula();
        NumericableString<Rate> num_;
        num_ = _data.createNumericableString(numericExp_, vars_);
        num_.evaluateExp(true);
        if (!num_.isValid()) {
            _data.setError(true);
            return;

        }
        for (String b : _data.getItems().getKeys()) {
            Item i_ = _data.getItem(b);
            if (!(i_ instanceof Ball)) {
                continue;
            }
            Ball b_ = (Ball) i_;
            if (b_.getCatchingRate().isEmpty()) {
                continue;
            }
            vars_ = FightRound.calculateCatchingVariables(_fight, false, _data);
            num_ = _data.createNumericableString(b_.getCatchingRate(), vars_);
            checkIfVarPresent(_data, num_.beforeEvaluated());
            checkTranslations(_data, num_.beforeEvaluated());
            num_.evaluateExp(true);
            if (!num_.isValid()) {
                _data.setError(true);
                return;

            }
            vars_ = FightRound.calculateCatchingVariables(_fight, true, _data);
            num_ = _data.createNumericableString(b_.getCatchingRate(), vars_);
            checkIfVarPresent(_data, num_.beforeEvaluated());
            checkTranslations(_data, num_.beforeEvaluated());
            num_.evaluateExp(true);
            if (!num_.isValid()) {
                _data.setError(true);
                return;

            }
            vars_.clear();
        }
        vars_ = FightRound.calculateFleeingVariable(_fight, _data);
        numericExp_ = _data.getFleeingFormula();
        num_ = _data.createNumericableString(numericExp_, vars_);
        num_.evaluateExp(true);
        if (!num_.isValid()) {
            _data.setError(true);

        }
    }

    private static StringMap<String> calculateBooleanValuesForValidation(
            Fight _fight, TeamPosition _lanceur, TeamPosition _cible,
            boolean _sending, DataBase _import) {
        StringMap<String> variables_ = new StringMap<String>();
        if (_sending) {
            StringList immuTypesIndiv_ = _import
                    .getVarParamsMove(Fight.IMMU_TYPE_ATT_COMBATTANT_ENTRANT);
            immuTypesIndiv_.removeDuplicates();
            for (String e : immuTypesIndiv_) {
                variables_
                        .put(StringList.concat(DataBase.VAR_PREFIX,
                                Fight.IMMU_TYPE_ATT_COMBATTANT_ENTRANT,
                                DataBase.SEP_BETWEEN_KEYS, e), _import
                                .getFalseString());
            }
        }
        variables_
                .put(StringList.concat(DataBase.VAR_PREFIX,
                        Fight.PAS_ATTAQUE_INVOC), _import.getFalseString());
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,
                Fight.PAS_ATTAQUES_COPIABLES), _import.getFalseString());
        variables_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,
                _lanceur, _cible, _import));
        return variables_;
    }

    private static void checkIfVarPresent(DataBase _data, String _string) {
        if (_string.isEmpty()) {
            return;
        }
        StringList list_ = StringList.getWordsSeparatorsPrefix(_string,
                DataBase.VAR_PREFIX);
        if (list_.size() != CustList.ONE_ELEMENT) {
            _data.setError(true);

        }
    }

    private static void checkTranslations(DataBase _data, String _string) {
        if (!_data.isCheckTranslation()) {
            return;
        }
        String emptySet_ = StringList.concat(DataBase.EMPTY_STRING,
                String.valueOf(StringList.LEFT_BRACE),
                String.valueOf(StringList.RIGHT_BRACE));
        StringList sets_ = StringList.getTokensSets(StringList.removeStrings(
                _string, emptySet_));
        for (String s : sets_) {
            if (StringList.quickEq(s, emptySet_)) {
                continue;
            }
            if (!s.startsWith(StringList.concat(DataBase.EMPTY_STRING,
                    String.valueOf(StringList.LEFT_BRACE)))) {
                continue;
            }
            String insideSet_ = s.substring(CustList.SECOND_INDEX,
                    s.length() - 1);
            StringList words_ = StringList.getWordsSeparators(insideSet_);
            for (String w : words_) {
                if (!StringList.isWord(w)) {
                    if (w.isEmpty()) {
                        continue;
                    }
                    if (StringList.quickEq(w,
                            String.valueOf(_data.getSepartorSetChar()))) {
                        continue;
                    }
                    _data.setError(true);
                    return;

                }
                if (!_data.isTranslatable(w)) {
                    _data.setError(true);
                    return;

                }
            }
        }
    }
}
