/**
 * MdSec.java
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

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.anearalone.mets.SharedEnums.CHECKSUMTYPE;
import com.anearalone.utils.DOMHelp;

/**
 * From the METS Schema: A generic framework for pointing to/including metadata within a METS
 * document, a la Warwick Framework.
 */
public class MdSec extends IDElement implements ElementInterface {
    protected MdRef mdRef;
    protected MdWrap mdWrap;
    protected String groupid;
    protected XMLGregorianCalendar created;
    protected String status;
    /**
     * From the METS Schema: <blockquote>
     * <p>
     * Contains the ID attribute values of the <techMD>, <sourceMD>, <rightsMD> and/or <digiprovMD>
     * elements within the <amdSec> of the METS document that contain administrative metadata
     * pertaining to the METS document itself. For more information on using METS IDREFS and IDREF
     * type attributes for internal linking, see Chapter 4 of the METS Primer.
     */
    protected List<String> admid;

    protected static DatatypeFactory dtf;

    /**
     * The METS Schema requires an ID.
     * 
     * @param id
     */
    public MdSec(String id) {
        this.id = id;
        if (dtf == null)
            dtf = MetsIO.getDataTypeFactory();
    }

    // Needed for METS unmarshalling. The MdSec(String id) constructor is
    // preferred.
    protected MdSec() {
        if (dtf == null)
            dtf = MetsIO.getDataTypeFactory();
    }

    /**
     * Gets the <code>mets:mdRef</code> child
     * 
     * @return a {@link MdRef} object representing this element
     */
    public MdRef getMdRef() {
        return mdRef;
    }

    /**
     * Sets the <code>mets:mdRef</code>> child
     * 
     * @param mdRef
     */
    public void setMdRef(MdRef mdRef) {
        this.mdRef = mdRef;
    }

    /**
     * Gets the <code>mets:mdWrap</code> child
     * 
     * @return a {@link MdWrap} object representing this element
     */
    public MdWrap getMdWrap() {
        return mdWrap;
    }

    /**
     * Sets the <code>mets:mdWrap</code> child
     * 
     * @param mdWrap
     */
    public void setMdWrap(MdWrap mdWrap) {
        this.mdWrap = mdWrap;
    }

    /**
     * Gets admid List
     * 
     * <p>
     * To add a new item, do as follows:
     * 
     * <pre>
     * getADMID().add(newItem);
     * </pre>
     */
    public List<String> getADMID() {
        if (admid == null) {
            admid = new ArrayList<String>();
        }
        return this.admid;
    }

    /**
     * Gets the value of <code>@GROUPID</code>
     * 
     * @return the String value of <code>@GROUPID</code>
     */
    public String getGROUPID() {
        return groupid;
    }

    /**
     * Sets the value of <code>@GROUPID</code>
     * 
     * @param groupid
     */
    public void setGROUPID(String groupid) {
        this.groupid = groupid;
    }

    /**
     * Gets the value of <code>@CREATED</code>
     * 
     * @return an {@link XMLGregorianCalendar} representing <code>@CREATED</code>
     */
    public XMLGregorianCalendar getCREATED() {
        return created;
    }

    /**
     * Sets the value of <code>@CREATED</code>
     * 
     * @param created
     */
    public void setCREATEDATE(XMLGregorianCalendar created) {
        this.created = created;
    }

    /**
     * Gets the value of <code>@STATUS</code>
     * 
     * @return the String value of <code>@STATUS</code>
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Sets the value of <code>@STATUS</code>
     * 
     * @param status
     */
    public void setSTATUS(String status) {
        this.status = status;
    }

    @Override
    public void marshal(Element mdSec, Document doc) {
        super.marshal(mdSec, doc);
        if (this.admid != null) {
            // can be a sequence of space-separated Strings
            Attr admid = doc.createAttribute("ADMID");
            admid.setNodeValue(MetsWriter.listToString(this.admid));
            mdSec.setAttributeNode(admid);
        }
        if (this.groupid != null)
            mdSec.setAttribute("GROUPID", this.groupid);
        if (this.status != null)
            mdSec.setAttribute("STATUS", this.status);
        if (this.created != null) {
            Attr created = doc.createAttribute("CREATED");
            created.setNodeValue(this.created.toXMLFormat());
            mdSec.setAttributeNode(created);
        }
        if (this.mdRef != null) {
            Element mdRef = doc.createElementNS(NS.METS.ns(), "mets:mdRef");
            this.mdRef.marshal(mdRef, doc);
            mdSec.appendChild(mdRef);
        }
        if (this.mdWrap != null) {
            Element mdWrap = doc.createElementNS(NS.METS.ns(), "mets:mdWrap");
            this.mdWrap.marshal(mdWrap, doc);
            mdSec.appendChild(mdWrap);
        }
    }

    @Override
    public void unmarshal(Element mdSec) {
        super.unmarshal(mdSec);
        NamedNodeMap attrs = mdSec.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            String name = attr.getName();
            String value = attr.getNodeValue();
            if (name.equals("GROUPID"))
                this.groupid = value;
            if (name.equals("STATUS"))
                this.status = value;
            if (name.equals("CREATED"))
                this.created = dtf.newXMLGregorianCalendar(value);
            if (name.equals("ADMID"))
                this.admid = MetsReader.parseIDREFAttr(value);
        }

        List<Element> children = DOMHelp.getChildElements(mdSec);
        for (Element child : children) {
            String localName = child.getLocalName();
            if (localName.equals("mdRef")) {
                this.mdRef = new MdRef();
                this.mdRef.unmarshal(child);
            }
            if (localName.equals("mdWrap")) {
                this.mdWrap = new MdWrap();
                this.mdWrap.unmarshal(child);
            }
        }
    }

    /**
     * Representation of a <code>mets:mdRef</code>.
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * The metadata reference element <mdRef> element is a generic element used throughout the METS
     * schema to provide a pointer to metadata which resides outside the METS document. NB: <mdRef>
     * is an empty element. The location of the metadata must be recorded in the xlink:href
     * attribute, supplemented by the XPTR attribute as needed.
     */
    public static class MdRef extends LocatorElement implements ElementInterface {
        protected String label;
        protected String xptr;
        protected MDTYPE mdtype;
        protected String othermdtype;
        protected String mdtypeversion;
        protected String mimetype;
        protected Long size;
        protected XMLGregorianCalendar created;
        protected String checksum;
        protected CHECKSUMTYPE checksumtype;
        protected static DatatypeFactory dtf;

        public MdRef(LOCTYPE loctype, MDTYPE mdtype) {
            this.loctype = loctype;
            this.mdtype = mdtype;
            if (dtf == null)
                dtf = MetsIO.getDataTypeFactory();
        }

        private MdRef() {
            if (dtf == null)
                dtf = MetsIO.getDataTypeFactory();
        }

        /**
         * Gets the value of <code>@LABEL</code>
         * 
         * @return the String value of <code>@LABEL</code>
         */
        public String getLabel() {
            return label;
        }

        /**
         * Sets the value of <code>@LABEL</code>
         * 
         * @param label
         *            the String value for <code>@LABEL</code>
         */
        public void setLabel(String label) {
            this.label = label;
        }

        /**
         * Gets the value of <code>@XPTR</code>
         * 
         * @return the String value of <code>@XPTR</code>
         */
        public String getXPTR() {
            return xptr;
        }

        /**
         * Sets the value of <code>@XPTR</code>
         * 
         * @param xptr
         *            the String value for <code>@XPTR</code>
         */
        public void setXPTR(String xptr) {
            this.xptr = xptr;
        }

        /**
         * Gets the {@link MDTYPE} enum object for <code>@MDTYPE</code>
         * 
         * @return a {@link MDTYPE} instance
         */
        public MDTYPE getMDTYPE() {
            return mdtype;
        }

        /**
         * Sets <code>@MDTYPE</code>
         * 
         * @param mdtype
         */
        public void setMDTYPE(MDTYPE mdtype) {
            this.mdtype = mdtype;
        }

        /**
         * Gets the value of <code>@OTHERMDTYPE</code>
         * 
         * @return the String value of <code>@OTHERMDTYPE</code>
         */
        public String getOTHERMDTYPE() {
            return othermdtype;
        }

        /**
         * Sets the value of <code>@OTHERMDTYPE</code>
         * 
         * @param othermdtype
         *            the String value for <code>@OTHERMDTYPE</code>
         */
        public void setOTHERMDTYPE(String othermdtype) {
            this.othermdtype = othermdtype;
        }

        /**
         * Gets the value of <code>@MDTYPEVERSION</code>
         * 
         * @return the String value of <code>@MDTYPEVERSION</code>
         */
        public String getMDTYPEVERSION() {
            return mdtypeversion;
        }

        /**
         * Sets the value of <code>@MDTYPEVERSION</code>
         * 
         * @param mdtypeversion
         *            the String value for <code>@MDTYPEVERSION</code>
         */
        public void setMDTYPEVERSION(String mdtypeversion) {
            this.mdtypeversion = mdtypeversion;
        }

        /**
         * Gets the value of <code>@MIMETYPE</code>
         * 
         * @return the String value of <code>@MIMETYPE</code>
         */
        public String getMIMETYPE() {
            return mimetype;
        }

        /**
         * Sets the value of <code>@MIMETYPE</code>
         * 
         * @param mimetype
         *            the String value for <code>@MIMETYPE</code>
         */
        public void setMIMETYPE(String mimetype) {
            this.mimetype = mimetype;
        }

        /**
         * Gets <code>@SIZE</code>
         * 
         * @return a {@link Long} representing <code>@SIZE</code>
         */
        public Long getSIZE() {
            return size;
        }

        /**
         * Sets <code>@SIZE</code>
         * 
         * @param size
         */
        public void setSIZE(Long size) {
            this.size = size;
        }

        /**
         * Gets the value of <code>@CREATED</code>
         * 
         * @return an {@link XMLGregorianCalendar} representing <code>@CREATED</code>
         */
        public XMLGregorianCalendar getCREATED() {
            return created;
        }

        /**
         * Sets the value of <code>@CREATED</code>
         * 
         * @param created
         */
        public void setCREATED(XMLGregorianCalendar created) {
            this.created = created;
        }

        /**
         * Gets the value of <code>@CHECKSUM</code>
         * 
         * @return the String value of <code>@CHECKSUM</code>
         */
        public String getCHECKSUM() {
            return checksum;
        }

        /**
         * Sets the value of <code>@CHECKSUM</code>
         * 
         * @param checksum
         *            the String value for <code>@CHECKSUM</code>
         */
        public void setCHECKSUM(String checksum) {
            this.checksum = checksum;
        }

        /**
         * Gets the {@link CHECKSUMTYPE} enum object for <code>@CHECKSUMTYPE</code>
         * 
         * @return a {@link CHECKSUMTYPE} instance
         */
        public CHECKSUMTYPE getCHECKSUMTYPE() {
            return checksumtype;
        }

        /**
         * Sets <code>@CHECKSUMTYPE</code>
         * 
         * @param checksumtype
         */
        public void setCHECKSUMTYPE(CHECKSUMTYPE checksumtype) {
            this.checksumtype = checksumtype;
        }

        @Override
        public void marshal(Element mdRef, Document doc) {
            super.marshal(mdRef, doc);
            if (this.mimetype != null)
                mdRef.setAttribute("MIMETYPE", this.mimetype);
            if (this.size != null)
                mdRef.setAttribute("SIZE", this.size.toString());
            if (this.checksum != null)
                mdRef.setAttribute("CHECKSUM", this.checksum);
            if (this.checksumtype != null)
                mdRef.setAttribute("CHECKSUMTYPE", this.checksumtype.value());
            if (this.created != null) {
                Attr created = doc.createAttribute("CREATED");
                created.setNodeValue(this.created.toXMLFormat());
                mdRef.setAttributeNode(created);
            }
            if (this.label != null)
                mdRef.setAttribute("LABEL", this.label);
            if (this.xptr != null)
                mdRef.setAttribute("XPTR", this.xptr);
            if (this.mdtype != null)
                mdRef.setAttribute("MDTYPE", this.mdtype.value());
            if (this.othermdtype != null)
                mdRef.setAttribute("OTHERMDTYPE", this.othermdtype);
            if (this.mdtypeversion != null)
                mdRef.setAttribute("MDTYPEVERSION", this.mdtypeversion);
        }

        @Override
        public void unmarshal(Element mdRef) {
            super.unmarshal(mdRef);
            NamedNodeMap attrs = mdRef.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr = (Attr) attrs.item(i);
                String name = attr.getName();
                String value = attr.getNodeValue();
                if (name.equals("LABEL"))
                    this.label = value;
                if (name.equals("XPTR"))
                    this.xptr = value;
                if (name.equals("MDTYPE"))
                    this.mdtype = MDTYPE.fromValue(value);
                if (name.equals("OTHERMDTYPE"))
                    this.othermdtype = value;
                if (name.equals("MDTYPEVERSION"))
                    this.mdtypeversion = value;
                if (name.equals("MIMETYPE"))
                    this.mimetype = value;
                if (name.equals("SIZE"))
                    this.size = Long.parseLong(value);
                if (name.equals("CHECKSUM"))
                    this.checksum = value;
                if (name.equals("CHECKSUMTYPE"))
                    this.checksumtype = CHECKSUMTYPE.fromValue(value);
                if (name.equals("CREATED"))
                    this.created = dtf.newXMLGregorianCalendar(value);
            }
        }
    }

    /**
     * <strong>Note: <code>mets:binData</code> is not supported in this version</strong>
     * <p>
     * Representation of a <code>mets:mdRef</code>.
     */
    public static class MdWrap extends IDElement implements ElementInterface {

        protected String label;
        /**
         * <strong>Not supported in this version.</strong>
         */
        protected byte[] binData;
        protected List<Element> xmlData;
        protected MDTYPE mdtype;
        protected String othermdtype;
        protected String mdtypeversion;
        protected String mimetype;
        protected Long size;
        protected XMLGregorianCalendar created;
        protected String checksum;
        protected CHECKSUMTYPE checksumtype;
        protected static DatatypeFactory dtf;

        // for internal use
        protected MdWrap() {
        };

        /**
         * An MDTYPE is required
         */
        public MdWrap(MDTYPE mdtype) {
            this.mdtype = mdtype;
        }

        // /**
        // * Gets the base 64 value of the <code>mets:binData</code> child
        // *
        // * @return possible object is byte[]
        // */
        // public byte[] getBinData() {
        // return binData;
        // }
        //
        // /**
        // * Sets the <code>mets:binData</code> child
        // *
        // * @param binData
        // */
        // public void setBinData(byte[] binData) {
        // this.binData = ((byte[]) binData);
        // }

        /**
         * Gets the value of <code>@LABEL</code>
         * 
         * @return the String value of <code>@LABEL</code>
         */
        public String getLabel() {
            return label;
        }

        /**
         * Sets the value of <code>@LABEL</code>
         * 
         * @param label
         *            the String value for <code>@LABEL</code>
         */
        public void setLabel(String label) {
            this.label = label;
        }

        /**
         * Gets the xmlData List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getXmlData().add(newElement);
         * </pre>
         * 
         */
        public List<Element> getXmlData() {
            if (this.xmlData == null) {
                this.xmlData = new ArrayList<Element>();
            }
            return this.xmlData;
        }

        /**
         * Gets the {@link MDTYPE} enum object for <code>@MDTYPE</code>
         * 
         * @return a {@link MDTYPE} instance
         */
        public MDTYPE getMDTYPE() {
            return mdtype;
        }

        /**
         * Sets <code>@MDTYPE</code>
         * 
         * @param mdtype
         */
        public void setMDTYPE(MDTYPE mdtype) {
            this.mdtype = mdtype;
        }

        /**
         * Gets the value of <code>@OTHERMDTYPE</code>
         * 
         * @return the String value of <code>@OTHERMDTYPE</code>
         */
        public String getOTHERMDTYPE() {
            return othermdtype;
        }

        /**
         * Sets the value of <code>@OTHERMDTYPE</code>
         * 
         * @param othermdtype
         *            the String value for <code>@OTHERMDTYPE</code>
         */
        public void setOTHERMDTYPE(String othermdtype) {
            this.othermdtype = othermdtype;
        }

        /**
         * Gets the value of <code>@MDTYPEVERSION</code>
         * 
         * @return the String value of <code>@MDTYPEVERSION</code>
         */
        public String getMDTYPEVERSION() {
            return mdtypeversion;
        }

        /**
         * Sets the value of <code>@MDTYPEVERSION</code>
         * 
         * @param mdtypeversion
         *            the String value for <code>@MDTYPEVERSION</code>
         */
        public void setMDTYPEVERSION(String mdtypeversion) {
            this.mdtypeversion = mdtypeversion;
        }

        /**
         * Gets the value of <code>@MIMETYPE</code>
         * 
         * @return the String value of <code>@MIMETYPE</code>
         */
        public String getMIMETYPE() {
            return mimetype;
        }

        /**
         * Sets the value of <code>@MIMETYPE</code>
         * 
         * @param mimetype
         *            the String value for <code>@MIMETYPE</code>
         */
        public void setMIMETYPE(String mimetype) {
            this.mimetype = mimetype;
        }

        /**
         * Gets <code>@SIZE</code>
         * 
         * @return a {@link Long} representing <code>@SIZE</code>
         */
        public Long getSIZE() {
            return size;
        }

        /**
         * Sets <code>@SIZE</code>
         * 
         * @param size
         */
        public void setSIZE(Long size) {
            this.size = size;
        }

        /**
         * Gets the value of <code>@CREATED</code>
         * 
         * @return an {@link XMLGregorianCalendar} representing <code>@CREATED</code>
         */
        public XMLGregorianCalendar getCREATED() {
            return created;
        }

        /**
         * Sets the value of <code>@CREATED</code>
         * 
         * @param created
         */
        public void setCREATED(XMLGregorianCalendar created) {
            this.created = created;
        }

        /**
         * Gets the value of <code>@CHECKSUM</code>
         * 
         * @return the String value of <code>@CHECKSUM</code>
         */
        public String getCHECKSUM() {
            return checksum;
        }

        /**
         * Sets the value of <code>@CHECKSUM</code>
         * 
         * @param checksum
         *            the String value for <code>@CHECKSUM</code>
         */
        public void setCHECKSUM(String checksum) {
            this.checksum = checksum;
        }

        /**
         * Gets the {@link CHECKSUMTYPE} enum object for <code>@CHECKSUMTYPE</code>
         * 
         * @return a {@link CHECKSUMTYPE} instance
         */
        public CHECKSUMTYPE getCHECKSUMTYPE() {
            return checksumtype;
        }

        /**
         * Sets <code>@CHECKSUMTYPE</code>
         * 
         * @param checksumtype
         */
        public void setCHECKSUMTYPE(CHECKSUMTYPE checksumtype) {
            this.checksumtype = checksumtype;
        }

        @Override
        public void marshal(Element mdWrap, Document doc) {
            super.marshal(mdWrap, doc);
            if (this.mimetype != null)
                mdWrap.setAttribute("MIMETYPE", this.mimetype);
            if (this.size != null)
                mdWrap.setAttribute("SIZE", this.size.toString());
            if (this.checksum != null)
                mdWrap.setAttribute("CHECKSUM", this.checksum);
            if (this.checksumtype != null)
                mdWrap.setAttribute("CHECKSUMTYPE", this.checksumtype.value());
            if (this.created != null) {
                Attr created = doc.createAttribute("CREATED");
                created.setNodeValue(this.created.toXMLFormat());
                mdWrap.setAttributeNode(created);
            }
            if (this.label != null)
                mdWrap.setAttribute("LABEL", this.label);
            if (this.mdtype != null)
                mdWrap.setAttribute("MDTYPE", this.mdtype.value());
            if (this.othermdtype != null)
                mdWrap.setAttribute("OTHERMDTYPE", this.othermdtype);
            if (this.mdtypeversion != null)
                mdWrap.setAttribute("MDTYPEVERSION", this.mdtypeversion);
            if (this.xmlData != null) {
                Element xd = doc.createElementNS(NS.METS.ns(), "mets:xmlData");
                mdWrap.appendChild(xd);
                for (Element e : this.xmlData) {
                    Element data = (Element) doc.importNode(e, true);
                    xd.appendChild(data);
                }
            }
            // binData : byte[]
        }

        @Override
        public void unmarshal(Element mdWrap) {
            super.unmarshal(mdWrap);
            NamedNodeMap attrs = mdWrap.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr = (Attr) attrs.item(i);
                String name = attr.getName();
                String value = attr.getNodeValue();
                if (name.equals("LABEL"))
                    this.label = value;
                if (name.equals("MDTYPE"))
                    this.mdtype = MDTYPE.fromValue(value);
                if (name.equals("OTHERMDTYPE"))
                    this.othermdtype = value;
                if (name.equals("MDTYPEVERSION"))
                    this.mdtypeversion = value;
                if (name.equals("MIMETYPE"))
                    this.mimetype = value;
                if (name.equals("SIZE"))
                    this.size = Long.parseLong(value);
                if (name.equals("CHECKSUM"))
                    this.checksum = value;
                if (name.equals("CHECKSUMTYPE"))
                    this.checksumtype = CHECKSUMTYPE.fromValue(value);
                if (name.equals("CREATED"))
                    this.created = dtf.newXMLGregorianCalendar(value);
            }
            // xmlData
            List<Element> children = DOMHelp.getChildElements(mdWrap);
            for (Element child : children) {
                if (child.getLocalName().equals("xmlData")) {
                    List<Element> gChildren = DOMHelp.getChildElements(child);
                    for (Element gChild : gChildren) {
                        this.getXmlData().add(gChild);
                    }
                }
            }
        }
    }

    /**
     * Enumeration of values allowed for <code>@MDTYPE</code>
     */
    public enum MDTYPE {

        MARC("MARC"), //
        MODS("MODS"), //
        EAD("EAD"), //
        DC("DC"), //
        NISOIMG("NISOIMG"), //
        LC_AV("LC-AV"), //
        VRA("VRA"), //
        TEIHDR("TEIHDR"), //
        DDI("DDI"), //
        FGDC("FGDC"), //
        LOM("LOM"), //
        PREMIS("PREMIS"), //
        PREMIS_OBJECT("PREMIS:OBJECT"), //
        PREMIS_AGENT("PREMIS:AGENT"), //
        PREMIS_RIGHTS("PREMIS:RIGHTS"), //
        PREMIS_EVENT("PREMIS:EVENT"), //
        TEXTMD("TEXTMD"), //
        METSRIGHTS("METSRIGHTS"), //
        ISO_19115_2003_NAP("ISO 19115:2003 NAP"), //
        OTHER("OTHER");

        private final String value;

        MDTYPE(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static MDTYPE fromValue(String v) {
            for (MDTYPE c : MDTYPE.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }

}
