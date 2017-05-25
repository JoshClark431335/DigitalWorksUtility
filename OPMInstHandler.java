public class OPMInstHandler extends InstructionHandler {
    public boolean canHandle(CodeLine cl){
        String words[];
        //checks if the inst is the form "word <- word word word"
        if (!cl.getInst().matches("\\S+\\s+<-\\s+\\S+\\s+\\S+\\s+\\S+"))
            return false;
        words = cl.getInst().split("\\s+");
        if (!isDest(words[0]))
            return false;
        if (!isID(words[2]))
            return false;
        if (!isOPM(words[3]))
            return false;
        if (!isID(words[4]) && !isNum(words[4]))
            return false;
        cl.setDest(words[0]);
        cl.setID1(words[2]);
        cl.setOperator(words[3]);
        cl.setID2(words[4]);
        return true;
    }

    public ControlWord convert(CodeLine cl){
        ControlWord cw = new ControlWord();

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
        if (isRegister(cl.getID1())){
            cw.setA(registerToBin(cl.getID1()));
        } else if (cl.getID1().equals("MAR")){
        } else if (cl.getID1().equals("[MAR]")){
        } else if (cl.getID1().equals("IN")){
        }
        if (isRegister(cl.getID2())){
            cw.setB(registerToBin(cl.getID2()));
        } else if (cl.getID2().equals("MAR")){
        } else if (cl.getID2().equals("[MAR]")){
        } else if (isNum(cl.getID2())){
            cw.setAddress(numToHex(cl.getID2()));
        }

        return cw;
    }
}
