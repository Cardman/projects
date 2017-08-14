package code.expressionlanguage.methods;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.UnknownBlockException;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.methods.util.SearchingReturnThrow;
import code.expressionlanguage.stacks.IfBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.SortedNode;
import code.xml.ElementOffsetsNext;
import code.xml.RowCol;
import code.xml.XmlParser;

public abstract class Block extends Blockable implements SortedNode<Block> {
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

//    private Map<NodeAttribute, TreeMap<Integer, Integer>> encoded;
    private StringMap<RowCol> attributes;

    private RowCol endHeader;

    private StringMap<Numbers<Integer>> offsets;

    private StringMap<Numbers<Integer>> tabs;

//    private int indexBegin;

    private StringMap<NatTreeMap<Integer,Integer>> encoded;

    Block(Element _el, ContextEl _importingPage, int _indexChild, BracedBlock _m) {
        associateElement = _el;
        parent = _m;
        conf = _importingPage;
        indexChild = _indexChild;
        searching = new SearchingReturnThrow();
    }

//    public abstract void buildExpressionLanguage(ContextEl _cont);
    
    protected static void tryCheckBlocksTree(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            _cont.getLastPage().setCurrentBlock(_block);
            ((WithEl)_block).checkBlocksTree(_cont);
            return;
        }
//        Element elt_ = _block.getAssociateElement();
//        RowCol rc_ = _block.getRowCol(_cont.getHtml(), 0, _cont.getTabWidth(), EMPTY_STRING);
        RowCol rc_ = _block.getAttributes().getVal(EMPTY_STRING);
        throw new UnknownBlockException(_block.getTagName(), rc_);
    }

    protected static void tryBuildExpressionLanguage(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            _cont.getLastPage().setCurrentBlock(_block);
            ((WithEl)_block).buildExpressionLanguage(_cont);
            return;
        }
//        Element elt_ = _block.getAssociateElement();
//        RowCol rc_ = _block.getRowCol(_cont.getHtml(), 0, _cont.getTabWidth(), EMPTY_STRING);
        RowCol rc_ = _block.getAttributes().getVal(EMPTY_STRING);
        throw new UnknownBlockException(_block.getTagName(), rc_);
    }
    protected static void tryCheckConstCall(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            _cont.getLastPage().setCurrentBlock(_block);
            ((WithEl)_block).checkCallConstructor(_cont);
            return;
        }
//        Element elt_ = _block.getAssociateElement();
//        RowCol rc_ = _block.getRowCol(_cont.getHtml(), 0, _cont.getTabWidth(), EMPTY_STRING);
        RowCol rc_ = _block.getAttributes().getVal(EMPTY_STRING);
        throw new UnknownBlockException(_block.getTagName(), rc_);
    }
//    final void processAfterBlock(ContextEl _conf, PageEl _ip) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        if (getFirstChild() != null) {
//            rw_.setBlock(getFirstChild());
//        } else {
//            processBlock(_conf, _ip);
//        }
//    }
    final void processBlock(ContextEl _conf) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        Block en_ = rw_.getBlock();
//        ParentStackBlock parElt_ = getParentOfLastNode(_conf);
        ParentStackBlock parElt_ = getParentOfLastNode(this, _conf);
        //, false
        PageEl ip_ = _conf.getLastPage();
        if (parElt_ == null) {
            Block root_ = ip_.getBlockRoot();
            if (root_ instanceof RootedBlock) {
                if (ip_.isInstancing()) {
                    CallConstructor call_;
                    call_ = ip_.getCallingConstr();
                    ConstructorBlock cstr_;
                    cstr_ = call_.getUsedConstructor();
                    if (call_.isInitializedFields()) {
                        ip_.exitFromConstructor();
                        return;
                    }
//                    System.out.println(ip_.getCallingConstr().isFirstField());
                    if (cstr_ != null) {
                        Block bl_ = cstr_.getFirstChild();
//                        if (cstr_.hasFirstBlockAfterSuperConstr())
                        if (bl_ != null) {
                            call_.setInitializedFields(true);
                            ip_.getReadWrite().setBlock(bl_);
                            return;
                        }
//                        Block first_;
//                        first_ = cstr_.getFirstBlockAfterOtherConstr();
////                        if (first_ != null)
//                        if (cstr_.hasFirstBlockAfterSuperConstr()) {
//                            call_.setInitializedFields(true);
//                            ip_.getReadWrite().setBlock(first_);
//                            return;
//                        }
                    }
                    ip_.exitFromConstructor();
                    return;
                }
//                String curClass_ = ip_.getGlobalArgument().getArgClassName();
                String curClass_ = ip_.getGlobalClass();
                _conf.getClasses().successInitClass(curClass_);
                ip_.setNullReadWrite();
                return;
            }
            Argument void_ = Argument.createVoid();
            _conf.getLastPage().setReturnedArgument(void_);
            ip_.setNullReadWrite();
            return;
        }
        BracedBlock par_ = parElt_.getElement();
        if (par_ == null) {
            Block na_ = getNextNodeWrite(_conf);
            //, false
            ReadWrite rw_ = ip_.getReadWrite();
            rw_.setBlock(na_);
            return;
        }
//        ProcessXmlMethod.removeLocalVars(_ip, par_);
        par_.removeLocalVars(ip_);
        ((StackableBlockGroup)par_).exitStack(_conf);
//        if (isCatchNode(par_)) {
//            TryBlockStack tryStack_ = (TryBlockStack) _ip.getLastStack();
//            CatchEval catch_ = (CatchEval) tryStack_.getCurrentCatchBlock();
//            String var_ = catch_.getVariableName();
//            Map<String,LocalVariable> vars_ = _ip.getCatchVars();
//            vars_.removeKey(var_);
//            rw_.setBlock(catch_);
//            return;
//        }
//        if (isFinallyNode(par_)) {
//            ProcessXmlMethod.interruptAfterFinally(_ip);
//            if (_ip.isReturning()) {
////                _ip.getBlockStacks().removeLast();
//                _ip.removeLastBlock();
//                ProcessXmlMethod.removeBlockFinally(_conf, _ip);
//                FunctionBlock f_ = getFunction();
//                if (f_ instanceof StaticBlock) {
//                    if (_ip.getReadWrite() == null) {
//                        Block bn_ = ((StaticBlock)f_).getNextSibling();
//                        if (bn_ != null) {
//                            _ip.setReturning(false);
//                            rw_.setBlock(bn_);
//                            _ip.setReadWrite(rw_);
//                        }
//                    }
//                }
//                return;
//            }
////            TryBlockStack tryStack_ = (TryBlockStack) _ip.getBlockStacks().last();
//            TryBlockStack tryStack_ = (TryBlockStack) _ip.getLastStack();
//            FinallyEval catch_ = (FinallyEval) tryStack_.getCurrentCatchBlock();
//            tryStack_.setVisitedFinally(true);
//            rw_.setBlock(catch_);
//            return;
//        }
//        if (isLoopNode(par_)) {
//            ProcessXmlMethod.processLastElementLoop(_conf, _ip);
//        } else {
//            if (isTryNode(par_)) {
//                rw_.setBlock(par_.getNextSibling());
//                return;
//            }
//            if (isInstructionNode(par_)) {
////                IfBlockStack if_ = (IfBlockStack) _ip.getBlockStacks().last();
//                IfBlockStack if_ = (IfBlockStack) _ip.getLastStack();
//                if (if_.lastVisitedBlock() == par_) {
//                    rw_.setBlock(par_);
//                } else {
//                    if_.setVisitedBlock(if_.getVisitedBlock()+1);
//                    rw_.setBlock(par_.getNextSibling());
//                }
//                return;
//            }
//            if (isSwitchNode(par_)) {
////                SwitchBlockStack if_ = (SwitchBlockStack) _ip.getBlockStacks().last();
//                SwitchBlockStack if_ = (SwitchBlockStack) _ip.getLastStack();
//                if (if_.lastVisitedBlock() == par_) {
//                    if_.setFinished(true);
//                    rw_.setBlock(if_.getBlock());
//                } else {
//                    if_.setVisitedBlock(if_.getVisitedBlock()+1);
//                    rw_.setBlock(par_.getNextSibling());
//                }
//                return;
//            }
//        }
    }
    static ParentStackBlock getParentOfLastNode(Block _current, ContextEl _conf) {
        if (_current instanceof StackableBlock) {
//            if (!(_current instanceof StackableBlock)) {
//                System.out.println(_current.getClass());
//            }
            return _current.getParentOfLastNode(_conf);
        }
        Block n_ = _current.getNextSibling();
        if (n_ != null) {
            return new ParentStackBlock(null);
        }
        return null;
    }
    final ParentStackBlock getParentOfLastNode(ContextEl _conf) {
        //, boolean _child
//        Block n_ = _node.getFirstChild();
//        if (n_ != null && _child) {
//            return new ParentStackBlock(null);
//        }
        Block n_ = getNextSibling();
        if (n_ != null) {
            return new ParentStackBlock(null);
        }
        n_ = getParent();
        //n_ != null because strictly in class
        PageEl ip_ = _conf.getLastPage();
        Block root_ = ip_.getBlockRoot();
        if (ip_.isInstancing()) {
            CallConstructor call_;
            call_ = ip_.getCallingConstr();
            if (call_.isInitializedFields()) {
//                ConstructorBlock constr_;
                root_ = call_.getUsedConstructor();
//                if (constr_ != null) {
//                    root_ = constr_;
//                }
            }
        }
        if (n_ == root_) {
            //directly at the root => last element in the block root
            return null;
        }
//        if (n_ instanceof RootedBlock) {
//            return null;
//        }
        BracedBlock b_ = null;
//        CustList<BlockStack> l_ = ip_.getBlockStacks();
        if (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            b_ = bl_.getBlock();
            if (bl_ instanceof TryBlockStack) {
                TryBlockStack t_ = (TryBlockStack)bl_;
                int vis_ = t_.getVisitedCatch();
                if (vis_ > CustList.INDEX_NOT_FOUND_ELT) {
                    b_ = t_.getCatchBlocks().get(vis_);
                }
            } else if (bl_ instanceof IfBlockStack) {
                IfBlockStack t_ = (IfBlockStack)bl_;
                b_ = t_.getCurentVisitedBlock();
            } else if (bl_ instanceof SwitchBlockStack) {
                SwitchBlockStack t_ = (SwitchBlockStack)bl_;
//                if (t_.getVisitedBlock() > CustList.INDEX_NOT_FOUND_ELT) {
//                    b_ = t_.getCurentVisitedBlock();
//                }
                b_ = t_.getCurentVisitedBlock();
            }
        }
//        if (b_ == null) {
//            return null;
//        }
        if (b_ == n_) {
            //n_ != null => b_ != null
            return new ParentStackBlock(b_);
        }
        Block next_ = n_.getNextSibling();
//        while (next_ == null) {
//            Block par_ = n_.getParent();
//            if (par_ == root_) {
//                break;
//            }
//            if (b_ == par_) {
//                //par_ != null => b_ != null
//                return new ParentStackBlock(b_);
//            }
//            next_ = par_.getNextSibling();
//            n_ = par_;
//        }
        if (next_ != null) {
            return new ParentStackBlock(null);
        }
        //n_ is instance of Document
        // so _node is last element to treat in the document
        return null;
    }
    final Block getNextNodeWrite(ContextEl _conf) {
        //, boolean _firstChild
        Block n_ = null;
//        if (_firstChild) {
//            n_ = _node.getFirstChild();
//            if (n_ != null) {
//                return n_;
//            }
//        }
        n_ = getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = getParent();
//        PageEl ip_ = _conf.getLastPage();
//        Block root_ = ip_.getBlockRoot();
//        if (ip_.isInstancing()) {
//            CallConstructor call_;
//            call_ = ip_.getCallingConstr();
//            if (call_.isInitializedFields()) {
////                ConstructorBlock constr_;
//                root_ = call_.getUsedConstructor();
////                if (constr_ != null) {
////                    root_ = constr_;
////                }
//            }
//        }
        Block next_ = n_.getNextSibling();
//        while (next_ == null) {
//            Block par_ = n_.getParent();
//            if (par_ == root_) {
//                break;
//            }
////            if (par_ instanceof RootedBlock) {
////                break;
////            }
//            next_ = par_.getNextSibling();
//            n_ = par_;
//        }
        //        if (next_ == null) {
            //            return null;
            //        }
        return next_;
    }
    public final RowCol getRowCol(int _offset, int _tabWidth,String _attribute) {
//        int delta_ = 0;
//        TreeMap<Integer, Integer> esc_ = getEscapedChars(_attribute);
//        if (esc_ != null) {
//            int nbIndexes_ = getIndexesCount(esc_,_offset);
//            for (int i = 0; i < nbIndexes_; i++) {
//                delta_ += esc_.getValue(i);
//            }
//        }
        return XmlParser.getOffset(_attribute, attributes, encoded, _offset, offsets, tabs, endHeader, _tabWidth);
//        if (_attribute.isEmpty()) {
//            return XmlParser.getOffset(_attribute, attributes, encoded, _offset, offsets, tabs, _tabWidth);
////            return XmlParser.getRowColOfNodeOrAttribute(_html, associateElement, delta_+_offset, _attribute, _tabWidth, false);
//        }
//        return XmlParser.getRowColOfNodeOrAttribute(_html, associateElement, delta_+_offset, _attribute, _tabWidth, true);
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
    public final RootedBlock getRooted() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof RootedBlock) {
                return (RootedBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
    }
//    public abstract void checkCallConstructor(ContextEl _cont);
//    void checkCallConstructor() {
//        if (!canCallSuperThis()) {

//        }
//    }
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
//            searching.setAlwaysSkipped(true);
//            return;
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
            Block c_ = (Block) s;
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
//        if (!getSearching().isAlwaysSkipped()) {
//            return;
//        }
        Block b_ = (Block) ch_.last();
        if (b_.isStoppable() && b_.getFirstChild() == null) {
            setStoppable(true);
            return;
        }
        int lastIndexFoundGr_ = CustList.INDEX_NOT_FOUND_ELT;
        for (int i = ch_.size() - 1; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = (Block) ch_.get(i);
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
        int indexGr_ = ((Block) ch_.get(lastIndexFoundGr_)).indexGroup;
        boolean all_ = true;
        for (int i = lastIndexFoundGr_; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = (Block) ch_.get(i);
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
//        if (searching.isStoppable()) {
//            System.out.println(getClass());
//            return new RowCol();
//        }
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return new RowCol();
        }
        int len_ = ch_.size();
        boolean stoppableBlock_ = false;
        int indexGr_ = ((Block) ch_.first()).indexGroup;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Block b_ = (Block) ch_.get(i);
            if (b_.indexGroup != indexGr_ && stoppableBlock_) {
//                System.out.println("f:"+ch_.first().getClass());
//                System.out.println();
//                System.out.println(getClass());
//                System.out.println(b_.getClass());
//                System.out.println(len_);
//                System.out.println(getAssociateElement().getAttribute("name"));
                return b_.getRowCol(_offset, _tabWidth, EMPTY_STRING);
            }
//            if (allStoppableGroup()) {
//                stoppableBlock_ = true;
//            }
//            if (b_.isStoppable() && !b_.isAlwaysSkipped()) {
            if (b_.allStoppableGroup()) {
                stoppableBlock_ = true;
            }
//            }
//            if (b_.isStoppable() && !b_.isAlwaysSkipped()) {
//                if (b_.completeGroup) {
////                    System.out.println(b_.getTagName());
//                    stoppableBlock_ = true;
////                    System.out.println("c:"+b_.getClass());
//                }
//            } else {
//                stoppableBlock_ = false;
//            }
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
        Block b_ = (Block) ch_.last();
        if (b_.isExitable() && b_.getFirstChild() == null) {
            setExitable(true);
            return;
        }
        int lastIndexFoundGr_ = CustList.INDEX_NOT_FOUND_ELT;
        for (int i = ch_.size() - 1; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = (Block) ch_.get(i);
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
        int indexGr_ = ((Block) ch_.get(lastIndexFoundGr_)).indexGroup;
        boolean all_ = true;
        for (int i = lastIndexFoundGr_; i >= CustList.FIRST_INDEX; i--) {
            Block c_ = (Block) ch_.get(i);
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
//        encoded = new Map<String, TreeMap<Integer, Integer>>();
//        String html_ = _html;
////        int index_ = XmlParser.getIndexOfNodeOrAttribute(html_, associateElement, EMPTY_STRING);
//        int endHeader_ = html_.indexOf(GT, indexBegin);
//        int beginHeader_ = indexBegin + associateElement.getNodeName().length();
//        Map<String,AttributePart> attr_;
//        attr_ = MethodUtil.getAttributes(html_, beginHeader_, endHeader_);
//        for (EntryCust<String, AttributePart> e: attr_.entryList()) {
////            NodeAttribute nodeAttr_ = new NodeAttribute();
////            nodeAttr_.setNode(associateElement);
////            nodeAttr_.setAttribue(e.getKey());
//            encoded.put(e.getKey(), MethodUtil.getIndexesSpecChars(html_, true, e.getValue(), indexBegin));
//        }
    }
//    abstract void buildTree(ContextEl _context);
    protected final void removeLocalVariablesFromParent(ContextEl _cont) {
        if (!isLastLeaf()) {
            return;
        }
        for (Block s: Classes.getDirectChildren(parent)) {
            if (s instanceof InitVariable) {
                String var_ = ((InitVariable)s).getVariableName();
                _cont.getLastPage().getLocalVars().removeKey(var_);
            }
        }
        if (parent instanceof ForLoop) {
            String var_ = ((ForLoop)parent).getVariableName();
            _cont.getLastPage().getVars().removeKey(var_);
        }
        if (parent instanceof CatchEval) {
            String var_ = ((CatchEval)parent).getVariableName();
            _cont.getLastPage().getCatchVars().removeKey(var_);
        }
    }

    protected final boolean isLastLeaf() {
        return getFirstChild() == null && getNextSibling() == null;
    }

//    protected final void setIndexGroup(int _indexGroup) {
//        indexGroup = _indexGroup;
//    }

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
//        BracedBlock p_ = getParent();
//        if (p_ == null) {
//            return null;
//        }
//        Block prev_ = null;
//        Block f_ = p_.getFirstChild();
//        while (f_ != this) {
//            prev_ = f_;
//            f_ = f_.getNextSibling();
//        }
//        return prev_;
        return previousSibling;
    }
    @Override
    public final Block getNextSibling() {
        if (initializedNextSibling) {
//            conf = null;
            return nextSibling;
        }
        initializedNextSibling = true;
        BracedBlock p_ = getParent();
        if (p_ == null) {
//            conf = null;
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
//            conf = null;
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
//        nextSibling.indexBegin = ne_.getBegin();
        nextSibling.tabs = ne_.getTabs();
        nextSibling.offsets = ne_.getOffsets();
        conf.setElements(ne_);
//        nextSibling.attributes = XmlParser.getIndexesOfElementOrAttribute(html_, e_, associateElement, tabWidth_)
        nextSibling.previousSibling = this;
//        conf = null;
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
//    final void setIndexBegin(int _indexBegin) {
//        indexBegin = _indexBegin;
//    }
    @Override
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
