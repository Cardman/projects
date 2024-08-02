package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.*;
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
import aiki.util.TeamPositionList;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteral.EvolvedBooleanString;
import code.maths.litteral.EvolvedNumString;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class CheckNumericStringsFight {

    private final StringMap<String> variablesDiff;
    private final StringMap<String> variablesFighter;
    private final StringMap<String> variablesSame;
    private final StringMap<String> boolVarsNotSending;
    private final StringMap<String> boolVarsDiffNotSending;
    private final StringMap<String> boolVarsSending;
    private final StringMap<String> varsSending;
    private final TeamPosition userFighter;
    private final TeamPosition foeFighter;
    private final Fight fight;
    private final DataBase data;
    private final StringMap<String> variablesMoves;
    private final StringMap<String> variablesWithStat;
    private final StringMap<String> variablesFull;

    private CheckNumericStringsFight(Fight _fight, TeamPosition _userFighter, TeamPosition _foeFighter, DataBase _data) {
        fight = _fight;
        data = _data;
        userFighter = _userFighter;
        foeFighter = _foeFighter;
        variablesDiff = FightValues.calculateValues(_fight, _userFighter,
                _foeFighter, _data);
        variablesMoves = new StringMap<String>(variablesDiff);
        FightValues.completeValuesWithThrower(_fight, _userFighter, variablesMoves,_data);
        FightValues.completeValuesWithMoveInfo(data.getDefMove(), variablesMoves, Rate.one(), _data, new StringList(), _data.getDefCategory());
        variablesWithStat = FightValues.calculateValuesWithStat(variablesMoves,Rate.one(),Rate.one(),Rate.one(),_data);
        variablesFull = new StringMap<String>(variablesWithStat);
        FightValues.completeValuesWithRemaining(variablesFull,Rate.one(),LgInt.one(),_data);
        variablesFighter = FightValues.calculateValuesFighter(_fight,
                _userFighter, _data);
        variablesSame = FightValues.calculateValues(_fight, _userFighter,
                _userFighter, _data);
        varsSending = FightValues.calculateSendingVariables(
                _fight, _userFighter, _data);
        boolVarsNotSending = calculateBooleanValuesForValidation(_fight,
                _userFighter, _userFighter, false, _data);
        boolVarsDiffNotSending = calculateBooleanValuesForValidation(_fight,
                _userFighter, _foeFighter, false, _data);
        boolVarsSending = calculateBooleanValuesForValidation(_fight,
                _userFighter, _userFighter, true, _data);
        boolVarsSending.putAllMap(varsSending);
    }

    public static void validateNumericBooleanStrings(DataBase _data) {
        // NumericString.setCheckSyntax(true);
        checkBoosts(_data);
        checkRateGrowLevel(_data);
        // String varRegExp_ = StringList.BOUNDS+DataBase.VAR_PREFIX;
        checkExpGrowth(_data);
        Difficulty diff_ = new Difficulty();
        Player user_ = Player.build(diff_, false,
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
        CheckNumericStringsFight checker_ = new CheckNumericStringsFight(fight_,userFighter_,foeFighter_, _data);
        checker_.checkMovesStr(diff_);
        checker_.checkAbilitiesStr(diff_);
        checker_.checkCombosStr();
        checker_.checkStatusStr();
        checker_.checkItemsStr(diff_);
        FightFacade.endFight(fight_);
        FightFacade.initFight(fight_, user_, diff_, pk_, _data);
        checker_.checkNumericStringsBalls();
        checker_.checkDamageStr();
        // FightRound.calculateCatchingRate
        // NumericString.setCheckSyntax(false);
    }

    private void checkDamageStr() {
        String numericExp_;
        numericExp_ = data.getDamageFormula();
        EvolvedNumString num_;
        num_ = data.createNumericableString(numericExp_, variablesWithStat);
        num_.evaluateExp(true);
        checkValidNumeric(data, num_);
    }

    private static void checkValidNumeric(DataBase _data, EvolvedNumString _num) {
        if (!_num.isValid()) {
            _data.setError(true);
        }
    }

    private void checkCombosStr() {
        for (EffectCombo e : data.getCombos().getEffects().values()) {
            for (EffectEndRound effEndRound_ : e.getEffectEndRound()) {
                checkBoolStrings(data, variablesDiff, boolVarsDiffNotSending,
                        effEndRound_.getFailEndRound());
                checkBoolStrings(data, variablesSame, boolVarsNotSending,
                        effEndRound_.getFailEndRound());
            }
        }
    }

    private void checkStatusStr() {
        for (Status s : data.getStatus().values()) {
            for (EffectEndRound e : s.getEffectEndRound()) {
                checkBoolStrings(data, variablesDiff, boolVarsDiffNotSending,
                        e.getFailEndRound());
            }
            String fail_ = s.getFail();
            if (!fail_.isEmpty()) {
                EvolvedBooleanString chBool_;
                StringMap<String> map_ = new StringMap<String>();
                map_.putAllMap(variablesFighter);
                chBool_ = data.createBooleanString(fail_, map_);
                checkTranslations(data, chBool_.beforeEvaluated());
                chBool_.evaluateExp(true);
                checkValidBool(data, chBool_);
            }
        }
    }

    private static void checkValidBool(DataBase _data, EvolvedBooleanString _chBool) {
        if (!_chBool.isValid()) {
            _data.setError(true);
        }
    }

    private void checkItemsStr(Difficulty _diff) {
        for (Item o : data.getItems().values()) {
            if (!(o instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle o_ = (ItemForBattle) o;
            if (!o_.getMultPower().isEmpty()) {
                StringMap<String> loc_ = getVariablesMultPower();
                checkNumGeneString(data, loc_, o_.getMultPower());
            }
            if (!o_.getMultDamage().isEmpty()) {
                checkNumGeneString(data, variablesFull, o_.getMultDamage());
            }
            for (Statistic s : o_.getMultStat().getKeys()) {
                String str_ = o_.getMultStat().getVal(s);
                StringMap<String> loc_ = getVariablesStatis(s);
                checkNumGeneString(data, loc_, str_);
            }
            for (String v : o_.getFailStatus().values()) {
                checkBoolStrings(data, variablesDiff, boolVarsDiffNotSending,
                        v);
                checkBoolStrings(data, variablesSame, boolVarsNotSending, v);
            }
            for (EffectEndRound e : o_.getEffectEndRound()) {
                checkBoolStrings(data, variablesDiff, boolVarsDiffNotSending,
                        e.getFailEndRound());
                checkBoolStrings(data, variablesSame, boolVarsNotSending,
                        e.getFailEndRound());
            }
            for (EffectWhileSendingWithStatistic e : o_.getEffectSending()) {
                processWhenSend(_diff, e);
            }
        }
    }

    private void processWhenSend(Difficulty _diff,
                                 EffectWhileSendingWithStatistic _e) {
        StringList fails_ = new StringList();
        EffectStatistic e_ = _e.getEffect();
        if (e_ == null) {
            return;
        }
        fails_.addAllElts(e_.getLocalFailStatis().values());
        fails_.addAllElts(e_.getLocalFailSwapBoostStatis()
                .values());
        TeamPositionList listFighters_ = addIfEmpty(FightOrder
                .targetsEffect(fight, userFighter, e_, _diff,
                        data),foeFighter);
        checkFailsWhenFighter(data, foeFighter, variablesDiff, boolVarsDiffNotSending, fails_, listFighters_);
        checkFailsWhenFighter(data, userFighter, variablesSame, boolVarsNotSending, fails_, listFighters_);
    }

    private StringMap<String> getVariablesStatis(Statistic _s) {
        StringMap<String> loc_;
        boolean special_ = isSpecialStat(_s);
        if (special_) {
            loc_ = new StringMap<String>(variablesFighter);
        } else {
            loc_ = new StringMap<String>(variablesMoves);
            loc_.putAllMap(variablesFighter);
        }
        return loc_;
    }

    private StringMap<String> getVariablesMultPower() {
        StringMap<String> loc_ = new StringMap<String>(variablesMoves);
        loc_.putAllMap(variablesFighter);
        return loc_;
    }

    private void checkAbilitiesStr(Difficulty _diff) {
        for (String ab_ : data.getAbilities().getKeys()) {
            AbilityData a = data.getAbility(ab_);
            if (!a.getMultPower().isEmpty()) {
                StringMap<String> loc_ = getVariablesMultPower();
                checkNumGeneString(data, loc_, a.getMultPower());
            }
            if (!a.getMultDamage().isEmpty()) {
                checkNumGeneString(data, variablesFull, a.getMultDamage());
            }
            for (Statistic s : a.getMultStat().getKeys()) {
                String str_ = a.getMultStat().getVal(s);
                StringMap<String> loc_ = getVariablesStatis(s);
                checkNumGeneString(data, loc_, str_);
            }
            checkFails(data,variablesDiff, boolVarsDiffNotSending,a.getFailStatus().values());
            for (EffectEndRound e : a.getEffectEndRound()) {
                checkBoolStrings(data, variablesDiff, boolVarsDiffNotSending,
                        e.getFailEndRound());
                checkBoolStrings(data, variablesSame, boolVarsNotSending,
                        e.getFailEndRound());
            }
            for (EffectWhileSendingWithStatistic e : a.getEffectSending()) {
                processWhenSend(_diff, e);
            }
        }
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

    private void checkMovesStr(Difficulty _diff) {
        for (String m : data.getMoves().getKeys()) {
            MoveData m_ = data.getMove(m);
            int index_ = m_.indexOfPrimaryEffect();
            if (index_ < 0) {
                data.setError(true);
                continue;
            }
            checkMovesStrAccuracy(_diff, m_, index_);
            checkMovesStrEffectFail(_diff, m_);
            checkMovesStrEffectDamage(m_);
            checkMovesStrEffectTeamWhileSendingFoe(m_);
            checkMovesStrEffectFullHpRate(m_);
            checkMovesStrEffectStatisticStatus(_diff, m_);
            checkMovesStrEffectEndRound(m_);
            checkMovesStrEffectCounterAttack(m_);
        }
    }

    private void checkMovesStrEffectCounterAttack(MoveData _m) {
        for (Effect e : _m.getEffects()) {
            StringList fails_ = new StringList();
            if (e instanceof EffectCounterAttack) {
                EffectCounterAttack e_ = (EffectCounterAttack) e;
                fails_.add(e_.getCounterFail());
                fails_.add(e_.getProtectFail());
            }
            checkFails(data, variablesDiff, boolVarsDiffNotSending, fails_);
        }
    }

    private void checkMovesStrEffectEndRound(MoveData _m) {
        for (Effect e : _m.getEffects()) {
            StringList fails_ = new StringList();
            if (e instanceof EffectEndRound) {
                EffectEndRound e_ = (EffectEndRound) e;
                fails_.add(e_.getFailEndRound());
            }
            checkFails(data, variablesDiff, boolVarsDiffNotSending, fails_);
        }
    }

    private void checkMovesStrEffectStatisticStatus(Difficulty _diff, MoveData _m) {
        for (Effect e : _m.getEffects()) {
            if (!(e instanceof EffectCommonStatistics)) {
                continue;
            }
            EffectCommonStatistics eff_ = (EffectCommonStatistics) e;
            for (String e2_ : eff_.getCommonValue().values()) {
                checkNumString(data, variablesDiff, e2_);
            }
        }
        for (Effect e : _m.getEffects()) {
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
            TeamPositionList listFighters_ = addIfEmpty(FightOrder.targetsEffect(
                    fight, userFighter, e, _diff, data),foeFighter);
            checkFailsWhenFighter(data, foeFighter, variablesDiff, boolVarsDiffNotSending, fails_, listFighters_);
            checkFailsWhenFighter(data, userFighter, variablesSame, boolVarsNotSending, fails_, listFighters_);
        }
    }

    private void checkMovesStrEffectFullHpRate(MoveData _m) {
        for (Effect e : _m.getEffects()) {
            if (!(e instanceof EffectFullHpRate)) {
                continue;
            }
            EffectFullHpRate e_ = (EffectFullHpRate) e;
            String string_ = e_.getRestoredHp();
            checkNumGeneString(data, variablesSame, string_);
        }
    }

    private void checkMovesStrEffectTeamWhileSendingFoe(MoveData _m) {
        for (Effect e : _m.getEffects()) {
            if (!(e instanceof EffectTeamWhileSendFoe)) {
                continue;
            }
            EffectTeamWhileSendFoe e_ = (EffectTeamWhileSendFoe) e;
            String fail_ = e_.getFailSending();
            checkBoolStrings(data, variablesSame, boolVarsSending, fail_);
        }
        for (Effect e : _m.getEffects()) {
            if (!(e instanceof EffectTeamWhileSendFoe)) {
                continue;
            }
            EffectTeamWhileSendFoe e_ = (EffectTeamWhileSendFoe) e;
            String string_ = e_.getDamageRateAgainstFoe();
            StringMap<String> map_ = new StringMap<String>();
            map_.putAllMap(variablesSame);
            map_.putAllMap(varsSending);
            checkNumGeneString(data, map_, string_);
        }
    }

    private void checkMovesStrEffectDamage(MoveData _m) {
        for (Effect e : _m.getEffects()) {
            if (!(e instanceof EffectDamage)) {
                continue;
            }
            EffectDamage eff_ = (EffectDamage) e;
            EvolvedNumString chNum_;
            if (!eff_.getConstDamage() && !eff_.getPower().isEmpty()) {
                chNum_ = data.createNumericableString(eff_.getPower(),
                        variablesDiff);
                checkTranslations(data, chNum_.beforeEvaluated());
                chNum_.evaluateExp(true);
                checkValidNumeric(data, chNum_);
            }
            for (String e2_ : eff_.getDamageLaw().events()) {
                // accept empty strings
                checkNumGeneString(data, variablesDiff, e2_);
            }
        }
    }

    private void checkMovesStrEffectFail(Difficulty _diff, MoveData _m) {
        for (Effect e : _m.getEffects()) {
            String fail_ = e.getFail();
            if (fail_.isEmpty()) {
                continue;
            }
            for (TeamPosition t : addIfEmpty(FightOrder.targetsEffect(fight,
                    userFighter, e, _diff, data),foeFighter)) {
                EvolvedBooleanString chBool_;
                if (TeamPosition.eq(t, userFighter)) {
                    StringMap<String> map_ = new StringMap<String>();
                    map_.putAllMap(variablesSame);
                    map_.putAllMap(boolVarsNotSending);
                    chBool_ = data.createBooleanString(fail_, map_);
                } else {
                    StringMap<String> map_ = new StringMap<String>();
                    map_.putAllMap(variablesDiff);
                    map_.putAllMap(boolVarsDiffNotSending);
                    chBool_ = data.createBooleanString(fail_, map_);
                }
                checkTranslations(data, chBool_.beforeEvaluated());
                chBool_.evaluateExp(true);
                checkValidBool(data, chBool_);
            }
        }
    }

    private void checkMovesStrAccuracy(Difficulty _diff, MoveData _m, int _index) {
        for (TeamPosition t : addIfEmpty(FightOrder.targetsEffect(fight,
                userFighter, _m.getEffet(_index),
                _diff, data),foeFighter)) {
            EvolvedNumString chNum_;
            if (TeamPosition.eq(t, userFighter)) {
                chNum_ = data.createNumericableString(_m.getAccuracy(),
                        variablesSame);
            } else {
                chNum_ = data.createNumericableString(_m.getAccuracy(),
                        variablesDiff);
            }
            checkTranslations(data, chNum_.beforeEvaluated());
            chNum_.evaluateExp(true);
            checkValidNumeric(data, chNum_);
        }
    }

    private static void checkFailsWhenFighter(DataBase _data, TeamPosition _userFighter, StringMap<String> _varsSame, StringMap<String> _boolVarsNotSending,
                                              StringList _fails, TeamPositionList _listFighters) {
        boolean containsFighter_ = _listFighters
                .containsObj(_userFighter);
        if (containsFighter_) {
            checkFails(_data, _varsSame, _boolVarsNotSending, _fails);
        }
    }

    private static void checkRateGrowLevel(DataBase _data) {
        checkRateGrowLevel(_data, DifficultyWinPointsFight.TRES_FACILE);
        checkRateGrowLevel(_data, DifficultyWinPointsFight.FACILE);
        checkRateGrowLevel(_data, DifficultyWinPointsFight.DIFFICILE);
        checkRateGrowLevel(_data, DifficultyWinPointsFight.TRES_DIFFICILE);
    }

    private static void checkRateGrowLevel(DataBase _data, DifficultyWinPointsFight _d) {
        String varPref_ = StringUtil.concat(_data.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringUtil.concat(varPref_,
                _data.levelWinner()), LgInt.one().toNumberString());
        vars_.put(StringUtil.concat(varPref_,
                _data.levelLooser()), LgInt.one().toNumberString());
        EvolvedNumString chNum_ = _data.createNumericableString(
                _data.getRates().getVal(_d), vars_);
        chNum_.evaluateExp(true);
        checkValidNumeric(_data, chNum_);
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
        String varPref_ = StringUtil.concat(_data.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        long minBoost_ = _data.getMinBoost();
        long defBoost_ = _data.getDefaultBoost();
        long maxBoost_ = _data.getMaxBoost();
        String rateBoost_ = _data.getRateBoost();
        CustList<Rate> ratesBoost_ = new CustList<Rate>();
        for (long b = minBoost_; b <= maxBoost_; b++) {
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringUtil.concat(varPref_, _data.boost()),
                    Long.toString(b));
            EvolvedNumString chNum_ = _data.createNumericableString(
                    rateBoost_, variables_);
            chNum_.evaluateExp(false);
            Rate res_ = defRate(chNum_,_data);
            if (defBoost_ == b && !Rate.eq(res_, Rate.one())) {
                _data.setError(true);
            }
            checkIfNeg(_data, ratesBoost_, res_);
        }
        checkIncr(_data, ratesBoost_);
        rateBoost_ = _data.getRateBoostCriticalHit();
        ratesBoost_ = new CustList<Rate>();
        for (long b = minBoost_; b <= maxBoost_; b++) {
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringUtil.concat(varPref_, _data.boost()),
                    Long.toString(b));
            EvolvedNumString chNum_ = _data.createNumericableString(
                    rateBoost_, variables_);
            chNum_.evaluateExp(false);
            Rate res_ = defRate(chNum_,_data);
            checkIfNeg(_data, ratesBoost_, res_);
        }
        checkIncr(_data, ratesBoost_);
    }

    private static void checkIfNeg(DataBase _data, CustList<Rate> _ratesBoost, Rate _res) {
        if (_res.isZeroOrLt()) {
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
    private static void checkIncr(DataBase _data, CustList<Rate> _ratesBoost) {
        int nbRates_ = _ratesBoost.size();
        for (int i = IndexConstants.SECOND_INDEX; i < nbRates_; i++) {
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
        String varPref_ = StringUtil.concat(_data.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringMap<String> vars_ = new StringMap<String>();
        String varName_ = StringUtil
                .concat(varPref_, _data.niveau());
        long minLevel_ = _data.getMinLevel();
        long maxLevel_ = _data.getMaxLevel();
        Rate min_ = Rate.zero();
        for (long l = minLevel_; l <= maxLevel_; l++) {
            vars_.put(varName_, Long.toString(l));
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

    private void checkNumericStringsBalls() {
        String varPref_ = StringUtil.concat(data.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringMap<String> vars_;
        vars_ = new StringMap<String>();
        vars_.put(StringUtil.concat(varPref_, data.baseCaptPk()), LgInt.one().toNumberString());
        vars_.put(StringUtil.concat(varPref_, data.rateBallStatus()), LgInt.one().toNumberString());
        vars_.put(StringUtil.concat(varPref_, data.foePkMaxHp()), LgInt.one().toNumberString());
        vars_.put(StringUtil.concat(varPref_, data.foePkRemoteHp()), LgInt.one().toNumberString());

        String numericExp_ = data.getRateCatching();
        EvolvedNumString num_;
        num_ = data.createNumericableString(numericExp_, vars_);
        num_.evaluateExp(true);
        checkValidNumeric(data, num_);
        CustList<FighterPosition> cbts_=fight.getUserTeam().playerFighterAtIndex(IndexConstants.FIRST_INDEX);
        FighterPosition creatureUt_=cbts_.first();
        for (String b : data.getItems().getKeys()) {
            Item i_ = data.getItem(b);
            if (!(i_ instanceof Ball) || ((Ball) i_).getCatchingRate().isEmpty()) {
                continue;
            }
            vars_ = FightRound.calculateCatchingVariables(fight, creatureUt_, false, data, fight.wildPokemon());
            num_ = data.createNumericableString(((Ball) i_).getCatchingRate(), vars_);
            checkTranslations(data, num_.beforeEvaluated());
            num_.evaluateExp(true);
            checkValidNumeric(data, num_);
            vars_ = FightRound.calculateCatchingVariables(fight, creatureUt_, true, data, fight.wildPokemon());
            num_ = data.createNumericableString(((Ball) i_).getCatchingRate(), vars_);
            checkTranslations(data, num_.beforeEvaluated());
            num_.evaluateExp(true);
            checkValidNumeric(data, num_);
            vars_.clear();
        }
        vars_ = FightRound.calculateFleeingVariable(fight, data, fight.wildPokemon());

        numericExp_ = data.getRateFleeing();
        num_ = data.createNumericableString(numericExp_, vars_);
        num_.evaluateExp(true);
        checkValidNumeric(data, num_);
    }

    private static StringMap<String> calculateBooleanValuesForValidation(
            Fight _fight, TeamPosition _lanceur, TeamPosition _cible,
            boolean _sending, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringMap<String> variables_ = new StringMap<String>();
        if (_sending) {
            StringList immuTypesIndiv_ = _import
                    .getVarParamsMove(_import.immuTypeAttCombattantEntrant());
            for (String e : immuTypesIndiv_) {
                variables_
                        .put(StringUtil.concat(varPref_,
                                _import.immuTypeAttCombattantEntrant(),
                                DataBase.SEP_BETWEEN_KEYS, e), _import
                                .getFalseString());
            }
        }
        variables_
                .put(StringUtil.concat(varPref_,
                        DataBase.DEF_PAS_ATTAQUE_INVOC), _import.getFalseString());
        variables_.put(StringUtil.concat(varPref_,
                DataBase.DEF_PAS_ATTAQUES_COPIABLES), _import.getFalseString());
        variables_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,
                _lanceur, _cible, _import));
        return variables_;
    }

    private static TeamPositionList addIfEmpty(CustList<TeamPosition> _list, TeamPosition _foe) {
        if (!_list.isEmpty()) {
            return new TeamPositionList(_list);
        }
        _list.add(_foe);
        return new TeamPositionList(_list);
    }
    private static void checkTranslations(DataBase _data, String _string) {
        _data.checkTranslations(_string);
    }
}
