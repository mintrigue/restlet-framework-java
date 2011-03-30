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

package org.restlet.test.bench;

import java.util.logging.Level;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.engine.ConnectorHelper;
import org.restlet.engine.Engine;
import org.restlet.representation.FileRepresentation;

public class TestGetServer {

    public static void main(String[] args) throws Exception {
        ConnectorHelper<Server> helper;
        helper = new org.restlet.engine.connector.HttpServerHelper(null);
        Engine.getInstance().getRegisteredServers().add(0, helper);
        Engine.setLogLevel(Level.FINE);

        Server server = new Server(new Context(), Protocol.HTTP, 8554,
                new Restlet() {
                    @Override
                    public void handle(Request request, Response response) {
                        FileRepresentation fr = new FileRepresentation(
                                "file:///c:/TEST/restlet-jse-2.0.5-ff.zip",
                                MediaType.APPLICATION_ZIP);
                        System.out.println("Size sent: " + fr.getSize());
                        response.setEntity(fr);
                    }
                });

        server.start();
    }
}
