package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.StringList;

public final class GeneComponentModelText {
    private final AbstractProgramInfos compoFactory;
    private AbsTextPane textPane;
    private final StringList words = new StringList();
    private ScrollCustomGraphicList<String> element;
    private AbsPopupMenu popupMenu;

    public GeneComponentModelText(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    public AbsCustComponent geneString() {
        textPane = compoFactory.getCompoFactory().newTextPane();
        popupMenu = compoFactory.getCompoFactory().newAbsPopupMenu();
        popupMenu.setFocusable(false);
        popupMenu.setVisible(false);
        element = new DefScrollCustomGraphicList<String>(compoFactory.getCompoFactory(), compoFactory.getImageFactory(), new CustCellRenderString(compoFactory.getCompoFactory(), compoFactory.getImageFactory()),true);
        popupMenu.add(element.getScrollPane());
        words.clear();
        AbsScrollPane sc_ = compoFactory.getCompoFactory().newAbsScrollPane(textPane);
        sc_.setPreferredSize(new MetaDimension(320, 32));
        return sc_;
    }

    public void addComplete(FacadeGame _facade) {
        element.addListener(new PkCompleteEnterEvent(this));
        textPane.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new PkCompleteEnterEvent(this)), GuiConstants.VK_ENTER,0);
        textPane.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new PkCompleteEvent(this,_facade)), GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        textPane.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new PkCompleteNavigateEvent(this,1)), GuiConstants.VK_DOWN,0);
        textPane.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new PkCompleteNavigateEvent(this,-1)), GuiConstants.VK_UP,0);
    }

    public ScrollCustomGraphicList<String> getElement() {
        return element;
    }

    public AbsPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public StringList getWords() {
        return words;
    }

    public AbstractProgramInfos getCompoFactory() {
        return compoFactory;
    }

    public String valueString() {
        return textPane.getText();
    }

    public String valueString(String _v) {
        String p_ = textPane.getText();
        textPane.setText(_v);
        return p_;
    }

    public AbsTextPane getTextPane() {
        return textPane;
    }
}
