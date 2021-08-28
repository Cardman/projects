package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbsFrameFactory;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class SelectEgg extends SelectDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selectegg";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private FacadeGame facade;

//    private boolean ok;

    private StringMap<String> messages;
    private final AbsCompoFactory compo;

    public SelectEgg(AbsFrameFactory _frameFactory, AbsCompoFactory _compoFactory) {
        super(_frameFactory);
        getSelectDial().setAccessFile(DIALOG_ACCESS);
        compo = _compoFactory;
    }

    public static void setSelectEgg(WindowAiki _parent, FacadeGame _facade, SelectEgg _dialog) {
        _dialog.init(_parent, _facade);
    }

    private void init(WindowAiki _parent, FacadeGame _facade) {
        getSelectDial().setDialogIcon(_parent.getImageFactory(),_parent);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getSelectDial().getAccessFile());
        getSelectDial().setTitle(messages.getVal(TITLE));
        facade = _facade;
        initOk();
//        ok = false;
        AbsPanel contentPane_ = compo.newBorder();
        AbsPanel pag_ = compo.newPageBox();
        contentPane_.add(compo.newAbsScrollPane(new PaginatorEgg(_parent,pag_, getSelectDial(), _facade).getContainer()), BorderLayout.CENTER);
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
        facade.clearFiltersEgg();
        getSelectDial().closeWindow();
    }

    public static void setVisible(SelectEgg _dialog) {
        _dialog.getSelectDial().setVisible(true);
    }

    public static boolean isSelectedIndex(SelectEgg _dialog) {
        setVisible(_dialog);
        return _dialog.facade.getLineEgg() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static boolean isOk(SelectEgg _dialog) {
        return _dialog.isSelected();
    }

}
