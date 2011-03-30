/**
 * Copyright 2005-2011 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.ext.ssl.internal;

import java.io.IOException;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;

import org.restlet.engine.connector.Connection;
import org.restlet.engine.connector.HttpClientOutboundWay;

/**
 * HTTPS client outbound way.
 * 
 * @author Jerome Louvel
 */
public class HttpsClientOutboundWay extends HttpClientOutboundWay {

    /**
     * Constructor.
     * 
     * @param connection
     *            The parent connection.
     * @param bufferSize
     *            The byte buffer size.
     */
    public HttpsClientOutboundWay(Connection<?> connection, int bufferSize) {
        super(connection, bufferSize);
    }

    @Override
    protected SslConnection<?> getConnection() {
        return (SslConnection<?>) super.getConnection();
    }

    @Override
    public int processIoBuffer() throws IOException {
        int result = super.processIoBuffer();
        getConnection().handleSslResult();
        return result;
    }

    @Override
    protected boolean hasIoInterest() {
        return super.hasIoInterest()
                && ((getConnection().getSslState() != SslState.HANDSHAKING) || (getConnection()
                        .getSslHandshakeStatus() != HandshakeStatus.NEED_UNWRAP));
    }

}
