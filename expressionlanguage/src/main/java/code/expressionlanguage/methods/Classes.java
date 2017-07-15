package code.expressionlanguage.methods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustEnum;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.exceptions.AlreadyExistingClassException;
import code.expressionlanguage.methods.exceptions.AnalyzingErrorsException;
import code.expressionlanguage.methods.exceptions.BadClassNameException;
import code.expressionlanguage.methods.exceptions.BadFileNameException;
import code.expressionlanguage.methods.exceptions.UnknownBlockException;
import code.expressionlanguage.methods.util.BadClassName;
import code.expressionlanguage.methods.util.BadFieldName;
import code.expressionlanguage.methods.util.BadFileName;
import code.expressionlanguage.methods.util.BadInheritedClass;
import code.expressionlanguage.methods.util.BadMethodName;
import code.expressionlanguage.methods.util.BadNumberArgMethod;
import code.expressionlanguage.methods.util.BadParamName;
import code.expressionlanguage.methods.util.BadReturnTypeInherit;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.ClassEdge;
import code.expressionlanguage.methods.util.DeadCodeMethod;
import code.expressionlanguage.methods.util.DuplicateField;
import code.expressionlanguage.methods.util.DuplicateMethod;
import code.expressionlanguage.methods.util.DuplicateParamName;
import code.expressionlanguage.methods.util.EqualsEl;
import code.expressionlanguage.methods.util.FoundErrorInterpret;
import code.expressionlanguage.methods.util.MissingReturnMethod;
import code.expressionlanguage.methods.util.ReservedMethod;
import code.expressionlanguage.methods.util.StaticInstanceOverriding;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.graphs.Graph;
import code.xml.ElementOffsetsNext;
import code.xml.RowCol;
import code.xml.XmlParser;
import code.xml.exceptions.XmlParseException;

public final class Classes {

    /*private static final String EXPRESSIONLANGUAGE = "expressionlanguage";
    private static final String STREAM = "stream";
    private static final String UTIL = "util";*/
    public static final String EXT = "cdm";
    private static final String ATTRIBUTE_PACKAGE = "package";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_LEFT = "left";
    private static final String ATTRIBUTE_OPER = "oper";
    private static final String ATTRIBUTE_RIGHT = "right";
    private static final String ATTRIBUTE_VAR = "var";
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_MODIFIER = "modifier";
    private static final String ATTRIBUTE_SUPER_CLASS = "superclass";
    private static final String ATTRIBUTE_CLASS_INDEX = "classindex";
    private static final String ATTRIBUTE_CONDITION = "condition";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_EXPRESSION = "expression";
    private static final String ATTRIBUTE_EQ = "eq";
    private static final String ATTRIBUTE_INIT = "init";
    private static final String ATTRIBUTE_STEP = "step";

    private static final String EXT_PRO = "pro";
    private static final String TEMP_PREFIX = "tmp";
    private static final String EQ_FORMAT = "{0};.eq({1};.)";
    private static final String NAT_EQ_FORMAT = "{0};.={1};.";
    private static final char SEP_FILE = '/';
    private static final char DOT = '.';
    private static final String EMPTY_STRING = "";
    //    private final Map<ClassName, ClassMetaInfo> classes;
    private final ObjectMap<ClassName,Block> classesBodies;
//    private final Map<String, String> classesContent;
    private final ObjectMap<ClassField,Struct> staticFields;
    private final ObjectMap<ClassName,CustList<Struct>> values;
    private final StringMap<Boolean> initializedClasses;
    private final StringMap<Boolean> successfulInitializedClasses;
//    private final StringList errors;
    private final CustList<FoundErrorInterpret> errorsDet;
    private final StringList localVariablesNames;
    private final StringList classesInheriting;
    private EqualsEl eqEl;
    private EqualsEl natEqEl;
    private CustList<OperationNode> exps;
    public Classes(StringMap<String> _files, ContextEl _context) {
        classesBodies = new ObjectMap<ClassName,Block>();
//        errors = new StringList();
        errorsDet = new CustList<FoundErrorInterpret>();
        StringList classes_ = new StringList();
//        classesContent = new Map<String,String>();
        staticFields = new ObjectMap<ClassField,Struct>();
        values = new ObjectMap<ClassName,CustList<Struct>>();
        initializedClasses = new StringMap<Boolean>();
        successfulInitializedClasses = new StringMap<Boolean>();
        classesInheriting = new StringList();
        localVariablesNames = new StringList();
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            try {
                if (file_.isEmpty()) {
                    throw new BadFileNameException(file_);
                }
                for (char c: file_.toCharArray()) {
                    if (StringList.isWordChar(c)) {
                        continue;
                    }
                    if (c == DOT) {
                        continue;
                    }
                    if (c == SEP_FILE) {
                        continue;
                    }
                    throw new BadFileNameException(file_);
                }
                if (StringList.indexesOfChar(file_, DOT).size() != 1) {
                    throw new BadFileNameException(file_);
                }
                if (file_.lastIndexOf(SEP_FILE) > file_.indexOf(DOT)) {
                    throw new BadFileNameException(file_);
                }
                if (!file_.endsWith(DOT+EXT)) {
                    throw new BadFileNameException(file_);
                }
                for (String s: StringList.splitChars(file_, SEP_FILE)) {
                    if (StringList.isWord(s)) {
                        continue;
                    }
                    if (s.isEmpty()) {
                        throw new BadFileNameException(file_);
                    }
                    for (String e: StringList.splitChars(s, DOT)) {
                        if (StringList.isWord(e)) {
                            continue;
                        }
                        throw new BadFileNameException(file_);
                    }
                }
                String content_ = f.getValue();
                file_ = file_.substring(CustList.FIRST_INDEX, file_.indexOf(DOT));
                file_ = file_.replace(SEP_FILE, DOT);
                for (String e: classes_) {
                    if (e.equalsIgnoreCase(file_)) {
                        throw new AlreadyExistingClassException(file_);
                    }
                }
                classes_.add(file_);
//                Document doc_ = XmlParser.parseSaxHtml(content_);
                Document doc_ = XmlParser.parseSaxHtmlRowCol(content_);
                _context.setHtml(content_);
                _context.setElements(new ElementOffsetsNext(new RowCol(), 0, 0));
                Element root_ = doc_.getDocumentElement();
                Block bl_ = Block.createOperationNode(root_, _context, 0, null);
                if (!(bl_ instanceof RootedBlock)) {
                    throw new XmlParseException();
                }
                RootedBlock cl_ = (RootedBlock) bl_;
                String packageName_;
                packageName_ = cl_.getPackageName();
                if (packageName_.isEmpty()) {
                    throw new BadClassNameException(cl_.getFullName());
                }
                StringList elements_ = StringList.splitChars(packageName_, DOT);
                for (String e: elements_) {
                    if (!StringList.isWord(e)) {
                        throw new BadClassNameException(cl_.getFullName());
                    }
                }
                String className_;
                className_ = cl_.getName();
                if (!StringList.isWord(className_)) {
                    throw new BadClassNameException(cl_.getFullName());
                }
                //TODO check while analyzing code
                /*if (packagePrefix(cl_, UTIL)) {
                    throw new BadClassNameException(cl_.getFullName());
                }
                if (packagePrefix(cl_, STREAM)) {
                    throw new BadClassNameException(cl_.getFullName());
                }
                if (packagePrefix(cl_, EXPRESSIONLANGUAGE)) {
                    throw new BadClassNameException(cl_.getFullName());
                }*/
                if (!cl_.getFullName().equalsIgnoreCase(file_)) {
                    throw new BadClassNameException(cl_.getFullName());
                }
                try {
                    Class<?> clNat_ = ConstClasses.classAliasForNameNotInit(file_);
                    throw new AlreadyExistingClassException(clNat_.getName());
                } catch (RuntimeClassNotFoundException _0) {
                }
                CustList<Block> all_ = getSortedDescNodes((Block)cl_);
                for (Block b: all_) {
                    b.setConf(null);
                    b.setupChars(content_);
                    b.setCompleteGroup();
                    b.setNullAssociateElement();
                }
//                for (SortedNode<Block> b: all_) {
//                    ((Block)b).setAlwaysSkipped();
//                }
//                TreeRetrieving.getSortedDescNodes(cl_);
                initializedClasses.put(file_, false);
                classesBodies.put(new ClassName(file_, false), bl_);
            } catch (UnknownBlockException _0) {
                RowCol where_ = _0.getRc();
                UnexpectedTagName t_ = new UnexpectedTagName();
                t_.setRc(where_);
                t_.setFileName(file_);
                t_.setFoundTag(_0.getMessage());
                errorsDet.add(t_);
            } catch (BadClassNameException _0) {
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (BadFileNameException _0) {
                BadFileName bad_ = new BadFileName();
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
            } catch (XmlParseException _0) {
                //TODO change later class
                BadFileName bad_ = new BadFileName();
                bad_.setRc(_0.getRowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
//            } catch (RuntimeClassNotFoundException _0) {
//                errors.add(f.getKey());
            } catch (AlreadyExistingClassException _0) {
                //TODO change later class
                BadClassName bad_ = new BadClassName();
                bad_.setClassName(_0.getMessage());
                bad_.setRc(new RowCol());
                bad_.setFileName(file_);
                errorsDet.add(bad_);
//            } catch (RuntimeClassNotFoundException _0) {
//                errors.add(f.getKey());
            }
        }
        _context.setHtml(EMPTY_STRING);
    }
    public static void validateAll(StringMap<String> _files, ContextEl _context) {
        Classes bk_ = _context.getClasses();
        Classes classes_ = new Classes(_files, _context);
        if (!classes_.errorsDet.isEmpty()) {
            throw new AnalyzingErrorsException(classes_.errorsDet);
        }
        try {
            _context.setClasses(classes_);
            classes_.validateInheritingClasses(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateClassBodies(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateClassNames(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateFieldNames(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateFieldsId(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateMethodNames(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateMethodsId(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateOverridingInherit(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateLocalVariableNamesId(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateEl(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
            classes_.validateReturns(_context);
            if (!classes_.errorsDet.isEmpty()) {
                throw new AnalyzingErrorsException(classes_.errorsDet);
            }
//            classes_.classesContent.clear();
            _context.clearPages();
        } catch (AnalyzingErrorsException _0) {
            _context.setClasses(bk_);
            throw _0;
        } catch (RuntimeException _0) {
            _context.setClasses(bk_);
            throw _0;
        } catch (Error _0) {
            _context.setClasses(bk_);
            throw _0;
        }
    }
    public static CustList<Block> getSortedDescNodes(Block _root) {
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
    public void validateInheritingClasses(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        Graph<ClassEdge> inherit_;
        inherit_ = new Graph<ClassEdge>();
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            ClassName d_ = c.getKey();
            RootedBlock bl_ = (RootedBlock) c.getValue();
            for (String s: bl_.getDirectSuperClasses()) {
                ClassName b_ = new ClassName(s, false);
                if (!classesBodies.contains(b_)) {
                    if (!StringList.quickEq(b_.getName(), Object.class.getName())) {
                        UnknownClassName undef_;
                        undef_ = new UnknownClassName();
                        undef_.setClassName(b_.getName());
                        undef_.setFileName(d_.getName());
                        undef_.setRc(bl_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_SUPER_CLASS));
                        errorsDet.add(undef_);
                    }
                } else {
                    Block super_ = classesBodies.getVal(b_);
                    if (super_ instanceof EnumBlock) {
                        BadInheritedClass enum_;
                        enum_ = new BadInheritedClass();
                        String n_ = b_.getName();
                        enum_.setClassName(n_);
                        enum_.setFileName(n_);
                        enum_.setRc(new RowCol());
                        errorsDet.add(enum_);
                    }
                }
                inherit_.addSegment(new ClassEdge(d_), new ClassEdge(b_));
            }
        }
        if (!errorsDet.isEmpty()) {
            return;
        }
        EqList<ClassEdge> cycle_ = inherit_.elementsCycle();
        if (!cycle_.isEmpty()) {
            for (ClassEdge c: cycle_) {
                BadInheritedClass b_;
                b_ = new BadInheritedClass();
                String n_ = c.getId().getName();
                b_.setClassName(n_);
                b_.setFileName(n_);
                b_.setRc(new RowCol());
                errorsDet.add(b_);
            }
            return;
        }
        EqList<ClassEdge> elts_ = inherit_.getElementsListCopy();
        int order_ = 0;
        while (true) {
            EqList<ClassEdge> next_ = new EqList<ClassEdge>();
            for (ClassEdge e: elts_) {
                if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                    continue;
                }
                EqList<ClassEdge> list_ = inherit_.getChildren(e);
                boolean allNb_ = true;
                for (ClassEdge s: list_) {
                    ClassEdge s_ = inherit_.getElementByEq(s);
                    if (s_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
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
            for (ClassEdge o: next_) {
                o.setOrder(order_);
                order_++;
            }
        }
        elts_.sortElts(new ComparatorClassEdge());
        for (ClassEdge c: elts_) {
            classesInheriting.add(c.getId().getName());
        }
        
    }
    public void validateClassBodies(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
            Block r_ = c.getValue();
//            ClassBlock clblock_ = (ClassBlock) r_;
            CustList<Block> bl_ = getDirectChildren(r_);
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
                //TODO constructors, static_blocks
                RowCol where_ = ((Block)b).getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                String tagName_ = ((Block)b).getTagName();
                UnexpectedTagName unexp_ = new UnexpectedTagName();
                unexp_.setFileName(className_);
                unexp_.setFoundTag(tagName_);
                unexp_.setRc(where_);
                errorsDet.add(unexp_);
                //string (class name) - row col - tag name
            }
        }
    }
    public void validateClassNames(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
            CustList<Block> bl_ = getSortedDescNodes(c.getValue());
            for (Block e: bl_) {
                Block b_ = (Block) e;
                for (EntryCust<String, String> n: b_.getClassNames().entryList()) {
                    String classNameLoc_ = n.getValue();
                    try {
//                        String base_ = PrimitiveTypeUtil.getComponentBaseType(classNameLoc_).getComponent();
                        String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(classNameLoc_).getComponent();
                        if (classesBodies.contains(new ClassName(base_, false))) {
                            continue;
                        }
                        
                        if (!StringList.quickEq(classNameLoc_, OperationNode.VOID_RETURN)) {
                            if (classNameLoc_.startsWith(PrimitiveTypeUtil.PRIM)) {
                                Class<?> cl_ = ConstClasses.getPrimitiveClass(classNameLoc_.substring(1));
                                if (cl_ == null) {
                                    throw new RuntimeClassNotFoundException(classNameLoc_);
                                }
                            } else {
                                classNameLoc_ = PrimitiveTypeUtil.getArrayClass(classNameLoc_);
                                ConstClasses.classAliasForNameNotInit(classNameLoc_);
                            }
                        }
                    } catch (RuntimeClassNotFoundException _0) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(classNameLoc_);
                        un_.setFileName(className_);
                        un_.setRc(b_.getRowCol(0, _context.getTabWidth(), n.getKey()));
                        errorsDet.add(un_);
//                        errors.add(c.getKey().getName());
                    }
                }
            }
        }
    }
    //validate method names
    public void validateFieldNames(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//                String xml_ = classesContent.getVal(className_);
//                ClassBlock clblock_ = (ClassBlock) c.getValue();
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock m_ = (InfoBlock) b;
                    String name_ = m_.getFieldName();
                    if (!StringList.isWord(name_)) {
                        RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
                        BadFieldName badMeth_ = new BadFieldName();
                        badMeth_.setFileName(className_);
                        badMeth_.setRc(r_);
                        badMeth_.setName(name_);
                        errorsDet.add(badMeth_);
//                            errors.add(c.getKey().getName());
                        //string (class name) - method name - row col - tag name
//                        } else if (!Character.isLetter(name_.charAt(CustList.FIRST_INDEX))) {
//                            RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
//                            BadFieldName badMeth_ = new BadFieldName();
//                            badMeth_.setFileName(className_);
//                            badMeth_.setRc(r_);
//                            badMeth_.setName(name_);
////                            errors.add(c.getKey().getName());
//                            errorsDet.add(badMeth_);
                    }
                }
            }
        }
    }
    //validate method id and validate parameters names duplicates
    public void validateFieldsId(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//                String xml_ = classesContent.getVal(className_);
            StringList ids_ = new StringList();
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    String name_ = method_.getFieldName();
                    for (String m: ids_) {
                        if (StringList.quickEq(m, name_)) {
                            RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                            DuplicateField duplicate_;
                            duplicate_ = new DuplicateField();
                            duplicate_.setRc(r_);
                            duplicate_.setFileName(className_);
                            duplicate_.setId(name_);
                            errorsDet.add(duplicate_);
                        }
                    }
                    ids_.add(name_);
                }
            }
        }
    }
    //validate method names
    public void validateMethodNames(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof MethodBlock) {
                    MethodBlock m_ = (MethodBlock) b;
                    String name_ = m_.getName();
                    if (!StringList.isWord(name_) || m_.isConcreteInstanceDerivableMethod() != m_.isNormalMethod()) {
                        RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
                        BadMethodName badMeth_ = new BadMethodName();
                        badMeth_.setFileName(className_);
                        badMeth_.setRc(r_);
                        badMeth_.setName(name_);
                        errorsDet.add(badMeth_);
//                        errors.add(c.getKey().getName());
                        //string (class name) - method name - row col - tag name
//                    } else if (!Character.isLetter(name_.charAt(CustList.FIRST_INDEX))) {
//                        RowCol r_ = m_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_NAME);
//                        BadMethodName badMeth_ = new BadMethodName();
//                        badMeth_.setFileName(className_);
//                        badMeth_.setRc(r_);
//                        badMeth_.setName(name_);
////                        errors.add(c.getKey().getName());
//                        errorsDet.add(badMeth_);
                    }
                }
            }
        }
    }
    //validate method id and validate parameters names duplicates
    public void validateMethodsId(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
            EqList<MethodId> ids_ = new EqList<MethodId>();
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof Returnable) {
                    Returnable method_ = (Returnable) b;
                    String name_ = method_.getName();
                    StringList types_ = method_.getParametersTypes();
                    int len_ = types_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = types_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
//                    if (len_ != method_.getParametersNames().size()) {
//                        errors.add(c.getKey().getName());
//                    }
                    MethodId id_ = new MethodId(name_, pTypes_);
                    for (MethodId m: ids_) {
                        if (m.eq(id_)) {
                            RowCol r_ = method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING);
                            DuplicateMethod duplicate_;
                            duplicate_ = new DuplicateMethod();
                            duplicate_.setRc(r_);
                            duplicate_.setFileName(className_);
                            duplicate_.setId(id_);
                            errorsDet.add(duplicate_);
//                            errors.add(c.getKey().getName());
                        }
                    }
                    StringList l_ = method_.getParametersNames();
                    if (l_.size() != len_) {
                        BadNumberArgMethod b_;
                        b_ = new BadNumberArgMethod();
                        b_.setFileName(className_);
                        b_.setRc(method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                        b_.setNbTypes(len_);
                        b_.setNbVars(l_.size());
                        b_.setId(id_);
                        errorsDet.add(b_);
//                        errors.add(c.getKey().getName());
                    }
                    StringList seen_ = new StringList();
                    int i_ = CustList.FIRST_INDEX;
                    for (String v: l_) {
                        String attr_ = ATTRIBUTE_VAR+i_;
                        if (!StringList.isWord(v)) {
                            BadParamName b_;
                            b_ = new BadParamName();
                            b_.setFileName(className_);
                            b_.setRc(method_.getRowCol(0, _context.getTabWidth(), attr_));
                            b_.setParamName(v);
                            errorsDet.add(b_);
//                            errors.add(c.getKey().getName());
                        } else if (seen_.containsStr(v)){
                            DuplicateParamName b_;
                            b_ = new DuplicateParamName();
                            b_.setFileName(className_);
                            b_.setRc(method_.getRowCol(0, _context.getTabWidth(), attr_));
                            b_.setParamName(v);
                            errorsDet.add(b_);
//                            errors.add(c.getKey().getName());
                        } else {
                            seen_.add(v);
                        }
                        i_++;
                    }
                    ids_.add(id_);
                }
            }
        }
    }
    public void validateOverridingInherit(ContextEl _context) {
        for (EntryCust<ClassName, Block> e: classesBodies.entryList()) {
            String className_ = e.getKey().getName();
            Block b_ = e.getValue();
            if (!(b_ instanceof EnumBlock)) {
                continue;
            }
            EnumBlock e_ = (EnumBlock) b_;
            CustList<Block> ch_ = getDirectChildren(e_);
            for (Block c: ch_) {
                if (!(c instanceof MethodBlock)) {
                    continue;
                }
                MethodBlock m_ = (MethodBlock) c;
                if (m_.getId().eq(new MethodId(OperationNode.METH_NAME, new EqList<ClassName>()))) {
                    ReservedMethod r_ = new ReservedMethod();
                    r_.setFileName(className_);
                    r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    r_.setMethodeId(m_.getId());
                    errorsDet.add(r_);
                }
                if (m_.getId().eq(new MethodId(OperationNode.METH_ORDINAL, new EqList<ClassName>()))) {
                    ReservedMethod r_ = new ReservedMethod();
                    r_.setFileName(className_);
                    r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    r_.setMethodeId(m_.getId());
                    errorsDet.add(r_);
                }
                if (m_.getId().eq(new MethodId(OperationNode.METH_VALUES, new EqList<ClassName>()))) {
                    ReservedMethod r_ = new ReservedMethod();
                    r_.setFileName(className_);
                    r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    r_.setMethodeId(m_.getId());
                    errorsDet.add(r_);
                }
                if (m_.getId().eq(new MethodId(OperationNode.METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(), false))))) {
                    ReservedMethod r_ = new ReservedMethod();
                    r_.setFileName(className_);
                    r_.setRc(m_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                    r_.setMethodeId(m_.getId());
                    errorsDet.add(r_);
                }
            }
        }
        for (String c: classesInheriting) {
            if (StringList.quickEq(c, Object.class.getName())) {
                continue;
            }
            RootedBlock dBl_ = (RootedBlock) classesBodies.getVal(new ClassName(c, false));
            StringList all_ = dBl_.getAllSuperClasses();
            StringList direct_ = dBl_.getDirectSuperClasses();
            all_.addAllElts(direct_);
            for (String b: direct_) {
                if (StringList.quickEq(b, Object.class.getName())) {
                    continue;
                }
                RootedBlock bBl_ = (RootedBlock) classesBodies.getVal(new ClassName(b, false));
                all_.addAllElts(bBl_.getAllSuperClasses());
            }
//            all_.addAllElts(direct_);
        }
        for (String c: classesInheriting) {
            if (StringList.quickEq(c, Object.class.getName())) {
                continue;
            }
            ClassName idType_ = new ClassName(c, false);
            ClassBlock bl_ = (ClassBlock) classesBodies.getVal(idType_);
            RootedBlock dBl_ = (RootedBlock) bl_;
            for (Block b: getDirectChildren(bl_)) {
                if (b instanceof MethodBlock) {
                    MethodBlock m_ = (MethodBlock) b;
                    MethodId mid_ = m_.getId();
                    dBl_.getNormalMethods().add(mid_);
                    bl_.getAvailableMethods().put(new ClassMethodId(idType_, mid_), true);
                }
            }
        }
        for (String c: classesInheriting) {
            if (StringList.quickEq(c, Object.class.getName())) {
                continue;
            }
            ClassName idBase_ = new ClassName(c, false);
            ClassBlock bl_ = (ClassBlock) classesBodies.getVal(idBase_);
            RootedBlock dBl_ = (RootedBlock) bl_;
//            for (Block b: TreeRetrieving.<Block>getDirectChildren(bl_)) {
//                if (b instanceof MethodBlock) {
//                    MethodBlock m_ = (MethodBlock) b;
//                    dBl_.getNormalMethods().add(m_.getId());
//                }
//            }
//            StringList all_ = dBl_.getDirectSuperClasses();
            StringList all_ = dBl_.getAllSuperClasses();
            ClassMetaInfo deriveClass_ = getClassMetaInfo(c);
            for (String s: all_) {
                if (StringList.quickEq(s, Object.class.getName())) {
                    continue;
                }
                ClassName idType_ = new ClassName(s, false);
                ClassBlock superCl_ = (ClassBlock) classesBodies.getVal(idType_);
//                RootedBlock bBl_ = (RootedBlock) superCl_;
                ClassMetaInfo baseClass_ = getClassMetaInfo(s);
                for (ClassMethodId m: superCl_.getAvailableMethods().getKeys()) {
                    boolean found_ = false;
                    MethodId m_ = m.getMethod();
                    for (ClassMethodId k: bl_.getAvailableMethods().getKeys()) {
                        if (k.getMethod().eq(m_)) {
                            found_ = true;
                            break;
                        }
                    }
                    if (found_) {
                        MethodMetaInfo derive_ = deriveClass_.getMethods().getVal(m_);
                        MethodMetaInfo base_ = baseClass_.getMethods().getVal(m_);
                        if (derive_ != null && base_ != null) {
                            if (derive_.getModifier() != MethodModifier.STATIC) {
                                if (base_.getModifier() == MethodModifier.STATIC) {
                                    MethodBlock mDer_ = getMethodBody(c, m_);
                                    StaticInstanceOverriding err_;
                                    err_ = new StaticInstanceOverriding();
                                    err_.setFileName(c);
                                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_MODIFIER));
                                    err_.setBaseClass(idBase_);
                                    err_.setMethodeId(m_);
                                    err_.setStaticBaseMethod(true);
                                    errorsDet.add(err_);
                                }
                            } else {
                                if (base_.getModifier() != MethodModifier.STATIC) {
                                    MethodBlock mDer_ = getMethodBody(c, m_);
                                    StaticInstanceOverriding err_;
                                    err_ = new StaticInstanceOverriding();
                                    err_.setFileName(c);
                                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_MODIFIER));
                                    err_.setBaseClass(idBase_);
                                    err_.setMethodeId(m_);
                                    err_.setStaticBaseMethod(false);
                                    errorsDet.add(err_);
                                }
                            }
                        }
                        if (derive_ != null && base_ != null && derive_.getModifier() != MethodModifier.STATIC) {
                            MethodBlock mDer_ = getMethodBody(c, m_);
                            ClassName retDerive_ = derive_.getReturnType();
                            ClassName retBase_ = base_.getReturnType();
                            BadReturnTypeInherit err_;
                            if (StringList.quickEq(retBase_.getName(), OperationNode.VOID_RETURN)) {
                                if (!StringList.quickEq(retDerive_.getName(), OperationNode.VOID_RETURN)) {
                                    err_ = new BadReturnTypeInherit();
                                    err_.setFileName(c);
                                    err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                                    err_.setReturnType(retDerive_.getName());
                                    err_.setMethod(m_);
                                    err_.setParentClass(s);
                                    errorsDet.add(err_);
                                    //throw ex
                                }
                            } else if (!PrimitiveTypeUtil.canBeUseAsArgument(retBase_, retDerive_, this)) {
                                //throw ex
                                err_ = new BadReturnTypeInherit();
                                err_.setFileName(c);
                                err_.setRc(mDer_.getAttributes().getVal(ATTRIBUTE_CLASS));
                                err_.setReturnType(retDerive_.getName());
                                err_.setMethod(m_);
                                err_.setParentClass(s);
                                errorsDet.add(err_);
                            }
//                            System.out.println("pair "+c+" with "+s);
                        }
//                        System.out.println(base_);
//                        System.out.println(m.getSignature());
//                        if (base_ == null) {
//                            System.out.println(c);
//                            System.out.println(s);
//                        }
//                        System.out.println(base_ == null || base_.getReturnType().eq(derive_.getReturnType()));
                    }
//                    bl_.getAvailableMethods().put(new ClassMethodId(idType_, m), !found_);
                    bl_.getAvailableMethods().put(m, !found_);
                }
            }
//            System.out.println();
//            System.out.println(c);
////            System.out.println(bl_.getAvailableMethods().size());
////            System.out.println(bl_.getNormalMethods().size());
//            for (ClassMethodId m: bl_.getAvailableMethods().getKeys()) {
//                System.out.println(m.getClassName());
//                System.out.println(m.getMethod().getSignature());
//                System.out.println(bl_.getAvailableMethods().getVal(m));
//            }
        }
    }
    //validate local variables names and loop variables names
    public void validateLocalVariableNamesId(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
            CustList<Block> bl_ = getSortedDescNodes(c.getValue());
            for (Block b: bl_) {
                Block block_ = (Block)b;
                if (b instanceof InitVariable) {
                    String var_ = ((InitVariable)b).getVariableName();
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(className_);
                        b_.setRc(block_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_VAR));
                        b_.setVarName(var_);
                        errorsDet.add(b_);
//                        errors.add(c.getKey().getName());
                    }
                    localVariablesNames.add(var_);
                }
                if (b instanceof ForLoop) {
                    String var_ = ((ForLoop)b).getVariableName();
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(className_);
                        b_.setRc(block_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_VAR));
                        b_.setVarName(var_);
                        errorsDet.add(b_);
//                        errors.add(c.getKey().getName());
                    }
                }
                if (b instanceof CatchEval) {
                    String var_ = ((CatchEval)b).getVariableName();
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(className_);
                        b_.setRc(block_.getRowCol(0, _context.getTabWidth(), ATTRIBUTE_VAR));
                        b_.setVarName(var_);
                        errorsDet.add(b_);
//                        errors.add(c.getKey().getName());
                    }
                }
            }
//            if (bl_ instanceof DeclareVariable) {
//                
//            }
        }
        localVariablesNames.removeDuplicates();
//        while (localVariablesNames.containsStr(TEMP_PREFIX+i_)) {
//            i_++;
//        }
//        int one_ = i_;
//        i_++;
//        while (localVariablesNames.containsStr(TEMP_PREFIX+i_)) {
//            i_++;
//        }
//        int two_ = i_;
//        String firstArg_ = TEMP_PREFIX+one_;
//        String secondArg_ = TEMP_PREFIX+two_;
//        String eq_ = StringList.simpleFormat(EQUALS_FORMAT, firstArg_, secondArg_);
//        equalsEl = new EqualsEl(eq_, firstArg_, secondArg_);
//        i_++;
        int i_ = CustList.FIRST_INDEX;
        while (localVariablesNames.containsStr(TEMP_PREFIX+i_)) {
            i_++;
        }
        int three_ = i_;
        i_++;
        while (localVariablesNames.containsStr(TEMP_PREFIX+i_)) {
            i_++;
        }
        int four_ = i_;
        String thrirdArg_ = TEMP_PREFIX+three_;
        String fourthArg_ = TEMP_PREFIX+four_;
        String eqt_ = StringList.simpleFormat(EQ_FORMAT, thrirdArg_, fourthArg_);
        eqEl = new EqualsEl(eqt_, thrirdArg_, fourthArg_);
        i_++;
        while (localVariablesNames.containsStr(TEMP_PREFIX+i_)) {
            i_++;
        }
        int five_ = i_;
        i_++;
        while (localVariablesNames.containsStr(TEMP_PREFIX+i_)) {
            i_++;
        }
        int six_ = i_;
        String fifthArg_ = TEMP_PREFIX+five_;
        String sixthArg_ = TEMP_PREFIX+six_;
        String nateqt_ = StringList.simpleFormat(NAT_EQ_FORMAT, fifthArg_, sixthArg_);
        natEqEl = new EqualsEl(nateqt_, fifthArg_, sixthArg_);
        page_.getLocalVars().put(fifthArg_, new LocalVariable());
        page_.getLocalVars().put(sixthArg_, new LocalVariable());
//        exps = ElUtil.getAnalyzedOperations(nateqt_, _context, true);
        exps = ElUtil.getAnalyzedOperations(nateqt_, _context, Calculation.staticCalculation(true));
        page_.getLocalVars().removeKey(fifthArg_);
        page_.getLocalVars().removeKey(sixthArg_);
    }

    public EqualsEl getEqEl() {
        return eqEl;
    }

    public EqualsEl getNatEqEl() {
        return natEqEl;
    }

    public ExpressionLanguage getEqNatEl() {
        return new ExpressionLanguage(exps);
    }

    //validate el and its possible returned type
    public void validateEl(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
//            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
//            page_.setHtml(xml_);
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof Returnable) {
                    Returnable method_ = (Returnable) b;
                    method_.checkBlocksTree(_context);
                }
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    method_.checkBlocksTree(_context);
                }
                if (b instanceof AloneBlock) {
                    AloneBlock method_ = (AloneBlock) b;
                    method_.checkBlocksTree(_context);
                }
            }
        }
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
//            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
//            page_.setHtml(xml_);
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    Argument argGl_ = new Argument();
                    argGl_.setArgClassName(c.getKey().getName());
                    page_.setGlobalArgument(argGl_);
                    page_.setGlobalClass(c.getKey().getName());
                    InfoBlock method_ = (InfoBlock) b;
                    method_.buildExpressionLanguage(_context);
                }
                if (b instanceof AloneBlock) {
                    Argument argGl_ = new Argument();
                    argGl_.setArgClassName(c.getKey().getName());
                    page_.setGlobalArgument(argGl_);
                    page_.setGlobalClass(c.getKey().getName());
                    AloneBlock method_ = (AloneBlock) b;
                    method_.buildInstructions(_context);
                }
//                if (b instanceof ConstructorBlock) {
//                    Argument argGl_ = new Argument();
//                    argGl_.setArgClassName(c.getKey().getName());
//                    page_.setGlobalArgument(argGl_);
//                    ConstructorBlock method_ = (ConstructorBlock) b;
//                    StringList params_ = method_.getParametersNames();
//                    StringList types_ = method_.getParametersTypes();
//                    int len_ = params_.size();
//                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                        String p_ = params_.get(i);
//                        String c_ = types_.get(i);
//                        LocalVariable lv_ = new LocalVariable();
//                        lv_.setClassName(c_);
//                        page_.getParameters().put(p_, lv_);
//                    }
////                    CustList<SortedNode<Block>> ch_;
////                    ch_ = TreeRetrieving.getSortedDescNodes(b);
////                    for (SortedNode<Block> d: ch_) {
////                        ((Block)d).setupChars(xml_);
////                    }
//                    method_.buildInstructions(_context);
//                    page_.getParameters().clear();
//                }
                if (b instanceof Returnable) {
                    Argument argGl_ = new Argument();
                    argGl_.setArgClassName(c.getKey().getName());
                    page_.setGlobalArgument(argGl_);
                    page_.setGlobalClass(c.getKey().getName());
                    Returnable method_ = (Returnable) b;
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getParametersTypes();
                    int len_ = params_.size();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String p_ = params_.get(i);
                        String c_ = types_.get(i);
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(c_);
                        page_.getParameters().put(p_, lv_);
                    }
//                    CustList<SortedNode<Block>> ch_;
//                    ch_ = TreeRetrieving.getSortedDescNodes(b);
//                    for (SortedNode<Block> d: ch_) {
//                        ((Block)d).setupChars(xml_);
//                    }
                    method_.buildInstructions(_context);
                    page_.getParameters().clear();
                }
            }
        }
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
//            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
//            page_.setHtml(xml_);
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    Argument argGl_ = new Argument();
                    argGl_.setArgClassName(c.getKey().getName());
                    page_.setGlobalArgument(argGl_);
                    page_.setGlobalClass(c.getKey().getName());
                    InfoBlock method_ = (InfoBlock) b;
                    method_.checkCallConstructor(_context);
                }
                if (b instanceof AloneBlock) {
                    Argument argGl_ = new Argument();
                    argGl_.setArgClassName(c.getKey().getName());
                    page_.setGlobalArgument(argGl_);
                    page_.setGlobalClass(c.getKey().getName());
                    AloneBlock method_ = (AloneBlock) b;
                    method_.checkConstrCalls(_context);
                }
                if (b instanceof Returnable) {
                    Argument argGl_ = new Argument();
                    argGl_.setArgClassName(c.getKey().getName());
                    page_.setGlobalArgument(argGl_);
                    page_.setGlobalClass(c.getKey().getName());
                    Returnable method_ = (Returnable) b;
                    method_.checkConstrCalls(_context);
                }
//                if (b instanceof ConstructorBlock) {
//                    Argument argGl_ = new Argument();
//                    argGl_.setArgClassName(c.getKey().getName());
//                    page_.setGlobalArgument(argGl_);
//                    ConstructorBlock method_ = (ConstructorBlock) b;
//                    method_.checkConstrCalls(_context);
//                }
            }
        }
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            RootedBlock clblock_ = (RootedBlock) c.getValue();
            clblock_.validateConstructors(_context);
        }
    }
    //validate missing return
    //validate break,continue ancestors / try/catch/finally / switch/case/default
    //validate dead code
    public void validateReturns(ContextEl _context) {
        PageEl page_ = new PageEl();
        _context.clearPages();
        _context.addPage(page_);
//        if (!classesBodies.isEmpty()) {
//            return;
//        }
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            String className_ = c.getKey().getName();
//            String xml_ = classesContent.getVal(className_);
//            page_.setHtml(xml_);
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof AloneBlock) {
                    CustList<Block> chSort_ = getSortedDescNodes(b);
                    CustList<Block> all_ = new CustList<Block>();
                    for (Block s: chSort_) {
                        all_.add((Block) s);
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
                                Block op_ = (Block) s;
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
                    String name_ = EMPTY_STRING;
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    MethodId id_ = new MethodId(name_, pTypes_);
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(id_);
                            errorsDet.add(deadCode_);
                        }
                    }
                }
                if (b instanceof Returnable) {
                    Returnable method_ = (Returnable) b;
                    CustList<Block> chSort_ = getSortedDescNodes(b);
                    CustList<Block> all_ = new CustList<Block>();
                    for (Block s: chSort_) {
                        all_.add((Block) s);
                    }
                    int order_ = 0;
                    while (true) {
                        CustList<Block> next_ = new CustList<Block>();
                        for (Block e: all_) {
                            if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                                continue;
                            }
//                            Block cur_ = e;
//                            boolean tonumber_ = true;
//                            while (cur_ != null) {
//                                int index_ = cur_.getIndexChild() - 1;
//                                if (index_ >= CustList.FIRST_INDEX) {
//                                    CustList<SortedNode<BlockGroup>> sn_ = TreeRetrieving.getDirectChildren(cur_.getParent());
//                                    SortedNode<BlockGroup> s_ = sn_.get(index_);
//                                    BlockGroup prev_ = (BlockGroup) s_;
//                                    if (prev_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
//                                        tonumber_ = false;
//                                        break;
//                                    }
//                                }
//                                cur_ = cur_.getParent();
//                            }
//                            if (!tonumber_) {
//                                continue;
//                            }
                            CustList<Block> list_ = getDirectChildren(e);
//                            if (!list_.isEmpty()) {
//                                Block op_ = (Block) list_.last();
//                                if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
//                                    continue;
//                                }
//                            }
                            boolean allNb_ = true;
                            for (Block s: list_) {
                                Block op_ = (Block) s;
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
//                    for (Block d: all_) {
//                        boolean deadCode_ = !!d.existDeadCodeInBlock();
//                    }
//                    for (Block d: all_) {
//                        boolean eq_ = d.isExitable() == d.isStoppable();
//                    }
                    Block r_ = all_.last();
                    String name_ = method_.getName();
                    StringList types_ = method_.getParametersTypes();
                    int len_ = types_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = types_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
                    MethodId id_ = new MethodId(name_, pTypes_);
                    if (!r_.isExitable() && !StringList.quickEq(method_.getReturnType(), OperationNode.VOID_RETURN)) {
                        MissingReturnMethod miss_ = new MissingReturnMethod();
                        miss_.setRc(method_.getRowCol(0, _context.getTabWidth(), EMPTY_STRING));
                        miss_.setFileName(className_);
                        miss_.setId(id_);
                        miss_.setReturning(method_.getReturnType());
                        errorsDet.add(miss_);
                    }
                    for (Block d: all_) {
                        RowCol rc_ = d.existDeadCodeInBlock(0, _context.getTabWidth());
                        if (rc_.getRow() > 0) {
                            DeadCodeMethod deadCode_ = new DeadCodeMethod();
                            deadCode_.setFileName(className_);
                            deadCode_.setRc(rc_);
                            deadCode_.setId(id_);
                            errorsDet.add(deadCode_);
                        }
                    }
                }
            }
        }
    }

    public StringList getLocalVariablesNames() {
        return localVariablesNames;
    }

    public RootedBlock getClassBody(String _className) {
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey().getName(), _className)) {
                continue;
            }
            return (RootedBlock) c.getValue();
        }
        return null;
    }
    public MethodBlock getMethodBody(String _className, MethodId _methodId) {
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey().getName(), _className)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof MethodBlock)) {
                    continue;
                }
                MethodBlock method_ = (MethodBlock) b;
                String m_ = method_.getName();
                StringList p_ = method_.getParametersTypes();
                int len_ = p_.size();
                EqList<ClassName> pTypes_ = new EqList<ClassName>();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    String n_ = p_.get(i);
                    pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                }
                if (new MethodId(m_, pTypes_).eq(_methodId)) {
                    return method_;
                }
            }
        }
        return null;
    }
    public ConstructorBlock getConstructorBody(String _className, ConstructorId _methodId) {
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            if (!StringList.quickEq(c.getKey().getName(), _className)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (!(b instanceof ConstructorBlock)) {
                    continue;
                }
                ConstructorBlock method_ = (ConstructorBlock) b;
                String m_ = _className;
                StringList p_ = method_.getParametersTypes();
                int len_ = p_.size();
                EqList<ClassName> pTypes_ = new EqList<ClassName>();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    String n_ = p_.get(i);
                    pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                }
                if (new ConstructorId(m_, pTypes_).eq(_methodId)) {
                    return method_;
                }
            }
        }
        return null;
    }
    public boolean isInitialized(String _name) {
        return initializedClasses.getVal(_name);
    }
    public boolean isSuccessfulInitialized(String _name) {
        return successfulInitializedClasses.getVal(_name);
    }
    public void initialize(String _name) {
        initializedClasses.put(_name, true);
    }
    public void successInitClass(String _name) {
        successfulInitializedClasses.put(_name, true);
    }
    public void preInitializeStaticFields(String _className) {
        StringMap<FieldBlock> fieldsInfos_;
        fieldsInfos_ = new StringMap<FieldBlock>();
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            ClassName k_ = c.getKey();
            if (!StringList.quickEq(k_.getName(), _className)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            for (Block b: bl_) {
                if (b instanceof FieldBlock) {
                    FieldBlock method_ = (FieldBlock) b;
                    String m_ = method_.getFieldName();
                    if (method_.isStaticField()) {
                        Struct str_ = method_.getDefaultStruct();
                        staticFields.put(new ClassField(_className, m_), str_);
                        continue;
                    }
                    fieldsInfos_.put(m_, method_);
                }
            }
        }
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            ClassName k_ = c.getKey();
            if (!StringList.quickEq(k_.getName(), _className)) {
                continue;
            }
            CustList<Block> bl_ = getDirectChildren(c.getValue());
            int i_ = CustList.FIRST_INDEX;
            CustList<Struct> values_ = new CustList<Struct>();
            for (Block b: bl_) {
                if (b instanceof ElementBlock) {
                    ElementBlock method_ = (ElementBlock) b;
                    String m_ = method_.getFieldName();
                    CustEnum enum_;
                    enum_ = new CustEnum(_className, m_, i_);
                    ObjectMap<ClassField,Struct> fields_;
                    fields_ = new ObjectMap<ClassField,Struct>();
                    for (EntryCust<String, FieldBlock> f: fieldsInfos_.entryList()) {
                        String f_ = f.getKey();
                        FieldBlock inf_ = f.getValue();
                        fields_.put(new ClassField(_className, f_), inf_.getDefaultStruct());
                    }
                    Struct str_;
                    str_ = new Struct(enum_, _className, fields_);
                    staticFields.put(new ClassField(_className, m_), str_);
                    values_.add(str_);
                    i_++;
                }
            }
            values.put(new ClassName(_className, false), values_);
        }
    }
    public void initializeStaticField(ClassField _clField, Struct _str) {
        staticFields.put(_clField, _str);
    }
    public Struct getStaticField(ClassField _clField) {
        return staticFields.getVal(_clField);
    }
    public ClassMetaInfo getClassMetaInfo(String _name) {
        for (EntryCust<ClassName, Block> c: classesBodies.entryList()) {
            ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
            infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
            StringMap<FieldMetaInfo> infosFields_;
            infosFields_ = new StringMap<FieldMetaInfo>();
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
            infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
            ClassName k_ = c.getKey();
            if (!StringList.quickEq(k_.getName(), _name)) {
                continue;
            }
            RootedBlock clblock_ = (RootedBlock) c.getValue();
            CustList<Block> bl_ = getDirectChildren((Block)clblock_);
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    String m_ = method_.getFieldName();
                    String ret_ = method_.getClassName();
                    ClassName clRet_ = new ClassName(ret_, false);
                    boolean enumElement_ = b instanceof ElementBlock;
                    FieldMetaInfo met_ = new FieldMetaInfo(k_, m_, clRet_, method_.isStaticField(), enumElement_);
                    infosFields_.put(m_, met_);
                }
                if (b instanceof MethodBlock) {
                    MethodBlock method_ = (MethodBlock) b;
                    String m_ = method_.getName();
                    StringList p_ = method_.getParametersTypes();
                    int len_ = p_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = p_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
                    MethodId id_ = new MethodId(m_, pTypes_);
                    String ret_ = method_.getReturnType();
                    ClassName clRet_ = new ClassName(ret_, false);
                    MethodMetaInfo met_ = new MethodMetaInfo(_name, method_.getModifier(), clRet_);
                    infos_.put(id_, met_);
                }
                if (b instanceof ConstructorBlock) {
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    String m_ = k_.getName();
                    StringList p_ = method_.getParametersTypes();
                    int len_ = p_.size();
                    EqList<ClassName> pTypes_ = new EqList<ClassName>();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        String n_ = p_.get(i);
                        pTypes_.add(new ClassName(n_, i + 1 == len_ && method_.isVarargs()));
                    }
                    ConstructorId id_ = new ConstructorId(m_, pTypes_);
                    String ret_ = OperationNode.VOID_RETURN;
                    ClassName clRet_ = new ClassName(ret_, false);
                    ConstructorMetaInfo met_ = new ConstructorMetaInfo(clRet_);
                    infosConst_.put(id_, met_);
                }
            }
            ClassCategory cat_ = ClassCategory.CLASS;
            if (clblock_ instanceof EnumBlock) {
                cat_ = ClassCategory.ENUM;
            }
            return new ClassMetaInfo(clblock_.getSuperClass(), infosFields_,infos_, infosConst_, cat_);
        }
        return null;
    }
}
