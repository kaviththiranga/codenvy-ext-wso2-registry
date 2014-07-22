package org.wso2.developerstudio.codenvy.ext.registry.server.project;

import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.server.ProjectTypeExtension;
import com.codenvy.api.project.shared.Attribute;
import com.codenvy.api.project.shared.ProjectTemplateDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Arrays;
import java.util.List;

import org.wso2.developerstudio.codenvy.ext.registry.shared.Constants;

import javax.inject.Named;

/**
 * Created by kavith on 7/15/14.
 */
@Singleton
public class RegistryProjectTypeExtension implements ProjectTypeExtension{

    private final String baseUrl;

    @Inject
    public RegistryProjectTypeExtension(@Named("extension-url") String baseUrl, ProjectTypeDescriptionRegistry registry) {
        this.baseUrl = baseUrl;
        registry.registerProjectType(this);
    }

    @Override
    public ProjectType getProjectType() {
        return new ProjectType(Constants.WSO2_REGISTRY_PROJECT_ID, Constants.WSO2_REGISTRY_PROJECT, Constants.WSO2_PROJECT_ID);
    }

    @Override
    public List<Attribute> getPredefinedAttributes() {
        return Arrays.asList(
                new Attribute(Constants.LANGUAGE, Constants.WSO2_PROJECT_ID),
                new Attribute(Constants.FRAMEWORK, Constants.WSO2_PROJECT_ID),
                new Attribute(Constants.BUILDER_NAME, "maven")
        );
    }

    @Override
    public List<ProjectTemplateDescription> getTemplates() {
        return Arrays.asList(new ProjectTemplateDescription("zip",
                Constants.WSO2_REGISTRY_PROJECT,
                "This is a simple Registry resources project.",
                baseUrl+"templates/registry-project.zip"));
    }
}
