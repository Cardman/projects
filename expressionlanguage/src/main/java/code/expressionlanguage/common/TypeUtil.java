package code.expressionlanguage.common;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AloneBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.OverridingRelation;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorIndexes;

public final class TypeUtil {

    public static final String VAR_TYPE = "$var";

    private TypeUtil() {
    }

    public static void buildInherits(ContextEl _context, StringList _types, boolean _cust) {
        for (String c: _types) {
            GeneType dBl_ = _context.getClassBody(c);
            buildInherits(dBl_, _context);
        }
        for (GeneType c: _context.getClassBodies()) {
            if (!_types.containsStr(c.getFullName())) {
                continue;
            }
            if (c instanceof GeneClass) {
                c.getAllSuperTypes().addAllElts(((GeneClass)c).getAllSuperClasses(_context));
                c.getAllSuperTypes().addAllElts(((GeneClass)c).getAllInterfaces());
            } else if (c instanceof GeneInterface){
                c.getAllSuperTypes().addAllElts(((GeneInterface)c).getAllSuperClasses());
            }
        }
        if (_cust) {
            Classes classes_ = _context.getClasses();
            for (RootBlock c: classes_.getClassBodies()) {
                RootBlock bl_ = c;
                _context.getAnalyzing().setCurrentBlock(bl_);
                bl_.buildMapParamType(_context);
            }
            for (RootBlock c: classes_.getClassBodies()) {
                RootBlock bl_ = c;
                _context.getAnalyzing().setCurrentBlock(bl_);
                bl_.buildDirectGenericSuperTypes(_context);
            }
        }
    }
    public static void checkInterfaces(ContextEl _context, StringList _types) {
        Classes classes_ = _context.getClasses();
        for (RootBlock c: classes_.getClassBodies()) {
            RootBlock bl_ = c;
            _context.getAnalyzing().setCurrentBlock(bl_);
            String d_ = c.getFullName();
            StringList ints_ = bl_.getStaticInitInterfaces();
            int len_ = ints_.size();
            for (int i = 0; i < len_; i++) {
                int offset_ = bl_.getStaticInitInterfacesOffset().get(i);
                String base_ = ContextEl.removeDottedSpaces(ints_.get(i));
                _context.getAnalyzing().setCurrentBlock(bl_);
                _context.getAnalyzing().setOffset(offset_);
                base_ = _context.resolveCorrectType(base_, true);
                RootBlock r_ = classes_.getClassBody(base_);
                if (r_ == null) {
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(base_);
                    undef_.setFileName(d_);
                    undef_.setRc(_context.getAnalyzing().getTrace());
                    classes_.addError(undef_);
                    continue;
                }
                if (!(r_ instanceof InterfaceBlock)) {
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    String n_ = base_;
                    enum_.setClassName(n_);
                    enum_.setFileName(d_);
                    enum_.setRc(_context.getAnalyzing().getTrace());
                    classes_.addError(enum_);
                } else {
                    bl_.getStaticInitImportedInterfaces().add(base_);
                }
            }
            for (int i = 0; i < len_; i++) {
                String sup_ = ContextEl.removeDottedSpaces(ints_.get(i));
                int offsetSup_ = bl_.getStaticInitInterfacesOffset().get(i);
                _context.getAnalyzing().setCurrentBlock(bl_);
                _context.getAnalyzing().setOffset(offsetSup_);
                sup_ = _context.resolveCorrectType(sup_, true);
                RootBlock rs_ = classes_.getClassBody(sup_);
                if (rs_ == null) {
                    continue;
                }
                for (int j = i + 1; j < len_; j++) {
                    String sub_ = ContextEl.removeDottedSpaces(ints_.get(j));
                    int offsetSub_ = bl_.getStaticInitInterfacesOffset().get(j);
                    _context.getAnalyzing().setCurrentBlock(bl_);
                    _context.getAnalyzing().setOffset(offsetSub_);
                    sub_ = _context.resolveCorrectType(sub_, true);
                    rs_ = classes_.getClassBody(sub_);
                    if (rs_ == null) {
                        continue;
                    }
                    if (PrimitiveTypeUtil.canBeUseAsArgument(sub_, sup_, _context)) {
                        BadInheritedClass undef_;
                        undef_ = new BadInheritedClass();
                        undef_.setClassName(sub_);
                        undef_.setFileName(d_);
                        int offset_ = bl_.getStaticInitInterfacesOffset().get(j);
                        undef_.setRc(bl_.getRowCol(0, offset_));
                        classes_.addError(undef_);
                    }
                }
            }
        }
        for (String c: _types) {
            GeneType bl_ = _context.getClassBody(c);
            if (!(bl_ instanceof GeneClass)) {
                continue;
            }
            if (!(bl_ instanceof RootBlock)) {
                continue;
            }
            RootBlock block_ = (RootBlock) bl_;
            StringList ints_ = block_.getStaticInitImportedInterfaces();
            StringList trimmedInt_ = new StringList();
            for (String i: ints_) {
                trimmedInt_.add(i);
            }
            UniqueRootedBlock un_ = (UniqueRootedBlock)bl_;
            StringList all_ = bl_.getAllInterfaces();
            StringList allCopy_ = new StringList(all_);
            allCopy_.removeAllElements(_context.getStandards().getPredefinedInterfacesInitOrder());
            RootBlock superType_ = classes_.getClassBody(un_.getSuperClass(_context));
            if (superType_ != null) {
                allCopy_.removeAllElements(superType_.getAllInterfaces());
            }
            StringList filteredStatic_ = new StringList();
            for (String i: allCopy_) {
                RootBlock int_ = classes_.getClassBody(i);
                for (Block b: Classes.getDirectChildren(int_)) {
                    if (b instanceof NamedFunctionBlock) {
                        continue;
                    }
                    if (b instanceof GeneField) {
                        GeneField a_ = (GeneField) b;
                        String name_ = a_.getFieldName();
                        if (!a_.isStaticField()) {
                            continue;
                        }
                        if (_context.getClasses().getStaticField(new ClassField(i, name_)) == null) {
                            filteredStatic_.add(i);
                        }
                    }
                    if (b instanceof AloneBlock) {
                        AloneBlock a_ = (AloneBlock) b;
                        if (a_.isStaticContext()) {
                            filteredStatic_.add(i);
                        }
                    }
                }
            }
            if (!StringList.equalsSet(filteredStatic_, trimmedInt_)) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(c);
                undef_.setFileName(c);
                undef_.setRc(block_.getRowCol(0, 0));
                classes_.addError(undef_);
            }
        }
    }
    public static void buildInherits(GeneType _type,ContextEl _context) {
        String typeName_ = _type.getFullName();
        String aliasObject_ = _context.getStandards().getAliasObject();
        if (_type instanceof GeneClass) {
            GeneClass type_ = (GeneClass) _context.getClassBody(typeName_);
            typeName_ = type_.getSuperClass(_context);
            while (true) {
                _type.getAllSuperClasses().add(typeName_);
                if (StringList.quickEq(typeName_, aliasObject_)) {
                    break;
                }
                type_ = (GeneClass) _context.getClassBody(typeName_);
                typeName_ = type_.getSuperClass(_context);
            }
            typeName_ = _type.getFullName();
            type_ = (GeneClass) _context.getClassBody(typeName_);
            StringList allSuperInterfaces_ = new StringList(type_.getDirectInterfaces(_context));
            StringList currentSuperInterfaces_ = new StringList(type_.getDirectInterfaces(_context));
            for (String s: _type.getAllSuperClasses()) {
                allSuperInterfaces_.addAllElts(((GeneClass)_context.getClassBody(s)).getDirectInterfaces(_context));
                currentSuperInterfaces_.addAllElts(((GeneClass)_context.getClassBody(s)).getDirectInterfaces(_context));
            }
            while (true) {
                StringList newSuperInterfaces_ = new StringList();
                for (String c: currentSuperInterfaces_) {
                    if (StringList.quickEq(c, aliasObject_)) {
                        continue;
                    }
                    GeneInterface superType_ = (GeneInterface) _context.getClassBody(c);
                    for (String s: superType_.getDirectSuperClasses(_context)) {
                        newSuperInterfaces_.add(s);
                        allSuperInterfaces_.add(s);
                    }
                }
                if (newSuperInterfaces_.isEmpty()) {
                    break;
                }
                currentSuperInterfaces_ = newSuperInterfaces_;
            }
            allSuperInterfaces_.removeAllObj(aliasObject_);
            allSuperInterfaces_.removeDuplicates();
            type_.getAllInterfaces().addAllElts(allSuperInterfaces_);
        } else if (_type instanceof GeneInterface){
            GeneInterface type_ = (GeneInterface) _context.getClassBody(typeName_);
            StringList allSuperInterfaces_ = new StringList(type_.getDirectSuperClasses(_context));
            StringList currentSuperInterfaces_ = new StringList(type_.getDirectSuperClasses(_context));
            while (true) {
                StringList newSuperInterfaces_ = new StringList();
                for (String c: currentSuperInterfaces_) {
                    if (StringList.quickEq(c, aliasObject_)) {
                        continue;
                    }
                    GeneInterface superType_ = (GeneInterface) _context.getClassBody(c);
                    for (String s: superType_.getDirectSuperClasses(_context)) {
                        newSuperInterfaces_.add(s);
                        allSuperInterfaces_.add(s);
                    }
                }
                if (newSuperInterfaces_.isEmpty()) {
                    break;
                }
                currentSuperInterfaces_ = newSuperInterfaces_;
            }
            allSuperInterfaces_.removeDuplicates();
            type_.getAllSuperClasses().addAllElts(allSuperInterfaces_);
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
                visited_.add(Templates.getIdFromAllTypes(t.getClassName()));
            }
            while (true) {
                CustList<ClassMethodId> next_ = new CustList<ClassMethodId>();
                CustList<OverridingRelation> newpairs_ = new CustList<OverridingRelation>();
                for (ClassMethodId c: current_) {
                    String templClass_ = c.getClassName();
                    String typeName_ = Templates.getIdFromAllTypes(templClass_);
                    GeneType root_ = _context.getClassBody(typeName_);
                    for (String u:getAllGenericSuperTypes(root_,_context)) {
                        String superType_ = Templates.format(templClass_, u, _context);
                        String superTypeName_ = Templates.getIdFromAllTypes(u);
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
                    String superTypeId_ = Templates.getIdFromAllTypes(superType_);
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
        for (TypeVar t: _type.getParamTypesMapValues()) {
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
                    String retBase_ = sup_.getImportedReturnType();
                    String retDerive_ = sub_.getImportedReturnType();
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
                            err_.setRc(((MethodBlock) sub_).getRowCol(0, ((MethodBlock) sub_).getNameOffset()));
                        }
                        err_.setClassName(subId_.getClassName());
                        err_.setId(sub_.getId());
                        classesRef_.addError(err_);
                        continue;
                    }
                    if (sub_.getAccess().ordinal() > sup_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(_type.getFullName());
                        if (sub_ instanceof MethodBlock) {
                            err_.setRc(((MethodBlock) sub_).getRowCol(0, ((MethodBlock) sub_).getAccessOffset()));
                        }
                        err_.setId(sub_.getId());
                        classesRef_.addError(err_);
                        continue;
                    }
                    if (StringList.quickEq(retBase_, voidType_)) {
                        if (!StringList.quickEq(retDerive_, voidType_)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(_type.getFullName());
                            if (sub_ instanceof MethodBlock) {
                                err_.setRc(((MethodBlock) sub_).getRowCol(0, ((MethodBlock) sub_).getReturnTypeOffset()));
                            }
                            err_.setReturnType(retDerive_);
                            err_.setMethod(sub_.getId());
                            err_.setParentClass(supId_.getClassName());
                            classesRef_.addError(err_);
                            continue;
                        }
                    } else if (!Templates.isCorrect(mapping_, _context)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(_type.getFullName());
                        if (sub_ instanceof MethodBlock) {
                            err_.setRc(((MethodBlock) sub_).getRowCol(0, ((MethodBlock) sub_).getReturnTypeOffset()));
                        }
                        err_.setReturnType(retDerive_);
                        err_.setMethod(sub_.getId());
                        err_.setParentClass(supId_.getClassName());
                        classesRef_.addError(err_);
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
            String base_ = Templates.getIdFromAllTypes(s);
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
                            if (m instanceof MethodBlock) {
                                duplicate_.setRc(((MethodBlock)m).getRowCol(0, ((MethodBlock) m).getNameOffset()));
                            }
                            duplicate_.setCommonSignature(mId_.getSignature());
                            duplicate_.setOtherType(d);
                            classesRef_.addError(duplicate_);
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
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        int nbParams_ = _parametersTypes.size();
        for (GeneType c: _context.getClassBodies()) {
            if (!StringList.quickEq(c.getFullName(), base_)) {
                continue;
            }
            CustList<GeneConstructor> bl_ = ContextEl.getConstructorBlocks(c);
            for (GeneConstructor b: bl_) {
                StringList list_ = b.getId().getParametersTypes();
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
                    String type_ = Templates.format(_genericClassName, list_.get(i), _context);
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
    public static CustList<GeneConstructor> getConstructorBodiesById(String _genericClassName, ConstructorId _id, Analyzable _context) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        StringList paramTypes_ = _id.getParametersTypes();
        int nbParams_ = paramTypes_.size();
        boolean vararg_ = _id.isVararg();
        for (GeneType c: _context.getClassBodies()) {
            if (!StringList.quickEq(c.getFullName(), base_)) {
                continue;
            }
            CustList<GeneConstructor> bl_ = ContextEl.getConstructorBlocks(c);
            for (GeneConstructor b: bl_) {
                StringList list_ = b.getId().getParametersTypes();
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
                    String type_ = list_.get(i);
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
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
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
                StringList list_ = b.getId().getParametersTypes();
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
                    String type_ = Templates.format(_genericClassName, list_.get(i), _context);
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
    public static StringList getAllGenericSuperTypes(GeneType _type,Analyzable _classes) {
        StringList list_ = new StringList();
        StringList current_ = new StringList(_type.getGenericString());
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = Templates.getIdFromAllTypes(c);
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
            allBaseClasses_.addAllElts(subClassBlock_.getAllSuperClasses());
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
                for (String t: r_.getAllSuperClasses()) {
                    allSuperClasses_.add(Templates.getFullTypeByBases(gene_, t, _conf));
                }
                EqList<ClassMethodId> list_ = ov_.getVal(l_);
                //pkg.ExTwo & pkg.Int3
                for (ClassMethodId t: list_) {
                    String t_ = t.getClassName();
                    String baseSuperType_ = Templates.getIdFromAllTypes(t_);
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
                classNameFound_ = Templates.getIdFromAllTypes(classNameFound_);
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
                    String baseSuperType_ = Templates.getIdFromAllTypes(t_);
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
                    String baseSuperType_ = Templates.getIdFromAllTypes(t_);
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
                String classBase_ = Templates.getIdFromAllTypes(class_);
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
                    String classBase_ = Templates.getIdFromAllTypes(class_);
                    eq_.put(name_, new ClassMethodId(classBase_, defs_.getVal(class_)));
                }
            }
        }
        return eq_;
    }

    public static ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(GeneType _type, ContextEl _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (GeneMethod b: ContextEl.getMethodBlocks(_type)) {
            if (b.isStaticMethod()) {
                continue;
            }
            MethodId m_ = b.getId();
            map_.put(m_, new EqList<ClassMethodId>(new ClassMethodId(_type.getGenericString(), m_)));
        }
        for (String s: getAllGenericSuperTypes(_type, _classes)) {
            String base_ = Templates.getIdFromAllTypes(s);
            GeneType b_ = _classes.getClassBody(base_);
            for (GeneMethod b: ContextEl.getMethodBlocks(b_)) {
                if (b.isStaticMethod()) {
                    continue;
                }
                MethodId m_ = b.getId();
                addClass(map_, b.getFormattedId(s, _classes), new ClassMethodId(s, m_));
            }
        }
        return map_;
    }
    public static ObjectMap<MethodId, ClassMethodId> getLocalSignatures(GeneType _type, ContextEl _classes) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (GeneMethod b: ContextEl.getMethodBlocks(_type)) {
            MethodId m_ = b.getId();
            map_.put(m_, new ClassMethodId(_type.getGenericString(), m_));
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
