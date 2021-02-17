package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.Argument;
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

    public static ResultTernary getResultTernary(StringList _first, Argument _firstArg,
                                                 StringList _second, Argument _secondArg,
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
            String primShort_ = _page.getAliasPrimShort();
            String primChar_ = _page.getAliasPrimChar();
            String primByte_ = _page.getAliasPrimByte();
            String short_ = _page.getAliasShort();
            String char_ = _page.getAliasCharacter();
            String byte_ = _page.getAliasByte();
            if (_secondArg != null && _secondArg.getStruct() instanceof IntStruct) {
                int value_ = NumParsers.convertToNumber(_secondArg.getStruct()).intStruct();
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
            }
            if (_firstArg != null && _firstArg.getStruct() instanceof IntStruct) {
                int value_ = NumParsers.convertToNumber(_firstArg.getStruct()).intStruct();
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
            }
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
        String obj_ = _page.getAliasObject();
        Mapping m_ = new Mapping();
        m_.setMapping(_map);
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                DimComp baseArrSup_ = StringExpUtil.getQuickComponentBaseType(baseSup_);
                DimComp baseArrSub_ = StringExpUtil.getQuickComponentBaseType(baseSub_);
                if (StringUtil.quickEq(baseSup_, baseSub_)) {
                    continue;
                }
                if (AnaTypeUtil.isPrimitive(baseSup_, _page) && !AnaTypeUtil.isPrimitive(baseSub_, _page)) {
                    continue;
                }
                int dimSup_ = baseArrSub_.getDim();
                if (baseArrSub_.getComponent().startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                    boolean inh_ = false;
                    String b_ = baseArrSub_.getComponent().substring(AnaInherits.PREFIX_VAR_TYPE.length());
                    for (String u: Mapping.getAllBounds(_map, b_, obj_)) {
                        String a_ = StringExpUtil.getPrettyArrayType(u, dimSup_);
                        if (StringUtil.quickEq(a_, baseSup_)) {
                            inh_ = true;
                            break;
                        }
                    }
                    if (inh_) {
                        sub_ = false;
                        break;
                    }
                    continue;
                }
                if (StringUtil.quickEq(baseArrSup_.getComponent(), _page.getAliasObject())) {
                    if (baseArrSub_.getDim() >= baseArrSup_.getDim()) {
                        sub_ = false;
                        break;
                    }
                    continue;
                }
                if (baseArrSub_.getDim() != baseArrSup_.getDim()) {
                    continue;
                }
                if (baseArrSup_.getComponent().startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                    continue;
                }
                AnaInheritedType pr_ = _page.getPrimitiveTypes().getVal(baseArrSub_.getComponent());
                AnaInheritedType g_ = _page.getAnaGeneType(baseArrSub_.getComponent());
                AnaInheritedType in_;
                if (pr_ != null) {
                    in_ = pr_;
                } else {
                    in_ = g_;
                }
                if (in_.isSubTypeOf(baseArrSup_.getComponent(), _page)) {
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

    static StringList getSuperTypesSet(StringList _first, StringMap<StringList> _mapping, AnalyzedPageEl _page) {
        StringList superTypes_ = new StringList();
        String obj_ = _page.getAliasObject();
        String bool_ = _page.getAliasPrimBoolean();
        for (String c: _first) {
            DimComp dc_ = StringExpUtil.getQuickComponentBaseType(c);
            String base_ = dc_.getComponent();
            int d_ = dc_.getDim();
            superTypes_.add(c);
            if (base_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                String ex_ = base_.substring(AnaInherits.PREFIX_VAR_TYPE.length());
                for (String t: Mapping.getAllBounds(_mapping, ex_, obj_)) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(t, d_));
                    if (t.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                        continue;
                    }
                    DimComp dci_ = StringExpUtil.getQuickComponentBaseType(t);
                    String component_ = dci_.getComponent();
                    int dLoc_ = dci_.getDim();
                    String i_ = StringExpUtil.getIdFromAllTypes(component_);
                    AnaGeneType j_ = _page.getAnaGeneType(i_);
                    addTypes(superTypes_, component_, d_+ dLoc_, j_);
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (StringUtil.quickEq(base_, bool_)) {
                superTypes_.add(StringExpUtil.getPrettyArrayType(base_, d_));
                superTypes_.addAllElts(_page.getAllGenericSuperTypesWrapper(base_,d_));
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (AnaTypeUtil.isPrimitive(base_, _page)) {
                AnaClassArgumentMatching c_ = new AnaClassArgumentMatching(base_);
                for (AnaClassArgumentMatching s: getAllSuperTypes(c_, _page)) {
                    for (String p: s.getNames()) {
                        superTypes_.add(StringExpUtil.getPrettyArrayType(p, d_));
                        superTypes_.addAllElts(_page.getAllGenericSuperTypesWrapper(p,d_));
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            AnaGeneType g_ = _page.getAnaGeneType(id_);
            addTypes(superTypes_, base_, d_, g_);
            for (int d = 1; d <= d_; d++) {
                superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
            }
        }
        superTypes_.add(obj_);
        superTypes_.removeDuplicates();
        return superTypes_;
    }

    private static void addTypes(StringList _superTypes, String _base, int _d, AnaGeneType _g) {
        if (_g instanceof RootBlock) {
            addWildCard(_superTypes, _d, (RootBlock)_g);
            for (AnaFormattedRootBlock m: ((RootBlock) _g).getAllGenericSuperTypesInfo()) {
                RootBlock rootBlock_ = m.getRootBlock();
                String formatted_ = m.getFormatted();
                String f_ = AnaInherits.format(_g, _base, formatted_);
                addWildCard(_superTypes, _d, rootBlock_);
                _superTypes.add(StringExpUtil.getPrettyArrayType(f_, _d));
            }
        }
        if (_g instanceof StandardType) {
            for (String t: _g.getAllGenericSuperTypes()) {
                String f_ = AnaInherits.format(_g, _base, t);
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
