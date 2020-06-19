package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;

public abstract class ExecBracedBlock extends ExecBlock {

    private ExecBlock firstChild;

    ExecBracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }
    public static void refLabel(CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        _parts.add(new PartOffset("<a name=\"m"+_offset+"\">",_offset));
        _parts.add(new PartOffset("</a>",_offset+_label.length()));
    }
    public final void appendChild(ExecBlock _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ExecBlock child_ = firstChild;
        while (true) {
            ExecBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public void exitStack(ContextEl _context){
    }

    public final void removeLocalVars(AbstractPageEl _ip) {
        for (ExecBlock s: getDirectChildren(this)) {
            if (s instanceof ExecDeclareVariable) {
                for (String v: ((ExecDeclareVariable)s).getVariableNames()) {
                    _ip.removeLocalVar(v);
                }
            }
        }
    }

    public void removeAllVars(AbstractPageEl _ip) {
        removeLocalVars(_ip);
    }

    @Override
    public ExecBlock getFirstChild() {
        return firstChild;
    }
}
