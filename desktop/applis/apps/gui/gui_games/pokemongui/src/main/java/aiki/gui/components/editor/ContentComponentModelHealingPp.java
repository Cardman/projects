package aiki.gui.components.editor;

import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class ContentComponentModelHealingPp {

    private GeneComponentModelLong healedMovePp;
    private GeneComponentModelLong healingAllMovesFullpp;
    private AbsCustCheckBox healingAllMovesPp;
    private AbsCustCheckBox healingMoveFullpp;
    private AbsPanel healPpForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        healPpForm = compoFactory_.newLineBox();
        healedMovePp = new GeneComponentModelLong(_parent.getCompoFactory());
        healPpForm.add(healedMovePp.gene(0L));
        healingAllMovesFullpp = new GeneComponentModelLong(_parent.getCompoFactory());
        healPpForm.add(healingAllMovesFullpp.gene(0L));
        healingAllMovesPp=compoFactory_.newCustCheckBox();
        healPpForm.add(healingAllMovesPp);
        healingMoveFullpp=compoFactory_.newCustCheckBox();
        healPpForm.add(healingMoveFullpp);
        healPpForm.setVisible(false);
        return healPpForm;
    }
    void display(String _eff) {
        healPpForm.setVisible(StringUtil.quickEq(_eff, Item.HEALING_PP));
    }
    void buildEntity(HealingPp _item) {
        _item.setHealedMovePp(healedMovePp.valueLong());
        _item.setHealingAllMovesFullpp(healingAllMovesFullpp.valueLong());
        _item.setHealingAllMovesPp(healingAllMovesPp.isSelected());
        _item.setHealingMoveFullpp(healingMoveFullpp.isSelected());
    }
    void feedForm(HealingPp _item) {
        healedMovePp.valueLong(_item.getHealedMovePp());
        healingAllMovesFullpp.valueLong(_item.getHealingAllMovesFullpp());
        healingAllMovesPp.setSelected(_item.isHealingAllMovesPp());
        healingMoveFullpp.setSelected(_item.getHealingMoveFullpp());
    }

    public AbsCustCheckBox getHealingAllMovesPp() {
        return healingAllMovesPp;
    }

    public AbsCustCheckBox getHealingMoveFullpp() {
        return healingMoveFullpp;
    }

}
