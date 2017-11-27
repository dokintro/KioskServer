package server.utility;

import server.config.Config;

public class Crypter {

    // XOR-kypteringsmetoden: (CR)
    //https://github.com/KyleBanks/XOREncryption/blob/master/Java%20(Android%20compatible)/XOREncryption.java

    /**
     *
     * @param input
     * @return Input
     */
        public static String encryptDecrypt(String input) {

        if (Config.getEncryption()) {
            char[] key = {'J', 'M', 'F'};
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {
                output.append((char) (input.charAt(i) ^ key[i % key.length]));
            }
            return output.toString();
        } else {
            return input;
        }
    }
}





