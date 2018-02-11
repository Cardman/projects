package aiki.beans.facade.comparators;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringMap;

public final class AikiBeansFacadeComparatorsStd {
    public static final String TYPE_COMPARATOR_CATEGORY_MULT = "aiki.beans.facade.comparators.ComparatorCategoryMult";
    public static final String TYPE_COMPARATOR_DIRECTION = "aiki.beans.facade.comparators.ComparatorDirection";
    public static final String TYPE_COMPARATOR_LANGUAGE_ENV_TYPE = "aiki.beans.facade.comparators.ComparatorLanguageEnvType";
    public static final String TYPE_COMPARATOR_LANGUAGE_GENDER = "aiki.beans.facade.comparators.ComparatorLanguageGender";
    public static final String TYPE_COMPARATOR_LANGUAGE_SELECTED_BOOLEAN = "aiki.beans.facade.comparators.ComparatorLanguageSelectedBoolean";
    public static final String TYPE_COMPARATOR_LANGUAGE_STATISIC = "aiki.beans.facade.comparators.ComparatorLanguageStatisic";
    public static final String TYPE_COMPARATOR_LANGUAGE_STRING = "aiki.beans.facade.comparators.ComparatorLanguageString";
    public static final String TYPE_COMPARATOR_LANGUAGE_TARGET_CHOICE = "aiki.beans.facade.comparators.ComparatorLanguageTargetChoice";
    public static final String TYPE_COMPARATOR_MINI_MAP_COORDS = "aiki.beans.facade.comparators.ComparatorMiniMapCoords";
    public static final String TYPE_COMPARATOR_MOVES = "aiki.beans.facade.comparators.ComparatorMoves";
    public static final String TYPE_COMPARATOR_MOVE_TARGET = "aiki.beans.facade.comparators.ComparatorMoveTarget";
    public static final String TYPE_COMPARATOR_MOVE_TEAM_POSITION = "aiki.beans.facade.comparators.ComparatorMoveTeamPosition";
    public static final String TYPE_COMPARATOR_PLACE_INDEX = "aiki.beans.facade.comparators.ComparatorPlaceIndex";
    public static final String TYPE_COMPARATOR_PLACE_NUMBER = "aiki.beans.facade.comparators.ComparatorPlaceNumber";
    public static final String TYPE_COMPARATOR_POINT = "aiki.beans.facade.comparators.ComparatorPoint";
    public static final String TYPE_COMPARATOR_RADIO_LINE_MOVES = "aiki.beans.facade.comparators.ComparatorRadioLineMoves";
    public static final String TYPE_COMPARATOR_STATISTIC = "aiki.beans.facade.comparators.ComparatorStatistic";
    public static final String TYPE_COMPARATOR_STATISTIC_CATEGORY = "aiki.beans.facade.comparators.ComparatorStatisticCategory";
    public static final String TYPE_COMPARATOR_STATISTIC_INFO = "aiki.beans.facade.comparators.ComparatorStatisticInfo";
    public static final String TYPE_COMPARATOR_STATISTIC_INFO_PK_PLAYER = "aiki.beans.facade.comparators.ComparatorStatisticInfoPkPlayer";
    public static final String TYPE_COMPARATOR_STATISTIC_POKEMON = "aiki.beans.facade.comparators.ComparatorStatisticPokemon";
    public static final String TYPE_COMPARATOR_STATISTIC_TR = "aiki.beans.facade.comparators.ComparatorStatisticTr";
    public static final String TYPE_COMPARATOR_STATISTIC_TYPE = "aiki.beans.facade.comparators.ComparatorStatisticType";
    public static final String TYPE_COMPARATOR_STATUS_STATISTIC = "aiki.beans.facade.comparators.ComparatorStatusStatistic";
    public static final String TYPE_COMPARATOR_STRING_LIST = "aiki.beans.facade.comparators.ComparatorStringList";
    public static final String TYPE_COMPARATOR_TYPES_DUO = "aiki.beans.facade.comparators.ComparatorTypesDuo";
    public static final String TYPE_COMPARATOR_WEATHER_TYPE = "aiki.beans.facade.comparators.ComparatorWeatherType";
    public static final String TYPE_COMPARATOR_WILD_POKEMON_DTO = "aiki.beans.facade.comparators.ComparatorWildPokemonDto";


    public static void build(BeanLgNames _std) {
        buildComparatorCategoryMult(_std);
        buildComparatorDirection(_std);
        buildComparatorLanguageEnvType(_std);
        buildComparatorLanguageGender(_std);
        buildComparatorLanguageSelectedBoolean(_std);
        buildComparatorLanguageStatisic(_std);
        buildComparatorLanguageString(_std);
        buildComparatorLanguageTargetChoice(_std);
        buildComparatorMiniMapCoords(_std);
        buildComparatorMoves(_std);
        buildComparatorMoveTarget(_std);
        buildComparatorMoveTeamPosition(_std);
        buildComparatorPlaceIndex(_std);
        buildComparatorPlaceNumber(_std);
        buildComparatorPoint(_std);
        buildComparatorRadioLineMoves(_std);
        buildComparatorStatistic(_std);
        buildComparatorStatisticCategory(_std);
        buildComparatorStatisticInfo(_std);
        buildComparatorStatisticInfoPkPlayer(_std);
        buildComparatorStatisticPokemon(_std);
        buildComparatorStatisticTr(_std);
        buildComparatorStatisticType(_std);
        buildComparatorStatusStatistic(_std);
        buildComparatorStringList(_std);
        buildComparatorTypesDuo(_std);
        buildComparatorWeatherType(_std);
        buildComparatorWildPokemonDto(_std);
    }
    private static void buildComparatorCategoryMult(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_CATEGORY_MULT, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_CATEGORY_MULT, type_);
    }
    private static void buildComparatorDirection(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_DIRECTION, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_DIRECTION, type_);
    }
    private static void buildComparatorLanguageEnvType(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_LANGUAGE_ENV_TYPE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_LANGUAGE_ENV_TYPE, type_);
    }
    private static void buildComparatorLanguageGender(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_LANGUAGE_GENDER, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_LANGUAGE_GENDER, type_);
    }
    private static void buildComparatorLanguageSelectedBoolean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_LANGUAGE_SELECTED_BOOLEAN, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_LANGUAGE_SELECTED_BOOLEAN, type_);
    }
    private static void buildComparatorLanguageStatisic(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_LANGUAGE_STATISIC, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_LANGUAGE_STATISIC, type_);
    }
    private static void buildComparatorLanguageString(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_LANGUAGE_STRING, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_LANGUAGE_STRING, type_);
    }
    private static void buildComparatorLanguageTargetChoice(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_LANGUAGE_TARGET_CHOICE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_LANGUAGE_TARGET_CHOICE, type_);
    }
    private static void buildComparatorMiniMapCoords(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_MINI_MAP_COORDS, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_MINI_MAP_COORDS, type_);
    }
    private static void buildComparatorMoves(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_MOVES, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_MOVES, type_);
    }
    private static void buildComparatorMoveTarget(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_MOVE_TARGET, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_MOVE_TARGET, type_);
    }
    private static void buildComparatorMoveTeamPosition(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_MOVE_TEAM_POSITION, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_MOVE_TEAM_POSITION, type_);
    }
    private static void buildComparatorPlaceIndex(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_PLACE_INDEX, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_PLACE_INDEX, type_);
    }
    private static void buildComparatorPlaceNumber(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_PLACE_NUMBER, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_PLACE_NUMBER, type_);
    }
    private static void buildComparatorPoint(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_POINT, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_POINT, type_);
    }
    private static void buildComparatorRadioLineMoves(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_RADIO_LINE_MOVES, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_RADIO_LINE_MOVES, type_);
    }
    private static void buildComparatorStatistic(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC, type_);
    }
    private static void buildComparatorStatisticCategory(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC_CATEGORY, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC_CATEGORY, type_);
    }
    private static void buildComparatorStatisticInfo(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC_INFO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC_INFO, type_);
    }
    private static void buildComparatorStatisticInfoPkPlayer(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC_INFO_PK_PLAYER, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC_INFO_PK_PLAYER, type_);
    }
    private static void buildComparatorStatisticPokemon(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC_POKEMON, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC_POKEMON, type_);
    }
    private static void buildComparatorStatisticTr(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC_TR, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC_TR, type_);
    }
    private static void buildComparatorStatisticType(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATISTIC_TYPE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATISTIC_TYPE, type_);
    }
    private static void buildComparatorStatusStatistic(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STATUS_STATISTIC, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STATUS_STATISTIC, type_);
    }
    private static void buildComparatorStringList(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_STRING_LIST, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_STRING_LIST, type_);
    }
    private static void buildComparatorTypesDuo(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_TYPES_DUO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_TYPES_DUO, type_);
    }
    private static void buildComparatorWeatherType(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_WEATHER_TYPE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_WEATHER_TYPE, type_);
    }
    private static void buildComparatorWildPokemonDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMPARATOR_WILD_POKEMON_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMPARATOR_WILD_POKEMON_DTO, type_);
    }
}
