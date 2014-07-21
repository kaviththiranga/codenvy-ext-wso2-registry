package org.wso2.developerstudio.codenvy.ext.registry.client.wizard;

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.api.resources.model.Project;
import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.codenvy.ide.api.ui.wizard.ProjectWizard;
import com.codenvy.ide.api.ui.wizard.WizardContext;
import com.codenvy.ide.api.ui.wizard.WizardPage;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wso2.developerstudio.codenvy.ext.registry.shared.Constants;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kavith on 7/16/14.
 */
@Singleton
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
        super("Maven Attributes", null);

        this.view = view;
        // Important step, set the action delegate of view
        this.view.setDelegate(this);
        this.projectServiceClient = projectServiceClient;
        this.resourceProvider = resourceProvider;
        this.factory = factory;

    }

    @Nullable
    @Override
    public String getNotice() {
        return null;
    }

    @Override
    public boolean isCompleted() {
        return !view.getArtifactId().equals("") && !view.getGroupId().equals("") && !view.getVersion().equals("");
    }

    @Override
    public void focusComponent() {

    }

    @Override
    public void storeOptions() {
        wizardContext.putData(Constants.WKEY_MAVEN_ARTIFACT_ID, view.getArtifactId());
        wizardContext.putData(Constants.WKEY_MAVEN_GROUP_ID, view.getGroupId());
        wizardContext.putData(Constants.WKEY_MAVEN_VERSION, view.getVersion());
    }

    @Override
    public void removeOptions() {
    }

    @Override
    public void go(AcceptsOneWidget container) {
        // Important - Set view as the widget
        container.setWidget(view);
        Project project = wizardContext.getData(ProjectWizard.PROJECT);
        if (project != null) {
            view.setArtifactId(project.getAttributeValue(Constants.MAVEN_ARTIFACT_ID));
            view.setGroupId(project.getAttributeValue(Constants.MAVEN_GROUP_ID));
            view.setVersion(project.getAttributeValue(Constants.MAVEN_VERSION));
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    onTextChange();
                }
            });
        }
    }

    @Override
    public boolean canSkip() {
        return false;
    }

    @Override
    public void onTextChange() {
        delegate.updateControls();
    }
}
