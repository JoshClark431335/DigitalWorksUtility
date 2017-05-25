public class BUNBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BUN"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("001");
        return cw;
    }
}
