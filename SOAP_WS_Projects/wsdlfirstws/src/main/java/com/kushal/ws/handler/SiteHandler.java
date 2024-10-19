package com.kushal.ws.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SiteHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		System.out.println("handleMessage");
		// To check if SOAP message is request or response
		// This is true when SOAP message is response
		Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		// if SOAP Message is request then read the handler and can made changes in the
		// header
		if (!isResponse) {
			SOAPMessage soapMsg = context.getMessage();
			try {
				SOAPEnvelope envelope = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				// We get all the child element of the header,
				Iterator childElements = header.getChildElements();
				while (childElements.hasNext()) {
					// We get each node
					Node eachNode = (Node) childElements.next();
					String logicalName = eachNode.getLocalName();
					if (null != logicalName && logicalName.equals("SiteName")) {
						System.out.println("SiteName ==>" + eachNode.getValue());
					}
				}
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Reponse");
		}
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("Close");
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("handleFault");
		return false;
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("getHeaders");
		return null;
	}

}
