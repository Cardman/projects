package aiki.comparators;

import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import aiki.fight.util.TypeDamageBoost;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.*;

public final class DictionaryComparatorUtil {
    private DictionaryComparatorUtil() {
    }
    public static DictionaryComparator<Statistic,Byte> buildStatisByte(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,Byte>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic,Rate> buildStatisRate(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,Rate>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic,Short> buildStatisShort(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,Short>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic,String> buildStatisString(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,String>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<Statistic, BoostHpRate> buildStatisBoostHpRate(DataBase _data, String _language) {
        return new DictionaryComparator<Statistic,BoostHpRate>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static DictionaryComparator<String,String> buildAbilities(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedAbilities().getVal(_language));
    }
    public static DictionaryComparator<String,Rate> buildCatsRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,Rate>(_data.getTranslatedCategories().getVal(_language));
    }
    public static DictionaryComparator<String,Short> buildCatsShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Short>(_data.getTranslatedCategories().getVal(_language));
    }
    public static DictionaryComparator<String,Short> buildItemsShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Short>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,String> buildItemsStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,Ints> buildItemsLs(DataBase _data, String _language) {
        return new DictionaryComparator<String,Ints>(_data.getTranslatedItems().getVal(_language));
    }

    public static DictionaryComparator<String,Short> buildMovesShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Short>(_data.getTranslatedMoves().getVal(_language));
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

    public static DictionaryComparator<String,String> buildPkStr(DataBase _data, String _language) {
        return new DictionaryComparator<String,String>(_data.getTranslatedPokemon().getVal(_language));
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

    public static DictionaryComparator<String, Short> buildTypesShort(DataBase _data, String _language) {
        return new DictionaryComparator<String,Short>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, TypeDamageBoost> buildTypesTypeDamageBoost(DataBase _data, String _language) {
        return new DictionaryComparator<String,TypeDamageBoost>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, EfficiencyRate> buildTypesTypeEfficiencyRate(DataBase _data, String _language) {
        return new DictionaryComparator<String,EfficiencyRate>(_data.getTranslatedTypes().getVal(_language));
    }

    public static DictionaryComparator<String, DictionaryComparator<Statistic, Byte>> buildTypesTypeDic(DataBase _data, String _language) {
        return new DictionaryComparator<String,DictionaryComparator<Statistic, Byte>>(_data.getTranslatedTypes().getVal(_language));
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
    public static ComparatorTr<Statistic> cmpStatistic(DataBase _data, String _language) {
        return new ComparatorTr<Statistic>(_data.getTranslatedStatistics().getVal(_language));
    }
    public static ComparatorTr<String> cmpAbilities(DataBase _data, String _language) {
        return new ComparatorTr<String>(_data.getTranslatedAbilities().getVal(_language));
    }
    public static ComparatorTr<String> cmpItems(DataBase _data, String _language) {
        return new ComparatorTr<String>(_data.getTranslatedItems().getVal(_language));
    }
    public static ComparatorTr<String> cmpMoves(DataBase _data, String _language) {
        return new ComparatorTr<String>(_data.getTranslatedMoves().getVal(_language));
    }
    public static ComparatorTr<String> cmpPokemon(DataBase _data, String _language) {
        return new ComparatorTr<String>(_data.getTranslatedPokemon().getVal(_language));
    }

    public static ComparatorTr<String> cmpStatus(DataBase _data, String _language) {
        return new ComparatorTr<String>(_data.getTranslatedStatus().getVal(_language));
    }

    public static ComparatorTr<String> cmpTypes(DataBase _data, String _language) {
        return new ComparatorTr<String>(_data.getTranslatedTypes().getVal(_language));
    }
}
