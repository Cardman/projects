package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ResultTernary {

    private static final String NO_SUB_CLASS = "";
    private final StringList types;
    private final byte castPrim;
    private final boolean unwrapFirst;
    private final boolean unwrapSecond;
    public ResultTernary(StringList _types, byte _castPrim, boolean _unwrapFirst,
            boolean _unwrapSecond) {
        types = _types;
        castPrim = _castPrim;
        unwrapFirst = _unwrapFirst;
        unwrapSecond = _unwrapSecond;
    }
    public static ResultTernary noUnwrap(StringList _types) {
        return new ResultTernary(_types,(byte)-1,false,false);
    }
    public static ResultTernary unwrapLeft(StringList _types, byte _cast) {
        return new ResultTernary(_types,_cast,true,false);
    }
    public static ResultTernary unwrapRight(StringList _types, byte _cast) {
        return new ResultTernary(_types,_cast,false,true);
    }
    public static ResultTernary unwrapBoth(StringList _types, byte _cast) {
        return new ResultTernary(_types,_cast,true,true);
    }

    public static ResultTernary getResultTernary(StringList _first, Struct _firstArg,
                                                 StringList _second, Struct _secondArg,
                                                 StringMap<StringList> _vars,
                                                 AnalyzedPageEl _page) {
        if (StringUtil.equalsSet(_first, _second)) {
            return noUnwrap(_first);
        }
        AnaClassArgumentMatching first_ = new AnaClassArgumentMatching(_first);
        AnaClassArgumentMatching second_ = new AnaClassArgumentMatching(_second);
        if (_page.matchPrimWrap(_first,_second)) {
            return unwrapRight(_first, first_.getPrimitiveCast(_page));
        }
        if (_page.matchPrimWrap(_second,_first)) {
            return unwrapLeft(_second, second_.getPrimitiveCast(_page));
        }
        if (StringUtil.contains(_first, NO_SUB_CLASS)) {
            return noUnwrap(_page.getTernary(_second));
        }
        if (StringUtil.contains(_second, NO_SUB_CLASS)) {
            return noUnwrap(_page.getTernary(_first));
        }
        if (AnaTypeUtil.isPrimitiveOrWrapper(first_, _page) && AnaTypeUtil.isPrimitiveOrWrapper(second_, _page)) {
            return twoNumbers(_first, _firstArg, _second, _secondArg, _vars, _page);
        }
        return atLeastOneNonNumber(_first, _second, _vars, _page);
    }

    private static ResultTernary atLeastOneNonNumber(StringList _first, StringList _second, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        StringList superTypesFirst_ = getSuperTypesSet(_first, _vars, _page);
        StringList superTypesSecond_ = getSuperTypesSet(_second, _vars, _page);
        StringList superTypesFirstAdj_ = new StringList(superTypesFirst_);
        StringList superTypesSecondAdj_ = new StringList(superTypesSecond_);
        for (String f: superTypesFirst_) {
            for (String s: superTypesSecond_) {
                Mapping map_ = new Mapping();
                map_.setArg(s);
                map_.setParam(f);
                map_.setMapping(_vars);
                if (AnaInherits.isCorrectOrNumbers(map_, _page)) {
                    superTypesSecondAdj_.add(f);
                }
                map_ = new Mapping();
                map_.setArg(f);
                map_.setParam(s);
                map_.setMapping(_vars);
                if (AnaInherits.isCorrectOrNumbers(map_, _page)) {
                    superTypesFirstAdj_.add(s);
                }
            }
        }
        superTypesSecondAdj_.removeDuplicates();
        superTypesFirstAdj_.removeDuplicates();
        StringList ints_ = StringUtil.intersect(superTypesFirstAdj_,superTypesSecondAdj_);
        StringMap<String> basesGene_ = new StringMap<String>();
        StringList bases_ = new StringList();
        for (String l: ints_) {
            String id_ = StringExpUtil.getIdFromAllTypes(l);
            basesGene_.put(id_, l);
            bases_.add(id_);
        }
        bases_ = getTernarySubclasses(bases_, _vars, _page);
        StringList out_ = new StringList();
        for (String l: bases_) {
            out_.add(basesGene_.getVal(l));
        }
        out_.removeDuplicates();
        return noUnwrap(out_);
    }

    private static ResultTernary twoNumbers(StringList _first, Struct _firstArg, StringList _second, Struct _secondArg, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        if (_secondArg instanceof IntStruct) {
            return definedSecond(_first, _firstArg, _second, _secondArg, _vars, _page);
        }
        return possibleDefFirstNumber(_first, _firstArg, _second, _vars, _page);
    }

    private static ResultTernary definedSecond(StringList _first, Struct _firstArg, StringList _second, Struct _secondArg, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        String primShort_ = _page.getAliasPrimShort();
        String primChar_ = _page.getAliasPrimChar();
        String primByte_ = _page.getAliasPrimByte();
        String short_ = _page.getAliasShort();
        String char_ = _page.getAliasCharacter();
        String byte_ = _page.getAliasByte();
        int value_ = NumParsers.convertToNumber(_secondArg).intStruct();
        if (StringUtil.contains(_first, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
            return unwrapRight(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
        }
        if (StringUtil.contains(_first, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
            return unwrapRight(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
        }
        if (StringUtil.contains(_first, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
            return unwrapRight(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
        }
        if (StringUtil.contains(_first, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
            return unwrapBoth(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
        }
        if (StringUtil.contains(_first, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
            return unwrapBoth(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
        }
        if (StringUtil.contains(_first, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
            return unwrapBoth(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
        }
        return possibleDefFirstNumber(_first, _firstArg, _second, _vars, _page);
    }

    private static ResultTernary possibleDefFirstNumber(StringList _first, Struct _firstArg, StringList _second, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        if (_firstArg instanceof IntStruct) {
            return definedFirst(_first, _firstArg, _second, _vars, _page);
        }
        return defNumbers(_first, _second, _vars, _page);
    }

    private static ResultTernary definedFirst(StringList _first, Struct _firstArg, StringList _second, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        String primShort_ = _page.getAliasPrimShort();
        String primChar_ = _page.getAliasPrimChar();
        String primByte_ = _page.getAliasPrimByte();
        String short_ = _page.getAliasShort();
        String char_ = _page.getAliasCharacter();
        String byte_ = _page.getAliasByte();
        int value_ = NumParsers.convertToNumber(_firstArg).intStruct();
        if (StringUtil.contains(_second, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
            return unwrapLeft(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
        }
        if (StringUtil.contains(_second, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
            return unwrapLeft(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
        }
        if (StringUtil.contains(_second, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
            return unwrapLeft(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
        }
        if (StringUtil.contains(_second, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
            return unwrapBoth(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
        }
        if (StringUtil.contains(_second, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
            return unwrapBoth(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
        }
        if (StringUtil.contains(_second, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
            return unwrapBoth(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
        }
        return defNumbers(_first, _second, _vars, _page);
    }

    private static ResultTernary defNumbers(StringList _first, StringList _second, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        StringList prOne_ = new StringList();
        StringList prTwo_ = new StringList();
        for (String c: _first) {
            prOne_.add(AnaTypeUtil.toPrimitive(c, _page.getPrimitiveTypes()));
        }
        for (String c: _second) {
            prTwo_.add(AnaTypeUtil.toPrimitive(c, _page.getPrimitiveTypes()));
        }
        StringList superTypesFirst_ = getSuperTypesSet(prOne_, _vars, _page);
        StringList superTypesSecond_ = getSuperTypesSet(prTwo_, _vars, _page);
        StringList ints_ = StringUtil.intersect(superTypesFirst_,superTypesSecond_);
        StringList bases_;
        bases_ = getTernarySubclasses(ints_, _vars, _page);
        return unwrapBoth(bases_, AnaClassArgumentMatching.getPrimitiveCast(bases_, _page));
    }

    private static CustList<AnaClassArgumentMatching> getAllSuperTypes(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        CustList<AnaClassArgumentMatching> gt_ = new CustList<AnaClassArgumentMatching>();
        String name_ = _class.getName();
        StringMap<PrimitiveType> prs_ = _page.getPrimitiveTypes();
        PrimitiveType pr_ = prs_.getVal(name_);
        gt_.add(_class);
        for (String s: pr_.getAllSuperType(_page)) {
            if (!prs_.contains(s)) {
                continue;
            }
            gt_.add(new AnaClassArgumentMatching(s));
        }
        return gt_;
    }

    static StringList getTernarySubclasses(StringList _classNames, StringMap<StringList> _map, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        Mapping m_ = new Mapping();
        m_.setMapping(_map);
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                if (nonSub(_map, _page, i, j)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        types_.removeDuplicates();
        return types_;
    }
    private static boolean nonSub(StringMap<StringList> _map, AnalyzedPageEl _page, String _i, String _j) {
        String obj_ = _page.getAliasObject();
        String baseSup_ = StringExpUtil.getIdFromAllTypes(_i);
        String baseSub_ = StringExpUtil.getIdFromAllTypes(_j);
        DimComp baseArrSup_ = StringExpUtil.getQuickComponentBaseType(baseSup_);
        DimComp baseArrSub_ = StringExpUtil.getQuickComponentBaseType(baseSub_);
        if (StringUtil.quickEq(baseSup_, baseSub_) || AnaTypeUtil.isPrimitive(baseSup_, _page) && !AnaTypeUtil.isPrimitive(baseSub_, _page)) {
            return false;
        }
        if (baseArrSub_.getComponent().startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            return inhVar(_map, obj_, baseSup_, baseArrSub_);
        }
        if (StringUtil.quickEq(baseArrSup_.getComponent(), _page.getAliasObject())) {
            return baseArrSub_.getDim() >= baseArrSup_.getDim();
        }
        if (baseArrSub_.getDim() != baseArrSup_.getDim() || baseArrSup_.getComponent().startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            return false;
        }
        AnaInheritedType pr_ = _page.getPrimitiveTypes().getVal(baseArrSub_.getComponent());
        AnaInheritedType g_ = _page.getAnaGeneType(baseArrSub_.getComponent());
        AnaInheritedType in_;
        if (pr_ != null) {
            in_ = pr_;
        } else {
            in_ = g_;
        }
        return in_.isSubTypeOf(baseArrSup_.getComponent(), _page);
    }

    private static boolean inhVar(StringMap<StringList> _map, String _obj, String _baseSup, DimComp _baseArrSub) {
        int dimSup_ = _baseArrSub.getDim();
        boolean inh_ = false;
        String b_ = _baseArrSub.getComponent().substring(AnaInherits.PREFIX_VAR_TYPE.length());
        for (String u: Mapping.getAllBounds(_map, b_, _obj)) {
            String a_ = StringExpUtil.getPrettyArrayType(u, dimSup_);
            if (StringUtil.quickEq(a_, _baseSup)) {
                inh_ = true;
                break;
            }
        }
        return inh_;
    }

    static StringList getSuperTypesSet(StringList _first, StringMap<StringList> _mapping, AnalyzedPageEl _page) {
        StringList superTypes_ = new StringList();
        String obj_ = _page.getAliasObject();
        for (String c: _first) {
            loopTypeSet(_mapping, _page, superTypes_, c);
        }
        superTypes_.add(obj_);
        superTypes_.removeDuplicates();
        return superTypes_;
    }

    private static void loopTypeSet(StringMap<StringList> _mapping, AnalyzedPageEl _page, StringList _superTypes, String _c) {
        String bool_ = _page.getAliasPrimBoolean();
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_c);
        String base_ = dc_.getComponent();
        _superTypes.add(_c);
        if (base_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            loopSuperTypesVar(_mapping, _page, _superTypes, dc_);
            return;
        }
        if (StringUtil.quickEq(base_, bool_)) {
            loopSuperTypesBool(_page, _superTypes, dc_);
            return;
        }
        if (AnaTypeUtil.isPrimitive(base_, _page)) {
            loopSuperTypesPrim(_page, _superTypes, dc_);
            return;
        }
        loopSuperTypesStd(_page, _superTypes, dc_);
    }

    private static void loopSuperTypesStd(AnalyzedPageEl _page, StringList _superTypes, DimComp _dc) {
        String obj_ = _page.getAliasObject();
        String base_ = _dc.getComponent();
        int d_ = _dc.getDim();
        addTypes(_page, _superTypes, base_, d_);
        for (int d = 1; d <= d_; d++) {
            _superTypes.add(StringExpUtil.getPrettyArrayType(obj_, d));
        }
    }

    private static void loopSuperTypesPrim(AnalyzedPageEl _page, StringList _superTypes, DimComp _dc) {
        String obj_ = _page.getAliasObject();
        String base_ = _dc.getComponent();
        int d_ = _dc.getDim();
        AnaClassArgumentMatching c_ = new AnaClassArgumentMatching(base_);
        for (AnaClassArgumentMatching s: getAllSuperTypes(c_, _page)) {
            for (String p: s.getNames()) {
                _superTypes.add(StringExpUtil.getPrettyArrayType(p, d_));
                _superTypes.addAllElts(_page.getAllGenericSuperTypesWrapper(p, d_));
            }
        }
        for (int d = 1; d <= d_; d++) {
            _superTypes.add(StringExpUtil.getPrettyArrayType(obj_, d));
        }
    }

    private static void loopSuperTypesBool(AnalyzedPageEl _page, StringList _superTypes, DimComp _dc) {
        String obj_ = _page.getAliasObject();
        String base_ = _dc.getComponent();
        int d_ = _dc.getDim();
        _superTypes.add(StringExpUtil.getPrettyArrayType(base_, d_));
        _superTypes.addAllElts(_page.getAllGenericSuperTypesWrapper(base_, d_));
        for (int d = 1; d <= d_; d++) {
            _superTypes.add(StringExpUtil.getPrettyArrayType(obj_, d));
        }
    }

    private static void loopSuperTypesVar(StringMap<StringList> _mapping, AnalyzedPageEl _page, StringList _superTypes, DimComp _dc) {
        String obj_ = _page.getAliasObject();
        String base_ = _dc.getComponent();
        int d_ = _dc.getDim();
        String ex_ = base_.substring(AnaInherits.PREFIX_VAR_TYPE.length());
        for (String t: Mapping.getAllBounds(_mapping, ex_, obj_)) {
            _superTypes.add(StringExpUtil.getPrettyArrayType(t, d_));
            if (t.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                continue;
            }
            DimComp dci_ = StringExpUtil.getQuickComponentBaseType(t);
            String component_ = dci_.getComponent();
            int dLoc_ = dci_.getDim();
            addTypes(_page, _superTypes, component_, d_ + dLoc_);
        }
        for (int d = 1; d <= d_; d++) {
            _superTypes.add(StringExpUtil.getPrettyArrayType(obj_, d));
        }
    }

    private static void addTypes(AnalyzedPageEl _page, StringList _superTypes, String _base, int _d) {
        String id_ = StringExpUtil.getIdFromAllTypes(_base);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
        if (g_ instanceof RootBlock) {
            addWildCard(_superTypes, _d, (RootBlock) g_);
            for (AnaFormattedRootBlock m: ((RootBlock) g_).getAllGenericSuperTypesInfo()) {
                RootBlock rootBlock_ = m.getRootBlock();
                String formatted_ = m.getFormatted();
                String f_ = AnaInherits.format(g_, _base, formatted_);
                addWildCard(_superTypes, _d, rootBlock_);
                _superTypes.add(StringExpUtil.getPrettyArrayType(f_, _d));
            }
        }
        if (g_ instanceof StandardType) {
            for (String t: ((StandardType) g_).getAllGenericSuperTypes()) {
                String f_ = AnaInherits.format(g_, _base, t);
                _superTypes.add(StringExpUtil.getPrettyArrayType(f_, _d));
            }
        }
    }

    private static void addWildCard(StringList _superTypes, int _d, RootBlock _g) {
        _superTypes.add(StringExpUtil.getPrettyArrayType(_g.getWildCardString(), _d));
    }

    public StringList getTypes() {
        return types;
    }

    public byte getCastPrim() {
        return castPrim;
    }

    public boolean isUnwrapFirst() {
        return unwrapFirst;
    }
    public boolean isUnwrapSecond() {
        return unwrapSecond;
    }

}
