package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
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
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.*;
import code.util.graphs.Graph;

public abstract class RootBlock extends BracedBlock implements GeneType, AccessingImportingBlock, AnnotableBlock {

    private final String name;

    private final String packageName;

    private final AccessEnum access;

    private int accessOffset;

    private final String templateDef;

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    private ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();

    private final StringList directSuperTypes = new StringList();
    private final IntMap<String> importedDirectBaseSuperTypes = new IntMap<String>();

    private IntMap< String> rowColDirectSuperTypes;
    private IntMap< Boolean> explicitDirectSuperTypes = new IntMap< Boolean>();

    private int idRowCol;

    private int categoryOffset;

    private StringList staticInitInterfaces = new StringList();
    private int templateDefOffset;
    private int nameLength;

    private CustList<Ints> paramTypesConstraintsOffset = new CustList<Ints>();
    private CustList<PartOffset> superTypesParts = new CustList<PartOffset>();
    private CustList<PartOffset> constraintsParts = new CustList<PartOffset>();

    private StringList staticInitImportedInterfaces = new StringList();
    private Ints staticInitInterfacesOffset = new Ints();
    private Ints ancestorsIndexes = new Ints();

    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();
    private final StringList allGenericSuperTypes = new StringList();
    private final CustList<ClassMethodId> functional = new CustList<ClassMethodId>();

    RootBlock(int _idRowCol, int _categoryOffset, String _name,
              String _packageName, OffsetAccessInfo _access, String _templateDef,
              IntMap< String> _directSuperTypes, OffsetsBlock _offset) {
        super(_offset);
        categoryOffset = _categoryOffset;
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        name = _name.trim();
        packageName = ContextEl.removeDottedSpaces(_packageName);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        templateDef = _templateDef;
        if (!_templateDef.isEmpty()) {
            nameLength = _name.length();
            templateDefOffset = _idRowCol + nameLength;
            if (!_packageName.isEmpty()) {
                templateDefOffset += _packageName.length() + 1;
                nameLength += _packageName.length() + 1;
            }
        } else {
            nameLength = _name.length();
            if (!_packageName.isEmpty()) {
                nameLength += _packageName.length() + 1;
            }
        }
        rowColDirectSuperTypes = _directSuperTypes;
        idRowCol = _idRowCol;
        for (EntryCust<Integer, String> t: _directSuperTypes.entryList()) {
            String type_ = ContextEl.removeDottedSpaces(t.getValue());
            directSuperTypes.add(type_);
            ancestorsIndexes.add(-1);
            explicitDirectSuperTypes.put(t.getKey(), true);
        }
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public int getCategoryOffset() {
        return categoryOffset;
    }

    public abstract StringList getDirectGenericSuperTypes(Analyzable _classes);

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
            if (e instanceof InnerTypeOrElement) {
                String type_ = ((InnerTypeOrElement)e).getImportedClassName();
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
        IntMap< String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        StringMap<Integer> varsList_ = new StringMap<Integer>();
        for (RootBlock r: getSelfAndParentTypes()) {
            int i_ = 0;
            for (TypeVar t: r.paramTypes) {
                varsList_.addEntry(t.getName(),t.getOffset());
                i_ += 2;
            }
        }
        _an.getAvailableVariables().clear();
        _an.getAvailableVariables().putAllMap(varsList_);
        Ints bi_ = _an.getCurrentBadIndexes();
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
    public Ints getAncestorsIndexes() {
        return ancestorsIndexes;
    }
    public IntMap< Boolean> getExplicitDirectSuperTypes() {
        return explicitDirectSuperTypes;
    }

    public StringList getStaticInitInterfaces() {
        return staticInitInterfaces;
    }

    public StringList getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
    }

    public Ints getStaticInitInterfacesOffset() {
        return staticInitInterfacesOffset;
    }
    @Override
    public void buildAnnotations(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            annotationsOps.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
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
    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }
    @Override
    public StringList getImports() {
        return imports;
    }

    @Override
    public StringList getFileImports() {
        return getFile().getImports();
    }

    public Ints getImportsOffset() {
        return importsOffset;
    }

    public IntMap< String> getRowColDirectSuperTypes() {
        return rowColDirectSuperTypes;
    }

    public int getIdRowCol() {
        return idRowCol;
    }

    public StringList getDirectSuperTypes() {
        return directSuperTypes;
    }

    public IntMap<String> getImportedDirectBaseSuperTypes() {
        return importedDirectBaseSuperTypes;
    }

    public String getImportedDirectBaseSuperType(int _index) {
        if (_index >= importedDirectBaseSuperTypes.size()) {
            return EMPTY_STRING;
        }
        return importedDirectBaseSuperTypes.getValue(_index);
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
                        err_.setIndexFile(((OverridableBlock)supCl_).getAccessOffset());
                        err_.setId(supInt_.getId().getSignature(_context));
                        classesRef_.addError(err_);
                    }
                    String retInt_ = supInt_.getImportedReturnType();
                    String retBase_ = supCl_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(nameCl_, retBase_, _context);
                    String formattedRetBase_ = Templates.quickFormat(name_, retInt_, _context);
                    if (((OverridableBlock)supCl_).getKind() != MethodKind.STD_METHOD) {
                        if (!StringList.quickEq(formattedRetBase_, formattedRetDer_)) {
                            BadReturnTypeInherit err_;
                            err_ = new BadReturnTypeInherit();
                            err_.setFileName(getFile().getFileName());
                            err_.setIndexFile(((OverridableBlock)supCl_).getReturnTypeOffset());
                            err_.setReturnType(retBase_);
                            err_.setMethod(supCl_.getId().getSignature(_context));
                            err_.setParentClass(nameCl_);
                            classesRef_.addError(err_);
                        }
                        continue;
                    }
                    if (!Templates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
                        BadReturnTypeInherit err_;
                        err_ = new BadReturnTypeInherit();
                        err_.setFileName(getFile().getFileName());
                        err_.setIndexFile(((OverridableBlock)supCl_).getReturnTypeOffset());
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
            int j_ = 0;
            for (TypeVar t: r.paramTypes) {
                StringList const_ = new StringList();
                Ints ints_ = r.paramTypesConstraintsOffset.get(j_);
                if (r == this) {
                    constraintsParts.add(new PartOffset("<a name=\"m"+t.getOffset()+"\">",t.getOffset()));
                    constraintsParts.add(new PartOffset("</a>",t.getOffset()+t.getLength()));
                }
                _analyze.getCoverage().getCurrentParts().clear();
                int off_ = t.getOffset() + 1;
                int i_ = 0;
                for (String c: t.getConstraints()) {
                    int d_ = 0;
                    if (r == this && ints_.isValidIndex(i_)) {
                        d_ = ints_.get(i_);
                    }
                    const_.add(_analyze.resolveTypeMapping(c,r, off_+d_));
                    i_++;
                }
                if (r == this) {
                    constraintsParts.addAllElts(_analyze.getCoverage().getCurrentParts());
                    j_++;
                }
                TypeVar t_ = new TypeVar();
                t_.setOffset(t.getOffset());
                t_.setLength(t.getLength());
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
    public CustList<StringList> getBoundAll() {
        CustList<StringList> boundsAll_ = new CustList<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            boolean contained_ = false;
            for (TypeVar u: getParamTypes()) {
                if (!StringList.quickEq(t.getName(),u.getName())) {
                    continue;
                }
                contained_ = true;
            }
            if (!contained_) {
                continue;
            }
            StringList localBound_ = new StringList();
            for (String b: t.getConstraints()) {
                localBound_.add(b);
            }
            boundsAll_.add(localBound_);
        }
        return boundsAll_;
    }

    public CustList<TypeVar> getParamTypes() {
        return paramTypes;
    }

    public int getTemplateDefOffset() {
        return templateDefOffset;
    }

    public CustList<Ints> getParamTypesConstraintsOffset() {
        return paramTypesConstraintsOffset;
    }

    public Ints getTypeVarCounts() {
        Ints generic_ = new Ints();
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        CustList<RootBlock> stPars_ = pars_.first().getAllParentTypesReverse();
        int len_ = stPars_.size();
        for (int i = 0; i < len_; i++) {
            generic_.add(0);
        }
        for (RootBlock r: pars_) {
            generic_.add(r.paramTypes.size());
        }
        return generic_;
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
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
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
    public String getGenericString() {
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
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
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
    /**
     @return a map with formatted id from super types as key
     and a list of (formatted super types and id) as value
     */
    @Override
    public ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    @Override
    public String getFullName() {
        String packageName_ = getPackageName();
        StringList names_ = new StringList(getName());
        for (RootBlock r: getAllParentTypes()) {
            names_.add(r.getName());
        }
        if (packageName_.isEmpty()) {
            return getName();
        }
        return StringList.concat(packageName_,DOT, StringList.join(names_.getReverse(), ".."));
    }

    public final void validateIds(ContextEl _context) {
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        CustList<OverridableBlock> indexersGet_ = new CustList<OverridableBlock>();
        CustList<OverridableBlock> indexersSet_ = new CustList<OverridableBlock>();
        EqList<ConstructorId> idConstructors_ = new EqList<ConstructorId>();
        StringList idsField_ = new StringList();
        StringList idsAnnotMethods_ = new StringList();
        CustList<Block> bl_;
        bl_ = Classes.getDirectChildren(this);
        KeyWords keyWords_ = _context.getKeyWords();
        LgNames stds_ = _context.getStandards();
        String keyWordValue_ = keyWords_.getKeyWordValue();
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
                    if (((RootBlock)b).isStaticType() || this instanceof InnerElementBlock) {
                        int where_ = b.getOffset().getOffsetTrim();
                        UnexpectedTagName unexp_ = new UnexpectedTagName();
                        unexp_.setFileName(getFile().getFileName());
                        unexp_.setFoundTag(EMPTY_STRING);
                        unexp_.setIndexFile(where_);
                        _context.getClasses().addError(unexp_);
                    }
                }
                if (b instanceof StaticBlock) {
                    int where_ = b.getOffset().getOffsetTrim();
                    UnexpectedTagName unexp_ = new UnexpectedTagName();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setFoundTag(EMPTY_STRING);
                    unexp_.setIndexFile(where_);
                    _context.getClasses().addError(unexp_);
                }
            }
        }
        for (Block b: bl_) {
            if (b instanceof Returnable) {
                Returnable method_ = (Returnable) b;
                String name_ = method_.getName();
                if (method_ instanceof OverridableBlock) {
                    OverridableBlock m_ = (OverridableBlock) method_;
                    if (m_.getKind() == MethodKind.STD_METHOD) {
                        m_.buildImportedTypes(_context);
                        if (!_context.isValidToken(name_) && !StringList.quickEq(name_, keyWords_.getKeyWordToString()) && !StringList.quickEq(name_, keyWords_.getKeyWordExplicit())) {
                            int r_ = m_.getNameOffset();
                            BadMethodName badMeth_ = new BadMethodName();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            badMeth_.setName(name_);
                            _context.getClasses().addError(badMeth_);
                        } else if (StringList.quickEq(name_, keyWords_.getKeyWordToString()) && !m_.hiddenInstance() && m_.getParametersTypes().isEmpty()) {
                            if (!StringList.quickEq(m_.getImportedReturnType(),stds_.getAliasString())) {
                                int r_ = m_.getNameOffset();
                                BadMethodName badMeth_ = new BadMethodName();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                badMeth_.setName(name_);
                                _context.getClasses().addError(badMeth_);
                            } else if (m_.getAccess() != AccessEnum.PUBLIC) {
                                int r_ = m_.getNameOffset();
                                BadMethodName badMeth_ = new BadMethodName();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                badMeth_.setName(name_);
                                _context.getClasses().addError(badMeth_);
                            }
                        } else if (StringList.quickEq(name_, keyWords_.getKeyWordExplicit())) {
                            if (!StringList.quickEq(m_.getImportedReturnType(),getGenericString())) {
                                int r_ = m_.getNameOffset();
                                BadMethodName badMeth_ = new BadMethodName();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                badMeth_.setName(name_);
                                _context.getClasses().addError(badMeth_);
                            } else if (m_.getParametersTypes().size() != 1) {
                                int r_ = m_.getNameOffset();
                                BadMethodName badMeth_ = new BadMethodName();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                badMeth_.setName(name_);
                                _context.getClasses().addError(badMeth_);
                            } else if (!m_.isStaticMethod()) {
                                int r_ = m_.getNameOffset();
                                BadMethodName badMeth_ = new BadMethodName();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                badMeth_.setName(name_);
                                _context.getClasses().addError(badMeth_);
                            } else if (m_.isVarargs()) {
                                int r_ = m_.getNameOffset();
                                BadMethodName badMeth_ = new BadMethodName();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                badMeth_.setName(name_);
                                _context.getClasses().addError(badMeth_);
                            }
                        }
                    } else {
                        if (m_.isStaticMethod()) {
                            int where_ = b.getOffset().getOffsetTrim();
                            UnexpectedTagName unexp_ = new UnexpectedTagName();
                            unexp_.setFileName(getFile().getFileName());
                            unexp_.setFoundTag(EMPTY_STRING);
                            unexp_.setIndexFile(where_);
                            _context.getClasses().addError(unexp_);
                        }
                        if (m_.getParametersTypes().isEmpty()) {
                            int where_ = b.getOffset().getOffsetTrim();
                            UnexpectedTagName unexp_ = new UnexpectedTagName();
                            unexp_.setFileName(getFile().getFileName());
                            unexp_.setFoundTag(EMPTY_STRING);
                            unexp_.setIndexFile(where_);
                            _context.getClasses().addError(unexp_);
                        }
                        m_.buildImportedTypes(_context);
                        if (m_.getKind() == MethodKind.GET_INDEX) {
                            indexersGet_.add(m_);
                        } else {
                            indexersSet_.add(m_);
                        }
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
                if (method_ instanceof OverridableBlock) {
                    OverridableBlock m_ = (OverridableBlock) method_;
                    if (m_.getKind() == MethodKind.STD_METHOD) {
                        MethodId id_ = m_.getId();
                        if (ContextEl.isEnumType(this)) {
                            String valueOf_ = stds_.getAliasEnumPredValueOf();
                            String values_ = stds_.getAliasEnumValues();
                            String string_ = stds_.getAliasString();
                            if (id_.eq(new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_)))) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                DuplicateMethod duplicate_;
                                duplicate_ = new DuplicateMethod();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                duplicate_.setId(id_.getSignature(_context));
                                _context.getClasses().addError(duplicate_);
                            }
                            if (id_.eq(new MethodId(MethodAccessKind.STATIC, values_, new StringList()))) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                DuplicateMethod duplicate_;
                                duplicate_ = new DuplicateMethod();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                duplicate_.setId(id_.getSignature(_context));
                                _context.getClasses().addError(duplicate_);
                            }
                        }
                        for (MethodId m: idMethods_) {
                            if (m.eq(id_)) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                DuplicateMethod duplicate_;
                                duplicate_ = new DuplicateMethod();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                duplicate_.setId(id_.getSignature(_context));
                                _context.getClasses().addError(duplicate_);
                            }
                        }
                        idMethods_.add(id_);
                    } else {
                        MethodId id_ = m_.getId();
                        for (MethodId m: idMethods_) {
                            if (m.eq(id_)) {
                                int r_ = m_.getOffset().getOffsetTrim();
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
                            duplicate_.setId(new MethodId(MethodAccessKind.INSTANCE, id_, new StringList()).getSignature(_context));
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
                    if (method_ instanceof OverridableBlock) {
                        OverridableBlock i_ = (OverridableBlock) method_;
                        if (i_.getKind() == MethodKind.SET_INDEX) {
                            if (StringList.quickEq(v,keyWordValue_)) {
                                BadParamName b_;
                                b_ = new BadParamName();
                                b_.setFileName(getFile().getFileName());
                                b_.setIndexFile(method_.getOffset().getOffsetTrim());
                                b_.setParamName(v);
                                _context.getClasses().addError(b_);
                            }
                        }
                    }
                    if (StringList.contains(seen_, v)){
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
        for (OverridableBlock i: indexersGet_) {
            MethodId iOne_ = i.getId();
            boolean ok_ = false;
            OverridableBlock set_ = null;
            for (OverridableBlock j: indexersSet_) {
                MethodId iTwo_ = j.getId();
                if (iOne_.eqPartial(iTwo_)) {
                    ok_ = true;
                    set_ = j;
                }
            }
            if (!ok_) {
                int where_ = i.getOffset().getOffsetTrim();
                UnexpectedTagName unexp_ = new UnexpectedTagName();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setFoundTag(EMPTY_STRING);
                unexp_.setIndexFile(where_);
                _context.getClasses().addError(unexp_);
            } else {
                if (set_.getModifier() != i.getModifier()) {
                    int where_ = i.getOffset().getOffsetTrim();
                    UnexpectedTagName unexp_ = new UnexpectedTagName();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setFoundTag(EMPTY_STRING);
                    unexp_.setIndexFile(where_);
                    _context.getClasses().addError(unexp_);
                }
                if (set_.getAccess() != i.getAccess()) {
                    int where_ = i.getOffset().getOffsetTrim();
                    UnexpectedTagName unexp_ = new UnexpectedTagName();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setFoundTag(EMPTY_STRING);
                    unexp_.setIndexFile(where_);
                    _context.getClasses().addError(unexp_);
                }
            }
        }
        for (OverridableBlock i: indexersSet_) {
            MethodId iOne_ = i.getId();
            boolean ok_ = false;
            for (OverridableBlock j: indexersGet_) {
                MethodId iTwo_ = j.getId();
                if (iOne_.eqPartial(iTwo_)) {
                    ok_ = true;
                }
            }
            if (!ok_) {
                int where_ = i.getOffset().getOffsetTrim();
                UnexpectedTagName unexp_ = new UnexpectedTagName();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setFoundTag(EMPTY_STRING);
                unexp_.setIndexFile(where_);
                _context.getClasses().addError(unexp_);
            }
        }
    }
    public abstract void setupBasicOverrides(ContextEl _context);

    final void useSuperTypesOverrides(ContextEl _context) {
        TypeUtil.buildOverrides(this, _context);
    }

    public abstract void buildDirectGenericSuperTypes(ContextEl _classes);
    public abstract void buildErrorDirectGenericSuperTypes(ContextEl _classes);

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
                    if (StringList.contains(all_, format_)) {
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
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        LgNames stds_ = _context.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        for (TypeVar t: getParamTypesMapValues()) {
            ObjectMap<MethodId, CustList<MethodInfo>> signatures_;
            signatures_ = new ObjectMap<MethodId, CustList<MethodInfo>>();
            StringList upper_ = Mapping.getAllUpperBounds(vars_, t.getName(),objectClassName_);
            ObjectMap<ClassMethodId, MethodInfo> methods_;
            methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
            OperationNode.fetchParamClassAncMethods(_context,upper_,methods_);
            for (EntryCust<ClassMethodId, MethodInfo> e: methods_.entryList()) {
                MethodInfo info_ = e.getValue();
                if (info_.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                    continue;
                }
                addClass(signatures_, info_.getFormatted(), info_);
            }
            ObjectMap<MethodId, CustList<MethodInfo>> ov_;
            ov_ = RootBlock.getAllOverridingMethods(signatures_, _context);
            ObjectMap<MethodId, CustList<MethodInfo>> er_;
            er_ = RootBlock.areCompatible("", vars_, ov_, _context);
            for (EntryCust<MethodId, CustList<MethodInfo>> e: er_.entryList()) {
                for (MethodInfo s: e.getValue()) {
                    String s_ = s.getClassName();
                    IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(idRowCol);
                    err_.setReturnType(s.getReturnType());
                    err_.setMethod(s.getConstraints().getSignature(_context));
                    err_.setParentClass(s_);
                    _context.getClasses().addError(err_);
                }
            }
            er_ = RootBlock.areModifierCompatible(ov_);
            for (EntryCust<MethodId, CustList<MethodInfo>> e: er_.entryList()) {
                for (MethodInfo s: e.getValue()) {
                    String s_ = s.getClassName();
                    IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(idRowCol);
                    err_.setReturnType(s.getReturnType());
                    err_.setMethod(s.getConstraints().getSignature(_context));
                    err_.setParentClass(s_);
                    _context.getClasses().addError(err_);
                }
            }
        }
    }

    public final void checkCompatibility(ContextEl _context) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        ObjectMap<MethodId, CustList<MethodInfo>> ov_;
        ov_ = new ObjectMap<MethodId, CustList<MethodInfo>>();
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
        OperationNode.fetchParamClassAncMethods(_context,new StringList(getGenericString()),methods_);
        for (EntryCust<ClassMethodId, MethodInfo> e: methods_.entryList()) {
            MethodInfo info_ = e.getValue();
            if (info_.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                continue;
            }
            addClass(ov_, info_.getFormatted(), info_);
        }
        ov_ = RootBlock.getAllOverridingMethods(ov_, _context);
        ObjectMap<MethodId, CustList<MethodInfo>> er_;
        er_ = RootBlock.areCompatible(getFullName(), vars_, ov_, _context);
        for (EntryCust<MethodId, CustList<MethodInfo>> e: er_.entryList()) {
            for (MethodInfo s: e.getValue()) {
                String s_ = s.getClassName();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFile().getFileName());
                err_.setIndexFile(idRowCol);
                err_.setReturnType(s.getReturnType());
                err_.setMethod(s.getConstraints().getSignature(_context));
                err_.setParentClass(s_);
                _context.getClasses().addError(err_);
            }
        }
        er_ = RootBlock.areModifierCompatible(ov_);
        for (EntryCust<MethodId, CustList<MethodInfo>> e: er_.entryList()) {
            for (MethodInfo s: e.getValue()) {
                String s_ = s.getClassName();
                IncompatibilityReturnType err_ = new IncompatibilityReturnType();
                err_.setFileName(getFile().getFileName());
                err_.setIndexFile(idRowCol);
                err_.setReturnType(s.getReturnType());
                err_.setMethod(s.getConstraints().getSignature(_context));
                err_.setParentClass(s_);
                _context.getClasses().addError(err_);
            }
        }
    }
    public final void checkImplements(ContextEl _context) {
        Classes classesRef_ = _context.getClasses();
        CustList<ClassMethodId> abstractMethods_ = new CustList<ClassMethodId>();
        boolean concreteClass_ = false;
        if (mustImplement()) {
            concreteClass_ = true;
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (Block b: Classes.getDirectChildren(this)) {
            if (!(b instanceof OverridableBlock)) {
                continue;
            }
            OverridableBlock mDer_ = (OverridableBlock) b;
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
                    err_.setIndexFile(((OverridableBlock) b).getNameOffset());
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
                        abstractMethods_.add(new ClassMethodId(s, m.getId()));
                    }
                }
            }
            for (ClassMethodId m: abstractMethods_) {
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
        } else {
            StringList allSuperClass_ = new StringList(getGenericString());
            allSuperClass_.addAllElts(getAllGenericSuperTypes());
            for (String s: allSuperClass_) {
                String base_ = Templates.getIdFromAllTypes(s);
                RootBlock superBl_ = classesRef_.getClassBody(base_);
                for (GeneMethod m: Classes.getMethodBlocks(superBl_)) {
                    if (m.isAbstractMethod()) {
                        abstractMethods_.add(new ClassMethodId(s, m.getId()));
                    }
                }
            }
            for (ClassMethodId m: abstractMethods_) {
                String baseClass_ = m.getClassName();
                baseClass_ = Templates.getIdFromAllTypes(baseClass_);
                RootBlock info_ = classesRef_.getClassBody(baseClass_);
                StringMap<ClassMethodId> map_ = TypeUtil.getConcreteMethodsToCall(info_, m.getConstraints(), _context);
                if (!map_.contains(getFullName())) {
                    functional.add(m);
                }
            }
        }
    }

    public static ObjectMap<MethodId, CustList<MethodInfo>> getAllOverridingMethods(
            ObjectMap<MethodId, CustList<MethodInfo>> _methodIds,
            ContextEl _conf) {
        ObjectMap<MethodId, CustList<MethodInfo>> map_;
        map_ = new ObjectMap<MethodId, CustList<MethodInfo>>();
        for (EntryCust<MethodId, CustList<MethodInfo>> e: _methodIds.entryList()) {
            StringMap<MethodInfo> defs_ = new StringMap<MethodInfo>();
            CustList<MethodInfo> value_ = e.getValue();
            StringList list_ = new StringList(new CollCapacity(value_.size()));
            for (MethodInfo v: value_) {
                defs_.put(v.getClassName(), v);
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
            CustList<MethodInfo> out_ = new CustList<MethodInfo>(new CollCapacity(list_.size()));
            for (String v: list_) {
                out_.add(defs_.getVal(v));
            }
            map_.put(e.getKey(), out_);
        }
        return map_;
    }

    public static ObjectMap<MethodId, CustList<MethodInfo>> areCompatible(
            String _fullName,
            StringMap<StringList> _vars,
            ObjectMap<MethodId, CustList<MethodInfo>> _methodIds, ContextEl _context) {
        ObjectMap<MethodId, CustList<MethodInfo>> output_;
        output_ = new ObjectMap<MethodId, CustList<MethodInfo>>();
        for (EntryCust<MethodId, CustList<MethodInfo>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            CustList<MethodInfo> classes_ = e.getValue();
            boolean skip_ = false;
            for (MethodInfo m: classes_) {
                String id_ = Templates.getIdFromAllTypes(m.getClassName());
                if (StringList.quickEq(id_,_fullName)) {
                    skip_ = true;
                    break;
                }
            }
            if (skip_) {
                continue;
            }
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            StringList retClasses_ = new StringList();
            for (MethodInfo s: e.getValue()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
                retClasses_.add(s.getReturnType());
            }
            if (!StringList.isDollarWord(cst_.getName())) {
                //indexer
                retClasses_.removeDuplicates();
                if (retClasses_.size() != 1) {
                    for (MethodInfo c: classes_) {
                        addClass(output_, e.getKey(), c);
                    }
                }
                continue;
            }
            if (fClasses_.size() == 1) {
                MethodInfo subInt_ = fClasses_.first();
                String subType_ = subInt_.getReturnType();
                for (MethodInfo s: e.getValue()) {
                    String formattedSup_ = s.getReturnType();
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
                for (MethodInfo c: classes_) {
                    addClass(output_, e.getKey(), c);
                }
            }
        }
        return output_;
    }

    public static ObjectMap<MethodId, CustList<MethodInfo>> areModifierCompatible(
            ObjectMap<MethodId, CustList<MethodInfo>> _methodIds) {
        ObjectMap<MethodId, CustList<MethodInfo>> output_;
        output_ = new ObjectMap<MethodId, CustList<MethodInfo>>();
        for (EntryCust<MethodId, CustList<MethodInfo>> e: _methodIds.entryList()) {
            MethodId cst_ = e.getKey();
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getValue()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            if (fClasses_.size() > 1) {
                for (MethodInfo c: fClasses_) {
                    addClass(output_, cst_, c);
                }
            }
        }
        return output_;
    }

    protected static void addClass(ObjectMap<MethodId, CustList<MethodInfo>> _map, MethodId _key, MethodInfo _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
        } else {
            _map.put(_key, new CustList<MethodInfo>(_class));
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
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        processAnnotationReport(_cont, _parts);
        _parts.add(new PartOffset("<a name=\"m"+idRowCol+"\">",idRowCol));
        _parts.add(new PartOffset("</a>",idRowCol+nameLength));
        for (PartOffset p: constraintsParts) {
            _parts.add(p);
        }

        for (PartOffset p: superTypesParts) {
            _parts.add(p);
        }

    }

    protected void processAnnotationReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
    }

    public CustList<ClassMethodId> getFunctional() {
        return functional;
    }

    public CustList<PartOffset> getSuperTypesParts() {
        return superTypesParts;
    }
    public abstract boolean mustImplement();
    @Override
    public boolean isTypeHidden(String _class, Analyzable _analyzable) {
        return !Classes.canAccessClass(getFullName(), _class, _analyzable);
    }
}
