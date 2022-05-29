import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter message to encode: ");
        String s = scanner.nextLine().toUpperCase();

        System.out.print("Enter character to offset by: ");
        char offSetEncode = scanner.nextLine().toUpperCase().charAt(0);

        EncoderDecoder encoder = new EncoderDecoder(offSetEncode);
        String encodedMessage = encoder.encode(s);

        System.out.println(s + " is encoded to " + encodedMessage);


        System.out.print("Enter message to decode: ");
        String s1 = scanner.nextLine().toUpperCase();

        System.out.print("Enter character to offset by: ");
        char offSetDecode = scanner.nextLine().toUpperCase().charAt(0);

        EncoderDecoder decoder = new EncoderDecoder(offSetDecode);

        String decodedMessage = decoder.decode(s1);
        System.out.println(s1 + " is decoded to " + decodedMessage);

    }
}
