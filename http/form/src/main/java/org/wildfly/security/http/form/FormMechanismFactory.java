/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2018 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.security.http.form;

import static org.wildfly.common.Assert.checkNotNullParam;
import static org.wildfly.security.http.HttpConstants.FORM_NAME;

import java.security.Provider;
import java.util.Map;

import javax.security.auth.callback.CallbackHandler;

import org.kohsuke.MetaInfServices;

import org.wildfly.security.http.HttpAuthenticationException;
import org.wildfly.security.http.HttpServerAuthenticationMechanism;
import org.wildfly.security.http.HttpServerAuthenticationMechanismFactory;

/**
 * A {@link HttpServerAuthenticationMechanismFactory} implementation for the FORM HTTP authentication mechanism.
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
@MetaInfServices(value = HttpServerAuthenticationMechanismFactory.class)
public class FormMechanismFactory implements HttpServerAuthenticationMechanismFactory {

    public FormMechanismFactory() {
    }

    public FormMechanismFactory(final Provider provider) {
    }

    /**
     * @see org.wildfly.security.http.HttpServerAuthenticationMechanismFactory#getMechanismNames(java.util.Map)
     */
    @Override
    public String[] getMechanismNames(Map<String, ?> properties) {
        return new String[]{FORM_NAME};
    }

    /**
     * @see org.wildfly.security.http.HttpServerAuthenticationMechanismFactory#createAuthenticationMechanism(java.lang.String,
     * java.util.Map, javax.security.auth.callback.CallbackHandler)
     */
    @Override
    public HttpServerAuthenticationMechanism createAuthenticationMechanism(String mechanismName,
            Map<String, ?> properties, CallbackHandler callbackHandler) throws HttpAuthenticationException {
        checkNotNullParam("mechanismName", mechanismName);
        checkNotNullParam("properties", properties);
        checkNotNullParam("callbackHandler", callbackHandler);

        if (FORM_NAME.equals(mechanismName)) {
            return new FormAuthenticationMechanism(callbackHandler, properties);
        }

        return null;
    }

}
