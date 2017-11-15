package headfirst.proxy.gumball;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;

public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachineRemote gumballMachine = null;
        int count;
        if (args == null || args.length < 2) {
            args = new String[2];
            args[0] = "127.0.0.1:6600";
            args[1] = "10";
        }
//		if (args.length < 2) {
//			System.out.println("GumballMachine <name> <inventory>");
// 			System.exit(1);
//		}
        GumballMachine gumballMachine1 = null;
        try {
            count = Integer.parseInt(args[1]);
            LocateRegistry.createRegistry(6600);
            gumballMachine1 = new GumballMachine(args[0], count);
            gumballMachine = gumballMachine1;

//			Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachine);
            Naming.rebind("rmi://" + args[0] + "/gumballmachine", gumballMachine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(gumballMachine);

        gumballMachine1.insertQuarter();
        gumballMachine1.turnCrank();
        gumballMachine1.insertQuarter();
        gumballMachine1.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine1.insertQuarter();
        gumballMachine1.turnCrank();
        gumballMachine1.insertQuarter();
        gumballMachine1.turnCrank();

        System.out.println(gumballMachine);
    }
}
