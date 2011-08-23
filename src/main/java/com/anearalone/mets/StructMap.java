/**
 * StructMap.java 
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
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.anearalone.utils.DOMHelp;

/**
 * Representation of a <code>mets:structMap</code>.
 * <p>
 * From the METS Schema: <blockquote>
 * <p>
 * The structural map section <structMap> is the heart of a METS document. It provides a means for
 * organizing the digital content represented by the <file> elements in the <fileSec> of the METS
 * document into a coherent hierarchical structure. Such a hierarchical structure can be presented
 * to users to facilitate their comprehension and navigation of the digital content. It can further
 * be applied to any purpose requiring an understanding of the structural relationship of the
 * content files or parts of the content files. The organization may be specified to any level of
 * granularity (intellectual and or physical) that is desired. Since the <structMap> element is
 * repeatable, more than one organization can be applied to the digital content represented by the
 * METS document. The hierarchical structure specified by a <structMap> is encoded as a tree of
 * nested <div> elements. A <div> element may directly point to content via child file pointer
 * <fptr> elements (if the content is represented in the <fileSec<) or child METS pointer <mptr>
 * elements (if the content is represented by an external METS document). The <fptr> element may
 * point to a single whole <file> element that manifests its parent <div<, or to part of a <file>
 * that manifests its <div<. It can also point to multiple files or parts of files that must be
 * played/displayed either in sequence or in parallel to reveal its structural division. In addition
 * to providing a means for organizing content, the <structMap> provides a mechanism for linking
 * content at any hierarchical level with relevant descriptive and administrative metadata.
 * <p>
 * Note that because the METS Schema requires at least one instance of a structMap, the first
 * instance of {@link Mets#structMap} will always be populated with an empty {@link StructMap}.
 */

public class StructMap extends IDElement implements ElementInterface {

    protected Div div;
    protected String label;
    protected String type;

    /**
     * Gets the <code>mets:div</code> child
     * 
     * @return a {@link Div} object representing this element
     */
    public Div getDiv() {
        if (this.div == null)
            this.div = new Div();
        return div;
    }

    /**
     * Sets the <code>mets:div</code> child
     * 
     * @param div
     */
    public void setDiv(Div div) {
        this.div = div;
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

    @Override
    public void marshal(Element smap, Document doc) {
        super.marshal(smap, doc);

        if (this.type != null)
            smap.setAttribute("TYPE", this.type);
        if (this.label != null)
            smap.setAttribute("LABEL", this.label);

        // The root div is required, so make it if it doesn't exist
        if (this.div == null)
            this.div = new Div();
        Element div = doc.createElementNS(NS.METS.ns(), "mets:div");
        this.div.marshal(div, doc);
        smap.appendChild(div);
    }

    @Override
    public void unmarshal(Element smap) {
        super.unmarshal(smap);
        if (smap.hasAttribute("LABEL"))
            this.label = smap.getAttribute("LABEL");
        if (smap.hasAttribute("TYPE"))
            this.type = smap.getAttribute("TYPE");
        Element divE = DOMHelp.getFirstChildElement(smap);
        if (divE != null) {
            this.div = new Div();
            this.div.unmarshal(divE);
        }
    }

    /**
     * Representation of a <code>mets:div</code>.
     * <p>
     * From the METS Schema: <blockquote>
     * <p>
     * The METS standard represents a document structurally as a series of nested div elements, that
     * is, as a hierarchy (e.g., a book, which is composed of chapters, which are composed of
     * subchapters, which are composed of text). Every div node in the structural map hierarchy may
     * be connected (via subsidiary mptr or fptr elements) to content files which represent that
     * div's portion of the whole document.
     * <p>
     * SPECIAL NOTE REGARDING DIV ATTRIBUTE VALUES: to clarify the differences between the ORDER,
     * ORDERLABEL, and LABEL attributes for the <div> element, imagine a text with 10 roman numbered
     * pages followed by 10 arabic numbered pages. Page iii would have an ORDER of "3", an
     * ORDERLABEL of "iii" and a LABEL of "Page iii", while page 3 would have an ORDER of "13", an
     * ORDERLABEL of "3" and a LABEL of "Page 3".
     */

    public static class Div extends IDElement implements ElementInterface {
        protected List<Mptr> mptr;
        protected List<Fptr> fptr;
        protected List<Div> div;
        protected BigInteger order;
        protected String orderlabel;
        protected String label;
        protected List<String> dmdid;
        protected List<String> admid;
        protected List<String> contentids;
        protected String xlinkLabel;
        protected String type;

        /**
         * Gets the mptr List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getMptr().add(newMptr);
         * </pre>
         */
        public List<Mptr> getMptr() {
            if (mptr == null) {
                mptr = new ArrayList<Mptr>();
            }
            return this.mptr;
        }

        /**
         * Gets the fptr List
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getFptr().add(newFptr);
         * </pre>
         */
        public List<Fptr> getFptr() {
            if (fptr == null) {
                fptr = new ArrayList<Fptr>();
            }
            return this.fptr;
        }

        /**
         * Gets the div List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getDiv().add(newDiv);
         * </pre>
         */
        public List<Div> getDiv() {
            if (div == null) {
                div = new ArrayList<Div>();
            }
            return this.div;
        }

        /**
         * Gets <code>@ORDER</code>
         * 
         * @return a {@link BigInteger} representing this attribute
         */
        public BigInteger getORDER() {
            return order;
        }

        /**
         * Sets the <code>@ORDER</code> attribute
         * 
         * @param order
         */
        public void setORDER(BigInteger order) {
            this.order = order;
        }

        /**
         * Gets <code>@ORDERLABEL</code>
         * 
         * @return the String value of <code>@ORDERLABEL</code>
         */
        public String getORDERLABEL() {
            return orderlabel;
        }

        /**
         * Sets <code>@ORDERLABEL</code>
         * 
         * @param orderlabel
         */
        public void setORDERLABEL(String orderlabel) {
            this.orderlabel = orderlabel;
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
         * Gets the <code>@ADMID</code> List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getADMID().add(newADMID);
         * </pre>
         */
        public List<String> getADMID() {
            if (admid == null) {
                admid = new ArrayList<String>();
            }
            return this.admid;
        }

        /**
         * Gets the <code>@CONTENTIDS</code> List
         * 
         * <p>
         * To add a new item, do:
         * 
         * <pre>
         * getCONTENTIDS().add(newCONTENTID);
         * </pre>
         */
        public List<String> getCONTENTIDS() {
            if (contentids == null) {
                contentids = new ArrayList<String>();
            }
            return this.contentids;
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
         * Gets <code>@xlink:label</code>, an xlink label to be referred to by an smLink element
         * 
         * @return the String value of <code>xlink:label</code>
         */
        public String getXlinkLabel() {
            return xlinkLabel;
        }

        /**
         * Sets <code>@xlink:label</code>, an xlink label to be referred to by an smLink element
         * 
         * @param label
         */
        public void setXlinkLabel(String label) {
            this.xlinkLabel = label;
        }

        @Override
        public void unmarshal(Element e) {
            super.unmarshal(e);

            NamedNodeMap attrs = e.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr = (Attr) attrs.item(i);
                String name = attr.getName();
                String value = attr.getNodeValue();
                if (name.equals("ORDERLABEL"))
                    this.orderlabel = value;
                if (name.equals("LABEL"))
                    this.label = value;
                if (name.equals("TYPE"))
                    this.type = value;
                if (name.equals("ORDER"))
                    this.order = new BigInteger(value);
                if (name.equals("xlink:label"))
                    this.orderlabel = value;
                if (name.equals("ADMID"))
                    this.admid = MetsReader.parseIDREFAttr(value);
                if (name.equals("DMDID"))
                    this.dmdid = MetsReader.parseIDREFAttr(value);
                if (name.equals("CONTENTIDS"))
                    this.contentids = MetsReader.parseIDREFAttr(value);
            }

            List<Element> children = DOMHelp.getChildElements(e);
            for (Element child : children) {
                String localName = child.getLocalName();
                if (localName.equals("div")) {
                    Div subDiv = new Div();
                    subDiv.unmarshal(child);
                    this.getDiv().add(subDiv);
                }
                if (localName.equals("mptr")) {
                    Mptr mptr = new Mptr();
                    mptr.unmarshal(child);
                    this.getMptr().add(mptr);
                }
                if (localName.equals("fptr")) {
                    Fptr fptr = new Fptr();
                    fptr.unmarshal(child);
                    this.getFptr().add(fptr);
                }
            }
        }

        @Override
        public void marshal(Element div, Document doc) {
            super.marshal(div, doc);

            String metsNs = NS.METS.ns();

            if (this.orderlabel != null)
                div.setAttribute("ORDERLABEL", this.orderlabel);
            if (this.order != null)
                div.setAttribute("ORDER", this.order.toString());
            if (this.label != null)
                div.setAttribute("LABEL", this.label);
            if (this.type != null)
                div.setAttribute("TYPE", this.type);
            if (this.xlinkLabel != null)
                div.setAttributeNS(NS.XLINK.ns(), "TYPE", this.type);
            if (this.admid != null) {
                Attr admid = doc.createAttribute("ADMID");
                admid.setNodeValue(MetsWriter.listToString(this.admid));
                div.setAttributeNode(admid);
            }
            if (this.dmdid != null) {
                Attr dmdid = doc.createAttribute("DMDID");
                dmdid.setNodeValue(MetsWriter.listToString(this.dmdid));
                div.setAttributeNode(dmdid);
            }
            if (this.contentids != null) {
                Attr contentids = doc.createAttribute("CONTENTIDS");
                contentids.setNodeValue(MetsWriter.listToString(this.contentids));
                div.setAttributeNode(contentids);
            }
            if (this.mptr != null) {
                for (Mptr m : this.mptr) {
                    Element mptr = doc.createElementNS(metsNs, "mets:mptr");
                    m.marshal(mptr, doc);
                    div.appendChild(mptr);
                }
            }
            if (this.fptr != null) {
                for (Fptr m : this.fptr) {
                    Element fptr = doc.createElementNS(metsNs, "mets:fptr");
                    m.marshal(fptr, doc);
                    div.appendChild(fptr);
                }
            }
            if (this.div != null) {
                for (Div d : this.div) {
                    Element subDiv = doc.createElementNS(metsNs, "mets:div");
                    d.marshal(subDiv, doc);
                    div.appendChild(subDiv);
                }
            }
        }

        /**
         * Representation of a <code>mets:fptr</code>.
         * <p>
         * From the METS Schema: <blockquote>
         * <p>
         * The <fptr> or file pointer element represents digital content that manifests its parent
         * <div> element. The content represented by an <fptr> element must consist of integral
         * files or parts of files that are represented by <file> elements in the <fileSec>. Via its
         * FILEID attribute, an <fptr> may point directly to a single integral <file> element that
         * manifests a structural division. However, an <fptr> element may also govern an <area>
         * element, a <par>, or a <seq> which in turn would point to the relevant file or files. A
         * child <area> element can point to part of a <file> that manifests a division, while the
         * <par> and <seq> elements can point to multiple files or parts of files that together
         * manifest a division. More than one <fptr> element can be associated with a <div> element.
         * Typically sibling <fptr> elements represent alternative versions, or manifestations, of
         * the same content.
         * 
         */
        public static class Fptr extends IDElement implements ElementInterface {
            protected Par par;
            protected Seq seq;
            protected Area area;
            protected String fileid;
            protected List<String> contentids;

            /**
             * Gets the <code>mets:par</code> child
             * 
             * @return a {@link Par} object representing this element
             * 
             */
            public Par getPar() {
                return par;
            }

            /**
             * Sets the <code>mets:par</code> child
             * 
             * @param par
             */
            public void setPar(Par value) {
                this.par = value;
            }

            /**
             * Gets the <code>mets:seq</code> child
             * 
             * @return a {@link Seq} object representing this element
             * 
             */
            public Seq getSeq() {
                return seq;
            }

            /**
             * Sets the <code>mets:seq</code> child
             * 
             * @param seq
             */
            public void setSeq(Seq value) {
                this.seq = value;
            }

            /**
             * Gets the <code>mets:area</code> child
             * 
             * @return an {@link Area} object representing this element
             * 
             */
            public Area getArea() {
                return area;
            }

            /**
             * Sets the <code>mets:area</code> child
             * 
             * @param area
             */
            public void setArea(Area area) {
                this.area = area;
            }

            /**
             * Gets the value of <code>@FILEID</code>
             * 
             * @return the String value of <code>@FILEID</code>
             */
            public String getFILEID() {
                return fileid;
            }

            /**
             * Sets the value of <code>@FILEID</code>
             * 
             * @param value
             */
            public void setFILEID(String fileid) {
                this.fileid = fileid;
            }

            /**
             * Gets the CONTENTIDS List
             * 
             * <p>
             * To add a new item, do:
             * 
             * <pre>
             * getCONTENTIDS().add(newItem);
             * </pre>
             */
            public List<String> getCONTENTIDS() {
                if (contentids == null) {
                    contentids = new ArrayList<String>();
                }
                return this.contentids;
            }

            @Override
            public void marshal(Element fptr, Document doc) {
                super.marshal(fptr, doc);

                String metsNs = NS.METS.ns();

                if (this.contentids != null) {
                    String value = MetsWriter.listToString(this.contentids);
                    fptr.setAttribute("CONTENTIDS", value);
                }
                if (this.fileid != null)
                    fptr.setAttribute("FILEID", this.fileid);
                if (this.par != null) {
                    Element par = doc.createElementNS(metsNs, "mets:par");
                    this.par.marshal(par, doc);
                    fptr.appendChild(par);
                }
                if (this.seq != null) {
                    Element seq = doc.createElementNS(metsNs, "mets:seq");
                    this.seq.marshal(seq, doc);
                    fptr.appendChild(seq);
                }
                if (this.area != null) {
                    Element area = doc.createElementNS(metsNs, "mets:area");
                    this.area.marshal(area, doc);
                    fptr.appendChild(area);
                }
            }

            @Override
            public void unmarshal(Element fptr) {
                super.unmarshal(fptr);

                NamedNodeMap attrs = fptr.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Attr attr = (Attr) attrs.item(i);
                    String name = attr.getName();
                    String value = attr.getNodeValue();
                    if (name.equals("FILEID"))
                        this.fileid = value;
                    if (name.equals("CONTENTIDS"))
                        this.contentids = MetsReader.parseIDREFAttr(value);
                }

                List<Element> children = DOMHelp.getChildElements(fptr);
                for (Element child : children) {
                    String localName = child.getLocalName();
                    if (localName.equals("par")) {
                        this.par = new Par();
                        this.par.unmarshal(child);
                    }
                    if (localName.equals("seq")) {
                        this.seq = new Seq();
                        this.seq.unmarshal(child);
                    }
                    if (localName.equals("area")) {
                        this.area = new Area();
                        this.area.unmarshal(child);
                    }
                }
            }

            /**
             * An empty class to help enforce type safety in {@link Par#areaOrSeq} and
             * {@link Seq#areaOrPar}.
             * 
             * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
             * @since Aug 30, 2010
             */
            public static abstract class FptrChild extends IDElement {}

            /**
             * Representation of a <code>mets:par</code>.
             * <p>
             * From the METS Schema: <blockquote>
             * <p>
             * The <code>par</code> or parallel files element aggregates pointers to files, parts of
             * files, and/or sequences of files or parts of files that must be played or displayed
             * simultaneously to manifest a block of digital content represented by an <fptr>
             * element.
             */
            public static class Par extends FptrChild implements ElementInterface {
                /*
                 * Implementation note: Although it forces some type checking, and feels dirty,
                 * keeping these two objects in the same class list is the only way to assure
                 * document order is retained when re-marshalling an unmarshalled document.
                 * 
                 * The same approach is used in Seq
                 */
                protected List<FptrChild> areaOrSeq;

                /**
                 * Gets the areaOrSeq List
                 * <p>
                 * To add a new item, do:
                 * 
                 * <pre>
                 * getAreaOrSeq().add(newItem);
                 * </pre>
                 * 
                 */
                public List<FptrChild> getAreaOrSeq() {
                    if (areaOrSeq == null) {
                        areaOrSeq = new ArrayList<FptrChild>();
                    }
                    return this.areaOrSeq;
                }

                @Override
                public void marshal(Element par, Document doc) {
                    super.marshal(par, doc);
                    if (this.areaOrSeq != null) {
                        for (FptrChild f : this.areaOrSeq) {
                            String cname = f.getClass().getSimpleName();
                            String qname = "mets:" + cname.toLowerCase();
                            Element e = doc.createElementNS(NS.METS.ns(), qname);
                            f.marshal(e, doc);
                            par.appendChild(e);
                        }
                    }
                }

                @Override
                public void unmarshal(Element par) {
                    super.unmarshal(par);
                    List<Element> children = DOMHelp.getChildElements(par);
                    for (Element child : children) {
                        String localName = child.getLocalName();
                        if (localName.equals("area")) {
                            Area area = new Area();
                            area.unmarshal(child);
                            this.getAreaOrSeq().add(area);
                        }
                        if (localName.equals("seq")) {
                            Seq seq = new Seq();
                            seq.unmarshal(child);
                            this.getAreaOrSeq().add(seq);
                        }
                    }
                }
            }

            /**
             * Representation of a <code>mets:seq</code>.
             * <p>
             * From the METS Schema: <blockquote>
             * <p>
             * The seq element should be used to link a div to a set of content files when those
             * files should be played/displayed sequentially to deliver content to a user.
             * Individual <area> subelements within the seq element provide the links to the files
             * or portions thereof.
             */
            public static class Seq extends FptrChild implements ElementInterface {
                protected List<FptrChild> areaOrPar;

                /**
                 * Gets the areaOrPar List.
                 * 
                 * <p>
                 * To add a new item, do:
                 * 
                 * <pre>
                 * getAreaOrPar().add(newAreaOrPar);
                 * </pre>
                 */
                public List<FptrChild> getAreaOrPar() {
                    if (areaOrPar == null) {
                        areaOrPar = new ArrayList<FptrChild>();
                    }
                    return this.areaOrPar;
                }

                @Override
                public void marshal(Element seq, Document doc) {
                    // ID
                    super.marshal(seq, doc);
                    if (this.areaOrPar != null) {
                        for (FptrChild f : this.areaOrPar) {
                            // class and element have the same name.
                            String cname = f.getClass().getSimpleName();
                            String qname = "mets:" + cname.toLowerCase();
                            Element e = doc.createElementNS(NS.METS.ns(), qname);
                            f.marshal(e, doc);
                            seq.appendChild(e);
                        }
                    }
                }

                @Override
                public void unmarshal(Element seq) {
                    super.unmarshal(seq);
                    List<Element> children = DOMHelp.getChildElements(seq);
                    for (Element child : children) {
                        String localName = child.getLocalName();
                        if (localName.equals("area")) {
                            Area area = new Area();
                            area.unmarshal(child);
                            this.getAreaOrPar().add(area);
                        }
                        if (localName.equals("par")) {
                            Par par = new Par();
                            par.unmarshal(child);
                            this.getAreaOrPar().add(par);
                        }
                    }
                }
            }

            /**
             * Representation of a <code>mets:area</code>.
             * <p>
             * From the METS Schema: <blockquote>
             * <p>
             * The area element provides for more sophisticated linking between a div element and
             * content files representing that div, be they text, image, audio, or video files. An
             * area element can link a div to a point within a file, to a one-dimension segment of a
             * file (e.g., text segment, image line, audio/video clip), or a two-dimensional section
             * of a file (e.g, subsection of an image, or a subsection of the video display of a
             * video file. The area element has no content; all information is recorded within its
             * various attributes.
             */
            public static class Area extends FptrChild implements ElementInterface {
                protected String fileid;
                protected String extent;
                protected String coords;
                protected String begin;
                protected String end;
                protected List<String> contentids;
                protected List<String> admid;
                protected SHAPE shape;
                protected BETYPE betype;
                protected EXTTYPE exttype;

                public Area(String fileid) {
                    this.fileid = fileid;
                }

                // only for internal use when unmarshalling.
                private Area() {
                }

                /**
                 * Gets the admid List
                 * 
                 * <p>
                 * To add a new item, do:
                 * 
                 * <pre>
                 * getADMID().add(newAdmid);
                 * </pre>
                 */
                public List<String> getADMID() {
                    if (admid == null) {
                        admid = new ArrayList<String>();
                    }
                    return this.admid;
                }

                /**
                 * Gets the value of <code>@FILEID</code>
                 * 
                 * @return the String value of <code>@FILEID</code>
                 */
                public String getFILEID() {
                    return fileid;
                }

                /**
                 * Sets the value of <code>@FILEID</code>
                 * 
                 * @param value
                 */
                public void setFILEID(String value) {
                    this.fileid = value;
                }

                /**
                 * Gets the {@link SHAPE} enum object for <code>@SHAPE</code>
                 * 
                 * @return a {@link SHAPE} instance
                 */
                public SHAPE getSHAPE() {
                    return shape;
                }

                /**
                 * Sets <code>@SHAPE</code>
                 * 
                 * @param shape
                 * 
                 */
                public void setSHAPE(SHAPE shape) {
                    this.shape = shape;
                }

                /**
                 * Gets the value of <code>@COORDS</code>
                 * 
                 * @return the String value of <code>@COORDS</code>
                 */
                public String getCOORDS() {
                    return coords;
                }

                /**
                 * Sets the value of <code>@COORDS</code>
                 * 
                 * @param coords
                 */
                public void setCOORDS(String coords) {
                    this.coords = coords;
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
                public BETYPE getBETYPE() {
                    return betype;
                }

                /**
                 * Sets <code>@BETYPE</code>
                 * 
                 * @param exttype
                 */
                public void setBETYPE(BETYPE betype) {
                    this.betype = betype;
                }

                /**
                 * Gets the value of <code>@EXTENT</code>
                 * 
                 * @return the String value of <code>@EXTENT</code>
                 */
                public String getEXTENT() {
                    return extent;
                }

                /**
                 * Sets the value of <code>@EXTENT</code>
                 * 
                 * @param extent
                 */
                public void setEXTENT(String extent) {
                    this.extent = extent;
                }

                /**
                 * Gets the {@link EXTTYPE} enum object for <code>@EXTTYPE</code>
                 * 
                 * @return a {@link EXTTYPE} instance
                 */
                public EXTTYPE getEXTTYPE() {
                    return exttype;
                }

                /**
                 * Sets <code>@EXTTYPE</code>
                 * 
                 * @param exttype
                 */
                public void setEXTTYPE(EXTTYPE exttype) {
                    this.exttype = exttype;
                }

                /**
                 * Gets the value of the contentids property.
                 * 
                 * <p>
                 * To add a new item, do:
                 * 
                 * <pre>
                 * getCONTENTIDS().add(newItem);
                 * </pre>
                 */
                public List<String> getCONTENTIDS() {
                    if (contentids == null) {
                        contentids = new ArrayList<String>();
                    }
                    return this.contentids;
                }

                /**
                 * Enumeration of allowed values for <code>@BETYPE</code>
                 */
                public enum BETYPE {
                    BYTE("BYTE"), IDREF("IDREF"), SMIL("SMIL"), MIDI("MIDI"), SMPTE_25("SMPTE-25"), SMPTE_24("SMPTE-24"), SMPTE_DF_30(
                            "SMPTE-DF30"), SMPTE_NDF_30("SMPTE-NDF30"), SMPTE_DF_29_97("SMPTE-DF29.97"), SMPTE_NDF_29_97(
                            "SMPTE-NDF29.97"), TIME("TIME"), TCF("TCF"), XPTR("XPTR");
                    private final String value;

                    BETYPE(String v) {
                        value = v;
                    }

                    public String value() {
                        return value;
                    }

                    public static BETYPE fromValue(String v) {
                        for (BETYPE c : BETYPE.values()) {
                            if (c.value.equals(v)) {
                                return c;
                            }
                        }
                        throw new IllegalArgumentException(v);
                    }
                }

                /**
                 * Enumeration of allowed values for <code>@EXTTYPE</code>
                 */
                public enum EXTTYPE {
                    BYTE("BYTE"), SMIL("SMIL"), MIDI("MIDI"), SMPTE_25("SMPTE-25"), SMPTE_24("SMPTE-24"), SMPTE_DF_30(
                            "SMPTE-DF30"), SMPTE_NDF_30("SMPTE-NDF30"), SMPTE_DF_29_97("SMPTE-DF29.97"), SMPTE_NDF_29_97(
                            "SMPTE-NDF29.97"), TIME("TIME"), TCF("TCF");
                    private final String value;

                    EXTTYPE(String v) {
                        value = v;
                    }

                    public String value() {
                        return value;
                    }

                    public static EXTTYPE fromValue(String v) {
                        for (EXTTYPE c : EXTTYPE.values()) {
                            if (c.value.equals(v)) {
                                return c;
                            }
                        }
                        throw new IllegalArgumentException(v);
                    }
                }

                /**
                 * Enumeration of allowed values for <code>@SHAPE</code>
                 */
                public enum SHAPE {
                    RECT, CIRCLE, POLY;

                    public String value() {
                        return name();
                    }

                    public static SHAPE fromValue(String v) {
                        return valueOf(v);
                    }
                }

                @Override
                public void marshal(Element area, Document doc) {
                    super.marshal(area, doc);
                    if (this.fileid != null)
                        area.setAttribute("FILEID", this.fileid);
                    if (this.extent != null)
                        area.setAttribute("EXTENT", this.extent);
                    if (this.coords != null)
                        area.setAttribute("COORDS", this.coords);
                    if (this.begin != null)
                        area.setAttribute("BEGIN", this.begin);
                    if (this.end != null)
                        area.setAttribute("END", this.end);
                    if (this.contentids != null) {
                        String value = MetsWriter.listToString(this.contentids);
                        area.setAttribute("CONTENTIDS", value);
                    }
                    if (this.admid != null) {
                        String value = MetsWriter.listToString(this.admid);
                        area.setAttribute("ADMID", value);
                    }
                    if (this.shape != null)
                        area.setAttribute("SHAPE", this.shape.value());
                    if (this.betype != null)
                        area.setAttribute("BETYPE", this.betype.value());
                    if (this.exttype != null)
                        area.setAttribute("EXTTYPE", this.exttype.value());
                }

                @Override
                public void unmarshal(Element area) {
                    // ID
                    super.unmarshal(area);
                    NamedNodeMap attrs = area.getAttributes();
                    for (int i = 0; i < attrs.getLength(); i++) {
                        Attr attr = (Attr) attrs.item(i);
                        String name = attr.getName();
                        String value = attr.getNodeValue();
                        if (name.equals("FILEID"))
                            this.fileid = value;
                        if (name.equals("EXTENT"))
                            this.extent = value;
                        if (name.equals("COORDS"))
                            this.coords = value;
                        if (name.equals("BEGIN"))
                            this.begin = value;
                        if (name.equals("END"))
                            this.end = value;
                        if (name.equals("CONTENTIDS"))
                            this.contentids = MetsReader.parseIDREFAttr(value);
                        if (name.equals("ADMID"))
                            this.admid = MetsReader.parseIDREFAttr(value);
                        if (name.equals("SHAPE"))
                            this.shape = SHAPE.fromValue(value);
                        if (name.equals("BETYPE"))
                            this.betype = BETYPE.fromValue(value);
                        if (name.equals("EXTTYPE"))
                            this.exttype = EXTTYPE.fromValue(value);
                    }
                }
            }
        }

        /**
         * Representation of a <code>mets:mptr</code>.
         * <p>
         * From the METS Schema: <blockquote>
         * <p>
         * Like the <fptr> element, the METS pointer element <mptr> represents digital content that
         * manifests its parent <div> element. Unlike the <fptr>, which either directly or
         * indirectly points to content represented in the <fileSec> of the parent METS document,
         * the <mptr> element points to content represented by an external METS document. Thus, this
         * element allows multiple discrete and separate METS documents to be organized at a higher
         * level by a separate METS document. For example, METS documents representing the
         * individual issues in the series of a journal could be grouped together and organized by a
         * higher level METS document that represents the entire journal series. Each of the <div>
         * elements in the <structMap> of the METS document representing the journal series would
         * point to a METS document representing an issue. It would do so via a child <mptr>
         * element. Thus the <mptr> element gives METS users considerable flexibility in managing
         * the depth of the <structMap> hierarchy of individual METS documents. The <mptr> element
         * points to an external METS document by means of an xlink:href attribute and associated
         * XLink attributes.
         * 
         */
        public static class Mptr extends LocatorElement implements ElementInterface {

            protected List<String> contentids;

            // only for internal use when unmarshaling
            protected Mptr() {
            }

            public Mptr(LOCTYPE loctype) {
                this.loctype = loctype;
            }

            /**
             * Gets the contentids List. To add a new CONTENTID, do:
             * 
             * <pre>
             * getCONTENTIDS().add(newCONTENTID);
             * </pre>
             */
            public List<String> getCONTENTIDS() {
                if (contentids == null) {
                    contentids = new ArrayList<String>();
                }
                return this.contentids;
            }

            @Override
            public void marshal(Element mptr, Document doc) {
                super.marshal(mptr, doc);
                if (this.contentids != null) {
                    String value = MetsWriter.listToString(this.contentids);
                    mptr.setAttribute("CONTENTIDS", value);
                }
            }

            @Override
            public void unmarshal(Element e) {
                super.unmarshal(e);
                if (e.hasAttribute("CONTENTIDS")) {
                    String value = e.getAttribute("CONTENTIDS");
                    this.contentids = MetsReader.parseIDREFAttr(value);
                }
            }
        }
    }
}
