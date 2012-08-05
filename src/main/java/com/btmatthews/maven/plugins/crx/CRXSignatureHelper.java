/*
 * Copyright 2012 Brian Matthews
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

package com.btmatthews.maven.plugins.crx;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.Signature;

import org.codehaus.plexus.component.annotations.Component;

/**
 * Implementation of {@link SignatureHelper} that signs a byte array using a public/private key pair.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.1.0
 */
@Component(role = SignatureHelper.class, hint = "crx")
public class CRXSignatureHelper implements SignatureHelper {

    /**
     * The algorithm used to generate the signature.
     */
    private static final String ALGORITHM = "SHA1withRSA";

    /**
     * Generate the signature for a byte array using the private key.
     *
     * @param data    The byte array.
     * @param keyPair The public/private key pair.
     * @return The signature as a byte array.
     * @throws GeneralSecurityException If there was a error generating the signature.
     */
    public byte[] sign(final byte[] data, final KeyPair keyPair) throws GeneralSecurityException {
        final Signature signatureObject = Signature.getInstance(ALGORITHM);
        signatureObject.initSign(keyPair.getPrivate());
        signatureObject.update(data);
        return signatureObject.sign();
    }
}