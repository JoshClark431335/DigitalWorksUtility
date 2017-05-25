import java.util.ArrayList;

class InstructionHandlerTest {
    private static ArrayList<InstructionHandler> handlers = new ArrayList<InstructionHandler>();

    public static void main(String args[]){
        handlers.add(new NOOPInstHandler());
        handlers.add(new ADDInstHandler());
        handlers.add(new SUBTInstHandler());
        handlers.add(new ANDInstHandler());
        handlers.add(new ORInstHandler());
        handlers.add(new XORInstHandler());
        handlers.add(new IncInstHandler());
        handlers.add(new DecInstHandler());
        handlers.add(new CompInstHandler());
        handlers.add(new SHLInstHandler());
        handlers.add(new SHRInstHandler());
        handlers.add(new TransInstHandler());
        handlers.add(new LoadInstHandler());
        handlers.add(new ClearInstHandler());
        handlers.add(new LoadMARInstHandler());
        /*
        System.out.print("NOOP test \"NOOP\": ");
        if (handler1.canHandle("NOOP"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("NOOP test \"R1 + R2\": ");
        if (handler1.canHandle("R1 + R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
            */
        
        /*
        System.out.print("OPM test \"NOOP\": ");
        if (handler2.canHandle("NOOP"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"R1 + R2\": ");
        if (handler2.canHandle("R1 + R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"R1 - R2\": ");
        if (handler2.canHandle("R1 - R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"R1 & R2\": ");
        if (handler2.canHandle("R1 & R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"R1 | R2\": ");
        if (handler2.canHandle("R1 | R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"R1 ^ R2\": ");
        if (handler2.canHandle("R1 ^ R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"MAR + R2\": ");
        if (handler2.canHandle("MAR + R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"[MAR] + R2\": ");
        if (handler2.canHandle("[MAR] + R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"IN + R2\": ");
        if (handler2.canHandle("IN + R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
        System.out.print("OPM test \"BUS + R2\": ");
        if (handler2.canHandle("BUS + R2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");    
            */

        CodeLine cl = new CodeLine();
        cl.setInst("NOOP");
        testCodeLine(cl);
        cl.setInst("R1    <- R1 + R2");
        testCodeLine(cl);
        cl.setInst("R2    <- R3 - R4");
        testCodeLine(cl);
        cl.setInst("BUS   <- R5 & R6");
        testCodeLine(cl);
        cl.setInst("[MAR] <- R7 | R1");
        testCodeLine(cl);
        cl.setInst("MAR   <- R2 ^ R3");
        testCodeLine(cl);
        cl.setInst("R1    <- R2 ++");
        testCodeLine(cl);
        cl.setInst("R2    <- R3 --");
        testCodeLine(cl);
        cl.setInst("R3    <- ~ R1");
        testCodeLine(cl);
        cl.setInst("R4    <- << R1");
        testCodeLine(cl);
        cl.setInst("R5    <- >> R1");
        testCodeLine(cl);
        cl.setInst("R6    <- R7");
        testCodeLine(cl);
        cl.setInst("R1    <- 6");
        testCodeLine(cl);
        cl.setInst("R2    <- CLR");
        testCodeLine(cl);
        cl.setInst("MAR   <- 251");
        testCodeLine(cl);
    }

    private static void testCodeLine(CodeLine cl){
        for (InstructionHandler handler : handlers){
            if (handler.canHandle(cl)){
                System.out.println("\"" + String.format("%-17s", cl.getInst()) + "\" converts to: " + handler.convert(cl));
                break;
            }
        }
    }
}
