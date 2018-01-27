package code.expressionlanguage.common;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.OverridingRelation;
import code.expressionlanguage.stds.LgNames;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorIndexes;

public final class TypeUtil {

    private static final String LT = "<";
    private static final String GT = ">";
    private static final String SEP_TMP = ",";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_ACCESS = "access";
    private static final String ATTRIBUTE_CLASS = "class";
    private TypeUtil() {
    }

    public static void buildInherits(ContextEl _context, StringList _types) {
        Classes classes_ = _context.getClasses();
        String objectClassName_ = _context.getStandards().getAliasObject();
        for (String c: _types) {
            GeneType dBl_ = _context.getClassBody(c);
            StringList all_ = dBl_.getAllSuperClasses();
            StringList direct_ = dBl_.getDirectSuperClasses(_context);
            all_.addAllElts(direct_);
            for (String b: direct_) {
                if (StringList.quickEq(b, objectClassName_)) {
                    continue;
                }
                GeneType bBl_ = _context.getClassBody(b);
                all_.addAllElts(bBl_.getAllSuperClasses());
            }
        }
        for (String c: _types) {
            GeneType bl_ = _context.getClassBody(c);
            if (!(bl_ instanceof GeneClass)) {
                continue;
            }
            StringList all_ = bl_.getAllInterfaces();
            StringList direct_ = ((GeneClass) bl_).getDirectInterfaces(_context);
            all_.addAllElts(direct_);
            for (String i: direct_) {
                GeneInterface i_ = (GeneInterface) _context.getClassBody(i);
                all_.addAllElts(i_.getAllInterfaces());
            }
            if (!(bl_ instanceof RootBlock)) {
                continue;
            }
            RootBlock cust_ = (RootBlock) bl_;
            StringList needed_ = new StringList();
            for (String s: cust_.getDirectSuperClasses(_context)) {
                if (!StringList.quickEq(s, objectClassName_)) {
                    RootBlock super_ = classes_.getClassBody(s);
                    all_.addAllElts(super_.getAllInterfaces());
                    needed_.addAllElts(super_.getAllSortedInterfaces());
                }
            }
            all_.removeAllObj(objectClassName_);
            all_.removeDuplicates();
            cust_.getAllSortedInterfaces().addAllElts(classes_.getSortedSuperInterfaces(all_,_context));
            cust_.getAllNeededSortedInterfaces().addAllElts(cust_.getAllSortedInterfaces());
            cust_.getAllNeededSortedInterfaces().removeAllElements(needed_);
        }
        for (GeneType c: _context.getClassBodies()) {
            if (!_types.containsStr(c.getFullName())) {
                continue;
            }
            if (c instanceof GeneClass) {
                c.getAllSuperTypes().addAllElts(((GeneClass)c).getAllSuperClasses(_context));
                c.getAllSuperTypes().addAllElts(((GeneClass)c).getAllInterfaces());
            } else {
                c.getAllSuperTypes().addAllElts(((GeneInterface)c).getAllSuperClasses());
            }
        }
    }
    public static void buildOverrides(GeneType _type,ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        LgNames stds_ = _context.getStandards();
        ObjectMap<MethodId, EqList<ClassMethodId>> allOv_ = getAllInstanceSignatures(_type, _context);
        ObjectMap<MethodId, EqList<ClassMethodId>> allBaseOv_ = getAllOverridingMethods(allOv_, _context);
        ObjectMap<MethodId,CustList<OverridingRelation>> allOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        CustList<OverridingRelation> allBases_ = new CustList<OverridingRelation>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: allOv_.entryList()) {
            CustList<ClassMethodId> current_ = new CustList<ClassMethodId>();
            StringList visited_ = new StringList();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            for (ClassMethodId t: allBaseOv_.getVal(e.getKey())) {
                OverridingRelation ovRelBase_ = new OverridingRelation();
                ovRelBase_.setRealId(e.getKey());
                ovRelBase_.setSubMethod(t);
                ovRelBase_.setSupMethod(t);
                allBases_.add(ovRelBase_);
                pairs_.add(ovRelBase_);
                current_.add(t);
                visited_.add(StringList.getAllTypes(t.getClassName()).first());
            }
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                CustList<OverridingRelation> newpairs_ = new CustList<OverridingRelation>();
                for (ClassMethodId c: current_) {
                    String templClass_ = c.getClassName();
                    String typeName_ = StringList.getAllTypes(templClass_).first();
                    GeneType root_ = _context.getClassBody(typeName_);
                    for (String u:getAllGenericSuperTypes(root_,_context)) {
                        String superType_ = Templates.format(templClass_, u, _context);
                        String superTypeName_ = StringList.getAllTypes(u).first();
                        GeneType super_ = _context.getClassBody(superTypeName_);
                        for (GeneMethod m: ContextEl.getMethodBlocks(super_)) {
                            MethodId f_ = m.getFormattedId(superType_, _context);
                            if (f_.eq(c.getConstraints().format(templClass_, _context))) {
                                OverridingRelation ovRel_ = new OverridingRelation();
                                ovRel_.setRealId(e.getKey());
                                ovRel_.setSubMethod(c);
                                ovRel_.setSupMethod(new ClassMethodId(superType_, m.getId()));
                                newpairs_.add(ovRel_);
                            }
                        }
                    }
                }
                for (OverridingRelation p: newpairs_) {
                    pairs_.add(p);
                    String superType_ = p.getSupMethod().getClassName();
                    String superTypeId_ = StringList.getAllTypes(superType_).first();
                    if (!visited_.containsStr(superTypeId_)) {
                        next_.add(p.getSupMethod());
                        visited_.add(superTypeId_);
                    }
                }
                if (next_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            allOverridings_.put(e.getKey(), pairs_);
        }
        ObjectMap<MethodId,CustList<OverridingRelation>> accOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        for (OverridingRelation o: allBases_) {
            OverridingRelation value_ = o;
            ClassMethodId subId_ = value_.getSubMethod();
            GeneMethod sub_ = _context.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
            if (Classes.canAccess(_type.getFullName(), sub_, _context)) {
                accOverridings_.put(o.getRealId(), allOverridings_.getVal(o.getRealId()));
            }
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: _type.getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        ObjectMap<MethodId,CustList<OverridingRelation>> filterAccOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        for (EntryCust<MethodId,CustList<OverridingRelation>> o: accOverridings_.entryList()) {
            CustList<OverridingRelation> relations_ = new CustList<OverridingRelation>();
            for (OverridingRelation l: o.getValue()) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                GeneMethod sub_ = _context.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
                GeneMethod sup_ = _context.getMethodBodiesById(supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    if (Classes.canAccess(_type.getFullName(), sub_, _context)) {
                        relations_.add(l);
                    }
                } else if (Classes.canAccess(subId_.getClassName(), sup_, _context)) {
                    relations_.add(l);
                }
            }
            if (relations_.isEmpty()) {
                continue;
            }
            filterAccOverridings_.put(o.getKey(), relations_);
        }
        ObjectMap<MethodId,CustList<OverridingRelation>> builtAccOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        for (EntryCust<MethodId,CustList<OverridingRelation>> o: filterAccOverridings_.entryList()) {
            CustList<ClassMethodId> current_ = new CustList<ClassMethodId>();
            CustList<OverridingRelation> pairs_ = new CustList<OverridingRelation>();
            for (OverridingRelation t: allBases_) {
                if (t.getRealId().eq(o.getKey())) {
                    current_.add(t.getSubMethod());
                    pairs_.add(t);
                }
            }
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                for (ClassMethodId c: current_) {
                    for (OverridingRelation a: o.getValue()) {
                        ClassMethodId clSup_ = a.getSupMethod();
                        ClassMethodId clSub_ = a.getSubMethod();
                        if (clSub_.eq(clSup_)) {
                            continue;
                        }
                        if (clSub_.eq(c)) {
                            next_.add(clSup_);
                            pairs_.add(a);
                        }
                    }
                }
                if (next_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            if (pairs_.isEmpty()) {
                continue;
            }
            builtAccOverridings_.put(o.getKey(), pairs_);
        }
        String voidType_ = stds_.getAliasVoid();
        for (EntryCust<MethodId,CustList<OverridingRelation>> o: builtAccOverridings_.entryList()) {
            for (OverridingRelation l: o.getValue()) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                GeneMethod sub_ = _context.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
                GeneMethod sup_ = _context.getMethodBodiesById(supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    addClass(_type.getAllOverridingMethods(), l.getRealId(), subId_);
                } else {
                    String retBase_ = sup_.getReturnType(stds_);
                    String retDerive_ = sub_.getReturnType(stds_);
                    String formattedRetDer_ = Templates.format(subId_.getClassName(), retDerive_, _context);
                    String formattedRetBase_ = Templates.format(supId_.getClassName(), retBase_, _context);
                    Mapping mapping_ = new Mapping();
                    mapping_.getMapping().putAllMap(vars_);
                    mapping_.setArg(formattedRetDer_);
                    mapping_.setParam(formattedRetBase_);
                    if (sup_.isFinalMethod()) {
                        FinalMethod err_;
                        err_ = new FinalMethod();
                        err_.setFileName(_type.getFullName());
                        if (sub_ instanceof MethodBlock) {
                            err_.setRc(((Block) sub_).getAttributes().getVal(ATTRIBUTE_NAME));
                        }
                        err_.setClassName(subId_.getClassName());
                        err_.setId(sub_.getId());
                        classesRef_.getErrorsDet().add(err_);
                        continue;
                    }
                    if (sub_.getAccess().ordinal() > sup_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(_type.getFullName());
                        if (sub_ instanceof MethodBlock) {
                            err_.setRc(((Block) sub_).getAttributes().getVal(ATTRIBUTE_ACCESS));
                        }
                        err_.setId(sub_.getId());
                        classesRef_.getErrorsDet().add(err_);
                        continue;
                    }
                    if (StringList.quickEq(retBase_, voidType_)) {
                        if (!StringList.quickEq(retDerive_, voidType_)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(_type.getFullName());
                            if (sub_ instanceof MethodBlock) {
                                err_.setRc(((Block) sub_).getAttributes().getVal(ATTRIBUTE_CLASS));
                            }
                            err_.setReturnType(retDerive_);
                            err_.setMethod(sub_.getId());
                            err_.setParentClass(supId_.getClassName());
                            classesRef_.getErrorsDet().add(err_);
                            continue;
                            //throw ex
                        }
                    } else if (!Templates.isCorrect(mapping_, _context)) {
                        //throw ex
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(_type.getFullName());
                        if (sub_ instanceof MethodBlock) {
                            err_.setRc(((Block) sub_).getAttributes().getVal(ATTRIBUTE_CLASS));
                        }
                        err_.setReturnType(retDerive_);
                        err_.setMethod(sub_.getId());
                        err_.setParentClass(supId_.getClassName());
                        classesRef_.getErrorsDet().add(err_);
                        continue;
                    }
                    addClass(_type.getAllOverridingMethods(), l.getRealId(), subId_);
                    addClass(_type.getAllOverridingMethods(), l.getRealId(), supId_);
                }
            }
        }
        StringList all_ = getAllGenericSuperTypes(_type,_context);
        String gene_ = _type.getGenericString();
        for (String s: all_) {
            String base_ = StringList.getAllTypes(s).first();
            GeneType r_ = _context.getClassBody(base_);
            for (GeneMethod m: ContextEl.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                String formattedSuper_ = Templates.getFullTypeByBases(gene_, s, _context);
                MethodId id_ = m.getId().format(formattedSuper_, _context);
                CustList<GeneMethod> mBases_ = _context.getMethodBodiesById(gene_, id_);
                if (!mBases_.isEmpty()) {
                    GeneMethod mBas_ = mBases_.first();
                    MethodId mId_ = mBas_.getId();
                    for (String d: _type.getDirectGenericSuperTypes(_context)) {
                        CustList<GeneMethod> mBasesSuper_ = TypeUtil.getMethodBodiesByFormattedId(_context, d, mId_);
                        if (mBasesSuper_.isEmpty()) {
                            continue;
                        }
                        if (mBasesSuper_.size() > 1) {
                            DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                            duplicate_.setFileName(_type.getFullName());
                            duplicate_.setRc(new RowCol());
                            duplicate_.setCommonSignature(mId_.getSignature());
                            duplicate_.setOtherType(d);
                            classesRef_.getErrorsDet().add(duplicate_);
                        }
                    }
                }
            }
        }
    }

    public static CustList<GeneConstructor> getConstructorBodiesByFormattedId(ContextEl _context, String _genericClassName, ConstructorId _id) {
        return getConstructorBodiesByFormattedId(_context, _genericClassName, _id.getParametersTypes(), _id.isVararg());
    }
    private static CustList<GeneConstructor> getConstructorBodiesByFormattedId(ContextEl _context, String _genericClassName, StringList _parametersTypes, boolean _vararg) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        int nbParams_ = _parametersTypes.size();
        for (GeneType c: _context.getClassBodies()) {
            if (!StringList.quickEq(c.getFullName(), base_)) {
                continue;
            }
            CustList<GeneConstructor> bl_ = ContextEl.getConstructorBlocks(c);
            for (GeneConstructor b: bl_) {
                EqList<ClassName> list_ = b.getId().getClassNames();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!b.isVarargs()) {
                        continue;
                    }
                } else {
                    if (b.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = Templates.format(_genericClassName, list_.get(i).getName(), _context);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(b);
            }
        }
        return methods_;
    }
    public static CustList<GeneConstructor> getConstructorBodiesById(String _genericClassName, ConstructorId _id, ContextEl _context) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        StringList paramTypes_ = _id.getParametersTypes();
        int nbParams_ = paramTypes_.size();
        boolean vararg_ = _id.isVararg();
        for (GeneType c: _context.getClassBodies()) {
            if (!StringList.quickEq(c.getFullName(), base_)) {
                continue;
            }
            CustList<GeneConstructor> bl_ = ContextEl.getConstructorBlocks(c);
            for (GeneConstructor b: bl_) {
                EqList<ClassName> list_ = b.getId().getClassNames();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (nbParams_ > 0 && vararg_) {
                    if (!b.isVarargs()) {
                        continue;
                    }
                } else {
                    if (b.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = list_.get(i).getName();
                    if (!StringList.quickEq(type_, paramTypes_.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(b);
            }
        }
        return methods_;
    }
    public static CustList<GeneMethod> getMethodBodiesByFormattedId(ContextEl _context, String _genericClassName, MethodId _id) {
        return getMethodBodiesByFormattedId(_context, _id.isStaticMethod(), _genericClassName, _id.getName(), _id.getParametersTypes(), _id.isVararg());
    }
    public static CustList<GeneMethod> getMethodBodiesByFormattedId(ContextEl _context, boolean _static, String _genericClassName, String _methodName, StringList _parametersTypes, boolean _vararg) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        StringList types_ = StringList.getAllTypes(_genericClassName);
        String base_ = types_.first();
        int nbParams_ = _parametersTypes.size();
        for (GeneType c: _context.getClassBodies()) {
            if (!StringList.quickEq(c.getFullName(), base_)) {
                continue;
            }
            CustList<GeneMethod> bl_ = ContextEl.getMethodBlocks(c);
            for (GeneMethod b: bl_) {
                if (!StringList.quickEq(_methodName, b.getName())) {
                    continue;
                }
                EqList<ClassName> list_ = b.getId().getClassNames();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (_static != b.isStaticMethod()) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!b.isVarargs()) {
                        continue;
                    }
                } else {
                    if (b.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = Templates.format(_genericClassName, list_.get(i).getName(), _context);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(b);
            }
        }
        return methods_;
    }
    public static StringList getAllGenericSuperTypes(GeneType _type,ContextEl _classes) {
        StringList list_ = new StringList();
        StringList vars_ = new StringList();
        for (TypeVar t: _type.getParamTypes()) {
            vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
        }
        StringList current_;
        if (vars_.isEmpty()) {
            current_ = new StringList(_type.getFullName());
        } else {
            current_ = new StringList(StringList.concat(_type.getFullName(),LT,vars_.join(SEP_TMP),GT));
        }
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = StringList.getAllTypes(c).first();
                StringList superTypes_ = _classes.getClassBody(baseType_).getDirectGenericSuperTypes(_classes);
                for (String t: superTypes_) {
                    String format_ = Templates.format(c, t, _classes);
                    list_.add(format_);
                    next_.add(format_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return list_;
    }
    public static StringMap<ClassMethodId> getConcreteMethodsToCall(GeneType _type,MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        String baseClassFound_ = _type.getFullName();
        for (GeneType c: _conf.getClassBodies()) {
            String name_ = c.getFullName();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, name_, _conf)) {
                continue;
            }
            if (!c.mustImplement()) {
                continue;
            }
            GeneClass subClassBlock_ = (GeneClass) c;
            StringList allBaseClasses_ = new StringList(name_);
            allBaseClasses_.addAllElts(subClassBlock_.getAllSuperClasses(_conf));
            boolean foundConcrete_ = false;
            for (String s: allBaseClasses_) {
                if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                GeneClass r_ = (GeneClass) _conf.getClassBody(s);
                String gene_ = r_.getGenericString();
                String v_ = Templates.getFullTypeByBases(gene_, baseClassFound_, _conf);
                MethodId l_ = _realId.format(v_, _conf);
                ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
                if (!ov_.contains(l_)) {
                    continue;
                }
                boolean found_ = false;
                StringList foundSuperClasses_ = new StringList();
                StringList allSuperClasses_ = new StringList(gene_);
                for (String t: r_.getAllSuperClasses(_conf)) {
                    allSuperClasses_.add(Templates.getFullTypeByBases(gene_, t, _conf));
                }
                EqList<ClassMethodId> list_ = ov_.getVal(l_);
                //pkg.ExTwo & pkg.Int3
                for (ClassMethodId t: list_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = StringList.getAllTypes(t_).first();
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    if (_conf.getClassBody(baseSuperType_) instanceof GeneInterface) {
                        continue;
                    }
                    foundSuperClasses_.add(t_);
                }
                if (!found_) {
                    continue;
                }
                String classNameFound_;
                MethodId realId_;
                foundSuperClasses_.sortElts(new ComparatorIndexes<String>(allSuperClasses_));
                if (foundSuperClasses_.isEmpty()) {
                    continue;
                }
                classNameFound_ = foundSuperClasses_.first();
                int i_ = 0;
                while (true) {
                    ClassMethodId methId_ = list_.get(i_);
                    if (StringList.quickEq(methId_.getClassName(), classNameFound_)) {
                        realId_ = methId_.getConstraints();
                        break;
                    }
                    i_++;
                }
                classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                if (_conf.getMethodBodiesById(classNameFound_, realId_).first().isAbstractMethod()) {
                    continue;
                }
                foundConcrete_ = true;
                eq_.put(name_, new ClassMethodId(classNameFound_, realId_));
                break;
            }
            if (foundConcrete_) {
                continue;
            }
            EqList<ClassMethodId> finalMethods_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> methods_ = new EqList<ClassMethodId>();
            for (String s: subClassBlock_.getAllInterfaces()) {
                if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                GeneType r_ = _conf.getClassBody(s);
                String gene_ = r_.getGenericString();
                String v_ = Templates.getFullTypeByBases(gene_, baseClassFound_, _conf);
                MethodId l_ = _realId.format(v_, _conf);
                ObjectMap<MethodId, EqList<ClassMethodId>> ov_ = r_.getAllOverridingMethods();
                if (!ov_.contains(l_)) {
                    continue;
                }
                EqList<ClassMethodId> foundSuperClasses_ = new EqList<ClassMethodId>();
                boolean found_ = false;
                EqList<ClassMethodId> list_ = ov_.getVal(l_);
                //pkg.ExTwo & pkg.Int3
                for (ClassMethodId t: list_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = StringList.getAllTypes(t_).first();
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, baseSuperType_, _conf)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                for (ClassMethodId t: foundSuperClasses_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = StringList.getAllTypes(t_).first();
                    GeneMethod method_ = _conf.getMethodBodiesById(baseSuperType_, t.getConstraints()).first();
                    if (method_.isAbstractMethod()) {
                        continue;
                    }
                    if (method_.isFinalMethod()) {
                        finalMethods_.add(t);
                    }
                    methods_.add(t);
                }
            }
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: finalMethods_) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
            if (list_.size() == 1) {
                String class_ = list_.first();
                String classBase_ = StringList.getAllTypes(class_).first();
                eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
            } else {
                defs_ = new StringMap<MethodId>();
                list_ = new StringList();
                for (ClassMethodId v: methods_) {
                    defs_.put(v.getClassName(), v.getConstraints());
                    list_.add(v.getClassName());
                }
                list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
                if (list_.size() == 1) {
                    String class_ = list_.first();
                    String classBase_ = StringList.getAllTypes(class_).first();
                    eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
                }
            }
        }
        return eq_;
    }

    public static StringList getCustomDirectSuperClasses(GeneType _type,ContextEl _context) {
        StringList direct_ = _type.getDirectSuperClasses(_context);
        direct_.removeAllObj(_context.getStandards().getAliasObject());
        return direct_;
    }
    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(GeneType _type, ContextEl _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (GeneMethod b: ContextEl.getMethodBlocks(_type)) {
            if (b.isStaticMethod()) {
                continue;
            }
            map_.put(b.getId(), new EqList<ClassMethodId>(new ClassMethodId(_type.getGenericString(), b.getId())));
        }
        for (String s: getAllGenericSuperTypes(_type, _classes)) {
            String base_ = StringList.getAllTypes(s).first();
            GeneType b_ = _classes.getClassBody(base_);
            for (GeneMethod b: ContextEl.getMethodBlocks(b_)) {
                if (b.isStaticMethod()) {
                    continue;
                }
                addClass(map_, b.getFormattedId(s, _classes), new ClassMethodId(s, b.getId()));
            }
        }
        return map_;
    }
    public static ObjectMap<MethodId, ClassMethodId> getLocalSignatures(GeneType _type, Classes _classes) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (GeneMethod b: ContextEl.getMethodBlocks(_type)) {
            map_.put(b.getId(), new ClassMethodId(_type.getGenericString(), b.getId()));
        }
        return map_;
    }
    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            ContextEl _conf) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
            EqList<ClassMethodId> out_ = new EqList<ClassMethodId>();
            for (String v: list_) {
                out_.add(new ClassMethodId(v, defs_.getVal(v)));
            }
            map_.put(e.getKey(), out_);
        }
        return map_;
    }
    private static void addClass(ObjectMap<MethodId, EqList<ClassMethodId>> _map, MethodId _key, ClassMethodId _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new EqList<ClassMethodId>(_class));
        }
    }
}
