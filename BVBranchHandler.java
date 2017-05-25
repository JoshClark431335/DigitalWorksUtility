public class BVBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BV"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("011");
        return cw;
    }
}
