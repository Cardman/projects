package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.FileRowCol;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadFieldName;
import code.expressionlanguage.methods.util.BadMethodName;
import code.expressionlanguage.methods.util.BadNumberArgMethod;
import code.expressionlanguage.methods.util.BadParamName;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.DuplicateConstructor;
import code.expressionlanguage.methods.util.DuplicateField;
import code.expressionlanguage.methods.util.DuplicateMethod;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.DuplicateParamName;
import code.expressionlanguage.methods.util.FinalMethod;
import code.expressionlanguage.methods.util.IncompatibilityReturnType;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassFormattedMethodId;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.OverridingRelation;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.sml.Element;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorIndexes;

public abstract class RootBlock extends BracedBlock implements AccessibleBlock {

    private final String name;

    private final String packageName;

    private final AccessEnum access;

    private final String templateDef;

    private ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();

    private final StringList directSuperTypes = new StringList();

    private ObjectMap<FileRowCol, String> rowColDirectSuperTypes;

    private String realTemplateDef;

    private String realName;

    private String realPackageName;

    RootBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        name = _el.getAttribute(ATTRIBUTE_NAME);
        packageName = _el.getAttribute(ATTRIBUTE_PACKAGE);
        access = AccessEnum.getAccessByName(_el.getAttribute(ATTRIBUTE_ACCESS));
        templateDef = _el.getAttribute(ATTRIBUTE_TEMPLATE_DEF);
    }

    RootBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, String _name, String _packageName, AccessEnum _access, String _templateDef, ObjectMap<FileRowCol, String> _directSuperTypes) {
        super(_importingPage, _indexChild, _m);
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        name = StringList.removeAllSpaces(_name);
        packageName = StringList.removeAllSpaces(_packageName);
        access = _access;
        realTemplateDef = _templateDef;
        realName = _name;
        realPackageName = _packageName;
        templateDef = StringList.removeAllSpaces(_templateDef);
        rowColDirectSuperTypes = _directSuperTypes;
        for (String t: _directSuperTypes.values()) {
            directSuperTypes.add(StringList.removeAllSpaces(t));
        }
    }

    protected StringList getDirectSuperTypes() {
        return directSuperTypes;
    }

    public abstract StringList getAllGenericSuperClasses(ContextEl _classes);

    public abstract StringList getAllSuperClasses();
    public abstract StringList getAllSuperTypes();

    public abstract StringList getDirectGenericSuperClasses(ContextEl _classes);

    public final StringList getCustomDirectSuperClasses(ContextEl _context) {
        StringList direct_ = getDirectSuperClasses(_context);
        direct_.removeAllObj(_context.getStandards().getAliasObject());
        return direct_;
    }

    /** Copy the list*/
    public abstract StringList getDirectSuperClasses(ContextEl _classes);

    public abstract boolean isFinalType();
    public abstract boolean isAbstractType();

    public abstract StringList getAllGenericInterfaces(ContextEl _classes);

    public abstract StringList getAllInterfaces();

    public abstract boolean mustImplement();

    public void buildMapParamType() {
        paramTypesMap = new StringMap<TypeVar>();
        for (TypeVar t: paramTypes) {
            paramTypesMap.put(t.getName(), t);
        }
    }

    public int getIndex(String _varType) {
        int len_ = paramTypes.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (StringList.quickEq(paramTypes.get(i).getName(), _varType)) {
                return i;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    public StringMap<TypeVar> getParamTypesMap() {
        return paramTypesMap;
    }

    public CustList<TypeVar> getParamTypes() {
        return paramTypes;
    }

    public String getGenericString() {
        String base_ = getFullName();
        if (paramTypes.isEmpty()) {
            return base_;
        }
        StringBuilder generic_ = new StringBuilder(base_);
        StringList vars_ = new StringList();
        for (TypeVar t:paramTypes) {
            vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
        }
        generic_.append(Templates.TEMPLATE_BEGIN);
        generic_.append(vars_.join(Templates.TEMPLATE_SEP));
        generic_.append(Templates.TEMPLATE_END);
        return generic_.toString();
    }

    public String getFullDefinition() {
        return StringList.concat(getFullName(),getTemplateDef());
    }

    public String getTemplateDef() {
        return templateDef;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    public final String getFullName() {
        String packageName_ = getPackageName();
        if (packageName_.isEmpty()) {
            return getName();
        }
        return StringList.concat(packageName_,DOT,getName());
    }

    protected void validateClassNames(ContextEl _context) {
        CustList<Block> bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            if (b instanceof Returnable) {
                continue;
            }
            if (b instanceof InfoBlock) {
                continue;
            }
            if (b instanceof AloneBlock) {
                continue;
            }
            //TODO intern classes
            RowCol where_ = b.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
            String tagName_ = b.getTagName();
            UnexpectedTagName unexp_ = new UnexpectedTagName();
            unexp_.setFileName(getFullName());
            unexp_.setFoundTag(tagName_);
            unexp_.setRc(where_);
            _context.getClasses().getErrorsDet().add(unexp_);
            //string (class name) - row col - tag name
        }
    }

    public final void validateIds(ContextEl _context) {
        validateClassNames(_context);
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        EqList<ConstructorId> idConstructors_ = new EqList<ConstructorId>();
        StringList idsField_ = new StringList();
        String className_ = getFullName();
        LgNames stds_ = _context.getStandards();
        StringMap<StringList> varsGl_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            varsGl_.put(t.getName(), t.getConstraints());
        }
        for (EntryCust<String, String> n: getClassNames(_context).entryList()) {
            if (StringList.quickEq(n.getKey(), ATTRIBUTE_NAME)) {
                continue;
            }
            String classNameLoc_ = n.getValue();
            if (!Templates.correctClassParts(classNameLoc_, varsGl_, _context)) {
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(classNameLoc_);
                un_.setFileName(className_);
                un_.setRc(getRowCol(0, _context.getTabWidth(), n.getKey()));
                _context.getClasses().getErrorsDet().add(un_);
            }
        }
        CustList<Block> bl_;
        bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            boolean staticContext_;
            if (b instanceof MethodBlock) {
                staticContext_ = ((MethodBlock)b).isStaticMethod();
            } else if (b instanceof InfoBlock) {
                staticContext_ = ((InfoBlock)b).isStaticField();
            } else if (b instanceof ConstructorBlock) {
                staticContext_ = false;
            } else {
                staticContext_ = ((AloneBlock)b).isStaticContext();
            }
            CustList<Block> blLoc_ = Classes.getSortedDescNodes(b);
            StringMap<StringList> vars_ = new StringMap<StringList>();
            if (!staticContext_) {
                for (TypeVar t: getParamTypes()) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            for (Block a: blLoc_) {
                for (EntryCust<String, String> n: a.getClassNames(_context).entryList()) {
                    String classNameLoc_ = n.getValue();
                    if (StringList.quickEq(classNameLoc_, stds_.getAliasVoid())) {
                        if (a == b) {
                            if (b instanceof MethodBlock) {
                                if (!StringList.quickEq(n.getKey(), ATTRIBUTE_CLASS)) {
                                    UnknownClassName un_ = new UnknownClassName();
                                    un_.setClassName(classNameLoc_);
                                    un_.setFileName(className_);
                                    un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                                    _context.getClasses().getErrorsDet().add(un_);
                                }
                                continue;
                            }
                        }
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(classNameLoc_);
                        un_.setFileName(className_);
                        un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                        _context.getClasses().getErrorsDet().add(un_);
                        continue;
                    }
                    if (!Templates.correctClassParts(classNameLoc_, vars_, _context)) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(classNameLoc_);
                        un_.setFileName(className_);
                        un_.setRc(b.getRowCol(0, _context.getTabWidth(), n.getKey()));
                        _context.getClasses().getErrorsDet().add(un_);
                    }
                }
            }
        }
        for (Block b: bl_) {
            if (b instanceof Returnable) {
                Returnable method_ = (Returnable) b;
                String name_ = method_.getName();
                if (method_ instanceof MethodBlock) {
                    MethodBlock m_ = (MethodBlock) method_;
                    if (!StringList.isWord(name_) || m_.isConcreteInstanceDerivableMethod() != m_.isNormalMethod()) {
                        RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
                        BadMethodName badMeth_ = new BadMethodName();
                        badMeth_.setFileName(className_);
                        badMeth_.setRc(r_);
                        badMeth_.setName(name_);
                        _context.getClasses().getErrorsDet().add(badMeth_);
                    }
                }
                StringList types_ = method_.getParametersTypes();
                int len_ = types_.size();
                EqList<ClassName> pTypes_ = new EqList<ClassName>();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    String n_ = types_.get(i);
                    pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                }
                if (name_.isEmpty()) {
                    name_ = className_;
                }
                String sgn_ = method_.getSignature();
                if (method_ instanceof MethodBlock) {
                    boolean st_ = ((MethodBlock)method_).isStaticMethod();
                    MethodId id_ = new MethodId(st_, name_, pTypes_);
                    for (MethodId m: idMethods_) {
                        if (m.eq(id_)) {
                            RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setRc(r_);
                            duplicate_.setFileName(className_);
                            duplicate_.setId(id_);
                            _context.getClasses().getErrorsDet().add(duplicate_);
                        }
                    }
                    idMethods_.add(id_);
                }
                if (method_ instanceof ConstructorBlock) {
                    ConstructorId idCt_ = new ConstructorId(name_, pTypes_);
                    for (ConstructorId m: idConstructors_) {
                        if (m.eq(idCt_)) {
                            RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                            DuplicateConstructor duplicate_;
                            duplicate_ = new DuplicateConstructor();
                            duplicate_.setRc(r_);
                            duplicate_.setFileName(className_);
                            ConstructorId id_ = new ConstructorId(name_, pTypes_);
                            duplicate_.setId(id_);
                            _context.getClasses().getErrorsDet().add(duplicate_);
                        }
                    }
                    idConstructors_.add(idCt_);
                }
                StringList l_ = method_.getParametersNames();
                if (l_.size() != len_) {
                    BadNumberArgMethod b_;
                    b_ = new BadNumberArgMethod();
                    b_.setFileName(className_);
                    b_.setRc(method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    b_.setNbTypes(len_);
                    b_.setNbVars(l_.size());
                    b_.setId(sgn_);
                    _context.getClasses().getErrorsDet().add(b_);
                }
                StringList seen_ = new StringList();
                int i_ = CustList.FIRST_INDEX;
                for (String v: l_) {
                    String attr_ = StringList.concatNbs(ATTRIBUTE_VAR,i_);
                    if (!StringList.isWord(v)) {
                        BadParamName b_;
                        b_ = new BadParamName();
                        b_.setFileName(className_);
                        b_.setRc(method_.getRowCol(0, _context.getTabWidth(), attr_));
                        b_.setParamName(v);
                        _context.getClasses().getErrorsDet().add(b_);
                    } else if (seen_.containsStr(v)){
                        DuplicateParamName b_;
                        b_ = new DuplicateParamName();
                        b_.setFileName(className_);
                        b_.setRc(method_.getRowCol(0, _context.getTabWidth(), attr_));
                        b_.setParamName(v);
                        _context.getClasses().getErrorsDet().add(b_);
                    } else {
                        seen_.add(v);
                    }
                    i_++;
                }
            } else if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                String name_ = method_.getFieldName();
                InfoBlock m_ = (InfoBlock) b;
                if (!StringList.isWord(name_)) {
                    RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
                    BadFieldName badMeth_ = new BadFieldName();
                    badMeth_.setFileName(className_);
                    badMeth_.setRc(r_);
                    badMeth_.setName(name_);
                    _context.getClasses().getErrorsDet().add(badMeth_);
                }
                for (String m: idsField_) {
                    if (StringList.quickEq(m, name_)) {
                        RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                        DuplicateField duplicate_;
                        duplicate_ = new DuplicateField();
                        duplicate_.setRc(r_);
                        duplicate_.setFileName(className_);
                        duplicate_.setId(name_);
                        _context.getClasses().getErrorsDet().add(duplicate_);
                    }
                }
                idsField_.add(name_);
            }
        }
    }
    public abstract void setupBasicOverrides(ContextEl _context);

    final void useSuperTypesOverrides(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        LgNames stds_ = _context.getStandards();
        ObjectMap<MethodId, EqList<ClassMethodId>> allOv_ = getAllInstanceSignatures(_context);
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
                    RootBlock root_ = classesRef_.getClassBody(typeName_);
                    for (String u:root_.getAllGenericSuperTypes(_context)) {
                        String superType_ = Templates.format(templClass_, u, _context);
                        String superTypeName_ = StringList.getAllTypes(u).first();
                        RootBlock super_ = classesRef_.getClassBody(superTypeName_);
                        for (MethodBlock m: Classes.getMethodBlocks(super_)) {
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
            MethodBlock sub_ = classesRef_.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
            if (classesRef_.canAccess(getFullName(), sub_, _context)) {
                accOverridings_.put(o.getRealId(), allOverridings_.getVal(o.getRealId()));
            }
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        ObjectMap<MethodId,CustList<OverridingRelation>> filterAccOverridings_ = new ObjectMap<MethodId,CustList<OverridingRelation>>();
        for (EntryCust<MethodId,CustList<OverridingRelation>> o: accOverridings_.entryList()) {
            CustList<OverridingRelation> relations_ = new CustList<OverridingRelation>();
            for (OverridingRelation l: o.getValue()) {
                ClassMethodId subId_ = l.getSubMethod();
                ClassMethodId supId_ = l.getSupMethod();
                MethodBlock sub_ = classesRef_.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
                MethodBlock sup_ = classesRef_.getMethodBodiesById(supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    if (classesRef_.canAccess(getFullName(), sub_, _context)) {
                        relations_.add(l);
                    }
                } else if (classesRef_.canAccess(subId_.getClassName(), sup_, _context)) {
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
                MethodBlock sub_ = classesRef_.getMethodBodiesById(subId_.getClassName(), subId_.getConstraints()).first();
                MethodBlock sup_ = classesRef_.getMethodBodiesById(supId_.getClassName(), supId_.getConstraints()).first();
                if (subId_.eq(supId_)) {
                    addClass(getAllOverridingMethods(), l.getRealId(), subId_);
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
                        err_.setFileName(getFullName());
                        err_.setRc(sub_.getAttributes().getVal(ATTRIBUTE_NAME));
                        err_.setClassName(subId_.getClassName());
                        err_.setId(sub_.getId());
                        classesRef_.getErrorsDet().add(err_);
                        continue;
                    }
                    if (sub_.getAccess().ordinal() > sup_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(sub_.getAttributes().getVal(ATTRIBUTE_ACCESS));
                        err_.setId(sub_.getId());
                        classesRef_.getErrorsDet().add(err_);
                        continue;
                    }
                    if (StringList.quickEq(retBase_, voidType_)) {
                        if (!StringList.quickEq(retDerive_, voidType_)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFullName());
                            err_.setRc(sub_.getAttributes().getVal(ATTRIBUTE_CLASS));
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
                        err_.setFileName(getFullName());
                        err_.setRc(sub_.getAttributes().getVal(ATTRIBUTE_CLASS));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(sub_.getId());
                        err_.setParentClass(supId_.getClassName());
                        classesRef_.getErrorsDet().add(err_);
                        continue;
                    }
                    addClass(getAllOverridingMethods(), l.getRealId(), subId_);
                    addClass(getAllOverridingMethods(), l.getRealId(), supId_);
                }
            }
        }
        StringList all_ = getAllGenericSuperTypes(_context);
        String gene_ = getGenericString();
        for (String s: all_) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                String formattedSuper_ = Templates.getFullTypeByBases(gene_, s, _context);
                MethodId id_ = m.getId().format(formattedSuper_, _context);
                CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesById(gene_, id_);
                if (!mBases_.isEmpty()) {
                    MethodBlock mBas_ = mBases_.first();
                    MethodId mId_ = mBas_.getId();
                    for (String d: getDirectGenericSuperTypes(_context)) {
                        CustList<MethodBlock> mBasesSuper_ = classesRef_.getMethodBodiesByFormattedId(_context, d, mId_);
                        if (mBasesSuper_.isEmpty()) {
                            continue;
                        }
                        if (mBasesSuper_.size() > 1) {
                            DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                            duplicate_.setFileName(getFullName());
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
    public abstract StringList getDirectGenericSuperTypes(ContextEl _classes);

    public final StringList getAllGenericSuperTypes(ContextEl _classes) {
        StringList list_ = new StringList();
        StringList vars_ = new StringList();
        Classes classes_ = _classes.getClasses();
        for (TypeVar t: getParamTypes()) {
            vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
        }
        StringList current_;
        if (vars_.isEmpty()) {
            current_ = new StringList(getFullName());
        } else {
            current_ = new StringList(StringList.concat(getFullName(),LT,vars_.join(SEP_TMP),GT));
        }
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = StringList.getAllTypes(c).first();
                StringList superTypes_ = classes_.getClassBody(baseType_).getDirectGenericSuperTypes(_classes);
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
    public final void checkCompatibilityBounds(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        ObjectMap<MethodId, ClassMethodId> localSignatures_;
        localSignatures_ = new ObjectMap<MethodId, ClassMethodId>();
        ObjectMap<MethodId, EqList<ClassMethodId>> signatures_;
        signatures_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
            mapping_.getMapping().put(t.getName(), t.getConstraints());
        }
        LgNames stds_ = _context.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        for (TypeVar t: getParamTypes()) {
            StringList upper_ = mapping_.getAllUpperBounds(t.getName(),objectClassName_);
            for (String c: upper_) {
                String base_ = StringList.getAllTypes(c).first();
                if (classesRef_.isCustomType(base_)) {
                    RootBlock r_ = classesRef_.getClassBody(base_);
                    for (EntryCust<MethodId, EqList<ClassMethodId>> e: r_.getAllOverridingMethods().entryList()) {
                        for (ClassMethodId s: e.getValue()) {
                            String c_ = s.getClassName();
                            MethodBlock m_ = classesRef_.getMethodBodiesById(c_, s.getConstraints()).first();
                            if (!classesRef_.canAccess(getFullName(), m_, _context)) {
                                continue;
                            }
                            if (m_.isStaticMethod()) {
                                continue;
                            }
                            MethodId id_ = m_.getFormattedId(c, _context);
                            String formattedType_ = Templates.format(c, c_, _context);
                            addClass(signatures_, id_, new ClassMethodId(formattedType_, m_.getId()));
                        }
                    }
                } else {
                    StandardType clBound_ = stds_.getStandards().getVal(base_);
                    for (StandardMethod m: clBound_.getMethods().values()) {
                        if (m.isStaticMethod()) {
                            continue;
                        }
                        MethodId id_ = m.getId();
                        MethodId realId_ = m.getId();
                        addClass(signatures_, id_, new ClassMethodId(c, realId_));
                    }
                }
            }
        }
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: signatures_.entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            StringMap<StringList> map_ = Classes.getBaseParams(list_);
            for (EntryCust<String,StringList> m:map_.entryList()) {
                if (m.getValue().size() > 1) {
                    for (String s: m.getValue()) {
                        MethodId id_ = defs_.getVal(s);
                        MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s, id_).first();
                        IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                        err_.setFileName(getFullName());
                        err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                        err_.setReturnType(mDer_.getReturnType(stds_));
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(s);
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context);
        ObjectMap<MethodId, EqList<ClassMethodId>> er_;
        er_ = RootBlock.areCompatible(localSignatures_, vars_, ov_, _context);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType(stds_));
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_, vars_, _context);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType(stds_));
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
    }

    public final void checkCompatibility(ContextEl _context) {
        ObjectMap<MethodId, ClassMethodId> localSignatures_ = getLocalSignatures(_context.getClasses());
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        LgNames stds_ = _context.getStandards();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: getAllOverridingMethods().entryList()) {
            StringMap<MethodId> defs_ = new StringMap<MethodId>();
            StringList list_ = new StringList();
            for (ClassMethodId v: e.getValue()) {
                defs_.put(v.getClassName(), v.getConstraints());
                list_.add(v.getClassName());
            }
            StringMap<StringList> map_ = Classes.getBaseParams(list_);
            for (EntryCust<String,StringList> m:map_.entryList()) {
                if (m.getValue().size() > 1) {
                    for (String s: m.getValue()) {
                        MethodId id_ = defs_.getVal(s);
                        MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s, id_).first();
                        IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                        err_.setFileName(getFullName());
                        err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                        err_.setReturnType(mDer_.getReturnType(stds_));
                        err_.setMethod(mDer_.getId());
                        err_.setParentClass(s);
                        _context.getClasses().getErrorsDet().add(err_);
                    }
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = getAllOverridingMethods();
        ov_ = RootBlock.getAllOverridingMethods(ov_, _context);
        ObjectMap<MethodId, EqList<ClassMethodId>> er_;
        er_ = RootBlock.areCompatible(localSignatures_, vars_, ov_, _context);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType(stds_));
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_, vars_, _context);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = _context.getClasses().getMethodBodiesById(s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFullName());
                err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                err_.setReturnType(mDer_.getReturnType(stds_));
                err_.setMethod(mDer_.getId());
                err_.setParentClass(s_);
                _context.getClasses().getErrorsDet().add(err_);
            }
        }
    }
    public final void checkImplements(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        EqList<ClassFormattedMethodId> abstractMethods_ = new EqList<ClassFormattedMethodId>();
        boolean concreteClass_ = false;
        if (mustImplement()) {
            concreteClass_ = true;
        }
        StringList allSuperClass_ = getAllGenericSuperTypes(_context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof MethodBlock)) {
                continue;
            }
            MethodBlock mDer_ = (MethodBlock) b;
            MethodId idFor_ = mDer_.getId();
            if (mDer_.isAbstractMethod()) {
                if (mDer_.getFirstChild() != null) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                    err_.setSgn(idFor_.getSignature());
                    err_.setClassName(getFullName());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
        }
        if (concreteClass_) {
            for (MethodBlock b: Classes.getMethodBlocks(this)) {
                MethodBlock mDer_ = b;
                MethodId idFor_ = mDer_.getId();
                if (mDer_.isAbstractMethod()) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_NAME));
                    err_.setSgn(idFor_.getSignature());
                    err_.setClassName(getFullName());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
            for (String s: allSuperClass_) {
                String base_ = StringList.getAllTypes(s).first();
                RootBlock superBl_ = classesRef_.getClassBody(base_);
                for (MethodBlock m: Classes.getMethodBlocks(superBl_)) {
                    if (m.isAbstractMethod()) {
                        abstractMethods_.add(new ClassFormattedMethodId(s, m.getId()));
                    }
                }
            }
            boolean isEnum_ = this instanceof EnumBlock;
            for (ClassFormattedMethodId m: abstractMethods_) {
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_NAME, new EqList<ClassName>()))) {
                    continue;
                }
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                    continue;
                }
                String baseClass_ = m.getClassName();
                baseClass_ = StringList.getAllTypes(baseClass_).first();
                RootBlock info_ = classesRef_.getClassBody(baseClass_);
                StringMap<ClassMethodId> map_ = info_.getConcreteMethodsToCall(m.getConstraints(), _context);
                if (!map_.contains(getFullName())) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setClassName(m.getClassName());
                    err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                    err_.setSgn(m.getConstraints().getSignature());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> signatures_;
        signatures_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        StringList allInterfaces_ = getAllGenericInterfaces(_context);
        for (String s: allInterfaces_) {
            String base_ = StringList.getAllTypes(s).first();
            InterfaceBlock superBl_ = (InterfaceBlock) classesRef_.getClassBody(base_);
            ObjectMap<MethodId, EqList<ClassMethodId>> signaturesInt_;
            signaturesInt_ = superBl_.getAllInstanceSignatures(_context);
            for (EntryCust<MethodId, EqList<ClassMethodId>> m: signaturesInt_.entryList()) {
                MethodId key_ = m.getKey().format(s, _context);
                for (ClassMethodId c: m.getValue()) {
                    String c_ = c.getClassName();
                    String formatCl_ = Templates.format(s, c_, _context);
                    addClass(signatures_, key_, new ClassMethodId(formatCl_, c.getConstraints()));
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context);
        if (concreteClass_) {
            abstractMethods_ = RootBlock.remainingMethodsToImplement(ov_, getFullName(), _context);
            boolean isEnum_ = this instanceof EnumBlock;
            for (ClassFormattedMethodId m: abstractMethods_) {
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_NAME, new EqList<ClassName>()))) {
                    continue;
                }
                if (isEnum_ && m.getConstraints().eq(new MethodId(false, OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                    continue;
                }
                String baseClass_ = m.getClassName();
                baseClass_ = StringList.getAllTypes(baseClass_).first();
                RootBlock info_ = classesRef_.getClassBody(baseClass_);
                MethodId realId_ = m.getConstraints();
                StringMap<ClassMethodId> map_ = info_.getConcreteMethodsToCallFromClass(realId_, _context);
                if (!map_.contains(getFullName())) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFullName());
                    err_.setClassName(m.getClassName());
                    err_.setRc(getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME));
                    err_.setSgn(m.getConstraints().getSignature());
                    classesRef_.getErrorsDet().add(err_);
                }
            }
        }
    }
    public final StringList getDirectSubTypes(ContextEl _conf) {
        StringList subTypes_ = new StringList();
        Classes classes_ = _conf.getClasses();
        String baseClassFound_ = getFullName();
        for (RootBlock c: classes_.getClassBodies()) {
            String name_ = c.getFullName();
            RootBlock r_ = classes_.getClassBody(name_);
            if (r_ instanceof InterfaceBlock) {
                if (((InterfaceBlock) r_).getDirectSuperInterfaces().containsStr(baseClassFound_)) {
                    subTypes_.add(name_);
                }
            } else {
                if (r_.getDirectSuperClasses(_conf).containsStr(baseClassFound_)) {
                    subTypes_.add(name_);
                }
            }
        }
        return subTypes_;
    }
    public final ObjectMap<MethodId, EqList<ClassMethodId>> getAllInstanceSignatures(ContextEl _classes) {
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        map_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                if (method_.isStaticMethod()) {
                    continue;
                }
                map_.put(method_.getId(), new EqList<ClassMethodId>(new ClassMethodId(getGenericString(), method_.getId())));
            }
        }
        Classes classes_ = _classes.getClasses();
        for (String s: getAllGenericSuperTypes(_classes)) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock b_ = classes_.getClassBody(base_);
            for (Block b: Classes.getDirectChildren(b_)) {
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    if (method_.isStaticMethod()) {
                        continue;
                    }
                    addClass(map_, method_.getFormattedId(s, _classes), new ClassMethodId(s, method_.getId()));
                }
            }
        }
        return map_;
    }
    public final ObjectMap<MethodId, ClassMethodId> getLocalSignatures(Classes _classes) {
        ObjectMap<MethodId, ClassMethodId> map_;
        map_ = new ObjectMap<MethodId, ClassMethodId>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock) b;
                map_.put(method_.getId(), new ClassMethodId(getGenericString(), method_.getId()));
            }
        }
        return map_;
    }
    public final StringMap<ClassMethodId> getConcreteMethodsToCall(MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        Classes classes_ = _conf.getClasses();
        String baseClassFound_ = getFullName();
        for (RootBlock c: classes_.getClassBodies()) {
            String name_ = c.getFullName();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, name_, _conf)) {
                continue;
            }
            if (!c.mustImplement()) {
                continue;
            }
            UniqueRootedBlock subClassBlock_ = (UniqueRootedBlock) c;
            StringList allBaseClasses_ = new StringList(name_);
            allBaseClasses_.addAllElts(subClassBlock_.getAllSuperClasses(_conf));
            boolean foundConcrete_ = false;
            for (String s: allBaseClasses_) {
                if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                UniqueRootedBlock r_ = (UniqueRootedBlock) classes_.getClassBody(s);
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
                    if (classes_.getClassBody(baseSuperType_) instanceof InterfaceBlock) {
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
                if (classes_.getMethodBodiesById(classNameFound_, realId_).first().isAbstractMethod()) {
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
                RootBlock r_ = classes_.getClassBody(s);
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
                    MethodBlock method_ = classes_.getMethodBodiesById(baseSuperType_, t.getConstraints()).first();
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
    public final StringMap<ClassMethodId> getConcreteMethodsToCallFromClass(MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        Classes classes_ = _conf.getClasses();
        String baseClassFound_ = getFullName();
        for (RootBlock c: classes_.getClassBodies()) {
            String name_ = c.getFullName();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, name_, _conf)) {
                continue;
            }
            if (!classes_.getClassBody(name_).mustImplement()) {
                continue;
            }
            UniqueRootedBlock subClassBlock_ = (UniqueRootedBlock) classes_.getClassBody(name_);
            StringList allBaseClasses_ = new StringList(name_);
            allBaseClasses_.addAllElts(subClassBlock_.getAllSuperClasses(_conf));
            for (String s: allBaseClasses_) {
                if (!PrimitiveTypeUtil.canBeUseAsArgument(baseClassFound_, s, _conf)) {
                    continue;
                }
                UniqueRootedBlock r_ = (UniqueRootedBlock) classes_.getClassBody(s);
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
                    if (classes_.getClassBody(baseSuperType_) instanceof InterfaceBlock) {
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
                if (classes_.getMethodBodiesById(classNameFound_, realId_).first().isAbstractMethod()) {
                    continue;
                }
                eq_.put(name_, new ClassMethodId(classNameFound_, realId_));
                break;
            }
        }
        return eq_;
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

    public static ObjectMap<MethodId, EqList<ClassMethodId>> areCompatible(
            ObjectMap<MethodId, ClassMethodId> _localMethodIds,
            StringMap<StringList> _vars,
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds, ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        ObjectMap<MethodId, EqList<ClassMethodId>> output_;
        output_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        LgNames stds_ = _context.getStandards();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            EqList<ClassMethodId> classes_ = e.getValue();
            Mapping mapping_ = new Mapping();
            mapping_.getMapping().putAllMap(_vars);
            if (_localMethodIds.contains(e.getKey())) {
                //overridden by this interface
                ClassMethodId subInt_ = _localMethodIds.getVal(e.getKey());
                String name_ = subInt_.getClassName();
                MethodBlock sub_ = classesRef_.getMethodBodiesById(name_, subInt_.getConstraints()).first();
                if (sub_.isStaticMethod()) {
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    StringList retClasses_ = new StringList();
                    for (ClassMethodId s: e.getValue()) {
                        String supName_ = s.getClassName();
                        MethodBlock sup_ = classesRef_.getMethodBodiesById(supName_, s.getConstraints()).first();
                        if (sup_.isStaticMethod()) {
                            continue;
                        }
                        retClasses_.add(sup_.getReturnType(stds_));
                    }
                    if (!retClasses_.isEmpty() && PrimitiveTypeUtil.getSubslass(retClasses_, vars_, _context).isEmpty()) {
                        for (ClassMethodId c: classes_) {
                            addClass(output_, e.getKey(), c);
                        }
                    }
                    continue;
                }
                String subType_ = sub_.getReturnType(stds_);
                subType_ = Templates.format(name_, subType_, _context);
                mapping_.setArg(subType_);
                for (ClassMethodId s: e.getValue()) {
                    String supName_ = s.getClassName();
                    MethodBlock sup_ = classesRef_.getMethodBodiesById(supName_, s.getConstraints()).first();
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String formattedSup_ = Templates.format(supName_, sup_.getReturnType(stds_), _context);
                    mapping_.setParam(formattedSup_);
                    if (StringList.quickEq(formattedSup_, subType_)) {
                        continue;
                    }
                    if (!Templates.isCorrect(mapping_, _context)) {
                        addClass(output_, e.getKey(), subInt_);
                        addClass(output_, e.getKey(), s);
                    }
                }
                continue;
            }
            StringList retClasses_ = new StringList();
            for (ClassMethodId s: e.getValue()) {
                String name_ = s.getClassName();
                String base_ = StringList.getAllTypes(name_).first();
                if (!classesRef_.isCustomType(base_)) {
                    StandardType clBound_ = stds_.getStandards().getVal(base_);
                    for (StandardMethod m: clBound_.getMethods().values()) {
                        if (m.isStaticMethod()) {
                            continue;
                        }
                        MethodId id_ = m.getId();
                        if (!id_.eq(cst_)) {
                            continue;
                        }
                        retClasses_.add(m.getReturnType());
                    }
                    continue;
                }
                MethodBlock sup_ = classesRef_.getMethodBodiesById(name_, s.getConstraints()).first();
                if (sup_.isStaticMethod()) {
                    continue;
                }
                String ret_ = sup_.getReturnType(stds_);
                retClasses_.add(Templates.format(name_, ret_, _context));
            }
            if (!retClasses_.isEmpty()) {
                if (PrimitiveTypeUtil.getSubslass(retClasses_, _vars, _context).isEmpty()) {
                    for (ClassMethodId c: classes_) {
                        addClass(output_, e.getKey(), c);
                    }
                } else {
                    StringMap<StringList> map_ = Classes.getBaseParams(retClasses_);
                    for (EntryCust<String,StringList> m:map_.entryList()) {
                        if (m.getValue().size() > 1) {
                            for (ClassMethodId c: classes_) {
                                addClass(output_, e.getKey(), c);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return output_;
    }

    public static ObjectMap<MethodId, EqList<ClassMethodId>> areModifierCompatible(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds, StringMap<StringList> _vars, ContextEl _context) {
        ObjectMap<MethodId, EqList<ClassMethodId>> output_;
        output_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        Classes classes_ = _context.getClasses();
        LgNames stds_ = _context.getStandards();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            EqList<ClassMethodId> fClasses_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> aClasses_ = new EqList<ClassMethodId>();
            for (ClassMethodId s: e.getValue()) {
                String name_ = s.getClassName();
                String base_ = StringList.getAllTypes(name_).first();
                if (classes_.getClassBody(base_) == null) {
                    continue;
                }
                MethodBlock sup_ = classes_.getMethodBodiesById(name_, s.getConstraints()).first();
                if (sup_.isStaticMethod()) {
                    continue;
                }
                if (sup_.isAbstractMethod()) {
                    aClasses_.add(s);
                }
                if (sup_.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            if (fClasses_.size() > 1) {
                for (ClassMethodId c: fClasses_) {
                    addClass(output_, cst_, c);
                }
                continue;
            }
            if (fClasses_.size() > 0 && aClasses_.size() > 0) {
                if (fClasses_.size() == 1) {
                    String name_ = fClasses_.first().getClassName();
                    String base_ = StringList.getAllTypes(name_).first();
                    if (classes_.getClassBody(base_) instanceof ClassBlock) {
                        continue;
                    }
                }
                for (ClassMethodId c: fClasses_) {
                    addClass(output_, cst_, c);
                }
                for (ClassMethodId c: aClasses_) {
                    addClass(output_, cst_, c);
                }
                continue;
            }
            if (fClasses_.size() == 1) {
                Mapping map_ = new Mapping();
                ClassMethodId subInt_ = fClasses_.first();
                String subIntName_ = subInt_.getClassName();
                map_.getMapping().putAllMap(_vars);
                MethodBlock sub_ = classes_.getMethodBodiesById(subIntName_, subInt_.getConstraints()).first();
                String subType_ = sub_.getReturnType(stds_);
                subType_ = Templates.format(subIntName_, subType_, _context);
                map_.setParam(subType_);
                for (ClassMethodId s: e.getValue()) {
                    String s_ = s.getClassName();
                    MethodBlock sup_ = classes_.getMethodBodiesById(s_, s.getConstraints()).first();
                    if (sup_.isStaticMethod()) {
                        continue;
                    }
                    String supType_ = sup_.getReturnType(stds_);
                    String formattedSupType_ = Templates.format(s_, supType_, _context);
                    map_.setArg(formattedSupType_);
                    if (StringList.quickEq(formattedSupType_, subType_)) {
                        continue;
                    }
                    if (!Templates.isCorrect(map_, _context)) {
                        addClass(output_, cst_, subInt_);
                        addClass(output_, cst_, s);
                    }
                }
            }
        }
        return output_;
    }
    public static EqList<ClassFormattedMethodId> remainingMethodsToImplement(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            String _fullName,
            ContextEl _context) {
        EqList<ClassFormattedMethodId> rem_ = new EqList<ClassFormattedMethodId>();
        Classes classes_ = _context.getClasses();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            int nbConcrete_ = 0;
            int nbFinal_ = 0;
            for (ClassMethodId f: e.getValue()) {
                String f_ = f.getClassName();
                MethodBlock method_ = classes_.getMethodBodiesById(f_, f.getConstraints()).first();
                if (method_.isStaticMethod()) {
                    continue;
                }
                if (method_.isFinalMethod() && classes_.canAccess(_fullName, method_, _context)) {
                    nbFinal_++;
                }
                if (method_.isConcreteMethod()) {
                    if (classes_.canAccess(_fullName, method_, _context)) {
                        nbConcrete_++;
                    }
                }
            }
            if (nbConcrete_ > 1 && nbFinal_ == 0) {
                for (ClassMethodId f: e.getValue()) {
                    String f_ = f.getClassName();
                    ClassFormattedMethodId id_ = new ClassFormattedMethodId(f_, f.getConstraints());
                    rem_.add(id_);
                }
            }
        }
        return rem_;
    }
    
    protected static void addClass(ObjectMap<MethodId, EqList<ClassMethodId>> _map, MethodId _key, ClassMethodId _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
            _map.getVal(_key).removeDuplicates();
        } else {
            _map.put(_key, new EqList<ClassMethodId>(_class));
        }
    }

    public abstract StringList getAllSortedInterfaces();

    public abstract StringList getAllNeededSortedInterfaces();

}
