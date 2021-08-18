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
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectHealingItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecthealingitem";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private FacadeGame facade;

//    private boolean ok;

    private StringMap<String> messages;

    public SelectHealingItem() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectHealingItem(WindowAiki _parent, FacadeGame _facade) {
        _parent.getSelectHealingItem().init(_parent, _facade);
    }

    private void init(WindowAiki _parent, FacadeGame _facade) {
        setDialogIcon(_parent.getImageFactory(),_parent);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getAccessFile());
        setTitle(messages.getVal(TITLE));
        facade = _facade;
//        ok = false;
        initOk();
        Panel contentPane_ = Panel.newBorder();
        Panel pag_ = Panel.newPageBox();
        contentPane_.add(new ScrollPane(new PaginatorHealingItem(_parent,pag_, this, _facade).getContainer()), BorderLayout.CENTER);
        Panel buttons_ = Panel.newLineBox();
        LabelButton ok_ = new LabelButton(WindowAiki.OK);
        ok_.addMouseListener(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL));
        cancel_.addMouseListener(new ClosingDialogEvent(this));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, BorderLayout.SOUTH);
        setContentPane(contentPane_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    @Override
    public void closeWindow() {
        facade.clearFiltersHealingItem();
        super.closeWindow();
    }

    public static boolean isSelectedIndex(SelectHealingItem _dialog) {
        _dialog.setVisible(true);
        return _dialog.facade.getLineHealingItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectHealingItem _dialog) {
        return _dialog.isSelected();
    }

}
