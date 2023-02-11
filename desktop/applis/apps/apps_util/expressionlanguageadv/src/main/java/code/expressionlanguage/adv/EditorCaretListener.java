package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DefaultFileEscapedCalc;
import code.expressionlanguage.common.FileMetrics;
import code.gui.events.AbsCaretListener;
import code.util.core.StringUtil;

public final class EditorCaretListener implements AbsCaretListener {
    private final TabEditor tabEditor;

    public EditorCaretListener(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void caretUpdate(int _begin, int _end) {
        FileBlock fb_ = new FileBlock(0, false, "", new DefaultFileEscapedCalc());
        fb_.metrics(tabEditor.getCenter().getText());
        FileMetrics m_ = fb_.getMetrics(tabEditor.getWindowEditor().getSpinner().getValue());
        int i_ = tabEditor.getCenter().getCaretPosition();
        String selected_ = StringUtil.nullToEmpty(tabEditor.getCenter().getSelectedText());
        tabEditor.getLabel().setText(formatLci(m_,i_,selected_));
        if (!tabEditor.getFinderPanel().isVisible()) {
            tabEditor.getFactories().getCompoFactory().invokeLater(new UpdatingEditorQuick(tabEditor));
            return;
        }
        tabEditor.getFactories().getCompoFactory().invokeLater(new UpdatingEditor(tabEditor));
    }
    static String formatLci(FileMetrics _metrics, int _index, String _selected) {
        int l_ = _metrics.getRowFile(_index);
        int c_ = _metrics.getColFile(_index,l_);
        if (_selected.isEmpty()) {
            return ":"+l_+","+c_+","+_index;
        }
        return ":"+l_+","+c_+","+_index+":"+_selected.length();
    }
}
