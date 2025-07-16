package aiki.gui.components.editor;

import code.threads.*;

public final class EditorValidateDataTask implements Runnable {
    private final WindowPkEditor window;

    public EditorValidateDataTask(WindowPkEditor _w) {
        this.window = _w;
    }


    @Override
    public void run() {
        int count_ = 0;
        if (window.getRenderDataWeb().getCommonFrame().isVisible()) {
            count_++;
        }
        if (window.getRenderDataWebSimu().getCommonFrame().isVisible()) {
            count_++;
        }
        if (count_ > 0) {
            window.getModal().set(false);
            window.getValidateDataSet().setEnabled(true);
            return;
        }
        AbstractAtomicIntegerCoreAdd p_ = window.getThreadFactory().newAtomicInteger();
        window.getLoadFlag().set(true);
        window.validateData(p_);
        window.getLoadFlag().set(false);
        window.getValidateDataSet().setEnabled(true);
    }

}
