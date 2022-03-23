package aiki.beans.facade.simulation.dto;

import aiki.beans.PokemonStandards;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.stds.ResultErrorStd;
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
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EV_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(EV, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        _std.getStds().addEntry(TYPE_EV_LINE, type_);
    }
    private static void buildPokemonPlayerDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_PLAYER_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER_DTO, type_);
    }
    private static void buildPokemonTrainerDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_TRAINER_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        _std.getStds().addEntry(TYPE_POKEMON_TRAINER_DTO, type_);
    }
    private static void buildRadioLineMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_RADIO_LINE_MOVE, fields_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        _std.getStds().addEntry(TYPE_RADIO_LINE_MOVE, type_);
    }
    private static void buildSelectLineMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SELECT_LINE_MOVE, fields_, methods_, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE);
        fields_.add(new StandardField(SELECTED,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false));
        _std.getStds().addEntry(TYPE_SELECT_LINE_MOVE, type_);
    }
    public static ResultErrorStd getResultEvLine(ClassField _classField, EvLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,EV)) {
            res_.setResult(new IntStruct(_inst.getEv()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonPlayerDto(ClassField _classField, PokemonPlayerDto _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonTrainerDto(ClassField _classField, PokemonTrainerDto _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultRadioLineMove(ClassField _classField, RadioLineMove _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return AikiBeansFacadeDtoStd.getResultMoveLine(_classField, _inst);
    }
    public static ResultErrorStd getResultSelectLineMove(ClassField _classField, SelectLineMove _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,SELECTED)) {
            res_.setResult(BooleanStruct.of(_inst.getSelected()));
            return res_;
        }
        return AikiBeansFacadeDtoStd.getResultMoveLine(_classField, _inst);
    }
    public static ResultErrorStd setResultEvLine(ClassField _classField, Struct _val, EvLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,EV)) {
            _inst.setEv(NumParsers.convertToNumber(_val).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSelectLineMove(ClassField _classField, Struct _val, SelectLineMove _inst) {
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
