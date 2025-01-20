package aiki.comparators;

import aiki.beans.facade.comparators.*;
import aiki.beans.facade.fight.FighterNameId;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.beans.help.LanguageElementStringKey;
import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.util.*;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.MoveTeamPosition;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.MoveTarget;
import aiki.map.DataMap;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.util.Point;
import code.maths.ComparatorLgInt;
import code.maths.ComparatorRate;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.*;
import code.util.core.BoolVal;
import code.util.ints.Comparing;

public final class DictionaryComparatorUtil {
    private DictionaryComparatorUtil() {
    }
    public static DictionaryComparator<Statistic,Long> buildStatisByte(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,Long>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic,Rate> buildStatisRate(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,Rate>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic,Long> buildStatisShort(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,Long>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic,String> buildStatisString(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,String>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic, BoostHpRate> buildStatisBoostHpRate(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,BoostHpRate>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<String,AbilityData> buildAbilitiesData(DataBase _data, String _language) {
        return new DictionaryComparator<String,AbilityData>(_data.getTranslatedAbilities().getVal(_language));
    }
    public static DictionaryComparator<String,String> buildAbilities(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedAbilities().getVal(_language));
    }
    public static DictionaryComparator<String,Rate> buildCatsRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,Rate>(_data.getTranslatedCategories().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildCatsData(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedCategories().getVal(_language));
    }
    public static DictionaryComparator<String,Long> buildCatsShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Long>(_data.getTranslatedCategories().getVal(_language));
    }
    public static DictionaryComparator<String,Long> buildItemsShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Long>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,Item> buildItemsData(DataBase _data, String _language) {
        return new DictionaryComparator<String,Item>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildItemsStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,Ints> buildItemsLs(DataBase _data, String _language) {
        return new DictionaryComparator<String,Ints>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,MoveData> buildMovesData(DataBase _data, String _language) {
        return new DictionaryComparator<String,MoveData>(_data.getTranslatedMoves().getVal(_language));
    }

    public static DictionaryComparator<String,Long> buildMovesShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Long>(_data.getTranslatedMoves().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildMovesStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedMoves().getVal(_language));
    }

    public static DictionaryComparator<String, StringList> buildMovesStrList(DataBase _data, String _language) {
        return new DictionaryComparator<String,StringList>(_data.getTranslatedMoves().getVal(_language));
    }
    public static DictionaryComparator<String,Rate> buildMovesRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,Rate>(_data.getTranslatedMoves().getVal(_language));
    }

    public static DictionaryComparator<String,PokemonData> buildPkData(DataBase _data, String _language) {
        return new DictionaryComparator<String,PokemonData>(_data.getTranslatedPokemon().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildPkStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedPokemon().getVal(_language));
    }

    public static DictionaryComparator<String,Status> buildStatusData(DataBase _data, String _language) {
        return new DictionaryComparator<String,Status>(_data.getTranslatedStatus().getVal(_language));
    }

    public static DictionaryComparator<String,StatusBeginRoundAutoDamage> buildStatusAutoData(DataBase _data, String _language) {
        return new DictionaryComparator<String,StatusBeginRoundAutoDamage>(_data.getTranslatedStatus().getVal(_language));
    }

    public static DictionaryComparator<String,Rate> buildStatusRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,Rate>(_data.getTranslatedStatus().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildStatusStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedStatus().getVal(_language));
    }

    public static DictionaryComparator<String,Rate> buildTypesRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,Rate>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildTypesStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, StringList> buildTypesStrList(DataBase _data, String _language) {
        return new DictionaryComparator<String,StringList>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, IdList<Statistic>> buildTypesStaList(DataBase _data, String _language) {
        return new DictionaryComparator<String,IdList<Statistic>>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, Long> buildTypesShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Long>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, TypeDamageBoost> buildTypesTypeDamageBoost(DataBase _data, String _language) {
        return new DictionaryComparator<String,TypeDamageBoost>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, EfficiencyRate> buildTypesTypeEfficiencyRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,EfficiencyRate>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, DictionaryComparator<Statistic, Long>> buildTypesTypeDic(DataBase _data, String _language) {
        return new DictionaryComparator<String,DictionaryComparator<Statistic, Long>>(_data.getTranslatedTypes().getVal(_language));
    }
    public static DictionaryComparator<FighterNameId,DictionaryComparator<String,IdMap<FighterNameId, KeyHypothesis>>> buildCalcAll(DataBase _data, String _language) {
        return new DictionaryComparator<FighterNameId,DictionaryComparator<String,IdMap<FighterNameId, KeyHypothesis>>>(new ComparatorFighterId(_data,_language));
    }
    public static DictionaryComparator<String,IdMap<FighterNameId, KeyHypothesis>> buildCalcMoves(DataBase _data, String _language) {
        return new DictionaryComparator<String,IdMap<FighterNameId, KeyHypothesis>>(_data.getTranslatedMoves().getVal(_language));
    }
    public static DictionaryComparator<FighterNameId, KeyHypothesis> buildCalcLoc(DataBase _data, String _language) {
        return new DictionaryComparator<FighterNameId, KeyHypothesis>(new ComparatorFighterId(_data,_language));
    }
    public static DictionaryComparator<String,String> buildBoolStr(DataBase _data, String _language) {
        AbsMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = _data.getTranslatedBooleans().getVal(_language);
        StringMap<String> translated_ = new StringMap<String>();
        for (EntryCust<SelectedBoolean,String> s: translatedBooleans_.entryList()) {
            translated_.addEntry(s.getKey().getBoolName(),s.getValue());
        }
        return new DictionaryComparator<String,String>(translated_);
    }

    public static DictionaryComparator<String,String> buildGenderStr(DataBase _data, String _language) {
        AbsMap<Gender,String> translatedGenders_;
        translatedGenders_ = _data.getTranslatedGenders().getVal(_language);
        StringMap<String> translated_ = new StringMap<String>();
        for (EntryCust<Gender,String> s: translatedGenders_.entryList()) {
            translated_.addEntry(s.getKey().getGenderName(),s.getValue());
        }
        return new DictionaryComparator<String,String>(translated_);
    }

    public static DictionaryComparator<MoveTeamPosition,Long> buildMoveTeamPositionShort() {
        return new DictionaryComparator<MoveTeamPosition, Long>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPosition,String> buildMoveTeamPositionString() {
        return new DictionaryComparator<MoveTeamPosition, String>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPosition, ActivityOfMove> buildMoveTeamPositionActivityOfMove() {
        return new DictionaryComparator<MoveTeamPosition, ActivityOfMove>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPosition, AffectedMove> buildMoveTeamPositionAffectedMove() {
        return new DictionaryComparator<MoveTeamPosition, AffectedMove>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPosition, BoolVal> buildMoveTeamPositionBoolVal() {
        return new DictionaryComparator<MoveTeamPosition, BoolVal>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MiniMapCoords,int[][]> buildMiniMapImgs() {
        return new DictionaryComparator<MiniMapCoords, int[][]>(new ComparatorMiniMapCoords());
    }
    public static DictionaryComparator<MiniMapCoords,String> buildMiniMapCoords() {
        return new DictionaryComparator<MiniMapCoords, String>(new ComparatorMiniMapCoords());
    }

    public static DictionaryComparator<TypesDuo,Rate> buildTypesDuoRate(DataBase _data, String _language, boolean _translate, boolean _reverse) {
        return new DictionaryComparator<TypesDuo, Rate>(new ComparatorTypesDuo(_data, _language,_translate,_reverse));
    }

    public static DictionaryComparator<StatisticCategory,Rate> buildStatisticCategoryRate(DataBase _data, String _language) {
        return new DictionaryComparator<StatisticCategory, Rate>(new ComparatorStatisticCategory(_data, _language));
    }

    public static DictionaryComparator<StatisticCategory,Long> buildStatisticCategoryByte(DataBase _data, String _language) {
        return new DictionaryComparator<StatisticCategory, Long>(new ComparatorStatisticCategory(_data, _language));
    }

    public static DictionaryComparator<Rate,Rate> feedRateRate(MonteCarloNumber _law) {
        DictionaryComparator<Rate, Rate> r_ = buildRateRate();
        for (Rate e: _law.eventsDiff()) {
            r_.put(e, _law.normalizedRate(e));
        }
        return r_;
    }

    public static DictionaryComparator<LgInt, Rate> buildIntRate(MonteCarloNumber _law) {
        DictionaryComparator<LgInt, Rate> r_ = buildIntRate();
        for (Rate r: _law.eventsDiff()) {
            r_.put(r.intPart(), _law.normalizedRate(r));
        }
        return r_;
    }

    public static DictionaryComparator<Rate,Rate> buildRateRate() {
        return new DictionaryComparator<Rate,Rate>(new ComparatorRate());
    }

    public static DictionaryComparator<LgInt,Rate> buildIntRate() {
        return new DictionaryComparator<LgInt,Rate>(new ComparatorLgInt());
    }

    public static DictionaryComparator<LanguageElementStringKey,String> buildTrs(StringMap<StringMap<String>> _translatorCurrentLanguage, String _language, StringList _sortedLg) {
        return new DictionaryComparator<LanguageElementStringKey,String>(new ComparatorLanguageString(_translatorCurrentLanguage, _language, _sortedLg));
    }

    public static DictionaryComparator<String,String> buildEnvStr(DataBase _data, String _language) {
        StringMap<String> translated_ = trEnvs(_data, _language);
        return new DictionaryComparator<String,String>(translated_);
    }

    public static StringMap<String> trEnvs(DataBase _data, String _language) {
        StringMap<String> translated_ = new StringMap<String>();
        AbsMap<EnvironmentType, String> tr_ = _data.getTranslatedEnvironment().getVal(_language);
        for (EntryCust<EnvironmentType,String> s: tr_.entryList()) {
            translated_.addEntry(s.getKey().getEnvName(),s.getValue());
        }
        return translated_;
    }
//
//    public static DictionaryComparator<PlaceLevel,CustList<WildPokemonDto>> buildPkRep(DataBase _data, String _language) {
//        //
//        StringMap<String> translated_ = trEnvs(_data, _language);
//        return new DictionaryComparator<String,String>(translated_);
//    }
    public static Comparing<Statistic> cmpStatistic(DataBase _data, String _language) {
        return new ComparatorTrWrapper<Statistic>().wrap(_data.getTranslatedStatistics().getVal(_language));
    }
    public static Comparing<String> cmpAbilities(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedAbilities().getVal(_language));
    }
    public static Comparing<String> cmpItems(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedItems().getVal(_language));
    }
    public static Comparing<String> cmpMoves(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedMoves().getVal(_language));
    }
    public static Comparing<String> cmpPokemon(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedPokemon().getVal(_language));
    }

    public static Comparing<String> cmpStatus(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedStatus().getVal(_language));
    }

    public static Comparing<String> cmpTypes(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<Point, int[][]> buildPointString() {
        return new DictionaryComparator<Point, int[][]>(new ComparatorPoint());
    }

    public static DictionaryComparator<MoveTarget, MoveTarget> buildMoveTarget() {
        return new DictionaryComparator<MoveTarget, MoveTarget>(new ComparatorMoveTarget());
    }

    public static DictionaryComparator<CategoryMult, Rate> buildCategoryMult() {
        return new DictionaryComparator<CategoryMult, Rate>(new ComparatorCategoryMult());
    }

    public static DictionaryComparator<StatisticType, Rate> buildStatisTypeRate(DataBase _data, String _lg) {
        return new DictionaryComparator<StatisticType, Rate>(new ComparatorStatisticType(_data, _lg));
    }
    public static DictionaryComparator<StatisticType, Long> buildStatisTypeByte(DataBase _data, String _lg) {
        return new DictionaryComparator<StatisticType, Long>(new ComparatorStatisticType(_data, _lg));
    }

    public static DictionaryComparator<StatisticStatus, Long> buildStatisticStatus(DataBase _data, String _lg) {
        return new DictionaryComparator<StatisticStatus, Long>(new ComparatorStatusStatistic(_data, _lg));
    }

    public static DictionaryComparator<WeatherType, Rate> buildWeatherType(DataBase _data, String _lg) {
        return new DictionaryComparator<WeatherType, Rate>(new ComparatorWeatherType(_data, _lg));
    }

    public static DictionaryComparator<StringList, ActivityOfMove> buildActivities(DataBase _data, String _lg) {
        return new DictionaryComparator<StringList, ActivityOfMove>(new ComparatorStringList(_data, _lg, true));
    }

    public static DictionaryComparator<Integer, Integer> buildPlaces(DataMap _map) {
        return new DictionaryComparator<Integer, Integer>(new ComparatorPlaceNumber(_map));
    }

    public static DictionaryComparator<Integer, String> buildStringPlaces(DataMap _map) {
        return new DictionaryComparator<Integer, String>(new ComparatorPlaceNumber(_map));
    }

    public static DictionaryComparator<StatisticPokemon, Long> buildStatPk(DataBase _data, String _lg) {
        return new DictionaryComparator<StatisticPokemon, Long>(new ComparatorStatisticPokemon(_data, _lg));
    }
}
