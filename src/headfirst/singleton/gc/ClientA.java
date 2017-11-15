package headfirst.singleton.gc;

/**
 * Created by dhgate.com
 * User: liyazhou
 * Date: 2016/4/7
 * Time: 20:47
 */
class Singleton {
    //    private byte[] a = new byte[10*1024*1024];
    private static Singleton singleton = new Singleton();
    private byte[] a = new byte[6 * 1024 * 1024];

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }
}

class Obj {
    private byte[] a = new byte[3 * 1024 * 1024];
}

public class ClientA {
    public static void main(String[] args) throws Exception {
        Singleton.getInstance();
        while (true) {
            new Obj();
        }
    }
}
