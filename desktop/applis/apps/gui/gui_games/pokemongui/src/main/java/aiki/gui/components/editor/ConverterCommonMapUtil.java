package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.util.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class ConverterCommonMapUtil {
    private ConverterCommonMapUtil() {
    }
    public static GeneComponentModelEltStrSub buildPk(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        TreeMap<String, String> tree_ = new TreeMap<String, String>(new ComparatorTr<String>(messages_));
        StringList rem_ = new StringList(messages_.getKeys());
        rem_.removeAllElements(_facade.getData().getPokedex().getKeys());
        for (String s: rem_) {
            tree_.put(s,messages_.getVal(s));
        }
        return merge(_api, tree_, new SubscribedTranslationPkMessages(messages_));
    }
    public static GeneComponentModelEltStrSub buildPkFull(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        return merge(_api, feedTree(messages_), new SubscribedTranslationPkMessages(messages_));
    }

    public static GeneComponentModelEltStrSub buildMvFull(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedMoves().getVal(_api.getLanguage()));
        return merge(_api, feedTree(messages_), new SubscribedTranslationMvMessages(messages_));
    }
    public static GeneComponentModelEltStrSub buildItFull(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedItems().getVal(_api.getLanguage()));
        return merge(_api, feedTree(messages_), new SubscribedTranslationPkMessages(messages_));
    }
    public static GeneComponentModelEltStrSub buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade){
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedTypes().getVal(_api.getLanguage()));
        return merge(_api, feedTree(messages_), new SubscribedTranslationPkMessages(messages_));
    }
    public static GeneComponentModelLsStrSub buildTypeList(AbstractProgramInfos _api, FacadeGame _facade){
        return mergeMapLs(_api, _facade.getData().getTranslatedTypes());
    }

    private static TreeMap<String, String> feedTree(StringMap<String> _messages) {
        TreeMap<String, String> tree_ = new TreeMap<String, String>(new ComparatorTr<String>(_messages));
        tree_.putAllMap(_messages);
        return tree_;
    }

    private static GeneComponentModelEltStrSub merge(AbstractProgramInfos _api, TreeMap<String, String> _tree, SubscribedTranslation _sub) {
        GeneComponentModelEltStrSub g_ = new GeneComponentModelEltStrSub(new GeneComponentModelEltStr(_api, _tree));
        g_.getSubs().add(_sub);
        return g_;
    }

    private static GeneComponentModelLsStrSub mergeMapLs(AbstractProgramInfos _api, StringMap<StringMap<String>> _trs) {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        StringMap<String> messages_ = new StringMap<String>(_trs.getVal(_api.getLanguage()));
        TreeMap<String, String> tree_ = feedTree(messages_);
        return mergeLs(_api, ids_, tree_, messages_);
    }

    private static GeneComponentModelLsStrSub mergeLs(AbstractProgramInfos _api, IdList<SubscribedTranslation> _ids, TreeMap<String, String> _tree, StringMap<String> _messages) {
        GeneComponentModelLsStrSub g_ = new GeneComponentModelLsStrSub(new GeneComponentModelLsStr(_api, _tree));
        _ids.add(new SubscribedTranslationPkMessages(_messages));
        g_.getSubs().addAllElts(_ids);
        return g_;
    }

    public static GeneComponentModelEltEnum<Gender> buildGender(AbstractProgramInfos _api, FacadeGame _facade){
        return new GeneComponentModelEltEnum<Gender>(_api,_facade.getData().getTranslatedGenders().getVal(_api.getLanguage()));
    }
    public static GeneComponentModelEltEnum<GenderRepartition> buildGenderRepartition(AbstractProgramInfos _api){
        return new GeneComponentModelEltEnum<GenderRepartition>(_api,messages(MessagesPkEditor.getMessagesEditorSelectGenderRepTr(MessagesPkEditor.getAppliTr(_api.currentLg())).getMapping()));
    }
    public static GeneComponentModelEltEnum<ExpType> buildExpType(AbstractProgramInfos _api, FacadeGame _facade){
        DataBase data_ = _facade.getData();
        IdMap<ExpType,String> messages_ = new IdMap<ExpType, String>();
        CustList<ExpType> all_ = ExpType.all();
        for (ExpType e: all_) {
            String litt_ = StringUtil.nullToEmpty(data_.getExpGrowth(e));
            if (litt_.isEmpty()) {
                messages_.addEntry(e,data_.getFormula("1",data_.getLanguage()));
            } else {
                messages_.addEntry(e,data_.getFormula(litt_,data_.getLanguage()));
            }
        }
        return new GeneComponentModelEltEnum<ExpType>(_api,messages_);
    }
    public static StringMap<StringMap<String>> toEntityLg(StringMap<StringMap<String>> _map) {
        StringMap<StringMap<String>> inv_ = new StringMap<StringMap<String>>();
        StringList next_ = new StringList();
        for (EntryCust<String,StringMap<String>> e: _map.entryList()) {
            next_.addAllElts(e.getValue().getKeys());
        }
        next_.removeDuplicates();
        for (String e: next_) {
            StringMap<String> trs_ = new StringMap<String>();
            for (EntryCust<String,StringMap<String>> l: _map.entryList()) {
                trs_.addEntry(l.getKey(), StringUtil.nullToEmpty(l.getValue().getVal(e)));
            }
            inv_.addEntry(e, trs_);
        }
        return inv_;
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

    public static StringMap<StringMap<String>> backUp(StringMap<StringMap<String>> _tr) {
        StringMap<StringMap<String>> bk_ = new StringMap<StringMap<String>>();
        for (EntryCust<String, StringMap<String>> e: _tr.entryList()) {
            bk_.addEntry(e.getKey(),new StringMap<String>(e.getValue()));
        }
        return bk_;
    }

    public static void setKey(String _key, StringMap<String> _value, StringMap<StringMap<String>> _tr) {
        for (EntryCust<String, StringMap<String>> v: _tr.entryList()) {
            v.getValue().set(_key, StringUtil.nullToEmpty(_value.getVal(v.getKey())));
        }
    }

    public static void addKey(String _key, StringMap<String> _value, StringMap<StringMap<String>> _tr) {
        for (EntryCust<String, StringMap<String>> v: _tr.entryList()) {
            v.getValue().addEntry(_key, StringUtil.nullToEmpty(_value.getVal(v.getKey())));
        }
    }

    public static void removeKey(String _key, StringMap<StringMap<String>> _tr) {
        for (StringMap<String> v: _tr.values()) {
            v.removeKey(_key);
        }
    }
}
