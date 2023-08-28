package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasMathType {
    private static final String MATH="485";
    private static final String ABS="486";
    private static final String MAX="487";
    private static final String MIN="488";
    private static final String MOD="489";
    private static final String QUOT="490";
    private static final String BIN_MOD="491";
    private static final String BIN_QUOT="492";
    private static final String PLUS="493";
    private static final String MINUS="494";
    private static final String MULT="495";
    private static final String NEG_BIN="496";
    private static final String NEG="497";
    private static final String AND="498";
    private static final String OR="499";
    private static final String XOR="500";
    private static final String LE="501";
    private static final String GE="502";
    private static final String LT="503";
    private static final String GT="504";
    private static final String SHIFT_LEFT="505";
    private static final String SHIFT_RIGHT="506";
    private static final String BIT_SHIFT_LEFT="507";
    private static final String BIT_SHIFT_RIGHT="508";
    private static final String ROTATE_LEFT="509";
    private static final String ROTATE_RIGHT="510";
    private static final String RANDOM="511";
    private static final String NATIVE_RANDOM="512";
    private static final String EVAL="______1791";
    private static final String SEED="______1792";
    private static final String SEED_SPEC_GENERATOR="______1797";
    private static final String SEED_SPEC_DOUBLE_GENERATOR="______1798";
    private String aliasAbs;
    private String aliasMax;
    private String aliasMin;
    private String aliasQuot;
    private String aliasMod;
    private String aliasMath;
    private String aliasBinQuot;
    private String aliasBinMod;
    private String aliasPlus;
    private String aliasMinus;
    private String aliasMult;
    private String aliasAnd;
    private String aliasOr;
    private String aliasXor;
    private String aliasNeg;
    private String aliasNegBin;
    private String aliasLt;
    private String aliasGt;
    private String aliasLe;
    private String aliasGe;
    private String aliasShiftLeft;
    private String aliasShiftRight;
    private String aliasBitShiftLeft;
    private String aliasBitShiftRight;
    private String aliasRotateLeft;
    private String aliasRotateRight;
    private String aliasRandom;
    private String aliasNativeRandom;
    private String aliasEval;
    private String aliasSeed;
    private String aliasSeedSpecGenerator;
    private String aliasSeedSpecDoubleGenerator;
    private final AliasParamMath params = new AliasParamMath();
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasMath(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH)));
        setAliasMod(LgNamesContent.get(_util,_cust,_mapping.getVal(MOD)));
        setAliasAbs(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS)));
        setAliasMax(LgNamesContent.get(_util,_cust,_mapping.getVal(MAX)));
        setAliasMin(LgNamesContent.get(_util,_cust,_mapping.getVal(MIN)));
        setAliasQuot(LgNamesContent.get(_util,_cust,_mapping.getVal(QUOT)));
        setAliasXor(LgNamesContent.get(_util,_cust,_mapping.getVal(XOR)));
        setAliasMult(LgNamesContent.get(_util,_cust,_mapping.getVal(MULT)));
        setAliasRandom(LgNamesContent.get(_util,_cust,_mapping.getVal(RANDOM)));
        setAliasNativeRandom(LgNamesContent.get(_util,_cust,_mapping.getVal(NATIVE_RANDOM)));
        setAliasEval(LgNamesContent.get(_util,_cust,_mapping.getVal(EVAL)));
        setAliasSeed(LgNamesContent.get(_util,_cust,_mapping.getVal(SEED)));
        setAliasSeedSpecGenerator(LgNamesContent.get(_util,_cust,_mapping.getVal(SEED_SPEC_GENERATOR)));
        setAliasSeedSpecDoubleGenerator(LgNamesContent.get(_util,_cust,_mapping.getVal(SEED_SPEC_DOUBLE_GENERATOR)));
        setAliasNegBin(LgNamesContent.get(_util,_cust,_mapping.getVal(NEG_BIN)));
        setAliasMinus(LgNamesContent.get(_util,_cust,_mapping.getVal(MINUS)));
        setAliasBinMod(LgNamesContent.get(_util,_cust,_mapping.getVal(BIN_MOD)));
        setAliasLt(LgNamesContent.get(_util,_cust,_mapping.getVal(LT)));
        setAliasGt(LgNamesContent.get(_util,_cust,_mapping.getVal(GT)));
        setAliasLe(LgNamesContent.get(_util,_cust,_mapping.getVal(LE)));
        setAliasGe(LgNamesContent.get(_util,_cust,_mapping.getVal(GE)));
        setAliasAnd(LgNamesContent.get(_util,_cust,_mapping.getVal(AND)));
        setAliasOr(LgNamesContent.get(_util,_cust,_mapping.getVal(OR)));
        setAliasPlus(LgNamesContent.get(_util,_cust,_mapping.getVal(PLUS)));
        setAliasBinQuot(LgNamesContent.get(_util,_cust,_mapping.getVal(BIN_QUOT)));
        setAliasNeg(LgNamesContent.get(_util,_cust,_mapping.getVal(NEG)));
        setAliasRotateLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(ROTATE_LEFT)));
        setAliasShiftRight(LgNamesContent.get(_util,_cust,_mapping.getVal(SHIFT_RIGHT)));
        setAliasRotateRight(LgNamesContent.get(_util,_cust,_mapping.getVal(ROTATE_RIGHT)));
        setAliasBitShiftLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(BIT_SHIFT_LEFT)));
        setAliasShiftLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(SHIFT_LEFT)));
        setAliasBitShiftRight(LgNamesContent.get(_util,_cust,_mapping.getVal(BIT_SHIFT_RIGHT)));
    }
    public static void en(TranslationsFile _en){
        _en.add(MATH,"Math=$core.Math");
        _en.add(ABS,"Abs=abs");
        _en.add(MAX,"Max=max");
        _en.add(MIN,"Min=min");
        _en.add(MOD,"Mod=mod");
        _en.add(QUOT,"Quot=quot");
        _en.add(BIN_MOD,"BinMod=binMod");
        _en.add(BIN_QUOT,"BinQuot=binQuot");
        _en.add(PLUS,"Plus=plus");
        _en.add(MINUS,"Minus=minus");
        _en.add(MULT,"Mult=mult");
        _en.add(NEG_BIN,"NegBin=negBin");
        _en.add(NEG,"Neg=neg");
        _en.add(AND,"And=and");
        _en.add(OR,"Or=or");
        _en.add(XOR,"Xor=xor");
        _en.add(LE,"Le=le");
        _en.add(GE,"Ge=ge");
        _en.add(LT,"Lt=lt");
        _en.add(GT,"Gt=gt");
        _en.add(SHIFT_LEFT,"ShiftLeft=shiftLeft");
        _en.add(SHIFT_RIGHT,"ShiftRight=shiftRight");
        _en.add(BIT_SHIFT_LEFT,"BitShiftLeft=bitShiftLeft");
        _en.add(BIT_SHIFT_RIGHT,"BitShiftRight=bitShiftRight");
        _en.add(ROTATE_LEFT,"RotateLeft=rotateLeft");
        _en.add(ROTATE_RIGHT,"RotateRight=rotateRight");
        _en.add(RANDOM,"Random=random");
        _en.add(NATIVE_RANDOM,"NativeRandom=natRandom");
        _en.add(EVAL,"Eval=eval");
        _en.add(SEED,"Seed=seed");
        _en.add(SEED_SPEC_GENERATOR,"SeedSpecGenerator=seedGenerator");
        _en.add(SEED_SPEC_DOUBLE_GENERATOR,"SeedSpecDoubleGenerator=seedDoubleGenerator");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(MATH,"Math=$coeur.Math");
        _fr.add(ABS,"Abs=abs");
        _fr.add(MAX,"Max=max");
        _fr.add(MIN,"Min=min");
        _fr.add(MOD,"Mod=mod");
        _fr.add(QUOT,"Quot=quot");
        _fr.add(BIN_MOD,"BinMod=binMod");
        _fr.add(BIN_QUOT,"BinQuot=binQuot");
        _fr.add(PLUS,"Plus=plus");
        _fr.add(MINUS,"Minus=moins");
        _fr.add(MULT,"Mult=mult");
        _fr.add(NEG_BIN,"NegBin=negBin");
        _fr.add(NEG,"Neg=neg");
        _fr.add(AND,"And=et");
        _fr.add(OR,"Or=ou");
        _fr.add(XOR,"Xor=ouExc");
        _fr.add(LE,"Le=pqe");
        _fr.add(GE,"Ge=gqe");
        _fr.add(LT,"Lt=pq");
        _fr.add(GT,"Gt=gq");
        _fr.add(SHIFT_LEFT,"ShiftLeft=glisserGauche");
        _fr.add(SHIFT_RIGHT,"ShiftRight=glisserDroite");
        _fr.add(BIT_SHIFT_LEFT,"BitShiftLeft=binGlisserGauche");
        _fr.add(BIT_SHIFT_RIGHT,"BitShiftRight=binGlisserDroite");
        _fr.add(ROTATE_LEFT,"RotateLeft=rotGauche");
        _fr.add(ROTATE_RIGHT,"RotateRight=rotDroite");
        _fr.add(RANDOM,"Random=alea");
        _fr.add(NATIVE_RANDOM,"NativeRandom=natAlea");
        _fr.add(EVAL,"Eval=eval");
        _fr.add(SEED,"Seed=graine");
        _fr.add(SEED_SPEC_GENERATOR,"SeedSpecGenerator=graineGenerateur");
        _fr.add(SEED_SPEC_DOUBLE_GENERATOR,"SeedSpecDoubleGenerator=graineGenerateurDec");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(MATH,"Math");
        _m.addEntry(ABS,"Abs");
        _m.addEntry(MAX,"Max");
        _m.addEntry(MIN,"Min");
        _m.addEntry(MOD,"Mod");
        _m.addEntry(QUOT,"Quot");
        _m.addEntry(BIN_MOD,"BinMod");
        _m.addEntry(BIN_QUOT,"BinQuot");
        _m.addEntry(PLUS,"Plus");
        _m.addEntry(MINUS,"Minus");
        _m.addEntry(MULT,"Mult");
        _m.addEntry(NEG_BIN,"NegBin");
        _m.addEntry(NEG,"Neg");
        _m.addEntry(AND,"And");
        _m.addEntry(OR,"Or");
        _m.addEntry(XOR,"Xor");
        _m.addEntry(LE,"Le");
        _m.addEntry(GE,"Ge");
        _m.addEntry(LT,"Lt");
        _m.addEntry(GT,"Gt");
        _m.addEntry(SHIFT_LEFT,"ShiftLeft");
        _m.addEntry(SHIFT_RIGHT,"ShiftRight");
        _m.addEntry(BIT_SHIFT_LEFT,"BitShiftLeft");
        _m.addEntry(BIT_SHIFT_RIGHT,"BitShiftRight");
        _m.addEntry(ROTATE_LEFT,"RotateLeft");
        _m.addEntry(ROTATE_RIGHT,"RotateRight");
        _m.addEntry(RANDOM,"Random");
        _m.addEntry(NATIVE_RANDOM,"NativeRandom");
        _m.addEntry(EVAL,"Eval");
        _m.addEntry(SEED,"Seed");
        _m.addEntry(SEED_SPEC_GENERATOR,"SeedSpecGenerator");
        _m.addEntry(SEED_SPEC_DOUBLE_GENERATOR,"SeedSpecDoubleGenerator");
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(MATH), getAliasMath());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasMath(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS), getAliasAbs()),
                new KeyValueMemberName(_mapping.getVal(MAX), getAliasMax()),
                new KeyValueMemberName(_mapping.getVal(MIN), getAliasMin()),
                new KeyValueMemberName(_mapping.getVal(MOD),getAliasMod()),
                new KeyValueMemberName(_mapping.getVal(QUOT),getAliasQuot()),
                new KeyValueMemberName(_mapping.getVal(BIN_MOD),getAliasBinMod()),
                new KeyValueMemberName(_mapping.getVal(BIN_QUOT),getAliasBinQuot()),
                new KeyValueMemberName(_mapping.getVal(PLUS),getAliasPlus()),
                new KeyValueMemberName(_mapping.getVal(MINUS),getAliasMinus()),
                new KeyValueMemberName(_mapping.getVal(MULT),getAliasMult()),
                new KeyValueMemberName(_mapping.getVal(NEG_BIN),getAliasNegBin()),
                new KeyValueMemberName(_mapping.getVal(NEG),getAliasNeg()),
                new KeyValueMemberName(_mapping.getVal(AND),getAliasAnd()),
                new KeyValueMemberName(_mapping.getVal(OR),getAliasOr()),
                new KeyValueMemberName(_mapping.getVal(XOR),getAliasXor()),
                new KeyValueMemberName(_mapping.getVal(LE),getAliasLe()),
                new KeyValueMemberName(_mapping.getVal(GE),getAliasGe()),
                new KeyValueMemberName(_mapping.getVal(LT),getAliasLt()),
                new KeyValueMemberName(_mapping.getVal(GT),getAliasGt()),
                new KeyValueMemberName(_mapping.getVal(SHIFT_LEFT),getAliasShiftLeft()),
                new KeyValueMemberName(_mapping.getVal(SHIFT_RIGHT),getAliasShiftRight()),
                new KeyValueMemberName(_mapping.getVal(BIT_SHIFT_LEFT),getAliasBitShiftLeft()),
                new KeyValueMemberName(_mapping.getVal(BIT_SHIFT_RIGHT),getAliasBitShiftRight()),
                new KeyValueMemberName(_mapping.getVal(ROTATE_LEFT),getAliasRotateLeft()),
                new KeyValueMemberName(_mapping.getVal(ROTATE_RIGHT),getAliasRotateRight()),
                new KeyValueMemberName(_mapping.getVal(RANDOM),getAliasRandom()),
                new KeyValueMemberName(_mapping.getVal(NATIVE_RANDOM),getAliasNativeRandom()),
                new KeyValueMemberName(_mapping.getVal(EVAL),getAliasEval()),
                new KeyValueMemberName(_mapping.getVal(SEED),getAliasSeed()),
                new KeyValueMemberName(_mapping.getVal(SEED_SPEC_GENERATOR),getAliasSeedSpecGenerator()),
                new KeyValueMemberName(_mapping.getVal(SEED_SPEC_DOUBLE_GENERATOR),getAliasSeedSpecDoubleGenerator())));
        return map_;
    }
    public void build(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasPrimInteger_ = _stds.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimLong_ = _stds.getContent().getPrimTypes().getAliasPrimLong();
        String aliasPrimFloat_ = _stds.getContent().getPrimTypes().getAliasPrimFloat();
        String aliasPrimDouble_ = _stds.getContent().getPrimTypes().getAliasPrimDouble();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        StandardClass std_ = new StandardClass(aliasMath, fields_, constructors_, methods_, aliasObject_, StdClassModifier.HYPER_ABSTRACT);
        std_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasPrimInteger_);
        StandardMethod method_ = new StandardMethod(aliasAbs, params_, aliasPrimInteger_, false, MethodModifier.STATIC, new StringList(params.getAliasMath0Abs0()), new FctMathAbs0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Abs0()),new FctMathAbs1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Abs0()),new FctMathAbs2());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3Abs0()),new FctMathAbs3());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMax, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Max0(),params.getAliasMath0Max1()),new FctMathMax0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMax, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Max0(),params.getAliasMath1Max1()),new FctMathMax1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasMax, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Max0(),params.getAliasMath2Max1()),new FctMathMax2());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasMax, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3Max0(),params.getAliasMath3Max1()),new FctMathMax3());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMin, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Min0(),params.getAliasMath0Min1()),new FctMathMin0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMin, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Min0(),params.getAliasMath1Min1()),new FctMathMin1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasMin, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Min0(),params.getAliasMath2Min1()),new FctMathMin2());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasMin, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3Min0(),params.getAliasMath3Min1()),new FctMathMin3());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasQuot, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Quot0(),params.getAliasMath0Quot1()),new FctMathQuot0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasQuot, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Quot0(),params.getAliasMath1Quot1()),new FctMathQuot1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMod, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Mod0(),params.getAliasMath0Mod1()),new FctMathMod0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMod, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Mod0(),params.getAliasMath1Mod1()),new FctMathMod1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Plus0()),new FctNbId());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Plus0()),new FctNbId());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Plus0()),new FctNbId());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3Plus0()),new FctNbId());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Minus0()),new FctMathOpposite(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Minus0()),new FctMathOpposite(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Minus0()),new FctMathOpposite(PrimitiveTypes.FLOAT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3Minus0()),new FctMathOpposite(PrimitiveTypes.DOUBLE_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasNeg, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Neg0()),new FctMathNeg());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasNegBin, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0NegBin0()),new FctMathNegBin(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasNegBin, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1NegBin0()),new FctMathNegBin(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_stds.getStandards(), aliasMath, std_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath4Plus0(),params.getAliasMath4Plus1()),new FctMathSum(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath5Plus0(),params.getAliasMath5Plus1()),new FctMathSum(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath6Plus0(),params.getAliasMath6Plus1()),new FctMathSum(PrimitiveTypes.FLOAT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasPlus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath7Plus0(),params.getAliasMath7Plus1()),new FctMathSum(PrimitiveTypes.DOUBLE_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath4Minus0(),params.getAliasMath4Minus1()),new FctMathMinus(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath5Minus0(),params.getAliasMath5Minus1()),new FctMathMinus(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath6Minus0(),params.getAliasMath6Minus1()),new FctMathMinus(PrimitiveTypes.FLOAT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasMinus, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath7Minus0(),params.getAliasMath7Minus1()),new FctMathMinus(PrimitiveTypes.DOUBLE_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Mult0(),params.getAliasMath0Mult1()),new FctMathMult(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Mult0(),params.getAliasMath1Mult1()),new FctMathMult(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Mult0(),params.getAliasMath2Mult1()),new FctMathMult(PrimitiveTypes.FLOAT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasMult, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3Mult0(),params.getAliasMath3Mult1()),new FctMathMult(PrimitiveTypes.DOUBLE_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0BinQuot0(),params.getAliasMath0BinQuot1()),new FctMathBinQuot(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1BinQuot0(),params.getAliasMath1BinQuot1()),new FctMathBinQuot(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2BinQuot0(),params.getAliasMath2BinQuot1()),new FctMathBinQuot(PrimitiveTypes.FLOAT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasBinQuot, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3BinQuot0(),params.getAliasMath3BinQuot1()),new FctMathBinQuot(PrimitiveTypes.DOUBLE_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0BinMod0(),params.getAliasMath0BinMod1()),new FctMathBinMod(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1BinMod0(),params.getAliasMath1BinMod1()),new FctMathBinMod(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimFloat_,aliasPrimFloat_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimFloat_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2BinMod0(),params.getAliasMath2BinMod1()),new FctMathBinMod(PrimitiveTypes.FLOAT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasBinMod, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasMath3BinMod0(),params.getAliasMath3BinMod1()),new FctMathBinMod(PrimitiveTypes.DOUBLE_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAnd, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0And0(),params.getAliasMath0And1()),new FctMathAnd(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasAnd, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1And0(),params.getAliasMath1And1()),new FctMathAnd(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimBoolean_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasAnd, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2And0(),params.getAliasMath2And1()),new FctMathAnd(PrimitiveTypes.BOOL_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasOr, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Or0(),params.getAliasMath0Or1()),new FctMathOr(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasOr, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Or0(),params.getAliasMath1Or1()),new FctMathOr(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimBoolean_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasOr, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Or0(),params.getAliasMath2Or1()),new FctMathOr(PrimitiveTypes.BOOL_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasXor, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Xor0(),params.getAliasMath0Xor1()),new FctMathXor(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasXor, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Xor0(),params.getAliasMath1Xor1()),new FctMathXor(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimBoolean_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasXor, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath2Xor0(),params.getAliasMath2Xor1()),new FctMathXor(PrimitiveTypes.BOOL_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasShiftLeft, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0ShiftLeft0(),params.getAliasMath0ShiftLeft1()),new FctMathShiftLeft(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasShiftLeft, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1ShiftLeft0(),params.getAliasMath1ShiftLeft1()),new FctMathShiftLeft(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasShiftRight, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0ShiftRight0(),params.getAliasMath0ShiftRight1()),new FctMathShiftRight(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasShiftRight, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1ShiftRight0(),params.getAliasMath1ShiftRight1()),new FctMathShiftRight(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBitShiftLeft, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0BitShiftLeft0(),params.getAliasMath0BitShiftLeft1()),new FctMathBitShiftLeft(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBitShiftLeft, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1BitShiftLeft0(),params.getAliasMath1BitShiftLeft1()),new FctMathBitShiftLeft(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasBitShiftRight, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0BitShiftRight0(),params.getAliasMath0BitShiftRight1()),new FctMathBitShiftRight(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasBitShiftRight, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1BitShiftRight0(),params.getAliasMath1BitShiftRight1()),new FctMathBitShiftRight(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasRotateLeft, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0RotateLeft0(),params.getAliasMath0RotateLeft1()),new FctMathRotateLeft(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasRotateLeft, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1RotateLeft0(),params.getAliasMath1RotateLeft1()),new FctMathRotateLeft(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasRotateRight, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0RotateRight0(),params.getAliasMath0RotateRight1()),new FctMathRotateRight(PrimitiveTypes.INT_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasRotateRight, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1RotateRight0(),params.getAliasMath1RotateRight1()),new FctMathRotateRight(PrimitiveTypes.LONG_WRAP));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasLe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Le0(),params.getAliasMath0Le1()),new FctMathLe());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasGe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Ge0(),params.getAliasMath0Ge1()),new FctMathGe());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasLt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Lt0(),params.getAliasMath0Lt1()),new FctMathLt());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_,aliasPrimLong_);
        method_ = new StandardMethod(aliasGt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Gt0(),params.getAliasMath0Gt1()),new FctMathGt());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasLe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Le0(),params.getAliasMath1Le1()),new FctMathLe());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasGe, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Ge0(),params.getAliasMath1Ge1()),new FctMathGe());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasLt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Lt0(),params.getAliasMath1Lt1()),new FctMathLt());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimDouble_,aliasPrimDouble_);
        method_ = new StandardMethod(aliasGt, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasMath1Gt0(),params.getAliasMath1Gt1()),new FctMathGt());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasRandom, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0Random0()),new FctMathRandom1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRandom, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new FctMathRandom0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasNativeRandom, params_, aliasPrimLong_, false, MethodModifier.STATIC,new StringList(params.getAliasMath0NativeRandom0()),new FctMathNativeRandom1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasNativeRandom, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new FctMathNativeRandom0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasSeed, params_, _stds.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(params.getAliasMath0Seed0()), new FctMathSeed1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSeed, params_, aliasObject_, false, MethodModifier.STATIC, new FctMathSeed0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_stds.getContent().getPredefTypes().getAliasSeedGenerator());
        method_ = new StandardMethod(aliasSeedSpecGenerator, params_, _stds.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(params.getAliasMath0SeedSpecGenerator0()),new FctMathSeedSpecGenerator1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSeedSpecGenerator, params_, _stds.getContent().getPredefTypes().getAliasSeedGenerator(), false, MethodModifier.STATIC,new FctMathSeedSpecGenerator0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_stds.getContent().getPredefTypes().getAliasSeedDoubleGenerator());
        method_ = new StandardMethod(aliasSeedSpecDoubleGenerator, params_, _stds.getContent().getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(params.getAliasMath0SeedSpecDoubleGenerator0()),new FctMathSeedSpecDoubleGenerator1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSeedSpecDoubleGenerator, params_, _stds.getContent().getPredefTypes().getAliasSeedDoubleGenerator(), false, MethodModifier.STATIC,new FctMathSeedSpecDoubleGenerator0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_stds.getContent().getCharSeq().getAliasString(),_stds.getContent().getCharSeq().getAliasReplacement());
        method_ = new StandardMethod(aliasEval, params_, _stds.getContent().getCharSeq().getAliasString(), true, MethodModifier.STATIC,new FctMathEval());
        StandardNamedFunction.addFct(methods_, method_);
    }

    public String getAliasAbs() {
        return aliasAbs;
    }
    public void setAliasAbs(String _aliasAbs) {
        aliasAbs = _aliasAbs;
    }

    public String getAliasMax() {
        return aliasMax;
    }

    public void setAliasMax(String _aliasMax) {
        this.aliasMax = _aliasMax;
    }

    public String getAliasMin() {
        return aliasMin;
    }

    public void setAliasMin(String _aliasMin) {
        this.aliasMin = _aliasMin;
    }

    public String getAliasQuot() {
        return aliasQuot;
    }
    public void setAliasQuot(String _aliasQuot) {
        aliasQuot = _aliasQuot;
    }
    public String getAliasMod() {
        return aliasMod;
    }
    public void setAliasMod(String _aliasMod) {
        aliasMod = _aliasMod;
    }
    public String getAliasMath() {
        return aliasMath;
    }
    public void setAliasMath(String _aliasMath) {
        aliasMath = _aliasMath;
    }
    public String getAliasBinQuot() {
        return aliasBinQuot;
    }
    public void setAliasBinQuot(String _aliasBinQuot) {
        aliasBinQuot = _aliasBinQuot;
    }
    public String getAliasBinMod() {
        return aliasBinMod;
    }
    public void setAliasBinMod(String _aliasBinMod) {
        aliasBinMod = _aliasBinMod;
    }
    public String getAliasPlus() {
        return aliasPlus;
    }
    public void setAliasPlus(String _aliasPlus) {
        aliasPlus = _aliasPlus;
    }
    public String getAliasMinus() {
        return aliasMinus;
    }
    public void setAliasMinus(String _aliasMinus) {
        aliasMinus = _aliasMinus;
    }
    public String getAliasMult() {
        return aliasMult;
    }
    public void setAliasMult(String _aliasMult) {
        aliasMult = _aliasMult;
    }
    public String getAliasAnd() {
        return aliasAnd;
    }
    public void setAliasAnd(String _aliasAnd) {
        aliasAnd = _aliasAnd;
    }
    public String getAliasOr() {
        return aliasOr;
    }
    public void setAliasOr(String _aliasOr) {
        aliasOr = _aliasOr;
    }
    public String getAliasXor() {
        return aliasXor;
    }
    public void setAliasXor(String _aliasXor) {
        aliasXor = _aliasXor;
    }
    public String getAliasNegBin() {
        return aliasNegBin;
    }
    public void setAliasNegBin(String _aliasNegBin) {
        aliasNegBin = _aliasNegBin;
    }
    public String getAliasNeg() {
        return aliasNeg;
    }
    public void setAliasNeg(String _aliasNeg) {
        aliasNeg = _aliasNeg;
    }
    public String getAliasLt() {
        return aliasLt;
    }
    public void setAliasLt(String _aliasLt) {
        aliasLt = _aliasLt;
    }
    public String getAliasGt() {
        return aliasGt;
    }
    public void setAliasGt(String _aliasGt) {
        aliasGt = _aliasGt;
    }
    public String getAliasLe() {
        return aliasLe;
    }
    public void setAliasLe(String _aliasLe) {
        aliasLe = _aliasLe;
    }
    public String getAliasGe() {
        return aliasGe;
    }
    public void setAliasGe(String _aliasGe) {
        aliasGe = _aliasGe;
    }
    public String getAliasShiftLeft() {
        return aliasShiftLeft;
    }
    public void setAliasShiftLeft(String _aliasShiftLeft) {
        aliasShiftLeft = _aliasShiftLeft;
    }
    public String getAliasShiftRight() {
        return aliasShiftRight;
    }
    public void setAliasShiftRight(String _aliasShiftRight) {
        aliasShiftRight = _aliasShiftRight;
    }
    public String getAliasBitShiftLeft() {
        return aliasBitShiftLeft;
    }
    public void setAliasBitShiftLeft(String _aliasBitShiftLeft) {
        aliasBitShiftLeft = _aliasBitShiftLeft;
    }
    public String getAliasBitShiftRight() {
        return aliasBitShiftRight;
    }
    public void setAliasBitShiftRight(String _aliasBitShiftRight) {
        aliasBitShiftRight = _aliasBitShiftRight;
    }
    public String getAliasRotateLeft() {
        return aliasRotateLeft;
    }
    public void setAliasRotateLeft(String _aliasRotateLeft) {
        aliasRotateLeft = _aliasRotateLeft;
    }
    public String getAliasRotateRight() {
        return aliasRotateRight;
    }
    public void setAliasRotateRight(String _aliasRotateRight) {
        aliasRotateRight = _aliasRotateRight;
    }

    public String getAliasRandom() {
        return aliasRandom;
    }

    public void setAliasRandom(String _aliasRandom) {
        aliasRandom = _aliasRandom;
    }

    public String getAliasNativeRandom() {
        return aliasNativeRandom;
    }

    public void setAliasNativeRandom(String _aliasNativeRandom) {
        this.aliasNativeRandom = _aliasNativeRandom;
    }

    public String getAliasEval() {
        return aliasEval;
    }

    public void setAliasEval(String _aliasEval) {
        this.aliasEval = _aliasEval;
    }

    public String getAliasSeed() {
        return aliasSeed;
    }

    public void setAliasSeed(String _aliasSeed) {
        this.aliasSeed = _aliasSeed;
    }

    public String getAliasSeedSpecGenerator() {
        return aliasSeedSpecGenerator;
    }

    public void setAliasSeedSpecGenerator(String _aliasSeedSpecGenerator) {
        this.aliasSeedSpecGenerator = _aliasSeedSpecGenerator;
    }

    public String getAliasSeedSpecDoubleGenerator() {
        return aliasSeedSpecDoubleGenerator;
    }

    public void setAliasSeedSpecDoubleGenerator(String _aliasSeedSpecDoubleGenerator) {
        this.aliasSeedSpecDoubleGenerator = _aliasSeedSpecDoubleGenerator;
    }

    public AliasParamMath getParams() {
        return params;
    }
}
