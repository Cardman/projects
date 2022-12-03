package aiki.beans.facade.simulation.dto;

import aiki.beans.PokemonStandards;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansFacadeSimulationDtoStd{
    public static final String TYPE_EV_LINE = "aiki.beans.facade.simulation.dto.EvLine";
    public static final String TYPE_POKEMON_PLAYER_DTO = "aiki.beans.facade.simulation.dto.PokemonPlayerDto";
    public static final String TYPE_POKEMON_TRAINER_DTO = "aiki.beans.facade.simulation.dto.PokemonTrainerDto";
    public static final String TYPE_RADIO_LINE_MOVE = "aiki.beans.facade.simulation.dto.RadioLineMove";
    public static final String TYPE_SELECT_LINE_MOVE = "aiki.beans.facade.simulation.dto.SelectLineMove";
    private static final String SELECTED = "selected";
    private static final String EV = "ev";
    private static final String INDEX = "index";
    private AikiBeansFacadeSimulationDtoStd(){}
    public static void build(PokemonStandards _std) {
        buildEvLine(_std);
        buildPokemonPlayerDto(_std);
        buildPokemonTrainerDto(_std);
        buildRadioLineMove(_std);
        buildSelectLineMove(_std);
    }
    private static void buildEvLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(EV, BeanNatCommonLgNames.PRIM_INTEGER, new EvLineEvGet(),new EvLineEvSet()));
        _std.getStds().addEntry(TYPE_EV_LINE, type_);
    }
    private static void buildPokemonPlayerDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonPlayerDtoIndexGet(),null));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER_DTO, type_);
    }
    private static void buildPokemonTrainerDto(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonTrainerDtoIndexGet(),null));
        _std.getStds().addEntry(TYPE_POKEMON_TRAINER_DTO, type_);
    }
    private static void buildRadioLineMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER, new RadioLineMoveIndexGet(),null));
        _std.getStds().addEntry(TYPE_RADIO_LINE_MOVE, type_);
    }
    private static void buildSelectLineMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE);
        fields_.add(new StandardField(SELECTED,BeanNatCommonLgNames.PRIM_BOOLEAN, new SelectLineMoveSelectedGet(),new SelectLineMoveSelectedSet()));
        _std.getStds().addEntry(TYPE_SELECT_LINE_MOVE, type_);
    }
}
