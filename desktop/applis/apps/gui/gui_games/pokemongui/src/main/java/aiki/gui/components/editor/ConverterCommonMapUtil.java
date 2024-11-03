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
        return merge(_api, _facade, new SubscribedTranslationMessagesFactoryPk(), _facade.getData().getPokedex().getKeys());
    }

    public static GeneComponentModelEltStrSub buildPkFull(AbstractProgramInfos _api, FacadeGame _facade) {
        return merge(_api, _facade, new SubscribedTranslationMessagesFactoryPk(), new CustList<String>());
    }

    public static GeneComponentModelEltStrSub buildMvFull(AbstractProgramInfos _api, FacadeGame _facade) {
        return merge(_api, _facade, new SubscribedTranslationMessagesFactoryMv(), new CustList<String>());
    }
    public static GeneComponentModelEltStrSub buildItFull(AbstractProgramInfos _api, FacadeGame _facade) {
        return merge(_api, _facade, new SubscribedTranslationMessagesFactoryIt(), new CustList<String>());
    }
    public static GeneComponentModelEltStrSub buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade){
        return merge(_api, _facade, new SubscribedTranslationMessagesFactoryTy(), new CustList<String>());
    }
    public static GeneComponentModelLsStrSub buildTypeList(AbstractProgramInfos _api, FacadeGame _facade){
        return mergeLs(_api, _facade, new SubscribedTranslationMessagesFactoryTy());
    }

    private static GeneComponentModelEltStrSub merge(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder, CustList<String> _excluded) {
        StringMap<String> sub_ = _builder.buildMessages(_api, _sub);
        StringList rem_ = new StringList(sub_.getKeys());
        rem_.removeAllElements(_excluded);
        TreeMap<String, String> treeFilter_ = feedTree(sub_, rem_);
        GeneComponentModelEltStr sel_ = new GeneComponentModelEltStr(_api, treeFilter_);
        GeneComponentModelEltStrSub g_ = new GeneComponentModelEltStrSub(sel_);
        g_.getSubs().add(_builder.buildSub(sub_));
        g_.getSubs().add(_builder.buildSub(treeFilter_));
        g_.getSubs().add(new SubscribedTranslationSelect(sel_));
        return g_;
    }

    private static TreeMap<String, String> feedTree(AbsMap<String, String> _messages, CustList<String> _rem) {
        TreeMap<String, String> tree_ = new TreeMap<String, String>(new ComparatorTr<String>(_messages));
        for (String s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }
    private static GeneComponentModelLsStrSub mergeLs(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder) {
        StringMap<String> sub_ = _builder.buildMessages(_api, _sub);
        StringList rem_ = new StringList(sub_.getKeys());
        TreeMap<String, String> treeFilter_ = feedTree(sub_, rem_);
        GeneComponentModelLsStrSub g_ = new GeneComponentModelLsStrSub(new GeneComponentModelLsStr(_api, treeFilter_));
        g_.getSubs().add(_builder.buildSub(sub_));
        g_.getSubs().add(_builder.buildSub(treeFilter_));
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
