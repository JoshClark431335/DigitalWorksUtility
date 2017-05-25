public class LoadInstHandler extends InstructionHandler {
    public boolean canHandle(CodeLine cl){
        String words[];
        int num;
        //checks if the inst is the form "word <- word"
        if (!cl.getInst().matches("\\S+\\s+<-\\s+\\S+"))
            return false;
        words = cl.getInst().split("\\s+");
        if (!isDest(words[0]))
            return false;
        try {
            num = Integer.parseInt(words[2]);
        } catch (NumberFormatException nfe){
            return false;
        }
        if (num < 0 || num > 255)
            return false;
        cl.setDest(words[0]);
        cl.setID1(words[2]);
        cl.setOperator("Load");
        return true;
    }

    public ControlWord convert(CodeLine cl){
        ControlWord cw = new ControlWord();
        cw.setOperation("001000");

        if (isRegister(cl.getDest())){
            cw.setDest(registerToBin(cl.getDest()));
        } else if (cl.getDest().equals("MAR")){
            String mem = cw.getMemOps();
            cw.setMemOps("1000");
        } else if (cl.getDest().equals("[MAR]")){
            cw.setMemOps("0010");
        } else if (cl.getDest().equals("BUS")){
            cw.setMemOps("0000");
        }
        cw.setAddress(String.format("%8s", Integer.toBinaryString(Integer.parseInt(cl.getID1()))).replace(
                    ' ', '0'));
        return cw;
    }
}
