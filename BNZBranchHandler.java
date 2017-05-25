public class BNZBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BNZ"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("101");
        return cw;
    }
}
