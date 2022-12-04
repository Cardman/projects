package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatRendElem;
import code.bean.nat.exec.blocks.NatRendForm;
import code.sml.NavigationCore;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.Longs;
import code.util.StringList;

public final class NatImportingPageForm extends NatImportingPageAbs {

    public void removeRendLastBlockSt() {
        NatAbstractStask last_ = getRendBlockStacks().last();
        if (last_ instanceof NatIfStack) {
            if (((NatIfStack)last_).getBlock() instanceof NatRendElem) {
                getRendReadWrite().setWrite(NavigationCore.getParentNode(getRendReadWrite()));
            }
            if (((NatIfStack)last_).getBlock() instanceof NatRendForm) {
                CustList<LongTreeMap<NatNodeContainer>> map_ = ((NatRendReadWriteAdv)getRendReadWrite()).getConf().getContainersMapStack();
                Longs formsNb_ = ((NatRendReadWriteAdv)getRendReadWrite()).getConf().getFormsNb();
                long nb_ = formsNb_.last();
                LongTreeMap<NatNodeContainer> containers_ = map_.last();
                ((NatRendReadWriteAdv)getRendReadWrite()).getConf().getContainersMap().put(nb_, containers_);
                CustList<StringList> formatId_ = ((NatRendReadWriteAdv)getRendReadWrite()).getConf().getFormatIdMapStack();
                StringList fid_ = formatId_.last();
                ((NatRendReadWriteAdv)getRendReadWrite()).getConf().getFormatIdMap().put(nb_,fid_);
                ((NatRendReadWriteAdv)getRendReadWrite()).getConf().getInputs().removeLast();
                map_.removeQuicklyLast();
                formatId_.removeQuicklyLast();
                formsNb_.removeQuicklyLast();
            }
        }
        getRendBlockStacks().removeQuicklyLast();
    }
    public NatImportingPageAbs fwd() {
        return new NatImportingPageForm();
    }

    @Override
    public NatRendReadWrite newNatRendReadWrite(NatRendStackCall _rendStackCall) {
        NatRendReadWriteAdv rw_ = new NatRendReadWriteAdv();
        rw_.setConf(((NatRendStackCallAdv)_rendStackCall).getFormParts());
        return rw_;
    }
}
