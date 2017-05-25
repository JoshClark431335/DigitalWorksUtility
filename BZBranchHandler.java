public class BZBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BZ"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("100");
        return cw;
    }
}
