package code.expressionlanguage.methods;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.SortedNode;

public final class BlockGroup implements SortedNode<BlockGroup> {

    private static final String TRUE_STRING = "true";

    private final int indexChild;

    private final Block root;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private BlockGroup parent;

    private BlockGroup firstChild;

    private boolean initializedFirstChild;

    private BlockGroup nextSibling;

    private boolean initializedNextSibling;

    private Block firstBlock;

    private CustList<Block> blockGroup;

    private boolean error;

    private boolean exitable;

    public BlockGroup(int _indexChild,CustList<Block> _blockGroup, BlockGroup _parent, Block _root, boolean _error) {
        indexChild = _indexChild;
        parent = _parent;
        blockGroup = _blockGroup;
        root = _root;
        error = _error;
        firstBlock = root;
    }

    public BlockGroup(int _indexChild,CustList<Block> _blockGroup, BlockGroup _parent, Block _root, boolean _error, Block _firstBlock) {
        indexChild = _indexChild;
        parent = _parent;
        blockGroup = _blockGroup;
        root = _root;
        error = _error;
        firstBlock = _firstBlock;
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public boolean isError() {
        return error;
    }

    public int indexComplete() {
        if (blockGroup.isEmpty()) {
            return CustList.INDEX_NOT_FOUND_ELT;
        }
        if (blockGroup.last() instanceof ElseCondition) {
            return blockGroup.last().getIndexGroup();
        }
        if (blockGroup.size() == CustList.ONE_ELEMENT) {
            if (blockGroup.first() instanceof WhileCondition) {
                if (StringList.quickEq(((WhileCondition)blockGroup.first()).getCondition(), TRUE_STRING)) {
                    return blockGroup.first().getIndexGroup();
                }
            }
        }
        if (blockGroup.first() instanceof TryEval) {
            int i_ = CustList.INDEX_NOT_FOUND_ELT;
            for (Block b: blockGroup) {
                if (b instanceof FinallyEval) {
                    break;
                }
                i_++;
            }
            return blockGroup.get(i_).getIndexGroup();
        }
        int i_ = CustList.FIRST_INDEX;
        for (Block b: blockGroup) {
            if (b instanceof DefaultCondition) {
                return blockGroup.get(i_).getIndexGroup();
            }
            i_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
    public boolean isEncounterReturnThrow() {
        if (blockGroup.isEmpty()) {
            return false;
        }
        for (Block b: blockGroup) {
            if (b instanceof ReturnMehod) {
                return true;
            }
            if (b instanceof Throwing) {
                return true;
            }
            if (b instanceof BreakBlock) {
                return true;
            }
            if (b instanceof ContinueBlock) {
                return true;
            }
        }
        return false;
    }

    public boolean isOkReturn() {
        if (!isEncounterReturnThrow() && parent != null) {
            return true;
        }
        if (!(blockGroup.last() instanceof ReturnMehod)) {
            if (!(blockGroup.last() instanceof Throwing)) {
                return false;
            }
        }
        for (Block b: blockGroup) {
            if (b == blockGroup.last()) {
                continue;
            }
            if (b instanceof ReturnMehod) {
                return false;
            }
            if (b instanceof Throwing) {
                return false;
            }
        }
        return true;
    }

    public CustList<Block> getBlockGroup() {
        return blockGroup;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }

    public boolean isExitable() {
        return exitable;
    }

    public void setExitable(boolean _exitable) {
        exitable = _exitable;
    }

    @Override
    public BlockGroup getFirstChild() {
        if (initializedFirstChild) {
            return firstChild;
        }
        initializedFirstChild = true;
        if (blockGroup.isEmpty()) {
            return null;
        }
        CustList<Block> l_ = new CustList<Block>();
        Block b_ = blockGroup.first();
        if (b_.getFirstChild() == null) {
            firstChild = new BlockGroup(CustList.FIRST_INDEX, l_, this, root, false, b_);
            return firstChild;
        }
        b_ = b_.getFirstChild();
        Block prev_ = b_.getPreviousSibling();
        int indGr_ = b_.getIndexGroup();
        boolean err_ = false;
        if (prev_ != null && prev_.getIndexGroup() == indGr_) {
            err_ = true;
        }
        while (true) {
            if (b_ == null) {
                break;
            }
            if (b_.getIndexGroup() != indGr_) {
                break;
            }
            l_.add(b_);
            b_ = b_.getNextSibling();
        }
        firstChild = new BlockGroup(CustList.FIRST_INDEX, l_, this, root, err_, l_.last());
        return firstChild;
    }

    @Override
    public BlockGroup getNextSibling() {
        if (initializedNextSibling) {
            return nextSibling;
        }
        initializedNextSibling = true;
        BlockGroup p_ = getParent();
        if (p_ == null) {
            return null;
        }
        if (indexChild + 1 >= p_.blockGroup.size()) {
            return null;
        }
        Block b_ = p_.blockGroup.get(indexChild + 1);
        CustList<Block> l_ = new CustList<Block>();
        if (b_.getFirstChild() == null) {
            nextSibling = new BlockGroup(indexChild + 1, l_, p_, root, false, b_);
            return nextSibling;
        }
        b_ = b_.getNextSibling();
        Block prev_ = b_.getPreviousSibling();
        int indGr_ = b_.getIndexGroup();
        boolean err_ = false;
        if (prev_ != null && prev_.getIndexGroup() == indGr_) {
            err_ = true;
        }
        while (true) {
            if (b_ == null) {
                break;
            }
            if (b_.getIndexGroup() != indGr_) {
                break;
            }
            l_.add(b_);
            b_ = b_.getNextSibling();
        }
        nextSibling = new BlockGroup(indexChild + 1, l_, p_, root, err_, l_.last());
        return nextSibling;
    }

    @Override
    public BlockGroup getParent() {
        return parent;
    }

}
