package code.sys;

import code.converterimages.main.LaunchingConverter;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public class LaunchingConverterSys extends LaunchingConverter {
    public LaunchingConverterSys() {
        super(new ProgramInfos(new GraphicStringListGenerator(), new GraphicComboBoxGenerator()));
    }
}
