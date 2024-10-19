package org.kushal.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.kushal.exception.CustomerOrderException;
import org.w3c.dom.NodeList;

public class CustomerOrderHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
			SOAPHeader header = envelope.getHeader();
			boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

			if (!isResponse) {
				Iterator childElements = header.getChildElements();
				while (childElements.hasNext()) {
					Node node = (Node) childElements.next();
					String logicalName = node.getLocalName();
					if ("kushalHeader".equals(logicalName)) {
						NodeList childNodes = node.getChildNodes();
						for (int i = 0; i < childNodes.getLength(); i++) {
org.w3c.dom.Node item = childNodes.item(i);

							if ("userLogin".equals(item.getLocalName())) {
								String value = item.getTextContent();
								if (!"Kushal".equals(value)) {
									
									envelope.removeContents();
									envelope.addBody().addFault();
									envelope.getBody().getFault().setFaultCode("12342");
									envelope.getBody().getFault().setFaultString("Invalid User Login");
									return false;
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
