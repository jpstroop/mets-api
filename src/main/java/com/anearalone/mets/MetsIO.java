/**
 * MetsIO.java
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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Abstract Class for {@link MetsReader} and {@link MetsWriter}
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Sep 2, 2010
 */
public abstract class MetsIO {
    protected static DocumentBuilderFactory dbfac;
    protected static DocumentBuilder docBuilder;
    protected static DatatypeFactory datatypeFactory;

    public MetsIO() throws ParserConfigurationException, DatatypeConfigurationException {
        dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setFeature("http://xml.org/sax/features/namespaces", true);
        docBuilder = dbfac.newDocumentBuilder();
    }

    /**
     * @return the dataTypeFactory
     */
    public static DatatypeFactory getDataTypeFactory() {
        if (datatypeFactory == null) {
            try {
                datatypeFactory = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return datatypeFactory;
    }

    /**
     * @return the docBuilder
     */
    public DocumentBuilder getDocBuilder() {
        return docBuilder;
    }

}
