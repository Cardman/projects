package aiki.beans.facade.simulation.dto;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansFacadeSimulationDtoStd {
    public static final String TYPE_EV_LINE = "aiki.beans.facade.simulation.dto.EvLine";
    public static final String TYPE_POKEMON_PLAYER_DTO = "aiki.beans.facade.simulation.dto.PokemonPlayerDto";
    public static final String TYPE_POKEMON_TRAINER_DTO = "aiki.beans.facade.simulation.dto.PokemonTrainerDto";
    public static final String TYPE_RADIO_LINE_MOVE = "aiki.beans.facade.simulation.dto.RadioLineMove";
    public static final String TYPE_SELECT_LINE_MOVE = "aiki.beans.facade.simulation.dto.SelectLineMove";

    private static final String SELECTED = "selected";
    private static final String EV = "ev";
    private static final String INDEX = "index";

    public static void build(BeanLgNames _std) {
        buildEvLine(_std);
        buildPokemonPlayerDto(_std);
        buildPokemonTrainerDto(_std);
        buildRadioLineMove(_std);
        buildSelectLineMove(_std);
    }
    private static void buildEvLine(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EV_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.put(EV,new StandardField(EV,_std.getAliasPrimShort(),false,false,type_));
        _std.getStandards().put(TYPE_EV_LINE, type_);
    }
    private static void buildPokemonPlayerDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_POKEMON_PLAYER_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.put(INDEX,new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().put(TYPE_POKEMON_PLAYER_DTO, type_);
    }
    private static void buildPokemonTrainerDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_POKEMON_TRAINER_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.put(INDEX,new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().put(TYPE_POKEMON_TRAINER_DTO, type_);
    }
    private static void buildRadioLineMove(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_RADIO_LINE_MOVE, fields_, constructors_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE, MethodModifier.NORMAL);
        fields_.put(INDEX,new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().put(TYPE_RADIO_LINE_MOVE, type_);
    }
    private static void buildSelectLineMove(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SELECT_LINE_MOVE, fields_, constructors_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE, MethodModifier.NORMAL);
        fields_.put(SELECTED,new StandardField(SELECTED,_std.getAliasPrimBoolean(),false,false,type_));
        _std.getStandards().put(TYPE_SELECT_LINE_MOVE, type_);
    }
    public static ResultErrorStd getResultEvLine(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvLine instance_ = (EvLine) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,EV)) {
            res_.setResult(new ShortStruct(instance_.getEv()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonPlayerDto(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonPlayerDto instance_ = (PokemonPlayerDto) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonTrainerDto(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonTrainerDto instance_ = (PokemonTrainerDto) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultRadioLineMove(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        RadioLineMove instance_ = (RadioLineMove) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        return AikiBeansFacadeDtoStd.getResultMoveLine(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultSelectLineMove(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        SelectLineMove instance_ = (SelectLineMove) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,SELECTED)) {
            res_.setResult(BooleanStruct.of(instance_.getSelected()));
            return res_;
        }
        return AikiBeansFacadeDtoStd.getResultMoveLine(_cont, _classField, _instance);
    }
    public static ResultErrorStd setResultEvLine(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvLine instance_ = (EvLine) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,EV)) {
            instance_.setEv((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSelectLineMove(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        SelectLineMove instance_ = (SelectLineMove) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,SELECTED)) {
            instance_.setSelected((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
}
