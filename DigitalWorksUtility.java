/**
 * 
 */
//package fileConverter;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author josh
 *
 */
public class DigitalWorksUtility {
    private ArrayList<ControlWord> commandList;
    private String line = "";
    private String filename = "";
    private FileReaderUtility fru;
    private ArrayList<String> labels = new ArrayList<String>();
    private ArrayList<CodeLine> codeLines = new ArrayList<CodeLine>();
    private ArrayList<ControlWord> controlWords = new ArrayList<ControlWord>();
    private ArrayList<InstructionHandler> instHandlers;
    private ArrayList<BranchHandler> branchHandlers;
    private String binaryFormatA = "";
    private String binaryFormatB = "";

    public DigitalWorksUtility(String filename){
        this.filename = filename;
        //System.out.println(filename);
    }

    private char checkForInvalidChar(String line){
        String validChars =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ[]0123456789+-&|^<-:, ";
        char c;

        for (int i = 0; i < line.length(); i++){
            c = line.charAt(i);
            if (validChars.indexOf(c) == -1) {
                return c;
            }
        }
        return 'a';
    }

    /*
    private boolean checkMajorSyntax(){
        int lineNo = 0;
        char c;
        boolean valid = true;

        lines = new ArrayList<String>();
        while ((line = fru.getNextLine()) != null) {
            lineNo++;
            //remove comments if applicable
            line = line.split("//")[0];
            lines.add(line);
            //System.out.println(line);
            //check for invalid characters in general
            if ((c = checkForInvalidChar(line)) != 'a'){
                System.err.println("Line " + lineNo + ": Invalid character '" + c +
                        "'.");
                valid = false;
                continue;
            }
            //check for multiple colons
            if (line.matches("[\\s\\S]*:([\\s\\S]*:)+[\\s\\S]*")){
                System.err.println("Line " + lineNo + ": Only one colon is allowed.");
                valid = false;
                continue;
            }
            //check for multiple commas
            if (line.matches("[\\s\\S]*,([\\s\\S]*,)+[\\s\\S]*")) {
                System.err.println("Line " + lineNo + ": Only one comma is allowed.");
                valid = false;
                continue;
            }
            //check for comma before colon
            if (line.matches("[\\s\\S]*,[\\s\\S]*:[\\s\\S]*")){
                System.err.println("Line " + lineNo + ": Comma must come before colon.");
                valid = false;
                continue;
            }
            //check for missing label
            if (line.matches("\\s*:[\\s\\S]*")){
                System.err.println("Line " + lineNo + ": Missing label.");
                valid = false;
                continue;
            }
            //check for multiple labels
            if (line.matches("\\s*\\w+(\\s+\\w+)+\\s*:[\\s\\S]*")){
                System.err.println("Line " + lineNo + ": Only one label allowed per"
                        + " line.");
                valid = false;
                continue;
            }
            //check for labels made of non alphanumeric + underscore characters
            if (line.matches("(\\w|\\s)*[^\\w\\s]+(\\w|\\s)*:[\\s\\S]*")){
                System.err.println("Line " + lineNo + ": Invalid character in label.");
                valid = false;
                continue;
            }
            //check for no words after comma
            if (line.matches("[\\s\\S]*,\\s*")){
                System.err.println("Line " + lineNo + ": Missing branch condition and"
                        + " label.");
                valid = false;
                continue;
            }
            //check for one word after comma
            if (line.matches("[\\s\\S]*,\\s*\\w+\\s*")){
                System.err.println("Line " + lineNo + ": Missing branch condition or"
                        + " label.");
                valid = false;
                continue;
            }
            //check for three or more words after comma
            if (line.matches("[\\s\\S]*,\\s*\\w+(\\s+\\w+){2,}\\s*")){
                System.err.println("Line " + lineNo + ": Only one label allowed per"
                        + " branch condition.");
                valid = false;
                continue;
            }
            checkBranchSyntax(line, lineNo);
            if (checkLabelSyntax(line, lineNo)){
                valid = false;
                continue;
            }
            if (checkBranchSyntax(line, lineNo)){
                valid = false;
                continue;
            }
            if (checkInstSyntax(line, lineNo)){
                valid = false;
                continue;
            }
        }
        return valid;
    }

    private boolean checkLabelSyntax(String line, int lineNo){
        //check for invalid characters
        if (line.matches("[\\s\\S]*([^\\s\\w]+[\\s\\S]*)+:[\\s\\S]*")){
            System.err.println("Line " + lineNo + ": Only alphanumeric characters and the"
                    + " underscore allowed in label.");
            return false;
        }
        //check for multiple words
        if (line.matches("\\s*(\\w+\\s+)+\\w+\\s*:[\\s\\S]*")){
            System.err.println("Line " + lineNo + ": Only one label allowed per line.");
            return false;
        }
        return true;
    }

    private boolean checkBranchSyntax(String line, int lineNo){
        String condition;
        String target;
        String branch;
        int targetIndex = 0;
        boolean invalidCond = false;

        if (line.split(",").length == 1){
            return true;
        }
        branch = line.split(",")[1].trim();
        for (targetIndex = branch.length() - 1; branch.charAt(targetIndex) != ' '; targetIndex--) {}
        condition = branch.substring(0, targetIndex).trim();
        target = branch.substring(targetIndex).trim();

        for (String cond : branchConditions){
            if (condition.equals(cond))
                invalidCond = true;
        }
        if (!invalidCond){
            System.out.println("Line " + lineNo + ": Invalid branch condition.");
            return false;
        }
        if (!target.matches("\\w+")){
            System.out.println("Line " + lineNo + ": Only alphanumeric characters and the"
                    + "underscore allowed in branch target.");
            return false;
        }
        return true;
    }

    private boolean checkInstSyntax(String line, int lineNo){
        return true;
    }
    */

    public void parseLines(){
        String label;
        String inst;
        String branch;
        int colonIndex = 0;
        int commaIndex = 0;
        String line;
        CodeLine cl;
        int lineNo = 0;

        loadHandlers();
        while ((line = fru.getNextLine()) != null) {
            lineNo++;
            cl = new CodeLine();
            cl.setLineNo(lineNo);
            cl.setWholeText(line);
            if (line.equals("")){
                codeLines.add(cl);
                continue;
            }
            label = "";
            inst = "";
            branch = "";
            //remove comments if applicable
            line = line.split("//")[0].trim();
            colonIndex = line.indexOf(":");
            commaIndex = line.indexOf(",");
            if (colonIndex != -1){
                label = line.substring(0,colonIndex);
                if (commaIndex != -1){
                    inst = line.substring(colonIndex+1,commaIndex);
                    branch = line.substring(commaIndex+1);
                } else {
                    inst = line.substring(colonIndex+1);
                }
            } else {
                if (commaIndex != -1){
                    inst = line.substring(0,commaIndex);
                    branch = line.substring(commaIndex+1);
                } else {
                    inst = line;
                }
            }
            cl.setLabel(label.trim());
            labels.add(label.trim());
            cl.setInst(inst.trim());
            cl.setBranch(branch.trim());
            codeLines.add(cl);
            convertToCW(cl);
        }
        addBranchTargets();
        convertToBin();
        createMapFiles();
    }

    private void createMapFiles(){
        try {
            FileOutputStream fos = new FileOutputStream("mapA.map");
            fos.write(hexStringToByteArray(binaryFormatA));
            fos.close();

            fos = new FileOutputStream("mapB.map");
            fos.write(hexStringToByteArray(binaryFormatB));
            fos.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    private void convertToBin(){
        String parts[];
        parts = Converter.split(controlWords);
        binaryFormatA = Converter.convert(parts[0]);
        binaryFormatB = Converter.convert(parts[1]);
        /*System.out.println(binaryFormatA);
        System.out.println();
        System.out.println(binaryFormatB);*/
    }

    private void convertToCW(CodeLine cl){
        boolean passed = false;
        String target;
        int targetIndex;
        ControlWord cw = new ControlWord();
        for (InstructionHandler handler : instHandlers){
            if (handler.canHandle(cl)){
                cw = handler.convert(cl);
                passed = true;
                break;
            }
        }
        if (!passed) {
            System.out.println(String.format("Line %2d: Could not convert instruction",
                        cl.getLineNo()));
            System.out.println("\t\"" + cl.getWholeText() + "\"");
        }
        passed = false;
        for (BranchHandler handler : branchHandlers){
            if (handler.canHandle(cl)){
                cw = handler.convert(cw);
                passed = true;
                break;
            }
        }
        if (!passed && !(cl.getBranch() == ""))
            System.out.println(String.format("Line %2d: Could not convert branch",
                        cl.getLineNo()));
            System.out.println("\t\"" + cl.getWholeText() + "\"");
        controlWords.add(cw);
    }

    private void addBranchTargets(){
        String target;
        int targetIndex;
        ControlWord cw;
        CodeLine cl;

        for (int i = 0; i < codeLines.size(); i++){
            cl = codeLines.get(i);
            cw = controlWords.get(i);
            target = cl.getTarget();
            if (target != null){
                targetIndex = labels.indexOf(target);
                if (targetIndex != -1) {
                    cw.setAddress(String.format("%8s", Integer.toBinaryString(targetIndex)).replace(
                                ' ', '0'));
                } else {
                    System.out.println("Could not find target");
                }
            }
        }
    }

    public boolean loadFile(){
        fru = new FileReaderUtility();
        if (!fru.readFile(filename)){
            System.err.println("Unable to read file: " + filename);
            return false;
        }
        return true;
    }

    private void loadHandlers(){
        instHandlers = new ArrayList<InstructionHandler>();
        instHandlers.add(new NOOPInstHandler());
        instHandlers.add(new ADDInstHandler());
        instHandlers.add(new SUBTInstHandler());
        instHandlers.add(new ANDInstHandler());
        instHandlers.add(new ORInstHandler());
        instHandlers.add(new XORInstHandler());
        instHandlers.add(new IncInstHandler());
        instHandlers.add(new DecInstHandler());
        instHandlers.add(new CompInstHandler());
        instHandlers.add(new SHLInstHandler());
        instHandlers.add(new SHRInstHandler());
        instHandlers.add(new TransInstHandler());
        instHandlers.add(new LoadInstHandler());
        instHandlers.add(new ClearInstHandler());
        instHandlers.add(new LoadMARInstHandler());

        branchHandlers = new ArrayList<BranchHandler>();
        branchHandlers.add(new BCBranchHandler());
        branchHandlers.add(new BNSBranchHandler());
        branchHandlers.add(new BNZBranchHandler());
        branchHandlers.add(new BSBranchHandler());
        branchHandlers.add(new BUNBranchHandler());
        branchHandlers.add(new BVBranchHandler());
        branchHandlers.add(new BZBranchHandler());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Proper Usage: java DigitalWorksUtility <filename>");
            System.exit(1);
        }

        DigitalWorksUtility dwu = new DigitalWorksUtility(args[0]);
        if (!dwu.loadFile()){
            System.err.println("Cannot load file.");
            System.exit(2);
        }
        dwu.parseLines();
    }
}
