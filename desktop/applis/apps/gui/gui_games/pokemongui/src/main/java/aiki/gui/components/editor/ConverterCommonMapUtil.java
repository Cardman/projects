package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
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

    public static GeneComponentModelEltStrSub buildPkFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return merge(_api, _facade, _sub.getFactoryPk(), new CustList<String>(), new StringMap<String>());
    }

    public static GeneComponentModelEltStrSub buildMvFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return buildMvFull(_api, _facade, _sub, new StringMap<String>());
    }

    public static GeneComponentModelEltStrSub buildMvFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty) {
        return merge(_api, _facade, _sub.getFactoryMv(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltStrSub buildItFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return merge(_api, _facade, _sub.getFactoryIt(), new CustList<String>(), new StringMap<String>());
    }
    public static GeneComponentModelEltStrSub buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return merge(_api, _facade, _sub.getFactoryTy(), new CustList<String>(), new StringMap<String>());
    }
    public static GeneComponentModelLsStrSub<String> buildAbilityList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryAb());
    }
    public static GeneComponentModelLsStrSub<String> buildMoveList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryMv());
    }
    public static GeneComponentModelLsStrSub<String> buildStatusList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactorySt());
    }
    public static GeneComponentModelLsStrSub<String> buildTypeList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryTy());
    }
    public static GeneComponentModelLsStrSub<Short> buildTmList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLsNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryTm());
    }
    public static GeneComponentModelLsStrSub<Short> buildHmList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLsNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryHm());
    }
    public static GeneComponentModelEltStrSub merge(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder, CustList<String> _excluded, AbsMap<String,String> _withEmptyStr) {
        StringMap<String> sub_ = _builder.buildMessages(_api, _sub, _withEmptyStr);
        StringList rem_ = new StringList(sub_.getKeys());
        rem_.removeAllElements(_excluded);
        TreeMap<String, String> treeFilter_ = feedTree(sub_, rem_);
        GeneComponentModelEltStr sel_ = new GeneComponentModelEltStr(_api, treeFilter_);
        GeneComponentModelEltStrSub g_ = new GeneComponentModelEltStrSub(sel_);
        feedSub(_builder, sub_, treeFilter_, sel_, g_.getSubs(), _withEmptyStr);
        return g_;
    }

    private static GeneComponentModelLsStrSub<String> mergeLs(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder) {
        StringMap<String> sub_ = _builder.buildMessages(_api, _sub);
        TreeMap<String, String> treeFilter_ = feedTree(sub_, sub_.getKeys());
        GeneComponentModelLsStr sel_ = new GeneComponentModelLsStr(_api, treeFilter_);
        GeneComponentModelLsStrSub<String> g_ = new GeneComponentModelLsStrSub<String>(sel_);
        feedSub(_builder, sub_, treeFilter_, sel_, g_.getSubs(), new StringMap<String>());
        return g_;
    }
    private static GeneComponentModelLsStrSub<Short> mergeLsNb(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder) {
        ShortMap<String> map_ = _builder.retrieveMap(_api, _sub);
        StringMap<String> messages_ = _builderMv.buildMessages(_api, _sub);
        ShortMap<String> sub_ = map(map_, messages_);
        TreeMap<Short, String> treeFilter_ = feedTreeNb(sub_, sub_.getKeys());
        GeneComponentModelLsEnum<Short> sel_ = new GeneComponentModelLsEnum<Short>(_api, treeFilter_);
        GeneComponentModelLsStrSub<Short> g_ = new GeneComponentModelLsStrSub<Short>(sel_);
        feedSubNb(_builderMv,_builder, sub_, treeFilter_, sel_, g_.getSubs(), messages_);
        return g_;
    }

    public static ShortMap<String> map(ShortMap<String> _map, AbsMap<String,String> _messages) {
        ShortMap<String> messages_ = new ShortMap<String>();
        for (EntryCust<Short,String> e: _map.entryList()) {
            messages_.addEntry(e.getKey(),StringUtil.nullToEmpty(_messages.getVal(e.getValue()))+":"+e.getKey());
        }
        return messages_;
    }

    private static void feedSub(SubscribedTranslationMessagesFactory _builder, StringMap<String> _sub, TreeMap<String, String> _treeFilter, GeneComponentModelStr _sel, IdList<SubscribedTranslation> _subs, AbsMap<String,String> _withEmpty) {
        _subs.add(_builder.buildSub(_sub, _withEmpty));
        _subs.add(_builder.buildSub(_treeFilter, _withEmpty));
        _subs.add(new SubscribedTranslationSelect(_sel));
    }
    private static void feedSubNb(SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder, ShortMap<String> _sub, TreeMap<Short, String> _treeFilter, GeneComponentModelStr _sel, IdList<SubscribedTranslation> _subs, AbsMap<String, String> _messages) {
        _subs.add(_builderMv.buildSub(_messages, new StringMap<String>()));
        _subs.add(_builder.buildSub(_sub, _messages));
        _subs.add(_builder.buildSub(_treeFilter, _messages));
        _subs.add(new SubscribedTranslationSelect(_sel));
    }
    public static TreeMap<String, String> feedTree(AbsMap<String, String> _messages, CustList<String> _rem) {
        TreeMap<String, String> tree_ = new TreeMap<String, String>(new ComparatorTrWrapper<String>().wrap(_messages));
        for (String s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }

    public static TreeMap<Short, String> feedTreeNb(AbsMap<Short, String> _messages, CustList<Short> _rem) {
        TreeMap<Short, String> tree_ = new TreeMap<Short, String>(new ComparatorTrWrapper<Short>().wrap(_messages));
        for (short s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }
    public static GeneComponentModelEltEnum<Gender> buildGender(AbstractProgramInfos _api, FacadeGame _facade){
        return new GeneComponentModelEltEnum<Gender>(_api,_facade.getData().getTranslatedGenders().getVal(_api.getLanguage()));
    }
    public static GeneComponentModelEltEnum<TargetChoice> buildTargetChoice(AbstractProgramInfos _api, FacadeGame _facade) {
        return new GeneComponentModelEltEnum<TargetChoice>(_api, _facade.getData().getTranslatedTargets().getVal(_api.getLanguage()));
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
    public static CategoryMults buildCategoryMults(CustList<EditedCrudPair<CategoryMult, Rate>> _m) {
        CategoryMults c_ = new CategoryMults(new CollCapacity(_m.size()));
        new ConverterCommonMap<CategoryMult, Rate>().feed(c_,_m);
        return c_;
    }
    public static StatisticCategoryByte buildStatisticCategoryByte(CustList<EditedCrudPair<StatisticCategory, Byte>> _m) {
        StatisticCategoryByte c_ = new StatisticCategoryByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticCategoryRate buildStatisticCategoryRate(CustList<EditedCrudPair<StatisticCategory, Rate>> _m) {
        StatisticCategoryRate c_ = new StatisticCategoryRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Rate>().feed(c_,_m);
        return c_;
    }
    public static StatisticPokemons buildStatisticPokemons(CustList<EditedCrudPair<StatisticPokemon,Byte>> _m) {
        StatisticPokemons c_ = new StatisticPokemons(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticPokemon,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticStatusList buildStatisticStatusList(CustList<EditedCrudPair<StatisticStatus,Byte>> _m) {
        StatisticStatusList c_ = new StatisticStatusList(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticStatus,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticTypeByte buildStatisticTypeByte(CustList<EditedCrudPair<StatisticType,Byte>> _m) {
        StatisticTypeByte c_ = new StatisticTypeByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticTypeRate buildStatisticTypeRate(CustList<EditedCrudPair<StatisticType,Rate>> _m) {
        StatisticTypeRate c_ = new StatisticTypeRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Rate>().feed(c_,_m);
        return c_;
    }
    public static TypesDuos buildTypesDuos(CustList<EditedCrudPair<TypesDuo,Rate>> _m) {
        TypesDuos c_ = new TypesDuos(new CollCapacity(_m.size()));
        new ConverterCommonMap<TypesDuo,Rate>().feed(c_,_m);
        return c_;
    }
    public static WeatherTypes buildWeatherTypes(CustList<EditedCrudPair<WeatherType,Rate>> _m) {
        WeatherTypes c_ = new WeatherTypes(new CollCapacity(_m.size()));
        new ConverterCommonMap<WeatherType,Rate>().feed(c_,_m);
        return c_;
    }
    public static StringMap<Evolution> buildStringMapEvolution(CustList<EditedCrudPair<String,Evolution>> _m) {
        StringMap<Evolution> c_ = new StringMap<Evolution>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Evolution>().feedMap(_m, c_);
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
    public static StringMap<String> defKeyEmpty(String _v) {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(DataBase.EMPTY_STRING,_v);
        return map_;
    }
}
