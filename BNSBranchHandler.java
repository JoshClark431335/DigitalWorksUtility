public class BNSBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BNS"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("111");
        return cw;
    }
}
