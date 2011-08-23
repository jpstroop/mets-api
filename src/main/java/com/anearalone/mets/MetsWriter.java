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

import java.util.List;
import java.io.File;
import java.io.OutputStream;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Factory class for writing out METS objects. A given instance may be reused as necessary.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Aug 23, 2010
 */

public class MetsWriter extends MetsIO {
    private static Document doc;
    private static TransformerFactory xfac;
    private static Transformer xformer;

    public MetsWriter() throws DatatypeConfigurationException, ParserConfigurationException {
        super();
        xfac = TransformerFactory.newInstance();
        try {
            xformer = xfac.newTransformer();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
        }
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
    }

    public void writeToFile(Mets mets, File file) throws TransformerException {

        doc = docBuilder.newDocument();
        Element root = doc.createElementNS(NS.METS.ns(), "mets:mets");
        mets.marshal(root, doc);
        doc.appendChild(root);
        // Prepare the DOM document for writing
        Source source = new DOMSource(doc);
        // Prepare the output file

        Result result = new StreamResult(file);
        // Write the DOM document to the file
        xformer.transform(source, result);

        docBuilder.reset();
    }

    public void writeToOutputStream(Mets mets, OutputStream out) throws TransformerException {

        doc = docBuilder.newDocument();
        Element root = doc.createElementNS(NS.METS.ns(), "mets:mets");
        mets.marshal(root, doc);
        doc.appendChild(root);
        // Prepare the DOM document for writing
        Source source = new DOMSource(doc);
        // Prepare the output file

        Result result = new StreamResult(out);
        // Write the DOM document to the file
        xformer.transform(source, result);

        docBuilder.reset();
    }

    public static String listToString(List<String> strings) {
        String value = "";
        for (int c = 0; c < strings.size(); c++) {
            if (c > 0)
                value = value + " ";
            value = value + strings.get(c);
        }
        return value;
    }

}
