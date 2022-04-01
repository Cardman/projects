package aiki.beans.facade.solution.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansFacadeSolutionDtoStd{
    public static final String TYPE_PLACE_TRAINER_DTO = "aiki.beans.facade.solution.dto.PlaceTrainerDto";
    public static final String TYPE_STEP_DTO = "aiki.beans.facade.solution.dto.StepDto";
    public static final String TYPE_WILD_POKEMON_DTO = "aiki.beans.facade.solution.dto.WildPokemonDto";
    private static final String GET_POKEMON = "getPokemon";
    private static final String GET_NAMES = "getNames";
    private static final String IMAGE = "image";
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String TRAINER = "trainer";
    private static final String PLACE = "place";
    private AikiBeansFacadeSolutionDtoStd(){}
    public static void build(PokemonStandards _std) {
        buildPlaceTrainerDto(_std);
        buildStepDto(_std);
        buildWildPokemonDto(_std);
    }
    private static void buildPlaceTrainerDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_PLACE_TRAINER_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(TRAINER,BeanNatCommonLgNames.STRING,false,false,new PlaceTrainerDtoTrainerGet(),null));
        fields_.add(new StandardField(PLACE,BeanNatCommonLgNames.STRING,false,false,new PlaceTrainerDtoPlaceGet(),null));
        _std.getStds().addEntry(TYPE_PLACE_TRAINER_DTO, type_);
    }
    private static void buildStepDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_STEP_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_POKEMON, BeanNatLgNames.TYPE_MAP, false, MethodModifier.NORMAL,new StepDtoGetPokemon()));
        methods_.add( new SpecNatMethod(GET_NAMES, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new StepDtoGetNames()));
        _std.getStds().addEntry(TYPE_STEP_DTO, type_);
    }
    private static void buildWildPokemonDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_WILD_POKEMON_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false,new WildPokemonDtoImageGet(),null));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING,false,false,new WildPokemonDtoNameGet(),null));
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING,false,false,new WildPokemonDtoGenderGet(),null));
        _std.getStds().addEntry(TYPE_WILD_POKEMON_DTO, type_);
    }
}
