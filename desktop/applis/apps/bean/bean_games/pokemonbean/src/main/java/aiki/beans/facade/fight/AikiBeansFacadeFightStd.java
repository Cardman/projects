package aiki.beans.facade.fight;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.RateStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.IntStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeFightStd {
    public static final String TYPE_KEY_HYPOTHESIS = "aiki.beans.facade.fight.KeyHypothesis";
    public static final String TYPE_MULT_POWER_MOVES = "aiki.beans.facade.fight.MultPowerMoves";
    public static final String TYPE_STATISTIC_INFO = "aiki.beans.facade.fight.StatisticInfo";
    public static final String TYPE_SUFFERED_DAMAGE_CATEGORY = "aiki.beans.facade.fight.SufferedDamageCategory";

    private static final String GET_PLAYER_POKEMON = "getPlayerPokemon";
    private static final String GET_MOVE = "getMove";
    private static final String GET_TARGET_POKEMON = "getTargetPokemon";
    private static final String IS_BELONGS_TO_USER = "isBelongsToUser";
    private static final String GET_NUMBER_TARGET = "getNumberTarget";
    private static final String GET_DAMAGE = "getDamage";
    private static final String GET_DISPLAY_STATISTIC = "getDisplayStatistic";
    private static final String IS_BASE = "isBase";
    private static final String GET_STATIS_BASE = "getStatisBase";
    private static final String GET_EV = "getEv";
    private static final String GET_IV = "getIv";
    private static final String IS_BOOST = "isBoost";
    private static final String GET_STATIS_BOOST = "getStatisBoost";
    private static final String GET_MULT_INFLICTED = "getMultInflicted";
    private static final String GET_MULT_SUFFERING = "getMultSuffering";
    private static final String GET_ROUND = "getRound";
    private static final String GET_USING = "getUsing";

    public static void build(PokemonStandards _std) {
        buildKeyHypothesis(_std);
        buildMultPowerMoves(_std);
        buildStatisticInfo(_std);
        buildSufferedDamageCategory(_std);
    }
    private static void buildKeyHypothesis(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_KEY_HYPOTHESIS, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        type_.getDirectInterfaces().add(BeanNatLgNames.TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new StandardMethod(GET_PLAYER_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TARGET_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_BELONGS_TO_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NUMBER_TARGET,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_DAMAGE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_KEY_HYPOTHESIS, type_);
    }
    private static void buildMultPowerMoves(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_MULT_POWER_MOVES, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MULT_INFLICTED,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MULT_SUFFERING,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_MULT_POWER_MOVES, type_);
    }
    private static void buildStatisticInfo(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_STATISTIC_INFO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_DISPLAY_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_BASE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_STATIS_BASE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_EV,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_IV,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_BOOST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_STATIS_BOOST,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_STATISTIC_INFO, type_);
    }
    private static void buildSufferedDamageCategory(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_SUFFERED_DAMAGE_CATEGORY, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ROUND,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_USING,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_SUFFERED_DAMAGE_CATEGORY, type_);
    }
    public static ResultErrorStd invokeMethodKeyHypothesis(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        KeyHypothesis instance_ = (KeyHypothesis) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_PLAYER_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getPlayerPokemon()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TARGET_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTargetPokemon()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BELONGS_TO_USER)) {
            res_.setResult(BooleanStruct.of(instance_.isBelongsToUser()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NUMBER_TARGET)) {
            res_.setResult(new IntStruct(instance_.getNumberTarget()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_DAMAGE)) {
            res_.setResult(new RateStruct(instance_.getDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMultPowerMoves(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        MultPowerMoves instance_ = (MultPowerMoves) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MULT_INFLICTED)) {
            res_.setResult(new RateStruct(instance_.getMultInflicted(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MULT_SUFFERING)) {
            res_.setResult(new RateStruct(instance_.getMultSuffering(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStatisticInfo(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        StatisticInfo instance_ = (StatisticInfo) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_DISPLAY_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getDisplayStatistic()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BASE)) {
            res_.setResult(BooleanStruct.of(instance_.isBase()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_STATIS_BASE)) {
            res_.setResult(new RateStruct(instance_.getStatisBase(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EV)) {
            res_.setResult(new IntStruct(instance_.getEv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IV)) {
            res_.setResult(new IntStruct(instance_.getIv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BOOST)) {
            res_.setResult(BooleanStruct.of(instance_.isBoost()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_STATIS_BOOST)) {
            res_.setResult(new IntStruct(instance_.getStatisBoost()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSufferedDamageCategory(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        SufferedDamageCategory instance_ = (SufferedDamageCategory) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_ROUND)) {
            res_.setResult(new RateStruct(instance_.getRound(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_USING)) {
            res_.setResult(new RateStruct(instance_.getUsing(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
}
