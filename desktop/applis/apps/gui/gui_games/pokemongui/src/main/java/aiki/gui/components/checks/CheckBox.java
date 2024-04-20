package aiki.gui.components.checks;


import aiki.facade.*;
import aiki.gui.components.*;
import aiki.gui.components.labels.*;
import aiki.main.PkNonModalEvent;
import code.gui.*;
import code.gui.initialize.*;
import code.threads.AbstractAtomicBooleanCore;

public abstract class CheckBox {

    private final AbstractProgramInfos programInfos;
    private final TmLabel component;

    private final String key;

    protected CheckBox(String _key, String _text, boolean _selected, AbstractProgramInfos _window, FacadeGame _facade, AbstractAtomicBooleanCore _act) {
        programInfos = _window;
        component = new TmLabel(_text,_key,_facade.getData().getMove(_key).getTargetChoice(), PaginationMove.price(_facade.getData(),_key),_facade,_window.getCompoFactory());
        component.setxMoveName(component.getNameWidth());
        component.setxTypes(component.getTypesWidth());
        component.setxPriority(component.getPriorityWidth());
        component.setxPp(component.getPpWidth());
        component.setxTarget(component.getTargetWidth());
        int width_ = component.getNameWidth()+component.getTypesWidth();
        width_ += component.getPriorityWidth();
        width_ += component.getPpWidth();
        width_ += component.getTargetWidth();
        width_ += component.getPriceWidth();
        key = _key;
        component.setSelected(_selected);
        component.setPreferredSize(width_);
        paintLabel();
        component.addMouseListener(new PkNonModalEvent(_act),new CheckEvent(this));
    }

    public void toggle() {
        component.setSelected(!component.isSelected());
        paintLabel();
    }

    public void setSelected(boolean _b) {
        component.setSelected(_b);
        paintLabel();
    }

    public void paintLabel() {
        AbsMetaLabelPk.paintPk(programInfos.getImageFactory(),component);
    }
//    public void setBackground(int _bg) {
//        component.setBackground(_bg);
//    }

    public AbsCustComponent getComponent() {
        return component.getPaintableLabel();
    }

    String getKey() {
        return key;
    }

    protected abstract void processKey(String _key);
}
