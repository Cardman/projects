package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneCustMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public abstract class RootBlock extends BracedBlock implements GeneType, AccessingImportingBlock, AnnotableBlock {

    private final String name;

    private final String packageName;

    private final AccessEnum access;

    private int accessOffset;

    private final String templateDef;

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    private ObjectMap<MethodId, CustList<ClassMethodId>> allOverridingMethods;

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

    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();
    private final StringList allGenericSuperTypes = new StringList();
    private final StringList allGenericClasses = new StringList();
    private final CustList<ClassMethodId> functional = new CustList<ClassMethodId>();

    RootBlock(int _idRowCol, int _categoryOffset, String _name,
              String _packageName, OffsetAccessInfo _access, String _templateDef,
              IntMap< String> _directSuperTypes, OffsetsBlock _offset) {
        super(_offset);
        categoryOffset = _categoryOffset;
        allOverridingMethods = new ObjectMap<MethodId, CustList<ClassMethodId>>();
        name = _name.trim();
        packageName = StringExpUtil.removeDottedSpaces(_packageName);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        templateDef = _templateDef;
        idRowCol = _idRowCol;
        setupOffsets(_name, _packageName);
        rowColDirectSuperTypes = _directSuperTypes;
        for (EntryCust<Integer, String> t: _directSuperTypes.entryList()) {
            String type_ = StringExpUtil.removeDottedSpaces(t.getValue());
            directSuperTypes.add(type_);
            explicitDirectSuperTypes.put(t.getKey(), true);
        }
    }

    public void setupOffsets(String _name, String _packageName) {
        if (!templateDef.isEmpty()) {
            nameLength = _name.length();
            templateDefOffset = idRowCol + nameLength;
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
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public int getCategoryOffset() {
        return categoryOffset;
    }

    public RootBlock getOuter() {
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
                String type_ = ((InnerTypeOrElement)e).getRealImportedClassName();
                allElements_.add(type_);
            }
        }
        String className_;
        if (allElements_.onlyOneElt()) {
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

    public abstract boolean isFinalType();
    public abstract boolean isAbstractType();

    @Override
    public abstract boolean isStaticType();

    public final StringList getAllGenericSuperTypes() {
        return allGenericSuperTypes;
    }

    public StringList getAllGenericClasses() {
        return allGenericClasses;
    }

    public abstract StringList getImportedDirectSuperTypes();

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
        for (EntryCust<MethodId, CustList<ClassMethodId>> e: allOverridingMethods.entryList()) {
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
                NamedFunctionBlock supInt_ = Classes.getMethodBodiesById(_context,name_, id_).first();
                for (ClassMethodId c: locGeneCl_) {
                    String nameCl_ = c.getClassName();
                    MethodId idCl_ = c.getConstraints();
                    NamedFunctionBlock supCl_ = Classes.getMethodBodiesById(_context,nameCl_, idCl_).first();
                    if (supInt_.getAccess().isStrictMoreAccessibleThan(supCl_.getAccess())) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(getFile().getFileName());
                        err_.setIndexFile(supCl_.getAccessOffset());
                        //key word access or method name
                        err_.buildError(_context.getAnalysisMessages().getMethodsAccesses(),
                                name_,
                                id_.getSignature(_context),
                                nameCl_,
                                idCl_.getSignature(_context));
                        _context.addError(err_);
                    }
                    String retInt_ = supInt_.getImportedReturnType();
                    String retBase_ = supCl_.getImportedReturnType();
                    String formattedRetDer_ = Templates.quickFormat(nameCl_, retBase_, _context);
                    String formattedRetBase_ = Templates.quickFormat(name_, retInt_, _context);
                    if (((OverridableBlock)supCl_).getKind() != MethodKind.STD_METHOD) {
                        if (!StringList.quickEq(formattedRetBase_, formattedRetDer_)) {
                            FoundErrorInterpret err_;
                            err_ = new FoundErrorInterpret();
                            err_.setFileName(getFile().getFileName());
                            err_.setIndexFile(supCl_.getReturnTypeOffset());
                            //sub return type len
                            err_.buildError(_context.getAnalysisMessages().getBadReturnTypeIndexer(),
                                    formattedRetBase_,
                                    id_.getSignature(_context),
                                    name_,
                                    formattedRetDer_,
                                    idCl_.getSignature(_context),
                                    nameCl_);
                            _context.addError(err_);
                        }
                        continue;
                    }
                    if (!Templates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(getFile().getFileName());
                        err_.setIndexFile(supCl_.getReturnTypeOffset());
                        //sub return type len
                        err_.buildError(_context.getAnalysisMessages().getBadReturnTypeInherit(),
                                formattedRetDer_,
                                idCl_.getSignature(_context),
                                nameCl_,
                                formattedRetBase_,
                                id_.getSignature(_context),
                                name_);
                        _context.addError(err_);
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
            if (c_.withoutInstance()) {
                break;
            }
            c_ = c_.getParentType();
        }
        return pars_.getReverse();
    }
    public void buildMapParamType(ContextEl _analyze) {
        paramTypesMap = new StringMap<TypeVar>();
        for (RootBlock r: getSelfAndParentTypes()) {
            if (r == this) {
                int j_ = 0;
                for (TypeVar t: paramTypes) {
                    StringList const_ = new StringList();
                    Ints ints_ = paramTypesConstraintsOffset.get(j_);
                    constraintsParts.add(new PartOffset("<a name=\"m"+t.getOffset()+"\">",t.getOffset()));
                    constraintsParts.add(new PartOffset("</a>",t.getOffset()+t.getLength()));
                    _analyze.getCoverage().getCurrentParts().clear();
                    int off_ = t.getOffset() + 1;
                    int i_ = 0;
                    for (String c: t.getConstraints()) {
                        int d_ = ints_.get(i_);
                        const_.add(ResolvingSuperTypes.resolveTypeMapping(_analyze,c,this, off_+d_));
                        i_++;
                    }
                    constraintsParts.addAllElts(_analyze.getCoverage().getCurrentParts());
                    j_++;
                    TypeVar t_ = new TypeVar();
                    t_.setOffset(t.getOffset());
                    t_.setLength(t.getLength());
                    t_.setConstraints(const_);
                    t_.setName(t.getName());
                    paramTypesMap.addEntry(t.getName(), t_);
                }
            } else {
                for (EntryCust<String,TypeVar> e: r.paramTypesMap.entryList()) {
                    boolean exist_ = false;
                    for (TypeVar t: r.paramTypes) {
                        if (StringList.quickEq(t.getName(),e.getKey())) {
                            exist_ = true;
                        }
                    }
                    if (!exist_) {
                        continue;
                    }
                    paramTypesMap.addEntry(e.getKey(),e.getValue());
                }
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
    public StringList getParamTypesValues() {
        StringList l_ = new StringList();
        for (RootBlock r: getSelfAndParentTypes()) {
            for (TypeVar t: r.paramTypes) {
                l_.add(t.getName());
            }
        }
        return l_;
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
        RootBlock previous_ = null;
        for (RootBlock r: pars_.first().getAllParentTypesReverse()) {
            appendParts(generic_, previous_, r, "-", "..");
            generic_.append(r.getName());
            previous_ = r;
        }
        for (RootBlock r: pars_) {
            appendParts(generic_, previous_, r, "-", "..");
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
            previous_ = r;
        }
        return generic_.toString();
    }

    private static void appendParts(StringBuilder generic_, RootBlock previous_, RootBlock r, String _sepInn, String _sep) {
        if (previous_ != null) {
            if (r instanceof InnerElementBlock) {
                generic_.append(_sepInn);
            } else {
                generic_.append(_sep);
            }
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
        RootBlock previous_ = null;
        for (RootBlock r: pars_.first().getAllParentTypesReverse()) {
            appendParts(generic_, previous_, r, "-", "..");
            generic_.append(r.getName());
            previous_ = r;
        }
        for (RootBlock r: pars_) {
            appendParts(generic_, previous_, r, "-", "..");
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
            previous_ = r;
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
        return packageName;
    }

    public AccessEnum getAccess() {
        return access;
    }
    /**
     @return a map with formatted id from super types as key
     and a list of (formatted super types and id) as value
     */
    public ObjectMap<MethodId, CustList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    @Override
    public String getFullName() {
        return formatName("-","..");
    }

    protected String formatName(String _sepInner, String _sep) {
        CustList<RootBlock> all_ = new CustList<RootBlock>(this);
        all_.addAllElts(getAllParentTypes());
        RootBlock p_ = null;
        StringBuilder strBuilder_ = new StringBuilder();
        addPkgIfNotEmpty(packageName,strBuilder_);
        for (RootBlock r: all_.getReverse()) {
            appendParts(strBuilder_,p_,r,_sepInner,_sep);
            strBuilder_.append(r.getName());
            p_ = r;
        }
        return strBuilder_.toString();
    }

    public final void validateIds(ContextEl _context) {
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        CustList<OverridableBlock> indexersGet_ = new CustList<OverridableBlock>();
        CustList<OverridableBlock> indexersSet_ = new CustList<OverridableBlock>();
        CustList<ConstructorId> idConstructors_ = new CustList<ConstructorId>();
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
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setIndexFile(where_);
                //block len
                unexp_.buildError(_context.getAnalysisMessages().getUnexpectedBlockExp());
                _context.addError(unexp_);
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
                        FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                        unexp_.setFileName(getFile().getFileName());
                        unexp_.setIndexFile(where_);
                        //key word type len inner
                        unexp_.buildError(_context.getAnalysisMessages().getUnexpectedMemberInst(),
                                getFullName()
                        );
                        _context.addError(unexp_);
                    }
                }
                if (b instanceof StaticBlock) {
                    int where_ = b.getOffset().getOffsetTrim();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setIndexFile(where_);
                    //key word len
                    unexp_.buildError(_context.getAnalysisMessages().getUnexpectedMemberInst(),
                            getFullName()
                    );
                    _context.addError(unexp_);
                }
            }
        }
        for (Block b: bl_) {
            if (b instanceof Returnable) {
                Returnable method_ = (Returnable) b;
                String name_ = method_.getName();
                if (method_ instanceof OverridableBlock) {
                    OverridableBlock m_ = (OverridableBlock) method_;
                    if (m_.getKind() == MethodKind.EXPLICIT_CAST) {
                        m_.buildImportedTypes(_context);
                        if (!StringList.quickEq(m_.getImportedReturnType(),getGenericString())) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.buildError(_context.getAnalysisMessages().getBadReturnType(),
                                    m_.getSignature(_context),
                                    getGenericString());
                            _context.addError(badMeth_);
                        } else if (m_.getParametersTypes().size() != 1) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.buildError(_context.getAnalysisMessages().getBadParams(),
                                    m_.getSignature(_context));
                            _context.addError(badMeth_);
                        } else if (!m_.isStaticMethod()) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.buildError(_context.getAnalysisMessages().getBadMethodModifier(),
                                    m_.getSignature(_context));
                            _context.addError(badMeth_);
                        } else if (m_.isVarargs()) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.buildError(_context.getAnalysisMessages().getBadMethodVararg(),
                                    m_.getSignature(_context));
                            _context.addError(badMeth_);
                        }
                    } else if (m_.getKind() == MethodKind.STD_METHOD) {
                        m_.buildImportedTypes(_context);
                        if (!_context.isValidToken(name_) && !StringList.quickEq(name_, keyWords_.getKeyWordToString())) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.buildError(_context.getAnalysisMessages().getBadMethodName(),
                                    name_);
                            _context.addError(badMeth_);
                        } else if (StringList.quickEq(name_, keyWords_.getKeyWordToString()) && !m_.hiddenInstance() && m_.getParametersTypes().isEmpty()) {
                            if (!StringList.quickEq(m_.getImportedReturnType(),stds_.getAliasString())) {
                                int r_ = m_.getNameOffset();
                                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                //method name len
                                badMeth_.buildError(_context.getAnalysisMessages().getBadReturnType(),
                                        name_,
                                        stds_.getAliasString());
                                _context.addError(badMeth_);
                            } else if (m_.getAccess() != AccessEnum.PUBLIC) {
                                int r_ = m_.getNameOffset();
                                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                                badMeth_.setFileName(getFile().getFileName());
                                badMeth_.setIndexFile(r_);
                                //method name len
                                badMeth_.buildError(_context.getAnalysisMessages().getBadAccess(),
                                        name_);
                                _context.addError(badMeth_);
                            }
                        }
                    } else {
                        if (m_.isStaticMethod()) {
                            int where_ = b.getOffset().getOffsetTrim();
                            FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                            unexp_.setFileName(getFile().getFileName());
                            unexp_.setIndexFile(where_);
                            //key word this len
                            unexp_.buildError(_context.getAnalysisMessages().getBadIndexerModifier(),
                                    m_.getSignature(_context));
                            _context.addError(unexp_);
                        }
                        if (m_.getParametersTypes().isEmpty()) {
                            int where_ = b.getOffset().getOffsetTrim();
                            FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                            unexp_.setFileName(getFile().getFileName());
                            unexp_.setIndexFile(where_);
                            //key word this len
                            unexp_.buildError(_context.getAnalysisMessages().getBadIndexerParams(),
                                    m_.getSignature(_context));
                            _context.addError(unexp_);
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
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadMethodName(),
                                name_);
                        _context.addError(badMeth_);
                    }
                }
                if (method_ instanceof OverridableBlock) {
                    OverridableBlock m_ = (OverridableBlock) method_;
                    if (m_.getKind() == MethodKind.STD_METHOD || m_.getKind() == MethodKind.EXPLICIT_CAST) {
                        MethodId id_ = m_.getId();
                        if (ContextEl.isEnumType(this)) {
                            String valueOf_ = stds_.getAliasEnumPredValueOf();
                            String values_ = stds_.getAliasEnumValues();
                            String string_ = stds_.getAliasString();
                            if (id_.eq(new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_)))) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                FoundErrorInterpret duplicate_;
                                duplicate_ = new FoundErrorInterpret();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                //method name len
                                duplicate_.buildError(_context.getAnalysisMessages().getReservedCustomMethod(),
                                        id_.getSignature(_context));
                                _context.addError(duplicate_);
                            }
                            if (id_.eq(new MethodId(MethodAccessKind.STATIC, values_, new StringList()))) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                FoundErrorInterpret duplicate_;
                                duplicate_ = new FoundErrorInterpret();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                //method name len
                                duplicate_.buildError(_context.getAnalysisMessages().getReservedCustomMethod(),
                                        id_.getSignature(_context));
                                _context.addError(duplicate_);
                            }
                        }
                        for (MethodId m: idMethods_) {
                            if (m.eq(id_)) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                FoundErrorInterpret duplicate_;
                                duplicate_ = new FoundErrorInterpret();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                //method name len
                                duplicate_.buildError(_context.getAnalysisMessages().getDuplicateCustomMethod(),
                                        id_.getSignature(_context));
                                _context.addError(duplicate_);
                            }
                        }
                        idMethods_.add(id_);
                    } else {
                        MethodId id_ = m_.getId();
                        for (MethodId m: idMethods_) {
                            if (m.eq(id_)) {
                                int r_ = m_.getOffset().getOffsetTrim();
                                FoundErrorInterpret duplicate_;
                                duplicate_ = new FoundErrorInterpret();
                                duplicate_.setIndexFile(r_);
                                duplicate_.setFileName(getFile().getFileName());
                                //method name len
                                duplicate_.buildError(_context.getAnalysisMessages().getDuplicateIndexer(),
                                        id_.getSignature(_context));
                                _context.addError(duplicate_);
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
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            String sgn_ = new MethodId(MethodAccessKind.INSTANCE, id_, new StringList()).getSignature(_context);
                            //method name len
                            duplicate_.buildError(_context.getAnalysisMessages().getDuplicateCustomMethod(),
                                    sgn_);
                            _context.addError(duplicate_);
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
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            //left par len
                            duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedCtor(),
                                    idCt_.getSignature(_context));
                            _context.addError(duplicate_);
                        }
                    }
                    idConstructors_.add(idCt_);
                }
                StringList l_ = method_.getParametersNames();
                StringList seen_ = new StringList();
                for (String v: l_) {
                    if (!_context.isValidToken(v)) {
                        FoundErrorInterpret b_;
                        b_ = new FoundErrorInterpret();
                        b_.setFileName(getFile().getFileName());
                        b_.setIndexFile(method_.getOffset().getOffsetTrim());
                        //param name len
                        b_.buildError(_context.getAnalysisMessages().getBadParamName(),
                                v);
                        _context.addError(b_);
                    }
                    if (method_ instanceof OverridableBlock) {
                        OverridableBlock i_ = (OverridableBlock) method_;
                        if (i_.getKind() == MethodKind.SET_INDEX) {
                            if (StringList.quickEq(v,keyWordValue_)) {
                                FoundErrorInterpret b_;
                                b_ = new FoundErrorInterpret();
                                b_.setFileName(getFile().getFileName());
                                b_.setIndexFile(method_.getOffset().getOffsetTrim());
                                //param name len
                                b_.buildError(_context.getAnalysisMessages().getReservedParamName(),
                                        v);
                                _context.addError(b_);
                            }
                        }
                    }
                    if (StringList.contains(seen_, v)){
                        FoundErrorInterpret b_;
                        b_ = new FoundErrorInterpret();
                        b_.setFileName(getFile().getFileName());
                        b_.setIndexFile(method_.getOffset().getOffsetTrim());
                        //param name len
                        b_.buildError(_context.getAnalysisMessages().getDuplicatedParamName(),
                                v);
                        _context.addError(b_);
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
                        FoundErrorInterpret b_;
                        b_ = new FoundErrorInterpret();
                        b_.setFileName(getFile().getFileName());
                        b_.setIndexFile(method_.getOffset().getOffsetTrim());
                        //trName_ len
                        b_.buildError(_context.getAnalysisMessages().getBadFieldName(),
                                trName_);
                        _context.addError(b_);
                    }
                    for (String m: idsField_) {
                        if (StringList.quickEq(m, trName_)) {
                            int r_ = method_.getOffset().getOffsetTrim();
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            //trName_ len
                            duplicate_.buildError(_context.getAnalysisMessages().getDuplicateField(),
                                    trName_);
                            _context.addError(duplicate_);
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
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setIndexFile(where_);
                //method name len
                unexp_.buildError(_context.getAnalysisMessages().getBadIndexerPairSet(),
                        i.getSignature(_context));
                _context.addError(unexp_);
            } else {
                if (set_.getModifier() != i.getModifier()) {
                    int where_ = i.getOffset().getOffsetTrim();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setIndexFile(where_);
                    //method name len
                    unexp_.buildError(_context.getAnalysisMessages().getBadIndexerModifiers(),
                            i.getSignature(_context));
                    _context.addError(unexp_);
                }
                if (set_.getAccess() != i.getAccess()) {
                    int where_ = i.getOffset().getOffsetTrim();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setIndexFile(where_);
                    //method name len
                    unexp_.buildError(_context.getAnalysisMessages().getBadIndexerAccesses(),
                            i.getSignature(_context));
                    _context.addError(unexp_);
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
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setIndexFile(where_);
                //method name len
                unexp_.buildError(_context.getAnalysisMessages().getBadIndexerPairGet(),
                        i.getSignature(_context));
                _context.addError(unexp_);
            }
        }
    }
    public abstract void setupBasicOverrides(ContextEl _context);

    final void useSuperTypesOverrides(ContextEl _context) {
        TypeUtil.buildOverrides(this, _context);
    }

    public abstract void buildDirectGenericSuperTypes(ContextEl _classes);
    public abstract void buildErrorDirectGenericSuperTypes(ContextEl _classes);

    public final StringList getAllGenericSuperTypes(ContextEl _classes) {
        Classes classes_ = _classes.getClasses();
        StringList current_ = new StringList(getGenericString());
        StringList all_ = new StringList();
        String obj_ = _classes.getStandards().getAliasObject();
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = Templates.getIdFromAllTypes(c);
                RootBlock curType_ = classes_.getClassBody(baseType_);
                if (curType_ == null) {
                    continue;
                }
                StringList superTypes_ = curType_.getImportedDirectSuperTypes();
                for (String t: superTypes_) {
                    String format_ = Templates.quickFormat(c, t, _classes);
                    if (!added(format_,all_,next_)) {
                        continue;
                    }
                    if (!StringList.quickEq(format_,obj_)) {
                        all_.add(format_);
                    }
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return all_;
    }

    public final StringList getAllGenericClasses(ContextEl _classes) {
        Classes classes_ = _classes.getClasses();
        StringList current_ = new StringList(getGenericString());
        StringList all_ = new StringList();
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = Templates.getIdFromAllTypes(c);
                RootBlock curType_ = classes_.getClassBody(baseType_);
                if (!(curType_ instanceof UniqueRootedBlock)) {
                    continue;
                }
                all_.add(c);
                StringList superTypes_ = curType_.getImportedDirectSuperTypes();
                for (String t: superTypes_) {
                    String format_ = Templates.quickFormat(c, t, _classes);
                    added(format_, all_, next_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return all_;
    }
    private static boolean added(String _type, StringList _all, StringList _next) {
        if (StringList.contains(_all, _type)) {
            return false;
        }
        _next.add(_type);
        return true;
    }
    public final void checkCompatibilityBounds(ContextEl _context) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        LgNames stds_ = _context.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        for (TypeVar t: getParamTypesMapValues()) {
            ObjectMap<MethodIdAncestor, CustList<MethodInfo>> signatures_;
            signatures_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
            StringList upper_ = Mapping.getAllUpperBounds(vars_, t.getName(),objectClassName_);
            CustList<MethodInfo> methods_;
            methods_ = new CustList<MethodInfo>();
            OperationNode.fetchParamClassAncMethods(_context,upper_,methods_);
            for (MethodInfo e: methods_) {
                if (e.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                    continue;
                }
                addClass(signatures_, new MethodIdAncestor(e.getFoundFormatted(),e.getAncestor()), e);
            }
            String fullName_ = "";
            lookForErrors(_context, vars_, signatures_, fullName_,t.getName());
        }
    }

    private void lookForErrors(ContextEl _context, StringMap<StringList> _vars, ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _signatures, String _fullName, String _virtualType) {
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> sub_;
        sub_ = RootBlock.getAllOverridingMethods(_signatures, _context);
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> er_;
        er_ = RootBlock.areCompatibleIndexer(_fullName, sub_);
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: er_.entryList()) {
            StringList retClasses_ = new StringList();
            StringList types_ = new StringList();
            for (MethodInfo m: e.getValue()) {
                retClasses_.add(m.getReturnType());
                types_.add(m.getClassName());
            }
            retClasses_.removeDuplicates();
            types_.removeDuplicates();
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(idRowCol);
            //original id len
            err_.buildError(_context.getAnalysisMessages().getReturnTypes(),
                    e.getKey().getClassMethodId().getSignature(_context),
                    StringList.join(types_,"&"),
                    StringList.join(retClasses_,"&"));
            _context.addError(err_);
        }
        er_ = RootBlock.areCompatibleFinalReturn(_fullName, _vars, sub_, _context);
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: er_.entryList()) {
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getValue()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            MethodInfo subInt_ = fClasses_.first();
            String subType_ = subInt_.getReturnType();
            for (MethodInfo s: e.getValue()) {
                String formattedSup_ = s.getReturnType();
                if (!Templates.isReturnCorrect(formattedSup_, subType_,_vars, _context)) {
                    FoundErrorInterpret err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(idRowCol);
                    //original id len
                    err_.buildError(_context.getAnalysisMessages().getFinalNotSubReturnType(),
                            subType_,
                            subInt_.getConstraints().getSignature(_context),
                            subInt_.getClassName(),
                            formattedSup_,
                            s.getConstraints().getSignature(_context),
                            s.getClassName());
                    _context.addError(err_);
                }
            }
        }
        er_ = RootBlock.areCompatibleMerged(_fullName, _vars, sub_, _context);
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: er_.entryList()) {
            StringList retClasses_ = new StringList();
            StringList types_ = new StringList();
            for (MethodInfo m: e.getValue()) {
                retClasses_.add(m.getReturnType());
                types_.add(m.getClassName());
            }
            retClasses_.removeDuplicates();
            types_.removeDuplicates();
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(idRowCol);
            //original id len
            err_.buildError(_context.getAnalysisMessages().getTwoReturnTypes(),
                    e.getKey().getClassMethodId().getSignature(_context),
                    StringList.join(types_,"&"),
                    StringList.join(retClasses_,"&"));
            _context.addError(err_);
        }
        er_ = RootBlock.areModifierCompatible(sub_);
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: er_.entryList()) {
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(idRowCol);
            //original id len
            err_.buildError(_context.getAnalysisMessages().getTwoFinal(),
                    _virtualType,
                    e.getKey().getClassMethodId().getSignature(_context));
            _context.addError(err_);
        }
    }

    public final void checkCompatibility(ContextEl _context) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> ov_;
        ov_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        OperationNode.fetchParamClassAncMethods(_context,new StringList(getGenericString()),methods_);
        for (MethodInfo e: methods_) {
            if (e.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                continue;
            }
            addClass(ov_, new MethodIdAncestor(e.getFoundFormatted(),e.getAncestor()), e);
        }
        String fullName_ = getFullName();
        lookForErrors(_context, vars_, ov_, fullName_,fullName_);
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
            if (mDer_.isAbstractMethod()) {
                if (mDer_.getFirstChild() != null) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(mDer_.getNameOffset());
                    //last char (brace) in header
                    err_.buildError(
                            _context.getAnalysisMessages().getAbstractMethodBody(),
                            getFullName(),
                            mDer_.getSignature(_context));
                    _context.addError(err_);
                }
            }
        }
        if (concreteClass_) {
            for (GeneCustMethod b: Classes.getMethodBlocks(this)) {
                MethodId idFor_ = b.getId();
                if (b.isAbstractMethod()) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(((OverridableBlock) b).getNameOffset());
                    //abstract key word
                    err_.buildError(
                            _context.getAnalysisMessages().getAbstractMethodConc(),
                            getFullName(),
                            idFor_.getSignature(_context));
                    _context.addError(err_);
                }
            }
            StringList allSuperClass_ = getAllGenericSuperTypes();
            for (String s: allSuperClass_) {
                String base_ = Templates.getIdFromAllTypes(s);
                RootBlock superBl_ = classesRef_.getClassBody(base_);
                for (GeneCustMethod m: Classes.getMethodBlocks(superBl_)) {
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
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(idRowCol);
                    //type id
                    err_.buildError(
                            _context.getAnalysisMessages().getAbstractMethodImpl(),
                            baseClass_,
                            m.getConstraints().getSignature(_context),
                            getFullName());
                    _context.addError(err_);
                }
            }
        } else {
            StringList allSuperClass_ = new StringList(getGenericString());
            allSuperClass_.addAllElts(getAllGenericSuperTypes());
            for (String s: allSuperClass_) {
                String base_ = Templates.getIdFromAllTypes(s);
                RootBlock superBl_ = classesRef_.getClassBody(base_);
                for (GeneCustMethod m: Classes.getMethodBlocks(superBl_)) {
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

    public static ObjectMap<MethodIdAncestor, CustList<MethodInfo>> getAllOverridingMethods(
            ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _methodIds,
            ContextEl _conf) {
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> map_;
        map_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: _methodIds.entryList()) {
            StringMap<MethodInfo> defs_ = new StringMap<MethodInfo>();
            CustList<MethodInfo> value_ = e.getValue();
            StringList list_ = new StringList(new CollCapacity(value_.size()));
            for (MethodInfo v: value_) {
                defs_.put(v.getClassName(), v);
                list_.add(v.getClassName());
            }
            list_ = PrimitiveTypeUtil.getSubclasses(list_, _conf);
            list_.removeDuplicates();
            CustList<MethodInfo> out_ = new CustList<MethodInfo>(new CollCapacity(list_.size()));
            for (String v: list_) {
                out_.add(defs_.getVal(v));
            }
            map_.put(e.getKey(), out_);
        }
        return map_;
    }

    private static ObjectMap<MethodIdAncestor, CustList<MethodInfo>> areCompatibleIndexer(
            String _fullName,
            ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _methodIds) {
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> output_;
        output_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: _methodIds.entryList()) {
            MethodIdAncestor cst_ = e.getKey();
            CustList<MethodInfo> classes_ = e.getValue();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            if (!StringList.isDollarWord(cst_.getClassMethodId().getName())) {
                StringList retClasses_ = new StringList();
                for (MethodInfo s: e.getValue()) {
                    retClasses_.add(s.getReturnType());
                }
                //indexer
                if (!retClasses_.onlyOneElt()) {
                    for (MethodInfo c: classes_) {
                        addClass(output_, e.getKey(), c);
                    }
                }
            }
        }
        return output_;
    }

    private static ObjectMap<MethodIdAncestor, CustList<MethodInfo>> areCompatibleFinalReturn(
            String _fullName,
            StringMap<StringList> _vars,
            ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _methodIds, ContextEl _context) {
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> output_;
        output_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: _methodIds.entryList()) {
            MethodIdAncestor cst_ = e.getKey();
            CustList<MethodInfo> classes_ = e.getValue();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getValue()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            if (!StringList.isDollarWord(cst_.getClassMethodId().getName())) {
                continue;
            }
            if (fClasses_.size() == 1) {
                MethodInfo subInt_ = fClasses_.first();
                String subType_ = subInt_.getReturnType();
                boolean err_ = false;
                for (MethodInfo s: e.getValue()) {
                    String formattedSup_ = s.getReturnType();
                    if (!Templates.isReturnCorrect(formattedSup_, subType_,_vars, _context)) {
                        err_ = true;
                        addClass(output_, cst_, s);
                    }
                }
                if (err_) {
                    addClass(output_, cst_, subInt_);
                }
            }
        }
        return output_;
    }

    private static ObjectMap<MethodIdAncestor, CustList<MethodInfo>> areCompatibleMerged(
            String _fullName,
            StringMap<StringList> _vars,
            ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _methodIds, ContextEl _context) {
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> output_;
        output_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: _methodIds.entryList()) {
            MethodIdAncestor cst_ = e.getKey();
            CustList<MethodInfo> classes_ = e.getValue();
            boolean skip_ = skip(_fullName, classes_);
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
            if (!StringList.isDollarWord(cst_.getClassMethodId().getName())) {
                continue;
            }
            if (fClasses_.size() == 1) {
                continue;
            }
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
                    if (!Templates.isReturnCorrect(other_, cur_, _vars, _context)) {
                        sub_ = false;
                        break;
                    }
                }
                if (sub_) {
                    subs_.add(cur_);
                }
            }
            if (!subs_.onlyOneElt()) {
                for (MethodInfo c: classes_) {
                    addClass(output_, e.getKey(), c);
                }
            }
        }
        return output_;
    }

    private static boolean skip(String _fullName, CustList<MethodInfo> _classes) {
        boolean skip_ = false;
        for (MethodInfo m: _classes) {
            String id_ = Templates.getIdFromAllTypes(m.getClassName());
            if (StringList.quickEq(id_,_fullName)) {
                skip_ = true;
                break;
            }
        }
        return skip_;
    }

    private static ObjectMap<MethodIdAncestor, CustList<MethodInfo>> areModifierCompatible(
            ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _methodIds) {
        ObjectMap<MethodIdAncestor, CustList<MethodInfo>> output_;
        output_ = new ObjectMap<MethodIdAncestor, CustList<MethodInfo>>();
        for (EntryCust<MethodIdAncestor, CustList<MethodInfo>> e: _methodIds.entryList()) {
            MethodIdAncestor cst_ = e.getKey();
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

    private static void addClass(ObjectMap<MethodIdAncestor, CustList<MethodInfo>> _map, MethodIdAncestor _key, MethodInfo _class) {
        if (_map.contains(_key)) {
            _map.getVal(_key).add(_class);
        } else {
            _map.put(_key, new CustList<MethodInfo>(_class));
        }
    }

    public void validateConstructors(ContextEl _cont) {
        boolean opt_ = optionalCallConstr(_cont);
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        IdMap<ConstructorId,ConstructorBlock> ctorsId_ = new IdMap<ConstructorId,ConstructorBlock>();
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof ConstructorBlock) {
                ctors_.add((ConstructorBlock) b);
                ctorsId_.addEntry(((ConstructorBlock) b).getId(),(ConstructorBlock) b);
            }
        }
        if (ctors_.isEmpty() && !opt_) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //original id len
            un_.buildError(_cont.getAnalysisMessages().getUndefinedSuperCtor(),
                    getFullName());
            _cont.addError(un_);
        }
        for (ConstructorBlock c: ctors_) {
            c.setupInstancingStep(_cont);
        }
        for (ConstructorBlock c: ctors_) {
            if (c.implicitConstr() && !opt_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(c.getOffset().getOffsetTrim());
                //original id len
                un_.buildError(_cont.getAnalysisMessages().getUndefinedSuperCtorCall(),
                        c.getSignature(_cont));
                _cont.addError(un_);
            }
        }
        for (EntryCust<ConstructorId, ConstructorBlock> e: ctorsId_.entryList()) {
            ConstructorBlock f = e.getValue();
            ConstructorId co_ = convert(e.getKey());
            CustList<ConstructorId> cycle_ = new CustList<ConstructorId>();
            while (true) {
                ConstructorBlock found_ = null;
                cycle_.add(co_);
                for (EntryCust<ConstructorId, ConstructorBlock> c: ctorsId_.entryList()) {
                    if (co_.eq(c.getKey())) {
                        found_ = c.getValue();
                        break;
                    }
                }
                if (found_ == null) {
                    break;
                }
                if (found_ == f && cycle_.size() > 1) {
                    FoundErrorInterpret cyclic_ = new FoundErrorInterpret();
                    StringList c_ = new StringList();
                    for (ConstructorId c: cycle_) {
                        c_.add(c.getSignature(_cont));
                    }
                    cyclic_.setFileName(getFile().getFileName());
                    cyclic_.setIndexFile(getOffset().getOffsetTrim());
                    //original contructor id len
                    cyclic_.buildError(_cont.getAnalysisMessages().getCyclicCtorCall(),
                            StringList.join(c_,"&"),
                            getFullName());
                    _cont.addError(cyclic_);
                    break;
                }
                co_ = convert(found_.getConstIdSameClass());
            }
        }
    }
    private static ConstructorId convert(ConstructorId _ctor) {
        if (_ctor == null) {
            return new ConstructorId("",new StringList(),true);
        }
        return _ctor;
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
            if (!Classes.canAccess(getFullName(), (AccessibleBlock) c, _cont)) {
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

    public boolean isSubTypeOf(String _fullName, ContextEl _an) {
        if (StringList.quickEq(getFullName(),_fullName)) {
            return true;
        }
        return StringList.contains(getAllSuperTypes(),_fullName);
    }

    @Override
    public boolean isTypeHidden(RootBlock _class, ContextEl _analyzable) {
        return !Classes.canAccess(getFullName(), _class, _analyzable);
    }
}
