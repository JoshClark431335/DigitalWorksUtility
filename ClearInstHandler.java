public class ClearInstHandler extends InstructionHandler {
    public boolean canHandle(CodeLine cl){
        String words[];
        int num;
        //checks if the inst is the form "word <- word"
        if (!cl.getInst().matches("\\S+\\s+<-\\s+\\S+"))
            return false;
        words = cl.getInst().split("\\s+");
        if (!isDest(words[0]))
            return false;
        if (!words[2].equals("CLR"))
            return false;
        cl.setDest(words[0]);
        cl.setOperator("Clear");
        return true;
    }

    public ControlWord convert(CodeLine cl){
        ControlWord cw = new ControlWord();
        cw.setOperation("001001");

        if (isRegister(cl.getDest())){
            cw.setDest(registerToBin(cl.getDest()));
        } else if (cl.getDest().equals("MAR")){
            String mem = cw.getMemOps();
            cw.setMemOps("11" + mem.substring(2));
        } else if (cl.getDest().equals("[MAR]")){
            cw.setMemOps("0010");
        } else if (cl.getDest().equals("BUS")){
            cw.setMemOps("0000");
        }
        return cw;
    }
}
