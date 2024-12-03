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
import code.maths.litteral.*;
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
        return buildCatElt(_api, _facade, _sub, new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildCatElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactoryCa(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildStatus(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactorySt(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildAbilityList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryAb());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildMoveList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryMv());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildPkList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryPk());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildStatusList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactorySt());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildTypeList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryTy());
    }
    public static GeneComponentModelLsStrSub<Short,Shorts> buildTmList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLsNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryTm());
    }
    public static GeneComponentModelLsStrSub<Short,Shorts> buildHmList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLsNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryHm());
    }
    public static GeneComponentModelEltEnumSub<String> merge(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder, CustList<String> _excluded, AbsMap<String,String> _withEmptyStr) {
        return new StringSubscribeBuilderUtil(_builder).merge(_api,_sub,_excluded,_withEmptyStr);
    }

    public static GeneComponentModelLsStrSub<String,StringList> mergeLs(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder) {
        return new StringSubscribeBuilderUtil(_builder).mergeLs(_api, _sub);
    }
    private static GeneComponentModelLsStrSub<Short,Shorts> mergeLsNb(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder) {
        ShortMap<String> map_ = _builder.retrieveMap(_api, _sub);
        AbsMap<String, String> messages_ = _builderMv.buildMessages(_api, _sub);
        ShortMap<String> sub_ = map(map_, messages_);
        TreeMap<Short, String> treeFilter_ = feedTreeNb(sub_, sub_.getKeys());
        GeneComponentModelLs<Short> sel_ = new GeneComponentModelLs<Short>(_api, treeFilter_);
        GeneComponentModelLsStrSub<Short,Shorts> g_ = new GeneComponentModelLsStrSub<Short,Shorts>(sel_,new IntListConvertShort());
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
    public static GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> buildStatisticsLs(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
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
    public static MonteCarloBoolean buildMonteCarloBool(CustList<EditedCrudPair<BoolVal, LgInt>> _m) {
        MonteCarloBoolean c_ = new MonteCarloBoolean(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<BoolVal,LgInt>().feedMap(_m, c_);
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
    public static StringMap<StringList> buildStringMapStringList(CustList<EditedCrudPair<String, StringList>> _m) {
        StringMap<StringList> c_ = new StringMap<StringList>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,StringList>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<IdList<Statistic>> buildStringMapIdListStatistic(CustList<EditedCrudPair<String, IdList<Statistic>>> _m) {
        StringMap<IdList<Statistic>> c_ = new StringMap<IdList<Statistic>>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,IdList<Statistic>>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<IdMap<Statistic, Byte>> buildStringMapIdMapStatisticByte(CustList<EditedCrudPair<String, IdMap<Statistic, Byte>>> _m) {
        StringMap<IdMap<Statistic, Byte>> c_ = new StringMap<IdMap<Statistic, Byte>>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,IdMap<Statistic, Byte>>().feedMap(_m, c_);
        return c_;
    }
    public static ShortMap<String> buildShortMapString(CustList<EditedCrudPair<Short, String>> _m) {
        ShortMap<String> c_ = new ShortMap<String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Short,String>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<EfficiencyRate> buildStringMapEfficiencyRate(CustList<EditedCrudPair<String, EfficiencyRate>> _m) {
        StringMap<EfficiencyRate> c_ = new StringMap<EfficiencyRate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,EfficiencyRate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Rate> buildStringMapRate(CustList<EditedCrudPair<String, Rate>> _m) {
        StringMap<Rate> c_ = new StringMap<Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Short> buildStringMapShort(CustList<EditedCrudPair<String, Short>> _m) {
        StringMap<Short> c_ = new StringMap<Short>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Short>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Ints> buildStringMapInts(CustList<EditedCrudPair<String, Ints>> _m) {
        StringMap<Ints> c_ = new StringMap<Ints>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Ints>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,BoostHpRate> buildIdMapStatisticBoostHpRate(CustList<EditedCrudPair<Statistic, BoostHpRate>> _m) {
        IdMap<Statistic,BoostHpRate> c_ = new IdMap<Statistic,BoostHpRate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,BoostHpRate>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,Byte> buildIdMapStatisticByte(CustList<EditedCrudPair<Statistic, Byte>> _m) {
        IdMap<Statistic,Byte> c_ = new IdMap<Statistic,Byte>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Byte>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,Short> buildIdMapStatisticShort(CustList<EditedCrudPair<Statistic, Short>> _m) {
        IdMap<Statistic,Short> c_ = new IdMap<Statistic,Short>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Short>().feedMap(_m, c_);
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
    public static StringMap<TypeDamageBoost> buildStringMapTypeDamageBoost(CustList<EditedCrudPair<String,TypeDamageBoost>> _m) {
        StringMap<TypeDamageBoost> c_ = new StringMap<TypeDamageBoost>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,TypeDamageBoost>().feed(c_,_m);
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


    public static CrudGeneFormMonteCarlo<BoolVal> buildMcBool(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<BoolVal> out_ = new CrudGeneFormMonteCarlo<BoolVal>(_f, _core, new ComparingBoolKey<LgInt>());
        out_.initFormKeys(new BoolLgIntDisplayEntryCust(),new GeneComponentModelEventBoolVal(_core), new ComparingBoolKey<LgInt>());
        return out_;
    }
    public static CrudGeneFormMonteCarlo<Rate> buildMcRate(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<Rate> out_ = new CrudGeneFormMonteCarlo<Rate>(_f, _core, new ComparingRateKey<LgInt>());
        out_.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(_core), new ComparingRateKey<LgInt>());
        return out_;
    }

    public static StringMap<AbsTextField> fields(AbsPanel _line, StringMap<String> _map, AbstractProgramInfos _api) {
        StringMap<AbsTextField> fs_ = new StringMap<AbsTextField>();
        for (EntryCust<String, String> l: _map.entryList()) {
            AbsTextField txt_ = _api.getCompoFactory().newTextField(l.getValue());
            _line.add(txt_);
            fs_.addEntry(l.getKey(),txt_);
        }
        return fs_;
    }
    public static StringList complete(DataBase _db, AbstractProgramInfos _api, String _text, int _caret) {
        MbDelimiters dels_ = MathResolver.checkSyntax(_text, new ErrorStatus());
        int count_ = 0;
        for (int i = 0; i < _caret; i++) {
            if (dels_.getDelStringsChars().contains(i)) {
                count_++;
            }
        }
        StringList infos_ = new StringList();
        infos_.addAllElts(variables(_db, _api));
        if (count_ % 2 == 1) {
            infos_.addAllElts(elements(_db, _api));
        }
        return infos_;
    }
    public static StringList elements(DataBase _db, AbstractProgramInfos _api) {
        StringList str_ = new StringList();
        appendSetVars(_db.getTranslatedAbilities(), _api, str_);
        appendSetVars(_db.getTranslatedCategories(), _api, str_);
        appendSetVars(_db.getTranslatedItems(), _api, str_);
        appendSetVars(_db.getTranslatedMoves(), _api, str_);
        appendSetVars(_db.getTranslatedPokemon(), _api, str_);
        appendSetVars(_db.getTranslatedStatus(), _api, str_);
        appendSetVars(_db.getTranslatedTypes(), _api, str_);
        return str_;
    }
    public static StringList variables(DataBase _db, AbstractProgramInfos _api) {
        StringList str_ = new StringList();
        str_.add(_db.prefixNiveau());
        str_.add(_db.prefixLevelLooser());
        str_.add(_db.prefixLevelWinner());
        str_.add(_db.prefixFighterNiveau());
        str_.add(_db.prefixCibleNiveau());
        str_.add(_db.prefixLanceurNiveau());
        str_.add(_db.prefixPkSauvageNiveau());
        str_.add(_db.prefixPkUtNiveau());
        str_.add(_db.prefixBoost());
        str_.add(_db.prefixPower());
        str_.add(_db.prefixAttack());
        str_.add(_db.prefixDefense());
        str_.add(_db.prefixBaseCaptPk());
        str_.add(_db.prefixRateBallStatus());
        str_.add(_db.prefixFoePkMaxHp());
        str_.add(_db.prefixFoePkRemoteHp());
        str_.add(_db.prefixCiblePvRestants());
        str_.add(_db.prefixFighterPvRestants());
        str_.add(_db.prefixLanceurPvRestants());
        str_.add(_db.prefixCiblePvMax());
        str_.add(_db.prefixFighterPvMax());
        str_.add(_db.prefixLanceurPvMax());
        str_.add(_db.prefixSommeBoostPosCible());
        str_.add(_db.prefixSommeBoostPosLanceur());
        str_.add(_db.prefixSommeBoostPosFighter());
        str_.add(_db.prefixCibleAttaques());
        str_.add(_db.prefixCibleAttaqueChoisie());
        str_.add(_db.prefixCibleAttaquesTypes());
        str_.add(_db.prefixCibleClone());
        str_.add(_db.prefixCibleDegatsRecusTotal());
        str_.add(_db.prefixCibleDegatsRecusTotalTour());
        str_.add(_db.prefixCibleDisparait());
        str_.add(_db.prefixCibleJoue());
        str_.add(_db.prefixCibleMasse());
        str_.add(_db.prefixCibleTaille());
        str_.add(_db.prefixCibleCapacite());
        str_.add(_db.prefixCibleObjet());
        str_.add(_db.prefixCibleStatuts());
        str_.add(_db.prefixCibleTypes());
        str_.add(_db.prefixCibleGenre());
        str_.add(_db.prefixCibleBonheur());
        str_.add(_db.prefixCibleNom());
        str_.add(_db.prefixCibleDerJoue());
        str_.add(_db.prefixNbKoEquipeCible());
        str_.add(_db.prefixNbKoEquipeAdvCible());
        str_.add(_db.prefixPasPpAttaqueCible());
        str_.add(_db.prefixPasUtilisAttaqueCible());
        str_.add(_db.prefixLanceurAttaques());
        str_.add(_db.prefixLanceurAttaqueChoisie());
        str_.add(_db.prefixLanceurAttaquesTypes());
        str_.add(_db.prefixLanceurClone());
        str_.add(_db.prefixLanceurDegatsRecusTotal());
        str_.add(_db.prefixLanceurDegatsRecusTotalTour());
        str_.add(_db.prefixLanceurDisparait());
        str_.add(_db.prefixLanceurJoue());
        str_.add(_db.prefixLanceurMasse());
        str_.add(_db.prefixLanceurTaille());
        str_.add(_db.prefixLanceurCapacite());
        str_.add(_db.prefixLanceurObjet());
        str_.add(_db.prefixLanceurStatuts());
        str_.add(_db.prefixLanceurTypes());
        str_.add(_db.prefixLanceurGenre());
        str_.add(_db.prefixLanceurBonheur());
        str_.add(_db.prefixLanceurNom());
        str_.add(_db.prefixLanceurDerJoue());
        str_.add(_db.prefixNbKoEquipeLanceur());
        str_.add(_db.prefixNbKoEquipeAdvLanceur());
        str_.add(_db.prefixFighterAttaques());
        str_.add(_db.prefixFighterAttaqueChoisie());
        str_.add(_db.prefixFighterAttaquesTypes());
        str_.add(_db.prefixFighterClone());
        str_.add(_db.prefixFighterDegatsRecusTotal());
        str_.add(_db.prefixFighterDegatsRecusTotalTour());
        str_.add(_db.prefixFighterDisparait());
        str_.add(_db.prefixFighterJoue());
        str_.add(_db.prefixFighterMasse());
        str_.add(_db.prefixFighterTaille());
        str_.add(_db.prefixFighterCapacite());
        str_.add(_db.prefixFighterObjet());
        str_.add(_db.prefixFighterStatuts());
        str_.add(_db.prefixFighterTypes());
        str_.add(_db.prefixFighterGenre());
        str_.add(_db.prefixFighterBonheur());
        str_.add(_db.prefixFighterNom());
        str_.add(_db.prefixFighterDerJoue());
        str_.add(_db.prefixNbKoEquipeFighter());
        str_.add(_db.prefixNbKoEquipeAdvFighter());
        str_.add(_db.prefixPkSauvageGenre());
        str_.add(_db.prefixPkSauvageMasse());
        str_.add(_db.prefixPkSauvageVitesse());
        str_.add(_db.prefixPkSauvageTypesBase());
        str_.add(_db.prefixPkSauvagePierresEvos());
        str_.add(_db.prefixPkUtGenre());
        str_.add(_db.prefixPkUtMasse());
        str_.add(_db.prefixPkUtVitesse());
        str_.add(_db.prefixPkUtTypesBase());
        str_.add(_db.prefixPkUtPierresEvos());
        str_.add(_db.prefixCombattantEntrantClone());
        str_.add(_db.prefixCombattantEntrantTypes());
        str_.add(_db.prefixAucunBoostPossible());
        str_.add(_db.prefixTypesAttaquesResVide());
        str_.add(_db.prefixPasPartenaire());
        str_.add(_db.prefixPasPartenaireArriere());
        str_.add(_db.prefixPasPartenaireTerrain());
        str_.add(_db.prefixPasTourTerrain());
        str_.add(_db.prefixExisteGenreAssexue());
        str_.add(_db.prefixGenresEgaux());
        str_.add(_db.prefixRateEffMoveAgainstTarget());
        str_.add(_db.prefixCoeffEff());
        str_.add(_db.prefixNbUtilisationConsecutif());
        str_.add(_db.prefixAttaqueCategorie());
        str_.add(_db.prefixAttaqueTypes());
        str_.add(_db.prefixAttaqueNom());
        str_.add(_db.prefixPuissanceBase());
        str_.add(_db.prefixPasAttaqueInvoc());
        str_.add(_db.prefixPasAttaquesCopiables());
        str_.add(_db.prefixDejaCapture());
        str_.add(_db.prefixNbFlees());
        str_.add(_db.prefixMasseMoyennePk());
        str_.add(_db.prefixClimats());
        str_.add(_db.prefixNbCombattantsTerrain());
        str_.add(_db.prefixLieuCombat());
        str_.add(_db.prefixTempsTour());
        appendVars(_db, _api, str_, StringUtil.concat(_db.cibleStatis(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.fighterStatis(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.lanceurStatis(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.cibleBoost(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.fighterBoost(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.lanceurBoost(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, _db.typesPart(), _db.getTranslatedTypes());
        appendVars(_db, _api, str_, _db.movesPart(), _db.getTranslatedMoves());
        appendVars(_db, _api, str_, _db.categoriesPart(), _db.getTranslatedCategories());
        appendVars(_db, _api, str_, _db.statusPart(), _db.getTranslatedStatus());
        return str_;
    }

    private static void appendVars(DataBase _db, AbstractProgramInfos _api, StringList _str, String _p) {
        for (Statistic t: _db.getTranslatedStatistics().getVal(_api.getLanguage()).getKeys()) {
            _str.add(_db.prefixVar()+DataBase.SEP_BETWEEN_KEYS+ _p +t.getStatName());
        }
    }

    private static void appendSetVars(StringMap<StringMap<String>> _trs, AbstractProgramInfos _api, StringList _str) {
        _str.addAllElts(_trs.getVal(_api.getLanguage()).getKeys());
    }

    private static void appendVars(DataBase _db, AbstractProgramInfos _api, StringList _str, StringList _prefixes, StringMap<StringMap<String>> _trs) {
        for (String p: _prefixes) {
            for (String t: _trs.getVal(_api.getLanguage()).getKeys()) {
                _str.add(_db.getConstNonNum().getPrefixVar()+DataBase.SEP_BETWEEN_KEYS+p+t);
            }
        }
    }
}
