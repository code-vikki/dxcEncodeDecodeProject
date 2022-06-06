
import java.util.*;

public class EncoderDecoder implements IEncoderDecoder {
    private char offSet;
    private static Map<Character, Integer> baseTable = Collections.unmodifiableMap(getBaseTable());
    private static Map<Character, Integer> offSetTab;

    public EncoderDecoder(char offSet) {
        this.offSet = offSet;
        this.offSetTab = this.deriveOffSetTable();
    }

    private Map<Character, Integer> deriveOffSetTable() {
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
            int e = integer == null ? c : integer;
            intValofChar.add(e);
        }

        List<Character> charOfList = new ArrayList<>();
        Character characterOfEmptySpace = null;

        for (int i = 0; i < intValofChar.size(); i++) {

            Integer integer = intValofChar.get(i);

            for (Map.Entry<Character, Integer> entry : baseTable.entrySet()) {

                if(baseTable.containsKey(chars[i]) && entry.getValue()==integer){
                    charOfList.add(entry.getKey());
                }
                if(!baseTable.containsKey(chars[i])){
                   charOfList.add((char)integer.intValue());
                   break;
                }

            }
        }


        String decodedString = " ";
        for (char a : charOfList) {
            decodedString += a;
        }

        if (characterOfEmptySpace == null) {
            return decodedString.trim();
        } else {
            return decodedString.replace(characterOfEmptySpace, ' ').trim();
        }

    }


    @Override
    public String encode(String plainText) {
        //hello
        char[] charArray = plainText.toUpperCase().toCharArray();

        List<Integer> valueInRefTable = new ArrayList<>();
        //h - 7. e-4, l- 11, l - 11, o -14
        for (char a : charArray) {
            Integer integer = baseTable.get(a);
            int e = integer == null ? a : integer;
            valueInRefTable.add(e);
        }

        List<Character> encodedMessage = new ArrayList<>();
        Character stringofEmptySpace = null;

        for (int i = 0; i < valueInRefTable.size(); i++) {

            Integer integer = valueInRefTable.get(i); //7 , 64

               for(Map.Entry<Character,Integer> entry: offSetTab.entrySet()){

                   if(offSetTab.containsKey(charArray[i]) && entry.getValue()==integer){
                       encodedMessage.add(entry.getKey());
                   }
                   if(!offSetTab.containsKey(charArray[i])){
                       encodedMessage.add((char)integer.intValue());
                       break;
                   }

               }
        }

        String encodedString = " ";
        for (Character d : encodedMessage) {
            String s1 = d.toString();
            encodedString += s1;
        }

        if (stringofEmptySpace == null) {
            return encodedString.trim();
        } else {
            return encodedString.replace(stringofEmptySpace, ' ').trim();
        }

    }


        private static Map<Character, Integer> getBaseTable () {
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

