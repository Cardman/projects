package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.errors.stds.StdErrorList;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.util.ClassEdge;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.util.*;
import code.util.graphs.Graph;
import code.util.graphs.SortedGraph;

public final class Classes {

    public static final String TEMP_PREFIX = "tmp";
    private static final char DOT = '.';

    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";

    private final StringMap<RootBlock> classesBodies;
    private final StringMap<FileBlock> filesBodies;
    private final StringMap<String> resources;

    private StringMap<StringMap<Struct>> staticFields;

    private final ErrorList errorsDet;
    private final WarningList warningsDet;
    private final StdErrorList stdErrorDet;
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
            DuplicateType d_ = new DuplicateType();
            d_.setId(fullName_);
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            addError(d_);
        }
        _context.getAnalyzing().setCurrentBlock(_root);
        String packageName_;
        packageName_ = _root.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (packageName_.trim().isEmpty()) {
            BadClassName badCl_ = new BadClassName();
            badCl_.setClassName(fullName_);
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getIdRowCol());
            addError(badCl_);
        }
        StringList elements_ = StringList.splitChars(packageName_, DOT);
        for (String e: elements_) {
            String tr_ = e.trim();
            if (!_context.isValidToken(tr_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullName_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                addError(badCl_);
            }
        }
        String className_;
        className_ = _root.getName().trim();
        if (!_context.isValidToken(className_) && !(_root instanceof InnerTypeOrElement)) {
            BadClassName badCl_ = new BadClassName();
            badCl_.setClassName(fullName_);
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getIdRowCol());
            addError(badCl_);
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
        namesFromParent_.removeDuplicates();
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            if (p.isEmpty()) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullDef_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                addError(badCl_);
                continue;
            }
            String name_;
            if (p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
            } else {
                name_ = p;
            }
            TypeVar type_ = new TypeVar();
            int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(name_, indexDef_);
            String id_ = parts_.first();
            if (!_context.isValidToken(id_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullDef_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                addError(badCl_);
            }
            if (StringList.contains(varTypes_, id_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullDef_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                addError(badCl_);
            }
            if (StringList.contains(namesFromParent_, id_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(fullDef_);
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(_root.getIdRowCol());
                addError(badCl_);
            }
            varTypes_.add(id_);
            StringList constraints_ = new StringList();
            if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                    constraints_.add(b);
                }
            } else {
                constraints_.add(objectClassName_);
            }
            type_.setConstraints(constraints_);
            type_.setName(id_);
            _root.getParamTypes().add(type_);
        }
        if (_root instanceof EnumBlock) {
            StringBuilder generic_ = new StringBuilder(fullName_);
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
            String type_ = StringList.concat(par_.getFullName(),i_.getTempClass());
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof AnnotationBlock) {
            String type_ = _context.getStandards().getAliasAnnotation();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (lgNames_.getStandards().contains(fullName_)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(fullName_);
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            addError(d_);
        }
        if (PrimitiveTypeUtil.isPrimitive(fullName_, _context)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(fullName_);
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            addError(d_);
        }
        classesBodies.put(fullName_, _root);
    }

    public boolean isEmptyErrors() {
        return errorsDet.isEmpty();
    }
    public String displayErrors() {
        return errorsDet.display(this);
    }
    public void addError(FoundErrorInterpret _error) {
        errorsDet.add(_error);
    }
    public ErrorList getErrorsDet() {
        return errorsDet;
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
    		resources.add(e.getKey(), e.getValue());
    	}
    }
    /**Resources are possibly added before analyzing file types*/
    public static void validateAll(StringMap<String> _files, ContextEl _context) {
        Classes classes_ = _context.getClasses();
        if (!classes_.isEmptyStdError()) {
            //all standards errors are logged here
            return;
        }
        Classes.buildPredefinedBracesBodies(_context);
        tryValidateCustom(_files, _context);
        if (!classes_.isEmptyErrors()) {
            //all errors are logged here
            return;
        }
        _context.setAnalyzing(null);
        tryInitStaticlyTypes(_context);
    }
    private static void tryValidateCustom(StringMap<String> _files, ContextEl _context) {
        builtTypes(_files, _context, false);
    }
    private static void tryInitStaticlyTypes(ContextEl _context) {
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
        _context.setInitEnums(true);
        while (true) {
            StringList new_ = new StringList();
            for (String c: all_) {
                _context.resetInitEnums();
                StringMap<StringMap<Struct>> bk_ = buildFieldValues(cl_.staticFields);
                ProcessMethod.initializeClassPre(c, _context);
                if (_context.isFailInit()) {
                    cl_.staticFields = buildFieldValues(bk_);
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
        _context.setInitEnums(false);
        dl_.initAlwaysSuccess();
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
        _context.setAnalyzing(new AnalyzedPageEl());
        LgNames stds_ = _context.getStandards();
        StringMap<String> files_ = stds_.buildFiles(_context);
        builtTypes(files_, _context, true);
        _context.getStandards().buildIterable(_context);
    }
    private static void builtTypes(StringMap<String> _files, ContextEl _context, boolean _predefined) {
        tryBuildBracedClassesBodies(_files, _context, _predefined);
        Classes cl_ = _context.getClasses();
        cl_.validateInheritingClasses(_context, _predefined);
        cl_.validateIds(_context, _predefined);
        cl_.validateOverridingInherit(_context, _predefined);
        cl_.validateEl(_context, _predefined);
        TypeUtil.checkInterfaces(_context, cl_.classesBodies.getKeys(), _predefined);
    }
    public static void tryBuildBracedClassesBodies(StringMap<String> _files, ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        parseFiles(_context, _files, _predefined);
        Classes cl_ = _context.getClasses();
        StringList pkgFound_ = cl_.getPackagesFound();
        pkgFound_.addAllElts(cl_.getPackages(_predefined));
        validatePkgNames(_context, _predefined);
    }
    private static void parseFiles(ContextEl _context, StringMap<String> _files, boolean _predefined) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileResolver.parseFile(file_, content_, _predefined, _context);
        }
    }
    private static void validatePkgNames(ContextEl _context, boolean _predefined) {
        Classes cl_ = _context.getClasses();
        StringList pkgFound_ = cl_.getPackagesFound();
        for (RootBlock r: _context.getClasses().getClassBodies(_predefined)) {
            if (!(r.getParent() instanceof FileBlock)) {
                continue;
            }
            String fullName_ = r.getFullName();
            for (String p: pkgFound_) {
                if (!p.startsWith(fullName_)) {
                    continue;
                }
                if (fullName_.length() < p.length()) {
                    continue;
                }
                //ERROR
                DuplicateType d_ = new DuplicateType();
                d_.setId(fullName_);
                d_.setFileName(r.getFile().getFileName());
                d_.setIndexFile(r.getIdRowCol());
                cl_.addError(d_);
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
    public static CustList<GeneMethod> getMethodBlocks(RootBlock _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof OverridableBlock) {
                methods_.add((GeneMethod) b);
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
    public void validateInheritingClasses(ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        String objectClassName_ = _context.getStandards().getAliasObject();
        CustList<RootBlock> clBodies_ = classesBodies.values();
        validateInheritingClassesId(_context, _predefined);
        Classes classes_ = _context.getClasses();
        StringList sorted_ =  _context.getSortedTypes(_predefined);
        if (sorted_ != null) {
            for (String s: sorted_) {
                RootBlock c_ = getClassBody(s);
                _context.getAnalyzing().setCurrentBlock(c_);
                c_.buildDirectGenericSuperTypes(_context);
            }
            for (RootBlock c: classes_.getClassBodies(_predefined)) {
                _context.getAnalyzing().setCurrentBlock(c);
                c.buildMapParamType(_context);
            }
        } else {
            //Error but continue
            for (RootBlock c: clBodies_) {
                c.buildErrorDirectGenericSuperTypes(_context);
            }
            for (RootBlock c: classes_.getClassBodies(_predefined)) {
                _context.getAnalyzing().setCurrentBlock(c);
                c.buildErrorMapParamType(_context);
            }
        }
        for (RootBlock c: classes_.getClassBodies(_predefined)) {
            StringList allPossibleDirectSuperTypes_ = new StringList();
            StringList allDirectSuperTypes_ = new StringList();
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c.getParentType();
            while (p_ != null) {
                allAncestors_.add(p_.getFullName());
                p_ = p_.getParentType();
            }
            for (String s: c.getImportedDirectSuperTypes()) {
                GeneType s_ = _context.getClassBody(s);
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
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    enum_.setClassName(s);
                    enum_.setFileName(c.getFile().getFileName());
                    enum_.setIndexFile(0);
                    classes_.addError(enum_);
                }
            }
        }
        classes_.validateSingleParameterizedClasses(_context, _predefined);
        checkTemplatesDef(_context, _predefined, objectClassName_);
    }
    public void validateInheritingClassesId(ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        String objectClassName_ = _context.getStandards().getAliasObject();
        String enumClassName_ = _context.getStandards().getAliasEnum();
        String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
        String annotName_ = _context.getStandards().getAliasAnnotation();
        CustList<RootBlock> staticClBodies_ = new CustList<RootBlock>();
        CustList<RootBlock> instClBodies_ = new CustList<RootBlock>();
        for (RootBlock r: getClassBodies(_predefined)) {
            if (r.isStaticType()) {
                staticClBodies_.add(r);
            } else {
                instClBodies_.add(r);
            }
        }
        StringMap<Boolean> builtTypes_ = new StringMap<Boolean>();
        StringList stClNames_ = new StringList();
        if (!_predefined) {
            for (RootBlock r: getClassBodies(true)) {
                String k_ = r.getFullName();
                stClNames_.add(k_);
                builtTypes_.put(k_, true);
            }
        }
        for (RootBlock r: staticClBodies_) {
            String k_ = r.getFullName();
            stClNames_.add(k_);
            if (_predefined) {
                builtTypes_.put(k_, false);
            } else {
                builtTypes_.put(k_, r.getFile().isPredefined());
            }
        }
        while (true) {
            StringList next_ = new StringList();
            for (String c: stClNames_) {
                boolean ready_ = true;
                RootBlock r_ = getClassBody(c);
                if (r_.getFile().isPredefined() != _predefined) {
                    continue;
                }
                if (r_ instanceof AnnotationBlock) {
                    int i_ = 0;
                    for (EntryCust<Integer, Boolean> e: r_.getExplicitDirectSuperTypes().entryList()) {
                        if (e.getValue()) {
                            int offset_ = r_.getRowColDirectSuperTypes().getKey(i_);
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(r_.getRowColDirectSuperTypes().getValue(i_));
                            undef_.setFileName(r_.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            addError(undef_);
                        }
                        i_++;
                    }
                }
                int index_ = 0;
                StringMap<Integer> foundNames_ = new StringMap<Integer>();
                for (EntryCust<Integer, String> e: r_.getRowColDirectSuperTypes().entryList()) {
                    String s = e.getValue();
                    s = ContextEl.removeDottedSpaces(s);
                    String idSuper_ = Templates.getIdFromAllTypes(s);
                    int offset_ = e.getKey();
                    if (StringList.quickEq(idSuper_, objectClassName_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(idSuper_);
                        undef_.setFileName(r_.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        addError(undef_);
                        index_++;
                        continue;
                    }
                    StringList readyTypes_ = new StringList();
                    for (EntryCust<String, Boolean> f: builtTypes_.entryList()) {
                        if (f.getValue()) {
                            readyTypes_.add(f.getKey());
                        }
                    }
                    if (r_ instanceof AnnotationBlock && !r_.getExplicitDirectSuperTypes().getValue(index_)) {
                        foundNames_.addEntry(annotName_,e.getKey());
                        index_++;
                        continue;
                    }
                    String foundType_ = _context.resolveBaseInherits(idSuper_, r_, index_, readyTypes_, true);
                    if (foundType_ == null) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(idSuper_);
                        undef_.setFileName(r_.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        addError(undef_);
                        index_++;
                        continue;
                    }
                    if (foundType_.isEmpty()) {
                        ready_ = false;
                        break;
                    }
                    foundNames_.addEntry(foundType_,e.getKey());
                    index_++;
                }
                if (!ready_) {
                    continue;
                }
                StringList dup_ = new StringList(foundNames_.getKeys());
                int oldSize_ = dup_.size();
                dup_.removeDuplicates();
                int newSize_ = dup_.size();
                if (oldSize_ != newSize_) {
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(r_.getFullName());
                    undef_.setFileName(r_.getFile().getFileName());
                    undef_.setIndexFile(0);
                    addError(undef_);
                    continue;
                }
                int indexType_ = -1;
                int nbDirectSuperClass_ = 0;
                for (EntryCust<String,Integer> e: foundNames_.entryList()) {
                    indexType_++;
                    String k_ = e.getKey();
                    int ind_ = e.getValue();
                    RootBlock s_ = getClassBody(k_);
                    if (r_ instanceof AnnotationBlock && s_ == null) {
                        r_.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        continue;
                    }
                    int offset_ = r_.getRowColDirectSuperTypes().getKey(indexType_);
                    if (s_ instanceof UniqueRootedBlock) {
                        nbDirectSuperClass_++;
                    }
                    if (r_ instanceof InterfaceBlock) {
                        if (!(s_ instanceof InterfaceBlock)) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            enum_.setClassName(k_);
                            enum_.setFileName(r_.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            addError(enum_);
                            continue;
                        }
                    } else if (s_.isFinalType()) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        enum_.setClassName(k_);
                        enum_.setFileName(r_.getFile().getFileName());
                        enum_.setIndexFile(offset_);
                        addError(enum_);
                        continue;
                    }
                    if (isReserved(enumParamClassName_, annotName_, k_)) {
                        Boolean exp_ = r_.getExplicitDirectSuperTypes().getVal(offset_);
                        if (exp_) {
                            UnknownClassName undef_;
                            undef_ = new UnknownClassName();
                            undef_.setClassName(k_);
                            undef_.setFileName(r_.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            addError(undef_);
                        } else {
                            r_.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        }
                        continue;
                    }
                    if (StringList.quickEq(k_, enumClassName_) && !StringList.quickEq(c, enumParamClassName_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(k_);
                        undef_.setFileName(r_.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        addError(undef_);
                        continue;
                    }
                    r_.getImportedDirectBaseSuperTypes().put(ind_,k_);
                }
                if (nbDirectSuperClass_ > 1) {
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    enum_.setClassName(EMPTY_STRING);
                    enum_.setFileName(r_.getFile().getFileName());
                    enum_.setIndexFile(r_.getIdRowCol());
                    addError(enum_);
                }
                r_.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                String dirSuper_ = objectClassName_;
                for (String f: foundNames_.getKeys()) {
                    GeneType s_ = _context.getClassBody(f);
                    if (s_ instanceof UniqueRootedBlock) {
                        dirSuper_ = f;
                    }
                    r_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                r_.getAllSuperTypes().add(objectClassName_);
                if (nbDirectSuperClass_ <= 1) {
                    prSupTypes(r_,dirSuper_,_context);
                }
                if (r_ instanceof UniqueRootedBlock) {
                    for (String t: r_.getAllSuperTypes()) {
                        GeneType g_ = _context.getClassBody(t);
                        if (g_ instanceof GeneInterface) {
                            ((UniqueRootedBlock) r_).getAllInterfaces().add(t);
                        }
                    }
                    ((UniqueRootedBlock) r_).getAllInterfaces().removeDuplicates();
                }
                r_.getAllSuperTypes().removeDuplicates();
                builtTypes_.set(c, true);
                next_.add(c);
            }
            if (next_.isEmpty()) {
                for (String c: stClNames_) {
                    RootBlock r_ = getClassBody(c);
                    if (r_.getFile().isPredefined() != _predefined) {
                        continue;
                    }
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(c);
                    undef_.setFileName(r_.getFile().getFileName());
                    undef_.setIndexFile(0);
                    addError(undef_);
                }
                break;
            }
            StringList.removeAllElements(stClNames_, next_);
        }
        if (instClBodies_.isEmpty()) {
            return;
        }
        instClBodies_.sortElts(new ClassDeepCmp());
        int rk_ = instClBodies_.first().getSelfAndParentTypes().size();
        int rkMax_ = instClBodies_.last().getSelfAndParentTypes().size();
        StringList possibleDirectBase_ = new StringList();
        StringList possibleQualifiers_ = new StringList();
        for (RootBlock s: staticClBodies_) {
            possibleQualifiers_.add(s.getFullName());
            possibleDirectBase_.add(s.getFullName());
        }
        StringMap<StringMap<Integer>> dirSuperTypes_ = new StringMap<StringMap<Integer>>();
        Graph<ClassEdge> inherit_;
        for (int i = rk_; i <= rkMax_; i++) {
            inherit_ = new Graph<ClassEdge>();
            CustList<RootBlock> curList_ = new CustList<RootBlock>();
            for (RootBlock c: instClBodies_) {
                int rkLoc_ = c.getSelfAndParentTypes().size();
                if (rkLoc_ != i) {
                    continue;
                }
                possibleDirectBase_.add(c.getFullName());
            }
            for (RootBlock c: instClBodies_) {
                int rkLoc_ = c.getSelfAndParentTypes().size();
                if (rkLoc_ != i) {
                    continue;
                }
                curList_.add(c);
                String d_ = c.getFullName();
                _context.getAnalyzing().setCurrentBlock(c);
                boolean int_ = c instanceof InterfaceBlock;
                int nbDirectSuperClass_ = 0;
                int index_ = -1;
                StringMap<Integer> names_ = new StringMap<Integer>();
                if (c instanceof InnerElementBlock) {
                    for (EntryCust<Integer, String> t: c.getRowColDirectSuperTypes().entryList()) {
                        index_++;
                        String v_ = t.getValue();
                        v_ = ContextEl.removeDottedSpaces(v_);
                        String base_ = Templates.getIdFromAllTypes(v_);
                        names_.addEntry(base_,t.getKey());
                    }
                    dirSuperTypes_.put(d_, names_);
                    continue;
                }
                for (EntryCust<Integer, String> t: c.getRowColDirectSuperTypes().entryList()) {
                    index_++;
                    String v_ = t.getValue();
                    v_ = ContextEl.removeDottedSpaces(v_);
                    String base_ = Templates.getIdFromAllTypes(v_);
                    int offset_ = c.getRowColDirectSuperTypes().getKey(index_);
                    if (isReserved(enumParamClassName_, annotName_, base_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(base_);
                        undef_.setFileName(c.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        addError(undef_);
                        continue;
                    }
                    if (StringList.quickEq(base_, objectClassName_)) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(base_);
                        undef_.setFileName(c.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        addError(undef_);
                        index_++;
                        continue;
                    }
                    String type_ = base_;
                    base_ = _context.resolveBaseInherits(base_, c, index_, possibleQualifiers_, false);
                    if (base_ == null) {
                        base_ = "";
                    }
                    RootBlock super_ = classesBodies.getVal(base_);
                    if (super_ == null) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(type_);
                        undef_.setFileName(c.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        addError(undef_);
                    } else {
                        if (super_ instanceof UniqueRootedBlock) {
                            nbDirectSuperClass_++;
                        }
                        if (int_) {
                            if (!(super_ instanceof InterfaceBlock)) {
                                BadInheritedClass enum_;
                                enum_ = new BadInheritedClass();
                                enum_.setClassName(base_);
                                enum_.setFileName(c.getFile().getFileName());
                                enum_.setIndexFile(offset_);
                                addError(enum_);
                            } else {
                                names_.addEntry(base_,t.getKey());
                            }
                        } else if (super_.isFinalType()) {
                            BadInheritedClass enum_;
                            enum_ = new BadInheritedClass();
                            enum_.setClassName(base_);
                            enum_.setFileName(c.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            addError(enum_);
                        } else {
                            names_.addEntry(base_,t.getKey());
                            RootBlock par_ = c.getParentType();
                            while (true) {
                                if (par_.isStaticType()) {
                                    break;
                                }
                                inherit_.addSegment(new ClassEdge(d_), new ClassEdge(par_.getFullName()));
                                par_ = par_.getParentType();
                            }
                        }
                        inherit_.addSegment(new ClassEdge(d_), new ClassEdge(base_));
                    }
                }
                StringList dup_ = new StringList(names_.getKeys());
                int oldSize_ = dup_.size();
                dup_.removeDuplicates();
                int newSize_ = dup_.size();
                if (oldSize_ != newSize_) {
                    UnknownClassName undef_;
                    undef_ = new UnknownClassName();
                    undef_.setClassName(c.getFullName());
                    undef_.setFileName(c.getFile().getFileName());
                    undef_.setIndexFile(0);
                    addError(undef_);
                    continue;
                }
                dirSuperTypes_.put(d_, names_);
                if (nbDirectSuperClass_ > 1) {
                    BadInheritedClass enum_;
                    enum_ = new BadInheritedClass();
                    enum_.setClassName(EMPTY_STRING);
                    enum_.setFileName(c.getFile().getFileName());
                    enum_.setIndexFile(c.getIdRowCol());
                    addError(enum_);
                }
            }
            EqList<ClassEdge> cycle_ = inherit_.elementsCycle();
            if (!cycle_.isEmpty()) {
                for (ClassEdge c: cycle_) {
                    BadInheritedClass b_;
                    b_ = new BadInheritedClass();
                    String n_ = c.getId();
                    RootBlock type_ = classesBodies.getVal(n_);
                    b_.setClassName(n_);
                    b_.setFileName(type_.getFile().getFileName());
                    b_.setIndexFile(type_.getIdRowCol());
                    addError(b_);
                }
            }
            for (EntryCust<String, StringMap<Integer>> e: dirSuperTypes_.entryList()) {
                RootBlock r_ = _context.getClasses().getClassBody(e.getKey());
                StringMap<Integer> foundNames_ = e.getValue();
                for (EntryCust<String,Integer> f: foundNames_.entryList()) {
                    r_.getImportedDirectBaseSuperTypes().put(f.getValue(),f.getKey());
                }
                r_.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                for (String f: foundNames_.getKeys()) {
                    RootBlock s_ = getClassBody(f);
                    r_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                r_.getAllSuperTypes().add(objectClassName_);
                String dirSuper_ = objectClassName_;
                int nbDirectSuperClass_ = 0;
                for (String f: foundNames_.getKeys()) {
                    RootBlock s_ = getClassBody(f);
                    if (s_ instanceof UniqueRootedBlock) {
                        nbDirectSuperClass_++;
                        dirSuper_ = f;
                    }
                    r_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                }
                if (nbDirectSuperClass_ <= 1) {
                    prSupTypes(r_,dirSuper_,_context);
                }
                if (r_ instanceof UniqueRootedBlock) {
                    for (String t: r_.getAllSuperTypes()) {
                        GeneType g_ = _context.getClassBody(t);
                        if (g_ instanceof GeneInterface) {
                            ((UniqueRootedBlock)r_).getAllInterfaces().add(t);
                        }
                    }
                    ((UniqueRootedBlock)r_).getAllInterfaces().removeDuplicates();
                }
                r_.getAllSuperTypes().removeDuplicates();
            }
            for (RootBlock c: instClBodies_) {
                int rkLoc_ = c.getSelfAndParentTypes().size();
                if (rkLoc_ != i) {
                    continue;
                }
                possibleQualifiers_.add(c.getFullName());
            }
        }
    }

    private boolean isReserved(String _enumParamClassName, String _annotName, String _f) {
        return StringList.quickEq(_f, _enumParamClassName) || StringList.quickEq(_f, _annotName);
    }

    private void prSupTypes(RootBlock _r, String _dir, ContextEl _context) {
        String s_ = _dir;
        String objectClassName_ = _context.getStandards().getAliasObject();
        StringList line_ = new StringList();
        while (true) {
            if (StringList.contains(line_, s_)) {
                break;
            }
            line_.add(s_);
            _r.getAllSuperClasses().add(s_);
            RootBlock sup_ = getClassBody(s_);
            if (sup_ == null) {
                break;
            }
            String dirSup_ = objectClassName_;
            for (String d: sup_.getImportedDirectBaseSuperTypes().values()) {
                RootBlock sTwo_ = getClassBody(d);
                if (sTwo_ instanceof UniqueRootedBlock) {
                    dirSup_ = d;
                }
            }
            s_ = dirSup_;
        }
    }
    private void checkTemplatesDef(ContextEl _context, boolean _predefined,
            String _objectClassName) {
        LgNames stds_ = _context.getStandards();
        for (RootBlock s: getClassBodies(_predefined)) {
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
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                b_.setClassName(c);
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                addError(b_);
                continue;
            }
            mapping_.setMapping(cts_);
            if (mapping_.isCyclic(_objectClassName)) {
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                b_.setClassName(c);
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                addError(b_);
                continue;
            }
            for (TypeVar t: s.getParamTypesMapValues()) {
                boolean existNative_ = false;
                boolean existCustom_ = false;
                StringList upper_ = Mapping.getAllUpperBounds(cts_, t.getName(),_objectClassName);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
                    if (b.startsWith("[")) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(c);
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        addError(un_);
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
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(c);
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    addError(un_);
                    okLoc_ = false;
                    ok_ = false;
                }
                StringMap<StringList> baseParams_ = getBaseParams(upper_);
                for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                    if (e.getValue().size() > 1) {
                        DuplicateGenericSuperTypes duplicate_;
                        duplicate_ = new DuplicateGenericSuperTypes();
                        duplicate_.setFileName(s.getFile().getFileName());
                        duplicate_.setIndexFile(s.getIdRowCol());
                        duplicate_.setGenericSuperTypes(e.getValue());
                        addError(duplicate_);
                    }
                }
                StringList all_ = new StringList();
                for (String u: upper_) {
                    String idUpper_ = Templates.getIdFromAllTypes(u);
                    RootBlock r_ = getClassBody(idUpper_);
                    if (r_ == null) {
                        continue;
                    }
                    StringList genericSuperTypes_ = r_.getAllGenericSuperTypes(_context);
                    all_.add(u);
                    for (String v: genericSuperTypes_) {
                        String formattedType_ = Templates.format(u, v, _context);
                        if (formattedType_ == null) {
                            continue;
                        }
                        all_.add(formattedType_);
                    }
                }
                baseParams_ = getBaseParams(all_);
                for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                    if (e.getValue().size() > 1) {
                        DuplicateGenericSuperTypes duplicate_;
                        duplicate_ = new DuplicateGenericSuperTypes();
                        duplicate_.setFileName(s.getFile().getFileName());
                        duplicate_.setIndexFile(s.getIdRowCol());
                        duplicate_.setGenericSuperTypes(e.getValue());
                        addError(duplicate_);
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
                            if (class_.isFinalType()) {
                                nbFinal_++;
                            }
                            if (class_.isAbstractType()) {
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
                        //error
                        BadInheritedClass inh_;
                        inh_ = new BadInheritedClass();
                        inh_.setFileName(s.getFile().getFileName());
                        inh_.setIndexFile(s.getIdRowCol());
                        inh_.setClassName(c);
                        addError(inh_);
                        ok_ = false;
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
                    if (!Templates.isCorrectTemplateAll(b, map_, _context, true)) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(b);
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        addError(un_);
                    }
                }
            }
            for (String t: s.getDirectGenericSuperTypes(_context)) {
                if (!Templates.isCorrectTemplateAll(t, map_, _context, true)) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(t);
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    addError(un_);
                }
            }
        }
    }
    public CustList<OperatorBlock> getOperators() {
        return operators;
    }
    public void validateSingleParameterizedClasses(ContextEl _context, boolean _predefined) {
        for (RootBlock i: getClassBodies(_predefined)) {
            StringList genericSuperTypes_ = i.getAllGenericSuperTypes(_context);
            StringMap<StringList> baseParams_ = getBaseParams(genericSuperTypes_);
            for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                if (e.getValue().size() > 1) {
                    DuplicateGenericSuperTypes duplicate_;
                    duplicate_ = new DuplicateGenericSuperTypes();
                    duplicate_.setFileName(i.getFile().getFileName());
                    duplicate_.setIndexFile(i.getIdRowCol());
                    duplicate_.setGenericSuperTypes(e.getValue());
                    addError(duplicate_);
                }
            }
            genericSuperTypes_.removeDuplicates();
            i.getAllGenericSuperTypes().addAllElts(genericSuperTypes_);
        }
    }
    public static StringMap<StringList> getBaseParams(StringList _genericSuperTypes) {
        StringMap<StringList> baseParams_ = new StringMap<StringList>();
        for (String t: _genericSuperTypes) {
            String key_ = Templates.getIdFromAllTypes(t);
            if (baseParams_.contains(key_)) {
                baseParams_.getVal(key_).add(t);
                baseParams_.getVal(key_).removeDuplicates();
            } else {
                baseParams_.put(key_, new StringList(t));
            }
        }
        return baseParams_;
    }

    public void validateIds(ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        for (RootBlock c: getClassBodies(_predefined)) {
            _context.setGlobalClass(c.getGenericString());
            _context.getAnalyzing().setImporting(c);
            c.validateIds(_context);
        }
        if (_predefined) {
            return;
        }
        EqList<MethodId> idMethods_ = new EqList<MethodId>();
        _context.setGlobalClass("");
        for (OperatorBlock o: getOperators()) {
            String name_ = o.getName();
            _context.getAnalyzing().setImporting(o);
            o.buildImportedTypes(_context);
            if (!isOper(name_)) {
                BadMethodName badMeth_ = new BadMethodName();
                badMeth_.setFileName(_context.getCurrentFileName());
                badMeth_.setIndexFile(o.getNameOffset());
                badMeth_.setName(name_);
                _context.getClasses().addError(badMeth_);
            }
            MethodId id_ = o.getId();
            for (MethodId m: idMethods_) {
                if (m.eq(id_)) {
                    DuplicateMethod duplicate_;
                    duplicate_ = new DuplicateMethod();
                    duplicate_.setIndexFile(o.getOffset().getOffsetTrim());
                    duplicate_.setFileName(_context.getCurrentFileName());
                    duplicate_.setId(id_.getSignature(_context));
                    _context.getClasses().addError(duplicate_);
                }
            }
            idMethods_.add(id_);
            StringList l_ = o.getParametersNames();
            StringList seen_ = new StringList();
            for (String v: l_) {
                if (!_context.isValidToken(v)) {
                    BadParamName b_;
                    b_ = new BadParamName();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    b_.setParamName(v);
                    _context.getClasses().addError(b_);
                }
                if (StringList.contains(seen_, v)){
                    DuplicateParamName b_;
                    b_ = new DuplicateParamName();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(o.getOffset().getOffsetTrim());
                    b_.setParamName(v);
                    _context.getClasses().addError(b_);
                } else {
                    seen_.add(v);
                }
            }
        }
    }
    private static boolean isOper(String _op) {
        if(StringList.quickEq(_op, "+")) {
            return true;
        }
        if(StringList.quickEq(_op, "-")) {
            return true;
        }
        if(StringList.quickEq(_op, "*")) {
            return true;
        }
        if(StringList.quickEq(_op, "/")) {
            return true;
        }
        if(StringList.quickEq(_op, "%")) {
            return true;
        }
        if(StringList.quickEq(_op, "==")) {
            return true;
        }
        if(StringList.quickEq(_op, "!=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<=")) {
            return true;
        }
        if(StringList.quickEq(_op, ">")) {
            return true;
        }
        if(StringList.quickEq(_op, ">=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<")) {
            return true;
        }
        if(StringList.quickEq(_op, "&")) {
            return true;
        }
        if(StringList.quickEq(_op, "|")) {
            return true;
        }
        if(StringList.quickEq(_op, "^")) {
            return true;
        }
        if(StringList.quickEq(_op, "~")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>>")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>>>")) {
            return true;
        }
        if(StringList.quickEq(_op, "++")) {
            return true;
        }
        return StringList.quickEq(_op, "--");
    }
    public void validateOverridingInherit(ContextEl _context, boolean _predefined) {
        _context.setAnalyzing(new AnalyzedPageEl());
        for (RootBlock c: getClassBodies(_predefined)) {
            c.setupBasicOverrides(_context);
        }
        for (RootBlock c: getClassBodies(_predefined)) {
            if (c instanceof AnnotationBlock) {
                continue;
            }
            c.checkCompatibility(_context);
            c.checkImplements(_context);
        }
        for (RootBlock c: getClassBodies(_predefined)) {
            if (c instanceof AnnotationBlock) {
                continue;
            }
            c.checkCompatibilityBounds(_context);
        }
    }

    public static boolean isHiddenField(String _className, String _accessedClass, String _name, Analyzable _context) {
        String baseClass_ = Templates.getIdFromAllTypes(_accessedClass);
        GeneType access_ = _context.getClassBody(baseClass_);
        if (access_ instanceof RootBlock) {
            CustList<Block> bl_ = getDirectChildren((Block) access_);
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    if (StringList.contains(((InfoBlock) b).getFieldName(), _name)) {
                        return !canAccess(_className, (InfoBlock) b, _context);
                    }
                }
            }
            return true;
        }
        return false;
    }
    public static CustList<RootBlock> accessedClassMembers(boolean _inherits,boolean _protectedInc,String _className, String _glClass,RootBlock _clOwner, Analyzable _context) {
        String idRoot_ = Templates.getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(idRoot_);
        String pkgRoot_ = root_.getPackageName();
        String pkgOwner_ = _clOwner.getPackageName();
        String ownerName_ = _clOwner.getFullName();
        CustList<RootBlock> inners_ = new CustList<RootBlock>();
        String outOwner_ = _clOwner.getOuter().getFullName();
        String outRoot_ = root_.getOuter().getFullName();

        String idGl_ = Templates.getIdFromAllTypes(_glClass);
        RootBlock rGl_ = _context.getClasses().getClassBody(idGl_);
        for (Block b: Classes.getDirectChildren(_clOwner)) {
            if (!(b instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) b;
            if (r_.getAccess() == AccessEnum.PUBLIC) {
                inners_.add(r_);
                continue;
            }
            if (rGl_ == null) {
                continue;
            }
            String outGl_ = rGl_.getOuter().getFullName();
            String pkgGl_ = rGl_.getPackageName();
            if (r_.getAccess() == AccessEnum.PROTECTED) {
                boolean okRoot_ = canUseInner(_protectedInc, _context, pkgOwner_, ownerName_, idRoot_, pkgRoot_);
                boolean okGl_ = canUseInner(_protectedInc, _context, pkgOwner_, ownerName_, idGl_, pkgGl_);
                if (_inherits && _protectedInc) {
                    okGl_ = true;
                }
                if (okGl_ && okRoot_) {
                    inners_.add(r_);
                    continue;
                }
            }
            if (r_.getAccess() == AccessEnum.PACKAGE) {
                if (StringList.quickEq(pkgOwner_, pkgRoot_)) {
                    if (StringList.quickEq(pkgOwner_, pkgGl_)) {
                        inners_.add(r_);
                        continue;
                    }
                }
            }
            if (r_.getAccess() == AccessEnum.PRIVATE) {
                if (StringList.quickEq(outRoot_, outOwner_)) {
                    if (StringList.quickEq(outGl_, outOwner_)) {
                        inners_.add(r_);
                    }
                }
            }
        }
        return inners_;
    }

    private static boolean canUseInner(boolean _protectedInc, Analyzable _context, String _pkgOwner, String _ownerName, String _idGl, String _pkgGl) {
        boolean okGl_ = false;
        if (_protectedInc) {
            if (PrimitiveTypeUtil.canBeUseAsArgument(_ownerName, _idGl, _context)) {
                okGl_ = true;
            }
        }
        if (StringList.quickEq(_pkgOwner, _pkgGl)){
            okGl_ = true;
        }
        return okGl_;
    }

    public static boolean canAccessClass(String _className, String _accessedClass, Analyzable _context) {
        String baseClass_ = Templates.getIdFromAllTypes(_accessedClass);
        GeneType access_ = _context.getClassBody(baseClass_);
        return canAccess(_className, access_, _context);
    }

    public static boolean canAccess(String _className, AccessibleBlock _block, Analyzable _context) {
        if (_block.getAccess() == AccessEnum.PUBLIC) {
            return true;
        }
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(baseClass_);
        if (root_ == null) {
            return false;
        }
        GeneType belong_ = _block.belong();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (PrimitiveTypeUtil.canBeUseAsArgument(belong_.getFullName(), baseClass_, _context)) {
                return true;
            }
            return StringList.quickEq(belong_.getPackageName(), root_.getPackageName());
        }
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            return StringList.quickEq(belong_.getPackageName(), root_.getPackageName());
        }
        GeneType outBelong_ = belong_.getOuter();
        GeneType outRoot_ = root_.getOuter();
        return StringList.quickEq(outBelong_.getFullName(), outRoot_.getFullName());
    }

    //validate el and its possible returned type
    public void validateEl(ContextEl _context, boolean _predefined) {
        initStaticFields(_context, _predefined);
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        _context.setAnalyzing(page_);
        for (RootBlock c: getClassBodies(_predefined)) {
            page_.setImporting(c);
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
                    if (staticFields.getVal(fullName_).getVal(f) == null) {
                        if (!c.isStaticType()) {
                            //ERROR
                            ClassField id_ = new ClassField(fullName_, f);
                            UnassignedFinalField un_ = new UnassignedFinalField(id_);
                            un_.setFileName(c.getFile().getFileName());
                            un_.setIndexFile(c.getOffset().getOffsetTrim());
                            _context.getClasses().addError(un_);
                        }
                    }
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
                if (!a.getValue().isAssignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(id_);
                    un_.setFileName(c.getFile().getFileName());
                    un_.setIndexFile(c.getOffset().getOffsetTrim());
                    _context.getClasses().addError(un_);
                }
            }
        }
        _context.setAssignedStaticFields(true);
        for (RootBlock c: getClassBodies(_predefined)) {
            page_.setImporting(c);
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
            b_.clear();
            boolean hasCtor_ = false;
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    hasCtor_ = true;
                    break;
                }
            }
            if (!hasCtor_) {
                for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                    String fieldName_ = a.getKey();
                    ClassField key_ = new ClassField(fullName_, fieldName_);
                    FieldInfo finfo_ = _context.getFieldInfo(key_);
                    if (!finfo_.isFinalField()) {
                        continue;
                    }
                    if (a.getValue().isAssignedAfter()) {
                        continue;
                    }
                    //error
                    for (Block b: bl_) {
                        if (b instanceof InfoBlock) {
                            if (StringList.contains(((InfoBlock) b).getFieldName(), fieldName_)) {
                                UnassignedFinalField un_ = new UnassignedFinalField(key_);
                                un_.setFileName(c.getFile().getFileName());
                                un_.setIndexFile(((InfoBlock) b).getFieldNameOffset());
                                _context.getClasses().addError(un_);
                            }
                        }
                    }
                }
            }
            b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
            StringList filteredCtor_ = new StringList();
            if (c instanceof UniqueRootedBlock) {
                Classes classes_ = _context.getClasses();
                UniqueRootedBlock un_ = (UniqueRootedBlock)c;
                StringList all_ = un_.getAllInterfaces();
                StringList allCopy_ = new StringList(all_);
                StringList.removeAllElements(allCopy_, _context.getStandards().getPredefinedInterfacesInitOrder());
                String superClass_ = un_.getImportedDirectGenericSuperClass();
                String superClassId_ = Templates.getIdFromAllTypes(superClass_);
                RootBlock superType_ = classes_.getClassBody(superClassId_);
                if (superType_ instanceof UniqueRootedBlock) {
                    StringList.removeAllElements(allCopy_, ((UniqueRootedBlock)superType_).getAllInterfaces());
                }
                for (String i: allCopy_) {
                    RootBlock int_ = classes_.getClassBody(i);
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
                            if (!a_.isStaticContext()) {
                                filteredCtor_.add(i);
                            }
                        }
                    }
                }
            }
            _context.getNeedInterfaces().clear();
            _context.getNeedInterfaces().addAllElts(filteredCtor_);
            if (!hasCtor_ && !filteredCtor_.isEmpty()) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(fullName_);
                undef_.setFileName(c.getFile().getFileName());
                undef_.setIndexFile(0);
                _context.getClasses().addError(undef_);
            }
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    page_.setGlobalClass(c.getGenericString());
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    int len_ = params_.size();
                    if (!method_.isVarargs()) {
                        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                    } else {
                        for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                        String p_ = params_.last();
                        String c_ = types_.last();
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(StringList.concat(c_,VARARG));
                        page_.getParameters().put(p_, lv_);
                    }
                    method_.buildFctInstructions(_context);
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
        for (RootBlock c: getClassBodies(_predefined)) {
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
                    int len_ = params_.size();
                    if (!method_.isVarargs()) {
                        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                    } else {
                        for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                        String p_ = params_.last();
                        String c_ = types_.last();
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(StringList.concat(c_,VARARG));
                        page_.getParameters().put(p_, lv_);
                    }
                    method_.buildFctInstructions(_context);
                    page_.getParameters().clear();
                    page_.clearAllLocalVars();
                } else {
                    page_.setGlobalClass(c.getGenericString());
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    int len_ = params_.size();
                    if (!method_.isVarargs()) {
                        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                    } else {
                        for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                            String p_ = params_.get(i);
                            String c_ = types_.get(i);
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                        String p_ = params_.last();
                        String c_ = types_.last();
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(StringList.concat(c_,VARARG));
                        page_.getParameters().put(p_, lv_);
                    }
                    if (method_.getKind() == MethodKind.SET_INDEX) {
                        String p_ = _context.getKeyWords().getKeyWordValue();
                        CustList<OverridableBlock> getIndexers_ = new CustList<OverridableBlock>();
                        for (Block d: Classes.getDirectChildren(c)) {
                            if (!(d instanceof OverridableBlock)) {
                                continue;
                            }
                            OverridableBlock i_ = (OverridableBlock) d;
                            if (i_.getKind() != MethodKind.GET_INDEX) {
                                continue;
                            }
                            if (!i_.getId().eqPartial(method_.getId())) {
                                continue;
                            }
                            getIndexers_.add(i_);
                        }
                        if (getIndexers_.size() == 1) {
                            OverridableBlock matching_ = getIndexers_.first();
                            String c_ = matching_.getImportedReturnType();
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setClassName(c_);
                            page_.getParameters().put(p_, lv_);
                        }
                    }
                    method_.buildFctInstructions(_context);
                    page_.getParameters().clear();
                    page_.clearAllLocalVars();
                }
            }
        }
        _context.setGlobalClass("");
        if (!_predefined) {
            _context.getCoverage().putCalls(_context,"");
            for (OperatorBlock o : getOperators()) {
                page_.setImporting(o);
                _context.getCoverage().putCalls(_context,"",o);
                if (!o.isVarargs()) {
                    StringList params_ = o.getParametersNames();
                    StringList types_ = o.getImportedParametersTypes();
                    int len_ = params_.size();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String p_ = params_.get(i);
                        String c_ = types_.get(i);
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(c_);
                        page_.getParameters().put(p_, lv_);
                    }
                } else {
                    StringList params_ = o.getParametersNames();
                    StringList types_ = o.getImportedParametersTypes();
                    int len_ = params_.size();
                    for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                        String p_ = params_.get(i);
                        String c_ = types_.get(i);
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(c_);
                        page_.getParameters().put(p_, lv_);
                    }
                    String p_ = params_.last();
                    String c_ = types_.last();
                    LocalVariable lv_ = new LocalVariable();
                    lv_.setClassName(StringList.concat(c_,VARARG));
                    page_.getParameters().put(p_, lv_);
                }
                o.buildFctInstructions(_context);
                page_.getParameters().clear();
                page_.clearAllLocalVars();
            }
        }
        _context.setAnnotAnalysis(true);
        for (RootBlock c: getClassBodies(_predefined)) {
            page_.setImporting(c);
            _context.setGlobalClass(c.getGenericString());
            for (Block b:getSortedDescNodes(c)) {
                _context.getAnalyzing().setCurrentBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    ((AnnotationMethodBlock)b).buildExpressionLanguage(_context);
                }
                if (b instanceof AnnotableBlock) {
                    ((AnnotableBlock)b).buildAnnotations(_context);
                }
            }
        }
        if (!_predefined) {
            _context.setGlobalClass("");
            for (OperatorBlock o : getOperators()) {
                page_.setImporting(o);
                o.buildAnnotations(_context);
            }
        }
        //init annotations here
        for (RootBlock c: getClassBodies(_predefined)) {
            c.validateConstructors(_context);
        }
    }
    public void initStaticFields(ContextEl _context, boolean _predefined) {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        _context.setAnalyzing(page_);
        page_.setGearConst(true);

        for (RootBlock c: getClassBodies(_predefined)) {
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

        for (RootBlock c: getClassBodies(_predefined)) {
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
        EqList<ClassField> success_ = new EqList<ClassField>();
        EqList<ClassField> cstFields_ = new EqList<ClassField>();
        for (RootBlock c: getClassBodies(_predefined)) {
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
                f_.buildExpressionLanguage(_context);
                String cl_ = c.getFullName();
                for (String f: f_.getFieldName()) {
                    cstFields_.add(new ClassField(cl_, f));
                    success_.add(cstFields_.last());
                }
            }
        }
        EqList<ClassField> filteredCstFields_ = new EqList<ClassField>();
        SortedGraph<SortedClassField> gr_ = new SortedGraph<SortedClassField>();
        EqList<SortedClassField> absDeps_ = new EqList<SortedClassField>();
        for (ClassField c: cstFields_) {
            RootBlock r_ = classesBodies.getVal(c.getClassName());
            CustList<Block> bl_ = getDirectChildren(r_);
            String fieldName_ = c.getFieldName();
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                for (String f: f_.getFieldName()) {
                    if (StringList.quickEq(f, fieldName_)) {
                        filteredCstFields_.add(new ClassField(c.getClassName(), f));
                        EqList<ClassField> deps_ = f_.getStaticConstantDependencies(_context,f);
                        if (deps_.isEmpty()) {
                            absDeps_.add(new SortedClassField(c));
                        }
                        for (ClassField d: deps_) {
                            gr_.addSegment(new SortedClassField(c), new SortedClassField(d));
                        }
                    }
                }
            }
        }
        while (true) {
            EqList<SortedClassField> cycle_ = gr_.elementsCycle();
            if (cycle_.isEmpty()) {
                break;
            }
            EqList<SortedClassField> cpy_ = gr_.getElementsListCopy();
            Graph<SortedClassField> rev_ = gr_.getReverse();
            EqList<SortedClassField> r_ = new EqList<SortedClassField>();
            for (SortedClassField e: cycle_) {
                r_.addAllElts(rev_.getTreeFrom(e));
            }
            cpy_.removeAllElements(r_);
            gr_ = new SortedGraph<SortedClassField>();
            rev_ = rev_.getReverse();
            for (SortedClassField e: cpy_) {
                for (SortedClassField d: rev_.getChildren(e)) {
                    gr_.addSegment(e, d);
                }
            }
        }
        EqList<SortedClassField> sort_;
        sort_ = new EqList<SortedClassField>();
        sort_.addAllElts(absDeps_);
        for (SortedClassField e: gr_.process()) {
            if (!sort_.containsObj(e)) {
                sort_.add(e);
            }
        }
        for (SortedClassField e: sort_) {
            ClassField c_ = e.getClassField();
            String fieldName_ = c_.getFieldName();
            RootBlock r_ = classesBodies.getVal(c_.getClassName());
            CustList<Block> bl_ = getDirectChildren(r_);
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (StringList.contains(f_.getFieldName(), fieldName_)) {
                    ElUtil.tryCalculate(f_, _context, fieldName_);
                }
            }
        }
    }
    public StringList getPackages(boolean _predefined) {
        StringList pkgs_ = new StringList();
        for (RootBlock r: getClassBodies(_predefined)) {
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

    public CustList<RootBlock> getClassBodies(boolean _predefined) {
        CustList<RootBlock> t_ = new CustList<RootBlock>();
        for (RootBlock r: classesBodies.values()) {
            if (r.getFile().isPredefined() != _predefined) {
                continue;
            }
            t_.add(r);
        }
        return t_;
    }

    public void addErrorIfNoMatch(String _generic, String _base, Block _currentBlock, int _location) {
        String id_ = Templates.getIdFromAllTypes(_generic);
        if (!StringList.quickEq(id_,_base)) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_generic);
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            addError(un_);
        }
    }
    public RootBlock getClassBody(String _className) {
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey(), _className)) {
                continue;
            }
            return c.getValue();
        }
        return null;
    }

    public static CustList<OverridableBlock> getMethodBodiesById(ContextEl _context,String _genericClassName, MethodId _id) {
        CustList<OverridableBlock> methods_ = new CustList<OverridableBlock>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(base_);
        for (GeneMethod m: Classes.getMethodBlocks(r_)) {
            if (m.getId().eq(_id)) {
                methods_.add((OverridableBlock)m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<OperatorBlock> getOperatorsBodiesById(ContextEl _context,MethodId _id) {
        CustList<OperatorBlock> methods_ = new CustList<OperatorBlock>();
        Classes classes_ = _context.getClasses();
        for (GeneMethod m: classes_.getOperators()) {
            if (m.getId().eq(_id)) {
                methods_.add((OperatorBlock)m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<GeneConstructor> getConstructorBodiesById(Analyzable _context,String _genericClassName, ConstructorId _id) {
        return getConstructorBodiesById(_context, _genericClassName, _id.getParametersTypes(), _id.isVararg());
    }
    public static CustList<GeneConstructor> getConstructorBodies(Analyzable _context,String _genericClassName) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        for (EntryCust<String, StandardType> c: _context.getStandards().getStandards().entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            for (StandardConstructor s: c.getValue().getConstructors()) {
                methods_.add(s);
            }
        }
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
                methods_.add(method_);
            }
        }
        return methods_;
    }
    private static CustList<GeneConstructor> getConstructorBodiesById(Analyzable _context,String _genericClassName, StringList _parametersTypes, boolean _vararg) {
        CustList<GeneConstructor> methods_ = new CustList<GeneConstructor>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        int nbParams_ = _parametersTypes.size();
        Classes classes_ = _context.getClasses();
        for (EntryCust<String, StandardType> c: _context.getStandards().getStandards().entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            for (StandardConstructor s: c.getValue().getConstructors()) {
                StringList list_ = s.getId().getParametersTypes();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!s.isVarargs()) {
                        continue;
                    }
                } else {
                    if (s.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = list_.get(i);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
                methods_.add(s);
            }
        }
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
                StringList list_ = method_.getId().getParametersTypes();
                if (list_.size() != nbParams_) {
                    continue;
                }
                if (nbParams_ > 0 && _vararg) {
                    if (!method_.isVarargs()) {
                        continue;
                    }
                } else {
                    if (method_.isVarargs()) {
                        continue;
                    }
                }
                boolean all_ = true;
                for (int i = CustList.FIRST_INDEX; i < nbParams_; i++) {
                    String type_ = list_.get(i);
                    if (!StringList.quickEq(type_, _parametersTypes.get(i))) {
                        all_ = false;
                        break;
                    }
                }
                if (!all_) {
                    continue;
                }
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

    public void preInitializeStaticFields(String _className, ContextEl _context) {
        String base_ = Templates.getIdFromAllTypes(_className);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof FieldBlock) {
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    String c_ = method_.getImportedClassName();
                    for (String f: method_.getFieldName()) {
                        for (EntryCust<String, Struct> e: staticFields.getVal(base_).entryList()) {
                            if (e.getValue() != null) {
                                continue;
                            }
                            if (StringList.quickEq(e.getKey(), f)) {
                                e.setValue(PrimitiveTypeUtil.defaultClass(c_, _context));
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InnerTypeOrElement) {
                    InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                    String m_ = method_.getUniqueFieldName();
                    for (EntryCust<String, Struct> f: staticFields.getVal(base_).entryList()) {
                        if (StringList.quickEq(f.getKey(), m_)) {
                            f.setValue(NullStruct.NULL_VALUE);
                        }
                    }
                }
            }
        }
    }
    public void initializeStaticField(ClassField _clField, Struct _str) {
        staticFields.getVal(_clField.getClassName()).set(_clField.getFieldName(), _str);
    }
    public int staticFieldCount() {
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
    
    public Struct getStaticField(ClassField _clField, ContextEl _context) {
        Struct strInit_ = getStaticField(_clField);
        if (strInit_ != null) {
            return strInit_;
        }
        String stn_ = _clField.getClassName();
        String sfn_ = _clField.getFieldName();
        String base_ = Templates.getIdFromAllTypes(stn_);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof FieldBlock) {
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    String c_ = method_.getImportedClassName();
                    if (StringList.contains(method_.getFieldName(), sfn_)) {
                        return PrimitiveTypeUtil.defaultClass(c_, _context);
                    }
                }
            }
        }
        return NullStruct.NULL_VALUE;
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
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        for (Block b: bl_) {
            if (b instanceof RootBlock) {
                inners_.add(((RootBlock) b).getFullName());
            }
            if (b instanceof InfoBlock) {
                InfoBlock method_ = (InfoBlock) b;
                String ret_ = method_.getImportedClassName();
                boolean staticElement_ = method_.isStaticField();
                boolean finalElement_ = method_.isFinalField();
                AccessEnum acc_ = method_.getAccess();
                for (String f: method_.getFieldName()) {
                    FieldMetaInfo met_ = new FieldMetaInfo(_name, f, ret_, staticElement_, finalElement_, acc_);
                    infosFields_.put(f, met_);
                }
            }
            if (b instanceof OverridableBlock) {
                OverridableBlock method_ = (OverridableBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                AccessEnum acc_ = method_.getAccess();
                MethodId fid_;
                String formCl_ = _type.getFullName();
                if (Templates.correctNbParameters(_name, _context)) {
                    fid_ = id_.reflectFormat(_name, _context);
                    formCl_ = _name;
                } else {
                    fid_ = id_;
                }
                MethodMetaInfo met_ = new MethodMetaInfo(acc_,_type.getFullName(), id_, method_.getModifier(), ret_, fid_, formCl_);
                infos_.put(id_, met_);
            }
            if (b instanceof AnnotationMethodBlock) {
                AnnotationMethodBlock method_ = (AnnotationMethodBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                AccessEnum acc_ = method_.getAccess();
                MethodId fid_;
                String formCl_ = _type.getFullName();
                fid_ = id_;
                MethodMetaInfo met_ = new MethodMetaInfo(acc_,_type.getFullName(), id_, method_.getModifier(), ret_, fid_, formCl_);
                infos_.put(id_, met_);
            }
            if (b instanceof ConstructorBlock) {
                existCtor_ = true;
                ConstructorBlock method_ = (ConstructorBlock) b;
                ConstructorId id_ = method_.getGenericId();
                AccessEnum acc_ = method_.getAccess();
                ConstructorId fid_;
                String ret_ = method_.getImportedReturnType();
                String formCl_ = method_.getDeclaringType();
                if (Templates.correctNbParameters(_name, _context)) {
                    fid_ = id_.reflectFormat(_name, _context);
                } else {
                    fid_ = id_;
                }
                ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, fid_, formCl_);
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
            infosConst_.put(id_, met_);
        }
        if (_type instanceof EnumBlock) {
            String valueOf_ = _context.getStandards().getAliasEnumPredValueOf();
            String values_ = _context.getStandards().getAliasEnumValues();
            String string_ = _context.getStandards().getAliasString();
            MethodId id_ = new MethodId(true, valueOf_, new StringList(string_));
            String ret_ = _type.getWildCardString();
            MethodId fid_;
            fid_ = id_;
            String decl_ = _type.getFullName();
            MethodMetaInfo met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, fid_, decl_);
            infos_.put(id_, met_);
            id_ = new MethodId(true, values_, new StringList());
            ret_ = PrimitiveTypeUtil.getPrettyArrayType(ret_);
            fid_ = id_;
            met_ = new MethodMetaInfo(AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, fid_, decl_);
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
            return new ClassMetaInfo(_name, ((InterfaceBlock)_type).getImportedDirectSuperInterfaces(), format_, inners_,
                    infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,st_,acc_);
        }
        if (_type instanceof AnnotationBlock) {
            return new ClassMetaInfo(_name, new StringList(), format_, inners_,
                    infosFields_,infos_, infosConst_, ClassCategory.ANNOTATION,st_,acc_);
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (_type instanceof EnumBlock) {
            cat_ = ClassCategory.ENUM;
        }
        boolean abs_ = _type.isAbstractType();
        boolean final_ = _type.isFinalType();
        String superClass_ = ((UniqueRootedBlock) _type).getImportedDirectGenericSuperClass();
        StringList superInterfaces_ = ((UniqueRootedBlock) _type).getImportedDirectGenericSuperInterfaces();
        return new ClassMetaInfo(_name, superClass_, superInterfaces_, format_, inners_,
                infosFields_,infos_, infosConst_, cat_, abs_, st_, final_,acc_);
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
