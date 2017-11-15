package headfirst.proxy.gumball;

import java.rmi.Naming;
import java.util.concurrent.TimeUnit;

public class GumballMonitorTestDrive {

    public static void main(String[] args) {
//		String[] location = {"rmi://santafe.mightygumball.com/gumballmachine",
//		                     "rmi://boulder.mightygumball.com/gumballmachine",
//		                     "rmi://seattle.mightygumball.com/gumballmachine"};
        String[] location = {"rmi://127.0.0.1:6600/gumballmachine"};
        GumballMonitor[] monitor = new GumballMonitor[location.length];

        for (int i = 0; i < location.length; i++) {
            try {
                GumballMachineRemote machine =
                        (GumballMachineRemote) Naming.lookup(location[i]);
                monitor[i] = new GumballMonitor(machine);
                System.out.println(monitor[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < monitor.length; i++) {
            monitor[i].report();
        }
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < monitor.length; i++) {
            monitor[i].report();
        }
    }
}
