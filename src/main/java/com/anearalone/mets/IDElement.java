/**
 * IDElement.java
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Superclass for any (i.e., all) METS elements that allow <code>@ID</code>.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Aug 26, 2010
 */
public abstract class IDElement implements ElementInterface {

    protected String id;

    /**
     * Gets the value of <code>@ID</code>
     * 
     * @return the String value of <code>@ID</code>
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of <code>@ID</code>
     * 
     * @param id
     *            the String value for <code>@ID</code>
     */
    public void setID(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.anearalone.mets.METSElementIf#marshal(org.w3c.dom.Element, org.w3c.dom.Document)
     */
    @Override
    public void marshal(Element e, Document d) {
        if (this.id != null)
            e.setAttribute("ID", this.id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.anearalone.mets.METSElementIf#unmarshal(org.w3c.dom.Element)
     */
    @Override
    public void unmarshal(Element e) {
        this.id = e.hasAttribute("ID") ? e.getAttribute("ID") : null;
    }

}
