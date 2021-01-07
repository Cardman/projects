package aiki.beans.map.pokemon;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import aiki.map.characters.Trainer;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansMapPokemonStd {
    public static final String TYPE_POKEMON_TEAM_BEAN = "aiki.beans.map.pokemon.PokemonTeamBean";

    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
    private static final String TRAINER = "trainer";
    private static final String REWARD = "reward";
    private static final String MULTIPLICITY = "multiplicity";
    private static final String TEAM = "team";
    private static final String NO_FIGHT = "noFight";

    public static void build(PokemonStandards _std) {
        buildPokemonTeamBean(_std);
    }
    private static void buildPokemonTeamBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_POKEMON_TEAM_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER,false,false,type_));
        fields_.add(new StandardField(REWARD,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(MULTIPLICITY,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(NO_FIGHT,_std.getAliasPrimInteger(),false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_POKEMON_TEAM_BEAN, type_);
    }
    public static ResultErrorStd getResultPokemonTeamBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonTeamBean instance_ = (PokemonTeamBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,REWARD)) {
            res_.setResult(new IntStruct(instance_.getReward()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MULTIPLICITY)) {
            res_.setResult(new IntStruct(instance_.getMultiplicity()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TEAM)) {
            res_.setResult(new DefaultStruct(instance_.getTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NO_FIGHT)) {
            res_.setResult(new IntStruct(instance_.getNoFight()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultPokemonTeamBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonTeamBean instance_ = (PokemonTeamBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TRAINER)) {
            instance_.setTrainer((Trainer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NO_FIGHT)) {
            instance_.setNoFight((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPokemonTeamBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        PokemonTeamBean instance_ = (PokemonTeamBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        return res_;
    }
}
