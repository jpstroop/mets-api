/**
 * FileSec.java
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
 * Representation of a <code>mets:fileSec</code>
 * <p>
 * Note that because the METS Schema requires that the <code>fileSec</code> has at least one
 * instance of a <code>fileGrp</code>, the first instance of {@link #fileGrp} will always be
 * populated with an empty {@link FileGrp}.
 * 
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Sep 2, 2010
 */
public class FileSec extends IDElement implements ElementInterface {

    protected List<FileGrp> fileGrp;

    /**
     * Gets the fileGrp List
     * <p>
     * To add a new FileGrp, do:
     * 
     * <pre>
     * getFileGrp().add(newFileGrp);
     * </pre>
     */
    public List<FileGrp> getFileGrp() {
        if (fileGrp == null) {
            fileGrp = new ArrayList<FileGrp>();
        }
        return this.fileGrp;
    }

    @Override
    public void marshal(Element fileSec, Document doc) {
        super.marshal(fileSec, doc);
        // At least one fileGrp is required, so make it if it doesn't exist
        if (this.fileGrp == null)
            this.getFileGrp().add(new FileGrp());
        for (FileGrp fileGrp : this.getFileGrp()) {
            Element fileGrpE;
            fileGrpE = doc.createElementNS(NS.METS.ns(), "mets:fileGrp");
            fileGrp.marshal(fileGrpE, doc);
            fileSec.appendChild(fileGrpE);
        }
    }

    @Override
    public void unmarshal(Element fileSec) {
        super.unmarshal(fileSec);
        List<Element> children = DOMHelp.getChildElements(fileSec);
        for (Element fileGrpE : children) {
            FileGrp fileGrp = new FileGrp();
            fileGrp.unmarshal(fileGrpE);
            this.getFileGrp().add(fileGrp);
        }
    }

    /**
     * Representation of a <code>mets:fileGrp</code>
     * <p>
     * From the METS Schema
     * 
     * <blockquote>
     * <p>
     * The file group is used to cluster all of the digital files composing a digital library object
     * in a hierarchical arrangement (fileGrp is recursively defined to enable the creation of the
     * hierarchy). Any file group may contain zero or more file elements. File elements in turn can
     * contain one or more FLocat elements (a pointer to a file containing content for this object)
     * and/or a FContent element (the contents of the file, in either XML or Base64 encoding).
     * 
     * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
     * @since Sep 2, 2010
     */
    public static class FileGrp extends IDElement implements ElementInterface {
        protected List<FileGrp> fileGrp;
        protected List<File> file;
        protected XMLGregorianCalendar versdate;
        protected String use;

        public FileGrp() {
        }

        /**
         * Gets the value of <code>@USE</code>
         * 
         * @return the String value of <code>@USE</code>
         */
        public String getUse() {
            return use;
        }

        /**
         * Sets the value of <code>@USE</code>
         * 
         * @param use
         *            the String value for <code>@USE</code>
         */
        public void setUse(String use) {
            this.use = use;
        }

        /**
         * Gets the fileGrp List
         * 
         * <p>
         * To add a new FileGrp, do as follows:
         * 
         * <pre>
         * getFileGrp().add(newFileGrp);
         * </pre>
         */
        public List<FileGrp> getFileGrp() {
            if (fileGrp == null) {
                fileGrp = new ArrayList<FileGrp>();
            }
            return this.fileGrp;
        }

        /**
         * Gets the file List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getFile().add(newFile);
         * </pre>
         */
        public List<File> getFile() {
            if (file == null) {
                file = new ArrayList<File>();
            }
            return this.file;
        }

        /**
         * Gets the value of the versdate property.
         * 
         * @return possible object is {@link Date }
         * 
         */
        public XMLGregorianCalendar getVERSDATE() {
            return versdate;
        }

        /**
         * Sets the value of the versdate property.
         * 
         * @param value
         *            allowed object is {@link Date }
         * 
         */
        public void setVERSDATE(XMLGregorianCalendar value) {
            this.versdate = value;
        }

        @Override
        public void marshal(Element fileGrp, Document doc) {
            super.marshal(fileGrp, doc);

            String metsNs = NS.METS.ns();

            Attr versdate = doc.createAttribute("VERSDATE");
            if (this.versdate != null) {
                versdate.setNodeValue(this.versdate.toXMLFormat());
                fileGrp.setAttributeNode(versdate);
            }
            if (this.use != null)
                fileGrp.setAttribute("USE", this.use);
            if (this.fileGrp != null) {
                for (FileGrp fg : this.fileGrp) {
                    Element subFileGrp = doc.createElementNS(metsNs, "mets:fileGrp");
                    fg.marshal(subFileGrp, doc);
                    fileGrp.appendChild(subFileGrp);
                }
            }
            if (this.file != null) {
                for (File f : this.file) {
                    Element file = doc.createElementNS(metsNs, "mets:file");
                    f.marshal(file, doc);
                    fileGrp.appendChild(file);
                }
            }
        }

        @Override
        public void unmarshal(Element fileGrp) {
            super.unmarshal(fileGrp);
            if (fileGrp.hasAttribute("VERSDATE")) {
                DatatypeFactory dtf = MetsIO.getDataTypeFactory();
                String value = fileGrp.getAttribute("VERSDATE");
                this.versdate = dtf.newXMLGregorianCalendar(value);
            }
            if (fileGrp.hasAttribute("USE"))
                this.use = fileGrp.getAttribute("USE");
            List<Element> children = DOMHelp.getChildElements(fileGrp);
            for (Element child : children) {
                String localName = child.getLocalName();
                if (localName.equals("fileGrp")) {
                    FileGrp subFileGrp = new FileGrp();
                    subFileGrp.unmarshal(child);
                    this.getFileGrp().add(subFileGrp);
                }
                if (localName.equals("file")) {
                    File file = new File();
                    file.unmarshal(child);
                    this.getFile().add(file);
                }
            }
        }

        /**
         * Representation of a <code>mets:file</code>
         * <p>
         * From the METS Schema:
         * <p>
         * The file element provides access to content files for a METS object. A file element may
         * contain one or more FLocat elements, which provide pointers to a content file, and/or an
         * FContent element, which wraps an encoded version of the file. Note that ALL FLocat and
         * FContent elements underneath a single file element should identify/contain identical
         * copies of a single file.
         */
        public static class File extends IDElement implements ElementInterface {

            protected String mimetype;
            protected List<String> admid;
            protected Long size;
            protected XMLGregorianCalendar created;
            protected String checksum;
            protected CHECKSUMTYPE checksumtype;
            protected static DatatypeFactory dtf;
            protected List<FLocat> fLocat;
            protected File.FContent fContent;
            protected List<Stream> stream;
            protected List<TransformFile> transformFile;
            protected List<File> file;
            protected Integer seq;
            protected String ownerid;
            protected String use;
            protected List<String> dmdid;
            protected String groupid;
            protected String begin;
            protected String end;
            protected BETYPE betype;

            public File(String id) {
                this.id = id;
                if (dtf == null)
                    dtf = MetsIO.getDataTypeFactory();
            }

            // internal only
            protected File() {
            };

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
             * Gets the value of <code>@USE</code>
             * 
             * @return the String value of <code>@USE</code>
             */
            public String getUse() {
                return use;
            }

            /**
             * Sets the value of <code>@USE</code>
             * 
             * @param use
             *            the String value for <code>@USE</code>
             */
            public void setUse(String use) {
                this.use = use;
            }

            /**
             * Gets the fLocat List
             * 
             * <p>
             * To add a new FLocat, do as follows:
             * 
             * <pre>
             * getFLocat().add(newFLocat);
             * </pre>
             */
            public List<FLocat> getFLocat() {
                if (fLocat == null) {
                    fLocat = new ArrayList<File.FLocat>();
                }
                return this.fLocat;
            }

            /**
             * Gets the FContent element
             * 
             * @return the FContent element as an FContent object
             * 
             */
            public File.FContent getFContent() {
                return fContent;
            }

            /**
             * Sets the FContent element
             * 
             * @param fcontent
             */
            public void setFContent(File.FContent fcontent) {
                this.fContent = fcontent;
            }

            /**
             * Gets the the stream List
             * 
             * <p>
             * To add a new Stream, do:
             * 
             * <pre>
             * getStream().add(newStream);
             * </pre>
             */
            public List<Stream> getStream() {
                if (stream == null) {
                    stream = new ArrayList<File.Stream>();
                }
                return this.stream;
            }

            /**
             * Gets the TransformFile List
             * 
             * <p>
             * To add a new item, do:
             * 
             * <pre>
             * getTransformFile().add(newItem);
             * </pre>
             */
            public List<TransformFile> getTransformFile() {
                if (transformFile == null) {
                    transformFile = new ArrayList<File.TransformFile>();
                }
                return this.transformFile;
            }

            /**
             * Gets the file List
             * 
             * <p>
             * To add a new File, do:
             * 
             * <pre>
             * getFile().add(newFile);
             * </pre>
             */
            public List<File> getFile() {
                if (file == null) {
                    file = new ArrayList<File>();
                }
                return this.file;
            }

            /**
             * Gets the value of <code>@SEQ</code> as an Integer
             * 
             * @return the integer value of <code>@SEQ</code>
             * 
             */
            public Integer getSEQ() {
                return seq;
            }

            /**
             * Sets the value of <code>@SEQ</code>
             * 
             * @param seq
             */
            public void setSEQ(Integer seq) {
                this.seq = seq;
            }

            /**
             * Gets the value of <code>@OWNERID</code>
             * 
             * @return the String value of <code>@OWNERID</code>
             * 
             */
            public String getOWNERID() {
                return ownerid;
            }

            /**
             * Sets the value of <code>@OWNERID</code>
             * 
             * @param ownerid
             */
            public void setOWNERID(String ownerid) {
                this.ownerid = ownerid;
            }

            /**
             * Gets the dmdid List
             * 
             * <p>
             * To add a new DMDID, do:
             * 
             * <pre>
             * getDMDID().add(newDMDID);
             * </pre>
             */
            public List<String> getDMDID() {
                if (dmdid == null) {
                    dmdid = new ArrayList<String>();
                }
                return this.dmdid;
            }

            /**
             * Gets the value of <code>@GROUPID</code>
             * 
             * @return the String value of <code>@GROUPID</code>
             * 
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
             * Gets the value of <code>@BEGIN</code>
             * 
             * @return the String value of <code>@BEGIN</code>
             */
            public String getBEGIN() {
                return begin;
            }

            /**
             * Sets the value of <code>@BEGIN</code>
             * 
             * @param begin
             */
            public void setBEGIN(String begin) {
                this.begin = begin;
            }

            /**
             * Gets the value of <code>@END</code>
             * 
             * @return the String value of <code>@END</code>
             */
            public String getEND() {
                return end;
            }

            /**
             * Sets the value of <code>@END</code>
             * 
             * @param end
             */
            public void setEND(String end) {
                this.end = end;
            }

            /**
             * Gets the {@link BETYPE} enum object for <code>@BETYPE</code>
             * 
             * @return a {@link BETYPE} instance
             */

            public File.BETYPE getBETYPE() {
                return betype;
            }

            /**
             * Sets <code>@BETYPE</code>
             * 
             * @param betype
             */
            public void setBETYPE(BETYPE betype) {
                this.betype = betype;
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
            public void marshal(Element file, Document doc) {
                super.marshal(file, doc);

                String metsNs = NS.METS.ns();

                if (this.seq != null)
                    file.setAttribute("SEQ", this.seq.toString());
                if (this.ownerid != null)
                    file.setAttribute("OWNERID", this.ownerid);
                if (this.dmdid != null) {
                    String value = MetsWriter.listToString(this.dmdid);
                    file.setAttribute("DMDID", value);
                }
                if (this.groupid != null)
                    file.setAttribute("GROUPID", this.groupid);
                if (this.begin != null)
                    file.setAttribute("BEGIN", this.begin);
                if (this.use != null)
                    file.setAttribute("USE", this.use);
                if (this.end != null)
                    file.setAttribute("END", this.end);
                if (this.betype != null)
                    file.setAttribute("BETYPE", this.betype.value());
                if (this.mimetype != null)
                    file.setAttribute("MIMETYPE", this.mimetype);
                if (this.size != null)
                    file.setAttribute("SIZE", this.size.toString());
                if (this.checksum != null)
                    file.setAttribute("CHECKSUM", this.checksum);
                if (this.checksumtype != null) {
                    Attr cstype = doc.createAttribute("CHECKSUMTYPE");
                    cstype.setNodeValue(this.checksumtype.value());
                    file.setAttributeNode(cstype);
                }
                if (this.created != null) {
                    Attr created = doc.createAttribute("CREATED");
                    created.setNodeValue(this.created.toXMLFormat());
                    file.setAttributeNode(created);
                }

                if (this.admid != null) {
                    // can be a sequence of space-separated Strings
                    Attr admid = doc.createAttribute("ADMID");
                    admid.setNodeValue(MetsWriter.listToString(this.admid));
                    file.setAttributeNode(admid);
                }

                // fLocat : List<FLocat>
                if (this.fLocat != null) {
                    for (FLocat f : this.fLocat) {
                        Element flocat;
                        flocat = doc.createElementNS(metsNs, "mets:FLocat");
                        f.marshal(flocat, doc);
                        file.appendChild(flocat);
                    }
                }
                // fContent : FContent
                if (this.fContent != null) {
                    Element fcontent;
                    fcontent = doc.createElementNS(metsNs, "mets:FContent");
                    this.fContent.marshal(fcontent, doc);
                    file.appendChild(fcontent);
                }

                // stream : List<Stream>
                if (this.stream != null) {
                    for (Stream s : this.stream) {
                        Element stream;
                        stream = doc.createElementNS(metsNs, "mets:stream");
                        s.marshal(stream, doc);
                        file.appendChild(stream);
                    }
                }
                if (this.transformFile != null) {
                    for (TransformFile t : this.transformFile) {
                        Element transformFile;
                        transformFile = doc.createElementNS(metsNs, "mets:transformFile");
                        t.marshal(transformFile, doc);
                        file.appendChild(transformFile);
                    }
                }
                // file : List<File>
                if (this.file != null) {
                    for (File f : this.file) {
                        Element subFile;
                        subFile = doc.createElementNS(metsNs, "mets:file");
                        f.marshal(subFile, doc);
                        file.appendChild(subFile);
                    }
                }

            }

            @Override
            public void unmarshal(Element file) {
                super.unmarshal(file);

                if (dtf == null)
                    dtf = MetsIO.getDataTypeFactory();

                NamedNodeMap attrs = file.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Attr attr = (Attr) attrs.item(i);
                    String name = attr.getName();
                    String value = attr.getNodeValue();
                    if (name.equals("ADMID"))
                        this.admid = MetsReader.parseIDREFAttr(value);
                    if (name.equals("SEQ"))
                        this.seq = new Integer(value);
                    if (name.equals("USE"))
                        this.use = value;
                    if (name.equals("OWNERID"))
                        this.ownerid = value;
                    if (name.equals("DMDID"))
                        this.dmdid = MetsReader.parseIDREFAttr(value);
                    if (name.equals("GROUPID"))
                        this.groupid = value;
                    if (name.equals("BEGIN"))
                        this.begin = value;
                    if (name.equals("END"))
                        this.end = value;
                    if (name.equals("BETYPE"))
                        this.betype = BETYPE.fromValue(value);
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

                List<Element> children = DOMHelp.getChildElements(file);
                for (Element child : children) {
                    String localName = child.getLocalName();

                    if (localName.equals("FLocat")) {
                        FLocat fLocat = new FLocat();
                        fLocat.unmarshal(child);
                        this.getFLocat().add(fLocat);
                    }
                    if (localName.equals("FContent")) {
                        this.fContent = new FContent();
                        this.fContent.unmarshal(child);
                    }
                    if (localName.equals("stream")) {
                        Stream stream = new Stream();
                        stream.unmarshal(child);
                        this.getStream().add(stream);
                    }
                    if (localName.equals("transformFile")) {
                        TransformFile transformFile = new TransformFile();
                        transformFile.unmarshal(child);
                        this.getTransformFile().add(transformFile);
                    }
                    if (localName.equals("file")) {
                        File subFile = new File();
                        subFile.unmarshal(child);
                        this.getFile().add(subFile);
                    }
                }
            }

            /**
             * Enumeration of possible values for <code>@BETYPE</code>
             */
            public enum BETYPE {
                BYTE;
                public String value() {
                    return name();
                }

                public static BETYPE fromValue(String v) {
                    return valueOf(v);
                }
            }

            /**
             * <strong>Warning: <code>mets:binData</code> NOT IMPLEMENTED</strong>
             * <p>
             * From the METS Schema:
             * <p>
             * <blockquote>The file content element <FContent> is used to identify a content file
             * contained internally within a METS document. The content file must be either Base64
             * encoded and contained within the subsidiary <binData> wrapper element, or consist of
             * XML information and be contained within the subsidiary <xmlData> wrapper element.
             * 
             */
            public static class FContent extends IDElement implements ElementInterface {

                /**
                 * <strong>Not supported in this version.</strong>
                 */
                protected byte[] binData;
                protected List<Element> xmlData;
                protected String id;
                protected String use;

                // /**
                // * Gets the value of the binData property.
                // *
                // * @return possible object is byte[]
                // */
                // public byte[] getBinData() {
                // return binData;
                // }
                //
                // /**
                // * Sets the value of the binData property.
                // *
                // * @param value
                // * allowed object is byte[]
                // */
                // public void setBinData(byte[] value) {
                // this.binData = ((byte[]) value);
                // }

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
                 * Gets the value of <code>@USE</code>
                 * 
                 * @return the String value of <code>@USE</code>
                 */
                public String getUse() {
                    return use;
                }

                /**
                 * Sets the value of <code>@USE</code>
                 * 
                 * @param use
                 *            the String value for <code>@USE</code>
                 */
                public void setUse(String use) {
                    this.use = use;
                }

                @Override
                public void marshal(Element fcontent, Document doc) {
                    super.marshal(fcontent, doc);
                    String mns = NS.METS.ns();
                    if (this.use != null)
                        fcontent.setAttribute("USE", this.use);
                    if (this.xmlData != null) {
                        Element xd = doc.createElementNS(mns, "mets:xmlData");
                        fcontent.appendChild(xd);
                        for (Element e : this.xmlData) {
                            Element data = (Element) doc.importNode(e, true);
                            xd.appendChild(data);
                        }
                    }
                }

                @Override
                public void unmarshal(Element fcontent) {
                    super.unmarshal(fcontent);
                    if (fcontent.hasAttribute("USE"))
                        this.use = fcontent.getAttribute("USE");
                    List<Element> children = DOMHelp.getChildElements(fcontent);
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
             * Representation of a <code>mets:FLocat</code>
             * <p>
             * From the METS Schema:
             * 
             * <blockquote>
             * <p>
             * The file location element <FLocat> provides a pointer to the location of a content
             * file. It uses the XLink reference syntax to provide linking information indicating
             * the actual location of the content file, along with other attributes specifying
             * additional linking information. NOTE: <FLocat> is an empty element. The location of
             * the resource pointed to MUST be stored in the xlink:href attribute.
             * 
             */

            public static class FLocat extends LocatorElement implements ElementInterface {

                protected String use;

                /**
                 * Gets the value of <code>@USE</code>
                 * 
                 * @return the String value of <code>@USE</code>
                 */
                public String getUse() {
                    return use;
                }

                /**
                 * Sets the value of <code>@USE</code>
                 * 
                 * @param use
                 *            the String value for <code>@USE</code>
                 */
                public void setUse(String use) {
                    this.use = use;
                }

                @Override
                public void marshal(Element e, Document d) {
                    super.marshal(e, d);
                    if (this.use != null)
                        e.setAttribute("USE", this.use);
                }

                @Override
                public void unmarshal(Element e) {
                    super.unmarshal(e);
                    if (e.hasAttribute("USE"))
                        this.use = e.getAttribute("USE");
                }

            }

            /**
             * Representation of a mets:stream
             * <p>
             * From the METS schema:
             * <p>
             * <blockquote> A component byte stream element <stream> may be composed of one or more
             * subsidiary streams. An MPEG4 file, for example, might contain separate audio and
             * video streams, each of which is associated with technical metadata. The repeatable
             * <stream> element provides a mechanism to record the existence of separate data
             * streams within a particular file, and the opportunity to associate <dmdSec> and
             * <amdSec> with those subsidiary data streams if desired.
             */

            public static class Stream extends IDElement implements ElementInterface {

                protected String streamType;
                protected String ownerid;
                protected List<String> dmdid;
                protected String begin;
                protected String end;
                protected BETYPE betype;
                /**
                 * From the METS Schema: <blockquote>
                 * <p>
                 * Contains the ID attribute values of the <techMD>, <sourceMD>, <rightsMD> and/or
                 * <digiprovMD> elements within the <amdSec> of the METS document that contain
                 * administrative metadata pertaining to the METS document itself. For more
                 * information on using METS IDREFS and IDREF type attributes for internal linking,
                 * see Chapter 4 of the METS Primer.
                 */
                protected List<String> admid;

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
                 * Gets the value of <code>@streamType</code>
                 * 
                 * @return the String value of <code>@streamType</code>
                 * 
                 */
                public String getStreamType() {
                    return streamType;
                }

                /**
                 * Sets the value of <code>@streamType</code>
                 * 
                 * @param streamType
                 */
                public void setStreamType(String streamType) {
                    this.streamType = streamType;
                }

                /**
                 * Gets the value of <code>@OWNERID</code>
                 * 
                 * @return the String value of <code>@OWNERID</code>
                 * 
                 */
                public String getOWNERID() {
                    return ownerid;
                }

                /**
                 * Sets the value of <code>@OWNERID</code>
                 * 
                 * @param ownerid
                 */
                public void setOWNERID(String ownerid) {
                    this.ownerid = ownerid;
                }

                /**
                 * Gets the <code>@DMDID</code> List
                 * 
                 * <p>
                 * To add a new item, do:
                 * 
                 * <pre>
                 * getDMDID().add(newDMDID);
                 * </pre>
                 */
                public List<String> getDMDID() {
                    if (dmdid == null) {
                        dmdid = new ArrayList<String>();
                    }
                    return this.dmdid;
                }

                /**
                 * Gets the value of <code>@BEGIN</code>
                 * 
                 * @return the String value of <code>@BEGIN</code>
                 */
                public String getBEGIN() {
                    return begin;
                }

                /**
                 * Sets the value of <code>@BEGIN</code>
                 * 
                 * @param begin
                 */
                public void setBEGIN(String begin) {
                    this.begin = begin;
                }

                /**
                 * Gets the value of <code>@END</code>
                 * 
                 * @return the String value of <code>@END</code>
                 */
                public String getEND() {
                    return end;
                }

                /**
                 * Sets the value of <code>@END</code>
                 * 
                 * @param end
                 */
                public void setEND(String end) {
                    this.end = end;
                }

                /**
                 * Gets the {@link BETYPE} enum object for <code>@BETYPE</code>
                 * 
                 * @return a {@link BETYPE} instance
                 */

                public File.BETYPE getBETYPE() {
                    return betype;
                }

                /**
                 * Sets <code>@BETYPE</code>
                 * 
                 * @param betype
                 */
                public void setBETYPE(BETYPE betype) {
                    this.betype = betype;
                }

                @Override
                public void marshal(Element e, Document d) {
                    super.marshal(e, d);
                    // ADMID
                    if (this.admid != null) {
                        Attr admid = d.createAttribute("ADMID");
                        admid.setNodeValue(MetsWriter.listToString(this.admid));
                        e.setAttributeNode(admid);
                    }
                    if (this.ownerid != null)
                        e.setAttribute("OWNERID", this.ownerid);
                    if (this.dmdid != null) {
                        String value = MetsWriter.listToString(this.dmdid);
                        e.setAttribute("DMDID", value);
                    }
                    if (this.streamType != null)
                        e.setAttribute("streamType", this.streamType);
                    if (this.begin != null)
                        e.setAttribute("BEGIN", this.begin);
                    if (this.end != null)
                        e.setAttribute("END", this.end);
                    if (this.betype != null)
                        e.setAttribute("BETYPE", this.betype.value());
                }

                @Override
                public void unmarshal(Element e) {
                    super.unmarshal(e);
                    NamedNodeMap attrs = e.getAttributes();
                    for (int i = 0; i < attrs.getLength(); i++) {
                        Attr attr = (Attr) attrs.item(i);
                        String name = attr.getName();
                        String value = attr.getNodeValue();
                        if (name.equals("ADMID"))
                            this.admid = MetsReader.parseIDREFAttr(value);
                        if (name.equals("OWNERID"))
                            this.ownerid = value;
                        if (name.equals("DMDID"))
                            this.dmdid = MetsReader.parseIDREFAttr(value);
                        if (name.equals("BEGIN"))
                            this.begin = value;
                        if (name.equals("END"))
                            this.end = value;
                        if (name.equals("BETYPE"))
                            this.betype = BETYPE.fromValue(value);
                        if (name.equals("streamType"))
                            this.streamType = value;
                    }
                }
            }

            /**
             * 
             * <p>
             * From the METS Schema:
             * 
             * <blockquote>
             * <p>
             * The transform file element <transformFile> provides a means to access any subsidiary
             * files listed below a <file> element by indicating the steps required to "unpack" or
             * transform the subsidiary files. This element is repeatable and might provide a link
             * to a <behavior> in the <behaviorSec> that performs the transformation.
             */
            public static class TransformFile extends IDElement implements ElementInterface {

                protected TRANSFORMTYPE transformtype;
                protected String transformalgorithm;
                protected String transformkey;
                protected String transformbehavior;
                protected BigInteger transformorder;

                /**
                 * Gets the {@link TRANSFORMTYPE} enum object for <code>@TRANSFORMTYPE</code>
                 * 
                 * @return a {@link TRANSFORMTYPE} instance
                 */
                public TRANSFORMTYPE getTRANSFORMTYPE() {
                    return transformtype;
                }

                /**
                 * Sets <code>@TRANSFORMTYPE</code>
                 * 
                 * @param shape
                 * 
                 */
                public void setTRANSFORMTYPE(TRANSFORMTYPE value) {
                    this.transformtype = value;
                }

                /**
                 * Gets the value of <code>@TRANSFORMMALGORITHM</code>
                 * 
                 * @return the String value of <code>@TRANSFORMMALGORITHM</code>
                 */
                public String getTRANSFORMALGORITHM() {
                    return transformalgorithm;
                }

                /**
                 * Sets the value of <code>@TRANSFORMMALGORITHMY</code>
                 * 
                 * @param value
                 */
                public void setTRANSFORMALGORITHM(String value) {
                    this.transformalgorithm = value;
                }

                /**
                 * Gets the value of <code>@TRANSFORMKEY</code>
                 * 
                 * @return the String value of <code>@TRANSFORMKEY</code>
                 */
                public String getTRANSFORMKEY() {
                    return transformkey;
                }

                /**
                 * Sets the value of <code>@TRANSFORMKEY</code>
                 * 
                 * @param value
                 */
                public void setTRANSFORMKEY(String value) {
                    this.transformkey = value;
                }

                /**
                 * Gets the value of <code>@TRANSFORMBEHAVIOR</code>
                 * 
                 * @return the String value of <code>@TRANSFORMBEHAVIOR</code>
                 */
                public java.lang.Object getTRANSFORMBEHAVIOR() {
                    return transformbehavior;
                }

                /**
                 * Sets the value of <code>@TRANSFORMBEHAVIOR</code>
                 * 
                 * @param value
                 */
                public void setTRANSFORMBEHAVIOR(String value) {
                    this.transformbehavior = value;
                }

                /**
                 * Gets the value of the transformorder property.
                 * 
                 * @return possible object is {@link BigInteger }
                 * 
                 */
                public BigInteger getTRANSFORMORDER() {
                    return transformorder;
                }

                /**
                 * Sets the value of <code>@TRANSFORMORDER</code>
                 * 
                 * @param bigint
                 */
                public void setTRANSFORMORDER(BigInteger bigint) {
                    this.transformorder = bigint;
                }

                @Override
                public void marshal(Element e, Document d) {
                    super.marshal(e, d);
                    if (this.transformtype != null) {
                        Attr attr = d.createAttribute("TRANSFORMTYPE");
                        attr.setNodeValue(this.transformtype.value());
                        e.setAttributeNode(attr);
                    }
                    if (this.transformalgorithm != null) {
                        Attr attr = d.createAttribute("TRANSFORMALGORITHM");
                        attr.setNodeValue(this.transformalgorithm);
                        e.setAttributeNode(attr);
                    }
                    if (this.transformkey != null) {
                        Attr attr = d.createAttribute("TRANSFORMKEY");
                        attr.setNodeValue(this.transformkey);
                        e.setAttributeNode(attr);
                    }
                    if (this.transformbehavior != null) {
                        Attr attr = d.createAttribute("TRANSFORMBEHAVIOR");
                        attr.setNodeValue(this.transformbehavior);
                        e.setAttributeNode(attr);
                    }
                    if (this.transformorder != null) {
                        Attr attr = d.createAttribute("TRANSFORMORDER");
                        attr.setNodeValue(this.transformorder.toString());
                        e.setAttributeNode(attr);
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
                        if (name.equals("TRANSFORMTYPE"))
                            this.transformtype = TRANSFORMTYPE.fromValue(value);
                        if (name.equals("TRANSFORMALGORITHM"))
                            this.transformalgorithm = value;
                        if (name.equals("TRANSFORMKEY"))
                            this.transformkey = value;
                        if (name.equals("TRANSFORMBEHAVIOR"))
                            this.transformbehavior = value;
                        if (name.equals("TRANSFORMORDER"))
                            this.transformorder = new BigInteger(value);
                    }
                }

                /**
                 * Enumeration of allowed values for <code>@TRANSFORMTYPE</code>
                 */
                public enum TRANSFORMTYPE {

                    DECOMPRESSION("decompression"),

                    DECRYPTION("decryption");
                    private final String value;

                    TRANSFORMTYPE(String v) {
                        value = v;
                    }

                    public String value() {
                        return value;
                    }

                    public static File.TransformFile.TRANSFORMTYPE fromValue(String v) {
                        for (File.TransformFile.TRANSFORMTYPE c : File.TransformFile.TRANSFORMTYPE.values()) {
                            if (c.value.equals(v)) {
                                return c;
                            }
                        }
                        throw new IllegalArgumentException(v);
                    }

                }

            }

        }

    }

}