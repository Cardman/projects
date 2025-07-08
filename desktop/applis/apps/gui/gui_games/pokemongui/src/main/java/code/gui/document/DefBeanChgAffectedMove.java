package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;
import code.gui.*;

public final class DefBeanChgAffectedMove extends BeanChgAffectedMove {

    private final GeneComponentModelElt<String> name;
    private final AbsCustCheckBox check;
    private final GeneComponentModelLong spinner;

    public DefBeanChgAffectedMove(GeneComponentModelElt<String> _n, AbsCustCheckBox _c, GeneComponentModelLong _s) {
        this.name = _n;
        this.check = _c;
        this.spinner = _s;
    }

    @Override
    public AffectedMove valAff() {
        ActivityOfMove act_ = new ActivityOfMove();
        act_.setNbTurn(spinner.valueLong());
        act_.setEnabled(check.isSelected());
        return new AffectedMove(name.tryRet(), act_);
    }

    @Override
    public void valAff(AffectedMove _v) {
        name.setupValue(_v.getMove());
        spinner.valueLong(_v.getActivity().getNbTurn());
        check.setSelected(_v.getActivity().isEnabled());
    }

}
