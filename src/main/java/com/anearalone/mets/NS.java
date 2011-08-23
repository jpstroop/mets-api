/**
 * MetsWriter.java
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
 * Enumeration providing constants for namespaces and schema locations. All interaction is done via
 * Strings, as that's how the field values are most likely to be used.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since June 30, 2009
 * 
 */
public enum NS {
    /**
     * <p>
     * Provides a constant for the Dublin Core namespace (http://purl.org/dc/elements/1.1/)
     */
    DC("http://purl.org/dc/elements/1.1/"),
    /**
     * <p>
     * Provides a constant for the Dublin Core Terms namespace (http://purl.org/dc/terms/)
     */
    DCTERMS("http://purl.org/dc/terms/"),
    /**
     * <p>
     * Provides a constant for the EAD namespace (urn:isbn:1-931666-22-9) and the related schema
     * (http://www.loc.gov/ead/ead.xsd)
     */
    EAD("urn:isbn:1-931666-22-9", "http://www.loc.gov/ead/ead.xsd"),
    /**
     * <p>
     * Provides a constant for the METS namespace (http://www.loc.gov/METS/) and the related schema
     * (http://www.loc.gov/standards/mets/mets.xsd)
     * 
     */
    METS("http://www.loc.gov/METS/", "http://www.loc.gov/standards/mets/mets.xsd"),
    /**
     * <p>
     * Provides a constant for the MODS Version 3.x namespace (http://www.loc.gov/mods/v3) and the
     * most recent related schema (http://www.loc.gov/standards/mets/mets.xsd)
     * 
     */
    MODS("http://www.loc.gov/mods/v3", "http://www.loc.gov/standards/mods/v3/mods.xsd"),
    /**
     * <p>
     * Provides a constant for the PREMIS Version 2.x namespace (info:lc/xmlns/premis-v2) and the
     * most recent related schema (http://www.loc.gov/standards/premis/premis.xsd)
     * 
     */
    PREMIS("info:lc/xmlns/premis-v2", "http://www.loc.gov/standards/premis/premis.xsd"),
    /**
     * <p>
     * Provides a constant for the RDF namespace (http://www.w3.org/1999/02/22-rdf-syntax-ns#)
     * 
     */
    RDF("http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
    /**
     * <p>
     * Provides a constant for the TEI namespace (http://www.tei-c.org/ns/1.0)
     * 
     */
    TEI("http://www.tei-c.org/ns/1.0"),
    /**
     * <p>
     * Provides a constant for the VRA Core 4 namespace (http://www.vraweb.org/vracore4.htm) and the
     * most recent related schema (http://www.vraweb.org/projects/vracore4/vra-4.0-restricted.xsd)
     * 
     */
    VRA_4("http://www.vraweb.org/vracore4.htm", "http://www.vraweb.org/projects/vracore4/vra-4.0-restricted.xsd"),
    /**
     * <p>
     * Provides a constant for the VRA Core 3 namespace (http://www.tei-c.org/ns/1.0) and the most
     * recent related schema (http://www.vraweb.org/projects/vracore4/vra-4.0-restricted.xsd)
     * 
     */
    VRA_3("http://www.vraweb.org/vracore3.htm"),
    /**
     * <p>
     * Provides a constant for the XLINK namespace (http://www.w3.org/1999/xlink)
     * 
     */
    XLINK("http://www.w3.org/1999/xlink"),
    /**
     * <p>
     * Provides a constant for the XSI namespace (http://www.w3.org/2001/XMLSchema-instance)
     * 
     */
    XSI("http://www.w3.org/2001/XMLSchema-instance"),
    /**
     * <p>
     * Provides a constant for the XMP namespace (adobe:ns:meta/)
     * 
     */
    XMP("adobe:ns:meta/");

    private String ns;
    private String schema;

    private NS(String ns) {
        this.ns = ns;
    };

    private NS(String ns, String schemaLoc) {
        this.ns = ns;
        this.schema = schemaLoc;
    };

    /**
     * @return a String representing the namespace URI associated with the object.
     */
    public String ns() {
        return this.ns;
    };

    /**
     * @return a String representing the namespace URI associated with the object.
     */
    public String value() {
        return this.ns;
    };

    /**
     * @return A String representing the URL for the schema, if one exists, else null.
     */
    public String loc() {
        return this.schema;
    }

    // will this work?
    /**
     * @return An String to be used as a value for {@code xsi:schemaLocation} if a schema exists,
     *         else null.
     */
    public String schemaLoc() {
        if (this.schema != null)
            return this.ns + " " + this.schema;
        else
            return null;
    }

    public static NS fromNamespace(String namespace) {
        for (NS ns : NS.values()) {
            if (ns.ns.equals(namespace)) {
                return ns;
            }
        }
        throw new IllegalArgumentException(namespace);
    }

}
