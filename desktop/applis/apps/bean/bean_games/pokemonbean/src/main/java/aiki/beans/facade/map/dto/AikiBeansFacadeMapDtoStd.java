package aiki.beans.facade.map.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansFacadeMapDtoStd{
    public static final String TYPE_PLACE_INDEX = "aiki.beans.facade.map.dto.PlaceIndex";
    private static final String GET_PLACE = "getPlace";
    private static final String INDEX = "index";
    private AikiBeansFacadeMapDtoStd(){}
    public static void build(PokemonStandards _std) {
        buildPlaceIndex(_std);
    }
    private static void buildPlaceIndex(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER, new PlaceIndexIndexGet(),null));
        methods_.add( new SpecNatMethod(GET_PLACE,PokemonStandards.TYPE_PLACE, new PlaceIndexGetPlace()));
        _std.getStds().addEntry(TYPE_PLACE_INDEX, type_);
    }
}
