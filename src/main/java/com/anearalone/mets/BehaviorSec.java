/**
 * BehaviorSec.java
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

import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <strong>Warning: NOT IMPLEMENTED</strong>
 */
public class BehaviorSec extends IDElement implements ElementInterface {

    protected List<BehaviorSec> behaviorSec;
    protected List<Behavior> behavior;
    protected XMLGregorianCalendar created;
    protected String label;

    /**
     * Gets the behaviorSec List. To add a new BehaviorSec, do:
     * 
     * <pre>
     * getBehaviorSec().add(newBehaviorSec);
     * </pre>
     */
    public List<BehaviorSec> getBehaviorSec() {
        if (behaviorSec == null) {
            behaviorSec = new ArrayList<BehaviorSec>();
        }
        return this.behaviorSec;
    }

    /**
     * Gets behavior List. To add a new Behavior, do:
     * 
     * <pre>
     * getBehavior().add(newBehavior);
     * </pre>
     */
    public List<Behavior> getBehavior() {
        if (behavior == null) {
            behavior = new ArrayList<Behavior>();
        }
        return this.behavior;
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
     * <strong>Warning: NOT IMPLEMENTED</strong>
     */
    public static class Behavior extends IDElement implements ElementInterface {

        protected Behavior.Object interfaceDef;
        protected Behavior.Object mechanism;
        protected List<String> structid;
        protected String btype;
        protected XMLGregorianCalendar created;
        protected String label;
        protected String groupid;
        protected List<String> admid;

        /**
         * Gets the interfaceDef Object
         * 
         * @return
         */
        public Behavior.Object getInterfaceDef() {
            return interfaceDef;
        }

        /**
         * Sets interfaceDef Object
         * 
         * @param object
         */
        public void setInterfaceDef(Behavior.Object object) {
            this.interfaceDef = object;
        }

        /**
         * Gets the mechanism Object
         * 
         * @return
         */
        public Behavior.Object getMechanism() {
            return mechanism;
        }

        /**
         * Sets mechanism Object
         * 
         * @param object
         */
        public void setMechanism(Behavior.Object object) {
            this.mechanism = object;
        }

        /**
         * Gets the <code>@STRUCTID</code> List. To add a new item, do:
         * 
         * <pre>
         * getSTRUCTID().add(newSTRUCTID);
         * </pre>
         */
        public List<String> getSTRUCTID() {
            if (structid == null) {
                structid = new ArrayList<String>();
            }
            return this.structid;
        }

        /**
         * Gets the value of <code>@BTYPE</code>
         * 
         * @return the String value of <code>@BTYPE</code>
         */
        public String getBTYPE() {
            return btype;
        }

        /**
         * Sets the value of <code>@BTYPE</code>
         * 
         * @param label
         *            the String value for <code>@BTYPE</code>
         */
        public void setBTYPE(String btype) {
            this.btype = btype;
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
         * @param label
         *            the String value for <code>@GROUPID</code>
         */
        public void setGROUPID(String groupid) {
            this.groupid = groupid;
        }

        /**
         * Gets admid List. To add a new item, do as follows:
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
         * <strong>Warning: NOT IMPLEMENTED</strong>
         */
        public class Object extends LocatorElement implements ElementInterface {

            protected String label;

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

            @Override
            public void marshal(Element e, Document d) {
                super.marshal(e, d);
                if (this.label != null)
                    e.setAttribute("LABEL", this.label);
                // TODO
            }

            @Override
            public void unmarshal(Element e) {
                super.unmarshal(e);
                // TODO
            }
        }
    }
}
