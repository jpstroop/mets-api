/**
 * MetsReader.java
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

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Creates METS objects from InputStreams. A given instance can, and should, be reused.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Aug 23, 2010
 */
public class MetsReader extends MetsIO {

    public MetsReader() throws ParserConfigurationException, DatatypeConfigurationException {
        super();
    }

    /**
     * @param in
     * @return
     * @throws SAXException
     *             When the InputStream cannot be parsed as XML
     * @throws IOException
     *             If any IO errors occur
     * @throws ParseException
     *             If ay attributes with date values cannot be parsed. See {@link MetsIO#dateFormat}
     *             and its related methods.
     */
    public Mets read(InputStream in) throws SAXException, ParseException, IOException {
        Mets mets = new Mets();
        Document doc = docBuilder.parse(in);
        mets.unmarshal(doc.getDocumentElement());
        return mets;
    }

    public static List<String> parseIDREFAttr(String value) {
        List<String> values = new ArrayList<String>();
        for (String token : value.split("\\s"))
            values.add(token);
        return values;
    }

}
