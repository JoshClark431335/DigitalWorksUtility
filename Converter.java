import java.util.ArrayList;

public class Converter {
    private static String binToHex(String bin){
        String chunk = "";
        int decimal;
        String hex = "";
        String fullHex = "";
        for (int i = 0; i < bin.length()-7; i+=8){
            chunk = bin.substring(i, i+8);
            decimal = Integer.parseInt(chunk, 2);
            hex = Integer.toString(decimal, 16);
            fullHex += String.format("%2s", hex).replace(" ", "0");
        }
        return fullHex;
    }

    public static String rearrange(String hex){
        String newHex = "";
        String A = "";
        String B = "";
        newHex = "CDAB000000010000";
        for (int i = 0; i < hex.length(); i+=4){
            A = hex.substring(i  ,i+2);
            B = hex.substring(i+2,i+4);
            newHex += B + A + "0000";
        }
        for (int i = newHex.length(); i < 2064; i+=8){
            newHex += "00000000";
        }
        /*for (int i = 0; i < newHex.length(); i+=8){
            System.out.println(newHex.substring(i,i+8));
        }*/
        return newHex;
    }

    public static String convert(String part){
        String bin = "";
        String hex;
        String arrangedHex;
        String ascii;

        hex = binToHex(part);
        arrangedHex = rearrange(hex);

        return arrangedHex;
    }

    public static String[] split(ArrayList<ControlWord> controlWords){
        String bin = "";
        String parts[] = {"", ""};
        for (ControlWord cw : controlWords){
            parts[0] += cw.toString().substring(0,16);
            parts[1] += cw.toString().substring(16);
        }
        return parts;
    }

    public static void main(String args[]){
        String hex =
            binToHex("0111010001101000011010010111001100100000011010010111001100100000011000010010000001110100011001010111001101110100");
        System.out.println(hex);
        
        Converter conv = new Converter();
        System.out.println(conv.rearrange("12345678"));
    }
}
