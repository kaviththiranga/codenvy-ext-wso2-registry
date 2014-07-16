package org.wso2.developerstudio.codenvy.ext.registry.client;

import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.notification.NotificationManager;
import com.codenvy.ide.api.ui.wizard.ProjectTypeWizardRegistry;
import com.codenvy.ide.api.ui.wizard.ProjectWizard;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.wso2.developerstudio.codenvy.ext.registry.client.wizard.MavenSettingsPagePresenter;
import org.wso2.developerstudio.codenvy.ext.registry.client.wizard.MavenSettingsPageView;
import org.wso2.developerstudio.codenvy.ext.registry.shared.Constants;

/** WSO2 Registry Extension. */
@Singleton
@Extension(title = "WSO2 Registry extension", version = "1.0.0")
public class WSO2RegistryExtension {

    @Inject
    public WSO2RegistryExtension(NotificationManager notificationManager,  ProjectTypeWizardRegistry wizardRegistry,
                                 Provider<MavenSettingsPagePresenter> mavenSettingsPagePresenter) {

        ProjectWizard wizard = new ProjectWizard(notificationManager);
        wizard.addPage(mavenSettingsPagePresenter);
        wizardRegistry.addWizard(Constants.WSO2_REGISTRY_PROJECT_ID, wizard);
    }
}