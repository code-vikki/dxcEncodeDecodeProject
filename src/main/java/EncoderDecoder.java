
import java.util.*;

public class EncoderDecoder implements IEncoderDecoder{
    private char offSet;
    private static Map<Character, Integer> baseTable = Collections.unmodifiableMap(getBaseTable()) ;
    private static Map<Character, Integer> offSetTab;

    public EncoderDecoder(char offSet) {
        this.offSet = offSet;
        this.offSetTab =  this.deriveOffSetTable();
    }

    private Map<Character, Integer> deriveOffSetTable(){
        //TODO
        offSetTab = new HashMap<>(baseTable);
        Integer offSetValueForOriginalText = offSetTab.get(this.offSet);
        for (Map.Entry<Character, Integer> entry : offSetTab.entrySet()) {

            if ((entry.getValue() + offSetValueForOriginalText) <= 43) {
                entry.setValue((entry.getValue() + offSetValueForOriginalText));
            } else {
                Integer newInteger = (entry.getValue() + offSetValueForOriginalText) - 44;
                entry.setValue(newInteger);
            }
        }

        return offSetTab;

    }

    @Override
    public String decode(String encodedText) {

        char[] chars = encodedText.toUpperCase().toCharArray();

        List<Integer> intValofChar = new ArrayList<>();

        for (char c : chars) {
            Integer integer = offSetTab.get(c);
            intValofChar.add(integer);
        }


        List<Character> charOfList= new ArrayList<>();
        for (Integer integer:intValofChar){
            for (Map.Entry<Character, Integer> entry : baseTable.entrySet()) {
                if(integer==entry.getValue()){
                    Character key = entry.getKey();
                    charOfList.add(key);
                }
            }
        }

        int indexOfWhitespace;
        if(intValofChar.contains(null)){
            indexOfWhitespace = intValofChar.indexOf(null);
            charOfList.add(indexOfWhitespace,' ');
        }

        String decodedString=" ";
        for(char a: charOfList){
            decodedString+=a;
        }


        return decodedString.trim();

    }

    @Override
    public String encode(String plainText) {


        //hello
        char[] charArray = plainText.toUpperCase().toCharArray();

        List<Integer> valueInRefTable = new ArrayList<>();
        //h - 7. e-4, l- 11, l - 11, o -14
        for (char a : charArray) {
            Integer integer = baseTable.get(a);
            valueInRefTable.add(integer);
        }


        List<Character> encodedMessage = new ArrayList<>();
        for (int i = 0; i < valueInRefTable.size(); i++) {

            for (Map.Entry<Character, Integer> entry : offSetTab.entrySet()) {
                if (entry.getValue() == valueInRefTable.get(i))
                    encodedMessage.add(entry.getKey());
            }
        }

        if (plainText.contains(" ")) {
            encodedMessage.add(plainText.indexOf(" "), ' ');
        }

        String encodedString = " ";
        for (Character d : encodedMessage) {
            String s1 = d.toString();
            encodedString += s1;
        }

        return encodedString.trim();

    }


    private static Map<Character, Integer> getBaseTable() {
        Map<Character, Integer> referenceTable = new HashMap<>();
        Character alphaChar = 65;
        Character numChar = 48;
        for (int i = 0; i < 43; i++) {
            if (i <= 25) {
                referenceTable.put(alphaChar, i);
                alphaChar++;
            }
            if (i > 25 && i <= 35) {
                referenceTable.put(numChar, i);
                numChar++;
            }
        }
        referenceTable.put('(', 36);
        referenceTable.put(')', 37);
        referenceTable.put('*', 38);
        referenceTable.put('+', 39);
        referenceTable.put(',', 40);
        referenceTable.put('-', 41);
        referenceTable.put('.', 42);
        referenceTable.put('/', 43);
//        System.out.println(referenceTable);
        return referenceTable;
    }


}
