package aiki.beans.facade.solution.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
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
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(TRAINER,BeanNatCommonLgNames.STRING, new PlaceTrainerDtoTrainerGet(),null));
        fields_.add(new StandardField(PLACE,BeanNatCommonLgNames.STRING, new PlaceTrainerDtoPlaceGet(),null));
        _std.getStds().addEntry(TYPE_PLACE_TRAINER_DTO, type_);
    }
    private static void buildStepDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_POKEMON, BeanNatCommonLgNames.TYPE_MAP, new StepDtoGetPokemon()));
        methods_.add( new SpecNatMethod(GET_NAMES, BeanNatCommonLgNames.TYPE_LIST, new StepDtoGetNames()));
        _std.getStds().addEntry(TYPE_STEP_DTO, type_);
    }
    private static void buildWildPokemonDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING, new WildPokemonDtoImageGet(),null));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING, new WildPokemonDtoNameGet(),null));
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING, new WildPokemonDtoGenderGet(),null));
        _std.getStds().addEntry(TYPE_WILD_POKEMON_DTO, type_);
    }
}
