package code.mock;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MockWithListSelectionSample extends MockAbsCommonFrame implements MockWithListSelection {

    private final MockComboBox graphicComboGrInt;
    private final MockCustGrList<String> graphicListString;
    private final MockCustGrList<String> input;
//    private final AbsGraphicListStr multList;
    private final AbsTextField textField;

    public MockWithListSelectionSample(AbstractProgramInfos _f, String _lgKey) {
        super(_lgKey);
        graphicComboGrInt = new MockGraphicComboBoxGenerator().createCombo(new StringList("0","1","2","3"),1);
        graphicComboGrInt.addListener(new MockListSelection(0,this));
        getContentPane().add(graphicComboGrInt.getGlobal());
        graphicListString = new MockGraphicStringListGenerator().createStrList(new StringList("4","5","6","7"));
        graphicListString.setListener(new MockListSelection(1, this));
        getContentPane().add(graphicListString.scroll());
        input = new MockGraphicStringListGenerator().createMultStrList(new StringList("8","9","10","11"),new Ints());
        input.setListener(new MockListSelection(2, this));
        getContentPane().add(input.getGlobal());
//        multList=_f.getGeneStrCompo().createMult(null, _f.getCompoFactory());
//        multList.addListener(new MockListSelection(3,this));
//        getContentPane().add(multList.scroll());
//        AbsGraphicListStr s_ = _f.getGeneStrCompo().createSimple(null, _f.getCompoFactory());
//        getContentPane().add(s_.scroll());
        textField = _f.getCompoFactory().newTextField();
        getContentPane().add(textField);
        pack();
    }

    @Override
    public void action(int _nb) {
        if (_nb == 0) {
            textField.setText(Long.toString(graphicComboGrInt.getSelectedIndex()));
        } else if (_nb == 1){
            textField.setText(list(graphicListString.getSelectedIndexes()));
        } else {
            textField.setText(list(input.getSelectedIndexes()));
//        } else {
//            textField.setText(list(multList.getSelectedIndexes()));
        }
    }

    public static Ints wrap(int[] _arr) {
        Ints out_ = new Ints();
        for (int i: _arr) {
            out_.add(i);
        }
        return out_;
    }

    private static String list(Ints _sel) {
        StringList out_ = new StringList();
        for (int i: _sel) {
            out_.add(Long.toString(i));
        }
        return StringUtil.join(out_,',');
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }

    public MockComboBox getGraphicComboGrInt() {
        return graphicComboGrInt;
    }

    public MockCustGrList<String> getGraphicListString() {
        return graphicListString;
    }

    public MockCustGrList<String> getInput() {
        return input;
    }

//    public AbsGraphicListStr getMultList() {
//        return multList;
//    }

    public String getText() {
        return textField.getText();
    }
}
