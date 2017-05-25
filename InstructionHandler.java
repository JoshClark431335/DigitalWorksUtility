public class InstructionHandler{
    private static final String[] idList = {"R1", "R2", "R3", "R4", "R5", "R6", "R7", "MAR", "[MAR]",
        "IN"};
    private static final String[] destList = {"R1", "R2", "R3", "R4", "R5", "R6", "R7", "MAR", "[MAR]",
        "BUS"};
    private static final String[] registerList = {"R1", "R2", "R3", "R4", "R5", "R6",
        "R7"};
    private static final String[] opmList = {"+", "-", "&", "|", "^"};
    private static final String[] oprList = {"++", "--"};
    private static final String[] oplList = {"~", "<<", ">>"};

    public boolean canHandle(CodeLine cl){
        return false;
    }

    public ControlWord convert(CodeLine cl){
        return new ControlWord();
    }

    protected static boolean isID(String word){
        for (String id : idList){
            if (word.equals(id))
                return true;
        }
        return false;
    }

    protected static boolean isOPM(String word){
        for (String opm : opmList){
            if (word.equals(opm))
                return true;
        }
        return false;
    }

    protected static boolean isOPR(String word){
        for (String opr : oprList){
            if (word.equals(opr))
                return true;
        }
        return false;
    }

    protected static boolean isOPL(String word){
        for (String opl : oplList){
            if (word.equals(opl))
                return true;
        }
        return false;
    }

    protected static boolean isDest(String word){
        for (String dest : destList){
            if (word.equals(dest))
                return true;
        }
        return false;
    }

    protected static boolean isRegister(String word){
        for (String reg : registerList){
            if (word.equals(reg))
                return true;
        }
        return false;
    }

    protected static boolean isNum(String word){
        int num;
        try {
            num = Integer.parseInt(word);
            if (num < 0 || num > 255)
                return false;
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static String numToHex(String num){
        return String.format("%8s", Integer.toBinaryString(Integer.parseInt(num))).replace(
                    ' ', '0');
    }

    protected static String registerToBin(String word){
        String hex = "";
        for (int i = 1; i <= registerList.length; i++){
            if (word.equals(registerList[i-1])){
                if (i<4)
                    hex += "0";
                if (i<2)
                    hex += "0";
                hex += Integer.toBinaryString(i);
                return hex;
            }
        }
        return "0";
    }
}
