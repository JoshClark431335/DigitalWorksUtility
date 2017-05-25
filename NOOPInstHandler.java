public class NOOPInstHandler extends InstructionHandler{
    public boolean canHandle(CodeLine cl){
        if (cl.getInst().equals("NOOP")) {
            cl.setOperator("NOOP");
            return true;
        } else
            return false;
    }
    
    public ControlWord convert(CodeLine cl){
        ControlWord cw = new ControlWord();
        return cw;
    }
}
