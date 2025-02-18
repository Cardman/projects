package aiki.comparators;

import aiki.beans.TranslatedKey;
import aiki.beans.abilities.TranslatedKeyPair;
import aiki.beans.facade.comparators.*;
import aiki.beans.facade.fight.FighterNameId;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.beans.fight.*;
import aiki.beans.game.PlaceNamePk;
import aiki.beans.help.LanguageElementStringKey;
import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.util.*;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.util.AffectedMove;
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
import code.util.ints.Comparing;

public final class DictionaryComparatorUtil {
    private DictionaryComparatorUtil() {
    }
    public static DictionaryComparator<TranslatedKey,Long> buildStatisByte() {
        return new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey,Rate> buildStatisRate() {
        return new DictionaryComparator<TranslatedKey,Rate>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey,Long> buildStatisShort() {
        return new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey,String> buildStatisString() {
        return new DictionaryComparator<TranslatedKey,String>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey, BoostHpRate> buildStatisBoostHpRate() {
        return new DictionaryComparator<TranslatedKey,BoostHpRate>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<String,String> buildAbilities(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedAbilities().getVal(_language));
    }
    public static DictionaryComparator<TranslatedKey,Rate> buildCatsRate() {
        return new DictionaryComparator<TranslatedKey,Rate>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<String,String> buildCatsData(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedCategories().getVal(_language));
    }
    public static DictionaryComparator<TranslatedKey,Long> buildCatsShort() {
        return new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey,Long> buildItemsShort() {
        return new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,Item> buildItemsData() {
        return new DictionaryComparator<TranslatedKey,Item>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<String,String> buildItemsStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> buildItemsStr() {
        return new DictionaryComparator<TranslatedKey,TranslatedKey>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,Ints> buildItemsLs() {
        return new DictionaryComparator<TranslatedKey,Ints>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,MoveData> buildMovesData() {
        return new DictionaryComparator<TranslatedKey,MoveData>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,Long> buildMovesShort() {
        return new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> buildMovesStr() {
        return new DictionaryComparator<TranslatedKey,TranslatedKey>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> buildMovesStrList() {
        return new DictionaryComparator<TranslatedKey,CustList<TranslatedKey>>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey,Rate> buildMovesRate() {
        return new DictionaryComparator<TranslatedKey,Rate>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,PokemonData> buildPkData() {
        return new DictionaryComparator<TranslatedKey,PokemonData>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<String,String> buildPkStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedPokemon().getVal(_language));
    }

    public static DictionaryComparator<TranslatedKey,Status> buildStatusData() {
        return new DictionaryComparator<TranslatedKey,Status>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,AbilityData> buildAbilitiesData() {
        return new DictionaryComparator<TranslatedKey,AbilityData>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,StatusBeginRoundAutoDamage> buildStatusAutoData() {
        return new DictionaryComparator<TranslatedKey,StatusBeginRoundAutoDamage>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,Rate> buildStatusRate() {
        return new DictionaryComparator<TranslatedKey,Rate>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,String> buildStatusStrOnly() {
        return new DictionaryComparator<TranslatedKey,String>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> buildStatusStr() {
        return new DictionaryComparator<TranslatedKey,TranslatedKey>(new ComparingTranslatedKey());
    }
    public static DictionaryComparator<TranslatedKey,Rate> buildTypesRate() {
        return new DictionaryComparator<TranslatedKey,Rate>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> buildTypesStr() {
        return new DictionaryComparator<TranslatedKey,TranslatedKey>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> buildTypesStrList() {
        return new DictionaryComparator<TranslatedKey,CustList<TranslatedKey>>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey, Long> buildTypesShort() {
        return new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey, TypeDamageBoost> buildTypesTypeDamageBoost() {
        return new DictionaryComparator<TranslatedKey,TypeDamageBoost>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey, EfficiencyRate> buildTypesTypeEfficiencyRate() {
        return new DictionaryComparator<TranslatedKey,EfficiencyRate>(new ComparingTranslatedKey());
    }

    public static DictionaryComparator<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> buildTypesTypeDic() {
        return new DictionaryComparator<TranslatedKey,DictionaryComparator<TranslatedKey, Long>>(new ComparingTranslatedKey());
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

    public static DictionaryComparator<MoveTeamPositionFighterName,Long> buildMoveTeamPositionShort() {
        return new DictionaryComparator<MoveTeamPositionFighterName, Long>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPositionFighterName,String> buildMoveTeamPositionString() {
        return new DictionaryComparator<MoveTeamPositionFighterName, String>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPositionFighterName, ActivityOfMoveStill> buildMoveTeamPositionActivityOfMove() {
        return new DictionaryComparator<MoveTeamPositionFighterName, ActivityOfMoveStill>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPositionFighterName, AffectedMove> buildMoveTeamPositionAffectedMove() {
        return new DictionaryComparator<MoveTeamPositionFighterName, AffectedMove>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MoveTeamPositionFighterName, Integer> buildMoveTeamPositionBoolVal() {
        return new DictionaryComparator<MoveTeamPositionFighterName, Integer>(new ComparatorMoveTeamPosition());
    }

    public static DictionaryComparator<MiniMapCoords,int[][]> buildMiniMapImgs() {
        return new DictionaryComparator<MiniMapCoords, int[][]>(new ComparatorMiniMapCoords());
    }
    public static DictionaryComparator<MiniMapCoords,String> buildMiniMapCoords() {
        return new DictionaryComparator<MiniMapCoords, String>(new ComparatorMiniMapCoords());
    }

    public static DictionaryComparator<TranslatedKeyPair,Rate> buildTypesDuoRate() {
        return new DictionaryComparator<TranslatedKeyPair, Rate>(new ComparatorTranslatedKeyPair());
    }

    public static DictionaryComparator<TranslatedKeyPair,Rate> buildStatisticCategoryRate() {
        return new DictionaryComparator<TranslatedKeyPair, Rate>(new ComparatorTranslatedKeyPair());
    }

    public static DictionaryComparator<TranslatedKeyPair,Long> buildStatisticCategoryByte() {
        return new DictionaryComparator<TranslatedKeyPair, Long>(new ComparatorTranslatedKeyPair());
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

    public static DictionaryComparator<TranslatedKey,TranslatedKey> buildEnvStr() {
        return new DictionaryComparator<TranslatedKey,TranslatedKey>(new ComparingTranslatedKey());
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
    public static Comparing<TranslatedKey> cmpAbilities() {
        return new ComparingTranslatedKey();
    }
    public static Comparing<TranslatedKey> cmpItems() {
        return new ComparingTranslatedKey();
    }
    public static Comparing<String> cmpMoves(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedMoves().getVal(_language));
    }

    public static Comparing<String> cmpTypes(DataBase _data, String _language) {
        return new ComparatorTrWrapper<String>().wrap(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<Point, int[][]> buildPointString() {
        return new DictionaryComparator<Point, int[][]>(new ComparatorPoint());
    }

    public static DictionaryComparator<TrPkMoveTarget, TrPkMoveTarget> buildMoveTarget() {
        return new DictionaryComparator<TrPkMoveTarget, TrPkMoveTarget>(new ComparatorMoveTarget());
    }

    public static DictionaryComparator<TranslatedKeyPair, Rate> buildCategoryMult() {
        return new DictionaryComparator<TranslatedKeyPair, Rate>(new ComparatorTranslatedKeyPair());
    }

    public static DictionaryComparator<TranslatedKeyPair, Rate> buildStatisTypeRate() {
        return new DictionaryComparator<TranslatedKeyPair, Rate>(new ComparatorTranslatedKeyPair());
    }
    public static DictionaryComparator<TranslatedKeyPair, Long> buildStatisTypeByte() {
        return new DictionaryComparator<TranslatedKeyPair, Long>(new ComparatorTranslatedKeyPair());
    }

    public static DictionaryComparator<TranslatedKeyPair, Long> buildStatisticStatus() {
        return new DictionaryComparator<TranslatedKeyPair, Long>(new ComparatorTranslatedKeyPair());
    }

    public static DictionaryComparator<TranslatedKeyPair, Rate> buildWeatherType() {
        return new DictionaryComparator<TranslatedKeyPair, Rate>(new ComparatorTranslatedKeyPair());
    }

    public static DictionaryComparator<StringList, ActivityOfMove> buildActivities() {
        return new DictionaryComparator<StringList, ActivityOfMove>(new ComparatorStringList());
    }

    public static DictionaryComparator<Integer, PlaceNamePk> buildPlaces(DataMap _map) {
        return new DictionaryComparator<Integer, PlaceNamePk>(new ComparatorPlaceNumber(_map));
    }

    public static DictionaryComparator<Integer, String> buildStringPlaces(DataMap _map) {
        return new DictionaryComparator<Integer, String>(new ComparatorPlaceNumber(_map));
    }

    public static DictionaryComparator<TranslatedKeyPair, Long> buildStatPk() {
        return new DictionaryComparator<TranslatedKeyPair, Long>(new ComparatorTranslatedKeyPair());
    }
}
