package aiki.beans;

import aiki.beans.abilities.AikiBeansAbilitiesStd;
import aiki.beans.effects.AikiBeansEffectsStd;
import aiki.beans.endround.AikiBeansEndroundStd;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import aiki.beans.facade.map.dto.AikiBeansFacadeMapDtoStd;
import aiki.beans.facade.simulation.dto.AikiBeansFacadeSimulationDtoStd;
import aiki.beans.facade.solution.dto.AikiBeansFacadeSolutionDtoStd;
import aiki.beans.help.AikiBeansHelpStd;
import aiki.beans.items.AikiBeansItemsStd;
import aiki.beans.items.effects.AikiBeansItemsEffectsStd;
import aiki.beans.map.AikiBeansMapStd;
import aiki.beans.map.characters.AikiBeansMapCharactersStd;
import aiki.beans.map.elements.AikiBeansMapElementsStd;
import aiki.beans.map.pokemon.AikiBeansMapPokemonStd;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import aiki.beans.pokemon.AikiBeansPokemonStd;
import aiki.beans.pokemon.evolutions.AikiBeansPokemonEvolutionsStd;
import aiki.beans.simulation.AikiBeansSimulationStd;
import aiki.beans.solution.AikiBeansSolutionStd;
import aiki.beans.status.AikiBeansStatusStd;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;

public final class PkData extends PokemonStandards {
    private static final String GET_MOVE = "getMove";
    private static final String GET_BOOST = "getBoost";
    private static final String GET_EFF = "getEff";
    private static final String GET_HP_RATE = "getHpRate";
    private static final String GET_LEVEL = "getLevel";
    private static final String GET_ITEM = "getItem";
    private static final String GET_MOVES = "getMoves";
    private static final String GET_AVG_NB_STEPS = "getAvgNbSteps";
    private static final String GET_WILD_POKEMON = "getWildPokemon";
    private static final String GET_WILD_POKEMON_FISHING = "getWildPokemonFishing";
    private static final String GET_NAME = "getName";
    private static final String GET_DAMAGE_TYPE = "getDamageType";
    private static final String GET_POKEMON_TYPE = "getPokemonType";
    private static final String GET_CATEGORY = "getCategory";
    private static final String GET_MULT = "getMult";
    private static final String GET_WON_EXP_SINCE_LAST_LEVEL = "getWonExpSinceLastLevel";
    private static final String GET_HAPPINESS = "getHappiness";
    private static final String GET_RESTORED_HP_RATE_LOVED_ALLY = "getRestoredHpRateLovedAlly";
    private static final String GET_WEDDING_ALLY = "getWeddingAlly";
    private static final String GET_MULT_DAMAGE_AGAINST_FOE = "getMultDamageAgainstFoe";
    @Override
    public void buildAddon() {
        AikiBeansAbilitiesStd.build(this);
        AikiBeansStd.build(this);
        AikiBeansEffectsStd.build(this);
        AikiBeansEndroundStd.build(this);
        AikiBeansFacadeDtoStd.build(this);
        AikiBeansFacadeMapDtoStd.build(this);
        AikiBeansFacadeSimulationDtoStd.build(this);
        AikiBeansFacadeSolutionDtoStd.build(this);
        AikiBeansHelpStd.build(this);
        AikiBeansItemsStd.build(this);
        AikiBeansItemsEffectsStd.build(this);
        AikiBeansMapCharactersStd.build(this);
        AikiBeansMapElementsStd.build(this);
        AikiBeansMapStd.build(this);
        AikiBeansMapPokemonStd.build(this);
        AikiBeansMovesEffectsStd.build(this);
        AikiBeansMovesStd.build(this);
        AikiBeansPokemonEvolutionsStd.build(this);
        AikiBeansPokemonStd.build(this);
        AikiBeansSimulationStd.build(this);
        AikiBeansSolutionStd.build(this);
        AikiBeansStatusStd.build(this);
        buildTypeDamageBoost(this);
        buildEfficiencyRate(this);
        buildBoostHpRate(this);
        buildPkTrainer(this);
        buildAreaApparition(this);
        buildWildPk(this);
        buildPlace(this);
        buildTypesDuo(this);
        buildCategoryMult(this);
        buildLevelMove(this);
        buildPokemonPlayer(this);
        buildEffectPartnerStatus(this);
    }

    private static void buildTypeDamageBoost(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TYPE_DAMAGE_BOOST, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_BOOST,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new TypeDamageBoostGetBoost()));
        _std.getStds().addEntry(TYPE_TYPE_DAMAGE_BOOST, type_);
    }
    private static void buildEfficiencyRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFICIENCY_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_EFF,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EfficiencyRateGetEff()));
        methods_.add( new SpecNatMethod(GET_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EfficiencyRateGetHpRate()));
        _std.getStds().addEntry(TYPE_EFFICIENCY_RATE, type_);
    }
    private static void buildBoostHpRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_BOOST_HP_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new BoostHpRateGetHpRate()));
        methods_.add( new SpecNatMethod(GET_BOOST, PRIM_INTEGER, false, MethodModifier.NORMAL,new BoostHpRateGetBoost()));
        _std.getStds().addEntry(TYPE_BOOST_HP_RATE, type_);
    }
    private static void buildPkTrainer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_PK_TRAINER, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, false, MethodModifier.NORMAL,new PkTrainerGetLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PkTrainerGetItem()));
        methods_.add( new SpecNatMethod(GET_MOVES, TYPE_LIST, false, MethodModifier.NORMAL,new PkTrainerGetMoves()));
        _std.getStds().addEntry(TYPE_PK_TRAINER, type_);
    }

    private static void buildAreaApparition(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_AREA_APPARITION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_AVG_NB_STEPS, PRIM_INTEGER, false, MethodModifier.NORMAL,new AreaApparitionGetAvgNbSteps()));
        methods_.add( new SpecNatMethod(GET_WILD_POKEMON, TYPE_LIST, false, MethodModifier.NORMAL,new AreaApparitionGetWildPokemon()));
        methods_.add( new SpecNatMethod(GET_WILD_POKEMON_FISHING, TYPE_LIST, false, MethodModifier.NORMAL,new AreaApparitionGetWildPokemonFishing()));
        _std.getStds().addEntry(TYPE_AREA_APPARITION, type_);
    }
    private static void buildWildPk(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_WILD_PK, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, false, MethodModifier.NORMAL,new WildPkGetLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WildPkGetItem()));
        _std.getStds().addEntry(TYPE_WILD_PK, type_);
    }
    private static void buildPlace(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_PLACE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PlaceGetName()));
        _std.getStds().addEntry(TYPE_PLACE, type_);
    }
    private static void buildTypesDuo(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TYPES_DUO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_DAMAGE_TYPE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TypesDuoGetDamageType()));
        methods_.add( new SpecNatMethod(GET_POKEMON_TYPE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TypesDuoGetPokemonType()));
        _std.getStds().addEntry(TYPE_TYPES_DUO, type_);
    }
    private static void buildCategoryMult(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_CATEGORY_MULT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_CATEGORY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CategoryMultGetCategory()));
        methods_.add( new SpecNatMethod(GET_MULT, PRIM_INTEGER, false, MethodModifier.NORMAL,new CategoryMultGetMult()));
        _std.getStds().addEntry(TYPE_CATEGORY_MULT, type_);
    }
    private static void buildLevelMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_LEVEL_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, false, MethodModifier.NORMAL,new LevelMoveGetLevel()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LevelMoveGetMove()));
        _std.getStds().addEntry(TYPE_LEVEL_MOVE, type_);
    }
    private static void buildPokemonPlayer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POKEMON_PLAYER, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_WON_EXP_SINCE_LAST_LEVEL,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new PokemonPlayerGetWonExpSinceLastLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonPlayerGetItem()));
        methods_.add( new SpecNatMethod(GET_HAPPINESS, PRIM_INTEGER, false, MethodModifier.NORMAL,new PokemonPlayerGetHappiness()));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER, type_);
    }
    private static void buildEffectPartnerStatus(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_PARTNER_STATUS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_RESTORED_HP_RATE_LOVED_ALLY,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EffectPartnerStatusGetRestoredHpRateLovedAlly()));
        methods_.add( new SpecNatMethod(GET_WEDDING_ALLY,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new EffectPartnerStatusGetWeddingAlly()));
        methods_.add( new SpecNatMethod(GET_MULT_DAMAGE_AGAINST_FOE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EffectPartnerStatusGetMultDamageAgainstFoe()));
        _std.getStds().addEntry(TYPE_EFFECT_PARTNER_STATUS, type_);
    }
}
