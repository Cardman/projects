package aiki.gui.dialogs;


import aiki.comparators.TrMovesComparator;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorHealingItem;
import aiki.gui.components.walk.HealedMoveEvent;
import aiki.gui.dialogs.events.ClosingSelectHealingItem;
import aiki.sml.Resources;
import code.gui.AbsButton;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class SelectHealingItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecthealingitem";

    private static final String TITLE = "title";

//    private boolean ok;

    private final AbsCompoFactory compo;
    private final CustList<AbsButton> moves = new CustList<AbsButton>();
    private AbsPanel movesPanel;
    private int lineBack;
    private boolean inBattle;

    public SelectHealingItem(AbstractProgramInfos _infos, WindowAiki _window) {
        super(_infos.getFrameFactory(), _window);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo= _infos.getCompoFactory();
    }

    @Override
    protected AbsWindowListenerClosing build() {
        return new ClosingSelectHealingItem(this);
    }

    public static void setSelectHealingItem(WindowAiki _parent, FacadeGame _facade, boolean _battle) {
        _parent.getSelectHealingItem().init(_parent, _facade, _battle);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _battle) {
        _parent.getModal().set(true);
        inBattle = _battle;
        lineBack = _facade.getLineHealingItem();
        getSelectDial().setIconImage(_parent.getCommonFrame().getImageIconFrame());
        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages_.getVal(TITLE));
        setFacade(_facade);
//        ok = false;
        initOk();
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorHealingItem(_parent,pag_, getSelectDial(), _facade).getContainer()), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        movesPanel = compo.newPageBox();
        buttons(_parent,buttons_);
//        okButton = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
//        okButton.addActionListener(new ValidateSelectionEvent(this));
//        buttons_.add(okButton);
//        cancelButton = _parent.getCompoFactory().newPlainButton(messages_.getVal(CANCEL));
//        cancelButton.addActionListener(new ClosingSelectButtonEvt(getSelectDial(), _parent));
//        buttons_.add(cancelButton);
        buttons_.add(movesPanel);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        getSelectDial().setContentPane(contentPane_);
//        getSelectDial().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
        getSelectDial().setVisible(true);
    }

    @Override
    public void validateChoice() {
        okChoice();
        if (inBattle) {
            closWindow();
            getMainWindow().getBattle().getBattle().afterSelectInBattle(lineBack);
            return;
        }
        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex(getMainWindow().getSelectHealingItem());
//        boolean ok_ = SelectHealingItem.isOk(getMainWindow().getSelectHealingItem());
//        if (!ok_) {
//            getFacade().setLineHealingItem(lineBack);
//            getFacade().clearSortingHealingItem();
//        } else
        if (isSelectedIndex_) {
            getMainWindow().getScenePanel().prepareItem();
            if (getFacade().getPlayer().getSelectedObject().isEmpty()) {
                closWindow();
                getMainWindow().setSavedGame(false);
                getMainWindow().getScenePanel().setTextArea(StringUtil.join(getFacade().getComment().getMessages(), RETURN_LINE));
                return;
            }
            getMainWindow().getScenePanel().preparePk();
            if (getFacade().getPlayer().getChosenMoves().isEmpty()) {
                closWindow();
                getMainWindow().setSavedGame(false);
                getMainWindow().getScenePanel().setTextArea(StringUtil.join(getFacade().getComment().getMessages(), RETURN_LINE));
                return;
            }
            getOkButton().setEnabled(false);
            StringMap<Short> moves_ = getFacade().getPlayer().getChosenMoves();
            StringList keys_ = new StringList(moves_.getKeys());
            keys_.sortElts(new TrMovesComparator(getFacade().getData()));
            movesPanel.removeAll();
            moves.clear();
            for (String m: keys_) {
                String tr_ = getFacade().translateMove(m);
                AbsButton check_ = getMainWindow().getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
                check_.addActionListener(new HealedMoveEvent(this, getFacade(), m));
                movesPanel.add(check_);
                moves.add(check_);
            }
            getSelectDial().pack();
        } else {
            closWindow();
            getFacade().clearSortingHealingItem();
        }
    }

//    @Override
    public void closeWindow() {
        getFacade().clearFiltersHealingItem();
        closWindow();
        if (inBattle) {
            getMainWindow().getBattle().getBattle().afterSelectInBattle(lineBack);
            return;
        }
        getFacade().setLineHealingItem(lineBack);
    }

    public CustList<AbsButton> getMoves() {
        return moves;
    }

    public static boolean isSelectedIndex(SelectHealingItem _dialog) {
        return _dialog.getFacade().getLineHealingItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectHealingItem _dialog) {
        return _dialog.isSelected();
    }

}
