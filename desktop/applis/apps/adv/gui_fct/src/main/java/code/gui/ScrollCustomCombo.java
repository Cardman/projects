package code.gui;

import code.gui.events.AbsEnabledAction;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import code.util.core.NumberUtil;

public abstract class ScrollCustomCombo implements Input, SelectableIndexes {
    public static final int DEF_MIN_WIDTH = 128;
    private final AbsPopupMenu popupMenu;
    private final ScrollCustomGraphicList<String> list;
    private final AbsPanel elements;
    private final AbsPreparedLabel selected;
    private final CustList<AbsEnabledAction> actions = new CustList<AbsEnabledAction>();

    private final AdaptCustCellRenderString adaptCustCellRenderString;

    private boolean enabled = true;
    private boolean visiblePop;
    protected ScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img) {
        popupMenu = _compo.newAbsPopupMenu();
        popupMenu.setFocusable(false);
        popupMenu.setVisible(false);
        adaptCustCellRenderString = new AdaptCustCellRenderString(_compo, _img);
        list = new DefScrollCustomGraphicList<String>(_compo, _img, adaptCustCellRenderString, true);
        popupMenu.add(list.getScrollPane());
        elements = _compo.newLineBox();
        int s_ = _compo.heightFont(elements.getMetaFont()) + 2;
        selected = _compo.newPreparedLabel(_img.newImageRgb(DEF_MIN_WIDTH,s_));
        AbstractImage img_ = _img.newImageRgb(s_, s_);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, s_, s_);
        img_.setColor(GuiConstants.BLACK);
        img_.fillPolygon(NumberUtil.wrapIntArray(s_/4,s_*3/4,s_/2), NumberUtil.wrapIntArray(s_/4,s_/4,s_*3/4), 3);
        AbsPreparedLabel pseudoButton_ = _compo.newPreparedLabel(img_);
        pseudoButton_.setIcon(_img,img_);
        selected.setFocusable(true);
        selected.addFocusListener(new RefreshComboFocusEvent(this));
        selected.addMouseListener(new SelectingComboListEvent(this));
        pseudoButton_.addMouseListener(new SelectingComboListEvent(this));
        elements.add(selected);
        elements.add(pseudoButton_);
        selected.setBackground(GuiConstants.WHITE);
        selected.setForeground(GuiConstants.BLACK);
        list.setSelectedBg(GuiConstants.BLUE);
        list.setSelectedFg(GuiConstants.WHITE);
        CustList<AbsMouseListenerIntRel> old_ = new CustList<AbsMouseListenerIntRel>(list.getElements().getMouseListenersRel());
        for (AbsMouseListenerIntRel m: old_) {
            list.getElements().removeMouseListener(m);
        }
        list.getElements().addMouseListener(new MoveComboHideAfterMouseEvent(this));
    }
    protected void buildActions() {
        AbsEnabledAction moveDownAction_ = moveComboSelectEvent(1);
        actions.add(moveDownAction_);
        selected.registerKeyboardAction(moveDownAction_,GuiConstants.VK_DOWN,0);
        AbsEnabledAction moveUpAction_ = moveComboSelectEvent(-1);
        actions.add(moveUpAction_);
        selected.registerKeyboardAction(moveUpAction_,GuiConstants.VK_UP,0);
        AbsEnabledAction movePageUpAction_ = moveComboSelectPageEvent(1);
        actions.add(movePageUpAction_);
        selected.registerKeyboardAction(movePageUpAction_,GuiConstants.VK_PAGE_DOWN,0);
        AbsEnabledAction movePageDownAction_ = moveComboSelectPageEvent(-1);
        actions.add(movePageDownAction_);
        selected.registerKeyboardAction(movePageDownAction_,GuiConstants.VK_PAGE_UP,0);
        AbsEnabledAction movePageHomeAction_ = moveComboSelectBoundEvent(-1);
        actions.add(movePageHomeAction_);
        selected.registerKeyboardAction(movePageHomeAction_,GuiConstants.VK_HOME,0);
        AbsEnabledAction movePageEndAction_ = moveComboSelectBoundEvent(1);
        actions.add(movePageEndAction_);
        selected.registerKeyboardAction(movePageEndAction_,GuiConstants.VK_END,0);
        AbsEnabledAction hide_ = moveComboHideEvent();
        actions.add(hide_);
        selected.registerKeyboardAction(hide_,GuiConstants.VK_ESCAPE,0);
        AbsEnabledAction toggle_ = moveComboToggleEvent();
        actions.add(toggle_);
        selected.registerKeyboardAction(toggle_,GuiConstants.VK_F4,0);
        AbsEnabledAction enter_ = moveComboEnterEvent();
        actions.add(enter_);
        selected.registerKeyboardAction(enter_,GuiConstants.VK_ENTER,0);
    }
    protected abstract AbsEnabledAction moveComboSelectEvent(int _d);
    protected abstract AbsEnabledAction moveComboSelectPageEvent(int _d);
    protected abstract AbsEnabledAction moveComboSelectBoundEvent(int _d);
    protected abstract AbsEnabledAction moveComboHideEvent();
    protected abstract AbsEnabledAction moveComboToggleEvent();
    protected abstract AbsEnabledAction moveComboEnterEvent();
    private void enable(boolean _en) {
        for (AbsEnabledAction a: actions) {
            a.setEnabled(_en);
        }
    }
    public void add(String _i) {
        boolean was_ = list.isEmpty();
        list.add(_i);
        resize();
        if (was_) {
            list.select(0);
        }
    }

    private void resize() {
        int m_ = NumberUtil.max(DEF_MIN_WIDTH, list.getElements().getPreferredSizeValue().getWidth());
        int supp_ = list.getCompoFactory().heightFont(elements.getMetaFont()) + 6;
        adaptCustCellRenderString.setMaxWidth(m_);
        list.getScrollPane().setPreferredSize(new MetaDimension(m_+supp_,list.getScrollPane().getPreferredSizeValue().getHeight()));
    }

    public AbsCompoFactory getCompoFactory() {
        return list.getCompoFactory();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return list.size();
    }
    public ScrollCustomGraphicList<String> getList() {
        return list;
    }

    public boolean remove(int _index) {
        if (_index < 0) {
            return false;
        }
        boolean sel_;
        int index_ = list.getSelectedIndex();
        if (index_ == _index) {
            sel_ = true;
            if (_index == 0) {
                if (list.size() == 1) {
                    list.deselectAll();
                } else {
                    list.select(_index + 1);
                }
            } else {
                list.select(_index - 1);
            }
        } else {
            sel_ = false;
        }
        list.remove(_index);
        resize();
        list.revalidate();
        return sel_;
    }
    public void clearRevalidate() {
        list.clearRevalidate();
        adaptCustCellRenderString.setMaxWidth(DEF_MIN_WIDTH);
    }
    public void clear() {
        list.clear();
        adaptCustCellRenderString.setMaxWidth(DEF_MIN_WIDTH);
    }

    public void select(int _index) {
        if (_index < 0) {
            select(Ints.newList());
            return;
        }
        select(Ints.newList(_index));
    }

    public void select(Ints _indices) {
        list.select(_indices);
    }

    public void move(int _down) {
        list.move(_down);
        text();
    }

    public int forceRefresh() {
        int f_ = list.forceRefresh();
        text();
        return f_;
    }

    public void goBound(int _down) {
        list.goBound(_down);
        text();
    }

    public void movePage(int _down) {
        list.movePage(_down);
        text();
    }

    public void repaint() {
        list.applyRows();
        forceRefresh();
        revalidate();
    }
    public void revalidate() {
        list.revalidate();
    }

    public void refreshFocused() {
        if (selected.isFocused()) {
            selected.setLineBorder(GuiConstants.RED);
        } else {
            selected.setLineBorder(GuiConstants.BLACK);
        }
    }

    public Ints getSelectedIndexes() {
        return list.getSelectedIndexes();
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public void addListener(ListSelection _listener){
        list.addListener(_listener);
    }


    public void setListener(ListSelection _listener){
        list.setListener(_listener);
    }

    public void removeListener(ListSelection _listener){
        list.removeListener(_listener);
    }

    @Override
    public AbsCustComponent getGlobal() {
        return getElements();
    }

    public AbsPanel getElements() {
        return elements;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
        enable(_e);
    }
    public void changeSelect() {
        text();
        hidePopup();
    }
    public void text() {
        RowGraphicList<String> r_ = getList().getRow(getSelectedIndex());
        if (r_ == null) {
            update("");
            return;
        }
        String i_ = r_.getInfo();
        update(i_);
    }

    private void update(String _i) {
        int w_ = NumberUtil.max(DEF_MIN_WIDTH, adaptCustCellRenderString.getMaxWidth());
        int h_ = list.getCompoFactory().heightFont(elements.getMetaFont()) + 2;
        AbstractImage img_ = list.getImageFactory().newImageRgb(w_, h_);
        img_.setFont(elements.getMetaFont());
        img_.setColor(selected.getBackgroundValue());
        img_.fillRect(0,0, w_, h_);
        img_.setColor(selected.getForegroundValue());
        img_.drawString(_i,0,h_-1);
        selected.setIcon(list.getImageFactory(),img_);
        img_.dispose();
    }

    public AbsPreparedLabel getSelected() {
        return selected;
    }

    public void togglePopup() {
        if (visiblePop) {
            hidePopup();
        } else {
            showPopup();
        }
    }

    public void hidePopup() {
        getPopupMenu().setVisible(false);
        visiblePop = false;
    }

    public void showPopup() {
        AbsCustComponent sel_ = getSelected();
        getPopupMenu().show(sel_,0,sel_.getHeight());
        visiblePop = true;
    }
    public AbsPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public IdList<ListSelection> getSelections() {
        return list.getSelections();
    }

}
