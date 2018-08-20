import vfn.AbsBaseVNF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdderVNF extends AbsBaseVNF {

    private String toAdd;

    public AdderVNF(String toAdd, int port) {
        super(port);
        this.toAdd = toAdd;
    }

    public String functionality(String message) {
        Pattern pattern = Pattern.compile("Content-Length: \\d+");
        Matcher matcher = pattern.matcher(message);
        String match = "";
        while (matcher.find()) {
            match = matcher.group();
        }
        int length = Integer.parseInt(match.replaceAll("\\D+", "")) + toAdd.length();
        message = message.replaceAll("\r\n\r\n", "\r\n") + toAdd;
        message = message.replaceAll("Content-Length: \\d+", "Content-Length: " + length);
        return message;
    }

    public static void main(String[] args) {
        System.out.println("string to add: " + args[0]);
        System.out.println("port:          " + args[1]);
        new AdderVNF(args[0], Integer.parseInt(args[1])).execute();
    }
}