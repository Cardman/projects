package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassFormattedMethodId;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.graphs.Graph;

public abstract class RootBlock extends BracedBlock implements GeneType, AccessingImportingBlock, AnnotableBlock {

    private final String name;

    private final String packageName;

    private final AccessEnum access;

    private int accessOffset;

    private final String templateDef;

    private StringList imports = new StringList();

    private Numbers<Integer> importsOffset = new Numbers<Integer>();

    private ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();

    private final StringList directSuperTypes = new StringList();
    private final StringList importedDirectBaseSuperTypes = new StringList();

    private NatTreeMap<Integer, String> rowColDirectSuperTypes;
    private NatTreeMap<Integer, Boolean> explicitDirectSuperTypes = new NatTreeMap<Integer, Boolean>();

    private String realName;

    private String realPackageName;

    private int idRowCol;

    private int categoryOffset;

    private StringList staticInitInterfaces = new StringList();
    private StringList staticInitImportedInterfaces = new StringList();
    private Numbers<Integer> staticInitInterfacesOffset = new Numbers<Integer>();
    private Numbers<Integer> ancestorsIndexes = new Numbers<Integer>();

    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Numbers<Integer> annotationsIndexes = new Numbers<Integer>();
    private final StringList allGenericSuperTypes = new StringList();

    RootBlock(ContextEl _importingPage,
            BracedBlock _m, int _idRowCol, int _categoryOffset ,String _name,
            String _packageName, OffsetAccessInfo _access, String _templateDef,
            NatTreeMap<Integer, String> _directSuperTypes, OffsetsBlock _offset) {
        super(_m, _offset);
        categoryOffset = _categoryOffset;
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        name = _name.trim();
        packageName = ContextEl.removeDottedSpaces(_packageName);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        templateDef = _templateDef;
        realName = _name;
        realPackageName = _packageName;
        rowColDirectSuperTypes = _directSuperTypes;
        idRowCol = _idRowCol;
        for (EntryCust<Integer, String> t: _directSuperTypes.entryList()) {
            String type_ = ContextEl.removeDottedSpaces(t.getValue());
            directSuperTypes.add(type_);
            ancestorsIndexes.add(-1);
            explicitDirectSuperTypes.put(t.getKey(), true);
        }
    }

    @Override
    public GeneType getOuter() {
        RootBlock t = this;
        RootBlock o = this;
        while (t != null) {
            o = t;
            t = t.getParentType();
        }
        return o;
    }
    public String getWildCardElement() {
        StringList allElements_ = new StringList();
        for (Block e: Classes.getDirectChildren(this)) {
            if (e instanceof ElementBlock) {
                String type_ = ((ElementBlock)e).getImportedClassName();
                allElements_.add(type_);
            }
        }
        allElements_.removeDuplicates();
        String className_;
        if (allElements_.size() == 1) {
            className_ = allElements_.first();
        } else {
            className_ = getWildCardString();
        }
        return className_;
    }
    @Override
    public boolean withoutInstance() {
        return isStaticType();
    }
    @Override
    public abstract boolean isStaticType();

    public final StringList getAllGenericSuperTypes() {
        return allGenericSuperTypes;
    }

    public abstract StringList getImportedDirectSuperTypes();
    public final StringList getDepends(Analyzable _an) {
        NatTreeMap<Integer, String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        StringList varsList_ = new StringList();
        for (RootBlock r: getSelfAndParentTypes()) {
            for (TypeVar t: r.paramTypes) {
                varsList_.add(t.getName());
            }
        }
        _an.getAvailableVariables().clear();
        _an.getAvailableVariables().addAllElts(varsList_);
        Numbers<Integer> bi_ = _an.getCurrentBadIndexes();
        StringList all_ = new StringList();
        int i_ = 0;
        for (String s: getDirectSuperTypes()) {
            int index_ = rcs_.getKey(i_);
            bi_.clear();
            StringList list_ = PartTypeUtil.processAnalyzeDepends(s, i_,_an, this, true);
            i_++;
            for (int i : bi_) {
                BadIndexInParser bip_ = new BadIndexInParser();
                bip_.setIndex(index_+i);
                bip_.setIndexFile(_an.getCurrentLocationIndex());
                bip_.setFileName(_an.getCurrentFileName());
                _an.getClasses().addError(bip_);
            }
            all_.addAllElts(list_);
        }
        all_.removeDuplicates();
        return all_;
    }
    public Numbers<Integer> getAncestorsIndexes() {
        return ancestorsIndexes;
    }
    public NatTreeMap<Integer, Boolean> getExplicitDirectSuperTypes() {
        return explicitDirectSuperTypes;
    }

    public StringList getStaticInitInterfaces() {
        return staticInitInterfaces;
    }

    public StringList getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
    }

    public Numbers<Integer> getStaticInitInterfacesOffset() {
        return staticInitInterfacesOffset;
    }
    @Override
    public void buildAnnotations(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        for (String a: annotations) {
            Calculation c_ = Calculation.staticCalculation(true);
            annotationsOps.add(ElUtil.getAnalyzedOperations(a, _context, c_));
        }
    }
    @Override
    public void reduce(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
    }
    @Override
    public StringList getAnnotations() {
        return annotations;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }
    @Override
    public Numbers<Integer> getAnnotationsIndexes() {
        return annotationsIndexes;
    }
    @Override
    public StringList getImports() {
        return imports;
    }

    public Numbers<Integer> getImportsOffset() {
        return importsOffset;
    }

    public NatTreeMap<Integer, String> getRowColDirectSuperTypes() {
        return rowColDirectSuperTypes;
    }

    public int getIdRowCol() {
        return idRowCol;
    }

    public StringList getDirectSuperTypes() {
        return directSuperTypes;
    }

    public StringList getImportedDirectBaseSuperTypes() {
        return importedDirectBaseSuperTypes;
    }
    protected void checkAccess(ContextEl _context) {
        useSuperTypesOverrides(_context);
        Classes classesRef_ = _context.getClasses();
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allGenericSuperTypes) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (classesRef_.getClassBody(base_) instanceof ClassBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        String gene_ = getGenericString();
        StringList classes_ = new StringList(gene_);
        classes_.addAllElts(allGenericSuperClasses_);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: allOverridingMethods.entryList()) {
            CustList<ClassMethodId> locGeneInt_ = new CustList<ClassMethodId>();
            CustList<ClassMethodId> locGeneCl_ = new CustList<ClassMethodId>();
            for (ClassMethodId c: e.getValue()) {
                String base_ = Templates.getIdFromAllTypes(c.getClassName());
                RootBlock r_ = classesRef_.getClassBody(base_);
                if (r_ instanceof InterfaceBlock) {
                    locGeneInt_.add(c);
                }
                if (r_ instanceof ClassBlock) {
                    locGeneCl_.add(c);
                }
            }
            for (ClassMethodId i: locGeneInt_) {
                String name_ = i.getClassName();
                MethodId id_ = i.getConstraints();
                GeneMethod supInt_ = _context.getMethodBodiesById(name_, id_).first();
                for (ClassMethodId c: locGeneCl_) {
                    String nameCl_ = c.getClassName();
                    MethodId idCl_ = c.getConstraints();
                    GeneMethod supCl_ = _context.getMethodBodiesById(nameCl_, idCl_).first();
                    if (supInt_.getAccess().isStrictMoreAccessibleThan(supCl_.getAccess())) {
                        BadAccessMethod err_;
                        err_ = new BadAccessMethod();
                        err_.setFileName(getFile().getFileName());
                        err_.setIndexFile(((MethodBlock)supCl_).getAccessOffset());
                        err_.setId(supInt_.getId().getSignature(_context));
                        classesRef_.addError(err_);
                    }
                    String retInt_ = supInt_.getImportedReturnType();
                    String retBase_ = supCl_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(nameCl_, retBase_, _context);
                    String formattedRetBase_ = Templates.quickFormat(name_, retInt_, _context);
                    if (!Templates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFile().getFileName());
                        err_.setIndexFile(((MethodBlock)supCl_).getReturnTypeOffset());
                        err_.setReturnType(retBase_);
                        err_.setMethod(supCl_.getId().getSignature(_context));
                        err_.setParentClass(nameCl_);
                        classesRef_.addError(err_);
                    }
                }
            }
        }
    }
    public final RootBlock getParentType() {
        BracedBlock p_ = getParent();
        while (!(p_ instanceof RootBlock)) {
            if (p_ == null) {
                return null;
            }
            p_ = p_.getParent();
        }
        return (RootBlock) p_;
    }
    public final CustList<RootBlock> getAllParentTypesReverse() {
        return getAllParentTypes().getReverse();
    }
    public final CustList<RootBlock> getAllParentTypes() {
        CustList<RootBlock> pars_ = new CustList<RootBlock>();
        RootBlock c_ = getParentType();
        while (c_ != null) {
            pars_.add(c_);
            c_ = c_.getParentType();
        }
        return pars_;
    }
    public final CustList<RootBlock> getSelfAndParentTypes() {
        CustList<RootBlock> pars_ = new CustList<RootBlock>();
        RootBlock c_ = this;
        while (true) {
            pars_.add(c_);
            if (c_.isStaticType()) {
                break;
            }
            c_ = c_.getParentType();
        }
        return pars_.getReverse();
    }
    public void buildMapParamType(ContextEl _analyze) {
        paramTypesMap = new StringMap<TypeVar>();
        for (RootBlock r: getSelfAndParentTypes()) {
            int rc_ = r.idRowCol;
            for (TypeVar t: r.paramTypes) {
                StringList const_ = new StringList();
                for (String c: t.getConstraints()) {
                    const_.add(_analyze.resolveTypeMapping(c,r, rc_));
                }
                TypeVar t_ = new TypeVar();
                t_.setConstraints(const_);
                t_.setName(t.getName());
                paramTypesMap.put(t.getName(), t_);
            }
        }
    }
    public void buildErrorMapParamType(ContextEl _analyze) {
        paramTypesMap = new StringMap<TypeVar>();
        for (RootBlock r: getSelfAndParentTypes()) {
            for (TypeVar t: r.paramTypes) {
                StringList const_ = new StringList();
                const_.add(_analyze.getStandards().getAliasObject());
                TypeVar t_ = new TypeVar();
                t_.setConstraints(const_);
                t_.setName(t.getName());
                paramTypesMap.put(t.getName(), t_);
            }
        }
    }

    @Override
    public CustList<TypeVar> getParamTypesMapValues() {
        return paramTypesMap.values();
    }

    @Override
    public CustList<TypeVar> getParamTypes() {
        return paramTypes;
    }

    public final String getWildCardString() {
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        addPkgIfNotEmpty(pkg_, generic_);
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        for (RootBlock r: pars_.first().getAllParentTypesReverse()) {
            generic_.append(r.getName());
            generic_.append("..");
        }
        for (RootBlock r: pars_) {
            generic_.append(r.getName());
            if (!r.paramTypes.isEmpty()) {
                StringList vars_ = new StringList();
                int count_ = r.paramTypes.size();
                for (int i = 0; i < count_; i++) {
                    vars_.add(Templates.SUB_TYPE);
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(vars_.join(Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            appendSepInner(generic_, r);
        }
        return generic_.toString();
    }

    private void appendSepInner(StringBuilder _generic, RootBlock _r) {
        if (_r != this) {
            _generic.append("..");
        }
    }

    private static void addPkgIfNotEmpty(String _pkg, StringBuilder _generic) {
        if (!_pkg.isEmpty()) {
            _generic.append(_pkg);
            _generic.append(DOT);
        }
    }

    @Override
    public final String getGenericString() {
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        addPkgIfNotEmpty(pkg_, generic_);
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        for (RootBlock r: pars_.first().getAllParentTypesReverse()) {
            generic_.append(r.getName());
            generic_.append("..");
        }
        for (RootBlock r: pars_) {
            generic_.append(r.getName());
            if (!r.paramTypes.isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t:r.paramTypes) {
                    vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(vars_.join(Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            appendSepInner(generic_, r);
        }
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

    @Override
    public String getPackageName() {
        Block par_ = this;
        while (par_.getParent() instanceof RootBlock) {
            par_ = par_.getParent();
        }
        return ((RootBlock)par_).packageName;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    @Override
    public final String getFullName() {
        String packageName_ = getPackageName();
        Block par_ = this;
        StringList names_ = new StringList(getName());
        while (par_.getParent() instanceof RootBlock) {
            par_ = par_.getParent();
            names_.add(((RootBlock)par_).getName());
        }
        if (packageName_.isEmpty()) {
            return getName();
        }
        return StringList.concat(packageName_,DOT,names_.getReverse().join(".."));
    }

    public final void validateIds(ContextEl _context) {
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        EqList<ConstructorId> idConstructors_ = new EqList<ConstructorId>();
        StringList idsField_ = new StringList();
        StringList idsAnnotMethods_ = new StringList();
        String className_ = getFullName();
        CustList<Block> bl_;
        bl_ = Classes.getDirectChildren(this);
        for (Block b: bl_) {
            if (b instanceof InfoBlock) {
                continue;
            }
            if (b instanceof RootBlock) {
                continue;
            }
            if (!(b instanceof FunctionBlock)) {
                int where_ = b.getOffset().getOffsetTrim();
                UnexpectedTagName unexp_ = new UnexpectedTagName();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setFoundTag(EMPTY_STRING);
                unexp_.setIndexFile(where_);
                _context.getClasses().addError(unexp_);
            }
        }
        if (!isStaticType()) {
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    continue;
                }
                if (b instanceof RootBlock) {
                    if (((RootBlock)b).isStaticType()) {
                        int where_ = b.getOffset().getOffsetTrim();
                        UnexpectedTagName unexp_ = new UnexpectedTagName();
                        unexp_.setFileName(getFile().getFileName());
                        unexp_.setFoundTag(EMPTY_STRING);
                        unexp_.setIndexFile(where_);
                        _context.getClasses().addError(unexp_);
                    }
                }
                if (b instanceof FunctionBlock) {
                    if (((FunctionBlock)b).isStaticContext()) {
                        int where_ = b.getOffset().getOffsetTrim();
                        UnexpectedTagName unexp_ = new UnexpectedTagName();
                        unexp_.setFileName(getFile().getFileName());
                        unexp_.setFoundTag(EMPTY_STRING);
                        unexp_.setIndexFile(where_);
                        _context.getClasses().addError(unexp_);
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
                    m_.buildImportedTypes(_context);
                    if (!_context.isValidToken(name_)) {
                        int r_ = m_.getNameOffset();
                        BadMethodName badMeth_ = new BadMethodName();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        badMeth_.setName(name_);
                        _context.getClasses().addError(badMeth_);
                    }
                }
                if (method_ instanceof AnnotationMethodBlock) {
                    AnnotationMethodBlock m_ = (AnnotationMethodBlock) method_;
                    m_.buildImportedTypes(_context);
                    if (!_context.isValidToken(name_)) {
                        int r_ = m_.getNameOffset();
                        BadMethodName badMeth_ = new BadMethodName();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        badMeth_.setName(name_);
                        _context.getClasses().addError(badMeth_);
                    }
                }
                if (method_ instanceof MethodBlock) {
                    MethodId id_ = ((MethodBlock) method_).getId();
                    if (this instanceof EnumBlock) {
                        String aliasName_ = _context.getStandards().getAliasEnumName();
                        String ordinal_ = _context.getStandards().getAliasEnumOrdinal();
                        String valueOf_ = _context.getStandards().getAliasEnumPredValueOf();
                        String values_ = _context.getStandards().getAliasEnumValues();
                        String string_ = _context.getStandards().getAliasString();
                        if (id_.eq(new MethodId(false, aliasName_, new StringList()))) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(id_.getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                        if (id_.eq(new MethodId(false, ordinal_, new StringList()))) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(id_.getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                        if (id_.eq(new MethodId(true, valueOf_, new StringList(string_)))) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(id_.getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                        if (id_.eq(new MethodId(true, values_, new StringList()))) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(className_);
                            duplicate_.setId(id_.getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                    }
                    for (MethodId m: idMethods_) {
                        if (m.eq(id_)) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(id_.getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                    }
                    idMethods_.add(id_);
                }
                if (method_ instanceof AnnotationMethodBlock) {
                    String id_ = method_.getName();
                    for (String m: idsAnnotMethods_) {
                        if (StringList.quickEq(m,id_)) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(new MethodId(false, id_, new StringList()).getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                    }
                    idsAnnotMethods_.add(id_);
                }
                if (method_ instanceof ConstructorBlock) {
                    ((ConstructorBlock)method_).buildImportedTypes(_context);
                    ConstructorId idCt_ = ((ConstructorBlock)method_).getId();
                    for (ConstructorId m: idConstructors_) {
                        if (m.eq(idCt_)) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateConstructor duplicate_;
                            duplicate_ = new DuplicateConstructor();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(idCt_.getSignature(_context));
                            _context.getClasses().addError(duplicate_);
                        }
                    }
                    idConstructors_.add(idCt_);
                }
                StringList l_ = method_.getParametersNames();
                StringList seen_ = new StringList();
                for (String v: l_) {
                    if (!_context.isValidToken(v)) {
                        BadParamName b_;
                        b_ = new BadParamName();
                        b_.setFileName(getFile().getFileName());
                        b_.setIndexFile(method_.getOffset().getOffsetTrim());
                        b_.setParamName(v);
                        _context.getClasses().addError(b_);
                    }
                    if (seen_.containsStr(v)){
                        DuplicateParamName b_;
                        b_ = new DuplicateParamName();
                        b_.setFileName(getFile().getFileName());
                        b_.setIndexFile(method_.getOffset().getOffsetTrim());
                        b_.setParamName(v);
                        _context.getClasses().addError(b_);
                    } else {
                        seen_.add(v);
                    }
                }
            } else if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                method_.buildImportedType(_context);
                StringList name_ = method_.getFieldName();
                for (String n: name_) {
                    String trName_ = n.trim();
                    if (!_context.isValidToken(trName_)) {
                        BadParamName b_;
                        b_ = new BadParamName();
                        b_.setFileName(getFile().getFileName());
                        b_.setIndexFile(method_.getOffset().getOffsetTrim());
                        b_.setParamName(n);
                        _context.getClasses().addError(b_);
                    }
                    for (String m: idsField_) {
                        if (StringList.quickEq(m, trName_)) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            DuplicateField duplicate_;
                            duplicate_ = new DuplicateField();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            duplicate_.setId(n);
                            _context.getClasses().addError(duplicate_);
                        }
                    }
                    idsField_.add(trName_);
                }
            }
        }
    }
    public abstract void setupBasicOverrides(ContextEl _context);

    final void useSuperTypesOverrides(ContextEl _context) {
        TypeUtil.buildOverrides(this, _context);
    }

    public abstract void buildDirectGenericSuperTypes(ContextEl _classes);
    public abstract void buildErrorDirectGenericSuperTypes(ContextEl _classes);

    public final StringList getAllGenericInterfaces(Analyzable _classes){
        StringList allSuperTypes_ = getAllGenericSuperTypes();
        Classes classes_ = _classes.getClasses();
        StringList allGenericInterfaces_ = new StringList();
        for (String s: allSuperTypes_) {
            String base_ = Templates.getIdFromAllTypes(s);
            if (classes_.getClassBody(base_) instanceof InterfaceBlock) {
                allGenericInterfaces_.add(s);
            }
        }
        return allGenericInterfaces_;
    }

    @Override
    public final StringList getAllGenericSuperTypes(Analyzable _classes) {
        StringList list_ = new StringList();
        Classes classes_ = _classes.getClasses();
        StringList current_ = new StringList(getGenericString());
        StringList all_ = new StringList();
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = Templates.getIdFromAllTypes(c);
                RootBlock curType_ = classes_.getClassBody(baseType_);
                if (curType_ == null) {
                    continue;
                }
                StringList superTypes_ = curType_.getDirectGenericSuperTypes(_classes);
                for (String t: superTypes_) {
                    String format_ = Templates.quickFormat(c, t, _classes);
                    if (all_.containsStr(format_)) {
                        continue;
                    }
                    all_.add(format_);
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
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        LgNames stds_ = _context.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        for (TypeVar t: getParamTypesMapValues()) {
            ObjectMap<MethodId, EqList<ClassMethodId>> signatures_;
            signatures_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
            StringList upper_ = Mapping.getAllUpperBounds(vars_, t.getName(),objectClassName_);
            for (String c: upper_) {
                String base_ = Templates.getIdFromAllTypes(c);
                RootBlock r_ = classesRef_.getClassBody(base_);
                if (r_ != null) {
                    for (EntryCust<MethodId, EqList<ClassMethodId>> e: r_.getAllOverridingMethods().entryList()) {
                        for (ClassMethodId s: e.getValue()) {
                            String c_ = s.getClassName();
                            String formattedType_ = Templates.format(c, c_, _context);
                            if (formattedType_ == null) {
                                continue;
                            }
                            MethodBlock m_ = Classes.getMethodBodiesById(_context, c_, s.getConstraints()).first();
                            if (!Classes.canAccess(getFullName(), m_, _context)) {
                                continue;
                            }
                            MethodId id_ =  m_.getWildCardFormattedId(c, _context);
                            if (id_ == null) {
                                //the method is never callable
                                continue;
                            }
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
                            MethodBlock mDer_ = Classes.getMethodBodiesById(_context, s, id_).first();
                            IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                            err_.setFileName(getFile().getFileName());
                            err_.setIndexFile(idRowCol);
                            err_.setReturnType(mDer_.getImportedReturnType());
                            err_.setMethod(mDer_.getId().getSignature(_context));
                            err_.setParentClass(s);
                            _context.getClasses().addError(err_);
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
                    MethodBlock mDer_ = Classes.getMethodBodiesById(_context, s_, s.getConstraints()).first();
                    IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(idRowCol);
                    err_.setReturnType(mDer_.getImportedReturnType());
                    err_.setMethod(mDer_.getId().getSignature(_context));
                    err_.setParentClass(s_);
                    _context.getClasses().addError(err_);
                }
            }
            er_ = RootBlock.areModifierCompatible(ov_, _context);
            for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
                for (ClassMethodId s: e.getValue()) {
                    String s_ = s.getClassName();
                    MethodBlock mDer_ = Classes.getMethodBodiesById(_context, s_, s.getConstraints()).first();
                    IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(idRowCol);
                    err_.setReturnType(mDer_.getImportedReturnType());
                    err_.setMethod(mDer_.getId().getSignature(_context));
                    err_.setParentClass(s_);
                    _context.getClasses().addError(err_);
                }
            }
        }
    }

    public final void checkCompatibility(ContextEl _context) {
        ObjectMap<MethodId, ClassMethodId> localSignatures_ = TypeUtil.getLocalSignatures(this);
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
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
                        MethodBlock mDer_ = Classes.getMethodBodiesById(_context, s, id_).first();
                        IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                        err_.setFileName(getFile().getFileName());
                        err_.setIndexFile(idRowCol);
                        err_.setReturnType(mDer_.getImportedReturnType());
                        err_.setMethod(mDer_.getId().getSignature(_context));
                        err_.setParentClass(s);
                        _context.getClasses().addError(err_);
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
                MethodBlock mDer_ = Classes.getMethodBodiesById(_context, s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFile().getFileName());
                err_.setIndexFile(idRowCol);
                err_.setReturnType(mDer_.getImportedReturnType());
                err_.setMethod(mDer_.getId().getSignature(_context));
                err_.setParentClass(s_);
                _context.getClasses().addError(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_, _context);
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: er_.entryList()) {
            for (ClassMethodId s: e.getValue()) {
                String s_ = s.getClassName();
                MethodBlock mDer_ = Classes.getMethodBodiesById(_context, s_, s.getConstraints()).first();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFile().getFileName());
                err_.setIndexFile(idRowCol);
                err_.setReturnType(mDer_.getImportedReturnType());
                err_.setMethod(mDer_.getId().getSignature(_context));
                err_.setParentClass(s_);
                _context.getClasses().addError(err_);
            }
        }
    }
    public final void checkImplements(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        CustList<ClassFormattedMethodId> abstractMethods_ = new CustList<ClassFormattedMethodId>();
        boolean concreteClass_ = false;
        if (mustImplement()) {
            concreteClass_ = true;
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
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
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(mDer_.getNameOffset());
                    err_.setSgn(idFor_.getSignature(_context));
                    err_.setClassName(getFullName());
                    classesRef_.addError(err_);
                }
            }
        }
        if (concreteClass_) {
            for (GeneMethod b: Classes.getMethodBlocks(this)) {
                MethodId idFor_ = b.getId();
                if (b.isAbstractMethod()) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(((MethodBlock) b).getNameOffset());
                    err_.setSgn(idFor_.getSignature(_context));
                    err_.setClassName(getFullName());
                    classesRef_.addError(err_);
                }
            }
            StringList allSuperClass_ = getAllGenericSuperTypes();
            for (String s: allSuperClass_) {
                String base_ = Templates.getIdFromAllTypes(s);
                RootBlock superBl_ = classesRef_.getClassBody(base_);
                for (GeneMethod m: Classes.getMethodBlocks(superBl_)) {
                    if (m.isAbstractMethod()) {
                        abstractMethods_.add(new ClassFormattedMethodId(s, m.getId()));
                    }
                }
            }
            for (ClassFormattedMethodId m: abstractMethods_) {
                String baseClass_ = m.getClassName();
                baseClass_ = Templates.getIdFromAllTypes(baseClass_);
                RootBlock info_ = classesRef_.getClassBody(baseClass_);
                StringMap<ClassMethodId> map_ = TypeUtil.getConcreteMethodsToCall(info_, m.getConstraints(), _context);
                if (!map_.contains(getFullName())) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFile().getFileName());
                    err_.setClassName(m.getClassName());
                    err_.setIndexFile(idRowCol);
                    err_.setSgn(m.getConstraints().getSignature(_context));
                    classesRef_.addError(err_);
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> signatures_;
        signatures_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        StringList allInterfaces_ = getAllGenericInterfaces(_context);
        for (String s: allInterfaces_) {
            String base_ = Templates.getIdFromAllTypes(s);
            InterfaceBlock superBl_ = (InterfaceBlock) classesRef_.getClassBody(base_);
            ObjectMap<MethodId, EqList<ClassMethodId>> signaturesInt_;
            signaturesInt_ = TypeUtil.getAllInstanceSignatures(superBl_, _context);
            for (EntryCust<MethodId, EqList<ClassMethodId>> m: signaturesInt_.entryList()) {
                MethodId key_ = m.getKey().quickFormat(s, _context);
                for (ClassMethodId c: m.getValue()) {
                    String c_ = c.getClassName();
                    String formatCl_ = Templates.quickFormat(s, c_, _context);
                    addClass(signatures_, key_, new ClassMethodId(formatCl_, c.getConstraints()));
                }
            }
        }
        ObjectMap<MethodId, EqList<ClassMethodId>> ov_;
        ov_ = RootBlock.getAllOverridingMethods(signatures_, _context);
        if (concreteClass_) {
            abstractMethods_ = RootBlock.remainingInterfaceMethodsToImplement(ov_, getFullName(), _context);
            for (ClassFormattedMethodId m: abstractMethods_) {
                String baseClass_ = m.getClassName();
                baseClass_ = Templates.getIdFromAllTypes(baseClass_);
                RootBlock info_ = classesRef_.getClassBody(baseClass_);
                MethodId realId_ = m.getConstraints();
                StringMap<ClassMethodId> map_ = info_.getConcreteMethodsToCallFromClass(realId_, _context);
                if (!map_.contains(getFullName())) {
                    AbstractMethod err_;
                    err_ = new AbstractMethod();
                    err_.setFileName(getFile().getFileName());
                    err_.setClassName(m.getClassName());
                    err_.setIndexFile(idRowCol);
                    err_.setSgn(m.getConstraints().getSignature(_context));
                    classesRef_.addError(err_);
                }
            }
        }
    }
    
    final StringMap<ClassMethodId> getConcreteMethodsToCallFromClass(MethodId _realId, ContextEl _conf) {
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
            ClassMethodId f_ = TypeUtil.tryGetUniqueId(baseClassFound_, c, _realId, _conf);
            if (f_ != null) {
                eq_.put(name_, f_);
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
            if (_localMethodIds.contains(e.getKey())) {
                continue;
            }
            EqList<ClassMethodId> fClasses_ = new EqList<ClassMethodId>();
            StringList retClasses_ = new StringList();
            for (ClassMethodId s: e.getValue()) {
                String name_ = s.getClassName();
                String base_ = Templates.getIdFromAllTypes(name_);
                if (!classesRef_.isCustomType(base_)) {
                    StandardType clBound_ = stds_.getStandards().getVal(base_);
                    for (StandardMethod m: clBound_.getMethods().values()) {
                        MethodId id_ = m.getId();
                        if (!id_.eq(cst_)) {
                            continue;
                        }
                        retClasses_.add(m.getReturnType());
                    }
                    continue;
                }
                MethodBlock sup_ = Classes.getMethodBodiesById(_context, name_, s.getConstraints()).first();
                if (sup_.isFinalMethod()) {
                    fClasses_.add(s);
                }
                String ret_ = sup_.getImportedReturnType();
                retClasses_.add(Templates.quickFormat(name_, ret_, _context));
            }
            fClasses_.removeDuplicates();
            if (fClasses_.size() == 1) {
                ClassMethodId subInt_ = fClasses_.first();
                String name_ = subInt_.getClassName();
                MethodBlock sub_ = Classes.getMethodBodiesById(_context, name_, subInt_.getConstraints()).first();
                String subType_ = sub_.getImportedReturnType();
                subType_ = Templates.quickFormat(name_, subType_, _context);
                for (ClassMethodId s: e.getValue()) {
                    String supName_ = s.getClassName();
                    MethodBlock sup_ = Classes.getMethodBodiesById(_context, supName_, s.getConstraints()).first();
                    String supType_ = sup_.getImportedReturnType();
                    String formattedSup_ = Templates.quickFormat(supName_, supType_, _context);
                    if (!Templates.isReturnCorrect(formattedSup_, subType_,_vars, _context)) {
                        addClass(output_, cst_, subInt_);
                        addClass(output_, cst_, s);
                    }
                }
                continue;
            }
            retClasses_.removeDuplicates();
            StringList subs_ = new StringList();
            int len_ = retClasses_.size();
            for (int i = 0; i < len_; i++) {
                String cur_ = retClasses_.get(i);
                boolean sub_ = true;
                for (int j = 0; j < len_; j++) {
                    String other_ = retClasses_.get(j);
                    if (StringList.quickEq(cur_, other_)) {
                        continue;
                    }
                    if (Templates.isReturnCorrect(cur_, other_, _vars, _context)) {
                        sub_ = false;
                        break;
                    }
                }
                if (sub_) {
                    subs_.add(cur_);
                }
            }
            subs_.removeDuplicates();
            if (subs_.size() != 1) {
                for (ClassMethodId c: classes_) {
                    addClass(output_, e.getKey(), c);
                }
            }
        }
        return output_;
    }

    public static ObjectMap<MethodId, EqList<ClassMethodId>> areModifierCompatible(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds, ContextEl _context) {
        ObjectMap<MethodId, EqList<ClassMethodId>> output_;
        output_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        Classes classes_ = _context.getClasses();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            EqList<ClassMethodId> fClasses_ = new EqList<ClassMethodId>();
            EqList<ClassMethodId> aClasses_ = new EqList<ClassMethodId>();
            for (ClassMethodId s: e.getValue()) {
                String name_ = s.getClassName();
                String base_ = Templates.getIdFromAllTypes(name_);
                if (classes_.getClassBody(base_) == null) {
                    continue;
                }
                MethodBlock sup_ = Classes.getMethodBodiesById(_context, name_, s.getConstraints()).first();
                if (sup_.isAbstractMethod()) {
                    aClasses_.add(s);
                }
                if (sup_.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            fClasses_.removeDuplicates();
            if (fClasses_.size() > 1) {
                for (ClassMethodId c: fClasses_) {
                    addClass(output_, cst_, c);
                }
                continue;
            }
            if (fClasses_.size() > 0 && aClasses_.size() > 0) {
                String name_ = fClasses_.first().getClassName();
                String base_ = Templates.getIdFromAllTypes(name_);
                if (classes_.getClassBody(base_) instanceof ClassBlock) {
                    continue;
                }
                for (ClassMethodId c: fClasses_) {
                    addClass(output_, cst_, c);
                }
                for (ClassMethodId c: aClasses_) {
                    addClass(output_, cst_, c);
                }
            }
        }
        return output_;
    }
    public static CustList<ClassFormattedMethodId> remainingInterfaceMethodsToImplement(
            ObjectMap<MethodId, EqList<ClassMethodId>> _methodIds,
            String _fullName,
            ContextEl _context) {
        CustList<ClassFormattedMethodId> rem_ = new CustList<ClassFormattedMethodId>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _methodIds.entryList()) {
            int nbConcrete_ = 0;
            int nbFinal_ = 0;
            for (ClassMethodId f: e.getValue()) {
                String f_ = f.getClassName();
                MethodBlock method_ = Classes.getMethodBodiesById(_context, f_, f.getConstraints()).first();
                if (!Classes.canAccess(_fullName, method_, _context)) {
                    continue;
                }
                if (method_.isFinalMethod()) {
                    nbFinal_++;
                }
                if (method_.isConcreteMethod()) {
                    nbConcrete_++;
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

    public void validateConstructors(ContextEl _cont) {
        boolean opt_ = optionalCallConstr(_cont);
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof ConstructorBlock) {
                ctors_.add((ConstructorBlock) b);
            }
        }
        if (ctors_.isEmpty() && !opt_) {
            UndefinedSuperConstructor un_ = new UndefinedSuperConstructor();
            un_.setClassName(((UniqueRootedBlock)this).getImportedDirectGenericSuperClass());
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
        }
        String idType_ = getFullName();
        for (ConstructorBlock c: ctors_) {
            c.setupInstancingStep(_cont);
        }
        for (ConstructorBlock c: ctors_) {
            if (c.implicitConstr() && !opt_) {
                UndefinedSuperConstructor un_ = new UndefinedSuperConstructor();
                un_.setClassName(((UniqueRootedBlock)this).getImportedDirectGenericSuperClass());
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(c.getOffset().getOffsetTrim());
                _cont.getClasses().addError(un_);
            }
        }
        EqList<ConstructorId> l_ = new EqList<ConstructorId>();
        for (ConstructorBlock c: ctors_) {
            if (c.getConstIdSameClass() != null) {
                l_.add(c.getId());
            }
        }
        Graph<ConstructorEdge> graph_;
        graph_ = new Graph<ConstructorEdge>();
        for (ConstructorId f: l_) {
            ConstructorBlock b_ = (ConstructorBlock) Classes.getConstructorBodiesById(_cont, idType_, f).first();
            ConstructorId co_ = b_.getConstIdSameClass();
            ConstructorEdge f_ = new ConstructorEdge(f);
            ConstructorEdge t_ = new ConstructorEdge(co_);
            graph_.addSegment(f_, t_);
        }
        EqList<ConstructorEdge> cycle_ = graph_.elementsCycle();
        if (!cycle_.isEmpty()) {
            CyclicInheritingGraph cyclic_ = new CyclicInheritingGraph();
            StringList c_ = new StringList();
            for (ConstructorEdge c: cycle_) {
                c_.add(c.getId().getSignature(_cont));
            }
            cyclic_.setClassName(c_);
            cyclic_.setFileName(getFile().getFileName());
            cyclic_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(cyclic_);
        }
    }

    private boolean optionalCallConstr(ContextEl _cont) {
        if (!(this instanceof UniqueRootedBlock)) {
            return true;
        }
        String superClass_ = ((UniqueRootedBlock)this).getImportedDirectGenericSuperClass();
        String superClassId_ = Templates.getIdFromAllTypes(superClass_);
        RootBlock clMeta_ = _cont.getClasses().getClassBody(superClassId_);
        if (clMeta_ == null) {
            return true;
        }
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        for (Block b: Classes.getDirectChildren(clMeta_)) {
            if (b instanceof ConstructorBlock) {
                ctors_.add((ConstructorBlock) b);
            }
        }
        if (ctors_.isEmpty()) {
            return true;
        }
        for (ConstructorBlock c: ctors_) {
            if (!Classes.canAccess(getFullName(), c, _cont)) {
                continue;
            }
            if (c.getId().getParametersTypes().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTypeHidden(String _class, Analyzable _analyzable) {
        return !Classes.canAccessClass(getFullName(), _class, _analyzable);
    }
}
