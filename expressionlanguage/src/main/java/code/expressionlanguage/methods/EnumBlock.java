package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.FileRowCol;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.exceptions.CyclicCallingException;
import code.expressionlanguage.methods.exceptions.UndefinedSuperConstructorException;
import code.expressionlanguage.methods.util.BadAccessMethod;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.DuplicateParamMethod;
import code.expressionlanguage.methods.util.ReservedMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.graphs.Graph;

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList allInterfaces = new StringList();

    private final StringList allSortedInterfaces = new StringList();

    private final StringList allNeededSortedInterfaces = new StringList();

    public EnumBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        String superClass_ = _el.getAttribute(ATTRIBUTE_SUPER_CLASS);
        if (!superClass_.trim().isEmpty()) {
            getDirectSuperTypes().add(superClass_);
        }
        int i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_))) {
            getDirectSuperTypes().add(_el.getAttribute(StringList.concatNbs(ATTRIBUTE_CLASS,i_)));
            i_++;
        }
        getDirectSuperTypes().add(StringList.concat(PredefinedClasses.ENUM_PARAM,LT,getFullName(),GT));
    }

    public EnumBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, String _name, String _packageName, AccessEnum _access,
            String _templateDef, ObjectMap<FileRowCol, String> _directSuperTypes) {
        super(_importingPage, _indexChild, _m, _name, _packageName, _access, _templateDef, _directSuperTypes);
        getDirectSuperTypes().add(StringList.concat(PredefinedClasses.ENUM_PARAM,LT,getFullName(),GT));
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String stringType_ = stds_.getAliasString();
        for (MethodBlock m: Classes.getMethodBlocks(this)) {
            if (m.getId().eq(new MethodId(false, OperationNode.METH_NAME, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m.getId().eq(new MethodId(false, OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m.getId().eq(new MethodId(true, OperationNode.METH_VALUES, new EqList<ClassName>()))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
            if (m.getId().eq(new MethodId(true, OperationNode.METH_VALUEOF, new EqList<ClassName>(new ClassName(stringType_, false))))) {
                ReservedMethod r_ = new ReservedMethod();
                r_.setFileName(getFullName());
                r_.setRc(m.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                r_.setMethodeId(m.getId());
                _context.getClasses().getErrorsDet().add(r_);
            }
        }
        Classes classesRef_ = _context.getClasses();
        StringList classNames_ = getAllGenericSuperClasses(_context);
        String fullName_ = getFullName();
        for (String b: getCustomDirectSuperClasses(_context)) {
            ClassBlock bBl_ = (ClassBlock) classesRef_.getClassBody(b);
            AccessEnum acc_ = bBl_.getMaximumAccessConstructors(_context);
            if (acc_.ordinal() <= AccessEnum.PROTECTED.ordinal()) {
                continue;
            }
            if (acc_ == AccessEnum.PACKAGE) {
                if (StringList.quickEq(getPackageName(), bBl_.getPackageName())) {
                    continue;
                }
            }
            BadInheritedClass inherit_;
            inherit_ = new BadInheritedClass();
            inherit_.setClassName(fullName_);
            inherit_.setFileName(fullName_);
            inherit_.setRc(new RowCol());
            classesRef_.getErrorsDet().add(inherit_);
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof ConstructorBlock)) {
                continue;
            }
            ConstructorBlock c_ = (ConstructorBlock) b;
            for (String s: classNames_) {
                if (classesRef_.getConstructorBodiesByFormattedId(_context, s, c_.getId()).size() > 1) {
                    DuplicateParamMethod duplicate_ = new DuplicateParamMethod();
                    duplicate_.setFileName(getFullName());
                    duplicate_.setRc(new RowCol());
                    duplicate_.setCommonSignature(c_.getId().getSignature());
                    duplicate_.setOtherType(s);
                    classesRef_.getErrorsDet().add(duplicate_);
                }
            }
        }
        useSuperTypesOverrides(_context);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypes()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        String gene_ = getGenericString();
        StringList classes_ = new StringList(gene_);
        classes_.addAllElts(classNames_);
        String void_ = stds_.getAliasVoid();
        for (String s: getAllGenericInterfaces(_context)) {
            String base_ = StringList.getAllTypes(s).first();
            RootBlock r_ = classesRef_.getClassBody(base_);
            for (MethodBlock m: Classes.getMethodBlocks(r_)) {
                if (m.isStaticMethod()) {
                    continue;
                }
                String formattedSuper_ = Templates.getFullTypeByBases(gene_, s, _context);
                MethodId id_ = m.getId().format(formattedSuper_, _context);
                for (String c: classes_) {
                    CustList<MethodBlock> mBases_ = classesRef_.getMethodBodiesById(c, id_);
                    if (mBases_.isEmpty()) {
                        continue;
                    }
                    MethodBlock mBase_ = mBases_.first();
                    if (mBase_.isStaticMethod()) {
                        continue;
                    }
                    if (m.getAccess().ordinal() > mBase_.getAccess().ordinal()) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(getFullName());
                        err_.setRc(m.getAttributes().getVal(ATTRIBUTE_ACCESS));
                        err_.setId(m.getId());
                        classesRef_.getErrorsDet().add(err_);
                    }
                    String retBase_ = m.getReturnType(stds_);
                    String retDerive_ = mBase_.getReturnType(stds_);
                    String formattedRetDer_ = Templates.format(c, retDerive_, _context);
                    String formattedRetBase_ = Templates.format(s, retBase_, _context);
                    Mapping mapping_ = new Mapping();
                    mapping_.getMapping().putAllMap(vars_);
                    mapping_.setArg(formattedRetDer_);
                    mapping_.setParam(formattedRetBase_);
                    if (StringList.quickEq(retBase_, void_)) {
                        if (!StringList.quickEq(retDerive_, void_)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFullName());
                            err_.setRc(mBase_.getAttributes().getVal(ATTRIBUTE_CLASS));
                            err_.setReturnType(retDerive_);
                            err_.setMethod(mBase_.getId());
                            err_.setParentClass(c);
                            classesRef_.getErrorsDet().add(err_);
                            //throw ex
                        }
                    } else if (!Templates.isCorrect(mapping_, _context)) {
                        //throw ex
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFullName());
                        err_.setRc(mBase_.getAttributes().getVal(ATTRIBUTE_CLASS));
                        err_.setReturnType(retDerive_);
                        err_.setMethod(mBase_.getId());
                        err_.setParentClass(c);
                        classesRef_.getErrorsDet().add(err_);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public StringList getDirectGenericSuperTypes(ContextEl _classes) {
        return getDirectSuperTypes();
    }

    @Override
    public StringList getAllInterfaces() {
        return allInterfaces;
    }
    @Override
    public StringList getDirectInterfaces(ContextEl _classes) {
        StringList direct_ = new StringList();
        for (String s: getDirectGenericInterfaces(_classes)) {
            int index_ = s.indexOf(LT);
            if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
                direct_.add(s.substring(CustList.FIRST_INDEX, index_));
            } else {
                direct_.add(s);
            }
        }
        return direct_;
    }

    @Override
    public StringList getDirectGenericInterfaces(ContextEl _classes) {
        StringList interfaces_ = new StringList();
        for (String s: getDirectSuperTypes()) {
            String base_ = StringList.getAllTypes(s).first();
            if (_classes.getClasses().getClassBody(base_) instanceof InterfaceBlock) {
                interfaces_.add(s);
            }
        }
        return interfaces_;
    }

    @Override
    public StringList getAllNeededSortedInterfaces() {
        return allNeededSortedInterfaces;
    }

    @Override
    public StringList getAllSortedInterfaces() {
        return allSortedInterfaces;
    }

    @Override
    public void validateConstructors(ContextEl _cont) {
        boolean opt_ = optionalCallConstr(_cont);
        String idType_ = getFullName();
        ClassMetaInfo curMeta_ = _cont.getClasses().getClassMetaInfo(idType_, _cont);
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> c_;
        c_ = curMeta_.getConstructors();
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, e.getKey()).first();
            b_.setupInstancingStep(_cont);
        }
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, e.getKey()).first();
            if (b_.implicitConstr() && !opt_) {
                throw new UndefinedSuperConstructorException(_cont.joinPages());
            }
        }
        EqList<ConstructorId> l_ = new EqList<ConstructorId>();
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: c_.entryList()) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, e.getKey()).first();
            if (b_.getConstIdSameClass() != null) {
                l_.add(e.getKey());
            }
        }
        Graph<ConstructorEdge> graph_;
        graph_ = new Graph<ConstructorEdge>();
        for (ConstructorId f: l_) {
            ConstructorBlock b_ = _cont.getClasses().getConstructorBodiesById(idType_, f).first();
            ConstructorId co_ = b_.getConstIdSameClass();
            ConstructorEdge f_ = new ConstructorEdge(f);
            ConstructorEdge t_ = new ConstructorEdge(co_);
            graph_.addSegment(f_, t_);
        }
        EqList<ConstructorEdge> cycle_ = graph_.elementsCycle();
        if (!cycle_.isEmpty()) {
            //TODO souligner
            throw new CyclicCallingException(_cont.joinPages());
        }
    }

    private boolean optionalCallConstr(ContextEl _cont) {
        String superClass_ = getSuperClass(_cont);
        ClassMetaInfo clMeta_ = _cont.getClasses().getClassMetaInfo(superClass_, _cont);
        if (clMeta_ == null) {
            return true;
        }
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> m_;
        m_ = clMeta_.getConstructors();
        if (m_.isEmpty()) {
            return true;
        }
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: m_.entryList()) {
            CustList<ConstructorBlock> formatted_ = _cont.getClasses().getConstructorBodiesById(superClass_, e.getKey());
            if (!_cont.getClasses().canAccess(getFullName(), formatted_.first(), _cont)) {
                continue;
            }
            if (e.getKey().getParametersTypes().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_NAME, getFullDefinition());
        tr_.put(ATTRIBUTE_SUPER_CLASS, getGenericSuperClass(_context));
        int i_ = 0;
        for (String t: getDirectGenericInterfaces(_context)) {
            tr_.put(StringList.concatNbs(ATTRIBUTE_CLASS,i_), t);
            i_++;
        }
        return tr_;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_ENUM;
    }

    @Override
    public StringList getDirectGenericSuperClasses(ContextEl _classes) {
        StringList classes_ = new StringList();
        classes_.add(getGenericSuperClass(_classes));
        return classes_;
    }

    @Override
    public StringList getDirectSuperClasses(ContextEl _classes) {
        StringList classes_ = new StringList();
        String superClass_ = getGenericSuperClass(_classes);
        int index_ = superClass_.indexOf(LT);
        if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
            classes_.add(superClass_.substring(CustList.FIRST_INDEX, index_));
        } else {
            classes_.add(superClass_);
        }
        return classes_;
    }

    @Override
    public String getSuperClass(ContextEl _classes) {
        String superClass_ = getGenericSuperClass(_classes);
        int index_ = superClass_.indexOf(LT);
        if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
            return superClass_.substring(CustList.FIRST_INDEX, index_);
        }
        return superClass_;
    }

    @Override
    public String getGenericSuperClass(ContextEl _classes) {
        for (String s: getDirectSuperTypes()) {
            String base_ = StringList.getAllTypes(s).first();
            if (_classes.getClasses().getClassBody(base_) instanceof ClassBlock) {
                return s;
            }
        }
        return _classes.getStandards().getAliasObject();
    }

    @Override
    public RootBlock belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean mustImplement() {
        return true;
    }

    @Override
    public StringList getAllGenericSuperClasses(ContextEl _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        Classes classes_ = _classes.getClasses();
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = StringList.getAllTypes(s).first();
            if (classes_.getClassBody(base_) instanceof ClassBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        return allGenericSuperClasses_;
    }

    @Override
    public StringList getAllGenericInterfaces(ContextEl _classes) {
        StringList allSuperTypes_ = getAllGenericSuperTypes(_classes);
        Classes classes_ = _classes.getClasses();
        StringList allGenericInterfaces_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = StringList.getAllTypes(s).first();
            if (classes_.getClassBody(base_) instanceof InterfaceBlock) {
                allGenericInterfaces_.add(s);
            }
        }
        return allGenericInterfaces_;
    }
    @Override
    public StringList getAllSuperClasses(ContextEl _classes) {
        StringList superClasses_ = new StringList();
        String current_ = getFullName();
        String objectAlias_ = _classes.getStandards().getAliasObject();
        while (!StringList.quickEq(current_, objectAlias_)) {
            UniqueRootedBlock r_ = (UniqueRootedBlock) _classes.getClasses().getClassBody(current_);
            String superClass_ = r_.getSuperClass(_classes);
            superClasses_.add(superClass_);
            current_ = superClass_;
        }
        return superClasses_;
    }
}
