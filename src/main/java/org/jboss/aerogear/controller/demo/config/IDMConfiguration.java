/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.controller.demo.config;

import org.jboss.aerogear.controller.demo.model.CredentialObject;
import org.jboss.aerogear.controller.demo.model.CredentialObjectAttribute;
import org.jboss.aerogear.controller.demo.model.IdentityObject;
import org.jboss.aerogear.controller.demo.model.IdentityObjectAttribute;
import org.jboss.aerogear.controller.demo.model.PartitionObject;
import org.jboss.aerogear.controller.demo.model.RelationshipIdentityObject;
import org.jboss.aerogear.controller.demo.model.RelationshipObject;
import org.jboss.aerogear.controller.demo.model.RelationshipObjectAttribute;
import org.picketlink.idm.config.IdentityConfiguration;
import org.picketlink.idm.config.IdentityConfigurationBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * This bean produces the configuration for PicketLink IDM
 *
 * @author Shane Bryzak
 */
@ApplicationScoped
public class IDMConfiguration {

    private IdentityConfiguration identityConfig = null;

    @Produces
    IdentityConfiguration createConfig() {
        if (identityConfig == null) {
            initConfig();
        }
        return identityConfig;
    }

    private void initConfig() {
        IdentityConfigurationBuilder builder = new IdentityConfigurationBuilder();
        builder
                .stores()
                .jpa()
                .identityClass(IdentityObject.class)
                .credentialClass(CredentialObject.class)
                .credentialAttributeClass(CredentialObjectAttribute.class)
                .attributeClass(IdentityObjectAttribute.class)
                .relationshipClass(RelationshipObject.class)
                .relationshipIdentityClass(RelationshipIdentityObject.class)
                .relationshipAttributeClass(RelationshipObjectAttribute.class)
                .partitionClass(PartitionObject.class)
                .supportAllFeatures();

        identityConfig = builder.build();
    }
}
