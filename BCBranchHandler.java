public class BCBranchHandler extends BranchHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getCondition().equals("BC"));
    }

    public ControlWord convert(ControlWord cw){
        cw.setBranch("010");
        return cw;
    }
}
