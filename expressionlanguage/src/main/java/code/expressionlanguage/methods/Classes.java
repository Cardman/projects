package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.errors.stds.StdErrorList;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.util.*;

public final class Classes {

    public static final String TEMP_PREFIX = "tmp";
    private static final char DOT = '.';

    private final StringMap<RootBlock> classesBodies;
    private final StringMap<FileBlock> filesBodies;
    private final StringMap<String> resources;

    private StringMap<StringMap<Struct>> staticFields;

    private final ErrorList errorsDet;
    private final WarningList warningsDet;
    private final StdErrorList stdErrorDet;
    private final StringList messagesErrorDet;
    private DefaultLockingClass locks;
    private String iteratorVarCust;
    private String hasNextVarCust;
    private String nextVarCust;
    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private CustList<ExecOperationNode> expsIteratorCust;
    private CustList<ExecOperationNode> expsHasNextCust;
    private CustList<ExecOperationNode> expsNextCust;
    private CustList<ExecOperationNode> expsIteratorTableCust;
    private CustList<ExecOperationNode> expsHasNextPairCust;
    private CustList<ExecOperationNode> expsNextPairCust;
    private CustList<ExecOperationNode> expsFirstCust;
    private CustList<ExecOperationNode> expsSecondCust;
    private CustList<OperatorBlock> operators;
    private StringList packagesFound = new StringList();

    public Classes(){
        classesBodies = new StringMap<RootBlock>();
        filesBodies = new StringMap<FileBlock>();
        resources = new StringMap<String>();
        errorsDet = new ErrorList();
        warningsDet = new WarningList();
        staticFields = new StringMap<StringMap<Struct>>();
        operators = new CustList<OperatorBlock>();
        stdErrorDet = new StdErrorList();
        messagesErrorDet = new StringList();
    }
    public void putFileBlock(String _fileName, FileBlock _fileBlock) {
        filesBodies.put(_fileName, _fileBlock);
    }
    public StringList getPackagesFound() {
        return packagesFound;
    }
    public FileBlock getFileBody(String _string) {
        return filesBodies.getVal(_string);
    }
    public StringMap<FileBlock> getFilesBodies() {
        return filesBodies;
    }

    public StringMap<String> getResources() {
		return resources;
	}
    public void processBracedClass(RootBlock _root, ContextEl _context) {
        String fullName_ = _root.getFullName();
        if (classesBodies.contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedType(),
                    fullName_);
            _context.addError(d_);
            _context.getAnalyzing().setDuplicatedType(true);
        }
        _context.getAnalyzing().setCurrentBlock(_root);
        String packageName_;
        packageName_ = _root.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (packageName_.trim().isEmpty()) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getIdRowCol());
            //key word category len
            badCl_.buildError(_context.getAnalysisMessages().getEmptyPackage());
            _context.addError(badCl_);
        }
        StringList elements_ = StringList.splitChars(packageName_, DOT);
        for (String e: elements_) {
            String tr_ = e.trim();
            if (!_context.isValidToken(tr_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                //pkg part or dot
                badCl_.buildError(_context.getAnalysisMessages().getBadPartClassName(),
                        tr_
                );
                _context.addError(badCl_);
            }
        }
        String className_;
        className_ = _root.getName().trim();
        if (!_context.isValidToken(className_)) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getIdRowCol());
            //name part if possible or original type
            badCl_.buildError(_context.getAnalysisMessages().getBadPartClassName(),
                    className_
            );
            _context.addError(badCl_);
        }
        String fullDef_ = _root.getFullDefinition();
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        StringList params_ = Templates.getAllTypes(fullDef_);
        StringList namesFromParent_ = new StringList();
        RootBlock r_ = _root;
        if (!r_.isStaticType()) {
            while (true) {
                r_ = (RootBlock) r_.getParent();
                for (TypeVar t: r_.getParamTypes()) {
                    namesFromParent_.add(t.getName());
                }
                if (r_.isStaticType()) {
                    break;
                }
            }
        }
        int tempOff_ = _root.getTemplateDefOffset() + 1;
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            if (p.isEmpty()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                //char after def
                badCl_.buildError(_context.getAnalysisMessages().getEmptyPartClassName());
                _context.addError(badCl_);
                continue;
            }
            int delta_ = 0;
            String name_;
            if (p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                delta_++;
                name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
            } else {
                name_ = p;
            }
            TypeVar type_ = new TypeVar();
            int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(name_, indexDef_);
            String id_ = parts_.first();
            if (!_context.isValidToken(id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getBadPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
            }
            if (StringList.contains(varTypes_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getBadPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
                _context.getAnalyzing().setDuplicatedType(true);
            }
            if (StringList.contains(namesFromParent_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getBadPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
                _context.getAnalyzing().setDuplicatedType(true);
            }
            varTypes_.add(id_);
            StringList constraints_ = new StringList();
            if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                int begin_ = delta_ + indexDef_;
                Ints ct_ = new Ints();
                for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                    int off_ = begin_ + StringList.getFirstPrintableCharIndex(b);
                    constraints_.add(b);
                    ct_.add(off_);
                    begin_ += b.length() + 1;
                }
                _root.getParamTypesConstraintsOffset().add(ct_);
            } else {
                Ints ct_ = new Ints();
                ct_.add(0);
                _root.getParamTypesConstraintsOffset().add(ct_);
                constraints_.add(objectClassName_);
            }
            type_.setConstraints(constraints_);
            type_.setName(id_);
            _root.getParamTypes().add(type_);
            int off_ = tempOff_ + StringList.getFirstPrintableCharIndex(p);
            type_.setOffset(off_);
            type_.setLength(id_.length()+delta_);
            tempOff_ += p.length() + 1;
        }
        if (_root instanceof EnumBlock) {
            String fullNameOrig_ = ((EnumBlock)_root).getOriginalName();
            StringBuilder generic_ = new StringBuilder(fullNameOrig_);
            if (!_root.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t:_root.getParamTypes()) {
                    vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            StringBuilder sBuild_ = new StringBuilder(_context.getStandards().getAliasEnumParam());
            sBuild_.append(Templates.TEMPLATE_BEGIN);
            sBuild_.append(generic_);
            sBuild_.append(Templates.TEMPLATE_END);
            String type_ = sBuild_.toString();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof InnerElementBlock) {
            InnerElementBlock i_ = (InnerElementBlock) _root;
            EnumBlock par_ = (EnumBlock) _root.getParent();
            String type_ = StringList.concat(par_.getOriginalName(),i_.getTempClass());
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof AnnotationBlock) {
            String type_ = _context.getStandards().getAliasAnnotationType();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (lgNames_.getStandards().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedTypeStd(),
                    fullName_);
            _context.addError(d_);
            _context.getAnalyzing().setDuplicatedType(true);
        }
        if (PrimitiveTypeUtil.isPrimitive(fullName_, _context)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedTypePrim(),
                    fullName_);
            _context.addError(d_);
        }
        _context.getAnalyzing().getFoundTypes().add(_root);
        classesBodies.put(fullName_, _root);
    }

    public boolean isEmptyErrors() {
        return errorsDet.isEmpty();
    }
    public String displayErrors() {
        return errorsDet.display();
    }
    public void addError(FoundErrorInterpret _error) {
        errorsDet.add(_error);
    }
    public ErrorList getErrorsDet() {
        return errorsDet;
    }
    public String displayMessageErrors() {
        return messagesErrorDet.display();
    }
    public boolean isEmptyMessageError() {
        return messagesErrorDet.isEmpty();
    }
    public void addMessageError(String _std) {
        messagesErrorDet.add(_std);
    }

    public String displayStdErrors() {
        return stdErrorDet.display();
    }
    public boolean isEmptyStdError() {
        return stdErrorDet.isEmpty();
    }
    public void addStdError(StdWordError _std) {
        stdErrorDet.add(_std);
    }

    public boolean isEmptyWarnings() {
        return warningsDet.isEmpty();
    }
    public void addWarning(FoundWarningInterpret _warning) {
        warningsDet.add(_warning);
    }
    public String displayWarnings() {
        return warningsDet.display(this);
    }

    public void addResources(StringMap<String> _resources) {
    	for (EntryCust<String, String> e: _resources.entryList()) {
    		resources.addEntry(e.getKey(), e.getValue());
    	}
    }
    /**Resources are possibly added before analyzing file types*/
    public static void validateAll(StringMap<String> _files, ContextEl _context) {
        validateWithoutInit(_files, _context);
        if (!_context.isEmptyErrors()) {
            //all errors are logged here
            return;
        }
        tryInitStaticlyTypes(_context);
    }
    public static void validateWithoutInit(StringMap<String> _files, ContextEl _context) {
        Classes classes_ = _context.getClasses();
        if (!classes_.isEmptyStdError() || !classes_.isEmptyMessageError()) {
            //all standards errors are logged here
            return;
        }
        LgNames stds_ = _context.getStandards();
        StandardType type_ = stds_.getStandards().getVal(stds_.getAliasFct());
        StandardMethod method_ = type_.getMethods().getVal(new MethodId(
                MethodAccessKind.INSTANCE,
                stds_.getAliasCall(),
                new StringList(stds_.getAliasObject()),
                true
        ));
        MethodId id_ = method_.getId();
        String ret_ = method_.getImportedReturnType();
        String decl_ = method_.getDeclaringType();
        stds_.setMethodMetaInfo(new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, method_.getModifier(), ret_, id_, decl_));
        String strBuilder_ = stds_.getAliasStringBuilder();
        type_ = stds_.getStandards().getVal(strBuilder_);
        StandardConstructor ctor_ = type_.getConstructors().first();
        ConstructorId idCtor_ = ctor_.getGenericId();
        decl_ = ctor_.getDeclaringType();
        ret_ = ctor_.getImportedReturnType();
        stds_.setConstructorMetaInfo(new ConstructorMetaInfo(strBuilder_, AccessEnum.PUBLIC, idCtor_, ret_, idCtor_, decl_));
        String charType_ = stds_.getAliasCharacter();
        type_ = stds_.getStandards().getVal(charType_);
        StandardField field_ = type_.getFields().getVal(stds_.getAliasMinValueField());
        ret_ = field_.getImportedClassName();
        boolean staticElement_ = field_.isStaticField();
        boolean finalElement_ = field_.isFinalField();
        stds_.setFieldMetaInfo(new FieldMetaInfo(charType_, field_.getFieldName().first(), ret_, staticElement_, finalElement_, AccessEnum.PUBLIC));
        stds_.setClassMetaInfo(new ClassMetaInfo(stds_.getAliasVoid(),_context, ClassCategory.VOID,""));
        _context.setAnalyzing();
        Classes.buildPredefinedBracesBodies(_context);
        CustList<RootBlock> foundTypes_ = _context.getAnalyzing().getFoundTypes();
        _context.setAnalyzing();
        _context.getAnalyzing().getPreviousFoundTypes().addAllElts(foundTypes_);
        tryValidateCustom(_files, _context);
        if (!_context.isEmptyErrors()) {
            //all errors are logged here
            return;
        }
        _context.setNullAnalyzing();
    }
    private static void tryValidateCustom(StringMap<String> _files, ContextEl _context) {
        builtTypes(_files, _context, false);
    }
    public static void tryInitStaticlyTypes(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        DefaultLockingClass dl_ = cl_.getLocks();
        dl_.init(_context);
        for (RootBlock c: cl_.getClassBodies()) {
            for (Block b:getSortedDescNodes(c)) {
                if (b instanceof ReducableOperations) {
                    ((ReducableOperations)b).reduce(_context);
                }
            }
        }
        for (OperatorBlock o: cl_.getOperators()) {
            for (Block b:getSortedDescNodes(o)) {
                if (b instanceof ReducableOperations) {
                    ((ReducableOperations)b).reduce(_context);
                }
            }
        }
        CustList<String> all_ = cl_.classesBodies.getKeys();
        _context.setInitEnums(InitPhase.READ_ONLY_OTHERS);
        while (true) {
            StringList new_ = new StringList();
            for (String c: all_) {
                _context.resetInitEnums();
                StringMap<StringMap<Struct>> bk_ = buildFieldValues(cl_.staticFields);
                ProcessMethod.initializeClassPre(c, _context);
                if (_context.isFailInit()) {
                    cl_.staticFields = bk_;
                } else {
                    new_.add(c);
                }
            }
            StringList.removeAllElements(all_, new_);
            if (new_.isEmpty()) {
                break;
            }
        }
        _context.resetInitEnums();
        _context.setInitEnums(InitPhase.LIST);
        dl_.initAlwaysSuccess();
        for (String t: _context.getOptions().getTypesInit()) {
            String res_ = _context.resolveCandidate(ContextEl.removeDottedSpaces(t));
            if (_context.getClasses().getClassBody(res_) == null) {
                continue;
            }
            _context.resetInitEnums();
            ProcessMethod.initializeClass(res_,_context);
        }
        _context.resetInitEnums();
        StringList notInit_ = dl_.initAlwaysSuccess();
        if (_context.getOptions().isFailIfNotAllInit()) {
            for (String s: notInit_) {
                RootBlock r_ = cl_.getClassBody(s);
                FileBlock file_ = r_.getFile();
                FoundErrorInterpret n_ = new FoundErrorInterpret();
                n_.setFileName(file_.getFileName());
                n_.setIndexFile(r_.getIdRowCol());
                n_.buildError(_context.getAnalysisMessages().getNotInitClass(),
                        s);
                _context.addError(n_);
            }
        }
        _context.setInitEnums(InitPhase.NOTHING);
    }
    private static StringMap<StringMap<Struct>> buildFieldValues(StringMap<StringMap<Struct>> _infos) {
        StringMap<StringMap<Struct>> bkSt_ = new StringMap<StringMap<Struct>>();
        for (EntryCust<String, StringMap<Struct>> e: _infos.entryList()) {
            StringMap<Struct> b_ = new StringMap<Struct>();
            for (EntryCust<String, Struct> f: e.getValue().entryList()) {
                b_.addEntry(f.getKey(), f.getValue());
            }
            bkSt_.addEntry(e.getKey(), b_);
        }
        return bkSt_;
    }

    public static void buildPredefinedBracesBodies(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringMap<String> files_ = stds_.buildFiles(_context);
        builtTypes(files_, _context, true);
        _context.getStandards().buildIterable(_context);
    }
    private static void builtTypes(StringMap<String> _files, ContextEl _context, boolean _predefined) {
        tryBuildBracedClassesBodies(_files, _context, _predefined);
        if (_context.getAnalyzing().isDuplicatedType()) {
            return;
        }
        validateInheritingClasses(_context);
        validateIds(_context);
        validateOverridingInherit(_context);
        validateEl(_context);
        TypeUtil.checkInterfaces(_context);
    }
    public static void tryBuildBracedClassesBodies(StringMap<String> _files, ContextEl _context, boolean _predefined) {
        parseFiles(_context, _files, _predefined);
        Classes cl_ = _context.getClasses();
        StringList pkgFound_ = cl_.getPackagesFound();
        pkgFound_.addAllElts(getPackages(_context));
        validatePkgNames(_context);
    }
    private static void parseFiles(ContextEl _context, StringMap<String> _files, boolean _predefined) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileResolver.parseFile(file_, content_, _predefined, _context);
        }
    }
    private static void validatePkgNames(ContextEl _context) {
        Classes cl_ = _context.getClasses();
        StringList pkgFound_ = cl_.getPackagesFound();
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            if (!(r.getParent() instanceof FileBlock)) {
                continue;
            }
            String fullName_ = r.getFullName();
            for (String p: pkgFound_) {
                if (!p.startsWith(fullName_)) {
                    continue;
                }
                //ERROR
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(r.getFile().getFileName());
                d_.setIndexFile(r.getIdRowCol());
                //original id len
                d_.buildError(_context.getAnalysisMessages().getDuplicatedTypePkg(),
                        fullName_,
                        p);
                _context.addError(d_);
            }
        }
    }

    public static CustList<Block> getSortedDescNodes(Block _root) {
        CustList<Block> list_ = new CustList<Block>();
        Block c_ = _root;
        Block f_ = c_.getFirstChild();
        list_.add(c_);
        if (f_ == null) {
            return list_;
        }
        c_ = f_;
        while (c_ != null) {
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static Block getNext(Block _current, Block _root) {
        Block n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        Block current_ = _current;
        while (true) {
            n_ = current_.getNextSibling();
            if (n_ != null) {
                return n_;
            }
            n_ = current_.getParent();
            if (n_ == _root) {
                return null;
            }
            current_ = n_;
        }
    }
    public static CustList<GeneCustMethod> getMethodBlocks(RootBlock _element) {
        CustList<GeneCustMethod> methods_ = new CustList<GeneCustMethod>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof OverridableBlock) {
                methods_.add((GeneCustMethod) b);
            }
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((AnnotationMethodBlock) b);
            }
        }
        return methods_;
    }
    public static CustList<Block> getDirectChildren(Block _element) {
        CustList<Block> list_ = new CustList<Block>();
        if (_element == null) {
            return list_;
        }
        Block elt_ = _element.getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public static void validateInheritingClasses(ContextEl _context) {
        String objectClassName_ = _context.getStandards().getAliasObject();
        Classes classes_ = _context.getClasses();
        validateInheritingClassesId(_context);
        StringList resTwo_ = new StringList();
        for (RootBlock s: _context.getAnalyzing().getFoundTypes()) {
            resTwo_.add(s.getFullName());
        }
        StringList listTypesNames_ = _context.getAnalyzing().getListTypesNames();
        if (StringList.equalsSet(listTypesNames_,resTwo_)) {
            for (String s: listTypesNames_) {
                RootBlock c_ = classes_.getClassBody(s);
                _context.getAnalyzing().setCurrentBlock(c_);
                c_.buildDirectGenericSuperTypes(_context);
            }
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                _context.getAnalyzing().setCurrentBlock(c);
                c.buildMapParamType(_context);
            }
        } else {
            //Error but continue
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                c.buildErrorDirectGenericSuperTypes(_context);
            }
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                _context.getAnalyzing().setCurrentBlock(c);
                c.buildErrorMapParamType(_context);
            }
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            if (c.isStaticType()) {
                continue;
            }
            StringList allPossibleDirectSuperTypes_ = new StringList();
            StringList allDirectSuperTypes_ = new StringList();
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c.getParentType();
            while (p_ != null) {
                allAncestors_.add(p_.getFullName());
                p_ = p_.getParentType();
            }
            for (String s: c.getImportedDirectSuperTypes()) {
                GeneType s_ = _context.getClassBody(Templates.getIdFromAllTypes(s));
                if (!(s_ instanceof RootBlock)) {
                    continue;
                }
                if (s_.isStaticType()) {
                    continue;
                }
                allDirectSuperTypes_.add(s_.getFullName());
            }
            for (String a: allAncestors_) {
                RootBlock a_ = classes_.getClassBody(a);
                for (Block m: Classes.getDirectChildren(a_)) {
                    if (!(m instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock m_ = (RootBlock) m;
                    allPossibleDirectSuperTypes_.add(m_.getFullName());
                }
                for (String s: a_.getAllSuperTypes()) {
                    GeneType g_ = _context.getClassBody(s);
                    if (!(g_ instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock s_ = (RootBlock) g_;
                    for (Block m: Classes.getDirectChildren(s_)) {
                        if (!(m instanceof RootBlock)) {
                            continue;
                        }
                        RootBlock m_ = (RootBlock) m;
                        allPossibleDirectSuperTypes_.add(m_.getFullName());
                    }
                }
            }
            if (!allPossibleDirectSuperTypes_.containsAllObj(allDirectSuperTypes_)) {
                for (String s: allDirectSuperTypes_) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(c.getFile().getFileName());
                    enum_.setIndexFile(0);
                    //super type len
                    enum_.buildError(_context.getAnalysisMessages().getBadInheritsType(),
                            c.getFullName(),
                            s);
                    _context.addError(enum_);
                }
            }
        }
        classes_.validateSingleParameterizedClasses(_context);
        classes_.checkTemplatesDef(_context, objectClassName_);
    }
    public static void validateInheritingClassesId(ContextEl _context) {
        String objectClassName_ = _context.getStandards().getAliasObject();
        String enumClassName_ = _context.getStandards().getAliasEnumType();
        String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
        String annotName_ = _context.getStandards().getAliasAnnotationType();
        StringMap<Boolean> builtTypes_ = new StringMap<Boolean>();
        IdList<RootBlock> stClNames_ = new IdList<RootBlock>(_context.getAnalyzing().getFoundTypes());
        for (RootBlock r: _context.getAnalyzing().getPreviousFoundTypes()) {
            builtTypes_.put(r.getFullName(), true);
        }
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            builtTypes_.put(r.getFullName(), false);
        }
        while (true) {
            IdList<RootBlock> next_ = new IdList<RootBlock>();
            for (RootBlock r: stClNames_) {
                String c= r.getFullName();
                boolean ready_ = true;
                int index_ = 0;
                StringMap<Integer> foundNames_ = new StringMap<Integer>();
                for (EntryCust<Integer, String> e: r.getRowColDirectSuperTypes().entryList()) {
                    String s = e.getValue();
                    s = ContextEl.removeDottedSpaces(s);
                    String idSuper_ = Templates.getIdFromAllTypes(s);
                    int offset_ = e.getKey();
                    String void_ = _context.getStandards().getAliasVoid();
                    if (StringList.quickEq(idSuper_, void_)) {
                        FoundErrorInterpret undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        //_in len
                        undef_.buildError(_context.getAnalysisMessages().getVoidType(),
                                void_);
                        _context.addError(undef_);
                        index_++;
                        continue;
                    }
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (_context.getStandards().getStandards().contains(idSuper_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    idSuper_);
                            _context.addError(undef_);
                            index_++;
                            continue;
                        }
                        if (r instanceof AnnotationBlock) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_context.getAnalysisMessages().getBadInheritsType(),
                                    c,
                                    idSuper_);
                            _context.addError(undef_);
                            index_++;
                            continue;
                        }
                    }
                    if (r instanceof AnnotationBlock) {
                        foundNames_.addEntry(annotName_,e.getKey());
                        index_++;
                        continue;
                    }
                    StringList readyTypes_ = new StringList();
                    for (EntryCust<String, Boolean> f: builtTypes_.entryList()) {
                        if (f.getValue()) {
                            readyTypes_.add(f.getKey());
                        }
                    }
                    String foundType_ = _context.resolveBaseInherits(idSuper_, r, readyTypes_);
                    if (foundType_.isEmpty()) {
                        ready_ = false;
                        break;
                    }
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (StringList.quickEq(enumParamClassName_, foundType_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //original type len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _context.addError(undef_);
                            index_++;
                            continue;
                        }
                        if (StringList.quickEq(enumClassName_, foundType_) && !StringList.quickEq(c, enumParamClassName_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //original type len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _context.addError(undef_);
                            index_++;
                            continue;
                        }
                    }
                    foundNames_.addEntry(foundType_,e.getKey());
                    index_++;
                }
                if (!ready_) {
                    continue;
                }
                CustList<String> dup_ = foundNames_.getKeys();
                StringMap<Integer> counts_ = new StringMap<Integer>();
                for (String s: dup_) {
                    counts_.put(s,0);
                }
                for (String s: dup_) {
                    counts_.put(s, counts_.getVal(s)+1);
                }
                boolean hasDuplicates_ = false;
                for (EntryCust<String,Integer> e: counts_.entryList()) {
                    if (e.getValue() > 1) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(0);
                        //original type len
                        undef_.buildError(_context.getAnalysisMessages().getDuplicateSuper(),
                                c,e.getKey(),Integer.toString(e.getValue()));
                        _context.addError(undef_);
                        hasDuplicates_ = true;
                    }
                }
                if (hasDuplicates_) {
                    continue;
                }
                int indexType_ = -1;
                int nbDirectSuperClass_ = 0;
                for (EntryCust<String,Integer> e: foundNames_.entryList()) {
                    indexType_++;
                    String k_ = e.getKey();
                    int ind_ = e.getValue();
                    if (r instanceof AnnotationBlock) {
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        continue;
                    }
                    RootBlock s_ =_context.getClasses(). getClassBody(k_);
                    int offset_ = r.getRowColDirectSuperTypes().getKey(indexType_);
                    if (s_ instanceof UniqueRootedBlock) {
                        nbDirectSuperClass_++;
                    }
                    if (r.isStaticType()) {
                        if (!s_.isStaticType()) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original k_ string len
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeInn(),
                                    c,
                                    k_);
                            _context.addError(enum_);
                        }
                    } else {
                        int subSise_ = r.getSelfAndParentTypes().size();
                        int supSise_ = s_.getSelfAndParentTypes().size();
                        if (supSise_ > subSise_) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original k_ string len
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeAsInn(),
                                    c,
                                    k_,
                                    Integer.toString(subSise_-1),
                                    Integer.toString(supSise_-1));
                            _context.addError(enum_);
                        }
                    }
                    if (r instanceof InterfaceBlock) {
                        if (!(s_ instanceof InterfaceBlock)) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original type len
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeInt(),
                                    c,k_);
                            _context.addError(enum_);
                            continue;
                        }
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        continue;
                    }
                    if (r instanceof InnerTypeOrElement) {
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        continue;
                    }
                    if (s_.isFinalType()) {
                        FoundErrorInterpret enum_;
                        enum_ = new FoundErrorInterpret();
                        enum_.setFileName(r.getFile().getFileName());
                        enum_.setIndexFile(offset_);
                        //original type len
                        enum_.buildError(_context.getAnalysisMessages().getFinalType(),
                                c,k_);
                        _context.addError(enum_);
                        continue;
                    }
                    r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                }
                if (nbDirectSuperClass_ > 1) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(r.getFile().getFileName());
                    enum_.setIndexFile(r.getIdRowCol());
                    //second super class
                    enum_.buildError(_context.getAnalysisMessages().getSuperClass(),
                            c,Integer.toString(nbDirectSuperClass_));
                    _context.addError(enum_);
                }
                r.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                for (String f: foundNames_.getKeys()) {
                    RootBlock s_ = _context.getClasses().getClassBody(f);
                    if (s_ != null) {
                        r.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                    }
                }
                r.getAllSuperTypes().add(objectClassName_);
                r.getAllSuperTypes().removeDuplicates();
                _context.getAnalyzing().getListTypesNames().add(c);
                builtTypes_.set(c, true);
                next_.add(r);
            }
            if (next_.isEmpty()) {
                for (RootBlock r: stClNames_) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(r.getFile().getFileName());
                    undef_.setIndexFile(0);
                    //id len
                    undef_.buildError(_context.getAnalysisMessages().getUnknownSuperType(),
                            r.getFullName());
                    _context.addError(undef_);
                }
                break;
            }
            stClNames_.removeAllElements(next_);
        }
    }

    private void checkTemplatesDef(ContextEl _context,
                                   String _objectClassName) {
        LgNames stds_ = _context.getStandards();
        for (RootBlock s: _context.getAnalyzing().getFoundTypes()) {
            String c = s.getFullName();
            Mapping mapping_ = new Mapping();
            StringMap<StringList> cts_ = new StringMap<StringList>();
            StringList variables_ = new StringList();
            boolean ok_ = true;
            for (TypeVar t: s.getParamTypesMapValues()) {
                cts_.put(t.getName(), t.getConstraints());
                variables_.add(t.getName());
            }
            if (!variables_.isEmpty() && s instanceof AnnotationBlock) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                //first < after type id
                b_.buildError(_context.getAnalysisMessages().getAnnotationParam(),
                        c);
                _context.addError(b_);
                continue;
            }
            mapping_.setMapping(cts_);
            StringList cyclic_ = mapping_.getCyclic();
            if (!cyclic_.isEmpty()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                //first < after type id
                b_.buildError(_context.getAnalysisMessages().getCyclicMapping(),
                        c);
                _context.addError(b_);
                continue;
            }
            for (TypeVar t: s.getParamTypesMapValues()) {
                boolean existNative_ = false;
                boolean existCustom_ = false;
                StringList upper_ = Mapping.getAllUpperBounds(cts_, t.getName(),_objectClassName);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
                    if (b.startsWith("[")) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _context.addError(un_);
                    }
                    if (PrimitiveTypeUtil.isPrimitive(b,_context)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _context.addError(un_);
                    }
                    String baseParams_ = Templates.getIdFromAllTypes(b);
                    String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParams_).getComponent();
                    upperNotObj_.add(b);
                    if (classesBodies.contains(base_)) {
                        existCustom_ = true;
                    } else {
                        existNative_ = true;
                    }
                }
                boolean okLoc_ = true;
                if (existNative_ && existCustom_) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    //type var len => at def
                    un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                            c);
                    _context.addError(un_);
                    okLoc_ = false;
                    ok_ = false;
                }
                for (CustList<TypeInfo> g: OperationNode.typeLists(_context,upper_,MethodAccessKind.INSTANCE)) {
                    StringList all_ = new StringList();
                    for (TypeInfo i: g) {
                        all_.add(i.getType());
                    }
                    StringMap<StringList>baseParams_ = getBaseParams(all_);
                    for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                        if (!e.getValue().onlyOneElt()) {
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setFileName(s.getFile().getFileName());
                            duplicate_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                                    StringList.join(e.getValue(),"&"));
                            _context.addError(duplicate_);
                        }
                    }

                }
                if (okLoc_) {
                    int nbAbs_ = 0;
                    int nbFinal_ = 0;
                    if (existNative_) {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = Templates.getIdFromAllTypes(b);
                            String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            StandardType type_ = stds_.getStandards().getVal(base_);
                            if (!(type_ instanceof StandardClass)) {
                                continue;
                            }
                            StandardClass class_ = (StandardClass) type_;
                            if (class_.isFinalStdType()) {
                                nbFinal_++;
                            }
                            if (class_.isAbstractStdType()) {
                                nbAbs_++;
                            }
                        }
                    } else {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = Templates.getIdFromAllTypes(b);
                            String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            RootBlock r_ = getClassBody(base_);
                            if (!(r_ instanceof UniqueRootedBlock)) {
                                continue;
                            }
                            if (r_ instanceof EnumBlock) {
                                nbFinal_++;
                                continue;
                            }
                            if (r_.isAbstractType()) {
                                nbAbs_++;
                            }
                            if (r_.isFinalType()) {
                                nbFinal_++;
                            }
                        }
                    }
                    if (nbAbs_ > 1 || nbFinal_ > 0) {
                        if (nbAbs_ > 1) {
                            //error
                            FoundErrorInterpret inh_;
                            inh_ = new FoundErrorInterpret();
                            inh_.setFileName(s.getFile().getFileName());
                            inh_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            inh_.buildError(_context.getAnalysisMessages().getAbsMapping(),
                                    Integer.toString(nbAbs_));
                            _context.addError(inh_);
                            ok_ = false;
                        }
                        if (nbFinal_ > 0) {
                            //error
                            FoundErrorInterpret inh_;
                            inh_ = new FoundErrorInterpret();
                            inh_.setFileName(s.getFile().getFileName());
                            inh_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            inh_.buildError(_context.getAnalysisMessages().getFinalMapping(),
                                    Integer.toString(nbFinal_));
                            _context.addError(inh_);
                            ok_ = false;
                        }
                    }
                }
            }
            if (!ok_) {
                continue;
            }
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            for (TypeVar t: s.getParamTypesMapValues()) {
                map_.put(t.getName(), t.getConstraints());
            }
            for (TypeVar t: s.getParamTypesMapValues()) {
                for (String b: t.getConstraints()) {
                    if (!Templates.isCorrectTemplateAll(b, map_, _context)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                                b);
                        _context.addError(un_);
                    }
                }
            }
            for (String t: s.getImportedDirectSuperTypes()) {
                if (!Templates.isCorrectTemplateAll(t, map_, _context)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    // char : before super type
                    un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                            t);
                    _context.addError(un_);
                }
            }
        }
    }
    public CustList<OperatorBlock> getOperators() {
        return operators;
    }
    private void validateSingleParameterizedClasses(ContextEl _context) {
        for (RootBlock i: _context.getAnalyzing().getFoundTypes()) {
            StringList genericSuperTypes_ = i.getAllGenericSuperTypes(_context);
            StringMap<StringList> baseParams_ = getBaseParams(genericSuperTypes_);
            for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                if (!e.getValue().onlyOneElt()) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setFileName(i.getFile().getFileName());
                    duplicate_.setIndexFile(i.getIdRowCol());
                    //original id len
                    duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                            StringList.join(e.getValue(),"&"));
                    _context.addError(duplicate_);
                }
            }
            i.getAllGenericSuperTypes().addAllElts(genericSuperTypes_);
            StringList genericClasses_ = i.getAllGenericClasses(_context);
            i.getAllGenericClasses().addAllElts(genericClasses_);
        }
    }
    private static StringMap<StringList> getBaseParams(StringList _genericSuperTypes) {
        StringMap<StringList> baseParams_ = new StringMap<StringList>();
        for (String t: _genericSuperTypes) {
            String key_ = Templates.getIdFromAllTypes(t);
            if (baseParams_.contains(key_)) {
                baseParams_.getVal(key_).add(t);
            } else {
                baseParams_.put(key_, new StringList(t));
            }
        }
        return baseParams_;
    }

    public static void validateIds(ContextEl _context) {
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            _context.setGlobalClass(c.getGenericString());
            _context.getAnalyzing().setImporting(c);
            c.validateIds(_context);
        }
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        _context.setGlobalClass("");
        for (OperatorBlock o: _context.getClasses().getOperators()) {
            String name_ = o.getName();
            _context.getAnalyzing().setImporting(o);
            o.buildImportedTypes(_context);
            if (!StringExpUtil.isOper(name_)) {
                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                badMeth_.setFileName(_context.getCurrentFileName());
                badMeth_.setIndexFile(o.getNameOffset());
                //key word len
                badMeth_.buildError(_context.getAnalysisMessages().getBadOperatorName(),
                        name_);
                _context.addError(badMeth_);
            }
            MethodId id_ = o.getId();
            for (MethodId m: idMethods_) {
                if (m.eq(id_)) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(o.getOffset().getOffsetTrim());
                    duplicate_.setFileName(_context.getCurrentFileName());
                    //key word len
                    duplicate_.buildError(_context.getAnalysisMessages().getDuplicateOperator(),
                            id_.getSignature(_context));
                    _context.addError(duplicate_);
                }
            }
            idMethods_.add(id_);
            StringList l_ = o.getParametersNames();
            StringList seen_ = new StringList();
            for (String v: l_) {
                if (!_context.isValidToken(v)) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    //param name len
                    b_.buildError(_context.getAnalysisMessages().getBadParamName(),
                            v);
                    _context.addError(b_);
                }
                if (StringList.contains(seen_, v)){
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    //param name len
                    b_.buildError(_context.getAnalysisMessages().getDuplicatedParamName(),
                            v);
                    _context.addError(b_);
                } else {
                    seen_.add(v);
                }
            }
        }
    }
    public static void validateOverridingInherit(ContextEl _context) {
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.setupBasicOverrides(_context);
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.checkCompatibility(_context);
            c.checkImplements(_context);
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.checkCompatibilityBounds(_context);
        }
    }

    public static CustList<RootBlock> accessedClassMembers(RootBlock _clOwner) {
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        for (Block b: Classes.getDirectChildren(_clOwner)) {
            if (!(b instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) b;
            inners_.add(r_);
        }
        return inners_;
    }

    public static boolean canAccess(String _className, Block _block, Analyzable _context) {
        if (!(_block instanceof AccessibleBlock)) {
            return true;
        }
        return canAccess(_className,(AccessibleBlock)_block,_context);
    }
    public static boolean canAccess(String _className, AccessibleBlock _block, Analyzable _context) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return true;
        }
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        RootBlock root_ = _context.getClasses().getClassBody(baseClass_);
        if (root_ == null) {
            return false;
        }
        RootBlock belong_ = _block.belong();
        RootBlock parType_ = null;
        if (_block instanceof RootBlock) {
            parType_ = ((RootBlock) _block).getParentType();
        }
        String belongPkg_ = belong_.getPackageName();
        String rootPkg_ = root_.getPackageName();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (parType_ != null) {
                if (root_.isSubTypeOf(parType_.getFullName(),_context)) {
                    return true;
                }
            }
            if (root_.isSubTypeOf(belong_.getFullName(),_context)) {
                return true;
            }
            return StringList.quickEq(belongPkg_, rootPkg_);
        }
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            return StringList.quickEq(belongPkg_, rootPkg_);
        }
        RootBlock outBelong_ = belong_.getOuter();
        RootBlock outRoot_ = root_.getOuter();
        return StringList.quickEq(outBelong_.getFullName(), outRoot_.getFullName());
    }

    //validate el and its possible returned type
    private static void validateEl(ContextEl _context) {
        Classes cls_ = _context.getClasses();
        cls_.initStaticFields(_context);
        AnalyzedPageEl page_ = _context.getAnalyzing();
        if (!_context.getOptions().isReadOnly()) {
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                page_.setImporting(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                StringMap<AssignmentBefore> ass_;
                ass_ = new StringMap<AssignmentBefore>();
                for (Block b: bl_) {
                    if (!(b instanceof InfoBlock)) {
                        continue;
                    }
                    InfoBlock f_ = (InfoBlock) b;
                    if (!f_.isStaticField()) {
                        continue;
                    }
                    for (String f: f_.getFieldName()) {
                        AssignmentBefore as_ = new AssignmentBefore();
                        cls_.checkConstField(_context, c, fullName_, f);
                        as_.setUnassignedBefore(true);
                        ass_.put(f, as_);
                    }
                }
                AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
                StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
                b_.clear();
                asBlock_.getFinalVariablesGlobal().getFields().clear();
                asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
                asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
                asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
                b_.putAllMap(ass_);
                StringMap<SimpleAssignment> assAfter_;
                assAfter_ = new StringMap<SimpleAssignment>();
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        InfoBlock method_ = (InfoBlock) b;
                        if (!method_.isStaticField()) {
                            continue;
                        }
                        page_.setCurrentBlock(b);
                        method_.setAssignmentBefore(_context);
                        method_.buildExpressionLanguage(_context);
                        method_.setAssignmentAfter(_context);
                        assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(b).getFieldsRoot());
                    }
                    if (b instanceof StaticBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        StaticBlock method_ = (StaticBlock) b;
                        method_.buildFctInstructions(_context);
                        assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(b).getFieldsRoot());
                        page_.clearAllLocalVars();
                    }
                }
                for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                    String key_ = a.getKey();
                    ClassField id_ = new ClassField(fullName_, key_);
                    FieldInfo finfo_ = _context.getFieldInfo(id_);
                    if (!finfo_.isFinalField()) {
                        continue;
                    }
                    if (!StringList.contains(page_.getInitFields(),key_)) {
                        //error
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(c.getFile().getFileName());
                        un_.setIndexFile(c.getOffset().getOffsetTrim());
                        un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                key_,fullName_);
                        _context.addError(un_);
                    }
                }

            }
            _context.setAssignedStaticFields(true);
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                page_.setImporting(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                _context.getCoverage().putCalls(_context,fullName_);
                CustList<Block> bl_ = getDirectChildren(c);
                StringMap<AssignmentBefore> ass_;
                ass_ = new StringMap<AssignmentBefore>();
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        if (method_.isStaticField()) {
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        InfoBlock f_ = (InfoBlock) b;
                        for (String f: f_.getFieldName()) {
                            AssignmentBefore as_ = new AssignmentBefore();
                            as_.setUnassignedBefore(true);
                            ass_.put(f, as_);
                        }
                    }
                }
                AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
                StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
                asBlock_.getFinalVariablesGlobal().getFields().clear();
                asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
                asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
                asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
                b_.clear();
                b_.putAllMap(ass_);
                boolean hasCtor_ = false;
                for (Block b: bl_) {
                    if (b instanceof ConstructorBlock) {
                        hasCtor_ = true;
                        break;
                    }
                }
                StringMap<SimpleAssignment> assAfter_;
                assAfter_ = new StringMap<SimpleAssignment>();
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        if (method_.isStaticField()) {
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        FieldBlock method_ = (FieldBlock) b;
                        page_.setCurrentBlock(b);
                        method_.setAssignmentBefore(_context);
                        method_.buildExpressionLanguage(_context);
                        method_.setAssignmentAfter(_context);
                        assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(method_).getFieldsRoot());
                    }
                    if (b instanceof InstanceBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        InstanceBlock method_ = (InstanceBlock) b;
                        method_.buildFctInstructions(_context);
                        assAfter_.putAllMap(asBlock_.getFinalVariables().getVal(method_).getFieldsRoot());
                        page_.clearAllLocalVars();
                    }
                }
                b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
                b_.clear();
                if (!hasCtor_) {
                    for (EntryCust<String, SimpleAssignment> a : assAfter_.entryList()) {
                        String fieldName_ = a.getKey();
                        ClassField key_ = new ClassField(fullName_, fieldName_);
                        FieldInfo finfo_ = _context.getFieldInfo(key_);
                        if (!finfo_.isFinalField()) {
                            continue;
                        }
                        if (StringList.contains(page_.getInitFields(), fieldName_)) {
                            continue;
                        }
                        //error
                        for (Block b : bl_) {
                            if (b instanceof InfoBlock) {
                                if (StringList.contains(((InfoBlock) b).getFieldName(), fieldName_)) {
                                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                                    un_.setFileName(c.getFile().getFileName());
                                    un_.setIndexFile(((InfoBlock) b).getFieldNameOffset());
                                    un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                            fieldName_,fullName_);
                                    _context.addError(un_);
                                }
                            }
                        }
                    }
                }
                b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
                processInterfaceCtor(_context, c, fullName_, bl_);
                for (Block b: bl_) {
                    if (b instanceof ConstructorBlock) {
                        page_.getInitFieldsCtors().clear();
                        page_.getInitFieldsCtors().addAllElts(page_.getInitFields());
                        page_.setGlobalClass(c.getGenericString());
                        ConstructorBlock method_ = (ConstructorBlock) b;
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, params_, types_, method_.isVarargs());
                        method_.buildFctInstructions(_context);
                        IdMap<Block, AssignedVariables> id_ = _context.getContextEl().getAssignedVariables().getFinalVariables();
                        AssignedVariables assTar_ = id_.getVal(b);
                        for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                            String fieldName_ = f.getKey();
                            ClassField key_ = new ClassField(fullName_, fieldName_);
                            FieldInfo finfo_ = _context.getFieldInfo(key_);
                            if (!finfo_.isFinalField()) {
                                continue;
                            }
                            if (StringList.contains(page_.getInitFieldsCtors(),fieldName_)) {
                                continue;
                            }
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(c.getFile().getFileName());
                            un_.setIndexFile(method_.getNameOffset());
                            un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                    fieldName_,fullName_);
                            _context.addError(un_);
                        }
                        page_.getParameters().clear();
                        page_.clearAllLocalVars();
                    }
                }
            }
            _context.setAssignedFields(true);
            AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
            asBlock_.getFinalVariablesGlobal().getFields().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRoot().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            asBlock_.getFinalVariablesGlobal().getFieldsBefore().clear();
            StringMap<AssignmentBefore> b_ = asBlock_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();

            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                page_.setImporting(c);
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof OverridableBlock)) {
                        continue;
                    }
                    OverridableBlock method_ = (OverridableBlock) b;
                    if (method_.getKind() == MethodKind.STD_METHOD) {
                        page_.setGlobalClass(c.getGenericString());
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, params_, types_, method_.isVarargs());
                        method_.buildFctInstructions(_context);
                        page_.getParameters().clear();
                        page_.clearAllLocalVars();
                    } else {
                        page_.setGlobalClass(c.getGenericString());
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, params_, types_, method_.isVarargs());
                        cls_.processValueParam(_context, page_, c, method_);
                        method_.buildFctInstructions(_context);
                        page_.getParameters().clear();
                        page_.clearAllLocalVars();
                    }
                }
            }
            _context.setGlobalClass("");
            _context.getCoverage().putCalls(_context,"");
            for (OperatorBlock o : cls_.getOperators()) {
                page_.setImporting(o);
                _context.getCoverage().putCalls(_context,"",o);
                StringList params_ = o.getParametersNames();
                StringList types_ = o.getImportedParametersTypes();
                prepareParams(page_, params_, types_, o.isVarargs());
                o.buildFctInstructions(_context);
                page_.getParameters().clear();
                page_.clearAllLocalVars();
            }
        } else {
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                page_.setImporting(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof InfoBlock)) {
                        continue;
                    }
                    InfoBlock f_ = (InfoBlock) b;
                    page_.getAllDeclaredFields().addAllElts(f_.getFieldName());
                    if (!f_.isStaticField()) {
                        continue;
                    }
                    if (f_ instanceof FieldBlock) {
                        page_.getAssignedDeclaredFields().addAllElts(((FieldBlock)f_).getAssignedDeclaredFields());
                    }
                    if (f_ instanceof InnerTypeOrElement) {
                        page_.getAssignedDeclaredFields().add(((InnerTypeOrElement)f_).getUniqueFieldName());
                    }
                    for (String f: f_.getFieldName()) {
                        cls_.checkConstField(_context, c, fullName_, f);
                    }
                }
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        InfoBlock method_ = (InfoBlock) b;
                        if (!method_.isStaticField()) {
                            continue;
                        }
                        page_.setCurrentBlock(b);
                        method_.buildExpressionLanguageReadOnly(_context);
                    }
                    if (b instanceof StaticBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        StaticBlock method_ = (StaticBlock) b;
                        method_.buildFctInstructionsReadOnly(_context);
                        page_.clearAllLocalVarsReadOnly();
                    }
                }

            }
            _context.setAssignedStaticFields(true);
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                page_.setImporting(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                _context.getCoverage().putCalls(_context,fullName_);
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        page_.getAllDeclaredFields().addAllElts(method_.getFieldName());
                        if (method_.isStaticField()) {
                            page_.getAssignedDeclaredFields().addAllElts(method_.getFieldName());
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        page_.getAssignedDeclaredFields().addAllElts(((FieldBlock)b).getAssignedDeclaredFields());
                    }
                }
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        if (method_.isStaticField()) {
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        FieldBlock method_ = (FieldBlock) b;
                        page_.setCurrentBlock(b);
                        method_.buildExpressionLanguageReadOnly(_context);
                    }
                    if (b instanceof InstanceBlock) {
                        page_.setGlobalClass(c.getGenericString());
                        InstanceBlock method_ = (InstanceBlock) b;
                        method_.buildFctInstructionsReadOnly(_context);
                    }
                }
                processInterfaceCtor(_context, c, fullName_, bl_);
                for (Block b: bl_) {
                    if (b instanceof ConstructorBlock) {
                        page_.getInitFieldsCtors().clear();
                        page_.getInitFieldsCtors().addAllElts(page_.getInitFields());
                        page_.setGlobalClass(c.getGenericString());
                        ConstructorBlock method_ = (ConstructorBlock) b;
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, params_, types_, method_.isVarargs());
                        method_.buildFctInstructionsReadOnly(_context);
                        page_.getParameters().clear();
                        page_.clearAllLocalVarsReadOnly();
                    }
                }
            }
            _context.setAssignedFields(true);
            for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
                page_.setImporting(c);
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof OverridableBlock)) {
                        continue;
                    }
                    OverridableBlock method_ = (OverridableBlock) b;
                    if (method_.getKind() == MethodKind.STD_METHOD) {
                        page_.setGlobalClass(c.getGenericString());
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, params_, types_, method_.isVarargs());
                        method_.buildFctInstructionsReadOnly(_context);
                        page_.getParameters().clear();
                        page_.clearAllLocalVarsReadOnly();
                    } else {
                        page_.setGlobalClass(c.getGenericString());
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, params_, types_, method_.isVarargs());
                        cls_.processValueParam(_context, page_, c, method_);
                        method_.buildFctInstructionsReadOnly(_context);
                        page_.getParameters().clear();
                        page_.clearAllLocalVarsReadOnly();
                    }
                }
            }
            _context.setGlobalClass("");
            _context.getCoverage().putCalls(_context,"");
            for (OperatorBlock o : cls_.getOperators()) {
                page_.setImporting(o);
                _context.getCoverage().putCalls(_context,"",o);
                StringList params_ = o.getParametersNames();
                StringList types_ = o.getImportedParametersTypes();
                prepareParams(page_, params_, types_, o.isVarargs());
                o.buildFctInstructionsReadOnly(_context);
                page_.getParameters().clear();
                page_.clearAllLocalVarsReadOnly();
            }
        }
        _context.setAnnotAnalysis(true);
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            page_.setImporting(c);
            _context.setGlobalClass(c.getGenericString());
            CustList<Block> annotated_ = new CustList<Block>();
            annotated_.add(c);
            annotated_.addAllElts(getDirectChildren(c));
            for (Block b:annotated_) {
                _context.getAnalyzing().setCurrentBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    _context.setAnnotAnalysisField(true);
                    ((AnnotationMethodBlock)b).buildExpressionLanguage(_context);
                    _context.setAnnotAnalysisField(false);
                }
                if (b instanceof AnnotableBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    ((AnnotableBlock)b).buildAnnotations(_context);
                }
            }
        }
        _context.setGlobalClass("");
        for (OperatorBlock o : cls_.getOperators()) {
            page_.setImporting(o);
            _context.getAnalyzing().setCurrentBlock(o);
            _context.setAnnotAnalysisField(false);
            _context.getCoverage().putBlockOperationsField(_context,o);
            o.buildAnnotations(_context);
        }
        _context.setAnnotAnalysis(false);
        //init annotations here
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.validateConstructors(_context);
        }
    }

    private void processValueParam(ContextEl _context, AnalyzedPageEl _page, RootBlock _cl, OverridableBlock _method) {
        if (_method.getKind() == MethodKind.SET_INDEX) {
            String p_ = _context.getKeyWords().getKeyWordValue();
            CustList<OverridableBlock> getIndexers_ = new CustList<OverridableBlock>();
            for (Block d: Classes.getDirectChildren(_cl)) {
                if (!(d instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock i_ = (OverridableBlock) d;
                if (i_.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!i_.getId().eqPartial(_method.getId())) {
                    continue;
                }
                getIndexers_.add(i_);
            }
            if (getIndexers_.size() == 1) {
                OverridableBlock matching_ = getIndexers_.first();
                String c_ = matching_.getImportedReturnType();
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(c_);
                _page.getParameters().put(p_, lv_);
            }
        }
    }

    private static void processInterfaceCtor(ContextEl _context, RootBlock _cl, String _name, CustList<Block> _blocks) {
        boolean hasCtor_ = false;
        for (Block b: _blocks) {
            if (b instanceof ConstructorBlock) {
                hasCtor_ = true;
                break;
            }
        }
        StringList filteredCtor_ = new StringList();
        if (_cl instanceof UniqueRootedBlock) {
            Classes classes_ = _context.getClasses();
            UniqueRootedBlock un_ = (UniqueRootedBlock) _cl;
            StringList all_ = _cl.getAllSuperTypes();
            StringList allCopy_ = new StringList(all_);
            StringList.removeAllElements(allCopy_, _context.getStandards().getPredefinedInterfacesInitOrder());
            String superClass_ = un_.getImportedDirectGenericSuperClass();
            String superClassId_ = Templates.getIdFromAllTypes(superClass_);
            RootBlock superType_ = classes_.getClassBody(superClassId_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            for (String i: allCopy_) {
                RootBlock int_ = classes_.getClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (Block b: Classes.getDirectChildren(int_)) {
                    if (b instanceof NamedFunctionBlock) {
                        continue;
                    }
                    if (b instanceof GeneField) {
                        GeneField a_ = (GeneField) b;
                        if (!a_.isStaticField()) {
                            filteredCtor_.add(i);
                        }
                    }
                    if (b instanceof AloneBlock) {
                        AloneBlock a_ = (AloneBlock) b;
                        if (a_.getStaticContext() == MethodAccessKind.INSTANCE) {
                            filteredCtor_.add(i);
                        }
                    }
                }
            }
        }
        _context.getNeedInterfaces().clear();
        _context.getNeedInterfaces().addAllElts(filteredCtor_);
        if (!hasCtor_ && !filteredCtor_.isEmpty()) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFileName(_cl.getFile().getFileName());
            undef_.setIndexFile(0);
            //original id len
            undef_.buildError(_context.getAnalysisMessages().getMustCallIntCtor(),
                    _cl.getFullName());
            _context.addError(undef_);
        }
    }

    private static void prepareParams(AnalyzedPageEl _page, StringList _params, StringList _types, boolean _varargs) {
        int len_ = _params.size();
        if (!_varargs) {
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                String p_ = _params.get(i);
                String c_ = _types.get(i);
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(c_);
                _page.getParameters().put(p_, lv_);
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                String p_ = _params.get(i);
                String c_ = _types.get(i);
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(c_);
                _page.getParameters().put(p_, lv_);
            }
            String p_ = _params.last();
            String c_ = _types.last();
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(c_));
            _page.getParameters().put(p_, lv_);
        }
    }

    private void checkConstField(ContextEl _context, RootBlock _cl, String _clName, String _field) {
        if (staticFields.getVal(_clName).getVal(_field) == null) {
            if (!_cl.isStaticType()) {
                //ERROR
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cl.getFile().getFileName());
                un_.setIndexFile(_cl.getOffset().getOffsetTrim());
                //field name len
                un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                        _field,_clName);
                _context.addError(un_);
            }
        }
    }

    void initStaticFields(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();

        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            page_.setImporting(c);
            CustList<Block> bl_ = getDirectChildren(c);
            StringList fieldNames_ = new StringList();
            for (Block b: bl_) {
                if (!(b instanceof InnerTypeOrElement)) {
                    continue;
                }
                InnerTypeOrElement e_ = (InnerTypeOrElement)b;
                fieldNames_.addAllElts(e_.getFieldName());
            }
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                page_.setGlobalClass(c.getGenericString());
                page_.setCurrentBlock(f_);
                f_.retrieveNames(_context,fieldNames_);
            }
        }

        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<Struct> cl_ = new StringMap<Struct>();
            for (Block b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock)b;
                if (!i_.isStaticField()) {
                    continue;
                }
                for (String f: i_.getFieldName()) {
                    cl_.put(f, null);
                }
            }
            staticFields.put(fullName_, cl_);
        }
        ObjectMap<ClassField,FieldBlock> cstFields_ = new ObjectMap<ClassField,FieldBlock>();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            page_.setImporting(c);
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                if (!f_.isFinalField()) {
                    continue;
                }
                page_.setGlobalClass(c.getGenericString());
                page_.setCurrentBlock(f_);
                f_.buildExpressionLanguageReadOnly(_context);
                String cl_ = c.getFullName();
                for (String f: f_.getFieldName()) {
                    ClassField k_ = new ClassField(cl_, f);
                    cstFields_.addEntry(k_,f_);
                }
            }
        }
        while (true) {
            boolean calculatedValue_ = false;
            for (EntryCust<ClassField,FieldBlock> e: cstFields_.entryList()) {
                ClassField k_ = e.getKey();
                Struct value_ = getStaticField(k_);
                if (value_ != null) {
                    continue;
                }
                FieldBlock f_ = e.getValue();
                ElUtil.tryCalculate(f_, _context, k_.getFieldName());
                value_ = getStaticField(k_);
                if (value_ != null) {
                    calculatedValue_ = true;
                    break;
                }
            }
            if (!calculatedValue_) {
                break;
            }
        }
    }
    private static StringList getPackages(ContextEl _context) {
        StringList pkgs_ = new StringList();
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            String pkg_ = r.getPackageName();
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        return pkgs_;
    }

    public CustList<RootBlock> getClassBodies() {
        return classesBodies.values();
    }

    public RootBlock getClassBody(String _className) {
        return classesBodies.getVal(_className);
    }

    public static CustList<AnnotationMethodBlock> getMethodAnnotationBodiesById(Block _r, String _id) {
        CustList<AnnotationMethodBlock> methods_ = new CustList<AnnotationMethodBlock>();
        for (Block b: Classes.getDirectChildren(_r)) {
            if (!(b instanceof AnnotationMethodBlock)) {
                continue;
            }
            AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
            if (StringList.quickEq(a_.getName(), _id)) {
                methods_.add(a_);
            }
        }
        return methods_;
    }
    public static CustList<NamedFunctionBlock> getMethodBodiesById(ContextEl _context,String _genericClassName, MethodId _id) {
        return filter(getMethodBodies(_context,_genericClassName),_id);
    }
    private static CustList<NamedFunctionBlock> getMethodBodies(ContextEl _context,String _genericClassName) {
        CustList<NamedFunctionBlock> methods_ = new CustList<NamedFunctionBlock>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(base_);
        for (GeneCustMethod m: Classes.getMethodBlocks(r_)) {
            methods_.add((NamedFunctionBlock)m);
        }
        return methods_;
    }
    public static CustList<NamedFunctionBlock> getOperatorsBodiesById(ContextEl _context,MethodId _id) {
        return filter(getOperatorsBodies(_context),_id);
    }
    private static CustList<NamedFunctionBlock> getOperatorsBodies(ContextEl _context) {
        CustList<NamedFunctionBlock> methods_ = new CustList<NamedFunctionBlock>();
        Classes classes_ = _context.getClasses();
        for (OperatorBlock m: classes_.getOperators()) {
            methods_.add(m);
        }
        return methods_;
    }

    private static CustList<NamedFunctionBlock> filter(CustList<NamedFunctionBlock> _methods,MethodId _id) {
        CustList<NamedFunctionBlock> methods_ = new CustList<NamedFunctionBlock>();
        for (NamedFunctionBlock m: _methods) {
            if (((GeneCustMethod)m).getId().eq(_id)) {
                methods_.add(m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<ConstructorBlock> getConstructorBodiesById(Analyzable _context,String _genericClassName, ConstructorId _id) {
        CustList<ConstructorBlock> methods_ = new CustList<ConstructorBlock>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        for (EntryCust<String, RootBlock> c: classes_.classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                if (!method_.getId().eq(_id)) {
                    continue;
                }
                methods_.add(method_);
            }
        }
        return methods_;
    }
    public static CustList<GeneConstructor> getConstructorBodies(GeneType _type) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        if (_type instanceof StandardType) {
            for (StandardConstructor s: ((StandardType)_type).getConstructors()) {
                methods_.add(s);
            }
        } else {
            CustList<Block> bl_ = getDirectChildren((Block) _type);
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                methods_.add(method_);
            }
        }
        return methods_;
    }

    public boolean isInitialized(String _name) {
        return getLocks().getState(_name) != InitClassState.NOT_YET;
    }
    public boolean isSuccessfulInitialized(String _name) {
        return getLocks().getState(_name) == InitClassState.SUCCESS;
    }

    public void initializeStaticField(ClassField _clField, Struct _str) {
        staticFields.getVal(_clField.getClassName()).set(_clField.getFieldName(), _str);
    }
    int staticFieldCount() {
        int sum_ = 0;
        for (EntryCust<String, StringMap<Struct>> c: staticFields.entryList()) {
            for (EntryCust<String, Struct> e: c.getValue().entryList()) {
                if (e.getValue() == null) {
                    continue;
                }
                sum_++;
            }
        }
        return sum_;
    }
    public Struct getStaticField(ClassField _clField, String _returnType, ContextEl _context) {
        Struct strInit_ = getStaticField(_clField);
        if (strInit_ != null) {
            return strInit_;
        }
        return PrimitiveTypeUtil.defaultClass(_returnType, _context);
    }
    public Struct getStaticField(ClassField _clField) {
        return staticFields.getVal(_clField.getClassName()).getVal(_clField.getFieldName());
    }
    public boolean isCustomType(String _name) {
        String base_ = Templates.getIdFromAllTypes(_name);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public ClassMetaInfo getClassMetaInfo(String _name, ContextEl _context) {
        String base_ = Templates.getIdFromAllTypes(_name);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            RootBlock clblock_ = c.getValue();
            return getClassMetaInfo(clblock_, _name, _context);
        }
        return new ClassMetaInfo(_context.getStandards().getAliasVoid(),_context, ClassCategory.VOID,"");
    }
    public static ClassMetaInfo getClassMetaInfo(RootBlock _type,String _name, ContextEl _context) {
        ObjectMap<MethodId, MethodMetaInfo> infos_;
        infos_ = new ObjectMap<MethodId, MethodMetaInfo>();
        StringMap<FieldMetaInfo> infosFields_;
        infosFields_ = new StringMap<FieldMetaInfo>();
        ObjectMap<ConstructorId, ConstructorMetaInfo> infosConst_;
        infosConst_ = new ObjectMap<ConstructorId, ConstructorMetaInfo>();
        CustList<Block> bl_ = getDirectChildren(_type);
        String fileName_ = _type.getFile().getFileName();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        for (Block b: bl_) {
            AccessEnum access_ = AccessEnum.PUBLIC;
            if (b instanceof AccessibleBlock) {
                access_ = ((AccessibleBlock)b).getAccess();
            }
            if (b instanceof RootBlock) {
                inners_.add(((RootBlock) b).getFullName());
            }
            if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                String ret_ = method_.getImportedClassName();
                boolean staticElement_ = method_.isStaticField();
                boolean finalElement_ = method_.isFinalField();

                for (String f: method_.getFieldName()) {
                    FieldMetaInfo met_ = new FieldMetaInfo(_name, f, ret_, staticElement_, finalElement_, access_);
                    met_.setFileName(fileName_);
                    infosFields_.put(f, met_);
                }
            }
            if (b instanceof OverridableBlock) {
                OverridableBlock method_ = (OverridableBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                MethodId fid_;
                String formCl_ = _type.getFullName();
                boolean param_ = id_.canAccessParamTypesStatic(_context);
                if (Templates.correctNbParameters(_name, _context)) {
                    fid_ = id_.reflectFormat(_name, _context);
                    formCl_ = _name;
                } else {
                    fid_ = id_;
                }
                String idCl_ = _type.getFullName();
                if (param_) {
                    idCl_ = _name;
                }
                MethodMetaInfo met_ = new MethodMetaInfo(access_, idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                met_.setFileName(fileName_);
                infos_.put(id_, met_);
            }
            if (b instanceof AnnotationMethodBlock) {
                AnnotationMethodBlock method_ = (AnnotationMethodBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                MethodId fid_;
                String formCl_ = _type.getFullName();
                fid_ = id_;
                MethodMetaInfo met_ = new MethodMetaInfo(access_,_type.getFullName(), id_, method_.getModifier(), ret_, fid_, formCl_);
                met_.setFileName(fileName_);
                infos_.put(id_, met_);
            }
            if (b instanceof ConstructorBlock) {
                existCtor_ = true;
                ConstructorBlock method_ = (ConstructorBlock) b;
                ConstructorId id_ = method_.getGenericId();
                ConstructorId fid_;
                String ret_ = method_.getImportedReturnType();
                String formCl_ = method_.getDeclaringType();
                if (Templates.correctNbParameters(_name, _context)) {
                    fid_ = id_.reflectFormat(_name, _context);
                } else {
                    fid_ = id_;
                }
                ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, access_, id_, ret_, fid_, formCl_);
                met_.setFileName(fileName_);
                infosConst_.put(id_, met_);
            }
        }
        if (!existCtor_) {
            ConstructorId id_ = new ConstructorId(_name, new StringList(), false);
            AccessEnum acc_ = _type.getAccess();
            ConstructorId fid_;
            String ret_ = _context.getStandards().getAliasVoid();
            fid_ = id_;
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, fid_, _name);
            met_.setFileName(fileName_);
            infosConst_.put(id_, met_);
        }
        if (_type instanceof EnumBlock) {
            String valueOf_ = _context.getStandards().getAliasEnumPredValueOf();
            String values_ = _context.getStandards().getAliasEnumValues();
            String string_ = _context.getStandards().getAliasString();
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
            String ret_ = _type.getWildCardString();
            MethodId fid_;
            fid_ = id_;
            String decl_ = _type.getFullName();
            MethodMetaInfo met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, fid_, decl_);
            met_.setFileName(fileName_);
            infos_.put(id_, met_);
            id_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
            ret_ = PrimitiveTypeUtil.getPrettyArrayType(ret_);
            fid_ = id_;
            met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, fid_, decl_);
            met_.setFileName(fileName_);
            infos_.put(id_, met_);
        }
        RootBlock par_ = _type.getParentType();
        String format_;
        if (par_ != null) {
            String gene_ = par_.getGenericString();
            if (Templates.correctNbParameters(_name, _context)) {
                format_ = Templates.quickFormat(_name, gene_, _context);
            } else {
                format_ = par_.getFullName();
            }
        } else {
            format_ = "";
        }
        AccessEnum acc_ = _type.getAccess();
        boolean st_ = _type.isStaticType();
        if (_type instanceof InterfaceBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(_name, ((InterfaceBlock) _type).getImportedDirectSuperInterfaces(), format_, inners_,
                    infosFields_, infos_, infosConst_, ClassCategory.INTERFACE, st_, acc_);
            cl_.setFileName(fileName_);
            return cl_;
        }
        if (_type instanceof AnnotationBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(_name, new StringList(), format_, inners_,
                    infosFields_, infos_, infosConst_, ClassCategory.ANNOTATION, st_, acc_);
            cl_.setFileName(fileName_);
            return cl_;
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (_type instanceof EnumBlock) {
            cat_ = ClassCategory.ENUM;
        }
        boolean abs_ = _type.isAbstractType();
        boolean final_ = _type.isFinalType();
        String superClass_ = ((UniqueRootedBlock) _type).getImportedDirectGenericSuperClass();
        StringList superInterfaces_ = ((UniqueRootedBlock) _type).getImportedDirectGenericSuperInterfaces();
        ClassMetaInfo cl_ = new ClassMetaInfo(_name, superClass_, superInterfaces_, format_, inners_,
                infosFields_, infos_, infosConst_, cat_, abs_, st_, final_, acc_);
        cl_.setFileName(fileName_);
        return cl_;
    }
    public DefaultLockingClass getLocks() {
        return locks;
    }
    public void setLocks(DefaultLockingClass _locks) {
        locks = _locks;
    }

    public String getIteratorVarCust() {
        return iteratorVarCust;
    }

    public void setIteratorVarCust(String _iteratorVarCust) {
        iteratorVarCust = _iteratorVarCust;
    }

    public String getHasNextVarCust() {
        return hasNextVarCust;
    }

    public void setHasNextVarCust(String _hasNextVarCust) {
        hasNextVarCust = _hasNextVarCust;
    }

    public String getNextVarCust() {
        return nextVarCust;
    }

    public void setNextVarCust(String _nextVarCust) {
        nextVarCust = _nextVarCust;
    }

    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }
    public void setIteratorTableVarCust(String _iteratorTableVarCust) {
        iteratorTableVarCust = _iteratorTableVarCust;
    }
    public String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }
    public void setHasNextPairVarCust(String _hasNextPairVarCust) {
        hasNextPairVarCust = _hasNextPairVarCust;
    }
    public String getNextPairVarCust() {
        return nextPairVarCust;
    }
    public void setNextPairVarCust(String _nextPairVarCust) {
        nextPairVarCust = _nextPairVarCust;
    }
    public String getFirstVarCust() {
        return firstVarCust;
    }
    public void setFirstVarCust(String _firstVarCust) {
        firstVarCust = _firstVarCust;
    }
    public String getSecondVarCust() {
        return secondVarCust;
    }
    public void setSecondVarCust(String _secondVarCust) {
        secondVarCust = _secondVarCust;
    }
    public CustList<ExecOperationNode> getExpsIteratorCust() {
        return expsIteratorCust;
    }

    public void setExpsIteratorCust(CustList<ExecOperationNode> _expsIteratorCust) {
        expsIteratorCust = _expsIteratorCust;
    }

    public CustList<ExecOperationNode> getExpsHasNextCust() {
        return expsHasNextCust;
    }

    public void setExpsHasNextCust(CustList<ExecOperationNode> _expsHasNextCust) {
        expsHasNextCust = _expsHasNextCust;
    }

    public CustList<ExecOperationNode> getExpsNextCust() {
        return expsNextCust;
    }

    public void setExpsNextCust(CustList<ExecOperationNode> _expsNextCust) {
        expsNextCust = _expsNextCust;
    }
    public CustList<ExecOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }
    public void setExpsIteratorTableCust(
            CustList<ExecOperationNode> _expsIteratorTableCust) {
        expsIteratorTableCust = _expsIteratorTableCust;
    }
    public CustList<ExecOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }
    public void setExpsHasNextPairCust(CustList<ExecOperationNode> _expsHasNextPairCust) {
        expsHasNextPairCust = _expsHasNextPairCust;
    }
    public CustList<ExecOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }
    public void setExpsNextPairCust(CustList<ExecOperationNode> _expsNextPairCust) {
        expsNextPairCust = _expsNextPairCust;
    }
    public CustList<ExecOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }
    public void setExpsFirstCust(CustList<ExecOperationNode> _expsFirstCust) {
        expsFirstCust = _expsFirstCust;
    }
    public CustList<ExecOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }
    public void setExpsSecondCust(CustList<ExecOperationNode> _expsSecondCust) {
        expsSecondCust = _expsSecondCust;
    }

}
