/**
 * SharedEnums.java
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
package com.anearalone.mets;

/**
 * A set of enumerations that are shared across different parts of the METS record.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Sep 6, 2010
 */
public class SharedEnums {
    /**
     * Enumeration of possible values for <code>@CHECKSUMTYPE</code>
     */
    public enum CHECKSUMTYPE {
        ADLER_32("Adler-32"), //
        CRC_32("CRC32"), //
        HAVAL("HAVAL"), //
        MD_5("MD5"), //
        MNP("MNP"), //
        SHA_1("SHA-1"), //
        SHA_256("SHA-256"), //
        SHA_384("SHA-384"), //
        SHA_512("SHA-512"), //
        TIGER("TIGER"), //
        WHIRLPOOL("WHIRLPOOL");

        private final String value;

        CHECKSUMTYPE(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static CHECKSUMTYPE fromValue(String v) {
            for (CHECKSUMTYPE c : CHECKSUMTYPE.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
    }

}