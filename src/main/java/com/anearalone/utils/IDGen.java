/**
 * IDGen.java 
 * 
 * Copyright 2011 Jon Stroop
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.anearalone.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A simple class for generating random alphanumeric Strings. A given instance will not produce the
 * same string twice, and the first character will always be a letter (thus suitable for XML IDs).
 * 
 * This will only scale so far, since we're using {@link HashSet} to keep track of what's been
 * generated. It works fine for several hundred before it starts to slow.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Aug 22, 2010
 */
public class IDGen {
    private static String[] alphaNums;
    private static Random random;
    private int length;
    private Set<String> collection;

    /**
     * Create a new ID generator.
     * 
     * @param len
     *            The length of the IDs to be produced (e.g. 5 could result in "f5436")
     */
    public IDGen(int len) {
        alphaNums = new String[36];
        random = new Random();
        this.collection = new HashSet<String>();
        this.length = len;
        buildAlphaNumArray();
    }

    /*
     * @see http://xkcd.com/530/
     */
    private static void buildAlphaNumArray() {
        int c = 0;
        int codepoint = 97;
        // a-z
        while (codepoint <= 122) {
            alphaNums[c] = Character.toString((char) codepoint);
            c++;
            codepoint++;
        }
        // 0-9
        codepoint = 48;
        while (codepoint <= 57) {
            alphaNums[c] = Character.toString((char) codepoint);
            c++;
            codepoint++;
        }
    }

    /**
     * @param length
     *            the length of the string to be generated.
     * @return a random string of the specied length.
     */
    public String mint() {
        String str = "";
        boolean added;
        do {
            for (int c = 0; c < this.length; c++) {
                if (c == 0) // always a letter in the first position
                    str = alphaNums[random.nextInt(26)];
                else
                    str = str + alphaNums[random.nextInt(36)];
            }
            added = this.collection.add(str);
        } while (!added); // keep doing until we get a unique string

        return str;
    }

    /**
     * Empties the set of generated IDs.
     */
    public void reset() {
        this.collection.clear();
    }

}
