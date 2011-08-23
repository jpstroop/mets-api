/**
 * Mets.java 
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.anearalone.utils.DOMHelp;


/**
 * Representation of a METS object.
 * <p>
 * From the METS Schema: <blockquote>
 * <p>
 * A METS document consists of seven possible subsidiary sections: metsHdr (METS
 * document header), dmdSec (descriptive metadata section), amdSec
 * (administrative metadata section), fileGrp (file inventory group), structLink
 * (structural map linking), structMap (structural map) and behaviorSec
 * (behaviors section).
 * 
 * 
 * <p>
 * There are no setter methods for these elements; they are represented as
 * {@link List}s, and are manipulated by getting the List and using its methods.
 * <ul>
 * <li>metsHdr (METS document header) {@link #getMetsHdr()}
 * <li>dmdSec (descriptive metadata section) {@link #getDmdSec()}
 * <li>amdSec (administrative metadata section) {@link #getAmdSec()}
 * <li>fileGrp (file inventory group) {@link #getFileSec()}
 * <li>structLink (structural map linking) {@link #getStructMap()}
 * <li>structMap (structural map) {@link #getStructMap()}
 * <li>behaviorSec (behaviors section) {@link #getBehaviorSec()}
 * <p>
 * Creating a new Mets object and writing it out with no further manipulationa
 * {@link MetsWriter} will always result in a valid METS record. No more, no
 * less.
 */

public class Mets extends IDElement implements ElementInterface {
    protected MetsHdr metsHdr;
    protected List<MdSec> dmdSec;
    protected List<AmdSec> amdSec;
    protected FileSec fileSec;
    protected List<StructMap> structMap;
    protected StructLink structLink;
    protected List<BehaviorSec> behaviorSec;
    protected String label;
    protected String objid;
    protected String profile;
    protected String type;

    /**
     * Constructs an empty, valid METS object.
     * 
     * @see {@link #getStructMap()}.
     */
    public Mets() {
    }

    /**
     * Gets the <code>mets:metsHdr</code> child
     * 
     * @return a {@link MetsHdr} object representing this element
     */
    public MetsHdr getMetsHdr() {
        return this.metsHdr;
    }

    /**
     * Sets the <code>mets:metsHdr</code> child
     * 
     * @param metsHdr
     */
    public void setMetsHdr(MetsHdr metsHdr) {
        this.metsHdr = metsHdr;
    }

    /**
     * Gets the the dmdSec List
     * 
     * <p>
     * To add a new dmdSec, do:
     * 
     * <pre>
     * getDmdSec().add(newMdSec);
     * </pre>
     */
    public List<MdSec> getDmdSec() {
        if (dmdSec == null) {
            dmdSec = new ArrayList<MdSec>();
        }
        return this.dmdSec;
    }

    /**
     * Gets the amdSec List
     * 
     * <p>
     * To add a new amdSec, do:
     * 
     * <pre>
     * getAmdSec().add(newAmdSec);
     * </pre>
     */
    public List<AmdSec> getAmdSec() {
        if (amdSec == null) {
            amdSec = new ArrayList<AmdSec>();
        }
        return this.amdSec;
    }

    /**
     * Gets the <code>mets:fileSec</code> child
     * 
     * @return a {@link FileSec} object representing this element
     */
    public FileSec getFileSec() {
        return fileSec;
    }

    /**
     * Sets the <code>mets:fileSec</code> child
     * 
     * @param fileSec
     */
    public void setFileSec(FileSec fileSec) {
        this.fileSec = fileSec;
    }

    /**
     * Gets the structMap List. Note that because the METS schema requires at
     * least one structMap <strong>the first member of the structMap List will
     * always be initialized</strong> when a new Mets object is constructed.
     * <p>
     * To add a new structMap, do:
     * 
     * <pre>
     * getStructMap().add(newStructMap);
     * </pre>
     */
    public List<StructMap> getStructMap() {
        if (this.structMap == null) {
            this.structMap = new ArrayList<StructMap>();
        }
        return this.structMap;
    }

    /**
     * Gets the <code>mets:structLink</code> child
     * 
     * @return a {@link StructLink} object representing this element
     */
    public StructLink getStructLink() {
        return structLink;
    }

    /**
     * Sets the <code>mets:structLink</code> child
     * 
     * @param structlink
     */
    public void setStructLink(StructLink structlink) {
        this.structLink = structlink;
    }

    /**
     * Gets the behaviorSec List
     * 
     * <p>
     * To add a new behaviorSec, do:
     * 
     * <pre>
     * getBehaviorSec().add(newBehaviorSec);
     * </pre>
     * 
     */
    public List<BehaviorSec> getBehaviorSec() {
        if (behaviorSec == null) {
            behaviorSec = new ArrayList<BehaviorSec>();
        }
        return this.behaviorSec;
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
     * Gets the value of <code>@OBJID</code>
     * 
     * @return the String value of <code>@OBJID</code>
     */
    public String getOBJID() {
        return objid;
    }

    /**
     * Sets the value of <code>@OBJID</code>
     * 
     * @param objid
     */
    public void setOBJID(String objid) {
        this.objid = objid;
    }

    /**
     * Gets the value of <code>@PROFILE</code>
     * 
     * @return the String value of <code>@PROFILE</code>
     */
    public String getPROFILE() {
        return profile;
    }

    /**
     * Sets the value of <code>@PROFILE</code>
     * 
     * @param profile
     */
    public void setPROFILE(String profile) {
        this.profile = profile;
    }

    /*
     * Reconstitute our fields from a DOM
     */
    @Override
    public void unmarshal(Element root) {
        super.unmarshal(root);

        NamedNodeMap attrs = root.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            String name = attr.getName();
            String value = attr.getNodeValue();
            if (name.equals("OBJID"))
                this.objid = value;
            if (name.equals("PROFILE"))
                this.profile = value;
            if (name.equals("LABEL"))
                this.label = value;
            if (name.equals("TYPE"))
                this.type = value;
        }

        List<Element> children = DOMHelp.getChildElements(root);
        for (Element child : children) {
            String localName = child.getLocalName();
            if (localName.equals("metsHdr")) {
                this.metsHdr = new MetsHdr();
                this.metsHdr.unmarshal(child);
            }
            if (localName.equals("dmdSec")) {
                MdSec dmdSec = new MdSec();
                dmdSec.unmarshal(child);
                this.getDmdSec().add(dmdSec);
            }
            if (localName.equals("amdSec")) {
                AmdSec amdSec = new AmdSec();
                amdSec.unmarshal(child);
                this.getAmdSec().add(amdSec);
            }
            if (localName.equals("fileSec")) {
                this.fileSec = new FileSec();
                this.fileSec.unmarshal(child);
            }
            if (localName.equals("structMap")) {
                StructMap structMap = new StructMap();
                structMap.unmarshal(child);
                this.getStructMap().add(structMap);
            }
            if (localName.equals("structLink")) {
                this.structLink = new StructLink();
                this.structLink.unmarshal(child);
            }
        }

        // TODO: behaviorSec

    }

    /*
     * Arrange our fields into a DOM
     */
    public void marshal(Element root, Document doc) {
        String metsNs = NS.METS.ns();

        // set up namespace declarations and schema references
        root.setAttribute("xmlns:mets", metsNs);
        root.setAttribute("xmlns:xlink", NS.XLINK.ns());
        root.setAttribute("xmlns:xsi", NS.XSI.ns());
        root.setAttributeNS(NS.XSI.ns(), "xsi:schemaLocation", NS.METS.schemaLoc());

        // ID, TYPE
        super.marshal(root, doc);

        // OBJID
        if (this.objid != null)
            root.setAttribute("OBJID", this.objid);
        // PROFILE
        if (this.profile != null)
            root.setAttribute("PROFILE", this.profile);
        if (this.label != null)
            root.setAttribute("LABEL", this.label);
        if (this.type != null)
            root.setAttribute("TYPE", this.type);
        // CHILDREN
        // metsHdr
        if (this.metsHdr != null) {
            Element metsHdr = doc.createElementNS(metsNs, "mets:metsHdr");
            this.metsHdr.marshal(metsHdr, doc);
            root.appendChild(metsHdr);
        }
        // dmdSec
        for (MdSec dmd : this.getDmdSec()) {
            Element dmdSec = doc.createElementNS(metsNs, "mets:dmdSec");
            dmd.marshal(dmdSec, doc);
            root.appendChild(dmdSec);
        }
        // amdSec
        for (AmdSec amd : this.getAmdSec()) {
            Element amdSec = doc.createElementNS(metsNs, "mets:amdSec");
            amd.marshal(amdSec, doc);
            root.appendChild(amdSec);
        }
        // fileSec
        if (this.fileSec != null) {
            Element fileSec = doc.createElementNS(metsNs, "mets:fileSec");
            this.fileSec.marshal(fileSec, doc);
            root.appendChild(fileSec);
        }

        // structMap. One is REQUIRED in order to be valid, so add one if
        // necessary;
        if (this.structMap == null)
            this.getStructMap().add(new StructMap());
        for (StructMap s : this.getStructMap()) {
            Element smap = doc.createElementNS(metsNs, "mets:structMap");
            s.marshal(smap, doc);
            root.appendChild(smap);
        }

        if (this.structLink != null) {
            Element slink = doc.createElementNS(metsNs, "mets:structLink");
            this.structLink.marshal(slink, doc);
            root.appendChild(slink);
        }

        // TODO: behaviorSec
    }

}
