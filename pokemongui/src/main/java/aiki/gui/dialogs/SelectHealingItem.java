package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.PaginatorHealingItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.events.ClosingDialogEvent;
import code.util.CustList;
import code.util.StringMap;

public final class SelectHealingItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "dbpokemon.gui.dialogs.SelectHealingItem";

    private static final SelectHealingItem DIALOG = new SelectHealingItem();

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private FacadeGame facade;

//    private boolean ok;

    private StringMap<String> messages;

    private SelectHealingItem() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectHealingItem(GroupFrame _parent, FacadeGame _facade) {
        DIALOG.init(_parent, _facade);
    }

    private void init(GroupFrame _parent, FacadeGame _facade) {
        setDialogIcon(_parent);
        messages = getMessages(Resources.MESSAGES_FOLDER);
        setTitle(messages.getVal(TITLE));
        facade = _facade;
//        ok = false;
        initOk();
        JPanel contentPane_ = new JPanel();
        contentPane_.setLayout(new BorderLayout());
        contentPane_.add(new JScrollPane(new PaginatorHealingItem(this, _facade)), BorderLayout.CENTER);
        JPanel buttons_ = new JPanel();
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
        facade.clearFiltersHealingItem();
        super.closeWindow();
    }

    public static boolean isSelectedIndex() {
        DIALOG.setVisible(true);
        return DIALOG.facade.getLineHealingItem() != CustList.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk() {
        return DIALOG.isSelected();
    }
}
