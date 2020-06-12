package code.expressionlanguage.methods;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.*;

public abstract class BracedStack extends BracedBlock {

    BracedStack(OffsetsBlock _offset) {
        super(_offset);
    }
    protected void refLabel(CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        _parts.add(new PartOffset("<a name=\"m"+_offset+"\">",_offset));
        _parts.add(new PartOffset("</a>",_offset+_label.length()));
    }

}
