package aiki.beans.facade.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansFacadeDtoStd{
    public static final String TYPE_ITEM_LINE = "aiki.beans.facade.dto.ItemLine";
    public static final String TYPE_ITEM_TYPE_LINE = "aiki.beans.facade.dto.ItemTypeLine";
    public static final String TYPE_KEPT_MOVES_AFTER_FIGHT = "aiki.beans.facade.dto.KeptMovesAfterFight";
    public static final String TYPE_MOVE_LINE = "aiki.beans.facade.dto.MoveLine";
    public static final String TYPE_POKEMON_LINE = "aiki.beans.facade.dto.PokemonLine";
    public static final String TYPE_WEATHER_TYPE_LINE = "aiki.beans.facade.dto.WeatherTypeLine";
    private static final String IS_DAMAGE_MOVE = "isDamageMove";
    private static final String IS_DIRECT = "isDirect";
    private static final String GET_TYPES = "getTypes";
    private static final String DISPLAY_NAME = "displayName";
    private static final String PRICE = "price";
    private static final String DESCRIPTION_CLASS = "descriptionClass";
    private static final String TYPES = "types";
    private static final String EVOLUTIONS = "evolutions";
    private static final String PP = "pp";
    private static final String CATEGORY = "category";
    private static final String PRIORITY = "priority";
    private AikiBeansFacadeDtoStd(){}
    public static void build(PokemonStandards _std) {
        buildItemLine(_std);
        buildItemTypeLine(_std);
        buildKeptMovesAfterFight(_std);
        buildMoveLine(_std);
        buildPokemonLine(_std);
        buildWeatherTypeLine(_std);
    }
    private static void buildItemLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_ITEM_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new ItemLineDisplayNameGet(),null));
        fields_.add(new StandardField(PRICE, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new ItemLinePriceGet(),null));
        fields_.add(new StandardField(DESCRIPTION_CLASS,BeanNatCommonLgNames.STRING,false,false,new ItemLineDescriptionClassGet(),null));
        _std.getStds().addEntry(TYPE_ITEM_LINE, type_);
    }
    private static void buildItemTypeLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_ITEM_TYPE_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_ITEM_TYPE_LINE, type_);
    }
    private static void buildKeptMovesAfterFight(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_KEPT_MOVES_AFTER_FIGHT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_KEPT_MOVES_AFTER_FIGHT, type_);
    }
    private static void buildMoveLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MOVE_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new MoveLineDisplayNameGet(),null));
        fields_.add(new StandardField(PP, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLinePpGet(),null));
        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING,false,false,new MoveLineCategoryGet(),null));
        fields_.add(new StandardField(PRIORITY, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLinePriorityGet(),null));
        methods_.add( new SpecNatMethod(IS_DAMAGE_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveLineIsDamageMove()));
        methods_.add( new SpecNatMethod(IS_DIRECT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MoveLineIsDirect()));
        methods_.add( new SpecNatMethod(GET_TYPES, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new MoveLineGetTypes()));
        _std.getStds().addEntry(TYPE_MOVE_LINE, type_);
    }
    private static void buildPokemonLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POKEMON_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new PokemonLineDisplayNameGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new PokemonLineTypesGet(),null));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonLineEvolutionsGet(),null));
        _std.getStds().addEntry(TYPE_POKEMON_LINE, type_);
    }
    private static void buildWeatherTypeLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_WEATHER_TYPE_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_WEATHER_TYPE_LINE, type_);
    }
}
