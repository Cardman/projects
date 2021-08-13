package code.gui;

import code.util.core.StringUtil;

public class TaskPaintingLabel implements Runnable {

    private static final int DELTA = 100;

    private static final int SECOND_MILLIS = 1000;

    private static final String UNIT = " s";

    private static final String SEPARATOR = " - ";

    private static final String PERCENT = " %";

//    private PaintingLabel painting;
//    private AnimatedLabel label;
    private ProgressDialog dialog;
    private int time;

//    public TaskPaintingLabel(AnimatedLabel _label, JDialog _dialog) {
//        label = _label;
//        dialog = _dialog;
//    }

//    public TaskPaintingLabel(){
//    }

    public TaskPaintingLabel(ProgressDialog _dialog) {
//        label = _dialog.getAnim();
        dialog = _dialog;
    }

    @Override
    public void run() {
//        if (isPainting()) {
//            return;
//        }
        time += DELTA;
        if (dialog.getPercent().isEmpty()) {
            dialog.setTitle(StringUtil.concat(Long.toString(time/SECOND_MILLIS),UNIT));
        } else {
            dialog.setTitle(StringUtil.concat(Long.toString(time/SECOND_MILLIS),UNIT,SEPARATOR,dialog.getPercent(),PERCENT));
        }
//        painting = new PaintingLabel(label);
//        painting.start();
    }

//    public boolean isPainting() {
//        return painting != null && painting.isAlive();
//    }

    public ProgressDialog getDialog() {
        return dialog;
    }

    public void setDialog(ProgressDialog _dialog) {
        dialog = _dialog;
    }

//    public void setLabel(AnimatedLabel _label) {
//        label = _label;
//    }
}
