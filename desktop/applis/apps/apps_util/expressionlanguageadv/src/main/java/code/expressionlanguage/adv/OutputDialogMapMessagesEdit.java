package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class OutputDialogMapMessagesEdit {
    private final StringMap<String> messagesRows;
    private final AbsTextField key;
    private final AbsTextArea value;
    private final AbsPlainButton valPart;
    private final AutoCompleteDocument auto;
    private final AbsScrollPane scroll;

    public OutputDialogMapMessagesEdit(WindowCdmEditor _w, StringMap<String> _map, CustList<String> _keys) {
        messagesRows = OutputDialogMessages.initRows(_map, _keys);
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        AbsTextField keyAuto_ = factories_.getCompoFactory().newTextField(32);
        value = factories_.getCompoFactory().newTextArea("",1,32);
        auto = new AutoCompleteDocument(keyAuto_, new StringList(_keys), factories_, new FeedMessageValue(value, messagesRows));
        value.setLineBorder(GuiConstants.BLACK);
        key = keyAuto_;
        dels_.add(keyAuto_);
        AbsScrollPane inner_ = factories_.getCompoFactory().newAbsScrollPane(value);
        inner_.setPreferredSize(new MetaDimension(384,48));
        dels_.add(inner_);
        valPart = factories_.getCompoFactory().newPlainButton("MATCH");
        valPart.addActionListener(new ValidateMessage(keyAuto_,value, messagesRows));
        dels_.add(valPart);
        AbsScrollPane sc_ = factories_.getCompoFactory().newAbsScrollPane(dels_);
        sc_.setPreferredSize(new MetaDimension(384,128));
        scroll = sc_;
    }

    public StringMap<String> getMessagesRows() {
        return messagesRows;
    }
    public AbsScrollPane getScroll() {
        return scroll;
    }

    public AbsTextField getKey() {
        return key;
    }

    public AbsTextArea getValue() {
        return value;
    }

    public AbsPlainButton getValPart() {
        return valPart;
    }

    public AutoCompleteDocument getAuto() {
        return auto;
    }
}
