package code.sys;

import code.converterimages.main.LaunchingConverter;
import code.sys.impl.DefProgramInfos;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public final class LaunchingConverterSys extends LaunchingConverter {
    public LaunchingConverterSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingConverter.loadLaungage(_args,new LaunchingConverterSys());
    }
}
