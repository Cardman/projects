package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorItem;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbsFrameFactory;
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
    private final AbsCompoFactory compo;

    public SelectItem(AbsFrameFactory _frameFactory, AbsCompoFactory _compoFactory) {
        super(_frameFactory);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _compoFactory;
    }

    public static void setSelectItem(WindowAiki _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        _parent.getSelectItem().init(_parent, _facade, _buy, _sell);
    }

    private void init(WindowAiki _parent, FacadeGame _facade, boolean _buy, boolean _sell) {
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages.getVal(TITLE));
        facade = _facade;
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorItem(_parent,pag_, getSelectDial(), _facade, !_sell).getContainer()), BorderLayout.CENTER);
        AbsPanel buttons_ = compo.newLineBox();
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
        facade.clearFiltersItem();
        getSelectDial().closeWindow();
    }

    public static boolean isSelectedIndex(SelectItem _dialog) {
        _dialog.getSelectDial().setVisible(true);
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
