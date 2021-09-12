package aiki.beans.facade.comparators;

import aiki.beans.PokemonStandards;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.stds.StandardConstructor;
import code.util.CustList;

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


    public static void build(PokemonStandards _std) {
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
    private static void buildComparatorCategoryMult(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_CATEGORY_MULT, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_CATEGORY_MULT, type_);
    }
    private static void buildComparatorDirection(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_DIRECTION, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_DIRECTION, type_);
    }
    private static void buildComparatorLanguageEnvType(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_LANGUAGE_ENV_TYPE, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_LANGUAGE_ENV_TYPE, type_);
    }
    private static void buildComparatorLanguageGender(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_LANGUAGE_GENDER, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_LANGUAGE_GENDER, type_);
    }
    private static void buildComparatorLanguageSelectedBoolean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_LANGUAGE_SELECTED_BOOLEAN, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_LANGUAGE_SELECTED_BOOLEAN, type_);
    }
    private static void buildComparatorLanguageStatisic(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_LANGUAGE_STATISIC, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_LANGUAGE_STATISIC, type_);
    }
    private static void buildComparatorLanguageString(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_LANGUAGE_STRING, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_LANGUAGE_STRING, type_);
    }
    private static void buildComparatorLanguageTargetChoice(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_LANGUAGE_TARGET_CHOICE, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_LANGUAGE_TARGET_CHOICE, type_);
    }
    private static void buildComparatorMiniMapCoords(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_MINI_MAP_COORDS, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_MINI_MAP_COORDS, type_);
    }
    private static void buildComparatorMoves(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_MOVES, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_MOVES, type_);
    }
    private static void buildComparatorMoveTarget(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_MOVE_TARGET, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_MOVE_TARGET, type_);
    }
    private static void buildComparatorMoveTeamPosition(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_MOVE_TEAM_POSITION, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_MOVE_TEAM_POSITION, type_);
    }
    private static void buildComparatorPlaceIndex(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_PLACE_INDEX, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_PLACE_INDEX, type_);
    }
    private static void buildComparatorPlaceNumber(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_PLACE_NUMBER, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_PLACE_NUMBER, type_);
    }
    private static void buildComparatorPoint(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_POINT, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_POINT, type_);
    }
    private static void buildComparatorRadioLineMoves(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_RADIO_LINE_MOVES, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_RADIO_LINE_MOVES, type_);
    }
    private static void buildComparatorStatistic(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC, type_);
    }
    private static void buildComparatorStatisticCategory(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC_CATEGORY, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC_CATEGORY, type_);
    }
    private static void buildComparatorStatisticInfo(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC_INFO, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC_INFO, type_);
    }
    private static void buildComparatorStatisticInfoPkPlayer(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC_INFO_PK_PLAYER, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC_INFO_PK_PLAYER, type_);
    }
    private static void buildComparatorStatisticPokemon(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC_POKEMON, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC_POKEMON, type_);
    }
    private static void buildComparatorStatisticTr(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC_TR, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC_TR, type_);
    }
    private static void buildComparatorStatisticType(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATISTIC_TYPE, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATISTIC_TYPE, type_);
    }
    private static void buildComparatorStatusStatistic(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STATUS_STATISTIC, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STATUS_STATISTIC, type_);
    }
    private static void buildComparatorStringList(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_STRING_LIST, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_STRING_LIST, type_);
    }
    private static void buildComparatorTypesDuo(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_TYPES_DUO, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_TYPES_DUO, type_);
    }
    private static void buildComparatorWeatherType(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_WEATHER_TYPE, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_WEATHER_TYPE, type_);
    }
    private static void buildComparatorWildPokemonDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMPARATOR_WILD_POKEMON_DTO, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMPARATOR_WILD_POKEMON_DTO, type_);
    }
}
