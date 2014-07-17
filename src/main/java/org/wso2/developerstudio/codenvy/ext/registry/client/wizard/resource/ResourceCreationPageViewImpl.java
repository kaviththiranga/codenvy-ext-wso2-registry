package org.wso2.developerstudio.codenvy.ext.registry.client.wizard.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

/**
 * Created by kavith on 7/17/14.
 */
@Singleton
public class ResourceCreationPageViewImpl implements ResourceCreationPageView
{
    private ResourceCreationPageViewImplUiBinder uiBinder = GWT.create(ResourceCreationPageViewImplUiBinder.class);

    private DockLayoutPanel rootElement;

    @Override
    public void setDelegate(ActionDelegate delegate) {

    }

    @Override
    public Widget asWidget() {
        return rootElement;
    }

    interface ResourceCreationPageViewImplUiBinder extends UiBinder<DockLayoutPanel, ResourceCreationPageViewImpl>{}

    public ResourceCreationPageViewImpl() {

        rootElement = uiBinder.createAndBindUi(this);

    }
}
