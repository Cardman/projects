package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import code.gui.*;

public final class DefBeanChgStackOfUses extends BeanChgStackOfUses {
    private final AbsCustCheckBox first;
    private final AbsCustCheckBox last;
    private final GeneComponentModelLong spinner;
    public DefBeanChgStackOfUses(AbsCustCheckBox _f, AbsCustCheckBox _l,GeneComponentModelLong _c) {
        first = _f;
        last = _l;
        spinner = _c;
    }

    @Override
    public StacksOfUses valueStack() {
        StacksOfUses ac_ = new StacksOfUses();
        ac_.setFirstStacked(first.isSelected());
        ac_.setLastStacked(last.isSelected());
        ac_.setNbRounds(spinner.valueLong());
        return ac_;
    }

    @Override
    public void valueStack(StacksOfUses _v) {
        first.setSelected(_v.isFirstStacked());
        last.setSelected(_v.isLastStacked());
        spinner.valueLong(_v.getNbRounds());
    }

}
