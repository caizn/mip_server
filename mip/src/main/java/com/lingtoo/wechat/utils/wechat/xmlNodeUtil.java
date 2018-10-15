package com.lingtoo.wechat.utils.wechat;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlNodeUtil {
	/**
	 * xml节点根据名字获取字符串值
	 * 
	 * @param node
	 * @param name
	 * @return
	 */
	public static String getStringByName(Node node, String name) {
		NodeList nodelist = node.getChildNodes();
		String value = "";
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node childNode = nodelist.item(i);
			if (childNode.getNodeName().equals(name)) {
				value = childNode.getTextContent();
				break;
			}
		}
		return value;
	}

	/**
	 * xml节点根据名字获取NodeList
	 * 
	 * @param node
	 * @param name
	 * @return
	 */
	public static Node getNodeByName(Node node, String name) {
		NodeList nodelist = node.getChildNodes();
		Node value = null;
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node childNode = nodelist.item(i);
			if (childNode.getNodeName().equals(name)) {
				value = childNode;
				break;
			}
		}
		return value;
	}
}
