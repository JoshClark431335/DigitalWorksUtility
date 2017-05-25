public class CodeLine {
    private String label;
    private String inst;
    private String branch;
    private int lineNo;
    private String wholeText;

    private String dest;
    private String id1, id2;
    private String operator;
    private String condition;
    private String target;

    public void setWholeText(String newText){
        wholeText = newText;
    }

    public String getWholeText(){
        return wholeText;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String newLabel){
        label = newLabel;
    }

    public void setLineNo(int newLineNo){
        lineNo = newLineNo;
    }

    public int getLineNo(){
        return lineNo;
    }

    public String getInst(){
        return inst;
    }

    public void setInst(String newInst){
        inst = newInst;
    }

    public String getBranch(){
        return branch;
    }

    public void setBranch(String newBranch){
        branch = newBranch;
    }

    public String getDest(){
        return dest;
    }

    public void setDest(String newDest){
        dest = newDest;
    }

    public String getID1(){
        return id1;
    }

    public void setID1(String newID1){
        id1 = newID1;
    }

    public String getID2(){
        return id2;
    }

    public void setID2(String newID2){
        id2 = newID2;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String newOperator){
        operator = newOperator;
    }

    public String getCondition(){
        return condition;
    }

    public void setCondition(String newCondition){
        condition = newCondition;
    }

    public String getTarget(){
        return target;
    }

    public void setTarget(String newTarget){
        target = newTarget;
    }
}
