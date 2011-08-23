/**
 * ElementInterface.java
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

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Aug 24, 2010
 */
public interface ElementInterface {
    /**
     * Reconstitutes classes based on a DOM Element.
     * 
     * @param e
     * @throws ParseException
     *             Throws a ParseException when attributes cannot be parsed.
     * @see {@link MetsIO#setDateFormat(String)}
     */
    public void unmarshal(Element e) throws ParseException;

    /**
     * Structures fields into a DOM Element.
     * 
     * @param d
     * @return
     */
    /*
     * Implementation note: by having the calling class create the root element for the object, we
     * can recycle classes that have the same elements and attributes, and only a different root tag
     * name. The MdSecs are the best case for this, but also recordID and metsDocumentID.
     */
    public void marshal(Element e, Document d);
}
