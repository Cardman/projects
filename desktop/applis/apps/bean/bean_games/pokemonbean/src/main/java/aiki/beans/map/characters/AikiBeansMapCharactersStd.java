package aiki.beans.map.characters;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansMapCharactersStd {
    public static final String TYPE_ALLY_BEAN = "aiki.beans.map.characters.AllyBean";
    public static final String TYPE_DEALER_BEAN = "aiki.beans.map.characters.DealerBean";
    public static final String TYPE_DUAL_FIGHT_BEAN = "aiki.beans.map.characters.DualFightBean";
    public static final String TYPE_SELLER_BEAN = "aiki.beans.map.characters.SellerBean";
    public static final String TYPE_TRAINER_BEAN = "aiki.beans.map.characters.TrainerBean";
    public static final String TYPE_TRAINER_ONE_FIGHT_BEAN = "aiki.beans.map.characters.TrainerOneFightBean";

    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
    private static final String GET_ITEMS = "getItems";
    private static final String GET_ALL_TM = "getAllTm";
    private static final String CLICK_TM = "clickTm";
    private static final String GET_TM = "getTm";
    private static final String GET_TEAMS_REWARDS = "getTeamsRewards";
    private static final String GET_TR_MOVE = "getTrMove";
    private static final String TEAM = "team";
    private static final String IMAGE = "image";
    private static final String IMAGE_MINI = "imageMini";
    private static final String IMAGE_MINI_SECOND = "imageMiniSecond";
    private static final String PAGE_ALLY = "pageAlly";
    private static final String ALLY = "ally";
    private static final String PAGE_TEAM = "pageTeam";
    private static final String TRAINER = "trainer";
    private static final String MOVE = "move";

    public static void build(PokemonStandards _std) {
        buildAllyBean(_std);
        buildDealerBean(_std);
        buildDualFightBean(_std);
        buildSellerBean(_std);
        buildTrainerBean(_std);
        buildTrainerOneFightBean(_std);
    }
    private static void buildAllyBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_ALLY_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(TEAM, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(ALLY,PokemonStandards.TYPE_ALLY,false,false));
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_IMAGE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ABILITY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ABILITY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER, BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER, BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_ALLY_BEAN, type_);
    }
    private static void buildDealerBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_DEALER_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ITEMS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ALL_TM,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_TM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_TM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_DEALER_BEAN, type_);
    }
    private static void buildDualFightBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_DUAL_FIGHT_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(IMAGE_MINI,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(IMAGE_MINI_SECOND,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(PAGE_ALLY,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(ALLY,PokemonStandards.TYPE_ALLY,false,false));
        fields_.add(new StandardField(PAGE_TEAM,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TEMP_TRAINER,false,false));
        _std.getStds().addEntry(TYPE_DUAL_FIGHT_BEAN, type_);
    }
    private static void buildSellerBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SELLER_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ITEMS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ALL_TM,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_TM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_TM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_SELLER_BEAN, type_);
    }
    private static void buildTrainerBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TRAINER_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(IMAGE_MINI,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(PAGE_TEAM,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER,false,false));
        fields_.add(new StandardField(MOVE,BeanNatCommonLgNames.STRING,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TEAMS_REWARDS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TR_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_TRAINER_BEAN, type_);
    }
    private static void buildTrainerOneFightBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TRAINER_ONE_FIGHT_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        _std.getStds().addEntry(TYPE_TRAINER_ONE_FIGHT_BEAN, type_);
    }
    public static ResultErrorStd getResultAllyBean(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        AllyBean instance_ = (AllyBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TEAM)) {
            res_.setResult(PokemonStandards.getPkTrainerArray(instance_.getTeam()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultDualFightBean(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        DualFightBean instance_ = (DualFightBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMAGE_MINI)) {
            res_.setResult(new StringStruct(instance_.getImageMini()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMAGE_MINI_SECOND)) {
            res_.setResult(new StringStruct(instance_.getImageMiniSecond()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PAGE_ALLY)) {
            res_.setResult(new StringStruct(DualFightBean.PAGE_ALLY));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALLY)) {
            res_.setResult(new AllyStruct(instance_.getAlly(),PokemonStandards.TYPE_ALLY));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PAGE_TEAM)) {
            res_.setResult(new StringStruct(DualFightBean.PAGE_TEAM));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TRAINER)) {
            res_.setResult(new PersonStruct(instance_.getTrainer(),PokemonStandards.TYPE_TEMP_TRAINER));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultTrainerBean(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        TrainerBean instance_ = (TrainerBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMAGE_MINI)) {
            res_.setResult(new StringStruct(instance_.getImageMini()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PAGE_TEAM)) {
            res_.setResult(new StringStruct(TrainerBean.PAGE_TEAM));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TRAINER)) {
            res_.setResult(new PersonStruct(instance_.getTrainer(),PokemonStandards.TYPE_TRAINER));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultAllyBean(ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        AllyBean instance_ = (AllyBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,ALLY)) {
            instance_.setAlly(((AllyStruct)_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAllyBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        AllyBean instance_ = (AllyBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodDealerBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        DealerBean instance_ = (DealerBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_ITEMS)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getItems()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ALL_TM)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getAllTm()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TM)) {
            res_.setResult(new StringStruct(instance_.clickTm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TM)) {
            res_.setResult(new StringStruct(instance_.getTm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSellerBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        SellerBean instance_ = (SellerBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_ITEMS)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getItems()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ALL_TM)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getAllTm()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TM)) {
            res_.setResult(new StringStruct(instance_.clickTm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TM)) {
            res_.setResult(new StringStruct(instance_.getTm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTrainerBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        TrainerBean instance_ = (TrainerBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_TEAMS_REWARDS)) {
            res_.setResult(PokemonStandards.getPkTeam(instance_.getTeamsRewards()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrMove()));
            return res_;
        }
        return res_;
    }
}
