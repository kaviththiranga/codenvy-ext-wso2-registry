package org.wso2.developerstudio.codenvy.ext.registry.client.wizard.resource;

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.api.resources.model.Project;
import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.codenvy.ide.api.ui.wizard.ProjectWizard;
import com.codenvy.ide.api.ui.wizard.WizardPage;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wso2.developerstudio.codenvy.ext.registry.shared.Constants;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kavith on 7/17/14.
 */
@Singleton
public class ResourceCreationPagePresenter extends AbstractWizardPage implements ResourceCreationPageView.ActionDelegate {

    private final ResourceCreationPageView view;
    private final ProjectServiceClient projectServiceClient;
    private final ResourceProvider     resourceProvider;
    private final DtoFactory           factory;

    /**
     * Create wizard page with given caption and image.
     *
     */
    @Inject
    public ResourceCreationPagePresenter(ResourceCreationPageView view,
                                         ProjectServiceClient projectServiceClient,
                                         ResourceProvider resourceProvider,
                                         DtoFactory factory) {
        super("Create Resource", null);
        this.view = view;
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
    public void removeOptions() {

    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }

    @Override
    public void onTextChange() {
        delegate.updateControls();
    }

    @Override
    public void commit(@NotNull final CommitCallback callback) {
        Window.alert("wizard two");
        Map<String, List<String>> options = new HashMap<>();
        options.put(Constants.MAVEN_ARTIFACT_ID, Arrays.asList(wizardContext.getData(Constants.WKEY_MAVEN_ARTIFACT_ID)));
        options.put(Constants.MAVEN_GROUP_ID, Arrays.asList(wizardContext.getData(Constants.WKEY_MAVEN_GROUP_ID)));
        options.put(Constants.MAVEN_VERSION, Arrays.asList(wizardContext.getData(Constants.WKEY_MAVEN_VERSION)));

        final ProjectDescriptor projectDescriptor = factory.createDto(ProjectDescriptor.class);
        projectDescriptor.withProjectTypeId(wizardContext.getData(ProjectWizard.PROJECT_TYPE).getProjectTypeId());
        projectDescriptor.setAttributes(options);

        boolean visibility = wizardContext.getData(ProjectWizard.PROJECT_VISIBILITY);
        projectDescriptor.setVisibility(visibility ? "public" : "private");

        final String name = wizardContext.getData(ProjectWizard.PROJECT_NAME);
        final Project project = wizardContext.getData(ProjectWizard.PROJECT);
        if (project != null) {
            if (project.getName().equals(name)) {
                updateProject(project, projectDescriptor, callback);
            } else {
                projectServiceClient.rename(project.getPath(), name, null, new AsyncRequestCallback<Void>() {
                    @Override
                    protected void onSuccess(Void result) {
                        project.setName(name);

                        updateProject(project, projectDescriptor, callback);
                    }

                    @Override
                    protected void onFailure(Throwable exception) {
                        callback.onFailure(exception);
                    }
                });
            }

        } else {
            createProject(callback, projectDescriptor, name);
        }


    }

    private void updateProject(final Project project, ProjectDescriptor projectDescriptor, final CommitCallback callback) {
        projectServiceClient.updateProject(project.getPath(), projectDescriptor, new AsyncRequestCallback<ProjectDescriptor>() {
            @Override
            protected void onSuccess(ProjectDescriptor result) {
                resourceProvider.getProject(project.getName(), new AsyncCallback<Project>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        callback.onFailure(caught);
                    }

                    @Override
                    public void onSuccess(Project result) {
                        callback.onSuccess();
                    }
                });
            }

            @Override
            protected void onFailure(Throwable exception) {
                callback.onFailure(exception);
            }
        });
    }

    private void createProject(final CommitCallback callback, ProjectDescriptor projectDescriptor, final String name) {
        projectServiceClient
                .createProject(name, projectDescriptor,
                        new AsyncRequestCallback<ProjectDescriptor>() {


                            @Override
                            protected void onSuccess(ProjectDescriptor result) {
                                resourceProvider.getProject(name, new AsyncCallback<Project>() {
                                    @Override
                                    public void onSuccess(Project project) {
                                        createFiles(project);// Continue after successfully creating the project
                                        callback.onSuccess();
                                    }

                                    @Override
                                    public void onFailure(Throwable caught) {
                                        callback.onFailure(caught);
                                    }
                                });
                            }

                            @Override
                            protected void onFailure(Throwable exception) {
                                callback.onFailure(exception);
                            }
                        }
                );
   }

    private void createFiles(Project project){

        projectServiceClient.createFolder(project.getName()+"/src", new FolderCreationCallBack());
        projectServiceClient.createFolder(project.getName()+"/resources", new FolderCreationCallBack());
        //projectServiceClient.createFile(name, "pom.xml", "testingContent","text/xml", new FolderCreationCallBack(callback));

    }

    class FolderCreationCallBack extends AsyncRequestCallback<Void> {

        @Override
        protected void onSuccess(Void result) {

        }

        @Override
        protected void onFailure(Throwable exception) {

        }
    }
}
