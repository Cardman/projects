package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.FormattedMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.exec.types.OverridingMethod;
import code.util.*;

public final class TypeUtil {

    private TypeUtil() {
    }

    public static StringMap<ClassMethodId> getConcreteMethodsToCall(GeneType _type, MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        String baseClassFound_ = _type.getFullName();
        for (ExecRootBlock c: _conf.getClasses().getClassBodies()) {
            String name_ = c.getFullName();
            String baseCond_ = Templates.getOverridingFullTypeByBases(c.getGenericString(), baseClassFound_, _conf);
            if (baseCond_.isEmpty()) {
                continue;
            }
            ClassMethodId f_ = tryGetUniqueId(baseClassFound_, c, _realId, _conf);
            if (f_ != null) {
                eq_.put(name_, f_);
                continue;
            }
            CustList<ClassMethodId> finalMethods_ = new CustList<ClassMethodId>();
            CustList<ClassMethodId> methods_ = new CustList<ClassMethodId>();
            StringList all_ = new StringList();
            all_.add(name_);
            all_.addAllElts(c.getAllSuperTypes());
            for (String s: all_) {
                ExecRootBlock r_ = _conf.getClasses().getClassBody(s);
                if (!(r_ instanceof GeneInterface)) {
                    continue;
                }
                String gene_ = r_.getGenericString();
                String v_ = Templates.getOverridingFullTypeByBases(gene_, baseClassFound_, _conf);
                if (v_.isEmpty()) {
                    continue;
                }
                //r_, as super interface of c, is a sub type of type input
                FormattedMethodId l_ = _realId.quickOverrideFormat(v_, _conf);
                CustList<OverridingMethod> ov_ = r_.getAllOverridingMethods();
                //r_ inherit the formatted method
                CustList<ClassMethodId> foundSuperClasses_ = new CustList<ClassMethodId>();
                boolean found_ = false;
                //if the overridden types contain the type input, then retrieve the sub types of the input type
                //(which are super types of r_)
                for (ClassMethodId t: getList(ov_,l_)) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    ExecRootBlock sub_ = _conf.getClasses().getClassBody(baseSuperType_);
                    if (!sub_.isSubTypeOf(baseClassFound_,_conf)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                feedMehodsLists(_conf, finalMethods_, methods_, foundSuperClasses_);
            }
            ClassMethodId id_ = filterUniqId(_conf, finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
                continue;
            }
            finalMethods_ = new CustList<ClassMethodId>();
            methods_ = new CustList<ClassMethodId>();
            FormattedMethodId l_ = _realId.quickOverrideFormat(baseCond_, _conf);
            CustList<OverridingMethod> ov_ = c.getAllOverridingMethods();
            //r_ inherit the formatted method
            CustList<ClassMethodId> foundSuperClasses_ = new CustList<ClassMethodId>();
            boolean found_ = false;
            //if the overridden types contain the type input, then retrieve the sub types of the input type
            //(which are super types of r_)
            for (ClassMethodId t: getList(ov_,l_)) {
                String t_ = t.getClassName();
                String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                    found_ = true;
                }
                foundSuperClasses_.add(t);
            }
            if (!found_) {
                continue;
            }
            feedMehodsLists(_conf, finalMethods_, methods_, foundSuperClasses_);
            id_ = filterUniqId(_conf, finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
            }
        }
        return eq_;
    }

    private static void feedMehodsLists(ContextEl _conf, CustList<ClassMethodId> finalMethods_, CustList<ClassMethodId> methods_, CustList<ClassMethodId> foundSuperClasses_) {
        for (ClassMethodId t: foundSuperClasses_) {
            String t_ = t.getClassName();
            String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
            ExecOverridableBlock method_ = ExecBlock.getDeepMethodBodiesById(_conf,baseSuperType_, t.getConstraints()).first();
            if (method_.isAbstractMethod()) {
                continue;
            }
            if (method_.isFinalMethod()) {
                finalMethods_.add(t);
            }
            methods_.add(t);
        }
    }
    private static ClassMethodId filterUniqId(ContextEl _conf, CustList<ClassMethodId> finalMethods_, CustList<ClassMethodId> methods_) {
        StringMap<MethodId> defs_ = new StringMap<MethodId>();
        StringList list_ = new StringList();
        for (ClassMethodId v: finalMethods_) {
            defs_.put(v.getClassName(), v.getConstraints());
            list_.add(v.getClassName());
        }
        list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            return new ClassMethodId(class_, defs_.getVal(class_));
        }
        defs_ = new StringMap<MethodId>();
        list_ = new StringList();
        for (ClassMethodId v: methods_) {
            defs_.put(v.getClassName(), v.getConstraints());
            list_.add(v.getClassName());
        }
        list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            return new ClassMethodId(class_, defs_.getVal(class_));
        }
        return null;
    }

    private static ClassMethodId tryGetUniqueId(String _subTypeName, ExecRootBlock _type, MethodId _realId, ContextEl _conf) {
        if (ContextUtil.isEnumType(_type)) {
            String en_ = _conf.getStandards().getAliasEnumType();
            if (!ExecBlock.getDeepMethodBodiesById(_conf,en_, _realId).isEmpty()) {
                return new ClassMethodId(en_, _realId);
            }
        }
        //c is a concrete sub type of type input
        for (String s: _type.getAllGenericClasses()) {
            ExecRootBlock r_ = _conf.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(s));
            String gene_ = r_.getGenericString();
            String v_ = Templates.getOverridingFullTypeByBases(gene_, _subTypeName, _conf);
            if (v_.isEmpty()) {
                continue;
            }
            //r_, as super class of c, is a sub type of type input
            FormattedMethodId l_ = _realId.quickOverrideFormat(v_, _conf);
            CustList<OverridingMethod> ov_ = r_.getAllOverridingMethods();
            //r_ inherit the formatted method
            boolean found_ = false;
            TreeMap<String,MethodId> tree_ = new TreeMap<String,MethodId>(new ComparingByTypeList(r_.getAllGenericClasses()));
            //if the overridden types contain the type input, then look for the "most sub typed" super class of r_
            for (ClassMethodId t: getList(ov_,l_)) {
                String t_ = t.getClassName();
                String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                if (StringList.quickEq(baseSuperType_, _subTypeName)) {
                    found_ = true;
                }
                if (_conf.getClassBody(baseSuperType_) instanceof GeneInterface) {
                    continue;
                }
                tree_.put(t_,t.getConstraints());
            }
            if (!found_) {
                continue;
            }
            String classNameFound_;
            MethodId realId_;
            if (tree_.isEmpty()) {
                continue;
            }
            classNameFound_ = tree_.firstKey();
            realId_ = tree_.firstValue();
            if (ExecBlock.getDeepMethodBodiesById(_conf,classNameFound_, realId_).first().isAbstractMethod()) {
                continue;
            }
            return new ClassMethodId(classNameFound_, realId_);
        }
        return null;
    }
    private static CustList<ClassMethodId> getList(CustList<OverridingMethod> _list, FormattedMethodId _id) {
        CustList<ClassMethodId> list_ = getNullList(_list, _id);
        if (list_ == null) {
            list_ = new CustList<ClassMethodId>();
        }
        return list_;
    }

    private static CustList<ClassMethodId> getNullList(CustList<OverridingMethod> _list, FormattedMethodId _id) {
        for (OverridingMethod o: _list) {
            if (o.getFormattedMethodId().eq(_id)) {
                return o.getMethodIds();
            }
        }
        return null;
    }


}
