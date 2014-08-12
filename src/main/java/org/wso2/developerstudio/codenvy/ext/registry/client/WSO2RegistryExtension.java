package org.wso2.developerstudio.codenvy.ext.registry.client;

import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.notification.NotificationManager;
import com.codenvy.ide.api.ui.wizard.ProjectTypeWizardRegistry;
import com.codenvy.ide.api.ui.wizard.ProjectWizard;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.typedarrays.client.JsUtils;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.wso2.developerstudio.codenvy.ext.registry.client.wizard.maven.MavenSettingsPagePresenter;
import org.wso2.developerstudio.codenvy.ext.registry.client.wizard.maven.MavenSettingsPageView;
import org.wso2.developerstudio.codenvy.ext.registry.client.wizard.resource.ResourceCreationPagePresenter;
import org.wso2.developerstudio.codenvy.ext.registry.client.wizard.resource.ResourceCreationPageView;
import org.wso2.developerstudio.codenvy.ext.registry.shared.Constants;

/** WSO2 Registry Extension. */
@Singleton
@Extension(title = "WSO2 Registry extension", version = "1.0.0")
public class WSO2RegistryExtension {

    @Inject
    public WSO2RegistryExtension(NotificationManager notificationManager,
                                 WSO2RegistryExtensionResources resources,
                                 ProjectTypeWizardRegistry wizardRegistry,
                                 Provider<MavenSettingsPagePresenter> mavenSettingsPagePresenter,
                                 Provider<ResourceCreationPagePresenter> resourceCreationPagePresenter) {

        ProjectWizard wizard = new ProjectWizard(notificationManager);
        wizard.addPage(mavenSettingsPagePresenter);
        wizard.addPage(resourceCreationPagePresenter);
        wizardRegistry.addWizard(Constants.WSO2_REGISTRY_PROJECT_ID, wizard);

        ScriptInjector.fromString(resources.jqueryLib().getText()).inject();
        ScriptInjector.fromString(resources.jqueryUILib().getText()).inject();
        ScriptInjector.fromString(resources.jsPlumbLib().getText()).inject();

    }
}