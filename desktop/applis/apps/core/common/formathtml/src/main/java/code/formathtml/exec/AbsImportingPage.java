package code.formathtml.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendElem;
import code.formathtml.exec.blocks.RendFormInt;
import code.formathtml.exec.stacks.*;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.Longs;
import code.util.StringList;

public abstract class AbsImportingPage {
    private Struct internGlobal;

    private final CustList<RendAbstractStask> rendBlockStacks = new CustList<RendAbstractStask>();

    private String beanName;

    private RendReadWrite rendReadWrite;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

    public RendReadWrite getRendReadWrite() {
        return rendReadWrite;
    }

    public void setNullRendReadWrite() {
        rendReadWrite = null;
    }

    public void setRendReadWrite(RendReadWrite _rendReadWrite) {
        rendReadWrite = _rendReadWrite;
    }

    protected CustList<RendAbstractStask> getRendBlockStacks() {
        return rendBlockStacks;
    }

    public void addBlock(RendAbstractStask _b) {
        rendBlockStacks.add(_b);
    }
    protected void removeRendLastBlockSt() {
        RendAbstractStask last_ = rendBlockStacks.last();
        if (last_ instanceof RendIfStack) {
            if (((RendIfStack)last_).getBlock() instanceof RendElem) {
                rendReadWrite.setWrite(RendBlock.getParentNode(rendReadWrite));
            }
            if (((RendIfStack)last_).getBlock() instanceof RendFormInt) {
                CustList<LongTreeMap<NodeContainer>> map_ = rendReadWrite.getConf().getContainersMapStack();
                Longs formsNb_ = rendReadWrite.getConf().getFormsNb();
                Long nb_ = formsNb_.last();
                LongTreeMap<NodeContainer> containers_ = map_.last();
                rendReadWrite.getConf().getContainersMap().put(nb_, containers_);
                CustList<StringList> formatId_ = rendReadWrite.getConf().getFormatIdMapStack();
                StringList fid_ = formatId_.last();
                rendReadWrite.getConf().getFormatIdMap().put(nb_,fid_);
                rendReadWrite.getConf().getInputs().removeLast();
                map_.removeQuicklyLast();
                formatId_.removeQuicklyLast();
                formsNb_.removeQuicklyLast();
            }
        }
        rendBlockStacks.removeQuicklyLast();
    }

    public Struct getInternGlobal() {
        return internGlobal;
    }

    public void setInternGlobal(Struct _internGlobal) {
        internGlobal = _internGlobal;
    }

    public RendAbstractStask tryGetRendLastStack() {
        if (hasBlock()) {
            return rendBlockStacks.last();
        }
        return null;
    }

    public boolean hasBlock() {
        return !rendBlockStacks.isEmpty();
    }

    public RendLoopBlockStack getLastLoopIfPossible(RendBlock _bl) {
        RendLoopBlockStack c_ = null;
        RendAbstractStask last_ = tryGetRendLastStack();
        if (last_ instanceof RendLoopBlockStack) {
            c_ = (RendLoopBlockStack) last_;
        }
        if (c_ != null && c_.getCurrentVisitedBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(RendBlock _bl) {
        RendAbstractStask last_ = tryGetRendLastStack();
        if (!(last_ instanceof RendConditionBlockStack)) {
            return false;
        }
        return _bl == ((RendConditionBlockStack)last_).getBlock();
    }

}
