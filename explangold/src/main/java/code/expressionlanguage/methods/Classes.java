package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.FileResolver;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.util.BadClassName;
import code.expressionlanguage.methods.util.BadFileName;
import code.expressionlanguage.methods.util.DeadCodeMethod;
import code.expressionlanguage.methods.util.DuplicateType;
import code.expressionlanguage.methods.util.ErrorList;
import code.expressionlanguage.methods.util.FoundErrorInterpret;
import code.expressionlanguage.methods.util.FoundWarningInterpret;
import code.expressionlanguage.methods.util.MissingReturnMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.WarningList;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.sml.ElementOffsetsNext;
import code.sml.Node;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public class Classes {
    public static final String EXT = "cdm";
    public static final String TEMP_PREFIX = "tmp";
    private static final char SEP_FILE = '/';
    private static final char DOT = '.';
    private static final String LOC_VAR = ".";

    private static final String PARS = "()";
    private static final String EMPTY_STRING = "";
    private final StringMap<RootBlock> classesBodies;
    private final StringMap<FileBlock> filesBodies;

    private final StringMap<StringMap<Struct>> staticFields;

    private final ErrorList errorsDet;
    private final WarningList warningsDet;
    private DefaultLockingClass locks;
    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private String iteratorVarCust;
    private String hasNextVarCust;
    private String nextVarCust;
    private CustList<OperationNode> expsIterator;
    private CustList<OperationNode> expsHasNext;
    private CustList<OperationNode> expsNext;
    private CustList<OperationNode> expsIteratorCust;
    private CustList<OperationNode> expsHasNextCust;
    private CustList<OperationNode> expsNextCust;
    private CustList<OperatorBlock> operators;
    public Classes(){
        classesBodies = new StringMap<RootBlock>();
        filesBodies = new StringMap<FileBlock>();
        errorsDet = new ErrorList();
        warningsDet = new WarningList();
        staticFields = new StringMap<StringMap<Struct>>();
        operators = new CustList<OperatorBlock>();
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
                    String m_ = method_.getFieldName();
                    String c_ = method_.getImportedClassName();
                    for (EntryCust<String, Struct> f: staticFields.getVal(base_).entryList()) {
                        if (f.getValue() != null) {
                            continue;
                        }
                        if (StringList.quickEq(f.getKey(), m_)) {
                            f.setValue(StdStruct.defaultClass(c_, _context));
                            break;
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
                if (b instanceof ElementBlock) {
                    ElementBlock method_ = (ElementBlock) b;
                    String m_ = method_.getFieldName();
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
    public CustList<OperatorBlock> getOperators() {
		return operators;
	}
    public ClassMetaInfo getClassMetaInfo(String _name, ContextEl _context) {
        String base_ = Templates.getIdFromAllTypes(_name);
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
            infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
            StringMap<FieldMetaInfo> infosFields_;
            infosFields_ = new StringMap<FieldMetaInfo>();
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
            infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
            String k_ = c.getKey();
            if (!StringList.quickEq(k_, base_)) {
                continue;
            }
            RootBlock clblock_ = c.getValue();
            CustList<Block> bl_ = getDirectChildren(clblock_);
            StringList inners_ = new StringList();
            for (Block b: bl_) {
                if (b instanceof RootBlock) {
                    inners_.add(((RootBlock) b).getFullName());
                }
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    String m_ = method_.getFieldName();
                    String ret_ = method_.getImportedClassName();
                    boolean enumElement_ = b instanceof ElementBlock;
                    boolean staticElement_ = method_.isStaticField();
                    boolean finalElement_ = method_.isFinalField();
                    AccessEnum acc_ = method_.getAccess();
                    FieldMetaInfo met_ = new FieldMetaInfo(_name, m_, ret_, staticElement_, finalElement_, enumElement_, acc_);
                    infosFields_.put(m_, met_);
                }
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    MethodId id_ = method_.getId();
                    String ret_ = method_.getImportedReturnType();
                    AccessEnum acc_ = method_.getAccess();
                    String formatRet_;
                    MethodId fid_;
                    String formCl_ = method_.getDeclaringType();
                    if (Templates.correctNbParameters(_name, _context)) {
                        formatRet_ = Templates.format(_name, ret_, _context);
                        fid_ = id_.format(_name, _context);
                        formCl_ = Templates.format(_name,formCl_,_context);
                    } else {
                        formatRet_ = ret_;
                        fid_ = id_;
                    }
                    MethodMetaInfo met_ = new MethodMetaInfo(acc_,method_.getDeclaringType(), id_, method_.getModifier(), ret_, fid_, formatRet_,formCl_);
                    infos_.put(id_, met_);
                }
                if (b instanceof AnnotationMethodBlock) {
                    AnnotationMethodBlock method_ = (AnnotationMethodBlock) b;
                    MethodId id_ = method_.getId();
                    String ret_ = method_.getImportedReturnType();
                    AccessEnum acc_ = method_.getAccess();
                    String formatRet_;
                    MethodId fid_;
                    String formCl_ = method_.getDeclaringType();
                    formatRet_ = ret_;
                    fid_ = id_;
                    MethodMetaInfo met_ = new MethodMetaInfo(acc_,method_.getDeclaringType(), id_, method_.getModifier(), ret_, fid_, formatRet_,formCl_);
                    infos_.put(id_, met_);
                }
                if (b instanceof ConstructorBlock) {
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    ConstructorId id_ = method_.getGenericId();
                    AccessEnum acc_ = method_.getAccess();
                    String formatRet_;
                    ConstructorId fid_;
                    String ret_ = method_.getImportedReturnType();
                    String formCl_ = method_.getDeclaringType();
                    if (Templates.correctNbParameters(_name, _context)) {
                        formatRet_ = Templates.format(_name, ret_, _context);
                        fid_ = id_.format(_name, _context);
                        formCl_ = Templates.format(_name,formCl_,_context);
                    } else {
                        formatRet_ = ret_;
                        fid_ = id_;
                    }
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, fid_, formatRet_,formCl_);
                    infosConst_.put(id_, met_);
                }
            }
            RootBlock par_ = clblock_.getParentType();
            String format_;
            if (par_ != null) {
                String gene_ = par_.getGenericString();
                if (Templates.correctNbParameters(_name, _context)) {
                    format_ = Templates.format(_name, gene_, _context);
                } else {
                    format_ = par_.getFullName();
                }
            } else {
                format_ = "";
            }
            AccessEnum acc_ = clblock_.getAccess();
            boolean st_ = clblock_.isStaticType();
            if (clblock_ instanceof InterfaceBlock) {
                return new ClassMetaInfo(_name, ((InterfaceBlock)clblock_).getImportedDirectSuperInterfaces(), format_, inners_,
                        infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,st_,acc_);
            }
            if (clblock_ instanceof AnnotationBlock) {
                return new ClassMetaInfo(_name, new StringList(), format_, inners_,
                        infosFields_,infos_, infosConst_, ClassCategory.ANNOTATION,st_,acc_);
            }
            ClassCategory cat_ = ClassCategory.CLASS;
            if (clblock_ instanceof EnumBlock) {
                cat_ = ClassCategory.ENUM;
            }
            boolean abs_ = clblock_.isAbstractType();
            boolean final_ = clblock_.isFinalType();
            String superClass_ = ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperClass();
            StringList superInterfaces_ = ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperInterfaces();
            return new ClassMetaInfo(_name, superClass_, superInterfaces_, format_, inners_,
                    infosFields_,infos_, infosConst_, cat_, abs_, st_, final_,acc_);
        }
        return null;
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
    public static CustList<GeneConstructor> getConstructorBodiesById(Analyzable _context,String _genericClassName, ConstructorId _id) {
        return getConstructorBodiesById(_context, _genericClassName, _id.getParametersTypes(), _id.isVararg());
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
    public DefaultLockingClass getLocks() {
        return locks;
    }
    public void setLocks(DefaultLockingClass _locks) {
        locks = _locks;
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
    public void tryBuildClassesBodies(StringMap<String> _files, ContextEl _context) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            try {
                if (file_.isEmpty()) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                for (char c: file_.toCharArray()) {
                    if (StringList.isDollarWordChar(c)) {
                        continue;
                    }
                    if (c == DOT) {
                        continue;
                    }
                    if (c == SEP_FILE) {
                        continue;
                    }
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                if (StringList.indexesOfChar(file_, DOT).size() != 1) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                if (file_.lastIndexOf(SEP_FILE) > file_.indexOf(DOT)) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                if (!file_.endsWith(StringList.concat(String.valueOf(DOT),EXT))) {
                    BadFileName b_ = new BadFileName();
                    b_.setClassName(file_);
                    b_.setFileName(file_);
                    b_.setRc(new RowCol());
                    addError(b_);
                }
                for (String s: StringList.splitChars(file_, SEP_FILE)) {
                    if (StringList.isWord(s)) {
                        continue;
                    }
                    if (s.isEmpty()) {
                        BadFileName b_ = new BadFileName();
                        b_.setClassName(file_);
                        b_.setFileName(file_);
                        b_.setRc(new RowCol());
                        addError(b_);
                    }
                    for (String e: StringList.splitChars(s, DOT)) {
                        if (StringList.isWord(e)) {
                            continue;
                        }
                        BadFileName b_ = new BadFileName();
                        b_.setClassName(file_);
                        b_.setFileName(file_);
                        b_.setRc(new RowCol());
                        addError(b_);
                    }
                }
                String content_ = f.getValue();
                DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(content_);
                Document doc_ = res_.getDocument();
                if (doc_ == null) {
                    BadFileName bad_ = new BadFileName();
                    bad_.setRc(res_.getLocation());
                    bad_.setFileName(file_);
                    addError(bad_);
                    continue;
                }
                _context.setHtml(content_);
                _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
                Element root_ = doc_.getDocumentElement();
                FileBlock fileBlock_ = new FileBlock();
                fileBlock_.setFileName(file_);
                Block bl_ = Block.createOperationNode(root_, _context, 0, fileBlock_);
                if (!(bl_ instanceof RootBlock)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(bl_.getFile().getFileName());
                    un_.setRc(bl_.getRowCol(0, bl_.getOffset().getOffsetTrim()));
                    addError(un_);
                }
                int tabWidth_ = _context.getTabWidth();
                RootBlock cl_ = (RootBlock) bl_;
                ElementOffsetsNext e_ = _context.getElements();
                ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(content_, e_, root_, tabWidth_);
                fileBlock_.appendChild(cl_);
                processCustomClass(file_, fileBlock_, cl_, false, content_, _context, ne_);
            } catch (RuntimeException _0) {
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                addError(bad_);
            }
        }
        LgNames stds_ = _context.getStandards();
        String content_ = PredefinedClasses.getIterableType(_context);
        processPredefinedClass(stds_.getAliasIterable(), content_, _context);
        content_ = PredefinedClasses.getIteratorType(_context);
        processPredefinedClass(stds_.getAliasIteratorType(), content_, _context);
        content_ = PredefinedClasses.getEnumType(_context);
        processPredefinedClass(stds_.getAliasEnum(), content_, _context);
        content_ = PredefinedClasses.getEnumParamType(_context);
        processPredefinedClass(stds_.getAliasEnumParam(), content_, _context);
        _context.setHtml(EMPTY_STRING);
    }
    private void processPredefinedClass(String _fileName,String _content, ContextEl _context) {
        DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(_content);
        Document doc_ = res_.getDocument();
        _context.setHtml(_content);
        _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
        Element root_ = doc_.getDocumentElement();
        FileBlock fileBlock_ = new FileBlock();
        fileBlock_.setFileName(_fileName);
        Block bl_ = Block.createOperationNode(root_, _context, 0, fileBlock_);
        fileBlock_.appendChild(bl_);
        int tabWidth_ = _context.getTabWidth();
        RootBlock cl_ = (RootBlock) bl_;
        ElementOffsetsNext e_ = _context.getElements();
        ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(_content, e_, root_, tabWidth_);
        processCustomClass(_fileName, fileBlock_, cl_, true, _content, _context, ne_);
    }
    private void processCustomClass(String _fileName,FileBlock _fileBlock, RootBlock _root, boolean _predefined, String _content, ContextEl _context, ElementOffsetsNext _elt) {
        if (classesBodies.contains(_root.getFullName())) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(_root.getFullName());
            d_.setFileName(_root.getFile().getFileName());
            d_.setRc(_root.getRowCol(0, _root.getIdRowCol()));
            addError(d_);
        }
        filesBodies.put(_fileName, _fileBlock);
        RootBlock bl_ = _root;
        ElementOffsetsNext ne_ = _elt;
        bl_.setAttributes(ne_.getAttributes());
        bl_.setEndHeader(ne_.getEndHeader());
        bl_.setTabs(ne_.getTabs());
        bl_.setOffsets(ne_.getOffsets());
        RootBlock cl_ = bl_;
        String packageName_;
        packageName_ = cl_.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (!_predefined) {
            if (packageName_.isEmpty()) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(cl_.getFullName());
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                addError(badCl_);
            }
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                if (!StringList.isWord(e)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(cl_.getFullName());
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
            }
            String className_;
            className_ = cl_.getName();
            if (!StringList.isWord(className_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(cl_.getFullName());
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                addError(badCl_);
            }
        }
        String fullDef_ = cl_.getFullDefinition();
        StringList params_ = Templates.getAllTypes(fullDef_);
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        if (params_ != null) {
            for (String p: params_.mid(CustList.SECOND_INDEX)) {
                if (p.isEmpty()) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                    continue;
                }
                if (!p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                String name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
                TypeVar type_ = new TypeVar();
                int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
                StringList parts_ = StringList.splitInTwo(name_, indexDef_);
                if (!StringList.isWord(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                if (varTypes_.containsStr(parts_.first())) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(fullDef_);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
                varTypes_.add(parts_.first());
                StringList constraints_ = new StringList();
                if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                    for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                        if (!isCorrectTemplate(b, _context, cl_)) {
                            BadClassName badCl_ = new BadClassName();
                            badCl_.setClassName(fullDef_);
                            badCl_.setFileName(cl_.getFile().getFileName());
                            badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                            addError(badCl_);
                        }
                        constraints_.add(b);
                    }
                } else {
                    constraints_.add(objectClassName_);
                }
                type_.setConstraints(constraints_);
                type_.setName(parts_.first());
                cl_.getParamTypes().add(type_);
            }
        } else {
            BadClassName badCl_ = new BadClassName();
            badCl_.setClassName(fullDef_);
            badCl_.setFileName(cl_.getFile().getFileName());
            badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(badCl_);
        }
        int indexSuperType_= -1;
        for (String s: cl_.getDirectGenericSuperTypesBuild(_context)) {
            indexSuperType_++;
            if (!isCorrectTemplate(s, _context, cl_)) {
                BadClassName badCl_ = new BadClassName();
                badCl_.setClassName(s);
                badCl_.setFileName(cl_.getFile().getFileName());
                badCl_.setRc(cl_.getRowCol(0, cl_.getRowColDirectSuperTypes().getKey(indexSuperType_)));
                addError(badCl_);
            }
        }
        for (TypeVar t: cl_.getParamTypes()) {
            for (String u: t.getConstraints()) {
                if (!u.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                if (!cl_.getParamTypesMap().contains(u.substring(1))) {
                    BadClassName badCl_ = new BadClassName();
                    badCl_.setClassName(u);
                    badCl_.setFileName(cl_.getFile().getFileName());
                    badCl_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
                    addError(badCl_);
                }
            }
        }
        if (lgNames_.getStandards().contains(cl_.getFullName())) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(cl_.getFullName());
            d_.setFileName(cl_.getFile().getFileName());
            d_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(d_);
        }
        if (PrimitiveTypeUtil.isPrimitive(cl_.getFullName(), _context)) {
            DuplicateType d_ = new DuplicateType();
            d_.setId(cl_.getFullName());
            d_.setFileName(cl_.getFile().getFileName());
            d_.setRc(cl_.getRowCol(0, cl_.getIdRowCol()));
            addError(d_);
        }
        Block rootBl_ = cl_;
        CustList<Block> all_ = getSortedDescNodesRoot(rootBl_, _context);
        for (Block b: all_) {
            b.setupChars(_content);
            b.setCompleteGroup();
            b.setNullAssociateElement();
        }
        String fullName_ = cl_.getFullName();
        classesBodies.put(fullName_, cl_);
    }
    public static CustList<Block> getSortedDescNodesRoot(Block _root, ContextEl _context) {
        CustList<Block> list_ = new CustList<Block>();
        if (_root == null) {
            return list_;
        }
        Block c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            Block next_ = createFirstChild(c_, _context);
            if (next_ != null) {
                next_.setupMetrics(_context);
                ((BracedBlock) c_).appendChild(next_);
                c_ = next_;
                continue;
            }
            next_ = createNextSibling(c_, _context);
            if (next_ != null) {
                next_.getParent().appendChild(next_);
                next_.setupMetrics(_context);
                c_ = next_;
                continue;
            }
            next_ = c_.getParent();
            if (next_ == _root) {
                c_ = null;
                continue;
            }
            if (next_ != null) {
                Block nextAfter_ = createNextSibling(next_, _context);
                while (nextAfter_ == null) {
                    Block par_ = next_.getParent();
                    if (par_ == _root) {
                        break;
                    }
                    if (par_ == null) {
                        break;
                    }
                    nextAfter_ = createNextSibling(par_, _context);
                    next_ = par_;
                }
                if (nextAfter_ != null) {
                    nextAfter_.getParent().appendChild(nextAfter_);
                    nextAfter_.setupMetrics(_context);
                    c_ = nextAfter_;
                    continue;
                }
            }
            c_ = null;
        }
        return list_;
    }

    private static Block createFirstChild(Block _block, ContextEl _context) {
        if (!(_block instanceof BracedBlock)) {
            return null;
        }
        BracedBlock block_ = (BracedBlock) _block;
        Element elt_ = block_.getAssociateElement();
        Node first_ = elt_.getFirstChild();
        while (first_ != null) {
            if (first_ instanceof Element) {
                break;
            }
            first_ = first_.getNextSibling();
        }
        if (first_ == null) {
            return null;
        }
        Element eltFirst_ = (Element) first_;
        return Block.createOperationNode(eltFirst_, _context, CustList.FIRST_INDEX, block_);
    }

    private static Block createNextSibling(Block _block, ContextEl _context) {
        BracedBlock p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        Node n_ = _block.getAssociateElement().getNextSibling();
        while (n_ != null) {
            if (n_ instanceof Element) {
                break;
            }
            n_ = n_.getNextSibling();
        }
        if (n_ == null) {
            return null;
        }
        Element next_ = (Element) n_;
        return Block.createOperationNode(next_, _context, _block.getIndexChild() + 1, p_);
    }
    private static boolean isCorrectTemplate(String _temp, ContextEl _context, RootBlock _type) {
        RowCol rc_ = new RowCol();
        String temp_ = PartTypeUtil.processTypeHeaders(_temp, _context, _type, rc_);
        if (PrimitiveTypeUtil.isPrimitive(temp_, _context)) {
            return false;
        }
        return true;
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

    public static CustList<MethodBlock> getMethodBodiesById(ContextEl _context,String _genericClassName, MethodId _id) {
        CustList<MethodBlock> methods_ = new CustList<MethodBlock>();
        String base_ = Templates.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(base_);
        for (GeneMethod m: Classes.getMethodBlocks(r_)) {
            if (m.getId().eq(_id)) {
                methods_.add((MethodBlock)m);
                break;
            }
        }
        return methods_;
    }
    public static CustList<GeneMethod> getMethodBlocks(RootBlock _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof MethodBlock) {
                methods_.add((MethodBlock) b);
            }
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((AnnotationMethodBlock) b);
            }
        }
        return methods_;
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
    public boolean isEmptyWarnings() {
        return warningsDet.isEmpty();
    }
    public void addWarning(FoundWarningInterpret _warning) {
        warningsDet.add(_warning);
    }
    public String displayWarnings() {
        return warningsDet.display();
    }
    public WarningList getWarningsDet() {
        return warningsDet;
    }
  //validate missing return
    //validate break,continue ancestors / try/catch/finally / switch/case/default
    //validate dead code
    public void validateReturns(ContextEl _context) {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        _context.setAnalyzing(page_);
        LgNames stds_ = _context.getStandards();
        String void_ = stds_.getAliasVoid();
        for (EntryCust<String, RootBlock> c: classesBodies.entryList()) {
            String className_ = c.getKey();
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof AloneBlock) {
                    CustList<Block> chSort_ = getSortedDescNodes(b);
                    CustList<Block> all_ = new CustList<Block>();
                    for (Block s: chSort_) {
                        all_.add(s);
                    }
                    int order_ = 0;
                    while (true) {
                        CustList<Block> next_ = new CustList<Block>();
                        for (Block e: all_) {
                            if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                                continue;
                            }
                            CustList<Block> list_ = getDirectChildren(e);
                            boolean allNb_ = true;
                            for (Block s: list_) {
                                Block op_ = s;
                                if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                                    allNb_ = false;
                                    break;
                                }
                            }
                            if (!allNb_) {
                                continue;
                            }
                            next_.add(e);
                        }
                        if (next_.isEmpty()) {
                            break;
                        }
                        for (Block o: next_) {
                            o.setOrder(order_);
                            order_++;
                        }
                    }
                    all_.sortElts(new ComparatorBlockOrder());
                    for (Block d: all_) {
                        d.setAlwaysSkipped();
                    }
                    for (Block d: all_) {
                        d.setExitable();
                    }
                    for (Block d: all_) {
                        d.setStoppable();
                    }
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(EMPTY_STRING);
                            addError(deadCode_);
                        }
                    }
                }
                if (b instanceof Returnable) {
                    Returnable method_ = (Returnable) b;
                    if (method_ instanceof MethodBlock) {
                        if (((MethodBlock)b).isAbstractMethod()) {
                            continue;
                        }
                    }
                    CustList<Block> chSort_ = getSortedDescNodes(b);
                    CustList<Block> all_ = new CustList<Block>();
                    for (Block s: chSort_) {
                        all_.add(s);
                    }
                    int order_ = 0;
                    while (true) {
                        CustList<Block> next_ = new CustList<Block>();
                        for (Block e: all_) {
                            if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                                continue;
                            }
                            CustList<Block> list_ = getDirectChildren(e);
                            boolean allNb_ = true;
                            for (Block s: list_) {
                                Block op_ = s;
                                if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                                    allNb_ = false;
                                    break;
                                }
                            }
                            if (!allNb_) {
                                continue;
                            }
                            next_.add(e);
                        }
                        if (next_.isEmpty()) {
                            break;
                        }
                        for (Block o: next_) {
                            o.setOrder(order_);
                            order_++;
                        }
                    }
                    all_.sortElts(new ComparatorBlockOrder());
                    for (Block d: all_) {
                        d.setAlwaysSkipped();
                    }
                    for (Block d: all_) {
                        d.setExitable();
                    }
                    for (Block d: all_) {
                        d.setStoppable();
                    }
                    Block r_ = all_.last();
                    StringList types_ = method_.getImportedParametersTypes();
                    int len_ = types_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = types_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
                    if (!r_.isExitable() && !StringList.quickEq(method_.getImportedReturnType(), void_)) {
                        MissingReturnMethod miss_ = new MissingReturnMethod();
                        miss_.setRc(method_.getRowCol(0, method_.getOffset().getOffsetTrim()));
                        miss_.setFileName(className_);
                        miss_.setId(method_.getSignature());
                        miss_.setReturning(method_.getImportedReturnType());
                        addError(miss_);
                    }
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(method_.getSignature());
                            addError(deadCode_);
                        }
                    }
                }
            }
        }
    }
    public static CustList<Block> getSortedDescNodes(Block _root) {
        CustList<Block> list_ = new CustList<Block>();
        if (_root == null) {
            return list_;
        }
        Block c_ = _root;
        Block f_ = c_.getFirstChild();
        list_.add(c_);
        if (f_ == null) {
            return list_;
        }
        c_ = f_;
        while (true) {
            if (c_ == null) {
                break;
            }
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
        n_ = _current.getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getParent();
        if (n_ == _root) {
            return null;
        }
        if (n_ != null) {
            Block next_ = n_.getNextSibling();
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ != null) {
                return next_;
            }
        }
        return null;
    }
    public static CustList<Block> getDirectChildren(Block _element) {
        CustList<Block> list_ = new CustList<Block>();
        if (_element == null) {
            return list_;
        }
        Block firstChild_ = _element.getFirstChild();
        Block elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public StringList breadthFirst(String _parent, ContextEl _context) {

        StringList all_ = new StringList();
        StringList nodeQueue_ = new StringList();
        nodeQueue_.add(_parent);

        while (!nodeQueue_.isEmpty()) {
            String current_ = nodeQueue_.first();
            nodeQueue_.remove(0);
            all_.add(current_);
            RootBlock info_ = getClassBody(current_);
            StringList direct_ = getDirectSubTypes(info_, _context);
            for (String c : direct_) {
                nodeQueue_.add(c);
            }
        }
        StringList unique_ = new StringList();
        for (String c: all_) {
            if (!unique_.containsStr(c)) {
                unique_.add(c);
            }
        }
        return unique_;
    }
    public static StringList getDirectSubTypes(RootBlock _r,ContextEl _conf) {
        StringList subTypes_ = new StringList();
        Classes classes_ = _conf.getClasses();
        String baseClassFound_ = _r.getFullName();
        for (RootBlock c: classes_.getClassBodies()) {
            String name_ = c.getFullName();
            RootBlock r_ = classes_.getClassBody(name_);
            if (r_ instanceof InterfaceBlock) {
                if (getDirectSuperInterfaces((InterfaceBlock) r_).containsStr(baseClassFound_)) {
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
    public static void buildPredefinedBracesBodies(ContextEl _context) {
        _context.setAnalyzing(new AnalyzedPageEl());
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, String> e: stds_.buildFiles(_context).entryList()) {
            String name_ = e.getKey();
            String content_ = e.getValue();
            FileResolver.parseFile(name_, content_, true, _context);
        }
        Classes cl_ = _context.getClasses();
        TypeUtil.buildInherits(_context, cl_.classesBodies.getKeys(), true);
        for (RootBlock t: cl_.classesBodies.values()) {
            TypeUtil.buildOverrides(t, _context);
        }
        for (RootBlock t: cl_.classesBodies.values()) {
            t.validateIds(_context);
        }
        //local names
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setEnabledInternVars(true);
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIterableType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.iteratorVar = locName_;
        String simpleIterator_ = stds_.getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(simpleIterator_,PARS));
        cl_.expsIterator = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.hasNextVar = locName_;
        String hasNext_ = stds_.getAliasHasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.expsHasNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.nextVar = locName_;
        String next_ = stds_.getAliasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.expsNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIterable());
        _context.getInternVars().put(locName_, locVar_);
        cl_.iteratorVarCust = locName_;
        String iterator_ = stds_.getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.expsIteratorCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.hasNextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.expsHasNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getAliasIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        cl_.nextVarCust = locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.expsNextCust = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
    }

    public static boolean canAccessField(String _className, String _accessedClass, String _name, Analyzable _context) {
        String baseClass_ = Templates.getIdFromAllTypes(_accessedClass);
        GeneType access_ = _context.getClassBody(baseClass_);
        if (access_ instanceof RootBlock) {
            CustList<Block> bl_ = getDirectChildren((Block) access_);
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    if (StringList.quickEq(((InfoBlock)b).getFieldName(), _name)) {
                        return canAccess(_className,(InfoBlock)b, _context);
                    }
                }
            }
            return false;
        }
        for (StandardField f: _context.getStandards().getStandards().getVal(baseClass_).getFields().values()) {
            if (StringList.quickEq(f.getFieldName(), _name)) {
                return canAccess(_className,f, _context);
            }
        }
        return false;
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
        if (_className == null) {
            return false;
        }
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(baseClass_);
        GeneType belong_ = _block.belong();
        if (_block.getAccess() == AccessEnum.PROTECTED) {
            if (PrimitiveTypeUtil.canBeUseAsArgument(belong_.getFullName(), baseClass_, _context)) {
                return true;
            }
            if (StringList.quickEq(belong_.getPackageName(), root_.getPackageName())) {
                return true;
            }
            return false;
        }
        if (_block.getAccess() == AccessEnum.PACKAGE) {
            if (StringList.quickEq(belong_.getPackageName(), root_.getPackageName())) {
                return true;
            }
            return false;
        }
        GeneType outBelong_ = belong_.getOuter();
        GeneType outRoot_ = root_.getOuter();
        if (StringList.quickEq(outBelong_.getFullName(), outRoot_.getFullName())) {
            return true;
        }
        return false;
    }

    public String getIteratorVar(boolean _native) {
        if (_native) {
            return iteratorVar;
        }
        return iteratorVarCust;
    }

    public String getHasNextVar(boolean _native) {
        if (_native) {
            return hasNextVar;
        }
        return hasNextVarCust;
    }

    public String getNextVar(boolean _native) {
        if (_native) {
            return nextVar;
        }
        return nextVarCust;
    }

    public ExpressionLanguage getEqIterator(boolean _native) {
        if (_native) {
            return new ExpressionLanguage(expsIterator);
        }
        return new ExpressionLanguage(expsIteratorCust);
    }

    public ExpressionLanguage getEqHasNext(boolean _native) {
        if (_native) {
            return new ExpressionLanguage(expsHasNext);
        }
        return new ExpressionLanguage(expsHasNextCust);
    }

    public ExpressionLanguage getEqNext(boolean _native) {
        if (_native) {
            return new ExpressionLanguage(expsNext);
        }
        return new ExpressionLanguage(expsNextCust);
    }

    public static void tryBuildBracedClassesBodies(StringMap<String> _files, ContextEl _context) {
        _context.setAnalyzing(null);
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileResolver.parseFile(file_, content_, false, _context);
        }
    }
    public static StringList getCustomDirectSuperClasses(RootBlock _r,ContextEl _context) {
        StringList direct_ = _r.getDirectSuperClasses(_context);
        direct_.removeAllObj(_context.getStandards().getAliasObject());
        return direct_;
    }

    public static StringList getDirectSuperInterfaces(InterfaceBlock _i) {
        StringList classes_ = new StringList();
        for (String s: _i.getDirectSuperTypes()) {
            int index_ = s.indexOf("<");
            if (index_ > CustList.INDEX_NOT_FOUND_ELT) {
                classes_.add(s.substring(CustList.FIRST_INDEX, index_));
            } else {
                classes_.add(s);
            }
        }
        return classes_;
    }

    public StringList getSortedSuperInterfaces(StringList _interfaces, ContextEl _context) {
        StringList sortedSuperInterfaces_ = new StringList();
        for (String i: _interfaces) {
            StringList superInterfaces_ = new StringList(i);
            StringList currentInterfaces_ = new StringList(i);
            while (true) {
                StringList nextInterfaces_ = new StringList();
                for (String c: currentInterfaces_) {
                    String baseClass_ = Templates.getIdFromAllTypes(c);
                    RootBlock int_ = getClassBody(baseClass_);
                    StringList directSuperInterfaces_ = getCustomDirectSuperClasses(int_, _context);
                    for (String s:directSuperInterfaces_) {
                        if (superInterfaces_.containsStr(s)) {
                            continue;
                        }
                        superInterfaces_.add(s);
                        nextInterfaces_.add(s);
                    }
                }
                if (nextInterfaces_.isEmpty()) {
                    break;
                }
                currentInterfaces_ = nextInterfaces_;
            }
            StringMap<InterfaceNode> is_ = new StringMap<InterfaceNode>();
            InterfaceNode i_ = new InterfaceNode();
            i_.setInterfaceName(superInterfaces_.first());
            is_.put(superInterfaces_.first(), i_);
            for (String s: superInterfaces_) {
                String baseClass_ = Templates.getIdFromAllTypes(s);
                RootBlock int_ = getClassBody(baseClass_);
                StringList directSuperInterfaces_ = getCustomDirectSuperClasses(int_, _context);
                InterfaceNode current_ = is_.getVal(s);
                for (String r: directSuperInterfaces_) {
                    InterfaceNode intNode_ = is_.getVal(r);
                    if (intNode_ != null) {
                        continue;
                    }
                    InterfaceNode child_ = current_.getFirstChild();
                    int index_ = CustList.FIRST_INDEX;
                    if (child_ != null) {
                        InterfaceNode loc_ = child_;
                        while (true) {
                            if (loc_ == null) {
                                break;
                            }
                            index_++;
                            InterfaceNode next_ = loc_.getNextSibling();
                            if (next_ == null) {
                                child_ = loc_;
                            }
                            loc_ = next_;
                        }
                    }
                    InterfaceNode ic_ = new InterfaceNode();
                    ic_.setParent(current_);
                    ic_.setInterfaceName(r);
                    is_.put(r, ic_);
                    if (child_ == null) {
                        ic_.setIndexChild(0);
                        current_.setFirstChild(ic_);
                        continue;
                    }
                    ic_.setIndexChild(index_);
                    child_.setNextSibling(ic_);
                }
            }
            CustList<InterfaceNode> all_ = new CustList<InterfaceNode>();
            for (InterfaceNode s: getSortedDescNodes(i_)) {
                all_.add(s);
            }
            int order_ = 0;
            while (true) {
                CustList<InterfaceNode> next_ = new CustList<InterfaceNode>();
                for (InterfaceNode e: all_) {
                    if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                        continue;
                    }
                    InterfaceNode cur_ = e;
                    boolean tonumber_ = true;
                    while (cur_ != null) {
                        int index_ = cur_.getIndexChild() - 1;
                        if (index_ >= CustList.FIRST_INDEX) {
                            CustList<InterfaceNode> sn_ = getDirectChildren(cur_.getParent());
                            InterfaceNode s_ = sn_.get(index_);
                            InterfaceNode prev_ = s_;
                            if (prev_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                                tonumber_ = false;
                                break;
                            }
                        }
                        cur_ = cur_.getParent();
                    }
                    if (!tonumber_) {
                        continue;
                    }
                    CustList<InterfaceNode> list_ = getDirectChildren(e);
                    if (!list_.isEmpty()) {
                        InterfaceNode op_ = list_.last();
                        if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                            continue;
                        }
                    }
                    next_.add(e);
                }
                if (next_.isEmpty()) {
                    break;
                }
                for (InterfaceNode o: next_) {
                    o.setOrder(order_);
                    order_++;
                }
            }
            all_.sortElts(new ComparatorInterfaceNode());
            for (InterfaceNode j: all_) {
                String name_ = j.getInterfaceName();
                if (!sortedSuperInterfaces_.containsStr(name_)) {
                    sortedSuperInterfaces_.add(name_);
                }
            }
        }
        return sortedSuperInterfaces_;
    }
    public static CustList<InterfaceNode> getSortedDescNodes(InterfaceNode _root) {
        CustList<InterfaceNode> list_ = new CustList<InterfaceNode>();
        if (_root == null) {
            return list_;
        }
        InterfaceNode c_ = _root;
        if (c_.getFirstChild() == null) {
            list_.add(c_);
            return list_;
        }
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static InterfaceNode getNext(InterfaceNode _current, InterfaceNode _root) {
        InterfaceNode n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getParent();
        if (n_ == _root) {
            return null;
        }
        if (n_ != null) {
            InterfaceNode next_ = n_.getNextSibling();
            while (next_ == null) {
                InterfaceNode par_ = n_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ != null) {
                return next_;
            }
        }
        return null;
    }

    public static CustList<InterfaceNode> getDirectChildren(InterfaceNode _element) {
        CustList<InterfaceNode> list_ = new CustList<InterfaceNode>();
        if (_element == null) {
            return list_;
        }
        InterfaceNode firstChild_ = _element.getFirstChild();
        InterfaceNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public StringList getSortedSuperInterfaces(StringList _already, String _interface, ContextEl _context) {
        StringList superInterfaces_ = new StringList(_interface);
        StringList currentInterfaces_ = new StringList(_interface);
        while (true) {
            StringList nextInterfaces_ = new StringList();
            for (String c: currentInterfaces_) {
                String baseClass_ = Templates.getIdFromAllTypes(c);
                RootBlock int_ = getClassBody(baseClass_);
                StringList directSuperInterfaces_ = getCustomDirectSuperClasses(int_, _context);
                for (String s:directSuperInterfaces_) {
                    superInterfaces_.add(s);
                    nextInterfaces_.add(s);
                }
            }
            if (nextInterfaces_.isEmpty()) {
                break;
            }
            currentInterfaces_ = nextInterfaces_;
        }
        StringList superInterfacesSet_ = new StringList();
        for (String s: superInterfaces_.getReverse()) {
            if (_already.containsStr(s)) {
                continue;
            }
            if (!superInterfacesSet_.containsStr(s)) {
                superInterfacesSet_.add(s);
            }
        }
        return superInterfacesSet_;
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

    public CustList<RootBlock> getClassBodies() {
        return classesBodies.values();
    }

    public StringMap<RootBlock> getClassesBodies() {
        return classesBodies;
    }
	public void validateInheritingClasses(ContextEl _cont) {
	}
}
