package aiki.beans;

import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import aiki.beans.facade.dto.ItemLine;
import aiki.beans.facade.dto.MoveLine;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.facade.fight.*;
import aiki.beans.facade.game.dto.AikiBeansFacadeGameDtoStd;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import aiki.beans.facade.map.dto.AikiBeansFacadeMapDtoStd;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.solution.dto.AikiBeansFacadeSolutionDtoStd;
import aiki.beans.facade.solution.dto.PlaceTrainerDto;
import aiki.beans.facade.solution.dto.StepDto;
import aiki.beans.facade.solution.dto.WildPokemonDto;
import aiki.beans.validators.PositiveRateValidator;
import aiki.beans.validators.RateValidator;
import aiki.beans.validators.ShortValidator;
import aiki.beans.validators.UnselectedRadio;
import aiki.comparators.DictionaryComparator;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.*;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.instances.Instances;
import aiki.map.levels.Level;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceLevel;
import aiki.util.Point;
import aiki.util.TeamPositionList;
import code.bean.Bean;
import code.bean.nat.*;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.fwd.AdvNatBlockBuilder;
import code.expressionlanguage.common.StringExpUtil;
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
    public static final String TYPE_TRAINER_ONE_FIGHT = "aiki.map.characters.TrainerOneFight";
    public static final String TYPE_TRAINER = "aiki.map.characters.Trainer";
    public static final String TYPE_PERSON = "aiki.map.characters.Person";
    public static final String TYPE_FULL_RATE_VALIDATOR = "aiki.beans.validators.RateValidator";
    public static final String TYPE_FULL_POSITIVE_RATE_VALIDATOR = "aiki.beans.validators.PositiveRateValidator";
    public static final String TYPE_FULL_SHORT_VALIDATOR = "aiki.beans.validators.ShortValidator";
    public static final String TYPE_FULL_UNSELECTED_RADIO = "aiki.beans.validators.UnselectedRadio";
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
        stringMapObject_.putAllMapBase(_bean);
        String currentBeanName_;
        NatDocumentBlock rendDocumentBlock_ = getRender(_dest,_curUrl);
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(currentBeanName_);
        if (bean_ instanceof PokemonBeanStruct&& ((PokemonBeanStruct) bean_).getBean() instanceof WithForms) {
            ((WithForms) ((PokemonBeanStruct) bean_).getBean()).setForms(stringMapObject_);
        }
        _rendStack.clearPages();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack);
        return new InvokedPageOutput(getDest(_dest,_curUrl),res_);
    }

    @Override
    public void setBeanForms(Struct _mainBean, String _beanName) {
        beanForms(_mainBean, _beanName);
    }

    private void beanForms(Struct _mainBean,
                           String _beanName) {
        if (_mainBean == null) {
            return;
        }
        Struct bean_ = getBeansStruct().getVal(_beanName);
        if (bean_ == null) {
            return;
        }
        gearFw(_mainBean, bean_);
    }

    void gearFw(Struct _mainBean, Struct _bean) {
        StringMapObject forms_ = ((WithForms) ((PokemonBeanStruct)_bean).getBean()).getForms();
        StringMapObject formsMap_ = ((WithForms) ((PokemonBeanStruct)_mainBean).getBean()).getForms();
        forms_.putAllMap(formsMap_);
    }

    public static PkTrainer toPkTrainer(Struct _inst) {
        if (!(_inst instanceof PkStruct)) {
            return Instances.newPkTrainer();
        }
        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
        if (instance_ instanceof PkTrainer) {
            return (PkTrainer) instance_;
        }
        return Instances.newPkTrainer();
    }

    public static WildPk toWildPk(Struct _inst) {
        if (!(_inst instanceof PkStruct)) {
            return Instances.newWildPk();
        }
        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
        if (instance_ instanceof WildPk) {
            return (WildPk) instance_;
        }
        return Instances.newWildPk();
    }

    public static PokemonPlayer toPokemonPlayer(Struct _inst) {
        if (!(_inst instanceof PkStruct)) {
            return Instances.newPokemonPlayer();
        }
        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
        if (instance_ instanceof PokemonPlayer) {
            return (PokemonPlayer) instance_;
        }
        return Instances.newPokemonPlayer();
    }

    protected PokemonBeanStruct bean(Bean _bean, String _name, String _lg) {
        _bean.setClassName(_name);
        PokemonBeanStruct res_ = new PokemonBeanStruct(_bean);
        Bean bean_ = res_.getBean();
        res_.setDataBase(dataBase);
        if (bean_ instanceof WithForms) {
            ((WithForms)bean_).setForms(new StringMapObject());
        }
        bean_.setLanguage(_lg);
        return res_;
    }

    public static ArrayStruct getBigByAn(AbsMap<String, ByteTreeMap<Anticipation>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, ByteTreeMap<Anticipation>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getByAn(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBigBySt(AbsMap<String, ByteTreeMap<StacksOfUses>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, ByteTreeMap<StacksOfUses>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getBySt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getByAn(AbsMap<Byte, Anticipation> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, Anticipation> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),new AnticipationStruct(e.getValue(),TYPE_ANTICIPATION));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBySt(AbsMap<Byte, StacksOfUses> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, StacksOfUses> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),new StacksOfUsesStruct(e.getValue(),TYPE_STACKS_OF_USES));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBigNatMapLs(AbsMap<String, TeamPositionList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, TeamPositionList> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getTeamPos(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getBigNatMap(AbsMap<String, AbsBasicTreeMap<Rate, Rate>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, AbsBasicTreeMap<Rate, Rate>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getRateRate(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBigNatMapSta(AbsMap<String, DictionaryComparator<Statistic, Byte>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, DictionaryComparator<Statistic, Byte>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getStaByte(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrAct(AbsMap<StringList, ActivityOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StringList, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,getStringArray(e.getKey()),new ActivityOfMoveStruct(e.getValue(),TYPE_ACTIVITY_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaBoost(AbsMap<Statistic, BoostHpRate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Statistic, BoostHpRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(""),new BoostHpRateStruct(e.getValue(),TYPE_BOOST_HP_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvTp(AbsMap<MoveTeamPosition, AffectedMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, AffectedMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new AffectedMoveStruct(e.getValue(),TYPE_AFFECTED_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvAm(AbsMap<MoveTeamPosition, ActivityOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new ActivityOfMoveStruct(e.getValue(),TYPE_ACTIVITY_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvTpNb(AbsMap<MoveTeamPosition, Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, Short> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new ShortStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvTpBool(AbsMap<MoveTeamPosition, BoolVal> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, BoolVal> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),BooleanStruct.of(e.getValue() == BoolVal.TRUE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcStr(AbsMap<MiniMapCoords, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MiniMapCoords, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(""),new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPtStr(AbsMap<Point, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Point, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(""),new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvTpStr(AbsMap<MoveTeamPosition, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getCpStr(AbsMap<String, CopiedMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, CopiedMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new CopiedMoveStruct(e.getValue(),TYPE_COPIED_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMultPowStr(AbsMap<String, MultPowerMoves> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, MultPowerMoves> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new MultPowerMovesStruct(e.getValue(),AikiBeansFacadeFightStd.TYPE_MULT_POWER_MOVES));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSuffCatStr(AbsMap<String, SufferedDamageCategory> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, SufferedDamageCategory> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new SufferedDamageCategoryStruct(e.getValue(),AikiBeansFacadeFightStd.TYPE_SUFFERED_DAMAGE_CATEGORY));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getEffRateStr(AbsMap<String, EfficiencyRate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, EfficiencyRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new EfficiencyRateStruct(e.getValue(),TYPE_EFFICIENCY_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getUsesStr(AbsMap<String, UsesOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, UsesOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new UsesOfMoveStruct(e.getValue(),TYPE_USES_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcByteMap(AbsMap<StatisticPokemon, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticPokemon, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticTypeByteMap(AbsMap<StatisticType, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticType, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticCategoryByteMap(AbsMap<StatisticCategory, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticCategory, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticStatusByteMap(AbsMap<StatisticStatus, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticStatus, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StatisticStatusStruct(e.getKey(),OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getCatMultRateMap(AbsMap<CategoryMult, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<CategoryMult, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new CategoryMultStruct(e.getKey(),TYPE_CATEGORY_MULT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticCategoryRateMap(AbsMap<StatisticCategory, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticCategory, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcRateMap(AbsMap<StatisticType, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticType, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWeatherTypeRateMap(AbsMap<WeatherType, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<WeatherType, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrListStaList(AbsMap<String, IdList<Statistic>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, IdList<Statistic>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getSta(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrListStrList(AbsMap<String, CustList<StringList>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, CustList<StringList>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getStrList(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getShStrList(AbsMap<Short, StringList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Short, StringList> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ShortStruct(e.getKey()),getStringArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getTpDuoRate(AbsMap<TypesDuo, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<TypesDuo, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new TypesDuoStruct(e.getKey(),TYPE_TYPES_DUO),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getIntIntMap(AbsMap<Integer, Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Integer, Integer> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IntStruct(e.getKey()),new IntStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getByteBytes(AbsMap<Byte, Bytes> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, Bytes> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),getByteArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrInts(AbsMap<String, Ints> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, Ints> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getIntArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvTars(AbsMap<MoveTarget, MoveTarget> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTarget, MoveTarget> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTargetStruct(e.getKey(),TYPE_MOVE_TARGET),new MoveTargetStruct(e.getValue(),TYPE_MOVE_TARGET));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvTar(AbsMap<Byte, MoveTarget> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, MoveTarget> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),new MoveTargetStruct(e.getValue(),TYPE_MOVE_TARGET));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrTpDam(AbsMap<String, TypeDamageBoost> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, TypeDamageBoost> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new TypeDamageBoostStruct(e.getValue(),TYPE_TYPE_DAMAGE_BOOST));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getActMove(AbsMap<String, ActivityOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new ActivityOfMoveStruct(e.getValue(),PokemonStandards.TYPE_ACTIVITY_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPlLevWildPkDto(AbsMap<PlaceLevel, CustList<WildPokemonDto>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<PlaceLevel, CustList<WildPokemonDto>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new PlaceLevelStruct(e.getKey(),OBJECT),getWildPkDto(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWildPkDto(CustList<WildPokemonDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSolutionDtoStd.TYPE_WILD_POKEMON_DTO));
        int j_ = 0;
        for (WildPokemonDto s:_ls) {
            arr_.set(j_,new WildPokemonDtoStruct(s, AikiBeansFacadeSolutionDtoStd.TYPE_WILD_POKEMON_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSteDto(CustList<StepDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSolutionDtoStd.TYPE_STEP_DTO));
        int j_ = 0;
        for (StepDto s:_ls) {
            arr_.set(j_,new StepDtoStruct(s, AikiBeansFacadeSolutionDtoStd.TYPE_STEP_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkPlDto(CustList<PokemonPlayerDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_PLAYER_DTO));
        int j_ = 0;
        for (PokemonPlayerDto s:_ls) {
            arr_.set(j_,new PokemonPlayerDtoStruct(s, AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_PLAYER_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkTrDto(CustList<PokemonTrainerDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_TRAINER_DTO));
        int j_ = 0;
        for (PokemonTrainerDto s:_ls) {
            arr_.set(j_,new PokemonTrainerDtoStruct(s, AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_TRAINER_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaInf(CustList<StatisticInfo> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeFightStd.TYPE_STATISTIC_INFO));
        int j_ = 0;
        for (StatisticInfo s:_ls) {
            arr_.set(j_,new StatisticInfoStruct(s, AikiBeansFacadeFightStd.TYPE_STATISTIC_INFO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getKeyHyp(CustList<KeyHypothesis> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeFightStd.TYPE_KEY_HYPOTHESIS));
        int j_ = 0;
        for (KeyHypothesis s:_ls) {
            arr_.set(j_,new KeyHypothesisStruct(s, AikiBeansFacadeFightStd.TYPE_KEY_HYPOTHESIS));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStPkPl(CustList<StatisticInfoPkPlayer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeGameDtoStd.TYPE_STATISTIC_INFO_PK_PLAYER));
        int j_ = 0;
        for (StatisticInfoPkPlayer s:_ls) {
            arr_.set(j_,new StatisticInfoPkPlayerStruct(s, AikiBeansFacadeGameDtoStd.TYPE_STATISTIC_INFO_PK_PLAYER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getTrPlNa(CustList<TrainerPlaceNames> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_TRAINER_PLACE_NAMES));
        int j_ = 0;
        for (TrainerPlaceNames s:_ls) {
            arr_.set(j_,new TrainerPlaceNamesStruct(s, TYPE_TRAINER_PLACE_NAMES));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getLvMv(CustList<LevelMove> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LEVEL_MOVE));
        int j_ = 0;
        for (LevelMove s:_ls) {
            arr_.set(j_,new LevelMoveStruct(s, TYPE_LEVEL_MOVE));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPlTr(CustList<PlaceTrainerDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSolutionDtoStd.TYPE_PLACE_TRAINER_DTO));
        int j_ = 0;
        for (PlaceTrainerDto s:_ls) {
            arr_.set(j_,new PlaceTrainerDtoStruct(s, AikiBeansFacadeSolutionDtoStd.TYPE_PLACE_TRAINER_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getTypesDuo(CustList<TypesDuo> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_TYPES_DUO));
        int j_ = 0;
        for (TypesDuo s:_ls) {
            arr_.set(j_,new TypesDuoStruct(s, TYPE_TYPES_DUO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkTeam(CustList<PokemonTeam> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (PokemonTeam s:_ls) {
            arr_.set(j_,new PokemonTeamStruct(s, OBJECT));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSiSa(CustList<StatisticStatus> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (StatisticStatus s:_ls) {
            arr_.set(j_,new StatisticStatusStruct(s, OBJECT));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPlInd(CustList<PlaceIndex> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeMapDtoStd.TYPE_PLACE_INDEX));
        int j_ = 0;
        for (PlaceIndex s:_ls) {
            arr_.set(j_,new PlaceIndexStruct(s, AikiBeansFacadeMapDtoStd.TYPE_PLACE_INDEX));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWildPkArray(CustList<WildPk> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_WILD_PK));
        int j_ = 0;
        for (WildPk s:_ls) {
            arr_.set(j_,new PkStruct(s, TYPE_WILD_PK));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkPlayerArray(CustList<PokemonPlayer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_POKEMON_PLAYER));
        int j_ = 0;
        for (PokemonPlayer s:_ls) {
            arr_.set(j_,new PkStruct(s, TYPE_POKEMON_PLAYER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkTrainerArray(CustList<PkTrainer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_PK_TRAINER));
        int j_ = 0;
        for (PkTrainer s:_ls) {
            arr_.set(j_,new PkStruct(s, TYPE_PK_TRAINER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkLine(CustList<PokemonLine> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeDtoStd.TYPE_POKEMON_LINE));
        int j_ = 0;
        for (PokemonLine s:_ls) {
            arr_.set(j_,new PkLineStruct(s, AikiBeansFacadeDtoStd.TYPE_POKEMON_LINE));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getItLine(CustList<ItemLine> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeDtoStd.TYPE_ITEM_LINE));
        int j_ = 0;
        for (ItemLine s:_ls) {
            arr_.set(j_,new ItLineStruct(s, AikiBeansFacadeDtoStd.TYPE_ITEM_LINE));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvLine(CustList<MoveLine> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeDtoStd.TYPE_MOVE_LINE));
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getRdMvLine(CustList<RadioLineMove> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_RADIO_LINE_MOVE));
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSelectLineMove(CustList<SelectLineMove> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_SELECT_LINE_MOVE));
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static Struct newMoveLine(MoveLine _s) {
        if (_s instanceof SelectLineMove) {
            return new MvLineStruct(_s, AikiBeansFacadeSimulationDtoStd.TYPE_SELECT_LINE_MOVE);
        }
        if (_s instanceof RadioLineMove) {
            return new MvLineStruct(_s, AikiBeansFacadeSimulationDtoStd.TYPE_RADIO_LINE_MOVE);
        }
        return new MvLineStruct(_s, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE);
    }
    public static ArrayStruct getEffPartStat(CustList<EffectPartnerStatus> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_EFFECT_PARTNER_STATUS));
        int j_ = 0;
        for (EffectPartnerStatus s:_ls) {
            arr_.set(j_,new EffectPartnerStatusStruct(s, TYPE_EFFECT_PARTNER_STATUS));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getEvLine(AbsMap<Statistic, EvLine> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_EV_LINE));
        int j_ = 0;
        for (EntryCust<Statistic, EvLine> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new EvLineStruct(e.getValue(),AikiBeansFacadeSimulationDtoStd.TYPE_EV_LINE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrStr(AbsMap<String, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrRate(AbsMap<String, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrLgInt(AbsMap<String, LgInt> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,LgInt> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new LgIntStruct(e.getValue(),TYPE_LG_INT));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrShort(AbsMap<String, Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrByte(AbsMap<String, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Byte> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ByteStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaByte(AbsMap<Statistic, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,Byte> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaShort(AbsMap<Statistic, Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaStr(AbsMap<Statistic, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaRate(AbsMap<Statistic, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getIntStr(AbsMap<Integer, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Integer,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IntStruct(e.getKey()),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getLgIntRate(AbsMap<LgInt, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<LgInt,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new LgIntStruct(e.getKey(),TYPE_LG_INT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getLongRate(AbsMap<Long, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Long,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new LongStruct(e.getKey()),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getRateRate(AbsMap<Rate, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Rate,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new RateStruct(e.getKey(),TYPE_RATE),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrBool(AbsMap<String, BoolVal> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,BoolVal> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),BooleanStruct.of(e.getValue() == BoolVal.TRUE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getShortStr(AbsMap<Short, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Short, String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new ShortStruct(e.getKey()),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getShortInt(AbsMap<Short, Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Short, Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new ShortStruct(e.getKey()),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrStrList(AbsMap<String, StringList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,StringList> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),getStringArray(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getLayers(CustList<Level> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }
    public static ArrayStruct getEnd(CustList<EndRoundMainElements> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }
    public static ArrayStruct getSta(CustList<Statistic> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }
    public static ArrayStruct getTeamPos(CustList<TeamPosition> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }

    private static ArrayStruct arrId(int _len) {
        ArrayStruct arr_ = new ArrayStruct(_len, StringExpUtil.getPrettyArrayType(OBJECT));
        for (int i = 0; i < _len; i++) {
            arr_.set(i,new IdStruct(OBJECT));
        }
        return arr_;
    }
    public static ArrayStruct getStrList(CustList<StringList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (StringList e: _map){
            arr_.set(i_,getStringArray(e));
            i_++;
        }
        return arr_;
    }

    public static ArrayStruct getIntArray(Ints _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(PRIM_INTEGER));
        int j_ = 0;
        for (Integer s:_ls) {
            arr_.set(j_,new IntStruct(s));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getByteArray(Bytes _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(PRIM_BYTE));
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
