import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderDecoderTest {

    @Test
    public void encode(){
        EncoderDecoder encoderDecoder = new EncoderDecoder('B');
        String encodeText = encoderDecoder.encode("HELLO WORLD");
        assertEquals("GDKKN VNQKC", encodeText);
    }

    @Test
    public void encodeWithMoreThanOneEmptySpace(){
        EncoderDecoder encoderDecoder = new EncoderDecoder('B');
        String encodeText = encoderDecoder.encode("HOW ARE YOU");
        assertEquals("GNV /QD XNT", encodeText);
    }

    @Test
    public void should_be_case_insenstive(){
        EncoderDecoder encoderDecoder = new EncoderDecoder('B');
        String encodeText = encoderDecoder.encode("hello world");
        assertEquals("GDKKN VNQKC", encodeText);
    }

    @Test
    public void decode(){
        EncoderDecoder encoderDecoder = new EncoderDecoder('B');
        String decodedText = encoderDecoder.decode("GDKKN VNQKC");
        assertEquals("HELLO WORLD", decodedText);
    }


    @Test
    public void decodeAfterEncode(){
        EncoderDecoder encoderDecoder = new EncoderDecoder('F');
        String originalText = "MY PASS";

        String encodeText = encoderDecoder.encode(originalText);
        assertEquals("HT K+NN", encodeText);
        String decodedText = encoderDecoder.decode(encodeText);
        assertEquals(originalText, decodedText);
    }


    @Test
    public void enodeAfterDecode(){
        EncoderDecoder encoderDecoder = new EncoderDecoder('F');
        String originalText = "HT K+NN";

        String decodedText = encoderDecoder.decode(originalText);
        assertEquals("MY PASS", decodedText);
        String  encodedText = encoderDecoder.encode(decodedText);
        assertEquals(originalText, encodedText);
    }
}
