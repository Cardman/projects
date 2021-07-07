package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.OverridingMethodDto;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.util.*;
import code.util.core.IndexConstants;

public final class OverridesTypeUtil {

    private OverridesTypeUtil() {
    }

    public static StringMap<GeneStringOverridable> getConcreteMethodsToCall(RootBlock _type, MethodId _realId, AnalyzedPageEl _page) {
        StringMap<GeneStringOverridable> eq_ = new StringMap<GeneStringOverridable>();
        for (RootBlock c: _page.getAllFoundTypes()) {
            String name_ = c.getFullName();
            AnaFormattedRootBlock baseCond_ = AnaInherits.getOverridingFullTypeByBases(c, _type);
            if (baseCond_ == null) {
                continue;
            }
            GeneStringOverridable f_ = tryGetUniqueId(_type, c, _realId);
            if (f_ != null) {
                eq_.put(name_, f_);
                continue;
            }
            CustList<GeneStringOverridable> finalMethods_ = new CustList<GeneStringOverridable>();
            CustList<GeneStringOverridable> methods_ = new CustList<GeneStringOverridable>();
            CustList<AnaGeneType> all_ = new CustList<AnaGeneType>();
            all_.add(c);
            all_.addAllElts(c.getAllSuperTypesInfo());
            for (AnaGeneType s: all_) {
                if (!(s instanceof InterfaceBlock)) {
                    continue;
                }
                InterfaceBlock i_ = (InterfaceBlock) s;
                AnaFormattedRootBlock v_ = AnaInherits.getOverridingFullTypeByBases(i_, _type);
                if (v_ == null) {
                    continue;
                }
                //r_, as super interface of c, is a sub type of type input
                FormattedMethodId l_ = new FormattedMethodId(_realId.quickFormat(AnaInherits.getVarTypes(v_)));
                CustList<OverridingMethodDto> ov_ = i_.getAllOverridingMethods();
                //r_ inherit the formatted method
                CustList<GeneStringOverridable> foundSuperClasses_ = new CustList<GeneStringOverridable>();
                boolean found_ = false;
                //if the overridden types contain the type input, then retrieve the sub types of the input type
                //(which are super types of r_)
                for (GeneStringOverridable t: getList(ov_,l_)) {
                    if (t.getType() == _type) {
                        found_ = true;
                    }
                    if (!t.getType().isSubTypeOf(_type)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                feedMehodsLists(finalMethods_, methods_, foundSuperClasses_);
            }
            GeneStringOverridable id_ = filterUniqId(finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
                continue;
            }
            finalMethods_ = new CustList<GeneStringOverridable>();
            methods_ = new CustList<GeneStringOverridable>();
            FormattedMethodId l_ = new FormattedMethodId(_realId.quickFormat(AnaInherits.getVarTypes(baseCond_)));
            CustList<OverridingMethodDto> ov_ = c.getAllOverridingMethods();
            //r_ inherit the formatted method
            CustList<GeneStringOverridable> foundSuperClasses_ = new CustList<GeneStringOverridable>();
            boolean found_ = false;
            //if the overridden types contain the type input, then retrieve the sub types of the input type
            //(which are super types of r_)
            for (GeneStringOverridable t: getList(ov_,l_)) {
                if (t.getType() == _type) {
                    found_ = true;
                }
                foundSuperClasses_.add(t);
            }
            if (!found_) {
                continue;
            }
            feedMehodsLists(finalMethods_, methods_, foundSuperClasses_);
            id_ = filterUniqId(finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
            }
        }
        return eq_;
    }

    private static void feedMehodsLists(CustList<GeneStringOverridable> _finalMethods, CustList<GeneStringOverridable> _methods, CustList<GeneStringOverridable> _foundSuperClasses) {
        for (GeneStringOverridable t: _foundSuperClasses) {
            NamedCalledFunctionBlock method_ = t.getBlock();
            if (method_.isAbstractMethod()) {
                continue;
            }
            if (method_.isFinalMethod()) {
                _finalMethods.add(t);
            }
            _methods.add(t);
        }
    }
    private static GeneStringOverridable filterUniqId(CustList<GeneStringOverridable> _finalMethods, CustList<GeneStringOverridable> _methods) {
        IdMap<RootBlock,GeneStringOverridable> defs_ = new IdMap<RootBlock,GeneStringOverridable>();
        IdList<RootBlock> list_ = new IdList<RootBlock>();
        for (GeneStringOverridable v: _finalMethods) {
            defs_.put(v.getType(), v);
            list_.add(v.getType());
        }
        list_ = AnaTypeUtil.getSubclassesCust(list_);
        if (onlyOneElt(list_)) {
            RootBlock class_ = list_.first();
            return defs_.getVal(class_);
        }
        defs_ = new IdMap<RootBlock,GeneStringOverridable>();
        list_ = new IdList<RootBlock>();
        for (GeneStringOverridable v: _methods) {
            defs_.put(v.getType(), v);
            list_.add(v.getType());
        }
        list_ = AnaTypeUtil.getSubclassesCust(list_);
        if (onlyOneElt(list_)) {
            RootBlock class_ = list_.first();
            return defs_.getVal(class_);
        }
        return null;
    }
    public static boolean onlyOneElt(IdList<RootBlock> _list) {
        if (_list.isEmpty()) {
            return false;
        }
        RootBlock e_ = _list.first();
        int s_ = _list.size();
        for (int i = IndexConstants.SECOND_INDEX; i < s_; i++) {
            if (_list.get(i) != e_) {
                return false;
            }
        }
        return true;
    }

    private static GeneStringOverridable tryGetUniqueId(RootBlock _toFind, RootBlock _type, MethodId _realId) {
        //c is a concrete sub type of type input
        for (AnaFormattedRootBlock s: _type.getAllGenericClassesInfo()) {
            RootBlock r_ = s.getRootBlock();
            AnaFormattedRootBlock v_ = AnaInherits.getOverridingFullTypeByBases(r_, _toFind);
            if (v_ == null) {
                continue;
            }
            //r_, as super class of c, is a sub type of type input
            FormattedMethodId l_ = new FormattedMethodId(_realId.quickFormat(AnaInherits.getVarTypes(v_)));
            CustList<OverridingMethodDto> ov_ = r_.getAllOverridingMethods();
            //r_ inherit the formatted method
            boolean found_ = false;
            TreeMap<RootBlock,GeneStringOverridable> tree_ = new TreeMap<RootBlock,GeneStringOverridable>(new ComparingByTypeList(getAllGenericClasses(r_)));
            //if the overridden types contain the type input, then look for the "most sub typed" super class of r_
            for (GeneStringOverridable t: getList(ov_,l_)) {
                RootBlock type_ = t.getType();
                if (type_ == _toFind) {
                    found_ = true;
                }
                if (type_ instanceof InterfaceBlock) {
                    continue;
                }
                tree_.put(type_,t);
            }
            if (!found_) {
                continue;
            }
            if (tree_.isEmpty()) {
                continue;
            }
            GeneStringOverridable gene_ = tree_.firstValue();
            if (gene_.getBlock().isAbstractMethod()) {
                continue;
            }
            return gene_;
        }
        return null;
    }
    private static IdList<RootBlock> getAllGenericClasses(RootBlock _r) {
        IdList<RootBlock> list_ = new IdList<RootBlock>();
        for (AnaFormattedRootBlock a: _r.getAllGenericClassesInfo()) {
            list_.add(a.getRootBlock());
        }
        return list_;
    }
    private static CustList<GeneStringOverridable> getList(CustList<OverridingMethodDto> _list, FormattedMethodId _id) {
        CustList<GeneStringOverridable> list_ = getNullList(_list, _id);
        if (list_ == null) {
            list_ = new CustList<GeneStringOverridable>();
        }
        return list_;
    }

    private static CustList<GeneStringOverridable> getNullList(CustList<OverridingMethodDto> _list, FormattedMethodId _id) {
        for (OverridingMethodDto o: _list) {
            if (o.getFormattedMethodId().eq(_id)) {
                return o.getMethodIds();
            }
        }
        return null;
    }


}
