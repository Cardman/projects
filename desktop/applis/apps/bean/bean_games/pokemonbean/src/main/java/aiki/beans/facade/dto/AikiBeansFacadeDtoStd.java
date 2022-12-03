package aiki.beans.facade.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
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
    private static final String ACCURACY = "accuracy";
    private static final String POWER = "power";
    private AikiBeansFacadeDtoStd(){}
    public static void build(PokemonStandards _std) {
        buildItemLine(_std);
        buildMoveLine(_std);
        buildPokemonLine(_std);
    }
    private static void buildItemLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING, new ItemLineDisplayNameGet(),null));
        fields_.add(new StandardField(PRICE, BeanNatCommonLgNames.PRIM_INTEGER, new ItemLinePriceGet(),null));
        fields_.add(new StandardField(DESCRIPTION_CLASS,BeanNatCommonLgNames.STRING, new ItemLineDescriptionClassGet(),null));
        _std.getStds().addEntry(TYPE_ITEM_LINE, type_);
    }

    private static void buildMoveLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING, new MoveLineDisplayNameGet(),null));
        fields_.add(new StandardField(PP, BeanNatCommonLgNames.PRIM_INTEGER, new MoveLinePpGet(),null));
        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING, new MoveLineCategoryGet(),null));
        fields_.add(new StandardField(PRIORITY, BeanNatCommonLgNames.PRIM_INTEGER, new MoveLinePriorityGet(),null));
//        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLineBeanIndexGet(),new MoveLineBeanIndexSet()));
//        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new MoveLineBeanDisplayNameGet(),null));
//        fields_.add(new StandardField(PP, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLineBeanPpGet(),null));
//        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MoveLineBeanTypesGet(),null));
//        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING,false,false,new MoveLineBeanCategoryGet(),null));
//        fields_.add(new StandardField(MOVE_LINE,AikiBeansFacadeDtoStd.TYPE_MOVE_LINE,false,false,new MoveLineBeanMoveLineGet(),new MoveLineBeanMoveLineSet()));
//        fields_.add(new StandardField(PRIORITY, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MoveLineBeanPriorityGet(),null));
        fields_.add(new StandardField(ACCURACY,BeanNatCommonLgNames.STRING, new MoveLineAccuracyGet(),null));
        fields_.add(new StandardField(POWER,BeanNatCommonLgNames.STRING, new MoveLinePowerGet(),null));
//        fields_.add(new StandardField(SORTED_MOVES, BeanNatCommonLgNames.TYPE_LIST,false,false,null,new MoveLineBeanSortedMovesSet()));
        methods_.add( new SpecNatMethod(IS_DAMAGE_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, new MoveLineIsDamageMove()));
        methods_.add( new SpecNatMethod(IS_DIRECT,BeanNatCommonLgNames.PRIM_BOOLEAN, new MoveLineIsDirect()));
        methods_.add( new SpecNatMethod(GET_TYPES, BeanNatCommonLgNames.TYPE_LIST, new MoveLineGetTypes()));
        _std.getStds().addEntry(TYPE_MOVE_LINE, type_);
    }
    private static void buildPokemonLine(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING, new PokemonLineDisplayNameGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST, new PokemonLineTypesGet(),null));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonLineEvolutionsGet(),null));
        _std.getStds().addEntry(TYPE_POKEMON_LINE, type_);
    }
}
