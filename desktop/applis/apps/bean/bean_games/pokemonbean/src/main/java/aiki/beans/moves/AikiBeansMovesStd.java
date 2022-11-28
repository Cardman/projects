package aiki.beans.moves;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansMovesStd{
    public static final String WEB_HTML_MOVES_MOVES_HTML="web/html/moves/moves.html";
    public static final String WEB_HTML_MOVES_DATA_HTML="web/html/moves/data.html";
    public static final String WEB_HTML_MOVES_MOVE_LINE_HTML="web/html/moves/moveline.html";
    public static final String BEAN_MOVES="moves";
    public static final String BEAN_MOVE="move";
    public static final String BEAN_MOVE_LINE="move_line";
    public static final String TYPE_MOVE_BEAN = "aiki.beans.moves.MoveBean";
    public static final String TYPE_MOVE_LINE_BEAN = "aiki.beans.moves.MoveLineBean";
    public static final String TYPE_MOVES_BEAN = "aiki.beans.moves.MovesBean";
    private static final String CLICK_MOVES = "clickMoves";
    private static final String TYPES_DEPEND_ONLY_ON_ITEM = "typesDependOnlyOnItem";
    private static final String IS_ITEM = "isItem";
    private static final String CLICK_TYPES_BY_OWNED_ITEMS = "clickTypesByOwnedItems";
    private static final String GET_TR_TYPES_BY_OWNED_ITEMS = "getTrTypesByOwnedItems";
    private static final String TYPES_DEPEND_ONLY_ON_WEATHER = "typesDependOnlyOnWeather";
    private static final String IS_WEATHER = "isWeather";
    private static final String CLICK_TYPES_BY_WEATHERS = "clickTypesByWeathers";
    private static final String GET_TR_TYPES_BY_WEATHERS = "getTrTypesByWeathers";
    private static final String TYPES_DEPEND_ON_WEATHER_AND_ITEM = "typesDependOnWeatherAndItem";
    private static final String IS_DAMAGING_MOVE = "isDamagingMove";
    private static final String EFF_PRIM_OR_BEFORE_NOT_END_ROUND = "effPrimOrBeforeNotEndRound";
    private static final String EFF_SEC_NOT_END_ROUND = "effSecNotEndRound";
    private static final String IS_DAMAGING_DIRECT_MOVE = "isDamagingDirectMove";
    private static final String IS_ZERO_PRIORITY = "isZeroPriority";
    private static final String IS_ADJ_ADV = "isAdjAdv";
    private static final String IS_ADJ_MULT = "isAdjMult";
    private static final String IS_ADJ_UNIQ = "isAdjUniq";
    private static final String IS_ALLIE = "isAllie";
    private static final String IS_ALLIES = "isAllies";
    private static final String IS_ANY_FOE = "isAnyFoe";
    private static final String IS_AUTRE_UNIQ = "isAutreUniq";
    private static final String IS_GLOBALE = "isGlobale";
    private static final String IS_LANCEUR = "isLanceur";
    private static final String IS_PSEUDO_GLOBALE = "isPseudoGlobale";
    private static final String IS_TOUS_ADV = "isTousAdv";
    private static final String IS_UNIQUE_IMPORTE = "isUniqueImporte";
    private static final String IS_CONST_ACCURACY = "isConstAccuracy";
    private static final String IS_ZERO_PREPA_ROUND = "isZeroPrepaRound";
    private static final String CLICK_DELETED_STATUS = "clickDeletedStatus";
    private static final String GET_DELETED_STATUS = "getDeletedStatus";
    private static final String CLICK_REQUIRED_STATUS = "clickRequiredStatus";
    private static final String GET_REQUIRED_STATUS = "getRequiredStatus";
    private static final String GET_TR_ACHIEVE_DISAPPEARED_PK_USING_MOVE = "getTrAchieveDisappearedPkUsingMove";
    private static final String CLICK_ACHIEVE_DISAPPEARED_PK_USING_MOVE = "clickAchieveDisappearedPkUsingMove";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_TR_ABILITY = "getTrAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_TR_ITEM = "getTrItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_TR_MOVE = "getTrMove";
    private static final String IS_BEFORE_PRIMARY_EFFECT = "isBeforePrimaryEffect";
    private static final String IS_END_ROUND_EFFECT = "isEndRoundEffect";
    private static final String IS_PRIMARY_EFFECT = "isPrimaryEffect";
    private static final String IS_AFTER_PRIMARY_EFFECT = "isAfterPrimaryEffect";
    private static final String GET_PAGE = "getPage";
    private static final String CLICK_ITEM_SEC_EFFECT = "clickItemSecEffect";
    private static final String TRANSLATE_ITEM_SEC_EFFECT = "translateItemSecEffect";
    private static final String SWITCH_AFTER_USING_MOVE = "switchAfterUsingMove";
    private static final String IS_REPEATED_ROUND = "isRepeatedRound";
    private static final String CAN_BE_LEARNT = "canBeLearnt";
    private static final String CLICK_POKEMON = "clickPokemon";
    private static final String GET_TR_POKEMON = "getTrPokemon";
    private static final String CLICK_POKEMON_TM = "clickPokemonTm";
    private static final String GET_TR_POKEMON_TM = "getTrPokemonTm";
    private static final String CLICK_POKEMON_HM = "clickPokemonHm";
    private static final String GET_TR_POKEMON_HM = "getTrPokemonHm";
    private static final String CLICK_POKEMON_MT = "clickPokemonMt";
    private static final String GET_TR_POKEMON_MT = "getTrPokemonMt";
    private static final String SEARCH = "search";
    private static final String DISPLAY_NAME = "displayName";
    private static final String BOOSTED_TYPES = "boostedTypes";
    private static final String HAS_DEFAULT_TYPES = "hasDefaultTypes";
    private static final String TYPES = "types";
    private static final String TYPES_BY_OWNED_ITEMS = "typesByOwnedItems";
    private static final String TYPES_BY_WEATHERS = "typesByWeathers";
    private static final String CATEGORY = "category";
    private static final String PP = "pp";
    private static final String PRIORITY = "priority";
    private static final String ACCURACY = "accuracy";
    private static final String MAP_VARS_ACCURACY = "mapVarsAccuracy";
    private static final String IGN_VAR_ACCUR_USER_NEG = "ignVarAccurUserNeg";
    private static final String IGN_VAR_EVAS_TARGET_POS = "ignVarEvasTargetPos";
    private static final String NB_PREPA_ROUND = "nbPrepaRound";
    private static final String DISAPPEAR_BEFORE_USE = "disappearBeforeUse";
    private static final String DELETED_STATUS = "deletedStatus";
    private static final String REQUIRED_STATUS = "requiredStatus";
    private static final String ACHIEVE_DISAPPEARED_PK_USING_MOVE = "achieveDisappearedPkUsingMove";
    private static final String ABILITIES = "abilities";
    private static final String ITEMS = "items";
    private static final String CANNOT_KO = "cannotKo";
    private static final String AFFECTED_BY_MOVES = "affectedByMoves";
    private static final String SEC_EFFECTS_BY_ITEM = "secEffectsByItem";
    private static final String EFFECTS = "effects";
    private static final String SEC_EFFECT_IF_NO_DAMAGE = "secEffectIfNoDamage";
    private static final String NAME = "name";
    private static final String RECHARGE_ROUND = "rechargeRound";
    private static final String CONST_USER_CHOICE = "constUserChoice";
    private static final String RANK_INCREMENT_NB_ROUND = "rankIncrementNbRound";
    private static final String REPEAT_ROUND_LAW = "repeatRoundLaw";
    private static final String MOVES_LEVEL_LEARNT_BY_POKEMON = "movesLevelLearntByPokemon";
    private static final String MOVES_TM_LEARNT_BY_POKEMON = "movesTmLearntByPokemon";
    private static final String MOVES_HM_LEARNT_BY_POKEMON = "movesHmLearntByPokemon";
    private static final String MOVES_MT_LEARNT_BY_POKEMON = "movesMtLearntByPokemon";
//    private static final String INDEX = "index";
//    private static final String MOVE_LINE = "moveLine";
//    private static final String POWER = "power";
    private static final String TYPED_NAME = "typedName";
    private static final String CATEGORIES = "categories";
    private static final String TYPED_TYPE = "typedType";
    private static final String WHOLE_WORD = "wholeWord";
    private static final String MIN_ACCURACY = "minAccuracy";
    private static final String MAX_ACCURACY = "maxAccuracy";
    private static final String MIN_POWER = "minPower";
    private static final String MAX_POWER = "maxPower";
    private static final String MOVES = "moves";
//    private static final String MOVES_BEAN = "movesBean";
    private static final String LEARNT = "learnt";
    private static final String BOOLEANS = "booleans";
//    private static final String SORTED_MOVES = "sortedMoves";
    private static final String CLICK_LINK = "clickLink";

    private AikiBeansMovesStd(){}
    public static void build(PokemonStandards _std) {
        buildMoveBean(_std);
//        buildMoveLineBean(_std);
        buildMovesBean(_std);
    }
    private static void buildMoveBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MOVE_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new MoveBeanDisplayNameGet(),null));
        fields_.add(new StandardField(BOOSTED_TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanBoostedTypesGet(),null));
        fields_.add(new StandardField(HAS_DEFAULT_TYPES,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanHasDefaultTypesGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanTypesGet(),null));
        fields_.add(new StandardField(TYPES_BY_OWNED_ITEMS, BeanNatCommonLgNames.TYPE_MAP,false,false,new MoveBeanTypesByOwnedItemsGet(),null));
        fields_.add(new StandardField(TYPES_BY_WEATHERS, BeanNatCommonLgNames.TYPE_MAP,false,false,new MoveBeanTypesByWeathersGet(),null));
        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING,false,false,new MoveBeanCategoryGet(),null));
        fields_.add(new StandardField(PP, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveBeanPpGet(),null));
        fields_.add(new StandardField(PRIORITY, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveBeanPriorityGet(),null));
        fields_.add(new StandardField(ACCURACY,BeanNatCommonLgNames.STRING,false,false,new MoveBeanAccuracyGet(),null));
        fields_.add(new StandardField(MAP_VARS_ACCURACY, BeanNatCommonLgNames.TYPE_MAP,false,false,new MoveBeanMapVarsAccuracyGet(),null));
        fields_.add(new StandardField(IGN_VAR_ACCUR_USER_NEG,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanIgnVarAccurUserNegGet(),null));
        fields_.add(new StandardField(IGN_VAR_EVAS_TARGET_POS,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanIgnVarEvasTargetPosGet(),null));
        fields_.add(new StandardField(NB_PREPA_ROUND, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveBeanNbPrepaRoundGet(),null));
        fields_.add(new StandardField(DISAPPEAR_BEFORE_USE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanDisappearBeforeUseGet(),null));
        fields_.add(new StandardField(DELETED_STATUS, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanDeletedStatusGet(),null));
        fields_.add(new StandardField(REQUIRED_STATUS, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanRequiredStatusGet(),null));
        fields_.add(new StandardField(ACHIEVE_DISAPPEARED_PK_USING_MOVE, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanAchieveDisappearedPkUsingMoveGet(),null));
        fields_.add(new StandardField(ABILITIES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanAbilitiesGet(),null));
        fields_.add(new StandardField(ITEMS, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanItemsGet(),null));
        fields_.add(new StandardField(CANNOT_KO,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanCannotKoGet(),null));
        fields_.add(new StandardField(AFFECTED_BY_MOVES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanAffectedByMovesGet(),null));
        fields_.add(new StandardField(SEC_EFFECTS_BY_ITEM, BeanNatCommonLgNames.TYPE_MAP,false,false,new MoveBeanSecEffectsByItemGet(),null));
        fields_.add(new StandardField(EFFECTS, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanEffectsGet(),null));
        fields_.add(new StandardField(SEC_EFFECT_IF_NO_DAMAGE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanSecEffectIfNoDamageGet(),null));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING,false,false,new MoveBeanNameGet(),null));
        fields_.add(new StandardField(RECHARGE_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanRechargeRoundGet(),null));
        fields_.add(new StandardField(CONST_USER_CHOICE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MoveBeanConstUserChoiceGet(),null));
        fields_.add(new StandardField(RANK_INCREMENT_NB_ROUND, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveBeanRankIncrementNbRoundGet(),null));
        fields_.add(new StandardField(REPEAT_ROUND_LAW, BeanNatCommonLgNames.TYPE_MAP,false,false,new MoveBeanRepeatRoundLawGet(),null));
        fields_.add(new StandardField(MOVES_LEVEL_LEARNT_BY_POKEMON, BeanNatCommonLgNames.TYPE_MAP,false,false,new MoveBeanMovesLevelLearntByPokemonGet(),null));
        fields_.add(new StandardField(MOVES_TM_LEARNT_BY_POKEMON, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanMovesTmLearntByPokemonGet(),null));
        fields_.add(new StandardField(MOVES_HM_LEARNT_BY_POKEMON, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanMovesHmLearntByPokemonGet(),null));
        fields_.add(new StandardField(MOVES_MT_LEARNT_BY_POKEMON, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveBeanMovesMtLearntByPokemonGet(),null));
        methods_.add( new SpecNatMethod(CLICK_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickMoves()));
        methods_.add( new SpecNatMethod(TYPES_DEPEND_ONLY_ON_ITEM,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanTypesDependOnlyOnItem()));
        methods_.add( new SpecNatMethod(IS_ITEM,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsItem()));
        methods_.add( new SpecNatMethod(CLICK_TYPES_BY_OWNED_ITEMS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickTypesByOwnedItems()));
        methods_.add( new SpecNatMethod(GET_TR_TYPES_BY_OWNED_ITEMS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrTypesByOwnedItems()));
        methods_.add( new SpecNatMethod(TYPES_DEPEND_ONLY_ON_WEATHER,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanTypesDependOnlyOnWeather()));
        methods_.add( new SpecNatMethod(IS_WEATHER,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsWeather()));
        methods_.add( new SpecNatMethod(CLICK_TYPES_BY_WEATHERS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickTypesByWeathers()));
        methods_.add( new SpecNatMethod(GET_TR_TYPES_BY_WEATHERS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrTypesByWeathers()));
        methods_.add( new SpecNatMethod(TYPES_DEPEND_ON_WEATHER_AND_ITEM,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanTypesDependOnWeatherAndItem()));
        methods_.add( new SpecNatMethod(IS_DAMAGING_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsDamagingMove()));
        methods_.add( new SpecNatMethod(IS_DAMAGING_DIRECT_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsDamagingDirectMove()));
        methods_.add( new SpecNatMethod(EFF_PRIM_OR_BEFORE_NOT_END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanEffPrimOrBeforeNotEndRound()));
        methods_.add( new SpecNatMethod(EFF_SEC_NOT_END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanSecNotEndRound()));
        methods_.add( new SpecNatMethod(IS_ZERO_PRIORITY,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsZeroPriority()));
        methods_.add( new SpecNatMethod(IS_ADJ_ADV,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAdjAdv()));
        methods_.add( new SpecNatMethod(IS_ADJ_MULT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAdjMult()));
        methods_.add( new SpecNatMethod(IS_ADJ_UNIQ,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAdjUniq()));
        methods_.add( new SpecNatMethod(IS_ALLIE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAllie()));
        methods_.add( new SpecNatMethod(IS_ALLIES,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAllies()));
        methods_.add( new SpecNatMethod(IS_ANY_FOE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAnyFoe()));
        methods_.add( new SpecNatMethod(IS_AUTRE_UNIQ,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAutreUniq()));
        methods_.add( new SpecNatMethod(IS_GLOBALE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsGlobale()));
        methods_.add( new SpecNatMethod(IS_LANCEUR,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsLanceur()));
        methods_.add( new SpecNatMethod(IS_PSEUDO_GLOBALE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsPseudoGlobale()));
        methods_.add( new SpecNatMethod(IS_TOUS_ADV,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsTousAdv()));
        methods_.add( new SpecNatMethod(IS_UNIQUE_IMPORTE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsUniqueImporte()));
        methods_.add( new SpecNatMethod(IS_CONST_ACCURACY,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsConstAccuracy()));
        methods_.add( new SpecNatMethod(IS_ZERO_PREPA_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsZeroPrepaRound()));
        methods_.add( new SpecNatMethod(CLICK_DELETED_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickDeletedStatus()));
        methods_.add( new SpecNatMethod(GET_DELETED_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetDeletedStatus()));
        methods_.add( new SpecNatMethod(CLICK_REQUIRED_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickRequiredStatus()));
        methods_.add( new SpecNatMethod(GET_REQUIRED_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetRequiredStatus()));
        methods_.add( new SpecNatMethod(GET_TR_ACHIEVE_DISAPPEARED_PK_USING_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrAchieveDisappearedPkUsingMove()));
        methods_.add( new SpecNatMethod(CLICK_ACHIEVE_DISAPPEARED_PK_USING_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickAchieveDisappearedPkUsingMove()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickAbility()));
        methods_.add( new SpecNatMethod(GET_TR_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrAbility()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_TR_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrItem()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_TR_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrMove()));
        methods_.add( new SpecNatMethod(IS_BEFORE_PRIMARY_EFFECT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsBeforePrimaryEffect()));
        methods_.add( new SpecNatMethod(IS_END_ROUND_EFFECT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsEndRoundEffect()));
        methods_.add( new SpecNatMethod(IS_PRIMARY_EFFECT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsPrimaryEffect()));
        methods_.add( new SpecNatMethod(IS_AFTER_PRIMARY_EFFECT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsAfterPrimaryEffect()));
        methods_.add( new SpecNatMethod(GET_PAGE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetPage()));
        methods_.add( new SpecNatMethod(CLICK_ITEM_SEC_EFFECT,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickItemSecEffect()));
        methods_.add( new SpecNatMethod(TRANSLATE_ITEM_SEC_EFFECT,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanTranslateItemSecEffect()));
        methods_.add( new SpecNatMethod(SWITCH_AFTER_USING_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanSwitchAfterUsingMove()));
        methods_.add( new SpecNatMethod(IS_REPEATED_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanIsRepeatedRound()));
        methods_.add( new SpecNatMethod(CAN_BE_LEARNT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveBeanCanBeLearnt()));
        methods_.add( new SpecNatMethod(CLICK_POKEMON,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickPokemon()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrPokemon()));
        methods_.add( new SpecNatMethod(CLICK_POKEMON_TM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickPokemonTm()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_TM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrPokemonTm()));
        methods_.add( new SpecNatMethod(CLICK_POKEMON_HM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickPokemonHm()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_HM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrPokemonHm()));
        methods_.add( new SpecNatMethod(CLICK_POKEMON_MT,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanClickPokemonMt()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_MT,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveBeanGetTrPokemonMt()));
        _std.getStds().addEntry(TYPE_MOVE_BEAN, type_);
    }
//    private static void buildMoveLineBean(PokemonStandards _std){
//        CustList<StandardField> fields_=new CustList<StandardField>();
//        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
//        SpecialNatClass type_ = new SpecialNatClass(TYPE_MOVE_LINE_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
//        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLineBeanIndexGet(),new MoveLineBeanIndexSet()));
//        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new MoveLineBeanDisplayNameGet(),null));
//        fields_.add(new StandardField(PP, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLineBeanPpGet(),null));
//        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveLineBeanTypesGet(),null));
//        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING,false,false,new MoveLineBeanCategoryGet(),null));
//        fields_.add(new StandardField(MOVE_LINE,AikiBeansFacadeDtoStd.TYPE_MOVE_LINE,false,false,new MoveLineBeanMoveLineGet(),new MoveLineBeanMoveLineSet()));
//        fields_.add(new StandardField(PRIORITY, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLineBeanPriorityGet(),null));
//        fields_.add(new StandardField(ACCURACY,BeanNatCommonLgNames.STRING,false,false,new MoveLineBeanAccuracyGet(),null));
//        fields_.add(new StandardField(POWER,BeanNatCommonLgNames.STRING,false,false,new MoveLineBeanPowerGet(),null));
//        fields_.add(new StandardField(SORTED_MOVES, BeanNatCommonLgNames.TYPE_LIST,false,false,null,new MoveLineBeanSortedMovesSet()));
//        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveLineBeanClickMove()));
//        _std.getStds().addEntry(TYPE_MOVE_LINE_BEAN, type_);
//    }
    private static void buildMovesBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MOVES_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(TYPED_NAME,BeanNatCommonLgNames.STRING,false,false,new MovesBeanTypedNameGet(),new MovesBeanTypedNameSet()));
        fields_.add(new StandardField(CATEGORIES, BeanNatCommonLgNames.TYPE_MAP,false,false,new MovesBeanCategoriesGet(),null));
        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING,false,false,new MovesBeanCategoryGet(),new MovesBeanCategorySet()));
        fields_.add(new StandardField(TYPED_TYPE,BeanNatCommonLgNames.STRING,false,false,new MovesBeanTypedTypeGet(),new MovesBeanTypedTypeSet()));
        fields_.add(new StandardField(WHOLE_WORD,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MovesBeanWholeWordGet(),new MovesBeanWholeWordSet()));
        fields_.add(new StandardField(MIN_ACCURACY,BeanNatCommonLgNames.STRING,false,false,new MovesBeanMinAccuracyGet(),new MovesBeanMinAccuracySet()));
        fields_.add(new StandardField(MAX_ACCURACY,BeanNatCommonLgNames.STRING,false,false,new MovesBeanMaxAccuracyGet(),new MovesBeanMaxAccuracySet()));
        fields_.add(new StandardField(MIN_POWER,BeanNatCommonLgNames.STRING,false,false,new MovesBeanMinPowerGet(),new MovesBeanMinPowerSet()));
        fields_.add(new StandardField(MAX_POWER,BeanNatCommonLgNames.STRING,false,false,new MovesBeanMaxPowerGet(),new MovesBeanMaxPowerSet()));
        fields_.add(new StandardField(MOVES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MovesBeanMovesGet(),null));
//        fields_.add(new StandardField(MOVES_BEAN,BeanNatCommonLgNames.STRING,false,false,new MovesBeanMovesBeanGet(),null));
        fields_.add(new StandardField(LEARNT,BeanNatCommonLgNames.STRING,false,false,new MovesBeanLearntGet(),new MovesBeanLearntSet()));
//        fields_.add(new StandardField(SORTED_MOVES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MovesBeanSortedMovesGet(),null));
        fields_.add(new StandardField(BOOLEANS, BeanNatCommonLgNames.TYPE_MAP,false,false,new MovesBeanBooleansGet(),null));
        methods_.add( new SpecNatMethod(SEARCH,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MovesBeanSearch()));
        methods_.add( new SpecNatMethod(CLICK_LINK,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MovesBeanClickLink()));
        _std.getStds().addEntry(TYPE_MOVES_BEAN, type_);
    }
}
