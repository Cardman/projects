package code.vi.prot.impl.variant;

import code.vi.prot.impl.LightProgramInfos;

public final class LigDefOtherProgramInfos extends LightProgramInfos {
    public LigDefOtherProgramInfos() {
        super(new DefAdvGraphicStringListGenerator(), new DefAdvGraphicComboBoxGenerator());
    }
}
