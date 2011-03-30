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

package org.restlet.ext.oauth.provider.data;

/**
 * Abstract Token that must be extended by all token implementations
 * 
 * @author Kristoffer Gronowski
 * 
 * @see UnlimitedToken
 * @see ExpireToken
 */
public abstract class Token {

	/**
	 * Value indicating that the Token should not expire 
	 */
    public static final long UNLIMITED = 0;

    /**
     * 
     * @return the actual token to be used for OAuth invocations.
     */
    public abstract String getToken();

    /**
     * 
     * @return the user that is the owner of this token
     */
    public abstract AuthenticatedUser getUser();
    
    /**
     * Generic package method since the Token can be revoked
     * and re-issued or just persisted and re-instantiated.
     * 
     * 
     * @param token
     */
    abstract void setToken( String token );
}
