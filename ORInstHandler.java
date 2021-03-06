public class ORInstHandler extends OPMInstHandler {
    public boolean canHandle(CodeLine cl){
        if (!super.canHandle(cl))
            return false;
        return (cl.getOperator().equals("|"));
    }
    public ControlWord convert(CodeLine cl){
        ControlWord cw = super.convert(cl);
        cw.setOperation("010100");
        return cw;
    }
}
