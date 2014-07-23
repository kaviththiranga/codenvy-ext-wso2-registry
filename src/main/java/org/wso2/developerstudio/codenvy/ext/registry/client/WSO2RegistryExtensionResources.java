package org.wso2.developerstudio.codenvy.ext.registry.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

/**
 * Created by kavith on 7/23/14.
 */
public interface WSO2RegistryExtensionResources extends ClientBundle{

    @Source("sample_pom.xml")
    DataResource defaultPOMFile();
}
