package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.PaginatorItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.events.ClosingDialogEvent;
import code.util.CustList;
import code.util.StringMap;

public final class SelectItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.SelectItem";

    private static final SelectItem DIALOG = new SelectItem();

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private static final String GIVE = "give";

//    private static final String SET_FIELDS = "setFields";

    private FacadeGame facade;

//    private boolean ok;

//    private boolean give;

    private JCheckBox giveCheckBox;

    private StringMap<String> messages;

    private SelectItem() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectItem(GroupFrame _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        DIALOG.init(_parent, _facade, _buy, _sell);
    }

    private void init(GroupFrame _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        setDialogIcon(_parent);
        messages = getMessages(Resources.MESSAGES_FOLDER);
        setTitle(messages.getVal(TITLE));
        facade = _facade;
        initOk();
//        ok = false;
        JPanel contentPane_ = new JPanel();
        contentPane_.setLayout(new BorderLayout());
        contentPane_.add(new JScrollPane(new PaginatorItem(this, _facade, !_sell)), BorderLayout.CENTER);
        JPanel buttons_ = new JPanel();
        if (!_buy) {
            giveCheckBox = new JCheckBox(messages.getVal(GIVE));
//            giveCheckBox.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent _arg0) {
//                    give = giveCheckBox.isSelected();
//                }
//            });
            buttons_.add(giveCheckBox);
        }
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
        facade.clearFiltersItem();
        super.closeWindow();
    }

    public static boolean isSelectedIndex() {
        DIALOG.setVisible(true);
        return DIALOG.facade.getLineItem() != CustList.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk() {
        return DIALOG.isSelected();
    }

    public static boolean isGive() {
        return DIALOG.giveCheckBox.isSelected();
    }

//    public static void setGive(boolean _give) {
//        DIALOG.giveCheckBox.setSelected(_give);
////        DIALOG.give = _give;
//    }
}
