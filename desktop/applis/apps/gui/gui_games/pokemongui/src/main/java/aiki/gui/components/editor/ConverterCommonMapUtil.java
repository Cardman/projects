package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import code.util.core.*;

public final class ConverterCommonMapUtil {
    private ConverterCommonMapUtil() {
    }

    public static GeneComponentModelEltEnumSub<String> buildAbFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty) {
        return merge(_api, _facade, _sub.getFactoryAb(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildPkFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return merge(_api, _facade, _sub.getFactoryPk(), new CustList<String>(), new StringMap<String>());
    }

    public static GeneComponentModelEltEnumSub<String> buildMvFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return buildMvFull(_api, _facade, _sub, new StringMap<String>());
    }

    public static GeneComponentModelEltEnumSub<String> buildMvFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty) {
        return merge(_api, _facade, _sub.getFactoryMv(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildItFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return merge(_api, _facade, _sub.getFactoryIt(), new CustList<String>(), new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return buildTypeElt(_api, _facade, _sub, new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactoryTy(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildCatElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return merge(_api, _facade, _sub.getFactoryCa(), new CustList<String>(), new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildStatus(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactorySt(), new CustList<String>(), _empty);
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
    public static GeneComponentModelEltEnumSub<String> merge(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder, CustList<String> _excluded, AbsMap<String,String> _withEmptyStr) {
        return new StringSubscribeBuilderUtil(_builder).merge(_api,_sub,_excluded,_withEmptyStr);
    }

    private static GeneComponentModelLsStrSub<String> mergeLs(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder) {
        return new StringSubscribeBuilderUtil(_builder).mergeLs(_api, _sub);
    }
    private static GeneComponentModelLsStrSub<Short> mergeLsNb(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder) {
        ShortMap<String> map_ = _builder.retrieveMap(_api, _sub);
        AbsMap<String, String> messages_ = _builderMv.buildMessages(_api, _sub);
        ShortMap<String> sub_ = map(map_, messages_);
        TreeMap<Short, String> treeFilter_ = feedTreeNb(sub_, sub_.getKeys());
        GeneComponentModelLs<Short> sel_ = new GeneComponentModelLs<Short>(_api, treeFilter_);
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

    private static void feedSubNb(SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder, ShortMap<String> _sub, TreeMap<Short, String> _treeFilter, GeneComponentModelStr _sel, IdList<SubscribedTranslation> _subs, AbsMap<String, String> _messages) {
        _subs.add(_builderMv.buildSub(_messages, new StringMap<String>()));
        _subs.add(_builder.buildSub(_sub, _messages));
        _subs.add(_builder.buildSub(_treeFilter, _messages));
        _subs.add(new SubscribedTranslationSelect(_sel));
    }

    public static TreeMap<Short, String> feedTreeNb(AbsMap<Short, String> _messages, CustList<Short> _rem) {
        TreeMap<Short, String> tree_ = new TreeMap<Short, String>(new ComparatorTrWrapper<Short>().wrap(_messages));
        for (short s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }
    public static GeneComponentModelEltEnumSub<Gender> buildGender(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact){
        return new SubscribeBuilderUtil<Gender>(_fact.getFactoryGender()).merge(_api,_facade,new CustList<Gender>(),new IdMap<Gender, String>());
    }
    public static GeneComponentModelEltEnumSub<TargetChoice> buildTargetChoice(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
        return new SubscribeBuilderUtil<TargetChoice>(_fact.getFactoryTarget()).merge(_api, _facade, new CustList<TargetChoice>(), new IdMap<TargetChoice, String>());
    }
    public static GeneComponentModelEltEnumSub<Statistic> buildStatisticsElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
        return new SubscribeBuilderUtil<Statistic>(_fact.getFactoryStat()).merge(_api, _facade, new CustList<Statistic>(), new IdMap<Statistic, String>());
    }
    public static GeneComponentModelLsStrSub<Statistic> buildStatisticsLs(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
        return new SubscribeBuilderUtil<Statistic>(_fact.getFactoryStat()).mergeLs(_api,_facade);
    }
    public static GeneComponentModelElt<GenderRepartition> buildGenderRepartition(AbstractProgramInfos _api){
        return new GeneComponentModelElt<GenderRepartition>(_api,messages(MessagesPkEditor.getMessagesEditorSelectGenderRepTr(MessagesPkEditor.getAppliTr(_api.currentLg())).getMapping()));
    }
    public static GeneComponentModelElt<ExpType> buildExpType(AbstractProgramInfos _api, FacadeGame _facade){
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
        return new GeneComponentModelElt<ExpType>(_api,messages_);
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
    public static MonteCarloString buildMonteCarloString(CustList<EditedCrudPair<String, LgInt>> _m) {
        MonteCarloString c_ = new MonteCarloString(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static MonteCarloNumber buildMonteCarloNumber(CustList<EditedCrudPair<Rate, LgInt>> _m) {
        MonteCarloNumber c_ = new MonteCarloNumber(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Rate,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static MonteCarloEnum<Statistic> buildMonteCarloEnumStatistic(CustList<EditedCrudPair<Statistic, LgInt>> _m) {
        MonteCarloEnum<Statistic> c_ = new MonteCarloEnum<Statistic>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static LongMap<Rate> buildLongMapRate(CustList<EditedCrudPair<Long, Rate>> _m) {
        LongMap<Rate> c_ = new LongMap<Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Long,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<String> buildStringMapString(CustList<EditedCrudPair<String, String>> _m) {
        StringMap<String> c_ = new StringMap<String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,String>().feedMap(_m, c_);
        return c_;
    }
    public static ShortMap<String> buildShortMapString(CustList<EditedCrudPair<Short, String>> _m) {
        ShortMap<String> c_ = new ShortMap<String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Short,String>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Rate> buildStringMapRate(CustList<EditedCrudPair<String, Rate>> _m) {
        StringMap<Rate> c_ = new StringMap<Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Ints> buildStringMapInts(CustList<EditedCrudPair<String, Ints>> _m) {
        StringMap<Ints> c_ = new StringMap<Ints>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Ints>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,Byte> buildIdMapStatisticByte(CustList<EditedCrudPair<Statistic, Byte>> _m) {
        IdMap<Statistic,Byte> c_ = new IdMap<Statistic,Byte>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Byte>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,Rate> buildIdMapStatisticRate(CustList<EditedCrudPair<Statistic, Rate>> _m) {
        IdMap<Statistic,Rate> c_ = new IdMap<Statistic,Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,String> buildIdMapStatisticString(CustList<EditedCrudPair<Statistic, String>> _m) {
        IdMap<Statistic,String> c_ = new IdMap<Statistic,String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,String>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<EnvironmentType,String> buildIdMapEnvironmentTypeString(CustList<EditedCrudPair<EnvironmentType, String>> _m) {
        IdMap<EnvironmentType,String> c_ = new IdMap<EnvironmentType,String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<EnvironmentType,String>().feedMap(_m, c_);
        return c_;
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
    public static void trigger(GeneComponentModelElt<String> _sel, String _key) {
        _sel.setupValue(_key);
        _sel.getSelect().events(null);
    }

    public static CrudGeneFormMonteCarloSub<String> buildStatusLaw(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        CrudGeneFormMonteCarloSub<String> law_ = new CrudGeneFormMonteCarloSub<String>(_f, _core);
        law_.initFormKeys(buildStatus(_core,_fac,_fact, defKeyEmpty(" ")),new DisplayEntryCustSubElementLgIntImpl<String>(_fact.getFactorySt(), _core, _fac, defKeyEmpty(" ")));
        return law_;
    }

    public static CrudGeneFormMonteCarlo<Rate> buildMcRate(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<Rate> out_ = new CrudGeneFormMonteCarlo<Rate>(_f, _core, new ComparingRateKey<LgInt>());
        out_.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(_core), new ComparingRateKey<LgInt>());
        return out_;
    }

    public static CrudGeneFormMonteCarlo<String> buildMcString(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<String> out_ = new CrudGeneFormMonteCarlo<String>(_f, _core, new ComparingStringKey<LgInt>());
        out_.initFormKeys(new StringLgIntDisplayEntryCust(),new GeneComponentModelEventString(_core), new ComparingStringKey<LgInt>());
        return out_;
    }
}
