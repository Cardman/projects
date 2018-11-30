package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.events.ClosingDialogEvent;
import code.util.CustList;
import code.util.StringMap;

public final class SelectEgg extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectegg";

    private static final SelectEgg DIALOG = new SelectEgg();

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private FacadeGame facade;

//    private boolean ok;

    private StringMap<String> messages;

    private SelectEgg() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectEgg(MainWindow _parent, FacadeGame _facade) {
        DIALOG.init(_parent, _facade);
    }

    private void init(MainWindow _parent, FacadeGame _facade) {
        setDialogIcon(_parent);
        messages = getMessages(_parent,Resources.MESSAGES_FOLDER);
        setTitle(DIALOG.messages.getVal(TITLE));
        facade = _facade;
        initOk();
//        ok = false;
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new BorderLayout());
        contentPane_.add(new ScrollPane(new PaginatorEgg(_parent, this, _facade)), BorderLayout.CENTER);
        Panel buttons_ = new Panel();
        LabelButton ok_ = new LabelButton(MainWindow.OK);
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
        facade.clearFiltersEgg();
        super.closeWindow();
    }

    public static void setVisible() {
        DIALOG.setVisible(true);
    }

    public static boolean isSelectedIndex() {
        DIALOG.setVisible(true);
        return DIALOG.facade.getLineEgg() != CustList.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk() {
        return DIALOG.isSelected();
    }
}
