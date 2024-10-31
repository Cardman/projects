package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ConverterCommonMapUtil {
    private ConverterCommonMapUtil() {
    }
    public static GeneComponentModelLsStr buildTypeList(AbstractProgramInfos _api, FacadeGame _facade){
        StringMap<String> messages_ = _facade.getData().getTranslatedTypes().getVal(_api.getLanguage());
        return new GeneComponentModelLsStr(_api, messages_,new StringList(messages_.getKeys()));
    }
    public static GeneComponentModelEltGenderRepartition buildGenderRepartition(AbstractProgramInfos _api){
        return new GeneComponentModelEltGenderRepartition(_api,messages(MessagesPkEditor.getMessagesEditorSelectContentTr(MessagesPkEditor.getAppliTr(_api.currentLg())).getMapping()), GenderRepartition.all());
    }
    private static IdMap<GenderRepartition,String> messages(StringMap<String> _m) {
        IdMap<GenderRepartition,String> i_ = new IdMap<GenderRepartition, String>();
        for (EntryCust<String,String> e: _m.entryList()) {
            i_.addEntry(GenderRepartition.getGenderRepartitionByName(e.getKey()),e.getValue());
        }
        return i_;
    }
    public static CategoryMults buildCategoryMults(AbsMap<CategoryMult, Rate> _m) {
        CategoryMults c_ = new CategoryMults(new CollCapacity(_m.size()));
        new ConverterCommonMap<CategoryMult, Rate>().feed(c_,_m);
        return c_;
    }
    public static StatisticCategoryByte buildStatisticCategoryByte(AbsMap<StatisticCategory, Byte> _m) {
        StatisticCategoryByte c_ = new StatisticCategoryByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticCategoryRate buildStatisticCategoryRate(AbsMap<StatisticCategory, Rate> _m) {
        StatisticCategoryRate c_ = new StatisticCategoryRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Rate>().feed(c_,_m);
        return c_;
    }
    public static StatisticPokemons buildStatisticPokemons(AbsMap<StatisticPokemon,Byte> _m) {
        StatisticPokemons c_ = new StatisticPokemons(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticPokemon,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticStatusList buildStatisticStatusList(AbsMap<StatisticStatus,Byte> _m) {
        StatisticStatusList c_ = new StatisticStatusList(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticStatus,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticTypeByte buildStatisticTypeByte(AbsMap<StatisticType,Byte> _m) {
        StatisticTypeByte c_ = new StatisticTypeByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticTypeRate buildStatisticTypeRate(AbsMap<StatisticType,Rate> _m) {
        StatisticTypeRate c_ = new StatisticTypeRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Rate>().feed(c_,_m);
        return c_;
    }
    public static TypesDuos buildTypesDuos(AbsMap<TypesDuo,Rate> _m) {
        TypesDuos c_ = new TypesDuos(new CollCapacity(_m.size()));
        new ConverterCommonMap<TypesDuo,Rate>().feed(c_,_m);
        return c_;
    }
    public static WeatherTypes buildWeatherTypes(AbsMap<WeatherType,Rate> _m) {
        WeatherTypes c_ = new WeatherTypes(new CollCapacity(_m.size()));
        new ConverterCommonMap<WeatherType,Rate>().feed(c_,_m);
        return c_;
    }
}
