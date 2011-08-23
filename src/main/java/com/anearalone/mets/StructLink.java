/**
 * StructLink.java
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
 * 
 */

package com.anearalone.mets;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.anearalone.mets.LocatorElement.ACTUATE;
import com.anearalone.mets.LocatorElement.SHOW;
import com.anearalone.utils.DOMHelp;

/**
 * Representation of a <code>mets:structLink</code>
 * <p>
 * From the METS Schema: 
 * <blockquote>
 * <p>
 * The Structural Map Linking section allows for the specification of hyperlinks between different
 * components of a METS structure delineated in a structural map. structLink contains a single,
 * repeatable element, smLink. Each smLink element indicates a hyperlink between two nodes in the
 * structMap. The structMap nodes recorded in smLink are identified using their XML ID attribute
 * values.
 */
public class StructLink extends IDElement implements ElementInterface {

    protected List<StructLinkChild> smLinkOrSmLinkGrp;
    protected String id;

    /**
     * Gets the value of the smLinkOrSmLinkGrp List
     * 
     * <p>
     * To add a new SmLink or SmLink, do:
     * 
     * <pre>
     * getSmLinkOrSmLinkGrp().add(newItem);
     * </pre>
     * 
     */
    public List<StructLinkChild> getSmLinkOrSmLinkGrp() {
        if (smLinkOrSmLinkGrp == null) {
            smLinkOrSmLinkGrp = new ArrayList<StructLinkChild>();
        }
        return this.smLinkOrSmLinkGrp;
    }

    @Override
    public void marshal(Element slink, Document doc) {
        // TODO
        super.marshal(slink, doc);
        if (this.smLinkOrSmLinkGrp != null) {
            for (StructLinkChild slc : this.smLinkOrSmLinkGrp) {
                if (slc instanceof SmLink) {
                    Element smLink = doc.createElementNS(NS.METS.ns(), "mets:smLink");
                    slc.marshal(smLink, doc);
                    slink.appendChild(smLink);
                } // smLinkGrp not supported
            }
        }
    }

    @Override
    public void unmarshal(Element slink) {
        // TODO
        super.unmarshal(slink);
        List<Element> children = DOMHelp.getChildElements(slink);
        for (Element child : children) {
            String localName = child.getLocalName();
            if (localName.equals("smLink")) {
                SmLink smLink = new SmLink();
                smLink.unmarshal(child);
                this.getSmLinkOrSmLinkGrp().add(smLink);
            }
            if (localName.equals("smLinkGrp")) {
                // not implemented
            }
        }
    }

    /**
     * An empty class to help enforce type safety in {@link StructLink#smLinkOrSmLinkGrp}
     * 
     * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
     * @since Aug 30, 2010
     */
    public static abstract class StructLinkChild extends IDElement {}

    /**
     * Representation of a <code>mets:smLink</code>.
     * <p>
     * From the METS Schema:
     * 
     * <blockquote>
     * <p>
     * The Structural Map Link element <smLink> identifies a hyperlink between two nodes in the
     * structural map. You would use <smLink>, for instance, to note the existence of hypertext
     * links between web pages, if you wished to record those links within METS. NOTE: <smLink> is
     * an empty element. The location of the <smLink> element to which the <smLink> element is
     * pointing MUST be stored in the xlink:href attribute.
     */
    public static class SmLink extends StructLinkChild implements ElementInterface {

        protected String xlinkArcRole;
        protected String xlinkTitle;
        protected SHOW xlinkShow;
        protected ACTUATE xlinkActuate;
        protected String xlinkTo;
        protected String xlinkFrom;

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
         * Gets the value of <code>@xlink:to</code>
         * 
         * @return the String value of <code>@xlink:to</code>
         */
        public String getXlinkTo() {
            return xlinkTo;
        }

        /**
         * Sets the value of <code>@xlink:to</code>
         * 
         * @param to
         */
        public void setXlinkTo(String to) {
            this.xlinkTo = to;
        }

        /**
         * Gets the value of <code>@xlink:from</code>
         * 
         * @return the String value of <code>@xlink:from</code>
         */
        public String getXlinkFrom() {
            return xlinkFrom;
        }

        /**
         * Sets the value of <code>@xlink:from</code>
         * 
         * @param from
         */
        public void setXlinkFrom(String from) {
            this.xlinkFrom = from;
        }

        @Override
        public void marshal(Element slink, Document doc) {
            // TODO
            super.marshal(slink, doc);
            String xns = NS.XLINK.ns(); // to keep lines short.

            if (this.xlinkArcRole != null) {
                Attr xArcRole = doc.createAttributeNS(xns, "xlink:arcrole");
                xArcRole.setNodeValue(this.xlinkArcRole);
                slink.setAttributeNode(xArcRole);
            }
            if (this.xlinkTitle != null) {
                Attr xtitle = doc.createAttributeNS(xns, "xlink:title");
                xtitle.setNodeValue(this.xlinkTitle);
                slink.setAttributeNode(xtitle);
            }
            if (this.xlinkTo != null) {
                Attr xto = doc.createAttributeNS(xns, "xlink:to");
                xto.setNodeValue(this.xlinkTo);
                slink.setAttributeNode(xto);
            }
            if (this.xlinkFrom != null) {
                Attr xfrom = doc.createAttributeNS(xns, "xlink:from");
                xfrom.setNodeValue(this.xlinkFrom);
                slink.setAttributeNode(xfrom);
            }
            if (this.xlinkShow != null) {
                Attr show = doc.createAttributeNS(xns, "xlink:show");
                show.setNodeValue(this.xlinkShow.value());
                slink.setAttributeNode(show);
            }
            if (this.xlinkActuate != null) {
                Attr actuate = doc.createAttributeNS(xns, "xlink:actuate");
                actuate.setNodeValue(this.xlinkActuate.value());
                slink.setAttributeNode(actuate);
            }
        }

        @Override
        public void unmarshal(Element smLink) {
            // TODO
            super.unmarshal(smLink);
            NamedNodeMap attrs = smLink.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr = (Attr) attrs.item(i);
                String name = attr.getName();
                String value = attr.getNodeValue();
                if (name.equals("xlink:from"))
                    this.xlinkFrom = value;
                if (name.equals("xlink:to"))
                    this.xlinkTo = value;
                if (name.equals("xlink:actuate"))
                    this.xlinkActuate = ACTUATE.fromValue(value);
                if (name.equals("xlink:arcrole"))
                    this.xlinkArcRole = value;
                if (name.equals("xlink:show"))
                    this.xlinkShow = SHOW.fromValue(value);
                if (name.equals("xlink:title"))
                    this.xlinkTitle = value;
            }
        }

    }

    /**
     * <strong>Warning: NOT IMPLEMENTED</strong>
     */
    public static class SmLinkGrp extends StructLinkChild implements ElementInterface {

        protected List<SmLocatorLink> smLocatorLink;
        protected List<SmArcLink> smArcLink;
        protected ARCLINKORDER arclinkorder;
        protected SmLinkGrp.TYPE xlinkType;
        protected String xlinkRole;
        protected String xlinkTitle;

        /**
         * Gets the smLocatorLink List
         * 
         * <p>
         * To add a new SmLocatorLink:
         * 
         * <pre>
         * getSmLocatorLink().add(newSmLocatorLink);
         * </pre>
         */
        public List<SmLocatorLink> getSmLocatorLink() {
            if (smLocatorLink == null) {
                smLocatorLink = new ArrayList<SmLocatorLink>();
            }
            return this.smLocatorLink;
        }

        /**
         * Gets the smArcLink List
         * 
         * <p>
         * To add a new SmArcLink, do:
         * 
         * <pre>
         * getSmArcLink().add(newSmArcLink);
         * </pre>
         */
        public List<SmArcLink> getSmArcLink() {
            if (smArcLink == null) {
                smArcLink = new ArrayList<SmArcLink>();
            }
            return this.smArcLink;
        }

        /**
         * Gets the value of <code>@ARKLINKORDER</code>
         * 
         * @return an ARCLINKORDER instance
         * 
         */
        public StructLink.SmLinkGrp.ARCLINKORDER getARCLINKORDER() {
            if (arclinkorder == null) {
                return StructLink.SmLinkGrp.ARCLINKORDER.UNORDERED;
            } else {
                return arclinkorder;
            }
        }

        /**
         * Sets the <code>@ARKLINKORDER</code>
         */
        public void setARCLINKORDER(ARCLINKORDER value) {
            this.arclinkorder = value;
        }

        /**
         * Gets the <code>@xlink:type</code>
         * 
         * @return <code>@xlink:type</code>
         */
        public TYPE getXlinkType() {
            return this.xlinkType;
        }

        /**
         * Sets the value of <code>@xlink:type</code>
         * 
         * @param type
         * 
         */
        public void setXlinkType(TYPE type) {
            this.xlinkType = type;
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

        @Override
        public void marshal(Element e, Document d) {
            // TODO
            super.marshal(e, d);
        }

        @Override
        public void unmarshal(Element e) {
            // TODO
            super.unmarshal(e);
        }

        /**
         * Enumeration of values for <code>@xlink:type</code>
         */
        public enum TYPE {
            EXTENDED("extended");

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

        /**
         * Enumeration of allowed values for <code>@ARCLINKORDER</code>
         */
        public enum ARCLINKORDER {

            ORDERED("ordered"), UNORDERED("unordered");

            private final String value;

            ARCLINKORDER(String v) {
                value = v;
            }

            public String value() {
                return value;
            }

            public static StructLink.SmLinkGrp.ARCLINKORDER fromValue(String v) {
                for (StructLink.SmLinkGrp.ARCLINKORDER c : StructLink.SmLinkGrp.ARCLINKORDER.values()) {
                    if (c.value.equals(v)) {
                        return c;
                    }
                }
                throw new IllegalArgumentException(v);
            }

        }

        /**
         * <strong>Warning: NOT IMPLEMENTED</strong>
         */

        public static class SmArcLink extends IDElement implements ElementInterface {

            protected String arctype;
            protected List<String> admid;
            protected SmArcLink.TYPE xlinkType;
            protected String xlinkArcRole;
            protected String xlinkTitle;
            protected SHOW xlinkShow;
            protected ACTUATE xlinkActuate;
            protected String xlinkFrom;
            protected String xlinkTo;

            /**
             * Gets the value of <code>@ARCTYPE</code>
             */
            public String getARCTYPE() {
                return arctype;
            }

            /**
             * Sets the value of <code>@ARCTYPE</code>
             * 
             * @return the String value of <code>@ARCTYPE</code>
             */
            public void setARCTYPE(String value) {
                this.arctype = value;
            }

            /**
             * Gets the admid List
             * 
             * <p>
             * To add a new ADMID, do:
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
             * Gets the <code>@xlink:type</code>
             * 
             * @return <code>@xlink:type</code>
             */
            public TYPE getXlinkType() {
                return this.xlinkType;
            }

            /**
             * Sets the value of <code>@xlink:type</code>
             * 
             * @param type
             * 
             */
            public void setXlinkType(TYPE type) {
                this.xlinkType = type;
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
             * Gets the value of <code>@xlink:to</code>
             * 
             * @return the String value of <code>@xlink:to</code>
             */
            public String getXlinkTo() {
                return xlinkTo;
            }

            /**
             * Sets the value of <code>@xlink:to</code>
             * 
             * @param to
             */
            public void setXlinkTo(String to) {
                this.xlinkTo = to;
            }

            /**
             * Gets the value of <code>@xlink:from</code>
             * 
             * @return the String value of <code>@xlink:from</code>
             */
            public String getXlinkFrom() {
                return xlinkFrom;
            }

            /**
             * Sets the value of <code>@xlink:from</code>
             * 
             * @param from
             */
            public void setXlinkFrom(String from) {
                this.xlinkFrom = from;
            }

            @Override
            public void marshal(Element e, Document d) {
                // TODO
                super.marshal(e, d);
            }

            @Override
            public void unmarshal(Element e) {
                // TODO
                super.unmarshal(e);
            }

            /**
             * Enumeration of values for <code>@xlink:type</code>
             */
            public enum TYPE {
                ARC("arc");

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

        /**
         * <strong>Warning: NOT IMPLEMENTED</strong>
         */

        public static class SmLocatorLink extends IDElement implements ElementInterface {

            protected SmLocatorLink.TYPE xlinkType;
            protected String xlinkHREF;
            protected String xlinkRole;
            protected String xlinkTitle;
            protected String xlinkLabel;

            /**
             * Gets the <code>@xlink:type</code>
             * 
             * @return <code>@xlink:type</code>
             */
            public TYPE getXlinkType() {
                return this.xlinkType;
            }

            /**
             * Sets the value of <code>@xlink:type</code>
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
             * Gets the value of <code>@xlink:label</code>
             * 
             * @return the String value of <code>@xlink:label</code>
             */
            public String getXlinkLabel() {
                return xlinkLabel;
            }

            /**
             * Sets the value of <code>@xlink:label</code>
             * 
             * @param label
             */
            public void setXlinkLabel(String label) {
                this.xlinkLabel = label;
            }

            @Override
            public void marshal(Element e, Document d) {
                // TODO
                super.marshal(e, d);
            }

            @Override
            public void unmarshal(Element e) {
                // TODO
                super.unmarshal(e);
            }

            /**
             * Enumeration of values for <code>@xlink:type</code>
             */
            public enum TYPE {
                LOCATOR("locator");

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

    }

}
