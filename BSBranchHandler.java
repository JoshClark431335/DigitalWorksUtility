public class BSBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BS"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("110");
        return cw;
    }
}
