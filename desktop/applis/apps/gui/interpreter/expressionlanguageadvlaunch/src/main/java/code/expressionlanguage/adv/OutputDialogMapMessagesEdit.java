package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class OutputDialogMapMessagesEdit {
    private final StringMap<String> messagesRows;
    private final AbsTextField key;
    private final AbsTextArea value;
    private final AbsButton valPart;
    private final AbsPlainLabel valPartLabel;
    private final AutoCompleteDocument auto;
    private final AbsScrollPane scroll;

    public OutputDialogMapMessagesEdit(WindowWithTreeImpl _w, StringMap<String> _map, CustList<String> _keys) {
        messagesRows = initRows(_map, _keys);
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        AbsTextField keyAuto_ = factories_.getCompoFactory().newTextField(32);
        value = factories_.getCompoFactory().newTextArea(AbsEditorTabList.EMPTY_STRING,1,32);
        valPartLabel = factories_.getCompoFactory().newPlainLabel(AbsEditorTabList.EMPTY_STRING);
        value.addAutoComplete(new DirectValidateKeyValueEvent(new ValidateMessage(_w,keyAuto_, value, messagesRows,valPartLabel,false)));
        auto = new AutoCompleteDocument(keyAuto_, new StringList(_keys), factories_, new FeedMessageValue(value, messagesRows, valPartLabel));
        value.setLineBorder(GuiConstants.BLACK);
        key = keyAuto_;
        dels_.add(keyAuto_);
        AbsScrollPane inner_ = factories_.getCompoFactory().newAbsScrollPane(value);
        inner_.setPreferredSize(new MetaDimension(384,48));
        dels_.add(inner_);
        valPart = factories_.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(MessagesIde.valMessages(factories_.currentLg()).getVal(MessagesIde.IDE_MESSAGES_VALIDATE)));
        valPart.setVisible(!_w.softParams().isDirectMatchKeyValue());
        valPart.addActionListener(new ValidateMessage(_w,keyAuto_, value, messagesRows,valPartLabel,true));
        dels_.add(valPart);
        valPartLabel.setVisible(!_w.softParams().isDirectMatchKeyValue());
        dels_.add(valPartLabel);
        AbsScrollPane sc_ = factories_.getCompoFactory().newAbsScrollPane(dels_);
        sc_.setPreferredSize(new MetaDimension(384,128));
        scroll = sc_;
    }
    public void reinit(WindowWithTreeImpl _w) {
        valPart.setVisible(!_w.softParams().isDirectMatchKeyValue());
        valPartLabel.setVisible(!_w.softParams().isDirectMatchKeyValue());
    }

    static StringMap<String> initRows(StringMap<String> _infos, CustList<String> _keys) {
        StringMap<String> messagesRows_ = new StringMap<String>();
        for (String k:_keys){
            int index_ = _infos.indexOfEntry(k);
            if (index_ < 0) {
                messagesRows_.addEntry(k,AbsEditorTabList.EMPTY_STRING);
            } else {
                messagesRows_.addEntry(k,_infos.getValue(index_));
            }
        }
        return messagesRows_;
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

    public AbsButton getValPart() {
        return valPart;
    }

    public AbsPlainLabel getValPartLabel() {
        return valPartLabel;
    }

    public AutoCompleteDocument getAuto() {
        return auto;
    }
}
