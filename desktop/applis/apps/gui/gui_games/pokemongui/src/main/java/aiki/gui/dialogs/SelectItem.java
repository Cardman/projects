package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.PaginatorItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.sml.stream.ExtractFromFiles;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectItem extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectitem";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private static final String GIVE = "give";

//    private static final String SET_FIELDS = "setFields";

    private FacadeGame facade;

//    private boolean ok;

//    private boolean give;

    private CustCheckBox giveCheckBox;

    private StringMap<String> messages;

    public SelectItem() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectItem(MainWindow _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        _parent.getSelectItem().init(_parent, _facade, _buy, _sell);
    }

    private void init(MainWindow _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        setDialogIcon(_parent);
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getAccessFile());
        setTitle(messages.getVal(TITLE));
        facade = _facade;
        initOk();
//        ok = false;
        Panel contentPane_ = Panel.newBorder();
        Panel pag_ = Panel.newPageBox();
        contentPane_.add(new ScrollPane(new PaginatorItem(_parent,pag_, this, _facade, !_sell).getContainer()), BorderLayout.CENTER);
        Panel buttons_ = Panel.newLineBox();
        if (!_buy) {
            giveCheckBox = new CustCheckBox(messages.getVal(GIVE));
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

    public static boolean isSelectedIndex(SelectItem _dialog) {
        _dialog.setVisible(true);
        return _dialog.facade.getLineItem() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectItem _dialog) {
        return _dialog.isSelected();
    }

    public static boolean isGive(SelectItem _dialog) {
        return _dialog.giveCheckBox.isSelected();
    }

    //    public static void setGive(boolean _give) {
//        DIALOG.giveCheckBox.setSelected(_give);
////        DIALOG.give = _give;
//    }
}
