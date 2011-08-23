/**
 * AmdSecImpl.java
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.anearalone.utils.DOMHelp;

/**
 * Representation of a <code>mets:metsHdr</code>.
 * <p>
 * From the METS Schema:
 * 
 * <blockquote>
 * <p>
 * The administrative metadata section consists of four possible subsidiary sections: techMD
 * (technical metadata for text/image/audio/video files), rightsMD (intellectual property rights
 * metadata), sourceMD (analog/digital source metadata), and digiprovMD (digital provenance
 * metadata, that is, the history of migrations/translations performed on a digital library object
 * from it's original digital capture/encoding).
 * 
 * 
 */
public class AmdSec extends IDElement implements ElementInterface {

    /**
     * From the METS Schema: <blockquote>
     * <p>
     * A technical metadata element <techMD> records technical metadata about a component of the
     * METS object, such as a digital content file. The <techMD> element conforms to same generic
     * datatype as the <dmdSec>, <rightsMD>, <sourceMD> and <digiprovMD> elements.... Technical
     * metadata can be expressed according to many current technical description standards (such as
     * MIX and textMD) or a locally produced XML schema.
     * 
     * 
     */
    protected List<MdSec> techMD;
    /**
     * From the METS Schema: <blockquote>
     * <p>
     * An intellectual property rights metadata element <rightsMD> records information about
     * copyright and licensing pertaining to a component of the METS object. The <rightsMD> element
     * conforms to same generic datatype as the <dmdSec>, <techMD>, <sourceMD> and <digiprovMD>
     * elements.... Rights metadata can be expressed according current rights description standards
     * (such as CopyrightMD and rightsDeclarationMD) or a locally produced XML schema.
     * 
     * 
     */
    protected List<MdSec> rightsMD;
    /**
     * From the METS Schema: <blockquote>
     * <p>
     * A source metadata element <sourceMD> records descriptive and administrative metadata about
     * the source format or media of a component of the METS object such as a digital content file.
     * It is often used for discovery, data administration or preservation of the digital object.
     * The <sourceMD> element conforms to same generic datatype as the <dmdSec>, <techMD>,
     * <rightsMD>, and <digiprovMD> elements.... Source metadata can be expressed according to
     * current source description standards (such as PREMIS) or a locally produced XML schema.
     * 
     * 
     */
    protected List<MdSec> sourceMD;
    /**
     * From the METS Schema: <blockquote>
     * <p>
     * A digital provenance metadata element <digiprovMD> can be used to record any
     * preservation-related actions taken on the various files which comprise a digital object
     * (e.g., those subsequent to the initial digitization of the files such as transformation or
     * migrations) or, in the case of born digital materials, the files’ creation. In short, digital
     * provenance should be used to record information that allows both archival/library staff and
     * scholars to understand what modifications have been made to a digital object and/or its
     * constituent parts during its life cycle. This information can then be used to judge how those
     * processes might have altered or corrupted the object’s ability to accurately represent the
     * original item. One might, for example, record master derivative relationships and the process
     * by which those derivations have been created. Or the <digiprovMD> element could contain
     * information regarding the migration/transformation of a file from its original digitization
     * (e.g., OCR, TEI, etc.,)to its current incarnation as a digital object (e.g., JPEG2000). The
     * <digiprovMD> element conforms to same generic datatype as the <dmdSec>, <techMD>, <rightsMD>,
     * and <sourceMD> elements.... Digital provenance metadata can be expressed according to current
     * digital provenance description standards (such as PREMIS) or a locally produced XML schema.
     * 
     * 
     */
    protected List<MdSec> digiprovMD;

    /**
     * Gets the techMD List
     * <p>
     * To add a new item, do:
     * 
     * <pre>
     * getTechMD().add(newMdSec);
     * </pre>
     */
    public List<MdSec> getTechMD() {
        if (techMD == null) {
            techMD = new ArrayList<MdSec>();
        }
        return this.techMD;
    }

    /**
     * Gets the rightsMD List
     * <p>
     * To add a new item, do:
     * 
     * <pre>
     * getRightsMD().add(newMdSec);
     * </pre>
     */
    public List<MdSec> getRightsMD() {
        if (rightsMD == null) {
            rightsMD = new ArrayList<MdSec>();
        }
        return this.rightsMD;
    }

    /**
     * Gets the sourceMD List
     * <p>
     * To add a new item, do:
     * 
     * <pre>
     * getSourceMD().add(newMdSec);
     * </pre>
     */
    public List<MdSec> getSourceMD() {
        if (sourceMD == null) {
            sourceMD = new ArrayList<MdSec>();
        }
        return this.sourceMD;
    }

    /**
     * Gets the digiprovMD List
     * <p>
     * To add a new item, do:
     * 
     * <pre>
     * getDigiprovMD().add(newMdSec);
     * </pre>
     */
    public List<MdSec> getDigiprovMD() {
        if (digiprovMD == null) {
            digiprovMD = new ArrayList<MdSec>();
        }
        return this.digiprovMD;
    }

    @Override
    public void marshal(Element amdSec, Document doc) {
        super.marshal(amdSec, doc);
        String metsNs = NS.METS.ns();
        if (this.techMD != null) {
            for (MdSec mds : this.techMD) {
                Element techMD = doc.createElementNS(metsNs, "mets:techMD");
                mds.marshal(techMD, doc);
                amdSec.appendChild(techMD);
            }
        }
        if (this.rightsMD != null) {
            for (MdSec mds : this.rightsMD) {
                Element rightsMD = doc.createElementNS(metsNs, "mets:rightsMD");
                mds.marshal(rightsMD, doc);
                amdSec.appendChild(rightsMD);
            }
        }
        if (this.sourceMD != null) {
            for (MdSec mds : this.sourceMD) {
                Element sourceMD = doc.createElementNS(metsNs, "mets:sourceMD");
                mds.marshal(sourceMD, doc);
                amdSec.appendChild(sourceMD);
            }
        }
        if (this.digiprovMD != null) {
            for (MdSec mds : this.digiprovMD) {
                Element digiprovMD = doc.createElementNS(metsNs, "mets:digiprovMD");
                mds.marshal(digiprovMD, doc);
                amdSec.appendChild(digiprovMD);
            }
        }
    }

    @Override
    public void unmarshal(Element e) {
        super.unmarshal(e);
        List<Element> children = DOMHelp.getChildElements(e);
        for (Element child : children) {
            String localName = child.getLocalName();
            MdSec md = new MdSec();
            md.unmarshal(child);
            if (localName.equals("techMD"))
                this.getTechMD().add(md);
            if (localName.equals("rightsMD"))
                this.getRightsMD().add(md);
            if (localName.equals("sourceMD"))
                this.getSourceMD().add(md);
            if (localName.equals("digiprovMD"))
                this.getDigiprovMD().add(md);
        }
    }

}
