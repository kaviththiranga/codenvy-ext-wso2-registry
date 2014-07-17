package org.wso2.developerstudio.codenvy.ext.registry.client.wizard.resource;

import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nullable;

/**
 * Created by kavith on 7/17/14.
 */
@Singleton
public class ResourceCreationPagePresenter extends AbstractWizardPage implements ResourceCreationPageView.ActionDelegate {

    private final ResourceCreationPageView view;

    /**
     * Create wizard page with given caption and image.
     *
     */
    @Inject
    public ResourceCreationPagePresenter(ResourceCreationPageView view) {
        super("Create Resource", null);
        this.view = view;
    }

    @Nullable
    @Override
    public String getNotice() {
        return null;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void focusComponent() {

    }

    @Override
    public void removeOptions() {

    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }
}
