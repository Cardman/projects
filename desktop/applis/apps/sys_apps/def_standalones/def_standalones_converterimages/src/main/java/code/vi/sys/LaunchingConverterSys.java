package code.vi.sys;

import code.converterimages.main.LaunchingConverter;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingConverterSys extends LaunchingConverter {
    public LaunchingConverterSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingConverter.loadLaungage(_args,new LaunchingConverterSys());
    }
}
