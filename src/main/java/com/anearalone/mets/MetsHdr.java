/**
 * MetsHdr.java
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
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.anearalone.utils.DOMHelp;

/**
 * Representation of a <code>mets:metsHdr</code>.
 * <p>
 * From the METS Schema:
 * 
 * <blockquote>
 * <p>
 * The mets header element <metsHdr> captures metadata about the METS document itself, not the
 * digital object the METS document encodes. Although it records a more limited set of metadata, it
 * is very similar in function and purpose to the headers employed in other schema such as the Text
 * Encoding Initiative (TEI) or in the Encoded Archival Description (EAD).
 * 
 */
public class MetsHdr extends IDElement implements ElementInterface {
    /**
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * The agent element <agent> provides for various parties and their roles with respect to the
     * METS record to be documented.
     * 
     * 
     */
    protected List<Agent> agent;
    /**
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * The alternative record identifier element <altRecordID> allows one to use alternative record
     * identifier values for the digital object represented by the METS document; the primary record
     * identifier is stored in the OBJID attribute in the root <mets> element.
     * 
     * 
     */
    protected List<RecordID> altRecordID;
    /**
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * The metsDocument identifier element <metsDocumentID> allows a unique identifier to be
     * assigned to the METS document itself. This may be different from the OBJID attribute value in
     * the root <mets> element, which uniquely identifies the entire digital object represented by
     * the METS document.
     * 
     * 
     */
    protected RecordID metsDocumentID;
    /**
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * Records the date/time the METS document was created.
     * 
     * 
     */
    protected XMLGregorianCalendar createdate;
    /**
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * Is used to indicate the date/time the METS document was last modified.
     * 
     * 
     */
    protected XMLGregorianCalendar lastmoddate;
    /**
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * Specifies the status of the METS document. It is used for internal processing purposes.
     * 
     * 
     */
    protected String recordstatus;

    /**
     * From the METS Schema: <blockquote>
     * <p>
     * Contains the ID attribute values of the <techMD>, <sourceMD>, <rightsMD> and/or <digiprovMD>
     * elements within the <amdSec> of the METS document that contain administrative metadata
     * pertaining to the METS document itself. For more information on using METS IDREFS and IDREF
     * type attributes for internal linking, see Chapter 4 of the METS Primer.
     */
    protected List<String> admid;

    public MetsHdr() {
    }

    /**
     * Gets the agent List
     * 
     * <p>
     * To add a new item, do:
     * 
     * <pre>
     * getAgent().add(newAgent);
     * </pre>
     */
    public List<Agent> getAgent() {
        if (agent == null) {
            agent = new ArrayList<MetsHdr.Agent>();
        }
        return this.agent;
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
     * Gets the altRecordID List
     * <p>
     * To add a new item, do:
     * 
     * <pre>
     * getAltRecordID().add(newRecordID);
     * </pre>
     */
    public List<RecordID> getAltRecordID() {
        if (this.altRecordID == null) {
            this.altRecordID = new ArrayList<MetsHdr.RecordID>();
        }
        return this.altRecordID;
    }

    /**
     * Gets the <code>mets:metsDocumentID</code> child
     * 
     * @return a {@link RecordID} object representing this element
     * 
     */
    public RecordID getMetsDocumentID() {
        return this.metsDocumentID;
    }

    /**
     * Sets the <code>mets:metsDocumentID</code> child
     * 
     * @param par
     */
    public void setMetsDocumentID(MetsHdr.RecordID metsId) {
        this.metsDocumentID = metsId;
    }

    /**
     * Gets the value of <code>@CREATEDATE</code>
     * 
     * @return an {@link XMLGregorianCalendar} representing <code>@CREATEDATE</code>
     */
    public XMLGregorianCalendar getCREATEDATE() {
        return createdate;
    }

    /**
     * Sets the value of <code>@CREATEDATE</code>
     * 
     * @param createdate
     */
    public void setCREATEDATE(XMLGregorianCalendar createdate) {
        this.createdate = createdate;
    }

    /**
     * Gets the value of <code>@LASTMODDATE</code>
     * 
     * @return an {@link XMLGregorianCalendar} representing <code>@LASTMODDATE</code>
     */
    public XMLGregorianCalendar getLASTMODDATE() {
        return lastmoddate;
    }

    /**
     * Sets the value of <code>@LASTMODDATE</code>
     * 
     * @param lastmoddate
     */
    public void setLASTMODDATE(XMLGregorianCalendar lastmoddate) {
        this.lastmoddate = lastmoddate;
    }

    /**
     * Gets the value of <code>@RECORDSTATUS</code>
     * 
     * @return the String value of <code>@RECORDSTATUS</code>
     */
    public String getRECORDSTATUS() {
        return recordstatus;
    }

    /**
     * Sets the value of <code>@RECORDSTATUS</code>
     * 
     * @param recordstatus
     */
    public void setRECORDSTATUS(String recordstatus) {
        this.recordstatus = recordstatus;
    }

    public void marshal(Element metsHdr, Document doc) {
        super.marshal(metsHdr, doc);
        DatatypeFactory dtf = MetsIO.getDataTypeFactory();
        if (this.recordstatus != null)
            metsHdr.setAttribute("RECORDSTATUS", this.recordstatus);
        // ADMID
        if (this.admid != null) {
            Attr admid = doc.createAttribute("ADMID");
            admid.setNodeValue(MetsWriter.listToString(this.admid));
            metsHdr.setAttributeNode(admid);
        }

        /*
         * Date attributes. CREATEDATE gets set if it's null, otherwise behaves like any other
         * field. LASTMODDATE will always be set to the reflect the time of this marshalling.
         */
        Attr createdate = doc.createAttribute("CREATEDATE");
        if (this.createdate != null) {
            createdate.setNodeValue(this.createdate.toXMLFormat());
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            XMLGregorianCalendar xmlgc = dtf.newXMLGregorianCalendar(gc);
            createdate.setNodeValue(xmlgc.toXMLFormat());
        }
        metsHdr.setAttributeNode(createdate);

        // LASTMODDATE
        Attr lastmoddate = doc.createAttribute("LASTMODDATE");
        GregorianCalendar gc = new GregorianCalendar();
        XMLGregorianCalendar xmlgc = dtf.newXMLGregorianCalendar(gc);
        lastmoddate.setNodeValue(xmlgc.toXMLFormat());
        metsHdr.setAttributeNode(lastmoddate);

        if (this.agent != null) {
            for (Agent a : this.agent) {
                Element agent = doc.createElementNS(NS.METS.ns(), "mets:agent");
                a.marshal(agent, doc);
                metsHdr.appendChild(agent);
            }
        }
        if (this.altRecordID != null) {
            for (RecordID a : this.altRecordID) {
                Element altID;
                altID = doc.createElementNS(NS.METS.ns(), "mets:altRecordID");
                a.marshal(altID, doc);
                metsHdr.appendChild(altID);
            }
        }
        if (this.metsDocumentID != null) {
            Element mdId;
            mdId = doc.createElementNS(NS.METS.ns(), "mets:metsDocumentID");
            this.metsDocumentID.marshal(mdId, doc);
            metsHdr.appendChild(mdId);
        }
    }

    public void unmarshal(Element metsHdr) {
        super.unmarshal(metsHdr);
        DatatypeFactory dtf = MetsIO.getDataTypeFactory();
        NamedNodeMap attrs = metsHdr.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            String name = attr.getName();
            String value = attr.getNodeValue();
            if (name.equals("RECORDSTATUS"))
                this.recordstatus = value;
            if (name.equals("CREATEDATE"))
                this.createdate = dtf.newXMLGregorianCalendar(value);
            if (name.equals("LASTMODDATE"))
                this.lastmoddate = dtf.newXMLGregorianCalendar(value);
            if (name.equals("ADMID"))
                this.admid = MetsReader.parseIDREFAttr(value);
        }

        List<Element> children = DOMHelp.getChildElements(metsHdr);
        for (Element child : children) {
            String localName = child.getLocalName();
            if (localName.equals("agent")) {
                Agent agent = new Agent();
                agent.unmarshal(child);
                this.getAgent().add(agent);
            }
            if (localName.equals("altRecordID")) {
                RecordID aid = new RecordID();
                aid.unmarshal(child);
                this.getAltRecordID().add(aid);
            }
            if (localName.equals("metsDocumentID")) {
                this.metsDocumentID = new RecordID();
                this.metsDocumentID.unmarshal(child);
            }
        }
    }

    /**
     * Representation of a <code>mets:agent</code>.
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * The agent element <agent> provides for various parties and their roles with respect to the
     * METS record to be documented.
     * 
     * 
     */
    public static class Agent extends IDElement implements ElementInterface {
        protected String name;
        protected String otherrole;
        protected String othertype;
        protected List<String> note;
        protected ROLE role;
        protected AGENTTYPE agenttype;

        // only to be used by the parent class that unmarshals
        private Agent() {
        }

        /**
         * The default constructor for {@link Agent} is disabled because a name and ROLE are
         * required by the METS schema.
         * 
         * @param role
         * @param name
         */
        public Agent(Agent.ROLE role, String name) {
            this.role = role;
            this.name = name;
        }

        /**
         * Gets the <code>mets:name</code> child
         * 
         * @return the String vale of the name element
         * 
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the <code>mets:name</code> child
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gets note List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getNote().add(newItem);
         * </pre>
         */
        public List<String> getNote() {
            if (note == null) {
                note = new ArrayList<String>();
            }
            return this.note;
        }

        /**
         * Gets the {@link ROLE} enum object for <code>@ROLE</code>
         * 
         * @return a {@link ROLE} instance
         */
        public ROLE getROLE() {
            return role;
        }

        /**
         * Sets <code>@ROLE</code>
         * 
         * @param role
         * 
         */
        public void setROLE(ROLE role) {
            this.role = role;
        }

        /**
         * Gets the value of <code>@OTHERROLE</code>
         * 
         * @return the String value of <code>@OTHERROLE</code>
         */
        public String getOTHERROLE() {
            return otherrole;
        }

        /**
         * Sets the value of <code>@OTHERROLE</code>
         * 
         * @param otherrole
         */
        public void setOTHERROLE(String otherrole) {
            this.otherrole = otherrole;
        }

        /**
         * Gets the {@link AGENTTYPE} enum object for <code>@AGENTTYPE</code>
         * 
         * @return a {@link AGENTTYPE} instance
         */
        public AGENTTYPE getAGENTTYPE() {
            return this.agenttype;
        }

        /**
         * Sets <code>@AGENTTYPE</code>
         * 
         * @param agenttype
         */
        public void setTYPE(AGENTTYPE agenttype) {
            this.agenttype = agenttype;
        }

        /**
         * Gets the value of <code>@OTHERTYPE</code>
         * 
         * @return the String value of <code>@OTHERTYPE</code>
         */
        public String getOTHERTYPE() {
            return othertype;
        }

        /**
         * Sets the value of <code>@OTHERTYPE</code>
         * 
         * @param othertype
         */
        public void setOTHERTYPE(String othertype) {
            this.othertype = othertype;
        }

        @Override
        public void marshal(Element agent, Document doc) {
            super.marshal(agent, doc);
            agent.setAttribute("ROLE", this.role.value()); // required
            if (this.agenttype != null)
                agent.setAttribute("AGENTTYPE", this.agenttype.value());
            if (this.otherrole != null)
                agent.setAttribute("OTHERROLE", this.otherrole);
            if (this.othertype != null)
                agent.setAttribute("OTHERTYPE", this.othertype);
            // name is required
            Element name = doc.createElementNS(NS.METS.ns(), "mets:name");
            name.setTextContent(this.name);
            agent.appendChild(name);
            if (this.note != null) {
                for (String n : this.note) {
                    Element note;
                    note = doc.createElementNS(NS.METS.ns(), "mets:note");
                    note.setTextContent(n);
                    agent.appendChild(note);
                }
            }
        }

        @Override
        public void unmarshal(Element e) {
            super.unmarshal(e);
            NamedNodeMap attrs = e.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr = (Attr) attrs.item(i);
                String name = attr.getName();
                String value = attr.getNodeValue();
                if (name.equals("ROLE"))
                    this.role = ROLE.fromValue(value);
                if (name.equals("AGENTTYPE"))
                    this.agenttype = AGENTTYPE.fromValue(value);
                if (name.equals("OTHERROLE"))
                    this.otherrole = value;
                if (name.equals("OTHERTYPE"))
                    this.othertype = value;
            }

            List<Element> children = DOMHelp.getChildElements(e);
            for (Element child : children) {
                String localName = child.getLocalName();
                if (localName.equals("note"))
                    this.getNote().add(child.getTextContent());
                if (localName.equals("name"))
                    this.name = child.getTextContent();
            }

            // NodeList children = e.getChildNodes();
            // for (int i = 0; i < children.getLength(); i++) {
            // Node thisElement = children.item(i);
            // if (thisElement.getNodeType() == 1) {
            // String localName = thisElement.getLocalName();
            // if (localName.equals("note"))
            // this.getNote().add(thisElement.getTextContent());
            // else
            // this.name = thisElement.getTextContent(); // required
            // }
            // }

        }

        /**
         * Enumeration of allowed values for <code>@AGENTTYPE</code>
         */
        public enum AGENTTYPE {
            INDIVIDUAL, ORGANIZATION, OTHER;
            public String value() {
                return name();
            }

            public static MetsHdr.Agent.AGENTTYPE fromValue(String v) {
                return valueOf(v);
            }
        }

        /**
         * Enumeration of allowed values for <code>@ROLE</code>
         */
        public enum ROLE {
            CREATOR, EDITOR, ARCHIVIST, PRESERVATION, DISSEMINATOR, CUSTODIAN, IPOWNER, OTHER;
            public String value() {
                return name();
            }

            public static MetsHdr.Agent.ROLE fromValue(String v) {
                return valueOf(v);
            }
        }
    }

    /**
     * A generic recordId, used for both {@link MetsHdr#metsDocumentID} and
     * {@link MetsHdr#altRecordID}
     */
    public static class RecordID extends IDElement implements ElementInterface {
        protected String identifier;
        protected String type;

        public RecordID() {
        }

        public RecordID(String identifier) {
            this.identifier = identifier;
        }

        /**
         * Gets the value of <code>@TYPE</code>
         * 
         * @return the String value of <code>@TYPE</code>
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of <code>@TYPE</code>
         * 
         * @param type
         *            the String value for <code>@TYPE</code>
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Gets the value of this RecordId
         * 
         * @return the String value of this RecordId
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * Sets the value of this RecordId
         * 
         * @param identifier
         */
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public void marshal(Element rid, Document doc) {
            super.marshal(rid, doc);
            if (this.identifier != null)
                rid.setTextContent(this.identifier);
            if (this.type != null)
                rid.setAttribute("TYPE", this.type);
        }

        public void unmarshal(Element rid) {
            super.unmarshal(rid);
            if (rid.getTextContent() != null)
                this.identifier = rid.getTextContent();
            if (rid.hasAttribute("TYPE"))
                this.type = rid.getAttribute("TYPE");
        }

    }

}