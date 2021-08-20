package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorMove;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectTm extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecttm";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private FacadeGame facade;

//    private boolean ok;

    private StringMap<String> messages;

    public SelectTm() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectTm(WindowAiki _parent, FacadeGame _facade, boolean _buy) {
        _parent.getSelectTm().init(_parent, _facade, _buy);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _buy) {
        setDialogIcon(_parent.getImageFactory(),_parent);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getAccessFile());
        setTitle(messages.getVal(TITLE));
        facade = _facade;
//        ok = false;
        initOk();
        Panel contentPane_ = Panel.newBorder();
        Panel pag_ = Panel.newPageBox();
        contentPane_.add(new ScrollPane(new PaginatorMove(_parent,pag_, this, _facade, _buy).getContainer()), BorderLayout.CENTER);
        Panel buttons_ = Panel.newLineBox();
        LabelButton ok_ = new LabelButton(WindowAiki.OK);
        ok_.addMouseList(new ValidateSelectionEvent(this));
        buttons_.add(ok_);
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL));
        cancel_.addMouseList(new ClosingDialogEvent(this));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, BorderLayout.SOUTH);
        setContentPane(contentPane_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    @Override
    public void closeWindow() {
        facade.clearFiltersMove();
        super.closeWindow();
    }

    public static boolean isSelectedIndex(SelectTm _dialog) {
        _dialog.setVisible(true);
        return _dialog.facade.getLineMove() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectTm _dialog) {
        return _dialog.isSelected();
    }

}
