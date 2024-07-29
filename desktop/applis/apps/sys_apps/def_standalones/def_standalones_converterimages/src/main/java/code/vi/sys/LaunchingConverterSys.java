package code.vi.sys;

import applications.code.converterimages.main.LaunchingConverter;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingConverterSys extends LaunchingConverter {
    public LaunchingConverterSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingConverterSys().loadLanguage(TEMP_FOLDER,_args);
    }
}
