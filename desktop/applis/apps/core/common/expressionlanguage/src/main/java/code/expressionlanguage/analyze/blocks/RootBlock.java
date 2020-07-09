package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingSuperTypes;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.types.OverridingMethod;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.util.ToStringMethodHeader;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public abstract class RootBlock extends BracedBlock implements AnnotableBlock,AnaGeneType,AnaInheritedType {

    private final String name;
    private final StringList nameErrors = new StringList();

    private final String packageName;

    private final AccessEnum access;

    private int accessOffset;

    private final String templateDef;

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    private CustList<OverridingMethod> allOverridingMethods;

    private CustList<TypeVar> paramTypes = new CustList<TypeVar>();

    private StringMap<TypeVar> paramTypesMap = new StringMap<TypeVar>();
    private final CustList<AnaResultPartType> results = new CustList<AnaResultPartType>();

    private final StringList directSuperTypes = new StringList();
    private final IntMap<String> importedDirectBaseSuperTypes = new IntMap<String>();

    private IntMap< String> rowColDirectSuperTypes;
    private IntMap< Boolean> explicitDirectSuperTypes = new IntMap< Boolean>();

    private int idRowCol;

    private StringList staticInitInterfaces = new StringList();
    private int templateDefOffset;
    private int nameLength;

    private CustList<Ints> paramTypesConstraintsOffset = new CustList<Ints>();
    private CustList<PartOffset> constraintsParts = new CustList<PartOffset>();
    private CustList<PartOffset> superTypesParts = new CustList<PartOffset>();


    private Ints staticInitInterfacesOffset = new Ints();
    private CustList<PartOffset> partsStaticInitInterfacesOffset = new CustList<PartOffset>();


    private String importedDirectSuperClass = "";
    private StringList staticInitImportedInterfaces = new StringList();
    private StringList importedDirectSuperInterfaces = new StringList();

    private StringList annotations = new StringList();

    private Ints annotationsIndexes = new Ints();
    private final StringList allGenericSuperTypes = new StringList();
    private final StringList allGenericClasses = new StringList();
    private final CustList<ClassMethodId> functional = new CustList<ClassMethodId>();
    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private int nbOperators;
    private StringList allSuperTypes = new StringList();

    RootBlock(int _idRowCol, String _name,
              String _packageName, OffsetAccessInfo _access, String _templateDef,
              IntMap<String> _directSuperTypes, OffsetsBlock _offset) {
        super(_offset);
        allOverridingMethods = new CustList<OverridingMethod>();
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
        nameLength = _name.length();
        if (!templateDef.isEmpty()) {
            templateDefOffset = idRowCol + nameLength;
            if (!_packageName.isEmpty()) {
                templateDefOffset += _packageName.length() + 1;
            }
        }
        if (!_packageName.isEmpty()) {
            nameLength += _packageName.length() + 1;
        }
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public abstract boolean isStaticType();

    public final StringList getAllGenericSuperTypes() {
        return allGenericSuperTypes;
    }

    public StringList getAllGenericClasses() {
        return allGenericClasses;
    }

    public IntMap< Boolean> getExplicitDirectSuperTypes() {
        return explicitDirectSuperTypes;
    }

    public StringList getStaticInitInterfaces() {
        return staticInitInterfaces;
    }

    public Ints getStaticInitInterfacesOffset() {
        return staticInitInterfacesOffset;
    }

    public void buildAnnotations(ContextEl _context, ExecAnnotableBlock _ex) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        roots = new CustList<OperationNode>();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            ops_.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
            roots.add(page_.getCurrentRoot());
        }
        _ex.getAnnotationsOps().clear();
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    public StringList getAnnotations() {
        return annotations;
    }


    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public StringList getImports() {
        return imports;
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

    public CustList<AnaResultPartType> getResults() {
        return results;
    }

    public StringList getDirectSuperTypes() {
        return directSuperTypes;
    }

    public IntMap<String> getImportedDirectBaseSuperTypes() {
        return importedDirectBaseSuperTypes;
    }

    protected void checkAccess(ContextEl _context,ExecRootBlock _exec) {
        useSuperTypesOverrides(_context);
        Classes classesRef_ = _context.getClasses();
        StringList allGenericSuperClasses_ = new StringList();
        for (String s: allGenericSuperTypes) {
            String base_ = StringExpUtil.getIdFromAllTypes(s);
            if (classesRef_.getClassBody(base_) instanceof ExecClassBlock) {
                allGenericSuperClasses_.add(s);
            }
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (ExecTypeVar t: _exec.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        String gene_ = _exec.getGenericString();
        StringList classes_ = new StringList(gene_);
        classes_.addAllElts(allGenericSuperClasses_);
        for (OverridingMethod e: allOverridingMethods) {
            CustList<ClassMethodId> locGeneInt_ = new CustList<ClassMethodId>();
            CustList<ClassMethodId> locGeneCl_ = new CustList<ClassMethodId>();
            for (ClassMethodId c: e.getMethodIds()) {
                String base_ = StringExpUtil.getIdFromAllTypes(c.getClassName());
                ExecRootBlock r_ = classesRef_.getClassBody(base_);
                if (r_ instanceof ExecInterfaceBlock) {
                    locGeneInt_.add(c);
                }
                if (r_ instanceof ExecClassBlock) {
                    locGeneCl_.add(c);
                }
            }
            for (ClassMethodId i: locGeneInt_) {
                String name_ = i.getClassName();
                MethodId id_ = i.getConstraints();
                ExecNamedFunctionBlock supInt_ = ExecBlock.getMethodBodiesById(_context,name_, id_).first();
                for (ClassMethodId c: locGeneCl_) {
                    String nameCl_ = c.getClassName();
                    MethodId idCl_ = c.getConstraints();
                    ExecNamedFunctionBlock supCl_ = ExecBlock.getMethodBodiesById(_context,nameCl_, idCl_).first();
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
                    if (((ExecOverridableBlock)supCl_).getKind() != MethodKind.STD_METHOD) {
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
                    if (!AnaTemplates.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _context)) {
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
        boolean add_ = true;
        while (c_ != null) {
            if (add_) {
                pars_.add(c_);
            }
            if (c_.withoutInstance()) {
                add_ = false;
            }
            c_ = c_.getParentType();
        }
        return pars_.getReverse();
    }
    public boolean withoutInstance() {
        return isStaticType();
    }
    public final void buildMapParamType(ContextEl _analyze,ExecRootBlock _exec) {
        paramTypesMap = new StringMap<TypeVar>();
        for (RootBlock r: getSelfAndParentTypes()) {
            if (r == this) {
                int j_ = 0;
                for (TypeVar t: paramTypes) {
                    StringList const_ = new StringList();
                    CustList<AnaResultPartType> results_ = new CustList<AnaResultPartType>();
                    Ints ints_ = paramTypesConstraintsOffset.get(j_);
                    if (t.getErrors().isEmpty()) {
                        constraintsParts.add(new PartOffset("<a name=\"m"+t.getOffset()+"\">",t.getOffset()));
                    } else {
                        String err_ = StringList.join(t.getErrors(),"\n\n");
                        constraintsParts.add(new PartOffset("<a name=\"m"+t.getOffset()+"\" title=\""+err_+"\" class=\"e\">",t.getOffset()));
                    }
                    constraintsParts.add(new PartOffset("</a>",t.getOffset()+t.getLength()));
                    _analyze.getAnalyzing().getCurrentParts().clear();
                    int off_ = t.getOffset() + 1;
                    int i_ = 0;
                    for (String c: t.getConstraints()) {
                        int d_ = ints_.get(i_);
                        AnaResultPartType res_ = ResolvingSuperTypes.resolveTypeMapping(_analyze, c, this, _exec, off_ + d_);
                        results_.add(res_);
                        const_.add(res_.getResult());
                        i_++;
                    }
                    constraintsParts.addAllElts(_analyze.getAnalyzing().getCurrentParts());
                    j_++;
                    TypeVar t_ = new TypeVar();
                    t_.setOffset(t.getOffset());
                    t_.setLength(t.getLength());
                    t_.getResults().addAllElts(results_);
                    t_.setConstraints(const_);
                    t_.setName(t.getName());
                    paramTypesMap.addEntry(t.getName(), t_);
                }
            } else {
                for (EntryCust<String,TypeVar> e: r.paramTypesMap.entryList()) {
                    boolean exist_ = false;
                    for (TypeVar t: r.getParamTypes()) {
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

    public CustList<TypeVar> getParamTypesMapValues() {
        return paramTypesMap.values();
    }

    public CustList<TypeVar> getParamTypes() {
        return paramTypes;
    }

    public StringList getParamTypesAsStringList() {
        StringList list_ = new StringList();
        for (TypeVar t: paramTypes) {
            list_.add(t.getName());
        }
        return list_;
    }

    public int getTemplateDefOffset() {
        return templateDefOffset;
    }

    public CustList<Ints> getParamTypesConstraintsOffset() {
        return paramTypesConstraintsOffset;
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

    public static void addPkgIfNotEmpty(String _pkg, StringBuilder _generic) {
        if (!_pkg.isEmpty()) {
            _generic.append(_pkg);
            _generic.append(DOT);
        }
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

    public AccessEnum getAccess() {
        return access;
    }
    /**
     @return a map with formatted id from super types as key
     and a list of (formatted super types and id) as value
     */
    public CustList<OverridingMethod> getAllOverridingMethods() {
        return allOverridingMethods;
    }

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

    public final void validateIds(ContextEl _context, ExecRootBlock _exec, Members _mem) {
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        CustList<OverridableBlock> indexersGet_ = new CustList<OverridableBlock>();
        CustList<OverridableBlock> indexersSet_ = new CustList<OverridableBlock>();
        CustList<ConstructorId> idConstructors_ = new CustList<ConstructorId>();
        CustList<Block> bl_;
        bl_ = ClassesUtil.getDirectChildren(this);
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
                b.setReachableError(true);
                b.getErrorsBlock().add(unexp_.getBuiltError());
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
                        ((RootBlock)b).addNameErrors(unexp_);
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
                    b.setReachableError(true);
                    b.getErrorsBlock().add(unexp_.getBuiltError());
                }
            }
        }
        CustList<MethodHeaderInfo> explicit_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> explicitId_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> explicitFrom_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> implicit_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> implicitId_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> implicitFrom_ = new CustList<MethodHeaderInfo>();
        for (Block b: bl_) {
            if (!(b instanceof NamedFunctionBlock)) {
                continue;
            }
            NamedFunctionBlock method_ = (NamedFunctionBlock) b;
            String name_ = method_.getName();
            if (method_ instanceof OverridableBlock) {
                OverridableBlock m_ = (OverridableBlock) method_;
                m_.buildImportedTypes(_context);
                if (m_.getKind() == MethodKind.OPERATOR) {
                    if (!StringExpUtil.isOper(m_.getName())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadOperatorName(),
                                name_);
                        _context.addError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    }
                    nbOperators++;
                } else if (m_.getKind() == MethodKind.EXPLICIT_CAST || m_.getKind() == MethodKind.IMPLICIT_CAST) {
                    if (m_.getParametersTypes().size() != 1) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadParams(),
                                m_.getSignature(_context));
                        _context.addError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!StringList.quickEq(m_.getImportedReturnType(),_exec.getGenericString())&&!StringList.quickEq(m_.getImportedParametersTypes().first(),_exec.getGenericString())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadReturnType(),
                                m_.getSignature(_context),
                                _exec.getGenericString());
                        _context.addError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!m_.isStaticMethod()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadMethodModifier(),
                                m_.getSignature(_context));
                        _context.addError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (m_.isVarargs()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadMethodVararg(),
                                m_.getSignature(_context));
                        _context.addError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else {
                        if (m_.getKind() == MethodKind.EXPLICIT_CAST) {
                            if (StringList.quickEq(m_.getImportedParametersTypes().first(),m_.getImportedReturnType())) {
                                explicitId_.add(new MethodHeaderInfo(m_.getId(),m_.getImportedReturnType(), m_.getAccess()));
                            } else if (StringList.quickEq(m_.getImportedReturnType(),_exec.getGenericString())){
                                explicit_.add(new MethodHeaderInfo(m_.getId(),m_.getImportedReturnType(), m_.getAccess()));
                            } else {
                                explicitFrom_.add(new MethodHeaderInfo(m_.getId(),m_.getImportedReturnType(), m_.getAccess()));
                            }
                        } else {
                            if (StringList.quickEq(m_.getImportedParametersTypes().first(),m_.getImportedReturnType())) {
                                implicitId_.add(new MethodHeaderInfo(m_.getId(),m_.getImportedReturnType(), m_.getAccess()));
                            } else if (StringList.quickEq(m_.getImportedReturnType(),_exec.getGenericString())){
                                implicit_.add(new MethodHeaderInfo(m_.getId(),m_.getImportedReturnType(), m_.getAccess()));
                            } else {
                                implicitFrom_.add(new MethodHeaderInfo(m_.getId(),m_.getImportedReturnType(), m_.getAccess()));
                            }
                        }
                    }
                } else if (m_.getKind() == MethodKind.TO_STRING) {
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
                        m_.addNameErrors(badMeth_);
                    } else if (m_.getAccess() != AccessEnum.PUBLIC) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_context.getAnalysisMessages().getBadAccess(),
                                name_);
                        _context.addError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else {
                        ToStringMethodHeader t_ = new ToStringMethodHeader(m_.getName(), m_.getImportedReturnType(), m_.isFinalMethod(),m_.isAbstractMethod());
                        _context.getClasses().getToStringMethods().addEntry(getFullName(), t_);
                    }
                } else if (m_.getKind() == MethodKind.STD_METHOD) {
                    if (!StringList.quickEq(name_, keyWords_.getKeyWordToString())) {
                        TokenErrorMessage mess_ = ManageTokens.partMethod(_context).checkStdToken(_context,name_);
                        if (mess_.isError()) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.setBuiltError(mess_.getMessage());
                            _context.addError(badMeth_);
                            m_.addNameErrors(badMeth_);
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
                        m_.addNameErrors(unexp_);
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
                        m_.addNameErrors(unexp_);
                    }
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
                if (m_.isKo()) {
                    int r_ = m_.getNameOffset();
                    FoundErrorInterpret b_ = new FoundErrorInterpret();
                    b_.setFileName(getFile().getFileName());
                    b_.setIndexFile(r_);
                    //underline index char
                    b_.buildError(_context.getAnalysisMessages().getBadIndexInParser());
                    _context.addError(b_);
                    m_.addNameErrors(b_);
                }
                TokenErrorMessage mess_ = ManageTokens.partMethod(_context).checkStdToken(_context,name_);
                if (mess_.isError()) {
                    int r_ = m_.getNameOffset();
                    FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                    badMeth_.setFileName(getFile().getFileName());
                    badMeth_.setIndexFile(r_);
                    //method name len
                    badMeth_.setBuiltError(mess_.getMessage());
                    _context.addError(badMeth_);
                    m_.addNameErrors(badMeth_);
                }
            }
            if (method_ instanceof OverridableBlock) {
                OverridableBlock m_ = (OverridableBlock) method_;
                if (m_.getKind() == MethodKind.TO_STRING || m_.getKind() == MethodKind.STD_METHOD || m_.getKind() == MethodKind.OPERATOR || m_.getKind() == MethodKind.EXPLICIT_CAST || m_.getKind() == MethodKind.IMPLICIT_CAST) {
                    MethodId id_ = m_.getId();
                    if (ContextUtil.isEnumType(_exec)) {
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
                            m_.addNameErrors(duplicate_);
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
                            m_.addNameErrors(duplicate_);
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
                            m_.addNameErrors(duplicate_);
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
                            m_.addNameErrors(duplicate_);
                        }
                    }
                    idMethods_.add(id_);
                }
            }
            if (method_ instanceof AnnotationMethodBlock) {
                MethodId id_ = ((AnnotationMethodBlock)method_).getId();
                for (MethodId m: idMethods_) {
                    if (m.eq(id_)) {
                        int r_ = method_.getOffset().getOffsetTrim();
                        FoundErrorInterpret duplicate_;
                        duplicate_ = new FoundErrorInterpret();
                        duplicate_.setIndexFile(r_);
                        duplicate_.setFileName(getFile().getFileName());
                        String sgn_ = id_.getSignature(_context);
                        //method name len
                        duplicate_.buildError(_context.getAnalysisMessages().getDuplicateCustomMethod(),
                                sgn_);
                        _context.addError(duplicate_);
                        method_.addNameErrors(duplicate_);
                    }
                }
                idMethods_.add(id_);
            }
            if (method_ instanceof ConstructorBlock) {
                method_.buildImportedTypes(_context);
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
                        method_.addNameErrors(duplicate_);
                    }
                }
                idConstructors_.add(idCt_);
            }
            StringList l_ = method_.getParametersNames();
            StringList seen_ = new StringList();
            int j_ = 0;
            for (String v: l_) {
                method_.addParamErrors();
                TokenErrorMessage res_ = ManageTokens.partParam(_context).checkStdToken(_context,v);
                if (res_.isError()) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(getFile().getFileName());
                    b_.setIndexFile(method_.getOffset().getOffsetTrim());
                    //param name len
                    b_.setBuiltError(res_.getMessage());
                    _context.addError(b_);
                    method_.addParamErrors(j_,b_);
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
                            method_.addParamErrors(j_,b_);
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
                    method_.addParamErrors(j_,b_);
                } else {
                    seen_.add(v);
                }
                j_++;
            }
        }
        buildFieldInfos(_context, bl_);
        _context.getAnalyzing().getExplicitCastMethods().addEntry(getFullName(),explicit_);
        _context.getAnalyzing().getExplicitIdCastMethods().addEntry(getFullName(),explicitId_);
        _context.getAnalyzing().getExplicitFromCastMethods().addEntry(getFullName(),explicitFrom_);
        _context.getAnalyzing().getImplicitCastMethods().addEntry(getFullName(),implicit_);
        _context.getAnalyzing().getImplicitIdCastMethods().addEntry(getFullName(),implicitId_);
        _context.getAnalyzing().getImplicitFromCastMethods().addEntry(getFullName(),implicitFrom_);
        validateIndexers(_context, indexersGet_, indexersSet_);
    }

    private static void buildFieldInfos(ContextEl _context, CustList<Block> _bl) {
        StringList idsField_ = new StringList();
        for (Block b: _bl) {
            if (!(b instanceof InfoBlock)) {
                continue;
            }
            InfoBlock method_ = (InfoBlock) b;
            method_.buildImportedType(_context);
            method_.retrieveNames(_context,idsField_);
        }
    }

    private void validateIndexers(ContextEl _context, CustList<OverridableBlock> _indexersGet, CustList<OverridableBlock> _indexersSet) {
        for (OverridableBlock i: _indexersGet) {
            MethodId iOne_ = i.getId();
            boolean ok_ = false;
            OverridableBlock set_ = null;
            for (OverridableBlock j: _indexersSet) {
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
                i.addNameErrors(unexp_);
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
                    i.addNameErrors(unexp_);
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
                    i.addNameErrors(unexp_);
                }
            }
        }
        for (OverridableBlock i: _indexersSet) {
            MethodId iOne_ = i.getId();
            boolean ok_ = false;
            for (OverridableBlock j: _indexersGet) {
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
                i.addNameErrors(unexp_);
            }
        }
    }

    public abstract void setupBasicOverrides(ContextEl _context,ExecRootBlock _exec);

    final void useSuperTypesOverrides(ContextEl _context) {
        AnaTypeUtil.buildOverrides(this, _context);
    }

    public final void buildDirectGenericSuperTypes(ContextEl _classes,ExecRootBlock _exec){
        IntMap< String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        int i_ = 0;
        importedDirectSuperInterfaces.clear();
        results.clear();
        for (String s: getDirectSuperTypes()) {
            int index_ = rcs_.getKey(i_);
            AnaResultPartType s_ = ResolvingSuperTypes.resolveTypeInherits(_classes,s,this, _exec,index_, getSuperTypesParts());
            results.add(s_);
            i_++;
            String base_ = StringExpUtil.getIdFromAllTypes(s_.getResult());
            ExecRootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (_exec instanceof ExecAnnotationBlock||r_ instanceof ExecInterfaceBlock) {
                _exec.getImportedDirectGenericSuperInterfaces().add(s_.getResult());
                importedDirectSuperInterfaces.add(s_.getResult());
            } else {
                _exec.setImportedDirectSuperClass(s_.getResult());
                importedDirectSuperClass = s_.getResult();
            }
        }
        if (_exec.getImportedDirectGenericSuperClass().isEmpty()) {
            _exec.setImportedDirectSuperClass(_classes.getStandards().getAliasObject());
            importedDirectSuperClass = _classes.getStandards().getAliasObject();

        }
    }

    public final StringList getAllGenericSuperTypes(ContextEl _classes,ExecRootBlock _exec) {
        Classes classes_ = _classes.getClasses();
        StringList current_ = new StringList(_exec.getGenericString());
        StringList all_ = new StringList();
        String obj_ = _classes.getStandards().getAliasObject();
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = StringExpUtil.getIdFromAllTypes(c);
                ExecRootBlock curType_ = classes_.getClassBody(baseType_);
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

    public final StringList getAllGenericClasses(ContextEl _classes,ExecRootBlock _exec) {
        Classes classes_ = _classes.getClasses();
        StringList current_ = new StringList(_exec.getGenericString());
        StringList all_ = new StringList();
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = StringExpUtil.getIdFromAllTypes(c);
                ExecRootBlock curType_ = classes_.getClassBody(baseType_);
                if (!(curType_ instanceof ExecUniqueRootedBlock)) {
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
            CustList<MethodIdAncestors> signatures_;
            signatures_ = new CustList<MethodIdAncestors>();
            StringList upper_ = Mapping.getAllUpperBounds(vars_, t.getName(),objectClassName_);
            CustList<CustList<MethodInfo>> methods_;
            methods_ = new CustList<CustList<MethodInfo>>();
            OperationNode.fetchParamClassAncMethods(_context,upper_,methods_);
            for (CustList<MethodInfo> l: methods_) {
                for (MethodInfo e: l) {
                    if (e.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                        continue;
                    }
                    addClass(signatures_, new MethodIdAncestor(e.getFoundFormatted(),e.getAncestor()), e);
                }
            }
            String fullName_ = "";
            lookForErrors(_context, vars_, signatures_, fullName_,t.getName());
        }
    }

    private void lookForErrors(ContextEl _context, StringMap<StringList> _vars, CustList<MethodIdAncestors> _signatures, String _fullName, String _virtualType) {
        CustList<MethodIdAncestors> sub_;
        sub_ = RootBlock.getAllOverridingMethods(_signatures, _context);
        CustList<MethodIdAncestors> er_;
        er_ = RootBlock.areCompatibleIndexer(_fullName, sub_);
        for (MethodIdAncestors e: er_) {
            StringList retClasses_ = new StringList();
            StringList types_ = new StringList();
            for (MethodInfo m: e.getMethodInfos()) {
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
                    e.getClassMethodId().getClassMethodId().getSignature(_context),
                    StringList.join(types_,"&"),
                    StringList.join(retClasses_,"&"));
            _context.addError(err_);
        }
        er_ = RootBlock.areCompatibleFinalReturn(_fullName, _vars, sub_, _context);
        for (MethodIdAncestors e: er_) {
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getMethodInfos()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            MethodInfo subInt_ = fClasses_.first();
            String subType_ = subInt_.getReturnType();
            for (MethodInfo s: e.getMethodInfos()) {
                String formattedSup_ = s.getReturnType();
                if (!AnaTemplates.isReturnCorrect(formattedSup_, subType_,_vars, _context)) {
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
        for (MethodIdAncestors e: er_) {
            StringList retClasses_ = new StringList();
            StringList types_ = new StringList();
            for (MethodInfo m: e.getMethodInfos()) {
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
                    e.getClassMethodId().getClassMethodId().getSignature(_context),
                    StringList.join(types_,"&"),
                    StringList.join(retClasses_,"&"));
            _context.addError(err_);
        }
        er_ = RootBlock.areModifierCompatible(sub_);
        for (MethodIdAncestors e: er_) {
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(idRowCol);
            //original id len
            err_.buildError(_context.getAnalysisMessages().getTwoFinal(),
                    _virtualType,
                    e.getClassMethodId().getClassMethodId().getSignature(_context));
            _context.addError(err_);
        }
    }

    public final void checkCompatibility(ContextEl _context,ExecRootBlock _exec) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        CustList<MethodIdAncestors> ov_;
        ov_ = new CustList<MethodIdAncestors>();
        CustList<CustList<MethodInfo>> methods_;
        methods_ = new CustList<CustList<MethodInfo>>();
        OperationNode.fetchParamClassAncMethods(_context,new StringList(_exec.getGenericString()),methods_);
        for (CustList<MethodInfo> l: methods_) {
            for (MethodInfo e: l) {
                if (e.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                    continue;
                }
                addClass(ov_, new MethodIdAncestor(e.getFoundFormatted(),e.getAncestor()), e);
            }
        }
        String fullName_ = getFullName();
        lookForErrors(_context, vars_, ov_, fullName_,fullName_);
    }
    public final void checkImplements(ContextEl _context,ExecRootBlock _exec) {
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
        for (Block b: ClassesUtil.getDirectChildren(this)) {
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
            for (GeneCustModifierMethod b: ExecBlock.getMethodExecBlocks(_exec)) {
                MethodId idFor_ = b.getId();
                if (b.isAbstractMethod()) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
                    err_.setIndexFile(((ExecOverridableBlock) b).getNameOffset());
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
                String base_ = StringExpUtil.getIdFromAllTypes(s);
                ExecRootBlock superBl_ = classesRef_.getClassBody(base_);
                for (GeneCustModifierMethod m: ExecBlock.getMethodExecBlocks(superBl_)) {
                    if (m.isAbstractMethod()) {
                        abstractMethods_.add(new ClassMethodId(s, m.getId()));
                    }
                }
            }
            for (ClassMethodId m: abstractMethods_) {
                String baseClass_ = m.getClassName();
                baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
                ExecRootBlock info_ = classesRef_.getClassBody(baseClass_);
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
            StringList allSuperClass_ = new StringList(_exec.getGenericString());
            allSuperClass_.addAllElts(getAllGenericSuperTypes());
            for (String s: allSuperClass_) {
                String base_ = StringExpUtil.getIdFromAllTypes(s);
                ExecRootBlock superBl_ = classesRef_.getClassBody(base_);
                for (GeneCustModifierMethod m: ExecBlock.getMethodExecBlocks(superBl_)) {
                    if (m.isAbstractMethod()) {
                        abstractMethods_.add(new ClassMethodId(s, m.getId()));
                    }
                }
            }
            for (ClassMethodId m: abstractMethods_) {
                String baseClass_ = m.getClassName();
                baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
                ExecRootBlock info_ = classesRef_.getClassBody(baseClass_);
                StringMap<ClassMethodId> map_ = TypeUtil.getConcreteMethodsToCall(info_, m.getConstraints(), _context);
                if (!map_.contains(getFullName())) {
                    functional.add(m);
                }
            }
        }
        _exec.getFunctional().addAllElts(functional);
    }

    public static CustList<MethodIdAncestors> getAllOverridingMethods(
            CustList<MethodIdAncestors> _methodIds,
            ContextEl _conf) {
        CustList<MethodIdAncestors> map_;
        map_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            StringMap<MethodInfo> defs_ = new StringMap<MethodInfo>();
            CustList<MethodInfo> value_ = e.getMethodInfos();
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
            MethodIdAncestors l_ = new MethodIdAncestors(e.getClassMethodId());
            l_.getMethodInfos().addAllElts(out_);
            map_.add(l_);
        }
        return map_;
    }

    private static CustList<MethodIdAncestors> areCompatibleIndexer(
            String _fullName,
            CustList<MethodIdAncestors> _methodIds) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            if (!StringList.isDollarWord(cst_.getClassMethodId().getName())) {
                StringList retClasses_ = new StringList();
                for (MethodInfo s: e.getMethodInfos()) {
                    retClasses_.add(s.getReturnType());
                }
                //indexer
                if (!retClasses_.onlyOneElt()) {
                    for (MethodInfo c: classes_) {
                        addClass(output_, e.getClassMethodId(), c);
                    }
                }
            }
        }
        return output_;
    }

    private static CustList<MethodIdAncestors> areCompatibleFinalReturn(
            String _fullName,
            StringMap<StringList> _vars,
            CustList<MethodIdAncestors> _methodIds, ContextEl _context) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getMethodInfos()) {
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
                for (MethodInfo s: e.getMethodInfos()) {
                    String formattedSup_ = s.getReturnType();
                    if (!AnaTemplates.isReturnCorrect(formattedSup_, subType_,_vars, _context)) {
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

    private static CustList<MethodIdAncestors> areCompatibleMerged(
            String _fullName,
            StringMap<StringList> _vars,
            CustList<MethodIdAncestors> _methodIds, ContextEl _context) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            StringList retClasses_ = new StringList();
            for (MethodInfo s: e.getMethodInfos()) {
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
                    if (!AnaTemplates.isReturnCorrect(other_, cur_, _vars, _context)) {
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
                    addClass(output_, e.getClassMethodId(), c);
                }
            }
        }
        return output_;
    }

    private static boolean skip(String _fullName, CustList<MethodInfo> _classes) {
        boolean skip_ = false;
        for (MethodInfo m: _classes) {
            String id_ = StringExpUtil.getIdFromAllTypes(m.getClassName());
            if (StringList.quickEq(id_,_fullName)) {
                skip_ = true;
                break;
            }
        }
        return skip_;
    }

    private static CustList<MethodIdAncestors> areModifierCompatible(
            CustList<MethodIdAncestors> _methodIds) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getMethodInfos()) {
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

    private static void addClass(CustList<MethodIdAncestors> _map, MethodIdAncestor _key, MethodInfo _class) {
        boolean found_ = false;
        for (MethodIdAncestors m : _map) {
            if (m.getClassMethodId().eq(_key)) {
                m.getMethodInfos().add(_class);
                found_ = true;
                break;
            }
        }
        if (!found_) {
            MethodIdAncestors m_ = new MethodIdAncestors(_key);
            m_.getMethodInfos().add(_class);
            _map.add(m_);
        }
    }

    public void validateConstructors(ContextEl _cont) {
        boolean opt_ = optionalCallConstr(_cont);
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        IdMap<ConstructorId,ConstructorBlock> ctorsId_ = new IdMap<ConstructorId,ConstructorBlock>();
        for (Block b: ClassesUtil.getDirectChildren(this)) {
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
            c.setupInstancingStep(_cont,_cont.getAnalyzing().getMapMembers().getVal(this).getAllCtors().getVal(c));
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
        String superClass_ = getImportedDirectGenericSuperClass();
        String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
        RootBlock clMeta_ = _cont.getAnalyzing().getAnaClassBody(superClassId_);
        if (clMeta_ == null) {
            return true;
        }
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        for (Block b: ClassesUtil.getDirectChildren(clMeta_)) {
            if (b instanceof ConstructorBlock) {
                ctors_.add((ConstructorBlock) b);
            }
        }
        if (ctors_.isEmpty()) {
            return true;
        }
        for (ConstructorBlock c: ctors_) {
            Accessed a_ = new Accessed(c.getAccess(), clMeta_.getPackageName(), clMeta_.getFullName(), clMeta_.getOuterFullName());
            if (!ContextUtil.canAccess(getFullName(), a_, _cont)) {
                continue;
            }
            if (c.getId().getParametersTypes().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public CustList<PartOffset> getConstraintsParts() {
        return constraintsParts;
    }

    public abstract boolean mustImplement();

    public int getNbOperators() {
        return nbOperators;
    }

    public int getNameLength() {
        return nameLength;
    }

    public StringMap<ExecTypeVar> getParamTypesMapAsExec() {
        StringMap<ExecTypeVar> map_ = new StringMap<ExecTypeVar>();
        for (EntryCust<String,TypeVar> e: paramTypesMap.entryList()) {
            ExecTypeVar t_ = new ExecTypeVar();
            t_.setName(e.getValue().getName());
            t_.setConstraints(e.getValue().getConstraints());
            map_.addEntry(e.getKey(), t_);
        }
        return map_;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public CustList<PartOffset> getSuperTypesParts() {
        return superTypesParts;
    }

    public CustList<PartOffset> getPartsStaticInitInterfacesOffset() {
        return partsStaticInitInterfacesOffset;
    }

    public String getImportedDirectGenericSuperClass(){
        return importedDirectSuperClass;
    }
    public StringList getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
    }

    public void addNameErrors(FoundErrorInterpret _error) {
        nameErrors.add(_error.getBuiltError());
    }

    public void addNameErrors(String _error) {
        nameErrors.add(_error);
    }
    public StringList getNameErrors() {
        return nameErrors;
    }

    public void addCategoryErrors(FoundErrorInterpret _error) {
        setReachableError(true);
        getErrorsBlock().add(_error.getBuiltError());
    }

    public final RootBlock getOuterParent() {
        RootBlock t = this;
        RootBlock o = this;
        while (t != null) {
            o = t;
            t = t.getParentType();
        }
        return o;
    }
    public final String getParentFullName() {
        RootBlock par_ = getParentType();
        if (par_ == null) {
            return "";
        }
        return par_.getFullName();
    }
    public String getWildCardElement() {
        StringList allElements_ = new StringList();
        for (Block e: ClassesUtil.getDirectChildren(this)) {
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

    public final String getWildCardString() {
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
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
    public final CustList<RootBlock> getAllParentTypesReverse() {
        return getAllParentTypes().getReverse();
    }
    public String getOuterFullName() {
        return getOuterParent().getFullName();
    }

    public boolean isSubTypeOf(String parName_, ContextEl context) {
        if (StringList.quickEq(parName_,getFullName())) {
            return true;
        }
        return StringList.contains(getAllSuperTypes(),parName_);
    }
    public StringList getAllSuperTypes(){
        return allSuperTypes;
    }

    public String getGenericString() {
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
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
}
