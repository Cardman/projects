package aiki.beans;

import aiki.beans.facade.simulation.dto.*;
import aiki.beans.game.*;

public final class BeanDisplaySelectLineMove implements BeanDisplayEltGrid<SelectLineMove> {
    @Override
    public int displayEltGrid(CommonBean _rend, SelectLineMove _info) {
        _rend.formatMessageDirCts(_info.getTranslatedKey().getTranslation());
        BeanDisplayMoveLine.displayLine(_rend,_info);
        _rend.initLine();
        _info.setSelected(DifficultyBeanForm.check(_rend.getBuilder().getGenInput(),_rend, _info.isSelected()));
        _rend.feedParentsCts();
        return 10;
    }
}
