package aiki.beans;

import aiki.beans.abilities.TranslatedKeyPair;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.fight.TrPkMoveTarget;
import aiki.beans.game.ImgPkPlayer;
import aiki.beans.moves.effects.*;
import aiki.beans.pokemon.evolutions.*;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.map.levels.Level;
import aiki.map.levels.enums.*;
import aiki.map.places.Place;
import aiki.map.pokemon.enums.*;
import aiki.map.util.MiniMapCoordsTileInts;
import aiki.util.Coords;
import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.*;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.*;
import code.util.ints.*;

public abstract class CommonBean extends Bean implements WithFacade,WithForms {
    public static final int FALSE_VALUE = 0;
    public static final int TRUE_VALUE = 1;
    public static final String GET_IMAGE = "getImage";
    public static final String CLICK_NAME = "clickName";
    public static final String GET_NAME = "getName";
    public static final String GET_GENDER = "getGender";
    public static final String CLICK_ABILITY = "clickAbility";
    public static final String GET_ABILITY = "getAbility";
    public static final String CLICK_ITEM = "clickItem";
    public static final String GET_ITEM = "getItem";
    public static final String CLICK_MOVE = "clickMove";
    public static final String GET_MOVE = "getMove";
//    protected static final String CST_ABILITIES = "abilities";
    protected static final String CST_ABILITIES_SET = "abilities_set";
    protected static final String CST_ABILITY = "ability";
    protected static final String CST_ALLY = "ally";
    protected static final String CST_AREA = "area";
    protected static final String CST_BALL = "ball";
    protected static final String CST_BERRY = "berry";
    protected static final String CST_BOOST = "boost";
    protected static final String CST_COMBO = "combo";
    protected static final String CST_SIMULATION = "simulation";
    protected static final String CST_SIMULATION_STATE = "simulation_state";
//    protected static final String CST_CURRENT_TILE = "current_tile";
//    protected static final String CST_DEALER = "dealer";
    protected static final String CST_DUAL = "dual";
//    protected static final String CST_EVO_ITEM = "evo_item";
//    protected static final String CST_EVO_STONE = "evo_stone";
    protected static final String CST_EVOLVINGITEM = "evolvingitem";
    protected static final String CST_EVOLVINGSTONE = "evolvingstone";
    protected static final String CST_FOSSIL = "fossil";
//    protected static final String CST_FROM_LIST = "from_list";
    protected static final String CST_HEALINGHP = "healinghp";
    protected static final String CST_HEALINGHPSTATUS = "healinghpstatus";
    protected static final String CST_HEALINGITEM = "healingitem";
    protected static final String CST_HEALINGPP = "healingpp";
    protected static final String CST_HEALINGSTATUS = "healingstatus";
//    protected static final String CST_INSIDE = "inside";
    protected static final String CST_ITEM = "item";
    protected static final String CST_ITEMFORBATTLE = "itemforbattle";
    protected static final String CST_ITEMS = "items";
    protected static final String CST_ITEMS_SET = "items_set";
    protected static final String CST_LEARNT = "learnt";
    protected static final String CST_LEARNT_MOVES = "learnt_moves";
    protected static final String CST_LEG_PK = "leg_pk";
//    protected static final String CST_LEVEL = "level";
//    protected static final String CST_LEVEL_MAP = "level_map";
//    protected static final String CST_LEVEL_MAP_INDEX = "level_map_index";
    protected static final String CST_MOVE = "move";
    protected static final String CST_MOVES = "moves";
    protected static final String CST_MOVES_SET = "moves_set";
    protected static final String CST_MOVES_EDIT_SET = "moves_edit_set";
//    protected static final String CST_OTHER_ITEM = "other_item";
//    protected static final String CST_OTHER_WEATHER = "other_weather";
    protected static final String CST_PK = "pk";
//    protected static final String CST_PLACE_MAP_INDEX = "place_map_index";
//    protected static final String CST_POKEMON = "pokemon";
    protected static final String CST_POKEMON_SET = "pokemon_set";
//    protected static final String CST_PROPONE_LINK = "propone_link";
    protected static final String CST_COORDS = "coords";
    protected static final String CST_NO_FIGHT = "no_fight";
    protected static final String CST_POKEMON_SET_SIMU = "pokemon_set_simu";
    protected static final String CST_POKEMON_ADDED = "pokemon_added";
    protected static final String CST_POKEMON_EDIT = "pokemon_edit";
    protected static final String CST_POKEMON_INDEX_EDIT = "pokemon_index_edit";
    protected static final String CST_POKEMON_NAME_EDIT = "pokemon_name_edit";
    protected static final String CST_POKEMON_LEVEL_EDIT = "pokemon_level_edit";
    protected static final String CST_POKEMON_EXPERIENCE = "pokemon_experience";
    protected static final String CST_POKEMON_HAPPINESS = "pokemon_happiness";
    protected static final String CST_POKEMON_HP = "pokemon_hp";
    protected static final String CST_POKEMON_EV_VAR = "pokemon_ev_";
    protected static final String CST_HEAL_EDIT_PK = "heal_edit_pk";
    protected static final String CST_CATCHING_BALL = "catching_ball";
    protected static final String CST_ITEM_EDIT = "item_edit";
    protected static final String CST_POKEMON_MOVES_EDIT = "pokemon_moves_edit";
    protected static final String CST_POKEMON_ABILITY_EDIT = "pokemon_ability_edit";
    protected static final String CST_POKEMON_GENDER_EDIT = "pokemon_gender_edit";
    protected static final String CST_POKEMON_FOE = "pokemon_foe";
    protected static final String CST_ADDING_TRAINER_PK = "adding_pk";
//    protected static final String CST_POKEMON_ITEM_EDIT = "pokemon_item_edit";
    protected static final String CST_ITEMS_SET_EDIT = "items_set_edit";
//    protected static final String CST_VALIDATE_TRAINER_PK = "validate_trainer_pk";
//    protected static final String CST_ADD_POKEMON_PLAYER = "add_pokemon_player";
    protected static final String CST_EDIT_POKEMON_PLAYER = "edit_pokemon_player";
    protected static final String CST_IS_POKEMON_PLAYER_MOVES = "is_pokemon_player_moves";
    protected static final String CST_PK_NAME = "pk_name";
    /**exception naming*/
//    protected static final String CST_PROPONE_LINK_VAR = "propone_link_";
//    protected static final String CST_PROPONE_TILE = "propone_tile";
    protected static final String CST_REPEL = "repel";
//    protected static final String CST_SEE_AREA = "see_area";
//    protected static final String CST_SELLER = "seller";
    protected static final String CST_SELLINGITEM = "sellingitem";
    protected static final String CST_STATUS = "status";
    protected static final String CST_STATUS_SET = "status_set";
//    protected static final String CST_TRAINER = "trainer";
    protected static final String CST_PERSON = "person";
    protected static final String CST_TRAINER_MULTI_FIGHT = "trainer_multi_fight";
//    protected static final String CST_TRAINER_ONE_FIGHT = "trainer_one_fight";
    protected static final String CST_SPACE = " ";
    protected static final String CST_SEP_DASH = " - ";
    protected static final String CST_CENT = "100";
//    protected static final char CST_UNDERSCORE = '_';
    protected static final String CST_LEFT_BRACE = "{";
    protected static final String CST_RIGHT_BRACE = "}";
    protected static final String CST_QUOTED_LEFT_BRACE = "'{";
    protected static final String CST_QUOTED_RIGHT_BRACE = "}'";
    protected static final String CST_QUOTE = "'";
    protected static final String CST_ESCAPED_QUOTE = "''";
    protected static final char CST_LEFT_PAR = '(';
    protected static final char CST_RIGHT_PAR = ')';
    protected static final char CST_PIPE_CHAR = '|';

    private StringMapObjectBase baseForms;

    private FacadeGame dataBase;
    private IntBeanBuilderHelper builder;
    private String appName = "";
//    private String baseEncode;

    protected void fwd(CommonBean _b) {
        _b.setAppName(getAppName());
        _b.setDataBase(db());
        _b.setForms(new StringMapObject());
        _b.getForms().putAllMap(getForms());
        _b.setLanguage(getLanguage());
        _b.setBuilder(getBuilder());
    }

    public static int toInt(BoolVal _b) {
        if (_b == BoolVal.TRUE) {
            return TRUE_VALUE;
        }
        return FALSE_VALUE;
    }
    public static int toInt(boolean _b) {
        if (_b) {
            return TRUE_VALUE;
        }
        return FALSE_VALUE;
    }
    public static boolean toBool(int _i) {
        return _i == TRUE_VALUE;
    }
    public static Rate rateTrue(MonteCarloBoolean _law) {
        if (_law.isZero()) {
            return Rate.zero();
        }
        return _law.normalizedRate(BoolVal.TRUE);
    }

    public static void feedForms(int _indexOne, int _indexTwo, StringMapObject _forms) {
        Coords c_ = new Coords();
        c_.setNumberPlace(_indexOne);
        c_.getLevel().setLevelIndex(_indexTwo);
        _forms.put(CST_COORDS, c_);
//        _forms.put(CST_PROPONE_LINK, false);
//        _forms.put(CST_PROPONE_TILE, false);
//        _forms.put(CST_SEE_AREA, false);
//        for (Direction d: Direction.all()) {
//            _forms.putDir(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
//        }
    }
    protected void eff(EffectBean _sub) {
        preEff(_sub);
        target(_sub.getEffect().getTargetChoice(),MessagesPkBean.EFF,MessagesDataEff.M_P_36_TARGETS_ADJ_ADV,MessagesDataEff.M_P_36_TARGETS_ADJ_MULT,MessagesDataEff.M_P_36_TARGETS_ADJ_UNIQ,MessagesDataEff.M_P_36_TARGETS_ALLIE,MessagesDataEff.M_P_36_TARGETS_ALLIES,MessagesDataEff.M_P_36_TARGETS_ANY_FOE,MessagesDataEff.M_P_36_TARGETS_AUTRE_UNIQ,MessagesDataEff.M_P_36_TARGETS_GLOBALE,MessagesDataEff.M_P_36_TARGETS_LANCEUR,MessagesDataEff.M_P_36_TARGETS_PSEUDO_GLOBALE,MessagesDataEff.M_P_36_TARGETS_TOUS_ADV,MessagesDataEff.M_P_36_TARGETS_UNIQUE_IMPORTE);
        displayStringList(MessagesPkBean.EFF,_sub.getReasons(),MessagesDataEff.M_P_36_REASONS);
        mapVarsInit(_sub.getMapVarsFail());
        displayBoolTrue(MessagesPkBean.EFF,toInt(_sub.getNeedSuccessFirstEffect()),MessagesDataEff.M_P_36_NEED_SUCESS);
        eff1(_sub);
        eff2(_sub);
    }

    private void eff1(EffectBean _sub) {
        if (_sub.getEffect() instanceof EffectAccuracy) {
            formatMessage(MessagesPkBean.EFF_ACCURACY,MessagesDataEffaccuracy.M_P_37_ACCURACY_MAX);
        }
        if (_sub instanceof EffectAllyBean) {
            formatMessage(MessagesPkBean.EFF_ALLY,MessagesDataEffally.M_P_38_MUL_ALLY_DAMAGE,((EffectAllyBean)_sub).getMultAllyDamage().toNumberString());
        }
        if (_sub instanceof EffectBatonPassBean) {
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectBatonPassBean)_sub).getMoves(),MessagesPkBean.EFF_BATONPASS,MessagesDataEffbatonpass.M_P_39_EFFECT_2);
        }
        if (_sub instanceof EffectCloneBean) {
            formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_2);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectCloneBean)_sub).getMovesEndRound(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_3);
            batonPassSending((EffectCloneBean) _sub);
            formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_6);
            formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_7);
            formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_8);
            formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_9);
        }
        if (_sub instanceof EffectCommonStatisticsBean) {
            new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,((EffectCommonStatisticsBean)_sub).getCommonValue(),MessagesPkBean.EFF_COMMONSTATISTICS,MessagesDataEffcommonstatistics.M_P_41_COMMON,MessagesDataEffcommonstatistics.M_P_41_COMMON_STAT,MessagesDataEffcommonstatistics.M_P_41_COMMON_VALUE);
            mapVarsInit(((EffectCommonStatisticsBean)_sub).getMapVarsCommonStatistics());
        }
        if (_sub instanceof EffectCopyFighterBean) {
            formatMessage(MessagesPkBean.EFF_COPYFIGHTER,MessagesDataEffcopyfighter.M_P_42_PP_MOVES,Long.toString(((EffectCopyFighterBean)_sub).getPpForMoves()));
        }
        if (_sub instanceof EffectCopyMoveBean) {
            effCopy((EffectCopyMoveBean) _sub);
        }
        if (_sub instanceof EffectCounterAttackBean) {
            effCounter((EffectCounterAttackBean) _sub);
        }
        if (_sub instanceof EffectDamageBean) {
            effDam((EffectDamageBean) _sub);
        }
        if (_sub instanceof EffectDamageRateBean) {
            displayBoolFull(MessagesPkBean.EFF_DAMAGERATE,toInt(((EffectDamageRateBean)_sub).getWinHp()),MessagesDataEffdamagerate.M_P_46_POS_RATE,MessagesDataEffdamagerate.M_P_46_NEG_RATE,((EffectDamageRateBean)_sub).getRateDamage().toNumberString());
        }
        if (_sub instanceof EffectFullHpRateBean) {
            displayIntDef(MessagesPkBean.EFF_FULLHPRATE,((EffectFullHpRateBean)_sub).getLeftUserHp(),MessagesDataEfffullhprate.M_P_48_LEFT_USER_HP);
            displayNotEmpty(MessagesPkBean.EFF_FULLHPRATE,((EffectFullHpRateBean)_sub).getRestoredHp(),MessagesDataEfffullhprate.M_P_48_RESTORED);
            mapVarsInit(((EffectFullHpRateBean)_sub).getMapVarsRestored());
            displayIntDef(MessagesPkBean.EFF_FULLHPRATE,((EffectFullHpRateBean)_sub).getClosestFoeDamageRateHp(),MessagesDataEfffullhprate.M_P_48_CLOSEST_FOE_DAMAGE_RATE_HP);
        }
        if (_sub instanceof EffectGlobalBean) {
            displayBoolFull(MessagesPkBean.EFF_GLOBAL,toInt(((EffectGlobalBean)_sub).getEffectGlobalCore().getWeather()),MessagesDataEffglobal.M_P_49_IS_WEATHER,MessagesDataEffglobal.M_P_49_IS_NOT_WEATHER);
            displayBoolTrue(MessagesPkBean.EFF_GLOBAL,toInt(((EffectGlobalBean)_sub).getEffectGlobalCore().getCanceledIfUsed()),MessagesDataEffglobal.M_P_49_CANCEL_REUSE);
            displayBoolTrue(MessagesPkBean.EFF_GLOBAL,toInt(((EffectGlobalBean)_sub).getEffectGlobalCore().getReverseOrderOfSortBySpeed()),MessagesDataEffglobal.M_P_49_REVERSE_SPEED);
            displayBoolTrue(MessagesPkBean.EFF_GLOBAL,toInt(((EffectGlobalBean)_sub).getEffectGlobalCore().getUnusableItem()),MessagesDataEffglobal.M_P_49_UNUSABLE_ITEM);
            displayBoolTrue(MessagesPkBean.EFF_GLOBAL,toInt(((EffectGlobalBean)_sub).getEffectGlobalCore().getPuttingKo()),MessagesDataEffglobal.M_P_49_PUTTING_KO);
            displayIntDef(MessagesPkBean.EFF_GLOBAL,((EffectGlobalBean)_sub).getEffectGlobalCore().getMultAccuracy(),MessagesDataEffglobal.M_P_49_MULT_ACC);
            displayIntDef(MessagesPkBean.EFF_GLOBAL,((EffectGlobalBean)_sub).getEffectGlobalCore().getDamageEndRound(),MessagesDataEffglobal.M_P_49_DAMAGE_END_ROUND);
            displayIntDef(MessagesPkBean.EFF_GLOBAL,((EffectGlobalBean)_sub).getEffectGlobalCore().getHealingEndRoundGround(),MessagesDataEffglobal.M_P_49_HEALING_END_ROUND_GROUND);
            displayIntDef(MessagesPkBean.EFF_GLOBAL,((EffectGlobalBean)_sub).getEffectGlobalCore().getHealingEndRound(),MessagesDataEffglobal.M_P_49_HEALING_END_ROUND);
            displayIntDef(MessagesPkBean.EFF_GLOBAL,((EffectGlobalBean)_sub).getMultEffectLovingAlly(),MessagesDataEffglobal.M_P_49_MULT_LOVE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getPreventStatus(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_FORBID_STATUS);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getImmuneTypes(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_IMMUNE_TYPES);
            new BeanDisplayMap<TranslatedKeyPair,Rate>(new BeanDisplayTranslatedKeyPair(),new BeanDisplayRate()).displayGrid(this,((EffectGlobalBean)_sub).getEfficiencyMoves(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_EFFICIENCY_TABLE,MessagesDataEffglobal.M_P_49_DAMAGE_TYPE,MessagesDataEffglobal.M_P_49_POKEMON_TYPE,MessagesDataEffglobal.M_P_49_EFFICIENCY);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getDisableImmuAgainstTypes(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_DISABLE_IMMU_TYPES);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getCancelProtectingAbilities(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_DISABLE_IMMU_ABILITIES);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getUnusableMoves(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_UNUSABLE_MOVES);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getCancelEffects(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_CANCEL_EFFECTS);
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,((EffectGlobalBean)_sub).getMultPowerMoves(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MULT_POWER_MOVE,MessagesDataEffglobal.M_P_49_MOVE,MessagesDataEffglobal.M_P_49_RATE_DAMAGE);
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,((EffectGlobalBean)_sub).getMultDamageTypesMoves(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MULT_POWER_TYPE,MessagesDataEffglobal.M_P_49_MOVE_TYPE,MessagesDataEffglobal.M_P_49_RATE_DAMAGE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getCancelChgtStat(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_CANCEL_CHGT_STATIS);
//            displayNotEmpty(MessagesPkBean.EFF_GLOBAL,((EffectGlobalBean)_sub).getInvokedMoveTerrain().getTranslation(),MessagesDataEffglobal.M_P_49_INVOKED_MOVE);
//            formatMessageDir(((EffectGlobalBean)_sub).getInvokedMoveTerrain());
            formatTrKey(((EffectGlobalBean)_sub).getInvokedMoveTerrain(),MessagesPkBean.EFF_GLOBAL,"",MessagesDataEffglobal.M_P_49_INVOKED_MOVE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean)_sub).getInvokingMoves(),NumberUtil.signum(((EffectGlobalBean)_sub).getInvokedMoveTerrain().getKey().length()),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_INVOKED_MOVE_ENV);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean) _sub).getChangedTypesTerrain(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_CHANGING_TYPE_INVOKED);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean) _sub).getInvokingMovesChangingTypes(),NumberUtil.signum(((EffectGlobalBean)_sub).getChangedTypesTerrain().size()),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_CHANGING_TYPE_INVOKING);
            new BeanDisplayMap<TranslatedKeyPair,Rate>(new BeanDisplayTranslatedKeyPair(),new BeanDisplayRate()).displayGrid(this,((EffectGlobalBean) _sub).getMultStatIfContainsType(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MULT_STAT_TYPE,MessagesDataEffglobal.M_P_49_STATISTIC,MessagesDataEffglobal.M_P_49_POKEMON_TYPE_STAT,MessagesDataEffglobal.M_P_49_RATE_POKEMON_STATISTIC);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectGlobalBean) _sub).getMovesUsedByTargetedFighters(),NumberUtil.signum(((EffectGlobalBean) _sub).getMultDamagePrepaRound().size()),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MULT_DAMAGE_TYPE);
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(), new BeanDisplayRate()).displayGrid(this,((EffectGlobalBean) _sub).getMultDamagePrepaRound(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MULT_DAMAGE_TYPE,MessagesDataEffglobal.M_P_49_DAMAGE_TYPE,MessagesDataEffglobal.M_P_49_RATE);
        }
        if (_sub instanceof EffectInvokeBean) {
            displayBoolTrue(MessagesPkBean.EFF_INVOKE,toInt(((EffectInvokeBean)_sub).getInvokingMoveButUser()),MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_BUT_USER);
            displayBoolTrue(MessagesPkBean.EFF_INVOKE,toInt(((EffectInvokeBean)_sub).getInvokingTargetChosenMove()),MessagesDataEffinvoke.M_P_50_INVOKE_TARGET_CHOSEN_MOVE);
            displayBoolTrue(MessagesPkBean.EFF_INVOKE,toInt(((EffectInvokeBean)_sub).getInvokingUserMoveWhileSleep()),MessagesDataEffinvoke.M_P_50_INVOKE_USER_MOVE_WHILE_SLEEP);
            displayBoolTrue(MessagesPkBean.EFF_INVOKE,toInt(((EffectInvokeBean)_sub).getInvokingAllyMove()),MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_PART);
            displayBoolTrue(MessagesPkBean.EFF_INVOKE,toInt(((EffectInvokeBean)_sub).getInvokingTargetSuccesfulMove()),MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_SUCCESS_TARGET);
            displayBoolTrue(MessagesPkBean.EFF_INVOKE,toInt(((EffectInvokeBean)_sub).getInvokingSufferedMove()),MessagesDataEffinvoke.M_P_50_INVOKE_SUFFERED_MOVE);
            displayIntDef(MessagesPkBean.EFF_INVOKE,((EffectInvokeBean)_sub).getRateInvokationMove(),MessagesDataEffinvoke.M_P_50_RATE_INVOKE_MOVE);
            new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKey()).displayGrid(this,((EffectInvokeBean)_sub).getMoveFctEnv(),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_MOVE_FCT_ENV,MessagesDataEffinvoke.M_P_50_ENV_TYPE,MessagesDataEffinvoke.M_P_50_INVOKED_MOVE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectInvokeBean) _sub).getGlobalMoves(),NumberUtil.signum(((EffectInvokeBean) _sub).getMoveFctEnv().size()),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_MOVE_FCT_ENV_EXC);
            new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_OTHER_OWNED_TYPE),new BeanDisplayTranslatedKey()).displayGrid(this,((EffectInvokeBean)_sub).getInvokingMoveByUserTypes(),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_TYPE,MessagesDataEffinvoke.M_P_50_OWNED_TYPE,MessagesDataEffinvoke.M_P_50_INVOKED_MOVE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectInvokeBean) _sub).getMovesNotToBeInvoked(),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_MOVES_NOT_INVOKED);
        }
        if (_sub instanceof EffectMultMovePowerBean) {
            effMult((EffectMultMovePowerBean) _sub);
        }
    }
    private void eff2(EffectBean _sub) {
        if (_sub instanceof EffectOrderBean) {
            displayBoolFull(MessagesPkBean.EFF_ORDER,toInt(((EffectOrderBean)_sub).getTargetAttacksLast()),MessagesDataEfforder.M_P_53_LAST,MessagesDataEfforder.M_P_53_AFTER_USER);
        }
        if (_sub instanceof EffectProtectFromTypesBean) {
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectProtectFromTypesBean)_sub).getImmuAgainstTypes(),MessagesPkBean.EFF_PROTECTFROMTYPES,MessagesDataEffprotectfromtypes.M_P_54_IMMU_MOVE_TYPES);
        }
        if (_sub instanceof EffectProtectionBean) {
            displayBoolTrue(MessagesPkBean.EFF_PROTECTION,toInt(((EffectProtectionBean)_sub).getProtSingle()),MessagesDataEffprotection.M_P_55_PROT_SINGLE);
            displayIntDef(MessagesPkBean.EFF_PROTECTION,((EffectProtectionBean)_sub).getProtSingleAgainstKo(),MessagesDataEffprotection.M_P_55_PROT_SINGLE_KO);
            displayBoolTrue(MessagesPkBean.EFF_PROTECTION,toInt(((EffectProtectionBean)_sub).getProtTeamAgainstMultTargets()),MessagesDataEffprotection.M_P_55_PROT_MULTI_TARGETS);
            displayBoolTrue(MessagesPkBean.EFF_PROTECTION,toInt(((EffectProtectionBean)_sub).getProtTeamAgainstPrio()),MessagesDataEffprotection.M_P_55_PROT_PRIO);
            displayBoolTrue(MessagesPkBean.EFF_PROTECTION,toInt(((EffectProtectionBean)_sub).getProtTeamAgainstStatusMoves()),MessagesDataEffprotection.M_P_55_PROT_SINGLE_STATUS);
            displayBoolTrue(MessagesPkBean.EFF_PROTECTION,toInt(((EffectProtectionBean)_sub).getProtTeamAgainstDamageMoves()),MessagesDataEffprotection.M_P_55_PROT_SINGLE_DAMAGE);
        }
        if (_sub instanceof EffectRemainedHpRateBean) {
            displayBoolFull(MessagesPkBean.EFF_REMAINEDHPRATE,toInt(((EffectRemainedHpRateBean)_sub).getWinHp()),MessagesDataEffremainedhprate.M_P_56_RATE_WIN,MessagesDataEffremainedhprate.M_P_56_RATE_LOOSE,((EffectRemainedHpRateBean)_sub).getRateHp().toNumberString());
        }
        if (_sub instanceof EffectRestrictionBean) {
            procMoveChoiceRestrictionType(((EffectRestrictionBean)_sub).getChoiceRestriction(),MoveChoiceRestrictionType.CATEGORIE_AUTRE,MessagesDataEffrestriction.M_P_57_FORBID_STATUS_MOVE);
            procMoveChoiceRestrictionType(((EffectRestrictionBean)_sub).getChoiceRestriction(),MoveChoiceRestrictionType.DER,MessagesDataEffrestriction.M_P_57_FORBID_LAST_MOVE);
            procMoveChoiceRestrictionType(((EffectRestrictionBean)_sub).getChoiceRestriction(),MoveChoiceRestrictionType.LANCEUR_ATTAQUES,MessagesDataEffrestriction.M_P_57_FORBID_USER_MOVES);
            procMoveChoiceRestrictionType(((EffectRestrictionBean)_sub).getChoiceRestriction(),MoveChoiceRestrictionType.FORBIDDEN,MessagesDataEffrestriction.M_P_57_FORBID_USE_LAST_MOVE);
            procMoveChoiceRestrictionType(((EffectRestrictionBean)_sub).getChoiceRestriction(),MoveChoiceRestrictionType.FORCE,MessagesDataEffrestriction.M_P_57_FORCE_USE_LAST_MOVE);
        }
        if (_sub instanceof EffectStatisticBean) {
            effStatis((EffectStatisticBean) _sub);
        }
        if (_sub instanceof EffectStatusBean) {
            effStatus((EffectStatusBean) _sub);
        }
        if (_sub instanceof EffectSwitchAbilitiesBean) {
            swAb((EffectSwitchAbilitiesBean) _sub);
        }
        if (_sub instanceof EffectSwitchItemsBean) {
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.DELETE_DEF_TARGET_BERRY,MessagesDataEffswitchitems.M_P_61_DELETE_BERRY);
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.TAKE_OBJET,MessagesDataEffswitchitems.M_P_61_TAKE_ITEM);
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.REMOVE_TARGET_OBJECT,MessagesDataEffswitchitems.M_P_61_REMOVE_ITEM);
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.EXCHANGE_OBJECTS,MessagesDataEffswitchitems.M_P_61_SWITCH_ITEMS);
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.REUSE_LAST_OBJECT,MessagesDataEffswitchitems.M_P_61_REUSE_ITEM);
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.GIVE_OBJECT_TARGET,MessagesDataEffswitchitems.M_P_61_GIVE_TO_TARGET);
            procExchangeType(((EffectSwitchItemsBean)_sub).getMoveObject(),MoveItemType.USE_OBJECT_AS_POSSIBLE,MessagesDataEffswitchitems.M_P_61_USE_ITEM_IF_POSSIBLE);
        }
        if (_sub instanceof EffectSwitchMoveTypesBean) {
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,((EffectSwitchMoveTypesBean)_sub).getReplacingTypes(),MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_REPLACING_TYPES);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).displayHead(this,((EffectSwitchMoveTypesBean)_sub).getChangeTypes().getKeys(),NumberUtil.signum(((EffectSwitchMoveTypesBean)_sub).getReplacingTypes().size()),MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_CHANGING_TYPE_POSSIBLE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).displayHead(this,((EffectSwitchMoveTypesBean)_sub).getChangeTypes().getKeys(),1-NumberUtil.signum(((EffectSwitchMoveTypesBean)_sub).getReplacingTypes().size()),MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_CHANGING_TYPE);
            new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKey()).displayGrid(this,((EffectSwitchMoveTypesBean)_sub).getChangeTypes(),MessagesPkBean.EFF_SWITCHMOVESTYPES,"",MessagesDataEffswitchmovestypes.M_P_62_OLD_TYPE,MessagesDataEffswitchmovestypes.M_P_62_NEW_TYPE);
        }
        if (_sub instanceof EffectSwitchPointViewBean) {
            procPointViewChangementType(((EffectSwitchPointViewBean)_sub).getPointViewChangement(),PointViewChangementType.THIEF_BONUSES,MessagesDataEffswitchpointview.M_P_63_THIEF,_sub.getMove());
            procPointViewChangementType(((EffectSwitchPointViewBean)_sub).getPointViewChangement(),PointViewChangementType.MIRROR_AGAINST_THROWER,MessagesDataEffswitchpointview.M_P_63_MIRROR,_sub.getMove());
            procPointViewChangementType(((EffectSwitchPointViewBean)_sub).getPointViewChangement(),PointViewChangementType.ATTRACT_DAMAGES_MOVES,MessagesDataEffswitchpointview.M_P_63_ATTRACT);
        }
        if (_sub instanceof EffectSwitchTypesBean) {
            swTy((EffectSwitchTypesBean) _sub);
        }
    }

    private void swTy(EffectSwitchTypesBean _sub) {
        displayBoolTrue(MessagesPkBean.EFF_SWITCHTYPES,toInt(_sub.isResTypes()),MessagesDataEffswitchtypes.M_P_65_RES_MOVES);
        displayBoolTrue(MessagesPkBean.EFF_SWITCHTYPES,toInt(_sub.isUserTypes()),MessagesDataEffswitchtypes.M_P_65_USER_MOVES);
        if (!_sub.isConstTypes()) {
            new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKey()).displayGrid(this, _sub.getChgtTypeByEnv(), MessagesPkBean.EFF_SWITCHTYPES, MessagesDataEffswitchtypes.M_P_65_ENVIR,MessagesDataEffswitchtypes.M_P_65_ENVIR_ENV,MessagesDataEffswitchtypes.M_P_65_ENVIR_TYPE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_sub.getGlobalMoves(),NumberUtil.signum(_sub.getChgtTypeByEnv().size()),MessagesPkBean.EFF_SWITCHTYPES,MessagesDataEffswitchtypes.M_P_65_ENVIR_ENV_EXC);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_sub.getAddedTypes(),MessagesPkBean.EFF_SWITCHTYPES,MessagesDataEffswitchtypes.M_P_65_ADDED_TYPES);
            procExchangeType(_sub.getExchangeTypes(),ExchangeType.GIVE_TO_TARGET,_sub.getAddedTypes(),new CustList<TranslatedKey>(),MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES_NOT_CONST_TARGET);
            procExchangeType(_sub.getExchangeTypes(),ExchangeType.GIVE_TO_THROWER,_sub.getAddedTypes(),new CustList<TranslatedKey>(),MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES_NOT_CONST_USER);
            procExchangeType(_sub.getExchangeTypes(),ExchangeType.EXCHANGE,_sub.getAddedTypes(),new CustList<TranslatedKey>(),MessagesDataEffswitchtypes.M_P_65_SWITCH_TYPES);
            procExchangeType(_sub.getExchangeTypes(),ExchangeType.GIVE_CONST,_sub.getAddedTypes(),_sub.getConstTypes(),MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES);
        }
    }

    private void swAb(EffectSwitchAbilitiesBean _sub) {
        procExchangeType(_sub.getExchangeAbility(),ExchangeType.GIVE_TO_TARGET,MessagesDataEffswitchabilities.M_P_60_GIVE_TO_TARGET);
        procExchangeType(_sub.getExchangeAbility(),ExchangeType.GIVE_TO_THROWER,MessagesDataEffswitchabilities.M_P_60_GIVE_TO_USER);
        procExchangeType(_sub.getExchangeAbility(),ExchangeType.EXCHANGE,MessagesDataEffswitchabilities.M_P_60_SWICTH_ABILITIES);
        if (_sub.giveConst()) {
//            formatMessageDir(_sub.getConstAbility());
            formatTrKey(_sub.getConstAbility(),MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST_EMPTY,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST);
//            if (_sub.isDefAbility()) {
//                formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST);
//                formatMessageDir(_sub.getConstAbility());
//            } else {
//                formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST_EMPTY);
//            }
        }
    }
    protected void formatTrKey(TranslatedKey _key,String _file,String _empty, String _fill) {
        if (!_key.getKey().isEmpty()) {
            formatMessage(_file,_fill);
            formatMessageDir(_key);
        } else {
            formatMessage(_file,_empty);
        }
//        displayNotEmpty(_file,_key.getTranslation(),_fill);
//        displayEmpty(_file,_key.getTranslation(),_empty);
//        formatMessageDir(_key);
    }

    private void procMoveChoiceRestrictionType(MoveChoiceRestrictionType _value, MoveChoiceRestrictionType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_RESTRICTION,_key);
        }
    }
    private void procExchangeType(ExchangeType _value, ExchangeType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,_key);
        }
    }
    private void procExchangeType(MoveItemType _value, MoveItemType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHITEMS,_key);
        }
    }

    private void procPointViewChangementType(PointViewChangementType _value, PointViewChangementType _cst, String _key, String... _values) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHPOINTVIEW,_key,_values);
        }
    }
    private void procExchangeType(ExchangeType _value, ExchangeType _cst, CustList<TranslatedKey> _addedTypes, CustList<TranslatedKey> _constTypes, String _key) {
        if (_value == _cst) {
            displayBoolTrue(MessagesPkBean.EFF_SWITCHTYPES,1-NumberUtil.signum(_addedTypes.size()),_key);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_constTypes,1-NumberUtil.signum(_addedTypes.size()),MessagesPkBean.EFF_SWITCHTYPES,"");
        }
    }
    private void effStatis(EffectStatisticBean _sub) {
        effStatis(_sub.getEffectStatisticCommon());
    }
    protected void effStatis(EffectStatisticCommon _sub) {
        if (!_sub.randomStatis()) {
            displayBoolFull(MessagesPkBean.EFF_STATIS,toInt(_sub.isAlwaysEnabled()),MessagesDataEffstatis.M_P_58_ALWAYS_ENABLED,MessagesDataEffstatis.M_P_58_RATE_ENABLED,_sub.getEvtRate().toNumberString(),_sub.getEvtRatePerCent());
        }
        if (_sub.notEmptyVarBoost()) {
            if (_sub.randomStatis()) {
                new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(),new BeanDisplayStatRankRate(true,true)).displayGrid(this,_sub.getStatisVarRank(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST,MessagesDataEffstatis.M_P_58_FAIL,MessagesDataEffstatis.M_P_58_RATE_EVENT);
            } else {
                new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(),new BeanDisplayStatRankRate(true,false)).displayGrid(this,_sub.getStatisVarRank(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST,MessagesDataEffstatis.M_P_58_FAIL);
            }
            mapVarsInit(_sub.getMapVarsStatistics());
        }
        if (!_sub.getSwapBoostStatis().isEmpty()) {
            new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,_sub.getSwapBoostStatis(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_SWAP_BOOST,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_FAIL);
            mapVarsInit(_sub.getMapVarsStatistics());
        }
        display(MessagesPkBean.EFF_STATIS,_sub.getCancelLowStat(),MessagesDataEffstatis.M_P_58_CANCEL_LOW_STAT,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCancelLowStat());
        display(MessagesPkBean.EFF_STATIS,_sub.getCancelChgtStat(),MessagesDataEffstatis.M_P_58_CANCEL_CHGT_STAT,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCancelChgtStat());
        display(MessagesPkBean.EFF_STATIS,_sub.getCopyBoost(),MessagesDataEffstatis.M_P_58_COPY_BOOST,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCopyBoost());
    }
    private void effStatus(EffectStatusBean _sub) {
        if (!_sub.getLawStatus().isEmpty()) {
            new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_OTHER_STATUS),new BeanDisplayStatRankRate(false, true)).displayGrid(this,_sub.getLawStatus(),MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_LAW_STATUS,MessagesDataEffstatus.M_P_59_STATUS,MessagesDataEffstatus.M_P_59_FAIL,MessagesDataEffstatus.M_P_59_RATE_EVENT);
            mapVarsInit(_sub.getMapVarsStatus());
        }
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getDeletedStatus(), MessagesPkBean.EFF_STATUS, MessagesDataEffstatus.M_P_59_DELETED_STATUS);
        displayBoolTrue(MessagesPkBean.EFF_STATUS,toInt(_sub.getKoUserHealSubst()),MessagesDataEffstatus.M_P_59_KO_USER);
        displayBoolTrue(MessagesPkBean.EFF_STATUS,toInt(_sub.getStatusFromUser()),MessagesDataEffstatus.M_P_59_FORWARD);
    }
    private void effMult(EffectMultMovePowerBean _sub) {
        if (_sub.getEffect() instanceof EffectMultSufferedMovePower) {
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this, _sub.getMultMovePowerFctType(),MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,MessagesDataEffmultsufferedmovepower.M_P_51_MULT_POWER,MessagesDataEffmultsufferedmovepower.M_P_51_MULT_POWER_TYPE,MessagesDataEffmultsufferedmovepower.M_P_51_MULT_POWER_RATE);
        } else {
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this, _sub.getMultMovePowerFctType(),MessagesPkBean.EFF_MULTUSEDMOVEPOWER,MessagesDataEffmultusedmovepower.M_P_52_MULT_POWER,MessagesDataEffmultusedmovepower.M_P_52_MULT_POWER_TYPE,MessagesDataEffmultusedmovepower.M_P_52_MULT_POWER_RATE);
        }
    }

    private void effDam(EffectDamageBean _sub) {
        displayEmpty(MessagesPkBean.EFF_DAMAGE, _sub.getHitsLaw(),MessagesDataEffdamage.M_P_45_HIT_LAW_CONST,Long.toString(_sub.getNbHits()));
        new BeanDisplayMap<Long,Rate>(new BeanDisplayLong(),new BeanDisplayRate()).displayGrid(this, _sub.getHitsLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_HIT_LAW,MessagesDataEffdamage.M_P_45_EVENT_NB_HITS,MessagesDataEffdamage.M_P_45_RATE_EVENT);
        displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getConstDamage()),MessagesDataEffdamage.M_P_45_CONST_DAMAGE,_sub.getPower());
        int condDet_ = toInt(_sub.hasLawForDamage())*toInt(!_sub.getConstDamage());
//        if (_sub.hasLawForDamage()) {
        if (condDet_ == TRUE_VALUE) {
            displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(_sub.hasDeterminatedLawForDamage()),MessagesDataEffdamage.M_P_45_DAMAG_LAW_CONST,_sub.getPower());
            if (!_sub.hasDeterminatedLawForDamage()) {
                new BeanDisplayMap<String,Rate>(new BeanDisplayString(),new BeanDisplayRate()).displayGrid(this, _sub.getDamageLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_DAMAG_LAW,MessagesDataEffdamage.M_P_45_EVENT,MessagesDataEffdamage.M_P_45_RATE_EVENT);
            }
            mapVarsInit(_sub.getMapVarsDamage());
        }
        if (!_sub.getConstDamage()) {
//            if (_sub.hasLawForDamage()) {
//                displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(_sub.hasDeterminatedLawForDamage()),MessagesDataEffdamage.M_P_45_DAMAG_LAW_CONST,_sub.getPower());
//                if (!_sub.hasDeterminatedLawForDamage()) {
//                    new BeanDisplayMap<String,Rate>(new BeanDisplayString(),new BeanDisplayRate()).displayGrid(this, _sub.getDamageLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_DAMAG_LAW,MessagesDataEffdamage.M_P_45_EVENT,MessagesDataEffdamage.M_P_45_RATE_EVENT);
//                }
//                mapVarsInit(_sub.getMapVarsDamage());
//            }
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(), new BeanDisplayRate()).displayGrid(this, _sub.getMultDamageAgainst(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_DAMAGE_MULT_COUNTER,MessagesDataEffdamage.M_P_45_CATEGORY,MessagesDataEffdamage.M_P_45_RATE);
//            if (_sub.constPower()) {
//                displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(_sub.hasConstPower()),MessagesDataEffdamage.M_P_45_CONST_POWER,MessagesDataEffdamage.M_P_45_VAR_POWER,_sub.getPower());
//                mapVarsInit(_sub.getMapVarsDamage());
//                formatMessage(MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_RATE,Long.toString(_sub.getChRate()));
//                new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).displayGrid(this, _sub.getChLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_LAW,MessagesDataEffdamage.M_P_45_EVENT_RATE,MessagesDataEffdamage.M_P_45_RATE);
//                displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getUserAttack()),MessagesDataEffdamage.M_P_45_ATTACK_USER,MessagesDataEffdamage.M_P_45_ATTACK_TARGET,_sub.getStatisAtt());
//                displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getTargetDefense()),MessagesDataEffdamage.M_P_45_DEFENSE_TARGET,MessagesDataEffdamage.M_P_45_DEFENSE_USER,_sub.getStatisDef());
//                new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_sub.getIgnVarStatTargetPos(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_POS_STAT);
//                new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_sub.getIgnVarStatUserNeg(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_NEG_STAT);
//                displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getRandMax()),MessagesDataEffdamage.M_P_45_RAND_MAX);
//            }
        }
        int cond_ = toInt(_sub.constPower())*toInt(!_sub.getConstDamage());
        if (cond_ == TRUE_VALUE) {
            displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(_sub.hasConstPower()),MessagesDataEffdamage.M_P_45_CONST_POWER,MessagesDataEffdamage.M_P_45_VAR_POWER,_sub.getPower());
            mapVarsInit(_sub.getMapVarsDamage());
            formatMessage(MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_RATE,Long.toString(_sub.getChRate()));
            new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).displayGrid(this, _sub.getChLaw(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CH_LAW,MessagesDataEffdamage.M_P_45_EVENT_RATE,MessagesDataEffdamage.M_P_45_RATE);
            displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getUserAttack()),MessagesDataEffdamage.M_P_45_ATTACK_USER,MessagesDataEffdamage.M_P_45_ATTACK_TARGET,_sub.getStatisAtt());
            displayBoolFull(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getTargetDefense()),MessagesDataEffdamage.M_P_45_DEFENSE_TARGET,MessagesDataEffdamage.M_P_45_DEFENSE_USER,_sub.getStatisDef());
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_sub.getIgnVarStatTargetPos(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_POS_STAT);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_sub.getIgnVarStatUserNeg(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_IGN_NEG_STAT);
            displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getRandMax()),MessagesDataEffdamage.M_P_45_RAND_MAX);
        }
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this, _sub.getBoostStatisOnceKoFoe(), MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_BOOST_STATIS_ONCE_KO_FOE, MessagesDataEffdamage.M_P_45_STATISTIC, MessagesDataEffdamage.M_P_45_BOOST);
        displayBoolTrue(MessagesPkBean.EFF_DAMAGE,toInt(_sub.getSummingUserTeamOkFighter()),MessagesDataEffdamage.M_P_45_SUMMING_TEAM);
    }

    private void effCounter(EffectCounterAttackBean _sub) {
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGridParam(this, _sub.getSufferingDamageTypes(),new String[]{_sub.getMove()},MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_T,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_HP);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGridParam(this, _sub.getDroppedStatDirectMove(),new String[]{_sub.getMove()},MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_S,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_V);
//        if (!_sub.getSufferingDamageDirectMove().isZero()) {
//            formatMessage(MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_SUFFERING_DIRECT, _sub.getMove(), _sub.getSufferingDamageDirectMove().toNumberString());
//        }
        displayIntDef(MessagesPkBean.EFF_COUNTERATTACK,_sub.getSufferingDamageDirectMove(),MessagesDataEffcounterattack.M_P_44_SUFFERING_DIRECT);
        displayStringList(MessagesPkBean.EFF_COUNTERATTACK, _sub.getReasonsProtect(),MessagesDataEffcounterattack.M_P_44_FAIL_PROTECT, _sub.getMove());
        displayStringList(MessagesPkBean.EFF_COUNTERATTACK, _sub.getReasonsCounter(),MessagesDataEffcounterattack.M_P_44_COUNTER_PROTECT, _sub.getMove());
        mapVarsInit(_sub.getMapVarsFailCounter());
    }

    private void effCopy(EffectCopyMoveBean _sub) {
        displayBoolTrue(MessagesPkBean.EFF_COPYMOVE,toInt(_sub.copyMoveForUser()),MessagesDataEffcopymove.M_P_43_COPY_TMP_MOVE, _sub.getDisplayName(),Long.toString(_sub.getCopyingMoveForUser()));
        if (_sub.getCopyingMoveForUserDef()) {
            formatMessage(MessagesPkBean.EFF_COPYMOVE,MessagesDataEffcopymove.M_P_43_NO_EFFECT_2);
            formatMessageDir(_sub.getDefaultMove());
            formatMessage(MessagesPkBean.EFF_COPYMOVE,MessagesDataEffcopymove.M_P_43_COPY_DEF_MOVE, _sub.getDisplayName());
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getMovesTransforming());
            displayEmpty(MessagesPkBean.EFF_COPYMOVE, _sub.getMovesTransforming(),MessagesDataEffcopymove.M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS, _sub.getDisplayName());
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getMovesNotToBeCopied(),MessagesPkBean.EFF_COPYMOVE,MessagesDataEffcopymove.M_P_43_MOVES_NOT_COPIED);
        }
    }

    private void batonPassSending(EffectCloneBean _sub) {
//        if (NumberUtil.signum(_sub.getMovesBatonPass().size()) * NumberUtil.signum(_sub.getMovesSending().size()) == 0) {
//            return;
//        }
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getMovesBatonPass(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_4);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getMovesSending(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_5);
    }

    private void preEff(EffectBean _sub) {
        preEff1(_sub);
        preEff2(_sub);
        preEff3(_sub);
    }

    private void preEff1(EffectBean _sub) {
        if (_sub.getEffect() instanceof EffectAccuracy) {
            formatMessage(MessagesPkBean.EFF_ACCURACY,MessagesDataEffaccuracy.M_P_37_EFFECT);
        }
        if (_sub instanceof EffectAllyBean) {
            formatMessage(MessagesPkBean.EFF_ALLY,MessagesDataEffally.M_P_38_EFFECT);
        }
        if (_sub instanceof EffectBatonPassBean) {
            formatMessage(MessagesPkBean.EFF_BATONPASS,MessagesDataEffbatonpass.M_P_39_EFFECT);
        }
        if (_sub instanceof EffectCloneBean) {
            formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT,((EffectCloneBean)_sub).getHpRateClone().toNumberString());
        }
        if (_sub instanceof EffectCommonStatisticsBean) {
            formatMessage(MessagesPkBean.EFF_COMMONSTATISTICS,MessagesDataEffcommonstatistics.M_P_41_EFFECT);
        }
        if (_sub instanceof EffectCopyFighterBean) {
            formatMessage(MessagesPkBean.EFF_COPYFIGHTER,MessagesDataEffcopyfighter.M_P_42_EFFECT);
        }
        if (_sub instanceof EffectCopyMoveBean) {
            formatMessage(MessagesPkBean.EFF_COPYMOVE,MessagesDataEffcopymove.M_P_43_EFFECT);
            formatMessage(MessagesPkBean.EFF_COPYMOVE,MessagesDataEffcopymove.M_P_43_NO_EFFECT);
            formatMessageDir(((EffectCopyMoveBean)_sub).getDefaultMove());
        }
        if (_sub instanceof EffectCounterAttackBean) {
            formatMessage(MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_EFFECT);
        }
        if (_sub instanceof EffectDamageBean) {
            formatMessage(MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_EFFECT);
        }
        if (_sub instanceof EffectDamageRateBean) {
            formatMessage(MessagesPkBean.EFF_DAMAGERATE,MessagesDataEffdamagerate.M_P_46_EFFECT);
        }
        if (_sub instanceof EffectFullHpRateBean) {
            formatMessage(MessagesPkBean.EFF_FULLHPRATE,MessagesDataEfffullhprate.M_P_48_EFFECT);
        }
        if (_sub instanceof EffectGlobalBean) {
            formatMessage(MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_EFFECT);
        }
        if (_sub instanceof EffectInvokeBean) {
            formatMessage(MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_EFFECT);
        }
        if (_sub.getEffect() instanceof EffectMultSufferedMovePower) {
            formatMessage(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,MessagesDataEffmultsufferedmovepower.M_P_51_EFFECT);
        }
        if (_sub.getEffect() instanceof EffectMultMovePower) {
            formatMessage(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,MessagesDataEffmultusedmovepower.M_P_52_EFFECT);
        }
    }

    private void preEff2(EffectBean _sub) {
        if (_sub instanceof EffectOrderBean) {
            formatMessage(MessagesPkBean.EFF_ORDER,MessagesDataEfforder.M_P_53_EFFECT);
        }
        if (_sub instanceof EffectProtectFromTypesBean) {
            formatMessage(MessagesPkBean.EFF_PROTECTFROMTYPES,MessagesDataEffprotectfromtypes.M_P_54_EFFECT);
        }
        if (_sub instanceof EffectProtectionBean) {
            formatMessage(MessagesPkBean.EFF_PROTECTION,MessagesDataEffprotection.M_P_55_EFFECT);
        }
        if (_sub instanceof EffectRemainedHpRateBean) {
            formatMessage(MessagesPkBean.EFF_REMAINEDHPRATE,MessagesDataEffremainedhprate.M_P_56_EFFECT);
        }
        if (_sub instanceof EffectRestrictionBean) {
            displayBoolTrue(MessagesPkBean.EFF_RESTRICTION,toInt(((EffectRestrictionBean)_sub).getForbidTargetUsingItem()),MessagesDataEffrestriction.M_P_57_EFFECT_ITEM);
            displayBoolTrue(MessagesPkBean.EFF_RESTRICTION,toInt(((EffectRestrictionBean)_sub).getForbidTargetUsingItem()),MessagesDataEffrestriction.M_P_57_EFFECT_ITEM_2);
            displayBoolTrue(MessagesPkBean.EFF_RESTRICTION,toInt(((EffectRestrictionBean)_sub).forbid()),MessagesDataEffrestriction.M_P_57_EFFECT_MOVE);
        }
        if (_sub instanceof EffectStatisticBean) {
            formatMessage(MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_EFFECT);
        }
        if (_sub instanceof EffectStatusBean) {
            formatMessage(MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_EFFECT);
        }
        if (_sub instanceof EffectSwitchAbilitiesBean) {
            formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_EFFECT);
        }
        if (_sub instanceof EffectSwitchItemsBean) {
            formatMessage(MessagesPkBean.EFF_SWITCHITEMS,MessagesDataEffswitchitems.M_P_61_EFFECT);
        }
        if (_sub instanceof EffectSwitchMoveTypesBean) {
            formatMessage(MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_EFFECT);
        }
        if (_sub instanceof EffectSwitchPointViewBean) {
            formatMessage(MessagesPkBean.EFF_SWITCHPOINTVIEW,MessagesDataEffswitchpointview.M_P_63_EFFECT);
        }
        if (_sub.getEffect() instanceof EffectSwitchPosition) {
            formatMessage(MessagesPkBean.EFF_SWITCHPOSITION,MessagesDataEffswitchposition.M_P_64_EFFECT);
        }
        if (_sub instanceof EffectSwitchTypesBean) {
            formatMessage(MessagesPkBean.EFF_SWITCHTYPES,MessagesDataEffswitchtypes.M_P_65_EFFECT);
        }
        if (_sub instanceof EffectTeamBean) {
            formatMessage(MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_EFFECT);
        }
        if (_sub instanceof EffectTeamWhileSendFoeBean) {
            formatMessage(MessagesPkBean.EFF_TEAMWHILESENDINGFOE,MessagesDataEffteamwhilesendingfoe.M_P_67_EFFECT);
        }
    }
    private void preEff3(EffectBean _sub) {
        if (_sub instanceof EffectUnprotectFromTypesBean) {
            formatMessage(MessagesPkBean.EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.M_P_68_EFFECT);
        }
        if (_sub instanceof EffectVarPPBean) {
            formatMessage(MessagesPkBean.EFF_VARPP,MessagesDataEffvarpp.M_P_69_EFFECT);
        }
        if (_sub instanceof EffectWinMoneyBean) {
            formatMessage(MessagesPkBean.EFF_WINMONEY,MessagesDataEffwinmoney.M_P_70_EFFECT);
        }
    }
    protected void evo(EvolutionBean _sub) {
        formatMessageDirCts(_sub.getName());
        if (_sub instanceof EvolutionLevelGenderBean) {
            formatMessageCts(MessagesPkBean.EVO_LEVEL_GENDER, MessagesDataEvolutionsEvolevelgender.M_P_76_GENDER, _sub.getDisplayBase(),Long.toString(((EvolutionLevelGenderBean) _sub).getLevel()),((EvolutionLevelGenderBean) _sub).getGender());
        } else if (_sub instanceof EvolutionLevelBean) {
            formatMessageCts(MessagesPkBean.EVO_LEVEL, MessagesDataEvolutionsEvolevel.M_P_75_LEVEL, _sub.getDisplayBase(),Long.toString(((EvolutionLevelBean) _sub).getLevel()));
        } else if (_sub instanceof EvolutionHappinessBean) {
            formatMessageCts(MessagesPkBean.EVO_HAPPINESS, MessagesDataEvolutionsEvohappiness.M_P_73_HAPPY, _sub.getDisplayBase(),Long.toString(((EvolutionHappinessBean) _sub).getMin()));
        } else if (_sub instanceof EvolutionMoveBean) {
            initLine();
            formatMessage(MessagesPkBean.EVO_MOVE,MessagesDataEvolutionsEvomove.M_P_77_MOVE, _sub.getDisplayBase());
            formatMessageDir(((EvolutionMoveBean) _sub).getMove());
            feedParentsCts();
        } else if (_sub instanceof EvolutionItemBean) {
            initLine();
            formatMessage(MessagesPkBean.EVO_ITEM,MessagesDataEvolutionsEvoitem.M_P_74_ITEM, _sub.getDisplayBase());
            formatMessageDir(((EvolutionItemBean) _sub).getItem());
            feedParentsCts();
        } else if (_sub instanceof EvolutionStoneGenderBean) {
            initLine();
            formatMessage(MessagesPkBean.EVO_STONE_GENDER,MessagesDataEvolutionsEvostonegender.M_P_79_STONE_GENDER, _sub.getDisplayBase(),((EvolutionStoneGenderBean) _sub).getGender());
            formatMessageDir(((EvolutionStoneGenderBean) _sub).getStone());
            feedParentsCts();
        } else if (_sub instanceof EvolutionStoneBean) {
            initLine();
            formatMessage(MessagesPkBean.EVO_STONE,MessagesDataEvolutionsEvostone.M_P_78_STONE, _sub.getDisplayBase());
            formatMessageDir(((EvolutionStoneBean) _sub).getStone());
            feedParentsCts();
        } else if (_sub instanceof EvolutionMoveTypeBean) {
            initLine();
            formatMessage(MessagesPkBean.EVO_TYPE,MessagesDataEvolutionsEvotype.M_P_81_TYPE, _sub.getDisplayBase(),((EvolutionMoveTypeBean)_sub).getType());
            feedParentsCts();
        } else {
            initLine();
            formatMessage(MessagesPkBean.EVO_TEAM,MessagesDataEvolutionsEvoteam.M_P_80_TEAM, _sub.getDisplayBase());
            formatMessageDir(((EvolutionTeamBean) _sub).getOther());
            feedParentsCts();
        }
    }

    protected void mapVarsInit(AbsMap<String,String> _m) {
        for (EntryCust<String,String> e: _m.entryList()) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDir(e.getKey()+" : "+e.getValue());
            feedParents();
        }
    }

    public static int width(MiniMapCoordsTileInts _miniMap) {
        int w_ = 0;
        int y_ = _miniMap.getKey(w_).getYcoords();
        while (_miniMap.isValidIndex(w_) && _miniMap.getKey(w_).getYcoords() != y_+1) {
            w_++;
        }
        return w_;
    }
    public boolean isMultiLayer(CustList<PlaceIndex> _pls,int _index) {
        return layers(_pls,_index).size() > IndexConstants.SECOND_INDEX;
    }
    public CustList<Level> layers(CustList<PlaceIndex> _pls,int _index) {
        Place pl_ = _pls.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    protected DictionaryComparator<TranslatedKey, Rate> map(StringMap<Rate> _input) {
        DictionaryComparator<TranslatedKey, Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (String m: _input.getKeys()) {
            multDamageTypesMoves_.put(buildTy(getFacade(),m), _input.getVal(m));
        }
        return multDamageTypesMoves_;
    }

    protected static CustList<TranslatedKey> listTrStringsAb(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildAb(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsIt(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildIt(_db, s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsMv(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildMv(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsPk(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildPk(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSi(CustList<Statistic> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (Statistic s: _input) {
            res_.add(buildSi(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSt(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildSt(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsTy(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildTy(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }
    public static TranslatedKey buildCa(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedCategories().getVal(_k)));
    }
    public static TranslatedKey buildTy(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedTypes().getVal(_k)));
    }
    public static TranslatedKey buildAb(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedAbilities().getVal(_k)),new RedirectAb(_k,""), PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, CST_ABILITY);
    }
    public static TranslatedKey buildIt(FacadeGame _db, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_db.getTranslatedItems().getVal(_k)),new RedirectIt(_k,"",_db.getData().getItem(_k)), "", CST_ITEM);
    }
    public static TranslatedKey buildMv(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedMoves().getVal(_k)),new RedirectMv(_k,""), PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML, CST_MOVE);
    }
    public static TranslatedKey buildPk(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedPokemon().getVal(_k)),new RedirectPk(_k,""), PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML, CST_PK);
    }
    public static TranslatedKey buildSt(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedStatus().getVal(_k)),new RedirectSt(_k,""), PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML, CST_STATUS);
    }
    public static TranslatedKey buildEnv(FacadeGame _tr, EnvironmentType _k) {
        return new TranslatedKey(_k.getEnvName(),StringUtil.nullToEmpty(_tr.getTranslatedEnvironment().getVal(_k)));
    }
    public static TranslatedKey buildSi(FacadeGame _tr, Statistic _k) {
        return new TranslatedKey(_k.getStatName(),StringUtil.nullToEmpty(_tr.getTranslatedStatistics().getVal(_k)));
    }
    public static TranslatedKey buildGender(FacadeGame _tr, Gender _k) {
        return new TranslatedKey(_k.getGenderName(),StringUtil.nullToEmpty(_tr.getTranslatedGenders().getVal(_k)));
    }
    public DataBase getDataBase() {
        return db().getData();
    }

    @Override
    public FacadeGame db() {
        return getFacade();
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        setFacade(_dataBase);
    }

    public FacadeGame getFacade() {
        return dataBase;
    }
    public void setFacade(FacadeGame _f) {
        dataBase = _f;
    }
//
//    public String getBaseEncode() {
//        return baseEncode;
//    }

//    public void setBaseEncode(String _p) {
//        this.baseEncode = _p;
//    }

    public StringMapObject getForms() {
        return (StringMapObject) getBaseForms();
    }

    public void setForms(StringMapObject _forms) {
        setBaseForms(_forms);
    }

    public static boolean inRange(long _value, long _min, long _max) {
        return _value >= _min && _value <= _max;
    }
    protected static String escapedStringQuote(String _string) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(CST_QUOTE, CST_ESCAPED_QUOTE);
        map_.put(CST_LEFT_BRACE, StringUtil.concat(CST_QUOTED_LEFT_BRACE, CST_QUOTE));
        map_.put(CST_RIGHT_BRACE, StringUtil.concat(CST_QUOTE, CST_QUOTED_RIGHT_BRACE));
        return StringUtil.formatBasic(_string, map_, false);
    }
    protected static StringList getFormattedReasons(DataBase _data, String _reasons, String _language) {
        return getFormattedReasons(_data,getReasons(_reasons),_language);
    }
    protected static StringList getFormattedReasons(DataBase _data, StringList _reasons, String _language) {
//      Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_;
        reasons_ = new StringList();
        for (String f: _reasons) {
            String formula_ = _data.getFormula(f, _language);
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(EGT, E_GT);
//            formula_ = formula_.replace(ELT, E_LT);
            reasons_.add(formula_);
        }
        return reasons_;
    }

    protected static NatStringTreeMap<String> getMapVarsFail(DataBase _data, String _fail, String _language) {
        NatStringTreeMap<String> mapVars_ = _data.getDescriptions(_fail, _language);
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        return mapVarsFail_;
    }

    protected static StringList getReasons(String _booleanString) {
        StringList reasons_;
        reasons_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        int iPostSep_ = IndexConstants.FIRST_INDEX;
        int nbLeftPar_ = 0;
        int nbRightPar_ = 0;
        while (i_ < _booleanString.length()) {
            if (_booleanString.charAt(i_) == CST_LEFT_PAR) {
                nbLeftPar_++;
            }
            if (_booleanString.charAt(i_) == CST_RIGHT_PAR) {
                nbRightPar_++;
            }
            if (_booleanString.charAt(i_) == CST_PIPE_CHAR && nbLeftPar_ == nbRightPar_) {
                reasons_.add(_booleanString.substring(iPostSep_, i_));
                iPostSep_ = i_ + 1;
            }
            i_++;
        }
        if (iPostSep_ < _booleanString.length()) {
            reasons_.add(_booleanString.substring(iPostSep_));
        }
        return reasons_;
    }

    protected static String validOrEmpty(String _str) {
        if (Rate.isValid(_str)) {
            return _str;
        }
        return DataBase.EMPTY_STRING;
    }
    public String tryRedirect(TranslatedKey _tk) {
        return AbsRedirect.tryRedirect(this,_tk.getRedirect(),_tk.getKeyForm(),_tk.getDest());
    }

    public StringMapObjectBase getBaseForms() {
        return baseForms;
    }

    public void setBaseForms(StringMapObjectBase _base) {
        this.baseForms = _base;
    }

    protected void displayStringList(String _file, CustList<String> _list, String _key, String... _values) {
        display(_file, _list, _key,_values);
        displayStringList(_list);
    }

    protected void displayStringList(CustList<String> _list) {
        new BeanDisplayList<String>(new BeanDisplayString()).display(this,_list);
    }

    protected void displayTrainerPlaceNamesList(String _file, CustList<TrainerPlaceNames> _list, String _key) {
        display(_file, _list, _key);
        displayTrainerPlaceNamesList(_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list) {
        for (TrainerPlaceNames i: _list) {
            builder.initLine();
            paintMetaLabelDisk();
            builder.formatMessageDir(i.getTrainer()+" - "+i.getPlace());
            builder.feedParents();
        }
    }
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
    }

    protected void init(FacadeGame _facade, StringMapObject _form) {
        setDataBase(_facade);
        setForms(_form);
        setLanguage(_facade.getLanguage());
        beforeDisplaying();
    }

    public TranslationsFile file(String _file) {
        return builder.file(getAppName(),_file);
    }

    public void nextPart() {
        builder.nextPart();
    }

    public void addImg(int[][] _img) {
        builder.addImg(_img);
    }

    public void paintMetaLabelDisk() {
        builder.paintMetaLabelDisk();
    }

    public void formatMessageAnc(IntBeanAction _e,String _file, String _key, String... _values) {
        builder.formatMessageAnc(getAppName(),_e,_file,_key,_values);
        nextPart();
    }

    public void buildPkList(String _file, String _key, CustList<ImgPkPlayer> _list) {
        builder.initPage();
        display(_file, _list, _key);
        buildPkList(_list);
    }

    public void buildPkList(CustList<ImgPkPlayer> _list) {
        new BeanDisplayList<ImgPkPlayer>(new BeanDisplayImgPkPlayer()).display(this,_list);
    }

    public void setTitledBorder(String _title){
        builder.setTitledBorder(_title);
    }
    public void initLine() {
        builder.initLine();
    }

    public void initGrid() {
        builder.initGrid();
    }

    public void initPage() {
        builder.initPage();
    }

    public void displayHead(Countable _info, String _file, String _keyTitle, String... _cols) {
        display(_file, _info, _keyTitle);
        initGrid();
        headerCols(_file, _info, _cols);
    }

    public void displayHeadParam(Countable _info, String[] _values, String _file, String _keyTitle, String... _cols) {
        display(_file, _info, _keyTitle,_values);
        initGrid();
        headerCols(_file, _info, _cols);
    }

    public void display(String _file, Countable _ls, String _key, String... _values) {
        if (!_ls.isEmpty() && !_key.isEmpty()) {
            builder.formatMessage(getAppName(),_file,_key,_values);
            builder.breakNext();
        }
    }
    public void headerCols(String _file, Countable _ls, String... _cols) {
        if (!_ls.isEmpty()) {
            builder.colCount(_cols.length);
            for (String h_ : _cols) {
                headerCol(_file, h_);
            }
        }
    }
    public void displayEmpty(String _file, String _value, String _key) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key);
        }
    }
    public void displayEmpty(String _file, Countable _value, String _key, String... _values) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayNotEmpty(String _file, String _value, String _key) {
        if (!_value.isEmpty()) {
            formatMessage(_file,_key,_value);
        }
    }
    public void displayBoolFull(String _file, int _value, String _one, String _two) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two);
        }
    }

    public void displayBoolFull(String _file, int _value, String _one, String _two, String... _values) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one,_values);
        } else {
            formatMessage(_file,_two,_values);
        }
    }

    public void displayIntDef(String _file, long _value, String _one, String _two) {
        if (_value == 0) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two,Long.toString(_value));
        }
    }

    public void displayIntDef(String _file, long _value, String _one) {
        displayIntDef(_file,Long.toString(_value),_one);
    }

    public void displayIntDef(String _file, Rate _value, String _one) {
        displayIntDef(_file,_value.toNumberString(),_one);
    }

    public void displayIntDef(String _file, String _value, String _one) {
        if (!StringUtil.quickEq(_value,"0")) {
            formatMessage(_file,_one,_value);
        }
    }
    public void target(TargetChoice _target, String _file, String... _poss) {
        if (_target == TargetChoice.ADJ_ADV) {
            formatMessage(_file,_poss[0]);
        }
        if (_target == TargetChoice.ADJ_MULT) {
            formatMessage(_file,_poss[1]);
        }
        if (_target == TargetChoice.ADJ_UNIQ) {
            formatMessage(_file,_poss[2]);
        }
        if (_target == TargetChoice.ALLIE) {
            formatMessage(_file,_poss[3]);
        }
        if (_target == TargetChoice.ALLIES) {
            formatMessage(_file,_poss[4]);
        }
        if (_target == TargetChoice.ANY_FOE) {
            formatMessage(_file,_poss[5]);
        }
        if (_target == TargetChoice.AUTRE_UNIQ) {
            formatMessage(_file,_poss[6]);
        }
        if (_target == TargetChoice.GLOBALE) {
            formatMessage(_file,_poss[7]);
        }
        if (_target == TargetChoice.LANCEUR) {
            formatMessage(_file,_poss[8]);
        }
        if (_target == TargetChoice.PSEUDO_GLOBALE) {
            formatMessage(_file,_poss[9]);
        }
        if (_target == TargetChoice.TOUS_ADV) {
            formatMessage(_file,_poss[10]);
        }
        if (_target == TargetChoice.UNIQUE_IMPORTE) {
            formatMessage(_file,_poss[11]);
        }
    }
    public void displayBoolFalse(String _file, int _value, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.FALSE_VALUE,_key,_values);
    }
    public void displayBoolTrue(String _file, int _value, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.TRUE_VALUE,_key,_values);
    }
    public void displayBool(String _file, int _value, int _car, String _key, String... _values) {
        if (_value == _car) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayBool(int _value, int _car, int[][] _key) {
        if (_value == _car) {
            addImg(_key);
        }
    }
    public void formatMessage(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(getAppName(),_file, _key, _values);
        builder.formatMessageDir(txt_);
    }

    public void formatMessageCts(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key, _values);
        builder.formatMessageDirCts(txt_);
        builder.nextPart();
    }

    public String formatMessageRend(String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(builder.file(getAppName(),_file).getMapping().getVal(_key), _values);
    }

    public void formatMessageDirAnc(String _txt, IntBeanAction _b) {
        builder.formatMessageDir(_txt, _b);
    }

    public void formatMessageDir(String _txt) {
        builder.formatMessageDir(_txt);
    }

    public void formatMessageDirCts(String _txt) {
        formatMessageDirCts(new TranslatedKey(_txt,_txt));
    }

    public void formatMessageDirCts(TranslatedKey _txt) {
        if (ent(_txt)) {
            builder.formatMessageDirCts(_txt.getTranslation(),new EntityClickFormEvent(this,_txt));
        } else {
            builder.formatMessageDirCts(_txt.getTranslation());
        }
    }

    public void formatMessageDir(TranslatedKey _txt) {
        if (ent(_txt)) {
            builder.formatMessageDir(_txt.getTranslation(),new EntityClickFormEvent(this,_txt));
        } else {
            builder.formatMessageDir(_txt.getTranslation());
        }
    }
    private boolean ent(TranslatedKey _txt) {
        return !_txt.getKey().isEmpty() && _txt.getRedirect() != null;
    }

    public void feedParentsCts() {
        builder.feedParentsCts();
    }

    public void feedParents() {
        builder.feedParents();
    }

    public void displayTrPkMoveTarget(String _file, TrPkMoveTarget _value) {
        formatMessageDirCts(_value.getMoveTarget().getMove());
        if (_value.getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
            formatMessageCts(_file, MessagesFightFight.M_P_90_ALLY_CHOICES_FOE);
        } else {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_PLAYER);
        }
        if (_value.getMoveTarget().getTarget().getPosition() != Fighter.BACK) {
            formatMessageDirCts(Long.toString(_value.getMoveTarget().getTarget().getPosition()));
            formatMessageDirCts(_value.getTranslation());
        } else {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
        }
    }

    public void displayActivityOfMoveEnabled(String _file, ActivityOfMove _value, String _one, String _two) {
        if (_value.isEnabled()) {
            formatMessageCts(_file,_one);
        } else {
            formatMessageCts(_file,_two);
        }
    }
    public void displayActivityOfMoveNbRound(String _file, ActivityOfMove _value, String _key) {
        if (_value.isIncrementCount()) {
            formatMessageDirCts(Long.toString(_value.getNbTurn()));
        } else {
            formatMessageCts(_file,_key);
        }
    }
    public void headerCol(String _file, String _key) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key);
        builder.formatMessageDirCtsHeader(txt_);
    }
    public IntBeanBuilderHelper getBuilder() {
        return builder;
    }

    public void setBuilder(IntBeanBuilderHelper _b) {
        this.builder = _b;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String _a) {
        this.appName = _a;
    }
}