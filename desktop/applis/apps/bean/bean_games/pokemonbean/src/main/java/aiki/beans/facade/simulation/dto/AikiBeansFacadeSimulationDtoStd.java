package aiki.beans.facade.simulation.dto;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import code.bean.nat.SpecialNatClass;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.bean.nat.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeSimulationDtoStd {
    public static final String TYPE_EV_LINE = "aiki.beans.facade.simulation.dto.EvLine";
    public static final String TYPE_POKEMON_PLAYER_DTO = "aiki.beans.facade.simulation.dto.PokemonPlayerDto";
    public static final String TYPE_POKEMON_TRAINER_DTO = "aiki.beans.facade.simulation.dto.PokemonTrainerDto";
    public static final String TYPE_RADIO_LINE_MOVE = "aiki.beans.facade.simulation.dto.RadioLineMove";
    public static final String TYPE_SELECT_LINE_MOVE = "aiki.beans.facade.simulation.dto.SelectLineMove";

    private static final String SELECTED = "selected";
    private static final String EV = "ev";
    private static final String INDEX = "index";

    public static void build(PokemonStandards _std) {
        buildEvLine(_std);
        buildPokemonPlayerDto(_std);
        buildPokemonTrainerDto(_std);
        buildRadioLineMove(_std);
        buildSelectLineMove(_std);
    }
    private static void buildEvLine(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EV_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(EV,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().addEntry(TYPE_EV_LINE, type_);
    }
    private static void buildPokemonPlayerDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_PLAYER_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().addEntry(TYPE_POKEMON_PLAYER_DTO, type_);
    }
    private static void buildPokemonTrainerDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_TRAINER_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().addEntry(TYPE_POKEMON_TRAINER_DTO, type_);
    }
    private static void buildRadioLineMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_RADIO_LINE_MOVE, fields_, constructors_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE, MethodModifier.NORMAL);
        fields_.add(new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().addEntry(TYPE_RADIO_LINE_MOVE, type_);
    }
    private static void buildSelectLineMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SELECT_LINE_MOVE, fields_, constructors_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE, MethodModifier.NORMAL);
        fields_.add(new StandardField(SELECTED,_std.getAliasPrimBoolean(),false,false,type_));
        _std.getStandards().addEntry(TYPE_SELECT_LINE_MOVE, type_);
    }
    public static ResultErrorStd getResultEvLine(ContextEl _cont, ClassField _classField, EvLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,EV)) {
            res_.setResult(new IntStruct(_inst.getEv()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonPlayerDto(ContextEl _cont, ClassField _classField, PokemonPlayerDto _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonTrainerDto(ContextEl _cont, ClassField _classField, PokemonTrainerDto _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultRadioLineMove(ContextEl _cont, ClassField _classField, RadioLineMove _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return AikiBeansFacadeDtoStd.getResultMoveLine(_cont, _classField, _inst);
    }
    public static ResultErrorStd getResultSelectLineMove(ContextEl _cont, ClassField _classField, SelectLineMove _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,SELECTED)) {
            res_.setResult(BooleanStruct.of(_inst.getSelected()));
            return res_;
        }
        return AikiBeansFacadeDtoStd.getResultMoveLine(_cont, _classField, _inst);
    }
    public static ResultErrorStd setResultEvLine(ContextEl _cont, ClassField _classField, Struct _val, EvLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,EV)) {
            _inst.setEv(NumParsers.convertToNumber(_val).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSelectLineMove(ContextEl _cont, ClassField _classField, Struct _val, SelectLineMove _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,SELECTED)) {
            _inst.setSelected(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
}
