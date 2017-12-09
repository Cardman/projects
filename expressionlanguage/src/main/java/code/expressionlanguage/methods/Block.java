package code.expressionlanguage.methods;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.UnknownBlockException;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.methods.util.SearchingReturnThrow;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.xml.ElementOffsetsNext;
import code.xml.RowCol;
import code.xml.XmlParser;

public abstract class Block extends Blockable {
    public static final String EQ = "=";
    public static final String PLUS_EQ = "+=";
    public static final String MINUS_EQ = "-=";
    public static final String MULT_EQ = "*=";
    public static final String DIV_EQ = "/=";
    public static final String MOD_EQ = "%=";
    public static final String EQ_PLUS = "=+";
    public static final String EQ_MINUS = "=-";
    public static final String EQ_MULT = "=*";
    public static final String EQ_DIV = "=/";
    public static final String EQ_MOD = "=%";
    public static final String INCR = "++";
    public static final String DECR = "--";

    protected static final String ATTRIBUTE_PACKAGE = "package";
    protected static final String ATTRIBUTE_NAME = "name";
    protected static final String ATTRIBUTE_LEFT = "left";
    protected static final String ATTRIBUTE_OPER = "oper";
    protected static final String ATTRIBUTE_RIGHT = "right";
    protected static final String ATTRIBUTE_VAR = "var";
    protected static final String ATTRIBUTE_CLASS = "class";
    protected static final String ATTRIBUTE_CLASS_INDEX = "classindex";
    protected static final String ATTRIBUTE_CONDITION = "condition";
    protected static final String ATTRIBUTE_VALUE = "value";
    protected static final String ATTRIBUTE_STATIC = "static";
    protected static final String ATTRIBUTE_FINAL = "final";
    protected static final String ATTRIBUTE_EXPRESSION = "expression";
    protected static final String ATTRIBUTE_EQ = "eq";
    protected static final String ATTRIBUTE_INIT = "init";
    protected static final String ATTRIBUTE_STEP = "step";
    protected static final String ATTRIBUTE_MODIFIER = "modifier";
    protected static final String ATTRIBUTE_SUPER_CLASS = "superclass";
    protected static final String ATTRIBUTE_ACCESS = "access";
    protected static final String ATTRIBUTE_TEMPLATE_DEF = "template";
    protected static final String TAG_AFFECT = "affect";
    protected static final String TAG_BREAK = "break";
    protected static final String TAG_CASE = "case";
    protected static final String TAG_CATCH = "catch";
    protected static final String TAG_CLASS = "class";
    protected static final String TAG_ENUM = "enum";
    protected static final String TAG_CONSTRUCTOR = "constructor";
    protected static final String TAG_CONTINUE = "continue";
    protected static final String TAG_DECLARE = "declare";
    protected static final String TAG_DECLARE_SET = "declareset";
    protected static final String TAG_DEFAULT = "default";
    protected static final String TAG_DO = "do";
    protected static final String TAG_ELEMENT = "element";
    protected static final String TAG_ELSE = "else";
    protected static final String TAG_ELSEIF = "elseif";
    protected static final String TAG_FIELD = "field";
    protected static final String TAG_FINALLY = "finally";
    protected static final String TAG_FOR = "for";
    protected static final String TAG_FOREACH = "foreach";
    protected static final String TAG_IF = "if";
    protected static final String TAG_INSTANCE = "instance";
    protected static final String TAG_INTERFACE = "interface";
    protected static final String TAG_LINE = "line";
    protected static final String TAG_METHOD = "method";
    protected static final String TAG_RETURN = "return";
    protected static final String TAG_STATIC = "static";
    protected static final String TAG_SWITCH = "switch";
    protected static final String TAG_TRY = "try";
    protected static final String TAG_THROW = "throw";
    protected static final String TAG_WHILE = "while";

    protected static final String VALUE_ABSTRACT = "abstract";
    protected static final String VALUE_FINAL = "final";
    protected static final String VALUE_STATIC = "static";
    protected static final String VALUE_NORMAL = "normal";

    protected static final String VARARG = "...";

    protected static final String DOT = ".";

    protected static final String PAR_LEFT = "(";
    protected static final String PAR_RIGHT = ")";
    protected static final String RETURN_LINE = "\n";
    protected static final String EMPTY_STRING = "";
    protected static final String LT = "<";
    protected static final String GT = ">";
    protected static final String SEP_TMP = ",";

    private static final String TRUE_STRING = "true";

    private Element associateElement;

    private BracedBlock parent;

    private boolean initializedNextSibling;

    private ContextEl conf;

    private final int indexChild;
    private int indexGroup;

    private int indexInGroup;

    private boolean completeGroup;

    private SearchingReturnThrow searching;
    private Block nextSibling;

    private Block previousSibling;

    private StringMap<RowCol> attributes;

    private RowCol endHeader;

    private StringMap<Numbers<Integer>> offsets;

    private StringMap<Numbers<Integer>> tabs;

    private StringMap<NatTreeMap<Integer,Integer>> encoded;

    Block(Element _el, ContextEl _importingPage, int _indexChild, BracedBlock _m) {
        associateElement = _el;
        parent = _m;
        conf = _importingPage;
        indexChild = _indexChild;
        searching = new SearchingReturnThrow();
    }
    protected static void tryCheckBlocksTree(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            _cont.getLastPage().setCurrentBlock(_block);
            ((WithEl)_block).checkBlocksTree(_cont);
            return;
        }
        RowCol rc_ = _block.getAttributes().getVal(EMPTY_STRING);
        throw new UnknownBlockException(_block.getTagName(), rc_);
    }

    protected static void tryBuildExpressionLanguage(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            _cont.getLastPage().setCurrentBlock(_block);
            ((WithEl)_block).buildExpressionLanguage(_cont);
            return;
        }
        RowCol rc_ = _block.getAttributes().getVal(EMPTY_STRING);
        throw new UnknownBlockException(_block.getTagName(), rc_);
    }
    protected static void tryCheckConstCall(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            _cont.getLastPage().setCurrentBlock(_block);
            ((WithEl)_block).checkCallConstructor(_cont);
            return;
        }
        RowCol rc_ = _block.getAttributes().getVal(EMPTY_STRING);
        throw new UnknownBlockException(_block.getTagName(), rc_);
    }

    final void processBlock(ContextEl _conf) {
        ParentStackBlock parElt_ = getParentOfLastNode(this, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (parElt_ == null) {
            Block root_ = ip_.getBlockRoot();
            if (root_ instanceof RootBlock) {
                if (ip_.isInstancing()) {
                    CallConstructor call_;
                    call_ = ip_.getCallingConstr();
                    ConstructorBlock cstr_;
                    cstr_ = call_.getUsedConstructor();
                    if (call_.isInitializedFields()) {
                        ip_.exitFromConstructor();
                        return;
                    }
                    if (cstr_ != null) {
                        Block bl_ = cstr_.getFirstChild();
                        if (bl_ != null) {
                            call_.setInitializedFields(true);
                            ip_.getReadWrite().setBlock(bl_);
                            return;
                        }
                    }
                    ip_.exitFromConstructor();
                    return;
                }
                String curClass_ = ip_.getGlobalClass();
                String curClassBase_ = StringList.getAllTypes(curClass_).first();
                _conf.getClasses().successInitClass(curClassBase_);
                ip_.setNullReadWrite();
                return;
            }
            _conf.getLastPage().setReturnedArgument(PrimitiveTypeUtil.defaultValue(root_, _conf.getLastPage().getGlobalArgument()));
            ip_.setNullReadWrite();
            return;
        }
        BracedBlock par_ = parElt_.getElement();
        if (par_ == null) {
            ReadWrite rw_ = ip_.getReadWrite();
            Block n_ = null;
            n_ = getNextSibling();
            if (n_ != null) {
                rw_.setBlock(n_);
                return;
            }
            n_ = getParent();
            Block next_ = n_.getNextSibling();
            rw_.setBlock(next_);
            return;
        }
        par_.removeLocalVars(ip_);
        ((StackableBlockGroup)par_).exitStack(_conf);
    }
    static ParentStackBlock getParentOfLastNode(Block _current, ContextEl _conf) {
        Block n_ = _current.getNextSibling();
        if (n_ != null) {
            return new ParentStackBlock(null);
        }
        if (_current instanceof StackableBlock) {
            return _current.getParentOfLastNode(_conf);
        }
        return null;
    }
    final ParentStackBlock getParentOfLastNode(ContextEl _conf) {
        BracedBlock n_ = getParent();
        //n_ != null because strictly in class
        PageEl ip_ = _conf.getLastPage();
        Block root_ = ip_.getBlockRoot();
        if (ip_.isInstancing()) {
            CallConstructor call_;
            call_ = ip_.getCallingConstr();
            if (call_.isInitializedFields()) {
                root_ = call_.getUsedConstructor();
            }
        }
        if (n_ == root_) {
            //directly at the root => last element in the block root
            return null;
        }
        if (!ip_.noBlock()) {
            return new ParentStackBlock(n_);
        }
        Block next_ = n_.getNextSibling();
        if (next_ != null) {
            return new ParentStackBlock(null);
        }
        return null;
    }
    public final RowCol getRowCol(int _offset, int _tabWidth,String _attribute) {
        return XmlParser.getOffset(_attribute, attributes, encoded, _offset, offsets, tabs, endHeader, _tabWidth);
    }

    private static RowCol getRowColBeginElt(String _html, int _offset, int _tabWidth, Element _elt) {
        return XmlParser.getRowColOfNodeOrAttribute(_html, _elt, _offset, EMPTY_STRING, _tabWidth, false);
    }

    public final StringMap<NatTreeMap<Integer,Integer>> getEncoded() {
        return encoded;
    }
    public final StringMap<Numbers<Integer>> getTabs() {
        return tabs;
    }
    public final StringMap<Numbers<Integer>> getOffsets() {
        return offsets;
    }
    public final StringMap<RowCol> getAttributes() {
        return attributes;
    }
    public final RowCol getEndHeader() {
        return endHeader;
    }
    @Override
    boolean canCallSuperThis() {
        return false;
    }
    @Override
    boolean isAlwaysExitable() {
        return false;
    }
    public final FunctionBlock getFunction() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof FunctionBlock) {
                return (FunctionBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
    }
    public final RootBlock getRooted() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof RootBlock) {
                return (RootBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
    }

    public final void setAlwaysSkipped() {
        Block c_ = this;
        if (!(c_ instanceof WhileCondition)) {
            if (!(c_ instanceof DoBlock)) {
                return;
            }
        }
        if (c_ instanceof WhileCondition) {
            if (c_.getFirstChild() == null && c_.previousSibling instanceof DoBlock) {
                setAlwaysSkipped(c_.previousSibling.isAlwaysSkipped());
                return;
            }
            WhileCondition next_ = (WhileCondition) c_;
            if (!StringList.quickEq(next_.getCondition(), TRUE_STRING)) {
                setAlwaysSkipped(true);
                return;
            }
            while (true) {
                if (c_ == null) {
                    break;
                }
                if (c_ instanceof BreakBlock) {
                    BracedBlock b_ = c_.getParent();
                    boolean descSwitch_ = false;
                    while (b_ != this) {
                        if (b_ instanceof BreakableBlock) {
                            descSwitch_ = true;
                            break;
                        }
                        b_ = b_.getParent();
                    }
                    if (!descSwitch_) {
                        //direct
                        setAlwaysSkipped(true);
                        return;
                    }
                }
                Block n_ = c_.getFirstChild();
                if (n_ != null && !c_.isAlwaysSkipped()) {
                    c_ = n_;
                    continue;
                }
                n_ = c_.getNextSibling();
                if (n_ != null) {
                    c_ = n_;
                    continue;
                }
                n_ = c_.getParent();
                if (n_ != this) {
                    Block nextBl_ = n_.getNextSibling();
                    while (nextBl_ == null) {
                        Block par_ = n_.getParent();
                        if (par_ == this) {
                            break;
                        }
                        nextBl_ = par_.getNextSibling();
                        n_ = par_;
                    }
                    if (nextBl_ != null) {
                        c_ = nextBl_;
                        continue;
                    }
                }
                break;
            }
            return;
        }
        WhileCondition next_ = (WhileCondition) c_.getNextSibling();
        boolean lookForContinue_ = false;
        if (!StringList.quickEq(next_.getCondition(), TRUE_STRING)) {
            lookForContinue_ = true;
        }
        while (true) {
            if (c_ == null) {
                break;
            }
            if (lookForContinue_ && c_ instanceof ContinueBlock) {
                //direct
                BracedBlock b_ = c_.getParent();
                boolean descLoop_ = false;
                while (b_ != this) {
                    if (b_ instanceof Loop) {
                        descLoop_ = true;
                        break;
                    }
                    b_ = b_.getParent();
                }
                if (!descLoop_) {
                    //direct
                    setAlwaysSkipped(true);
                    return;
                }
            }
            if (c_ instanceof BreakBlock) {
                BracedBlock b_ = c_.getParent();
                boolean descSwitch_ = false;
                while (b_ != this) {
                    if (b_ instanceof BreakableBlock) {
                        descSwitch_ = true;
                        break;
                    }
                    b_ = b_.getParent();
                }
                if (!descSwitch_) {
                    //direct
                    setAlwaysSkipped(true);
                    return;
                }
            }
            Block n_ = c_.getFirstChild();
            if (n_ != null && !c_.isAlwaysSkipped()) {
                c_ = n_;
                continue;
            }
            n_ = c_.getNextSibling();
            if (n_ != null) {
                c_ = n_;
                continue;
            }
            n_ = c_.getParent();
            if (n_ != this) {
                Block nextBl_ = n_.getNextSibling();
                while (nextBl_ == null) {
                    Block par_ = n_.getParent();
                    if (par_ == this) {
                        break;
                    }
                    nextBl_ = par_.getNextSibling();
                    n_ = par_;
                }
                if (nextBl_ != null) {
                    c_ = nextBl_;
                    continue;
                }
            }
            break;
        }
    }
    final boolean allStoppableGroup() {
        if (!completeGroup) {
            return false;
        }
        if (isAlwaysSkipped()) {
            return false;
        }
        Block par_ = getParent();
        CustList<Block> ch_ = Classes.getDirectChildren(par_);
        for (Block s: ch_) {
            Block c_ = s;
            if (c_ instanceof FinallyEval) {
                continue;
            }
            if (c_.indexGroup != indexGroup) {
                continue;
            }
            if (!c_.isStoppable()) {
                return false;
            }
        }
        return true;
    }
    public final void setStoppable() {
        if (isStoppable()) {
            return;
        }
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return;
        }
        Block b_ = ch_.last();
        if (b_.isStoppable() && b_.getFirstChild() == null) {
            setStoppable(true);
            return;
        }
        int lastIndexFoundGr_ = CustList.INDEX_NOT_FOUND_ELT;
        for (int i = ch_.size() - 1; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = ch_.get(i);
            if (c_ instanceof FinallyEval) {
                continue;
            }
            if (c_.completeGroup) {
                lastIndexFoundGr_ = i;
                break;
            }
        }
        if (lastIndexFoundGr_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        int indexGr_ = ch_.get(lastIndexFoundGr_).indexGroup;
        boolean all_ = true;
        for (int i = lastIndexFoundGr_; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = ch_.get(i);
            if (c_.indexGroup != indexGr_) {
                break;
            }
            if (c_.isStoppable()) {
                continue;
            }
            all_ = false;
            break;
        }
        if (all_) {
            setStoppable(true);
        }
    }
    public final RowCol existDeadCodeInBlock(int _offset, int _tabWidth) {
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return new RowCol();
        }
        int len_ = ch_.size();
        boolean stoppableBlock_ = false;
        int indexGr_ = ch_.first().indexGroup;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Block b_ = ch_.get(i);
            if (b_.indexGroup != indexGr_ && stoppableBlock_) {
                return b_.getRowCol(_offset, _tabWidth, EMPTY_STRING);
            }
            if (b_.allStoppableGroup()) {
                stoppableBlock_ = true;
            }
            indexGr_ = b_.indexGroup;
        }
        return new RowCol();
    }
    public final void setExitable() {
        if (isExitable()) {
            return;
        }
        BracedBlock par_ = getParent();
        while (par_ != null) {
            if (par_.isAlwaysSkipped()) {
                return;
            }
            par_ = par_.getParent();
        }
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return;
        }
        Block b_ = ch_.last();
        if (b_.isExitable() && b_.getFirstChild() == null) {
            setExitable(true);
            return;
        }
        int lastIndexFoundGr_ = CustList.INDEX_NOT_FOUND_ELT;
        for (int i = ch_.size() - 1; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = ch_.get(i);
            if (c_ instanceof FinallyEval) {
                continue;
            }
            if (c_.completeGroup) {
                lastIndexFoundGr_ = i;
                break;
            }
        }
        if (lastIndexFoundGr_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        int indexGr_ = ch_.get(lastIndexFoundGr_).indexGroup;
        boolean all_ = true;
        for (int i = lastIndexFoundGr_; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = ch_.get(i);
            if (c_.indexGroup != indexGr_) {
                break;
            }
            if (c_.isExitable()) {
                continue;
            }
            all_ = false;
            break;
        }
        if (all_) {
            setExitable(true);
        }
    }
    public final void setOrder(int _order) {
        searching.setOrder(_order);
    }
    public final int getOrder() {
        return searching.getOrder();
    }
    final SearchingReturnThrow getSearching() {
        return searching;
    }
    public final void setCompleteGroup() {
        if (!canBeLastOfBlockGroup()) {
            return;
        }
        Block next_ = nextSibling;
        if (next_ != null && next_.canBeLastOfBlockGroup()) {
            return;
        }
        Block bl_ = this;
        if (bl_.getFirstChild() == null) {
            if (!(bl_ instanceof WhileCondition)) {
                return;
            }
        }
        int index_ = bl_.indexGroup;
        while (bl_ != null) {
            if (bl_.indexGroup != index_) {
                break;
            }
            bl_.completeGroup = true;
            bl_ = bl_.previousSibling;
        }
    }
    public final void setupChars(String _html) {
        encoded = XmlParser.getSpecialChars(_html, associateElement);
    }

    protected final void removeLocalVariablesFromParent(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        parent.removeLocalVars(page_);
        if (parent instanceof ForLoop) {
            String var_ = ((ForLoop)parent).getVariableName();
            page_.getVars().removeKey(var_);
        }
        if (parent instanceof CatchEval) {
            String var_ = ((CatchEval)parent).getVariableName();
            page_.getCatchVars().removeKey(var_);
        }
    }

    public abstract String getTagName();
    abstract boolean canBeIncrementedNextGroup();
    abstract boolean canBeIncrementedCurGroup();
    abstract boolean canBeLastOfBlockGroup();
    public final int getIndexGroup() {
        return indexGroup;
    }
    public final int getIndexInGroup() {
        return indexInGroup;
    }

    public abstract NatTreeMap<String,String> getClassNames();
    public static Block createOperationNode(Element _el, ContextEl _conf,
            int _indexChild, BracedBlock _m) {
        if (StringList.quickEq(_el.getNodeName(),TAG_AFFECT)) {
            if (!_el.hasAttribute(ATTRIBUTE_RIGHT)) {
                return new SemiAffectation(_el, _conf, _indexChild, _m);
            }
            return new Affectation(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_BREAK)) {
            return new BreakBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_CASE)) {
            return new CaseCondition(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_CATCH)) {
            return new CatchEval(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_CLASS)) {
            return new ClassBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_CONSTRUCTOR)) {
            return new ConstructorBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_CONTINUE)) {
            return new ContinueBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_DECLARE)) {
            return new DeclareVariable(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_DECLARE_SET)) {
            return new DeclareAffectVariable(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_DEFAULT)) {
            return new DefaultCondition(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_DO)) {
            return new DoBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_ELEMENT)) {
            return new ElementBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_ELSE)) {
            return new ElseCondition(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_ELSEIF)) {
            return new ElseIfCondition(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_ENUM)) {
            return new EnumBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_FIELD)) {
            return new FieldBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_FINALLY)) {
            return new FinallyEval(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_FOR)) {
            return new ForIterativeLoop(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_FOREACH)) {
            return new ForEachLoop(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_IF)) {
            return new IfCondition(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_INSTANCE)) {
            return new InstanceBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_INTERFACE)) {
            return new InterfaceBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_LINE)) {
            return new Line(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_METHOD)) {
            return new MethodBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_RETURN)) {
            return new ReturnMehod(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_STATIC)) {
            return new StaticBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_SWITCH)) {
            return new SwitchBlock(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_THROW)) {
            return new Throwing(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_TRY)) {
            return new TryEval(_el, _conf, _indexChild, _m);
        }
        if (StringList.quickEq(_el.getNodeName(),TAG_WHILE)) {
            return new WhileCondition(_el, _conf, _indexChild, _m);
        }
        RowCol rc_ = getRowColBeginElt(_conf.getHtml(), 0, _conf.getTabWidth(), _el);
        throw new UnknownBlockException(_el.getNodeName(), rc_);
    }
    protected final ContextEl getConf() {
        return conf;
    }
    public final void setConf(ContextEl _conf) {
        conf = _conf;
    }
    public final Element getAssociateElement() {
        return associateElement;
    }
    public final void setNullAssociateElement() {
        associateElement = null;
    }
    protected final Block getPreviousSibling() {
        return previousSibling;
    }
    public abstract Block getFirstChild();

    public final Block getNextSibling() {
        if (initializedNextSibling) {
            return nextSibling;
        }
        initializedNextSibling = true;
        BracedBlock p_ = getParent();
        if (p_ == null) {
            return null;
        }
        Node n_ = associateElement.getNextSibling();
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
        nextSibling = createOperationNode(next_, conf, indexChild + 1, p_);
        if (canBeIncrementedCurGroup() && nextSibling.canBeIncrementedNextGroup()) {
            nextSibling.indexGroup = indexGroup;
            nextSibling.indexInGroup = indexInGroup + 1;
        } else {
            nextSibling.indexGroup = indexGroup+1;
        }
        String html_ = conf.getHtml();
        int tabWidth_ = conf.getTabWidth();
        ElementOffsetsNext e_ = conf.getElements();
        ElementOffsetsNext ne_ = XmlParser.getIndexesOfElementOrAttribute(html_, e_, next_, tabWidth_);
        nextSibling.attributes = ne_.getAttributes();
        nextSibling.endHeader = ne_.getEndHeader();
        nextSibling.tabs = ne_.getTabs();
        nextSibling.offsets = ne_.getOffsets();
        conf.setElements(ne_);
        nextSibling.previousSibling = this;
        return nextSibling;
    }
    final void setAttributes(StringMap<RowCol> _attributes) {
        attributes = _attributes;
    }
    final void setEndHeader(RowCol _endHeader) {
        endHeader = _endHeader;
    }
    final void setOffsets(StringMap<Numbers<Integer>> _offsets) {
        offsets = _offsets;
    }
    final void setTabs(StringMap<Numbers<Integer>> _tabs) {
        tabs = _tabs;
    }

    public final BracedBlock getParent() {
        return parent;
    }

    public final boolean hasChildNodes() {
        return getFirstChild() != null;
    }
    public final boolean isAlwaysSkipped() {
        return searching.isAlwaysSkipped();
    }
    public final boolean isExitable() {
        return searching.isExitable() || isAlwaysExitable();
    }

    public final boolean isStoppable() {
        return searching.isStoppable();
    }
    final void setAlwaysSkipped(boolean _b) {
        searching.setAlwaysSkipped(_b);
    }
    final void setExitable(boolean _b) {
        searching.setExitable(_b);
    }

    final void setStoppable(boolean _b) {
        searching.setStoppable(_b);
    }
}
