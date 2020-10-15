package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.OverridableBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.OverridingMethodDto;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.util.*;
import code.util.core.StringUtil;

public final class OverridesTypeUtil {

    private OverridesTypeUtil() {
    }

    public static StringMap<GeneStringOverridable> getConcreteMethodsToCall(AnaGeneType _type, MethodId _realId, AnalyzedPageEl _page) {
        StringMap<GeneStringOverridable> eq_ = new StringMap<GeneStringOverridable>();
        String baseClassFound_ = _type.getFullName();
        for (RootBlock c: _page.getAllFoundTypes()) {
            String name_ = c.getFullName();
            String baseCond_ = AnaTemplates.getOverridingFullTypeByBases(c, baseClassFound_, _page);
            if (baseCond_.isEmpty()) {
                continue;
            }
            GeneStringOverridable f_ = tryGetUniqueId(baseClassFound_,_type, c, _realId, _page);
            if (f_ != null) {
                eq_.put(name_, f_);
                continue;
            }
            CustList<GeneStringOverridable> finalMethods_ = new CustList<GeneStringOverridable>();
            CustList<GeneStringOverridable> methods_ = new CustList<GeneStringOverridable>();
            StringList all_ = new StringList();
            all_.add(name_);
            all_.addAllElts(c.getAllSuperTypes());
            for (String s: all_) {
                RootBlock r_ = _page.getAnaClassBody(s);
                if (!(r_ instanceof InterfaceBlock)) {
                    continue;
                }
                String v_ = AnaTemplates.getOverridingFullTypeByBases(r_, baseClassFound_, _page);
                if (v_.isEmpty()) {
                    continue;
                }
                //r_, as super interface of c, is a sub type of type input
                FormattedMethodId l_ = _realId.quickOverrideFormat(_type,v_);
                CustList<OverridingMethodDto> ov_ = r_.getAllOverridingMethods();
                //r_ inherit the formatted method
                CustList<GeneStringOverridable> foundSuperClasses_ = new CustList<GeneStringOverridable>();
                boolean found_ = false;
                //if the overridden types contain the type input, then retrieve the sub types of the input type
                //(which are super types of r_)
                for (GeneStringOverridable t: getList(ov_,l_)) {
                    String t_ = t.getGeneString();
                    String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                    if (StringUtil.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    if (!t.getType().isSubTypeOf(baseClassFound_, _page)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                feedMehodsLists(finalMethods_, methods_, foundSuperClasses_);
            }
            GeneStringOverridable id_ = filterUniqId(finalMethods_, methods_, _page);
            if (id_ != null) {
                eq_.put(name_, id_);
                continue;
            }
            finalMethods_ = new CustList<GeneStringOverridable>();
            methods_ = new CustList<GeneStringOverridable>();
            FormattedMethodId l_ = _realId.quickOverrideFormat(_type,baseCond_);
            CustList<OverridingMethodDto> ov_ = c.getAllOverridingMethods();
            //r_ inherit the formatted method
            CustList<GeneStringOverridable> foundSuperClasses_ = new CustList<GeneStringOverridable>();
            boolean found_ = false;
            //if the overridden types contain the type input, then retrieve the sub types of the input type
            //(which are super types of r_)
            for (GeneStringOverridable t: getList(ov_,l_)) {
                String t_ = t.getGeneString();
                String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                if (StringUtil.quickEq(baseSuperType_, baseClassFound_)) {
                    found_ = true;
                }
                foundSuperClasses_.add(t);
            }
            if (!found_) {
                continue;
            }
            feedMehodsLists(finalMethods_, methods_, foundSuperClasses_);
            id_ = filterUniqId(finalMethods_, methods_, _page);
            if (id_ != null) {
                eq_.put(name_, id_);
            }
        }
        return eq_;
    }

    private static void feedMehodsLists(CustList<GeneStringOverridable> _finalMethods, CustList<GeneStringOverridable> _methods, CustList<GeneStringOverridable> _foundSuperClasses) {
        for (GeneStringOverridable t: _foundSuperClasses) {
            OverridableBlock method_ = t.getBlock();
            if (method_.isAbstractMethod()) {
                continue;
            }
            if (method_.isFinalMethod()) {
                _finalMethods.add(t);
            }
            _methods.add(t);
        }
    }
    private static GeneStringOverridable filterUniqId(CustList<GeneStringOverridable> _finalMethods, CustList<GeneStringOverridable> _methods, AnalyzedPageEl _page) {
        StringMap<GeneStringOverridable> defs_ = new StringMap<GeneStringOverridable>();
        StringList list_ = new StringList();
        for (GeneStringOverridable v: _finalMethods) {
            defs_.put(v.getGeneString(), v);
            list_.add(v.getGeneString());
        }
        list_ = AnaTypeUtil.getSubclasses(list_, _page);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            return defs_.getVal(class_);
        }
        defs_ = new StringMap<GeneStringOverridable>();
        list_ = new StringList();
        for (GeneStringOverridable v: _methods) {
            defs_.put(v.getGeneString(), v);
            list_.add(v.getGeneString());
        }
        list_ = AnaTypeUtil.getSubclasses(list_, _page);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            return defs_.getVal(class_);
        }
        return null;
    }

    private static GeneStringOverridable tryGetUniqueId(String _subTypeName, AnaGeneType _toFind, RootBlock _type, MethodId _realId, AnalyzedPageEl _page) {
        //c is a concrete sub type of type input
        for (AnaFormattedRootBlock s: _type.getAllGenericClassesInfo()) {
            RootBlock r_ = s.getRootBlock();
            String v_ = AnaTemplates.getOverridingFullTypeByBases(r_, _subTypeName, _page);
            if (v_.isEmpty()) {
                continue;
            }
            //r_, as super class of c, is a sub type of type input
            FormattedMethodId l_ = _realId.quickOverrideFormat(_toFind,v_);
            CustList<OverridingMethodDto> ov_ = r_.getAllOverridingMethods();
            //r_ inherit the formatted method
            boolean found_ = false;
            TreeMap<String,GeneStringOverridable> tree_ = new TreeMap<String,GeneStringOverridable>(new ComparingByTypeList(r_.getAllGenericClasses()));
            //if the overridden types contain the type input, then look for the "most sub typed" super class of r_
            for (GeneStringOverridable t: getList(ov_,l_)) {
                String t_ = t.getGeneString();
                String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                if (StringUtil.quickEq(baseSuperType_, _subTypeName)) {
                    found_ = true;
                }
                if (t.getType() instanceof InterfaceBlock) {
                    continue;
                }
                tree_.put(t_,t);
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
