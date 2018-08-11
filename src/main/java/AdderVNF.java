import vfn.AbsBaseVNF;

public class AdderVNF extends AbsBaseVNF {

    private String toAdd;

    public AdderVNF(String toAdd, int port) {
        super(port);
        this.toAdd = toAdd;
    }

    public String functionality(String message) {
        return message.replaceAll("\r\n\r\n", "\r\n") + toAdd;
    }

    public static void main(String[] args) {
        System.out.println("string to add: " + args[0]);
        System.out.println("port:          " + args[1]);
        new vfn.testvnfs.AddStringVNF(args[0], Integer.parseInt(args[1])).execute();
    }
}