package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamMath {
    private static final String MATH_0_ABS_0="513";
    private static final String MATH_1_ABS_0="514";
    private static final String MATH_2_ABS_0="515";
    private static final String MATH_3_ABS_0="516";
    private static final String MATH_0_MAX_0="517";
    private static final String MATH_0_MAX_1="518";
    private static final String MATH_1_MAX_0="519";
    private static final String MATH_1_MAX_1="520";
    private static final String MATH_0_MIN_0="521";
    private static final String MATH_0_MIN_1="522";
    private static final String MATH_1_MIN_0="523";
    private static final String MATH_1_MIN_1="524";
    private static final String MATH_2_MAX_0="525";
    private static final String MATH_2_MAX_1="526";
    private static final String MATH_3_MAX_0="527";
    private static final String MATH_3_MAX_1="528";
    private static final String MATH_2_MIN_0="529";
    private static final String MATH_2_MIN_1="530";
    private static final String MATH_3_MIN_0="531";
    private static final String MATH_3_MIN_1="532";
    private static final String MATH_0_QUOT_0="533";
    private static final String MATH_0_QUOT_1="534";
    private static final String MATH_1_QUOT_0="535";
    private static final String MATH_1_QUOT_1="536";
    private static final String MATH_0_MOD_0="537";
    private static final String MATH_0_MOD_1="538";
    private static final String MATH_1_MOD_0="539";
    private static final String MATH_1_MOD_1="540";
    private static final String MATH_0_PLUS_0="541";
    private static final String MATH_1_PLUS_0="542";
    private static final String MATH_2_PLUS_0="543";
    private static final String MATH_3_PLUS_0="544";
    private static final String MATH_0_MINUS_0="545";
    private static final String MATH_1_MINUS_0="546";
    private static final String MATH_2_MINUS_0="547";
    private static final String MATH_3_MINUS_0="548";
    private static final String MATH_0_NEG_0="549";
    private static final String MATH_0_NEG_BIN_0="550";
    private static final String MATH_1_NEG_BIN_0="551";
    private static final String MATH_4_PLUS_0="552";
    private static final String MATH_4_PLUS_1="553";
    private static final String MATH_5_PLUS_0="554";
    private static final String MATH_5_PLUS_1="555";
    private static final String MATH_6_PLUS_0="556";
    private static final String MATH_6_PLUS_1="557";
    private static final String MATH_7_PLUS_0="558";
    private static final String MATH_7_PLUS_1="559";
    private static final String MATH_4_MINUS_0="560";
    private static final String MATH_4_MINUS_1="561";
    private static final String MATH_5_MINUS_0="562";
    private static final String MATH_5_MINUS_1="563";
    private static final String MATH_6_MINUS_0="564";
    private static final String MATH_6_MINUS_1="565";
    private static final String MATH_7_MINUS_0="566";
    private static final String MATH_7_MINUS_1="567";
    private static final String MATH_0_MULT_0="568";
    private static final String MATH_0_MULT_1="569";
    private static final String MATH_1_MULT_0="570";
    private static final String MATH_1_MULT_1="571";
    private static final String MATH_2_MULT_0="572";
    private static final String MATH_2_MULT_1="573";
    private static final String MATH_3_MULT_0="574";
    private static final String MATH_3_MULT_1="575";
    private static final String MATH_0_BIN_QUOT_0="576";
    private static final String MATH_0_BIN_QUOT_1="577";
    private static final String MATH_1_BIN_QUOT_0="578";
    private static final String MATH_1_BIN_QUOT_1="579";
    private static final String MATH_2_BIN_QUOT_0="580";
    private static final String MATH_2_BIN_QUOT_1="581";
    private static final String MATH_3_BIN_QUOT_0="582";
    private static final String MATH_3_BIN_QUOT_1="583";
    private static final String MATH_0_BIN_MOD_0="584";
    private static final String MATH_0_BIN_MOD_1="585";
    private static final String MATH_1_BIN_MOD_0="586";
    private static final String MATH_1_BIN_MOD_1="587";
    private static final String MATH_2_BIN_MOD_0="588";
    private static final String MATH_2_BIN_MOD_1="589";
    private static final String MATH_3_BIN_MOD_0="590";
    private static final String MATH_3_BIN_MOD_1="591";
    private static final String MATH_0_AND_0="592";
    private static final String MATH_0_AND_1="593";
    private static final String MATH_1_AND_0="594";
    private static final String MATH_1_AND_1="595";
    private static final String MATH_2_AND_0="596";
    private static final String MATH_2_AND_1="597";
    private static final String MATH_0_OR_0="598";
    private static final String MATH_0_OR_1="599";
    private static final String MATH_1_OR_0="600";
    private static final String MATH_1_OR_1="601";
    private static final String MATH_2_OR_0="602";
    private static final String MATH_2_OR_1="603";
    private static final String MATH_0_XOR_0="604";
    private static final String MATH_0_XOR_1="605";
    private static final String MATH_1_XOR_0="606";
    private static final String MATH_1_XOR_1="607";
    private static final String MATH_2_XOR_0="608";
    private static final String MATH_2_XOR_1="609";
    private static final String MATH_0_SHIFT_LEFT_0="610";
    private static final String MATH_0_SHIFT_LEFT_1="611";
    private static final String MATH_1_SHIFT_LEFT_0="612";
    private static final String MATH_1_SHIFT_LEFT_1="613";
    private static final String MATH_0_SHIFT_RIGHT_0="614";
    private static final String MATH_0_SHIFT_RIGHT_1="615";
    private static final String MATH_1_SHIFT_RIGHT_0="616";
    private static final String MATH_1_SHIFT_RIGHT_1="617";
    private static final String MATH_0_BIT_SHIFT_LEFT_0="618";
    private static final String MATH_0_BIT_SHIFT_LEFT_1="619";
    private static final String MATH_1_BIT_SHIFT_LEFT_0="620";
    private static final String MATH_1_BIT_SHIFT_LEFT_1="621";
    private static final String MATH_0_BIT_SHIFT_RIGHT_0="622";
    private static final String MATH_0_BIT_SHIFT_RIGHT_1="623";
    private static final String MATH_1_BIT_SHIFT_RIGHT_0="624";
    private static final String MATH_1_BIT_SHIFT_RIGHT_1="625";
    private static final String MATH_0_ROTATE_LEFT_0="626";
    private static final String MATH_0_ROTATE_LEFT_1="627";
    private static final String MATH_1_ROTATE_LEFT_0="628";
    private static final String MATH_1_ROTATE_LEFT_1="629";
    private static final String MATH_0_ROTATE_RIGHT_0="630";
    private static final String MATH_0_ROTATE_RIGHT_1="631";
    private static final String MATH_1_ROTATE_RIGHT_0="632";
    private static final String MATH_1_ROTATE_RIGHT_1="633";
    private static final String MATH_0_LE_0="634";
    private static final String MATH_0_LE_1="635";
    private static final String MATH_0_GE_0="636";
    private static final String MATH_0_GE_1="637";
    private static final String MATH_0_LT_0="638";
    private static final String MATH_0_LT_1="639";
    private static final String MATH_0_GT_0="640";
    private static final String MATH_0_GT_1="641";
    private static final String MATH_1_LE_0="642";
    private static final String MATH_1_LE_1="643";
    private static final String MATH_1_GE_0="644";
    private static final String MATH_1_GE_1="645";
    private static final String MATH_1_LT_0="646";
    private static final String MATH_1_LT_1="647";
    private static final String MATH_1_GT_0="648";
    private static final String MATH_1_GT_1="649";
    private static final String MATH_0_RANDOM_0="650";
    private static final String MATH_0_NATIVE_RANDOM_0="651";
    private static final String MATH_0_SEED_0="______1799";
    private static final String MATH_0_SEED_SPEC_GENERATOR_0="______1800";
    private static final String MATH_0_SEED_SPEC_DOUBLE_GENERATOR_0="______1801";
    private static final String MATH_0_EVAL_0="______1802";
    private static final String MATH_0_EVAL_1="______1803";
    private String aliasMath0Abs0="a";
    private String aliasMath1Abs0="a";
    private String aliasMath2Abs0="a";
    private String aliasMath3Abs0="a";
    private String aliasMath0Max0="a";
    private String aliasMath0Max1="b";
    private String aliasMath1Max0="a";
    private String aliasMath1Max1="b";
    private String aliasMath0Min0="a";
    private String aliasMath0Min1="b";
    private String aliasMath1Min0="a";
    private String aliasMath1Min1="b";
    private String aliasMath2Max0="a";
    private String aliasMath2Max1="b";
    private String aliasMath3Max0="a";
    private String aliasMath3Max1="b";
    private String aliasMath2Min0="a";
    private String aliasMath2Min1="b";
    private String aliasMath3Min0="a";
    private String aliasMath3Min1="b";
    private String aliasMath0Quot0="a";
    private String aliasMath0Quot1="b";
    private String aliasMath1Quot0="a";
    private String aliasMath1Quot1="b";
    private String aliasMath0Mod0="a";
    private String aliasMath0Mod1="b";
    private String aliasMath1Mod0="a";
    private String aliasMath1Mod1="b";
    private String aliasMath0Plus0="a";
    private String aliasMath1Plus0="a";
    private String aliasMath2Plus0="a";
    private String aliasMath3Plus0="a";
    private String aliasMath0Minus0="a";
    private String aliasMath1Minus0="a";
    private String aliasMath2Minus0="a";
    private String aliasMath3Minus0="a";
    private String aliasMath0Neg0="a";
    private String aliasMath0NegBin0="a";
    private String aliasMath1NegBin0="a";
    private String aliasMath4Plus0="a";
    private String aliasMath4Plus1="b";
    private String aliasMath5Plus0="a";
    private String aliasMath5Plus1="b";
    private String aliasMath6Plus0="a";
    private String aliasMath6Plus1="b";
    private String aliasMath7Plus0="a";
    private String aliasMath7Plus1="b";
    private String aliasMath4Minus0="a";
    private String aliasMath4Minus1="b";
    private String aliasMath5Minus0="a";
    private String aliasMath5Minus1="b";
    private String aliasMath6Minus0="a";
    private String aliasMath6Minus1="b";
    private String aliasMath7Minus0="a";
    private String aliasMath7Minus1="b";
    private String aliasMath0Mult0="a";
    private String aliasMath0Mult1="b";
    private String aliasMath1Mult0="a";
    private String aliasMath1Mult1="b";
    private String aliasMath2Mult0="a";
    private String aliasMath2Mult1="b";
    private String aliasMath3Mult0="a";
    private String aliasMath3Mult1="b";
    private String aliasMath0BinQuot0="a";
    private String aliasMath0BinQuot1="b";
    private String aliasMath1BinQuot0="a";
    private String aliasMath1BinQuot1="b";
    private String aliasMath2BinQuot0="a";
    private String aliasMath2BinQuot1="b";
    private String aliasMath3BinQuot0="a";
    private String aliasMath3BinQuot1="b";
    private String aliasMath0BinMod0="a";
    private String aliasMath0BinMod1="b";
    private String aliasMath1BinMod0="a";
    private String aliasMath1BinMod1="b";
    private String aliasMath2BinMod0="a";
    private String aliasMath2BinMod1="b";
    private String aliasMath3BinMod0="a";
    private String aliasMath3BinMod1="b";
    private String aliasMath0And0="a";
    private String aliasMath0And1="b";
    private String aliasMath1And0="a";
    private String aliasMath1And1="b";
    private String aliasMath2And0="a";
    private String aliasMath2And1="b";
    private String aliasMath0Or0="a";
    private String aliasMath0Or1="b";
    private String aliasMath1Or0="a";
    private String aliasMath1Or1="b";
    private String aliasMath2Or0="a";
    private String aliasMath2Or1="b";
    private String aliasMath0Xor0="a";
    private String aliasMath0Xor1="b";
    private String aliasMath1Xor0="a";
    private String aliasMath1Xor1="b";
    private String aliasMath2Xor0="a";
    private String aliasMath2Xor1="b";
    private String aliasMath0ShiftLeft0="a";
    private String aliasMath0ShiftLeft1="b";
    private String aliasMath1ShiftLeft0="a";
    private String aliasMath1ShiftLeft1="b";
    private String aliasMath0ShiftRight0="a";
    private String aliasMath0ShiftRight1="b";
    private String aliasMath1ShiftRight0="a";
    private String aliasMath1ShiftRight1="b";
    private String aliasMath0BitShiftLeft0="a";
    private String aliasMath0BitShiftLeft1="b";
    private String aliasMath1BitShiftLeft0="a";
    private String aliasMath1BitShiftLeft1="b";
    private String aliasMath0BitShiftRight0="a";
    private String aliasMath0BitShiftRight1="b";
    private String aliasMath1BitShiftRight0="a";
    private String aliasMath1BitShiftRight1="b";
    private String aliasMath0RotateLeft0="a";
    private String aliasMath0RotateLeft1="b";
    private String aliasMath1RotateLeft0="a";
    private String aliasMath1RotateLeft1="b";
    private String aliasMath0RotateRight0="a";
    private String aliasMath0RotateRight1="b";
    private String aliasMath1RotateRight0="a";
    private String aliasMath1RotateRight1="b";
    private String aliasMath0Le0="a";
    private String aliasMath0Le1="b";
    private String aliasMath0Ge0="a";
    private String aliasMath0Ge1="b";
    private String aliasMath0Lt0="a";
    private String aliasMath0Lt1="b";
    private String aliasMath0Gt0="a";
    private String aliasMath0Gt1="b";
    private String aliasMath1Le0="a";
    private String aliasMath1Le1="b";
    private String aliasMath1Ge0="a";
    private String aliasMath1Ge1="b";
    private String aliasMath1Lt0="a";
    private String aliasMath1Lt1="b";
    private String aliasMath1Gt0="a";
    private String aliasMath1Gt1="b";
    private String aliasMath0Random0="a";
    private String aliasMath0NativeRandom0="a";
    private String aliasMath0Seed0="a";
    private String aliasMath0SeedSpecGenerator0="a";
    private String aliasMath0SeedSpecDoubleGenerator0="a";
    private String aliasMath0Eval0="a";
    private String aliasMath0Eval1="b";

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasMath0Abs0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_ABS_0)));
        setAliasMath1Abs0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_ABS_0)));
        setAliasMath2Abs0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_ABS_0)));
        setAliasMath3Abs0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_ABS_0)));
        setAliasMath0Max0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MAX_0)));
        setAliasMath0Max1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MAX_1)));
        setAliasMath1Max0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MAX_0)));
        setAliasMath1Max1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MAX_1)));
        setAliasMath0Min0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MIN_0)));
        setAliasMath0Min1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MIN_1)));
        setAliasMath1Min0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MIN_0)));
        setAliasMath1Min1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MIN_1)));
        setAliasMath2Max0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MAX_0)));
        setAliasMath2Max1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MAX_1)));
        setAliasMath3Max0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MAX_0)));
        setAliasMath3Max1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MAX_1)));
        setAliasMath2Min0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MIN_0)));
        setAliasMath2Min1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MIN_1)));
        setAliasMath3Min0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MIN_0)));
        setAliasMath3Min1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MIN_1)));
        setAliasMath0Quot0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_QUOT_0)));
        setAliasMath0Quot1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_QUOT_1)));
        setAliasMath1Quot0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_QUOT_0)));
        setAliasMath1Quot1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_QUOT_1)));
        setAliasMath0Mod0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MOD_0)));
        setAliasMath0Mod1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MOD_1)));
        setAliasMath1Mod0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MOD_0)));
        setAliasMath1Mod1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MOD_1)));
        setAliasMath0Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_PLUS_0)));
        setAliasMath1Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_PLUS_0)));
        setAliasMath2Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_PLUS_0)));
        setAliasMath3Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_PLUS_0)));
        setAliasMath0Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MINUS_0)));
        setAliasMath1Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MINUS_0)));
        setAliasMath2Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MINUS_0)));
        setAliasMath3Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MINUS_0)));
        setAliasMath0Neg0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_NEG_0)));
        setAliasMath0NegBin0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_NEG_BIN_0)));
        setAliasMath1NegBin0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_NEG_BIN_0)));
        setAliasMath4Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_4_PLUS_0)));
        setAliasMath4Plus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_4_PLUS_1)));
        setAliasMath5Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_5_PLUS_0)));
        setAliasMath5Plus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_5_PLUS_1)));
        setAliasMath6Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_6_PLUS_0)));
        setAliasMath6Plus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_6_PLUS_1)));
        setAliasMath7Plus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_7_PLUS_0)));
        setAliasMath7Plus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_7_PLUS_1)));
        setAliasMath4Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_4_MINUS_0)));
        setAliasMath4Minus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_4_MINUS_1)));
        setAliasMath5Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_5_MINUS_0)));
        setAliasMath5Minus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_5_MINUS_1)));
        setAliasMath6Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_6_MINUS_0)));
        setAliasMath6Minus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_6_MINUS_1)));
        setAliasMath7Minus0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_7_MINUS_0)));
        setAliasMath7Minus1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_7_MINUS_1)));
        setAliasMath0Mult0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MULT_0)));
        setAliasMath0Mult1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_MULT_1)));
        setAliasMath1Mult0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MULT_0)));
        setAliasMath1Mult1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_MULT_1)));
        setAliasMath2Mult0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MULT_0)));
        setAliasMath2Mult1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_MULT_1)));
        setAliasMath3Mult0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MULT_0)));
        setAliasMath3Mult1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_MULT_1)));
        setAliasMath0BinQuot0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIN_QUOT_0)));
        setAliasMath0BinQuot1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIN_QUOT_1)));
        setAliasMath1BinQuot0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIN_QUOT_0)));
        setAliasMath1BinQuot1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIN_QUOT_1)));
        setAliasMath2BinQuot0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_BIN_QUOT_0)));
        setAliasMath2BinQuot1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_BIN_QUOT_1)));
        setAliasMath3BinQuot0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_BIN_QUOT_0)));
        setAliasMath3BinQuot1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_BIN_QUOT_1)));
        setAliasMath0BinMod0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIN_MOD_0)));
        setAliasMath0BinMod1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIN_MOD_1)));
        setAliasMath1BinMod0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIN_MOD_0)));
        setAliasMath1BinMod1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIN_MOD_1)));
        setAliasMath2BinMod0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_BIN_MOD_0)));
        setAliasMath2BinMod1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_BIN_MOD_1)));
        setAliasMath3BinMod0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_BIN_MOD_0)));
        setAliasMath3BinMod1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_3_BIN_MOD_1)));
        setAliasMath0And0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_AND_0)));
        setAliasMath0And1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_AND_1)));
        setAliasMath1And0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_AND_0)));
        setAliasMath1And1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_AND_1)));
        setAliasMath2And0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_AND_0)));
        setAliasMath2And1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_AND_1)));
        setAliasMath0Or0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_OR_0)));
        setAliasMath0Or1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_OR_1)));
        setAliasMath1Or0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_OR_0)));
        setAliasMath1Or1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_OR_1)));
        setAliasMath2Or0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_OR_0)));
        setAliasMath2Or1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_OR_1)));
        setAliasMath0Xor0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_XOR_0)));
        setAliasMath0Xor1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_XOR_1)));
        setAliasMath1Xor0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_XOR_0)));
        setAliasMath1Xor1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_XOR_1)));
        setAliasMath2Xor0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_XOR_0)));
        setAliasMath2Xor1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_2_XOR_1)));
        setAliasMath0ShiftLeft0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SHIFT_LEFT_0)));
        setAliasMath0ShiftLeft1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SHIFT_LEFT_1)));
        setAliasMath1ShiftLeft0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_SHIFT_LEFT_0)));
        setAliasMath1ShiftLeft1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_SHIFT_LEFT_1)));
        setAliasMath0ShiftRight0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SHIFT_RIGHT_0)));
        setAliasMath0ShiftRight1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SHIFT_RIGHT_1)));
        setAliasMath1ShiftRight0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_SHIFT_RIGHT_0)));
        setAliasMath1ShiftRight1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_SHIFT_RIGHT_1)));
        setAliasMath0BitShiftLeft0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIT_SHIFT_LEFT_0)));
        setAliasMath0BitShiftLeft1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIT_SHIFT_LEFT_1)));
        setAliasMath1BitShiftLeft0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIT_SHIFT_LEFT_0)));
        setAliasMath1BitShiftLeft1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIT_SHIFT_LEFT_1)));
        setAliasMath0BitShiftRight0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIT_SHIFT_RIGHT_0)));
        setAliasMath0BitShiftRight1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_BIT_SHIFT_RIGHT_1)));
        setAliasMath1BitShiftRight0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIT_SHIFT_RIGHT_0)));
        setAliasMath1BitShiftRight1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_BIT_SHIFT_RIGHT_1)));
        setAliasMath0RotateLeft0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_ROTATE_LEFT_0)));
        setAliasMath0RotateLeft1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_ROTATE_LEFT_1)));
        setAliasMath1RotateLeft0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_ROTATE_LEFT_0)));
        setAliasMath1RotateLeft1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_ROTATE_LEFT_1)));
        setAliasMath0RotateRight0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_ROTATE_RIGHT_0)));
        setAliasMath0RotateRight1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_ROTATE_RIGHT_1)));
        setAliasMath1RotateRight0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_ROTATE_RIGHT_0)));
        setAliasMath1RotateRight1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_ROTATE_RIGHT_1)));
        setAliasMath0Le0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_LE_0)));
        setAliasMath0Le1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_LE_1)));
        setAliasMath0Ge0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_GE_0)));
        setAliasMath0Ge1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_GE_1)));
        setAliasMath0Lt0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_LT_0)));
        setAliasMath0Lt1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_LT_1)));
        setAliasMath0Gt0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_GT_0)));
        setAliasMath0Gt1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_GT_1)));
        setAliasMath1Le0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_LE_0)));
        setAliasMath1Le1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_LE_1)));
        setAliasMath1Ge0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_GE_0)));
        setAliasMath1Ge1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_GE_1)));
        setAliasMath1Lt0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_LT_0)));
        setAliasMath1Lt1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_LT_1)));
        setAliasMath1Gt0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_GT_0)));
        setAliasMath1Gt1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_1_GT_1)));
        setAliasMath0Random0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_RANDOM_0)));
        setAliasMath0NativeRandom0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_NATIVE_RANDOM_0)));
        setAliasMath0Seed0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SEED_0)));
        setAliasMath0SeedSpecGenerator0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SEED_SPEC_GENERATOR_0)));
        setAliasMath0SeedSpecDoubleGenerator0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_SEED_SPEC_DOUBLE_GENERATOR_0)));
        setAliasMath0Eval0(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_EVAL_0)));
        setAliasMath0Eval1(LgNamesContent.get(_util,_cust,_mapping.getVal(MATH_0_EVAL_1)));
    }
    public static void en(TranslationsFile _en){
        _en.add(MATH_0_ABS_0,"Math0Abs0=a");
        _en.add(MATH_1_ABS_0,"Math1Abs0=a");
        _en.add(MATH_2_ABS_0,"Math2Abs0=a");
        _en.add(MATH_3_ABS_0,"Math3Abs0=a");
        _en.add(MATH_0_MAX_0,"Math0Max0=a");
        _en.add(MATH_0_MAX_1,"Math0Max1=b");
        _en.add(MATH_1_MAX_0,"Math1Max0=a");
        _en.add(MATH_1_MAX_1,"Math1Max1=b");
        _en.add(MATH_0_MIN_0,"Math0Min0=a");
        _en.add(MATH_0_MIN_1,"Math0Min1=b");
        _en.add(MATH_1_MIN_0,"Math1Min0=a");
        _en.add(MATH_1_MIN_1,"Math1Min1=b");
        _en.add(MATH_2_MAX_0,"Math2Max0=a");
        _en.add(MATH_2_MAX_1,"Math2Max1=b");
        _en.add(MATH_3_MAX_0,"Math3Max0=a");
        _en.add(MATH_3_MAX_1,"Math3Max1=b");
        _en.add(MATH_2_MIN_0,"Math2Min0=a");
        _en.add(MATH_2_MIN_1,"Math2Min1=b");
        _en.add(MATH_3_MIN_0,"Math3Min0=a");
        _en.add(MATH_3_MIN_1,"Math3Min1=b");
        _en.add(MATH_0_QUOT_0,"Math0Quot0=a");
        _en.add(MATH_0_QUOT_1,"Math0Quot1=b");
        _en.add(MATH_1_QUOT_0,"Math1Quot0=a");
        _en.add(MATH_1_QUOT_1,"Math1Quot1=b");
        _en.add(MATH_0_MOD_0,"Math0Mod0=a");
        _en.add(MATH_0_MOD_1,"Math0Mod1=b");
        _en.add(MATH_1_MOD_0,"Math1Mod0=a");
        _en.add(MATH_1_MOD_1,"Math1Mod1=b");
        _en.add(MATH_0_PLUS_0,"Math0Plus0=a");
        _en.add(MATH_1_PLUS_0,"Math1Plus0=a");
        _en.add(MATH_2_PLUS_0,"Math2Plus0=a");
        _en.add(MATH_3_PLUS_0,"Math3Plus0=a");
        _en.add(MATH_0_MINUS_0,"Math0Minus0=a");
        _en.add(MATH_1_MINUS_0,"Math1Minus0=a");
        _en.add(MATH_2_MINUS_0,"Math2Minus0=a");
        _en.add(MATH_3_MINUS_0,"Math3Minus0=a");
        _en.add(MATH_0_NEG_0,"Math0Neg0=a");
        _en.add(MATH_0_NEG_BIN_0,"Math0NegBin0=a");
        _en.add(MATH_1_NEG_BIN_0,"Math1NegBin0=a");
        _en.add(MATH_4_PLUS_0,"Math4Plus0=a");
        _en.add(MATH_4_PLUS_1,"Math4Plus1=b");
        _en.add(MATH_5_PLUS_0,"Math5Plus0=a");
        _en.add(MATH_5_PLUS_1,"Math5Plus1=b");
        _en.add(MATH_6_PLUS_0,"Math6Plus0=a");
        _en.add(MATH_6_PLUS_1,"Math6Plus1=b");
        _en.add(MATH_7_PLUS_0,"Math7Plus0=a");
        _en.add(MATH_7_PLUS_1,"Math7Plus1=b");
        _en.add(MATH_4_MINUS_0,"Math4Minus0=a");
        _en.add(MATH_4_MINUS_1,"Math4Minus1=b");
        _en.add(MATH_5_MINUS_0,"Math5Minus0=a");
        _en.add(MATH_5_MINUS_1,"Math5Minus1=b");
        _en.add(MATH_6_MINUS_0,"Math6Minus0=a");
        _en.add(MATH_6_MINUS_1,"Math6Minus1=b");
        _en.add(MATH_7_MINUS_0,"Math7Minus0=a");
        _en.add(MATH_7_MINUS_1,"Math7Minus1=b");
        _en.add(MATH_0_MULT_0,"Math0Mult0=a");
        _en.add(MATH_0_MULT_1,"Math0Mult1=b");
        _en.add(MATH_1_MULT_0,"Math1Mult0=a");
        _en.add(MATH_1_MULT_1,"Math1Mult1=b");
        _en.add(MATH_2_MULT_0,"Math2Mult0=a");
        _en.add(MATH_2_MULT_1,"Math2Mult1=b");
        _en.add(MATH_3_MULT_0,"Math3Mult0=a");
        _en.add(MATH_3_MULT_1,"Math3Mult1=b");
        _en.add(MATH_0_BIN_QUOT_0,"Math0BinQuot0=a");
        _en.add(MATH_0_BIN_QUOT_1,"Math0BinQuot1=b");
        _en.add(MATH_1_BIN_QUOT_0,"Math1BinQuot0=a");
        _en.add(MATH_1_BIN_QUOT_1,"Math1BinQuot1=b");
        _en.add(MATH_2_BIN_QUOT_0,"Math2BinQuot0=a");
        _en.add(MATH_2_BIN_QUOT_1,"Math2BinQuot1=b");
        _en.add(MATH_3_BIN_QUOT_0,"Math3BinQuot0=a");
        _en.add(MATH_3_BIN_QUOT_1,"Math3BinQuot1=b");
        _en.add(MATH_0_BIN_MOD_0,"Math0BinMod0=a");
        _en.add(MATH_0_BIN_MOD_1,"Math0BinMod1=b");
        _en.add(MATH_1_BIN_MOD_0,"Math1BinMod0=a");
        _en.add(MATH_1_BIN_MOD_1,"Math1BinMod1=b");
        _en.add(MATH_2_BIN_MOD_0,"Math2BinMod0=a");
        _en.add(MATH_2_BIN_MOD_1,"Math2BinMod1=b");
        _en.add(MATH_3_BIN_MOD_0,"Math3BinMod0=a");
        _en.add(MATH_3_BIN_MOD_1,"Math3BinMod1=b");
        _en.add(MATH_0_AND_0,"Math0And0=a");
        _en.add(MATH_0_AND_1,"Math0And1=b");
        _en.add(MATH_1_AND_0,"Math1And0=a");
        _en.add(MATH_1_AND_1,"Math1And1=b");
        _en.add(MATH_2_AND_0,"Math2And0=a");
        _en.add(MATH_2_AND_1,"Math2And1=b");
        _en.add(MATH_0_OR_0,"Math0Or0=a");
        _en.add(MATH_0_OR_1,"Math0Or1=b");
        _en.add(MATH_1_OR_0,"Math1Or0=a");
        _en.add(MATH_1_OR_1,"Math1Or1=b");
        _en.add(MATH_2_OR_0,"Math2Or0=a");
        _en.add(MATH_2_OR_1,"Math2Or1=b");
        _en.add(MATH_0_XOR_0,"Math0Xor0=a");
        _en.add(MATH_0_XOR_1,"Math0Xor1=b");
        _en.add(MATH_1_XOR_0,"Math1Xor0=a");
        _en.add(MATH_1_XOR_1,"Math1Xor1=b");
        _en.add(MATH_2_XOR_0,"Math2Xor0=a");
        _en.add(MATH_2_XOR_1,"Math2Xor1=b");
        _en.add(MATH_0_SHIFT_LEFT_0,"Math0ShiftLeft0=a");
        _en.add(MATH_0_SHIFT_LEFT_1,"Math0ShiftLeft1=b");
        _en.add(MATH_1_SHIFT_LEFT_0,"Math1ShiftLeft0=a");
        _en.add(MATH_1_SHIFT_LEFT_1,"Math1ShiftLeft1=b");
        _en.add(MATH_0_SHIFT_RIGHT_0,"Math0ShiftRight0=a");
        _en.add(MATH_0_SHIFT_RIGHT_1,"Math0ShiftRight1=b");
        _en.add(MATH_1_SHIFT_RIGHT_0,"Math1ShiftRight0=a");
        _en.add(MATH_1_SHIFT_RIGHT_1,"Math1ShiftRight1=b");
        _en.add(MATH_0_BIT_SHIFT_LEFT_0,"Math0BitShiftLeft0=a");
        _en.add(MATH_0_BIT_SHIFT_LEFT_1,"Math0BitShiftLeft1=b");
        _en.add(MATH_1_BIT_SHIFT_LEFT_0,"Math1BitShiftLeft0=a");
        _en.add(MATH_1_BIT_SHIFT_LEFT_1,"Math1BitShiftLeft1=b");
        _en.add(MATH_0_BIT_SHIFT_RIGHT_0,"Math0BitShiftRight0=a");
        _en.add(MATH_0_BIT_SHIFT_RIGHT_1,"Math0BitShiftRight1=b");
        _en.add(MATH_1_BIT_SHIFT_RIGHT_0,"Math1BitShiftRight0=a");
        _en.add(MATH_1_BIT_SHIFT_RIGHT_1,"Math1BitShiftRight1=b");
        _en.add(MATH_0_ROTATE_LEFT_0,"Math0RotateLeft0=a");
        _en.add(MATH_0_ROTATE_LEFT_1,"Math0RotateLeft1=b");
        _en.add(MATH_1_ROTATE_LEFT_0,"Math1RotateLeft0=a");
        _en.add(MATH_1_ROTATE_LEFT_1,"Math1RotateLeft1=b");
        _en.add(MATH_0_ROTATE_RIGHT_0,"Math0RotateRight0=a");
        _en.add(MATH_0_ROTATE_RIGHT_1,"Math0RotateRight1=b");
        _en.add(MATH_1_ROTATE_RIGHT_0,"Math1RotateRight0=a");
        _en.add(MATH_1_ROTATE_RIGHT_1,"Math1RotateRight1=b");
        _en.add(MATH_0_LE_0,"Math0Le0=a");
        _en.add(MATH_0_LE_1,"Math0Le1=b");
        _en.add(MATH_0_GE_0,"Math0Ge0=a");
        _en.add(MATH_0_GE_1,"Math0Ge1=b");
        _en.add(MATH_0_LT_0,"Math0Lt0=a");
        _en.add(MATH_0_LT_1,"Math0Lt1=b");
        _en.add(MATH_0_GT_0,"Math0Gt0=a");
        _en.add(MATH_0_GT_1,"Math0Gt1=b");
        _en.add(MATH_1_LE_0,"Math1Le0=a");
        _en.add(MATH_1_LE_1,"Math1Le1=b");
        _en.add(MATH_1_GE_0,"Math1Ge0=a");
        _en.add(MATH_1_GE_1,"Math1Ge1=b");
        _en.add(MATH_1_LT_0,"Math1Lt0=a");
        _en.add(MATH_1_LT_1,"Math1Lt1=b");
        _en.add(MATH_1_GT_0,"Math1Gt0=a");
        _en.add(MATH_1_GT_1,"Math1Gt1=b");
        _en.add(MATH_0_RANDOM_0,"Math0Random0=a");
        _en.add(MATH_0_NATIVE_RANDOM_0,"Math0NativeRandom0=a");
        _en.add(MATH_0_SEED_0,"Math0Seed0=a");
        _en.add(MATH_0_SEED_SPEC_GENERATOR_0,"Math0SeedSpecGenerator0=a");
        _en.add(MATH_0_SEED_SPEC_DOUBLE_GENERATOR_0,"Math0SeedSpecDoubleGenerator0=a");
        _en.add(MATH_0_EVAL_0,"Math0Eval0=a");
        _en.add(MATH_0_EVAL_1,"Math0Eval1=b");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(MATH_0_ABS_0,"Math0Abs0=a");
        _fr.add(MATH_1_ABS_0,"Math1Abs0=a");
        _fr.add(MATH_2_ABS_0,"Math2Abs0=a");
        _fr.add(MATH_3_ABS_0,"Math3Abs0=a");
        _fr.add(MATH_0_MAX_0,"Math0Max0=a");
        _fr.add(MATH_0_MAX_1,"Math0Max1=b");
        _fr.add(MATH_1_MAX_0,"Math1Max0=a");
        _fr.add(MATH_1_MAX_1,"Math1Max1=b");
        _fr.add(MATH_0_MIN_0,"Math0Min0=a");
        _fr.add(MATH_0_MIN_1,"Math0Min1=b");
        _fr.add(MATH_1_MIN_0,"Math1Min0=a");
        _fr.add(MATH_1_MIN_1,"Math1Min1=b");
        _fr.add(MATH_2_MAX_0,"Math2Max0=a");
        _fr.add(MATH_2_MAX_1,"Math2Max1=b");
        _fr.add(MATH_3_MAX_0,"Math3Max0=a");
        _fr.add(MATH_3_MAX_1,"Math3Max1=b");
        _fr.add(MATH_2_MIN_0,"Math2Min0=a");
        _fr.add(MATH_2_MIN_1,"Math2Min1=b");
        _fr.add(MATH_3_MIN_0,"Math3Min0=a");
        _fr.add(MATH_3_MIN_1,"Math3Min1=b");
        _fr.add(MATH_0_QUOT_0,"Math0Quot0=a");
        _fr.add(MATH_0_QUOT_1,"Math0Quot1=b");
        _fr.add(MATH_1_QUOT_0,"Math1Quot0=a");
        _fr.add(MATH_1_QUOT_1,"Math1Quot1=b");
        _fr.add(MATH_0_MOD_0,"Math0Mod0=a");
        _fr.add(MATH_0_MOD_1,"Math0Mod1=b");
        _fr.add(MATH_1_MOD_0,"Math1Mod0=a");
        _fr.add(MATH_1_MOD_1,"Math1Mod1=b");
        _fr.add(MATH_0_PLUS_0,"Math0Plus0=a");
        _fr.add(MATH_1_PLUS_0,"Math1Plus0=a");
        _fr.add(MATH_2_PLUS_0,"Math2Plus0=a");
        _fr.add(MATH_3_PLUS_0,"Math3Plus0=a");
        _fr.add(MATH_0_MINUS_0,"Math0Minus0=a");
        _fr.add(MATH_1_MINUS_0,"Math1Minus0=a");
        _fr.add(MATH_2_MINUS_0,"Math2Minus0=a");
        _fr.add(MATH_3_MINUS_0,"Math3Minus0=a");
        _fr.add(MATH_0_NEG_0,"Math0Neg0=a");
        _fr.add(MATH_0_NEG_BIN_0,"Math0NegBin0=a");
        _fr.add(MATH_1_NEG_BIN_0,"Math1NegBin0=a");
        _fr.add(MATH_4_PLUS_0,"Math4Plus0=a");
        _fr.add(MATH_4_PLUS_1,"Math4Plus1=b");
        _fr.add(MATH_5_PLUS_0,"Math5Plus0=a");
        _fr.add(MATH_5_PLUS_1,"Math5Plus1=b");
        _fr.add(MATH_6_PLUS_0,"Math6Plus0=a");
        _fr.add(MATH_6_PLUS_1,"Math6Plus1=b");
        _fr.add(MATH_7_PLUS_0,"Math7Plus0=a");
        _fr.add(MATH_7_PLUS_1,"Math7Plus1=b");
        _fr.add(MATH_4_MINUS_0,"Math4Minus0=a");
        _fr.add(MATH_4_MINUS_1,"Math4Minus1=b");
        _fr.add(MATH_5_MINUS_0,"Math5Minus0=a");
        _fr.add(MATH_5_MINUS_1,"Math5Minus1=b");
        _fr.add(MATH_6_MINUS_0,"Math6Minus0=a");
        _fr.add(MATH_6_MINUS_1,"Math6Minus1=b");
        _fr.add(MATH_7_MINUS_0,"Math7Minus0=a");
        _fr.add(MATH_7_MINUS_1,"Math7Minus1=b");
        _fr.add(MATH_0_MULT_0,"Math0Mult0=a");
        _fr.add(MATH_0_MULT_1,"Math0Mult1=b");
        _fr.add(MATH_1_MULT_0,"Math1Mult0=a");
        _fr.add(MATH_1_MULT_1,"Math1Mult1=b");
        _fr.add(MATH_2_MULT_0,"Math2Mult0=a");
        _fr.add(MATH_2_MULT_1,"Math2Mult1=b");
        _fr.add(MATH_3_MULT_0,"Math3Mult0=a");
        _fr.add(MATH_3_MULT_1,"Math3Mult1=b");
        _fr.add(MATH_0_BIN_QUOT_0,"Math0BinQuot0=a");
        _fr.add(MATH_0_BIN_QUOT_1,"Math0BinQuot1=b");
        _fr.add(MATH_1_BIN_QUOT_0,"Math1BinQuot0=a");
        _fr.add(MATH_1_BIN_QUOT_1,"Math1BinQuot1=b");
        _fr.add(MATH_2_BIN_QUOT_0,"Math2BinQuot0=a");
        _fr.add(MATH_2_BIN_QUOT_1,"Math2BinQuot1=b");
        _fr.add(MATH_3_BIN_QUOT_0,"Math3BinQuot0=a");
        _fr.add(MATH_3_BIN_QUOT_1,"Math3BinQuot1=b");
        _fr.add(MATH_0_BIN_MOD_0,"Math0BinMod0=a");
        _fr.add(MATH_0_BIN_MOD_1,"Math0BinMod1=b");
        _fr.add(MATH_1_BIN_MOD_0,"Math1BinMod0=a");
        _fr.add(MATH_1_BIN_MOD_1,"Math1BinMod1=b");
        _fr.add(MATH_2_BIN_MOD_0,"Math2BinMod0=a");
        _fr.add(MATH_2_BIN_MOD_1,"Math2BinMod1=b");
        _fr.add(MATH_3_BIN_MOD_0,"Math3BinMod0=a");
        _fr.add(MATH_3_BIN_MOD_1,"Math3BinMod1=b");
        _fr.add(MATH_0_AND_0,"Math0And0=a");
        _fr.add(MATH_0_AND_1,"Math0And1=b");
        _fr.add(MATH_1_AND_0,"Math1And0=a");
        _fr.add(MATH_1_AND_1,"Math1And1=b");
        _fr.add(MATH_2_AND_0,"Math2And0=a");
        _fr.add(MATH_2_AND_1,"Math2And1=b");
        _fr.add(MATH_0_OR_0,"Math0Or0=a");
        _fr.add(MATH_0_OR_1,"Math0Or1=b");
        _fr.add(MATH_1_OR_0,"Math1Or0=a");
        _fr.add(MATH_1_OR_1,"Math1Or1=b");
        _fr.add(MATH_2_OR_0,"Math2Or0=a");
        _fr.add(MATH_2_OR_1,"Math2Or1=b");
        _fr.add(MATH_0_XOR_0,"Math0Xor0=a");
        _fr.add(MATH_0_XOR_1,"Math0Xor1=b");
        _fr.add(MATH_1_XOR_0,"Math1Xor0=a");
        _fr.add(MATH_1_XOR_1,"Math1Xor1=b");
        _fr.add(MATH_2_XOR_0,"Math2Xor0=a");
        _fr.add(MATH_2_XOR_1,"Math2Xor1=b");
        _fr.add(MATH_0_SHIFT_LEFT_0,"Math0ShiftLeft0=a");
        _fr.add(MATH_0_SHIFT_LEFT_1,"Math0ShiftLeft1=b");
        _fr.add(MATH_1_SHIFT_LEFT_0,"Math1ShiftLeft0=a");
        _fr.add(MATH_1_SHIFT_LEFT_1,"Math1ShiftLeft1=b");
        _fr.add(MATH_0_SHIFT_RIGHT_0,"Math0ShiftRight0=a");
        _fr.add(MATH_0_SHIFT_RIGHT_1,"Math0ShiftRight1=b");
        _fr.add(MATH_1_SHIFT_RIGHT_0,"Math1ShiftRight0=a");
        _fr.add(MATH_1_SHIFT_RIGHT_1,"Math1ShiftRight1=b");
        _fr.add(MATH_0_BIT_SHIFT_LEFT_0,"Math0BitShiftLeft0=a");
        _fr.add(MATH_0_BIT_SHIFT_LEFT_1,"Math0BitShiftLeft1=b");
        _fr.add(MATH_1_BIT_SHIFT_LEFT_0,"Math1BitShiftLeft0=a");
        _fr.add(MATH_1_BIT_SHIFT_LEFT_1,"Math1BitShiftLeft1=b");
        _fr.add(MATH_0_BIT_SHIFT_RIGHT_0,"Math0BitShiftRight0=a");
        _fr.add(MATH_0_BIT_SHIFT_RIGHT_1,"Math0BitShiftRight1=b");
        _fr.add(MATH_1_BIT_SHIFT_RIGHT_0,"Math1BitShiftRight0=a");
        _fr.add(MATH_1_BIT_SHIFT_RIGHT_1,"Math1BitShiftRight1=b");
        _fr.add(MATH_0_ROTATE_LEFT_0,"Math0RotateLeft0=a");
        _fr.add(MATH_0_ROTATE_LEFT_1,"Math0RotateLeft1=b");
        _fr.add(MATH_1_ROTATE_LEFT_0,"Math1RotateLeft0=a");
        _fr.add(MATH_1_ROTATE_LEFT_1,"Math1RotateLeft1=b");
        _fr.add(MATH_0_ROTATE_RIGHT_0,"Math0RotateRight0=a");
        _fr.add(MATH_0_ROTATE_RIGHT_1,"Math0RotateRight1=b");
        _fr.add(MATH_1_ROTATE_RIGHT_0,"Math1RotateRight0=a");
        _fr.add(MATH_1_ROTATE_RIGHT_1,"Math1RotateRight1=b");
        _fr.add(MATH_0_LE_0,"Math0Le0=a");
        _fr.add(MATH_0_LE_1,"Math0Le1=b");
        _fr.add(MATH_0_GE_0,"Math0Ge0=a");
        _fr.add(MATH_0_GE_1,"Math0Ge1=b");
        _fr.add(MATH_0_LT_0,"Math0Lt0=a");
        _fr.add(MATH_0_LT_1,"Math0Lt1=b");
        _fr.add(MATH_0_GT_0,"Math0Gt0=a");
        _fr.add(MATH_0_GT_1,"Math0Gt1=b");
        _fr.add(MATH_1_LE_0,"Math1Le0=a");
        _fr.add(MATH_1_LE_1,"Math1Le1=b");
        _fr.add(MATH_1_GE_0,"Math1Ge0=a");
        _fr.add(MATH_1_GE_1,"Math1Ge1=b");
        _fr.add(MATH_1_LT_0,"Math1Lt0=a");
        _fr.add(MATH_1_LT_1,"Math1Lt1=b");
        _fr.add(MATH_1_GT_0,"Math1Gt0=a");
        _fr.add(MATH_1_GT_1,"Math1Gt1=b");
        _fr.add(MATH_0_RANDOM_0,"Math0Random0=a");
        _fr.add(MATH_0_NATIVE_RANDOM_0,"Math0NativeRandom0=a");
        _fr.add(MATH_0_SEED_0,"Math0Seed0=a");
        _fr.add(MATH_0_SEED_SPEC_GENERATOR_0,"Math0SeedSpecGenerator0=a");
        _fr.add(MATH_0_SEED_SPEC_DOUBLE_GENERATOR_0,"Math0SeedSpecDoubleGenerator0=a");
        _fr.add(MATH_0_EVAL_0,"Math0Eval0=a");
        _fr.add(MATH_0_EVAL_1,"Math0Eval1=b");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(MATH_0_ABS_0,"Math0Abs0");
        _m.addEntry(MATH_1_ABS_0,"Math1Abs0");
        _m.addEntry(MATH_2_ABS_0,"Math2Abs0");
        _m.addEntry(MATH_3_ABS_0,"Math3Abs0");
        _m.addEntry(MATH_0_MAX_0,"Math0Max0");
        _m.addEntry(MATH_0_MAX_1,"Math0Max1");
        _m.addEntry(MATH_1_MAX_0,"Math1Max0");
        _m.addEntry(MATH_1_MAX_1,"Math1Max1");
        _m.addEntry(MATH_0_MIN_0,"Math0Min0");
        _m.addEntry(MATH_0_MIN_1,"Math0Min1");
        _m.addEntry(MATH_1_MIN_0,"Math1Min0");
        _m.addEntry(MATH_1_MIN_1,"Math1Min1");
        _m.addEntry(MATH_2_MAX_0,"Math2Max0");
        _m.addEntry(MATH_2_MAX_1,"Math2Max1");
        _m.addEntry(MATH_3_MAX_0,"Math3Max0");
        _m.addEntry(MATH_3_MAX_1,"Math3Max1");
        _m.addEntry(MATH_2_MIN_0,"Math2Min0");
        _m.addEntry(MATH_2_MIN_1,"Math2Min1");
        _m.addEntry(MATH_3_MIN_0,"Math3Min0");
        _m.addEntry(MATH_3_MIN_1,"Math3Min1");
        _m.addEntry(MATH_0_QUOT_0,"Math0Quot0");
        _m.addEntry(MATH_0_QUOT_1,"Math0Quot1");
        _m.addEntry(MATH_1_QUOT_0,"Math1Quot0");
        _m.addEntry(MATH_1_QUOT_1,"Math1Quot1");
        _m.addEntry(MATH_0_MOD_0,"Math0Mod0");
        _m.addEntry(MATH_0_MOD_1,"Math0Mod1");
        _m.addEntry(MATH_1_MOD_0,"Math1Mod0");
        _m.addEntry(MATH_1_MOD_1,"Math1Mod1");
        _m.addEntry(MATH_0_PLUS_0,"Math0Plus0");
        _m.addEntry(MATH_1_PLUS_0,"Math1Plus0");
        _m.addEntry(MATH_2_PLUS_0,"Math2Plus0");
        _m.addEntry(MATH_3_PLUS_0,"Math3Plus0");
        _m.addEntry(MATH_0_MINUS_0,"Math0Minus0");
        _m.addEntry(MATH_1_MINUS_0,"Math1Minus0");
        _m.addEntry(MATH_2_MINUS_0,"Math2Minus0");
        _m.addEntry(MATH_3_MINUS_0,"Math3Minus0");
        _m.addEntry(MATH_0_NEG_0,"Math0Neg0");
        _m.addEntry(MATH_0_NEG_BIN_0,"Math0NegBin0");
        _m.addEntry(MATH_1_NEG_BIN_0,"Math1NegBin0");
        _m.addEntry(MATH_4_PLUS_0,"Math4Plus0");
        _m.addEntry(MATH_4_PLUS_1,"Math4Plus1");
        _m.addEntry(MATH_5_PLUS_0,"Math5Plus0");
        _m.addEntry(MATH_5_PLUS_1,"Math5Plus1");
        _m.addEntry(MATH_6_PLUS_0,"Math6Plus0");
        _m.addEntry(MATH_6_PLUS_1,"Math6Plus1");
        _m.addEntry(MATH_7_PLUS_0,"Math7Plus0");
        _m.addEntry(MATH_7_PLUS_1,"Math7Plus1");
        _m.addEntry(MATH_4_MINUS_0,"Math4Minus0");
        _m.addEntry(MATH_4_MINUS_1,"Math4Minus1");
        _m.addEntry(MATH_5_MINUS_0,"Math5Minus0");
        _m.addEntry(MATH_5_MINUS_1,"Math5Minus1");
        _m.addEntry(MATH_6_MINUS_0,"Math6Minus0");
        _m.addEntry(MATH_6_MINUS_1,"Math6Minus1");
        _m.addEntry(MATH_7_MINUS_0,"Math7Minus0");
        _m.addEntry(MATH_7_MINUS_1,"Math7Minus1");
        _m.addEntry(MATH_0_MULT_0,"Math0Mult0");
        _m.addEntry(MATH_0_MULT_1,"Math0Mult1");
        _m.addEntry(MATH_1_MULT_0,"Math1Mult0");
        _m.addEntry(MATH_1_MULT_1,"Math1Mult1");
        _m.addEntry(MATH_2_MULT_0,"Math2Mult0");
        _m.addEntry(MATH_2_MULT_1,"Math2Mult1");
        _m.addEntry(MATH_3_MULT_0,"Math3Mult0");
        _m.addEntry(MATH_3_MULT_1,"Math3Mult1");
        _m.addEntry(MATH_0_BIN_QUOT_0,"Math0BinQuot0");
        _m.addEntry(MATH_0_BIN_QUOT_1,"Math0BinQuot1");
        _m.addEntry(MATH_1_BIN_QUOT_0,"Math1BinQuot0");
        _m.addEntry(MATH_1_BIN_QUOT_1,"Math1BinQuot1");
        _m.addEntry(MATH_2_BIN_QUOT_0,"Math2BinQuot0");
        _m.addEntry(MATH_2_BIN_QUOT_1,"Math2BinQuot1");
        _m.addEntry(MATH_3_BIN_QUOT_0,"Math3BinQuot0");
        _m.addEntry(MATH_3_BIN_QUOT_1,"Math3BinQuot1");
        _m.addEntry(MATH_0_BIN_MOD_0,"Math0BinMod0");
        _m.addEntry(MATH_0_BIN_MOD_1,"Math0BinMod1");
        _m.addEntry(MATH_1_BIN_MOD_0,"Math1BinMod0");
        _m.addEntry(MATH_1_BIN_MOD_1,"Math1BinMod1");
        _m.addEntry(MATH_2_BIN_MOD_0,"Math2BinMod0");
        _m.addEntry(MATH_2_BIN_MOD_1,"Math2BinMod1");
        _m.addEntry(MATH_3_BIN_MOD_0,"Math3BinMod0");
        _m.addEntry(MATH_3_BIN_MOD_1,"Math3BinMod1");
        _m.addEntry(MATH_0_AND_0,"Math0And0");
        _m.addEntry(MATH_0_AND_1,"Math0And1");
        _m.addEntry(MATH_1_AND_0,"Math1And0");
        _m.addEntry(MATH_1_AND_1,"Math1And1");
        _m.addEntry(MATH_2_AND_0,"Math2And0");
        _m.addEntry(MATH_2_AND_1,"Math2And1");
        _m.addEntry(MATH_0_OR_0,"Math0Or0");
        _m.addEntry(MATH_0_OR_1,"Math0Or1");
        _m.addEntry(MATH_1_OR_0,"Math1Or0");
        _m.addEntry(MATH_1_OR_1,"Math1Or1");
        _m.addEntry(MATH_2_OR_0,"Math2Or0");
        _m.addEntry(MATH_2_OR_1,"Math2Or1");
        _m.addEntry(MATH_0_XOR_0,"Math0Xor0");
        _m.addEntry(MATH_0_XOR_1,"Math0Xor1");
        _m.addEntry(MATH_1_XOR_0,"Math1Xor0");
        _m.addEntry(MATH_1_XOR_1,"Math1Xor1");
        _m.addEntry(MATH_2_XOR_0,"Math2Xor0");
        _m.addEntry(MATH_2_XOR_1,"Math2Xor1");
        _m.addEntry(MATH_0_SHIFT_LEFT_0,"Math0ShiftLeft0");
        _m.addEntry(MATH_0_SHIFT_LEFT_1,"Math0ShiftLeft1");
        _m.addEntry(MATH_1_SHIFT_LEFT_0,"Math1ShiftLeft0");
        _m.addEntry(MATH_1_SHIFT_LEFT_1,"Math1ShiftLeft1");
        _m.addEntry(MATH_0_SHIFT_RIGHT_0,"Math0ShiftRight0");
        _m.addEntry(MATH_0_SHIFT_RIGHT_1,"Math0ShiftRight1");
        _m.addEntry(MATH_1_SHIFT_RIGHT_0,"Math1ShiftRight0");
        _m.addEntry(MATH_1_SHIFT_RIGHT_1,"Math1ShiftRight1");
        _m.addEntry(MATH_0_BIT_SHIFT_LEFT_0,"Math0BitShiftLeft0");
        _m.addEntry(MATH_0_BIT_SHIFT_LEFT_1,"Math0BitShiftLeft1");
        _m.addEntry(MATH_1_BIT_SHIFT_LEFT_0,"Math1BitShiftLeft0");
        _m.addEntry(MATH_1_BIT_SHIFT_LEFT_1,"Math1BitShiftLeft1");
        _m.addEntry(MATH_0_BIT_SHIFT_RIGHT_0,"Math0BitShiftRight0");
        _m.addEntry(MATH_0_BIT_SHIFT_RIGHT_1,"Math0BitShiftRight1");
        _m.addEntry(MATH_1_BIT_SHIFT_RIGHT_0,"Math1BitShiftRight0");
        _m.addEntry(MATH_1_BIT_SHIFT_RIGHT_1,"Math1BitShiftRight1");
        _m.addEntry(MATH_0_ROTATE_LEFT_0,"Math0RotateLeft0");
        _m.addEntry(MATH_0_ROTATE_LEFT_1,"Math0RotateLeft1");
        _m.addEntry(MATH_1_ROTATE_LEFT_0,"Math1RotateLeft0");
        _m.addEntry(MATH_1_ROTATE_LEFT_1,"Math1RotateLeft1");
        _m.addEntry(MATH_0_ROTATE_RIGHT_0,"Math0RotateRight0");
        _m.addEntry(MATH_0_ROTATE_RIGHT_1,"Math0RotateRight1");
        _m.addEntry(MATH_1_ROTATE_RIGHT_0,"Math1RotateRight0");
        _m.addEntry(MATH_1_ROTATE_RIGHT_1,"Math1RotateRight1");
        _m.addEntry(MATH_0_LE_0,"Math0Le0");
        _m.addEntry(MATH_0_LE_1,"Math0Le1");
        _m.addEntry(MATH_0_GE_0,"Math0Ge0");
        _m.addEntry(MATH_0_GE_1,"Math0Ge1");
        _m.addEntry(MATH_0_LT_0,"Math0Lt0");
        _m.addEntry(MATH_0_LT_1,"Math0Lt1");
        _m.addEntry(MATH_0_GT_0,"Math0Gt0");
        _m.addEntry(MATH_0_GT_1,"Math0Gt1");
        _m.addEntry(MATH_1_LE_0,"Math1Le0");
        _m.addEntry(MATH_1_LE_1,"Math1Le1");
        _m.addEntry(MATH_1_GE_0,"Math1Ge0");
        _m.addEntry(MATH_1_GE_1,"Math1Ge1");
        _m.addEntry(MATH_1_LT_0,"Math1Lt0");
        _m.addEntry(MATH_1_LT_1,"Math1Lt1");
        _m.addEntry(MATH_1_GT_0,"Math1Gt0");
        _m.addEntry(MATH_1_GT_1,"Math1Gt1");
        _m.addEntry(MATH_0_RANDOM_0,"Math0Random0");
        _m.addEntry(MATH_0_NATIVE_RANDOM_0,"Math0NativeRandom0");
        _m.addEntry(MATH_0_SEED_0,"Math0Seed0");
        _m.addEntry(MATH_0_SEED_SPEC_GENERATOR_0,"Math0SeedSpecGenerator0");
        _m.addEntry(MATH_0_SEED_SPEC_DOUBLE_GENERATOR_0,"Math0SeedSpecDoubleGenerator0");
        _m.addEntry(MATH_0_EVAL_0,"Math0Eval0");
        _m.addEntry(MATH_0_EVAL_1,"Math0Eval1");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_ABS_0), getAliasMath0Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_ABS_0), getAliasMath1Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_ABS_0), getAliasMath2Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_ABS_0), getAliasMath3Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_MAX_0), getAliasMath0Max0()),new KeyValueMemberName(_mapping.getVal(MATH_0_MAX_1), getAliasMath0Max1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_MAX_0), getAliasMath1Max0()),new KeyValueMemberName(_mapping.getVal(MATH_1_MAX_1), getAliasMath1Max1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_MIN_0), getAliasMath0Min0()),new KeyValueMemberName(_mapping.getVal(MATH_0_MIN_1), getAliasMath0Min1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_MIN_0), getAliasMath1Min0()),new KeyValueMemberName(_mapping.getVal(MATH_1_MIN_1), getAliasMath1Min1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_MAX_0), getAliasMath2Max0()),new KeyValueMemberName(_mapping.getVal(MATH_2_MAX_1), getAliasMath2Max1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_MAX_0), getAliasMath3Max0()),new KeyValueMemberName(_mapping.getVal(MATH_3_MAX_1), getAliasMath3Max1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_MIN_0), getAliasMath2Min0()),new KeyValueMemberName(_mapping.getVal(MATH_2_MIN_1), getAliasMath2Min1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_MIN_0), getAliasMath3Min0()),new KeyValueMemberName(_mapping.getVal(MATH_3_MIN_1), getAliasMath3Min1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_QUOT_0), getAliasMath0Quot0()),new KeyValueMemberName(_mapping.getVal(MATH_0_QUOT_1), getAliasMath0Quot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_QUOT_0), getAliasMath1Quot0()),new KeyValueMemberName(_mapping.getVal(MATH_1_QUOT_1), getAliasMath1Quot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_MOD_0), getAliasMath0Mod0()),new KeyValueMemberName(_mapping.getVal(MATH_0_MOD_1), getAliasMath0Mod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_MOD_0), getAliasMath1Mod0()),new KeyValueMemberName(_mapping.getVal(MATH_1_MOD_1), getAliasMath1Mod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_PLUS_0), getAliasMath0Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_PLUS_0), getAliasMath1Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_PLUS_0), getAliasMath2Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_PLUS_0), getAliasMath3Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_MINUS_0), getAliasMath0Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_MINUS_0), getAliasMath1Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_MINUS_0), getAliasMath2Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_MINUS_0), getAliasMath3Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_NEG_0), getAliasMath0Neg0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_NEG_BIN_0), getAliasMath0NegBin0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_NEG_BIN_0), getAliasMath1NegBin0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_4_PLUS_0), getAliasMath4Plus0()),new KeyValueMemberName(_mapping.getVal(MATH_4_PLUS_1), getAliasMath4Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_5_PLUS_0), getAliasMath5Plus0()),new KeyValueMemberName(_mapping.getVal(MATH_5_PLUS_1), getAliasMath5Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_6_PLUS_0), getAliasMath6Plus0()),new KeyValueMemberName(_mapping.getVal(MATH_6_PLUS_1), getAliasMath6Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_7_PLUS_0), getAliasMath7Plus0()),new KeyValueMemberName(_mapping.getVal(MATH_7_PLUS_1), getAliasMath7Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_4_MINUS_0), getAliasMath4Minus0()),new KeyValueMemberName(_mapping.getVal(MATH_4_MINUS_1), getAliasMath4Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_5_MINUS_0), getAliasMath5Minus0()),new KeyValueMemberName(_mapping.getVal(MATH_5_MINUS_1), getAliasMath5Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_6_MINUS_0), getAliasMath6Minus0()),new KeyValueMemberName(_mapping.getVal(MATH_6_MINUS_1), getAliasMath6Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_7_MINUS_0), getAliasMath7Minus0()),new KeyValueMemberName(_mapping.getVal(MATH_7_MINUS_1), getAliasMath7Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_MULT_0), getAliasMath0Mult0()),new KeyValueMemberName(_mapping.getVal(MATH_0_MULT_1), getAliasMath0Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_MULT_0), getAliasMath1Mult0()),new KeyValueMemberName(_mapping.getVal(MATH_1_MULT_1), getAliasMath1Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_MULT_0), getAliasMath2Mult0()),new KeyValueMemberName(_mapping.getVal(MATH_2_MULT_1), getAliasMath2Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_MULT_0), getAliasMath3Mult0()),new KeyValueMemberName(_mapping.getVal(MATH_3_MULT_1), getAliasMath3Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_BIN_QUOT_0), getAliasMath0BinQuot0()),new KeyValueMemberName(_mapping.getVal(MATH_0_BIN_QUOT_1), getAliasMath0BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_BIN_QUOT_0), getAliasMath1BinQuot0()),new KeyValueMemberName(_mapping.getVal(MATH_1_BIN_QUOT_1), getAliasMath1BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_BIN_QUOT_0), getAliasMath2BinQuot0()),new KeyValueMemberName(_mapping.getVal(MATH_2_BIN_QUOT_1), getAliasMath2BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_BIN_QUOT_0), getAliasMath3BinQuot0()),new KeyValueMemberName(_mapping.getVal(MATH_3_BIN_QUOT_1), getAliasMath3BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_BIN_MOD_0), getAliasMath0BinMod0()),new KeyValueMemberName(_mapping.getVal(MATH_0_BIN_MOD_1), getAliasMath0BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_BIN_MOD_0), getAliasMath1BinMod0()),new KeyValueMemberName(_mapping.getVal(MATH_1_BIN_MOD_1), getAliasMath1BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_BIN_MOD_0), getAliasMath2BinMod0()),new KeyValueMemberName(_mapping.getVal(MATH_2_BIN_MOD_1), getAliasMath2BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_3_BIN_MOD_0), getAliasMath3BinMod0()),new KeyValueMemberName(_mapping.getVal(MATH_3_BIN_MOD_1), getAliasMath3BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_AND_0), getAliasMath0And0()),new KeyValueMemberName(_mapping.getVal(MATH_0_AND_1), getAliasMath0And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_AND_0), getAliasMath1And0()),new KeyValueMemberName(_mapping.getVal(MATH_1_AND_1), getAliasMath1And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_AND_0), getAliasMath2And0()),new KeyValueMemberName(_mapping.getVal(MATH_2_AND_1), getAliasMath2And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_OR_0), getAliasMath0Or0()),new KeyValueMemberName(_mapping.getVal(MATH_0_OR_1), getAliasMath0Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_OR_0), getAliasMath1Or0()),new KeyValueMemberName(_mapping.getVal(MATH_1_OR_1), getAliasMath1Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_OR_0), getAliasMath2Or0()),new KeyValueMemberName(_mapping.getVal(MATH_2_OR_1), getAliasMath2Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_XOR_0), getAliasMath0Xor0()),new KeyValueMemberName(_mapping.getVal(MATH_0_XOR_1), getAliasMath0Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_XOR_0), getAliasMath1Xor0()),new KeyValueMemberName(_mapping.getVal(MATH_1_XOR_1), getAliasMath1Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_2_XOR_0), getAliasMath2Xor0()),new KeyValueMemberName(_mapping.getVal(MATH_2_XOR_1), getAliasMath2Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_SHIFT_LEFT_0), getAliasMath0ShiftLeft0()),new KeyValueMemberName(_mapping.getVal(MATH_0_SHIFT_LEFT_1), getAliasMath0ShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_SHIFT_LEFT_0), getAliasMath1ShiftLeft0()),new KeyValueMemberName(_mapping.getVal(MATH_1_SHIFT_LEFT_1), getAliasMath1ShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_SHIFT_RIGHT_0), getAliasMath0ShiftRight0()),new KeyValueMemberName(_mapping.getVal(MATH_0_SHIFT_RIGHT_1), getAliasMath0ShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_SHIFT_RIGHT_0), getAliasMath1ShiftRight0()),new KeyValueMemberName(_mapping.getVal(MATH_1_SHIFT_RIGHT_1), getAliasMath1ShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_BIT_SHIFT_LEFT_0), getAliasMath0BitShiftLeft0()),new KeyValueMemberName(_mapping.getVal(MATH_0_BIT_SHIFT_LEFT_1), getAliasMath0BitShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_BIT_SHIFT_LEFT_0), getAliasMath1BitShiftLeft0()),new KeyValueMemberName(_mapping.getVal(MATH_1_BIT_SHIFT_LEFT_1), getAliasMath1BitShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_BIT_SHIFT_RIGHT_0), getAliasMath0BitShiftRight0()),new KeyValueMemberName(_mapping.getVal(MATH_0_BIT_SHIFT_RIGHT_1), getAliasMath0BitShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_BIT_SHIFT_RIGHT_0), getAliasMath1BitShiftRight0()),new KeyValueMemberName(_mapping.getVal(MATH_1_BIT_SHIFT_RIGHT_1), getAliasMath1BitShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_ROTATE_LEFT_0), getAliasMath0RotateLeft0()),new KeyValueMemberName(_mapping.getVal(MATH_0_ROTATE_LEFT_1), getAliasMath0RotateLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_ROTATE_LEFT_0), getAliasMath1RotateLeft0()),new KeyValueMemberName(_mapping.getVal(MATH_1_ROTATE_LEFT_1), getAliasMath1RotateLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_ROTATE_RIGHT_0), getAliasMath0RotateRight0()),new KeyValueMemberName(_mapping.getVal(MATH_0_ROTATE_RIGHT_1), getAliasMath0RotateRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_ROTATE_RIGHT_0), getAliasMath1RotateRight0()),new KeyValueMemberName(_mapping.getVal(MATH_1_ROTATE_RIGHT_1), getAliasMath1RotateRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_LE_0), getAliasMath0Le0()),new KeyValueMemberName(_mapping.getVal(MATH_0_LE_1), getAliasMath0Le1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_GE_0), getAliasMath0Ge0()),new KeyValueMemberName(_mapping.getVal(MATH_0_GE_1), getAliasMath0Ge1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_LT_0), getAliasMath0Lt0()),new KeyValueMemberName(_mapping.getVal(MATH_0_LT_1), getAliasMath0Lt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_GT_0), getAliasMath0Gt0()),new KeyValueMemberName(_mapping.getVal(MATH_0_GT_1), getAliasMath0Gt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_LE_0), getAliasMath1Le0()),new KeyValueMemberName(_mapping.getVal(MATH_1_LE_1), getAliasMath1Le1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_GE_0), getAliasMath1Ge0()),new KeyValueMemberName(_mapping.getVal(MATH_1_GE_1), getAliasMath1Ge1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_LT_0), getAliasMath1Lt0()),new KeyValueMemberName(_mapping.getVal(MATH_1_LT_1), getAliasMath1Lt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_1_GT_0), getAliasMath1Gt0()),new KeyValueMemberName(_mapping.getVal(MATH_1_GT_1), getAliasMath1Gt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_RANDOM_0), getAliasMath0Random0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_NATIVE_RANDOM_0), getAliasMath0NativeRandom0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_SEED_0), getAliasMath0Seed0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_SEED_SPEC_GENERATOR_0), getAliasMath0SeedSpecGenerator0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_SEED_SPEC_DOUBLE_GENERATOR_0), getAliasMath0SeedSpecDoubleGenerator0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MATH_0_EVAL_0), getAliasMath0Eval0()),new KeyValueMemberName(_mapping.getVal(MATH_0_EVAL_1), getAliasMath0Eval1())));
        return map_;
    }
    public String getAliasMath0Abs0() {
        return aliasMath0Abs0;
    }

    public void setAliasMath0Abs0(String _v) {
        this.aliasMath0Abs0 =_v;
    }

    public String getAliasMath1Abs0() {
        return aliasMath1Abs0;
    }

    public void setAliasMath1Abs0(String _v) {
        this.aliasMath1Abs0 =_v;
    }

    public String getAliasMath2Abs0() {
        return aliasMath2Abs0;
    }

    public void setAliasMath2Abs0(String _v) {
        this.aliasMath2Abs0 = _v;
    }

    public String getAliasMath3Abs0() {
        return aliasMath3Abs0;
    }

    public void setAliasMath3Abs0(String _v) {
        this.aliasMath3Abs0 = _v;
    }

    public String getAliasMath0Max0() {
        return aliasMath0Max0;
    }

    public void setAliasMath0Max0(String _v) {
        this.aliasMath0Max0 = _v;
    }

    public String getAliasMath0Max1() {
        return aliasMath0Max1;
    }

    public void setAliasMath0Max1(String _v) {
        this.aliasMath0Max1 = _v;
    }

    public String getAliasMath1Max0() {
        return aliasMath1Max0;
    }

    public void setAliasMath1Max0(String _v) {
        this.aliasMath1Max0 = _v;
    }

    public String getAliasMath1Max1() {
        return aliasMath1Max1;
    }

    public void setAliasMath1Max1(String _v) {
        this.aliasMath1Max1 = _v;
    }

    public String getAliasMath0Min0() {
        return aliasMath0Min0;
    }

    public void setAliasMath0Min0(String _v) {
        this.aliasMath0Min0 = _v;
    }

    public String getAliasMath0Min1() {
        return aliasMath0Min1;
    }

    public void setAliasMath0Min1(String _v) {
        this.aliasMath0Min1 = _v;
    }

    public String getAliasMath1Min0() {
        return aliasMath1Min0;
    }

    public void setAliasMath1Min0(String _v) {
        this.aliasMath1Min0 = _v;
    }

    public String getAliasMath1Min1() {
        return aliasMath1Min1;
    }

    public void setAliasMath1Min1(String _v) {
        this.aliasMath1Min1 = _v;
    }

    public String getAliasMath2Max0() {
        return aliasMath2Max0;
    }

    public void setAliasMath2Max0(String _v) {
        this.aliasMath2Max0 = _v;
    }

    public String getAliasMath2Max1() {
        return aliasMath2Max1;
    }

    public void setAliasMath2Max1(String _v) {
        this.aliasMath2Max1 = _v;
    }

    public String getAliasMath3Max0() {
        return aliasMath3Max0;
    }

    public void setAliasMath3Max0(String _v) {
        this.aliasMath3Max0 = _v;
    }

    public String getAliasMath3Max1() {
        return aliasMath3Max1;
    }

    public void setAliasMath3Max1(String _v) {
        this.aliasMath3Max1 = _v;
    }

    public String getAliasMath2Min0() {
        return aliasMath2Min0;
    }

    public void setAliasMath2Min0(String _v) {
        this.aliasMath2Min0 = _v;
    }

    public String getAliasMath2Min1() {
        return aliasMath2Min1;
    }

    public void setAliasMath2Min1(String _v) {
        this.aliasMath2Min1 = _v;
    }

    public String getAliasMath3Min0() {
        return aliasMath3Min0;
    }

    public void setAliasMath3Min0(String _v) {
        this.aliasMath3Min0 = _v;
    }

    public String getAliasMath3Min1() {
        return aliasMath3Min1;
    }

    public void setAliasMath3Min1(String _v) {
        this.aliasMath3Min1 = _v;
    }

    public String getAliasMath0Quot0() {
        return aliasMath0Quot0;
    }

    public void setAliasMath0Quot0(String _v) {
        this.aliasMath0Quot0 =_v;
    }

    public String getAliasMath0Quot1() {
        return aliasMath0Quot1;
    }

    public void setAliasMath0Quot1(String _v) {
        this.aliasMath0Quot1 =_v;
    }

    public String getAliasMath1Quot0() {
        return aliasMath1Quot0;
    }

    public void setAliasMath1Quot0(String _v) {
        this.aliasMath1Quot0 =_v;
    }

    public String getAliasMath1Quot1() {
        return aliasMath1Quot1;
    }

    public void setAliasMath1Quot1(String _v) {
        this.aliasMath1Quot1 =_v;
    }

    public String getAliasMath0Mod0() {
        return aliasMath0Mod0;
    }

    public void setAliasMath0Mod0(String _v) {
        this.aliasMath0Mod0 =_v;
    }

    public String getAliasMath0Mod1() {
        return aliasMath0Mod1;
    }

    public void setAliasMath0Mod1(String _v) {
        this.aliasMath0Mod1 =_v;
    }

    public String getAliasMath1Mod0() {
        return aliasMath1Mod0;
    }

    public void setAliasMath1Mod0(String _v) {
        this.aliasMath1Mod0 =_v;
    }

    public String getAliasMath1Mod1() {
        return aliasMath1Mod1;
    }

    public void setAliasMath1Mod1(String _v) {
        this.aliasMath1Mod1 =_v;
    }

    public String getAliasMath0Plus0() {
        return aliasMath0Plus0;
    }

    public void setAliasMath0Plus0(String _v) {
        this.aliasMath0Plus0 =_v;
    }

    public String getAliasMath1Plus0() {
        return aliasMath1Plus0;
    }

    public void setAliasMath1Plus0(String _v) {
        this.aliasMath1Plus0 =_v;
    }

    public String getAliasMath2Plus0() {
        return aliasMath2Plus0;
    }

    public void setAliasMath2Plus0(String _v) {
        this.aliasMath2Plus0 =_v;
    }

    public String getAliasMath3Plus0() {
        return aliasMath3Plus0;
    }

    public void setAliasMath3Plus0(String _v) {
        this.aliasMath3Plus0 =_v;
    }

    public String getAliasMath0Minus0() {
        return aliasMath0Minus0;
    }

    public void setAliasMath0Minus0(String _v) {
        this.aliasMath0Minus0 =_v;
    }

    public String getAliasMath1Minus0() {
        return aliasMath1Minus0;
    }

    public void setAliasMath1Minus0(String _v) {
        this.aliasMath1Minus0 =_v;
    }

    public String getAliasMath2Minus0() {
        return aliasMath2Minus0;
    }

    public void setAliasMath2Minus0(String _v) {
        this.aliasMath2Minus0 =_v;
    }

    public String getAliasMath3Minus0() {
        return aliasMath3Minus0;
    }

    public void setAliasMath3Minus0(String _v) {
        this.aliasMath3Minus0 =_v;
    }

    public String getAliasMath0Neg0() {
        return aliasMath0Neg0;
    }

    public void setAliasMath0Neg0(String _v) {
        this.aliasMath0Neg0 =_v;
    }

    public String getAliasMath0NegBin0() {
        return aliasMath0NegBin0;
    }

    public void setAliasMath0NegBin0(String _v) {
        this.aliasMath0NegBin0 =_v;
    }

    public String getAliasMath1NegBin0() {
        return aliasMath1NegBin0;
    }

    public void setAliasMath1NegBin0(String _v) {
        this.aliasMath1NegBin0 =_v;
    }

    public String getAliasMath4Plus0() {
        return aliasMath4Plus0;
    }

    public void setAliasMath4Plus0(String _v) {
        this.aliasMath4Plus0 =_v;
    }

    public String getAliasMath4Plus1() {
        return aliasMath4Plus1;
    }

    public void setAliasMath4Plus1(String _v) {
        this.aliasMath4Plus1 =_v;
    }

    public String getAliasMath5Plus0() {
        return aliasMath5Plus0;
    }

    public void setAliasMath5Plus0(String _v) {
        this.aliasMath5Plus0 =_v;
    }

    public String getAliasMath5Plus1() {
        return aliasMath5Plus1;
    }

    public void setAliasMath5Plus1(String _v) {
        this.aliasMath5Plus1 =_v;
    }

    public String getAliasMath6Plus0() {
        return aliasMath6Plus0;
    }

    public void setAliasMath6Plus0(String _v) {
        this.aliasMath6Plus0 =_v;
    }

    public String getAliasMath6Plus1() {
        return aliasMath6Plus1;
    }

    public void setAliasMath6Plus1(String _v) {
        this.aliasMath6Plus1 =_v;
    }

    public String getAliasMath7Plus0() {
        return aliasMath7Plus0;
    }

    public void setAliasMath7Plus0(String _v) {
        this.aliasMath7Plus0 =_v;
    }

    public String getAliasMath7Plus1() {
        return aliasMath7Plus1;
    }

    public void setAliasMath7Plus1(String _v) {
        this.aliasMath7Plus1 =_v;
    }

    public String getAliasMath4Minus0() {
        return aliasMath4Minus0;
    }

    public void setAliasMath4Minus0(String _v) {
        this.aliasMath4Minus0 =_v;
    }

    public String getAliasMath4Minus1() {
        return aliasMath4Minus1;
    }

    public void setAliasMath4Minus1(String _v) {
        this.aliasMath4Minus1 =_v;
    }

    public String getAliasMath5Minus0() {
        return aliasMath5Minus0;
    }

    public void setAliasMath5Minus0(String _v) {
        this.aliasMath5Minus0 =_v;
    }

    public String getAliasMath5Minus1() {
        return aliasMath5Minus1;
    }

    public void setAliasMath5Minus1(String _v) {
        this.aliasMath5Minus1 =_v;
    }

    public String getAliasMath6Minus0() {
        return aliasMath6Minus0;
    }

    public void setAliasMath6Minus0(String _v) {
        this.aliasMath6Minus0 =_v;
    }

    public String getAliasMath6Minus1() {
        return aliasMath6Minus1;
    }

    public void setAliasMath6Minus1(String _v) {
        this.aliasMath6Minus1 =_v;
    }

    public String getAliasMath7Minus0() {
        return aliasMath7Minus0;
    }

    public void setAliasMath7Minus0(String _v) {
        this.aliasMath7Minus0 =_v;
    }

    public String getAliasMath7Minus1() {
        return aliasMath7Minus1;
    }

    public void setAliasMath7Minus1(String _v) {
        this.aliasMath7Minus1 =_v;
    }

    public String getAliasMath0Mult0() {
        return aliasMath0Mult0;
    }

    public void setAliasMath0Mult0(String _v) {
        this.aliasMath0Mult0 =_v;
    }

    public String getAliasMath0Mult1() {
        return aliasMath0Mult1;
    }

    public void setAliasMath0Mult1(String _v) {
        this.aliasMath0Mult1 =_v;
    }

    public String getAliasMath1Mult0() {
        return aliasMath1Mult0;
    }

    public void setAliasMath1Mult0(String _v) {
        this.aliasMath1Mult0 =_v;
    }

    public String getAliasMath1Mult1() {
        return aliasMath1Mult1;
    }

    public void setAliasMath1Mult1(String _v) {
        this.aliasMath1Mult1 =_v;
    }

    public String getAliasMath2Mult0() {
        return aliasMath2Mult0;
    }

    public void setAliasMath2Mult0(String _v) {
        this.aliasMath2Mult0 =_v;
    }

    public String getAliasMath2Mult1() {
        return aliasMath2Mult1;
    }

    public void setAliasMath2Mult1(String _v) {
        this.aliasMath2Mult1 =_v;
    }

    public String getAliasMath3Mult0() {
        return aliasMath3Mult0;
    }

    public void setAliasMath3Mult0(String _v) {
        this.aliasMath3Mult0 =_v;
    }

    public String getAliasMath3Mult1() {
        return aliasMath3Mult1;
    }

    public void setAliasMath3Mult1(String _v) {
        this.aliasMath3Mult1 =_v;
    }

    public String getAliasMath0BinQuot0() {
        return aliasMath0BinQuot0;
    }

    public void setAliasMath0BinQuot0(String _v) {
        this.aliasMath0BinQuot0 =_v;
    }

    public String getAliasMath0BinQuot1() {
        return aliasMath0BinQuot1;
    }

    public void setAliasMath0BinQuot1(String _v) {
        this.aliasMath0BinQuot1 =_v;
    }

    public String getAliasMath1BinQuot0() {
        return aliasMath1BinQuot0;
    }

    public void setAliasMath1BinQuot0(String _v) {
        this.aliasMath1BinQuot0 =_v;
    }

    public String getAliasMath1BinQuot1() {
        return aliasMath1BinQuot1;
    }

    public void setAliasMath1BinQuot1(String _v) {
        this.aliasMath1BinQuot1 =_v;
    }

    public String getAliasMath2BinQuot0() {
        return aliasMath2BinQuot0;
    }

    public void setAliasMath2BinQuot0(String _v) {
        this.aliasMath2BinQuot0 =_v;
    }

    public String getAliasMath2BinQuot1() {
        return aliasMath2BinQuot1;
    }

    public void setAliasMath2BinQuot1(String _v) {
        this.aliasMath2BinQuot1 =_v;
    }

    public String getAliasMath3BinQuot0() {
        return aliasMath3BinQuot0;
    }

    public void setAliasMath3BinQuot0(String _v) {
        this.aliasMath3BinQuot0 =_v;
    }

    public String getAliasMath3BinQuot1() {
        return aliasMath3BinQuot1;
    }

    public void setAliasMath3BinQuot1(String _v) {
        this.aliasMath3BinQuot1 =_v;
    }

    public String getAliasMath0BinMod0() {
        return aliasMath0BinMod0;
    }

    public void setAliasMath0BinMod0(String _v) {
        this.aliasMath0BinMod0 =_v;
    }

    public String getAliasMath0BinMod1() {
        return aliasMath0BinMod1;
    }

    public void setAliasMath0BinMod1(String _v) {
        this.aliasMath0BinMod1 =_v;
    }

    public String getAliasMath1BinMod0() {
        return aliasMath1BinMod0;
    }

    public void setAliasMath1BinMod0(String _v) {
        this.aliasMath1BinMod0 =_v;
    }

    public String getAliasMath1BinMod1() {
        return aliasMath1BinMod1;
    }

    public void setAliasMath1BinMod1(String _v) {
        this.aliasMath1BinMod1 =_v;
    }

    public String getAliasMath2BinMod0() {
        return aliasMath2BinMod0;
    }

    public void setAliasMath2BinMod0(String _v) {
        this.aliasMath2BinMod0 =_v;
    }

    public String getAliasMath2BinMod1() {
        return aliasMath2BinMod1;
    }

    public void setAliasMath2BinMod1(String _v) {
        this.aliasMath2BinMod1 =_v;
    }

    public String getAliasMath3BinMod0() {
        return aliasMath3BinMod0;
    }

    public void setAliasMath3BinMod0(String _v) {
        this.aliasMath3BinMod0 =_v;
    }

    public String getAliasMath3BinMod1() {
        return aliasMath3BinMod1;
    }

    public void setAliasMath3BinMod1(String _v) {
        this.aliasMath3BinMod1 =_v;
    }

    public String getAliasMath0And0() {
        return aliasMath0And0;
    }

    public void setAliasMath0And0(String _v) {
        this.aliasMath0And0 =_v;
    }

    public String getAliasMath0And1() {
        return aliasMath0And1;
    }

    public void setAliasMath0And1(String _v) {
        this.aliasMath0And1 =_v;
    }

    public String getAliasMath1And0() {
        return aliasMath1And0;
    }

    public void setAliasMath1And0(String _v) {
        this.aliasMath1And0 =_v;
    }

    public String getAliasMath1And1() {
        return aliasMath1And1;
    }

    public void setAliasMath1And1(String _v) {
        this.aliasMath1And1 =_v;
    }

    public String getAliasMath2And0() {
        return aliasMath2And0;
    }

    public void setAliasMath2And0(String _v) {
        this.aliasMath2And0 =_v;
    }

    public String getAliasMath2And1() {
        return aliasMath2And1;
    }

    public void setAliasMath2And1(String _v) {
        this.aliasMath2And1 =_v;
    }

    public String getAliasMath0Or0() {
        return aliasMath0Or0;
    }

    public void setAliasMath0Or0(String _v) {
        this.aliasMath0Or0 =_v;
    }

    public String getAliasMath0Or1() {
        return aliasMath0Or1;
    }

    public void setAliasMath0Or1(String _v) {
        this.aliasMath0Or1 =_v;
    }

    public String getAliasMath1Or0() {
        return aliasMath1Or0;
    }

    public void setAliasMath1Or0(String _v) {
        this.aliasMath1Or0 =_v;
    }

    public String getAliasMath1Or1() {
        return aliasMath1Or1;
    }

    public void setAliasMath1Or1(String _v) {
        this.aliasMath1Or1 =_v;
    }

    public String getAliasMath2Or0() {
        return aliasMath2Or0;
    }

    public void setAliasMath2Or0(String _v) {
        this.aliasMath2Or0 =_v;
    }

    public String getAliasMath2Or1() {
        return aliasMath2Or1;
    }

    public void setAliasMath2Or1(String _v) {
        this.aliasMath2Or1 =_v;
    }

    public String getAliasMath0Xor0() {
        return aliasMath0Xor0;
    }

    public void setAliasMath0Xor0(String _v) {
        this.aliasMath0Xor0 =_v;
    }

    public String getAliasMath0Xor1() {
        return aliasMath0Xor1;
    }

    public void setAliasMath0Xor1(String _v) {
        this.aliasMath0Xor1 =_v;
    }

    public String getAliasMath1Xor0() {
        return aliasMath1Xor0;
    }

    public void setAliasMath1Xor0(String _v) {
        this.aliasMath1Xor0 =_v;
    }

    public String getAliasMath1Xor1() {
        return aliasMath1Xor1;
    }

    public void setAliasMath1Xor1(String _v) {
        this.aliasMath1Xor1 =_v;
    }

    public String getAliasMath2Xor0() {
        return aliasMath2Xor0;
    }

    public void setAliasMath2Xor0(String _v) {
        this.aliasMath2Xor0 =_v;
    }

    public String getAliasMath2Xor1() {
        return aliasMath2Xor1;
    }

    public void setAliasMath2Xor1(String _v) {
        this.aliasMath2Xor1 =_v;
    }

    public String getAliasMath0ShiftLeft0() {
        return aliasMath0ShiftLeft0;
    }

    public void setAliasMath0ShiftLeft0(String _v) {
        this.aliasMath0ShiftLeft0 =_v;
    }

    public String getAliasMath0ShiftLeft1() {
        return aliasMath0ShiftLeft1;
    }

    public void setAliasMath0ShiftLeft1(String _v) {
        this.aliasMath0ShiftLeft1 =_v;
    }

    public String getAliasMath1ShiftLeft0() {
        return aliasMath1ShiftLeft0;
    }

    public void setAliasMath1ShiftLeft0(String _v) {
        this.aliasMath1ShiftLeft0 =_v;
    }

    public String getAliasMath1ShiftLeft1() {
        return aliasMath1ShiftLeft1;
    }

    public void setAliasMath1ShiftLeft1(String _v) {
        this.aliasMath1ShiftLeft1 =_v;
    }

    public String getAliasMath0ShiftRight0() {
        return aliasMath0ShiftRight0;
    }

    public void setAliasMath0ShiftRight0(String _v) {
        this.aliasMath0ShiftRight0 =_v;
    }

    public String getAliasMath0ShiftRight1() {
        return aliasMath0ShiftRight1;
    }

    public void setAliasMath0ShiftRight1(String _v) {
        this.aliasMath0ShiftRight1 =_v;
    }

    public String getAliasMath1ShiftRight0() {
        return aliasMath1ShiftRight0;
    }

    public void setAliasMath1ShiftRight0(String _v) {
        this.aliasMath1ShiftRight0 =_v;
    }

    public String getAliasMath1ShiftRight1() {
        return aliasMath1ShiftRight1;
    }

    public void setAliasMath1ShiftRight1(String _v) {
        this.aliasMath1ShiftRight1 =_v;
    }

    public String getAliasMath0BitShiftLeft0() {
        return aliasMath0BitShiftLeft0;
    }

    public void setAliasMath0BitShiftLeft0(String _v) {
        this.aliasMath0BitShiftLeft0 =_v;
    }

    public String getAliasMath0BitShiftLeft1() {
        return aliasMath0BitShiftLeft1;
    }

    public void setAliasMath0BitShiftLeft1(String _v) {
        this.aliasMath0BitShiftLeft1 =_v;
    }

    public String getAliasMath1BitShiftLeft0() {
        return aliasMath1BitShiftLeft0;
    }

    public void setAliasMath1BitShiftLeft0(String _v) {
        this.aliasMath1BitShiftLeft0 =_v;
    }

    public String getAliasMath1BitShiftLeft1() {
        return aliasMath1BitShiftLeft1;
    }

    public void setAliasMath1BitShiftLeft1(String _v) {
        this.aliasMath1BitShiftLeft1 =_v;
    }

    public String getAliasMath0BitShiftRight0() {
        return aliasMath0BitShiftRight0;
    }

    public void setAliasMath0BitShiftRight0(String _v) {
        this.aliasMath0BitShiftRight0 =_v;
    }

    public String getAliasMath0BitShiftRight1() {
        return aliasMath0BitShiftRight1;
    }

    public void setAliasMath0BitShiftRight1(String _v) {
        this.aliasMath0BitShiftRight1 =_v;
    }

    public String getAliasMath1BitShiftRight0() {
        return aliasMath1BitShiftRight0;
    }

    public void setAliasMath1BitShiftRight0(String _v) {
        this.aliasMath1BitShiftRight0 =_v;
    }

    public String getAliasMath1BitShiftRight1() {
        return aliasMath1BitShiftRight1;
    }

    public void setAliasMath1BitShiftRight1(String _v) {
        this.aliasMath1BitShiftRight1 =_v;
    }

    public String getAliasMath0RotateLeft0() {
        return aliasMath0RotateLeft0;
    }

    public void setAliasMath0RotateLeft0(String _v) {
        this.aliasMath0RotateLeft0 =_v;
    }

    public String getAliasMath0RotateLeft1() {
        return aliasMath0RotateLeft1;
    }

    public void setAliasMath0RotateLeft1(String _v) {
        this.aliasMath0RotateLeft1 =_v;
    }

    public String getAliasMath1RotateLeft0() {
        return aliasMath1RotateLeft0;
    }

    public void setAliasMath1RotateLeft0(String _v) {
        this.aliasMath1RotateLeft0 =_v;
    }

    public String getAliasMath1RotateLeft1() {
        return aliasMath1RotateLeft1;
    }

    public void setAliasMath1RotateLeft1(String _v) {
        this.aliasMath1RotateLeft1 =_v;
    }

    public String getAliasMath0RotateRight0() {
        return aliasMath0RotateRight0;
    }

    public void setAliasMath0RotateRight0(String _v) {
        this.aliasMath0RotateRight0 =_v;
    }

    public String getAliasMath0RotateRight1() {
        return aliasMath0RotateRight1;
    }

    public void setAliasMath0RotateRight1(String _v) {
        this.aliasMath0RotateRight1 =_v;
    }

    public String getAliasMath1RotateRight0() {
        return aliasMath1RotateRight0;
    }

    public void setAliasMath1RotateRight0(String _v) {
        this.aliasMath1RotateRight0 =_v;
    }

    public String getAliasMath1RotateRight1() {
        return aliasMath1RotateRight1;
    }

    public void setAliasMath1RotateRight1(String _v) {
        this.aliasMath1RotateRight1 =_v;
    }

    public String getAliasMath0Le0() {
        return aliasMath0Le0;
    }

    public void setAliasMath0Le0(String _v) {
        this.aliasMath0Le0 =_v;
    }

    public String getAliasMath0Le1() {
        return aliasMath0Le1;
    }

    public void setAliasMath0Le1(String _v) {
        this.aliasMath0Le1 =_v;
    }

    public String getAliasMath0Ge0() {
        return aliasMath0Ge0;
    }

    public void setAliasMath0Ge0(String _v) {
        this.aliasMath0Ge0 =_v;
    }

    public String getAliasMath0Ge1() {
        return aliasMath0Ge1;
    }

    public void setAliasMath0Ge1(String _v) {
        this.aliasMath0Ge1 =_v;
    }

    public String getAliasMath0Lt0() {
        return aliasMath0Lt0;
    }

    public void setAliasMath0Lt0(String _v) {
        this.aliasMath0Lt0 =_v;
    }

    public String getAliasMath0Lt1() {
        return aliasMath0Lt1;
    }

    public void setAliasMath0Lt1(String _v) {
        this.aliasMath0Lt1 =_v;
    }

    public String getAliasMath0Gt0() {
        return aliasMath0Gt0;
    }

    public void setAliasMath0Gt0(String _v) {
        this.aliasMath0Gt0 =_v;
    }

    public String getAliasMath0Gt1() {
        return aliasMath0Gt1;
    }

    public void setAliasMath0Gt1(String _v) {
        this.aliasMath0Gt1 =_v;
    }

    public String getAliasMath1Le0() {
        return aliasMath1Le0;
    }

    public void setAliasMath1Le0(String _v) {
        this.aliasMath1Le0 =_v;
    }

    public String getAliasMath1Le1() {
        return aliasMath1Le1;
    }

    public void setAliasMath1Le1(String _v) {
        this.aliasMath1Le1 =_v;
    }

    public String getAliasMath1Ge0() {
        return aliasMath1Ge0;
    }

    public void setAliasMath1Ge0(String _v) {
        this.aliasMath1Ge0 =_v;
    }

    public String getAliasMath1Ge1() {
        return aliasMath1Ge1;
    }

    public void setAliasMath1Ge1(String _v) {
        this.aliasMath1Ge1 =_v;
    }

    public String getAliasMath1Lt0() {
        return aliasMath1Lt0;
    }

    public void setAliasMath1Lt0(String _v) {
        this.aliasMath1Lt0 =_v;
    }

    public String getAliasMath1Lt1() {
        return aliasMath1Lt1;
    }

    public void setAliasMath1Lt1(String _v) {
        this.aliasMath1Lt1 =_v;
    }

    public String getAliasMath1Gt0() {
        return aliasMath1Gt0;
    }

    public void setAliasMath1Gt0(String _v) {
        this.aliasMath1Gt0 =_v;
    }

    public String getAliasMath1Gt1() {
        return aliasMath1Gt1;
    }

    public void setAliasMath1Gt1(String _v) {
        this.aliasMath1Gt1 =_v;
    }

    public String getAliasMath0Random0() {
        return aliasMath0Random0;
    }

    public void setAliasMath0Random0(String _v) {
        this.aliasMath0Random0 =_v;
    }

    public String getAliasMath0NativeRandom0() {
        return aliasMath0NativeRandom0;
    }

    public void setAliasMath0NativeRandom0(String _v) {
        this.aliasMath0NativeRandom0 = _v;
    }

    public String getAliasMath0Seed0() {
        return aliasMath0Seed0;
    }

    public void setAliasMath0Seed0(String _v) {
        this.aliasMath0Seed0 =_v;
    }

    public String getAliasMath0SeedSpecGenerator0() {
        return aliasMath0SeedSpecGenerator0;
    }

    public void setAliasMath0SeedSpecGenerator0(String _v) {
        this.aliasMath0SeedSpecGenerator0 =_v;
    }

    public String getAliasMath0SeedSpecDoubleGenerator0() {
        return aliasMath0SeedSpecDoubleGenerator0;
    }

    public void setAliasMath0SeedSpecDoubleGenerator0(String _v) {
        this.aliasMath0SeedSpecDoubleGenerator0 =_v;
    }

    public String getAliasMath0Eval0() {
        return aliasMath0Eval0;
    }

    public void setAliasMath0Eval0(String _v) {
        this.aliasMath0Eval0 = _v;
    }

    public String getAliasMath0Eval1() {
        return aliasMath0Eval1;
    }

    public void setAliasMath0Eval1(String _v) {
        this.aliasMath0Eval1 = _v;
    }
}
