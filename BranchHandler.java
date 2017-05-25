public class BranchHandler {
    public boolean canHandle(CodeLine cl){
        String words[];
        if (!cl.getBranch().matches("\\S+\\s+\\w+"))
            return false;
        words = cl.getBranch().split("\\s+");
        cl.setCondition(words[0]);
        cl.setTarget(words[1]);
        return true;
    }

    public ControlWord convert(ControlWord cw){
        return cw;
    }
}
