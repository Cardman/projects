package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.utilcompo.stds.*;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class MathAdvAliases {
    private static final String RATE="______1781";
    private static final String LG_INT="______1782";
    private static final String LG_INT_PARSE="______1783";
    private static final String RATE_PARSE="______1784";
    private static final String RATE_NUM="______1785";
    private static final String RATE_DEN="______1786";
    private String aliasLgInt;
    private String aliasRate;
    private String aliasLgIntParse;
    private String aliasRateParse;
    private String aliasRateDen;
    private String aliasRateNum;
    private final MathAdvAliasesParameters mathAliasParameters = new MathAdvAliasesParameters();
    public void buildOther(LgNamesContent _content) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasRate, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.NORMAL, new DfLgNumber(this));
        StringList params_ = new StringList(_content.getCharSeq().getAliasString());
        StandardMethod method_ = new StandardMethod(aliasRateParse, params_, aliasRate, false, MethodModifier.STATIC,new StringList(mathAliasParameters.getAliasRate0RateParse0()),new FctNbRateSafeAbs(new FctRateParse(this)));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasLgIntParse, params_, aliasLgInt, false, MethodModifier.STATIC,new StringList(mathAliasParameters.getAliasLgInt0LgIntParse0()),new FctNbRateSafeAbs(new FctLgIntParse(this)));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRateNum, params_, aliasLgInt, false, MethodModifier.FINAL,new FctNbRateNum(this));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRateDen, params_, aliasLgInt, false, MethodModifier.FINAL,new FctNbRateDen(this));
        methods_.add( method_);
        params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_,false,new FctRate0(this));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(mathAliasParameters.getAliasRate0Rate0()),new FctNbRateAbs(new FctRateParse(this)));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasRate, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        StandardClass std_ = new StandardClass(aliasLgInt, fields_, constructors_, methods_, aliasRate, MethodModifier.FINAL, new DfLgNumber(this));
        ctor_ = new StandardConstructor(params_,false,new FctRate0(this));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(mathAliasParameters.getAliasLgInt0LgInt0()),new FctNbRateAbs(new FctLgIntParse(this)));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasLgInt, std_);
    }
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasLgInt(LgNamesContent.get(_util,_cust,_mapping.getVal(LG_INT)));
        setAliasRate(LgNamesContent.get(_util,_cust,_mapping.getVal(RATE)));
        setAliasRateParse(LgNamesContent.get(_util,_cust,_mapping.getVal(RATE_PARSE)));
        setAliasLgIntParse(LgNamesContent.get(_util,_cust,_mapping.getVal(LG_INT_PARSE)));
        setAliasRateDen(LgNamesContent.get(_util,_cust,_mapping.getVal(RATE_DEN)));
        setAliasRateNum(LgNamesContent.get(_util,_cust,_mapping.getVal(RATE_NUM)));
        mathAliasParameters.build(_util, _cust, _mapping);
    }
    public static void en(TranslationsFile _en){
        _en.add(RATE,"Rate=$core.Rate");
        _en.add(LG_INT,"LgInt=$core.LgInt");
        _en.add(LG_INT_PARSE,"LgIntParse=parseLgInt");
        _en.add(RATE_PARSE,"RateParse=parseRate");
        _en.add(RATE_NUM,"RateNum=num");
        _en.add(RATE_DEN,"RateDen=den");
        MathAdvAliasesParameters.en(_en);
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(RATE,"Rate=$coeur.Taux");
        _fr.add(LG_INT,"LgInt=$coeur.LgEnt");
        _fr.add(LG_INT_PARSE,"LgIntParse=parseLgEnt");
        _fr.add(RATE_PARSE,"RateParse=parseTaux");
        _fr.add(RATE_NUM,"RateNum=num");
        _fr.add(RATE_DEN,"RateDen=den");
        MathAdvAliasesParameters.fr(_fr);
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(mathAliasParameters.allTableTypeMethodParamNames(_mapping));
        return m_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
        m_.addEntry(getAliasLgInt(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(LG_INT_PARSE),getAliasLgIntParse()),
                new KeyValueMemberName(_mapping.getVal(RATE_PARSE),getAliasRateParse()),
                new KeyValueMemberName(_mapping.getVal(RATE_NUM),getAliasRateNum()),
                new KeyValueMemberName(_mapping.getVal(RATE_DEN),getAliasRateDen())));
        return m_;
    }

    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ = new StringMap<String>();
        ref_.addEntry(_mapping.getVal(RATE),getAliasRate());
        ref_.addEntry(_mapping.getVal(LG_INT),getAliasLgInt());
        return ref_;
    }

    public String getAliasLgInt() {
        return aliasLgInt;
    }

    public void setAliasLgInt(String _v) {
        this.aliasLgInt = _v;
    }

    public String getAliasRate() {
        return aliasRate;
    }

    public void setAliasRate(String _v) {
        this.aliasRate = _v;
    }

    public String getAliasLgIntParse() {
        return aliasLgIntParse;
    }

    public void setAliasLgIntParse(String _v) {
        this.aliasLgIntParse = _v;
    }

    public String getAliasRateParse() {
        return aliasRateParse;
    }

    public void setAliasRateParse(String _v) {
        this.aliasRateParse = _v;
    }

    public String getAliasRateDen() {
        return aliasRateDen;
    }

    public void setAliasRateDen(String _v) {
        this.aliasRateDen = _v;
    }

    public String getAliasRateNum() {
        return aliasRateNum;
    }

    public void setAliasRateNum(String _v) {
        this.aliasRateNum = _v;
    }
}
