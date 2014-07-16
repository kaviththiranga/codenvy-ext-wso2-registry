package org.wso2.developerstudio.codenvy.ext.registry.client.wizard;

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.codenvy.ide.dto.DtoFactory;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import javax.annotation.Nullable;

/**
 * Created by kavith on 7/16/14.
 */
public class MavenSettingsPagePresenter extends AbstractWizardPage implements MavenSettingsPageView.ActionDelegate {

    private final MavenSettingsPageView         view;
    private final ProjectServiceClient projectServiceClient;
    private final ResourceProvider     resourceProvider;
    private final DtoFactory           factory;

    /**
     * Create wizard page with given caption and image.
     *
     */
    @Inject
    public MavenSettingsPagePresenter(MavenSettingsPageView view,
                                      ProjectServiceClient projectServiceClient,
                                      ResourceProvider resourceProvider,
                                      DtoFactory factory) {
        super("Maven settings", null);

        this.view = view;
        this.projectServiceClient = projectServiceClient;
        this.resourceProvider = resourceProvider;
        this.factory = factory;

    }

    @Nullable
    @Override
    public String getNotice() {
        return "dfdfdsfdsfdsfs";
    }

    @Override
    public boolean isCompleted() {
        return false;
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

    @Override
    public boolean canSkip() {
        return false;
    }

    @Override
    public void onTextChange() {

    }
}
