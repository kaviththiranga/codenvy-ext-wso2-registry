package org.wso2.developerstudio.codenvy.ext.registry.client.wizard.resource;

import com.codenvy.ide.api.mvp.View;
import com.google.inject.ImplementedBy;

/**
 * Created by kavith on 7/17/14.
 */
@ImplementedBy(ResourceCreationPageViewImpl.class)
public interface ResourceCreationPageView extends View<ResourceCreationPageView.ActionDelegate> {
    public interface ActionDelegate {
    }
}
