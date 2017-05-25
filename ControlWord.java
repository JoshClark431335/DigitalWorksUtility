/**
 * 
 */
//package fileConverter;

/**
 * @author josh
 *
 */
public class ControlWord {
	private String A, B;
	private String dest;
	private String operation;
    private String branch;
    private String address;
    private String memOps;

    ControlWord(){
        A = "000";
        B = "000";
        dest = "000";
        operation = "000000";
        branch = "000";
        address = "00000000";
        memOps = "0000";
    }
	
    /**
     * @return the ControlWord as one contiguous string
     */
	public String toString(){
        return (A + B + dest + operation + branch + address + memOps +
                "00");
        //return A + " " + B + " " + dest + " " + operation + " " + branch + " " + address + " " + memOps + " 00";
	}

	/**
	 * @return the A binary
	 */
	public String getA() {
		return A;
	}

	/**
	 * @param A the A binary to set
	 */
	public void setA(String newA) {
		A = newA;
	}

	/**
	 * @return the B binary
	 */
	public String getB() {
		return B;
	}

	/**
	 * @param newB the B binary to set
	 */
	public void setB(String newB) {
		B = newB;
	}

	/**
	 * @return the destination
	 */
	public String getDest() {
		return dest;
	}

	/**
	 * @param destination the destination binary to set
	 */
	public void setDest(String newDest) {
		dest = newDest;
	}

	/**
	 * @return the ALU operation binary
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the ALU operation binary to set
	 */
	public void setOperation(String newOperation) {
		operation = newOperation;
	}

    /**
     * @return the branch binary
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param newBranch the new branch binary to set
     */
    public void setBranch(String newBranch) {
        branch = newBranch;
    }

    /**
     * @return the address binary
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param newAddress the new address binary to set
     */
    public void setAddress(String newAddress) {
        address = newAddress;
    }

    /**
     * @return the memOps binary
     */
    public String getMemOps() {
        return memOps;
    }

    /**
     * @param newMemOps the new memOps binary to set
     */
    public void setMemOps(String newMemOps){
        memOps = newMemOps;
    }
}
