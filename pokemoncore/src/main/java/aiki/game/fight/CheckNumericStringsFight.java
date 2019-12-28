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
import aiki.fight.pokemon.PokemonData;
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
import code.maths.litteral.EvolvedBooleanString;
import code.maths.litteral.EvolvedNumString;
import code.util.CustList;
import code.util.EqList;
import code.util.SortableCustList;
import code.util.StringList;
import code.util.StringMap;

public final class CheckNumericStringsFight {

    private CheckNumericStringsFight() {
    }

    public static void validateNumericBooleanStrings(DataBase _data) {
        // NumericString.setCheckSyntax(true);
        checkBoosts(_data);
        checkRateGrowLevel(_data);
        // String varRegExp_ = StringList.BOUNDS+DataBase.VAR_PREFIX;
        checkExpGrowth(_data);
        Difficulty diff_ = new Difficulty();
        Player user_ = new Player(DataBase.EMPTY_STRING, null, diff_, false,
                _data);
        WildPk pk_ = new WildPk();
        pk_.setName(_data.getPokedex().firstKey());
        PokemonData pokemonData_ = _data.getPokedex().firstValue();
        if (!Statistic.equalsSet(pokemonData_.getStatistics().getKeys(),
                Statistic.getStatisticsWithBase())) {
            _data.setError(true);
            return;
        }
        pk_.setAbility(_data.getAbilities().getKeys().first());
        StringList moves_ = new StringList();
        StringMap<Short> movesPp_ = new StringMap<Short>();
        for (String m: pokemonData_.getMovesAtLevel(pk_.getLevel(), _data.getNbMaxMoves())) {
            MoveData fAtt_ = _data.getMove(m);
            if (fAtt_ == null) {
                continue;
            }
            movesPp_.put(m,fAtt_.getPp());
            moves_.add(m);
        }
        if (moves_.isEmpty()) {
            _data.setError(true);
            return;
        }
        PokemonPlayer pkUser_ = new PokemonPlayer(pk_, _data,movesPp_);
        user_.getTeam().add(pkUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(_data.getPokedex().getKeys().first());
        foePokemon_.setAbility(_data.getAbilities().getKeys().first());
        foePokemon_.setMoves(moves_);
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
        EvolvedNumString num_;
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
        checkValidNumeric(_data, num_);
    }

    private static void checkValidNumeric(DataBase _data, EvolvedNumString _num) {
        if (!_num.isValid()) {
            _data.setError(true);
        }
    }

    private static void checkCombosStr(DataBase _data,
            StringMap<String> _varsDiff, StringMap<String> _varsSame,
            StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending) {
        for (EffectCombo e : _data.getCombos().getEffects().values()) {
            for (EffectEndRound effEndRound_ : e.getEffectEndRound()) {
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
                checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                        e.getFailEndRound());
            }
            String fail_ = s.getFail();
            if (!fail_.isEmpty()) {
                EvolvedBooleanString chBool_;
                StringMap<String> map_ = new StringMap<String>();
                map_.putAllMap(_varsFighter);
                chBool_ = _data.createBooleanString(fail_, map_);
                checkTranslations(_data, chBool_.beforeEvaluated());
                chBool_.evaluateExp(true);
                checkValidBool(_data, chBool_);
            }
        }
    }

    private static void checkValidBool(DataBase _data, EvolvedBooleanString _chBool) {
        if (!_chBool.isValid()) {
            _data.setError(true);
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
                    StringMap<String> loc_ = getVariablesMultPower(_data, _fight, _userFighter, _varsDiff, _varsFighter);
                    checkNumGeneString(_data, loc_, o_.getMultPower());
                }
                if (!o_.getMultDamage().isEmpty()) {
                    StringMap<String> loc_ = getVariablesMultDamage(_data, _fight, _userFighter, _varsDiff);
                    checkNumGeneString(_data, loc_, o_.getMultDamage());
                }
                for (Statistic s : o_.getMultStat().getKeys()) {
                    String str_ = o_.getMultStat().getVal(s);
                    StringMap<String> loc_ = getVariablesStatis(_data, _fight, _userFighter, _varsDiff, _varsFighter, s);
                    checkNumGeneString(_data, loc_, str_);
                }
                for (String v : o_.getFailStatus().values()) {
                    checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                            v);
                    checkBoolStrings(_data, _varsSame, _boolVarsNotSending, v);
                }
                for (EffectEndRound e : o_.getEffectEndRound()) {
                    checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                            e.getFailEndRound());
                    checkBoolStrings(_data, _varsSame, _boolVarsNotSending,
                            e.getFailEndRound());
                }
                for (EffectWhileSending e : o_.getEffectSending()) {
                    processWhenSend(_data, _diff, _fight, _userFighter, _foeFighter, _varsDiff, _varsSame, _boolVarsNotSending, _boolVarsDiffNotSending, e);
                }
            }
        }
    }

    private static void processWhenSend(DataBase _data, Difficulty _diff, Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter, StringMap<String> _varsDiff, StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending, StringMap<String> _boolVarsDiffNotSending, EffectWhileSending _e) {
        if (_e instanceof EffectWhileSendingWithStatistic) {
            EffectWhileSendingWithStatistic eff_ = (EffectWhileSendingWithStatistic) _e;
            StringList fails_ = new StringList();
            EffectStatistic e_ = eff_.getEffect();
            fails_.addAllElts(e_.getLocalFailStatis().values());
            fails_.addAllElts(e_.getLocalFailSwapBoostStatis()
                    .values());
            EqList<TeamPosition> listFighters_ = addIfEmpty(FightOrder
                    .targetsEffect(_fight, _userFighter, e_, _diff,
                            _data),_foeFighter);
            checkFailsWhenFighter(_data, _foeFighter, _varsDiff, _boolVarsDiffNotSending, fails_, listFighters_);
            checkFailsWhenFighter(_data, _userFighter, _varsSame, _boolVarsNotSending, fails_, listFighters_);
        }
    }

    private static StringMap<String> getVariablesStatis(DataBase _data, Fight _fight, TeamPosition _userFighter, StringMap<String> _varsDiff, StringMap<String> _varsFighter, Statistic _s) {
        StringMap<String> loc_;
        boolean special_ = isSpecialStat(_s);
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
                    Fight.ATTAQUE_TYPES), StringList.join(types_, _data
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
        return loc_;
    }

    private static StringMap<String> getVariablesMultPower(DataBase _data, Fight _fight, TeamPosition _userFighter, StringMap<String> _varsDiff, StringMap<String> _varsFighter) {
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
                Fight.ATTAQUE_TYPES), StringList.join(types_, _data
                .getSepartorSetChar()));
        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                Fight.ATTAQUE_NOM), _data.getDefaultMove());
        loc_.put(StringList.concat(DataBase.VAR_PREFIX,
                Fight.PUISSANCE_BASE), DataBase.getDefaultPower()
                .toNumberString());
        return loc_;
    }

    private static void checkAbilitiesStr(DataBase _data, Difficulty _diff,
            Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter,
            StringMap<String> _varsDiff, StringMap<String> _varsFighter,
            StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending) {
        for (String ab_ : _data.getAbilities().getKeys()) {
            AbilityData a = _data.getAbility(ab_);
            if (!a.getMultPower().isEmpty()) {
                StringMap<String> loc_ = getVariablesMultPower(_data, _fight, _userFighter, _varsDiff, _varsFighter);
                checkNumGeneString(_data, loc_, a.getMultPower());
            }
            if (!a.getMultDamage().isEmpty()) {
                StringMap<String> loc_ = getVariablesMultDamage(_data, _fight, _userFighter, _varsDiff);
                checkNumGeneString(_data, loc_, a.getMultDamage());
            }
            for (Statistic s : a.getMultStat().getKeys()) {
                String str_ = a.getMultStat().getVal(s);
                StringMap<String> loc_ = getVariablesStatis(_data, _fight, _userFighter, _varsDiff, _varsFighter, s);
                checkNumGeneString(_data, loc_, str_);
            }
            checkFails(_data,_varsDiff, _boolVarsDiffNotSending,a.getFailStatus().values());
            for (EffectEndRound e : a.getEffectEndRound()) {
                checkBoolStrings(_data, _varsDiff, _boolVarsDiffNotSending,
                        e.getFailEndRound());
                checkBoolStrings(_data, _varsSame, _boolVarsNotSending,
                        e.getFailEndRound());
            }
            for (EffectWhileSending e : a.getEffectSending()) {
                processWhenSend(_data, _diff, _fight, _userFighter, _foeFighter, _varsDiff, _varsSame, _boolVarsNotSending, _boolVarsDiffNotSending, e);
            }
        }
    }

    private static StringMap<String> getVariablesMultDamage(DataBase _data, Fight _fight, TeamPosition _userFighter, StringMap<String> _varsDiff) {
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
                Fight.ATTAQUE_TYPES), StringList.join(types_, _data
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
        return loc_;
    }

    private static boolean isSpecialStat(Statistic _s) {
        return _s.isSpecial();
    }

    private static void checkFails(DataBase _data, StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending, CustList<String> _fails) {
        for (String f : _fails) {
            checkBoolStrings(_data, _varsSame,
                    _boolVarsNotSending, f);
        }
    }

    private static void checkMovesStr(DataBase _data, Difficulty _diff,
            Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter,
            StringMap<String> _varsDiff, StringMap<String> _varsSame,
            StringMap<String> _boolVarsNotSending,
            StringMap<String> _boolVarsDiffNotSending,
            StringMap<String> _boolVarsSending, StringMap<String> _varsSending) {
        EvolvedNumString chNum_;
        for (String m : _data.getMoves().getKeys()) {
            MoveData m_ = _data.getMove(m);
            int index_ = m_.indexOfPrimaryEffect();
            if (index_ < 0) {
                _data.setError(true);
                continue;
            }
            for (TeamPosition t : addIfEmpty(FightOrder.targetsEffect(_fight,
                    _userFighter, m_.getEffet(index_),
                    _diff, _data),_foeFighter)) {
                if (TeamPosition.eq(t, _userFighter)) {
                    chNum_ = _data.createNumericableString(m_.getAccuracy(),
                            _varsSame);
                } else {
                    chNum_ = _data.createNumericableString(m_.getAccuracy(),
                            _varsDiff);
                }
                checkTranslations(_data, chNum_.beforeEvaluated());
                chNum_.evaluateExp(true);
                checkValidNumeric(_data, chNum_);
            }
            for (Effect e : m_.getEffects()) {
                String fail_ = e.getFail();
                if (fail_.isEmpty()) {
                    continue;
                }
                for (TeamPosition t : addIfEmpty(FightOrder.targetsEffect(_fight,
                        _userFighter, e, _diff, _data),_foeFighter)) {
                    EvolvedBooleanString chBool_;
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
                    checkTranslations(_data, chBool_.beforeEvaluated());
                    chBool_.evaluateExp(true);
                    checkValidBool(_data, chBool_);
                }
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage eff_ = (EffectDamage) e;
                if (!eff_.getConstDamage() && !eff_.getPower().isEmpty()) {
                    chNum_ = _data.createNumericableString(eff_.getPower(),
                            _varsDiff);
                    checkTranslations(_data, chNum_.beforeEvaluated());
                    chNum_.evaluateExp(true);
                    checkValidNumeric(_data, chNum_);
                }
                for (String e2_ : eff_.getDamageLaw().events()) {
                    // accept empty strings
                    checkNumGeneString(_data, _varsDiff, e2_);
                }
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectTeamWhileSendFoe)) {
                    continue;
                }
                EffectTeamWhileSendFoe e_ = (EffectTeamWhileSendFoe) e;
                String fail_ = e_.getFailSending();
                checkBoolStrings(_data, _varsSame, _boolVarsSending, fail_);
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectTeamWhileSendFoe)) {
                    continue;
                }
                EffectTeamWhileSendFoe e_ = (EffectTeamWhileSendFoe) e;
                String string_ = e_.getDamageRateAgainstFoe();
                StringMap<String> map_ = new StringMap<String>();
                map_.putAllMap(_varsSame);
                map_.putAllMap(_varsSending);
                checkNumGeneString(_data, map_, string_);
            }
            for (Effect e : m_.getEffects()) {
                if (!(e instanceof EffectFullHpRate)) {
                    continue;
                }
                EffectFullHpRate e_ = (EffectFullHpRate) e;
                String string_ = e_.getRestoredHp();
                checkNumGeneString(_data, _varsSame, string_);
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
                EqList<TeamPosition> listFighters_ = addIfEmpty(FightOrder.targetsEffect(
                        _fight, _userFighter, e, _diff, _data),_foeFighter);
                checkFailsWhenFighter(_data, _foeFighter, _varsDiff, _boolVarsDiffNotSending, fails_, listFighters_);
                checkFailsWhenFighter(_data, _userFighter, _varsSame, _boolVarsNotSending, fails_, listFighters_);
            }
            for (Effect e : m_.getEffects()) {
                StringList fails_ = new StringList();
                if (e instanceof EffectEndRound) {
                    EffectEndRound e_ = (EffectEndRound) e;
                    fails_.add(e_.getFailEndRound());
                }
                checkFails(_data, _varsDiff, _boolVarsDiffNotSending, fails_);
            }
            for (Effect e : m_.getEffects()) {
                StringList fails_ = new StringList();
                if (e instanceof EffectCounterAttack) {
                    EffectCounterAttack e_ = (EffectCounterAttack) e;
                    fails_.add(e_.getCounterFail());
                    fails_.add(e_.getProtectFail());
                }
                checkFails(_data, _varsDiff, _boolVarsDiffNotSending, fails_);
            }
        }
    }

    private static void checkFailsWhenFighter(DataBase _data, TeamPosition _userFighter, StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending, StringList _fails, EqList<TeamPosition> _listFighters) {
        boolean containsFighter_ = _listFighters
                .containsObj(_userFighter);
        if (containsFighter_) {
            checkFails(_data, _varsSame, _boolVarsNotSending, _fails);
        }
    }

    private static void checkRateGrowLevel(DataBase _data) {
        for (DifficultyWinPointsFight d : DifficultyWinPointsFight.values()) {
            StringMap<String> vars_ = new StringMap<String>();
            vars_.put(StringList.concat(DataBase.VAR_PREFIX,
                    Fight.LEVEL_WINNER), LgInt.one().toNumberString());
            vars_.put(StringList.concat(DataBase.VAR_PREFIX,
                    Fight.LEVEL_LOOSER), LgInt.one().toNumberString());
            EvolvedNumString chNum_ = _data.createNumericableString(
                    _data.getRates().getVal(d), vars_);
            chNum_.evaluateExp(true);
            checkValidNumeric(_data, chNum_);
        }
    }

    private static void checkBoolStrings(DataBase _data,
            StringMap<String> _variablesSame,
            StringMap<String> _boolVarsSending, String _fail) {
        if (_fail.isEmpty()) {
            return;
        }
        EvolvedBooleanString chBool_;
        StringMap<String> map_ = new StringMap<String>();
        map_.putAllMap(_variablesSame);
        map_.putAllMap(_boolVarsSending);
        chBool_ = _data.createBooleanString(_fail, map_);
        checkTranslations(_data, chBool_.beforeEvaluated());
        chBool_.evaluateExp(true);
        checkValidBool(_data, chBool_);
    }

    private static void checkBoosts(DataBase _data) {
        long minBoost_ = _data.getMinBoost();
        long defBoost_ = _data.getDefaultBoost();
        long maxBoost_ = _data.getMaxBoost();
        String rateBoost_ = _data.getRateBoost();
        EqList<Rate> ratesBoost_ = new EqList<Rate>();
        for (long b = minBoost_; b <= maxBoost_; b++) {
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.BOOST),
                    Long.toString(b));
            EvolvedNumString chNum_ = _data.createNumericableString(
                    rateBoost_, variables_);
            chNum_.evaluateExp(false);
            Rate res_ = defRate(chNum_,_data);
            if (defBoost_ == b) {
                if (!Rate.eq(res_, Rate.one())) {
                    _data.setError(true);
                }
            }
            checkIfNeg(_data, ratesBoost_, res_);
        }
        checkIncr(_data, ratesBoost_);
        rateBoost_ = _data.getRateBoostCriticalHit();
        ratesBoost_ = new EqList<Rate>();
        for (long b = minBoost_; b <= maxBoost_; b++) {
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringList.concat(DataBase.VAR_PREFIX, Fight.BOOST),
                    Long.toString(b));
            EvolvedNumString chNum_ = _data.createNumericableString(
                    rateBoost_, variables_);
            chNum_.evaluateExp(false);
            Rate res_ = defRate(chNum_,_data);
            checkIfNeg(_data, ratesBoost_, res_);
        }
        checkIncr(_data, ratesBoost_);
    }

    private static void checkIfNeg(DataBase _data, EqList<Rate> _ratesBoost, Rate _res) {
        SortableCustList<Rate> rates_ = new SortableCustList<Rate>();
        rates_.add(_res);
        rates_.add(Rate.zero());
        rates_.sort();
        if (rates_.last().isZero()) {
            _data.setError(true);
        }
        _ratesBoost.add(_res);
    }

    private static Rate defRate(EvolvedNumString _e, DataBase _data) {
        if (!_e.isValid()) {
            _data.setError(true);
            return Rate.zero();
        }
        return _e.getResult();
    }
    private static void checkIncr(DataBase _data, EqList<Rate> _ratesBoost) {
        int nbRates_ = _ratesBoost.size();
        for (int i = CustList.SECOND_INDEX; i < nbRates_; i++) {
            if (Rate.greaterEq(_ratesBoost.get(i - 1), _ratesBoost.get(i))) {
                _data.setError(true);
            }
        }
    }

    private static void checkNumGeneString(DataBase _data,
                                       StringMap<String> _variablesDiff, String _numString) {
        if (_numString.isEmpty()) {
            return;
        }
        checkNumString(_data,_variablesDiff,_numString);
    }
    private static void checkNumString(DataBase _data,
            StringMap<String> _variablesDiff, String _numString) {
        EvolvedNumString num_ = _data.createNumericableString(
                _numString, _variablesDiff);
        checkTranslations(_data, num_.beforeEvaluated());
        num_.evaluateExp(true);
        checkValidNumeric(_data, num_);
    }

    private static void checkExpGrowth(DataBase _data) {
        for (ExpType e : _data.getExpGrowth().getKeys()) {
            String formula_ = _data.getExpGrowth().getVal(e);
            checkLevels(_data, formula_);
        }
    }

    private static void checkLevels(DataBase _data, String _formula) {
        StringMap<String> vars_ = new StringMap<String>();
        String varName_ = StringList
                .concat(DataBase.VAR_PREFIX, Fighter.NIVEAU);
        long minLevel_ = _data.getMinLevel();
        long maxLevel_ = _data.getMaxLevel();
        Rate min_ = Rate.zero();
        for (long l = minLevel_; l <= maxLevel_; l++) {
            vars_.put(varName_, String.valueOf(l));
            EvolvedNumString chNum_;
            chNum_ = _data.createNumericableString(_formula, vars_);
            chNum_.evaluateExp(false);
            Rate next_ = defRate(chNum_,_data);
            if (!Rate.strGreater(next_, min_)) {
                _data.setError(true);
            }
            min_ = next_;
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
        EvolvedNumString num_;
        num_ = _data.createNumericableString(numericExp_, vars_);
        num_.evaluateExp(true);
        checkValidNumeric(_data, num_);
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
            checkTranslations(_data, num_.beforeEvaluated());
            num_.evaluateExp(true);
            checkValidNumeric(_data, num_);
            vars_ = FightRound.calculateCatchingVariables(_fight, true, _data);
            num_ = _data.createNumericableString(b_.getCatchingRate(), vars_);
            checkTranslations(_data, num_.beforeEvaluated());
            num_.evaluateExp(true);
            checkValidNumeric(_data, num_);
            vars_.clear();
        }
        vars_ = FightRound.calculateFleeingVariable(_fight, _data);
        numericExp_ = _data.getFleeingFormula();
        num_ = _data.createNumericableString(numericExp_, vars_);
        num_.evaluateExp(true);
        checkValidNumeric(_data, num_);
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

    private static EqList<TeamPosition> addIfEmpty(CustList<TeamPosition> _list, TeamPosition _foe) {
        if (!_list.isEmpty()) {
            return new EqList<TeamPosition>(_list);
        }
        _list.add(_foe);
        return new EqList<TeamPosition>(_list);
    }
    private static void checkTranslations(DataBase _data, String _string) {
        _data.checkTranslations(_string);
    }
}
