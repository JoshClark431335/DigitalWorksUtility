import java.util.ArrayList;

public class Example {

	public static void main(String args[]){
		new Example().go();

		/*
		String testString = "R1<-R2";

		System.out.println("0123456789");
		System.out.println(testString);
		int idx = testString.indexOf("<-");

		System.out.println("Dest: " + testString.substring(0,idx));
		System.out.println(idx);
		System.out.println("ID: " + testString.substring(idx+2));
		*/

	}

	private ArrayList<Validator> validators = new ArrayList<Validator>();

	public void go() {
		loadValidators();

		String myInstruction = "R7 <- R8";

		boolean result = hasNoErrors(myInstruction);

		if(result)
			System.out.println("Has no errors");
		else
			System.out.println("Has errors");
	}

	private boolean hasNoErrors(String instruction){
		boolean isCorrect = false;
		for(Validator v : validators){
			if(v.canValidate(instruction)){
				isCorrect = true;
				break;
			}
		}

		return isCorrect;
	}

	private void loadValidators() {
		validators.add(new NOOPValidator());
		validators.add(new SimpleTransferValidator());
	}

	abstract class Validator {
		public abstract boolean canValidate(String inst);

		protected boolean validDest(String dest){
			boolean isValid = false;

			if(dest.matches("R[1-7]")) isValid = true;
			else if (dest.equals("MAR")) isValid = true;
			else if (dest.equals("[MAR]")) isValid = true;
			else if (dest.equals("BUS")) isValid = true;

			return isValid;

		}

		protected boolean validID(String id){
			boolean isValid = false;

			if(id.matches("R[1-7]")) isValid = true;
			else if (id.equals("MAR")) isValid = true;
			else if (id.equals("[MAR]")) isValid = true;
			else if (id.equals("IN")) isValid = true;

			return isValid;
		}
	}

	class NOOPValidator extends Validator {
		public boolean canValidate(String inst){
			return inst.equalsIgnoreCase("noop");
		}
	}

	class SimpleTransferValidator extends Validator {
		public boolean canValidate(String inst){
			int idx = inst.indexOf("<-");
			if(idx == -1) return false;

			String dest = inst.substring(0, idx).trim();
			if(!validDest(dest)) return false;

			String id = inst.substring(idx + 2).trim();
			if(!validID(id)) return false;

			return true;	


		}
	}
}
