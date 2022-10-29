package aiki.beans;

import aiki.beans.facade.dto.ItemLine;
import aiki.beans.facade.dto.MoveLine;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.solution.dto.PlaceTrainerDto;
import aiki.beans.facade.solution.dto.StepDto;
import aiki.beans.facade.solution.dto.WildPokemonDto;
import aiki.beans.game.DifficultyCommonBean;
import aiki.beans.validators.PositiveRateValidator;
import aiki.beans.validators.RateValidator;
import aiki.beans.validators.ShortValidator;
import aiki.beans.validators.UnselectedRadio;
import aiki.comparators.DictionaryComparator;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.Statistic;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.*;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceLevel;
import aiki.util.Point;
import code.bean.nat.*;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.fwd.AdvNatBlockBuilder;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
public abstract class PokemonStandards extends BeanNatCommonLgNames implements AbstractNatImpLgNames {
    public static final String TYPE_ACTIVITY_OF_MOVE = "aiki.game.fight.ActivityOfMove";
    public static final String TYPE_MOVE_TARGET = "aiki.game.fight.util.MoveTarget";
    public static final String TYPE_TARGET_COORDS = "aiki.game.fight.TargetCoords";
    public static final String TYPE_USES_OF_MOVE = "aiki.game.UsesOfMove";
    public static final String TYPE_COPIED_MOVE = "aiki.game.fight.util.CopiedMove";
    public static final String TYPE_MOVE_TEAM_POSITION = "aiki.game.fight.MoveTeamPosition";
    public static final String TYPE_AFFECTED_MOVE = "aiki.game.fight.util.AffectedMove";
    public static final String TYPE_STACKS_OF_USES = "aiki.game.fight.StacksOfUses";
    public static final String TYPE_ANTICIPATION = "aiki.game.fight.Anticipation";
    public static final String TYPE_TYPE_DAMAGE_BOOST = "aiki.fight.util.TypeDamageBoost";
    public static final String TYPE_EFFICIENCY_RATE = "aiki.fight.util.EfficiencyRate";
    public static final String TYPE_BOOST_HP_RATE = "aiki.fight.util.BoostHpRate";
    public static final String TYPE_PK_TRAINER = "aiki.map.pokemon.PkTrainer";
    public static final String TYPE_POKEMON = "aiki.map.pokemon.Pokemon";
    public static final String TYPE_AREA_APPARITION = "aiki.map.levels.AreaApparition";
    public static final String TYPE_WILD_PK = "aiki.map.pokemon.WildPk";
    public static final String TYPE_PLACE = "aiki.map.places.Place";
    public static final String TYPE_TYPES_DUO = "aiki.fight.util.TypesDuo";
    public static final String TYPE_CATEGORY_MULT = "aiki.fight.util.CategoryMult";
    public static final String TYPE_LEVEL_MOVE = "aiki.fight.util.LevelMove";
    public static final String TYPE_POKEMON_PLAYER = "aiki.map.pokemon.PokemonPlayer";
    public static final String TYPE_EFFECT_PARTNER_STATUS = "aiki.fight.status.effects.EffectPartnerStatus";
    public static final String TYPE_TRAINER_PLACE_NAMES = "aiki.fight.pokemon.TrainerPlaceNames";
    public static final String TYPE_EFFECT_WHILE_SENDING = "aiki.fight.effects.EffectWhileSending";
    public static final String TYPE_ALLY = "aiki.map.characters.Ally";
    public static final String TYPE_TEMP_TRAINER = "aiki.map.characters.TempTrainer";
//    public static final String TYPE_TRAINER_ONE_FIGHT = "aiki.map.characters.TrainerOneFight";
    public static final String TYPE_TRAINER = "aiki.map.characters.Trainer";
//    public static final String TYPE_PERSON = "aiki.map.characters.Person";
//    public static final String TYPE_FULL_RATE_VALIDATOR = "aiki.beans.validators.RateValidator";
//    public static final String TYPE_FULL_POSITIVE_RATE_VALIDATOR = "aiki.beans.validators.PositiveRateValidator";
//    public static final String TYPE_FULL_SHORT_VALIDATOR = "aiki.beans.validators.ShortValidator";
//    public static final String TYPE_FULL_UNSELECTED_RADIO = "aiki.beans.validators.UnselectedRadio";
    public static final String TYPE_RATE_VALIDATOR = "RateValidator";
    public static final String TYPE_POSITIVE_RATE_VALIDATOR = "PositiveRateValidator";
    public static final String TYPE_SHORT_VALIDATOR = "ShortValidator";
    public static final String TYPE_UNSELECTED_RADIO = "UnselectedRadio";
    private static final String IS_ZERO = "isZero";
    private static final String IS_ZERO_OR_GT = "isZeroOrGt";
    private static final String ABS_NB = "absNb";

    private FacadeGame dataBase;
    protected PokemonStandards(){
        getValidators().addEntry("positive_rate_validator",new PositiveRateValidator());
        getValidators().addEntry("rate_validator",new RateValidator());
        getValidators().addEntry("short_validator",new ShortValidator());
        getValidators().addEntry("selected_radio",new UnselectedRadio());
    }
    @Override
    public void buildOther() {
        buildAddon();
        buildRate(this);
        buildLgInt(this);
        buildRateValidator(this);
        buildPositiveRateValidator(this);
        buildShortValidator(this);
        buildUnselectedRadio(this);
    }
    protected abstract void buildAddon();
    private static void buildRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(IS_ZERO,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new RateIsZero()));
        methods_.add( new SpecNatMethod(IS_ZERO_OR_GT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new RateIsZeroOrGt()));
        methods_.add( new SpecNatMethod(ABS_NB,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new RateAbsNb()));
        _std.getStds().addEntry(TYPE_RATE, type_);
    }
    private static void buildLgInt(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_LG_INT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_LG_INT, type_);
    }
    private static void buildRateValidator(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_RATE_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_RATE_VALIDATOR, type_);
    }
    private static void buildPositiveRateValidator(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POSITIVE_RATE_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_POSITIVE_RATE_VALIDATOR, type_);
    }
    private static void buildShortValidator(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_SHORT_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_SHORT_VALIDATOR, type_);
    }
    private static void buildUnselectedRadio(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_UNSELECTED_RADIO, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_UNSELECTED_RADIO, type_);
    }

    @Override
    public InvokedPageOutput processAfterInvoke(Configuration _conf, String _dest, String _curUrl, String _beanName, StringMapObjectBase _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        StringMapObject stringMapObject_ = new StringMapObject();
        stringMapObject_.putAllMapGene(_bean);
        String currentBeanName_;
        NatDocumentBlock rendDocumentBlock_ = getRender(_dest,_curUrl);
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(currentBeanName_);
        setForms(stringMapObject_, bean_);
        _rendStack.clearPages();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack);
        return new InvokedPageOutput(getDest(_dest,_curUrl),res_);
    }

    public void setForms(StringMapObject _forms, Struct _bean) {
        ((WithForms) ((PokemonBeanStruct) _bean).getBean()).setForms(_forms);
    }

    @Override
    public void setBeanForms(Struct _mainBean, Struct _called) {
        fwd((PokemonBeanStruct) _mainBean, (PokemonBeanStruct) _called);
    }

    public static void fwd(PokemonBeanStruct _mainBean, PokemonBeanStruct _called) {
        StringMapObject forms_ = ((WithForms) _called.getBean()).getForms();
        StringMapObject formsMap_ = ((WithForms) _mainBean.getBean()).getForms();
        forms_.putAllMap(formsMap_);
    }

//    public static PkTrainer toPkTrainer(Struct _inst) {
//        if (!(_inst instanceof PkStruct)) {
//            return Instances.newPkTrainer();
//        }
//        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
//        if (instance_ instanceof PkTrainer) {
//            return (PkTrainer) instance_;
//        }
//        return Instances.newPkTrainer();
//    }
//
//    public static WildPk toWildPk(Struct _inst) {
//        if (!(_inst instanceof PkStruct)) {
//            return Instances.newWildPk();
//        }
//        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
//        if (instance_ instanceof WildPk) {
//            return (WildPk) instance_;
//        }
//        return Instances.newWildPk();
//    }
//
//    public static PokemonPlayer toPokemonPlayer(Struct _inst) {
//        if (!(_inst instanceof PkStruct)) {
//            return Instances.newPokemonPlayer();
//        }
//        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
//        if (instance_ instanceof PokemonPlayer) {
//            return (PokemonPlayer) instance_;
//        }
//        return Instances.newPokemonPlayer();
//    }

    protected PokemonBeanStruct bean(CommonBean _bean, String _lg) {
        _bean.setDataBase(dataBase);
        _bean.setForms(new StringMapObject());
        _bean.setLanguage(_lg);
        return new PokemonBeanStruct(_bean);
    }

    public PokemonBeanStruct beanDiffCommon(String _language) {
        return bean(new DifficultyCommonBean(), _language);
    }
    public static NatArrayStruct getBigNatMap(AbsMap<String, AbsBasicTreeMap<Rate, Rate>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, AbsBasicTreeMap<Rate, Rate>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),getRateRate(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getBigNatMapSta(AbsMap<String, DictionaryComparator<Statistic, Byte>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, DictionaryComparator<Statistic, Byte>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),getStaByte(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStaBoost(AbsMap<Statistic, BoostHpRate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Statistic, BoostHpRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new BoostHpRateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcStr(AbsMap<MiniMapCoords, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MiniMapCoords, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPtStr(AbsMap<Point, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Point, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getEffRateStr(AbsMap<String, EfficiencyRate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, EfficiencyRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),new EfficiencyRateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcByteMap(AbsMap<StatisticPokemon, Byte> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StatisticPokemon, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStatisticTypeByteMap(AbsMap<StatisticType, Byte> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StatisticType, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStatisticCategoryByteMap(AbsMap<StatisticCategory, Byte> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StatisticCategory, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStatisticStatusByteMap(AbsMap<StatisticStatus, Byte> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StatisticStatus, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StatisticStatusStruct(e.getKey()),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getCatMultRateMap(AbsMap<CategoryMult, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<CategoryMult, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new CategoryMultStruct(e.getKey()),new RateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStatisticCategoryRateMap(AbsMap<StatisticCategory, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StatisticCategory, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new RateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWcRateMap(AbsMap<StatisticType, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StatisticType, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new RateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWeatherTypeRateMap(AbsMap<WeatherType, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<WeatherType, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new RateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrListStaList(AbsMap<String, IdList<Statistic>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, IdList<Statistic>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),getSta(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrListStrList(AbsMap<String, CustList<StringList>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, CustList<StringList>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),getStrList(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getShStrList(AbsMap<Short, StringList> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Short, StringList> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new ShortStruct(e.getKey()),getStringArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getTpDuoRate(AbsMap<TypesDuo, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TypesDuo, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new TypesDuoStruct(e.getKey()),new RateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getIntIntMap(AbsMap<Integer, Integer> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Integer, Integer> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new IntStruct(e.getKey()),new IntStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getByteBytes(AbsMap<Byte, Bytes> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Byte, Bytes> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new ByteStruct(e.getKey()),getByteArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrInts(AbsMap<String, Ints> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, Ints> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),getIntArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrTpDam(AbsMap<String, TypeDamageBoost> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, TypeDamageBoost> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey()),new TypeDamageBoostStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getPlLevWildPkDto(AbsMap<PlaceLevel, CustList<WildPokemonDto>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<PlaceLevel, CustList<WildPokemonDto>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new PlaceLevelStruct(e.getKey()),getWildPkDto(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWildPkDto(CustList<WildPokemonDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (WildPokemonDto s:_ls) {
            arr_.set(j_,new WildPokemonDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getSteDto(CustList<StepDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (StepDto s:_ls) {
            arr_.set(j_,new StepDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkPlDto(CustList<PokemonPlayerDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonPlayerDto s:_ls) {
            arr_.set(j_,new PokemonPlayerDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkTrDto(CustList<PokemonTrainerDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonTrainerDto s:_ls) {
            arr_.set(j_,new PokemonTrainerDtoStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getLvMv(CustList<LevelMove> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (LevelMove s:_ls) {
            arr_.set(j_,new LevelMoveStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPlTr(CustList<PlaceTrainerDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PlaceTrainerDto s:_ls) {
            arr_.set(j_,new PlaceTrainerDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getTypesDuo(CustList<TypesDuo> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (TypesDuo s:_ls) {
            arr_.set(j_,new TypesDuoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkTeam(CustList<PokemonTeam> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonTeam s:_ls) {
            arr_.set(j_,new PokemonTeamStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getSiSa(CustList<StatisticStatus> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (StatisticStatus s:_ls) {
            arr_.set(j_,new StatisticStatusStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPlInd(CustList<PlaceIndex> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PlaceIndex s:_ls) {
            arr_.set(j_,new PlaceIndexStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWildPkArray(CustList<WildPk> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (WildPk s:_ls) {
            arr_.set(j_,new PkStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkPlayerArray(CustList<PokemonPlayer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonPlayer s:_ls) {
            arr_.set(j_,new PkStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkTrainerArray(CustList<PkTrainer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PkTrainer s:_ls) {
            arr_.set(j_,new PkStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkLine(CustList<PokemonLine> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonLine s:_ls) {
            arr_.set(j_,new PkLineStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getItLine(CustList<ItemLine> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (ItemLine s:_ls) {
            arr_.set(j_,new ItLineStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getMvLine(CustList<MoveLine> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getRdMvLine(CustList<RadioLineMove> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getSelectLineMove(CustList<SelectLineMove> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static Struct newMoveLine(MoveLine _s) {
//        if (_s instanceof SelectLineMove) {
//            return new MvLineStruct(_s);
//        }
//        if (_s instanceof RadioLineMove) {
//            return new MvLineStruct(_s);
//        }
        return new MvLineStruct(_s);
    }
    public static NatArrayStruct getEffPartStat(CustList<EffectPartnerStatus> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (EffectPartnerStatus s:_ls) {
            arr_.set(j_,new EffectPartnerStatusStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getEvLine(AbsMap<Statistic, EvLine> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Statistic, EvLine> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new EvLineStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStr(AbsMap<String, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrRate(AbsMap<String, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new RateStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrLgInt(AbsMap<String, LgInt> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,LgInt> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new LgIntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrShort(AbsMap<String, Short> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrByte(AbsMap<String, Byte> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,Byte> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ByteStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStaByte(AbsMap<Statistic, Byte> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Statistic,Byte> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new ByteStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStaShort(AbsMap<Statistic, Short> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Statistic,Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStaStr(AbsMap<Statistic, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Statistic,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStaRate(AbsMap<Statistic, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Statistic,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NullStruct.NULL_VALUE,new RateStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getIntStr(AbsMap<Integer, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Integer,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new IntStruct(e.getKey()),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLgIntRate(AbsMap<LgInt, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<LgInt,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new LgIntStruct(e.getKey()),new RateStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLongRate(AbsMap<Long, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Long,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new LongStruct(e.getKey()),new RateStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getRateRate(AbsMap<Rate, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Rate,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new RateStruct(e.getKey()),new RateStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrBool(AbsMap<String, BoolVal> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,BoolVal> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),BooleanStruct.of(e.getValue() == BoolVal.TRUE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getDirBool(AbsMap<Direction, BoolVal> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Direction,BoolVal> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(e.getKey().getDirName()),BooleanStruct.of(e.getValue() == BoolVal.TRUE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getShortStr(AbsMap<Short, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Short, String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new ShortStruct(e.getKey()),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getShortInt(AbsMap<Short, Integer> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Short, Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new ShortStruct(e.getKey()),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStrList(AbsMap<String, StringList> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,StringList> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),getStringArray(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLayers(CustList<Level> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }
    public static NatArrayStruct getEnd(CustList<EndRoundMainElements> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }
    public static NatArrayStruct getSta(CustList<Statistic> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }

    public static NatArrayStruct arrId(int _len) {
        NatArrayStruct arr_ = new NatArrayStruct(_len);
        for (int i = 0; i < _len; i++) {
            arr_.set(i,NullStruct.NULL_VALUE);
        }
        return arr_;
    }
    public static NatArrayStruct getStrList(CustList<StringList> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (StringList e: _map){
            arr_.set(i_,getStringArray(e));
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getIntArray(Ints _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (Integer s:_ls) {
            arr_.set(j_,new IntStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getByteArray(Bytes _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (Byte s:_ls) {
            arr_.set(j_,new ByteStruct(s));
            j_++;
        }
        return arr_;
    }

    public static SelectedBoolean getBoolByName(String _env) {
        return SelectedBoolean.getBoolByName(_env);
    }
    public static DifficultyModelLaw getModelByName(String _env) {
        return DifficultyModelLaw.getModelByName(_env);
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        return DifficultyWinPointsFight.getDiffWonPtsByName(_env);
    }
    public static EnvironmentType getEnvByName(String _env) {
        return EnvironmentType.getEnvByName(_env);
    }
    public static Gender getGenderByName(String _env) {
        return Gender.getGenderByName(_env);
    }

    public void setDataBase(FacadeGame _dataBase){
        dataBase = _dataBase;
    }

    @Override
    protected AbstractNatBlockBuilder blockBuilder() {
        return new AdvNatBlockBuilder(this);
    }
}
