package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorHealingItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbsFrameFactory;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectHealingItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecthealingitem";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private FacadeGame facade;

//    private boolean ok;

    private StringMap<String> messages;
    private final AbsCompoFactory compo;

    public SelectHealingItem(AbsFrameFactory _frameFactory, AbsCompoFactory _compoFactory) {
        super(_frameFactory);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo= _compoFactory;
    }

    public static void setSelectHealingItem(WindowAiki _parent, FacadeGame _facade) {
        _parent.getSelectHealingItem().init(_parent, _facade);
    }

    private void init(WindowAiki _parent, FacadeGame _facade) {
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages.getVal(TITLE));
        facade = _facade;
//        ok = false;
        initOk();
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorHealingItem(_parent,pag_, getSelectDial(), _facade).getContainer()), BorderLayout.CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        LabelButton ok_ = new LabelButton(WindowAiki.OK);
        ok_.addMouseList(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL));
        cancel_.addMouseList(new ClosingDialogEvent(this));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, BorderLayout.SOUTH);
        getSelectDial().setContentPane(contentPane_);
        getSelectDial().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getSelectDial().pack();
    }

    @Override
    public void closeWindow() {
        facade.clearFiltersHealingItem();
        getSelectDial().closeWindow();
    }

    public static boolean isSelectedIndex(SelectHealingItem _dialog) {
        _dialog.getSelectDial().setVisible(true);
        return _dialog.facade.getLineHealingItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectHealingItem _dialog) {
        return _dialog.isSelected();
    }

}
