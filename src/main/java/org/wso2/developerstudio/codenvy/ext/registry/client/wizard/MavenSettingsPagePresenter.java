package org.wso2.developerstudio.codenvy.ext.registry.client.wizard;

import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import javax.annotation.Nullable;

/**
 * Created by kavith on 7/16/14.
 */
public class MavenSettingsPagePresenter extends AbstractWizardPage implements MavenSettingsPageView.ActionDelegate {

    /**
     * Create wizard page with given caption and image.
     *
     */
    @Inject
    public MavenSettingsPagePresenter() {
        super("Maven settings", null);
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

    }

    @Override
    public boolean canSkip() {
        return false;
    }

}
