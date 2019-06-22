package aiki.beans.map.characters;
import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import aiki.map.characters.Ally;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.util.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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

    public static void build(BeanLgNames _std) {
        buildAllyBean(_std);
        buildDealerBean(_std);
        buildDualFightBean(_std);
        buildSellerBean(_std);
        buildTrainerBean(_std);
        buildTrainerOneFightBean(_std);
    }
    private static void buildAllyBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_ALLY_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(TEAM,new StandardField(TEAM,_std.getCustList(),false,false,type_));
        fields_.put(ALLY,new StandardField(ALLY,PokemonStandards.TYPE_ALLY,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_ALLY_BEAN, type_);
    }
    private static void buildDealerBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_DEALER_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ITEMS,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ALL_TM,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_DEALER_BEAN, type_);
    }
    private static void buildDualFightBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_DUAL_FIGHT_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(IMAGE,new StandardField(IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(IMAGE_MINI,new StandardField(IMAGE_MINI,_std.getAliasString(),false,false,type_));
        fields_.put(IMAGE_MINI_SECOND,new StandardField(IMAGE_MINI_SECOND,_std.getAliasString(),false,false,type_));
        fields_.put(PAGE_ALLY,new StandardField(PAGE_ALLY,_std.getAliasString(),false,false,type_));
        fields_.put(ALLY,new StandardField(ALLY,PokemonStandards.TYPE_ALLY,false,false,type_));
        fields_.put(PAGE_TEAM,new StandardField(PAGE_TEAM,_std.getAliasString(),false,false,type_));
        fields_.put(TRAINER,new StandardField(TRAINER,PokemonStandards.TYPE_TEMP_TRAINER,false,false,type_));
        _std.getStandards().put(TYPE_DUAL_FIGHT_BEAN, type_);
    }
    private static void buildSellerBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SELLER_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ITEMS,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ALL_TM,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SELLER_BEAN, type_);
    }
    private static void buildTrainerBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_TRAINER_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(IMAGE,new StandardField(IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(IMAGE_MINI,new StandardField(IMAGE_MINI,_std.getAliasString(),false,false,type_));
        fields_.put(PAGE_TEAM,new StandardField(PAGE_TEAM,_std.getAliasString(),false,false,type_));
        fields_.put(TRAINER,new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER,false,false,type_));
        fields_.put(MOVE,new StandardField(MOVE,_std.getAliasString(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_TEAMS_REWARDS,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_TRAINER_BEAN, type_);
    }
    private static void buildTrainerOneFightBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_TRAINER_ONE_FIGHT_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_TRAINER_ONE_FIGHT_BEAN, type_);
    }
    public static ResultErrorStd getResultAllyBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        AllyBean instance_ = (AllyBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TEAM)) {
            res_.setResult(new StdStruct(instance_.getTeam(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultDualFightBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        DualFightBean instance_ = (DualFightBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMAGE_MINI)) {
            res_.setResult(new StringStruct(instance_.getImageMini()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMAGE_MINI_SECOND)) {
            res_.setResult(new StringStruct(instance_.getImageMiniSecond()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PAGE_ALLY)) {
            res_.setResult(new StringStruct(DualFightBean.PAGE_ALLY));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY)) {
            res_.setResult(StdStruct.newInstance(instance_.getAlly(),PokemonStandards.TYPE_ALLY));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PAGE_TEAM)) {
            res_.setResult(new StringStruct(DualFightBean.PAGE_TEAM));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TRAINER)) {
            res_.setResult(StdStruct.newInstance(instance_.getTrainer(),PokemonStandards.TYPE_TEMP_TRAINER));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultTrainerBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        TrainerBean instance_ = (TrainerBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMAGE_MINI)) {
            res_.setResult(new StringStruct(instance_.getImageMini()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PAGE_TEAM)) {
            res_.setResult(new StringStruct(TrainerBean.PAGE_TEAM));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TRAINER)) {
            res_.setResult(StdStruct.newInstance(instance_.getTrainer(),PokemonStandards.TYPE_TRAINER));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultAllyBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        AllyBean instance_ = (AllyBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,ALLY)) {
            instance_.setAlly((Ally) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAllyBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        AllyBean instance_ = (AllyBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodDealerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        DealerBean instance_ = (DealerBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getItems(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ALL_TM)) {
            res_.setResult(new StdStruct(instance_.getAllTm(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TM)) {
            res_.setResult(new StringStruct(instance_.clickTm((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TM)) {
            res_.setResult(new StringStruct(instance_.getTm((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSellerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        SellerBean instance_ = (SellerBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getItems(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ALL_TM)) {
            res_.setResult(new StdStruct(instance_.getAllTm(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TM)) {
            res_.setResult(new StringStruct(instance_.clickTm((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TM)) {
            res_.setResult(new StringStruct(instance_.getTm((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTrainerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        TrainerBean instance_ = (TrainerBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TEAMS_REWARDS)) {
            res_.setResult(new StdStruct(instance_.getTeamsRewards(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrMove()));
            return res_;
        }
        return res_;
    }
}
