public class LoadMARInstHandler extends InstructionHandler{
    public boolean canHandle(CodeLine cl){
        String words[];
        int num;
        //checks if the inst is the form "word <- word"
        if (!cl.getInst().matches("\\S+\\s+<-\\s+\\S+"))
            return false;
        words = cl.getInst().split("\\s+");
        if (!words[0].equals("MAR"))
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
        return true;
    }
    
    public ControlWord convert(CodeLine cl){
        ControlWord cw = new ControlWord();

        cw.setMemOps("1100");
        cw.setAddress(String.format("%8s", Integer.toBinaryString(Integer.parseInt(cl.getID1()))).replace(
                    ' ', '0'));
        return cw;
    }
}
