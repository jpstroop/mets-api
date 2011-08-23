/**
 * DOMHelp.java
 * $LastChangedDate: 2010-09-02 14:40:10 -0400 (Thu, 02 Sep 2010) $ 
 * $Author: jstroop $ 
 * $Rev: 872 $
 */
package com.anearalone.utils;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author <a href="mailto:jpstroop@gmail.com">Jon Stroop</a>
 * @since Sep 2, 2010
 * 
 */
public class DOMHelp {
	/**
	 * Gets the first element child of the provided Element. Returns null if the
	 * element has no child elements. Equiv. to XPath
	 * <code>Element/child[1]</code>.
	 * 
	 * @param e
	 * @return
	 */
	public static Element getFirstChildElement(Element e) {
		Element childElement = null;
		NodeList children = e.getChildNodes();
		for (int p = 0; p < children.getLength(); p++) {
			Node thisNode = children.item(p);
			if (thisNode.getNodeType() == 1) {
				childElement = (Element) thisNode;
				break;
			}
		}
		return childElement;
	}
	
	/**
	 * Gets the element children of the provided Element. Returns null if the
	 * element has no child elements. Equiv. to XPath
	 * <code>Element/child</code>.
	 * 
	 * @param e
	 * @return
	 */
	public static List<Element> getChildElements(Element e) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList children = e.getChildNodes();
		for (int p = 0; p < children.getLength(); p++) {
			Node thisNode = children.item(p);
			if (thisNode.getNodeType() == 1) {
				childElements.add((Element) thisNode);
			}
		}
		return childElements;
	}
	
	
}
