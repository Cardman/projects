package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class ReportingFrame {
    private final AbsCommonFrame commonFrame;
    private final AbsTxtComponent report;
    public ReportingFrame(AbstractProgramInfos _api) {
        this(_api.getFrameFactory().newCommonFrame("",_api,null), _api.getCompoFactory().newWrappedTextArea(4, 32));
    }
    public ReportingFrame(AbsCommonFrame _fr, AbsTxtComponent _report) {
        commonFrame = _fr;
        report = _report;
    }
    public static ReportingFrame newInstance(AbstractProgramInfos _api) {
        ReportingFrame r_ = new ReportingFrame(_api);
        r_.init(_api);
        return r_;
    }
    public void init(AbstractProgramInfos _api) {
        commonFrame.setContentPane(_api.getCompoFactory().newAbsScrollPane(report));
    }
    public void display(String _title, String _txt) {
        getCommonFrame().setTitle(_title);
        getReport().setText(_txt);
        commonFrame.pack();
        commonFrame.setVisible(true);
    }

    public AbsTxtComponent getReport() {
        return report;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }
}
