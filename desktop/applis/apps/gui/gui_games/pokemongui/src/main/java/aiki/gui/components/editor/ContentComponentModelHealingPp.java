package aiki.gui.components.editor;

import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
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
        healPpForm.add(line(_parent,MessagesDataItemsHealingpp.M_P_25_HEAL_MOVE_INTRO,healedMovePp.geneLong()));
        healingAllMovesFullpp = new GeneComponentModelLong(_parent.getCompoFactory());
        healPpForm.add(line(_parent,MessagesDataItemsHealingpp.M_P_25_HEAL_MOVES_INTRO,healingAllMovesFullpp.geneLong()));
        healingAllMovesPp=compoFactory_.newCustCheckBox();
        healPpForm.add(line(_parent,MessagesDataItemsHealingpp.M_P_25_FULL_HEAL_MOVES,healingAllMovesPp));
        healingMoveFullpp=compoFactory_.newCustCheckBox();
        healPpForm.add(line(_parent,MessagesDataItemsHealingpp.M_P_25_FULL_HEAL_MOVE,healingMoveFullpp));
        healPpForm.setVisible(false);
        return healPpForm;
    }
    private AbsCustComponent line(GeneComponentModelItem _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.IT_HEALINGPP, _key,_input);
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
