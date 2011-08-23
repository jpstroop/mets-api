/**
 * LocatorElement.java
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * Location fields common to <code>mets:FLocat</code>, <code>mets:mdRef</code>,
 * <code>mets:mptr</code>, and <code>mets:object</code>.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Sep 8, 2010
 */
public abstract class LocatorElement extends IDElement implements ElementInterface {

    protected ACTUATE xlinkActuate;
    protected String xlinkArcRole;
    protected String xlinkHREF;
    protected String xlinkRole;
    protected SHOW xlinkShow;
    protected String xlinkTitle;
    protected TYPE xlinkType;
    protected LOCTYPE loctype;
    protected String otherloctype;

    /**
     * Gets the <code>@xlink:type</code>
     * 
     * @return <code>@xlink:type</code>
     */
    public TYPE getXlinkType() {
        return this.xlinkType;
    }

    /**
     * Sets the value of <code>@xlink:type</code>. The value must be "simple" in order for the
     * record to be valid.
     * 
     * @param type
     * 
     */
    public void setXlinkType(TYPE type) {
        this.xlinkType = type;
    }

    /**
     * Gets the value of <code>@xlink:href</code>
     * 
     * @return the String value of <code>@xlink:href</code>
     */
    public String getXlinkHREF() {
        return xlinkHREF;
    }

    /**
     * Sets the value of <code>@xlink:href</code>
     * 
     * @param value
     */
    public void setXlinkHREF(String href) {
        this.xlinkHREF = href;
    }

    /**
     * Gets the value of <code>@xlink:role</code>
     * 
     * @return the String value of <code>@xlink:role</code>
     */
    public String getXlinkRole() {
        return xlinkRole;
    }

    /**
     * Sets the value of <code>@xlink:role</code>
     * 
     * @param role
     */
    public void setXlinkRole(String role) {
        this.xlinkRole = role;
    }

    /**
     * Gets the value of <code>@xlink:arcrole</code>
     * 
     * @return the String value of <code>@xlink:arcrole</code>
     */
    public String getXlinkArcRole() {
        return xlinkArcRole;
    }

    /**
     * Sets the value of <code>@xlink:arcrole</code>
     * 
     * @param role
     */
    public void setXlinkArcRole(String arcrole) {
        this.xlinkArcRole = arcrole;
    }

    /**
     * Gets the value of <code>@xlink:title</code>
     * 
     * @return the String value of <code>@xlink:title</code>
     */
    public String getXlinkTitle() {
        return xlinkTitle;
    }

    /**
     * Sets the value of <code>@xlink:title</code>
     * 
     * @param title
     */
    public void setXlinkTitle(String title) {
        this.xlinkTitle = title;
    }

    /**
     * Gets the {@link SHOW} enum object for <code>@xlink:show</code>
     * 
     * @return a {@link SHOW} instance
     */
    public SHOW getXlinkShow() {
        return xlinkShow;
    }

    /**
     * Sets <code>@SHOW</code>
     * 
     * @param show
     */
    public void setXlinkShow(SHOW show) {
        this.xlinkShow = show;
    }

    /**
     * Gets the {@link ACTUATE} enum object for <code>@xlink:actuate</code>
     * 
     * @return a {@link ACTUATE} instance
     */
    public ACTUATE getXlinkActuate() {
        return xlinkActuate;
    }

    /**
     * Sets <code>@ACTUATE</code>
     * 
     * @param actuate
     */
    public void setXlinkActuate(ACTUATE actuate) {
        this.xlinkActuate = actuate;
    }

    /**
     * Gets the {@link LOCTYPE} enum object for <code>@LOCTYPE</code>
     * 
     * @return a {@link LOCTYPE} instance
     */
    public LOCTYPE getLOCTYPE() {
        return loctype;
    }

    /**
     * Sets <code>@LOCTYPE</code>
     * 
     * @param actuate
     */
    public void setLOCTYPE(LOCTYPE loctype) {
        this.loctype = loctype;
    }

    /**
     * Gets the value of <code>@OTHERLOCTYPE</code>
     * 
     * @return the String value of <code>@OTHERLOCTYPE</code>
     */
    public String getOTHERLOCTYPE() {
        return otherloctype;
    }

    /**
     * Sets the value of <code>@OTHERLOCTYPE</code>
     * 
     * @param otherloctype
     */
    public void setOTHERLOCTYPE(String otherloctype) {
        this.otherloctype = otherloctype;
    }

    @Override
    public void marshal(Element e, Document doc) {
        super.marshal(e, doc);
        String xns = NS.XLINK.ns(); // to keep lines short.

        if (this.xlinkType != null) {
            Attr xtype = doc.createAttributeNS(xns, "xlink:type");
            xtype.setNodeValue(this.xlinkType.value());
            e.setAttributeNode(xtype);
        }
        if (this.xlinkHREF != null) {
            Attr xhref = doc.createAttributeNS(xns, "xlink:href");
            xhref.setNodeValue(this.xlinkHREF);
            e.setAttributeNode(xhref);
        }
        if (this.xlinkRole != null) {
            Attr xrole = doc.createAttributeNS(xns, "xlink:role");
            xrole.setNodeValue(this.xlinkRole);
            e.setAttributeNode(xrole);
        }
        if (this.xlinkArcRole != null) {
            Attr xArcRole = doc.createAttributeNS(xns, "xlink:arcrole");
            xArcRole.setNodeValue(this.xlinkArcRole);
            e.setAttributeNode(xArcRole);
        }
        if (this.xlinkTitle != null) {
            Attr xtitle = doc.createAttributeNS(xns, "xlink:title");
            xtitle.setNodeValue(this.xlinkTitle);
            e.setAttributeNode(xtitle);
        }
        if (this.otherloctype != null)
            e.setAttribute("OTHERLOCTYPE", this.otherloctype);

        if (this.xlinkShow != null) {
            Attr show = doc.createAttributeNS(xns, "xlink:show");
            show.setNodeValue(this.xlinkShow.value());
            e.setAttributeNode(show);
        }
        if (this.xlinkActuate != null) {
            Attr actuate = doc.createAttributeNS(xns, "xlink:actuate");
            actuate.setNodeValue(this.xlinkActuate.value());
            e.setAttributeNode(actuate);
        }
        if (this.loctype != null)
            e.setAttribute("LOCTYPE", this.loctype.value());
    }

    @Override
    public void unmarshal(Element e) {
        super.unmarshal(e);
        NamedNodeMap attrs = e.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            String name = attr.getName();
            String value = attr.getNodeValue();
            if (name.equals("xlink:type"))
                this.xlinkType = TYPE.fromValue(value);
            if (name.equals("xlink:href"))
                this.xlinkHREF = value;
            if (name.equals("xlink:role"))
                this.xlinkRole = value;
            if (name.equals("xlink:arcrole"))
                this.xlinkArcRole = value;
            if (name.equals("xlink:title"))
                this.xlinkTitle = value;
            if (name.equals("OTHERLOCTYPE"))
                this.otherloctype = value;
            if (name.equals("xlink:show"))
                this.xlinkShow = SHOW.fromValue(value);
            if (name.equals("xlink:actuate"))
                this.xlinkActuate = ACTUATE.fromValue(value);
            if (name.equals("LOCTYPE"))
                this.loctype = LOCTYPE.fromValue(value);
        }

    }

    /**
     * Enumeration of values for <code>@ACTUATE</code>
     */
    public enum ACTUATE {

        ON_LOAD("onLoad"), //
        ON_REQUEST("onRequest"), //
        OTHER("other"), //
        NONE("none"); //
        private final String value;

        ACTUATE(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static ACTUATE fromValue(String v) {
            for (ACTUATE c : ACTUATE.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }

    /**
     * Enumeration of values for <code>@LOCTYPE</code>
     */
    public enum LOCTYPE {
        ARK, //
        URN, //
        URL, //
        PURL, //
        HANDLE, //
        DOI, //
        OTHER;
        public String value() {
            return name();
        }

        public static LOCTYPE fromValue(String v) {
            return valueOf(v);
        }
    }

    /**
     * Enumeration of values for <code>@SHOW</code>
     */
    public enum SHOW {
        NEW("new"), //
        REPLACE("replace"), //
        EMBED("embed"), //
        OTHER("other"), //
        NONE("none");
        private final String value;

        SHOW(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static SHOW fromValue(String v) {
            for (SHOW c : SHOW.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }

    /**
     * Enumeration of values for <code>@xlink:type</code>
     */
    public enum TYPE {
        SIMPLE("simple");

        private final String value;

        TYPE(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static TYPE fromValue(String v) {
            for (TYPE c : TYPE.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }

}
